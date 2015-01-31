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

package facebook4j.api;

import facebook4j.FacebookException;
import facebook4j.ResponseList;
import facebook4j.TestUser;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface TestUserMethods {
    /**
     * Creates a test user associated with a particular app.
     * @param appId the ID of the app
     * @return Test User
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/test_users/">Test Users - Facebook Developers</a>
     */
    TestUser createTestUser(String appId) throws FacebookException;

    /**
     * Creates a test user associated with a particular app.
     * @param appId the ID of the app
     * @param name test user name
     * @param locale locale
     * @param permissions comma-separated list of permissions
     * @return Test User
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/test_users/">Test Users - Facebook Developers</a>
     */
    TestUser createTestUser(String appId, String name, String locale, String permissions) throws FacebookException;

    /**
     * Creates a test user associated with a particular app.
     * @param appId the ID of the app
     * @param name test user name
     * @param locale locale
     * @param permissions comma-separated list of permissions
     * @param installed automatically installs the app for the test user once it is created
     * @return Test User
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/test_users/">Test Users - Facebook Developers</a>
     */
    TestUser createTestUser(String appId, String name, String locale, String permissions, boolean installed) throws FacebookException;


    /**
     * Returns test users associated with a particular app.
     * This method can't manage paging
     * @param appId the ID of the app
     * @return Test Users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/test_users/">Test Users - Facebook Developers</a>
     */
    ResponseList<TestUser> getTestUsers(String appId) throws FacebookException;

    /**
     * Returns test users associated with a particular app. 
     * This method manage paging, call facebook.fetchNext to get next page
     * @param appId the ID of the app
     * @param limit number of elements per page (can be null to use default page size)
     * @return Test Users first page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/test_users/">Test Users - Facebook Developers</a>
     */
    ResponseList<TestUser> getTestUsers(String appId, Integer limit) throws FacebookException;
    
    /**
     * Deletes the test user.
     * @param testUserId the ID of the test user
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/test_users/">Test Users - Facebook Developers</a>
     */
    boolean deleteTestUser(String testUserId) throws FacebookException;

    /**
     * Makes friends connections for a test user with other test users.
     * @param testUser1 test user
     * @param testUser2 test user
     * @return true on success, false otherwise
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/test_users/">Test Users - Facebook Developers</a>
     */
    boolean makeFriendTestUser(TestUser testUser1, TestUser testUser2) throws FacebookException;

}
