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

package facebook4j;

import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A builder that can construct a Graph API's reading options (selection, paging, dates, introspection).
 * 
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
 */
public class Reading implements java.io.Serializable {
    private static final long serialVersionUID = -8052261582496495423L;

    private Map<String, String> parameterMap = new LinkedHashMap<String, String>();
    
    public String getQuery() {
        String[] array = new String[parameterMap.size()];
        int i = 0;
        for (String key : parameterMap.keySet()) {
            array[i] = key + "=" + HttpParameter.encode(parameterMap.get(key));
            i++;
        }
        return z_F4JInternalStringUtil.join(array, "&");
    }

    public Reading fields(String... fields) {
        if (fields == null) {
            throw new NullPointerException("fields is null");
        }
        String value = parameterMap.get("fields");
        String newValue = "";
        if (value != null) {
            newValue = value + ",";
        }
        newValue += z_F4JInternalStringUtil.join(fields);
        parameterMap.put("fields", newValue);
        return this;
    }

    public Reading limit(int limit) {
        if (parameterMap.containsKey("limit")) {
            throw new IllegalStateException("'limit' already sets");
        }
        parameterMap.put("limit", String.valueOf(limit));
        return this;
    }

    public Reading offset(int offset) {
        if (parameterMap.containsKey("offset")) {
            throw new IllegalStateException("'offset' already sets");
        }
        parameterMap.put("offset", String.valueOf(offset));
        return this;
    }

    //http://php.net/manual/en/function.strtotime.php
    public Reading until(String phpDateTimeFormats) {
        if (phpDateTimeFormats == null) {
            throw new NullPointerException("phpDateTimeFormats is null");
        }
        if (parameterMap.containsKey("until")) {
            throw new IllegalStateException("'until' already sets");
        }
        parameterMap.put("until", phpDateTimeFormats);
        return this;
    }

    //http://php.net/manual/en/function.strtotime.php
    public Reading until(Date datetime) {
        if (datetime == null) {
            throw new NullPointerException("datetime is null");
        }
        if (parameterMap.containsKey("until")) {
            throw new IllegalStateException("'until' already sets");
        }
        long unixtime = datetime.getTime() / 1000L;
        parameterMap.put("until", String.valueOf(unixtime));
        return this;
    }

    //http://php.net/manual/en/function.strtotime.php
    public Reading since(String phpDateTimeFormats) {
        if (phpDateTimeFormats == null) {
            throw new NullPointerException("phpDateTimeFormats is null");
        }
        if (parameterMap.containsKey("since")) {
            throw new IllegalStateException("'since' already sets");
        }
        parameterMap.put("since", phpDateTimeFormats);
        return this;
    }

    //http://php.net/manual/en/function.strtotime.php
    public Reading since(Date datetime) {
        if (datetime == null) {
            throw new NullPointerException("datetime is null");
        }
        if (parameterMap.containsKey("since")) {
            throw new IllegalStateException("'since' already sets");
        }
        long unixtime = datetime.getTime() / 1000L;
        parameterMap.put("since", String.valueOf(unixtime));
        return this;
    }

    public Reading after(String cursor) {
        if (parameterMap.containsKey("after")) {
            throw new IllegalStateException("'after' already sets");
        }
        parameterMap.put("after", String.valueOf(cursor));
        return this;
    }

    public Reading before(String cursor) {
        if (parameterMap.containsKey("before")) {
            throw new IllegalStateException("'before' already sets");
        }
        parameterMap.put("before", String.valueOf(cursor));
        return this;
    }

    public Reading metadata() {
        if (parameterMap.containsKey("metadata")) {
            throw new IllegalStateException("'metadata' already sets");
        }
        parameterMap.put("metadata", "1");
        return this;
    }

    public Reading locale(Locale locale) {
        if (locale == null) {
            throw new NullPointerException("locale is null");
        }
        if (parameterMap.containsKey("locale")) {
            throw new IllegalStateException("'locale' already sets");
        }
        parameterMap.put("locale", locale.toString());
        return this;
    }

    public Reading withLocation() {
        if (parameterMap.containsKey("with")) {
            throw new IllegalStateException("'with' already sets");
        }
        parameterMap.put("with", "location");
        return this;
    }

    public Reading summary() {
        if (parameterMap.containsKey("summary")) {
            throw new IllegalStateException("'summary' already sets");
        }
        parameterMap.put("summary", "true");
        return this;
    }

    public Reading filter(String filterName) {
        if (filterName == null) {
            throw new NullPointerException("filterName is null");
        }
        if (parameterMap.containsKey("filter")) {
            throw new IllegalStateException("'filter' already sets");
        }
        parameterMap.put("filter", filterName);
        return this;
    }

    public Reading order(Ordering ordering) {
        if (parameterMap.containsKey("order")) {
            throw new IllegalStateException("'order' already sets");
        }
        parameterMap.put("order", ordering.toString().toLowerCase());
        return this;
    }

    public Reading includeHidden(boolean includeHidden) {
        if (parameterMap.containsKey("include_hidden")) {
            throw new IllegalStateException("'includeHidden' already sets");
        }
        parameterMap.put("include_hidden", Boolean.toString(includeHidden));
        return this;
    }

    public Reading showExpired(boolean showExpired) {
        if (parameterMap.containsKey("show_expired")) {
            throw new IllegalStateException("'showExpired' already sets");
        }
        parameterMap.put("show_expired", Boolean.toString(showExpired));
        return this;
    }

    public Reading addParameter(String name, Object value) {
        parameterMap.put(name, value.toString());
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((parameterMap == null) ? 0 : parameterMap.hashCode());
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
        Reading other = (Reading) obj;
        if (parameterMap == null) {
            if (other.parameterMap != null)
                return false;
        } else if (!parameterMap.equals(other.parameterMap))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reading [" + getQuery() + "]";
    }
    
}
