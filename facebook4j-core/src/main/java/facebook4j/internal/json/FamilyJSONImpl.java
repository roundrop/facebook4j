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
import facebook4j.Family;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class FamilyJSONImpl extends UserJSONImpl implements Family, java.io.Serializable {
    private static final long serialVersionUID = 3676633893253219856L;

    private String relationship;
    
    /*package*/FamilyJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res, conf);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/FamilyJSONImpl(JSONObject json) throws FacebookException {
        super(json);
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        relationship = getRawString("relationship", json);
    }

    public String getRelationship() {
        return relationship;
    }

    /*package*/
    static ResponseList<Family> createFamilyList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Family> familys = new ResponseListImpl<Family>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject familyJSONObject = list.getJSONObject(i);
                Family family = new FamilyJSONImpl(familyJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(family, familyJSONObject);
                }
                familys.add(family);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(familys, list);
            }
            return familys;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "FamilyJSONImpl{" +
                "relationship='" + relationship + '\'' +
                '}' +
                " extends " + super.toString();
    }
}
