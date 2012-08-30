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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import facebook4j.auth.AccessToken;

public class GraphAPITestBase extends FacebookTestBase {

    protected TestUser testUser1;
    protected TestUser testUser2;
    
    @BeforeClass
    public static void setUpGraphAPITestBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownGraphAPITestAfterClass() throws Exception {
    }

    @Before
    public void setUpGraphAPITest() throws Exception {
        testUser1 = createTestUser(facebook1, getAppAccessToken(facebook1));
        facebook1.setOAuthAccessToken(new AccessToken(testUser1.getAccessToken(), null));
        testUser2 = createTestUser(facebook2, getAppAccessToken(facebook2));
        facebook2.setOAuthAccessToken(new AccessToken(testUser2.getAccessToken(), null));
    }

    @After
    public void tearDownGraphAPITest() throws Exception {
        deleteTestUser(facebook1, testUser1);
        deleteTestUser(facebook2, testUser2);
    }

}
