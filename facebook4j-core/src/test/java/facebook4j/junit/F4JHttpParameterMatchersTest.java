package facebook4j.junit;

import facebook4j.internal.http.HttpParameter;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class F4JHttpParameterMatchersTest {

    public static class hasPostParameterWithValue {

        @Test
        public void httpParameters() throws Exception {
            HttpParameter[] params = new HttpParameter[2];
            params[0] = new HttpParameter("p1", "v1");
            params[1] = new HttpParameter("p2", "v2");
            assertThat(params, hasPostParameter("p1", "v1"));
            assertThat(params, hasPostParameter("p2", "v2"));

            assertThat(params, not(hasPostParameter("p2", "v3")));
            assertThat(params, not(hasPostParameter("p3", "v3")));
            assertThat(params, not(hasPostParameter("", "v")));
            assertThat(params, not(hasPostParameter("p", "")));
            assertThat(params, not(hasPostParameter(null, "v")));
            assertThat(params, not(hasPostParameter("p", null)));
            assertThat(params, not(hasPostParameter(null, null)));
        }

        @Test
        public void nullValue() throws Exception {
            assertThat(null, not(hasPostParameter("p", "v")));
        }
    }

    public static class hasPostParameterWithoutValue {

        @Test
        public void httpParameters() throws Exception {
            HttpParameter[] params = new HttpParameter[2];
            params[0] = new HttpParameter("p1", "v1");
            params[1] = new HttpParameter("p2", "v2");
            assertThat(params, hasPostParameter("p1"));
            assertThat(params, hasPostParameter("p2"));

            assertThat(params, not(hasPostParameter("p3")));
            assertThat(params, not(hasPostParameter("")));
            assertThat(params, not(hasPostParameter("p")));
            assertThat(params, not(hasPostParameter(null)));
            assertThat(params, not(hasPostParameter("p")));
            assertThat(params, not(hasPostParameter(null)));
        }

        @Test
        public void nullValue() throws Exception {
            assertThat(null, not(hasPostParameter("p")));
        }
    }

}