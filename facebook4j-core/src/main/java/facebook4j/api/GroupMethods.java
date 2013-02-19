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

import java.net.URL;

import facebook4j.FacebookException;
import facebook4j.Group;
import facebook4j.GroupDoc;
import facebook4j.GroupMember;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.Reading;
import facebook4j.ResponseList;

public interface GroupMethods {
    /**
     * Returns the Groups that the current user belongs to.
     * @return groups
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#groups">User#groups - Facebook Developers</a>
     */
    ResponseList<Group> getGroups() throws FacebookException;

    /**
     * Returns the Groups that the current user belongs to.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return groups
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#groups">User#groups - Facebook Developers</a>
     */
    ResponseList<Group> getGroups(Reading reading) throws FacebookException;

    /**
     * Returns the Groups that a user belongs to.
     * @param userId the ID of a user
     * @return groups
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#groups">User#groups - Facebook Developers</a>
     */
    ResponseList<Group> getGroups(String userId) throws FacebookException;

    /**
     * Returns the Groups that a user belongs to.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return groups
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#groups">User#groups - Facebook Developers</a>
     */
    ResponseList<Group> getGroups(String userId, Reading reading) throws FacebookException;


    /**
     * Returns a single group.
     * @param groupId the ID of the group
     * @return group
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a>
     */
    Group getGroup(String groupId) throws FacebookException;

    /**
     * Returns a single group.
     * @param groupId the ID of the group
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return group
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a>
     */
    Group getGroup(String groupId, Reading reading) throws FacebookException;


    /**
     * Returns the group's wall.
     * @param groupId the ID of the group
     * @return group's feed
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/#feed">Group#feed - Facebook Developers</a>
     */
    ResponseList<Post> getGroupFeed(String groupId) throws FacebookException;

    /**
     * Returns the group's wall.
     * @param groupId the ID of the group
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return group's feed
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/#feed">Group#feed - Facebook Developers</a>
     */
    ResponseList<Post> getGroupFeed(String groupId, Reading reading) throws FacebookException;


    /**
     * Creates the post on a group's wall.
     * @param groupId the ID of a group
     * @param postUpdate the post to be created
     * @return The new post ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/#posts">Group#posts - Facebook Developers</a>
     */
    String postGroupFeed(String groupId, PostUpdate postUpdate) throws FacebookException;


    /**
     * Posts the link on a group's wall.
     * @param groupId the ID of a group
     * @param link link URL
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/#links">Group#links - Facebook Developers</a>
     */
    String postGroupLink(String groupId, URL link) throws FacebookException;
    
    /**
     * Posts the link on a group's wall.
     * @param groupId the ID of a group
     * @param link link URL
     * @param message link message
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/#links">Group#links - Facebook Developers</a>
     */
    String postGroupLink(String groupId, URL link, String message) throws FacebookException;


    /**
     * Posts the status message on a group's wall.
     * @param groupId the ID of a group
     * @param message status message content
     * @return The new status message ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/#statuses">Group#statuses - Facebook Developers</a>
     */
    String postGroupStatusMessage(String groupId, String message) throws FacebookException;


    /**
     * Returns all of the users who are members of a group.
     * @param groupId the ID of a group
     * @return all of the users who are members of the group
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a> - Connections - members
     */
    ResponseList<GroupMember> getGroupMembers(String groupId) throws FacebookException;

    /**
     * Returns all of the users who are members of a group.
     * @param groupId the ID of a group
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return all of the users who are members of the group
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a> - Connections - members
     */
    ResponseList<GroupMember> getGroupMembers(String groupId, Reading reading) throws FacebookException;


    /**
     * Returns url of a group's profile picture.
     * @param groupId the ID of a group
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a> - Connections - picture
     */
    URL getGroupPictureURL(String groupId) throws FacebookException;


    /**
     * Returns the docs in a group.
     * @param groupId the ID of a group
     * @return docs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a> - Connections - docs
     */
    ResponseList<GroupDoc> getGroupDocs(String groupId) throws FacebookException;

    /**
     * Returns the docs in a group.
     * @param groupId the ID of a group
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return docs
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a> - Connections - docs
     */
    ResponseList<GroupDoc> getGroupDocs(String groupId, Reading reading) throws FacebookException;

}
