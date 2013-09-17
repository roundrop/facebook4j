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

import facebook4j.internal.http.RequestMethod;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class PokeMethodsTest {

    public static class getPokes extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/poke/pokes.json");
            ResponseList<Poke> actuals = facebook.getPokes();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/pokes")));

            assertThat(actuals.size(), is(2));
            Poke actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Poke Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-03T02:25:23+0000")));
            Poke actual2 = actuals.get(1);
            assertThat(actual2.getTo().getId(), is("1234567890123456"));
            assertThat(actual2.getTo().getName(), is("My Name"));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("Poke Name2"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-07-02T02:25:23+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/poke/pokes_last.json");
            ResponseList<Poke> actuals = facebook.getPokes(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/pokes")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Poke actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Poke Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-03T02:25:23+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/poke/pokes.json");
            ResponseList<Poke> actuals = facebook.getPokes("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/pokes")));

            assertThat(actuals.size(), is(2));
            Poke actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Poke Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-03T02:25:23+0000")));
            Poke actual2 = actuals.get(1);
            assertThat(actual2.getTo().getId(), is("1234567890123456"));
            assertThat(actual2.getTo().getName(), is("My Name"));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("Poke Name2"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-07-02T02:25:23+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/poke/pokes_last.json");
            ResponseList<Poke> actuals = facebook.getPokes("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/pokes")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Poke actual1 = actuals.get(0);
            assertThat(actual1.getTo().getId(), is("1234567890123456"));
            assertThat(actual1.getTo().getName(), is("My Name"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Poke Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-03T02:25:23+0000")));
        }

    }

}
