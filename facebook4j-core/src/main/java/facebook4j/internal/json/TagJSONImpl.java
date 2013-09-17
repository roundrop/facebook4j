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
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class TagJSONImpl extends FacebookResponseImpl implements Tag, java.io.Serializable {
    private static final long serialVersionUID = -947248601368391860L;
    
    private String id;
    private String name;
    private Integer offset;
    private Integer length;
    private String type;
    
    private Double x;
    private Double y;
    private Date createdTime;

    /*package*/TagJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/TagJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
        if (!json.isNull("offset")) {
            offset = getPrimitiveInt("offset", json);
        } else {
            offset = null;
        }
        if (!json.isNull("length")) {
            length = getPrimitiveInt("length", json);
        } else {
            length = null;
        }
        type = getRawString("type", json);
        
        if (!json.isNull("x")) {
            x = getDouble("x", json);
        } else {
            x = null;
        }
        if (!json.isNull("y")) {
            y = getDouble("y", json);
        } else {
            y = null;
        }
        createdTime = getISO8601Datetime("created_time", json);
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLength() {
        return length;
    }

    public String getType() {
        return type;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    /*package*/
    static ResponseList<Tag> createTagList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Tag> tags = new ResponseListImpl<Tag>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject tagJSONObject = list.getJSONObject(i);
                Tag tag = new TagJSONImpl(tagJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(tag, tagJSONObject);
                }
                tags.add(tag);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(tags, list);
            }
            return tags;
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
        TagJSONImpl other = (TagJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TagJSONImpl [id=" + id + ", name=" + name + ", offset="
                + offset + ", length=" + length + ", type=" + type + "]";
    }

}
