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
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @see <a href="https://developers.facebook.com/docs/reference/fql/">Facebook Query Language (FQL) - Facebook Developers</a>
 */
@RunWith(Enclosed.class)
public class FQLMethodsTest {
    public static class executeFQL extends MockFacebookTestBase {
        @Test
        public void friends() throws Exception {
            facebook.setMockJSON("mock_json/fql/friends.json");
            String query = "SELECT uid2 FROM friend WHERE uid1=me()";
            JSONArray actual = facebook.executeFQL(query);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/fql")));

            assertThat(actual.length(), is(3));
            for (int i = 0; i < actual.length(); i++) {
                JSONObject jsonObject = actual.getJSONObject(i);
                assertThat(jsonObject.getString("uid2"), is("10000000" + i));
            }
        }

        @Test
        public void locale() throws Exception {
            facebook.setMockJSON("mock_json/fql/uid_name.json");
            String query = "SELECT uid,name FROM user WHERE uid = me()";
            JSONArray actual = facebook.executeFQL(query, Locale.JAPAN);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/fql")));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "ja_JP"));

            assertThat(actual.length(), is(1));
            JSONObject jsonObject = actual.getJSONObject(0);
            assertThat(jsonObject.getString("uid"), is("1234567890123456"));
            assertThat(jsonObject.getString("name"), is("foo bar"));
        }
    }

    public static class executeMultiFQL extends MockFacebookTestBase {
        @Test
        public void idAndName() throws Exception {
            facebook.setMockJSON("mock_json/fql/multi.json");
            Map<String, String> queries = new HashMap<String, String>();
            queries.put("my_id", "SELECT uid FROM user WHERE uid = me()");
            queries.put("my_name", "SELECT name FROM user WHERE uid = me()");
            Map<String, JSONArray> actuals = facebook.executeMultiFQL(queries);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/fql")));

            assertThat(actuals.size(), is(2));
            JSONArray actual1 = actuals.get("my_id");
            JSONObject jsonObject1 = actual1.getJSONObject(0);
            assertThat(jsonObject1.getString("uid"), is("1234567890123456"));
            JSONArray actual2 = actuals.get("my_name");
            JSONObject jsonObject2 = actual2.getJSONObject(0);
            assertThat(jsonObject2.getString("name"), is("foo bar"));
        }

        @Test
        public void locale() throws Exception {
            facebook.setMockJSON("mock_json/fql/multi.json");
            Map<String, String> queries = new HashMap<String, String>();
            queries.put("my_id", "SELECT uid FROM user WHERE uid = me()");
            queries.put("my_name", "SELECT name FROM user WHERE uid = me()");
            Map<String, JSONArray> actuals = facebook.executeMultiFQL(queries, Locale.JAPAN);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/fql")));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "ja_JP"));

            assertThat(actuals.size(), is(2));
            JSONArray actual1 = actuals.get("my_id");
            JSONObject jsonObject1 = actual1.getJSONObject(0);
            assertThat(jsonObject1.getString("uid"), is("1234567890123456"));
            JSONArray actual2 = actuals.get("my_name");
            JSONObject jsonObject2 = actual2.getJSONObject(0);
            assertThat(jsonObject2.getString("name"), is("foo bar"));
        }
    }

}
