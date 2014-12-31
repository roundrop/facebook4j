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
import facebook4j.IdNameEntity;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ class IdNameEntityJSONImpl implements IdNameEntity, java.io.Serializable {
    private static final long serialVersionUID = -9152205822176968747L;

    protected String id;
    protected String name;
    
    /*package*/IdNameEntityJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }
    
    /*package*/IdNameEntityJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    /*package*/IdNameEntityJSONImpl(String name) {
        super();
        this.name = name;
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
    }
    
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    /*package*/
    static ResponseList<IdNameEntity> createIdNameEntityList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<IdNameEntity> entities = new ResponseListImpl<IdNameEntity>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject entityJSONObject = list.getJSONObject(i);
                IdNameEntity entity = new IdNameEntityJSONImpl(entityJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(entity, entityJSONObject);
                }
                entities.add(entity);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(entities, list);
            }
            return entities;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        IdNameEntityJSONImpl other = (IdNameEntityJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "IdNameEntityJSONImpl [name=" + name + ", id=" + id + "]";
    }

}
