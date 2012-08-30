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
import facebook4j.FacebookException;
import facebook4j.Privacy;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PrivacyJSONImpl implements Privacy, java.io.Serializable {
    private static final long serialVersionUID = -3272219269372534734L;
    
    private final String value;
    private final String friends;
    private final String networks;
    private final String allow;
    private final String deny;
    private final String description;
    
    /*package*/public PrivacyJSONImpl(JSONObject json) throws FacebookException {
        value = getRawString("value", json);
        friends = getRawString("friends", json);
        networks = getRawString("networks", json);
        allow = getRawString("allow", json);
        deny = getRawString("deny", json);
        description = getRawString("description", json);
    }

    public String getValue() {
        return value;
    }

    public String getFriends() {
        return friends;
    }

    public String getNetworks() {
        return networks;
    }

    public String getAllow() {
        return allow;
    }

    public String getDeny() {
        return deny;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PrivacyJSONImpl [value=" + value + ", friends=" + friends
                + ", networks=" + networks + ", allow=" + allow + ", deny="
                + deny + ", description=" + description + "]";
    }

}
