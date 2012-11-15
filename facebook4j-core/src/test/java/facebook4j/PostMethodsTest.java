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
    public void getFeed() throws Exception {
        ResponseList<Post> feed = facebook1.getFeed();
        assertThat(feed, is(notNullValue()));
        for (Post post : feed) {
            System.out.println(post);
        }
    }
    
    @Test
    public void getFeed_withReading() throws Exception {
        ResponseList<Post> feed = facebook1.getFeed(new Reading().fields("from"));
        assertThat(feed, is(notNullValue()));
        for (Post post : feed) {
            assertThat(post.getFrom(), is(notNullValue()));
            assertThat(post.getFrom().getName(), is(notNullValue()));
            assertThat(post.getMessage(), is(nullValue()));
        }
    }
    
    @Test
    public void getFeed_byUserid() throws Exception {
        ResponseList<Post> feed = facebook2.getFeed(id1.getId());
        assertThat(feed, is(notNullValue()));
        for (Post post : feed) {
            System.out.println(post);
        }
    }
    
    @Test
    public void getHome() throws Exception {
        ResponseList<Post> feed = facebookBestFriend2.getHome();
        assertThat(feed, is(notNullValue()));
        boolean friendsIncluded = false;
        for (Post post : feed) {
            if (post.getFrom().getId().equals(bestFriend1.getId())) {
                friendsIncluded = true;
            }
        }
        assertThat(friendsIncluded, is(true));
    }
    
    @Test
    public void getHome_withReading() throws Exception {
        ResponseList<Post> feed = facebook1.getHome(new Reading().fields("name"));
        assertThat(feed, is(notNullValue()));
        for (Post post : feed) {
            assertThat(post.getMessage(), is(nullValue()));
        }
    }

    @Test
    public void postStatusMessage() throws Exception {
        String message = "postStatusMessage() test";
        String postId = facebook1.postStatusMessage(message);
        assertThat(postId, is(notNullValue()));
        
        boolean deleteResult = facebook1.deletePost(postId);
        assertThat(deleteResult, is(true));
    }
    
    @Test
    public void getLinks() throws Exception {
        ResponseList<Link> links = facebook1.getLinks();
        for (Link link : links) {
            System.out.println(link);
        }
    }
    
    @Test
    public void getLinks_withReading() throws Exception {
        ResponseList<Link> links = facebook1.getLinks(new Reading().fields("link"));
        for (Link link : links) {
            assertThat(link, is(notNullValue()));
            assertThat(link.getLink(), is(notNullValue()));
            assertThat(link.getFrom(), is(nullValue()));
        }
    }
    
    @Test
    public void postLink() throws Exception {
        URL link = new URL("http://facebook4j.org");
        String postId = facebook1.postLink(link);
        assertThat(postId, is(notNullValue()));
        
        boolean deleteResult = facebook1.deletePost(postId);
        assertThat(deleteResult, is(true));
        
        postId = facebook1.postLink(link, "A Java library for the Facebook Graph API");
        assertThat(postId, is(notNullValue()));
        
        deleteResult = facebook1.deletePost(postId);
        assertThat(deleteResult, is(true));
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

        boolean deleteResult = facebook1.deletePost(postId);
        assertThat(deleteResult, is(true));
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
        
        boolean deleteResult = facebook1.deletePost(postId);
        assertThat(deleteResult, is(true));
    }
    
    @Test
    public void getPosts() throws Exception {
        ResponseList<Post> posts = facebook1.getPosts();
        for (Post post : posts) {
            assertThat(post.getFrom().getId(), is(id1.getId()));
        }
    }

    @Test
    public void getStatuses() throws Exception {
        ResponseList<Post> posts = facebook1.getStatuses();
        for (Post post : posts) {
            assertThat(post.getFrom().getId(), is(id1.getId()));
            assertThat(post.getMessage(), is(notNullValue()));
        }
    }
    
    @Test
    public void getTagged() throws Exception {
        ResponseList<Post> posts = facebookBestFriend1.getTagged();
        for (Post post : posts) {
            assertThat(post.getFrom().getId(),is(bestFriend2.getId()));
        }
    }
    
    @Test
    public void getPost() throws Exception {
        ResponseList<Post> posts = facebook1.getPosts();
        Post latestPost = posts.get(0);
        Post post = facebook1.getPost(latestPost.getId());
        assertThat(post, is(latestPost));
    }
    
    @Test
    public void getPostComments() throws Exception {
        ResponseList<Post> posts = facebookBestFriend1.getFeed();
        for (Post post : posts) {
            ResponseList<Comment> comments = facebookBestFriend1.getPostComments(post.getId());
            System.out.println(comments);
        }
    }

    @Test
    public void commentPost() throws Exception {
        ResponseList<Post> posts = facebookBestFriend2.getHome();
        for (Post post : posts) {
            if (post.getFrom().getId().equals(bestFriend1.getId())) {
                String postId = post.getId();
                String commentId = facebookBestFriend2.commentPost(postId, "comment from bestFriend2 via test");
                assertThat(commentId, is(notNullValue()));
                
                Post p = facebookBestFriend2.getPost(postId);
                assertThat(p.getComments().size() > 0, is(true));
            }
        }
    }
    
    @Test
    public void likeAndUnlikePost() throws Exception {
        ResponseList<Post> posts = facebookBestFriend2.getHome();
        for (Post post : posts) {
            if (post.getFrom().getId().equals(bestFriend1.getId())) {
                String postId = post.getId();
                boolean likeResult = facebookBestFriend2.likePost(postId);
                assertThat(likeResult, is(true));
                
                boolean unlikeResult = facebookBestFriend2.unlikePost(postId);
                assertThat(unlikeResult, is(true));
            }
        }
    }
    
}
