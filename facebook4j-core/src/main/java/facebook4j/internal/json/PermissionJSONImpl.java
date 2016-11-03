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
import facebook4j.Permission;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PermissionJSONImpl implements Permission, java.io.Serializable {
    private static final long serialVersionUID = -3507159691486381813L;
    
    private String name;
    private boolean isGranted;

    private PermissionJSONImpl(String name, boolean isGranted) {
        this.name = name;
        this.isGranted = isGranted;
    }

    public String getName() {
        return name;
    }

    public boolean isGranted() {
        return isGranted;
    }

    /*package*/
    static List<Permission> createPermissionArray(HttpResponse res, Configuration conf) throws FacebookException {
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        List<Permission> permissions = new ArrayList<Permission>();
        JSONObject json = res.asJSONObject();
        try {
            JSONArray list = json.getJSONArray("data");
            for (int i = 0; i < list.length(); i++) {
                JSONObject permissionJSONObject = list.getJSONObject(i);
                String permissionName = permissionJSONObject.getString("permission");
                boolean isGranted =  "granted".equalsIgnoreCase(permissionJSONObject.getString("status"));
                permissions.add(new PermissionJSONImpl(permissionName, isGranted));
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(permissions, list);
            }
            return permissions;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isGranted ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        PermissionJSONImpl other = (PermissionJSONImpl) obj;
        if (isGranted != other.isGranted)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PermissionJSONImpl [name=" + name + ", isGranted=" + isGranted
                + "]";
    }
    
}
