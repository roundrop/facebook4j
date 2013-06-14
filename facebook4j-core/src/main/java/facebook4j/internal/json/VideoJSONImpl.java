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
import java.util.Date;
import java.util.List;

import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.PagableList;
import facebook4j.ResponseList;
import facebook4j.Video;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class VideoJSONImpl extends FacebookResponseImpl implements Video, java.io.Serializable {
    private static final long serialVersionUID = -7490753250841393087L;
    
    private String id;
    private IdNameEntity from;
    private List<IdNameEntity> tags;
    private String name;
    private String description;
    private URL picture;
    private String embedHtml;
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
                from = new IdNameEntityJSONImpl(fromJSONObject);
            }
            if (!json.isNull("tags")) {
                JSONArray tagsJSONArray = json.getJSONArray("tags");
                for (int i = 0; i < tagsJSONArray.length(); i++) {
                    tags.add(new IdNameEntityJSONImpl(tagsJSONArray.getJSONObject(i)));
                }
            }
            name = getRawString("name", json);
            description = getRawString("description", json);
            picture = getURL("picture", json);
            embedHtml = getRawString("embed_html", json);
            icon = getURL("icon", json);
            source = getURL("source", json);
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            if (!json.isNull("comments")) {
                JSONArray commentJSONArray = json.getJSONObject("comments").getJSONArray("data");
                int size = commentJSONArray.length();
                comments = new PagableListImpl<Comment>(size, json.getJSONObject("comments"));
                for (int i = 0; i < size; i++) {
                    CommentJSONImpl comment = new CommentJSONImpl(commentJSONArray.getJSONObject(i));
                    comments.add(comment);
                }
            }
            link = getURL("link", json);
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

    public List<IdNameEntity> getTags() {
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
            int size = list.length();
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
                ", icon=" + icon +
                ", source=" + source +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", comments=" + comments +
                ", link=" + link +
                '}';
    }
}
