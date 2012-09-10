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

import java.util.Date;

import facebook4j.Application;
import facebook4j.Checkin;
import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.PagableList;
import facebook4j.Place;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class CheckinJSONImpl implements Checkin, java.io.Serializable {
    private static final long serialVersionUID = 2502877498804174869L;
    
    private String id;
    private IdNameEntity from;
    private PagableList<IdNameEntity> tags;
    private Place place;
    private Application application;
    private Date createdTime;
    private PagableList<IdNameEntity> likes;
    private String message;
    private PagableList<Comment> comments;
    private String type;

    /*package*/CheckinJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/CheckinJSONImpl(JSONObject json) throws FacebookException {
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
                tags = new PagableListImpl<IdNameEntity>(size, tagsJSONObject);
                for (int i = 0; i < size; i++) {
                    IdNameEntityJSONImpl tag = new IdNameEntityJSONImpl(list.getJSONObject(i));
                    tags.add(tag);
                }
            }
            if (!json.isNull("place")) {
                JSONObject placeJSONObject = json.getJSONObject("place");
                place = new PlaceJSONImpl(placeJSONObject);
            }
            if (!json.isNull("application")) {
                JSONObject applicationJSONObject = json.getJSONObject("application");
                application = new ApplicationJSONImpl(applicationJSONObject);
            }
            createdTime = getISO8601Datetime("created_time", json);
            if (!json.isNull("likes")) {
                JSONObject likesJSONObject = json.getJSONObject("likes");
                JSONArray list = likesJSONObject.getJSONArray("data");
                int size = list.length();
                likes = new PagableListImpl<IdNameEntity>(size, likesJSONObject);
                for (int i = 0; i < size; i++) {
                    IdNameEntityJSONImpl like = new IdNameEntityJSONImpl(list.getJSONObject(i));
                    likes.add(like);
                }
            }
            message = getRawString("message", json);
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
            type = getRawString("type", json);
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
    
    public PagableList<IdNameEntity> getTags() {
        return tags;
    }

    public Place getPlace() {
        return place;
    }

    public Application getApplication() {
        return application;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public PagableList<IdNameEntity> getLikes() {
        return likes;
    }

    public String getMessage() {
        return message;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }
    
    public String getType() {
        return type;
    }

    /*package*/
    static ResponseList<Checkin> createCheckinList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            ResponseList<Checkin> checkins = new ResponseListImpl<Checkin>(size, json);
            for (int i = 0; i < size; i++) {
                Checkin checkin = new CheckinJSONImpl(list.getJSONObject(i));
                checkins.add(checkin);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(checkins, json);
            }
            return checkins;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "CheckinJSONImpl [id=" + id + ", from=" + from + ", tags="
                + tags + ", place=" + place + ", application=" + application
                + ", createdTime=" + createdTime + ", likes=" + likes
                + ", message=" + message + ", comments=" + comments + ", type="
                + type + "]";
    }

}
