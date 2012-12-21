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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class SearchMethodsTest extends FacebookTestBase {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchPosts() throws Exception {
        ResponseList<Post> posts = facebook1.searchPosts("watermelon"/*, new Reading().fields("name")*/);
        for (Post post : posts) {
            System.out.println(post);
        }
        posts = facebook1.searchPosts("テスト");
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    public void searchUsers() throws Exception {
        //TODO
        ResponseList<User> users = facebook1.searchUsers("roundrop"/*, new Reading().fields("name", "gender")*/);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    @Test
    public void searchEvents() throws Exception {
        ResponseList<Event> events = facebook1.searchEvents("conference");
        for (Event event : events) {
            System.out.println(event);
        }
        events = facebook1.searchEvents("セミナー");
        for (Event event : events) {
            System.out.println(event);
        }
    }
    
    @Test
    public void searchGroups() throws Exception {
        ResponseList<Group> groups = facebook1.searchGroups("programming");
        for (Group group : groups) {
            System.out.println(group);
        }
    }
    
    @Test
    public void searchPlaces() throws Exception {
        ResponseList<Place> places = facebook1.searchPlaces("coffee");
        for (Place place : places) {
            System.out.println(place);
        }
    }
    
    @Test
    public void searchPlacesWithGeoLocation() throws Exception {
        GeoLocation center = new GeoLocation(37.76, -122.427);
        int distance = 1000;
        ResponseList<Place> places = facebook1.searchPlaces("coffee", center, distance);
        for (Place place : places) {
            System.out.println(place);
        }
    }
    
    @Test
    public void searchCheckins() throws Exception {
        ResponseList<Checkin> checkins = facebook1.searchCheckins();
        for (Checkin checkin : checkins) {
            System.out.println(checkin);
        }
    }
    
    @Test
    public void searchLocationsByGeo() throws Exception {
        GeoLocation center = new GeoLocation(35.661725, 139.707105);
        int distance = 1000;
        ResponseList<Location> locations = facebookBestFriend1.searchLocations(center, distance);
        for (Location location : locations) {
            System.out.println(location);
        }
    }
    
    @Test
    public void searchLocationsByPlace() throws Exception {
        String placeId = "116972348358579";
        ResponseList<Location> locations = facebookBestFriend1.searchLocations(placeId);
        for (Location location : locations) {
            System.out.println(location);
        }
    }
    
    @Test
    public void search() throws Exception {
        ResponseList<JSONObject> results = facebook1.search("orange");
        System.out.println(results);
        for (JSONObject result : results) {
            System.out.println(result);
        }
        
        results = facebook1.search("orange", new Reading().until("yesterday"));
        System.out.println(results);
        for (JSONObject result : results) {
            System.out.println(result);
        }
    }

}
