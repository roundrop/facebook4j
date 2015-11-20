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
import facebook4j.internal.logging.Logger;
import facebook4j.internal.util.z_F4JLRUCache;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * 
 * @see <a href="http://oauth.net/core/1.0a/">OAuth Core 1.0a</a>
 */
public class OAuthAuthorization implements Authorization, OAuthSupport, Security, java.io.Serializable {
    private static final long serialVersionUID = 2548925849295080874L;

    private static final Logger logger = Logger.getLogger(OAuthAuthorization.class);

    public static final String HMAC_SHA_256 = "HmacSHA256";

    private final Configuration conf;
    private transient static HttpClientWrapper http;

    private String appId = "";
    private String appSecret;
    private AccessToken oauthToken;
    private String permissions;
    private String callbackURL;
    private boolean appSecretProofEnabled;
    private transient z_F4JLRUCache<String, String> appSecretProofCache;

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
        setAppSecretProofEnabled(conf.isAppSecretProofEnabled());
        appSecretProofCache = new z_F4JLRUCache<String, String>(conf.getAppSecretProofCacheSize());
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
        return getOAuthAuthorizationURL(callbackURL, new NullAuthOption());
    }

    public String getOAuthAuthorizationURL(String callbackURL, String state) {
        return getOAuthAuthorizationURL(callbackURL, new DialogAuthOption().state(state));
    }

    public String getOAuthAuthorizationURL(String callbackURL, AuthOption authOption) {
        this.callbackURL = callbackURL;
        String url = conf.getOAuthAuthorizationURL() +
                "?client_id=" + this.appId +
                "&redirect_uri=" + callbackURL;
        if (this.permissions != null) {
            url += "&scope=" + this.permissions;
        }
        if (authOption != null) {
            url += authOption.getQuery("&");
        }
        return url;
    }

    public String getOAuthReAuthenticationURL(String callbackURL, String nonce) {
        if (nonce == null) {
            logger.warn("strongly encourage to use 'nonce' parameter, especially when requesting reauthenticate.");
        }
        return getOAuthAuthorizationURL(callbackURL,
                new DialogAuthOption().authType(AuthType.REAUTHENTICATE).authNonce(nonce));
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

    public DeviceCode getOAuthDeviceCode() throws FacebookException {
        String url = getDeviceCodeURL();
        HttpResponse response = http.get(url);
        if (response.getStatusCode() != 200) {
            throw new FacebookException("generate a device code failed");
        }
        return new DeviceCode(response);
    }

    private String getDeviceCodeURL() {
        return conf.getOAuthDeviceTokenURL() +
                "?type=device_code" +
                "&client_id=" + this.appId +
                "&scope=" + this.permissions;
    }

    public AccessToken getOAuthDeviceToken(DeviceCode deviceCode) throws FacebookException {
        String url = getDeviceTokenURL(deviceCode.getCode());
        HttpResponse response = http.get(url);
        if (response.getStatusCode() != 200) {
            throw new FacebookException("authorization failed.");
        }
        this.oauthToken = new AccessToken(response);
        return this.oauthToken;
    }

    private String getDeviceTokenURL(String deviceCode) {
        return conf.getOAuthDeviceTokenURL() +
                "?type=device_token" +
                "&client_id=" + this.appId +
                "&code=" + deviceCode;
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

    public AccessToken extendTokenExpiration(String shortLivedToken) throws FacebookException {
        String url = getExtendTokenURL(shortLivedToken);
        HttpResponse response = http.get(url);
        if (response.getStatusCode() != 200) {
            throw new FacebookException("token expiration extention failed.");
        }
        this.oauthToken = new AccessToken(response);
        return this.oauthToken;
    }

    public AccessToken extendTokenExpiration() throws FacebookException {
        return extendTokenExpiration(this.oauthToken.getToken());
    }

    protected String getExtendTokenURL(String shortLivedToken) {
        return conf.getOAuthAccessTokenURL() +
                "?grant_type=fb_exchange_token" +
                "&client_id=" + this.appId +
                "&client_secret=" + this.appSecret +
                "&fb_exchange_token=" + shortLivedToken;
    }

    public AccessToken getOAuthAccessTokenInfo(String accessToken) throws FacebookException {
        String url = getAccessTokenInfoURL(accessToken);
        HttpResponse response = http.get(url);
        if (response.getStatusCode() != 200) {
            throw new FacebookException("authorization failed.");
        }
        return new AccessToken(response);
    }

    public AccessToken getOAuthAccessTokenInfo() throws FacebookException {
        return getOAuthAccessTokenInfo(this.oauthToken.getToken());
    }

    protected String getAccessTokenInfoURL(String accessToken) {
        return conf.getOAuthAccessTokenInfoURL() +
                "?client_id=" + this.appId +
                "&access_token=" + accessToken;
    }


    public void setAppSecretProofEnabled(boolean enabled) {
        this.appSecretProofEnabled = enabled;
    }

    public boolean isAppSecretProofEnabled() {
        return appSecretProofEnabled;
    }

    // implementations for Security

    /**
     * Computes a appsecret_proof value using the HMAC method.
     * 
     * @return appsecret_proof
     * @see <a href="https://developers.facebook.com/docs/graph-api/securing-requests/#appsecret_proof">Verifying Graph API Calls with appsecret_proof</a>
     */
    public String generateAppSecretProof() {
        if (appSecret == null || !isEnabled()) {
            throw new IllegalStateException("App Secret and Access Token are required.");
        }

        String accessToken = oauthToken.getToken();
        String cache = appSecretProofCache.get(accessToken);
        if (cache != null) {
            return cache;
        }
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance(HMAC_SHA_256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(appSecret.getBytes("UTF-8"), HMAC_SHA_256);
            mac.init(secretKeySpec);
            byteHMAC = mac.doFinal(accessToken.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException ignore) {
        } catch (UnsupportedEncodingException ignore) {
        } catch (InvalidKeyException e) {
            throw new IllegalStateException();
        }

        StringBuilder result = new StringBuilder();
        for (byte b : byteHMAC) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        String appSecretProof = result.toString();
        appSecretProofCache.put(accessToken, appSecretProof);
        return appSecretProof;
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
