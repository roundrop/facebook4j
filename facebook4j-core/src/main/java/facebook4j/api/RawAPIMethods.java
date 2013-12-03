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

package facebook4j.api;

import java.util.Map;

import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Hiroaki Takeuchi - takke30 at gmail.com
 */
public interface RawAPIMethods {
    /**
     * Calls raw Facebook GET API simply
     * 
     * @param graphPath graphPath
     * @param params parameters
     * @return json object
     * @throws FacebookException when Facebook service or network is unavailable
     */
    JSONObject callGetAPI(String graphPath, Map<String, String> params) throws FacebookException;
    
    /**
     * Calls raw Facebook POST API simply
     * 
     * @param graphPath graphPath
     * @param params parameters
     * @return json object
     * @throws FacebookException when Facebook service or network is unavailable
     */
    JSONObject callPostAPI(String graphPath, Map<String, String> params) throws FacebookException;
    
    /**
     * Calls raw Facebook DELETE API simply
     * 
     * @param graphPath graphPath
     * @param params parameters
     * @return json object
     * @throws FacebookException when Facebook service or network is unavailable
     */
    boolean callDeleteAPI(String graphPath, Map<String, String> params) throws FacebookException;
    
    /**
     * Calls Facebook batch API
     * 
     * @param params batch parameters
     * @return json array object
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/making-multiple-requests/">Making Multiple API Requests - Facebook Developers</a>
     */
    JSONArray executeBatch(JSONArray params) throws FacebookException;

}
