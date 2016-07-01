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
import facebook4j.FacebookResponse.Metadata;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/*package*/ final class MetadataJSONImpl implements Metadata, java.io.Serializable {
    private static final long serialVersionUID = 47702530016158838L;

    private Metadata.Fields fields;
    private String type;
    private Metadata.Connections connections;
    
    /*package*/MetadataJSONImpl(JSONObject json) throws FacebookException {
        try {
            fields = new FieldsJSONImpl(json.getJSONArray("fields"));
            type = getRawString("type", json);
            connections = new ConnectionsJSONImpl(json.getJSONObject("connections"));
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public Fields getFields() {
        return fields;
    }

    public String getType() {
        return type;
    }

    public Metadata.Connections getConnections() {
        return connections;
    }

    private final class FieldsJSONImpl implements Metadata.Fields, java.io.Serializable {
        private static final long serialVersionUID = -4785397260262958674L;

        private List<Metadata.Fields.Field> fields = new ArrayList<Metadata.Fields.Field>();

        /*package*/FieldsJSONImpl(JSONArray jsonArray) throws FacebookException {
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject fieldJSONObject = null;
                try {
                    fieldJSONObject = jsonArray.getJSONObject(i);
                } catch (JSONException jsone) {
                    throw new FacebookException(jsone.getMessage(), jsone);
                }
                fields.add(new FieldJSONImpl(fieldJSONObject));
            }
        }

        public List<Metadata.Fields.Field> getFields() {
            return fields;
        }

        private final class FieldJSONImpl implements Metadata.Fields.Field, java.io.Serializable {
            private static final long serialVersionUID = -8533365818279231831L;

            private String name;
            private String description;
            private String type;

            private FieldJSONImpl(JSONObject json) throws FacebookException {
                try {
                    if (!json.isNull("name")) {
                        this.name = json.getString("name");
                    }
                    if (!json.isNull("description")) {
                        this.description = json.getString("description");
                    }
                    if (!json.isNull("type")) {
                        this.type = json.getString("type");
                    }
                } catch (JSONException jsone) {
                    throw new FacebookException(jsone.getMessage(), jsone);
                }
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public String getType() {
                return type;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof FieldJSONImpl)) return false;

                FieldJSONImpl fieldJSON = (FieldJSONImpl) o;

                if (description != null ? !description.equals(fieldJSON.description) : fieldJSON.description != null)
                    return false;
                if (name != null ? !name.equals(fieldJSON.name) : fieldJSON.name != null) return false;
                if (type != null ? !type.equals(fieldJSON.type) : fieldJSON.type != null) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = name != null ? name.hashCode() : 0;
                result = 31 * result + (description != null ? description.hashCode() : 0);
                result = 31 * result + (type != null ? type.hashCode() : 0);
                return result;
            }

            @Override
            public String toString() {
                return "FieldJSONImpl{" +
                        "name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", type='" + type + '\'' +
                        '}';
            }
        }
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ConnectionsJSONImpl)) return false;

            ConnectionsJSONImpl that = (ConnectionsJSONImpl) o;

            if (map != null ? !map.equals(that.map) : that.map != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return map != null ? map.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "ConnectionsJSONImpl{" +
                    "map=" + map +
                    '}';
        }
    }
}
