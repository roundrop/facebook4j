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

import java.util.Date;

import facebook4j.FacebookException;
import facebook4j.ResponseList;
import facebook4j.Tag;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class TagJSONImpl implements Tag, java.io.Serializable {
    private static final long serialVersionUID = -947248601368391860L;
    
    private String id;
    private String name;
    private Integer offset;
    private Integer length;
    private String type;
    
    private Integer x;
    private Integer y;
    private Date createdTime;

    /*package*/TagJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
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
            offset = getPrimitiveInt("offset");
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
            x = getPrimitiveInt("x", json);
        } else {
            x = null;
        }
        if (!json.isNull("y")) {
            y = getPrimitiveInt("y", json);
        } else {
            y = null;
        }
        createdTime = getFacebookDatetime("created_time", json);
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

    public Integer getX() {
        return x;
    }

    public Integer getY() {
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
            int size = list.length();
            ResponseList<Tag> tags = new ResponseListImpl<Tag>(size, json);
            for (int i = 0; i < size; i++) {
                Tag tag = new TagJSONImpl(list.getJSONObject(i));
                tags.add(tag);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(tags, json);
            }
            return tags;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }
    
    @Override
    public String toString() {
        return "TagJSONImpl [id=" + id + ", name=" + name + ", offset="
                + offset + ", length=" + length + ", type=" + type + "]";
    }

}
