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

import facebook4j.*;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface SearchMethods {
    /**
     * Searches public posts.
     * @param query the search condition
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Post> searchPosts(String query) throws FacebookException;

    /**
     * Searches public posts.
     * @param query the search condition
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return posts
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Post> searchPosts(String query, Reading reading) throws FacebookException;


    /**
     * Searches users.
     * @param query the search condition
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<User> searchUsers(String query) throws FacebookException;

    /**
     * Searches users.
     * @param query the search condition
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<User> searchUsers(String query, Reading reading) throws FacebookException;

    /**
     * Searches events.
     * @param query the search condition
     * @return events
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Event> searchEvents(String query) throws FacebookException;

    /**
     * Searches events.
     * @param query the search condition
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return events
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Event> searchEvents(String query, Reading reading) throws FacebookException;


    /**
     * Searches groups.
     * @param query the search condition
     * @return groups
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Group> searchGroups(String query) throws FacebookException;

    /**
     * Searches groups.
     * @param query the search condition
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return groups
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Group> searchGroups(String query, Reading reading) throws FacebookException;


    /**
     * Searches places.
     * @param query the search condition
     * @return places
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Place> searchPlaces(String query) throws FacebookException;

    /**
     * Searches places.
     * @param query the search condition
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return places
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Place> searchPlaces(String query, Reading reading) throws FacebookException;

    /**
     * Searches places.
     * @param query the search condition
     * @param center latitude and longitude
     * @param distance distance
     * @return places
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Place> searchPlaces(String query, GeoLocation center, int distance) throws FacebookException;

    /**
     * Searches places. narrows the search to a specific location and distance.
     * @param query the search condition
     * @param center latitude and longitude
     * @param distance distance
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return places
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Place> searchPlaces(String query, GeoLocation center, int distance, Reading reading) throws FacebookException;


    /**
     * Returns latest checkins.
     * @return checkins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Checkin> searchCheckins() throws FacebookException;

    /**
     * Returns latest checkins.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return checkins
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Checkin> searchCheckins(Reading reading) throws FacebookException;


    /**
     * Returns locations near a geographical location.
     * @param center latitude and longitude
     * @param distance distance
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Location> searchLocations(GeoLocation center, int distance) throws FacebookException;

    /**
     * Returns locations near a geographical location.
     * @param center latitude and longitude
     * @param distance distance
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Location> searchLocations(GeoLocation center, int distance, Reading reading) throws FacebookException;

    /**
     * Returns a particular place.
     * @param placeId specify the ID of the place
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Location> searchLocations(String placeId) throws FacebookException;

    /**
     * Returns a particular place.
     * @param placeId specify the ID of the place
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return locations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/#searching">Graph API#searching - Facebook Developers</a>
     */
    ResponseList<Location> searchLocations(String placeId, Reading reading) throws FacebookException;

    /**
     * Searches pages.
     * @param query the search condition
     * @return pages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/search/">Search - Facebook Developers</a>
     * @since Facebook4J 2.0.0
     */
    ResponseList<Page> searchPages(String query) throws FacebookException;

    /**
     * Searches pages.
     * @param query the search condition
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return pages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/search/">Search - Facebook Developers</a>
     * @since Facebook4J 2.0.0
     */
    ResponseList<Page> searchPages(String query, Reading reading) throws FacebookException;

    /**
     * Searches every type all public objects.
     * @param query the search condition
     * @return objects
     * @throws FacebookException when Facebook service or network is unavailable
     */
    ResponseList<JSONObject> search(String query) throws FacebookException;

    /**
     * Searches every type all public objects.
     * @param query the search condition
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return objects
     * @throws FacebookException when Facebook service or network is unavailable
     */
    ResponseList<JSONObject> search(String query, Reading reading) throws FacebookException;
    
    /**
     * Searches all public objects of given type.
     * @param query the search condition
     * @param type search type
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return objects
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/using-graph-api#search">Using the Graph API - Searching</a>
     */
    ResponseList<JSONObject> search(String query, String type, Reading reading) throws FacebookException;


}
