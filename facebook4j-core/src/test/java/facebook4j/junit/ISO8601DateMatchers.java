package facebook4j.junit;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ISO8601DateMatchers {
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String ISO8601_DATE_FORMAT_WITHOUT_TZ = "yyyy-MM-dd'T'HH:mm:ss";

    @Factory
    public static Matcher<Date> iso8601DateOf(final String dateString) {
        return new TypeSafeMatcher<Date>() {
            private Date actual;
            private boolean withoutTZ = false;

            @Override
            public boolean matchesSafely(Date actual) {
                this.actual = actual;

                if (dateString == null) return false;
                Date expected = null;
                try {
                    expected = new SimpleDateFormat(ISO8601_DATE_FORMAT).parse(dateString);
                } catch (ParseException e1) {
                    try {
                        expected = new SimpleDateFormat(ISO8601_DATE_FORMAT_WITHOUT_TZ).parse(dateString);
                        withoutTZ = true;
                    } catch (ParseException e2) {
                        throw new AssertionError("expected");
                    }
                }

                return actual.equals(expected);
            }

            public void describeTo(Description desc) {
                desc.appendValue(dateString);
                if (actual != null) {
                    desc.appendText(" but actual is ");
                    if (withoutTZ) {
                        desc.appendValue(new SimpleDateFormat(ISO8601_DATE_FORMAT_WITHOUT_TZ).format(actual));
                    } else {
                        desc.appendValue(new SimpleDateFormat(ISO8601_DATE_FORMAT).format(actual));
                    }
                }
            }
        };
    }

}
