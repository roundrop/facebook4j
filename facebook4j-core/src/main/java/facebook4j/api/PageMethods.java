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
import facebook4j.Page;
import facebook4j.Reading;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 1.2.0
 */
public interface PageMethods {
    /**
     * Returns a specific page that the current user has liked.
     * @param pageId the ID of a page
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#likes">User#likes - Facebook Developers</a>
     */
    Page getLikedPage(String pageId) throws FacebookException;

    /**
     * Returns a specific page that the current user has liked.
     * @param pageId the ID of a page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#likes">User#likes - Facebook Developers</a>
     */
    Page getLikedPage(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns a specific page that a user has liked.
     * @param userId the ID of a user
     * @param pageId the ID of a page
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#likes">User#likes - Facebook Developers</a>
     */
    Page getLikedPage(String userId, String pageId) throws FacebookException;

    /**
     * Returns a specific page that a user has liked.
     * @param userId the ID of a user
     * @param pageId the ID of a page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#likes">User#likes - Facebook Developers</a>
     */
    Page getLikedPage(String userId, String pageId, Reading reading) throws FacebookException;
    
}
