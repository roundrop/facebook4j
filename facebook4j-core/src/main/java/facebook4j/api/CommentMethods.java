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
import facebook4j.FacebookException;
import facebook4j.Like;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface CommentMethods {
    /**
     * Returns a single comment.
     * @param commentId the ID of a comment
     * @return comment
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/">Comment - Facebook Developers</a>
     */
    Comment getComment(String commentId) throws FacebookException;

    /**
     * Returns a single comment.
     * @param commentId the ID of a comment
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comment
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/">Comment - Facebook Developers</a>
     */
    Comment getComment(String commentId, Reading reading) throws FacebookException;

    /**
     * Returns the replies on a comment.
     * @param commentId the ID of a comment
     * @return replies
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/">Comment - Facebook Developers</a>
     */
    ResponseList<Comment> getCommentReplies(String commentId) throws FacebookException;

    /**
     * Returns the replies on a comment.
     * @param commentId the ID of a comment
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return replies
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/">Comment - Facebook Developers</a>
     */
    ResponseList<Comment> getCommentReplies(String commentId, Reading reading) throws FacebookException;

    /**
     * Deletes the comment.
     * @param commentId the ID of a comment
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/">Comment - Facebook Developers</a>
     */
    boolean deleteComment(String commentId) throws FacebookException;
    

    /**
     * Returns the likes on a comment.
     * @param commentId the ID of a comment
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/#likes">Comment#likes - Facebook Developers</a>
     */
    ResponseList<Like> getCommentLikes(String commentId) throws FacebookException;

    /**
     * Returns the likes on a comment.
     * @param commentId the ID of a comment
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/#likes">Comment#likes - Facebook Developers</a>
     */
    ResponseList<Like> getCommentLikes(String commentId, Reading reading) throws FacebookException;

    /**
     * Likes the comment.
     * @param commentId the ID of a comment
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/#likes">Comment#likes - Facebook Developers</a>
     */
    boolean likeComment(String commentId) throws FacebookException;

    /**
     * Unlikes the comment.
     * @param commentId the ID of a comment
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/Comment/#likes">Comment#likes - Facebook Developers</a>
     */
    boolean unlikeComment(String commentId) throws FacebookException;

}
