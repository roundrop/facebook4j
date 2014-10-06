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
import facebook4j.conf.Configuration;
import facebook4j.junit.FacebookAPIVersion;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class MockFacebookTestBase extends FacebookTestBase {
    @Rule
    public TestName name = new TestName();

    protected MockFacebook facebook;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        facebook = MockFacebookFactory.create();

        String appId = p.getProperty("oauth.appId");
        String appSecret = p.getProperty("oauth.appSecret");
        String accessToken = p.getProperty("oauth.accessToken");
        String appSecretProofEnabled = p.getProperty("security.appSecretProofEnabled");
        if (appId != null && appSecret != null) {
            facebook.setOAuthAppId(appId, appSecret);
            if (accessToken == null) {
                facebook.getOAuthAppAccessToken();
            }
        }
        if (accessToken != null) {
            facebook.setOAuthAccessToken(new AccessToken(accessToken));
        }
        if (appSecretProofEnabled != null) {
            facebook.setAppSecretProofEnabled(Boolean.valueOf(appSecretProofEnabled));
        }

        // @FacebookAPIVersion
        String restBaseURL = "https://graph.facebook.com/";
        Method method = this.getClass().getMethod(this.name.getMethodName(), new Class[0]);
        FacebookAPIVersion annotation = method.getAnnotation(FacebookAPIVersion.class);
        if (annotation != null) {
            String apiVersion = annotation.value();
            restBaseURL += apiVersion + "/";
        }
        Configuration conf = ((FacebookBaseImpl) facebook).conf;
        Field field = conf.getClass().getSuperclass().getDeclaredField("restBaseURL");
        field.setAccessible(true);
        field.set(conf, restBaseURL);
    }

}
