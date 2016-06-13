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

import facebook4j.Comment;
import facebook4j.CommentUpdate;
import facebook4j.FacebookException;
import facebook4j.Like;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.Reaction;
import facebook4j.Reading;
import facebook4j.ResponseList;

import java.net.URL;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface PostMethods {
    /**
     * Returns the current user's/page's/event's wall.
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#feed">User#feed - Facebook Developers</a>
     */
    ResponseList<Post> getFeed() throws FacebookException;

    /**
     * Returns the current user's/page's/event's wall.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#feed">User#feed - Facebook Developers</a>
     */
    ResponseList<Post> getFeed(Reading reading) throws FacebookException;

    /**
     * Returns a user's/page's/event's wall.
     * @param id the ID of a user/page/event
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#feed">User#feed - Facebook Developers</a>
     */
    ResponseList<Post> getFeed(String id) throws FacebookException;

    /**
     * Returns a user's/page's/event's wall.
     * @param id the ID of a user/page/event
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#feed">User#feed - Facebook Developers</a>
     */
    ResponseList<Post> getFeed(String id, Reading reading) throws FacebookException;


    /**
     * Returns the current user's news feed.
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#home">User#home - Facebook Developers</a>
     */
    ResponseList<Post> getHome() throws FacebookException;

    /**
     * Returns the current user's news feed.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#home">User#home - Facebook Developers</a>
     */
    ResponseList<Post> getHome(Reading reading) throws FacebookException;


    /**
     * Returns the current user's/page's own posts.
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#posts">User#posts - Facebook Developers</a>
     */
    ResponseList<Post> getPosts() throws FacebookException;

    /**
     * Returns the current user's/page's own posts.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#posts">User#posts - Facebook Developers</a>
     */
    ResponseList<Post> getPosts(Reading reading) throws FacebookException;

    /**
     * Returns the user's/page's own posts.
     * @param id the ID of a user/page
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#posts">User#posts - Facebook Developers</a>
     */
    ResponseList<Post> getPosts(String id) throws FacebookException;

    /**
     * Returns the user's/page's own posts.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#posts">User#posts - Facebook Developers</a>
     */
    ResponseList<Post> getPosts(String id, Reading reading) throws FacebookException;


    /**
     * Returns the current user's status updates.
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#statuses">User#statuses - Facebook Developers</a>
     */
    ResponseList<Post> getStatuses() throws FacebookException;

    /**
     * Returns the current user's status updates.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#statuses">User#statuses - Facebook Developers</a>
     */
    ResponseList<Post> getStatuses(Reading reading) throws FacebookException;

    /**
     * Returns the user's/page's status updates.
     * @param id the ID of a user/page
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#statuses">User#statuses - Facebook Developers</a>
     */
    ResponseList<Post> getStatuses(String id) throws FacebookException;

    /**
     * Returns the user's/page's status updates.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#statuses">User#statuses - Facebook Developers</a>
     */
    ResponseList<Post> getStatuses(String id, Reading reading) throws FacebookException;


    /**
     * Returns the posts the current user is tagged in.
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - tagged
     */
    ResponseList<Post> getTagged() throws FacebookException;

    /**
     * Returns the posts the current user is tagged in.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - tagged
     */
    ResponseList<Post> getTagged(Reading reading) throws FacebookException;

    /**
     * Returns the posts a user is tagged in.
     * @param userId the ID of a user
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - tagged
     */
    ResponseList<Post> getTagged(String userId) throws FacebookException;

    /**
     * Returns the posts the user is tagged in.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - tagged
     */
    ResponseList<Post> getTagged(String userId, Reading reading) throws FacebookException;


    /**
     * Returns a single post.
     * @param postId the ID of the post
     * @return post
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/">Post - Facebook Developers</a>
     */
    Post getPost(String postId) throws FacebookException;

    /**
     * Returns a single post.
     * @param postId the ID of the post
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return post
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/">Post - Facebook Developers</a>
     */
    Post getPost(String postId, Reading reading) throws FacebookException;


    /**
     * Returns all of the comments on a post.
     * @param postId the ID of a post
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#comments">Post#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getPostComments(String postId) throws FacebookException;

    /**
     * Returns all of the comments on a post.
     * @param postId the ID of a post
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#comments">Post#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getPostComments(String postId, Reading reading) throws FacebookException;

    /**
     * Comments on the post.
     * @param postId the ID of the post
     * @param message comment text
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#comments">Post#comments - Facebook Developers</a>
     */
    String commentPost(String postId, String message) throws FacebookException;

    /**
     * Comments on the post.
     * @param postId the ID of the post
     * @param commentUpdate comment content
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#comments">Post#comments - Facebook Developers</a>
     */
    String commentPost(String postId, CommentUpdate commentUpdate) throws FacebookException;


    /**
     * Returns the likes on a post.
     * @param postId the ID of a post
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#likes">Post#likes - Facebook Developers</a>
     */
    ResponseList<Like> getPostLikes(String postId) throws FacebookException;

    /**
     * Returns the likes on a post.
     * @param postId the ID of a post
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#likes">Post#likes - Facebook Developers</a>
     */
    ResponseList<Like> getPostLikes(String postId, Reading reading) throws FacebookException;
    
    /**
     * Returns the posts in which this post is shared.
     * @param postId the ID of a post
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/object/sharedposts">sharedposts - Facebook Developers</a>
     */
    ResponseList<Post> getSharedPosts(String postId) throws FacebookException;

    /**
     * Returns the posts in which this post is shared.
     * @param postId the ID of a post
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/object/sharedposts">sharedposts - Facebook Developers</a>
     */
    ResponseList<Post> getSharedPosts(String postId, Reading reading) throws FacebookException;

    /**
     * Likes the post.
     * @param postId the ID of the post
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#likes">Post#likes - Facebook Developers</a>
     */
    boolean likePost(String postId) throws FacebookException;

    /**
     * Unlikes the post.
     * @param postId the ID of the post
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/#likes">Post#likes - Facebook Developers</a>
     */
    boolean unlikePost(String postId) throws FacebookException;

    /**
     * Creates the post.
     * @param postUpdate the post to be created
     * @return The new post ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#posts">User#posts - Facebook Developers</a>
     */
    String postFeed(PostUpdate postUpdate) throws FacebookException;

    /**
     * Creates the post.
     * @param id the ID of a user/page/event
     * @param postUpdate the post to be created
     * @return The new post ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#posts">User#posts - Facebook Developers</a>
     */
    String postFeed(String id, PostUpdate postUpdate) throws FacebookException;


    /**
     * Creates the link.
     * @param link link URL
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    String postLink(URL link) throws FacebookException;

    /**
     * Creates the link.
     * @param link link URL
     * @param message link message
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    String postLink(URL link, String message) throws FacebookException;

    /**
     * Creates the link.
     * @param userId the ID of a user
     * @param link link URL
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    String postLink(String userId, URL link) throws FacebookException;

    /**
     * Creates the link.
     * @param userId the ID of a user
     * @param link link URL
     * @param message link message
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    String postLink(String userId, URL link, String message) throws FacebookException;


    /**
     * Creates the status message.
     * @param message link message
     * @return The new status message ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#statuses">User#statuses - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#statuses">Page#statuses - Facebook Developers</a>
     */
    String postStatusMessage(String message) throws FacebookException;

    /**
     * Creates the status message.
     * @param id the ID of a user/page
     * @param message link message
     * @return The new status message ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#statuses">User#statuses - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#statuses">Page#statuses - Facebook Developers</a>
     */
    String postStatusMessage(String id, String message) throws FacebookException;


    /**
     * Deletes the post.
     * @param postId the ID of the post
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/post/">Post - Facebook Developers</a>
     */
    boolean deletePost(String postId) throws FacebookException;
    
    /**
     * Returns the reactions on a post.
     * @param postId the ID of the post
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getPostReactions(String postId) throws FacebookException;
    
    /**
     * Returns the reactions on a post.
     * @param postId the ID of a post
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getPostReactions(String postId, Reading reading) throws FacebookException;

}
