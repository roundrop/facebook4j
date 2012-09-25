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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @see <a href="https://developers.facebook.com/docs/reference/fql/">Facebook Query Language (FQL) - Facebook Developers</a>
 */
public class FQLMethodsTest extends FacebookTestBase {

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
    public void executeFQL() throws Exception {
        String query = "SELECT uid2 FROM friend WHERE uid1=me()";
        JSONArray jsonArray = facebookBestFriend1.executeFQL(query);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            assertThat((String) jsonObject.get("uid2"), is(bestFriend2.getId()));
        }
    }

    @Test
    public void executeMultiFQL() throws Exception {
        Map<String, String> queries = new HashMap<String, String>();
        queries.put("all friends", "SELECT uid2 FROM friend WHERE uid1=me()");
        queries.put("my name", "SELECT name FROM user WHERE uid=me()");
        Map<String, JSONArray> result = facebookBestFriend1.executeMultiFQL(queries);
        JSONArray allFriendsJSONArray = result.get("all friends");
        for (int i = 0; i < allFriendsJSONArray.length(); i++) {
            JSONObject jsonObject = allFriendsJSONArray.getJSONObject(i);
            assertThat((String) jsonObject.get("uid2"), is(bestFriend2.getId()));
        }
        JSONArray myNameJSONArray = result.get("my name");
        assertThat((String) myNameJSONArray.getJSONObject(0).get("name"), is("bestfriend one"));
    }

}
