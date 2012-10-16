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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.util.z_F4JInternalStringUtil;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class EventUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 375802784481895434L;

    private final String name;
    private final Calendar startTime;
    private Calendar endTime;
    private String description;
    private String location;
    private String locationId;
    private EventPrivacyType privacyType;

    public EventUpdate(String name, Calendar startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public EventUpdate(String name, Calendar startTime, Calendar endTime,
            String description, String location, String locationId,
            EventPrivacyType privacyType) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.location = location;
        this.locationId = locationId;
        this.privacyType = privacyType;
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

    public String getName() {
        return name;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("name", name));
        params.add(new HttpParameter("start_time", z_F4JInternalStringUtil.formatISO8601Datetime(startTime)));
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
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result
                + ((location == null) ? 0 : location.hashCode());
        result = prime * result
                + ((locationId == null) ? 0 : locationId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((privacyType == null) ? 0 : privacyType.hashCode());
        result = prime * result
                + ((startTime == null) ? 0 : startTime.hashCode());
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
        EventUpdate other = (EventUpdate) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (locationId == null) {
            if (other.locationId != null)
                return false;
        } else if (!locationId.equals(other.locationId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (privacyType != other.privacyType)
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EventUpdate [name=" + name + ", startTime=" + startTime
                + ", endTime=" + endTime + ", description=" + description
                + ", location=" + location + ", locationId=" + locationId
                + ", privacyType=" + privacyType + "]";
    }

}
