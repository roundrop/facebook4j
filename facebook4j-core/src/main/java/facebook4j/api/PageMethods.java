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

import facebook4j.*;

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
<<<<<<< HEAD
     * Returns the url of a page's profile picture.
     * This method requires page access_token.
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - picture
     */
    URL getPagePictureURL() throws FacebookException;

    /**
     * Returns the url of a page's profile picture.
     * This method requires page access_token.
     * @param size {@link facebook4j.PictureSize picture size}
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - picture
     */
    URL getPagePictureURL(PictureSize size) throws FacebookException;

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
     * Returns the current Page's own posts, including unpublished and scheduled posts.
     * This method requires page access_token.
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPromotablePosts() throws FacebookException;

    /**
     * Returns the current Page's own posts, including unpublished and scheduled posts.
     * This method requires page access_token.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPromotablePosts(Reading reading) throws FacebookException;

    /**
     * Returns the Page's own posts, including unpublished and scheduled posts.
     * @param pageId the ID of the page
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPromotablePosts(String pageId) throws FacebookException;

    /**
     * Returns the Page's own posts, including unpublished and scheduled posts.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Post> getPromotablePosts(String pageId, Reading reading) throws FacebookException;

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
     * Posts a photo to the current page's wall.
     * @param pageId the ID of a page
     * @param pagePhotoUpdate photo content, targeting and more
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#photos">Page#photos - Facebook Developers</a>
     */
    String postPagePhoto(String pageId, PagePhotoUpdate pagePhotoUpdate) throws FacebookException;

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
