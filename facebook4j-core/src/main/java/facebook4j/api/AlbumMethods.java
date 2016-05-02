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

import facebook4j.Album;
import facebook4j.AlbumUpdate;
import facebook4j.Comment;
import facebook4j.CommentUpdate;
import facebook4j.FacebookException;
import facebook4j.Like;
import facebook4j.Media;
import facebook4j.Photo;
import facebook4j.Reaction;
import facebook4j.Reading;
import facebook4j.ResponseList;

import java.net.URL;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface AlbumMethods {
    /**
     * Returns the photo albums the current user/page has created.
     * @return albums
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - albums
     */
    ResponseList<Album> getAlbums() throws FacebookException;

    /**
     * Returns the photo albums the current user/page has created.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return albums
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#albums">User#albums - Facebook Developers</a>
     */
    ResponseList<Album> getAlbums(Reading reading) throws FacebookException;

    /**
     * Returns the photo albums a user/page has created.
     * @param id the ID of a user/page
     * @return albums
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#albums">User#albums - Facebook Developers</a>
     */
    ResponseList<Album> getAlbums(String id) throws FacebookException;

    /**
     * Returns the photo albums a user/page has created.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return albums
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#albums">User#albums - Facebook Developers</a>
     */
    ResponseList<Album> getAlbums(String id, Reading reading) throws FacebookException;


    /**
     * Creates the current user's photo album.
     * @param albumUpdate the album to be created
     * @return The new album ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#albums">User#albums - Facebook Developers</a>
     */
    String createAlbum(AlbumUpdate albumUpdate) throws FacebookException;

    /**
     * Creates the user's photo album.
     * @param userId the ID of a user
     * @param albumUpdate the album to be created
     * @return The new album ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#albums">User#albums - Facebook Developers</a>
     */
    String createAlbum(String userId, AlbumUpdate albumUpdate) throws FacebookException;


    /**
     * Returns a single photo album.
     * @param albumId the ID of a album
     * @return album
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/">Album - Facebook Developers</a>
     */
    Album getAlbum(String albumId) throws FacebookException;

    /**
     * Returns a single photo album.
     * @param albumId the ID of a album
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return album
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/">Album - Facebook Developers</a>
     */
    Album getAlbum(String albumId, Reading reading) throws FacebookException;


    /**
     * Returns the photos contained in the album.
     * @param albumId the ID of a album
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#photos">Album#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getAlbumPhotos(String albumId) throws FacebookException;

    /**
     * Returns the photos contained in the album.
     * @param albumId the ID of a album
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#photos">Album#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getAlbumPhotos(String albumId, Reading reading) throws FacebookException;

    /**
     * Adds a photo to the album.
     * @param albumId the ID of a album
     * @param source photo content
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#photos">Album#photos - Facebook Developers</a>
     */
    String addAlbumPhoto(String albumId, Media source) throws FacebookException;

    /**
     * Adds a photo to the album.
     * @param albumId the ID of a album
     * @param source photo content
     * @param message photo description
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#photos">Album#photos - Facebook Developers</a>
     */
    String addAlbumPhoto(String albumId, Media source, String message) throws FacebookException;


    /**
     * Returns the comments made on the album.
     * @param albumId the ID of a album
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#comments">Album#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getAlbumComments(String albumId) throws FacebookException;

    /**
     * Returns the comments made on the album.
     * @param albumId the ID of a album
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#comments">Album#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getAlbumComments(String albumId, Reading reading) throws FacebookException;

    /**
     * Comments on the album.
     * @param albumId the ID of a album
     * @param message comment text
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#comments">Album#comments - Facebook Developers</a>
     */
    String commentAlbum(String albumId, String message) throws FacebookException;

    /**
     * Comments on the album.
     * @param albumId the ID of a album
     * @param commentUpdate comment content
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#comments">Album#comments - Facebook Developers</a>
     */
    String commentAlbum(String albumId, CommentUpdate commentUpdate) throws FacebookException;


    /**
     * Returns likes made on the album.
     * @param albumId the ID of a album
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#likes">Album#likes - Facebook Developers</a>
     */
    ResponseList<Like> getAlbumLikes(String albumId) throws FacebookException;

    /**
     * Returns likes made on the album.
     * @param albumId the ID of a album
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#likes">Album#likes - Facebook Developers</a>
     */
    ResponseList<Like> getAlbumLikes(String albumId, Reading reading) throws FacebookException;

    /**
     * Likes the album.
     * @param albumId the ID of a album
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#likes">Album#likes - Facebook Developers</a>
     */
    boolean likeAlbum(String albumId) throws FacebookException;

    /**
     * Unlikes the album.
     * @param albumId the ID of a album
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/#likes">Album#likes - Facebook Developers</a>
     */
    boolean unlikeAlbum(String albumId) throws FacebookException;
    
    /**
     * Returns url of the album's cover photo.
     * @param albumId the ID of a album
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/album/">Album - Facebook Developers</a> - Connections - picture
     */
    URL getAlbumCoverPhoto(String albumId) throws FacebookException;

    /**
     * Returns the reactions on the album.
     * @param albumId the ID of the album
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getAlbumReactions(String albumId) throws FacebookException;
    
    /**
     * Returns the reactions on the album.
     * @param albumId the ID of a album
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getAlbumReactions(String albumId, Reading reading) throws FacebookException;
}
