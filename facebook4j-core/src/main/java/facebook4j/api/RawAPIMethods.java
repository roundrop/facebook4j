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
import facebook4j.RawAPIResponse;
import facebook4j.internal.http.HttpParameter;

import java.util.Map;

/**
 * @since Facebook4J 2.1.0
 * @author Hiroaki Takeuchi - takke30 at gmail.com
 */
public interface RawAPIMethods {
    /**
     * Calls raw Facebook GET API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callGetAPI(String relativeUrl) throws FacebookException;

    /**
     * Calls raw Facebook GET API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @param parameters GET parameters
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callGetAPI(String relativeUrl, Map<String, String> parameters) throws FacebookException;

    /**
     * Calls raw Facebook GET API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @param parameters GET parameters
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callGetAPI(String relativeUrl, HttpParameter... parameters) throws FacebookException;

    /**
     * Calls raw Facebook POST API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callPostAPI(String relativeUrl) throws FacebookException;

    /**
     * Calls raw Facebook POST API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @param parameters POST parameters
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callPostAPI(String relativeUrl, Map<String, String> parameters) throws FacebookException;

    /**
     * Calls raw Facebook POST API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @param parameters POST parameters
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callPostAPI(String relativeUrl, HttpParameter... parameters) throws FacebookException;

    /**
     * Calls raw Facebook DELETE API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callDeleteAPI(String relativeUrl) throws FacebookException;

    /**
     * Calls raw Facebook DELETE API simply.
     * This method not supports "JSONStore" option because this method returns the json object itself.
     * @param relativeUrl relativeUrl
     * @param parameters parameters
     * @return response
     * @throws FacebookException when Facebook service or network is unavailable
     */
    RawAPIResponse callDeleteAPI(String relativeUrl, Map<String, String> parameters) throws FacebookException;

}
