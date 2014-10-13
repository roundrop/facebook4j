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

import facebook4j.Category;
import facebook4j.FacebookException;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ class CategoryJSONImpl extends FacebookResponseImpl implements Category {
    private static final long serialVersionUID = 8273954429202255868L;

    protected String id;
    protected String name;
    protected String category;
    protected Date createdTime;

    /*package*/CategoryJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/CategoryJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
        category = getRawString("category", json);
        createdTime = getISO8601Datetime("created_time", json);
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Date getCreatedTime() {
        return createdTime;
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
        CategoryJSONImpl other = (CategoryJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CategoryJSONImpl [id=" + id + ", name=" + name + ", category="
                + category + ", createdTime=" + createdTime + "]";
    }

}
