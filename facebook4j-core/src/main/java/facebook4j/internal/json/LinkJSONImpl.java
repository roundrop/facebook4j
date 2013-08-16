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

import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Like;
import facebook4j.Link;
import facebook4j.PagableList;
import facebook4j.Privacy;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class LinkJSONImpl extends FacebookResponseImpl implements Link, java.io.Serializable {
    private static final long serialVersionUID = -5724838051542150714L;

    private String id;
    private IdNameEntity from;
    private String link;
    private String name;
    private PagableList<Like> likes;
    private PagableList<Comment> comments;
    private String description;
    private String icon;
    private String picture;
    private String message;
    private Date createdTime;
    private String type;
    private Privacy privacy;

    /*package*/LinkJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/LinkJSONImpl(JSONObject json) throws FacebookException {
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
            link = getRawString("link", json);
            name = getRawString("name", json);
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
                        CommentJSONImpl tag = new CommentJSONImpl(list.getJSONObject(i));
                        comments.add(tag);
                    }
                } else {
                    comments = new PagableListImpl<Comment>(1, commentsJSONObject);
                }
            } else {
                comments = new PagableListImpl<Comment>(0);
            }
            description = getRawString("description", json);
            icon = getRawString("icon", json);
            picture = getRawString("picture", json);
            message = getRawString("message", json);
            createdTime = getISO8601Datetime("created_time", json);
            type = getRawString("type", json);
            if (!json.isNull("privacy")) {
                JSONObject privacyJSONObject = json.getJSONObject("privacy");
                privacy = new PrivacyJSONImpl(privacyJSONObject);
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

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public PagableList<Like> getLikes() {
        return likes;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getPicture() {
        return picture;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getType() {
        return type;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    /*package*/
    static ResponseList<Link> createLinkList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Link> links = new ResponseListImpl<Link>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject linkJSONObject = list.getJSONObject(i);
                Link link = new LinkJSONImpl(linkJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(link, linkJSONObject);
                }
                links.add(link);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(links, list);
            }
            return links;
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
        LinkJSONImpl other = (LinkJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LinkJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", picture='" + picture + '\'' +
                ", message='" + message + '\'' +
                ", createdTime=" + createdTime +
                ", type='" + type + '\'' +
                ", privacy=" + privacy +
                '}';
    }
}
