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

import facebook4j.conf.Configuration;

/**
 * A static factory class for Authorization.
 * 
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class AuthorizationFactory {

    public static Authorization getInstance(Configuration conf) {
        Authorization auth = null;
        String appId = conf.getOAuthAppId();
        String appSecret = conf.getOAuthAppSecret();

        if (appId != null && appSecret != null) {
            OAuthAuthorization oauth = new OAuthAuthorization(conf);
            String accessToken = conf.getOAuthAccessToken();
            if (accessToken != null) {
                oauth.setOAuthAccessToken(new AccessToken(accessToken, null));
            }
            auth = oauth;
        } else {
            auth = NullAuthorization.getInstance();
        }
        return auth;
    }
}
