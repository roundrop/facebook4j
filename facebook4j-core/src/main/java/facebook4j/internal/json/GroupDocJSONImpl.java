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
import facebook4j.GroupDoc;
import facebook4j.IdNameEntity;
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
/*package*/ final class GroupDocJSONImpl extends FacebookResponseImpl implements GroupDoc, java.io.Serializable {
    private static final long serialVersionUID = -4514164915020257301L;

    private String id;
    private IdNameEntity from;
    private String subject;
    private String message;
    private URL icon;
    private Date createdTime;
    private Date updatedTime;
    private Long revision;
    private Boolean canEdit;
    private Boolean canDelete;

    /*package*/GroupDocJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/GroupDocJSONImpl(JSONObject json) throws FacebookException {
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
            subject = getRawString("subject", json);
            message = getRawString("message", json);
            icon = getURL("icon", json);
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            revision = getLong("revision", json);
            canEdit = getBoolean("can_edit", json);
            canDelete = getBoolean("can_delete", json);
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

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public URL getIcon() {
        return icon;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public Long getRevision() {
        return revision;
    }

    public Boolean canEdit() {
        return canEdit;
    }

    public Boolean canDelete() {
        return canDelete;
    }

    /*package*/
    static ResponseList<GroupDoc> createGroupDocList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<GroupDoc> docs = new ResponseListImpl<GroupDoc>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject groupDocJSONObject = list.getJSONObject(i);
                GroupDoc doc = new GroupDocJSONImpl(groupDocJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(doc, groupDocJSONObject);
                }
                docs.add(doc);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(docs, list);
            }
            return docs;
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
        GroupDocJSONImpl other = (GroupDocJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GroupDocJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", icon=" + icon +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", revision=" + revision +
                ", canEdit=" + canEdit +
                ", canDelete=" + canDelete +
                '}';
    }
}
