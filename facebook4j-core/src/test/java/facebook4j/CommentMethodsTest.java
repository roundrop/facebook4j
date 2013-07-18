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

public class CommentMethodsTest {
    
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
