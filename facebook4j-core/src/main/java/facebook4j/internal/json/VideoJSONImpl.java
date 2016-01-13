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
import facebook4j.PagableList;
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.Video;
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
/*package*/ final class VideoJSONImpl extends FacebookResponseImpl implements Video, java.io.Serializable {
    private static final long serialVersionUID = 7462511067275657778L;

    private String id;
    private Category from;
    private List<Tag> tags;
    private String name;
    private String description;
    private URL picture;
    private String embedHtml;
    private List<Video.Format> format;
    private URL icon;
    private URL source;
    private Date createdTime;
    private Date updatedTime;
    private PagableList<Comment> comments;
    private URL link;

    /*package*/VideoJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/VideoJSONImpl(JSONObject json) throws FacebookException {
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
                JSONArray tagsJSONArray = tagsJSONObject.getJSONArray("data");
                final int size = tagsJSONArray.length();
                tags = new ArrayList<Tag>(size);
                for (int i = 0; i < size; i++) {
                    tags.add(new TagJSONImpl(tagsJSONArray.getJSONObject(i)));
                }
            } else {
                tags = Collections.emptyList();
            }
            name = getRawString("name", json);
            description = getRawString("description", json);
            picture = getURL("picture", json);
            embedHtml = getRawString("embed_html", json);
            if (!json.isNull("format")) {
                JSONArray formatJSONArray = json.getJSONArray("format");
                final int size = formatJSONArray.length();
                format = new ArrayList<Format>(size);
                for (int i = 0; i < size; i++) {
                    format.add(new FormatJSONImpl(formatJSONArray.getJSONObject(i)));
                }
            } else {
                format = Collections.emptyList();
            }
            icon = getURL("icon", json);
            source = getURL("source", json);
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
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
            link = getURL("link", json);
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

    public List<Tag> getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public URL getPicture() {
        return picture;
    }

    public String getEmbedHtml() {
        return embedHtml;
    }

    public List<Format> getFormat() {
        return format;
    }

    public URL getIcon() {
        return icon;
    }

    public URL getSource() {
        return source;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    public URL getLink() {
        return link;
    }

    /*package*/
    static ResponseList<Video> createVideoList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Video> videos = new ResponseListImpl<Video>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject videoJSONObject = list.getJSONObject(i);
                Video video = new VideoJSONImpl(videoJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(video, videoJSONObject);
                }
                videos.add(video);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(videos, list);
            }
            return videos;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    private final class FormatJSONImpl implements Video.Format, java.io.Serializable {
        private static final long serialVersionUID = 3289798902942149539L;

        private String embedHtml;
        private String filter;
        private Integer height;
        private Integer width;
        private URL picture;

        FormatJSONImpl(JSONObject json) throws FacebookException {
            embedHtml = getRawString("embed_html", json);
            filter = getRawString("filter", json);
            height = getInt("height", json);
            width = getInt("width", json);
            picture = getURL("picture", json);
        }

        public String getEmbedHtml() {
            return embedHtml;
        }

        public String getFilter() {
            return filter;
        }

        public Integer getHeight() {
            return height;
        }

        public Integer getWidth() {
            return width;
        }

        public URL getPicture() {
            return picture;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FormatJSONImpl)) return false;

            FormatJSONImpl that = (FormatJSONImpl) o;

            if (embedHtml != null ? !embedHtml.equals(that.embedHtml) : that.embedHtml != null) return false;
            if (filter != null ? !filter.equals(that.filter) : that.filter != null) return false;
            if (height != null ? !height.equals(that.height) : that.height != null) return false;
            if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
            if (width != null ? !width.equals(that.width) : that.width != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = embedHtml != null ? embedHtml.hashCode() : 0;
            result = 31 * result + (filter != null ? filter.hashCode() : 0);
            result = 31 * result + (height != null ? height.hashCode() : 0);
            result = 31 * result + (width != null ? width.hashCode() : 0);
            result = 31 * result + (picture != null ? picture.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "FormatJSONImpl{" +
                    "embedHtml='" + embedHtml + '\'' +
                    ", filter='" + filter + '\'' +
                    ", height=" + height +
                    ", width=" + width +
                    ", picture=" + picture +
                    '}';
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
        VideoJSONImpl other = (VideoJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "VideoJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", tags=" + tags +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + picture +
                ", embedHtml='" + embedHtml + '\'' +
                ", format=" + format +
                ", icon=" + icon +
                ", source=" + source +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", comments=" + comments +
                ", link=" + link +
                '}';
    }
}
