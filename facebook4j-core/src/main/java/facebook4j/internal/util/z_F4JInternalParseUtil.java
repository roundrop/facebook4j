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

import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

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
 * <li>Deleted getDate() methods</li>
 * </ul>
 */
public final class z_F4JInternalParseUtil {

    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String ISO8601_DATE_FORMAT_WITHOUT_TZ = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String ISO8601_DATE_FORMAT_WITHOUT_TIME = "yyyy-MM-dd";

    private z_F4JInternalParseUtil() {
        // should never be instantiated
        throw new AssertionError();
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
            try {
                return Long.valueOf(str);
            } catch (NumberFormatException nfe) {
                return -1;
            }
        }
    }

    public static Long getLong(String name, JSONObject json) {
        return getLong(getRawString(name, json));
    }
    public static Long getLong(String str) {
        if (null == str || "".equals(str) || "null".equals(str)) {
            return null;
        } else {
            try {
                return Long.valueOf(str);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
    }

    public static Double getDouble(String name, JSONObject json) {
        String str2 = getRawString(name, json);
        if (null == str2 || "".equals(str2) || "null".equals(str2)) {
            return null;
        } else {
            return Double.valueOf(str2);
        }
    }

    /**
     * Parses the value of a field as a timezone offset in hours. A timezone 
     * offset is the value added to UTC time to get the user's local time. 
     * A date/time reference point must be provided because the offset may 
     * differ based on the date/time for which it is to be computed, for
     * example because of daylight savings.
     * 
     * @param name the name of the member of the given json object that is 
     * the raw string to be parsed
     * @param json the json object
     * @param datetimeReference the date/time for which the offset is to be computed
     * @return the timezone offset, in hours
     */
    public static Double getTimeZoneOffset(String name, JSONObject json, long datetimeReference) {
        String rawString = getRawString(name, json);
        if (null == rawString || "".equals(rawString) || "null".equals(rawString)) {
            return null;
        } else {
            try {
                return Double.valueOf(rawString);
            } catch (NumberFormatException ignore) {
                TimeZone timeZone = TimeZone.getTimeZone(rawString); // returns GMT if not understood
                return Integer.valueOf(computeTimeZoneOffsetInHours(timeZone, datetimeReference)).doubleValue();
            }
        }
    }
    
    /**
     * Computes the offset in hours for a given timezone at a given date/time.
     * @param timeZone the timezone 
     * @param currentDatetime the date/time 
     * @return the offset, in hours
     */
    public static int computeTimeZoneOffsetInHours(TimeZone timeZone, long currentDatetime) {
        int offsetInMilliseconds = timeZone.getOffset(currentDatetime);
        return offsetInMilliseconds / (1000 * 60 * 60);
    }
    
    public static Boolean getBoolean(String name, JSONObject json) {
        String str = getRawString(name, json);
        if (null == str || "null".equals(str)) {
            return null;
        }
        return Boolean.valueOf(str);
    }
    
    public static boolean getFlag(String name, JSONObject json) {
        int flag = getPrimitiveInt(name, json);
        return flag == 1;
    }

    public static Date getISO8601Datetime(String name, JSONObject json) throws FacebookException {
        String dateString = getRawString(name, json);
        if (dateString == null) {
            return null;
        }
        if (json.isNull("timezone")) {
            return parseISO8601Date(dateString);
        } else {
            TimeZone timezone = getTimeZone("timezone", json);
            return parseISO8601Date(dateString, timezone);
        }
    }

    private static Date parseISO8601Date(String dateString) {
        return parseISO8601Date(dateString, TimeZone.getTimeZone("UTC"));
    }
    private static Date parseISO8601Date(String dateString, TimeZone timezone) {
        try {
            return new SimpleDateFormat(ISO8601_DATE_FORMAT).parse(dateString);
        } catch (ParseException e1) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_FORMAT_WITHOUT_TZ);
                sdf.setTimeZone(timezone);
                return sdf.parse(dateString);
            } catch (ParseException e2) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_DATE_FORMAT_WITHOUT_TIME);
                    sdf.setTimeZone(timezone);
                    return sdf.parse(dateString);
                } catch (ParseException e3) {
                    return null;
                }
            }
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
    
    public static URI getURI(String name, JSONObject json) {
        if (json.isNull(name)) {
            return null;
        }
        try {
            return new URI(getRawString(name, json));
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static TimeZone getTimeZone(String name, JSONObject json) {
        if (json.isNull(name)) {
            return null;
        }
        return TimeZone.getTimeZone(getRawString(name, json));
    }

    public static Map<String, String> getStringMap(String name, JSONObject json) throws FacebookException {
        if (json.isNull(name)) {
            return Collections.emptyMap();
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

    public static Map<String, Long> getLongMap(String name, JSONObject json) throws FacebookException {
        if (json.isNull(name)) {
            return Collections.emptyMap();
        }
        try {
            JSONObject jsonObject = json.getJSONObject(name);
            HashMap<String, Long> result = new HashMap<String, Long>();
            @SuppressWarnings("unchecked")
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                result.put(key, jsonObject.getLong(key));
            }
            return result;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public static Map<String, Boolean> getBooleanMap(String name, JSONObject json) throws FacebookException {
        if (json.isNull(name)) {
            return Collections.emptyMap();
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
            return Collections.emptyList();
        }
        try {
            JSONArray jsonArray = json.getJSONArray(name);
            final int size = jsonArray.length();
            List<String> result = new ArrayList<String>(size);
            for (int i = 0; i < size; i++) {
                result.add(jsonArray.getString(i));
            }
            return result;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public static boolean isJSONObject(String name, JSONObject json) {
        try {
            Object object = json.get(name);
            return (object instanceof JSONObject);
        } catch (JSONException e) {
            return false;
        }
    }
    
    public static boolean isJSONArray(String name, JSONObject json) {
        try {
            Object object = json.get(name);
            return (object instanceof JSONArray);
        } catch (JSONException e) {
            return false;
        }
    }

}