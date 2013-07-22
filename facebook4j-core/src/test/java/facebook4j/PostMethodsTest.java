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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PostMethodsTest extends MockFacebookTestBase {

    @Test
    public void getStatuses_page() throws Exception {
        facebook.setMockJSON("mock_json/status/page.json");
        String pageId = "19292868552";
        ResponseList<Post> statuses = facebook.getStatuses(pageId);

        assertThat(statuses.size(), is(25));

        Post status = statuses.get(0);
        PagableList<Comment> comments = status.getComments();
        assertThat(comments.size(), is(25));
        assertThat(comments.get(0).canRemove(), is(false));
        assertThat(comments.get(0).getFrom().getId(), is("625087546"));
        assertThat(comments.get(0).getFrom().getName(), is("Kevin Cooke"));
        assertThat(comments.get(0).getId(), is("10150293984393553_18352943"));
        assertThat(comments.get(0).getLikeCount(), is(0));
        assertThat(comments.get(0).getMessage(), is("mogotix is non-responsive.  gateway time out error..."));
        assertThat(comments.get(0).isUserLikes(), is(false));
        assertThat(comments.get(7).canRemove(), is(false));
        assertThat(comments.get(7).getFrom().getId(), is("100000095949504"));
        assertThat(comments.get(7).getFrom().getName(), is("زياد بن علي الرويشان"));
        assertThat(comments.get(7).getId(), is("10150293984393553_18353012"));
        assertThat(comments.get(7).getLikeCount(), is(1));
        assertThat(comments.get(7).getMessage(), is("جميل"));
        assertThat(comments.get(7).isUserLikes(), is(false));
        assertThat(comments.get(24).canRemove(), is(false));
        assertThat(comments.get(24).getFrom().getId(), is("100001590657464"));
        assertThat(comments.get(24).getFrom().getName(), is("Karan Kantaria"));
        assertThat(comments.get(24).getId(), is("10150293984393553_18353134"));
        assertThat(comments.get(24).getLikeCount(), is(0));
        assertThat(comments.get(24).getMessage(), is("aa su chhee."));
        assertThat(comments.get(24).isUserLikes(), is(false));
        assertThat(comments.getPaging().getNext().toString(), is("https://graph.facebook.com/10150293984393553/comments?access_token=access_token&limit=25&after=MzI%3D"));
        assertThat(comments.getPaging().getPrevious(), is(nullValue()));
        assertThat(status.getFrom().getId(), is("19292868552"));
        assertThat(status.getFrom().getName(), is("Facebook Developers"));
//        assertThat(status.getFrom().getCategory(), is("Product/service"));  //TODO
        assertThat(status.getId(), is("10150293984393553"));
        PagableList<IdNameEntity> likes = status.getLikes();
        assertThat(likes.size(), is(25));
        assertThat(likes.get(0).getId(), is("100002589774742"));
        assertThat(likes.get(0).getName(), is("Katsumi Ohmuro"));
        assertThat(likes.get(23).getId(), is("100002947834268"));
        assertThat(likes.get(23).getName(), is("منى الفهد"));
        assertThat(likes.get(24).getId(), is("100002011318315"));
        assertThat(likes.get(24).getName(), is("Ḿǿẩid Cǿǿl"));
        assertThat(likes.getPaging().getNext().toString(), is("https://graph.facebook.com/10150293984393553/likes?access_token=access_token&limit=25&after=MTAwMDAyMDExMzE4MzE1"));
        assertThat(likes.getPaging().getPrevious(), is(nullValue()));
        assertThat(status.getMessage(), is("The 2nd wave of registration is now open for f8, our developer conference on Sept. 22 in San Francisco. We expect them to sell out quickly, so be sure to buy yours now at f8.facebook.com. Also, don't forget to like the f8 Page for the latest updates on the event."));

        assertThat(statuses.getPaging().getNext().toString(), is("https://graph.facebook.com/19292868552/statuses?access_token=access_token&limit=25&until=1251825600&__paging_token=124014878930"));
        assertThat(statuses.getPaging().getPrevious().toString(), is("https://graph.facebook.com/19292868552/statuses?access_token=access_token&limit=25&since=1314979729&__paging_token=10150293984393553&__previous=1"));
    }

    @Test
    public void postStatusMessage_page() throws Exception {
        facebook.setMockJSON("mock_json/post_id.json");
        String postId = facebook.postStatusMessage("137246726435626", "page's status update");
        assertThat(postId, is("137246726435626_185932178233747"));
    }

    @Test
    public void getStatus_statusType() throws Exception {
        facebook.setMockJSON("mock_json/post/with_status_type.json");
        Post post = facebook.getPost("1111_2222");
        assertThat(post.getStatusType(), is("approved_friend"));
    }

/*
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
    public void getFeed_page() throws Exception {
        String pageId = "19292868552"; //The Page for Facebook Platform
        ResponseList<Post> feed = facebook1.getFeed(pageId);
        for (Post post : feed) {
            System.out.println(post);
        }
    }

    @Test
    public void getFeed_page_access_token() throws Exception {
        // require manage_pages permission
        // replace to your page id
        String pageId = "137246726435626";
        ResponseList<Post> feed = real.getFeed();
        for (Post post : feed) {
            System.out.println(post);
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
    public void postFeed() throws Exception {
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
    public void getPosts_Page() throws Exception {
        String pageId = "19292868552"; //The Page for Facebook Platform
        ResponseList<Post> posts = facebook1.getPosts(pageId);
        for (Post post : posts) {
            assertThat(post.getFrom().getId(), is("19292868552"));
        }
    }

    @Test
    public void getPosts_page_access_token() throws Exception {
        // require manage_pages permission
        // replace to your page id
        String pageId = "137246726435626";
        ResponseList<Post> posts = real.getPosts();
        for (Post post : posts) {
            System.out.println(post);
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

    @Test
    public void paging() throws Exception {
        ResponseList<Post> feed = facebook1.getFeed(new Reading().limit(1));
        Post post1 = feed.get(0);
        Paging<Post> paging = feed.getPaging();
        ResponseList<Post> nextPage = facebook1.fetchNext(paging);
        Post post2 = nextPage.get(0);
        assertThat(post1, is(not(post2)));
        Paging<Post> paging2 = nextPage.getPaging();
        ResponseList<Post> previousPage = facebook1.fetchPrevious(paging2);
        Post post3 = previousPage.get(0);
        assertThat(post1, is(post3));
    }

    @Test
    public void likesCount() throws Exception {
        ResponseList<Post> posts = facebook1.getPosts("BillGates", new Reading().fields("likes"));
        for (Post post : posts) {
            assertThat(post.getLikes().getCount() > 0, is(true));
        }
    }

    @Test
    public void commentsCount() throws Exception {
        ResponseList<Post> posts = facebook1.getPosts("BillGates", new Reading().fields("comments"));
        for (Post post : posts) {
            assertThat(post.getComments().getCount() > 0, is(true));
        }
    }

    @Test
    public void sharesCount() throws Exception {
        ResponseList<Post> posts = facebook1.getPosts("BillGates", new Reading().fields("shares"));
        for (Post post : posts) {
            assertThat(post.getSharesCount() > 0, is(true));
        }
    }
*/
}
