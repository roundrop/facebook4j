package facebook4j.junit;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.net.URL;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class URLMatchersTest {

    public static class PathOfTest {

        @Test
        public void url() throws Exception {
            URL url = new URL("http://facebook4j.org/foo/bar");
            assertThat(url, is(pathOf("/foo/bar")));

            assertThat(url, is(not(pathOf("/foo/bar/"))));
            assertThat(url, is(not(pathOf("foo/bar"))));
            assertThat(url, is(not(pathOf("/bar/baz"))));
            assertThat(url, is(not(pathOf("/"))));
            assertThat(url, is(not(pathOf(""))));
            assertThat(url, is(not(pathOf(null))));
        }

        @Test
        public void nullValue() throws Exception {
            assertThat(null, is(not(pathOf("/foo/bar"))));
        }
    }

    public static class HasParameterTest {

        @Test
        public void url() throws Exception {
            URL url = new URL("http://facebook4j.org/foo/bar?p1=v1&p2=v2");
            assertThat(url, hasParameter("p1", "v1"));
            assertThat(url, hasParameter("p2", "v2"));

            assertThat(url, not(hasParameter("p2", "v3")));
            assertThat(url, not(hasParameter("p3", "v3")));
            assertThat(url, not(hasParameter("", "v")));
            assertThat(url, not(hasParameter("p", "")));
            assertThat(url, not(hasParameter(null, "v")));
            assertThat(url, not(hasParameter("p", null)));
            assertThat(url, not(hasParameter(null, null)));
        }

        @Test
        public void nullValue() throws Exception {
            assertThat(null, not(hasParameter("p", "v")));
        }
    }

}