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
import facebook4j.Paging;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.URL;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PagingJSONImpl<T> implements Paging<T>, java.io.Serializable {
    private static final long serialVersionUID = 4741821984775700187L;

    private final Class<?> jsonObjectType;

    private final Cursors cursors;
    private final URL previous;
    private final URL next;

    /*pakcage*/PagingJSONImpl(JSONObject json, Class<?> type) throws FacebookException {
        this.jsonObjectType = type;
        try {
            if (!json.isNull("cursors")) {
                cursors = new CursorsJSONImpl(json.getJSONObject("cursors"));
            } else {
                cursors = null;
            }
            previous = getURL("previous", json);
            next = getURL("next", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    public Class<?> getJSONObjectType() {
        return jsonObjectType;
    }

    public URL getPrevious() {
        return previous;
    }
    
    public URL getNext() {
        return next;
    }
    
    public Cursors getCursors() {
        return cursors;
    }

    @Override
    public String toString() {
        return "PagingJSONImpl [previous=" + previous + ", next=" + next
                + ", jsonObjectType=" + jsonObjectType + "]";
    }

    private final class CursorsJSONImpl implements Cursors, java.io.Serializable {
        private static final long serialVersionUID = -4827220838408801570L;

        private final String after;
        private final String before;

        CursorsJSONImpl(JSONObject json) throws FacebookException {
            after = getRawString("after", json);
            before = getRawString("before", json);
        }

        public String getAfter() {
            return after;
        }

        public String getBefore() {
            return before;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PagingJSONImpl.CursorsJSONImpl)) return false;

            CursorsJSONImpl that = (CursorsJSONImpl) o;

            if (after != null ? !after.equals(that.after) : that.after != null) return false;
            if (before != null ? !before.equals(that.before) : that.before != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = after != null ? after.hashCode() : 0;
            result = 31 * result + (before != null ? before.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "CursorsJSONImpl{" +
                    "after='" + after + '\'' +
                    ", before='" + before + '\'' +
                    '}';
        }
    }

}
