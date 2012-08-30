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

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

import java.util.Date;

import facebook4j.Event;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class EventJSONImpl implements Event, java.io.Serializable {
    private static final long serialVersionUID = 6176123276059683967L;

    private String id;
    private IdNameEntity owner;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private String location;
    private Event.Venue venue;
    private String privacy;
    private Date updatedTime;
    private String rsvpStatus;
    
    /*package*/EventJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
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
                owner = new IdNameEntityJSONImpl(ownerJSONObject);
            }
            name = getRawString("name", json);
            description = getRawString("description", json);
            startTime = getDate("start_time", json, "yyyy-MM-dd'T'HH:mm:ss");
            endTime = getDate("end_time", json, "yyyy-MM-dd'T'HH:mm:ss");
            location = getRawString("location", json);
            if (!json.isNull("venue")) {
                JSONObject venueJSONObject = json.getJSONObject("venue");
                venue = new VenueJSONImpl(venueJSONObject);
            }
            privacy = getRawString("privacy", json);
            updatedTime = getFacebookDatetime("updated_time" ,json);
            rsvpStatus = getRawString("rsvp_status", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    public String getId() {
        return id;
    }

    public IdNameEntity getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public String getPrivacy() {
        return privacy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public String getRsvpStatus() {
        return rsvpStatus;
    }

    /*package*/
    static ResponseList<Event> createEventList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            ResponseList<Event> events = new ResponseListImpl<Event>(size, json);
            for (int i = 0; i < size; i++) {
                Event event = new EventJSONImpl(list.getJSONObject(i));
                events.add(event);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(events, json);
            }
            return events;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "EventJSONImpl [id=" + id + ", owner=" + owner + ", name="
                + name + ", description=" + description + ", startTime="
                + startTime + ", endTime=" + endTime + ", location=" + location
                + ", venue=" + venue + ", privacy=" + privacy
                + ", updatedTime=" + updatedTime + ", rsvpStatus=" + rsvpStatus
                + "]";
    }


    private final class VenueJSONImpl implements Venue, java.io.Serializable {
        private static final long serialVersionUID = -8662557267558208918L;

        private final String id;
        private final String street;
        private final String city;
        private final String state;
        private final String country;
        private final Double latitude;
        private final Double longitude;

        /*package*/VenueJSONImpl(JSONObject json) {
            id = getRawString("id", json);
            street = getRawString("street", json);
            city = getRawString("city", json);
            state = getRawString("state", json);
            country = getRawString("country", json);
            latitude = getDouble("latitude", json);
            longitude = getDouble("longitude", json);
        }

        public String getId() {
            return id;
        }

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getCountry() {
            return country;
        }

        public Double getLatitude() {
            return latitude;
        }

        public Double getLongitude() {
            return longitude;
        }
    }
}
