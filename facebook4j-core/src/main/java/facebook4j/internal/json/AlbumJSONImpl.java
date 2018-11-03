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

package facebook4j.internal.json;

import facebook4j.*;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.URL;
import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

 /**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class AlbumJSONImpl extends FacebookResponseImpl implements Album, java.io.Serializable {
    private static final long serialVersionUID = 4708626697445405636L;

    private String id;
    private Category from;
    private String name;
    private String description;
    private String location;
    private URL link;
    private String coverPhoto;
    private PrivacyType privacy;
    private Integer count;
    private String type;
    private Date createdTime;
    private Date updatedTime;
    private Boolean canUpload;
    private Place place;
    private Picture picture;

    private PagableList<Like> likes;
    private PagableList<Comment> comments;
    private PagableList<Reaction> reactions;

    /*package*/AlbumJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/AlbumJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("from")) {
                JSONObject fromJSONObject = json.getJSONObject("from");
                from = new CategoryJSONImpl(fromJSONObject);
            }
            name = getRawString("name", json);
            description = getRawString("description", json);
            location = getRawString("location", json);
            link = getURL("link", json);
            coverPhoto = getRawString("cover_photo", json);
            privacy = PrivacyType.getInstance(getRawString("privacy", json));
            count = getInt("count", json);
            type = getRawString("type", json);
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            canUpload = getBoolean("can_upload", json);
            if (!json.isNull("place")) {
                JSONObject placeJSONObject = json.getJSONObject("place");
                place = new PlaceJSONImpl(placeJSONObject);
            }

            if (!json.isNull("picture")) {
                JSONObject pictureJSONObject = json.getJSONObject("picture");
                if (!pictureJSONObject.isNull("data")) {
                    picture = new PictureJSONImpl(pictureJSONObject);
                }
            }

            if (!json.isNull("likes")) {
                JSONObject likesJSONObject = json.getJSONObject("likes");
                if (!likesJSONObject.isNull("data")) {
                    JSONArray list = likesJSONObject.getJSONArray("data");
                    final int size = list.length();
                    likes = new PagableListImpl<Like>(size, likesJSONObject);
                    for (int i = 0; i < size; i++) {
                        LikeJSONImpl like = new LikeJSONImpl(list.getJSONObject(i));
                        likes.add(like);
                    }
                } else {
                    likes = new PagableListImpl<Like>(1, likesJSONObject);
                }
            } else {
                likes = new PagableListImpl<Like>(0);
            }

            if (!json.isNull("comments")) {
                JSONObject commentsJSONObject = json.getJSONObject("comments");
                if (!commentsJSONObject.isNull("data")) {
                    JSONArray list = commentsJSONObject.getJSONArray("data");
                    final int size = list.length();
                    comments = new PagableListImpl<Comment>(size, commentsJSONObject);
                    for (int i = 0; i < size; i++) {
                        CommentJSONImpl comment = new CommentJSONImpl(list.getJSONObject(i));
                        comments.add(comment);
                    }
                } else {
                    comments = new PagableListImpl<Comment>(1, commentsJSONObject);
                }
            } else {
                comments = new PagableListImpl<Comment>(0);
            }

            if (!json.isNull("reactions")) {
                JSONObject reactionsJSONObject = json.getJSONObject("reactions");
                if (!reactionsJSONObject.isNull("data")) {
                    JSONArray list = reactionsJSONObject.getJSONArray("data");
                    final int size = list.length();
                    reactions = new PagableListImpl<Reaction>(size, reactionsJSONObject);
                    for (int i = 0; i < size; i++) {
                        ReactionJSONImpl reaction = new ReactionJSONImpl(list.getJSONObject(i));
                        reactions.add(reaction);
                    }
                } else {
                    reactions = new PagableListImpl<Reaction>(1, reactionsJSONObject);
                }
            } else {
                reactions = new PagableListImpl<Reaction>(0);
            }

        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public Category getFrom() {
        return from;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public URL getLink() {
        return link;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public PrivacyType getPrivacy() {
        return privacy;
    }

    public Integer getCount() {
        return count;
    }

    public String getType() {
        return type;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public Boolean canUpload() {
        return canUpload;
    }

    public Place getPlace() {
        return place;
    }

    public Picture getPicture() {
        return picture;
    }

    public PagableList<Like> getLikes() {
        return likes;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    public PagableList<Reaction> getReactions() {
        return reactions;
    }

    /*package*/
    static ResponseList<Album> createAlbumList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Album> albums = new ResponseListImpl<Album>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject albumJSONObject = list.getJSONObject(i);
                Album album = new AlbumJSONImpl(albumJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(album, albumJSONObject);
                }
                albums.add(album);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(albums, list);
            }
            return albums;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AlbumJSONImpl other = (AlbumJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

     @Override
     public String toString() {
         return "AlbumJSONImpl{" +
                 "id='" + id + '\'' +
                 ", from=" + from +
                 ", name='" + name + '\'' +
                 ", description='" + description + '\'' +
                 ", location='" + location + '\'' +
                 ", link=" + link +
                 ", coverPhoto='" + coverPhoto + '\'' +
                 ", privacy=" + privacy +
                 ", count=" + count +
                 ", type='" + type + '\'' +
                 ", createdTime=" + createdTime +
                 ", updatedTime=" + updatedTime +
                 ", canUpload=" + canUpload +
                 ", place=" + place +
                 ", picture=" + picture +
                 ", likes=" + likes +
                 ", comments=" + comments +
                 '}';
     }

 }
