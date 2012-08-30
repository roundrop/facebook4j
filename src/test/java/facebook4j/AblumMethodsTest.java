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

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AblumMethodsTest extends GraphAPITestBase {

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
    public void create() throws Exception {
        PrivacyBean privacy = new PrivacyBuilder().setValue("EVERYONE").build();
        String albumId = facebook1.createAlbum(new AlbumCreate("test album1", "test message1", privacy));
        assertThat(albumId, is(notNullValue()));
    }

    @Test(expected = FacebookException.class)
    public void createByOtherUser() throws Exception {
        facebook2.createAlbum(testUser1.getId(), new AlbumCreate("test album", "test message"));
    }

    @Test
    public void get() throws Exception {
        facebook1.createAlbum(new AlbumCreate("test album1", "test message1"));
        facebook1.createAlbum(new AlbumCreate("test album2", "test message2"));
        facebook1.createAlbum(new AlbumCreate("test album3", "test message3"));

        //read
        ResponseList<Album> albums = facebook1.getAlbums();
        assertThat(albums.size(), is(3));
        int i = 3;
        for (Album album : albums) {
//            System.out.println(album);
            assertThat(album.getName(), is("test album" + i));
            assertThat(album.getDescription(), is("test message" + i));
            i--;
        }

        //use fields parameter
        albums = facebook1.getAlbums(new Reading().fields("name", "link"));
        assertThat(albums.size(), is(3));
        i = 3;
        for (Album album : albums) {
            assertThat(album.getName(), is("test album" + i));
            assertThat(album.getDescription(), is(nullValue()));
            assertThat(album.getLink(), is(notNullValue()));
            i--;
        }
        
        //read by other user
        assertThat(facebook2.getAlbums(testUser1.getId()).size(), is(3));
        
        //read single album
        Album album2_1 = facebook1.getAlbum(albums.get(1).getId());
        assertThat(album2_1, is(notNullValue()));
        assertThat(album2_1.getName(), is("test album2"));
        
        Album album2_2 = facebook2.getAlbum(albums.get(1).getId(), new Reading().fields("id", "name", "description"));
        assertThat(album2_1, is(album2_2));
        
    }
    
    @Test
    public void photo() throws Exception {
        String albumId = facebook1.createAlbum(new AlbumCreate("test album1", "test message1"));
        
        //add a photo
        File file = new File("src/test/resources/test_image.png");
        Media source = new Media(file);
        String photoId1 = facebook1.addAlbumPhoto(albumId, source);
        assertThat(photoId1, is(notNullValue()));
        String photoId2 = facebook1.addAlbumPhoto(albumId, source, "photo no2");
        assertThat(photoId2, is(notNullValue()));
        
        //read photos from album
        ResponseList<Photo> albumPhotos = facebook1.getAlbumPhotos(albumId);
//        System.out.println(albumPhotos);
        assertThat(albumPhotos.size(), is(2));
        assertThat(facebook1.getAlbumPhotos(albumId, new Reading().fields("name")).get(1).getName(), is("photo no2"));
    }
    
    @Test
    public void comment() throws Exception {
        String albumId = facebook1.createAlbum(new AlbumCreate("test album1", "test message1"));
        
        //comment
        //TODO cannot comment by other test-user, why?
        //album:aid=100004272091863_1826/object_id=100651570087246/owner=100004272091863
//        String commentId1 = facebook2.commentAlbum("100004272091863_100651570087246", "comment1");
//        assertThat(commentId1, is(notNullValue()));
//        String commentId2 = facebook2.commentAlbum(albumId, "comment2");
//        assertThat(commentId2, is(notNullValue()));
//        String commentId3 = facebook2.commentAlbum(albumId, "comment3");
//        assertThat(commentId3, is(notNullValue()));
        String commentId1 = facebook1.commentAlbum(albumId, "comment1");
        assertThat(commentId1, is(notNullValue()));
        String commentId2 = facebook1.commentAlbum(albumId, "comment2");
        assertThat(commentId2, is(notNullValue()));
        String commentId3 = facebook1.commentAlbum(albumId, "comment3");
        assertThat(commentId3, is(notNullValue()));
        
        //read comments
        ResponseList<Comment> comments = facebook1.getAlbumComments(albumId);
        assertThat(comments.size(), is(3));
    }
    
    @Test
    public void like() throws Exception {
        String albumId = facebook1.createAlbum(new AlbumCreate("test album1", "test message1"));
        
        //like
        //TODO cannot read by other test-user, why?
//        boolean likeId = facebook2.likeAlbum(albumId);
//        assertThat(likeId, is(notNullValue()));
        boolean likeResult = facebook1.likeAlbum(albumId);
        assertThat(likeResult, is(true));
        
        //read like
        ResponseList<Like> likes = facebook1.getAlbumLikes(albumId);
        assertThat(likes.size(), is(1));
        
        //unlike
        boolean unlikeResult = facebook1.unlikeAlbum(albumId);
        assertThat(unlikeResult, is(true));
        assertThat(facebook1.getAlbumLikes(albumId).size(), is(0));
    }
    
    @Test
    public void picture() throws Exception {
        String albumId = facebook1.createAlbum(new AlbumCreate("test album1", "test message1"));
        File file = new File("src/test/resources/test_image.png");
        Media source = new Media(file);
        facebook1.addAlbumPhoto(albumId, source);
        facebook1.addAlbumPhoto(albumId, source, "photo no2");

        URL url = facebook1.getAlbumCoverPhoto(albumId);
        assertThat(url, is(notNullValue()));
    }

}
