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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import facebook4j.Application;
import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.PagableList;
import facebook4j.Place;
import facebook4j.Post;
import facebook4j.Privacy;
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
final class PostJSONImpl extends FacebookResponseImpl implements Post, java.io.Serializable {
    private static final long serialVersionUID = 5829948216755728751L;

    private String id;
    private IdNameEntity from;
    private List<IdNameEntity> to;
    private String message;
    private List<Tag> messageTags;
    private URL picture;
    private URL link;
    private String name;
    private String caption;
    private String description;
    private URL source;
    private List<Post.Property> properties;
    private String icon;
    private List<Post.Action> actions;
    private Privacy privacy;
    private String type;
    private PagableList<IdNameEntity> likes;
    private Place place;
    private String story;
    private Map<String, Tag[]> storyTags;
    private List<IdNameEntity> withTags;
    private PagableList<Comment> comments;
    private Long objectId;
    private Application application;
    private Date createdTime;
    private Date updatedTime;
    private Boolean isPublished;
    private Integer scheduledPublishTime;

    /*package*/PostJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/PostJSONImpl(JSONObject json) throws FacebookException {
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
            if (!json.isNull("to")) {
                JSONArray toJSONArray = json.getJSONObject("to").getJSONArray("data");
                to = new ArrayList<IdNameEntity>();
                for (int i = 0; i < toJSONArray.length(); i++) {
                    JSONObject toJSONObject = toJSONArray.getJSONObject(i);
                    to.add(new IdNameEntityJSONImpl(toJSONObject));
                }
            }
            message = getRawString("message", json);
            if (!json.isNull("message_tags")) {
                String raw = json.get("message_tags").toString();
                if (raw.startsWith("[")) {
                    JSONArray tagsJSONArray = json.getJSONArray("message_tags");
                    messageTags = new ArrayList<Tag>();
                    for (int i = 0; i < tagsJSONArray.length(); i++) {
                        JSONObject tagJSONObject = tagsJSONArray.getJSONObject(i);
                        messageTags.add(new TagJSONImpl(tagJSONObject));
                    }
                } else {
                    JSONObject tagsJSONObject = json.getJSONObject("message_tags");
                    Iterator ids = tagsJSONObject.keys();
                    while (ids.hasNext()) {
                        String id = (String) ids.next();
                        JSONArray tagsJSONArray = tagsJSONObject.getJSONArray(id);
                        messageTags = new ArrayList<Tag>();
                        for (int i = 0; i < tagsJSONArray.length(); i++) {
                            JSONObject tagJSONObject = tagsJSONArray.getJSONObject(i);
                            messageTags.add(new TagJSONImpl(tagJSONObject));
                        }
                    }
                }
            }
            picture = getURL("picture", json);
            link = getURL("link", json);
            name = getRawString("name", json);
            caption = getRawString("caption", json);
            description = getRawString("description", json);
            source = getURL("source", json);
            if (!json.isNull("properties")) {
                JSONArray propertyJSONArray = json.getJSONArray("properties");
                properties = new ArrayList<Post.Property>();
                for (int i = 0; i < propertyJSONArray.length(); i++) {
                    JSONObject propertyJSONObject = propertyJSONArray.getJSONObject(i);
                    properties.add(this.new PropertyJSONImpl(propertyJSONObject));
                }
            }
            icon = getRawString("icon", json);
            if (!json.isNull("actions")) {
                JSONArray actionJSONArray = json.getJSONArray("actions");
                actions = new ArrayList<Post.Action>();
                for (int i = 0; i < actionJSONArray.length(); i++) {
                    JSONObject actionJSONObject = actionJSONArray.getJSONObject(i);
                    actions.add(this.new ActionJSONImpl(actionJSONObject));
                }
            }
            if (!json.isNull("privacy")) {
                JSONObject privacyJSONObject = json.getJSONObject("privacy");
                privacy = new PrivacyJSONImpl(privacyJSONObject);
            }
            type = getRawString("type", json);
            if (!json.isNull("likes")) {
                JSONObject likesJSONObject = json.getJSONObject("likes");
                if (!likesJSONObject.isNull("data")) {
                    JSONArray list = likesJSONObject.getJSONArray("data");
                    int size = list.length();
                    likes = new PagableListImpl<IdNameEntity>(size, likesJSONObject);
                    for (int i = 0; i < size; i++) {
                        IdNameEntityJSONImpl like = new IdNameEntityJSONImpl(list.getJSONObject(i));
                        likes.add(like);
                    }
                }
            }
            if (!json.isNull("place")) {
                JSONObject placeJSONObject = json.getJSONObject("place");
                place = new PlaceJSONImpl(placeJSONObject);
            }
            story = getRawString("story", json);
            if (!json.isNull("story_tags")) {
                JSONObject storyTagsJSONObject = json.getJSONObject("story_tags");
                storyTags = new HashMap<String, Tag[]>();
                @SuppressWarnings("unchecked")
                Iterator<String> keys = storyTagsJSONObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    JSONArray storyTagsJSONArray = storyTagsJSONObject.getJSONArray(key);
                    Tag[] tags = new Tag[storyTagsJSONArray.length()];
                    for (int i = 0; i < storyTagsJSONArray.length(); i++) {
                        JSONObject tag = storyTagsJSONArray.getJSONObject(i);
                        tags[i] = new TagJSONImpl(tag);
                    }
                    storyTags.put(key, tags);
                }
            }
            if (!json.isNull("with_tags")) {
                JSONArray withTagsJSONArray = json.getJSONObject("with_tags").getJSONArray("data");
                withTags = new ArrayList<IdNameEntity>();
                for (int i = 0; i < withTagsJSONArray.length(); i++) {
                    JSONObject withTagJSONObject = withTagsJSONArray.getJSONObject(i);
                    withTags.add(new IdNameEntityJSONImpl(withTagJSONObject));
                }
            }
            if (!json.isNull("comments")) {
                JSONObject commentsJSONObject = json.getJSONObject("comments");
                if (!commentsJSONObject.isNull("data")) {
                    JSONArray list = commentsJSONObject.getJSONArray("data");
                    int size = list.length();
                    comments = new PagableListImpl<Comment>(size, commentsJSONObject);
                    for (int i = 0; i < size; i++) {
                        CommentJSONImpl comment = new CommentJSONImpl(list.getJSONObject(i));
                        comments.add(comment);
                    }
                }
            }
            if (!json.isNull("object_id")) {
                objectId = getLong("object_id", json);
            }
            if (!json.isNull("application")) {
                JSONObject applicationJSONObject = json.getJSONObject("application");
                application = new ApplicationJSONImpl(applicationJSONObject);
            }
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            if (!json.isNull("is_published")) {
                isPublished = getBoolean("is_published", json);
            }
            scheduledPublishTime = getInt("scheduled_publish_time", json);
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

