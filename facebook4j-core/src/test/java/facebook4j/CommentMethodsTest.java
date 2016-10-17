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
import facebook4j.junit.FacebookAPIVersion;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class CommentMethodsTest {

    public static class getComment extends MockFacebookTestBase {
        @Test
        public void simple() throws Exception {
            facebook.setMockJSON("mock_json/comment/simple.json");
            Comment actual = facebook.getComment("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));

            assertThat(actual.isUserLikes(), is(false));
            assertThat(actual.getMessage(), is("Enjoy!!"));
            assertThat(actual.getId(), is("100000000000001_50000001"));
            assertThat(actual.getLikeCount(), is(1));
            assertThat(actual.getFrom().getId(), is("1234567890123456"));
            assertThat(actual.getFrom().getName(), is("Name Name1"));
            assertThat(actual.canRemove(), is(false));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-08-07T04:08:42+0000")));
        }

        @Test
        public void moreFields() throws Exception {
            facebook.setMockJSON("mock_json/comment/more_fields.json");
            Comment actual = facebook.getComment("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));

            assertThat(actual.canComment(), is(true));
            assertThat(actual.canHide(), is(false));
            assertThat(actual.canLike(), is(true));
            assertThat(actual.isHidden(), is(false));
            assertThat(actual.getMessageTags(), is(notNullValue()));
            assertThat(actual.getMessageTags().size(), is(1));
            assertThat(actual.getMessageTags().get(0).getId(), is("1111111111111111"));
            assertThat(actual.getMessageTags().get(0).getLength(), is(15));
            assertThat(actual.getMessageTags().get(0).getName(), is("Ryuji Yamashita"));
            assertThat(actual.getMessageTags().get(0).getOffset(), is(0));
            assertThat(actual.getMessageTags().get(0).getType(), is("user"));
            assertThat(actual.getCommentCount(), is(1));
        }

        @Test
        public void simpleWithReplies() throws Exception {
            facebook.setMockJSON("mock_json/comment/simple_with_replies.json");
            Comment actual = facebook.getComment("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));

            assertThat(actual.getMessage(), is("Enjoy!!"));
            assertThat(actual.getId(), is("100000000000001_50000001"));

            PagableList<Comment> replyComments = actual.getComments();
            assertThat(replyComments.size(), is(1));
            Comment replyComment = replyComments.get(0);
            assertThat(replyComment.getMessage(), is("Thank you!"));
            assertThat(replyComment.getId(), is("100000000000001_50000002"));
            assertThat(replyComment.getCreatedTime(), is(iso8601DateOf("2013-09-27T14:16:41+0000")));
            assertThat(replyComment.getFrom().getId(), is("0123456789012345"));
            assertThat(replyComment.getFrom().getName(), is("Name Name2"));
        }

        @Test
        public void reply() throws Exception {
            facebook.setMockJSON("mock_json/comment/reply.json");
            Comment actual = facebook.getComment("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));

            assertThat(actual.getParent(), is(notNullValue()));
            assertThat(actual.getParent().getId(), is("773931769335777_1020021014726850"));
            assertThat(actual.getParent().getMessage(), is("test"));
            assertThat(actual.getParent().getFrom().getId(), is("100001568838021"));
            assertThat(actual.getParent().getFrom().getName(), is("Ryuji Yamashita"));
        }

        @Test
        public void attachment_image() throws Exception {
            facebook.setMockJSON("mock_json/comment/with_attachment_image.json");
            Comment actual = facebook.getComment("100000000000001_50000001", new Reading().fields("attachment"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "attachment"));

            assertThat(actual.getAttachment(), is(notNullValue()));
            Comment.Attachment attachment = actual.getAttachment();
            assertThat(attachment.getDescription(), is(nullValue()));
            assertThat(attachment.getMedia(), is(notNullValue()));
            assertThat(attachment.getMedia().getImage(), is(notNullValue()));
            assertThat(attachment.getMedia().getImage().getHeight(), is(415));
            assertThat(attachment.getMedia().getImage().getWidth(), is(720));
            assertThat(attachment.getMedia().getImage().getSource().toString(), is("https://scontent.xx.fbcdn.net/hphotos-xtp1/v/t1.0-9/s720x720/12122769_1020022801393338_4909706565847005933_n.jpg?oh=03c45d24967cad9e0961590187f097ae&oe=56C2EAE7"));
            assertThat(attachment.getTarget(), is(notNullValue()));
            assertThat(attachment.getTarget().getId(), is("1020022801393338"));
            assertThat(attachment.getTarget().getUrl(), is("https://www.facebook.com/photo.php?fbid=1020022801393338&set=p.1020022801393338&type=3"));
            assertThat(attachment.getTitle(), is(nullValue()));
            assertThat(attachment.getType(), is("photo"));
            assertThat(attachment.getUrl(), is("https://www.facebook.com/photo.php?fbid=1020022801393338&set=p.1020022801393338&type=3"));
        }

        @Test
        public void attachment_link() throws Exception {
            facebook.setMockJSON("mock_json/comment/with_attachment_link.json");
            Comment actual = facebook.getComment("100000000000001_50000001", new Reading().fields("attachment"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "attachment"));

            assertThat(actual.getAttachment(), is(notNullValue()));
            Comment.Attachment attachment = actual.getAttachment();
            assertThat(attachment.getDescription(), is("GoogleDoodle"));
            assertThat(attachment.getMedia(), is(notNullValue()));
            assertThat(attachment.getMedia().getImage(), is(notNullValue()));
            assertThat(attachment.getMedia().getImage().getHeight(), is(230));
            assertThat(attachment.getMedia().getImage().getWidth(), is(230));
            assertThat(attachment.getMedia().getImage().getSource().toString(), is("https://external.xx.fbcdn.net/safe_image.php?d=AQDIW-14fE-a1cwy&w=720&h=720&url=http%3A%2F%2Fwww.google.com%2Flogos%2Fdoodles%2F2015%2Fhedy-lamarrs-101st-birthday-5679746450980864.3-thp.png&cfs=1"));
            assertThat(attachment.getTarget(), is(notNullValue()));
            assertThat(attachment.getTarget().getId(), is(nullValue()));
            assertThat(attachment.getTarget().getUrl(), is("http://www.facebook.com/l.php?u=http%3A%2F%2Fwww.google.co.jp%2F&h=KAQHrD88-&s=1&enc=AZNU4JqjNy5HJT4ATtYCutTMGWjv3RS2hjwAoeViE0UfGJ2n8FjyPB95XKmkq2UM1kupwqYvJNypXkpCHj1UbbVFCtTOWcCYUk6uM9LxMgQTwQ"));
            assertThat(attachment.getTitle(), is("Google"));
            assertThat(attachment.getType(), is("share"));
            assertThat(attachment.getUrl(), is("http://www.facebook.com/l.php?u=http%3A%2F%2Fwww.google.co.jp%2F&h=KAQHrD88-&s=1&enc=AZNU4JqjNy5HJT4ATtYCutTMGWjv3RS2hjwAoeViE0UfGJ2n8FjyPB95XKmkq2UM1kupwqYvJNypXkpCHj1UbbVFCtTOWcCYUk6uM9LxMgQTwQ"));
        }
    }

    public static class getCommentReplies extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/comment/replies.json");
            ResponseList<Comment> actual = facebook.getCommentReplies("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001/comments")));

            assertThat(actual.size(), is(1));
            assertThat(actual.get(0).getId(), is("100000000000001_50000001"));
            assertThat(actual.get(0).canRemove(), is(true));
            assertThat(actual.get(0).getFrom().getId(), is("100001568838021"));
            assertThat(actual.get(0).getFrom().getName(), is("Ryuji Yamashita"));
            assertThat(actual.get(0).getLikeCount(), is(0));
            assertThat(actual.get(0).getMessage(), is("reply"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/comment/replies.json");
            ResponseList<Comment> actual = facebook.getCommentReplies("100000000000001_50000001", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actual.size(), is(1));
            assertThat(actual.get(0).getId(), is("100000000000001_50000001"));
            assertThat(actual.get(0).canRemove(), is(true));
            assertThat(actual.get(0).getFrom().getId(), is("100001568838021"));
            assertThat(actual.get(0).getFrom().getName(), is("Ryuji Yamashita"));
            assertThat(actual.get(0).getLikeCount(), is(0));
            assertThat(actual.get(0).getMessage(), is("reply"));
        }
    }

    public static class deleteComment extends MockFacebookTestBase {
        @Test
        public void delete() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteComment("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_50000001")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void delete_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteComment("100000000000001_50000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/100000000000001_50000001")));

            assertThat(actual, is(true));
        }
    }

    public static class getCommentLikes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/comment/likes.json");
            ResponseList<Like> actuals = facebook.getCommentLikes("100000000000001_90000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_90000001/likes")));

            assertThat(actuals.size(), is(2));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Name Name1"));
            Like actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000000002"));
            assertThat(actual2.getName(), is("Name Name2"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/comment/likes_limit1.json");
            ResponseList<Like> actuals = facebook.getCommentLikes("100000000000001_90000001", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_90000001/likes")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Name Name1"));
        }
    }

    public static class likeComment extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.likeComment("100000000000001_90000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_90000001/likes")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.likeComment("100000000000001_90000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/100000000000001_90000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class unlikeComment extends MockFacebookTestBase {
        @Test
        public void unlike() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.unlikeComment("100000000000001_90000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100000000000001_90000001/likes")));

            assertThat(actual, is(true));
        }
    }

/*
    @Test
    public void get() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        ResponseList<Comment> albumComments = facebook1.getAlbumComments(albums.get(0).getId());

        for (Comment comment : albumComments) {
            Comment actual = facebook1.getComment(comment.getId());
            assertThat(actual.getMessage(), is(comment.getMessage()));
        }

    }

    @Test
    public void delete() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();
        String commentId = facebook1.commentAlbum(albumId, "Comment on an Album");

        boolean deleteResult = facebook1.deleteComment(commentId);
        assertThat(deleteResult, is(true));
    }

    @Test
    public void like() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();
        String commentId = facebook1.commentAlbum(albumId, "Comment on an Album");

        boolean likeResult = facebook1.likeComment(commentId);
        assertThat(likeResult, is(true));

        ResponseList<Like> commentLikes = facebook1.getCommentLikes(commentId);
        assertThat(commentLikes.size(), is(1));

        boolean unlikeResult = facebook1.unlikeComment(commentId);
        assertThat(unlikeResult, is(true));

        commentLikes = facebook1.getCommentLikes(commentId);
        assertThat(commentLikes.size(), is(0));

        facebook1.deleteComment(commentId);
    }
*/

}
