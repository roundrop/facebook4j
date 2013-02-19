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

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;
import facebook4j.FacebookException;
import facebook4j.Place;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PlaceJSONImpl extends FacebookResponseImpl implements Place, java.io.Serializable {
    private static final long serialVersionUID = -4977957628922020910L;

    private String id;
    private String name;
    private Place.Location location;

    /*package*/PlaceJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/PlaceJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            name = getRawString("name", json);
            JSONObject locationJSONObject = json.getJSONObject("location");
            location = new PlaceJSONImpl.LocationJSONImpl(locationJSONObject);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    /*package*/
    static ResponseList<Place> createPlaceList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            ResponseList<Place> places = new ResponseListImpl<Place>(size, json);
            for (int i = 0; i < size; i++) {
                Place place = new PlaceJSONImpl(list.getJSONObject(i));
                places.add(place);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(places, json);
            }
            return places;
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
        PlaceJSONImpl other = (PlaceJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PlaceJSONImpl [id=" + id + ", name=" + name + ", location="
                + location + "]";
    }

    /*package*/ static final class LocationJSONImpl implements Location, java.io.Serializable {
        private static final long serialVersionUID = -9144378506897014902L;
        
        private String street;
        private String city;
        private String state;
        private String country;
        private String zip;
        private Double latitude;
        private Double longitude;

        /*package*/LocationJSONImpl(JSONObject json) throws FacebookException {
            street = getRawString("street", json);
            city = getRawString("city", json);
            state = getRawString("state", json);
            country = getRawString("country", json);
            zip = getRawString("zip", json);
            latitude = getDouble("latitude", json);
            longitude = getDouble("longitude", json);
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
        public String getZip() {
            return zip;
        }
        public Double getLatitude() {
            return latitude;
        }
        public Double getLongitude() {
            return longitude;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((city == null) ? 0 : city.hashCode());
            result = prime * result
                    + ((country == null) ? 0 : country.hashCode());
            result = prime * result
                    + ((latitude == null) ? 0 : latitude.hashCode());
            result = prime * result
                    + ((longitude == null) ? 0 : longitude.hashCode());
            result = prime * result + ((state == null) ? 0 : state.hashCode());
            result = prime * result
                    + ((street == null) ? 0 : street.hashCode());
            result = prime * result + ((zip == null) ? 0 : zip.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            LocationJSONImpl other = (LocationJSONImpl) obj;
            if (city == null) {
                if (other.city != null) {
                    return false;
                }
            } else if (!city.equals(other.city)) {
                return false;
            }
            if (country == null) {
                if (other.country != null) {
                    return false;
                }
            } else if (!country.equals(other.country)) {
                return false;
            }
            if (latitude == null) {
                if (other.latitude != null) {
                    return false;
                }
            } else if (!latitude.equals(other.latitude)) {
                return false;
            }
            if (longitude == null) {
                if (other.longitude != null) {
                    return false;
                }
            } else if (!longitude.equals(other.longitude)) {
                return false;
            }
            if (state == null) {
                if (other.state != null) {
                    return false;
                }
            } else if (!state.equals(other.state)) {
                return false;
            }
            if (street == null) {
                if (other.street != null) {
                    return false;
                }
            } else if (!street.equals(other.street)) {
                return false;
            }
            if (zip == null) {
                if (other.zip != null) {
                    return false;
                }
            } else if (!zip.equals(other.zip)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "LocationJSONImpl [street=" + street + ", city=" + city
                    + ", state=" + state + ", country=" + country + ", zip=" + zip
                    + ", latitude=" + latitude + ", longitude=" + longitude + "]";
        }
    }
}
