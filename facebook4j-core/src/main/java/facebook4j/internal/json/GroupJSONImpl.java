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
import facebook4j.Group;
import facebook4j.GroupPrivacyType;
import facebook4j.IdNameEntity;
import facebook4j.ResponseList;
import facebook4j.Venue;
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
/*package*/ final class GroupJSONImpl extends FacebookResponseImpl implements Group, java.io.Serializable {
    private static final long serialVersionUID = 561347972556756182L;

    private Integer version;
    private String name;
    private String id;
    private Boolean administrator;
    private Integer bookmarkOrder;
    
    private IdNameEntity owner;
    private String description;
    private GroupPrivacyType privacy;
    private URL icon;
    private Date updatedTime;
    private String email;
    private Venue venue;

    /*package*/GroupJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }
    /*package*/GroupJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            if (!json.isNull("version")) {
                version = getPrimitiveInt("version", json);
            }
            name = getRawString("name", json);
            id = getRawString("id", json);
            administrator = getBoolean("administrator", json);
            if (!json.isNull("bookmark_order")) {
                bookmarkOrder = getPrimitiveInt("bookmark_order", json);
            }
            
            if (!json.isNull("owner")) {
                owner = new IdNameEntityJSONImpl(json.getJSONObject("owner"));
            }
            description = getRawString("description", json);
            privacy = GroupPrivacyType.getInstance(getRawString("privacy", json));
            icon = getURL("icon", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            email = getRawString("email", json);
            if (!json.isNull("venue")) {
                venue = new VenueJSONImpl(json.getJSONObject("venue"));
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public Integer getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Boolean isAdministrator() {
        return administrator;
    }

    public Integer getBookmarkOrder() {
        return bookmarkOrder;
    }

    public IdNameEntity getOwner() {
        return owner;
    }
    public String getDescription() {
        return description;
    }
    public GroupPrivacyType getPrivacy() {
        return privacy;
    }
    public URL getIcon() {
        return icon;
    }
    public Date getUpdatedTime() {
        return updatedTime;
    }
    public String getEmail() {
        return email;
    }

    public Venue getVenue() {
        return venue;
    }

    /*package*/
    static ResponseList<Group> createGroupList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Group> groups = new ResponseListImpl<Group>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject groupJSONObject = list.getJSONObject(i);
                Group group = new GroupJSONImpl(groupJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(group, groupJSONObject);
                }
                groups.add(group);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(groups, list);
            }
            return groups;
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
        GroupJSONImpl other = (GroupJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GroupJSONImpl{" +
                "version=" + version +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", administrator=" + administrator +
                ", bookmarkOrder=" + bookmarkOrder +
                ", owner=" + owner +
                ", description='" + description + '\'' +
                ", privacy=" + privacy +
                ", icon=" + icon +
                ", updatedTime=" + updatedTime +
                ", email='" + email + '\'' +
                ", venue=" + venue +
                '}';
    }
}
