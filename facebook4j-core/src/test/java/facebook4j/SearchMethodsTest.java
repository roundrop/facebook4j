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

import facebook4j.internal.http.RequestMethod;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.junit.FacebookAPIVersion;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.TimeZone;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class SearchMethodsTest {

    public static class searchPosts extends MockFacebookTestBase {
        @Test
        public void query() throws Exception {
            facebook.setMockJSON("mock_json/search/posts.json");
            ResponseList<Post> actuals = facebook.searchPosts("watermelon");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "watermelon"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "post"));

            assertThat(actuals.size(), is(25));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is("1 spot left for the watermelon mini sessions tues coming aug 27th"));
            assertThat(actual1.getId(), is("110821725680303_483362711759534"));
            assertThat(actual1.getApplication().getId(), is("2915120374"));
            assertThat(actual1.getApplication().getName(), is("Mobile"));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual1.getFrom().getId(), is("110821725680303"));
            assertThat(actual1.getFrom().getCategory(), is("Local business"));
            assertThat(actual1.getFrom().getName(), is("Stacy Crane Photography"));
            assertThat(actual1.getType(), is("status"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-24T12:24:07+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-24T12:24:07+0000")));
            assertThat(actual1.getActions().get(0).getName(), is("Comment"));
            assertThat(actual1.getActions().get(0).getLink().toString(), is("https://www.facebook.com/110821725680303/posts/483362711759534"));
            assertThat(actual1.getActions().get(1).getName(), is("Like"));
            assertThat(actual1.getActions().get(1).getLink().toString(), is("https://www.facebook.com/110821725680303/posts/483362711759534"));
            Post actual24 = actuals.get(23);
            Place.Location location = actual24.getPlace().getLocation();
            assertThat(location.getText(), is("R\u1eebng Tr\u00e0m Tr\u00e0 S\u01b0"));
            assertThat(location.getStreet(), is(nullValue()));
            assertThat(location.getCity(), is(nullValue()));
            assertThat(location.getState(), is(nullValue()));
            assertThat(location.getCountry(), is(nullValue()));
            assertThat(location.getZip(), is(nullValue()));
            assertThat(location.getLatitude(), is(nullValue()));
            assertThat(location.getLongitude(), is(nullValue()));
            Post actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("633068986_10151834080613987"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/search/posts_id.json");
            ResponseList<Post> actuals = facebook.searchPosts("watermelon", new Reading().fields("id").limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "watermelon"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "post"));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "id"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("146033799093_10151642861969094"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-24T12:40:01+0000")));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getActions().size(), is(0));
            Post actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("293688877420119_397767977012208"));
            assertThat(actual5.getCreatedTime(), is(iso8601DateOf("2013-08-24T12:33:12+0000")));
        }
    }

    public static class searchUsers extends MockFacebookTestBase {
        @Test
        public void query() throws Exception {
            facebook.setMockJSON("mock_json/search/users.json");
            ResponseList<User> actuals = facebook.searchUsers("mark");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "mark"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "user"));

            assertThat(actuals.size(), is(25));
            User actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("637793028"));
            assertThat(actual1.getName(), is("Marketto Mark"));
            User actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("100004948595038"));
            assertThat(actual25.getName(), is("Muhammed Akan"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/search/users_limit5.json");
            ResponseList<User> actuals = facebook.searchUsers("mark", new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "mark"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "user"));

            assertThat(actuals.size(), is(5));
            User actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("637793028"));
            assertThat(actual1.getName(), is("Marketto Mark"));
            User actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("100002324931390"));
            assertThat(actual5.getName(), is("Mark Cefalo"));
        }
    }

    public static class searchEvents extends MockFacebookTestBase {
        @Test
        public void query() throws Exception {
            facebook.setMockJSON("mock_json/search/events.json");
            ResponseList<Event> actuals = facebook.searchEvents("conference");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "conference"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "event"));

            assertThat(actuals.size(), is(25));
            Event actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("139020352947962"));
            assertThat(actual1.getTimezone(), is(TimeZone.getTimeZone("Asia/Karachi")));
            assertThat(actual1.getLocation(), is("Marriott Hotel Karachi"));
            assertThat(actual1.getEndTime(), is(iso8601DateOf("2013-10-07T16:00:00+0500")));
            assertThat(actual1.getName(), is("Conference on Framework of Respectful Workplace \u2013     \u201cMerit Culture\u201d"));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-10-07T08:00:00+0500")));
            Event actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("167779470050701"));
            assertThat(actual25.getTimezone(), is(TimeZone.getTimeZone("Africa/Kampala")));
            assertThat(actual25.getLocation(), is("HOLIDAY EXPRESS HOTEL on Luwum Street,near Nakasero Market,Kampala City Centre"));
            assertThat(actual25.getEndTime(), is(iso8601DateOf("2013-10-08T20:30:00+0300")));
            assertThat(actual25.getName(), is("INTERNATIONAL GREATER HEIGHTS CONFERENCE   2013"));
            assertThat(actual25.getStartTime(), is(iso8601DateOf("2013-08-08T17:30:00+0300")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/search/events_start_time.json");
            ResponseList<Event> actuals = facebook.searchEvents("conference", new Reading().fields("start_time").limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "conference"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "event"));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "start_time"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));
            Event actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("162050023957963"));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-08-29T10:00:00+0530")));
            assertThat(actual1.getName(), is(nullValue()));
            Event actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("597121436988482"));
            assertThat(actual3.getStartTime(), is(iso8601DateOf("2013-08-21T19:00:00+0200")));
        }
    }

    public static class searchGroups extends MockFacebookTestBase {
        @Test
        public void query() throws Exception {
            facebook.setMockJSON("mock_json/search/groups.json");
            ResponseList<Group> actuals = facebook.searchGroups("programming");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "programming"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "group"));

            assertThat(actuals.size(), is(25));
            Group actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("305284592823142"));
            assertThat(actual1.getName(), is("Programming pipz."));
            Group actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("444330665619454"));
            assertThat(actual25.getName(), is("MCohort 9 Hybrid Doctoral in Education Program @Lamar University"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/search/groups_limit5.json");
            ResponseList<Group> actuals = facebook.searchGroups("programming", new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "programming"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "group"));

            assertThat(actuals.size(), is(5));
            Group actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("305284592823142"));
            assertThat(actual1.getName(), is("Programming pipz."));
            Group actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("200690936636126"));
            assertThat(actual5.getName(), is("Passive Income Programs"));        }
    }

    public static class searchPlaces extends MockFacebookTestBase {
        @Test
        public void query() throws Exception {
            facebook.setMockJSON("mock_json/search/places.json");
            ResponseList<Place> actuals = facebook.searchPlaces("coffee");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "coffee"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "place"));

            assertThat(actuals.size(), is(24));
            Place actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("151441331571686"));
            assertThat(actual1.getLocation().getZip(), is(""));
            assertThat(actual1.getLocation().getStreet(), is(""));
            assertThat(actual1.getLocation().getLongitude(), is(-4.429223900414));
            assertThat(actual1.getLocation().getLatitude(), is(36.718526684607));
            assertThat(actual1.getName(), is("Dunkin' Coffee"));
            Place actual24 = actuals.get(23);
            assertThat(actual24.getId(), is("285561108121509"));
            assertThat(actual24.getLocation().getZip(), is("11147"));
            assertThat(actual24.getLocation().getStreet(), is("Syrou 7"));
            assertThat(actual24.getLocation().getState(), is(""));
            assertThat(actual24.getLocation().getLongitude(), is(23.753690002078));
            assertThat(actual24.getLocation().getLatitude(), is(38.012859995462));
            assertThat(actual24.getLocation().getCountry(), is("Greece"));
            assertThat(actual24.getLocation().getCity(), is("Galátsi"));
            assertThat(actual24.getName(), is("Molliz coffee drinks & food"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/search/places_limit5.json");
            ResponseList<Place> actuals = facebook.searchPlaces("coffee", new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "coffee"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "place"));

            assertThat(actuals.size(), is(5));
            Place actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("151441331571686"));
            assertThat(actual1.getLocation().getZip(), is(""));
            assertThat(actual1.getLocation().getStreet(), is(""));
            assertThat(actual1.getLocation().getLongitude(), is(-4.429223900414));
            assertThat(actual1.getLocation().getLatitude(), is(36.718526684607));
            assertThat(actual1.getName(), is("Dunkin' Coffee"));
            Place actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("56120464632"));
            assertThat(actual5.getLocation().getZip(), is("05676"));
            assertThat(actual5.getLocation().getStreet(), is("33 Coffee Lane"));
            assertThat(actual5.getLocation().getState(), is("VT"));
            assertThat(actual5.getLocation().getLongitude(), is(-72.73444));
            assertThat(actual5.getLocation().getLatitude(), is(44.34108));
            assertThat(actual5.getLocation().getCountry(), is("United States"));
            assertThat(actual5.getLocation().getCity(), is("Waterbury"));
            assertThat(actual5.getName(), is("Green Mountain Coffee"));
        }

        @Test
        public void geoLocation() throws Exception {
            facebook.setMockJSON("mock_json/search/places_geo.json");
            GeoLocation geoLocation = new GeoLocation(37.76, -122.427);
            int distance = 1000;
            ResponseList<Place> actuals = facebook.searchPlaces("coffee", geoLocation, distance);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "coffee"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "place"));
            assertThat(facebook.getEndpointURL(), hasParameter("center", "37.76,-122.427"));
            assertThat(facebook.getEndpointURL(), hasParameter("distance", "1000"));

            assertThat(actuals.size(), is(25));
            Place actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("151116474914629"));
            assertThat(actual1.getLocation().getZip(), is("94114-2501"));
            assertThat(actual1.getLocation().getStreet(), is("4023 18th St"));
            assertThat(actual1.getLocation().getState(), is("CA"));
            assertThat(actual1.getLocation().getLongitude(), is(-122.43336658638));
            assertThat(actual1.getLocation().getLatitude(), is(37.761003830281));
            assertThat(actual1.getLocation().getCountry(), is("United States"));
            assertThat(actual1.getLocation().getCity(), is("San Francisco"));
            assertThat(actual1.getName(), is("Philz Coffee - Castro"));
            Place actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("63018133564"));
            assertThat(actual25.getLocation().getZip(), is("94110"));
            assertThat(actual25.getLocation().getStreet(), is("3614 18th St."));
            assertThat(actual25.getLocation().getState(), is("CA"));
            assertThat(actual25.getLocation().getLongitude(), is(-122.42417675138));
            assertThat(actual25.getLocation().getLatitude(), is(37.761561383753));
            assertThat(actual25.getLocation().getCountry(), is("United States"));
            assertThat(actual25.getLocation().getCity(), is("San Francisco"));
            assertThat(actual25.getName(), is("Fayes Video & Espresso Bar"));
        }

        @Test
        public void geoLocation_reading() throws Exception {
            facebook.setMockJSON("mock_json/search/places_geo_5.json");
            GeoLocation geoLocation = new GeoLocation(37.76, -122.427);
            int distance = 1000;
            ResponseList<Place> actuals = facebook.searchPlaces("coffee", geoLocation, distance, new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "coffee"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "place"));
            assertThat(facebook.getEndpointURL(), hasParameter("center", "37.76,-122.427"));
            assertThat(facebook.getEndpointURL(), hasParameter("distance", "1000"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Place actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("151116474914629"));
            assertThat(actual1.getLocation().getZip(), is("94114-2501"));
            assertThat(actual1.getLocation().getStreet(), is("4023 18th St"));
            assertThat(actual1.getLocation().getState(), is("CA"));
            assertThat(actual1.getLocation().getLongitude(), is(-122.43336658638));
            assertThat(actual1.getLocation().getLatitude(), is(37.761003830281));
            assertThat(actual1.getLocation().getCountry(), is("United States"));
            assertThat(actual1.getLocation().getCity(), is("San Francisco"));
            assertThat(actual1.getName(), is("Philz Coffee - Castro"));
            Place actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("195498370504878"));
            assertThat(actual5.getLocation().getZip(), is("94114"));
            assertThat(actual5.getLocation().getStreet(), is(""));
            assertThat(actual5.getLocation().getState(), is("CA"));
            assertThat(actual5.getLocation().getLongitude(), is(-122.43213544929));
            assertThat(actual5.getLocation().getLatitude(), is(37.76487069397));
            assertThat(actual5.getLocation().getCountry(), is("United States"));
            assertThat(actual5.getLocation().getCity(), is("San Francisco"));
            assertThat(actual5.getName(), is("Peets Coffee, Castro Dist."));
        }

        @Test
        public void categories() throws Exception {
            facebook.setMockJSON("mock_json/search/places_with_categories.json");
            GeoLocation geoLocation = new GeoLocation(37.7793, -122.419);
            int distance = 1000;
            ResponseList<Place> actuals = facebook.searchPlaces("San Francisco, California", geoLocation, distance);

            Place place1 = actuals.get(0);
            assertThat(place1.getCategories().size(), is(1));
            assertThat(place1.getCategories().get(0).getId(), is("224455390913969"));
            assertThat(place1.getCategories().get(0).getName(), is("City"));
            Place place3 = actuals.get(2);
            assertThat(place3.getCategories().size(), is(3));
            assertThat(place3.getCategories().get(0).getId(), is("115725465228008"));
            assertThat(place3.getCategories().get(0).getName(), is("Region"));
            assertThat(place3.getCategories().get(1).getId(), is("407338945943828"));
            assertThat(place3.getCategories().get(1).getName(), is("River"));
            assertThat(place3.getCategories().get(2).getId(), is("215492291888288"));
            assertThat(place3.getCategories().get(2).getName(), is("Ocean"));
        }
    }

    public static class searchCheckins extends MockFacebookTestBase {
        @Test
        public void checkins() throws Exception {
            facebook.setMockJSON("mock_json/search/checkins.json");
            ResponseList<Checkin> actuals = facebook.searchCheckins();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "checkin"));

            assertThat(actuals.size(), is(2));
            Checkin actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is("ana"));
            assertThat(actual1.getId(), is("400000000000001"));
            assertThat(actual1.getApplication().getId(), is("350685531728"));
            assertThat(actual1.getApplication().getName(), is("Facebook for Android"));
            assertThat(actual1.getApplication().getNamespace(), is("fbandroid"));
            assertThat(actual1.getLikes().get(0).getId(), is("200000000000001"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("200000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("200000000000003"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAzMTA0MjA0NjAy"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAxMjcwMjU3MDU5"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getPlace().getId(), is("174479159241890"));
            assertThat(actual1.getPlace().getLocation().getZip(), is(""));
            assertThat(actual1.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual1.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(139.78833242848));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(35.550898482637));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Ota-ku"));
            assertThat(actual1.getPlace().getName(), is("ANA Lounge"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-20T10:30:24+0000")));
            Checkin actual2 = actuals.get(1);
            assertThat(actual2.getTags().get(0).getId(), is("300000000000002"));
            assertThat(actual2.getTags().get(0).getName(), is("Like Name22"));
            assertThat(actual2.getTags().get(1).getId(), is("300000000000001"));
            assertThat(actual2.getTags().get(1).getName(), is("Like Name21"));
            assertThat(actual2.getTags().get(2).getId(), is("300000000000003"));
            assertThat(actual2.getTags().get(2).getName(), is("Like Name23"));
            assertThat(actual2.getTags().getPaging().getNext().toString(), is("https://graph.facebook.com/400000000000002/tags?access_token=access_token&limit=25&offset=25&__after_id=564933715"));
            assertThat(actual2.getId(), is("400000000000002"));
            assertThat(actual2.getApplication().getId(), is("6628568379"));
            assertThat(actual2.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual2.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual2.getLikes().get(0).getId(), is("300000000000001"));
            assertThat(actual2.getLikes().get(0).getName(), is("Like Name21"));
            assertThat(actual2.getLikes().get(1).getId(), is("300000000000002"));
            assertThat(actual2.getLikes().get(1).getName(), is("Like Name22"));
            assertThat(actual2.getLikes().get(2).getId(), is("300000000000003"));
            assertThat(actual2.getLikes().get(2).getName(), is("Like Name23"));
            assertThat(actual2.getLikes().get(3).getId(), is("300000000000004"));
            assertThat(actual2.getLikes().get(3).getName(), is("Like Name24"));
            assertThat(actual2.getLikes().get(4).getId(), is("300000000000005"));
            assertThat(actual2.getLikes().get(4).getName(), is("Like Name25"));
            assertThat(actual2.getLikes().get(5).getId(), is("300000000000006"));
            assertThat(actual2.getLikes().get(5).getName(), is("Like Name26"));
            assertThat(actual2.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwNjQzNzgxODQy"));
            assertThat(actual2.getLikes().getPaging().getCursors().getBefore(), is("NjQ5MTQzNTAw"));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("From Name2"));
            assertThat(actual2.getPlace().getId(), is("145968818775790"));
            assertThat(actual2.getPlace().getLocation().getZip(), is(""));
            assertThat(actual2.getPlace().getLocation().getStreet(), is("Eiheiji-cho"));
            assertThat(actual2.getPlace().getLocation().getState(), is("Fukui"));
            assertThat(actual2.getPlace().getLocation().getLongitude(), is(136.29786803334));
            assertThat(actual2.getPlace().getLocation().getLatitude(), is(36.089500128549));
            assertThat(actual2.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual2.getPlace().getLocation().getCity(), is("Yoshida-gun"));
            assertThat(actual2.getPlace().getName(), is("Kenzo Sobaya"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-18T04:38:42+0000")));
        }
    }

    public static class searchLocations extends MockFacebookTestBase {
        @Test
        public void geo() throws Exception {
            facebook.setMockJSON("mock_json/search/locations.json");
            GeoLocation geoLocation = new GeoLocation(37.76, -122.427);
            int distance = 1000;
            ResponseList<Location> actuals = facebook.searchLocations(geoLocation, distance);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "location"));
            assertThat(facebook.getEndpointURL(), hasParameter("center", "37.76,-122.427"));
            assertThat(facebook.getEndpointURL(), hasParameter("distance", "1000"));
        }

        @Test
        public void geo_reading() throws Exception {
            facebook.setMockJSON("mock_json/search/locations_5.json");
            GeoLocation geoLocation = new GeoLocation(37.76, -122.427);
            int distance = 1000;
            ResponseList<Location> actuals = facebook.searchLocations(geoLocation, distance, new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "location"));
            assertThat(facebook.getEndpointURL(), hasParameter("center", "37.76,-122.427"));
            assertThat(facebook.getEndpointURL(), hasParameter("distance", "1000"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));
        }

        @Test
        public void placeId() throws Exception {
            facebook.setMockJSON("mock_json/search/locations_place.json");
            ResponseList<Location> actuals = facebook.searchLocations("166793820034304");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "location"));
            assertThat(facebook.getEndpointURL(), hasParameter("place", "166793820034304"));

            assertThat(actuals.size(), is(1));
            Location actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("306562006072081"));
            assertThat(actual1.getApplication().getId(), is("2305272732"));
            assertThat(actual1.getApplication().getName(), is("Photos"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name"));
            assertThat(actual1.getType(), is("photo"));
            assertThat(actual1.getPlace().getId(), is("166793820034304"));
            assertThat(actual1.getPlace().getLocation().getZip(), is("94025"));
            assertThat(actual1.getPlace().getLocation().getStreet(), is("1 Hacker Way"));
            assertThat(actual1.getPlace().getLocation().getState(), is("CA"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(-122.14853582158));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(37.484254836239));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("United States"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Menlo Park"));
            assertThat(actual1.getPlace().getName(), is("Facebook HQ"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-02-26T08:09:56+0000")));
        }
    }

    public static class searchPages extends MockFacebookTestBase {
        @Test
        public void query() throws Exception {
            facebook.setMockJSON("mock_json/search/pages.json");
            ResponseList<Page> actuals = facebook.searchPages("platform");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "platform"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "page"));

            assertThat(actuals.size(), is(25));
            Page actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("152603664817327"));
            assertThat(actual1.getCategory(), is("Software"));
            assertThat(actual1.getName(), is("eXo Platform"));
            Page actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("354725424655619"));
            assertThat(actual25.getCategory(), is("Health/medical/pharmaceuticals"));
            assertThat(actual25.getName(), is("Medi Platform"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/search/pages_5.json");
            ResponseList<Page> actuals = facebook.searchPages("platform", new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "platform"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "page"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Page actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("152603664817327"));
            assertThat(actual1.getCategory(), is("Software"));
            assertThat(actual1.getName(), is("eXo Platform"));
            Page actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("79544810932"));
            assertThat(actual5.getCategory(), is("Music video"));
            assertThat(actual5.getName(), is("VEVO"));
        }
    }

    public static class search extends MockFacebookTestBase {
        @Test
        public void query() throws Exception {
            facebook.setMockJSON("mock_json/search/orange.json");
            ResponseList<JSONObject> actuals = facebook.search("orange");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "orange"));

            assertThat(actuals.size(), is(5));
            assertThat(actuals.get(0).getString("type"), is("status"));
            assertThat(actuals.get(1).getString("type"), is("status"));
            assertThat(actuals.get(2).getString("type"), is("photo"));
            assertThat(actuals.get(3).getString("type"), is("link"));
            assertThat(actuals.get(4).getString("type"), is("link"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/search/orange_type.json");
            ResponseList<JSONObject> actuals = facebook.search("orange", new Reading().fields("type"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "orange"));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "type"));

            assertThat(actuals.size(), is(6));
            assertThat(actuals.get(0).getString("type"), is("link"));
            assertThat(actuals.get(1).getString("type"), is("status"));
            assertThat(actuals.get(2).getString("type"), is("status"));
            assertThat(actuals.get(3).getString("type"), is("status"));
            assertThat(actuals.get(4).getString("type"), is("status"));
            assertThat(actuals.get(5).getString("type"), is("photo"));
        }

        @Test
        @FacebookAPIVersion("v2.6")
        public void type() throws Exception {
            facebook.setMockJSON("mock_json/search/placetopic_all.json");
            ResponseList<JSONObject> actuals = facebook.search("awesome", "placetopic", new Reading().addParameter("topic_filter", "all"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.6/search")));
            assertThat(facebook.getEndpointURL(), hasParameter("q", "awesome"));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "placetopic"));
            assertThat(facebook.getEndpointURL(), hasParameter("topic_filter", "all"));

            assertThat(actuals.size(), is(22));
        }
    }

}
