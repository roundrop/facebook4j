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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.FacebookException;
import facebook4j.internal.org.json.JSONObject;

public class z_F4JInternalParseUtilTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPrimitiveInt() throws Exception {
        JSONObject json = new JSONObject("{\"int\": 123}");
        assertThat(z_F4JInternalParseUtil.getPrimitiveInt("int", json), is(123));
    }

    @Test
    public void getPrimitiveLong() throws Exception {
        JSONObject json = new JSONObject("{\"long\": 123}");
        assertThat(z_F4JInternalParseUtil.getPrimitiveLong("long", json), is(123l));
    }
    
    @Test
    public void getFlag() throws Exception {
        JSONObject json = new JSONObject("{\"flag\": 1}");
        assertThat(z_F4JInternalParseUtil.getFlag("flag", json), is(true));
        json = new JSONObject("{\"flag\": 0}");
        assertThat(z_F4JInternalParseUtil.getFlag("flag", json), is(false));
        json = new JSONObject("{\"flag\": true}");
        assertThat(z_F4JInternalParseUtil.getFlag("flag", json), is(false));
        json = new JSONObject("{\"flag\": \"true\"}");
        assertThat(z_F4JInternalParseUtil.getFlag("flag", json), is(false));
    }
    
    @Test
    public void getISO8601Datetime() throws Exception {
        JSONObject json = new JSONObject("{\"datetime\": \"2012-08-01T05:49:44+0900\"}");
        Date actual = z_F4JInternalParseUtil.getISO8601Datetime("datetime", json);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, instanceOf(java.util.Date.class));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
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
    public void getURL() throws Exception {
        JSONObject json = new JSONObject("{\"url\": \"http://facebook4j.org\"}");
        URL actual = z_F4JInternalParseUtil.getURL("url", json);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, instanceOf(URL.class));
        assertThat(actual.toString(), is("http://facebook4j.org"));
    }
    
    @Test
    public void getStringMap() throws Exception {
        JSONObject json = new JSONObject("{\"map\": {\"key1\": \"value1\"}}");
        Map<String, String> actual = z_F4JInternalParseUtil.getStringMap("map", json);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, instanceOf(Map.class));
        assertThat(actual.size(), is(1));
        assertThat(actual.get("key1"), is("value1"));

        json = new JSONObject("{\"map\": {\"key1\": \"value1\", \"key2\": \"value2\", \"key3\": \"value3\"}}");
        actual = z_F4JInternalParseUtil.getStringMap("map", json);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, instanceOf(Map.class));
        assertThat(actual.size(), is(3));
        assertThat(actual.get("key1"), is("value1"));
        assertThat(actual.get("key2"), is("value2"));
        assertThat(actual.get("key3"), is("value3"));
        
    }
    
    @Test
    public void getBooleanMap() throws Exception {
        JSONObject json = new JSONObject("{\"map\": {\"key1\": \"true\", \"key2\": \"false\"}}");
        Map<String, Boolean> actual = z_F4JInternalParseUtil.getBooleanMap("map", json);
        assertThat(actual, is(notNullValue()));
        assertThat(actual, instanceOf(Map.class));
        assertThat(actual.size(), is(2));
        assertThat(actual.get("key1"), is(true));
        assertThat(actual.get("key2"), is(false));
    }
    
    @Test(expected = FacebookException.class)
    public void getBooleanMap_error() throws Exception {
        JSONObject json = new JSONObject("{\"map\": {\"key1\": \"1\"}}");
        z_F4JInternalParseUtil.getBooleanMap("map", json);
    }
    
    @Test
    public void getStringList() throws Exception {
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
    public void getStringList_error() throws Exception {
        JSONObject json = new JSONObject("{\"list\": \"value1\"}");
        z_F4JInternalParseUtil.getStringList("list", json);
    }
    
}
