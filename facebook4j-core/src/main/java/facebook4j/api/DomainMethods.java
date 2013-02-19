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

import java.util.List;

import facebook4j.Domain;
import facebook4j.FacebookException;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface DomainMethods {
    /**
     * Returns the website domain information using Facebook.
     * @param domainId the ID of a website domain
     * @return domain
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/domain/">Domain - Facebook Developers</a>
     */
    Domain getDomain(String domainId) throws FacebookException;

    /**
     * Returns the website domain information using Facebook.
     * @param domainName the name of a website domain
     * @return domain
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/domain/">Domain - Facebook Developers</a>
     */
    Domain getDomainByName(String domainName) throws FacebookException;

    /**
     * Returns the website domains information using Facebook.
     * @param domainName the name list of website domain
     * @return domains
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/domain/">Domain - Facebook Developers</a>
     */
    List<Domain> getDomainsByName(String... domainName) throws FacebookException;

}
