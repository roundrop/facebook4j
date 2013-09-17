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

import facebook4j.Admin;
import facebook4j.FacebookException;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class AdminJSONImpl extends FacebookResponseImpl implements Admin, java.io.Serializable {
    private static final long serialVersionUID = 392257519791459917L;

    private String id;
    private String name;
    private String role;
    private List<String> perms;

    /*package*/AdminJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/AdminJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
        role = getRawString("role", json);
        if (!json.isNull("perms")) {
            try {
                JSONArray permsJSONArray = json.getJSONArray("perms");
                final int size = permsJSONArray.length();
                perms = new ArrayList<String>(size);
                for (int i = 0; i < permsJSONArray.length(); i++) {
                    perms.add(permsJSONArray.getString(i));
                }
            } catch (JSONException jsone) {
                throw new FacebookException(jsone);
            }
        } else {
            perms = Collections.emptyList();
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public List<String> getPerms() {
        return perms;
    }

    /*package*/
    static ResponseList<Admin> createAdminList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Admin> admins = new ResponseListImpl<Admin>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject adminJSONObject = list.getJSONObject(i);
                Admin admin = new AdminJSONImpl(adminJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(admin, adminJSONObject);
                }
                admins.add(admin);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(admins, list);
            }
            return admins;
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
        AdminJSONImpl other = (AdminJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AdminJSONImpl{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", perms=" + perms +
                '}';
    }
}
