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

import facebook4j.Application;
import facebook4j.Category;
import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.Place;
import facebook4j.Post;
import facebook4j.Privacy;
import facebook4j.Reaction;
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.Targeting;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
final class PostJSONImpl extends FacebookResponseImpl implements Post, java.io.Serializable {
    private static final long serialVersionUID = 9003714776647753272L;

    private String id;
    private Category from;
    private List<IdNameEntity> to;
    private String message;
    private List<Tag> messageTags;
    private URL picture;
    private URL fullPicture;
    private URL link;
    private String name;
    private String caption;
    private String description;
    private URL source;
    private List<Post.Property> properties;
    private URL icon;
    private List<Post.Action> actions;
    private Privacy privacy;
    private String type;
    private Integer sharesCount;
    private PagableList<Like> likes;
    private Place place;
    private String statusType;
    private String story;
    private Map<String, Tag[]> storyTags;
    private List<IdNameEntity> withTags;
    private PagableList<Comment> comments;
    private List<Attachment> attachments;
    private String objectId;
    private Application application;
    private Date createdTime;
    private Date updatedTime;
    private Boolean isPublished;
    private Boolean isHidden;
    private Integer scheduledPublishTime;
    private Targeting targeting;
    private PagableList<Reaction> reactions;
    private String parentId;
    private URL permalinkUrl;

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
                from = new CategoryJSONImpl(fromJSONObject);
            }
            if (!json.isNull("to")) {
                JSONArray toJSONArray = json.getJSONObject("to").getJSONArray("data");
                to = new ArrayList<IdNameEntity>();
                for (int i = 0; i < toJSONArray.length(); i++) {
                    JSONObject toJSONObject = toJSONArray.getJSONObject(i);
                    to.add(new IdNameEntityJSONImpl(toJSONObject));
                }
            } else {
                to = Collections.emptyList();
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
            } else {
                messageTags = Collections.emptyList();
            }
            picture = getURL("picture", json);
            fullPicture = getURL("full_picture", json);
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
                    properties.add(new PropertyJSONImpl(propertyJSONObject));
                }
            } else {
                properties = Collections.emptyList();
            }
            icon = getURL("icon", json);
            if (!json.isNull("actions")) {
                JSONArray actionJSONArray = json.getJSONArray("actions");
                actions = new ArrayList<Post.Action>();
                for (int i = 0; i < actionJSONArray.length(); i++) {
                    JSONObject actionJSONObject = actionJSONArray.getJSONObject(i);
                    actions.add(new ActionJSONImpl(actionJSONObject));
                }
            } else {
                actions = Collections.emptyList();
            }
            if (!json.isNull("privacy")) {
                JSONObject privacyJSONObject = json.getJSONObject("privacy");
                privacy = new PrivacyJSONImpl(privacyJSONObject);
            }
            type = getRawString("type", json);
            if (!json.isNull("shares")){
                JSONObject sharesJSONObject = json.getJSONObject("shares");
                if (!sharesJSONObject.isNull("count")){
                    sharesCount = getInt("count", sharesJSONObject);
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
            if (!json.isNull("place")) {
                JSONObject placeJSONObject = json.getJSONObject("place");
                place = new PlaceJSONImpl(placeJSONObject);
            }
            statusType = getRawString("status_type", json);
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
            } else {
                storyTags = Collections.emptyMap();
            }
            if (!json.isNull("with_tags")) {
                JSONArray withTagsJSONArray = json.getJSONObject("with_tags").getJSONArray("data");
                withTags = new ArrayList<IdNameEntity>();
                for (int i = 0; i < withTagsJSONArray.length(); i++) {
                    JSONObject withTagJSONObject = withTagsJSONArray.getJSONObject(i);
                    withTags.add(new IdNameEntityJSONImpl(withTagJSONObject));
                }
            } else {
                withTags = Collections.emptyList();
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
            if (!json.isNull("attachments")) {
                JSONArray attachmentsJSONArray = json.getJSONObject("attachments").getJSONArray("data");
                final int size = attachmentsJSONArray.length();
                attachments = new ArrayList<Attachment>(size);
                for (int i = 0; i < attachmentsJSONArray.length(); i++) {
                    JSONObject attachmentJsonObject = attachmentsJSONArray.getJSONObject(i);
                       attachments.add(new AttachmentJSONImpl(attachmentJsonObject));
                }
            } else {
                attachments = Collections.emptyList();
            }
            if (!json.isNull("object_id")) {
                objectId = getRawString("object_id", json);
            }
            if(!json.isNull("parent_id")) {
                parentId = getRawString("parent_id", json);
            }
            permalinkUrl = getURL("permalink_url", json);
            if (!json.isNull("application")) {
                JSONObject applicationJSONObject = json.getJSONObject("application");
                application = new ApplicationJSONImpl(applicationJSONObject);
            }
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            if (!json.isNull("is_published")) {
                isPublished = getBoolean("is_published", json);
            }
            if (!json.isNull("is_hidden")) {
                isHidden = getBoolean("is_hidden", json);
            }
            scheduledPublishTime = getInt("scheduled_publish_time", json);
            if (!json.isNull("targeting")) {
                targeting = new TargetingJSONImpl(json.getJSONObject("targeting"));
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

    public URL getFullPicture() {
      return fullPicture;
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

    public URL getIcon() {
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

    public Integer getSharesCount() {
        return sharesCount;
    }

    public PagableList<Like> getLikes() {
        return likes;
    }

    public Place getPlace() {
        return place;
    }

    public String getStatusType() {
        return statusType;
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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getParentId() {
        return parentId;
    }

    public URL getPermalinkUrl() {
        return permalinkUrl;
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

    public Boolean isHidden() {
        return isHidden;
    }

    public Date getScheduledPublishTime() {
        if (scheduledPublishTime == null) {
            return null;
        }
        return new Date(scheduledPublishTime * 1000);
    }

    public Targeting getTargeting() {
        return targeting;
    }

    /*package*/
    static ResponseList<Post> createPostList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Post> posts = new ResponseListImpl<Post>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject postJSONObject = list.getJSONObject(i);
                Post post = new PostJSONImpl(postJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(post, postJSONObject);
                }
                posts.add(post);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(posts, list);
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
                ", fullPicture=" + fullPicture +
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
                ", sharesCount=" + sharesCount +
                ", likes=" + likes +
                ", place=" + place +
                ", statusType='" + statusType + '\'' +
                ", story='" + story + '\'' +
                ", storyTags=" + storyTags +
                ", withTags=" + withTags +
                ", comments=" + comments +
                ", attachments=" + attachments +
                ", objectId='" + objectId + '\'' +
                ", application=" + application +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", isPublished=" + isPublished +
                ", isHidden=" + isHidden +
                ", scheduledPublishTime=" + scheduledPublishTime +
                ", targeting=" + targeting +
                ", permalinkUrl=" + permalinkUrl +
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

    private final class AttachmentJSONImpl implements Post.Attachment, java.io.Serializable {
        private static final long serialVersionUID = 8479952805957989278L;

        private String id;
        private String title;
        private String type;
        private String url;
        private List<Attachment> subattachments;

        AttachmentJSONImpl(JSONObject json) throws FacebookException {
            try {
                if (!json.isNull("target")) {
                    JSONObject targetJSONObject = json.getJSONObject("target");
                    id = getRawString("id", targetJSONObject);
                }
                if (!json.isNull("media")) {
                    JSONObject mediaJSONObject = json.getJSONObject("media");
                    JSONObject mediaJson = null;
                    if (!mediaJSONObject.isNull("video")) {
                        mediaJson = mediaJSONObject.getJSONObject("video");
                    }
                    else if (!mediaJSONObject.isNull("image")) {
                        mediaJson = mediaJSONObject.getJSONObject("image");
                    }
                    if(mediaJson!=null){
                        url = getRawString("src", mediaJson);
                    }
                    else{
                        url = getRawString("file_url", mediaJSONObject);
                    }
                }
                title = getRawString("title", json);
                type = getRawString("type", json);
                if (!json.isNull("subattachments")) {
                    JSONArray attachmentsJSONArray = json.getJSONObject("subattachments").getJSONArray("data");
                    final int size = attachmentsJSONArray.length();
                    subattachments = new ArrayList<Attachment>(size);
                    for (int i = 0; i < attachmentsJSONArray.length(); i++) {
                        JSONObject attachmentJsonObject = attachmentsJSONArray.getJSONObject(i);
                        subattachments.add(new AttachmentJSONImpl(attachmentJsonObject));
                    }
                } else {
                    subattachments = Collections.emptyList();
                }
            } catch (JSONException jsone) {
                throw new FacebookException(jsone.getMessage(), jsone);
            }
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public List<Attachment> getSubattachments() {
            return subattachments;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AttachmentJSONImpl that = (AttachmentJSONImpl) o;

            return !(id != null ? !id.equals(that.id) : that.id != null);

        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "AttachmentJSONImpl{" +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", subattachments=" + subattachments +
                    '}';
        }
    }

    public PagableList<Reaction> getReactions() {
        return reactions;
    }

}
