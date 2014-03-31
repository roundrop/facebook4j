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
import facebook4j.internal.json.z_F4JInternalFactory;
import facebook4j.internal.json.z_F4JInternalJSONImplFactory;
import facebook4j.internal.logging.Logger;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @since Facebook4J 2.1.0
 */
public class BatchResponseImpl extends HttpResponse implements BatchResponse, java.io.Serializable {
    private static final long serialVersionUID = -6354217417137066979L;

    private static final Logger logger = Logger.getLogger(BatchResponseImpl.class);

    private JSONObject json;

    /*package*/BatchResponseImpl(JSONObject json) {
        this.json = json;
        if (null != json) {
            this.statusCode = getInt("code", json);
        }
    }

    private ConcurrentHashMap<String, List<String>> headers = new ConcurrentHashMap<String, List<String>>();

    @Override
    public String getResponseHeader(String name) {
        if (json == null) return null;

        if (headers.isEmpty()) {
            cacheHeaders();
        }
        if (!headers.containsKey(name)) {
            return null;
        }
        List<String> values = headers.get(name);
        return values.get(values.size()-1);
    }

    @Override
    public Map<String, List<String>> getResponseHeaderFields() {
        if (headers.isEmpty()) {
            cacheHeaders();
        }
        return headers;
    }

    private void cacheHeaders() {
        try {
            JSONArray array = json.getJSONArray("headers");
            for (int i = 0; i < array.length(); i++) {
                JSONObject headersJsonObject = array.getJSONObject(i);
                String n = getRawString("name", headersJsonObject);
                String v = getRawString("value", headersJsonObject);
                synchronized (headers) {
                    List<String> values = headers.get(n);
                    if (values == null) {
                        values = new ArrayList<String>();
                    }
                    values.add(v);
                    headers.put(n, values);
                }
            }
        } catch (JSONException ignore) {}
    }

    @Override
    public String asString() throws FacebookException {
        if (null == responseAsString) {
            if (null != json) {
                responseAsString = getRawString("body", json);
            }
        }
        return responseAsString;
    }

    private JSONObject bodyJson = null;

    @Override
    public JSONObject asJSONObject() throws FacebookException {
        if (bodyJson == null) {
            try {
                bodyJson = new JSONObject(asString());
                if (CONF.isPrettyDebugEnabled()) {
                    logger.debug(bodyJson.toString(1));
                } else {
                    logger.debug(responseAsString != null ? responseAsString : bodyJson.toString());
                }
            } catch (JSONException jsone) {
                if (responseAsString == null) {
                    throw new FacebookException(jsone.getMessage(), jsone);
                } else {
                    throw new FacebookException(jsone.getMessage() + ":" + this.responseAsString, jsone);
                }
            }
        }
        return bodyJson;
    }

    private final z_F4JInternalFactory factory = new z_F4JInternalJSONImplFactory(null);

    public ResponseList<JSONObject> asResponseList() throws FacebookException {
        return factory.createJSONObjectList(asJSONObject());
    }

    private JSONArray bodyJsonArray = null;

    @Override
    public JSONArray asJSONArray() throws FacebookException {
        if (bodyJsonArray == null) {
            try {
                bodyJsonArray = new JSONArray(asString());
                if (CONF.isPrettyDebugEnabled()) {
                    logger.debug(bodyJsonArray.toString(1));
                } else {
                    logger.debug(responseAsString != null ? responseAsString : bodyJsonArray.toString());
                }
            } catch (JSONException jsone) {
                if (responseAsString == null) {
                    throw new FacebookException(jsone.getMessage(), jsone);
                } else {
                    throw new FacebookException(jsone.getMessage() + ":" + this.responseAsString, jsone);
                }
            }
        }
        return bodyJsonArray;
    }

    @Override
    public void disconnect() throws IOException {
        // nop
    }

    @Override
    public InputStream asStream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader asReader() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "BatchResponse{" +
                "statusCode=" + statusCode +
                ", responseAsString=" + responseAsString +
                ", json=" + json +
                '}';
    }
}
