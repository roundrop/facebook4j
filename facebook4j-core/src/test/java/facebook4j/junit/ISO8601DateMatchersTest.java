package facebook4j.junit;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.TimeZone;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ISO8601DateMatchersTest {

    public static class Iso8601DateOfTest {

        @Test
        public void date() throws Exception {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.set(Calendar.YEAR, 2013);
            cal.set(Calendar.MONTH, 4);
            cal.set(Calendar.DAY_OF_MONTH, 11);
            cal.set(Calendar.HOUR_OF_DAY, 16);
            cal.set(Calendar.MINUTE, 8);
            cal.set(Calendar.SECOND, 47);
            cal.set(Calendar.MILLISECOND, 0);
            assertThat(cal.getTime(), is(iso8601DateOf("2013-05-11T16:08:47+0000")));

            assertThat(cal.getTime(), is(not(iso8601DateOf("2014-05-11T16:08:47+0000"))));
        }

        @Test
        public void nullValue() throws Exception {
            assertThat(null, is(not(iso8601DateOf("2013-05-11T16:08:47+0000"))));
        }
    }

}