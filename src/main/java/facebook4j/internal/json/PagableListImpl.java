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

import facebook4j.FacebookException;
import facebook4j.PagableList;
import facebook4j.Paging;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ class PagableListImpl<T> extends ArrayList<T> implements PagableList<T> {
    private static final long serialVersionUID = 3562128202194010360L;

    private Paging paging;

    /*package*/PagableListImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    /*package*/PagableListImpl(int size, JSONObject json) throws FacebookException {
        super(size);
        init(json);
    }
    private void init(JSONObject json) throws FacebookException {
        try {
            if (!json.isNull("paging")) {
                JSONObject pagingJSONObject = json.getJSONObject("paging");
                paging = new PagingJSONImpl(pagingJSONObject);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public Paging getPaging() {
        return paging;
    }

    @Override
    public String toString() {
        return "PagableListImpl [paging=" + paging + "]";
    }

}
