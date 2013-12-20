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
import facebook4j.conf.ConfigurationBuilder;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class OAuthAuthorizationTest {

    public static class AppSecretProof {
        @Test
        public void defaultCacheSize() throws Exception {
            Configuration conf = new ConfigurationBuilder()
                    .setAppSecretProofEnabled(true)
                    .setOAuthAppSecret("1234567890123456")
                    .build();
            OAuthAuthorization auth = new OAuthAuthorization(conf);
            auth.setOAuthAccessToken(new AccessToken("access_token1"));
            String proof1 = auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token2"));
            String proof2 = auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token3"));
            auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token4"));
            auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token5"));
            auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token6"));
            auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token7"));
            auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token8"));
            auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token9"));
            auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token10"));
            auth.generateAppSecretProof();

            auth.setOAuthAppId("", "change appSecret value for proof compute result");
            auth.setOAuthAccessToken(new AccessToken("access_token1"));
            String cachedProof1 = auth.generateAppSecretProof();
            assertThat(proof1, is(cachedProof1));   // cache hit

            auth.setOAuthAccessToken(new AccessToken("access_token11"));
            auth.generateAppSecretProof();

            auth.setOAuthAccessToken(new AccessToken("access_token2"));
            String reComputedProof2 = auth.generateAppSecretProof();
            assertThat(reComputedProof2, is(not(proof2)));   // re-computed
        }

        @Test
        public void customCacheSize() throws Exception {
            Configuration conf = new ConfigurationBuilder()
                    .setAppSecretProofEnabled(true)
                    .setOAuthAppSecret("1234567890123456")
                    .setAppSecretProofCacheSize(3)
                    .build();
            OAuthAuthorization auth = new OAuthAuthorization(conf);
            auth.setOAuthAccessToken(new AccessToken("access_token1"));
            String proof1 = auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token2"));
            String proof2 = auth.generateAppSecretProof();
            auth.setOAuthAccessToken(new AccessToken("access_token3"));
            auth.generateAppSecretProof();

            auth.setOAuthAppId("", "change appSecret value for proof compute result");
            auth.setOAuthAccessToken(new AccessToken("access_token1"));
            String cachedProof1 = auth.generateAppSecretProof();
            assertThat(proof1, is(cachedProof1));   // cache hit

            auth.setOAuthAccessToken(new AccessToken("access_token4"));
            auth.generateAppSecretProof();

            auth.setOAuthAccessToken(new AccessToken("access_token2"));
            String reComputedProof2 = auth.generateAppSecretProof();
            assertThat(reComputedProof2, is(not(proof2)));   // re-computed
        }
    }
}
