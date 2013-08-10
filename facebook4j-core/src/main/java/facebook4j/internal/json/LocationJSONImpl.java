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

import facebook4j.Application;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Location;
import facebook4j.PagableList;
import facebook4j.Place;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class LocationJSONImpl extends FacebookResponseImpl implements Location, java.io.Serializable {
    private static final long serialVersionUID = -5585291369443446494L;

    private String id;
    private IdNameEntity from;
    private PagableList<IdNameEntity> tags;
    private Place place;
    private Application application;
    private Date createdTime;
    private String type;

    /*package*/LocationJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/LocationJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("from")) {
                JSONObject fromJSONObject = json.getJSONObject("from");
                from = new IdNameEntityJSONImpl(fromJSONObject);
            }
            if (!json.isNull("tags")) {
                JSONObject tagsJSONObject = json.getJSONObject("tags");
                JSONArray list = tagsJSONObject.getJSONArray("data");
                final int size = list.length();
                tags = new PagableListImpl<IdNameEntity>(size, tagsJSONObject);
                for (int i = 0; i < size; i++) {
                    IdNameEntityJSONImpl tag = new IdNameEntityJSONImpl(list.getJSONObject(i));
                    tags.add(tag);
                }
            } else {
                tags = new PagableListImpl<IdNameEntity>(0);
            }
            if (!json.isNull("place")) {
                JSONObject placeJSONObject = json.getJSONObject("place");
                place = new PlaceJSONImpl(placeJSONObject);
            }
            if (!json.isNull("application")) {
                JSONObject applicationJSONObject = json.getJSONObject("application");
                application = new ApplicationJSONImpl(applicationJSONObject);
            }
            createdTime = getISO8601Datetime("created_time", json);
            type = getRawString("type", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public IdNameEntity getFrom() {
        return from;
    }

    public PagableList<IdNameEntity> getTags() {
        return tags;
    }

    public Place getPlace() {
        return place;
    }

    public Application getApplication() {
        return application;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getType() {
        return type;
    }

    /*package*/
    static ResponseList<Location> createLocationList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Location> locations = new ResponseListImpl<Location>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject locationJSONObject = list.getJSONObject(i);
                Location location = new LocationJSONImpl(locationJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(location, locationJSONObject);
                }
                locations.add(location);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(locations, list);
            }
            return locations;
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
        LocationJSONImpl other = (LocationJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LocationJSONImpl [id=" + id + ", from=" + from + ", tags="
                + tags + ", place=" + place + ", application=" + application
                + ", createdTime=" + createdTime + ", type=" + type + "]";
    }

}