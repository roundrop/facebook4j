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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommentMethodsTest extends FacebookTestBase {
    
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
    public void get() throws Exception {
        AlbumCreate album = new AlbumCreate("test album", "test message");
        String albumId = facebook1.createAlbum(album);
        String commentId = facebook1.commentAlbum(albumId, "Comment on an Album");
        
        Comment comment = facebook1.getComment(commentId);
        assertThat(comment.getMessage(), is("Comment on an Album"));
    }

    @Test
    public void delete() throws Exception {
        AlbumCreate album = new AlbumCreate("test album", "test message");
        String albumId = facebook1.createAlbum(album);
        String commentId = facebook1.commentAlbum(albumId, "Comment on an Album");
        
        boolean deleteResult = facebook1.deleteComment(commentId);
        assertThat(deleteResult, is(true));
    }

    @Test
    public void like() throws Exception {
        AlbumCreate album = new AlbumCreate("test album", "test message");
        String albumId = facebook1.createAlbum(album);
        String commentId = facebook1.commentAlbum(albumId, "Comment on an Album");
        
        boolean likeResult = facebook1.likeComment(commentId);
        assertThat(likeResult, is(true));

        ResponseList<Like> commentLikes = facebook1.getCommentLikes(commentId);
        assertThat(commentLikes.size(), is(1));
        
        boolean unlikeResult = facebook1.unlikeComment(commentId);
        assertThat(unlikeResult, is(true));
        
        commentLikes = facebook1.getCommentLikes(commentId);
        assertThat(commentLikes.size(), is(0));
    }
    

}
