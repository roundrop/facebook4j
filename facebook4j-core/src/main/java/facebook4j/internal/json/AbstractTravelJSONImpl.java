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

import facebook4j.*;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.getISO8601Datetime;
import static facebook4j.internal.util.z_F4JInternalParseUtil.getRawString;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ abstract class AbstractTravelJSONImpl extends FacebookResponseImpl {

    protected String id;
    protected IdNameEntity from;
    protected PagableList<IdNameEntity> tags;
    protected Place place;
    protected Application application;
    protected Date createdTime;

    /*package*/AbstractTravelJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
    }

    /*package*/AbstractTravelJSONImpl() throws FacebookException {
        super();
    }

    protected void initTravelBaseJson(JSONObject json) throws FacebookException, JSONException {
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
}
