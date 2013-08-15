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
import facebook4j.GroupMember;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/*package*/ final class GroupMemberJSONImpl extends UserJSONImpl implements GroupMember, java.io.Serializable {
    private static final long serialVersionUID = 8912198140971501463L;

    private Boolean isAdministrator;

    /*package*/GroupMemberJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res, conf);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }
    /*package*/GroupMemberJSONImpl(JSONObject json) throws FacebookException {
        super(json);
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        isAdministrator = getBoolean("administrator", json);
    }

    public Boolean isAdministrator() {
        return isAdministrator;
    }

    /*package*/
    static ResponseList<GroupMember> createGroupMemberList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<GroupMember> members = new ResponseListImpl<GroupMember>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject groupMemberJSONObject = list.getJSONObject(i);
                GroupMember member = new GroupMemberJSONImpl(groupMemberJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(member, groupMemberJSONObject);
                }
                members.add(member);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(members, list);
            }
            return members;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "GroupMemberJSONImpl{" +
                "isAdministrator=" + isAdministrator +
                '}' +
                " extends " + super.toString();
    }
}
