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
import facebook4j.ResponseList;
import facebook4j.TestUser;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 *
 */
/*package*/ final class TestUserJSONImpl implements TestUser, java.io.Serializable {
    private static final long serialVersionUID = -5704997286478031118L;
    
    private String id;
    private String accessToken;
    private String loginUrl;
    private String email;
    private String password;
    
    /*package*/TestUserJSONImpl(HttpResponse res) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
    }
    
    /*package*/TestUserJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    /*package*/
    static ResponseList<TestUser> createTestUserList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<TestUser> testUsers = new ResponseListImpl<TestUser>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject testUserJSONObject = list.getJSONObject(i);
                TestUser testUser = new TestUserJSONImpl(testUserJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(testUser, testUserJSONObject);
                }
                testUsers.add(testUser);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(testUsers, list);
            }
            return testUsers;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    
    
    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        accessToken = getRawString("access_token", json);
        loginUrl = getRawString("login_url", json);
        if (!json.isNull("email")) {
            email = getRawString("email", json);
        }
        if (!json.isNull("password")) {
            password = getRawString("password", json);
        }
    }

    public int compareTo(TestUser that) {
        return this.id.compareTo(that.getId());
    }

    public String getId() {
        return id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
        TestUserJSONImpl other = (TestUserJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TestUserJSONImpl [id=" + id + ", accessToken=" + accessToken
                + ", loginUrl=" + loginUrl + ", email=" + email + ", password="
                + password + "]";
    }

}
