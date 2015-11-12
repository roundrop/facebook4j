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

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
@RunWith(Enclosed.class)
public class ReadingTest {

    public static class Fields {
        @Test(expected = NullPointerException.class)
        public void nul() throws Exception {
            new Reading().fields(null);
        }

        @Test
        public void single() throws Exception {
            Reading reading = new Reading().fields("email");
            assertThat(reading.getQuery(), is("fields=email"));

            String[] array = {"email"};
            reading = new Reading().fields(array);
            assertThat(reading.getQuery(), is("fields=email"));
        }

        @Test
        public void multi() throws Exception {
            Reading reading = new Reading().fields("id,name,email");
            assertThat(reading.getQuery(), is("fields=id%2Cname%2Cemail"));

            reading = new Reading().fields("id", "name", "email");
            assertThat(reading.getQuery(), is("fields=id%2Cname%2Cemail"));

            reading = new Reading().fields("id").fields("name").fields("email");
            assertThat(reading.getQuery(), is("fields=id%2Cname%2Cemail"));

            String[] array = {"id", "name", "email"};
            reading = new Reading().fields(array);
            assertThat(reading.getQuery(), is("fields=id%2Cname%2Cemail"));
        }
    }

    public static class Limit {
        @Test
        public void limit() throws Exception {
            Reading reading = new Reading().limit(10);
            assertThat(reading.getQuery(), is("limit=10"));
        }

        @Test(expected = IllegalStateException.class)
        public void duplicate() throws Exception {
            Reading reading = new Reading().limit(10);
            reading.limit(20);
        }
    }

    public static class Offset {
        @Test
        public void offset() throws Exception {
            Reading reading = new Reading().offset(10);
            assertThat(reading.getQuery(), is("offset=10"));
        }

        @Test(expected = IllegalStateException.class)
        public void duplicate() throws Exception {
            Reading reading = new Reading().offset(10);
            reading.offset(20);
        }
    }

    public static class Until extends FacebookTestBase {
        @Test
        public void phpFormat() throws Exception {
            Reading reading = new Reading().until("1372155564");
            assertThat(reading.getQuery(), is("until=1372155564"));
        }

        @Test(expected = IllegalStateException.class)
        public void phpFormat_duplicate() throws Exception {
            Reading reading = new Reading().until("1372155564");
            reading.until("1372155565");
        }

        // http://www.crystal-creation.com/web-appli/converter/unix-time/
        @Test
        public void date() throws Exception {
            Calendar cal = createCal(2013, 6, 28, 19, 57, 15, TimeZone.getTimeZone("JST"));
            Reading reading = new Reading().until(cal.getTime());
            assertThat(reading.getQuery(), is("until=1372417035"));
        }

        @Test(expected = IllegalStateException.class)
        public void date_duplicate() throws Exception {
            Date date = new SimpleDateFormat("yyyy/M/d HH:mm:ss").parse("2013/6/28 19:57:15");
            new Reading().until(date).until(date);
        }
    }

    public static class Since extends FacebookTestBase {
        @Test
        public void phpFormat() throws Exception {
            Reading reading = new Reading().since("1372155564");
            assertThat(reading.getQuery(), is("since=1372155564"));
        }

        @Test(expected = IllegalStateException.class)
        public void phpFormat_duplicate() throws Exception {
            Reading reading = new Reading().since("1372155564");
            reading.since("1372155565");
        }

        // http://www.crystal-creation.com/web-appli/converter/unix-time/
        @Test
        public void date() throws Exception {
            Calendar cal = createCal(2013, 6, 28, 19, 57, 15, TimeZone.getTimeZone("JST"));
            Reading reading = new Reading().since(cal.getTime());
            assertThat(reading.getQuery(), is("since=1372417035"));
        }

        @Test(expected = IllegalStateException.class)
        public void date_duplicate() throws Exception {
            Date date = new SimpleDateFormat("yyyy/M/d HH:mm:ss").parse("2013/6/28 19:57:15");
            new Reading().since(date).since(date);
        }
    }

    public static class After {
        @Test
        public void after() throws Exception {
            Reading reading = new Reading().after("MTAxNTExOTQ1MjAwNzI5NDE=");
            assertThat(reading.getQuery(), is("after=MTAxNTExOTQ1MjAwNzI5NDE%3D"));
        }

        @Test(expected = IllegalStateException.class)
        public void duplicate() throws Exception {
            Reading reading = new Reading().after("MTAxNTExOTQ1MjAwNzI5NDE=");
            reading.after("MTAxNTExOTQ1MjAwNzI5NDE=");
        }
    }

