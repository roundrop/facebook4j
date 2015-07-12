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
import facebook4j.junit.FacebookAPIVersion;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Locale;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class FriendMethodsTest {

    public static class getFriends extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/friend/friends.json");
            ResponseList<Friend> actuals = facebook.getFriends();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friends")));

            assertThat(actuals.size(), is(5));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000001"));
            assertThat(actual1.getName(), is("Friend Name1"));
            Friend actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("100000005"));
            assertThat(actual5.getName(), is("Friend Name5"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/friends_large_picture.json");
            ResponseList<Friend> actuals = facebook.getFriends(new Reading().fields("name").fields("picture.type(large)").limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friends")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,picture.type(large)"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000001"));
            assertThat(actual1.getName(), is("Friend Name1"));
            assertThat(actual1.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/111111_111111111_111111111_n.jpg"));
            assertThat(actual1.getPicture().isSilhouette(), is(false));
            Friend actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000003"));
            assertThat(actual3.getName(), is("Friend Name3"));
            assertThat(actual3.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/111111_111111111_111111113_n.jpg"));
            assertThat(actual3.getPicture().isSilhouette(), is(false));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/friend/friends.json");
            ResponseList<Friend> actuals = facebook.getFriends("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/friends")));

            assertThat(actuals.size(), is(5));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000001"));
            assertThat(actual1.getName(), is("Friend Name1"));
            Friend actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("100000005"));
            assertThat(actual5.getName(), is("Friend Name5"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/friends_large_picture.json");
            ResponseList<Friend> actuals = facebook.getFriends("1234567890123456", new Reading().fields("name").fields("picture.type(large)").limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/friends")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,picture.type(large)"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000001"));
            assertThat(actual1.getName(), is("Friend Name1"));
            assertThat(actual1.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/111111_111111111_111111111_n.jpg"));
            assertThat(actual1.getPicture().isSilhouette(), is(false));
            Friend actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000003"));
            assertThat(actual3.getName(), is("Friend Name3"));
            assertThat(actual3.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/111111_111111111_111111113_n.jpg"));
            assertThat(actual3.getPicture().isSilhouette(), is(false));
        }
    }
    
    public static class getBelongsFriend extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/friend/belongs.json");
            ResponseList<Friend> actual = facebook.getBelongsFriend("100000002");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friends/100000002")));

            assertThat(actual.size(), is(1));
            assertThat(actual.get(0).getId(), is("100000002"));
            assertThat(actual.get(0).getName(), is("Friend Name2"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/belongs_locale.json");
            ResponseList<Friend> actual = facebook.getBelongsFriend("100000002", new Reading().fields("name,locale"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friends/100000002")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,locale"));

            assertThat(actual.size(), is(1));
            assertThat(actual.get(0).getId(), is("100000002"));
            assertThat(actual.get(0).getName(), is("Friend Name2"));
            assertThat(actual.get(0).getLocale(), is(Locale.JAPAN));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/friend/belongs.json");
            ResponseList<Friend> actual = facebook.getBelongsFriend("1234567890123456", "100000002");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/friends/100000002")));

            assertThat(actual.size(), is(1));
            assertThat(actual.get(0).getId(), is("100000002"));
            assertThat(actual.get(0).getName(), is("Friend Name2"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/belongs_locale.json");
            ResponseList<Friend> actual = facebook.getBelongsFriend("1234567890123456", "100000002", new Reading().fields("name,locale"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/friends/100000002")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,locale"));

            assertThat(actual.size(), is(1));
            assertThat(actual.get(0).getId(), is("100000002"));
            assertThat(actual.get(0).getName(), is("Friend Name2"));
            assertThat(actual.get(0).getLocale(), is(Locale.JAPAN));
        }

        @Test
        public void notFriend() throws Exception {
            facebook.setMockJSON("mock_json/empty_array.json");
            ResponseList<Friend> actual = facebook.getBelongsFriend("200000002");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friends/200000002")));

            assertThat(actual.size(), is(0));
        }

    }

    public static class getFriendlists extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/friend/friendlists.json");
            ResponseList<Friendlist> actuals = facebook.getFriendlists();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friendlists")));

            assertThat(actuals.size(), is(4));
            Friendlist actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("200000000000001"));
            assertThat(actual1.getListType(), is("work"));
            assertThat(actual1.getName(), is("atWare, Inc."));
            Friendlist actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("200000000000002"));
            assertThat(actual2.getListType(), is("family"));
            assertThat(actual2.getName(), is("Family"));
            Friendlist actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("200000000000003"));
            assertThat(actual3.getListType(), is("close_friends"));
            assertThat(actual3.getName(), is("Close Friends"));
            Friendlist actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("200000000000004"));
            assertThat(actual4.getListType(), is("current_city"));
            assertThat(actual4.getName(), is("Yokohama-shi, Kanagawa, Japan Area"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/friendlists_limit1.json");
            ResponseList<Friendlist> actuals = facebook.getFriendlists(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friendlists")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Friendlist actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("200000000000001"));
            assertThat(actual1.getListType(), is("work"));
            assertThat(actual1.getName(), is("atWare, Inc."));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/friend/friendlists.json");
            ResponseList<Friendlist> actuals = facebook.getFriendlists("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/friendlists")));

            assertThat(actuals.size(), is(4));
            Friendlist actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("200000000000001"));
            assertThat(actual1.getListType(), is("work"));
            assertThat(actual1.getName(), is("atWare, Inc."));
            Friendlist actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("200000000000002"));
            assertThat(actual2.getListType(), is("family"));
            assertThat(actual2.getName(), is("Family"));
            Friendlist actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("200000000000003"));
            assertThat(actual3.getListType(), is("close_friends"));
            assertThat(actual3.getName(), is("Close Friends"));
            Friendlist actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("200000000000004"));
            assertThat(actual4.getListType(), is("current_city"));
            assertThat(actual4.getName(), is("Yokohama-shi, Kanagawa, Japan Area"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/friendlists_limit1.json");
            ResponseList<Friendlist> actuals = facebook.getFriendlists("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/friendlists")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Friendlist actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("200000000000001"));
            assertThat(actual1.getListType(), is("work"));
            assertThat(actual1.getName(), is("atWare, Inc."));
        }
    }

    public static class createFriendlist extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.createFriendlist("F4J-TEST");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friendlists")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("name", "F4J-TEST"));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.createFriendlist("1111111111111111", "F4J-TEST");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1111111111111111/friendlists")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("name", "F4J-TEST"));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class getFriendlist extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/friend/friendlist.json");
            Friendlist actual = facebook.getFriendlist("579087298820226");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/579087298820226")));

            assertThat(actual.getId(), is("579087298820226"));
            assertThat(actual.getListType(), is("user_created"));
            assertThat(actual.getName(), is("F4J-TEST"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/friendlist_name.json");
            Friendlist actual = facebook.getFriendlist("579087298820226", new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/579087298820226")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(actual.getId(), is("579087298820226"));
            assertThat(actual.getListType(), is(nullValue()));
            assertThat(actual.getName(), is("F4J-TEST"));
        }
    }

    public static class deleteFriendlist extends MockFacebookTestBase {
        @Test
        public void delete() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteFriendlist("579087298820226");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/579087298820226")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void delete_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteFriendlist("579087298820226");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/579087298820226")));

            assertThat(actual, is(true));
        }
    }

    public static class getFriendlistMembers extends MockFacebookTestBase {
        @Test
        public void get() throws Exception {
            facebook.setMockJSON("mock_json/friend/members.json");
            ResponseList<Friend> actuals = facebook.getFriendlistMembers("269085193153773");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/269085193153773/members")));

            assertThat(actuals.size(), is(5));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Member Name1"));
            Friend actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("100000000000005"));
            assertThat(actual5.getName(), is("Member Name5"));
        }
    }

    public static class addFriendlistMember extends MockFacebookTestBase {
        @Test
        public void add() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.addFriendlistMember("579096788819277", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/579096788819277/members/1234567890123456")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void add_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.addFriendlistMember("579096788819277", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/579096788819277/members/1234567890123456")));

            assertThat(actual, is(true));
        }
    }

    public static class removeFriendlistMember extends MockFacebookTestBase {
        @Test
        public void add() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.removeFriendlistMember("579096788819277", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/579096788819277/members/1234567890123456")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void add_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.removeFriendlistMember("579096788819277", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/579096788819277/members/1234567890123456")));

            assertThat(actual, is(true));
        }
    }

    public static class deleteFriendlistMember extends MockFacebookTestBase {
        @Test
        public void add() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteFriendlistMember("579096788819277", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/579096788819277/members/1234567890123456")));

            assertThat(actual, is(true));
        }
    }

    public static class getFriendRequests extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/friend/requests.json");
            ResponseList<FriendRequest> actuals = facebook.getFriendRequests();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friendrequests")));

            assertThat(actuals.size(), is(1));
            FriendRequest actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("100000000000001"));
            assertThat(actual1.getTo().getName(), is("bestfriend one"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getFrom().getId(), is("100000000000002"));
            assertThat(actual1.getFrom().getName(), is("bestfriend two"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-11T12:17:01+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/requests.json");
            ResponseList<FriendRequest> actuals = facebook.getFriendRequests(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/friendrequests")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            FriendRequest actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("100000000000001"));
            assertThat(actual1.getTo().getName(), is("bestfriend one"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getFrom().getId(), is("100000000000002"));
            assertThat(actual1.getFrom().getName(), is("bestfriend two"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-11T12:17:01+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/friend/requests.json");
            ResponseList<FriendRequest> actuals = facebook.getFriendRequests("100000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001/friendrequests")));

            assertThat(actuals.size(), is(1));
            FriendRequest actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("100000000000001"));
            assertThat(actual1.getTo().getName(), is("bestfriend one"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getFrom().getId(), is("100000000000002"));
            assertThat(actual1.getFrom().getName(), is("bestfriend two"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-11T12:17:01+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/requests.json");
            ResponseList<FriendRequest> actuals = facebook.getFriendRequests("100000000000001", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001/friendrequests")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            FriendRequest actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("100000000000001"));
            assertThat(actual1.getTo().getName(), is("bestfriend one"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getFrom().getId(), is("100000000000002"));
            assertThat(actual1.getFrom().getName(), is("bestfriend two"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-11T12:17:01+0000")));
        }
    }

    public static class getMutualFriends extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/friend/mutual.json");
            ResponseList<Friend> actuals = facebook.getMutualFriends("21212121");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/mutualfriends/21212121")));

            assertThat(actuals.size(), is(3));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000001"));
            assertThat(actual1.getName(), is("Mutual Name1"));
            Friend actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000002"));
            assertThat(actual2.getName(), is("Mutual Name2"));
            Friend actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000003"));
            assertThat(actual3.getName(), is("Mutual Name3"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/mutual_gender.json");
            ResponseList<Friend> actuals = facebook.getMutualFriends("21212121", new Reading().fields("gender"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/mutualfriends/21212121")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "gender"));

            assertThat(actuals.size(), is(3));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000001"));
            assertThat(actual1.getName(), is("Mutual Name1"));
            assertThat(actual1.getGender(), is("male"));
            Friend actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000002"));
            assertThat(actual2.getName(), is("Mutual Name2"));
            assertThat(actual2.getGender(), is("female"));
            Friend actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000003"));
            assertThat(actual3.getName(), is("Mutual Name3"));
            assertThat(actual3.getGender(), is("male"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/friend/mutual.json");
            ResponseList<Friend> actuals = facebook.getMutualFriends("1234567890123456", "21212121");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/mutualfriends/21212121")));

            assertThat(actuals.size(), is(3));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000001"));
            assertThat(actual1.getName(), is("Mutual Name1"));
            Friend actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000002"));
            assertThat(actual2.getName(), is("Mutual Name2"));
            Friend actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000003"));
            assertThat(actual3.getName(), is("Mutual Name3"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/mutual_gender.json");
            ResponseList<Friend> actuals = facebook.getMutualFriends("1234567890123456", "21212121", new Reading().fields("gender"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/mutualfriends/21212121")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "gender"));

            assertThat(actuals.size(), is(3));
            Friend actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000001"));
            assertThat(actual1.getName(), is("Mutual Name1"));
            assertThat(actual1.getGender(), is("male"));
            Friend actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000002"));
            assertThat(actual2.getName(), is("Mutual Name2"));
            assertThat(actual2.getGender(), is("female"));
            Friend actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000003"));
            assertThat(actual3.getName(), is("Mutual Name3"));
            assertThat(actual3.getGender(), is("male"));
        }
    }

    public static class getTaggableFriends extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/friend/taggable_friends.json");
            ResponseList<TaggableFriend> actuals = facebook.getTaggableFriends();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/taggable_friends")));

            assertThat(actuals.size(), is(2));
            TaggableFriend actual1 = actuals.get(0);
            assertThat(actual1.getToken(), is("AaLml5U5tssCqmaVxDfaID0JWQW_cmloE_iD0UQ1ALz4g-bdAqgY0sqDIsdfastzr-yewJhYuuT7W0Mm-YEEq8n1aG9lAeAfmvA9cq-B9Hg"));
            assertThat(actual1.getName(), is("Friend One"));
            TaggableFriend actual5 = actuals.get(1);
            assertThat(actual5.getToken(), is("AaJ73QgEXPUlrEb6XNob5B6S_Xl2qeCCpj5parsRlvmFkXs7SOq5hPEcIt8Enw37MasfmLxnJ0i1RGRrspiPjhb_KXDQbzKW68Tg"));
            assertThat(actual5.getName(), is("Friend Two"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/taggable_friends.json");
            ResponseList<TaggableFriend> actuals = facebook.getTaggableFriends(new Reading().fields("name").fields("picture.type(large)").limit(2));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/taggable_friends")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,picture.type(large)"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "2"));

            assertThat(actuals.size(), is(2));
            TaggableFriend actual1 = actuals.get(0);
            assertThat(actual1.getToken(), is("AaLml5U5tssCqmaVxDfaID0JWQW_cmloE_iD0UQ1ALz4g-bdAqgY0sqDIsdfastzr-yewJhYuuT7W0Mm-YEEq8n1aG9lAeAfmvA9cq-B9Hg"));
            assertThat(actual1.getName(), is("Friend One"));
            assertThat(actual1.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/v/t1.0-1/p50x50/1532100_61720120769_7344809391293777_n.jpg"));
            assertThat(actual1.getPicture().isSilhouette(), is(false));
            TaggableFriend actual3 = actuals.get(1);
            assertThat(actual3.getToken(), is("AaJ73QgEXPUlrEb6XNob5B6S_Xl2qeCCpj5parsRlvmFkXs7SOq5hPEcIt8Enw37MasfmLxnJ0i1RGRrspiPjhb_KXDQbzKW68Tg"));
            assertThat(actual3.getName(), is("Friend Two"));
            assertThat(actual3.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xaf1/v/t1.0-1/c51.50.611.119/s50x50/3146305_1015111258096607_1858078876_n.jpg"));
            assertThat(actual3.getPicture().isSilhouette(), is(false));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/friend/taggable_friends.json");
            ResponseList<TaggableFriend> actuals = facebook.getTaggableFriends("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/taggable_friends")));

            assertThat(actuals.size(), is(2));
            TaggableFriend actual1 = actuals.get(0);
            assertThat(actual1.getToken(), is("AaLml5U5tssCqmaVxDfaID0JWQW_cmloE_iD0UQ1ALz4g-bdAqgY0sqDIsdfastzr-yewJhYuuT7W0Mm-YEEq8n1aG9lAeAfmvA9cq-B9Hg"));
            assertThat(actual1.getName(), is("Friend One"));
            TaggableFriend actual5 = actuals.get(1);
            assertThat(actual5.getToken(), is("AaJ73QgEXPUlrEb6XNob5B6S_Xl2qeCCpj5parsRlvmFkXs7SOq5hPEcIt8Enw37MasfmLxnJ0i1RGRrspiPjhb_KXDQbzKW68Tg"));
            assertThat(actual5.getName(), is("Friend Two"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/friend/taggable_friends.json");
            ResponseList<TaggableFriend> actuals = facebook.getTaggableFriends("1234567890123456", new Reading().fields("name").fields("picture.type(large)").limit(2));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/taggable_friends")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,picture.type(large)"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "2"));

            assertThat(actuals.size(), is(2));
            TaggableFriend actual1 = actuals.get(0);
            assertThat(actual1.getToken(), is("AaLml5U5tssCqmaVxDfaID0JWQW_cmloE_iD0UQ1ALz4g-bdAqgY0sqDIsdfastzr-yewJhYuuT7W0Mm-YEEq8n1aG9lAeAfmvA9cq-B9Hg"));
            assertThat(actual1.getName(), is("Friend One"));
            assertThat(actual1.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/v/t1.0-1/p50x50/1532100_61720120769_7344809391293777_n.jpg"));
            assertThat(actual1.getPicture().isSilhouette(), is(false));
            TaggableFriend actual3 = actuals.get(1);
            assertThat(actual3.getToken(), is("AaJ73QgEXPUlrEb6XNob5B6S_Xl2qeCCpj5parsRlvmFkXs7SOq5hPEcIt8Enw37MasfmLxnJ0i1RGRrspiPjhb_KXDQbzKW68Tg"));
            assertThat(actual3.getName(), is("Friend Two"));
            assertThat(actual3.getPicture().getURL().toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xaf1/v/t1.0-1/c51.50.611.119/s50x50/3146305_1015111258096607_1858078876_n.jpg"));
            assertThat(actual3.getPicture().isSilhouette(), is(false));
        }

    }

}
