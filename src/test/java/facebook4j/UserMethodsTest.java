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

import java.net.URL;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.json.DataObjectFactory;

public class UserMethodsTest extends FacebookTestBase {

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
    public void me() throws Exception {
        User me = facebook1.getMe(new Reading().locale(new Locale("ja_JP")));
        assertThat(me.getId(), is(id1.getId()));
        assertThat(me.getEmail(), is(notNullValue()));
        assertThat(me.getBirthday(), is(notNullValue()));
        assertThat(me.getName(), is(notNullValue()));

        String rawJSON = DataObjectFactory.getRawJSON(me);
        User userViaRawJSON = DataObjectFactory.createUser(rawJSON);
        assertThat(userViaRawJSON, is(me));
        
        User user = facebook1.getUser(id1.getId(), new Reading().fields("email"));
        assertThat(user.getEmail(), is(notNullValue()));
        assertThat(user.getName(), is(nullValue()));
    }
    
    @Test
    public void pictureURL() throws Exception {
        URL normal = facebook1.getPictureURL();
        assertThat(normal, is(notNullValue()));
        
        URL large = facebook1.getPictureURL(PictureSize.large);
        assertThat(large, is(notNullValue()));
        assertThat(large, is(not(normal)));

        URL url = facebook1.getPictureURL(id2.getId());
        assertThat(url, is(notNullValue()));
    }
    
    @Test
    public void users() throws Exception {
        List<User> users = facebook1.getUsers(id1.getId(), id2.getId());
        assertThat(users.size(), is(2));

        users = facebook1.getUsers(id1.getId(), id2.getId(), "aaaaaaaaaa");
        assertThat(users.size(), is(2));
    }

}
