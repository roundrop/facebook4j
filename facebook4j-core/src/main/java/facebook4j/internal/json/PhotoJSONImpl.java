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

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.Photo;
import facebook4j.Place;
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PhotoJSONImpl extends FacebookResponseImpl implements Photo, java.io.Serializable {
    private static final long serialVersionUID = -6530726368840036344L;

    private String id;
    private IdNameEntity from;
    private List<Tag> tags;
    private String name;
    private URL icon;
    private URL picture;
    private URL source;
    private Integer height;
    private Integer width;
    private List<Photo.Image> images;
    private URL link;
    private Place place;
    private Date createdTime;
    private Date updatedTime;
    private Integer position;
    private PagableList<Comment> comments;
    private PagableList<Like> likes;

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
                from = new IdNameEntityJSONImpl(fromJSONObject);
            }
            if (!json.isNull("tags")) {
                JSONObject tagsJSONObject = json.getJSONObject("tags");
                JSONArray list = tagsJSONObject.getJSONArray("data");
                int size = list.length();
                tags = new ArrayList<Tag>(size);
                for (int i = 0; i < size; i++) {
                    TagJSONImpl tag = new TagJSONImpl(list.getJSONObject(i));
                    tags.add(tag);
                }
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
                images = new ArrayList<Photo.Image>();
                JSONArray imagesJSONArray = json.getJSONArray("images");
                for (int i = 0; i < imagesJSONArray.length(); i++) {
                    JSONObject image = imagesJSONArray.getJSONObject(i);
                    images.add(new PhotoJSONImpl.ImageJSONImpl(image));
                }
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
                JSONArray list = commentsJSONObject.getJSONArray("data");
                int size = list.length();
                comments = new PagableListImpl<Comment>(size, commentsJSONObject);
                for (int i = 0; i < size; i++) {
                    CommentJSONImpl comment = new CommentJSONImpl(list.getJSONObject(i));
                    comments.add(comment);
                }
            }
            if (!json.isNull("likes")) {
                JSONObject likesJSONObject = json.getJSONObject("likes");
                JSONArray list = likesJSONObject.getJSONArray("data");
                int size = list.length();
                likes = new PagableListImpl<Like>(size, likesJSONObject);
                for (int i = 0; i < size; i++) {
                    LikeJSONImpl like = new LikeJSONImpl(list.getJSONObject(i));
                    likes.add(like);
                }
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public IdNameEntity getFrom() {
        return from;
    }

    public List<Tag> getTags() {
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

    public List<Photo.Image> getImages() {
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

    /*package*/
    static ResponseList<Photo> createPhotoList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
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
        return "PhotoJSONImpl [id=" + id + ", from=" + from + ", tags=" + tags
                + ", name=" + name + ", icon=" + icon + ", picture=" + picture
                + ", source=" + source + ", height=" + height + ", width="
                + width + ", images=" + images + ", link=" + link + ", place="
                + place + ", createdTime=" + createdTime + ", updatedTime="
                + updatedTime + ", position=" + position + ", comments="
                + comments + ", likes=" + likes + "]";
    }

    private final class ImageJSONImpl implements Photo.Image, java.io.Serializable {
        private static final long serialVersionUID = 5512851070213770944L;

        private Integer height;
        private Integer width;
        private URL source;

        /*package*/ImageJSONImpl(JSONObject json) {
            if (!json.isNull("height")) {
                height = getPrimitiveInt("height", json);
            }
            if (!json.isNull("width")) {
                width = getPrimitiveInt("width", json);
            }
            source = getURL("source", json);
        }

        public Integer getHeight() {
            return height;
        }

        public Integer getWidth() {
            return width;
        }

        public URL getSource() {
            return source;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result
                    + ((height == null) ? 0 : height.hashCode());
            result = prime * result
                    + ((source == null) ? 0 : source.hashCode());
            result = prime * result + ((width == null) ? 0 : width.hashCode());
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
            ImageJSONImpl other = (ImageJSONImpl) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (height == null) {
                if (other.height != null)
                    return false;
            } else if (!height.equals(other.height))
                return false;
            if (source == null) {
                if (other.source != null)
                    return false;
            } else if (!source.equals(other.source))
                return false;
            if (width == null) {
                if (other.width != null)
                    return false;
            } else if (!width.equals(other.width))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "ImageJSONImpl [height=" + height + ", width=" + width
                    + ", source=" + source + "]";
        }

        private PhotoJSONImpl getOuterType() {
            return PhotoJSONImpl.this;
        }
    }
}
