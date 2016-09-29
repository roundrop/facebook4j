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
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ class ResponseListImpl<T> extends PagableListImpl<T> implements ResponseList<T> {

    /*package*/ResponseListImpl(JSONObject json, T... t) throws FacebookException {
        super(json, t);
        init(json);
    }

    /*package*/ResponseListImpl(int size, JSONObject json, T... t) throws FacebookException {
        super(size, json, t);
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
    }

    /*package*/
    static ResponseList<JSONObject> createJSONObjectList(HttpResponse res, Configuration conf) throws FacebookException {
        return createJSONObjectList(res.asJSONObject(), conf);
    }

    /*package*/
    static ResponseList<JSONObject> createJSONObjectList(JSONObject json) throws FacebookException {
        return createJSONObjectList(json, null);
    }

    private static ResponseList<JSONObject> createJSONObjectList(JSONObject json, Configuration conf) throws FacebookException {
        try {
            if (null != conf && conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray jsonArray = json.getJSONArray("data");
            final int size = jsonArray.length();
            ResponseList<JSONObject> results = new ResponseListImpl<JSONObject>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (null != conf && conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(jsonObject, jsonObject);
                }
                results.add(jsonObject);
            }
            if (null != conf && conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(results, jsonArray);
            }
            return results;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }
}
