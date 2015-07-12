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
import facebook4j.Friend;
import facebook4j.FriendRequest;
import facebook4j.Friendlist;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.TaggableFriend;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface FriendMethods {
    /**
     * Returns the current user's friends.
     * @return friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getFriends() throws FacebookException;

    /**
     * Returns the current user's friends.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getFriends(Reading reading) throws FacebookException;

    /**
     * Returns a user's friends.
     * @param userId the ID of a user
     * @return friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getFriends(String userId) throws FacebookException;

    /**
     * Returns a user's friends.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getFriends(String userId, Reading reading) throws FacebookException;
    
    /**
     * Returns the friend if the current user friends with.
     * @param friendId the ID of the friend
     * @return friend. If the current user is not a friend with the User represented by friendId, returns empty list.
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getBelongsFriend(String friendId) throws FacebookException;

    /**
     * Returns the friend if the current user friends with.
     * @param friendId the ID of the friend
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friend. If the current user is not a friend with the User represented by friendId, returns empty list.
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getBelongsFriend(String friendId, Reading reading) throws FacebookException;

    /**
     * Returns the friend if a user friends with.
     * @param userId the ID of a user
     * @param friendId the ID of the friend
     * @return friend. If the user is not a friend with the User represented by friendId, returns empty list.
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getBelongsFriend(String userId, String friendId) throws FacebookException;

    /**
     * Returns the friend if a user friends with.
     * @param userId the ID of a user
     * @param friendId the ID of the friend
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friend. If the user is not a friend with the User represented by friendId, returns empty list.
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friends">User#friends - Facebook Developers</a>
     */
    ResponseList<Friend> getBelongsFriend(String userId, String friendId, Reading reading) throws FacebookException;

    
    /**
     * Returns the current user's friend lists.
     * @return friend lists
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - friendlists
     */
    ResponseList<Friendlist> getFriendlists() throws FacebookException;

    /**
     * Returns the current user's friend lists.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friend lists
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - friendlists
     */
    ResponseList<Friendlist> getFriendlists(Reading reading) throws FacebookException;

    /**
     * Returns the user's friend lists.
     * @param userId the ID of a user
     * @return friend lists
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - friendlists
     */
    ResponseList<Friendlist> getFriendlists(String userId) throws FacebookException;

    /**
     * Returns the user's friend lists.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friend lists
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - friendlists
     */
    ResponseList<Friendlist> getFriendlists(String userId, Reading reading) throws FacebookException;


    /**
     * Creates the friend list for the current user.
     * @param friendlistName friend list name
     * @return The new friend list ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friendlists">User#friendlists - Facebook Developers</a>
     */
    String createFriendlist(String friendlistName) throws FacebookException;

    /**
     * Creates the friend list for a user.
     * @param userId the ID of a user
     * @param friendlistName friend list name. Maximum 25 characters
     * @return The new friend list ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friendlists">User#friendlists - Facebook Developers</a>
     */
    String createFriendlist(String userId, String friendlistName) throws FacebookException;


    /**
     * Returns a single friend list.
     * @param friendlistId the ID of the friend list
     * @return friend list
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/FriendList/">FriendList - Facebook Developers</a>
     */
    Friendlist getFriendlist(String friendlistId) throws FacebookException;

    /**
     * Returns a single friend list.
     * @param friendlistId the ID of the friend list
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friend list
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/FriendList/">FriendList - Facebook Developers</a>
     */
    Friendlist getFriendlist(String friendlistId, Reading reading) throws FacebookException;
    
    /**
     * Deletes the friend list.
     * @param friendlistId the ID of the friend list
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/FriendList/">FriendList - Facebook Developers</a>
     */
    boolean deleteFriendlist(String friendlistId) throws FacebookException;


    /**
     * Returns all of the users who are members of this list.
     * @param friendlistId the ID of the friend list
     * @return members of this list
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/FriendList/#members">FriendList#members - Facebook Developers</a>
     */
    ResponseList<Friend> getFriendlistMembers(String friendlistId) throws FacebookException;

    /**
     * Adds a user to the friend list.
     * @param friendlistId the ID of the friend list
     * @param userId the ID of target user
     * @return true if add is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/FriendList/#members">FriendList#members - Facebook Developers</a>
     */
    boolean addFriendlistMember(String friendlistId, String userId) throws FacebookException;

    /**
     * Removes the user from a friend list.
     * @param friendlistId the ID of a friend list
     * @param userId the ID of a target user
     * @return true if remove is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/FriendList/#members">FriendList#members - Facebook Developers</a>
     */
    boolean removeFriendlistMember(String friendlistId, String userId) throws FacebookException;

    /**
     * This method is an alias of: {@link facebook4j.api.FriendMethods#removeFriendlistMember(String, String)} .
     * @param friendlistId the ID of a friend list
     * @param userId the ID of a target user
     * @return true if remove is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/FriendList/#members">FriendList#members - Facebook Developers</a>
     */
    boolean deleteFriendlistMember(String friendlistId, String userId) throws FacebookException;


    /**
     * Returns the current user's incoming friend requests.
     * @return friend requests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friendrequests">User#friendrequests - Facebook Developers</a>
     */
    ResponseList<FriendRequest> getFriendRequests() throws FacebookException;

    /**
     * Returns the current user's incoming friend requests.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friend requests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friendrequests">User#friendrequests - Facebook Developers</a>
     */
    ResponseList<FriendRequest> getFriendRequests(Reading reading) throws FacebookException;

    /**
     * Returns the user's incoming friend requests.
     * @param userId the ID of a user
     * @return friend requests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friendrequests">User#friendrequests - Facebook Developers</a>
     */
    ResponseList<FriendRequest> getFriendRequests(String userId) throws FacebookException;

    /**
     * Returns the user's incoming friend requests.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return friend requests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#friendrequests">User#friendrequests - Facebook Developers</a>
     */
    ResponseList<FriendRequest> getFriendRequests(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the mutual friends between the current user and friend.
     * @param friendUserId the ID of a friend
     * @return mutual friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#mutualfriends">User#mutualfriends - Facebook Developers</a>
     */
    ResponseList<Friend> getMutualFriends(String friendUserId) throws FacebookException;

    /**
     * Returns the mutual friends between the current user and friend.
     * @param friendUserId the ID of a friend
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return mutual friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#mutualfriends">User#mutualfriends - Facebook Developers</a>
     */
    ResponseList<Friend> getMutualFriends(String friendUserId, Reading reading) throws FacebookException;

    /**
     * Returns the mutual friends between two users.
     * @param userId1 the ID of a user
     * @param userId2 the ID of a user
     * @return mutual friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#mutualfriends">User#mutualfriends - Facebook Developers</a>
     */
    ResponseList<Friend> getMutualFriends(String userId1, String userId2) throws FacebookException;

    /**
     * Returns the mutual friends between two users.
     * @param userId1 the ID of a user
     * @param userId2 the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return mutual friends
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#mutualfriends">User#mutualfriends - Facebook Developers</a>
     */
    ResponseList<Friend> getMutualFriends(String userId1, String userId2, Reading reading) throws FacebookException;
    
    /**
     * Returns the list of friends of the current user.
     * @return taggable friend's list
     * @throws FacebookException when Facebook service or network is unavailable, and when taggable friends feature requires app review
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/taggable_friends">User#taggablefriends - Facebook Developers</a>
     */
    ResponseList<TaggableFriend> getTaggableFriends() throws FacebookException;

    /**
     * Returns the list of friends of the current user.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return taggable friend's list
     * @throws FacebookException when Facebook service or network is unavailable,and when taggable friends feature requires app review
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/taggable_friends">User#taggablefriends - Facebook Developers</a>
     */
    ResponseList<TaggableFriend> getTaggableFriends(Reading reading) throws FacebookException;

    /**
     * Returns the list of friends of the current user.
     * @param userId the ID of a user
     * @return taggable friend's list
     * @throws FacebookException when Facebook service or network is unavailable, and when taggable friends feature requires app review
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/taggable_friends">User#taggablefriends - Facebook Developers</a>
     */
    ResponseList<TaggableFriend> getTaggableFriends(String userId) throws FacebookException;

    /**
     * Returns the list of friends of the current user.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return taggable friend's list
     * @throws FacebookException when Facebook service or network is unavailable,and when taggable friends feature requires app review
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/taggable_friends">User#taggablefriends - Facebook Developers</a>
     */
    ResponseList<TaggableFriend> getTaggableFriends(String userId, Reading reading) throws FacebookException;

}
