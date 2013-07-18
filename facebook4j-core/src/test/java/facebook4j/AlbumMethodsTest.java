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
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class AlbumMethodsTest {

    public static class getAlbums extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/album/albums.json");
            ResponseList<Album> actuals = facebook.getAlbums();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/albums")));

            assertThat(actuals.size(), is(5));
            Album actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111000000001"));
            assertThat(actual1.getCoverPhoto(), is("1000000000001"));
            assertThat(actual1.getCount(), is(34));
            assertThat(actual1.getName(), is("Mobile Uploads"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/album.php?fbid=1111000001&id=11111000001&aid=50926"));
            assertThat(actual1.getPrivacy(), is(PrivacyType.EVERYONE));
            assertThat(actual1.canUpload(), is(false));
            assertThat(actual1.getFrom().getId(), is("11000000000001"));
            assertThat(actual1.getFrom().getName(), is("Taro Yamada"));
            assertThat(actual1.getType(), is("mobile"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-07-07T08:01:44+0000")));
            Album actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("2220000000001"));
            assertThat(actual2.getCoverPhoto(), is("20000000001"));
            assertThat(actual2.getCount(), is(32));
            assertThat(actual2.getName(), is("foursquare Photos"));
            assertThat(actual2.getLink().toString(), is("http://www.facebook.com/album.php?fbid=22220000001&id=2222200001&aid=69301"));
            assertThat(actual2.getPrivacy(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual2.canUpload(), is(false));
            assertThat(actual2.getFrom().getId(), is("11000000000001"));
            assertThat(actual2.getFrom().getName(), is("Taro Yamada"));
            assertThat(actual2.getType(), is("app"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-01-28T02:05:19+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-05-05T05:48:11+0000")));
            Album actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("3330000000001"));
            assertThat(actual3.getCoverPhoto(), is("3000000000001"));
            assertThat(actual3.getCount(), is(6));
            assertThat(actual3.getName(), is("Timeline Photos"));
            assertThat(actual3.getLink().toString(), is("http://www.facebook.com/album.php?fbid=3333000001&id=3333300001&aid=60658"));
            assertThat(actual3.getPrivacy(), is(PrivacyType.EVERYONE));
            assertThat(actual3.canUpload(), is(false));
            assertThat(actual3.getFrom().getId(), is("11000000000001"));
            assertThat(actual3.getFrom().getName(), is("Taro Yamada"));
            assertThat(actual3.getType(), is("wall"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2011-11-11T16:56:08+0000")));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-03-04T16:04:16+0000")));
            Album actual4 = actuals.get(3);
            assertThat(actual4.getId(), is("444000000001"));
            assertThat(actual4.getCoverPhoto(), is("40000000000001"));
            assertThat(actual4.getCount(), is(2));
            assertThat(actual4.getName(), is("Profile Pictures"));
            assertThat(actual4.getLink().toString(), is("http://www.facebook.com/album.php?fbid=44440000001&id=4444400001&aid=36789"));
            assertThat(actual4.getPrivacy(), is(PrivacyType.EVERYONE));
            assertThat(actual4.canUpload(), is(false));
            assertThat(actual4.getFrom().getId(), is("11000000000001"));
            assertThat(actual4.getFrom().getName(), is("Taro Yamada"));
            assertThat(actual4.getType(), is("profile"));
            assertThat(actual4.getCreatedTime(), is(iso8601DateOf("2011-04-21T11:44:43+0000")));
            assertThat(actual4.getUpdatedTime(), is(iso8601DateOf("2013-01-25T14:30:59+0000")));
            Album actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("555000000001"));
            assertThat(actual5.getCoverPhoto(), is("50000000000001"));
            assertThat(actual5.getCount(), is(2));
            assertThat(actual5.getName(), is("Cover Photos"));
            assertThat(actual5.getLink().toString(), is("http://www.facebook.com/album.php?fbid=55550000001&id=5555500001&aid=69618"));
            assertThat(actual5.getPrivacy(), is(PrivacyType.EVERYONE));
            assertThat(actual5.canUpload(), is(false));
            assertThat(actual5.getFrom().getId(), is("11000000000001"));
            assertThat(actual5.getFrom().getName(), is("Taro Yamada"));
            assertThat(actual5.getType(), is("unknown"));
            assertThat(actual5.getCreatedTime(), is(iso8601DateOf("2012-01-30T15:13:07+0000")));
            assertThat(actual5.getUpdatedTime(), is(iso8601DateOf("2012-05-22T17:13:52+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/album/albums_link_limit1.json");
            ResponseList<Album> actuals = facebook.getAlbums(new Reading().fields("link").limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/albums")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "link"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Album actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111000000001"));
            assertThat(actual1.getCoverPhoto(), is(nullValue()));
            assertThat(actual1.getCount(), is(nullValue()));
            assertThat(actual1.getName(), is(nullValue()));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/album.php?fbid=1111000001&id=11111000001&aid=50926"));
            assertThat(actual1.getPrivacy(), is(nullValue()));
            assertThat(actual1.canUpload(), is(false));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getType(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/album/albums.json");
            ResponseList<Album> actuals = facebook.getAlbums("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/albums")));

            assertThat(actuals.size(), is(5));
            Album actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111000000001"));
            assertThat(actual1.getCoverPhoto(), is("1000000000001"));
            assertThat(actual1.getCount(), is(34));
            assertThat(actual1.getName(), is("Mobile Uploads"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/album.php?fbid=1111000001&id=11111000001&aid=50926"));
            assertThat(actual1.getPrivacy(), is(PrivacyType.EVERYONE));
            assertThat(actual1.canUpload(), is(false));
            assertThat(actual1.getFrom().getId(), is("11000000000001"));
            assertThat(actual1.getFrom().getName(), is("Taro Yamada"));
            assertThat(actual1.getType(), is("mobile"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-07-07T08:01:44+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/album/albums_link_limit1.json");
            ResponseList<Album> actuals = facebook.getAlbums("1234567980123456", new Reading().fields("link").limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567980123456/albums")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "link"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Album actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111000000001"));
            assertThat(actual1.getCoverPhoto(), is(nullValue()));
            assertThat(actual1.getCount(), is(nullValue()));
            assertThat(actual1.getName(), is(nullValue()));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/album.php?fbid=1111000001&id=11111000001&aid=50926"));
            assertThat(actual1.getPrivacy(), is(nullValue()));
            assertThat(actual1.canUpload(), is(false));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getType(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
        }

        @Test
        public void page() throws Exception {
            facebook.setMockJSON("mock_json/album/page.json");
            String pageId = "19292868552";
            ResponseList<Album> albums = facebook.getAlbums(pageId);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/19292868552/albums")));

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
