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
public class MessageMethodsTest {

    public static class getInbox extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/message/inbox.json");
            InboxResponseList<Message> actuals = facebook.getInbox();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/inbox")));

            assertThat(actuals.size(), is(3));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getTo().size(), is(2));
            assertThat(actual1.getTo().get(0).getId(), is("1000000000000001"));
            assertThat(actual1.getTo().get(0).getName(), is("From Name1"));
            assertThat(actual1.getTo().get(1).getId(), is("1234567980123456"));
            assertThat(actual1.getTo().get(1).getName(), is("My Name"));
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().size(), is(2));
            assertThat(actual1.getComments().get(0).getMessage(), is("Hi"));
            assertThat(actual1.getComments().get(0).getId(), is("500000000000001_1"));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("1000000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("From Name1"));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-31T13:53:46+0000")));
            assertThat(actual1.getComments().get(1).getMessage(), is("How are you?"));
            assertThat(actual1.getComments().get(1).getId(), is("500000000000001_2"));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("1234567980123456"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("My Name"));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&since=1376384578&__paging_token=600000000000001_51&__previous=1"));
            assertThat(actual1.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&until=1359640426&__paging_token=600000000000001_27"));
            assertThat(actual1.getUnseen(), is(0));
            assertThat(actual1.getUnread(), is(0));
            Message actual2 = actuals.get(1);
            assertThat(actual2.getFrom(), is(nullValue()));
            assertThat(actual2.getTo().size(), is(2));
            assertThat(actual2.getTo().get(0).getId(), is("1000000000000002"));
            assertThat(actual2.getTo().get(0).getName(), is("From Name2"));
            assertThat(actual2.getTo().get(1).getId(), is("1234567980123456"));
            assertThat(actual2.getTo().get(1).getName(), is("My Name"));
            assertThat(actual2.getId(), is("600000000000002"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-08T02:45:16+0000")));
            assertThat(actual2.getComments().size(), is(3));
            assertThat(actual2.getComments().get(0).getMessage(), is("m(_ _)m"));
            assertThat(actual2.getComments().get(0).getId(), is("600000000000002_1"));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:23:47+0000")));
            assertThat(actual2.getComments().get(2).getMessage(), is("Thanks! (^o^)"));
            assertThat(actual2.getComments().get(2).getId(), is("600000000000002_3"));
            assertThat(actual2.getComments().get(2).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(2).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:35:52+0000")));
            assertThat(actual2.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&since=1375929916&__paging_token=600000000000002_8&__previous=1"));
            assertThat(actual2.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&until=1375925027&__paging_token=600000000000002_1"));
            assertThat(actual2.getUnseen(), is(1));
            assertThat(actual2.getUnread(), is(2));
            Message actual3 = actuals.get(2);
            assertThat(actual3.getFrom(), is(nullValue()));
            assertThat(actual3.getTo().size(), is(5));
            assertThat(actual3.getTo().get(0).getId(), is("1000000000000005"));
            assertThat(actual3.getTo().get(0).getName(), is("From Name5"));
            assertThat(actual3.getTo().get(4).getId(), is("1234567980123456"));
            assertThat(actual3.getTo().get(4).getName(), is("My Name"));
            assertThat(actual3.getId(), is("600000000000003"));
            assertThat(actual3.getUnread(), is(0));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().size(), is(9));
            assertThat(actual3.getComments().get(0).getMessage(), is("From Name3 named the conversation: \"hogehoge\"."));
            assertThat(actual3.getComments().get(0).getId(), is("600000000000003_1"));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("1000000000000003"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("From Name3"));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-31T03:58:05+0000")));
            assertThat(actual3.getComments().get(8).getMessage(), is("Hi!!!"));
            assertThat(actual3.getComments().get(8).getId(), is("600000000000003_9"));
            assertThat(actual3.getComments().get(8).getFrom().getId(), is("1000000000000006"));
            assertThat(actual3.getComments().get(8).getFrom().getName(), is("From Name6"));
            assertThat(actual3.getComments().get(8).getCreatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&since=1375245266&__paging_token=600000000000003_16&__previous=1"));
            assertThat(actual3.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&until=1375243085&__paging_token=600000000000003_1"));
            assertThat(actual3.getUnseen(), is(0));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/message/inbox_unread.json");
            InboxResponseList<Message> actuals = facebook.getInbox(new Reading().fields("unread").limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/inbox")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUnread(), is(1));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            Message actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("600000000000005"));
            assertThat(actual5.getUnread(), is(5));
            assertThat(actual5.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:53:23+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/message/inbox.json");
            InboxResponseList<Message> actuals = facebook.getInbox("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/inbox")));

            assertThat(actuals.size(), is(3));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getTo().size(), is(2));
            assertThat(actual1.getTo().get(0).getId(), is("1000000000000001"));
            assertThat(actual1.getTo().get(0).getName(), is("From Name1"));
            assertThat(actual1.getTo().get(1).getId(), is("1234567980123456"));
            assertThat(actual1.getTo().get(1).getName(), is("My Name"));
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().size(), is(2));
            assertThat(actual1.getComments().get(0).getMessage(), is("Hi"));
            assertThat(actual1.getComments().get(0).getId(), is("500000000000001_1"));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("1000000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("From Name1"));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-31T13:53:46+0000")));
            assertThat(actual1.getComments().get(1).getMessage(), is("How are you?"));
            assertThat(actual1.getComments().get(1).getId(), is("500000000000001_2"));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("1234567980123456"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("My Name"));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&since=1376384578&__paging_token=600000000000001_51&__previous=1"));
            assertThat(actual1.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&until=1359640426&__paging_token=600000000000001_27"));
            assertThat(actual1.getUnseen(), is(0));
            assertThat(actual1.getUnread(), is(0));
            Message actual2 = actuals.get(1);
            assertThat(actual2.getFrom(), is(nullValue()));
            assertThat(actual2.getTo().size(), is(2));
            assertThat(actual2.getTo().get(0).getId(), is("1000000000000002"));
            assertThat(actual2.getTo().get(0).getName(), is("From Name2"));
            assertThat(actual2.getTo().get(1).getId(), is("1234567980123456"));
            assertThat(actual2.getTo().get(1).getName(), is("My Name"));
            assertThat(actual2.getId(), is("600000000000002"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-08T02:45:16+0000")));
            assertThat(actual2.getComments().size(), is(3));
            assertThat(actual2.getComments().get(0).getMessage(), is("m(_ _)m"));
            assertThat(actual2.getComments().get(0).getId(), is("600000000000002_1"));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:23:47+0000")));
            assertThat(actual2.getComments().get(2).getMessage(), is("Thanks! (^o^)"));
            assertThat(actual2.getComments().get(2).getId(), is("600000000000002_3"));
            assertThat(actual2.getComments().get(2).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(2).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:35:52+0000")));
            assertThat(actual2.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&since=1375929916&__paging_token=600000000000002_8&__previous=1"));
            assertThat(actual2.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&until=1375925027&__paging_token=600000000000002_1"));
            assertThat(actual2.getUnseen(), is(1));
            assertThat(actual2.getUnread(), is(2));
            Message actual3 = actuals.get(2);
            assertThat(actual3.getFrom(), is(nullValue()));
            assertThat(actual3.getTo().size(), is(5));
            assertThat(actual3.getTo().get(0).getId(), is("1000000000000005"));
            assertThat(actual3.getTo().get(0).getName(), is("From Name5"));
            assertThat(actual3.getTo().get(4).getId(), is("1234567980123456"));
            assertThat(actual3.getTo().get(4).getName(), is("My Name"));
            assertThat(actual3.getId(), is("600000000000003"));
            assertThat(actual3.getUnread(), is(0));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().size(), is(9));
            assertThat(actual3.getComments().get(0).getMessage(), is("From Name3 named the conversation: \"hogehoge\"."));
            assertThat(actual3.getComments().get(0).getId(), is("600000000000003_1"));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("1000000000000003"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("From Name3"));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-31T03:58:05+0000")));
            assertThat(actual3.getComments().get(8).getMessage(), is("Hi!!!"));
            assertThat(actual3.getComments().get(8).getId(), is("600000000000003_9"));
            assertThat(actual3.getComments().get(8).getFrom().getId(), is("1000000000000006"));
            assertThat(actual3.getComments().get(8).getFrom().getName(), is("From Name6"));
            assertThat(actual3.getComments().get(8).getCreatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&since=1375245266&__paging_token=600000000000003_16&__previous=1"));
            assertThat(actual3.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&until=1375243085&__paging_token=600000000000003_1"));
            assertThat(actual3.getUnseen(), is(0));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/message/inbox_unread.json");
            InboxResponseList<Message> actuals = facebook.getInbox("1234567890123456", new Reading().fields("unread").limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/inbox")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUnread(), is(1));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            Message actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("600000000000005"));
            assertThat(actual5.getUnread(), is(5));
            assertThat(actual5.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:53:23+0000")));
        }
    }

    public static class getOutbox extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/message/outbox.json");
            ResponseList<Message> actuals = facebook.getOutbox();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/outbox")));

            assertThat(actuals.size(), is(3));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getFrom().getId(), is("1000000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getTo().size(), is(2));
            assertThat(actual1.getTo().get(0).getId(), is("1000000000000001"));
            assertThat(actual1.getTo().get(0).getName(), is("From Name1"));
            assertThat(actual1.getTo().get(1).getId(), is("1234567890123456"));
            assertThat(actual1.getTo().get(1).getName(), is("My Name"));
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().size(), is(2));
            assertThat(actual1.getComments().get(0).getMessage(), is("Hi"));
            assertThat(actual1.getComments().get(0).getId(), is("500000000000001_1"));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("1000000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("From Name1"));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-31T13:53:46+0000")));
            assertThat(actual1.getComments().get(1).getMessage(), is("How are you?"));
            assertThat(actual1.getComments().get(1).getId(), is("500000000000001_2"));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("My Name"));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&since=1376384578&__paging_token=600000000000001_51&__previous=1"));
            assertThat(actual1.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&until=1359640426&__paging_token=600000000000001_27"));
            assertThat(actual1.getUnseen(), is(0));
            assertThat(actual1.getUnread(), is(0));
            Message actual2 = actuals.get(1);
            assertThat(actual2.getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getFrom().getName(), is("From Name2"));
            assertThat(actual2.getTo().size(), is(2));
            assertThat(actual2.getTo().get(0).getId(), is("1000000000000002"));
            assertThat(actual2.getTo().get(0).getName(), is("From Name2"));
            assertThat(actual2.getTo().get(1).getId(), is("1234567890123456"));
            assertThat(actual2.getTo().get(1).getName(), is("My Name"));
            assertThat(actual2.getId(), is("600000000000002"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-08T02:45:16+0000")));
            assertThat(actual2.getComments().size(), is(3));
            assertThat(actual2.getComments().get(0).getMessage(), is("m(_ _)m"));
            assertThat(actual2.getComments().get(0).getId(), is("600000000000002_1"));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:23:47+0000")));
            assertThat(actual2.getComments().get(2).getMessage(), is("Thanks! (^o^)"));
            assertThat(actual2.getComments().get(2).getId(), is("600000000000002_3"));
            assertThat(actual2.getComments().get(2).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(2).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:35:52+0000")));
            assertThat(actual2.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&since=1375929916&__paging_token=600000000000002_8&__previous=1"));
            assertThat(actual2.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&until=1375925027&__paging_token=600000000000002_1"));
            assertThat(actual2.getUnseen(), is(1));
            assertThat(actual2.getUnread(), is(2));
            Message actual3 = actuals.get(2);
            assertThat(actual3.getFrom().getId(), is("1000000000000003"));
            assertThat(actual3.getFrom().getName(), is("From Name3"));
            assertThat(actual3.getTo().size(), is(5));
            assertThat(actual3.getTo().get(0).getId(), is("1000000000000005"));
            assertThat(actual3.getTo().get(0).getName(), is("From Name5"));
            assertThat(actual3.getTo().get(4).getId(), is("1234567890123456"));
            assertThat(actual3.getTo().get(4).getName(), is("My Name"));
            assertThat(actual3.getId(), is("600000000000003"));
            assertThat(actual3.getUnread(), is(0));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().size(), is(9));
            assertThat(actual3.getComments().get(0).getMessage(), is("From Name3 named the conversation: \"hogehoge\"."));
            assertThat(actual3.getComments().get(0).getId(), is("600000000000003_1"));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("1000000000000003"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("From Name3"));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-31T03:58:05+0000")));
            assertThat(actual3.getComments().get(8).getMessage(), is("Hi!!!"));
            assertThat(actual3.getComments().get(8).getId(), is("600000000000003_9"));
            assertThat(actual3.getComments().get(8).getFrom().getId(), is("1000000000000006"));
            assertThat(actual3.getComments().get(8).getFrom().getName(), is("From Name6"));
            assertThat(actual3.getComments().get(8).getCreatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&since=1375245266&__paging_token=600000000000003_16&__previous=1"));
            assertThat(actual3.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&until=1375243085&__paging_token=600000000000003_1"));
            assertThat(actual3.getUnseen(), is(0));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/message/outbox_unread.json");
            ResponseList<Message> actuals = facebook.getOutbox(new Reading().fields("unread").limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/outbox")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUnread(), is(5));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            Message actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("600000000000005"));
            assertThat(actual5.getUnread(), is(1));
            assertThat(actual5.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:53:23+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/message/outbox.json");
            ResponseList<Message> actuals = facebook.getOutbox("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/outbox")));

            assertThat(actuals.size(), is(3));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getFrom().getId(), is("1000000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getTo().size(), is(2));
            assertThat(actual1.getTo().get(0).getId(), is("1000000000000001"));
            assertThat(actual1.getTo().get(0).getName(), is("From Name1"));
            assertThat(actual1.getTo().get(1).getId(), is("1234567890123456"));
            assertThat(actual1.getTo().get(1).getName(), is("My Name"));
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().size(), is(2));
            assertThat(actual1.getComments().get(0).getMessage(), is("Hi"));
            assertThat(actual1.getComments().get(0).getId(), is("500000000000001_1"));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("1000000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("From Name1"));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-31T13:53:46+0000")));
            assertThat(actual1.getComments().get(1).getMessage(), is("How are you?"));
            assertThat(actual1.getComments().get(1).getId(), is("500000000000001_2"));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("My Name"));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            assertThat(actual1.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&since=1376384578&__paging_token=600000000000001_51&__previous=1"));
            assertThat(actual1.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000001/comments?access_token=access_token&limit=25&until=1359640426&__paging_token=600000000000001_27"));
            assertThat(actual1.getUnseen(), is(0));
            assertThat(actual1.getUnread(), is(0));
            Message actual2 = actuals.get(1);
            assertThat(actual2.getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getFrom().getName(), is("From Name2"));
            assertThat(actual2.getTo().size(), is(2));
            assertThat(actual2.getTo().get(0).getId(), is("1000000000000002"));
            assertThat(actual2.getTo().get(0).getName(), is("From Name2"));
            assertThat(actual2.getTo().get(1).getId(), is("1234567890123456"));
            assertThat(actual2.getTo().get(1).getName(), is("My Name"));
            assertThat(actual2.getId(), is("600000000000002"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-08T02:45:16+0000")));
            assertThat(actual2.getComments().size(), is(3));
            assertThat(actual2.getComments().get(0).getMessage(), is("m(_ _)m"));
            assertThat(actual2.getComments().get(0).getId(), is("600000000000002_1"));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:23:47+0000")));
            assertThat(actual2.getComments().get(2).getMessage(), is("Thanks! (^o^)"));
            assertThat(actual2.getComments().get(2).getId(), is("600000000000002_3"));
            assertThat(actual2.getComments().get(2).getFrom().getId(), is("1000000000000002"));
            assertThat(actual2.getComments().get(2).getFrom().getName(), is("From Name2"));
            assertThat(actual2.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-08-08T01:35:52+0000")));
            assertThat(actual2.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&since=1375929916&__paging_token=600000000000002_8&__previous=1"));
            assertThat(actual2.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000002/comments?access_token=access_token&limit=25&until=1375925027&__paging_token=600000000000002_1"));
            assertThat(actual2.getUnseen(), is(1));
            assertThat(actual2.getUnread(), is(2));
            Message actual3 = actuals.get(2);
            assertThat(actual3.getFrom().getId(), is("1000000000000003"));
            assertThat(actual3.getFrom().getName(), is("From Name3"));
            assertThat(actual3.getTo().size(), is(5));
            assertThat(actual3.getTo().get(0).getId(), is("1000000000000005"));
            assertThat(actual3.getTo().get(0).getName(), is("From Name5"));
            assertThat(actual3.getTo().get(4).getId(), is("1234567890123456"));
            assertThat(actual3.getTo().get(4).getName(), is("My Name"));
            assertThat(actual3.getId(), is("600000000000003"));
            assertThat(actual3.getUnread(), is(0));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().size(), is(9));
            assertThat(actual3.getComments().get(0).getMessage(), is("From Name3 named the conversation: \"hogehoge\"."));
            assertThat(actual3.getComments().get(0).getId(), is("600000000000003_1"));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("1000000000000003"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("From Name3"));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-31T03:58:05+0000")));
            assertThat(actual3.getComments().get(8).getMessage(), is("Hi!!!"));
            assertThat(actual3.getComments().get(8).getId(), is("600000000000003_9"));
            assertThat(actual3.getComments().get(8).getFrom().getId(), is("1000000000000006"));
            assertThat(actual3.getComments().get(8).getFrom().getName(), is("From Name6"));
            assertThat(actual3.getComments().get(8).getCreatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual3.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&since=1375245266&__paging_token=600000000000003_16&__previous=1"));
            assertThat(actual3.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&until=1375243085&__paging_token=600000000000003_1"));
            assertThat(actual3.getUnseen(), is(0));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/message/outbox_unread.json");
            ResponseList<Message> actuals = facebook.getOutbox("1234567890123456", new Reading().fields("unread").limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/outbox")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "unread"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Message actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getUnread(), is(5));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-13T09:02:58+0000")));
            Message actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("600000000000005"));
            assertThat(actual5.getUnread(), is(1));
            assertThat(actual5.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:53:23+0000")));
        }
    }

    public static class getMessage extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/message/message.json");
            Message actual = facebook.getMessage("600000000000003");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000003")));

            assertThat(actual.getFrom().getId(), is("1000000000000003"));
            assertThat(actual.getFrom().getName(), is("From Name3"));
            assertThat(actual.getTo().size(), is(5));
            assertThat(actual.getTo().get(0).getId(), is("1000000000000005"));
            assertThat(actual.getTo().get(0).getName(), is("From Name5"));
            assertThat(actual.getTo().get(4).getId(), is("1234567890123456"));
            assertThat(actual.getTo().get(4).getName(), is("My Name"));
            assertThat(actual.getId(), is("600000000000003"));
            assertThat(actual.getMessage(), is("Hello"));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual.getComments().size(), is(9));
            assertThat(actual.getComments().get(0).getMessage(), is("From Name3 named the conversation: \"hogehoge\"."));
            assertThat(actual.getComments().get(0).getId(), is("600000000000003_1"));
            assertThat(actual.getComments().get(0).getFrom().getId(), is("1000000000000003"));
            assertThat(actual.getComments().get(0).getFrom().getName(), is("From Name3"));
            assertThat(actual.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-31T03:58:05+0000")));
            assertThat(actual.getComments().get(8).getMessage(), is("Hi!!!"));
            assertThat(actual.getComments().get(8).getId(), is("600000000000003_9"));
            assertThat(actual.getComments().get(8).getFrom().getId(), is("1000000000000006"));
            assertThat(actual.getComments().get(8).getFrom().getName(), is("From Name6"));
            assertThat(actual.getComments().get(8).getCreatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual.getComments().getPaging().getPrevious().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&since=1375245266&__paging_token=600000000000003_16&__previous=1"));
            assertThat(actual.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/600000000000003/comments?access_token=access_token&limit=25&until=1375243085&__paging_token=600000000000003_1"));
            assertThat(actual.getUnread(), is(2));
            assertThat(actual.getUnseen(), is(1));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/message/message_index.json");
            Message actual = facebook.getMessage("214191725403815", new Reading().fields("from").fields("message"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/214191725403815")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "from,message"));

            assertThat(actual.getFrom().getId(), is("1000000000000003"));
            assertThat(actual.getFrom().getName(), is("From Name3"));
            assertThat(actual.getTo().size(), is(0));
            assertThat(actual.getId(), is("600000000000003"));
            assertThat(actual.getMessage(), is("Hello"));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-07-31T04:34:26+0000")));
            assertThat(actual.getComments().size(), is(0));
            assertThat(actual.getUnread(), is(nullValue()));
            assertThat(actual.getUnseen(), is(nullValue()));
        }
    }

}
