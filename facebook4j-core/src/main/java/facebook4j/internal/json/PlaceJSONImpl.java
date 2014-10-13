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

import facebook4j.Category;
import facebook4j.FacebookException;
import facebook4j.Place;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PlaceJSONImpl extends FacebookResponseImpl implements Place, java.io.Serializable {
    private static final long serialVersionUID = 859565077681007052L;

    private String id;
    private String name;
    private List<Category> categories;
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
            
            if (isJSONArray("category_list", json)) {
                JSONArray categoriesJSONArray = json.getJSONArray("category_list");
                categories = new ArrayList<Category>();
                for (int i = 0; i < categoriesJSONArray.length(); i++) {
                    categories.add(new CategoryJSONImpl(categoriesJSONArray.getJSONObject(i)));
                }
            }
            
            if (isJSONObject("location", json)) {
                JSONObject locationJSONObject = json.getJSONObject("location");
                location = new PlaceJSONImpl.LocationJSONImpl(locationJSONObject);
            } else {
                location = new PlaceJSONImpl.LocationJSONImpl(getRawString("location", json));
            }
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

    public List<Category> getCategories() {
        return categories;
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
            final int size = list.length();
            ResponseList<Place> places = new ResponseListImpl<Place>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject placeJSONObject = list.getJSONObject(i);
                Place place = new PlaceJSONImpl(placeJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(place, placeJSONObject);
                }
                places.add(place);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(places, list);
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
        return "PlaceJSONImpl{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", location=" + location +
                '}';
    }

    /*package*/ static final class LocationJSONImpl implements Location, java.io.Serializable {
        private static final long serialVersionUID = 1578695411333203753L;

        private String street;
        private String city;
        private String state;
        private String country;
        private String zip;
        private Double latitude;
        private Double longitude;
        private String text;

        /*package*/LocationJSONImpl(JSONObject json) throws FacebookException {
            street = getRawString("street", json);
            city = getRawString("city", json);
            state = getRawString("state", json);
            country = getRawString("country", json);
            zip = getRawString("zip", json);
            latitude = getDouble("latitude", json);
            longitude = getDouble("longitude", json);
        }
        /*package*/LocationJSONImpl(String text) throws FacebookException {
            this.text = text;
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
        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LocationJSONImpl)) return false;

            LocationJSONImpl that = (LocationJSONImpl) o;

            if (city != null ? !city.equals(that.city) : that.city != null) return false;
            if (country != null ? !country.equals(that.country) : that.country != null) return false;
            if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
            if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
            if (state != null ? !state.equals(that.state) : that.state != null) return false;
            if (street != null ? !street.equals(that.street) : that.street != null) return false;
            if (text != null ? !text.equals(that.text) : that.text != null) return false;
            if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = street != null ? street.hashCode() : 0;
            result = 31 * result + (city != null ? city.hashCode() : 0);
            result = 31 * result + (state != null ? state.hashCode() : 0);
            result = 31 * result + (country != null ? country.hashCode() : 0);
            result = 31 * result + (zip != null ? zip.hashCode() : 0);
            result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
            result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
            result = 31 * result + (text != null ? text.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "LocationJSONImpl{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", country='" + country + '\'' +
                    ", zip='" + zip + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
}
