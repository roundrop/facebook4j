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

package facebook4j.auth;

import facebook4j.FacebookException;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpClientWrapper;
import facebook4j.internal.http.HttpResponse;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * 
 * @see <a href="http://oauth.net/core/1.0a/">OAuth Core 1.0a</a>
 */
public class OAuthAuthorization implements Authorization, java.io.Serializable, OAuthSupport {
    private static final long serialVersionUID = -5790157037375431464L;

    private final Configuration conf;
    private transient static HttpClientWrapper http;

    private String appId = "";
    private String appSecret;
    private AccessToken oauthToken;
    private String permissions;
    private String callbackURL;

    // constructors

    /**
     * @param conf Configuration
     */
    public OAuthAuthorization(Configuration conf) {
        this.conf = conf;
        http = new HttpClientWrapper(conf);
        setOAuthAppId(conf.getOAuthAppId(), conf.getOAuthAppSecret());
        if (conf.getOAuthPermissions() != null) {
            setOAuthPermissions(conf.getOAuthPermissions());
        }
        if (conf.getOAuthAccessToken() != null) {
            setOAuthAccessToken(new AccessToken(conf.getOAuthAccessToken(), null));
        }
    }

    // implementations for Authorization
    private void ensureTokenIsAvailable() {
        if (null == oauthToken) {
            throw new IllegalStateException("No Token available.");
        }
    }

    /**
     * #{inheritDoc}
     */
    public boolean isEnabled() {
        return (null != oauthToken);
    }

    // implementation for OAuthSupport interface

    public String getOAuthAuthorizationURL(String callbackURL) {
        return getOAuthAuthorizationURL(callbackURL, null);
    }
    
    public String getOAuthAuthorizationURL(String callbackURL, String state) {
        this.callbackURL = callbackURL;
        String url = conf.getOAuthAuthorizationURL() +
                        "?client_id=" + this.appId + 
                        "&redirect_uri=" + callbackURL;
        if (this.permissions != null) {
            url += "&scope=" + this.permissions;
        }
        if (state != null) {
            url += "&state=" + state;
        }
        return url;
    }

    public AccessToken getOAuthAccessToken(String oauthCode) throws FacebookException {
        String url = getExchangeAccessTokenURL(oauthCode);
        HttpResponse response = http.get(url);
        if (response.getStatusCode() != 200) {
            throw new FacebookException("authorization failed.");
        }
        this.oauthToken = new AccessToken(response);
        return this.oauthToken;
    }

    public AccessToken getOAuthAccessToken(String oauthCode, String callbackURL) throws FacebookException {
        this.callbackURL = callbackURL;
        return getOAuthAccessToken(oauthCode);
    }

    protected String getExchangeAccessTokenURL(String oauthCode) {
        return conf.getOAuthAccessTokenURL() +
                "?client_id=" + this.appId + 
                "&client_secret=" + this.appSecret + 
                "&redirect_uri=" + this.callbackURL +
                "&code=" + oauthCode;
    }

    public AccessToken getOAuthAccessToken() {
        ensureTokenIsAvailable();
        return this.oauthToken;
    }
    
    public AccessToken getOAuthAppAccessToken() throws FacebookException {
        String url = getAppAccessTokenURL();
        HttpResponse response = http.get(url);
        if (response.getStatusCode() != 200) {
            throw new FacebookException("authorization failed.");
        }
        this.oauthToken = new AccessToken(response);
        return this.oauthToken;
    }

    private String getAppAccessTokenURL() {
        return conf.getOAuthAccessTokenURL() +
                "?client_id=" + this.appId + 
                "&client_secret=" + this.appSecret + 
                "&grant_type=client_credentials";
    }

    public void setOAuthAccessToken(AccessToken accessToken) {
        this.oauthToken = accessToken;
    }

    public void setOAuthAccessToken(AccessToken accessToken, String callbackURL) {
        this.oauthToken = accessToken;
        this.callbackURL = callbackURL;
    }

    public String getOAuthCallbackURL() {
        return callbackURL;
    }

    public void setOAuthCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

    public void setOAuthAppId(String appId, String appSecret) {
        this.appId = appId != null ? appId : "";
        this.appSecret = appSecret != null ? appSecret : "";
    }

    public void setOAuthPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appId == null) ? 0 : appId.hashCode());
        result = prime * result
                + ((appSecret == null) ? 0 : appSecret.hashCode());
        result = prime * result
                + ((callbackURL == null) ? 0 : callbackURL.hashCode());
        result = prime * result + ((conf == null) ? 0 : conf.hashCode());
        result = prime * result
                + ((oauthToken == null) ? 0 : oauthToken.hashCode());
        result = prime * result
                + ((permissions == null) ? 0 : permissions.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OAuthAuthorization other = (OAuthAuthorization) obj;
        if (appId == null) {
            if (other.appId != null)
                return false;
        } else if (!appId.equals(other.appId))
            return false;
        if (appSecret == null) {
            if (other.appSecret != null)
                return false;
        } else if (!appSecret.equals(other.appSecret))
            return false;
        if (callbackURL == null) {
            if (other.callbackURL != null)
                return false;
        } else if (!callbackURL.equals(other.callbackURL))
            return false;
        if (oauthToken == null) {
            if (other.oauthToken != null)
                return false;
        } else if (!oauthToken.equals(other.oauthToken))
            return false;
        if (permissions == null) {
            if (other.permissions != null)
                return false;
        } else if (!permissions.equals(other.permissions))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OAuthAuthorization [appId=" + appId
                + ", appSecret=****************" + ", oauthToken=" + oauthToken
                + ", permissions=" + permissions + ", callbackURL="
                + callbackURL + "]";
    }

}
