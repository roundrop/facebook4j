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

import facebook4j.Achievement;
import facebook4j.Application;
import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.ResponseList;
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
/*package*/ final class AchievementJSONImpl extends FacebookResponseImpl implements Achievement, java.io.Serializable {
    private static final long serialVersionUID = -4720957321727049130L;
    
    private String id;
    private IdNameEntity from;
    private Date startTime;
    private Date endTime;
    private Date publishTime;
    private Application application;
    private Integer importance;
    private Achievement.AchievedObject achievement;
    private PagableList<Like> likes;
    private PagableList<Comment> comments;

    /*package*/AchievementJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/AchievementJSONImpl(JSONObject json) throws FacebookException {
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
            startTime = getISO8601Datetime("start_time", json);
            endTime = getISO8601Datetime("end_time", json);
            publishTime = getISO8601Datetime("publish_time", json);
            if (!json.isNull("application")) {
                JSONObject applicationJSONObject = json.getJSONObject("application");
                application = new ApplicationJSONImpl(applicationJSONObject);
            }
            if (!json.isNull("importance")) {
                importance = getPrimitiveInt("importance", json);
            }
            if (!json.isNull("achievement")) {
                achievement = new AchievementJSONImpl.AchievedObjectJSONImpl(json.getJSONObject("achievement"));
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

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public Application getApplication() {
        return application;
    }

    public Integer getImportance() {
        return importance;
    }

    public Achievement.AchievedObject getAchievement() {
        return achievement;
    }

    public PagableList<Like> getLikes() {
        return likes;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    /*package*/
    static ResponseList<Achievement> createAchievementList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Achievement> achievements = new ResponseListImpl<Achievement>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject achievementJSONObject = list.getJSONObject(i);
                Achievement achievement = new AchievementJSONImpl(achievementJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(achievement, achievementJSONObject);
                }
                achievements.add(achievement);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(achievements, list);
            }
            return achievements;
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
        AchievementJSONImpl other = (AchievementJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AchievementJSONImpl [id=" + id + ", from=" + from
                + ", startTime=" + startTime + ", endTime=" + endTime
                + ", publishTime=" + publishTime + ", application="
                + application + ", importance=" + importance + ", achievement="
                + achievement + ", likes=" + likes + ", comments=" + comments
                + "]";
    }



    private final class AchievedObjectJSONImpl implements Achievement.AchievedObject, java.io.Serializable {
        private static final long serialVersionUID = 5301659229411941184L;

        private String id;
        private URL url;
        private String type;
        private String title;

        /*package*/AchievedObjectJSONImpl(JSONObject json) {
            id = getRawString("id", json);
            url = getURL("url", json);
            type = getRawString("type", json);
            title = getRawString("title", json);
        }

        public String getId() {
            return id;
        }

        public URL getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
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
            AchievedObjectJSONImpl other = (AchievedObjectJSONImpl) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "AchievedObjectJSONImpl [id=" + id + ", url=" + url
                    + ", type=" + type + ", title=" + title + "]";
        }

        private AchievementJSONImpl getOuterType() {
            return AchievementJSONImpl.this;
        }
    }
}
