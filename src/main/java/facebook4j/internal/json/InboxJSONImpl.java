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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Inbox;
import facebook4j.InboxResponseList;
import facebook4j.PagableList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class InboxJSONImpl implements Inbox, java.io.Serializable {
    private static final long serialVersionUID = -6689657536816020492L;

    private String id;
    private List<IdNameEntity> to;
    private Date updatedTime;
    private Integer unread;
    private Integer unseen;
    private PagableList<Comment> comments;

    /*package*/InboxJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/InboxJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("to")) {
                to = new ArrayList<IdNameEntity>();
                JSONArray toJSONArray = json.getJSONObject("to").getJSONArray("data");
                for (int i = 0; i < toJSONArray.length(); i++) {
                    to.add(new IdNameEntityJSONImpl(toJSONArray.getJSONObject(i)));
                }
            }
            updatedTime = getFacebookDatetime("updated_time", json);
            if (!json.isNull("unread")) {
                unread = getPrimitiveInt("unread", json);
            }
            if (!json.isNull("unseen")) {
                unseen = getPrimitiveInt("unseen", json);
            }
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
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public List<IdNameEntity> getTo() {
        return to;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public Integer getUnread() {
        return unread;
    }

    public Integer getUnseen() {
        return unseen;
    }

    public PagableList<Comment> getComments() {
        return comments;
    }

    /*package*/
    static InboxResponseList<Inbox> createInboxList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            InboxResponseList<Inbox> inboxes = new InboxResponseListImpl<Inbox>(size, json);
            for (int i = 0; i < size; i++) {
                Inbox inbox = new InboxJSONImpl(list.getJSONObject(i));
                inboxes.add(inbox);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(inboxes, json);
            }
            return inboxes;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "InboxJSONImpl [id=" + id + ", to=" + to + ", updatedTime="
                + updatedTime + ", unread=" + unread + ", unseen=" + unseen
                + ", comments=" + comments + "]";
    }

}