    public List<IdNameEntity> getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public List<Tag> getMessageTags() {
        return messageTags;
    }

    public URL getPicture() {
        return picture;
    }

    public URL getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public URL getSource() {
        return source;
    }

    public List<Post.Property> getProperties() {
        return properties;
    }

    public String getIcon() {
        return icon;
    }

    public List<Post.Action> getActions() {
        return actions;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public String getType() {
        return type;
    }

    public PagableList<IdNameEntity> getLikes() {
        return likes;
    }

    public Place getPlace() {
        return place;
    }

    public String getStory() {
        return story;
    }

    public Map<String, Tag[]> getStoryTags() {
        return storyTags;
    }

    public List<IdNameEntity> getWithTags() {
        return withTags;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    public Long getObjectId() {
        return objectId;
    }

    public Application getApplication() {
        return application;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public Boolean isPublished() {
        return isPublished;
    }

    public Date getScheduledPublishTime() {
        return new Date(scheduledPublishTime * 1000);
    }

    /*package*/
    static ResponseList<Post> createPostList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            ResponseList<Post> posts = new ResponseListImpl<Post>(size, json);
            for (int i = 0; i < size; i++) {
                Post post = new PostJSONImpl(list.getJSONObject(i));
                posts.add(post);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(posts, json);
            }
            return posts;
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
        PostJSONImpl other = (PostJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PostJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", message='" + message + '\'' +
                ", messageTags=" + messageTags +
                ", picture=" + picture +
                ", link=" + link +
                ", name='" + name + '\'' +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", source=" + source +
                ", properties=" + properties +
                ", icon='" + icon + '\'' +
                ", actions=" + actions +
                ", privacy=" + privacy +
                ", type='" + type + '\'' +
                ", likes=" + likes +
                ", place=" + place +
                ", story='" + story + '\'' +
                ", storyTags=" + storyTags +
                ", withTags=" + withTags +
                ", comments=" + comments +
                ", objectId=" + objectId +
                ", application=" + application +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", isPublished=" + isPublished +
                ", scheduledPublishTime=" + scheduledPublishTime +
                '}';
    }

    private class PropertyJSONImpl implements Post.Property, java.io.Serializable {
        private static final long serialVersionUID = -2917519371927503549L;
        
        private final String name;
        private final String text;
        private final String href;

        /*package*/PropertyJSONImpl(JSONObject json) {
            name = getRawString("name", json);
            text = getRawString("text", json);
            href = getRawString("href", json);
        }

        public String getName() {
            return name;
        }

        public String getText() {
            return text;
        }

        public String getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "Post.PropertyJSONImpl [name=" + name + ", text=" + text
                    + ", href=" + href + "]";
        }

    }

    private class ActionJSONImpl implements Post.Action, java.io.Serializable {
        private static final long serialVersionUID = 2407371708630166786L;
        
        private final String name;
        private final String link;

        /*package*/ActionJSONImpl(JSONObject json) throws FacebookException {
            name = getRawString("name", json);
            link = getRawString("link", json);
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        @Override
        public String toString() {
            return "Post.ActionJSONImpl [name=" + name + ", link=" + link + "]";
        }

    }

}
