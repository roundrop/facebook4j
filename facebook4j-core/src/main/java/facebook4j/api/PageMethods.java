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
import facebook4j.Media;
import facebook4j.Page;
import facebook4j.PageSetting;
import facebook4j.PageSettingUpdate;
import facebook4j.PageUpdate;
import facebook4j.PictureSize;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;

import java.net.URL;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 1.2.0
 */
public interface PageMethods {
    /**
     * Returns the page.
     * @param pageId the ID of the page
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    Page getPage(String pageId) throws FacebookException;

    /**
     * Returns the page.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    Page getPage(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns the url of a page's profile picture.
     * @param pageId the ID of a page
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - picture
     */
    URL getPagePictureURL(String pageId) throws FacebookException;

    /**
     * Returns the url of a page's profile picture.
     * @param pageId the ID of a page
     * @param size {@link facebook4j.PictureSize picture size}
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - picture
     */
    URL getPagePictureURL(String pageId, PictureSize size) throws FacebookException;

    /**
     * Returns the Page's wall.
     * @param pageId the ID of the page
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPageFeed(String pageId) throws FacebookException;

    /**
     * Returns the Page's wall.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPageFeed(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns the Page's own posts.
     * @param pageId the ID of the page
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPagePosts(String pageId) throws FacebookException;

    /**
     * Returns the Page's own posts.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPagePosts(String pageId, Reading reading) throws FacebookException;

    /**
     * Updates a Page's basic attributes.
     * @param pageId the ID of the page
     * @param pageUpdate the page to be updated
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a>
     */
    void updatePageBasicAttributes(String pageId, PageUpdate pageUpdate) throws FacebookException;

    /**
     * Updates the profile photo for a Page.
     * @param pageId the ID of the page
     * @param picture A URL to the photo
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Setting a Page Profile Photo
     */
    void updatePageProfilePhoto(String pageId, URL picture) throws FacebookException;

    /**
     * Updates the profile photo for a Page.
     * @param pageId the ID of the page
     * @param source Photo content
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Setting a Page Profile Photo
     */
    void updatePageProfilePhoto(String pageId, Media source) throws FacebookException;

    /**
     * Returns the settings for the page.
     * @param pageId the ID of the page
     * @return settings
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<PageSetting> getPageSettings(String pageId) throws FacebookException;

    /**
     * Updates the setting for the page.
     * @param pageId the ID of the page
     * @param pageSettingUpdate setting
     * @return true if the setting was successfully set or changed
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#settings">Page#settings - Facebook Developers</a>
     */
    boolean updatePageSetting(String pageId, PageSettingUpdate pageSettingUpdate) throws FacebookException;

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
