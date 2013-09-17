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
public class GroupMethodsTest {

    public static class getGroups extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/group/groups.json");
            ResponseList<Group> actuals = facebook.getGroups();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/groups")));

            assertThat(actuals.size(), is(3));
            Group actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getName(), is("Group Name1"));
            assertThat(actual1.getBookmarkOrder(), is(1));
            Group actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getName(), is("Group Name3"));
            assertThat(actual3.isAdministrator(), is(true));
            assertThat(actual3.getBookmarkOrder(), is(2));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/group/groups_allfields.json");
            ResponseList<Group> actuals = facebook.getGroups(new Reading().fields("administrator,bookmark_order,cover,description,email,icon,id,link,name,owner,parent,privacy,unread,updated_time,venue"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/groups")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "administrator,bookmark_order,cover,description,email,icon,id,link,name,owner,parent,privacy,unread,updated_time,venue"));

            assertThat(actuals.size(), is(3));
            Group actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yr/r/zzzzzzzzzz.png"));
            assertThat(actual1.getEmail(), is("500000000000001@groups.facebook.com"));
            assertThat(actual1.getName(), is("Group Name1"));
            assertThat(actual1.getPrivacy(), is(GroupPrivacyType.OPEN));
            assertThat(actual1.getOwner().getId(), is("101010101010"));
            assertThat(actual1.getOwner().getName(), is("Owner Name1"));
            assertThat(actual1.getVenue().getStreet(), is(""));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-11T15:18:40+0000")));
            assertThat(actual1.getBookmarkOrder(), is(1));
            Group actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yc/r/yyyyyyyyyyyyyyyy.png"));
            assertThat(actual2.getEmail(), is("500000000000002@groups.facebook.com"));
            assertThat(actual2.getDescription(), is("Group2 Description"));
            assertThat(actual2.getName(), is("Group Name2"));
            assertThat(actual2.getPrivacy(), is(GroupPrivacyType.OPEN));
            assertThat(actual2.getOwner().getId(), is("2020202020"));
            assertThat(actual2.getOwner().getName(), is("Owner Name2"));
            assertThat(actual2.getVenue().getStreet(), is(""));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-02T04:33:26+0000")));
            assertThat(actual2.getBookmarkOrder(), is(3));
            Group actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yI/r/xxxxxxxxxxxxxxxx.png"));
            assertThat(actual3.getEmail(), is("500000000000003@groups.facebook.com"));
            assertThat(actual3.getName(), is("Group Name3"));
            assertThat(actual3.isAdministrator(), is(true));
            assertThat(actual3.getPrivacy(), is(GroupPrivacyType.SECRET));
            assertThat(actual3.getOwner().getId(), is("1234567890123456"));
            assertThat(actual3.getOwner().getName(), is("Owner Name3"));
            assertThat(actual3.getVenue().getStreet(), is("Street Name3"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2012-11-27T23:44:34+0000")));
            assertThat(actual3.getBookmarkOrder(), is(5));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/group/groups.json");
            ResponseList<Group> actuals = facebook.getGroups("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/groups")));

            assertThat(actuals.size(), is(3));
            Group actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getName(), is("Group Name1"));
            assertThat(actual1.getBookmarkOrder(), is(1));
            Group actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getName(), is("Group Name3"));
            assertThat(actual3.isAdministrator(), is(true));
            assertThat(actual3.getBookmarkOrder(), is(2));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/group/groups_allfields.json");
            ResponseList<Group> actuals = facebook.getGroups("1234567890123456", new Reading().fields("administrator,bookmark_order,cover,description,email,icon,id,link,name,owner,parent,privacy,unread,updated_time,venue"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/groups")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "administrator,bookmark_order,cover,description,email,icon,id,link,name,owner,parent,privacy,unread,updated_time,venue"));

            assertThat(actuals.size(), is(3));
            Group actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yr/r/zzzzzzzzzz.png"));
            assertThat(actual1.getEmail(), is("500000000000001@groups.facebook.com"));
            assertThat(actual1.getName(), is("Group Name1"));
            assertThat(actual1.getPrivacy(), is(GroupPrivacyType.OPEN));
            assertThat(actual1.getOwner().getId(), is("101010101010"));
            assertThat(actual1.getOwner().getName(), is("Owner Name1"));
            assertThat(actual1.getVenue().getStreet(), is(""));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-11T15:18:40+0000")));
            assertThat(actual1.getBookmarkOrder(), is(1));
            Group actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yc/r/yyyyyyyyyyyyyyyy.png"));
            assertThat(actual2.getEmail(), is("500000000000002@groups.facebook.com"));
            assertThat(actual2.getDescription(), is("Group2 Description"));
            assertThat(actual2.getName(), is("Group Name2"));
            assertThat(actual2.getPrivacy(), is(GroupPrivacyType.OPEN));
            assertThat(actual2.getOwner().getId(), is("2020202020"));
            assertThat(actual2.getOwner().getName(), is("Owner Name2"));
            assertThat(actual2.getVenue().getStreet(), is(""));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-02T04:33:26+0000")));
            assertThat(actual2.getBookmarkOrder(), is(3));
            Group actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yI/r/xxxxxxxxxxxxxxxx.png"));
            assertThat(actual3.getEmail(), is("500000000000003@groups.facebook.com"));
            assertThat(actual3.getName(), is("Group Name3"));
            assertThat(actual3.isAdministrator(), is(true));
            assertThat(actual3.getPrivacy(), is(GroupPrivacyType.SECRET));
            assertThat(actual3.getOwner().getId(), is("1234567890123456"));
            assertThat(actual3.getOwner().getName(), is("Owner Name3"));
            assertThat(actual3.getVenue().getStreet(), is("Street Name3"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2012-11-27T23:44:34+0000")));
            assertThat(actual3.getBookmarkOrder(), is(5));
        }
    }

    public static class getGroup extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/group/group.json");
            Group actual = facebook.getGroup("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001")));

            assertThat(actual.getId(), is("500000000000001"));
            assertThat(actual.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yr/r/zzzzzzzzzzzz.png"));
            assertThat(actual.getEmail(), is("500000000000001@groups.facebook.com"));
            assertThat(actual.getName(), is("Group Name1"));
            assertThat(actual.getPrivacy(), is(GroupPrivacyType.OPEN));
            assertThat(actual.getOwner().getId(), is("101010101010"));
            assertThat(actual.getOwner().getName(), is("Owner Name1"));
            assertThat(actual.getVenue().getStreet(), is(""));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-08-11T15:18:40+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/group/group_name.json");
            Group actual = facebook.getGroup("500000000000001", new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001")));

            assertThat(actual.getId(), is("500000000000001"));
            assertThat(actual.getEmail(), is(nullValue()));
            assertThat(actual.getName(), is("Group Name1"));
        }
    }

    public static class getGroupFeed extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/group/feed.json");
            ResponseList<Post> actuals = facebook.getGroupFeed("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/feed")));

            assertThat(actuals.size(), is(2));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getTo().get(0).getId(), is("500000000000001"));
            assertThat(actual1.getTo().get(0).getName(), is("Group Name1"));
            assertThat(actual1.getMessage(), is("Message1"));
            assertThat(actual1.getId(), is("500000000000001_400000000000001"));
            assertThat(actual1.getLikes().getCount(), is(6));
            assertThat(actual1.getLikes().get(0).getId(), is("200000001"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("200000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("200000003"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().get(3).getId(), is("200000004"));
            assertThat(actual1.getLikes().get(3).getName(), is("Like Name4"));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getType(), is("status"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-11T15:18:40+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-11T15:18:40+0000")));
            assertThat(actual1.getActions().get(0).getLink().toString(), is("https://www.facebook.com/500000000000001/posts/400000000000001"));
            assertThat(actual1.getActions().get(0).getName(), is("Comment"));
            assertThat(actual1.getActions().get(1).getLink().toString(), is("https://www.facebook.com/500000000000001/posts/400000000000001"));
            assertThat(actual1.getActions().get(1).getName(), is("Like"));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getTo().get(0).getId(), is("500000000000001"));
            assertThat(actual2.getTo().get(0).getName(), is("Group Name1"));
            assertThat(actual2.getMessage(), is("Message2"));
            assertThat(actual2.getId(), is("500000000000001_400000000000002"));
            assertThat(actual2.getLikes().getCount(), is(3));
            assertThat(actual2.getLikes().get(0).getId(), is("300000001"));
            assertThat(actual2.getLikes().get(0).getName(), is("Like Name21"));
            assertThat(actual2.getLikes().get(1).getId(), is("300000001"));
            assertThat(actual2.getLikes().get(1).getName(), is("Like Name22"));
            assertThat(actual2.getLikes().get(2).getId(), is("300000001"));
            assertThat(actual2.getLikes().get(2).getName(), is("Like Name23"));
            assertThat(actual2.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual2.getFrom().getId(), is("100000000000001"));
            assertThat(actual2.getFrom().getName(), is("From Name2"));
            assertThat(actual2.getType(), is("status"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-07T17:13:51+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-08T22:46:07+0000")));
            assertThat(actual2.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual2.getComments().get(0).getMessage(), is("Comment1"));
            assertThat(actual2.getComments().get(0).getId(), is("570000000000001"));
            assertThat(actual2.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("3000000001"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("Comment Name1"));
            assertThat(actual2.getComments().get(0).canRemove(), is(false));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-07T21:30:13+0000")));
            assertThat(actual2.getComments().get(1).isUserLikes(), is(false));
            assertThat(actual2.getComments().get(1).getMessage(), is("Comment2"));
            assertThat(actual2.getComments().get(1).getId(), is("576458272413130"));
            assertThat(actual2.getComments().get(1).getLikeCount(), is(0));
            assertThat(actual2.getComments().get(1).getFrom().getId(), is("3000000002"));
            assertThat(actual2.getComments().get(1).getFrom().getName(), is("Comment Name2"));
            assertThat(actual2.getComments().get(1).canRemove(), is(false));
            assertThat(actual2.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-08-08T00:21:38+0000")));
            assertThat(actual2.getComments().getPaging().getCursors().getAfter(), is("NTc2ODY3OTA1NzA1NTAw"));
            assertThat(actual2.getComments().getPaging().getCursors().getBefore(), is("NTc2NDAxNzQ5MDg1NDQ5"));
            assertThat(actual2.getActions().get(0).getLink().toString(), is("https://www.facebook.com/500000000000001/posts/400000000000002"));
            assertThat(actual2.getActions().get(0).getName(), is("Comment"));
            assertThat(actual2.getActions().get(1).getLink().toString(), is("https://www.facebook.com/500000000000001/posts/400000000000002"));
            assertThat(actual2.getActions().get(1).getName(), is("Like"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/group/feed_message.json");
            ResponseList<Post> actuals = facebook.getGroupFeed("547321978660093", new Reading().fields("message"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/547321978660093/feed")));

            assertThat(actuals.size(), is(2));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is("Message1"));
            assertThat(actual1.getId(), is("500000000000001_400000000000001"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-11T15:18:40+0000")));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getMessage(), is("Message2"));
            assertThat(actual2.getId(), is("500000000000001_400000000000002"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-08T22:46:07+0000")));
        }
    }

    public static class postGroupFeed extends MockFacebookTestBase {
        @Test
        public void onlyMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            PostUpdate postUpdate = new PostUpdate("test message");
            String actual = facebook.postGroupFeed("21212121212121", postUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test message"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void onlyURL() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            PostUpdate postUpdate = new PostUpdate(new URL("http://facebook4j.org"));
            String actual = facebook.postGroupFeed("21212121212121", postUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void share() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            PostUpdate postUpdate = new PostUpdate(new URL("http://facebook4j.org"))
                                    .picture(new URL("http://facebook4j.org/images/hero.png"))
                                    .name("Facebook4J - A Java library for the Facebook Graph API")
                                    .caption("facebook4j.org")
                                    .description("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library.");
            String actual = facebook.postGroupFeed("21212121212121", postUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("picture", "http://facebook4j.org/images/hero.png"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("name", "Facebook4J - A Java library for the Facebook Graph API"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("caption", "facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

    }

    public static class postGroupLink extends MockFacebookTestBase {
        @Test
        public void onlyLink() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postGroupLink("21212121212121", new URL("http://facebook4j.org"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postGroupLink("21212121212121", new URL("http://facebook4j.org"), "The best Facebook API wrapper!");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "The best Facebook API wrapper!"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class postGroupStatusMessage extends MockFacebookTestBase {
        @Test
        public void status() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postGroupStatusMessage("21212121212121", "Facebook4J is a Java library for the Facebook Graph API.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "Facebook4J is a Java library for the Facebook Graph API."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class getGroupMembers extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/group/members.json");
            ResponseList<GroupMember> actuals = facebook.getGroupMembers("21212121212121");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/members")));

            assertThat(actuals.size(), is(5));
            GroupMember actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1000000000000001"));
            assertThat(actual1.getName(), is("Member Name1"));
            assertThat(actual1.isAdministrator(), is(false));
            GroupMember actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("1000000000000004"));
            assertThat(actual4.getName(), is("Member Name4"));
            assertThat(actual4.isAdministrator(), is(true));
            GroupMember actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("1000000000000005"));
            assertThat(actual5.getName(), is("Member Name5"));
            assertThat(actual5.isAdministrator(), is(false));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/group/members_more_fields.json");
            ResponseList<GroupMember> actuals = facebook.getGroupMembers("21212121212121", new Reading().fields("bio,gender,first_name,last_name,birthday"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/members")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "bio,gender,first_name,last_name,birthday"));

            assertThat(actuals.size(), is(5));
            GroupMember actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1000000000000001"));
            assertThat(actual1.getFirstName(), is("First1"));
            assertThat(actual1.getBirthday(), is("04/28/1982"));
            assertThat(actual1.getLastName(), is("Last1"));
            assertThat(actual1.getGender(), is("male"));
            GroupMember actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("1000000000000004"));
            assertThat(actual4.getFirstName(), is("First4"));
            assertThat(actual4.getLastName(), is("Last4"));
            assertThat(actual4.getGender(), is("male"));
            assertThat(actual4.isAdministrator(), is(nullValue()));
            GroupMember actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("1000000000000005"));
            assertThat(actual5.getFirstName(), is("First5"));
            assertThat(actual5.getBirthday(), is("08/01/1971"));
            assertThat(actual5.getBio(), is("Ruby Programmer.\r\nI Love Emacs."));
            assertThat(actual5.getLastName(), is("Last5"));
            assertThat(actual5.getGender(), is("male"));
        }
    }

    public static class getGroupPictureURL extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/empty.json");
            URL actual = facebook.getGroupPictureURL("195466193802264");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/195466193802264/picture")));

            assertThat(actual.toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash3/644169_573207722741517_740837405_a.jpg"));
        }
    }

    public static class getGroupDocs extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/group/docs.json");
            ResponseList<GroupDoc> actuals = facebook.getGroupDocs("195466193802264");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/195466193802264/docs")));

            assertThat(actuals.size(), is(2));
            GroupDoc actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("207279222620961"));
            assertThat(actual1.getRevision(), is(207279219287628L));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yi/r/-64q65AWgXb.png"));
            assertThat(actual1.getSubject(), is("DENEME"));
            assertThat(actual1.getFrom().getId(), is("100000584317872"));
            assertThat(actual1.getFrom().getName(), is("Şuhut Gündem Haber Portalı"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-03-12T02:43:04+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2011-03-12T02:43:04+0000")));
            GroupDoc actual2 = actuals.get(1);
            assertThat(actual2.getMessage(), is("<p>Document text.</p>"));
            assertThat(actual2.getId(), is("195467123802171"));
            assertThat(actual2.getRevision(), is(195467120468838L));
            assertThat(actual2.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yi/r/-64q65AWgXb.png"));
            assertThat(actual2.getSubject(), is("Example Doc"));
            assertThat(actual2.getFrom().getId(), is("202875"));
            assertThat(actual2.getFrom().getName(), is("Ravi Grover"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2011-01-27T00:11:46+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2011-01-27T00:11:46+0000")));
        }
    }
}
