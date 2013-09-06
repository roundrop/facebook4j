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

import facebook4j.Admin;
import facebook4j.BackdatingPostUpdate;
import facebook4j.FacebookException;
import facebook4j.Insight;
import facebook4j.Media;
import facebook4j.Milestone;
import facebook4j.MilestoneUpdate;
import facebook4j.Offer;
import facebook4j.OfferUpdate;
import facebook4j.Page;
import facebook4j.PageCoverUpdate;
import facebook4j.PagePhotoUpdate;
import facebook4j.PageSetting;
import facebook4j.PageSettingUpdate;
import facebook4j.PageUpdate;
import facebook4j.PictureSize;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Tab;
import facebook4j.TabUpdate;
import facebook4j.Tagged;
import facebook4j.User;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public interface PageMethods {
    /**
     * Returns the current page.
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    Page getPage() throws FacebookException;

    /**
     * Returns the current page.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return page
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    Page getPage(Reading reading) throws FacebookException;

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
     * @param pageUpdate the page to be updated
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a>
     */
    boolean updatePageBasicAttributes(PageUpdate pageUpdate) throws FacebookException;

    /**
     * Updates a Page's basic attributes.
     * @param pageId the ID of the page
     * @param pageUpdate the page to be updated
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a>
     */
    boolean updatePageBasicAttributes(String pageId, PageUpdate pageUpdate) throws FacebookException;

    /**
     * Updates the profile photo for the current Page.
     * @param picture A URL to the photo
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a> - Setting a Page Profile Photo
     */
    boolean updatePageProfilePhoto(URL picture) throws FacebookException;

    /**
     * Updates the profile photo for a Page.
     * @param pageId the ID of the page
     * @param picture A URL to the photo
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a> - Setting a Page Profile Photo
     */
    boolean updatePageProfilePhoto(String pageId, URL picture) throws FacebookException;

    /**
     * Updates the profile photo for the current Page.
     * @param source Photo content
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a> - Setting a Page Profile Photo
     */
    boolean updatePageProfilePhoto(Media source) throws FacebookException;

    /**
     * Updates the profile photo for a Page.
     * @param pageId the ID of the page
     * @param source Photo content
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a> - Setting a Page Profile Photo
     */
    boolean updatePageProfilePhoto(String pageId, Media source) throws FacebookException;

    /**
     * Updates the profile photo for the current page.
     * @param pageCoverUpdate the cover photo for the page to be updated
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a> - Setting a Cover Photo
     */
    boolean updatePageCoverPhoto(PageCoverUpdate pageCoverUpdate) throws FacebookException;

    /**
     * Updates the profile photo for the current page.
     * @param pageId the ID of the page
     * @param pageCoverUpdate the cover photo for the page to be updated
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a> - Setting a Cover Photo
     */
    boolean updatePageCoverPhoto(String pageId, PageCoverUpdate pageCoverUpdate) throws FacebookException;

    /**
     * Hides/Shows the page's post that is published by a non-admin user.
     * @param postId the ID of the post in the page
     * @param isHidden whether a post is hidden
     * @return true if hide/show is successful
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#attributes">Page#attributes - Facebook Developers</a> - Hiding a Page Post
     */
    boolean displayPagePost(String postId, boolean isHidden) throws FacebookException;

    /**
     * Returns the settings for the current page.
     * @return settings
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<PageSetting> getPageSettings() throws FacebookException;

    /**
     * Returns the settings for the page.
     * @param pageId the ID of the page
     * @return settings
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<PageSetting> getPageSettings(String pageId) throws FacebookException;

    /**
     * Updates the setting for the current page.
     * @param pageSettingUpdate setting
     * @return true if the setting was successfully set or changed
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#settings">Page#settings - Facebook Developers</a>
     */
    boolean updatePageSetting(PageSettingUpdate pageSettingUpdate) throws FacebookException;

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
     * Creates the post that's back-dated to the current page's wall.
     * @param backdatingPostUpdate the post to be created
     * @return The new post ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#backdating">Page#backdating - Facebook Developers</a>
     */
    String postBackdatingFeed(BackdatingPostUpdate backdatingPostUpdate) throws FacebookException;

    /**
     * Creates the post that's back-dated to a page's wall.
     * @param pageId the ID of a page
     * @param backdatingPostUpdate the post to be created
     * @return The new post ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#backdating">Page#backdating - Facebook Developers</a>
     */
    String postBackdatingFeed(String pageId, BackdatingPostUpdate backdatingPostUpdate) throws FacebookException;

    /**
     * Posts a photo to the current page's wall.
     * @param pagePhotoUpdate photo content, targeting and more
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#photos">Page#photos - Facebook Developers</a>
     */
    String postPagePhoto(PagePhotoUpdate pagePhotoUpdate) throws FacebookException;

    /**
     * Posts a photo to a page's wall.
     * @param pageId the ID of a page
     * @param pagePhotoUpdate photo content, targeting and more
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#photos">Page#photos - Facebook Developers</a>
     */
    String postPagePhoto(String pageId, PagePhotoUpdate pagePhotoUpdate) throws FacebookException;

    /**
     * Returns all children pages of a specific page.
     * @param pageId the ID of the page
     * @return pages
     * @throws FacebookException
     */
    ResponseList<Page> getGlobalBrandChildren(String pageId) throws FacebookException;

    /**
     * Returns all children pages of a specific page.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return pages
     * @throws FacebookException
     */
    ResponseList<Page> getGlobalBrandChildren(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns the page's insights data.
     * @param pageId the ID of the page
     * @return insights
     * @throws FacebookException
     */
    ResponseList<Insight> getPageInsights(String pageId) throws FacebookException;

    /**
     * Returns the page's insights data.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return pages
     * @throws FacebookException
     */
    ResponseList<Insight> getPageInsights(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns the photos, videos, and posts in which the Page has been tagged.
     * @param pageId the ID of the page
     * @return pages
     * @throws FacebookException
     */
    ResponseList<Tagged> getPageTagged(String pageId) throws FacebookException;

    /**
     * Returns the photos, videos, and posts in which the Page has been tagged.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return pages
     * @throws FacebookException
     */
    ResponseList<Tagged> getPageTagged(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns a list of the Page's milestones.
     * Note that this method requires 'page' access_token.
     * @return milestones
     * @throws FacebookException when Facebook service or network is unavailable
     */
    ResponseList<Milestone> getMilestones() throws FacebookException;

    /**
    /**
     * Returns a list of the Page's milestones.
     * Note that this method requires 'page' access_token.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return milestones
     * @throws FacebookException when Facebook service or network is unavailable
     */
    ResponseList<Milestone> getMilestones(Reading reading) throws FacebookException;

    /**
     * Returns a list of the Page's milestones.
     * @param pageId the ID of the page
     * @return milestones
     * @throws FacebookException when Facebook service or network is unavailable
     */
    ResponseList<Milestone> getMilestones(String pageId) throws FacebookException;

    /**
     * Returns a list of the Page's milestones.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return milestones
     * @throws FacebookException when Facebook service or network is unavailable
     */
    ResponseList<Milestone> getMilestones(String pageId, Reading reading) throws FacebookException;

    /**
     * Creates the milestone for the current page.
     * Note that this method requires 'page' access_token.
     * @param milestoneUpdate a milestone to be created
     * @return The new milestone ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#milestones">Page#milestones - Facebook Developers</a>
     */
    String createMilestone(MilestoneUpdate milestoneUpdate) throws FacebookException;

    /**
     * Creates the milestone for the page.
     * Note that this method requires 'page' access_token.
     * @param pageId the ID of the page
     * @param milestoneUpdate a milestone to be created
     * @return The new milestone ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#milestones">Page#milestones - Facebook Developers</a>
     */
    String createMilestone(String pageId, MilestoneUpdate milestoneUpdate) throws FacebookException;

    /**
     * Deletes the milestone.
     * Note that this method requires 'page' access_token.
     * @param milestoneId the ID of a milestone
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#milestones">Page#milestones - Facebook Developers</a>
     */
    boolean deleteMilestone(String milestoneId) throws FacebookException;

    /**
     * Returns a list of the Page's Admins.
     * Note that this method requires 'page' access_token.
     * @return admins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Admin> getPageAdmins() throws FacebookException;

    /**
     * Returns a list of the Page's Admins.
     * Note that this method requires 'page' access_token.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return admins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Admin> getPageAdmins(Reading reading) throws FacebookException;

    /**
     * Returns a list of the Page's Admins.
     * Note that this method requires 'page' access_token.
     * @param pageId the ID of the page
     * @return admins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Admin> getPageAdmins(String pageId) throws FacebookException;

    /**
     * Returns a list of the Page's Admins.
     * Note that this method requires 'page' access_token.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return admins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Admin> getPageAdmins(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns the current Page's profile tabs.
     * Note that this method requires 'page' access_token.
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getTabs() throws FacebookException;

    /**
     * Returns the current Page's profile tabs.
     * Note that this method requires 'page' access_token.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getTabs(Reading reading) throws FacebookException;

    /**
     * Returns the Page's profile tabs.
     * Note that this method requires 'page' access_token.
     * @param pageId the ID of the page
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getTabs(String pageId) throws FacebookException;

    /**
     * Returns the Page's profile tabs.
     * Note that this method requires 'page' access_token.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getTabs(String pageId, Reading reading) throws FacebookException;

    /**
     * Returns the current Page's profile tabs if a specific app is installed.
     * @param appIds the IDs of app
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getInstalledTabs(List<String> appIds) throws FacebookException;

    /**
     * Returns the current Page's profile tabs if a specific app is installed.
     * @param appIds the IDs of app
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getInstalledTabs(List<String> appIds, Reading reading) throws FacebookException;

    /**
     * Returns the Page's profile tabs if a specific app is installed.
     * @param pageId the ID of the page
     * @param appIds the IDs of app
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getInstalledTabs(String pageId, List<String> appIds) throws FacebookException;

    /**
     * Returns the Page's profile tabs if a specific app is installed.
     * @param pageId the ID of the page
     * @param appIds the IDs of app
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return tabs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    ResponseList<Tab> getInstalledTabs(String pageId, List<String> appIds, Reading reading) throws FacebookException;

    /**
     * Installs a profile_tab at the end of the current list of installed tabs for the current page.
     * @param appId the ID of the application for which to install the tab
     * @return true if install is successful
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    boolean installTab(String appId) throws FacebookException;

    /**
     * Installs a profile_tab at the end of the current list of installed tabs for the current page.
     * @param pageId the ID of the page
     * @param appId the ID of the application for which to install the tab
     * @return true if install is successful
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    boolean installTab(String pageId, String appId) throws FacebookException;

    /**
     * Updates an installed profile_tab for the current page.
     * @param tabId the ID of the tab
     * @param tabUpdate tab to be updated
     * @return true if the update is successful
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    boolean updateTab(String tabId, TabUpdate tabUpdate) throws FacebookException;

    /**
     * Updates an installed profile_tab for the page.
     * @param pageId the ID of the page
     * @param tabId the ID of the tab
     * @param tabUpdate tab to be updated
     * @return true if the update is successful
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#tabs">Page#tabs - Facebook Developers</a>
     */
    boolean updateTab(String pageId, String tabId, TabUpdate tabUpdate) throws FacebookException;

    /**
     * Deletes an installed profile_tab where is_permanent is not true for the current page.
     * @param tabId the ID of the tab
     * @return true if the delete is successful
     * @throws FacebookException
     */
    boolean deleteTab(String tabId) throws FacebookException;

    /**
     * Deletes an installed profile_tab where is_permanent is not true for the page.
     * @param pageId the ID of the page
     * @param tabId the ID of the tab
     * @return true if the delete is successful
     * @throws FacebookException
     */
    boolean deleteTab(String pageId, String tabId) throws FacebookException;

    /**
     * Returns a list of users blocked from the current Page.
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    ResponseList<User> getBlocked() throws FacebookException;

    /**
     * Returns a list of users blocked from the current Page.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    ResponseList<User> getBlocked(Reading reading) throws FacebookException;

    /**
     * Returns a list of users blocked from the Page.
     * @param pageId the ID of the page
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    ResponseList<User> getBlocked(String pageId) throws FacebookException;

    /**
     * Returns a list of users blocked from the Page.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    ResponseList<User> getBlocked(String pageId, Reading reading) throws FacebookException;

    /**
     * Blocks users from posting content to the current page.
     * @param userIds user IDs you wish to block
     * @return Map where the keys are the user IDs and the values a boolean of whether or not the block was successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    Map<String, Boolean> block(List<String> userIds) throws FacebookException;

    /**
     * Blocks users from posting content to the current page.
     * @param pageId the ID of the page
     * @param userIds user IDs you wish to block
     * @return Map where the keys are the user IDs and the values a boolean of whether or not the block was successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    Map<String, Boolean> block(String pageId, List<String> userIds) throws FacebookException;

    /**
     * Unblocks the blocked user for the current page.
     * @param userId the IDs of the user
     * @return true if unblock is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    boolean unblock(String userId) throws FacebookException;

    /**
     * Unblocks the blocked user for the page.
     * @param pageId the ID of the page
     * @param userId the IDs of the user
     * @return true if unblock is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#blocked">Page#blocked - Facebook Developers</a>
     */
    boolean unblock(String pageId, String userId) throws FacebookException;

    /**
     * Returns a list of offers of the current page.
     * @return offers
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#offers">Page#offers - Facebook Developers</a>
     */
    ResponseList<Offer> getOffers() throws FacebookException;

    /**
     * Returns a list of offers of the current page.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return offers
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#offers">Page#offers - Facebook Developers</a>
     */
    ResponseList<Offer> getOffers(Reading reading) throws FacebookException;

    /**
     * Returns a list of offers of the current page.
     * @param pageId the ID of the page
     * @return offers
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#offers">Page#offers - Facebook Developers</a>
     */
    ResponseList<Offer> getOffers(String pageId) throws FacebookException;

    /**
     * Returns a list of offers of the current page.
     * @param pageId the ID of the page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return offers
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#offers">Page#offers - Facebook Developers</a>
     */
    ResponseList<Offer> getOffers(String pageId, Reading reading) throws FacebookException;

    /**
     * Creates an offer for the current page.
     * @param offerUpdate a createOffer to be created
     * @return The new createOffer ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#offers">Page#offers - Facebook Developers</a>
     */
    String createOffer(OfferUpdate offerUpdate) throws FacebookException;

    /**
     * Creates an offers for the page.
     * @param pageId the ID of the page
     * @param offerUpdate a createOffer to be created
     * @return The new createOffer ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#offers">Page#offers - Facebook Developers</a>
     */
    String createOffer(String pageId, OfferUpdate offerUpdate) throws FacebookException;

    /**
     * Deletes the createOffer.
     * @param offerId the ID of the createOffer
     * @return true if delete is successful
     * @throws FacebookException
     */
    boolean deleteOffer(String offerId) throws FacebookException;

    /**
     * Returns the offer.
     * @param offerId the ID of the offer
     * @return offer
     * @throws FacebookException when Facebook service or network is unavailable
     */
    Offer getOffer(String offerId) throws FacebookException;

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
