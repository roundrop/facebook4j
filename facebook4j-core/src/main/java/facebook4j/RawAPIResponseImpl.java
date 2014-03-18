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

package facebook4j;

import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;

/**
 * @since Facebook4J 2.1.0
 */
/*package*/ final class RawAPIResponseImpl implements RawAPIResponse, java.io.Serializable {
    private static final long serialVersionUID = 8389958738540393505L;

    private final String responseAsString;
    private final JSONObject jsonObject;
    private final JSONArray jsonArray;
    private final Boolean bool;

    /*package*/RawAPIResponseImpl(HttpResponse res) throws FacebookException {
        responseAsString = res.asString();
        if (responseAsString.startsWith("{")) {
            jsonObject = res.asJSONObject();
            jsonArray = null;
            bool = null;
        } else
        if (responseAsString.startsWith("[")) {
            jsonObject = null;
            jsonArray = res.asJSONArray();
            bool = null;
        } else {
            jsonObject = null;
            jsonArray = null;
            bool = Boolean.valueOf(responseAsString.trim());
        }
    }

    public boolean isJSONObject() {
        return (jsonObject != null);
    }

    public boolean isJSONArray() {
        return (jsonArray != null);
    }

    public boolean isBoolean() {
        return (bool != null);
    }

    public JSONObject asJSONObject() throws FacebookException {
        return jsonObject;
    }

    public JSONArray asJSONArray() throws FacebookException {
        return jsonArray;
    }

    public boolean asBoolean() throws FacebookException {
        return bool;
    }

    public String asString() {
        return responseAsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RawAPIResponseImpl)) return false;

        RawAPIResponseImpl that = (RawAPIResponseImpl) o;

        if (responseAsString != null ? !responseAsString.equals(that.responseAsString) : that.responseAsString != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return responseAsString != null ? responseAsString.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RawAPIResponseImpl{" +
                "responseAsString='" + responseAsString + '\'' +
                '}';
    }
}
