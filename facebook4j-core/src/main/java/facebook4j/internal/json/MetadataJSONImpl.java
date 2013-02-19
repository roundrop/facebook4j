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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import facebook4j.FacebookException;
import facebook4j.FacebookResponse.Metadata;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/*package*/ final class MetadataJSONImpl implements Metadata, java.io.Serializable {
    private static final long serialVersionUID = 2544362523876092964L;
    
    private Metadata.Connections connections;
    
    /*package*/MetadataJSONImpl(JSONObject json) throws FacebookException {
        try {
            JSONObject connectionsJSONObject = json.getJSONObject("connections");
            connections = new ConnectionsJSONImpl(connectionsJSONObject);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public Metadata.Connections getConnections() {
        return connections;
    }

    private final class ConnectionsJSONImpl implements Metadata.Connections, java.io.Serializable {
        private static final long serialVersionUID = -826235388607408320L;
        
        private Map<String, URL> map = new HashMap<String, URL>();

        /*package*/ConnectionsJSONImpl(JSONObject json) throws FacebookException {
            try {
                Iterator connectionNames = json.keys();
                while (connectionNames.hasNext()) {
                    String connectionName = (String) connectionNames.next();
                    try {
                        map.put(connectionName, new URL((String) json.get(connectionName)));
                    } catch (MalformedURLException ignore) {}
                }
            } catch (JSONException jsone) {
                throw new FacebookException(jsone.getMessage(), jsone);
            }
        }

        public URL getURL(String connectionName) {
            return map.get(connectionName);
        }

        public List<String> getConnectionNames() {
            return Arrays.asList(map.keySet().toArray(new String[map.size()]));
        }
    }
}
