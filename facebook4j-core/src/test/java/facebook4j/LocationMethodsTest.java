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
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class LocationMethodsTest {

    public static class getLocations extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/location/locations.json");
            ResponseList<Location> actuals = facebook.getLocations();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/locations")));

            assertThat(actuals.size(), is(3));
            Location actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("575740359154920"));
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("From Name"));
            assertThat(actual1.getType(), is("photo"));
            assertThat(actual1.getPlace().getId(), is("262956980385539"));
            assertThat(actual1.getPlace().getLocation().getZip(), is(""));
            assertThat(actual1.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual1.getPlace().getLocation().getState(), is(nullValue()));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(131.88856357));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(34.75762856));
            assertThat(actual1.getPlace().getLocation().getCountry(), is(nullValue()));
            assertThat(actual1.getPlace().getLocation().getCity(), is(nullValue()));
            assertThat(actual1.getPlace().getName(), is("Araisokan"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-04T00:17:53+0000")));
            Location actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("574034109325545"));
            assertThat(actual2.getApplication().getId(), is("6628568379"));
            assertThat(actual2.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual2.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("From Name"));
            assertThat(actual2.getType(), is("checkin"));
            assertThat(actual2.getPlace().getId(), is("167166979985363"));
            assertThat(actual2.getPlace().getLocation().getZip(), is("104-0053"));
            assertThat(actual2.getPlace().getLocation().getStreet(), is("Harumi1-8-16"));
            assertThat(actual2.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual2.getPlace().getLocation().getLongitude(), is(139.78167227116));
            assertThat(actual2.getPlace().getLocation().getLatitude(), is(35.65692512948));
            assertThat(actual2.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual2.getPlace().getLocation().getCity(), is("Chuo-ku"));
            assertThat(actual2.getPlace().getName(), is("Harumi Island Triton Square"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-31T08:37:26+0000")));
            Location actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("565425746853048"));
            assertThat(actual3.getApplication().getId(), is("6628568379"));
            assertThat(actual3.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual3.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("From Name"));
            assertThat(actual3.getType(), is("status"));
            assertThat(actual3.getPlace().getId(), is("172643522795721"));
            assertThat(actual3.getPlace().getLocation().getZip(), is(""));
            assertThat(actual3.getPlace().getLocation().getStreet(), is("Goryokaku-cho44"));
            assertThat(actual3.getPlace().getLocation().getState(), is("Hokkaido"));
            assertThat(actual3.getPlace().getLocation().getLongitude(), is(140.75461653069));
            assertThat(actual3.getPlace().getLocation().getLatitude(), is(41.795708973186));
            assertThat(actual3.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual3.getPlace().getLocation().getCity(), is("Hakodate-shi"));
            assertThat(actual3.getPlace().getName(), is("Goryokaku Park"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2013-07-14T02:58:34+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/location/tags.json");
            ResponseList<Location> actuals = facebook.getLocations(new Reading().fields("tags"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/locations")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "tags"));

            assertThat(actuals.size(), is(6));
            Location actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-14T02:58:34+0000")));
            Location actual2 = actuals.get(1);
            assertThat(actual2.getTags().get(0).getId(), is("123456790123456"));
            assertThat(actual2.getTags().get(0).getName(), is("Name Name1"));
            assertThat(actual2.getTags().get(1).getId(), is("100000000000001"));
            assertThat(actual2.getTags().get(1).getName(), is("Name Name2"));
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-13T11:58:23+0000")));
            Location actual4 = actuals.get(3);
            assertThat(actual4.getTags().get(0).getId(), is("100000000000002"));
            assertThat(actual4.getTags().get(0).getName(), is("Name Name3"));
            assertThat(actual4.getTags().get(1).getId(), is("123456790123456"));
            assertThat(actual4.getTags().get(1).getName(), is("Name Name1"));
            assertThat(actual4.getTags().get(2).getId(), is("100000000000003"));
            assertThat(actual4.getTags().get(2).getName(), is("Name Name4"));
            assertThat(actual4.getId(), is("500000000000004"));
            assertThat(actual4.getCreatedTime(), is(iso8601DateOf("2013-07-13T07:10:23+0000")));
            Location actual6 = actuals.get(5);
            assertThat(actual6.getTags().get(0).getId(), is("100000000000004"));
            assertThat(actual6.getTags().get(0).getName(), is("Name Name5"));
            assertThat(actual6.getId(), is("500000000000006"));
            assertThat(actual6.getCreatedTime(), is(iso8601DateOf("2013-03-19T09:49:04+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/location/locations.json");
            ResponseList<Location> actuals = facebook.getLocations("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/locations")));

            assertThat(actuals.size(), is(3));
            Location actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("575740359154920"));
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("From Name"));
            assertThat(actual1.getType(), is("photo"));
            assertThat(actual1.getPlace().getId(), is("262956980385539"));
            assertThat(actual1.getPlace().getLocation().getZip(), is(""));
            assertThat(actual1.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual1.getPlace().getLocation().getState(), is(nullValue()));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(131.88856357));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(34.75762856));
            assertThat(actual1.getPlace().getLocation().getCountry(), is(nullValue()));
            assertThat(actual1.getPlace().getLocation().getCity(), is(nullValue()));
            assertThat(actual1.getPlace().getName(), is("Araisokan"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-04T00:17:53+0000")));
            Location actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("574034109325545"));
            assertThat(actual2.getApplication().getId(), is("6628568379"));
            assertThat(actual2.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual2.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("From Name"));
            assertThat(actual2.getType(), is("checkin"));
            assertThat(actual2.getPlace().getId(), is("167166979985363"));
            assertThat(actual2.getPlace().getLocation().getZip(), is("104-0053"));
            assertThat(actual2.getPlace().getLocation().getStreet(), is("Harumi1-8-16"));
            assertThat(actual2.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual2.getPlace().getLocation().getLongitude(), is(139.78167227116));
            assertThat(actual2.getPlace().getLocation().getLatitude(), is(35.65692512948));
            assertThat(actual2.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual2.getPlace().getLocation().getCity(), is("Chuo-ku"));
            assertThat(actual2.getPlace().getName(), is("Harumi Island Triton Square"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-31T08:37:26+0000")));
            Location actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("565425746853048"));
            assertThat(actual3.getApplication().getId(), is("6628568379"));
            assertThat(actual3.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual3.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("From Name"));
            assertThat(actual3.getType(), is("status"));
            assertThat(actual3.getPlace().getId(), is("172643522795721"));
            assertThat(actual3.getPlace().getLocation().getZip(), is(""));
            assertThat(actual3.getPlace().getLocation().getStreet(), is("Goryokaku-cho44"));
            assertThat(actual3.getPlace().getLocation().getState(), is("Hokkaido"));
            assertThat(actual3.getPlace().getLocation().getLongitude(), is(140.75461653069));
            assertThat(actual3.getPlace().getLocation().getLatitude(), is(41.795708973186));
            assertThat(actual3.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual3.getPlace().getLocation().getCity(), is("Hakodate-shi"));
            assertThat(actual3.getPlace().getName(), is("Goryokaku Park"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2013-07-14T02:58:34+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/location/tags.json");
            ResponseList<Location> actuals = facebook.getLocations("1234567890123456", new Reading().fields("tags"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/locations")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "tags"));

            assertThat(actuals.size(), is(6));
            Location actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-14T02:58:34+0000")));
            Location actual2 = actuals.get(1);
            assertThat(actual2.getTags().get(0).getId(), is("123456790123456"));
            assertThat(actual2.getTags().get(0).getName(), is("Name Name1"));
            assertThat(actual2.getTags().get(1).getId(), is("100000000000001"));
            assertThat(actual2.getTags().get(1).getName(), is("Name Name2"));
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-13T11:58:23+0000")));
            Location actual4 = actuals.get(3);
            assertThat(actual4.getTags().get(0).getId(), is("100000000000002"));
            assertThat(actual4.getTags().get(0).getName(), is("Name Name3"));
            assertThat(actual4.getTags().get(1).getId(), is("123456790123456"));
            assertThat(actual4.getTags().get(1).getName(), is("Name Name1"));
            assertThat(actual4.getTags().get(2).getId(), is("100000000000003"));
            assertThat(actual4.getTags().get(2).getName(), is("Name Name4"));
            assertThat(actual4.getId(), is("500000000000004"));
            assertThat(actual4.getCreatedTime(), is(iso8601DateOf("2013-07-13T07:10:23+0000")));
            Location actual6 = actuals.get(5);
            assertThat(actual6.getTags().get(0).getId(), is("100000000000004"));
            assertThat(actual6.getTags().get(0).getName(), is("Name Name5"));
            assertThat(actual6.getId(), is("500000000000006"));
            assertThat(actual6.getCreatedTime(), is(iso8601DateOf("2013-03-19T09:49:04+0000")));
        }
    }
    
    public static class getPlaceTags extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
             facebook.setMockJSON("mock_json/location/place_tags.json");
             ResponseList<PlaceTag> actuals = facebook.getTaggedPlaces();
             assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
             assertThat(facebook.getEndpointURL(), is(pathOf("/me/tagged_places")));
             
             assertThat(actuals.size(), is(2));
             PlaceTag actual1 = actuals.get(0);
             assertThat(actual1.getId(), is("10100130254301913"));
             assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2010-02-18T04:51:23+0000")));
             assertThat(actual1.getPlaceTag().getId(), is("112197455462418"));
             assertThat(actual1.getPlaceTag().getName(), is("Rockville, Maryland"));
             assertThat(actual1.getPlaceTag().getLocation().getLatitude(), is(39.0839));
             assertThat(actual1.getPlaceTag().getLocation().getLongitude(), is(-77.1531));
             assertThat(actual1.getPlaceTag().getLocation().getZip(), is(nullValue()));
             assertThat(actual1.getPlaceTag().getLocation().getStreet(), is(nullValue()));
             assertThat(actual1.getPlaceTag().getLocation().getState(), is(nullValue()));
             assertThat(actual1.getPlaceTag().getLocation().getCountry(), is(nullValue()));
             assertThat(actual1.getPlaceTag().getLocation().getCity(), is(nullValue()));
        }
        
        @Test
        public void id() throws Exception {
             facebook.setMockJSON("mock_json/location/place_tags.json");
             ResponseList<PlaceTag> actuals = facebook.getTaggedPlaces("1234124");
             assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
             assertThat(facebook.getEndpointURL(), is(pathOf("/1234124/tagged_places")));
             
             assertThat(actuals.size(), is(2));
             
        }
        
        @Test
        public void reading() throws Exception {
             facebook.setMockJSON("mock_json/location/place_tags.json");
             ResponseList<PlaceTag> actuals = facebook.getTaggedPlaces(new Reading().fields("id"));
             assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
             assertThat(facebook.getEndpointURL(), is(pathOf("/me/tagged_places")));
             assertThat(facebook.getEndpointURL(), hasParameter("fields", "id"));
             
             assertThat(actuals.size(), is(2));
        }
        
        @Test
        public void id_reading() throws Exception {
             facebook.setMockJSON("mock_json/location/place_tags.json");
             ResponseList<PlaceTag> actuals = facebook.getTaggedPlaces("1234", new Reading().fields("id"));
             assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
             assertThat(facebook.getEndpointURL(), is(pathOf("/1234/tagged_places")));
             assertThat(facebook.getEndpointURL(), hasParameter("fields", "id"));
             
             assertThat(actuals.size(), is(2));
        }
    }
    
    

}
