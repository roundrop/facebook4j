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

import facebook4j.internal.org.json.JSONObject;


/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class PrivacyParameter implements java.io.Serializable {
    private static final long serialVersionUID = 3427495429925711593L;

    private String value;
    private String friends;
    private String networks;
    private String allow;
    private String deny;
    private String description;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getFriends() {
        return friends;
    }
    public void setFriends(String friends) {
        this.friends = friends;
    }
    public String getNetworks() {
        return networks;
    }
    public void setNetworks(String networks) {
        this.networks = networks;
    }
    public String getAllow() {
        return allow;
    }
    public void setAllow(String allow) {
        this.allow = allow;
    }
    public String getDeny() {
        return deny;
    }
    public void setDeny(String deny) {
        this.deny = deny;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private JSONObject json = null;

    public JSONObject asJSONObject() {
        if (json == null) {
            json = new JSONObject(this);
        }
        return json;
    }
    
    public String asJSONString() {
        return asJSONObject().toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allow == null) ? 0 : allow.hashCode());
        result = prime * result + ((deny == null) ? 0 : deny.hashCode());
        result = prime * result + ((friends == null) ? 0 : friends.hashCode());
        result = prime * result
                + ((networks == null) ? 0 : networks.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        PrivacyParameter other = (PrivacyParameter) obj;
        if (allow == null) {
            if (other.allow != null)
                return false;
        } else if (!allow.equals(other.allow))
            return false;
        if (deny == null) {
            if (other.deny != null)
                return false;
        } else if (!deny.equals(other.deny))
            return false;
        if (friends == null) {
            if (other.friends != null)
                return false;
        } else if (!friends.equals(other.friends))
            return false;
        if (networks == null) {
            if (other.networks != null)
                return false;
        } else if (!networks.equals(other.networks))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PrivacyParameter [value=" + value + ", friends=" + friends
                + ", networks=" + networks + ", allow=" + allow + ", deny="
                + deny + "]";
    }
    
}
