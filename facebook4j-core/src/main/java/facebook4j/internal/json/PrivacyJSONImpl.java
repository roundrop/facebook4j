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
import facebook4j.Privacy;
import facebook4j.PrivacyType;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PrivacyJSONImpl implements Privacy, java.io.Serializable {
    private static final long serialVersionUID = -3272219269372534734L;
    
    private PrivacyType value;
    private PrivacyType friends;
    private List<String> networks;
    private List<String> allow;
    private List<String> deny;
    private List<String> description;
    
    /*package*/public PrivacyJSONImpl(JSONObject json) throws FacebookException {
        value = PrivacyType.getInstance(getRawString("value", json));
        friends = PrivacyType.getInstance(getRawString("friends", json));
        networks = new ArrayList<String>();
        if (!json.isNull("networks")) {
            String networksString = getRawString("networks", json);
            if (!networksString.equals("")) {
                String[] networksArray = networksString.split(",");
                for (String network : networksArray) {
                    networks.add(network.trim());
                }
            }
        }
        allow = new ArrayList<String>();
        if (!json.isNull("allow")) {
            String allowString = getRawString("allow", json);
            if (!allowString.equals("")) {
                String[] allowArray = allowString.split(",");
                for (String _allow : allowArray) {
                    allow.add(_allow.trim());
                }
            }
        }
        deny = new ArrayList<String>();
        if (!json.isNull("deny")) {
            String denyString = getRawString("deny", json);
            if (!denyString.equals("")) {
                String[] denyArray = denyString.split(",");
                for (String _deny : denyArray) {
                    deny.add(_deny.trim());
                }
            }
        }
        description = new ArrayList<String>();
        if (!json.isNull("description")) {
            String descriptionString = getRawString("description", json);
            if (!descriptionString.equals("")) {
                String[] descriptionArray = descriptionString.split(",");
                for (String _description : descriptionArray) {
                    description.add(_description.trim());
                }
            }
        }
    }

    public PrivacyType getValue() {
        return value;
    }

    public PrivacyType getFriends() {
        return friends;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public List<String> getAllow() {
        return allow;
    }

    public List<String> getDeny() {
        return deny;
    }

    public List<String> getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PrivacyJSONImpl [value=" + value + ", friends=" + friends
                + ", networks=" + networks + ", allow=" + allow + ", deny="
                + deny + ", description=" + description + "]";
    }

}
