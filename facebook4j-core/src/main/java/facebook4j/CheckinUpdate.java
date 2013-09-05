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

public final class CheckinUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -6053752054448309622L;

    private final String place;
    private final GeoLocation coordinates;
    private String tags;
    private String message;
    private URL link;
    private URL picture;

    public CheckinUpdate(String place, GeoLocation coordinates) {
        this.place = place;
        this.coordinates = coordinates;
    }

    public CheckinUpdate(String place, GeoLocation coordinates, String tags,
                         String message, URL link, URL picture) {
        this.place = place;
        this.coordinates = coordinates;
        this.tags = tags;
        this.message = message;
        this.link = link;
        this.picture = picture;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public CheckinUpdate tags(String tags) {
        setTags(tags);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public CheckinUpdate message(String message) {
        setMessage(message);
        return this;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }
    
    public CheckinUpdate link(URL link) {
        setLink(link);
        return this;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }
    
    public CheckinUpdate picture(URL picture) {
        setPicture(picture);
        return this;
    }

    public String getPlace() {
        return place;
    }

    public GeoLocation getCoordinates() {
        return coordinates;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((place == null) ? 0 : place.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
        CheckinUpdate other = (CheckinUpdate) obj;
        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;
        if (link == null) {
            if (other.link != null)
                return false;
        } else if (!link.equals(other.link))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (place == null) {
            if (other.place != null)
                return false;
        } else if (!place.equals(other.place))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CheckinUpdate [place=" + place + ", coordinates=" + coordinates
                + ", tags=" + tags + ", message=" + message + ", link=" + link
                + ", picture=" + picture + "]";
    }

}
