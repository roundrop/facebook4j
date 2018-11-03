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

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.FacebookTestBase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AuthorizationTest extends FacebookTestBase {

    @Test
    public void anonymousInstance() throws Exception {
        Facebook facebook = new FacebookFactory().getInstance();
        Authorization auth = facebook.getAuthorization();
        assertThat(auth, instanceOf(NullAuthorization.class));
    }

    @Test
    public void OAuthInstance() throws Exception {
        String appSecret;
        String appId;
        appSecret = p.getProperty("oauth.appSecret", "fakeAppSecret");
        appId = p.getProperty("oauth.appId", "fakeAppId");

        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(appId, appSecret);
        try {
            facebook.setOAuthAppId(appSecret, appId);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException ignore) {}

        Authorization auth = facebook.getAuthorization();
        assertThat(auth, instanceOf(OAuthAuthorization.class));
    }

}
