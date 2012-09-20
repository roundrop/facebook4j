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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.internal.org.json.JSONObject;

public class z_F4JInternalStringUtilTest {

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
    public void join() {
        assertThat(z_F4JInternalStringUtil.join(new String[] {"foo"}, "&"), is("foo"));
        assertThat(z_F4JInternalStringUtil.join(new String[] {"foo", "bar", "baz"}, "&"), is("foo&bar&baz"));
    }

    @Test
    public void formatISO8601Datetime() throws Exception {
        Calendar cal = Calendar.getInstance();

        cal.setTimeZone(TimeZone.getTimeZone("JST"));
        String actual1 = z_F4JInternalStringUtil.formatISO8601Datetime(cal);
        System.out.println(actual1);
        assertThat(actual1.length(), is(24));

        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        String actual2 = z_F4JInternalStringUtil.formatISO8601Datetime(cal);
        System.out.println(actual2);
        assertThat(actual2.length(), is(24));
        
        JSONObject json = new JSONObject("{\"datetime1\": \"" + actual1 + "\", \"datetime2\": \"" + actual2 + "\"}");
        Date d1 = z_F4JInternalParseUtil.getISO8601Datetime("datetime1", json);
        Date d2 = z_F4JInternalParseUtil.getISO8601Datetime("datetime2", json);
        assertThat(d1, is(d2));
    }

}
