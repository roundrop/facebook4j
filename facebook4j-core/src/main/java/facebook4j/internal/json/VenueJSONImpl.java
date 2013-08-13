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

package facebook4j.internal.json;

import facebook4j.Venue;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ class VenueJSONImpl implements Venue, java.io.Serializable {
    private static final long serialVersionUID = -6139700799490380547L;

    private final String id;
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final Double latitude;
    private final Double longitude;
    private final String zip;

    /*package*/VenueJSONImpl(JSONObject json) {
        id = getRawString("id", json);
        street = getRawString("street", json);
        city = getRawString("city", json);
        state = getRawString("state", json);
        country = getRawString("country", json);
        latitude = getDouble("latitude", json);
        longitude = getDouble("longitude", json);
        zip = getRawString("zip", json);
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

    public String getZip() {
        return zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenueJSONImpl)) return false;

        VenueJSONImpl venueJSON = (VenueJSONImpl) o;

        if (id != null ? !id.equals(venueJSON.id) : venueJSON.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VenueJSONImpl{" +
                "id='" + id + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", zip='" + zip + '\'' +
                '}';
    }
}
