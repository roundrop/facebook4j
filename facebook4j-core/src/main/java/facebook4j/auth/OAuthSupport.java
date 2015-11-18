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

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface OAuthSupport {
    /**
     * sets the OAuth AppID and App secret
     *
     * @param appId OAuth AppID
     * @param appSecret OAuth App secret
     * @throws IllegalStateException when OAuth AppId has already been set
     */
    void setOAuthAppId(String appId, String appSecret);

    /**
     * sets the permissions
     *
     * @param permissions comma-separated permission names
     * @see <a href="https://developers.facebook.com/docs/authentication/permissions/">Permissions Reference</a>
     */
    void setOAuthPermissions(String permissions);

    String getOAuthAuthorizationURL(String callbackURL);
    String getOAuthAuthorizationURL(String callbackURL, String state);

    /**
     * Returns an access token associated with this instance.
     *
     * @return access token
     * @throws IllegalStateException when this instance has no access token
     * @see <a href="https://developers.facebook.com/docs/authentication/server-side/">Server-Side Authentication</a>
     */
    AccessToken getOAuthAccessToken();

    /**
     * Exchange the code for a User Access Token.
     *
     * @param oauthCode OAuth code.
     * @return User Access Token
     * @throws FacebookException when Facebook service or network is unavailable, or the user has not authorized
     * @see <a href="https://developers.facebook.com/docs/authentication/server-side/">Server-Side Authentication</a>
     */
    AccessToken getOAuthAccessToken(String oauthCode) throws FacebookException;

    /**
     * Exchange the code for a User Access Token.
     *
     * @param oauthCode OAuth code.
     * @param callbackURL callback URL
     * @return User Access Token
     * @throws FacebookException when Facebook service or network is unavailable, or the user has not authorized
     * @see <a href="https://developers.facebook.com/docs/authentication/server-side/">Server-Side Authentication</a>
     */
    AccessToken getOAuthAccessToken(String oauthCode, String callbackURL) throws FacebookException;

    /**
     * Returns an App Access Token.
     *
     * @return App Access Token
     * @throws FacebookException when Facebook service or network is unavailable, or the user has not authorized
     * @see <a href="https://developers.facebook.com/docs/authentication/server-side/">Server-Side Authentication</a>
     */
    AccessToken getOAuthAppAccessToken() throws FacebookException;
    
    /**
     * Sets the access token
     *
     * @param accessToken access token
     */
    void setOAuthAccessToken(AccessToken accessToken);

    /**
     * Sets the access token and callback URL
     * @return Callback URL
     */
    String getOAuthCallbackURL();

    /**
     * Sets the access token
     *
     * @param callbackURL Callback URL
     */
    void setOAuthCallbackURL(String callbackURL);


    /**
     * Extends the short-lived-token expiration.
     * @param shortLivedToken access token
     * @return extended access token
     * @throws FacebookException when Facebook service or network is unavailable, or the user has not authorized
     * @see <a href="https://developers.facebook.com/docs/facebook-login/access-tokens/expiration-and-extension">Expiration and Extension of Access Tokens - Facebook Login</a>
     */
    AccessToken extendTokenExpiration(String shortLivedToken) throws FacebookException;

}
