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
import facebook4j.FacebookResponse;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * Super interface of Facebook Response data interfaces
 * 
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ abstract class FacebookResponseImpl implements FacebookResponse, java.io.Serializable {
    private static final long serialVersionUID = 4709046756028485684L;
    
    private Metadata metadata;

    public FacebookResponseImpl() {}
    
    public FacebookResponseImpl(HttpResponse res) throws FacebookException {
        if (res == null) return;
        JSONObject json = res.asJSONObject();
        if (!json.isNull("metadata")) {
            try {
                metadata = new MetadataJSONImpl(json.getJSONObject("metadata"));
            } catch (JSONException jsone) {
                throw new FacebookException(jsone.getMessage(), jsone);
            }
        }
    }

    public Metadata getMetadata() {
        return metadata;
    }

}
