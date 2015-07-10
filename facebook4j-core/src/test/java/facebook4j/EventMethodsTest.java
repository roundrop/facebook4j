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

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class EventMethodsTest {

    public static class getEvents extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/event/attending.json");
            ResponseList<Event> actuals = facebook.getEvents();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/events")));

            assertThat(actuals.size(), is(1));
            Event actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("170000000000001"));
            assertThat(actual1.getTimezone(), is(TimeZone.getTimeZone("Asia/Tokyo")));
            assertThat(actual1.getRsvpStatus(), is("attending"));
            assertThat(actual1.getLocation(), is("Jazz Festival"));
            assertThat(actual1.getEndTime(), is(iso8601DateOf("2013-08-04T20:40:00+0900")));
            assertThat(actual1.getName(), is("'13 Jazz Festival YOKOHAMA"));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-08-04T11:15:00+0900")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/event/name_status.json");
            ResponseList<Event> actuals = facebook.getEvents(new Reading().fields("name").fields("rsvp_status"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/events")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,rsvp_status"));

            assertThat(actuals.size(), is(1));
            Event actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("170000000000001"));
            assertThat(actual1.getTimezone(), is(nullValue()));
            assertThat(actual1.getRsvpStatus(), is("attending"));
            assertThat(actual1.getLocation(), is(nullValue()));
            assertThat(actual1.getEndTime(), is(nullValue()));
            assertThat(actual1.getName(), is("'13 Jazz Festival YOKOHAMA"));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-08-04T11:15:00+0900")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/event/attending.json");
            ResponseList<Event> actuals = facebook.getEvents("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/events")));

            assertThat(actuals.size(), is(1));
            Event actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("170000000000001"));
            assertThat(actual1.getTimezone(), is(TimeZone.getTimeZone("Asia/Tokyo")));
            assertThat(actual1.getRsvpStatus(), is("attending"));
            assertThat(actual1.getLocation(), is("Jazz Festival"));
            assertThat(actual1.getEndTime(), is(iso8601DateOf("2013-08-04T20:40:00+0900")));
            assertThat(actual1.getName(), is("'13 Jazz Festival YOKOHAMA"));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-08-04T11:15:00+0900")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/event/name_status.json");
            ResponseList<Event> actuals = facebook.getEvents("1234567890123456", new Reading().fields("name").fields("rsvp_status"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/events")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,rsvp_status"));

            assertThat(actuals.size(), is(1));
            Event actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("170000000000001"));
            assertThat(actual1.getTimezone(), is(nullValue()));
            assertThat(actual1.getRsvpStatus(), is("attending"));
            assertThat(actual1.getLocation(), is(nullValue()));
            assertThat(actual1.getEndTime(), is(nullValue()));
            assertThat(actual1.getName(), is("'13 Jazz Festival YOKOHAMA"));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-08-04T11:15:00+0900")));
        }

        @Test
        public void group() throws Exception {
            facebook.setMockJSON("mock_json/event/group.json");
            String groupId = "547321978660093";
            ResponseList<Event> actuals = facebook.getEvents(groupId);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/547321978660093/events")));

            assertThat(actuals.size(), is(2));
            Event actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("344025155729339"));
            assertThat(actual1.getTimezone(), is(TimeZone.getTimeZone("Asia/Tokyo")));
            assertThat(actual1.getLocation(), is("Oracle Aoyama Center"));
            assertThat(actual1.getEndTime(), is(iso8601DateOf("2013-10-19T22:00:00+0900")));
            assertThat(actual1.getName(), is("Java"));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-10-19T10:00:00+0900")));
            Event actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("352961404835185"));
            assertThat(actual2.getTimezone(), is(TimeZone.getTimeZone("America/Los_Angeles")));
            assertThat(actual2.getLocation(), is("4101 Judah Street San Francisco"));
            assertThat(actual2.getEndTime(), is(iso8601DateOf("2013-09-26T22:00:00-0700")));
            assertThat(actual2.getName(), is("Crab Party"));
            assertThat(actual2.getStartTime(), is(iso8601DateOf("2013-09-26T19:00:00-0700")));
        }

        @Test
        public void page_allfields() throws Exception {
            facebook.setMockJSON("mock_json/event/page.json");
            ResponseList<Event> events = facebook.getEvents("137246726435626", new Reading().fields("ticket_uri,cover,is_date_only,owner,parent_group,privacy,updated_time,venue,description,end_time,id,location,name,start_time,timezone"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/events")));

            assertThat(events.size(), is(1));

            Event event = events.get(0);
            assertThat(event.getDescription(), is("Test Event Details"));
            assertThat(event.getEndTime(), is(iso8601DateOf("2013-06-30T22:00:00+0900")));
            assertThat(event.getId(), is("552962794746679"));
            assertThat(event.isDateOnly(), is(false));
            assertThat(event.getLocation(), is("Yokohama-shi"));
            assertThat(event.getName(), is("Test Event"));
            assertThat(event.getOwner().getCategory(), is("Software"));
            assertThat(event.getOwner().getId(), is("137246726435626"));
            assertThat(event.getOwner().getName(), is("F4J"));
            assertThat(event.getPrivacy(), is(EventPrivacyType.OPEN));
            assertThat(event.getStartTime(), is(iso8601DateOf("2013-06-30T19:00:00+0900")));
            assertThat(event.getTicketURI().toString(), is("http://www.facebook.com/ajax/events/ticket.php?event_id=552962794746679&action_source=12"));
            assertThat(event.getTimezone(), is(TimeZone.getTimeZone("Asia/Tokyo")));
            assertThat(event.getUpdatedTime(), is(iso8601DateOf("2013-06-17T11:15:39+0000")));
            assertThat(event.getVenue().getId(), is("191903894154620"));
            assertThat(event.getVenue().getLatitude(), is(35.45));
            assertThat(event.getVenue().getLongitude(), is(139.633));
            assertThat(event.getVenue().getStreet(), is(""));
            assertThat(event.getVenue().getZip(), is(""));

            assertThat(events.getPaging().getNext().toString(), is("https://graph.facebook.com/137246726435626/events?fields=ticket_uri,cover,is_date_only,owner,parent_group,privacy,updated_time,venue,description,end_time,id,location,name,start_time,timezone&access_token=access_token&limit=5000&until=1372586400&__paging_token=552962794746679"));
            assertThat(events.getPaging().getPrevious().toString(), is("https://graph.facebook.com/137246726435626/events?fields=ticket_uri,cover,is_date_only,owner,parent_group,privacy,updated_time,venue,description,end_time,id,location,name,start_time,timezone&access_token=access_token&limit=5000&since=1372586400&__paging_token=552962794746679&__previous=1"));
        }
    }

    public static class createEvent extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            Calendar startTime = createCal(2012, 9, 30, 12, 34, 56, TimeZone.getTimeZone("UTC"));
            EventUpdate eventUpdate = new EventUpdate("Test Event", startTime);
            String eventId = facebook.createEvent(eventUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/events")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("name", "Test Event"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("start_time", "2012-09-30T12:34:56+0000"));

            assertThat(eventId, is("1234567890123456"));
        }

        @Test
        public void me_page() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            Calendar startTime = createCal(2013, 7, 1, 12, 0, 0, TimeZone.getTimeZone("UTC"));
            Calendar endTime = createCal(2013, 7, 1, 13, 0, 0, TimeZone.getTimeZone("UTC"));
            EventUpdate eventUpdate = new EventUpdate("Test Event", startTime)
                                        .endTime(endTime)
                                        .description("Test description")
                                        .location("Gran Tokyo South Tower")
                                        .locationId("154470644580235")
                                        .ticketURI(new URI("http://facebook4j.org"))
                                        .noFeedStory(true);
            String eventId = facebook.createEvent(eventUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/events")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("name", "Test Event"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("start_time", "2013-07-01T12:00:00+0000"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("end_time", "2013-07-01T13:00:00+0000"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "Test description"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("location", "Gran Tokyo South Tower"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("location_id", "154470644580235"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("ticket_uri", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("no_feed_story", "true"));

            assertThat(eventId, is("1234567890123456"));
        }
    }

    public static class editEvent extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            Calendar endTime = createCal(2013, 7, 1, 15, 0, 0, TimeZone.getTimeZone("UTC"));
            EventUpdate eventUpdate = new EventUpdate()
                                        .endTime(endTime);
            boolean result = facebook.editEvent("138661276338112", eventUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/138661276338112")));
            assertThat(result, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            Calendar endTime = createCal(2013, 7, 1, 15, 0, 0, TimeZone.getTimeZone("UTC"));
            EventUpdate eventUpdate = new EventUpdate()
                                        .endTime(endTime);
            boolean result = facebook.editEvent("138661276338112", eventUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/138661276338112")));
            assertThat(result, is(true));
        }
    }

    public static class deleteEvent extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean result = facebook.deleteEvent("138661276338112");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/138661276338112")));
            assertThat(result, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean result = facebook.deleteEvent("138661276338112");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/138661276338112")));
            assertThat(result, is(true));
        }
    }

    public static class getEvent extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/event/facebook.json");
            Event actual = facebook.getEvent("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435")));

            assertThat(actual.getId(), is("331218348435"));
            assertThat(actual.getLocation(), is("The Phoenix"));
            assertThat(actual.getEndTime(), is(iso8601DateWithoutTzOf("2010-03-14T17:30:00")));
            assertThat(actual.getDescription(), is("Join the Facebook team and local developers for a deep dive into the latest and most exciting ways developers are building with Facebook technologies.  \n\nCome to learn, stay to make friends!\n\nTentative Agenda:\n2:00 - 2:30 PM - Registration\n2:30 - 3:30 PM - Learn the latest from Facebook and local developers\n3:30 - 5:30 PM - Drink with friends!  Stay and mingle with your developer community.\n\n*Come early!  Drink tickets and t-shirts provided to the first 300 attendees.  Cash bar provided for all attendees.\n\nTopics & Speakers:\n--Multi-Platform Social Games (Gareth Davis, Facebook) \n--Increasing Mobile Engagement with Facebook Connect (Josh Williams, Gowalla)\n--Facebook Integration with Seesmic (or How to Build Community Using Octopus Balls...) (John Yamasaki, Seesmic)\n--Going multi-platform: the brave new world beyond facebook.com (Sebastien de Halleux, Playfish / EA Interactive)\n--Socially Connected Exploding Gems Everywhere...Excellent! (Jon David, PopCap Games)\n\n* Emceed by Austin local: whurley, Chaotic Moon Studios\n* All are welcome to attend, no badge is required.\n* If you can't make it in person, you can join the live stream, beginning at 2:00 PM CST, here: http://ustream.tv/fbplatform  \n\n***DAYLIGHT SAVINGS STARTS SUNDAY AT 2 AM, PLEASE ADJUST YOUR CLOCKS ACCORDINGLY***"));
            assertThat(actual.getName(), is("Facebook Developer Garage Austin - SXSW Edition"));
            assertThat(actual.getPrivacy(), is(EventPrivacyType.OPEN));
            assertThat(actual.getOwner().getId(), is("2503747"));
            assertThat(actual.getOwner().getName(), is("Julia Lam"));
            assertThat(actual.getStartTime(), is(iso8601DateWithoutTzOf("2010-03-14T14:00:00")));
            assertThat(actual.isDateOnly(), is(false));
            assertThat(actual.getVenue().getStreet(), is("409 Colorado St."));
            assertThat(actual.getVenue().getState(), is("Texas"));
            assertThat(actual.getVenue().getLongitude(), is(-97.7428));
            assertThat(actual.getVenue().getLatitude(), is(30.2669));
            assertThat(actual.getVenue().getCountry(), is("United States"));
            assertThat(actual.getVenue().getCity(), is("Austin"));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2010-04-13T15:29:40+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/event/facebook_name.json");
            Event actual = facebook.getEvent("331218348435", new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(actual.getId(), is("331218348435"));
            assertThat(actual.getLocation(), is(nullValue()));
            assertThat(actual.getEndTime(), is(nullValue()));
            assertThat(actual.getDescription(), is(nullValue()));
            assertThat(actual.getName(), is("Facebook Developer Garage Austin - SXSW Edition"));
            assertThat(actual.getPrivacy(), is(nullValue()));
            assertThat(actual.getOwner(), is(nullValue()));
            assertThat(actual.getStartTime(), is(iso8601DateWithoutTzOf("2010-03-14T14:00:00")));
            assertThat(actual.isDateOnly(), is(nullValue()));
            assertThat(actual.getVenue(), is(nullValue()));
            assertThat(actual.getUpdatedTime(), is(nullValue()));
        }

        @Test
        public void withoutTime() throws Exception {
            facebook.setMockJSON("mock_json/event/date_without_time.json");
            Event actual = facebook.getEvent("495480000534584");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/495480000534584")));

            assertThat(actual.getId(), is("495480000534584"));
            assertThat(actual.getTimezone(), is(TimeZone.getTimeZone("Europe/Prague")));
            assertThat(actual.getRsvpStatus(), is("attending"));
            assertThat(actual.getName(), is("TestEvent"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Prague"));
            assertThat(actual.getStartTime(), is(sdf.parse("2013-07-22")));
        }
    }

    public static class postEventLink extends MockFacebookTestBase {
        @Test
        public void withoutMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postEventLink("1234567890123456", new URL("http://facebook4j.org"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postEventLink("1234567890123456", new URL("http://facebook4j.org"), "unit test.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "unit test."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class postEventStatusMessage extends MockFacebookTestBase {
        @Test
        public void status() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postEventStatusMessage("1234567890123456", "unit test.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "unit test."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class getRSVPStatusAsNoreply extends MockFacebookTestBase {
        @Test
        public void list() throws Exception {
            facebook.setMockJSON("mock_json/event/noreply_list.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusAsNoreply("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/noreply")));

            assertThat(actuals.size(), is(208));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1523366066"));
            assertThat(actual1.getRsvpStatus(), is("not_replied"));
            assertThat(actual1.getName(), is("Acelya Dandin"));
            RSVPStatus actual100 = actuals.get(99);
            assertThat(actual100.getId(), is("831954908"));
            assertThat(actual100.getRsvpStatus(), is("not_replied"));
            assertThat(actual100.getName(), is("Gamze Kaya"));
            RSVPStatus actual208 = actuals.get(207);
            assertThat(actual208.getId(), is("542365225"));
            assertThat(actual208.getRsvpStatus(), is("not_replied"));
            assertThat(actual208.getName(), is("Carl Reed"));
        }

        @Test
        public void specificUser() throws Exception {
            facebook.setMockJSON("mock_json/event/noreply_specific.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusAsNoreply("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/noreply/1234567890123456")));

            assertThat(actuals.size(), is(1));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1234567890123456"));
            assertThat(actual1.getRsvpStatus(), is("not_replied"));
            assertThat(actual1.getName(), is("Name Name1"));
        }
    }

    public static class getRSVPStatusAsInvited extends MockFacebookTestBase {
        @Test
        public void list() throws Exception {
            facebook.setMockJSON("mock_json/event/invited_list.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusAsInvited("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/invited")));

            assertThat(actuals.size(), is(2091));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000113813905"));
            assertThat(actual1.getRsvpStatus(), is("attending"));
            assertThat(actual1.getName(), is("Tang Bai Ren"));
            RSVPStatus actual2000 = actuals.get(1999);
            assertThat(actual2000.getId(), is("716001215"));
            assertThat(actual2000.getRsvpStatus(), is("not_replied"));
            assertThat(actual2000.getName(), is("Rachel Chaplin Petersen"));
            RSVPStatus actual2091 = actuals.get(2090);
            assertThat(actual2091.getId(), is("542365225"));
            assertThat(actual2091.getRsvpStatus(), is("not_replied"));
            assertThat(actual2091.getName(), is("Carl Reed"));
        }

        @Test
        public void specificUser() throws Exception {
            facebook.setMockJSON("mock_json/event/noreply_specific.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusAsInvited("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/invited/1234567890123456")));

            assertThat(actuals.size(), is(1));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1234567890123456"));
            assertThat(actual1.getRsvpStatus(), is("not_replied"));
            assertThat(actual1.getName(), is("Name Name1"));
        }
    }

    public static class getRSVPStatusInAttending extends MockFacebookTestBase {
        @Test
        public void list() throws Exception {
            facebook.setMockJSON("mock_json/event/attending_list.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusInAttending("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/attending")));

            assertThat(actuals.size(), is(1392));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000113813905"));
            assertThat(actual1.getRsvpStatus(), is("attending"));
            assertThat(actual1.getName(), is("Tang Bai Ren"));
            RSVPStatus actual1000 = actuals.get(999);
            assertThat(actual1000.getId(), is("100001288224050"));
            assertThat(actual1000.getRsvpStatus(), is("attending"));
            assertThat(actual1000.getName(), is("Skäggiga Damen"));
            RSVPStatus actual1392 = actuals.get(1391);
            assertThat(actual1392.getId(), is("1189355583"));
            assertThat(actual1392.getRsvpStatus(), is("attending"));
            assertThat(actual1392.getName(), is("Diana Anzaldua"));
        }

        @Test
        public void specificUser() throws Exception {
            facebook.setMockJSON("mock_json/event/attending_specific.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusInAttending("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/attending/1234567890123456")));

            assertThat(actuals.size(), is(1));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1234567890123456"));
            assertThat(actual1.getRsvpStatus(), is("attending"));
            assertThat(actual1.getName(), is("Name Name1"));
        }
    }

    public static class getRSVPStatusInMaybe extends MockFacebookTestBase {
        @Test
        public void list() throws Exception {
            facebook.setMockJSON("mock_json/event/maybe_list.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusInMaybe("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/maybe")));

            assertThat(actuals.size(), is(274));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("622083982"));
            assertThat(actual1.getRsvpStatus(), is("unsure"));
            assertThat(actual1.getName(), is("Amanda O'Brien"));
            RSVPStatus actual274 = actuals.get(273);
            assertThat(actual274.getId(), is("1457982049"));
            assertThat(actual274.getRsvpStatus(), is("unsure"));
            assertThat(actual274.getName(), is("Sean Clark"));
        }

        @Test
        public void specificUser() throws Exception {
            facebook.setMockJSON("mock_json/event/maybe_specific.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusInMaybe("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/maybe/1234567890123456")));

            assertThat(actuals.size(), is(1));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1234567890123456"));
            assertThat(actual1.getRsvpStatus(), is("unsure"));
            assertThat(actual1.getName(), is("Name Name1"));
        }
    }

    public static class getRSVPStatusInDeclined extends MockFacebookTestBase {
        @Test
        public void list() throws Exception {
            facebook.setMockJSON("mock_json/event/declined_list.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusInDeclined("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/declined")));

            assertThat(actuals.size(), is(220));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1059364896"));
            assertThat(actual1.getRsvpStatus(), is("declined"));
            assertThat(actual1.getName(), is("Vishal Sood"));
            RSVPStatus actual220 = actuals.get(219);
            assertThat(actual220.getId(), is("1485512422"));
            assertThat(actual220.getRsvpStatus(), is("declined"));
            assertThat(actual220.getName(), is("Ayi Malcolm Adigbo"));
        }

        @Test
        public void specificUser() throws Exception {
            facebook.setMockJSON("mock_json/event/declined_specific.json");
            ResponseList<RSVPStatus> actuals = facebook.getRSVPStatusInDeclined("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/declined/1234567890123456")));

            assertThat(actuals.size(), is(1));
            RSVPStatus actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1234567890123456"));
            assertThat(actual1.getRsvpStatus(), is("declined"));
            assertThat(actual1.getName(), is("Name Name1"));
        }
    }

    public static class inviteToEvent extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.inviteToEvent("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/invited/1234567890123456")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.inviteToEvent("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/invited/1234567890123456")));

            assertThat(actual, is(true));
        }

        @Test
        public void ids() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.inviteToEvent("331218348435", new String[]{"1234567890123456", "1234567890123457", "1234567890123458"});
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/invited")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("users", "1234567890123456,1234567890123457,1234567890123458"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void ids_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.inviteToEvent("331218348435", new String[]{"1234567890123456", "1234567890123457", "1234567890123458"});
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/invited")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("users", "1234567890123456,1234567890123457,1234567890123458"));

            assertThat(actual, is(true));
        }
    }

    public static class uninviteFromEvent extends MockFacebookTestBase {
        @Test
        public void uninvite() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.uninviteFromEvent("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/invited/1234567890123456")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void uninvite_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.uninviteFromEvent("331218348435", "1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/invited/1234567890123456")));

            assertThat(actual, is(true));
        }
    }

    public static class rsvpEvent extends MockFacebookTestBase {
        @Test
        public void asAttending() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.rsvpEventAsAttending("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/attending")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void asAttending_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.rsvpEventAsAttending("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/attending")));

            assertThat(actual, is(true));
        }

        @Test
        public void asMaybe() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.rsvpEventAsMaybe("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/maybe")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void asMaybe_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.rsvpEventAsMaybe("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/maybe")));

            assertThat(actual, is(true));
        }

        @Test
        public void asDeclined() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.rsvpEventAsDeclined("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/declined")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void asDeclined_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.rsvpEventAsDeclined("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/declined")));

            assertThat(actual, is(true));
        }
    }

    public static class getEventPictureURL extends MockFacebookTestBase {
        @Test
        public void defaultSize() throws Exception {
            facebook.setMockJSON("mock_json/picture.json");
            URL actual = facebook.getEventPictureURL("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/picture")));

            assertThat(actual.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/211200_137246726435626_559668710_q.jpg"));
        }
        @Test
        public void specifiedSize() throws Exception {
            facebook.setMockJSON("mock_json/picture.json");
            URL actual = facebook.getEventPictureURL("331218348435", PictureSize.large);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/picture")));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "large"));

            assertThat(actual.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/211200_137246726435626_559668710_q.jpg"));
        }
    }

    public static class updateEventPicture extends MockFacebookTestBase {
        @Test
        public void update() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            File file = new File("src/test/resources/500x500.png");
            Media source = new Media(file);
            boolean actual = facebook.updateEventPicture("331218348435", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/picture")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void update_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            File file = new File("src/test/resources/500x500.png");
            Media source = new Media(file);
            boolean actual = facebook.updateEventPicture("331218348435", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/picture")));

            assertThat(actual, is(true));
        }
    }

    public static class deleteEventPicture extends MockFacebookTestBase {
        @Test
        public void delete() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteEventPicture("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/picture")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void delete_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteEventPicture("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/331218348435/picture")));

            assertThat(actual, is(true));
        }
    }

    public static class getEventPhotos extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/event/photos.json");
            ResponseList<Photo> actuals = facebook.getEventPhotos("331218348435");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/photos")));

            assertThat(actuals.size(), is(23));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-h-a.akamaihd.net/hphotos-ak-prn1/24508_103019776398301_7078156_s.jpg"));
            assertThat(actual1.getId(), is("103019776398301"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StEh3RhPvjk.gif"));
            assertThat(actual1.getHeight(), is(720));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-prn1/24508_103019776398301_7078156_n.jpg"));
            assertThat(actual1.getWidth(), is(576));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=103019776398301&set=o.331218348435&type=1"));
            assertThat(actual1.getImages().size(), is(8));
            assertThat(actual1.getImages().get(0).getHeight(), is(720));
            assertThat(actual1.getImages().get(0).getWidth(), is(576));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-prn1/24508_103019776398301_7078156_n.jpg"));
            assertThat(actual1.getImages().get(7).getHeight(), is(130));
            assertThat(actual1.getImages().get(7).getWidth(), is(104));
            assertThat(actual1.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-h-a.akamaihd.net/hphotos-ak-prn1/s75x225/24508_103019776398301_7078156_s.jpg"));
            assertThat(actual1.getFrom().getId(), is("100000708376398"));
            assertThat(actual1.getFrom().getName(), is("Shojev Hossan"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2010-03-12T13:15:18+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2010-03-12T13:16:51+0000")));
            Photo actual23 = actuals.get(22);
            assertThat(actual23.getId(), is("103018939731718"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/event/photos_source_limit3.json");
            ResponseList<Photo> actuals = facebook.getEventPhotos("331218348435", new Reading().fields("source").limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/photos")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "source"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("103019776398301"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-prn1/24508_103019776398301_7078156_n.jpg"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2010-03-12T13:15:18+0000")));
            Photo actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("103019779731634"));
            assertThat(actual3.getSource().toString(), is("https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-ash3/24508_103019779731634_3930425_n.jpg"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2010-03-12T13:15:18+0000")));
        }
    }

    public static class postEventPhoto extends MockFacebookTestBase {
        @Test
        public void withoutMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            File file = new File("src/test/resources/500x500.png");
            Media source = new Media(file);
            String actual = facebook.postEventPhoto("331218348435", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/photos")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            File file = new File("src/test/resources/500x500.png");
            Media source = new Media(file);
            String actual = facebook.postEventPhoto("331218348435", source, "unit test!");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "unit test!"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class getEventVideos extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/event/videos.json");
            ResponseList<Video> actuals = facebook.getEventVideos("MINI.Japan");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/MINI.Japan/videos")));

            assertThat(actuals.size(), is(1));
            Video actual1 = actuals.get(0);
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-frc1/245886_135916643167703_135916229834411_25730_1226_t.jpg"));
            assertThat(actual1.getId(), is("135916229834411"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/DggDhA4z4tO.gif"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/cfs-ak-ash3/v/232046/949/135916229834411_61901.mp4?oh=af1c6e5501df0bfc47b315abd538809c&oe=52093332&__gda__=1376358945_4b75778db16484f6ab5d2d938a2eb5a9"));
            assertThat(actual1.getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=135916229834411\" width=\"720\" height=\"406\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getDescription(), is("\u201dMINI DRIVING EXCITEMENT\u201d 新作TVCM, facebook限定で先行公開:)"));
            assertThat(actual1.getName(), is("\"MINI DRIVING EXCITEMENT\" TVCM"));
            assertThat(actual1.getFrom().getId(), is("232547976762303"));
            assertThat(actual1.getFrom().getCategory(), is("Cars"));
            assertThat(actual1.getFrom().getName(), is("MINI Japan"));
            assertThat(actual1.getFormat().size(), is(3));
            assertThat(actual1.getFormat().get(0).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=135916229834411\" width=\"130\" height=\"73\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getFormat().get(0).getFilter(), is("130x130"));
            assertThat(actual1.getFormat().get(0).getHeight(), is(73));
            assertThat(actual1.getFormat().get(0).getWidth(), is(130));
            assertThat(actual1.getFormat().get(0).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-frc1/s130x130/245886_135916643167703_135916229834411_25730_1226_t.jpg"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-08-19T04:09:55+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2011-08-19T04:09:55+0000")));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(0).getMessage(), is("Cool!"));
            assertThat(actual1.getComments().get(0).getId(), is("135916229834411_345557"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Name Mini Name1"));
            assertThat(actual1.getComments().get(0).canRemove(), is(false));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2011-08-19T04:20:30+0000")));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("MTE="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/event/videos_icon.json");
            ResponseList<Video> actuals = facebook.getEventVideos("MINI.Japan", new Reading().fields("icon"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/MINI.Japan/videos")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "icon"));

            assertThat(actuals.size(), is(1));
            Video actual1 = actuals.get(0);
            assertThat(actual1.getPicture(), is(nullValue()));
            assertThat(actual1.getId(), is("135916229834411"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/DggDhA4z4tO.gif"));
            assertThat(actual1.getSource(), is(nullValue()));
            assertThat(actual1.getEmbedHtml(), is(nullValue()));
            assertThat(actual1.getDescription(), is(nullValue()));
            assertThat(actual1.getName(), is(nullValue()));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getFormat().size(), is(0));
            assertThat(actual1.getCreatedTime(), is(nullValue()));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2011-08-19T04:09:55+0000")));
            assertThat(actual1.getComments().size(), is(0));
        }
    }

    public static class postEventVideo extends MockFacebookTestBase {
        @Test
        public void withoutDescription() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            File file = new File("src/test/resources/test.mov");
            Media source = new Media(file);
            String actual = facebook.postEventVideo("331218348435", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/videos")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withDescription() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            File file = new File("src/test/resources/test.mov");
            Media source = new Media(file);
            String actual = facebook.postEventVideo("331218348435", source, "test video title", "test video description");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/331218348435/videos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("title", "test video title"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "test video description"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

}
