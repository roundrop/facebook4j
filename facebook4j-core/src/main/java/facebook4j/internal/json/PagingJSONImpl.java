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
import facebook4j.FacebookException;
import facebook4j.Paging;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PagingJSONImpl implements Paging, java.io.Serializable {
    private static final long serialVersionUID = -5139851986761334875L;

    private final String previous;
    private final String next;
    
    /*pakcage*/PagingJSONImpl(JSONObject json) throws FacebookException {
        previous = getRawString("previous", json);
        next = getRawString("next", json);
    }

    public String getPrevious() {
        return previous;
    }
    
    public String getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "PagingJSONImpl [previous=" + previous + ", next=" + next + "]";
    }

}
