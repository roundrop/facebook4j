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

import facebook4j.Account;
import facebook4j.FacebookException;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface AccountMethods {
    /**
     * Returns the current user's accounts of Facebook apps and pages.
     * @return accounts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - accounts
     */
    ResponseList<Account> getAccounts() throws FacebookException;

    /**
     * Returns the current user's accounts of Facebook apps and pages.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return accounts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - accounts
     */
    ResponseList<Account> getAccounts(Reading reading) throws FacebookException;

    /**
     * Returns a user's accounts of Facebook apps and pages.
     * @param userId the ID of a user
     * @return accounts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - accounts
     */
    ResponseList<Account> getAccounts(String userId) throws FacebookException;

    /**
     * Returns a user's accounts of Facebook apps and pages.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return accounts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - accounts
     */
    ResponseList<Account> getAccounts(String userId, Reading reading) throws FacebookException;

}
