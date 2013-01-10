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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EventMethodsTest extends FacebookTestBase {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
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
}
