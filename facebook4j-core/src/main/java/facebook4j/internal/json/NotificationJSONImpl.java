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
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Notification;
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
/*package*/ final class NotificationJSONImpl extends FacebookResponseImpl implements Notification, java.io.Serializable {
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
    private Notification.TargetObject targetObject;

    /*package*/NotificationJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
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
            if (!json.isNull("application")) {
                application = new ApplicationJSONImpl(json.getJSONObject("application"));
            }
            if (!json.isNull("unread")) {
                unread = getFlag("unread", json);
            }
            if (!json.isNull("object")) {
                targetObject = new TargetObjectJSONImpl(json.getJSONObject("object"));
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

    public IdNameEntity getTo() {
        return to;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public String getTitle() {
        return title;
    }

    public URL getLink() {
        return link;
    }

    public Application getApplication() {
        return application;
    }

    public Boolean unread() {
        return unread;
    }

    public TargetObject getTargetObject() {
        return targetObject;
    }

    /*package*/
    static ResponseList<Notification> createNotificationList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Notification> notifications = new ResponseListImpl<Notification>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject notificationJSONObject = list.getJSONObject(i);
                Notification notification = new NotificationJSONImpl(notificationJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(notification, notificationJSONObject);
                }
                notifications.add(notification);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(notifications, list);
            }
            return notifications;
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
        NotificationJSONImpl other = (NotificationJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "NotificationJSONImpl [id=" + id + ", from=" + from + ", to="
                + to + ", createdTime=" + createdTime + ", updatedTime="
                + updatedTime + ", title=" + title + ", link=" + link
                + ", application=" + application + ", unread=" + unread + "]";
    }

    private static class TargetObjectJSONImpl implements Notification.TargetObject, java.io.Serializable {
        private static final long serialVersionUID = 6760783049866927374L;

        private String id;
        private String type;

        TargetObjectJSONImpl(JSONObject json) {
            id = getRawString("id", json);
            type = getRawString("type", json);
        }

        public String getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TargetObjectJSONImpl)) return false;

            TargetObjectJSONImpl that = (TargetObjectJSONImpl) o;

            if (id != null ? !id.equals(that.id) : that.id != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "TargetObjectJSONImpl{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

}
