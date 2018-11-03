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

import java.io.File;
import java.net.URL;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
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
            assertThat(actual1.canUpload(), is(nullValue()));
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
            assertThat(actual1.canUpload(), is(nullValue()));
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

        @Test
        public void withPicture() throws Exception {
            facebook.setMockJSON("mock_json/album/page_picture.json");
            String pageId = "eclipse";
            ResponseList<Album> albums = facebook.getAlbums(pageId, new Reading().fields("picture{url}"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/eclipse/albums")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "picture{url}"));

            assertThat(albums.get(0).getPicture().getURL().toString(), is("https://scontent.xx.fbcdn.net/v/t1.0-0/s180x540/72971_563871533633265_1735045691_n.jpg?oh=fe352f46567e2df4eae7dc76f8ad7812&oe=596B72F1"));
            assertThat(albums.get(2).getPicture().getURL().toString(), is("https://scontent.xx.fbcdn.net/v/t1.0-0/s180x540/547126_383818641638556_2000440510_n.jpg?oh=55e6692fadc3966980fc885afb511fbe&oe=5966F853"));
        }
    }

    public static class getAlbum extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/album/album.json");
            Album actual = facebook.getAlbum("285019608226998");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/285019608226998")));

            assertThat(actual.getId(), is("222222222222222"));
            assertThat(actual.getCoverPhoto(), is("111111111111111"));
            assertThat(actual.getCount(), is(33));
            assertThat(actual.getName(), is("foursquare Photos"));
            assertThat(actual.getLink().toString(), is("https://www.facebook.com/album.php?fbid=222222222222222&id=100001568838021&aid=69301"));
            assertThat(actual.getPrivacy(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual.canUpload(), is(false));
            assertThat(actual.getFrom().getId(), is("1234567890123456"));
            assertThat(actual.getFrom().getName(), is("Name Name1"));
            assertThat(actual.getType(), is("app"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2012-01-28T02:05:19+0000")));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-07-20T03:36:05+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/album/album_link.json");
            Album actual = facebook.getAlbum("285019608226998");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/285019608226998")));

            assertThat(actual.getId(), is("222222222222222"));
            assertThat(actual.getCoverPhoto(), is(nullValue()));
            assertThat(actual.getCount(), is(nullValue()));
            assertThat(actual.getName(), is(nullValue()));
            assertThat(actual.getLink().toString(), is("https://www.facebook.com/album.php?fbid=222222222222222&id=100001568838021&aid=69301"));
            assertThat(actual.getPrivacy(), is(nullValue()));
            assertThat(actual.canUpload(), is(nullValue()));
            assertThat(actual.getFrom(), is(nullValue()));
            assertThat(actual.getType(), is(nullValue()));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2012-01-28T02:05:19+0000")));
            assertThat(actual.getUpdatedTime(), is(nullValue()));
        }
    }

    public static class getAlbumPhotos extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/album/photos.json");
            ResponseList<Photo> actuals = facebook.getAlbumPhotos("10150146071791729");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10150146071791729/photos")));

            assertThat(actuals.size(), is(25));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StEh3RhPvjk.gif"));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=10150146071831729&set=a.10150146071791729.324257.20531316728&type=1"));
            assertThat(actual1.getWidth(), is(720));
            assertThat(actual1.getFrom().getId(), is("20531316728"));
            assertThat(actual1.getFrom().getCategory(), is("Product/service"));
            assertThat(actual1.getFrom().getName(), is("Facebook"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2011-02-02T17:00:50+0000")));
            assertThat(actual1.getId(), is("10150146071831729"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-g-a.akamaihd.net/hphotos-ak-prn2/168119_10150146071831729_5116892_s.jpg"));
            assertThat(actual1.getHeight(), is(483));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-prn2/168119_10150146071831729_5116892_n.jpg"));
            assertThat(actual1.getLikes().get(0).getId(), is("100004124615974"));
            assertThat(actual1.getLikes().get(0).getName(), is("Flipper Flipper"));
            assertThat(actual1.getLikes().get(10).getId(), is("1309758195"));
            assertThat(actual1.getLikes().get(10).getName(), is("Yanaphat Sasomsup"));
            assertThat(actual1.getLikes().get(20).getId(), is("100003760832386"));
            assertThat(actual1.getLikes().get(20).getName(), is("Francis Leo"));
            assertThat(actual1.getLikes().get(24).getId(), is("1395579847"));
            assertThat(actual1.getLikes().get(24).getName(), is("Huu Thinh"));
            assertThat(actual1.getLikes().getPaging().getNext().toString(), is("https://graph.facebook.com/10150146071831729/likes?access_token=access_token&limit=25&after=MTM5NTU3OTg0Nw%3D%3D"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTM5NTU3OTg0Nw=="));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDA0MTI0NjE1OTc0"));
            assertThat(actual1.getImages().size(), is(8));
            assertThat(actual1.getImages().get(0).getHeight(), is(483));
            assertThat(actual1.getImages().get(0).getWidth(), is(720));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-prn2/168119_10150146071831729_5116892_n.jpg"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-02-02T16:47:46+0000")));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(0).getMessage(), is("wish u all a very happy new year!!\nKung Hei Fat Choi!!!~!"));
            assertThat(actual1.getComments().get(0).getId(), is("10150146071831729_4508512"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("688085523"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Freda LjungBergkamp Wong"));
            assertThat(actual1.getComments().get(0).canRemove(), is(false));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2011-02-02T17:17:42+0000")));
            assertThat(actual1.getComments().get(24).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(24).getMessage(), is("aunque ya estamos en febrero: feliz año nuevo"));
            assertThat(actual1.getComments().get(24).getId(), is("10150146071831729_4579705"));
            assertThat(actual1.getComments().get(24).getLikeCount(), is(1));
            assertThat(actual1.getComments().get(24).getFrom().getId(), is("100001529892712"));
            assertThat(actual1.getComments().get(24).getFrom().getName(), is("Ana Luz Herrera Barrantes"));
            assertThat(actual1.getComments().get(24).canRemove(), is(false));
            assertThat(actual1.getComments().get(24).getCreatedTime(), is(iso8601DateOf("2011-02-10T21:55:22+0000")));
            assertThat(actual1.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/10150146071831729/comments?access_token=access_token&limit=25&after=Mjg%3D"));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("Mjg="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));

            Photo actual25 = actuals.get(24);
            assertThat(actual25.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StEh3RhPvjk.gif"));
            assertThat(actual25.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=10150146074231729&set=a.10150146071791729.324257.20531316728&type=1"));
            assertThat(actual25.getWidth(), is(720));
            assertThat(actual25.getFrom().getId(), is("20531316728"));
            assertThat(actual25.getFrom().getCategory(), is("Product/service"));
            assertThat(actual25.getFrom().getName(), is("Facebook"));
            assertThat(actual25.getUpdatedTime(), is(iso8601DateOf("2011-02-02T17:00:50+0000")));
            assertThat(actual25.getId(), is("10150146074231729"));
            assertThat(actual25.getPicture().toString(), is("https://fbcdn-photos-a-a.akamaihd.net/hphotos-ak-ash3/167155_10150146074231729_4322235_s.jpg"));
            assertThat(actual25.getHeight(), is(480));
            assertThat(actual25.getSource().toString(), is("https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-ash3/167155_10150146074231729_4322235_n.jpg"));
            assertThat(actual25.getLikes().get(0).getId(), is("100002296554927"));
            assertThat(actual25.getLikes().get(0).getName(), is("Joseph Oese"));
            assertThat(actual25.getLikes().get(10).getId(), is("100001293816457"));
            assertThat(actual25.getLikes().get(10).getName(), is("Cleonice Lacerda"));
            assertThat(actual25.getLikes().get(20).getId(), is("529193178"));
            assertThat(actual25.getLikes().get(20).getName(), is("Rózsika Nagy"));
            assertThat(actual25.getLikes().get(24).getId(), is("100004257395674"));
            assertThat(actual25.getLikes().get(24).getName(), is("Ilina Marino"));
            assertThat(actual25.getLikes().getPaging().getNext().toString(), is("https://graph.facebook.com/10150146074231729/likes?access_token=access_token&limit=25&after=MTAwMDA0MjU3Mzk1Njc0"));
            assertThat(actual25.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDA0MjU3Mzk1Njc0"));
            assertThat(actual25.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyMjk2NTU0OTI3"));
            assertThat(actual25.getImages().get(0).getHeight(), is(480));
            assertThat(actual25.getImages().get(0).getWidth(), is(720));
            assertThat(actual25.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-ash3/167155_10150146074231729_4322235_n.jpg"));
            assertThat(actual25.getCreatedTime(), is(iso8601DateOf("2011-02-02T16:51:28+0000")));
            assertThat(actual25.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual25.getComments().get(0).getMessage(), is("cute"));
            assertThat(actual25.getComments().get(0).getId(), is("10150146074231729_4508656"));
            assertThat(actual25.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual25.getComments().get(0).getFrom().getId(), is("1352248795"));
            assertThat(actual25.getComments().get(0).getFrom().getName(), is("Jeffrey Montesa"));
            assertThat(actual25.getComments().get(0).canRemove(), is(false));
            assertThat(actual25.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2011-02-02T17:20:40+0000")));
            assertThat(actual25.getComments().get(24).isUserLikes(), is(false));
            assertThat(actual25.getComments().get(24).getMessage(), is("bonitoooooooosssssssss"));
            assertThat(actual25.getComments().get(24).getId(), is("10150146074231729_4896270"));
            assertThat(actual25.getComments().get(24).getLikeCount(), is(0));
            assertThat(actual25.getComments().get(24).getFrom().getId(), is("100001117971123"));
            assertThat(actual25.getComments().get(24).getFrom().getName(), is("Daniela Desireth Gonzalez"));
            assertThat(actual25.getComments().get(24).canRemove(), is(false));
            assertThat(actual25.getComments().get(24).getCreatedTime(), is(iso8601DateOf("2011-03-29T16:07:35+0000")));
            assertThat(actual25.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/10150146074231729/comments?access_token=access_token&limit=25&after=MzM%3D"));
            assertThat(actual25.getComments().getPaging().getCursors().getAfter(), is("MzM="));
            assertThat(actual25.getComments().getPaging().getCursors().getBefore(), is("NA=="));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/album/photos_source_limit1.json");
            ResponseList<Photo> actuals = facebook.getAlbumPhotos("10150146071791729", new Reading().fields("source").limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10150146071791729/photos")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "source"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("10150146071831729"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-prn2/168119_10150146071831729_5116892_n.jpg"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-02-02T16:47:46+0000")));
        }

    }

    public static class getAlbumComments extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/album/comments.json");
            ResponseList<Comment> actuals = facebook.getAlbumComments("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/comments")));

            assertThat(actuals.size(), is(1));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.isUserLikes(), is(false));
            assertThat(actual1.getMessage(), is("test"));
            assertThat(actual1.getId(), is("1234567890123456_5174487"));
            assertThat(actual1.getLikeCount(), is(0));
            assertThat(actual1.getFrom().getId(), is("100001568838021"));
            assertThat(actual1.getFrom().getName(), is("Ryuji Yamashita"));
            assertThat(actual1.canRemove(), is(true));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-29T09:06:04+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/album/comments_from.json");
            ResponseList<Comment> actuals = facebook.getAlbumComments("1234567890123456", new Reading().fields("from"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "from"));

            assertThat(actuals.size(), is(1));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.isUserLikes(), is(nullValue()));
            assertThat(actual1.getMessage(), is(nullValue()));
            assertThat(actual1.getId(), is("1234567890123456_5174487"));
            assertThat(actual1.getLikeCount(), is(nullValue()));
            assertThat(actual1.getFrom().getId(), is("100001568838021"));
            assertThat(actual1.getFrom().getName(), is("Ryuji Yamashita"));
            assertThat(actual1.canRemove(), is(nullValue()));
            assertThat(actual1.getCreatedTime(), is(nullValue()));
        }
    }

    public static class getAlbumLikes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/album/likes.json");
            ResponseList<Like> actuals = facebook.getAlbumLikes("10150146071791729");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10150146071791729/likes")));

            assertThat(actuals.size(), is(25));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100002157503112"));
            assertThat(actual1.getName(), is("Iveta Frybertova"));
            Like actual10 = actuals.get(9);
            assertThat(actual10.getId(), is("100004624061391"));
            assertThat(actual10.getName(), is("Darren Dunn"));
            Like actual20 = actuals.get(19);
            assertThat(actual20.getId(), is("100003571250095"));
            assertThat(actual20.getName(), is("Abhijit Barhate"));
            Like actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("100000636243141"));
            assertThat(actual25.getName(), is("張家茂"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/album/likes_limit3.json");
            ResponseList<Like> actuals = facebook.getAlbumLikes("10150146071791729", new Reading().limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10150146071791729/likes")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100002157503112"));
            assertThat(actual1.getName(), is("Iveta Frybertova"));
            Like actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100005214576616"));
            assertThat(actual2.getName(), is("Nguyễn Đăng Tú"));
            Like actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100004748435046"));
            assertThat(actual3.getName(), is("Paul Pandi Yadav"));
        }
    }

    public static class createAlbum extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            AlbumUpdate albumUpdate = new AlbumUpdate("test album");
            String actual = facebook.createAlbum(albumUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/albums")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            AlbumUpdate albumUpdate = new AlbumUpdate("test album");
            String actual = facebook.createAlbum("roundrop", albumUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/roundrop/albums")));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class addAlbumPhoto extends MockFacebookTestBase {
        @Test
        public void noMessage() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            Media photo = new Media(new File("src/test/resources/test_image.png"));
            String actual = facebook.addAlbumPhoto("500000000000001", photo);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/photos")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void withMessage() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            Media photo = new Media(new File("src/test/resources/test_image.png"));
            String actual = facebook.addAlbumPhoto("500000000000001", photo, "test photo.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test photo."));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class commentAlbum extends MockFacebookTestBase {
        @Test
        public void comment() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.commentAlbum("500000000000001", "test comment.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test comment."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void byCommentUpdate() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.commentAlbum("500000000000001", new CommentUpdate().message("test"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withAttachmentId() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentId("1122334455667788");
            String actual = facebook.commentAlbum("500000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withAttachmentUrl() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentUrl("https://fortunedotcom.files.wordpress.com/2015/04/467495334.jpg?quality=80&w=840&h=485&crop=1");
            String actual = facebook.commentAlbum("500000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withSource() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .source(new Media(new File("src/test/resources/test_image.png")));
            String actual = facebook.commentAlbum("500000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class likeAlbum extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.likeAlbum("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class unlikeAlbum extends MockFacebookTestBase {
        @Test
        public void unlike() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.unlikeAlbum("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class getAlbumCoverPhoto extends MockFacebookTestBase {
        @Test
        public void test() throws Exception {
            facebook.setMockJSON("mock_json/empty.json");
            URL actual = facebook.getAlbumCoverPhoto("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));

            assertThat(actual.toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash3/644169_573207722741517_740837405_a.jpg"));
        }
    }
    
    public static class albumReactions extends MockFacebookTestBase {
        @Test
        @FacebookAPIVersion("v2.6")
        public void reactions_v26() throws Exception {
            facebook.setMockJSON("mock_json/album/reactions.json");
            
            ResponseList<Reaction> reactions = facebook.getAlbumReactions("10154076568048485");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.6/10154076568048485/reactions")));
            
            assertThat(reactions.size(), is(25));
        }
    }

}
