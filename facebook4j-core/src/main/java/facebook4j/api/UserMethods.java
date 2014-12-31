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
import facebook4j.PictureSize;
import facebook4j.Reading;
import facebook4j.User;

import java.net.URL;
import java.util.List;


/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface UserMethods {
    /**
     * Returns the current user.
     * @return user
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a>
     */
    User getMe() throws FacebookException;

    /**
     * Returns the current user.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return user
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a>
     */
    User getMe(Reading reading) throws FacebookException;

    /**
     * Returns a given user, specified by ID.
     * @param userId the ID of the user
     * @return user
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a>
     */
    User getUser(String userId) throws FacebookException;

    /**
     * Returns a given user, specified by ID.
     * @param userId the ID of the user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return user
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a>
     */
    User getUser(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the url of the current user's profile picture.
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     */
    URL getPictureURL() throws FacebookException;

    /**
     * Returns the url of the current user's profile picture.
     * @param size {@link PictureSize picture size}
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     */
    URL getPictureURL(PictureSize size) throws FacebookException;

    /**
     * Returns the url of the current user's profile picture.
     * @param width width of the picture
     * @param height height of the picture
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     */
    URL getPictureURL(int width, int height) throws FacebookException;

    /**
     * Returns the url of a user's profile picture.
     * @param userId the ID of a user
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     */
    URL getPictureURL(String userId) throws FacebookException;

    /**
     * Returns the url of a user's profile picture.
     * @param userId the ID of a user
     * @param size {@link PictureSize picture size}
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     */
    URL getPictureURL(String userId, PictureSize size) throws FacebookException;

    /**
     * Returns the url of a user's profile picture.
     * @param userId the ID of a user
     * @param width width of the picture
     * @param height height of the picture
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     */
    URL getPictureURL(String userId, int width, int height) throws FacebookException;

    /**
     * Returns the url of the current user's profile picture over a secure connection.
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     * @since Facebook4J 2.0.0
     */
    URL getSSLPictureURL() throws FacebookException;

    /**
     * Returns the url of the current user's profile picture over a secure connection.
     * @param size {@link PictureSize picture size}
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     * @since Facebook4J 2.0.0
     */
    URL getSSLPictureURL(PictureSize size) throws FacebookException;

    /**
     * Returns the url of a user's profile picture over a secure connection.
     * @param userId the ID of a user
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     * @since Facebook4J 2.0.0
     */
    URL getSSLPictureURL(String userId) throws FacebookException;

    /**
     * Returns the url of a user's profile picture over a secure connection.
     * @param userId the ID of a user
     * @param size {@link PictureSize picture size}
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - picture
     * @since Facebook4J 2.0.0
     */
    URL getSSLPictureURL(String userId, PictureSize size) throws FacebookException;

    /**
     * Returns the users specified by IDs.
     * @param ids the IDs of users
     * @return users
     * @throws FacebookException
     * @see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     */
    List<User> getUsers(String... ids) throws FacebookException;

}
