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

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.FacebookResponse.Metadata;
import facebook4j.FacebookResponse.Metadata.Connections;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
 */
public class ReadingTest extends FacebookTestBase {

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
    public void fields() throws Exception {
        User user = facebook1.getUser(id2.getId(), new Reading().fields("id", "name", "picture"));
        assertThat(user.getId(), is(notNullValue()));
        assertThat(user.getName(), is(notNullValue()));
        assertThat(user.getPicture(), is(notNullValue()));
        assertThat(user.getGender(), is(nullValue()));
    }
    
    @Test
    public void withLocation() throws Exception {
        ResponseList<Post> posts = facebook1.getHome(new Reading().withLocation());
        for (Post post : posts) {
            assertThat(post.getPlace(), is(notNullValue()));
        }
    }
    
    @Test
    public void limit() throws Exception {
        ResponseList<Like> likes = facebook1.getUserLikes("216311481960", new Reading().limit(3));
        assertThat(likes.size(), is(3));
    }
    
    @Test
    public void until() throws Exception {
        ResponseList<Post> posts = facebook1.searchPosts("orange", new Reading().until("yesterday"));
        assertThat(posts.size() > 0, is(true));
    }
    
    @Test
    public void metadata() throws Exception {
        Event event = facebook1.getEvent("331218348435");
        assertThat(event.getMetadata(), is(nullValue()));

        event = facebook1.getEvent("331218348435", new Reading().metadata());
        Metadata metadata = event.getMetadata();
        assertThat(metadata, is(notNullValue()));
        Connections connections = metadata.getConnections();
        List<String> connectionNames = connections.getConnectionNames();
        assertThat(connectionNames.size(), is(8));
        for (String connectionName : connectionNames) {
            String url = connections.getURL(connectionName).toString();
            assertThat(url.substring(url.lastIndexOf("/")+1, url.indexOf("?")), is(connectionName));
        }
    }
    
    @Test
    public void filter() throws Exception {
        ResponseList<Post> posts = facebookBestFriend1.getHome(new Reading().filter("app_2305272732"));    //to retrive your News Feed filtered by photos
        for (Post post : posts) {
            assertThat(post.getPicture(), is(notNullValue()));
        }
        
    }
    
}
