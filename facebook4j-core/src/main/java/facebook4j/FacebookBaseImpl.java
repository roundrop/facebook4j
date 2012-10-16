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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import facebook4j.auth.AccessToken;
import facebook4j.auth.Authorization;
import facebook4j.auth.NullAuthorization;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpClientWrapper;
import facebook4j.internal.json.z_F4JInternalFactory;
import facebook4j.internal.json.z_F4JInternalJSONImplFactory;

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
    protected transient String email;

    /*package*/ FacebookBaseImpl(Configuration conf, Authorization auth) {
        this.conf = conf;
        this.auth = auth;
        init();
    }

    private void init() {
        if (null == auth) {
            // try to populate OAuthAuthorization if available in the configuration
            String consumerKey = conf.getOAuthAppId();
            String consumerSecret = conf.getOAuthAppSecret();
            // try to find oauth tokens in the configuration
            if (consumerKey != null && consumerSecret != null) {
                OAuthAuthorization oauth = new OAuthAuthorization(conf);
                String accessToken = conf.getOAuthAccessToken();
                if (accessToken != null) {
                    oauth.setOAuthAccessToken(new AccessToken(accessToken, null));
                }
                this.auth = oauth;
            } else {
                this.auth = NullAuthorization.getInstance();
            }
        }
        http = new HttpClientWrapper(conf);
        setFactory();
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
            fillInIDAndNameAndEmail();
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
            fillInIDAndNameAndEmail();
        }
        return name;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getEmail() throws FacebookException, IllegalStateException {
        if (!auth.isEnabled()) {
            throw new IllegalStateException(
                    "Neither user ID/password combination nor OAuth app ID/secret combination supplied");
        }
        if (email == null) {
            fillInIDAndNameAndEmail();
        }
        return email;
    }

    protected void fillInIDAndNameAndEmail() throws FacebookException {
        ensureAuthorizationEnabled();
        User me = factory.createUser(http.get(conf.getRestBaseURL() + "me", auth));
        this.id = me.getId();
        this.name = me.getName();
        this.email = me.getEmail();
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
        out.writeObject(conf);
        out.writeObject(auth);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
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
        return getOAuthAuthorizationURL(callbackURL, null);
    }
    
    /**
     * {@inheritDoc}
     */
    public String getOAuthAuthorizationURL(String callbackURL, String state) {
        return getOAuth().getOAuthAuthorizationURL(callbackURL, state);
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
    public AccessToken getOAuthAppAccessToken() throws FacebookException {
        return getOAuth().getOAuthAppAccessToken();
    }
    
    /**
     * {@inheritDoc}
     */
    public void setOAuthAccessToken(AccessToken accessToken) {
        getOAuth().setOAuthAccessToken(accessToken);
        
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
