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

import facebook4j.FacebookException;
import facebook4j.RSVPStatus;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

 /**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class RSVPStatusJSONImpl extends FacebookResponseImpl implements RSVPStatus, java.io.Serializable {
    private static final long serialVersionUID = 716299001399262503L;

    private String id;
    private String name;
    private String rsvpStatus;

    /*package*/RSVPStatusJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/RSVPStatusJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
        rsvpStatus = getRawString("rsvp_status", json);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRsvpStatus() {
        return rsvpStatus;
    }

    /*package*/
    static ResponseList<RSVPStatus> createRSVPStatusList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<RSVPStatus> rsvpStatuses = new ResponseListImpl<RSVPStatus>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject rsvpStatusJSONObject = list.getJSONObject(i);
                RSVPStatus rsvpStatus = new RSVPStatusJSONImpl(rsvpStatusJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(rsvpStatus, rsvpStatusJSONObject);
                }
                rsvpStatuses.add(rsvpStatus);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(rsvpStatuses, list);
            }
            return rsvpStatuses;
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
        RSVPStatusJSONImpl other = (RSVPStatusJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RSVPStatusJSONImpl [id=" + id + ", name=" + name
                + ", rsvpStatus=" + rsvpStatus + "]";
    }

}
