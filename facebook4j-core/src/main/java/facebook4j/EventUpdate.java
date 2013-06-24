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

import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class EventUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -6106165246149864606L;

    private String name;
    private Calendar startTime;
    private Calendar endTime;
    private String description;
    private String location;
    private String locationId;
    private EventPrivacyType privacyType;
    private URI ticketURI;
    private Boolean noFeedStory;

    public EventUpdate() {
    }

    public EventUpdate(String name, Calendar startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventUpdate name(String name) {
        setName(name);
        return this;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public EventUpdate startTime(Calendar startTime) {
        setStartTime(startTime);
        return this;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
    
    public EventUpdate endTime(Calendar endTime) {
        setEndTime(endTime);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public EventUpdate description(String description) {
        setDescription(description);
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public EventUpdate location(String location) {
        setLocation(location);
        return this;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    
    public EventUpdate locationId(String locationId) {
        setLocationId(locationId);
        return this;
    }

    public EventPrivacyType getPrivacyType() {
        return privacyType;
    }

    public void setPrivacyType(EventPrivacyType privacyType) {
        this.privacyType = privacyType;
    }
    
    public EventUpdate privacyType(EventPrivacyType privacyType) {
        setPrivacyType(privacyType);
        return this;
    }

    public URI getTicketURI() {
        return ticketURI;
    }

    public void setTicketURI(URI ticketURI) {
        this.ticketURI = ticketURI;
    }
    public void setTicketURI(String ticketURI) throws URISyntaxException {
        this.ticketURI = new URI(ticketURI);
    }

    public EventUpdate ticketURI(URI ticketURI) {
        setTicketURI(ticketURI);
        return this;
    }
    public EventUpdate ticketURI(String ticketURI) throws URISyntaxException {
        setTicketURI(ticketURI);
        return this;
    }

    public Boolean getNoFeedStory() {
        return noFeedStory;
    }

    public void setNoFeedStory(Boolean noFeedStory) {
        this.noFeedStory = noFeedStory;
    }

    public EventUpdate noFeedStory(Boolean noFeedStory) {
        setNoFeedStory(noFeedStory);
        return this;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        if (name != null) {
            params.add(new HttpParameter("name", name));
        }
        if (startTime != null) {
            params.add(new HttpParameter("start_time", z_F4JInternalStringUtil.formatISO8601Datetime(startTime)));
        }
        if (endTime != null) {
            params.add(new HttpParameter("end_time", z_F4JInternalStringUtil.formatISO8601Datetime(endTime)));
        }
        if (description != null) {
            params.add(new HttpParameter("description", description));
        }
        if (location != null) {
            params.add(new HttpParameter("location", location));
        }
        if (locationId != null) {
            params.add(new HttpParameter("location_id", locationId));
        }
        if (privacyType != null) {
            params.add(new HttpParameter("privacy_type", privacyType.toString()));
        }
        if (ticketURI != null) {
            params.add(new HttpParameter("ticket_uri", ticketURI.toString()));
        }
        if (noFeedStory != null) {
            params.add(new HttpParameter("no_feed_story", noFeedStory));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventUpdate)) return false;

        EventUpdate that = (EventUpdate) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (locationId != null ? !locationId.equals(that.locationId) : that.locationId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (noFeedStory != null ? !noFeedStory.equals(that.noFeedStory) : that.noFeedStory != null) return false;
        if (privacyType != that.privacyType) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (ticketURI != null ? !ticketURI.equals(that.ticketURI) : that.ticketURI != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        result = 31 * result + (privacyType != null ? privacyType.hashCode() : 0);
        result = 31 * result + (ticketURI != null ? ticketURI.hashCode() : 0);
        result = 31 * result + (noFeedStory != null ? noFeedStory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventUpdate{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", locationId='" + locationId + '\'' +
                ", privacyType=" + privacyType +
                ", ticketURI=" + ticketURI +
                ", noFeedStory=" + noFeedStory +
                '}';
    }
}
