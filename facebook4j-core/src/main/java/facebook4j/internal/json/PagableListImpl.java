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
import facebook4j.PagableList;
import facebook4j.Paging;
import facebook4j.Summary;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ class PagableListImpl<T> extends ArrayList<T> implements PagableList<T> {
    private static final long serialVersionUID = 3378755563757846762L;

    private Integer count;
    private Paging<T> paging;
    private Summary summary;

    /*package*/PagableListImpl() {
        super();
    }

    /*package*/PagableListImpl(int size) {
        super(size);
    }

    /*package*/PagableListImpl(JSONObject json, T... t) throws FacebookException {
        super();
        init(json, t);
    }

    /*package*/PagableListImpl(int size, JSONObject json, T... t) throws FacebookException {
        super(size);
        init(json, t);
    }

    private void init(JSONObject json, T... t) throws FacebookException {
        @SuppressWarnings("unchecked")
        Class<T> jsonObjectType = (Class<T>) t.getClass().getComponentType();

        if (!json.isNull("count")) {
            count = getInt("count", json);
        }
        
        try {
            if (!json.isNull("paging")) {
                JSONObject pagingJSONObject = json.getJSONObject("paging");
                paging = new PagingJSONImpl<T>(pagingJSONObject, jsonObjectType);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
        
        try {
            if (!json.isNull("summary")) {
                summary = new SummaryJSONImpl(json.getJSONObject("summary"));
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public Integer getCount() {
        return count;
    }

    public Paging<T> getPaging() {
        return paging;
    }
    
    public Summary getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "PagableListImpl{" +
                "count=" + count +
                ", paging=" + paging +
                ", summary=" + summary +
                ", " + super.toString() +
                '}';
    }
}
