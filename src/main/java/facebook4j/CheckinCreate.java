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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import facebook4j.internal.http.HttpParameter;

public final class CheckinCreate implements java.io.Serializable {
    private static final long serialVersionUID = -6053752054448309622L;

    private final String place;
    private final GeoLocation coordinates;
    private String tags;
    private String message;
    private URL link;
    private URL picture;

    public CheckinCreate(String place, GeoLocation coordinates) {
        this.place = place;
        this.coordinates = coordinates;
    }

    public CheckinCreate(String place, GeoLocation coordinates, String tags,
            String message, URL link, URL picture) {
        this.place = place;
        this.coordinates = coordinates;
        this.tags = tags;
        this.message = message;
        this.link = link;
        this.picture = picture;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("place", place));
        params.add(new HttpParameter("coordinates", coordinates.asJSONString()));
        if (tags != null) {
            params.add(new HttpParameter("tags", tags));
        }
        if (message != null) {
            params.add(new HttpParameter("message", message));
        }
        if (link != null) {
            params.add(new HttpParameter("link", link.toString()));
        }
        if (picture != null) {
            params.add(new HttpParameter("picture", picture.toString()));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

}
