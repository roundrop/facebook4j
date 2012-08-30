/*
 * Copyright 2007 Yusuke Yamamoto
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

package facebook4j.internal.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import facebook4j.FacebookException;
import facebook4j.internal.http.HTMLEntity;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * A tiny parse utility class.
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 * <li>Deleted parseTrendsDate() method</li>
 * <li>Deleted toAccessLevel() method</li>
 * <li>Added getPrimitiveInt() methods</li>
 * <li>Added getPrimitiveLong() methods</li>
 * <li>Added getFlag() method</li>
 * <li>Added getFacebookDatetime() method</li>
 * <li>Added getURL() method</li>
 * <li>Added getStringMap() method</li>
 * <li>Added getBooleanMap() method</li>
 * <li>Added getStringList() method</li>
 * </ul>
 */
public final class z_F4JInternalParseUtil {
    private z_F4JInternalParseUtil() {
        // should never be instantiated
        throw new AssertionError();
    }

    private static ThreadLocal<Map<String, SimpleDateFormat>> formatMap = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<String, SimpleDateFormat>();
        }
    };

    public static String getUnescapedString(String str, JSONObject json) {
        return HTMLEntity.unescape(getRawString(str, json));
    }

    public static String getRawString(String name, JSONObject json) {
        try {
            if (json.isNull(name)) {
                return null;
            } else {
                return json.getString(name);
            }
        } catch (JSONException jsone) {
            return null;
        }
    }

    public static String getURLDecodedString(String name, JSONObject json) {
        String returnValue = getRawString(name, json);
        if (returnValue != null) {
            try {
                returnValue = URLDecoder.decode(returnValue, "UTF-8");
            } catch (UnsupportedEncodingException ignore) {
            }
        }
        return returnValue;
    }

    public static Date getDate(String name, JSONObject json) throws FacebookException {
        return getDate(name, json, "EEE MMM d HH:mm:ss z yyyy");
    }

    public static Date getDate(String name, JSONObject json, String format) throws FacebookException {
        String dateStr = getUnescapedString(name, json);
        if ("null".equals(dateStr) || null == dateStr) {
            return null;
        } else {
            return getDate(dateStr, format);
        }
    }

    public static Date getDate(String name, String format) throws FacebookException {
        SimpleDateFormat sdf = formatMap.get().get(format);
        if (null == sdf) {
            sdf = new SimpleDateFormat(format, Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            formatMap.get().put(format, sdf);
        }
        try {
            return sdf.parse(name);
        } catch (ParseException pe) {
            throw new FacebookException("Unexpected date format(" + name + ") returned from facebook.com", pe);
        }
    }

    public static int getPrimitiveInt(String name, JSONObject json) {
        return getPrimitiveInt(getRawString(name, json));
    }
    public static int getPrimitiveInt(String str) {
        if (null == str || "".equals(str) || "null".equals(str)) {
            return -1;
        } else {
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException nfe) {
                // workaround for the API side issue http://twitter4j.org/jira/browse/TFJ-484
                return -1;
            }
        }
    }

    public static Integer getInt(String name, JSONObject json) {
        return getInt(getRawString(name, json));
    }
    public static Integer getInt(String str) {
        if (null == str || "".equals(str) || "null".equals(str)) {
            return null;
        } else {
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException nfe) {
                // workaround for the API side issue http://twitter4j.org/jira/browse/TFJ-484
                return null;
            }
        }
    }
    
    public static long getPrimitiveLong(String name, JSONObject json) {
        return getPrimitiveLong(getRawString(name, json));
    }
    public static long getPrimitiveLong(String str) {
        if (null == str || "".equals(str) || "null".equals(str)) {
            return -1;
        } else {
            return Long.valueOf(str);
        }
    }

    public static Long getLong(String name, JSONObject json) {
        return getLong(getRawString(name, json));
    }
    public static Long getLong(String str) {
        if (null == str || "".equals(str) || "null".equals(str)) {
            return null;
        }
        return Long.valueOf(str);
    }

    public static double getDouble(String name, JSONObject json) {
        String str2 = getRawString(name, json);
        if (null == str2 || "".equals(str2) || "null".equals(str2)) {
            return -1;
        } else {
            return Double.valueOf(str2);
        }
    }

    public static boolean getBoolean(String name, JSONObject json) {
        String str = getRawString(name, json);
        if (null == str || "null".equals(str)) {
            return false;
        }
        return Boolean.valueOf(str);
    }
    
    public static boolean getFlag(String name, JSONObject json) {
        int flag = getPrimitiveInt(name, json);
        return flag == 1;
    }

    public static Date getFacebookDatetime(String name, JSONObject json) throws FacebookException {
        String fbDateString = getRawString(name, json);
        if (fbDateString == null) {
            return null;
        }
        try {
            long timeInMillis = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSSS")
                                    .parse(fbDateString)
                                    .getTime();
            return new Date(timeInMillis);
        } catch (ParseException pe) {
            throw new FacebookException(pe.getMessage(), pe);
        }
    }

    public static URL getURL(String name, JSONObject json) {
        if (json.isNull(name)) {
            return null;
        }
        try {
            return new URL(getRawString(name, json));
        } catch (MalformedURLException urle) {
            return null;
        }
    }
    
    public static Map<String, String> getStringMap(String name, JSONObject json) throws FacebookException {
        if (json.isNull(name)) {
            return null;
        }
        try {
            JSONObject jsonObject = json.getJSONObject(name);
            HashMap<String, String> result = new HashMap<String, String>();
            @SuppressWarnings("unchecked")
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                result.put(key, jsonObject.getString(key));
            }
            return result;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public static Map<String, Boolean> getBooleanMap(String name, JSONObject json) throws FacebookException {
        if (json.isNull(name)) {
            return null;
        }
        try {
            JSONObject jsonObject = json.getJSONObject(name);
            HashMap<String, Boolean> result = new HashMap<String, Boolean>();
            @SuppressWarnings("unchecked")
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                result.put(key, jsonObject.getBoolean(key));
            }
            return result;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }
    
    public static List<String> getStringList(String name, JSONObject json) throws FacebookException {
        if (json.isNull(name)) {
            return null;
        }
        try {
            JSONArray jsonArray = json.getJSONArray(name);
            int size = jsonArray.length();
            List<String> result = new ArrayList<String>(size);
            for (int i = 0; i < size; i++) {
                result.add(jsonArray.getString(i));
            }
            return result;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }
    
}