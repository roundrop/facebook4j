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

import facebook4j.internal.org.json.JSONObject;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class z_F4JInternalStringUtilTest {

    @Test
    public void join() {
        assertThat(z_F4JInternalStringUtil.join(new String[] {"foo"}, "&"), is("foo"));
        assertThat(z_F4JInternalStringUtil.join(new String[] {"foo", "bar", "baz"}, "&"), is("foo&bar&baz"));
    }


    @Test
    public void formatISO8601Datetime() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        cal.set(Calendar.HOUR_OF_DAY, 16);
        cal.set(Calendar.MINUTE, 17);
        cal.set(Calendar.SECOND, 18);
        cal.set(Calendar.MILLISECOND, 0);

        cal.setTimeZone(TimeZone.getTimeZone("JST"));
        String actual1 = z_F4JInternalStringUtil.formatISO8601Datetime(cal);
        assertThat(actual1, is("2012-06-15T16:17:18+0900"));

        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        String actual2 = z_F4JInternalStringUtil.formatISO8601Datetime(cal);
        assertThat(actual2, is("2012-06-15T07:17:18+0000"));    //16-9=7

        JSONObject json = new JSONObject("{\"datetime1\": \"" + actual1 + "\", \"datetime2\": \"" + actual2 + "\"}");
        Date d1 = z_F4JInternalParseUtil.getISO8601Datetime("datetime1", json);
        Date d2 = z_F4JInternalParseUtil.getISO8601Datetime("datetime2", json);
        assertThat(d1, is(d2));
    }

}
