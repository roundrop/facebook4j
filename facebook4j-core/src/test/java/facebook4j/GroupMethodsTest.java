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

}
