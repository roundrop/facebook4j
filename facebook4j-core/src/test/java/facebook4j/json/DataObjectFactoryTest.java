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

package facebook4j.json;

import facebook4j.FacebookFactory;
import facebook4j.FacebookTestBase;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class DataObjectFactoryTest extends FacebookTestBase {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        Configuration conf = new ConfigurationBuilder().setOAuthAppId(appId).setOAuthAppSecret(appSecret)
                                                       .setJSONStoreEnabled(true)
                                                       .build();
        facebook1 = new FacebookFactory().getInstance(new OAuthAuthorization(conf));
        facebook1.setOAuthAccessToken(new AccessToken(id1.getAccessToken(), null));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRawJSON() throws Exception {
        ResponseList<Post> posts = facebook1.getPosts();
        String rawJSON = DataObjectFactory.getRawJSON(posts);
        assertThat(rawJSON, is(notNullValue()));
        System.out.println(rawJSON);
    }

}
