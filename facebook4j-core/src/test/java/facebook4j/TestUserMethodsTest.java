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

import facebook4j.internal.http.RequestMethod;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class TestUserMethodsTest {

    public static class createTestUser extends MockFacebookTestBase {
        @Test
        public void appId() throws Exception {
            facebook.setMockJSON("mock_json/testuser/installed.json");
            TestUser testUser = facebook.createTestUser("appId");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "en_US"));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "true"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is("access_token"));
        }

        @Test
        public void installed_default() throws Exception {
            facebook.setMockJSON("mock_json/testuser/installed.json");
            TestUser testUser = facebook.createTestUser("appId", "test", "en_US", "user_about_me");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("name", "test"));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "en_US"));
            assertThat(facebook.getEndpointURL(), hasParameter("permissions", "user_about_me"));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "true"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is("access_token"));
        }

        @Test
        public void installed_true() throws Exception {
            facebook.setMockJSON("mock_json/testuser/installed.json");
            TestUser testUser = facebook.createTestUser("appId", "test", "en_US", "user_about_me", true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("name", "test"));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "en_US"));
            assertThat(facebook.getEndpointURL(), hasParameter("permissions", "user_about_me"));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "true"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is("access_token"));
        }

        @Test
        public void notInstalled() throws Exception {
            facebook.setMockJSON("mock_json/testuser/not_installed.json");
            TestUser testUser = facebook.createTestUser("appId", "test", "en_US", "user_about_me", false);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "false"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is(nullValue()));
        }

        public static class getTestUsers extends MockFacebookTestBase {
            @Test
            public void appId() throws Exception {
                facebook.setMockJSON("mock_json/testuser/all.json");
                ResponseList<TestUser> testUsers = facebook.getTestUsers("appId");
                assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
                assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));

                assertThat(testUsers.size(), is(5));
                assertThat(testUsers.get(0).getId(), is("1379885932316530"));
                assertThat(testUsers.get(0).getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/1379885932316530/"));
                assertThat(testUsers.get(0).getAccessToken(), is("CAAUs5XD3IK4BAPJnYxLqqKtpZBnNaqNGr4PfcCWuowZC1PWZAmCHsAW4OFhdwnz1B3ZARpCNR4ShuFoGUwZAZCAMquGfRWzvYCzpy0Q3Q6tikMpDhZCoyI3QUfuZCEdSEi8bbK9mFheRgAaWs0H4K0v1YZAKEg2j73QIeFWB3Ea7xRXuWJQUeEHvpSdRWBIAXXZAcZD"));
                assertThat(testUsers.get(4).getId(), is("1377730512532504"));
                assertThat(testUsers.get(4).getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/1377730512532504/"));
                assertThat(testUsers.get(4).getAccessToken(), is("CAAUs5XD3IK4BABZCZBgNxZAMW90NEYz4cYtkgDCYoJL6rA80LfkLJnl8hFvY7LXQkASJWfTOk9IVkU9R7MWgumkgzCspfoMYaViqrR5VsJ7BWmg6265ANwSelTZAP3EdAc6w12H2ccvSEkkYroQEh9qENhCNy1dkTeJ7GDMLAKN4n2Qp4GyWsZAaZA7t7gCf0ZD"));
            }

            @Test
            public void paging() throws Exception {
                int page = 1;
                facebook.setMockJSON("mock_json/testuser/page" + page + ".json");

                ResponseList<TestUser> testUsersResponseList = facebook.getTestUsers("appId_XXXXXXXXXXXX", 10);
                while (testUsersResponseList != null && testUsersResponseList.size() > 0) {
                    for (TestUser u : testUsersResponseList) {
                        assertThat(u.getId(), is(notNullValue()));
                        assertThat(u.getAccessToken(), is(notNullValue()));
                    }
                    Paging<TestUser> paging = testUsersResponseList.getPaging();
                    if (paging != null && paging.getNext() != null) {
                        facebook.setMockJSON("mock_json/testUser/page" + ++page + ".json");
                        testUsersResponseList = facebook.fetchNext(paging);
                    } else {
                        testUsersResponseList = null;
                    }

                }
            }
        }

    }

}
