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
import facebook4j.Media;
import facebook4j.Photo;
import facebook4j.PhotoUpdate;
import facebook4j.Post;
import facebook4j.Reaction;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.TagUpdate;

import java.net.URL;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface PhotoMethods {
    /**
     * Returns All of the updates photos of the current user/page.
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers (Connections - /photos/uploaded)</a>
     */
    ResponseList<Photo> getUploadedPhotos() throws FacebookException;

    /**
     * Returns All of the updates photos of the current user/page.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers (Connections - /photos/uploaded)</a>
     */
    ResponseList<Photo> getUploadedPhotos(Reading reading) throws FacebookException;

    /**
     * Returns All of the updates photos of the current user/page.
     * @param id the ID of a user/page
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers (Connections - /photos/uploaded)</a>
     */
    ResponseList<Photo> getUploadedPhotos(String id) throws FacebookException;

    /**
     * Returns All of the updates photos of the current user/page.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers (Connections - /photos/uploaded)</a>
     */
    ResponseList<Photo> getUploadedPhotos(String id, Reading reading) throws FacebookException;

    /**
     * Returns the photos the current user/page is tagged in.
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#photos">Page#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getPhotos() throws FacebookException;

    /**
     * Returns the photos the current user/page is tagged in.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#photos">Page#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getPhotos(Reading reading) throws FacebookException;

    /**
     * Returns the photos a user is tagged in / uploaded to a page / published to an event.
     * @param id the ID of a user/page/event
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#photos">Page#photos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#photos">Event#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getPhotos(String id) throws FacebookException;

    /**
     * Returns the photos a user is tagged in / uploaded to a page / published to an event.
     * @param id the ID of a user/page/event
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#photos">Page#photos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#photos">Event#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getPhotos(String id, Reading reading) throws FacebookException;


    /**
     * Posts a photo to the current user's wall.
     * @param source photo content
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     */
    String postPhoto(Media source) throws FacebookException;

    /**
     * Posts a photo to the current user's wall.
     * @param photoUpdate the photo to be created
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     */
    String postPhoto(PhotoUpdate photoUpdate) throws FacebookException;

    /**
     * Posts the photo to a user's wall.
     * @param userId the ID of a user
     * @param source photo content
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     */
    String postPhoto(String userId, Media source) throws FacebookException;

    /**
     * Posts the photo to a user's wall.
     * @param userId the ID of a user
     * @param photoUpdate the photo to be created
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#photos">User#photos - Facebook Developers</a>
     */
    String postPhoto(String userId, PhotoUpdate photoUpdate) throws FacebookException;


    /**
     * Deletes the photo.
     * @param photoId the ID of the photo
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     */
    boolean deletePhoto(String photoId) throws FacebookException;


    /**
     * Returns a single photo.
     * @param photoId the ID of the photo
     * @return photo
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/">Photo - Facebook Developers</a>
     */
    Photo getPhoto(String photoId) throws FacebookException;

    /**
     * Returns a single photo.
     * @param photoId the ID of the photo
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return photo
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/">Photo - Facebook Developers</a>
     */
    Photo getPhoto(String photoId, Reading reading) throws FacebookException;


    /**
     * Returns the comments made on a photo.
     * @param photoId the ID of a photo
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#comments">Photo#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getPhotoComments(String photoId) throws FacebookException;

    /**
     * Returns the comments made on a photo.
     * @param photoId the ID of a photo
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#comments">Photo#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getPhotoComments(String photoId, Reading reading) throws FacebookException;

    /**
     * Returns the posts in which this photo is shared.
     * @param photoId the ID of a photo
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/photo/sharedposts">Photo Sharedposts - Facebook Developers</a>
     */
    ResponseList<Post> getPhotoSharedposts(String photoId) throws FacebookException;

    /**
     * Returns the posts in which this photo is shared.
     * @param photoId the ID of a photo
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return photos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/photo/sharedposts">Photo Sharedposts - Facebook Developers</a>
     */
    ResponseList<Post> getPhotoSharedposts(String photoId, Reading reading) throws FacebookException;

    /**
     * Comments on the photo.
     * @param photoId the ID of the photo
     * @param message comment text
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#comments">Photo#comments - Facebook Developers</a>
     */
    String commentPhoto(String photoId, String message) throws FacebookException;

    /**
     * Comments on the photo.
     * @param photoId the ID of the photo
     * @param commentUpdate comment content
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#comments">Photo#comments - Facebook Developers</a>
     */
    String commentPhoto(String photoId, CommentUpdate commentUpdate) throws FacebookException;


    /**
     * Returns the likes made on a photo.
     * @param photoId the ID of a photo
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#likes">Photo#likes - Facebook Developers</a>
     */
    ResponseList<Like> getPhotoLikes(String photoId) throws FacebookException;

    /**
     * Returns the likes made on a photo.
     * @param photoId the ID of a photo
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#likes">Photo#likes - Facebook Developers</a>
     */
    ResponseList<Like> getPhotoLikes(String photoId, Reading reading) throws FacebookException;

    /**
     * Likes the photo.
     * @param photoId the ID of the photo
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#likes">Photo#likes - Facebook Developers</a>
     */
    boolean likePhoto(String photoId) throws FacebookException;

    /**
     * Unlikes the photo.
     * @param photoId the ID of the photo
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#likes">Photo#likes - Facebook Developers</a>
     */
    boolean unlikePhoto(String photoId) throws FacebookException;


    /**
     * Returns url of a photo.
     * @param photoId the ID of a photo
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/">Photo - Facebook Developers</a> - Connections - picture
     */
    URL getPhotoURL(String photoId) throws FacebookException;


    /**
     * Returns the Users tagged in a photo.
     * @param photoId the ID of a photo
     * @return tags
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/">Photo - Facebook Developers</a> - Connections - tags
     */
    ResponseList<Tag> getTagsOnPhoto(String photoId) throws FacebookException;

    /**
     * Returns the Users tagged in a photo.
     * @param photoId the ID of a photo
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return tags
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/">Photo - Facebook Developers</a> - Connections - tags
     */
    ResponseList<Tag> getTagsOnPhoto(String photoId, Reading reading) throws FacebookException;

    /**
     * Adds the tag to a photo.
     * @param photoId the ID of a photo
     * @param toUserId the ID of the user to tag
     * @return true if add is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#tags">Photo#tags - Facebook Developers</a> - Connections - tags
     */
    boolean addTagToPhoto(String photoId, String toUserId) throws FacebookException;
    
    /**
     * Adds the tag to a photo.
     * @param photoId the ID of a photo
     * @param toUserIds the IDs of the users to tag
     * @return true if add is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#tags">Photo#tags - Facebook Developers</a> - Connections - tags
     */
    boolean addTagToPhoto(String photoId, List<String> toUserIds) throws FacebookException;

    /**
     * Adds the tag to a photo.
     * @param photoId the ID of a photo
     * @param tagUpdate tag information
     * @return true if add is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#tags">Photo#tags - Facebook Developers</a> - Connections - tags
     */
    boolean addTagToPhoto(String photoId, TagUpdate tagUpdate) throws FacebookException;

    /**
     * Updates the position of the tag on a photo.
     * @param photoId the ID of a photo
     * @param tagUpdate tag information, supports 'to', 'x', 'y'
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#tags">Photo#tags - Facebook Developers</a> - Connections - tags
     */
    boolean updateTagOnPhoto(String photoId, TagUpdate tagUpdate) throws FacebookException;

    /**
     * Deletes a tag for a particular user in the photo.
     * @param photoId the ID of a photo
     * @param toUserId the ID of the user to tag
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/photo/#tags">Photo#tags - Facebook Developers</a> - Connections - tags
     * @since Facebook4J 2.0.0
     */
    boolean deleteTagOnPhoto(String photoId, String toUserId) throws FacebookException;

    /**
     * Returns the reactions on a photo.
     * @param photoId the ID of the photo
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getPhotoReactions(String photoId) throws FacebookException;
    
    /**
     * Returns the reactions on a photo.
     * @param photoId the ID of a photo
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getPhotoReactions(String photoId, Reading reading) throws FacebookException;
}
