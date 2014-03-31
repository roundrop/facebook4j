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

import facebook4j.BatchRequest;
import facebook4j.BatchRequests;
import facebook4j.BatchResponse;
import facebook4j.FacebookException;

import java.util.List;

/**
 * @since Facebook4J 2.1.0
 */
public interface BatchRequestsMethods {
    /**
     * Calls Facebook batch API.
     *
     * @param requests batch parameters
     * @return batch response
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/making-multiple-requests/">Making Multiple API Requests - Facebook Developers</a>
     */
    List<BatchResponse> executeBatch(BatchRequests<BatchRequest> requests) throws FacebookException;
}
