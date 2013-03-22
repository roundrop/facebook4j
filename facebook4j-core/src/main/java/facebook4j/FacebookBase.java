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

import facebook4j.auth.Authorization;
import facebook4j.conf.Configuration;


/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface FacebookBase {
    /**
     * Returns the ID of authenticating user/page.<br>
     * This method may internally call '/me' on the first invocation if<br>
     * - this instance is authenticated by OAuth.<br>
     *
     * @return the ID of authenticating user/page
     * @throws FacebookException     when '/me' threw an exception.
     * @throws IllegalStateException if no credentials are supplied. i.e.) this is an anonymous Facebook instance
     */
    String getId() throws FacebookException, IllegalStateException;

    /**
     * Returns authenticating user's/page's name.<br>
     * This method may internally call '/me' on the first invocation if<br>
     * - this instance is authenticated by OAuth.<br>
     *
     * @return the authenticating user's/page's name
     * @throws FacebookException     when '/me' threw an exception.
     * @throws IllegalStateException if no credentials are supplied. i.e.) this is an anonymous Facebook instance
     */
    String getName() throws FacebookException, IllegalStateException;
    
    /**
     * Returns the authorization scheme for this instance.<br>
     * The returned type will be either of BasicAuthorization, OAuthAuthorization, or NullAuthorization
     *
     * @return the authorization scheme for this instance
     */
    Authorization getAuthorization();

    /**
     * Returns the configuration associated with this instance
     *
     * @return configuration associated with this instance
     */
    Configuration getConfiguration();

    /**
     * Fetches the data of the next page.
     * 
     * @param paging paging information of Graph API result
     * @return the data of the next page
     * @throws FacebookException when Facebook service or network is unavailable
     */
    <T> ResponseList<T> fetchNext(Paging<T> paging) throws FacebookException;

    /**
     * Fetches the data of the previous page.
     * 
     * @param paging paging information of Graph API result
     * @return the data of the previous page
     * @throws FacebookException when Facebook service or network is unavailable
     */
    <T> ResponseList<T> fetchPrevious(Paging<T> paging) throws FacebookException;

    /**
     * Shuts down this instance and releases allocated resources.
     */
    void shutdown();
}
