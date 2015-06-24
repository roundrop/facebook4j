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

import java.net.URL;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class CheckinMethodsTest {

    public static class getCheckins extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/checkin/checkins.json");
            ResponseList<Checkin> actuals = facebook.getCheckins();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/checkins")));

            assertThat(actuals.size(), is(3));
            Checkin actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is("message1..."));
            assertThat(actual1.getId(), is("222222222222222"));
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getLikes().get(0).getId(), is("311111111111111"));
            assertThat(actual1.getLikes().get(0).getName(), is("Name Name3"));
            assertThat(actual1.getLikes().get(1).getId(), is("322222222222222"));
            assertThat(actual1.getLikes().get(1).getName(), is("Name Name4"));
            assertThat(actual1.getLikes().get(2).getId(), is("333333333333333"));
            assertThat(actual1.getLikes().get(2).getName(), is("Name Name5"));
            assertThat(actual1.getLikes().get(3).getId(), is("344444444444444"));
            assertThat(actual1.getLikes().get(3).getName(), is("Name Name6"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAxNTQ4NTEzNjU4"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyNTU4NDQ3Nzg5"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getPlace().getId(), is("154470644580235"));
            assertThat(actual1.getPlace().getLocation().getZip(), is("100-6690"));
            assertThat(actual1.getPlace().getLocation().getStreet(), is("Marunouchi1-9-2"));
            assertThat(actual1.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(139.76740305911));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(35.681076836862));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Chiyoda-ku"));
            assertThat(actual1.getPlace().getName(), is("Gran Tokyo South Tower"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-02-14T10:27:03+0000")));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(0).getMessage(), is("comment1..."));
            assertThat(actual1.getComments().get(0).getId(), is("2345678901234567_1111111"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getComments().get(0).canRemove(), is(true));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2012-02-14T12:19:52+0000")));
            assertThat(actual1.getComments().get(1).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(1).getMessage(), is("comment2..."));
            assertThat(actual1.getComments().get(1).getId(), is("2345678901234567_2222222"));
            assertThat(actual1.getComments().get(1).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("3456789012345678"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("Name Name2"));
            assertThat(actual1.getComments().get(1).canRemove(), is(true));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2012-02-14T14:04:40+0000")));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("Mg=="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Checkin actual2 = actuals.get(1);
            assertThat(actual2.getMessage(), is("message2..."));
            assertThat(actual2.getId(), is("444444444444444"));
            assertThat(actual2.getApplication().getId(), is("6628568379"));
            assertThat(actual2.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual2.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual2.getLikes().get(0).getId(), is("5111111111111111"));
            assertThat(actual2.getLikes().get(0).getName(), is("Name Name7"));
            assertThat(actual2.getLikes().get(1).getId(), is("5222222222222222"));
            assertThat(actual2.getLikes().get(1).getName(), is("Name Name8"));
            assertThat(actual2.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwMzM4NDY4Njg2"));
            assertThat(actual2.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAxMjg4OTA5ODEw"));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("Name Name1"));
            assertThat(actual2.getPlace().getId(), is("532123656820659"));
            assertThat(actual2.getPlace().getLocation().getZip(), is(""));
            assertThat(actual2.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual2.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual2.getPlace().getLocation().getLongitude(), is(139.74310960422));
            assertThat(actual2.getPlace().getLocation().getLatitude(), is(35.644253731576));
            assertThat(actual2.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual2.getPlace().getLocation().getCity(), is("Minato-ku"));
            assertThat(actual2.getPlace().getName(), is("Bellesalle Mita"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-01-27T00:32:09+0000")));
            Checkin actual3 = actuals.get(2);
            assertThat(actual3.getMessage(), is("message3..."));
            assertThat(actual3.getId(), is("666666666666666"));
            assertThat(actual3.getApplication().getId(), is("6628568379"));
            assertThat(actual3.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual3.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("Name Name1"));
            assertThat(actual3.getPlace().getId(), is("178106048903380"));
            assertThat(actual3.getPlace().getLocation().getZip(), is("144-0041"));
            assertThat(actual3.getPlace().getLocation().getStreet(), is("Haneda Airport3-3-2"));
            assertThat(actual3.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual3.getPlace().getLocation().getLongitude(), is(139.77104818379));
            assertThat(actual3.getPlace().getLocation().getLatitude(), is(35.545670908077));
            assertThat(actual3.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual3.getPlace().getLocation().getCity(), is("Ota-ku"));
            assertThat(actual3.getPlace().getName(), is("Haneda Airport"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2011-09-04T05:09:49+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/checkin/checkins_place_limit1.json");
            ResponseList<Checkin> actuals = facebook.getCheckins(new Reading().fields("place").limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/checkins")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "place"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Checkin actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is(nullValue()));
            assertThat(actual1.getId(), is("222222222222222"));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLikes().size(), is(0));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getPlace().getId(), is("154470644580235"));
            assertThat(actual1.getPlace().getLocation().getZip(), is("100-6690"));
            assertThat(actual1.getPlace().getLocation().getStreet(), is("Marunouchi1-9-2"));
            assertThat(actual1.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(139.76740305911));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(35.681076836862));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Chiyoda-ku"));
            assertThat(actual1.getPlace().getName(), is("Gran Tokyo South Tower"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-02-14T10:27:03+0000")));
            assertThat(actual1.getComments().size(), is(0));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/checkin/checkins.json");
            ResponseList<Checkin> actuals = facebook.getCheckins("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/checkins")));

            assertThat(actuals.size(), is(3));
            Checkin actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is("message1..."));
            assertThat(actual1.getId(), is("222222222222222"));
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getLikes().get(0).getId(), is("311111111111111"));
            assertThat(actual1.getLikes().get(0).getName(), is("Name Name3"));
            assertThat(actual1.getLikes().get(1).getId(), is("322222222222222"));
            assertThat(actual1.getLikes().get(1).getName(), is("Name Name4"));
            assertThat(actual1.getLikes().get(2).getId(), is("333333333333333"));
            assertThat(actual1.getLikes().get(2).getName(), is("Name Name5"));
            assertThat(actual1.getLikes().get(3).getId(), is("344444444444444"));
            assertThat(actual1.getLikes().get(3).getName(), is("Name Name6"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAxNTQ4NTEzNjU4"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyNTU4NDQ3Nzg5"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getPlace().getId(), is("154470644580235"));
            assertThat(actual1.getPlace().getLocation().getZip(), is("100-6690"));
            assertThat(actual1.getPlace().getLocation().getStreet(), is("Marunouchi1-9-2"));
            assertThat(actual1.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(139.76740305911));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(35.681076836862));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Chiyoda-ku"));
            assertThat(actual1.getPlace().getName(), is("Gran Tokyo South Tower"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-02-14T10:27:03+0000")));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(0).getMessage(), is("comment1..."));
            assertThat(actual1.getComments().get(0).getId(), is("2345678901234567_1111111"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getComments().get(0).canRemove(), is(true));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2012-02-14T12:19:52+0000")));
            assertThat(actual1.getComments().get(1).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(1).getMessage(), is("comment2..."));
            assertThat(actual1.getComments().get(1).getId(), is("2345678901234567_2222222"));
            assertThat(actual1.getComments().get(1).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("3456789012345678"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("Name Name2"));
            assertThat(actual1.getComments().get(1).canRemove(), is(true));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2012-02-14T14:04:40+0000")));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("Mg=="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/checkin/checkins_place_limit1.json");
            ResponseList<Checkin> actuals = facebook.getCheckins("1234567890123456", new Reading().fields("place").limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/checkins")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "place"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Checkin actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is(nullValue()));
            assertThat(actual1.getId(), is("222222222222222"));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLikes().size(), is(0));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getPlace().getId(), is("154470644580235"));
            assertThat(actual1.getPlace().getLocation().getZip(), is("100-6690"));
            assertThat(actual1.getPlace().getLocation().getStreet(), is("Marunouchi1-9-2"));
            assertThat(actual1.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(139.76740305911));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(35.681076836862));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Chiyoda-ku"));
            assertThat(actual1.getPlace().getName(), is("Gran Tokyo South Tower"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-02-14T10:27:03+0000")));
            assertThat(actual1.getComments().size(), is(0));
        }

        @Test
        public void page() throws Exception {
            facebook.setMockJSON("mock_json/checkin/page.json");
            ResponseList<Checkin> actuals = facebook.getCheckins("149655571740038");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/149655571740038/checkins")));

            assertThat(actuals.size(), is(2));

            Checkin actual1 = actuals.get(0);
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getFrom().getId(), is("1111"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getId(), is("2222"));
            assertThat(actual1.getLikes().size(), is(3));
            assertThat(actual1.getLikes().get(0).getId(), is("3331"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("3332"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("3333"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name3"));
            assertThat(actual1.getMessage(), is("Message"));
            assertThat(actual1.getPlace().getId(), is("149655571740038"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Sumida-ku"));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(35.710235962898));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(139.81112668973));
            assertThat(actual1.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual1.getPlace().getLocation().getStreet(), is("押上1-1-13"));
            assertThat(actual1.getPlace().getLocation().getZip(), is("131-0045"));
            assertThat(actual1.getPlace().getName(), is("東京スカイツリー (Tokyo Sky Tree)"));

            assertThat(actuals.get(1).getId(), is("5555"));

            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/149655571740038/checkins?access_token=access_token&limit=25&until=1111111111&__paging_token=179367212156708"));
            assertThat(actuals.getPaging().getPrevious().toString(), is("https://graph.facebook.com/149655571740038/checkins?access_token=access_token&limit=25&since=2222222222&__paging_token=395794067108539&__previous=1"));
        }
    }

    public static class getCheckin extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/checkin/airport.json");
            Checkin actual = facebook.getCheckin("200000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/200000000000001")));

            assertThat(actual.getMessage(), is("Haneda"));
            assertThat(actual.getId(), is("200000000000001"));
            assertThat(actual.getApplication().getId(), is("6628568379"));
            assertThat(actual.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual.getFrom().getId(), is("100001568838021"));
            assertThat(actual.getFrom().getName(), is("Ryuji Yamashita"));
            assertThat(actual.getPlace().getId(), is("178106048903380"));
            assertThat(actual.getPlace().getLocation().getZip(), is("144-0041"));
            assertThat(actual.getPlace().getLocation().getStreet(), is("羽田空港3-3-2"));
            assertThat(actual.getPlace().getLocation().getState(), is("Tokyo"));
            assertThat(actual.getPlace().getLocation().getLongitude(), is(139.77104818379));
            assertThat(actual.getPlace().getLocation().getLatitude(), is(35.545670908077));
            assertThat(actual.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual.getPlace().getLocation().getCity(), is("Ota-ku"));
            assertThat(actual.getPlace().getName(), is("Haneda Airport"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2011-09-04T05:09:49+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/checkin/coordinates.json");
            Checkin actual = facebook.getCheckin("200000000000001", new Reading().fields("coordinates"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/200000000000001")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "coordinates"));

            assertThat(actual.getMessage(), is(nullValue()));
            assertThat(actual.getId(), is("200000000000001"));
            assertThat(actual.getApplication(), is(nullValue()));
            assertThat(actual.getFrom(), is(nullValue()));
            assertThat(actual.getPlace(), is(nullValue()));
            assertThat(actual.getCoordinates().getLongitude(), is(139.77104818379));
            assertThat(actual.getCoordinates().getLatitude(), is(35.545670908077));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2011-09-04T05:09:49+0000")));
        }
    }

    public static class getCheckinComments extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/checkin/comments.json");
            ResponseList<Comment> actuals = facebook.getCheckinComments("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actuals.size(), is(2));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.isUserLikes(), is(false));
            assertThat(actual1.getMessage(), is("message1"));
            assertThat(actual1.getId(), is("500000000000001_4726985"));
            assertThat(actual1.getLikeCount(), is(0));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name  Name1"));
            assertThat(actual1.canRemove(), is(true));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-03-19T09:55:03+0000")));
            Comment actual2 = actuals.get(1);
            assertThat(actual2.isUserLikes(), is(false));
            assertThat(actual2.getMessage(), is("message2"));
            assertThat(actual2.getId(), is("500000000000001_4727422"));
            assertThat(actual2.getLikeCount(), is(0));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("Name Name2"));
            assertThat(actual2.canRemove(), is(true));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-03-19T13:03:26+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/checkin/comments_from.json");
            ResponseList<Comment> actuals = facebook.getCheckinComments("500000000000001", new Reading().fields("from"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "from"));

            assertThat(actuals.size(), is(2));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.isUserLikes(), is(nullValue()));
            assertThat(actual1.getMessage(), is(nullValue()));
            assertThat(actual1.getId(), is("500000000000001_4726985"));
            assertThat(actual1.getLikeCount(), is(nullValue()));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name  Name1"));
            assertThat(actual1.canRemove(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(nullValue()));
            Comment actual2 = actuals.get(1);
            assertThat(actual2.isUserLikes(), is(nullValue()));
            assertThat(actual2.getMessage(), is(nullValue()));
            assertThat(actual2.getId(), is("500000000000001_4727422"));
            assertThat(actual2.getLikeCount(), is(nullValue()));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("Name Name2"));
            assertThat(actual2.canRemove(), is(nullValue()));
            assertThat(actual2.getCreatedTime(), is(nullValue()));
        }
    }

    public static class getCheckinLikes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/checkin/likes.json");
            ResponseList<Like> actuals = facebook.getCheckinLikes("1122334455667788");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1122334455667788/likes")));

            assertThat(actuals.size(), is(5));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Name Name1"));
            Like actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000000002"));
            assertThat(actual2.getName(), is("Name Name2"));
            Like actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000000003"));
            assertThat(actual3.getName(), is("Name Name3"));
            Like actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("100000000000004"));
            assertThat(actual4.getName(), is("Name Name4"));
            Like actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("100000000000005"));
            assertThat(actual5.getName(), is("Name Name5"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/checkin/likes_limit1.json");
            ResponseList<Like> actuals = facebook.getCheckinLikes("1122334455667788", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1122334455667788/likes")));

            assertThat(actuals.size(), is(1));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Name Name1"));
            assertThat(actuals.getPaging().getCursors().getAfter(), is("MTAwMDAzNjQ0NTM4NDk2"));
            assertThat(actuals.getPaging().getCursors().getBefore(), is("MTAwMDAzNjQ0NTM4NDk2"));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/542957045765908/likes?limit=1&access_token=access_token&after=MTAwMDAzNjQ0NTM4NDk2"));
        }
    }

    public static class checkin extends MockFacebookTestBase {
        @Test
        public void me_minimum_args() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String place = "100404700021921";
            GeoLocation coordinates = new GeoLocation(35.675272122419, 139.69321689514);
            CheckinUpdate checkinUpdate = new CheckinUpdate(place, coordinates);
            String actual = facebook.checkin(checkinUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/checkins")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("place", "100404700021921"));
            assertThat(facebook.getHttpParameters(), hasPostJsonParameter("coordinates", "{\"longitude\":139.69321689514,\"latitude\":35.675272122419}"));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void id_all_args() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String place = "100404700021921";
            GeoLocation coordinates = new GeoLocation(35.675272122419, 139.69321689514);
            String tags = null;
            String message = "test message";
            URL link = new URL("http://www.facebook.com/");
            URL picture = null;
            CheckinUpdate checkinUpdate = new CheckinUpdate(place, coordinates, tags, message, link, picture);
            String actual = facebook.checkin("100000000000001", checkinUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001/checkins")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("place", "100404700021921"));
            assertThat(facebook.getHttpParameters(), hasPostJsonParameter("coordinates", "{\"longitude\":139.69321689514,\"latitude\":35.675272122419}"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test message"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://www.facebook.com/"));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class commentCheckin extends MockFacebookTestBase {
        @Test
        public void comment() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.commentCheckin("112233445566", "test comment for checkin.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/112233445566/comments")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test comment for checkin."));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class likeCheckin extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.likeCheckin("112233445566");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/112233445566/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class unlikeCheckin extends MockFacebookTestBase {
        @Test
        public void unlike() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.unlikeCheckin("112233445566");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/112233445566/likes")));

            assertThat(actual, is(true));
        }
    }

}
