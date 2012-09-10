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

import facebook4j.Application;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Notification;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class NotificationJSONImpl implements Notification, java.io.Serializable {
    private static final long serialVersionUID = -4598915714655180624L;

    private String id;
    private IdNameEntity from;
    private IdNameEntity to;
    private Date createdTime;
    private Date updatedTime;
    private String title;
    private URL link;
    private Application application;
    private Boolean unread;

    /*package*/NotificationJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/NotificationJSONImpl(JSONObject json) throws FacebookException {
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
                JSONObject toJSONObject = json.getJSONObject("to");
                to = new IdNameEntityJSONImpl(toJSONObject);
            }
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            title = getRawString("title", json);
            link = getURL("link", json);
            unread = getBoolean("unread", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdNameEntity getFrom() {
        return from;
    }

    public void setFrom(IdNameEntity from) {
        this.from = from;
    }

    public IdNameEntity getTo() {
        return to;
    }

    public void setTo(IdNameEntity to) {
        this.to = to;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Boolean unread() {
        return unread;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

    /*package*/
    static ResponseList<Notification> createNotificationList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            ResponseList<Notification> notifications = new ResponseListImpl<Notification>(size, json);
            for (int i = 0; i < size; i++) {
                Notification notification = new NotificationJSONImpl(list.getJSONObject(i));
                notifications.add(notification);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(notifications, json);
            }
            return notifications;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "NotificationJSONImpl [id=" + id + ", from=" + from + ", to="
                + to + ", createdTime=" + createdTime + ", updatedTime="
                + updatedTime + ", title=" + title + ", link=" + link
                + ", application=" + application + ", unread=" + unread + "]";
    }

}
