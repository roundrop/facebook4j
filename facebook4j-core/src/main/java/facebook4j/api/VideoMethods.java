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
import facebook4j.Reaction;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Video;
import facebook4j.VideoUpdate;

import java.net.URL;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface VideoMethods {
    /**
     * Returns the videos the current user has been tagged in / the current page has uploaded.
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#videos">User#videos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#videos">Page#videos - Facebook Developers</a>
     */
    ResponseList<Video> getVideos() throws FacebookException;

    /**
     * Returns the videos the current user has been tagged in / the current page has uploaded.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#videos">User#videos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#videos">Page#videos - Facebook Developers</a>
     */
    ResponseList<Video> getVideos(Reading reading) throws FacebookException;

    /**
     * Returns the videos a user has been tagged in / the current page has uploaded.
     * @param id the ID of a user/page
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#videos">User#videos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#videos">Page#videos - Facebook Developers</a>
     */
    ResponseList<Video> getVideos(String id) throws FacebookException;

    /**
     * Returns the videos a user has been tagged in / the current page has uploaded.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#videos">User#videos - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#videos">Page#videos - Facebook Developers</a>
     */
    ResponseList<Video> getVideos(String id, Reading reading) throws FacebookException;


    /**
     * Posts the video to the current user's/page's wall.
     * @param videoUpdate video content
     * @return The new video ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#videos">User#videos - Facebook Developers</a>
     */
    String postVideo(VideoUpdate videoUpdate) throws FacebookException;

    /**
     * Posts the video to a user's/page's wall.
     * @param id the ID of a user
     * @param videoUpdate video content
     * @return The new video ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#videos">User#videos - Facebook Developers</a>
     */
    String postVideo(String id, VideoUpdate videoUpdate) throws FacebookException;


    /**
     * Returns a single video.
     * @param videoId the ID of the video
     * @return video
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/">Video - Facebook Developers</a>
     */
    Video getVideo(String videoId) throws FacebookException;

    /**
     * Returns a single video.
     * @param videoId the ID of the video
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return video
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/">Video - Facebook Developers</a>
     */
    Video getVideo(String videoId, Reading reading) throws FacebookException;
    

    /**
     * Returns all of the likes on a video.
     * @param videoId the ID of a video
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#likes">Video#likes - Facebook Developers</a>
     */
    ResponseList<Like> getVideoLikes(String videoId) throws FacebookException;

    /**
     * Returns all of the likes on a video.
     * @param videoId the ID of a video
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#likes">Video#likes - Facebook Developers</a>
     */
    ResponseList<Like> getVideoLikes(String videoId, Reading reading) throws FacebookException;

    /**
     * Returns the posts in which this video is shared.
     * @param videoId the ID of a video
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/video/sharedposts">Video Sharedposts - Facebook Developers</a>
     */
    ResponseList<Post> getVideoSharedposts(String videoId) throws FacebookException;

    /**
     * Returns the posts in which this video is shared.
     * @param videoId the ID of a video
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return videos
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/video/sharedposts">Video Sharedposts - Facebook Developers</a>
     */
    ResponseList<Post> getVideoSharedposts(String videoId, Reading reading) throws FacebookException;

    /**
     * Likes the video.
     * @param videoId the ID of the video
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#likes">Video#likes - Facebook Developers</a>
     */
    boolean likeVideo(String videoId) throws FacebookException;

    /**
     * Unlikes the video.
     * @param videoId the ID of the video
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#likes">Video#likes - Facebook Developers</a>
     */
    boolean unlikeVideo(String videoId) throws FacebookException;


    /**
     * Returns the comments on a video.
     * @param videoId the ID of a video
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#comments">Video#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getVideoComments(String videoId) throws FacebookException;

    /**
     * Returns the comments on a video.
     * @param videoId the ID of a video
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#comments">Video#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getVideoComments(String videoId, Reading reading) throws FacebookException;

    /**
     * Comments on the video.
     * @param videoId the ID of the video
     * @param message comment text
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#comments">Video#comments - Facebook Developers</a>
     */
    String commentVideo(String videoId, String message) throws FacebookException;
    
    /**
     * Comments on the video.
     * @param videoId the ID of the video
     * @param commentUpdate comment content
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/#comments">Video#comments - Facebook Developers</a>
     */
    String commentVideo(String videoId, CommentUpdate commentUpdate) throws FacebookException;


    /**
     * Returns url of the image which represents the content of a video.
     * @param videoId the ID of a video
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/video/">Video - Facebook Developers</a> - Connections - picture
     */
    URL getVideoCover(String videoId) throws FacebookException;
    
    /**
     * Returns all of the reactions on a video.
     * @param videoId the ID of a video
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getVideoReactions(String videoId) throws FacebookException;

    /**
     * Returns all of the reactions on a video.
     * @param videoId the ID of a video
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return reactions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a https://developers.facebook.com/docs/graph-api/reference/post/reactions">Reactions - Facebook Developers</a>
     */
    ResponseList<Reaction> getVideoReactions(String videoId, Reading reading) throws FacebookException;
}
