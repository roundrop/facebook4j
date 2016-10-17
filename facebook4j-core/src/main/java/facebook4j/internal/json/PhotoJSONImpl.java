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

import facebook4j.Category;
import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.Image;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.Photo;
import facebook4j.Place;
import facebook4j.Reaction;
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PhotoJSONImpl extends FacebookResponseImpl implements Photo, java.io.Serializable {
    private static final long serialVersionUID = 831715533023012050L;

    private String id;
    private Category from;
    private PagableList<Tag> tags;
    private String name;
    private URL icon;
    private URL picture;
    private URL source;
    private Integer height;
    private Integer width;
    private List<Image> images;
    private URL link;
    private Place place;
    private Date createdTime;
    private Date updatedTime;
    private Integer position;
    private PagableList<Comment> comments;
    private PagableList<Like> likes;
    private Category album;
    private PagableList<Reaction> reactions;
    
    /*package*/PhotoJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/PhotoJSONImpl(JSONObject json) throws FacebookException {
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
            if (!json.isNull("tags")) {
                JSONObject tagsJSONObject = json.getJSONObject("tags");
                JSONArray list = tagsJSONObject.getJSONArray("data");
                final int size = list.length();
                tags = new PagableListImpl<Tag>(size, tagsJSONObject);
                for (int i = 0; i < size; i++) {
                    TagJSONImpl tag = new TagJSONImpl(list.getJSONObject(i));
                    tags.add(tag);
                }
            } else {
                tags = new PagableListImpl<Tag>();
            }
            name = getRawString("name", json);
            icon = getURL("icon", json);
            picture = getURL("picture", json);
            source = getURL("source", json);
            if (!json.isNull("height")) {
                height = getPrimitiveInt("height", json);
            }
            if (!json.isNull("width")) {
                width = getPrimitiveInt("width", json);
            }
            if (!json.isNull("images")) {
                images = new ArrayList<Image>();
                JSONArray imagesJSONArray = json.getJSONArray("images");
                for (int i = 0; i < imagesJSONArray.length(); i++) {
                    JSONObject imageJSONObject = imagesJSONArray.getJSONObject(i);
                    images.add(new ImageJSONImpl(imageJSONObject));
                }
            } else {
                images = Collections.emptyList();
            }
            link = getURL("link", json);
            if (!json.isNull("place")) {
                JSONObject placeJSONObject = json.getJSONObject("place");
                place = new PlaceJSONImpl(placeJSONObject);
            }
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            if (!json.isNull("position")) {
                position = getPrimitiveInt("position", json);
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
            if (!json.isNull("album")) {
                album = new CategoryJSONImpl(json.getJSONObject("album"));
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

    public PagableList<Tag> getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public URL getIcon() {
        return icon;
    }

    public URL getPicture() {
        return picture;
    }

    public URL getSource() {
        return source;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public List<Image> getImages() {
        return images;
    }

    public URL getLink() {
        return link;
    }

    public Place getPlace() {
        return place;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public Integer getPosition() {
        return position;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    public PagableList<Like> getLikes() {
        return likes;
    }

    public Category getAlbum() {
        return album;
    }

    public PagableList<Reaction> getReactions() {
        return reactions;
    }
    
    /*package*/
    static ResponseList<Photo> createPhotoList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Photo> photos = new ResponseListImpl<Photo>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject photoJSONObject = list.getJSONObject(i);
                Photo photo = new PhotoJSONImpl(photoJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(photo, photoJSONObject);
                }
                photos.add(photo);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(photos, list);
            }
            return photos;
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
        PhotoJSONImpl other = (PhotoJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PhotoJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", tags=" + tags +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", picture=" + picture +
                ", source=" + source +
                ", height=" + height +
                ", width=" + width +
                ", images=" + images +
                ", link=" + link +
                ", place=" + place +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", position=" + position +
                ", comments=" + comments +
                ", likes=" + likes +
                ", album=" + album +
                '}';
    }

}
