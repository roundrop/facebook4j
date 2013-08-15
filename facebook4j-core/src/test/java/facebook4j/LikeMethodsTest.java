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
public class LikeMethodsTest {

    public static class getUserLikes extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/like/likes.json");
            ResponseList<Like> actuals = facebook.getUserLikes();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/likes")));

            assertThat(actuals.size(), is(20));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("110985235591820"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-15T12:43:50+0000")));
            Like actual20 = actuals.get(19);
            assertThat(actual20.getId(), is("178725115782"));
            assertThat(actual20.getCategory(), is("Musician/band"));
            assertThat(actual20.getName(), is("Ken Ishii"));
            assertThat(actual20.getCreatedTime(), is(iso8601DateOf("2011-02-21T16:18:28+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/like/like.json");
            ResponseList<Like> actuals = facebook.getUserLikes(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/likes")));

            assertThat(actuals.size(), is(1));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("110985235591820"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-15T12:43:50+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/like/likes.json");
            ResponseList<Like> actuals = facebook.getUserLikes("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/likes")));

            assertThat(actuals.size(), is(20));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("110985235591820"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-15T12:43:50+0000")));
            Like actual20 = actuals.get(19);
            assertThat(actual20.getId(), is("178725115782"));
            assertThat(actual20.getCategory(), is("Musician/band"));
            assertThat(actual20.getName(), is("Ken Ishii"));
            assertThat(actual20.getCreatedTime(), is(iso8601DateOf("2011-02-21T16:18:28+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/like/like.json");
            ResponseList<Like> actuals = facebook.getUserLikes("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/likes")));

            assertThat(actuals.size(), is(1));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("110985235591820"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-15T12:43:50+0000")));
        }

    }

}
