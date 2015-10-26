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
import facebook4j.Link;
import facebook4j.Reading;
import facebook4j.ResponseList;

public interface LinkMethods {
    /**
     * Returns the current user's/page's/event's posted links.
     * @return links
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    ResponseList<Link> getLinks() throws FacebookException;

    /**
     * Returns the current user's/page's/event's posted links.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return links
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    ResponseList<Link> getLinks(Reading reading) throws FacebookException;

    /**
     * Returns a user's/page's/event's posted links.
     * @param id the ID of a user/page/event
     * @return links
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    ResponseList<Link> getLinks(String id) throws FacebookException;

    /**
     * Returns a user's/page's/event's posted links.
     * @param id the ID of a user/page/event
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return links
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#links">User#links - Facebook Developers</a>
     */
    ResponseList<Link> getLinks(String id, Reading reading) throws FacebookException;


    /**
     * Returns the link shared on a user's wall.
     * @param linkId the ID of the link
     * @return link
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/">Link - Facebook Developers</a>
     */
    Link getLink(String linkId) throws FacebookException;

    /**
     * Returns the link shared on a user's wall.
     * @param linkId the ID of the link
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return link
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/">Link - Facebook Developers</a>
     */
    Link getLink(String linkId, Reading reading) throws FacebookException;


    /**
     * Returns all of the comments on a link.
     * @param linkId the ID of a link
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#comments">Link#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getLinkComments(String linkId) throws FacebookException;

    /**
     * Returns all of the comments on a link.
     * @param linkId the ID of a link
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#comments">Link#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getLinkComments(String linkId, Reading reading) throws FacebookException;

    /**
     * Comments on the link.
     * @param linkId the ID of the link
     * @param message comment text
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#comments">Link#comments - Facebook Developers</a>
     */
    String commentLink(String linkId, String message) throws FacebookException;

    /**
     * Comments on the link.
     * @param linkId the ID of the link
     * @param commentUpdate comment content
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#comments">Link#comments - Facebook Developers</a>
     */
    String commentLink(String linkId, CommentUpdate commentUpdate) throws FacebookException;


    /**
     * Returns the likes on a link.
     * @param linkId the ID of a link
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#likes">Link#likes - Facebook Developers</a>
     */
    ResponseList<Like> getLinkLikes(String linkId) throws FacebookException;

    /**
     * Returns the likes on a link.
     * @param linkId the ID of a link
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#likes">Link#likes - Facebook Developers</a>
     */
    ResponseList<Like> getLinkLikes(String linkId, Reading reading) throws FacebookException;

    /**
     * Likes the link.
     * @param linkId the ID of the link
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#likes">Link#likes - Facebook Developers</a>
     */
    boolean likeLink(String linkId) throws FacebookException;

    /**
     * Unlikes the link.
     * @param linkId the ID of the link
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/link/#likes">Link#likes - Facebook Developers</a>
     */
    boolean unlikeLink(String linkId) throws FacebookException;

}
