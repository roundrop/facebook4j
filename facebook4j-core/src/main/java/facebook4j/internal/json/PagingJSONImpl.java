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

import java.net.URL;

import facebook4j.FacebookException;
import facebook4j.Paging;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PagingJSONImpl<T> implements Paging<T>, java.io.Serializable {
    private static final long serialVersionUID = 4741821984775700187L;

    private final URL previous;
    private final URL next;
    private final Class<?> jsonObjectType;
    
    /*pakcage*/PagingJSONImpl(JSONObject json, Class<?> type) throws FacebookException {
        previous = getURL("previous", json);
        next = getURL("next", json);
        this.jsonObjectType = type;
    }

    public URL getPrevious() {
        return previous;
    }
    
    public URL getNext() {
        return next;
    }
    
    public Class<?> getJSONObjectType() {
        return jsonObjectType;
    }

    @Override
    public String toString() {
        return "PagingJSONImpl [previous=" + previous + ", next=" + next
                + ", jsonObjectType=" + jsonObjectType + "]";
    }

}
