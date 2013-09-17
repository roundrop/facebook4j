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
import java.util.List;

import facebook4j.internal.http.HttpParameter;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class AlbumUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 4089671461658360414L;

    private final String name;
    private String message;
    private PrivacyParameter privacy;

    public AlbumUpdate(String name) {
        this.name = name;
    }

    public AlbumUpdate(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public AlbumUpdate(String name, PrivacyParameter privacy) {
        this.name = name;
        this.privacy = privacy;
    }

    public AlbumUpdate(String name, String message, PrivacyParameter privacy) {
        this.name = name;
        this.message = message;
        this.privacy = privacy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public AlbumUpdate message(String message) {
        setMessage(message);
        return this;
    }

    public PrivacyParameter getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyParameter privacy) {
        this.privacy = privacy;
    }
    
    public AlbumUpdate privacy(PrivacyParameter privacy) {
        setPrivacy(privacy);
        return this;
    }

    public String getName() {
        return name;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("name", name));
        if (message != null) {
            params.add(new HttpParameter("message", message));
        }
        if (privacy != null) {
            params.add(new HttpParameter("privacy", privacy.asJSONString()));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((privacy == null) ? 0 : privacy.hashCode());
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
        AlbumUpdate other = (AlbumUpdate) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (privacy == null) {
            if (other.privacy != null)
                return false;
        } else if (!privacy.equals(other.privacy))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AlbumUpdate [name=" + name + ", message=" + message
                + ", privacy=" + privacy + "]";
    }

}
