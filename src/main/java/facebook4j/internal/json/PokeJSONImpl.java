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

import java.util.Date;

import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.Poke;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PokeJSONImpl implements Poke, java.io.Serializable {
    private static final long serialVersionUID = -4818439169873945111L;

    private IdNameEntity to;
    private IdNameEntity from;
    private Date createdTime;

    /*package*/PokeJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/PokeJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            if (!json.isNull("from")) {
                JSONObject fromJSONObject;
                    fromJSONObject = json.getJSONObject("from");
                from = new IdNameEntityJSONImpl(fromJSONObject);
            }
            if (!json.isNull("to")) {
                JSONObject toJSONObject = json.getJSONObject("to");
                to = new IdNameEntityJSONImpl(toJSONObject);
            }
            createdTime = getISO8601Datetime("created_time", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public IdNameEntity getTo() {
        return to;
    }
    public IdNameEntity getFrom() {
        return from;
    }
    public Date getCreatedTime() {
        return createdTime;
    }

    /*package*/
    static ResponseList<Poke> createPokeList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            ResponseList<Poke> pokes = new ResponseListImpl<Poke>(size, json);
            for (int i = 0; i < size; i++) {
                Poke poke = new PokeJSONImpl(list.getJSONObject(i));
                pokes.add(poke);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(pokes, json);
            }
            return pokes;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }
    
    @Override
    public String toString() {
        return "PokeJSONImpl [to=" + to + ", from=" + from + ", createdTime="
                + createdTime + "]";
    }

}
