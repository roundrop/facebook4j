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
import facebook4j.Location;
import facebook4j.PlaceTag;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface LocationMethods {
    /**
     * Returns the posts with location.
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - locations
     */
    ResponseList<Location> getLocations() throws FacebookException;

    /**
     * Returns the posts with location.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - locations
     */
    ResponseList<Location> getLocations(Reading reading) throws FacebookException;

    /**
     * Returns the posts with location.
     * @param userId the ID of a user
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - locations
     */
    ResponseList<Location> getLocations(String userId) throws FacebookException;

    /**
     * Returns the posts with location.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - locations
     */
    ResponseList<Location> getLocations(String userId, Reading reading) throws FacebookException;
    
    /**
     * Returns a list of tags of this person at a place in a photo, video, post, status or link.
     * @return a list of Place Tag objects
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/tagged_places">User - Facebook Developers</a> - Connections - tagged places
     */
    ResponseList<PlaceTag> getTaggedPlaces() throws FacebookException;
    
    /**
     * Returns a list of tags of this person at a place in a photo, video, post, status or link.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return a list of Place Tag objects
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/tagged_places">User - Facebook Developers</a> - Connections - tagged places
     */
    ResponseList<PlaceTag> getTaggedPlaces(Reading reading) throws FacebookException;
    
    /**
     * Returns a list of tags of this person at a place in a photo, video, post, status or link.
     * @param userId the ID of a user
     * @return a list of Place Tag objects
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/tagged_places">User - Facebook Developers</a> - Connections - tagged places
     */
    ResponseList<PlaceTag> getTaggedPlaces(String userId) throws FacebookException;
    
    /**
     * Returns a list of tags of this person at a place in a photo, video, post, status or link.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return a list of Place Tag objects
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/user/tagged_places">User - Facebook Developers</a> - Connections - tagged places
     */
    ResponseList<PlaceTag> getTaggedPlaces(String userId, Reading reading) throws FacebookException;

}
