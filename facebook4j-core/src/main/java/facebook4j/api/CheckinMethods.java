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

import facebook4j.Checkin;
import facebook4j.CheckinUpdate;
import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.Like;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface CheckinMethods {
    /**
     * Returns the places that the current user has checked-into
     * or made to the Place Page by the current user or friends of the current user.
     * @return checkins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#checkins">User#checkins - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Checkin> getCheckins() throws FacebookException;

    /**
     * Returns the places that the current user has checked-into
     * or made to the Place Page by the current user or friends of the current user.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return checkins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#checkins">User#checkins - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Checkin> getCheckins(Reading reading) throws FacebookException;

    /**
     * Returns the places that a user has checked-into
     * or made to the Place Page by the user or friends of the user.
     * @param id the ID of a user/page
     * @return checkins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#checkins">User#checkins - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Checkin> getCheckins(String id) throws FacebookException;

    /**
     * Returns the places that a user has checked-into
     * or made to the Place Page by the user or friends of the user.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return checkins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#checkins">User#checkins - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a>
     */
    ResponseList<Checkin> getCheckins(String id, Reading reading) throws FacebookException;

    /**
     * Checkins the place as the current user.
     * @param checkinUpdate the checkin to be created
     * @return The new checkin ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#checkins">User#checkins - Facebook Developers</a>
     * @deprecated creating a Post with a location attached
     */
    String checkin(CheckinUpdate checkinUpdate) throws FacebookException;

    /**
     * Checkins the place as a user.
     * @param userId the ID of a user
     * @param checkinUpdate the checkin to be created
     * @return The new checkin ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#checkins">User#checkins - Facebook Developers</a>
     * @deprecated creating a Post with a location attached
     */
    String checkin(String userId, CheckinUpdate checkinUpdate) throws FacebookException;

    /**
     * Returns a single checkin information.
     * @param checkinId the ID of a checkin
     * @return checkin
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/">Checkin - Facebook Developers</a>
     */
    Checkin getCheckin(String checkinId) throws FacebookException;

    /**
     * Returns a single checkin information.
     * @param checkinId the ID of a checkin
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return checkin
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/">Checkin - Facebook Developers</a>
     */
    Checkin getCheckin(String checkinId, Reading reading) throws FacebookException;


    /**
     * Returns the comments on a checkin.
     * @param checkinId the ID of a checkin
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/#comments">Checkin#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getCheckinComments(String checkinId) throws FacebookException;

    /**
     * Returns the comments on a checkin.
     * @param checkinId the ID of a checkin
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/#comments">Checkin#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getCheckinComments(String checkinId, Reading reading) throws FacebookException;

    /**
     * Comments on a checkin.
     * @param checkinId the ID of a checkin
     * @param message comment text
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/#comments">Checkin#comments - Facebook Developers</a>
     */
    String commentCheckin(String checkinId, String message) throws FacebookException;


    /**
     * Returns the users who like a checkin.
     * @param checkinId the ID of a checkin
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/#likes">Checkin#likes - Facebook Developers</a>
     */
    ResponseList<Like> getCheckinLikes(String checkinId) throws FacebookException;

    /**
     * Returns the users who like a checkin.
     * @param checkinId the ID of a checkin
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/#likes">Checkin#likes - Facebook Developers</a>
     */
    ResponseList<Like> getCheckinLikes(String checkinId, Reading reading) throws FacebookException;

    /**
     * Likes the checkin.
     * @param checkinId the ID of a checkin
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/#likes">Checkin#likes - Facebook Developers</a>
     */
    boolean likeCheckin(String checkinId) throws FacebookException;

    /**
     * Unlikes the checkin.
     * @param checkinId the ID of a checkin
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/checkin/#likes">Checkin#likes - Facebook Developers</a>
     */
    boolean unlikeCheckin(String checkinId) throws FacebookException;

}