    public static class Before {
        @Test
        public void before() throws Exception {
            Reading reading = new Reading().before("NDMyNzQyODI3OTQw");
            assertThat(reading.getQuery(), is("before=NDMyNzQyODI3OTQw"));
        }

        @Test(expected = IllegalStateException.class)
        public void duplicate() throws Exception {
            Reading reading = new Reading().before("NDMyNzQyODI3OTQw");
            reading.before("NDMyNzQyODI3OTQw");
        }
    }

    public static class Metadata {
        @Test
        public void metadata() throws Exception {
            Reading reading = new Reading().metadata();
            assertThat(reading.getQuery(), is("metadata=1"));
        }

        @Test(expected = IllegalStateException.class)
        public void duplicate() throws Exception {
            Reading reading = new Reading().metadata();
            reading.metadata();
        }
    }

    public static class Locale_ {
        @Test(expected = NullPointerException.class)
        public void nul() throws Exception {
            new Reading().locale(null);
        }

        @Test
        public void locale() throws Exception {
            Reading reading = new Reading().locale(Locale.JAPAN);
            assertThat(reading.getQuery(), is("locale=ja_JP"));
        }

        @Test(expected = IllegalStateException.class)
        public void duplicate() throws Exception {
            Reading reading = new Reading().locale(Locale.JAPAN);
            reading.locale(Locale.JAPAN);
        }
    }

    public static class WithLocation {
        @Test
        public void withLocation() throws Exception {
            Reading reading = new Reading().withLocation();
            assertThat(reading.getQuery(), is("with=location"));
        }

        @Test(expected = IllegalStateException.class)
        public void duplicate() throws Exception {
            Reading reading = new Reading().withLocation();
            reading.withLocation();
        }
    }

    public static class Summary {
        @Test
        public void summary_true() throws Exception {
            Reading reading = new Reading().summary();
            assertThat(reading.getQuery(), is("summary=true"));
        }
    }

    public static class Order {
        @Test
        public void chronological() throws Exception {
            Reading reading = new Reading().order(Ordering.CHRONOLOGICAL);
            assertThat(reading.getQuery(), is("order=chronological"));
        }
        @Test
        public void reverseChronological() throws Exception {
            Reading reading = new Reading().order(Ordering.REVERSE_CHRONOLOGICAL);
            assertThat(reading.getQuery(), is("order=reverse_chronological"));
        }
    }

    public static class IncludeHidden {
        @Test
        public void true_() throws Exception {
            Reading reading = new Reading().includeHidden(true);
            assertThat(reading.getQuery(), is("include_hidden=true"));
        }
        @Test
        public void false_() throws Exception {
            Reading reading = new Reading().includeHidden(false);
            assertThat(reading.getQuery(), is("include_hidden=false"));
        }
    }

    public static class ShowExpired {
        @Test
        public void true_() throws Exception {
            Reading reading = new Reading().showExpired(true);
            assertThat(reading.getQuery(), is("show_expired=true"));
        }
        @Test
        public void false_() throws Exception {
            Reading reading = new Reading().showExpired(false);
            assertThat(reading.getQuery(), is("show_expired=false"));
        }
    }

    public static class AddParameter {
        @Test
        public void string() throws Exception {
            Reading reading = new Reading().addParameter("name", "value");
            assertThat(reading.getQuery(), is("name=value"));
        }
        @Test
        public void object() throws Exception {
            Reading reading = new Reading().addParameter("foo", PrivacyType.EVERYONE);
            assertThat(reading.getQuery(), is("foo=EVERYONE"));
        }
    }

    public static class Combination {
        @Test
        public void offset_based() throws Exception {
            Reading reading = new Reading()
                    .fields("id", "name")
                    .fields("email")
                    .limit(20)
                    .offset(10)
                    .metadata()
                    .withLocation();
            assertThat(reading.getQuery(), is("fields=id%2Cname%2Cemail&limit=20&offset=10&metadata=1&with=location"));
        }

        @Test
        public void time_based() throws Exception {
            Reading reading = new Reading()
                    .fields("id", "name")
                    .fields("email")
                    .until("1364587774")
                    .since("1364849754")
                    .metadata()
                    .withLocation();
            assertThat(reading.getQuery(), is("fields=id%2Cname%2Cemail&until=1364587774&since=1364849754&metadata=1&with=location"));
        }

        @Test
        public void cursor_based() throws Exception {
            Reading reading = new Reading()
                    .fields("id", "name")
                    .fields("email")
                    .after("AaBbCc")
                    .before("DdEeFf")
                    .metadata()
                    .withLocation();
            assertThat(reading.getQuery(), is("fields=id%2Cname%2Cemail&after=AaBbCc&before=DdEeFf&metadata=1&with=location"));
        }
    }

}
