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

/**
 * 
 */
package facebook4j.internal.json;

import facebook4j.Category;
import facebook4j.Event;
import facebook4j.EventPrivacyType;
import facebook4j.FacebookException;
import facebook4j.ResponseList;
import facebook4j.Venue;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.URI;
import java.util.Date;
import java.util.TimeZone;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class EventJSONImpl extends FacebookResponseImpl implements Event, java.io.Serializable {
    private static final long serialVersionUID = 6176123276059683967L;

    private String id;
    private Category owner;
    private String name;
    private String description;
    private Boolean isDateOnly;
    private Date startTime;
    private Date endTime;
    private String location;
    private Venue venue;
    private EventPrivacyType privacy;
    private Date updatedTime;
    private String rsvpStatus;
    private URI ticketURI;
    private TimeZone timezone;
    
    /*package*/EventJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/EventJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("owner")) {
                JSONObject ownerJSONObject = json.getJSONObject("owner");
                owner = new CategoryJSONImpl(ownerJSONObject);
            }
            name = getRawString("name", json);
            description = getRawString("description", json);
            isDateOnly = getBoolean("is_date_only", json);
            startTime = getISO8601Datetime("start_time", json);
            endTime = getISO8601Datetime("end_time", json);
            location = getRawString("location", json);
            if (!json.isNull("venue")) {
                JSONObject venueJSONObject = json.getJSONObject("venue");
                venue = new VenueJSONImpl(venueJSONObject);
            }
            privacy = EventPrivacyType.getInstance(getRawString("privacy", json));
            updatedTime = getISO8601Datetime("updated_time" ,json);
            rsvpStatus = getRawString("rsvp_status", json);
            ticketURI = getURI("ticket_uri", json);
            timezone = getTimeZone("timezone", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    public String getId() {
        return id;
    }

    public Category getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isDateOnly() {
        return isDateOnly;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public Venue getVenue() {
        return venue;
    }

    public EventPrivacyType getPrivacy() {
        return privacy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public String getRsvpStatus() {
        return rsvpStatus;
    }

    public URI getTicketURI() {
        return ticketURI;
    }

    public TimeZone getTimezone() {
        return timezone;
    }

    /*package*/
    static ResponseList<Event> createEventList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Event> events = new ResponseListImpl<Event>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject eventJSONObject = list.getJSONObject(i);
                Event event = new EventJSONImpl(eventJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(event, eventJSONObject);
                }
                events.add(event);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(events, list);
            }
            return events;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventJSONImpl other = (EventJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EventJSONImpl{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isDateOnly=" + isDateOnly +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", location='" + location + '\'' +
                ", venue=" + venue +
                ", privacy=" + privacy +
                ", updatedTime=" + updatedTime +
                ", rsvpStatus='" + rsvpStatus + '\'' +
                ", ticketURI=" + ticketURI +
                ", timezone=" + timezone +
                '}';
    }
}
