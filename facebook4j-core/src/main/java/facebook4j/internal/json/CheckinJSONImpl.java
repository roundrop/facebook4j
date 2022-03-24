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

import facebook4j.Checkin;
import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.GeoLocation;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.getRawString;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class CheckinJSONImpl extends AbstractTravelJSONImpl implements Checkin, java.io.Serializable {
    private static final long serialVersionUID = 2502877498804174869L;

    private PagableList<Like> likes;
    private String message;
    private PagableList<Comment> comments;
    private String type;
    private GeoLocation coordinates;

    /*package*/CheckinJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res,conf);
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
            initTravelBaseJson(json);
            likes = LikeJSONImpl.convertJsonToLikeList(json);
            message = getRawString("message", json);
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
            type = getRawString("type", json);
            if (!json.isNull("coordinates")) {
                JSONObject coordinatesJSONObject = json.getJSONObject("coordinates");
                coordinates = new GeoLocation(coordinatesJSONObject.getDouble("latitude"),
                                              coordinatesJSONObject.getDouble("longitude"));
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public PagableList<Like> getLikes() {
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

    public GeoLocation getCoordinates() {
        return coordinates;
    }

    /*package*/
    static ResponseList<Checkin> createCheckinList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Checkin> checkins = new ResponseListImpl<Checkin>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject checkinJSONObject = list.getJSONObject(i);
                Checkin checkin = new CheckinJSONImpl(checkinJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(checkin, checkinJSONObject);
                }
                checkins.add(checkin);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(checkins, list);
            }
            return checkins;
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
        CheckinJSONImpl other = (CheckinJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CheckinJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", tags=" + tags +
                ", place=" + place +
                ", application=" + application +
                ", createdTime=" + createdTime +
                ", likes=" + likes +
                ", message='" + message + '\'' +
                ", comments=" + comments +
                ", type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
