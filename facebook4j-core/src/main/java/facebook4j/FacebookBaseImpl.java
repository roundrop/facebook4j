/*
 * Copyright 2012 Ryuji Yamashita
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package facebook4j;

import facebook4j.auth.AccessToken;
import facebook4j.auth.AuthOption;
import facebook4j.auth.Authorization;
import facebook4j.auth.DeviceCode;
import facebook4j.auth.NullAuthOption;
import facebook4j.auth.NullAuthorization;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpClientWrapper;
import facebook4j.internal.json.z_F4JInternalFactory;
import facebook4j.internal.json.z_F4JInternalJSONImplFactory;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Base class of Facebook supports OAuth.
 *
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
abstract class FacebookBaseImpl implements Serializable, OAuthSupport {
    private static final long serialVersionUID = 5812835429308976884L;

    protected transient HttpClientWrapper http;
    protected z_F4JInternalFactory factory;

    protected Configuration conf;
    protected Authorization auth;
    
    protected transient String id;
    protected transient String name;

    /*package*/ FacebookBaseImpl(Configuration conf, Authorization auth) {
        this.conf = conf;
        this.auth = auth;
        init();
    }

    private void init() {
        if (null == auth) {
            // try to populate OAuthAuthorization if available in the configuration
            String appId = conf.getOAuthAppId();
            String appSecret = conf.getOAuthAppSecret();
            // try to find oauth tokens in the configuration
            if (appId != null && appSecret != null) {
                OAuthAuthorization oauth = new OAuthAuthorization(conf);
                String accessToken = conf.getOAuthAccessToken();
                String callbackURL = conf.getOAuthCallbackURL();
                if (accessToken != null) {
                    if (callbackURL != null) {
                        oauth.setOAuthAccessToken(new AccessToken(accessToken, null), callbackURL);
                    } else {
                        oauth.setOAuthAccessToken(new AccessToken(accessToken, null));
                    }
                }
                this.auth = oauth;
            } else {
                this.auth = NullAuthorization.getInstance();
            }
        }
        setHttp();
        setFactory();
    }

    protected void setHttp() {
        http = new HttpClientWrapper(conf);
    }

    protected void setFactory() {
        factory = new z_F4JInternalJSONImplFactory(conf);
    }

    /**
     * {@inheritDoc}
     */
    public String getId() throws FacebookException, IllegalStateException {
        if (!auth.isEnabled()) {
            throw new IllegalStateException(
                    "Neither user ID/password combination nor OAuth app ID/secret combination supplied");
        }
        if (id == null) {
            fillInIDAndName();
        }
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() throws FacebookException, IllegalStateException {
        if (!auth.isEnabled()) {
            throw new IllegalStateException(
                    "Neither user ID/password combination nor OAuth app ID/secret combination supplied");
        }
        if (name == null) {
            fillInIDAndName();
        }
        return name;
    }

    protected void fillInIDAndName() throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = http.get(conf.getRestBaseURL() + "me?fields=id,name", auth).asJSONObject();
        try {
            this.id = json.getString("id");
            this.name = json.getString("name");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final Authorization getAuthorization() {
        return auth;
    }

    /**
     * {@inheritDoc}
     */
    public Configuration getConfiguration() {
        return this.conf;
    }

    /**
     * {@inheritDoc}
     */
    public void shutdown() {
        if (http != null) http.shutdown();
    }

    protected final void ensureAuthorizationEnabled() {
        if (!auth.isEnabled()) {
            throw new IllegalStateException("Authentication credentials are missing.");
        }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        // http://docs.oracle.com/javase/6/docs/platform/serialization/spec/output.html#861
        out.putFields();
        out.writeFields();

        out.writeObject(conf);
        out.writeObject(auth);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        // http://docs.oracle.com/javase/6/docs/platform/serialization/spec/input.html#2971
        stream.readFields();

        conf = (Configuration) stream.readObject();
        auth = (Authorization) stream.readObject();
        http = new HttpClientWrapper(conf);
        setFactory();
    }


    // methods declared in OAuthSupport interface

    /**
     * {@inheritDoc}
     */
    public void setOAuthAppId(String appId, String appSecret) {
        if (appId == null) {
            throw new NullPointerException("app id is null");
        }
        if (appSecret == null) {
            throw new NullPointerException("app secret is null");
        }
        if (auth instanceof NullAuthorization) {
            OAuthAuthorization oauth = new OAuthAuthorization(conf);
            oauth.setOAuthAppId(appId, appSecret);
            this.auth = oauth;
        } else
        if (auth instanceof OAuthAuthorization) {
            throw new IllegalStateException("app id/secret pair already set.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setOAuthPermissions(String permissions) {
        getOAuth().setOAuthPermissions(permissions);
    }

    /**
     * {@inheritDoc}
     */
    public String getOAuthAuthorizationURL(String callbackURL) {
        return getOAuthAuthorizationURL(callbackURL, new NullAuthOption());
    }
    
    /**
     * {@inheritDoc}
     */
    public String getOAuthAuthorizationURL(String callbackURL, String state) {
        return getOAuth().getOAuthAuthorizationURL(callbackURL, state);
    }

    public String getOAuthAuthorizationURL(String callbackURL, AuthOption authOption) {
        return getOAuth().getOAuthAuthorizationURL(callbackURL, authOption);
    }

    public String getOAuthReAuthenticationURL(String callbackURL, String nonce) {
        return getOAuth().getOAuthReAuthenticationURL(callbackURL, nonce);
    }

    /**
     * {@inheritDoc}
     */
    public AccessToken getOAuthAccessToken() {
        return getOAuth().getOAuthAccessToken();
    }

    /**
     * {@inheritDoc}
     */
    synchronized public AccessToken getOAuthAccessToken(String oauthCode) throws FacebookException {
        return getOAuth().getOAuthAccessToken(oauthCode);
    }

    /**
     * {@inheritDoc}
     */
    synchronized public AccessToken getOAuthAccessToken(String oauthCode, String callbackURL) throws FacebookException {
        return getOAuth().getOAuthAccessToken(oauthCode, callbackURL);
    }

    /**
     * {@inheritDoc}
     */
    public AccessToken getOAuthAppAccessToken() throws FacebookException {
        return getOAuth().getOAuthAppAccessToken();
    }

    synchronized public DeviceCode getOAuthDeviceCode() throws FacebookException {
        return getOAuth().getOAuthDeviceCode();
    }

    synchronized public AccessToken getOAuthDeviceToken(DeviceCode deviceCode) throws FacebookException {
        return getOAuth().getOAuthDeviceToken(deviceCode);
    }
    
    /**
     * {@inheritDoc}
     */
    public void setOAuthAccessToken(AccessToken accessToken) {
        getOAuth().setOAuthAccessToken(accessToken);
        
    }

    /**
     * {@inheritDoc}
     */
    public String getOAuthCallbackURL() {
        return getOAuth().getOAuthCallbackURL();
    }

    /**
     * {@inheritDoc}
     */
    public void setOAuthCallbackURL(String callbackURL) {
        getOAuth().setOAuthCallbackURL(callbackURL);
    }

    public AccessToken extendTokenExpiration(String shortLivedToken) throws FacebookException {
        return getOAuth().extendTokenExpiration(shortLivedToken);
    }

    public AccessToken extendTokenExpiration() throws FacebookException {
        return extendTokenExpiration(getOAuthAccessToken().getToken());
    }

    public AccessToken getOAuthAccessTokenInfo(String accessToken) throws FacebookException {
        return getOAuth().getOAuthAccessTokenInfo(accessToken);
    }

    public AccessToken getOAuthAccessTokenInfo() throws FacebookException {
        return getOAuth().getOAuthAccessTokenInfo();
    }

    private OAuthSupport getOAuth() {
        if (!(auth instanceof OAuthSupport)) {
            throw new IllegalStateException("OAuth app id/secret combination not supplied");
        }
        return (OAuthSupport) auth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FacebookBaseImpl other = (FacebookBaseImpl) obj;
        if (auth == null) {
            if (other.auth != null)
                return false;
        } else if (!auth.equals(other.auth))
            return false;
        if (conf == null) {
            if (other.conf != null)
                return false;
        } else if (!conf.equals(other.conf))
            return false;
        if (http == null) {
            if (other.http != null)
                return false;
        } else if (!http.equals(other.http))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auth == null) ? 0 : auth.hashCode());
        result = prime * result + ((conf == null) ? 0 : conf.hashCode());
        result = prime * result + ((http == null) ? 0 : http.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "FacebookBaseImpl [http=" + http + ", conf=" + conf + ", auth="
                + auth + "]";
    }

}
