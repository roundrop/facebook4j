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

import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONArray;

import java.util.Locale;
import java.util.Map;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface FQLMethods {
    /**
     * Executes a single FQL query.
     * @param query FQL query
     * @return array of json objects
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/fql/">Facebook Query Language (FQL) - Facebook Developers</a>
     */
    JSONArray executeFQL(String query) throws FacebookException;

    /**
     * Executes a single FQL query.
     * @param query FQL query
     * @param locale Locale
     * @return array of json objects
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/fql/">Facebook Query Language (FQL) - Facebook Developers</a>
     */
    JSONArray executeFQL(String query, Locale locale) throws FacebookException;

    /**
     * Executes multiple FQL queries.
     * @param queries FQL queries
     * @return results Map (key => given query's key : value => array of json objects)
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/fql/">Facebook Query Language (FQL) - Facebook Developers</a>
     */
    Map<String, JSONArray> executeMultiFQL(Map<String, String> queries) throws FacebookException;

    /**
     * Executes multiple FQL queries.
     * @param queries FQL queries
     * @param locale Locale
     * @return results Map (key => given query's key : value => array of json objects)
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/fql/">Facebook Query Language (FQL) - Facebook Developers</a>
     */
    Map<String, JSONArray> executeMultiFQL(Map<String, String> queries, Locale locale) throws FacebookException;

}
