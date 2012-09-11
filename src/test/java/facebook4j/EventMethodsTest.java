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
import java.util.Date;

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
        EventUpdate eventUpdate1 = new EventUpdate("test event1", new Date());
        String eventId1 = facebook1.createEvent(eventUpdate1);
        Event event1 = facebook1.getEvent(eventId1);
        assertThat(event1, is(notNullValue()));
        assertThat(event1.getId(), is(eventId1));
        assertThat(event1.getName(), is("test event1"));

        EventUpdate eventUpdate2 = new EventUpdate("test event2", new Date());
        String eventId2 = facebook1.createEvent(eventUpdate2);
        Event event2 = facebook1.getEvent(eventId2);
        assertThat(event2, is(notNullValue()));
        assertThat(event2.getId(), is(eventId2));
        assertThat(event2.getName(), is("test event2"));

        ResponseList<Event> events = facebook1.getEvents();
        assertThat(events.size() >= 2, is(true));
    }
    
    @Test
    public void create_edit_delete() throws Exception {
        EventUpdate eventUpdate1 = new EventUpdate("test event1", new Date());
        String eventId1 = facebook1.createEvent(eventUpdate1);
        assertThat(eventId1, is(notNullValue()));
        
        EventUpdate eventUpdate2 = new EventUpdate("test event2", new Date(), tomorrow(new Date()),
                "description", "Gran Tokyo South Tower", "154470644580235", "SECRET");
        String eventId2 = facebook1.createEvent(id1.getId(), eventUpdate2);
        assertThat(eventId2, is(notNullValue()));

        // Permissions error(#200) why...?
//        eventUpdate1 = new EventUpdate("test event1 edit", new Date());
//        boolean editResult = facebook1.editEvent(eventId1, eventUpdate1);
//        assertThat(editResult, is(true));
        
        boolean deleteResult = facebook1.deleteEvent(eventId1);
        assertThat(deleteResult, is(true));
        Event event = facebook1.getEvent(eventId1);
        assertThat(event, is(nullValue()));
    }

    @Test
    public void noreply() throws Exception {
        String eventId = "331218348435";
        ResponseList<RSVPStatus> noreply = real.getRSVPStatusAsNoreply(eventId);
        for (RSVPStatus rsvpStatus : noreply) {
            assertThat(rsvpStatus.getRsvpStatus(), is("not_replied"));
        }
    }

    private Date tomorrow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }
}
