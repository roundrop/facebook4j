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

import facebook4j.FacebookException;
import facebook4j.FriendRequest;
import facebook4j.IdNameEntity;
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
/*package*/ final class FriendRequestJSONImpl extends FacebookResponseImpl implements FriendRequest, java.io.Serializable {
    private static final long serialVersionUID = 6531121120616740631L;
    
    private IdNameEntity from;
    private IdNameEntity to;
    private Date createdTime;
    private String message;
    private Boolean unread;

    /*package*/FriendRequestJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/FriendRequestJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            if (!json.isNull("from")) {
                JSONObject fromJSONObject = json.getJSONObject("from");
                from = new IdNameEntityJSONImpl(fromJSONObject);
            } else {
                from = null;
            }
            if (!json.isNull("to")) {
                JSONObject toJSONObject = json.getJSONObject("to");
                to = new IdNameEntityJSONImpl(toJSONObject);
            } else {
                to = null;
            }
            createdTime = getISO8601Datetime("created_time", json);
            message = getRawString("message", json);
            unread = getBoolean("unread", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public IdNameEntity getFrom() {
        return from;
    }

    public IdNameEntity getTo() {
        return to;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getMessage() {
        return message;
    }

    public Boolean unread() {
        return unread;
    }

    /*package*/
    static ResponseList<FriendRequest> createFriendRequestList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<FriendRequest> friendRequests = new ResponseListImpl<FriendRequest>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject friendRequestJSONObject = list.getJSONObject(i);
                FriendRequest friendRequest = new FriendRequestJSONImpl(friendRequestJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(friendRequest, friendRequestJSONObject);
                }
                friendRequests.add(friendRequest);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(friendRequests, list);
            }
            return friendRequests;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createdTime == null) ? 0 : createdTime.hashCode());
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((to == null) ? 0 : to.hashCode());
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
        FriendRequestJSONImpl other = (FriendRequestJSONImpl) obj;
        if (createdTime == null) {
            if (other.createdTime != null)
                return false;
        } else if (!createdTime.equals(other.createdTime))
            return false;
        if (from == null) {
            if (other.from != null)
                return false;
        } else if (!from.equals(other.from))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (to == null) {
            if (other.to != null)
                return false;
        } else if (!to.equals(other.to))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FriendRequestJSONImpl [from=" + from + ", to=" + to
                + ", createdTime=" + createdTime + ", unread=" + unread + "]";
    }

}
