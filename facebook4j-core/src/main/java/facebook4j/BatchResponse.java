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

import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @since Facebook4J 2.1.0
 */
public interface BatchResponse {
    int getStatusCode();

    String getResponseHeader(String name);
    Map<String, List<String>> getResponseHeaderFields();

    String asString() throws FacebookException;
    JSONObject asJSONObject() throws FacebookException;
    ResponseList<JSONObject> asResponseList() throws FacebookException;
    JSONArray asJSONArray() throws FacebookException;
}
