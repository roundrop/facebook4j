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

public class AlbumMethodsTest extends MockFacebookTestBase {

    @Test
    public void albums_page() throws Exception {
        facebook.setMockJSON("mock_json/album/page.json");
        String pageId = "19292868552";  //Facebook Developers Page
        ResponseList<Album> albums = facebook.getAlbums(pageId);

        assertThat(albums.size(), is(25));

        Album wall = albums.get(0);
        assertThat(wall.canUpload(), is(false));
        assertThat(wall.getCount(), is(83));
        assertThat(wall.getCoverPhoto(), is("10151473999083553"));
        assertThat(wall.getFrom().getCategory(), is("Product/service"));
        assertThat(wall.getFrom().getId(), is("19292868552"));
        assertThat(wall.getFrom().getName(), is("Facebook Developers"));
        assertThat(wall.getId(), is("441861428552"));
        assertThat(wall.getLikes().size(), is(25));
        assertThat(wall.getLikes().get(0).getId(), is("100000083608879"));
        assertThat(wall.getLikes().get(0).getName(), is("Abhishek Singh"));
        assertThat(wall.getLikes().get(1).getId(), is("100003468963517"));
        assertThat(wall.getLikes().get(1).getName(), is("Miloud Miloudi"));
        assertThat(wall.getLikes().get(24).getId(), is("100003405404963"));
        assertThat(wall.getLikes().get(24).getName(), is("Luisa Fedorova"));
        assertThat(wall.getLikes().getPaging().getNext().toString(), is("https://graph.facebook.com/441861428552/likes?access_token=access_token&limit=25&after=MTAwMDAzNDA1NDA0OTYz"));
        assertThat(wall.getLink().toString(), is("http://www.facebook.com/album.php?fbid=441861428552&id=19292868552&aid=204523"));
        assertThat(wall.getName(), is("Timeline Photos"));
        assertThat(wall.getType(), is("wall"));
        assertThat(wall.getComments().size(), is(0));
        assertThat(wall.getLocation(), is(nullValue()));

        Album normal = albums.get(1);
        assertThat(normal.canUpload(), is(false));
        assertThat(normal.getComments().size(), is(12));
        assertThat(normal.getComments().get(0).canRemove(), is(false));
        assertThat(normal.getComments().get(0).getFrom().getId(), is("100002271628908"));
        assertThat(normal.getComments().get(0).getFrom().getName(), is("Luciene Silva Silvino Silvino"));
        assertThat(normal.getComments().get(0).getId(), is("10150835335273553_22232799"));
        assertThat(normal.getComments().get(0).getLikeCount(), is(0));
        assertThat(normal.getComments().get(0).getMessage(), is("que diferente isso;;;;"));
        assertThat(normal.getComments().get(0).isUserLikes(), is(false));
        assertThat(normal.getComments().get(1).canRemove(), is(false));
        assertThat(normal.getComments().get(1).getFrom().getId(), is("100003832809175"));
        assertThat(normal.getComments().get(1).getFrom().getName(), is("Ha Lee"));
        assertThat(normal.getComments().get(1).getId(), is("10150835335273553_22256630"));
        assertThat(normal.getComments().get(1).getLikeCount(), is(1));
        assertThat(normal.getComments().get(1).getMessage(), is("tot"));
        assertThat(normal.getComments().get(11).canRemove(), is(false));
        assertThat(normal.getComments().get(11).getFrom().getId(), is("100000145824884"));
        assertThat(normal.getComments().get(11).getFrom().getName(), is("ĤeşĦàm Shëhàb"));
        assertThat(normal.getComments().get(11).getId(), is("10150835335273553_25654551"));
        assertThat(normal.getComments().get(11).getLikeCount(), is(1));
        assertThat(normal.getComments().get(11).getMessage(), is("butiful designes \\"));
        assertThat(normal.getCount(), is(10));
        assertThat(normal.getCoverPhoto(), is("10151411510568553"));
        assertThat(normal.getFrom().getCategory(), is("Product/service"));
        assertThat(normal.getFrom().getId(), is("19292868552"));
        assertThat(normal.getFrom().getName(), is("Facebook Developers"));
        assertThat(normal.getId(), is("10150835335273553"));
        assertThat(normal.getLikes().size(), is(25));
        assertThat(normal.getLink().toString(), is("http://www.facebook.com/album.php?fbid=10150835335273553&id=19292868552&aid=405490"));
        assertThat(normal.getName(), is("Cover Photos"));
        assertThat(normal.getType(), is("normal"));
        assertThat(wall.getLocation(), is(nullValue()));

        Album withLocation = albums.get(23);
        assertThat(withLocation.getLocation(), is("Paris, France"));

    }

/*
    @Test
    public void create() throws Exception {
        PrivacyParameter privacy = new PrivacyBuilder().setValue(PrivacyType.EVERYONE).build();
        String albumId = facebook1.createAlbum(new AlbumCreate("create() test album", "create() test message", privacy));
        assertThat(albumId, is(notNullValue()));
    }

    @Test(expected = FacebookException.class)
    public void createByOtherUser() throws Exception {
        facebook2.createAlbum(id1.getId(), new AlbumCreate("test album", "test message"));
    }

    @Test
    public void get() throws Exception {
        //read
        ResponseList<Album> albums = facebook1.getAlbums();
        assertThat(albums.size() >= 3, is(true));
        for (Album album : albums) {
            System.out.println(album);
        }

        //use fields parameter
        albums = facebook1.getAlbums(new Reading().fields("name", "link"));
        assertThat(albums.size() >= 3, is(true));
        for (Album album : albums) {
            System.out.println(album);
        }

        //read by other user
        assertThat(facebook2.getAlbums(id1.getId()).size() >= 3, is(true));

        //read single album
        Album album2_1 = facebook1.getAlbum(albums.get(1).getId());
        assertThat(album2_1, is(notNullValue()));
        assertThat(album2_1.getName(), is("test album2"));

        Album album2_2 = facebook2.getAlbum(albums.get(1).getId(), new Reading().fields("id", "name", "description"));
        assertThat(album2_1, is(album2_2));

    }

    @Test
    public void photo() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();

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
        assertThat(albumPhotos.size() >= 2, is(true));
        albumPhotos = facebook1.getAlbumPhotos(albumId, new Reading().fields("name"));
        for (Photo photo : albumPhotos) {
            if (photo.getId().equals(photoId2)) {
                assertThat(photo.getName(), is("photo no2"));
            }
        }

    }

    @Test
    public void comment() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();

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
        assertThat(comments.size() >= 3, is(true));
    }

    @Test
    public void like() throws Exception {
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();

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
        ResponseList<Album> albums = facebook1.getAlbums();
        String albumId = albums.get(0).getId();

        URL url = facebook1.getAlbumCoverPhoto(albumId);
        assertThat(url, is(notNullValue()));
    }
*/
}
