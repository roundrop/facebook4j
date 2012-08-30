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
import java.util.Date;
import java.util.List;

import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.util.z_F4JInternalStringUtil;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class EventUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 375802784481895434L;

    private final String name;
    private final Date startTime;
    private Date endTime;
    private String description;
    private String location;
    private String locationId;
    private String privacyType;

    public EventUpdate(String name, Date startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public EventUpdate(String name, Date startTime, Date endTime,
            String description, String location, String locationId,
            String privacyType) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.location = location;
        this.locationId = locationId;
        this.privacyType = privacyType;
    }
    
    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("name", name));
        params.add(new HttpParameter("start_time", z_F4JInternalStringUtil.formatFacebookDatetime(startTime)));
        if (endTime != null) {
            params.add(new HttpParameter("end_time", z_F4JInternalStringUtil.formatFacebookDatetime(endTime)));
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
            params.add(new HttpParameter("privacy_type", privacyType));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

}
