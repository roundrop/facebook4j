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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import facebook4j.Domain;
import facebook4j.FacebookException;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class DomainJSONImpl extends IdNameJSONImpl implements Domain, java.io.Serializable {
    private static final long serialVersionUID = 3035743532170128485L;

    /*package*/DomainJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res, conf);
    }
    /*package*/DomainJSONImpl(JSONObject json) throws FacebookException {
        super(json);
    }

    /*package*/
    static List<Domain> createDomainArray(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            List<Domain> domains = new ArrayList<Domain>();
            Iterator ids = json.keys();
            while (ids.hasNext()) {
                String id = (String) ids.next();
                JSONObject domainJSONObject = (JSONObject) json.get(id);
                Domain domain = new DomainJSONImpl(domainJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(domain, domainJSONObject);
                }
                domains.add(domain);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(domains, json);
            }
            return domains;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "DomainJSONImpl [id=" + id + ", name=" + name + "]";
    }

}
