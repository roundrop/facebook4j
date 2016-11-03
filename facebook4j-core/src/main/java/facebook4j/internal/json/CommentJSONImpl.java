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
import facebook4j.PagableList;
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class CommentJSONImpl extends FacebookResponseImpl implements Comment, java.io.Serializable {
    private static final long serialVersionUID = -4004859844463425632L;

    private String id;
    private Category from;
    private String message;
    private List<Tag> messageTags;
    private Boolean canComment;
    private Boolean canRemove;
    private Boolean canHide;
    private Boolean canLike;
    private Date createdTime;
    private Integer likeCount;
    private Integer commentCount;
    private Boolean isUserLikes;
    private Boolean isHidden;
    private Attachment attachment;
    private Comment parent;
    private PagableList<Comment> comments;

    /*package*/CommentJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/CommentJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("from")) {
                JSONObject fromJSONObject = json.getJSONObject("from");
                from = new CategoryJSONImpl(fromJSONObject);
            } else {
                from = null;
            }
            message = getRawString("message", json);
            if (!json.isNull("message_tags")) {
                JSONArray tagsJSONArray = json.getJSONArray("message_tags");
                final int size = tagsJSONArray.length();
                messageTags = new ArrayList<Tag>(size);
                for (int i = 0; i < size; i++) {
                    messageTags.add(new TagJSONImpl(tagsJSONArray.getJSONObject(i)));
                }
            } else {
                messageTags = Collections.emptyList();
            }
            canComment = getBoolean("can_comment", json);
            canRemove = getBoolean("can_remove", json);
            canHide = getBoolean("can_hide", json);
            canLike = getBoolean("can_like", json);
            createdTime = getISO8601Datetime("created_time", json);
            likeCount = getInt("like_count", json);
            commentCount = getInt("comment_count", json);
            isUserLikes = getBoolean("user_likes", json);
            isHidden = getBoolean("is_hidden", json);
            if (!json.isNull("attachment")) {
                attachment = new AttachmentJSONImpl(json.getJSONObject("attachment"));
            }
            if (!json.isNull("parent")) {
                parent = new CommentJSONImpl(json.getJSONObject("parent"));
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

    public String getMessage() {
        return message;
    }

    public List<Tag> getMessageTags() {
        return messageTags;
    }

    public Boolean canComment() {
        return canComment;
    }

    public Boolean canRemove() {
        return canRemove;
    }

    public Boolean canHide() {
        return canHide;
    }

    public Boolean canLike() {
        return canLike;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Boolean isUserLikes() {
        return isUserLikes;
    }

    public Boolean isHidden() {
        return isHidden;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public Comment getParent() {
        return parent;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    /*package*/
    static ResponseList<Comment> createCommentList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Comment> comments = new ResponseListImpl<Comment>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject commentJSONObject = list.getJSONObject(i);
                Comment comment = new CommentJSONImpl(commentJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(comment, commentJSONObject);
                }
                comments.add(comment);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(comments, list);
            }
            return comments;
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
        CommentJSONImpl other = (CommentJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CommentJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", message='" + message + '\'' +
                ", messageTags=" + messageTags +
                ", canComment=" + canComment +
                ", canRemove=" + canRemove +
                ", canHide=" + canHide +
                ", canLike=" + canLike +
                ", createdTime=" + createdTime +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", isUserLikes=" + isUserLikes +
                ", isHidden=" + isHidden +
                ", attachment=" + attachment +
                ", parent=" + parent +
                ", comments=" + comments +
                '}';
    }

    private final class AttachmentJSONImpl implements Comment.Attachment, java.io.Serializable {
        private static final long serialVersionUID = 5515482060929228129L;

        private String description;
        private AttachmentMedia media;
        private AttachmentTarget target;
        private String title;
        private String type;
        private String url;

        AttachmentJSONImpl(JSONObject json) throws FacebookException {
            try {
                description = getRawString("description", json);
                if (!json.isNull("media")) {
                    media = new AttachmentMediaJSONImpl(json.getJSONObject("media"));
                }
                if (!json.isNull("target")) {
                    target = new AttachmentTargetJSONImpl(json.getJSONObject("target"));
                }
                title = getRawString("title", json);
                type = getRawString("type", json);
                url = getRawString("url", json);
            } catch (JSONException jsone) {
                throw new FacebookException(jsone.getMessage(), jsone);
            }
        }

        public String getDescription() {
            return description;
        }

        public AttachmentMedia getMedia() {
            return media;
        }

        public AttachmentTarget getTarget() {
            return target;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AttachmentJSONImpl)) return false;

            AttachmentJSONImpl that = (AttachmentJSONImpl) o;

            if (description != null ? !description.equals(that.description) : that.description != null) return false;
            if (media != null ? !media.equals(that.media) : that.media != null) return false;
            if (target != null ? !target.equals(that.target) : that.target != null) return false;
            if (title != null ? !title.equals(that.title) : that.title != null) return false;
            if (type != null ? !type.equals(that.type) : that.type != null) return false;
            if (url != null ? !url.equals(that.url) : that.url != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = description != null ? description.hashCode() : 0;
            result = 31 * result + (media != null ? media.hashCode() : 0);
            result = 31 * result + (target != null ? target.hashCode() : 0);
            result = 31 * result + (title != null ? title.hashCode() : 0);
            result = 31 * result + (type != null ? type.hashCode() : 0);
            result = 31 * result + (url != null ? url.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "AttachmentJSONImpl{" +
                    "description='" + description + '\'' +
                    ", media=" + media +
                    ", target=" + target +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        private final class AttachmentMediaJSONImpl implements AttachmentMedia, java.io.Serializable {
            private static final long serialVersionUID = -4030126370782822645L;

            private final Image image;

            AttachmentMediaJSONImpl(JSONObject json) throws FacebookException {
                try {
                    image = new ImageJSONImpl(json.getJSONObject("image"));
                } catch (JSONException jsone) {
                    throw new FacebookException(jsone.getMessage(), jsone);
                }
            }

            public Image getImage() {
                return image;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof AttachmentMediaJSONImpl)) return false;

                AttachmentMediaJSONImpl that = (AttachmentMediaJSONImpl) o;

                return !(!image.equals(that.image));

            }

            @Override
            public int hashCode() {
                return image.hashCode();
            }

            @Override
            public String toString() {
                return "AttachmentMediaJSONImpl{" +
                        "image=" + image +
                        '}';
            }
        }

        private final class AttachmentTargetJSONImpl implements AttachmentTarget, java.io.Serializable {
            private String id;
            private String url;

            AttachmentTargetJSONImpl(JSONObject json) {
                id = getRawString("id", json);
                url = getRawString("url", json);
            }

            public String getId() {
                return id;
            }

            public String getUrl() {
                return url;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof AttachmentTargetJSONImpl)) return false;

                AttachmentTargetJSONImpl that = (AttachmentTargetJSONImpl) o;

                if (id != null ? !id.equals(that.id) : that.id != null) return false;
                if (url != null ? !url.equals(that.url) : that.url != null) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = id != null ? id.hashCode() : 0;
                result = 31 * result + (url != null ? url.hashCode() : 0);
                return result;
            }

            @Override
            public String toString() {
                return "AttachmentTargetJSONImpl{" +
                        "id='" + id + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }
}
