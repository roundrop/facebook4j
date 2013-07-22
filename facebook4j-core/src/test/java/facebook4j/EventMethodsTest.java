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

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class EventMethodsTest {

    public static class getEvents extends MockFacebookTestBase {
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
    }

    public static class getEvent extends MockFacebookTestBase {
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
            assertThat(actual.getStartTime(), is(new SimpleDateFormat("yyyy-MM-dd").parse("2013-07-22")));
        }
    }

/*
    @Test
    public void get() throws Exception {
        Event event = facebook1.getEvent("359439267470540");
        assertThat(event, is(notNullValue()));
        assertThat(event.getId(), is("359439267470540"));
        assertThat(event.getName(), is("test event2"));

        ResponseList<Event> events = facebook1.getEvents();
        assertThat(events.size() > 0, is(true));
    }
    
    @Test
    public void create_edit_delete() throws Exception {
        Calendar start = Calendar.getInstance();
        EventUpdate eventUpdate1 = new EventUpdate("test event1", start);
        String eventId1 = facebook1.createEvent(eventUpdate1);
        assertThat(eventId1, is(notNullValue()));
        
        EventUpdate eventUpdate2 = new EventUpdate("test event2", start, tomorrow(start),
                "description", "Gran Tokyo South Tower", "154470644580235", EventPrivacyType.SECRET);
        String eventId2 = facebook1.createEvent(id1.getId(), eventUpdate2);
        assertThat(eventId2, is(notNullValue()));

        // Permissions error(#200) why...?
//        eventUpdate1 = new EventUpdate("test event1 edit", new Date());
//        boolean editResult = facebook1.editEvent(eventId1, eventUpdate1);
//        assertThat(editResult, is(true));
        
        boolean deleteResult = facebook1.deleteEvent(eventId1);
        assertThat(deleteResult, is(true));
    }
    
    @Test(expected = FacebookException.class)
    public void eventNotFound() throws FacebookException {
        facebook1.getEvent("189792514488723");
    }

    @Test
    public void noreply() throws Exception {
        //TODO
        String eventId = "331218348435";
        ResponseList<RSVPStatus> noreply = real.getRSVPStatusAsNoreply(eventId);
        for (RSVPStatus rsvpStatus : noreply) {
            assertThat(rsvpStatus.getRsvpStatus(), is("not_replied"));
        }
    }

    @Test
    public void invite() throws Exception {
        String eventId = createTmpEvent();
        boolean result = facebookBestFriend1.inviteToEvent(eventId, bestFriend2.getId());
        assertThat(result, is(true));
        deleteTmpEvent(eventId);
    }

    @Test
    public void attending() throws Exception {
        String eventId = createTmpEvent();
        boolean result = facebookBestFriend2.rsvpEventAsAttending(eventId);
        assertThat(result, is(true));
        ResponseList<RSVPStatus> status = facebookBestFriend1.getRSVPStatusInAttending(eventId, bestFriend2.getId());
        assertThat(status.size(), is(1));
        assertThat(status.get(0).getRsvpStatus(), is("attending"));
        deleteTmpEvent(eventId);
    }

    @Test
    public void maybe() throws Exception {
        String eventId = createTmpEvent();
        boolean result = facebookBestFriend2.rsvpEventAsMaybe(eventId);
        assertThat(result, is(true));
        ResponseList<RSVPStatus> status = facebookBestFriend1.getRSVPStatusInMaybe(eventId, bestFriend2.getId());
        assertThat(status.size(), is(1));
        assertThat(status.get(0).getRsvpStatus(), is("unsure"));
        deleteTmpEvent(eventId);
    }

    @Test
    public void declined() throws Exception {
        String eventId = createTmpEvent();
        boolean result = facebookBestFriend2.rsvpEventAsDeclined(eventId);
        assertThat(result, is(true));
        ResponseList<RSVPStatus> status = facebookBestFriend1.getRSVPStatusInDeclined(eventId, bestFriend2.getId());
        assertThat(status.size(), is(1));
        assertThat(status.get(0).getRsvpStatus(), is("declined"));
        deleteTmpEvent(eventId);
    }
    
    private Calendar tomorrow(Calendar cal) {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(cal.getTime());
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        return tomorrow;
    }

    private String createTmpEvent() throws FacebookException {
        Calendar start = Calendar.getInstance();
        EventUpdate eventUpdate = new EventUpdate("tmp event", start);
        String eventId = facebookBestFriend1.createEvent(eventUpdate);
        assertThat(eventId, is(notNullValue()));
        return eventId;
    }

    private void deleteTmpEvent(String eventId) throws FacebookException {
        boolean result = facebookBestFriend1.deleteEvent(eventId);
        assertThat(result, is(true));
    }
*/
}
