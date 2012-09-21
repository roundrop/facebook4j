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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import facebook4j.Post.Action;

public class PostMethodsTest extends FacebookTestBase {

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
    public void postFeed_byConstructor() throws Exception {
        String message = "test message";
        URL link = null;
        URL picture = null;
        String name = null;
        String caption = null;
        String description = null;
        List<Action> actions = null;
        String place = "100404700021921";
        String tags = null;
        PrivacyBean privacy = new PrivacyBuilder().setValue(PrivacyType.ALL_FRIENDS).build();
        String objectAttachment = null;
        PostUpdate postUpdate = new PostUpdate(message, link, picture,
                name, caption, description, actions, place, tags, privacy,
                objectAttachment);
        String postId = facebook1.postFeed(postUpdate);
        Post post = facebook1.getPost(postId);
        assertThat(post, is(notNullValue()));
        System.out.println(post);
    }

    @Test
    public void postFeed_byBuilder() throws Exception {
        PostUpdate post = new PostUpdate(new URL("http://facebook4j.org"))
                            .picture(new URL("http://facebook4j.org/images/hero.png"))
                            .name("Facebook4J - A Java library for the Facebook Graph API")
                            .caption("facebook4j.org")
                            .description("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library.");
        String postId = facebook1.postFeed(post);
        assertThat(postId, is(notNullValue()));
    }
    
}
