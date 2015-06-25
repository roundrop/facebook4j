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

package facebook4j.internal.util;

import facebook4j.FacebookException;
import facebook4j.FacebookTestBase;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class z_F4JInternalParseUtilTest {

    public static class getPrimitiveInt extends FacebookTestBase {
        @Test
        public void whenInt() throws Exception {
            JSONObject json = new JSONObject("{\"int\": 123}");
            assertThat(z_F4JInternalParseUtil.getPrimitiveInt("int", json), is(123));
        }

        @Test
        public void whenNotInt() throws Exception {
            JSONObject json = new JSONObject("{\"int\": \"string\"}");
            assertThat(z_F4JInternalParseUtil.getPrimitiveInt("int", json), is(-1));
        }
    }

    public static class getPrimitiveLong extends FacebookTestBase {
        @Test
        public void whenLong() throws Exception {
            JSONObject json = new JSONObject("{\"long\": 123}");
            assertThat(z_F4JInternalParseUtil.getPrimitiveLong("long", json), is(123l));
        }

        @Test
        public void whenNotLong() throws Exception {
            JSONObject json = new JSONObject("{\"long\": \"string\"}");
            assertThat(z_F4JInternalParseUtil.getPrimitiveLong("long", json), is(-1l));
        }
    }

    public static class getLong extends FacebookTestBase {
        @Test
        public void whenLong() throws Exception {
            JSONObject json = new JSONObject("{\"Long\": 123}");
            assertThat(z_F4JInternalParseUtil.getLong("Long", json), is(123L));
        }

        @Test
        public void whenNotLong() throws Exception {
            JSONObject json = new JSONObject("{\"Long\": \"string\"}");
            assertThat(z_F4JInternalParseUtil.getLong("Long", json), is(nullValue()));
        }
    }

    @RunWith(Enclosed.class)
    public static class getFlag {

        @RunWith(Theories.class)
        public static class whenTrue extends FacebookTestBase {
            @DataPoints
            public static JSONObject[] params() throws JSONException {
                return new JSONObject[] {
                        new JSONObject("{\"flag\": 1}"),
                };
            }

            @Theory
            public void parameterized(JSONObject p) throws Exception {
                assertThat(z_F4JInternalParseUtil.getFlag("flag", p), is(true));
            }
        }

        @RunWith(Theories.class)
        public static class whenFalse extends FacebookTestBase {
            @DataPoints
            public static JSONObject[] params() throws JSONException {
                return new JSONObject[] {
                        new JSONObject("{\"flag\": 0}"),
                        new JSONObject("{\"flag\": true}"),
                        new JSONObject("{\"flag\": \"true\"}"),
                };
            }

            @Theory
            public void parameterized(JSONObject p) throws Exception {
                assertThat(z_F4JInternalParseUtil.getFlag("flag", p), is(false));
            }
        }
    }

    public static class getISO8601Datetime extends FacebookTestBase {
        @Before
        public void setUp() {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        }

        @Test
        public void preciseTime() throws Exception {
            JSONObject json = new JSONObject("{\"datetime\": \"2012-08-01T05:49:44+0900\"}");
            Date actual = z_F4JInternalParseUtil.getISO8601Datetime("datetime", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(java.util.Date.class));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            assertThat(actual, is(df.parse("2012-07-31 20:49:44")));

            json = new JSONObject("{\"datetime\": \"2012-07-31T20:49:44+0000\"}");
            actual = z_F4JInternalParseUtil.getISO8601Datetime("datetime", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(java.util.Date.class));
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("JST"));
            assertThat(actual, is(df.parse("2012-08-01 05:49:44")));
        }

        @Test
        public void localTime() throws Exception {
            JSONObject json = new JSONObject("{\"datetime\": \"2012-08-01T05:49:44\"}");
            Date actual = z_F4JInternalParseUtil.getISO8601Datetime("datetime", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(java.util.Date.class));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            assertThat(actual, is(df.parse("2012-08-01 05:49:44")));

            json = new JSONObject("{\"datetime\": \"2012-07-31T20:49:44\"}");
            actual = z_F4JInternalParseUtil.getISO8601Datetime("datetime", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(java.util.Date.class));
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("JST"));
            assertThat(actual, is(df.parse("2012-08-01 05:49:44")));
        }

        @Test
        public void dateOnly() throws Exception {
            JSONObject json = new JSONObject("{\"datetime\": \"2012-08-01\"}");
            Date actual = z_F4JInternalParseUtil.getISO8601Datetime("datetime", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(java.util.Date.class));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            assertThat(actual, is(df.parse("2012-08-01 00:00:00")));

            json = new JSONObject("{\"datetime\": \"2012-07-31\"}");
            actual = z_F4JInternalParseUtil.getISO8601Datetime("datetime", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(java.util.Date.class));
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("JST"));
            assertThat(actual, is(df.parse("2012-07-31 09:00:00")));
        }

    }

    public static class getURL extends FacebookTestBase {
        @Test
        public void url() throws Exception {
            JSONObject json = new JSONObject("{\"url\": \"http://facebook4j.org\"}");
            URL actual = z_F4JInternalParseUtil.getURL("url", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(URL.class));
            assertThat(actual.toString(), is("http://facebook4j.org"));
        }
    }

    public static class getURI extends FacebookTestBase {
        @Test
        public void uri() throws Exception {
            JSONObject json = new JSONObject("{\"uri\": \"http://facebook4j.org\"}");
            URI actual = z_F4JInternalParseUtil.getURI("uri", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(URI.class));
            assertThat(actual.toString(), is("http://facebook4j.org"));
        }
    }

    public static class getTimeZone extends FacebookTestBase {
        @Test
        public void tokyo() throws Exception {
            JSONObject json = new JSONObject("{\"timezone\": \"Asia/Tokyo\"}");
            TimeZone actual = z_F4JInternalParseUtil.getTimeZone("timezone", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(TimeZone.class));
            assertThat(actual, is(TimeZone.getTimeZone("Asia/Tokyo")));
        }
    }

    public static class getTimeZoneOffset extends FacebookTestBase {
        
        private static final long datetimeReference = 1449705600L * 1000L;
        
        @Test
        public void known() throws Exception {
            JSONObject json = new JSONObject("{\"timezone\": \"Asia/Tokyo\"}");
            Double actual = z_F4JInternalParseUtil.getTimeZoneOffset("timezone", json, datetimeReference);
            Double expectedTokyoOffset = 9d;
            assertEquals(expectedTokyoOffset, actual);
        }
        
        @Test
        public void unknown() throws Exception {
            JSONObject json = new JSONObject("{\"timezone\": \"Asia/Wonderland\"}");
            Double actual = z_F4JInternalParseUtil.getTimeZoneOffset("timezone", json, datetimeReference);
            Double expectedGmtOffset = 0d;
            assertEquals(expectedGmtOffset, actual);
        }
    }

    public static class getStringMap extends FacebookTestBase {
        @Test
        public void size1() throws Exception {
            JSONObject json = new JSONObject("{\"map\": {\"key1\": \"value1\"}}");
            Map<String, String> actual = z_F4JInternalParseUtil.getStringMap("map", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(Map.class));
            assertThat(actual.size(), is(1));
            assertThat(actual.get("key1"), is("value1"));
        }

        @Test
        public void sizeN() throws Exception {
            JSONObject json = new JSONObject("{\"map\": {\"key1\": \"value1\", \"key2\": \"value2\", \"key3\": \"value3\"}}");
            Map<String, String> actual = z_F4JInternalParseUtil.getStringMap("map", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(Map.class));
            assertThat(actual.size(), is(3));
            assertThat(actual.get("key1"), is("value1"));
            assertThat(actual.get("key2"), is("value2"));
            assertThat(actual.get("key3"), is("value3"));
        }

    }

    public static class getLongMap extends FacebookTestBase {
        @Test
        public void size1() throws Exception {
            JSONObject json = new JSONObject("{\"map\": {\"key1\": 1111111111111}}");
            Map<String, Long> actual = z_F4JInternalParseUtil.getLongMap("map", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(Map.class));
            assertThat(actual.size(), is(1));
            assertThat(actual.get("key1"), is(1111111111111L));
        }

        @Test
        public void sizeN() throws Exception {
            JSONObject json = new JSONObject("{\"map\": {\"key1\": 1111111111111, \"key2\": 1111111111112, \"key3\": 1111111111113}}");
            Map<String, Long> actual = z_F4JInternalParseUtil.getLongMap("map", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(Map.class));
            assertThat(actual.size(), is(3));
            assertThat(actual.get("key1"), is(1111111111111L));
            assertThat(actual.get("key2"), is(1111111111112L));
            assertThat(actual.get("key3"), is(1111111111113L));
        }

    }

    public static class getBooleanMap extends FacebookTestBase {
        @Test
        public void size1() throws Exception {
            JSONObject json = new JSONObject("{\"map\": {\"key1\": \"true\"}}");
            Map<String, Boolean> actual = z_F4JInternalParseUtil.getBooleanMap("map", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(Map.class));
            assertThat(actual.size(), is(1));
            assertThat(actual.get("key1"), is(true));
        }

        @Test
        public void sizeN() throws Exception {
            JSONObject json = new JSONObject("{\"map\": {\"key1\": \"true\", \"key2\": \"false\"}}");
            Map<String, Boolean> actual = z_F4JInternalParseUtil.getBooleanMap("map", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(Map.class));
            assertThat(actual.size(), is(2));
            assertThat(actual.get("key1"), is(true));
            assertThat(actual.get("key2"), is(false));
        }

        @Test(expected = FacebookException.class)
        public void whenNotBoolean() throws Exception {
            JSONObject json = new JSONObject("{\"map\": {\"key1\": \"1\"}}");
            z_F4JInternalParseUtil.getBooleanMap("map", json);
        }

    }

    public static class getStringList extends FacebookTestBase {
        @Test
        public void sizeN() throws Exception {
            JSONObject json = new JSONObject("{\"list\": [\"value1\", \"value2\", \"value3\"]}");
            List<String> actual = z_F4JInternalParseUtil.getStringList("list", json);
            assertThat(actual, is(notNullValue()));
            assertThat(actual, instanceOf(List.class));
            assertThat(actual.size(), is(3));
            assertThat(actual.get(0), is("value1"));
            assertThat(actual.get(1), is("value2"));
            assertThat(actual.get(2), is("value3"));
        }

        @Test(expected = FacebookException.class)
        public void whenNotArray() throws Exception {
            JSONObject json = new JSONObject("{\"list\": \"value1\"}");
            z_F4JInternalParseUtil.getStringList("list", json);
        }
    }

}
