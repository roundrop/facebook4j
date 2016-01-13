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

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class NotificationMethodsTest {

    public static class getNotifications extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j.json");
            ResponseList<Notification> actuals = facebook.getNotifications();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/notifications")));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getId(), is("notif_100000000000001_80000009"));
            assertThat(actual1.getTitle(), is("Name1 commented on a link you shared: \"test...\""));
            assertThat(actual1.getApplication().getId(), is("2309869772"));
            assertThat(actual1.getApplication().getName(), is("Links"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363?comment_id=3000001&offset=0&total_comments=1"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual1.getTargetObject().getType(), is("link"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo().getId(), is("1234567890123456"));
            assertThat(actual2.getTo().getName(), is("My Name"));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is("Name11 likes your link: \"test2...\""));
            assertThat(actual2.getApplication().getId(), is("2409997254"));
            assertThat(actual2.getApplication().getName(), is("Likes"));
            assertThat(actual2.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363"));
            assertThat(actual2.unread(), is(true));
            assertThat(actual2.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual2.getTargetObject().getType(), is("link"));
            assertThat(actual2.getFrom().getId(), is("200000000000001"));
            assertThat(actual2.getFrom().getName(), is("Name Name11"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
        }

        @Test
        public void me_includeRead() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j_include_read.json");
            ResponseList<Notification> actuals = facebook.getNotifications(true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/notifications")));
            assertThat(facebook.getEndpointURL(), hasParameter("include_read", "1"));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getId(), is("notif_100000000000001_80000009"));
            assertThat(actual1.getTitle(), is("Name1 commented on a link you shared: \"test...\""));
            assertThat(actual1.getApplication().getId(), is("2309869772"));
            assertThat(actual1.getApplication().getName(), is("Links"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363?comment_id=3000001&offset=0&total_comments=1"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual1.getTargetObject().getType(), is("link"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo().getId(), is("1234567890123456"));
            assertThat(actual2.getTo().getName(), is("My Name"));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is("Name11 likes your link: \"test2...\""));
            assertThat(actual2.getApplication().getId(), is("2409997254"));
            assertThat(actual2.getApplication().getName(), is("Likes"));
            assertThat(actual2.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363"));
            assertThat(actual2.unread(), is(false));
            assertThat(actual2.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual2.getTargetObject().getType(), is("link"));
            assertThat(actual2.getFrom().getId(), is("200000000000001"));
            assertThat(actual2.getFrom().getName(), is("Name Name11"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j_unread.json");
            ResponseList<Notification> actuals = facebook.getNotifications(new Reading().fields("unread"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/notifications")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo(), is(nullValue()));
            assertThat(actual1.getId(), is("notif_1234567890123456_80000009"));
            assertThat(actual1.getTitle(), is(nullValue()));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLink(), is(nullValue()));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject(), is(nullValue()));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo(), is(nullValue()));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is(nullValue()));
            assertThat(actual2.getApplication(), is(nullValue()));
            assertThat(actual2.getLink(), is(nullValue()));
            assertThat(actual2.unread(), is(true));
            assertThat(actual2.getTargetObject(), is(nullValue()));
            assertThat(actual2.getFrom(), is(nullValue()));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(nullValue()));
        }

        @Test
        public void me_reading_includeRead() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j_unread_include_read.json");
            ResponseList<Notification> actuals = facebook.getNotifications(new Reading().fields("unread"), true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/notifications")));
            assertThat(facebook.getEndpointURL(), hasParameter("include_read", "1"));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo(), is(nullValue()));
            assertThat(actual1.getId(), is("notif_1234567890123456_80000009"));
            assertThat(actual1.getTitle(), is(nullValue()));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLink(), is(nullValue()));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject(), is(nullValue()));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo(), is(nullValue()));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is(nullValue()));
            assertThat(actual2.getApplication(), is(nullValue()));
            assertThat(actual2.getLink(), is(nullValue()));
            assertThat(actual2.unread(), is(false));
            assertThat(actual2.getTargetObject(), is(nullValue()));
            assertThat(actual2.getFrom(), is(nullValue()));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j.json");
            ResponseList<Notification> actuals = facebook.getNotifications("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/notifications")));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getId(), is("notif_100000000000001_80000009"));
            assertThat(actual1.getTitle(), is("Name1 commented on a link you shared: \"test...\""));
            assertThat(actual1.getApplication().getId(), is("2309869772"));
            assertThat(actual1.getApplication().getName(), is("Links"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363?comment_id=3000001&offset=0&total_comments=1"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual1.getTargetObject().getType(), is("link"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo().getId(), is("1234567890123456"));
            assertThat(actual2.getTo().getName(), is("My Name"));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is("Name11 likes your link: \"test2...\""));
            assertThat(actual2.getApplication().getId(), is("2409997254"));
            assertThat(actual2.getApplication().getName(), is("Likes"));
            assertThat(actual2.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363"));
            assertThat(actual2.unread(), is(true));
            assertThat(actual2.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual2.getTargetObject().getType(), is("link"));
            assertThat(actual2.getFrom().getId(), is("200000000000001"));
            assertThat(actual2.getFrom().getName(), is("Name Name11"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
        }

        @Test
        public void id_includeRead() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j_include_read.json");
            ResponseList<Notification> actuals = facebook.getNotifications("1234567890123456", true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/notifications")));
            assertThat(facebook.getEndpointURL(), hasParameter("include_read", "1"));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getId(), is("notif_100000000000001_80000009"));
            assertThat(actual1.getTitle(), is("Name1 commented on a link you shared: \"test...\""));
            assertThat(actual1.getApplication().getId(), is("2309869772"));
            assertThat(actual1.getApplication().getName(), is("Links"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363?comment_id=3000001&offset=0&total_comments=1"));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual1.getTargetObject().getType(), is("link"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo().getId(), is("1234567890123456"));
            assertThat(actual2.getTo().getName(), is("My Name"));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is("Name11 likes your link: \"test2...\""));
            assertThat(actual2.getApplication().getId(), is("2409997254"));
            assertThat(actual2.getApplication().getName(), is("Likes"));
            assertThat(actual2.getLink().toString(), is("http://www.facebook.com/hogehoge/posts/363636363636363"));
            assertThat(actual2.unread(), is(false));
            assertThat(actual2.getTargetObject().getId(), is("1234567890123456_363636363636363"));
            assertThat(actual2.getTargetObject().getType(), is("link"));
            assertThat(actual2.getFrom().getId(), is("200000000000001"));
            assertThat(actual2.getFrom().getName(), is("Name Name11"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j_unread.json");
            ResponseList<Notification> actuals = facebook.getNotifications("1234567890123456", new Reading().fields("unread"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/notifications")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo(), is(nullValue()));
            assertThat(actual1.getId(), is("notif_1234567890123456_80000009"));
            assertThat(actual1.getTitle(), is(nullValue()));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLink(), is(nullValue()));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject(), is(nullValue()));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo(), is(nullValue()));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is(nullValue()));
            assertThat(actual2.getApplication(), is(nullValue()));
            assertThat(actual2.getLink(), is(nullValue()));
            assertThat(actual2.unread(), is(true));
            assertThat(actual2.getTargetObject(), is(nullValue()));
            assertThat(actual2.getFrom(), is(nullValue()));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(nullValue()));
        }

        @Test
        public void id_reading_includeRead() throws Exception {
            facebook.setMockJSON("mock_json/notification/f4j_unread_include_read.json");
            ResponseList<Notification> actuals = facebook.getNotifications("1234567890123456", new Reading().fields("unread"), true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/notifications")));
            assertThat(facebook.getEndpointURL(), hasParameter("include_read", "1"));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));

            assertThat(actuals.size(), is(2));
            Notification actual1 = actuals.get(0);
            assertThat(actual1.getTo(), is(nullValue()));
            assertThat(actual1.getId(), is("notif_1234567890123456_80000009"));
            assertThat(actual1.getTitle(), is(nullValue()));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLink(), is(nullValue()));
            assertThat(actual1.unread(), is(true));
            assertThat(actual1.getTargetObject(), is(nullValue()));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:59:13+0000")));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
            Notification actual2 = actuals.get(1);
            assertThat(actual2.getTo(), is(nullValue()));
            assertThat(actual2.getId(), is("notif_1234567890123456_80000005"));
            assertThat(actual2.getTitle(), is(nullValue()));
            assertThat(actual2.getApplication(), is(nullValue()));
            assertThat(actual2.getLink(), is(nullValue()));
            assertThat(actual2.unread(), is(false));
            assertThat(actual2.getTargetObject(), is(nullValue()));
            assertThat(actual2.getFrom(), is(nullValue()));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-17T09:58:03+0000")));
            assertThat(actual2.getUpdatedTime(), is(nullValue()));
        }
    }

    public static class markNotificationAsRead extends MockFacebookTestBase {
        @Test
        public void mark() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.markNotificationAsRead("notif_1234567890123456_80000005");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/notif_1234567890123456_80000005")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void mark_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.markNotificationAsRead("notif_1234567890123456_80000005");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/notif_1234567890123456_80000005")));

            assertThat(actual, is(true));
        }
    }

}
