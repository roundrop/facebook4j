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
import java.util.Arrays;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class PhotoMethodsTest {
    public static class getUploadedPhotos extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/photo/uploaded.json");
            ResponseList<Photo> actuals = facebook.getUploadedPhotos();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos/uploaded")));

            assertThat(actuals.size(), is(3));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StStStStStSt.gif"));
            assertThat(actual1.getWidth(), is(405));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=600000000000001&set=a.196287157100244.50926.1234567890123456&type=1"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("My Name"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-10-12T01:28:40+0000")));
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn2/1375777_600000000000001_2091899828_s.jpg"));
            assertThat(actual1.getHeight(), is(720));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-prn2/s720x720/1375777_600000000000001_2091899828_n.jpg"));
            assertThat(actual1.getLikes().get(0).getId(), is("200000000000003"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().get(1).getId(), is("200000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("200000000000001"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwMzI1Mzk4NzAw"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAxNjQ4NjIzODgx"));
            assertThat(actual1.getName(), is("too hot."));
            assertThat(actual1.getImages().size(), is(8));
            assertThat(actual1.getImages().get(0).getHeight(), is(960));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-prn2/1375777_600000000000001_2091899828_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(540));
            assertThat(actual1.getImages().get(7).getHeight(), is(130));
            assertThat(actual1.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn2/s75x225/1375777_600000000000001_2091899828_s.jpg"));
            assertThat(actual1.getImages().get(7).getWidth(), is(73));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-10-12T01:28:39+0000")));
            assertThat(actual1.getComments().size(), is(2));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual1.getComments().get(0).getMessage(), is("Are you ready?"));
            assertThat(actual1.getComments().get(0).getId(), is("600000000000001_1907203"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Comment Name1"));
            assertThat(actual1.getComments().get(0).canRemove(), is(true));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-10-12T02:49:42+0000")));
            assertThat(actual1.getComments().get(1).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(1).getMessage(), is("y"));
            assertThat(actual1.getComments().get(1).getId(), is("600000000000001_1907883"));
            assertThat(actual1.getComments().get(1).getLikeCount(), is(1));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("My Name"));
            assertThat(actual1.getComments().get(1).canRemove(), is(true));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-10-12T13:27:42+0000")));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("Mg=="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Photo actual3 = actuals.get(2);
            assertThat(actual3.getPicture().toString(), is("https://fbcdn-photos-d-a.akamaihd.net/hphotos-ak-prn2/1236436_600000000000003_914930274_s.jpg"));
            assertThat(actual3.getId(), is("600000000000003"));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StStStStSt.gif"));
            assertThat(actual3.getHeight(), is(720));
            assertThat(actual3.getSource().toString(), is("https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-prn2/s720x720/1236436_600000000000003_914930274_n.jpg"));
            assertThat(actual3.getWidth(), is(405));
            assertThat(actual3.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=600000000000003&set=a.196287157100244.50926.1234567890123456&type=1"));
            assertThat(actual3.getImages().size(), is(8));
            assertThat(actual3.getImages().get(0).getHeight(), is(960));
            assertThat(actual3.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-prn2/1236436_600000000000003_914930274_n.jpg"));
            assertThat(actual3.getImages().get(0).getWidth(), is(540));
            assertThat(actual3.getImages().get(7).getHeight(), is(130));
            assertThat(actual3.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-d-a.akamaihd.net/hphotos-ak-prn2/s75x225/1236436_600000000000003_914930274_s.jpg"));
            assertThat(actual3.getImages().get(7).getWidth(), is(73));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("My Name"));
            assertThat(actual3.getPlace().getId(), is("426325280037"));
            assertThat(actual3.getPlace().getLocation().getZip(), is("94103"));
            assertThat(actual3.getPlace().getLocation().getStreet(), is("747 Howard Street"));
            assertThat(actual3.getPlace().getLocation().getState(), is("CA"));
            assertThat(actual3.getPlace().getLocation().getLongitude(), is(-122.401426));
            assertThat(actual3.getPlace().getLocation().getLatitude(), is(37.784076));
            assertThat(actual3.getPlace().getLocation().getCountry(), is("United States"));
            assertThat(actual3.getPlace().getLocation().getCity(), is("San Francisco"));
            assertThat(actual3.getPlace().getName(), is("Moscone Center"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2013-09-22T00:38:55+0000")));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-09-22T00:38:55+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/uploaded_images.json");
            ResponseList<Photo> actuals = facebook.getUploadedPhotos(new Reading().fields("album").fields("images").limit(2));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos/uploaded")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "album,images"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "2"));

            assertThat(actuals.size(), is(2));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getAlbum().getId(), is("191919191919191"));
            assertThat(actual1.getAlbum().getName(), is("Mobile Uploads"));
            assertThat(actual1.getAlbum().getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual1.getImages().size(), is(8));
            assertThat(actual1.getImages().get(0).getHeight(), is(960));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-prn2/1375777_600000000000001_2091899828_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(540));
            assertThat(actual1.getImages().get(7).getHeight(), is(130));
            assertThat(actual1.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn2/s75x225/1375777_600000000000001_2091899828_s.jpg"));
            assertThat(actual1.getImages().get(7).getWidth(), is(73));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-10-12T01:28:39+0000")));
            Photo actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("600000000000002"));
            assertThat(actual2.getAlbum().getId(), is("191919191919191"));
            assertThat(actual2.getAlbum().getName(), is("Mobile Uploads"));
            assertThat(actual2.getAlbum().getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual2.getImages().size(), is(8));
            assertThat(actual2.getImages().get(0).getHeight(), is(720));
            assertThat(actual2.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-prn2/1186300_600000000000002_1541195281_n.jpg"));
            assertThat(actual2.getImages().get(0).getWidth(), is(960));
            assertThat(actual2.getImages().get(7).getHeight(), is(97));
            assertThat(actual2.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-prn2/s75x225/1186300_600000000000002_1541195281_s.jpg"));
            assertThat(actual2.getImages().get(7).getWidth(), is(130));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-09-30T11:26:55+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/photo/uploaded.json");
            ResponseList<Photo> actuals = facebook.getUploadedPhotos("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/photos/uploaded")));

            assertThat(actuals.size(), is(3));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StStStStStSt.gif"));
            assertThat(actual1.getWidth(), is(405));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=600000000000001&set=a.196287157100244.50926.1234567890123456&type=1"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("My Name"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-10-12T01:28:40+0000")));
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn2/1375777_600000000000001_2091899828_s.jpg"));
            assertThat(actual1.getHeight(), is(720));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-prn2/s720x720/1375777_600000000000001_2091899828_n.jpg"));
            assertThat(actual1.getLikes().get(0).getId(), is("200000000000003"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().get(1).getId(), is("200000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("200000000000001"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwMzI1Mzk4NzAw"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAxNjQ4NjIzODgx"));
            assertThat(actual1.getName(), is("too hot."));
            assertThat(actual1.getImages().size(), is(8));
            assertThat(actual1.getImages().get(0).getHeight(), is(960));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-prn2/1375777_600000000000001_2091899828_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(540));
            assertThat(actual1.getImages().get(7).getHeight(), is(130));
            assertThat(actual1.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn2/s75x225/1375777_600000000000001_2091899828_s.jpg"));
            assertThat(actual1.getImages().get(7).getWidth(), is(73));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-10-12T01:28:39+0000")));
            assertThat(actual1.getComments().size(), is(2));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual1.getComments().get(0).getMessage(), is("Are you ready?"));
            assertThat(actual1.getComments().get(0).getId(), is("600000000000001_1907203"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Comment Name1"));
            assertThat(actual1.getComments().get(0).canRemove(), is(true));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-10-12T02:49:42+0000")));
            assertThat(actual1.getComments().get(1).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(1).getMessage(), is("y"));
            assertThat(actual1.getComments().get(1).getId(), is("600000000000001_1907883"));
            assertThat(actual1.getComments().get(1).getLikeCount(), is(1));
            assertThat(actual1.getComments().get(1).getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getComments().get(1).getFrom().getName(), is("My Name"));
            assertThat(actual1.getComments().get(1).canRemove(), is(true));
            assertThat(actual1.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-10-12T13:27:42+0000")));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("Mg=="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Photo actual3 = actuals.get(2);
            assertThat(actual3.getPicture().toString(), is("https://fbcdn-photos-d-a.akamaihd.net/hphotos-ak-prn2/1236436_600000000000003_914930274_s.jpg"));
            assertThat(actual3.getId(), is("600000000000003"));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StStStStSt.gif"));
            assertThat(actual3.getHeight(), is(720));
            assertThat(actual3.getSource().toString(), is("https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-prn2/s720x720/1236436_600000000000003_914930274_n.jpg"));
            assertThat(actual3.getWidth(), is(405));
            assertThat(actual3.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=600000000000003&set=a.196287157100244.50926.1234567890123456&type=1"));
            assertThat(actual3.getImages().size(), is(8));
            assertThat(actual3.getImages().get(0).getHeight(), is(960));
            assertThat(actual3.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-prn2/1236436_600000000000003_914930274_n.jpg"));
            assertThat(actual3.getImages().get(0).getWidth(), is(540));
            assertThat(actual3.getImages().get(7).getHeight(), is(130));
            assertThat(actual3.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-d-a.akamaihd.net/hphotos-ak-prn2/s75x225/1236436_600000000000003_914930274_s.jpg"));
            assertThat(actual3.getImages().get(7).getWidth(), is(73));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("My Name"));
            assertThat(actual3.getPlace().getId(), is("426325280037"));
            assertThat(actual3.getPlace().getLocation().getZip(), is("94103"));
            assertThat(actual3.getPlace().getLocation().getStreet(), is("747 Howard Street"));
            assertThat(actual3.getPlace().getLocation().getState(), is("CA"));
            assertThat(actual3.getPlace().getLocation().getLongitude(), is(-122.401426));
            assertThat(actual3.getPlace().getLocation().getLatitude(), is(37.784076));
            assertThat(actual3.getPlace().getLocation().getCountry(), is("United States"));
            assertThat(actual3.getPlace().getLocation().getCity(), is("San Francisco"));
            assertThat(actual3.getPlace().getName(), is("Moscone Center"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2013-09-22T00:38:55+0000")));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-09-22T00:38:55+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/uploaded_images.json");
            ResponseList<Photo> actuals = facebook.getUploadedPhotos("1234567890123456", new Reading().fields("album").fields("images").limit(2));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/photos/uploaded")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "album,images"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "2"));

            assertThat(actuals.size(), is(2));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("600000000000001"));
            assertThat(actual1.getAlbum().getId(), is("191919191919191"));
            assertThat(actual1.getAlbum().getName(), is("Mobile Uploads"));
            assertThat(actual1.getAlbum().getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual1.getImages().size(), is(8));
            assertThat(actual1.getImages().get(0).getHeight(), is(960));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-prn2/1375777_600000000000001_2091899828_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(540));
            assertThat(actual1.getImages().get(7).getHeight(), is(130));
            assertThat(actual1.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn2/s75x225/1375777_600000000000001_2091899828_s.jpg"));
            assertThat(actual1.getImages().get(7).getWidth(), is(73));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-10-12T01:28:39+0000")));
            Photo actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("600000000000002"));
            assertThat(actual2.getAlbum().getId(), is("191919191919191"));
            assertThat(actual2.getAlbum().getName(), is("Mobile Uploads"));
            assertThat(actual2.getAlbum().getCreatedTime(), is(iso8601DateOf("2011-08-17T11:03:11+0000")));
            assertThat(actual2.getImages().size(), is(8));
            assertThat(actual2.getImages().get(0).getHeight(), is(720));
            assertThat(actual2.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-prn2/1186300_600000000000002_1541195281_n.jpg"));
            assertThat(actual2.getImages().get(0).getWidth(), is(960));
            assertThat(actual2.getImages().get(7).getHeight(), is(97));
            assertThat(actual2.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-prn2/s75x225/1186300_600000000000002_1541195281_s.jpg"));
            assertThat(actual2.getImages().get(7).getWidth(), is(130));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-09-30T11:26:55+0000")));
        }
    }

    public static class getPhotos extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/photo/photos.json");
            ResponseList<Photo> actuals = facebook.getPhotos();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos")));

            assertThat(actuals.size(), is(2));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getTags().get(0).getId(), is("300000000000001"));
            assertThat(actual1.getTags().get(0).getName(), is("Tag Name1"));
            assertThat(actual1.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(0).getY(), is(58.125));
            assertThat(actual1.getTags().get(0).getX(), is(67.34375));
            assertThat(actual1.getTags().get(1).getId(), is("1234567890123456"));
            assertThat(actual1.getTags().get(1).getName(), is("My Name"));
            assertThat(actual1.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(1).getY(), is(57.916664123535));
            assertThat(actual1.getTags().get(1).getX(), is(57.34375));
            assertThat(actual1.getTags().get(2).getId(), is("300000000000002"));
            assertThat(actual1.getTags().get(2).getName(), is("Tag Name2"));
            assertThat(actual1.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(2).getY(), is(53.541667938232));
            assertThat(actual1.getTags().get(2).getX(), is(28.906253814697));
            assertThat(actual1.getTags().getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000001/tags?access_token=access_token&limit=5000&offset=5000&__after_id=300000000000002"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/zzzzzzzzzzz.gif"));
            assertThat(actual1.getWidth(), is(720));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=500000000000001&set=a.211497942204820.50966.100000000000001&type=1"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_s.jpg"));
            assertThat(actual1.getHeight(), is(540));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s720x720/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getLikes().get(0).getId(), is("11111111111111"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("11111111111112"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("11111111111113"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTcxNzUwMjAzOQ=="));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyNjkzMDMxMzcz"));
            assertThat(actual1.getImages().get(0).getHeight(), is(720));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(960));
            assertThat(actual1.getImages().get(2).getHeight(), is(450));
            assertThat(actual1.getImages().get(2).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s600x600/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(2).getWidth(), is(600));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-13T07:10:23+0000")));
            assertThat(actual1.getPlace().getId(), is("161616161616161"));
            assertThat(actual1.getPlace().getLocation().getZip(), is(""));
            assertThat(actual1.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual1.getPlace().getLocation().getState(), is("Hokkaido"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(140.70444082934));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(41.759466850441));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Hakodate-shi"));
            assertThat(actual1.getPlace().getName(), is("Hakodateyama"));
            Photo actual2 = actuals.get(1);
            assertThat(actual2.getTags().get(0).getId(), is("1234567890123456"));
            assertThat(actual2.getTags().get(0).getName(), is("My Name"));
            assertThat(actual2.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-05-16T11:27:05+0000")));
            assertThat(actual2.getTags().get(0).getY(), is(38.745679012346));
            assertThat(actual2.getTags().get(0).getX(), is(76.458333333333));
            assertThat(actual2.getTags().getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000002/tags?access_token=access_token&limit=5000&offset=5000&__after_id=1234567890123456"));
            assertThat(actual2.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/yyyyyyyyyyy.gif"));
            assertThat(actual2.getWidth(), is(720));
            assertThat(actual2.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=500000000000002&set=a.175190805849024.38461.100000000000002&type=1"));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("From Name2"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-05-11T11:24:55+0000")));
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getPicture().toString(), is("https://fbcdn-photos-e-a.akamaihd.net/hphotos-ak-ash3/946279_500000000000002_1050718972_s.jpg"));
            assertThat(actual2.getHeight(), is(405));
            assertThat(actual2.getSource().toString(), is("https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-ash3/s720x720/946279_500000000000002_1050718972_n.jpg"));
            assertThat(actual2.getLikes().get(0).getId(), is("22222222222221"));
            assertThat(actual2.getLikes().get(0).getName(), is("Like Name21"));
            assertThat(actual2.getLikes().get(1).getId(), is("22222222222222"));
            assertThat(actual2.getLikes().get(1).getName(), is("Like Name22"));
            assertThat(actual2.getLikes().get(2).getId(), is("22222222222223"));
            assertThat(actual2.getLikes().get(2).getName(), is("Like Name23"));
            assertThat(actual2.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwNzQzMTYwMjY1"));
            assertThat(actual2.getLikes().getPaging().getCursors().getBefore(), is("NjkyNzY5MzQ0"));
            assertThat(actual2.getName(), is("4Js"));
            assertThat(actual2.getImages().get(0).getHeight(), is(540));
            assertThat(actual2.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-ash3/946279_500000000000002_1050718972_n.jpg"));
            assertThat(actual2.getImages().get(0).getWidth(), is(960));
            assertThat(actual2.getImages().get(7).getHeight(), is(73));
            assertThat(actual2.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-e-a.akamaihd.net/hphotos-ak-ash3/s75x225/946279_500000000000002_1050718972_s.jpg"));
            assertThat(actual2.getImages().get(7).getWidth(), is(130));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-05-11T11:23:50+0000")));
            assertThat(actual2.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual2.getComments().get(0).getMessage(), is("(^^)"));
            assertThat(actual2.getComments().get(0).getId(), is("500000000000002_1616161"));
            assertThat(actual2.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("400000000000001"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("Comment Name1"));
            assertThat(actual2.getComments().get(0).canRemove(), is(false));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-05-11T21:13:48+0000")));
            assertThat(actual2.getComments().getPaging().getCursors().getAfter(), is("MQ=="));
            assertThat(actual2.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/photos_last.json");
            ResponseList<Photo> actuals = facebook.getPhotos(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getTags().get(0).getId(), is("300000000000001"));
            assertThat(actual1.getTags().get(0).getName(), is("Tag Name1"));
            assertThat(actual1.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(0).getY(), is(58.125));
            assertThat(actual1.getTags().get(0).getX(), is(67.34375));
            assertThat(actual1.getTags().get(1).getId(), is("1234567890123456"));
            assertThat(actual1.getTags().get(1).getName(), is("My Name"));
            assertThat(actual1.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(1).getY(), is(57.916664123535));
            assertThat(actual1.getTags().get(1).getX(), is(57.34375));
            assertThat(actual1.getTags().get(2).getId(), is("300000000000002"));
            assertThat(actual1.getTags().get(2).getName(), is("Tag Name2"));
            assertThat(actual1.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(2).getY(), is(53.541667938232));
            assertThat(actual1.getTags().get(2).getX(), is(28.906253814697));
            assertThat(actual1.getTags().getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000001/tags?access_token=access_token&limit=5000&offset=5000&__after_id=300000000000002"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/zzzzzzzzzzz.gif"));
            assertThat(actual1.getWidth(), is(720));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=500000000000001&set=a.211497942204820.50966.100000000000001&type=1"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_s.jpg"));
            assertThat(actual1.getHeight(), is(540));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s720x720/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getLikes().get(0).getId(), is("11111111111111"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("11111111111112"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("11111111111113"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTcxNzUwMjAzOQ=="));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyNjkzMDMxMzcz"));
            assertThat(actual1.getImages().get(0).getHeight(), is(720));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(960));
            assertThat(actual1.getImages().get(2).getHeight(), is(450));
            assertThat(actual1.getImages().get(2).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s600x600/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(2).getWidth(), is(600));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-13T07:10:23+0000")));
            assertThat(actual1.getPlace().getId(), is("161616161616161"));
            assertThat(actual1.getPlace().getLocation().getZip(), is(""));
            assertThat(actual1.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual1.getPlace().getLocation().getState(), is("Hokkaido"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(140.70444082934));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(41.759466850441));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Hakodate-shi"));
            assertThat(actual1.getPlace().getName(), is("Hakodateyama"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/photo/photos.json");
            ResponseList<Photo> actuals = facebook.getPhotos("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/photos")));

            assertThat(actuals.size(), is(2));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getTags().get(0).getId(), is("300000000000001"));
            assertThat(actual1.getTags().get(0).getName(), is("Tag Name1"));
            assertThat(actual1.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(0).getY(), is(58.125));
            assertThat(actual1.getTags().get(0).getX(), is(67.34375));
            assertThat(actual1.getTags().get(1).getId(), is("1234567890123456"));
            assertThat(actual1.getTags().get(1).getName(), is("My Name"));
            assertThat(actual1.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(1).getY(), is(57.916664123535));
            assertThat(actual1.getTags().get(1).getX(), is(57.34375));
            assertThat(actual1.getTags().get(2).getId(), is("300000000000002"));
            assertThat(actual1.getTags().get(2).getName(), is("Tag Name2"));
            assertThat(actual1.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(2).getY(), is(53.541667938232));
            assertThat(actual1.getTags().get(2).getX(), is(28.906253814697));
            assertThat(actual1.getTags().getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000001/tags?access_token=access_token&limit=5000&offset=5000&__after_id=300000000000002"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/zzzzzzzzzzz.gif"));
            assertThat(actual1.getWidth(), is(720));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=500000000000001&set=a.211497942204820.50966.100000000000001&type=1"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_s.jpg"));
            assertThat(actual1.getHeight(), is(540));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s720x720/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getLikes().get(0).getId(), is("11111111111111"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("11111111111112"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("11111111111113"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTcxNzUwMjAzOQ=="));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyNjkzMDMxMzcz"));
            assertThat(actual1.getImages().get(0).getHeight(), is(720));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(960));
            assertThat(actual1.getImages().get(2).getHeight(), is(450));
            assertThat(actual1.getImages().get(2).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s600x600/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(2).getWidth(), is(600));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-13T07:10:23+0000")));
            assertThat(actual1.getPlace().getId(), is("161616161616161"));
            assertThat(actual1.getPlace().getLocation().getZip(), is(""));
            assertThat(actual1.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual1.getPlace().getLocation().getState(), is("Hokkaido"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(140.70444082934));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(41.759466850441));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Hakodate-shi"));
            assertThat(actual1.getPlace().getName(), is("Hakodateyama"));
            Photo actual2 = actuals.get(1);
            assertThat(actual2.getTags().get(0).getId(), is("1234567890123456"));
            assertThat(actual2.getTags().get(0).getName(), is("My Name"));
            assertThat(actual2.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-05-16T11:27:05+0000")));
            assertThat(actual2.getTags().get(0).getY(), is(38.745679012346));
            assertThat(actual2.getTags().get(0).getX(), is(76.458333333333));
            assertThat(actual2.getTags().getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000002/tags?access_token=access_token&limit=5000&offset=5000&__after_id=1234567890123456"));
            assertThat(actual2.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/yyyyyyyyyyy.gif"));
            assertThat(actual2.getWidth(), is(720));
            assertThat(actual2.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=500000000000002&set=a.175190805849024.38461.100000000000002&type=1"));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("From Name2"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-05-11T11:24:55+0000")));
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getPicture().toString(), is("https://fbcdn-photos-e-a.akamaihd.net/hphotos-ak-ash3/946279_500000000000002_1050718972_s.jpg"));
            assertThat(actual2.getHeight(), is(405));
            assertThat(actual2.getSource().toString(), is("https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-ash3/s720x720/946279_500000000000002_1050718972_n.jpg"));
            assertThat(actual2.getLikes().get(0).getId(), is("22222222222221"));
            assertThat(actual2.getLikes().get(0).getName(), is("Like Name21"));
            assertThat(actual2.getLikes().get(1).getId(), is("22222222222222"));
            assertThat(actual2.getLikes().get(1).getName(), is("Like Name22"));
            assertThat(actual2.getLikes().get(2).getId(), is("22222222222223"));
            assertThat(actual2.getLikes().get(2).getName(), is("Like Name23"));
            assertThat(actual2.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwNzQzMTYwMjY1"));
            assertThat(actual2.getLikes().getPaging().getCursors().getBefore(), is("NjkyNzY5MzQ0"));
            assertThat(actual2.getName(), is("4Js"));
            assertThat(actual2.getImages().get(0).getHeight(), is(540));
            assertThat(actual2.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-ash3/946279_500000000000002_1050718972_n.jpg"));
            assertThat(actual2.getImages().get(0).getWidth(), is(960));
            assertThat(actual2.getImages().get(7).getHeight(), is(73));
            assertThat(actual2.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-e-a.akamaihd.net/hphotos-ak-ash3/s75x225/946279_500000000000002_1050718972_s.jpg"));
            assertThat(actual2.getImages().get(7).getWidth(), is(130));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-05-11T11:23:50+0000")));
            assertThat(actual2.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual2.getComments().get(0).getMessage(), is("(^^)"));
            assertThat(actual2.getComments().get(0).getId(), is("500000000000002_1616161"));
            assertThat(actual2.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("400000000000001"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("Comment Name1"));
            assertThat(actual2.getComments().get(0).canRemove(), is(false));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-05-11T21:13:48+0000")));
            assertThat(actual2.getComments().getPaging().getCursors().getAfter(), is("MQ=="));
            assertThat(actual2.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/photos_last.json");
            ResponseList<Photo> actuals = facebook.getPhotos("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/photos")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Photo actual1 = actuals.get(0);
            assertThat(actual1.getTags().get(0).getId(), is("300000000000001"));
            assertThat(actual1.getTags().get(0).getName(), is("Tag Name1"));
            assertThat(actual1.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(0).getY(), is(58.125));
            assertThat(actual1.getTags().get(0).getX(), is(67.34375));
            assertThat(actual1.getTags().get(1).getId(), is("1234567890123456"));
            assertThat(actual1.getTags().get(1).getName(), is("My Name"));
            assertThat(actual1.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(1).getY(), is(57.916664123535));
            assertThat(actual1.getTags().get(1).getX(), is(57.34375));
            assertThat(actual1.getTags().get(2).getId(), is("300000000000002"));
            assertThat(actual1.getTags().get(2).getName(), is("Tag Name2"));
            assertThat(actual1.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getTags().get(2).getY(), is(53.541667938232));
            assertThat(actual1.getTags().get(2).getX(), is(28.906253814697));
            assertThat(actual1.getTags().getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000001/tags?access_token=access_token&limit=5000&offset=5000&__after_id=300000000000002"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/zzzzzzzzzzz.gif"));
            assertThat(actual1.getWidth(), is(720));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=500000000000001&set=a.211497942204820.50966.100000000000001&type=1"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_s.jpg"));
            assertThat(actual1.getHeight(), is(540));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s720x720/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getLikes().get(0).getId(), is("11111111111111"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("11111111111112"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().get(2).getId(), is("11111111111113"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name3"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTcxNzUwMjAzOQ=="));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyNjkzMDMxMzcz"));
            assertThat(actual1.getImages().get(0).getHeight(), is(720));
            assertThat(actual1.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(0).getWidth(), is(960));
            assertThat(actual1.getImages().get(2).getHeight(), is(450));
            assertThat(actual1.getImages().get(2).getSource().toString(), is("https://fbcdn-sphotos-b-a.akamaihd.net/hphotos-ak-ash4/s600x600/267203_500000000000001_1223467697_n.jpg"));
            assertThat(actual1.getImages().get(2).getWidth(), is(600));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-13T07:10:23+0000")));
            assertThat(actual1.getPlace().getId(), is("161616161616161"));
            assertThat(actual1.getPlace().getLocation().getZip(), is(""));
            assertThat(actual1.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual1.getPlace().getLocation().getState(), is("Hokkaido"));
            assertThat(actual1.getPlace().getLocation().getLongitude(), is(140.70444082934));
            assertThat(actual1.getPlace().getLocation().getLatitude(), is(41.759466850441));
            assertThat(actual1.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual1.getPlace().getLocation().getCity(), is("Hakodate-shi"));
            assertThat(actual1.getPlace().getName(), is("Hakodateyama"));
        }
    }

    public static class postPhoto extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            File file = new File("src/test/resources/test_image.png");
            String actual = facebook.postPhoto(new Media(file));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void me_withMesssage() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            File file = new File("src/test/resources/test_image.png");
            PhotoUpdate photoUpdate = new PhotoUpdate(new Media(file))
                                            .message("update photo with message");
            String actual = facebook.postPhoto(photoUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "update photo with message"));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void me_withAllParams() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            File file = new File("src/test/resources/test_image.png");
            PhotoUpdate photoUpdate = new PhotoUpdate(new Media(file))
                                            .message("update photo with message")
                                            .place("178106048903380")
                                            .noStory(true);
            String actual = facebook.postPhoto(photoUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "update photo with message"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("place", "178106048903380"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("no_story", "1"));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            File file = new File("src/test/resources/test_image.png");
            String actual = facebook.postPhoto("1234567980123456", new Media(file));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567980123456/photos")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void id_withAllParams() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            File file = new File("src/test/resources/test_image.png");
            PhotoUpdate photoUpdate = new PhotoUpdate(new Media(file))
                                            .message("update photo with message")
                                            .place("178106048903380")
                                            .noStory(true);
            String actual = facebook.postPhoto("1234567980123456", photoUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567980123456/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "update photo with message"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("place", "178106048903380"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("no_story", "1"));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        @FacebookAPIVersion("v2.2")
        public void privacy() throws Exception {
            facebook.setMockJSON("mock_json/id_and_post_id.json");
            PrivacyParameter privacyParameter = new PrivacyBuilder().setValue(PrivacyType.SELF).build();
            PhotoUpdate photoUpdate = new PhotoUpdate(new URL("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yC/r/gfLdB3lVEL5.png"))
                    .message("privacy")
                    .noStory(true)
                    .privacy(privacyParameter);
            ;
            String actual = facebook.postPhoto(photoUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.2/me/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("url", "https://fbstatic-a.akamaihd.net/rsrc.php/v2/yC/r/gfLdB3lVEL5.png"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "privacy"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("no_story", "1"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("privacy", "{\"value\":\"SELF\"}"));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class deletePhoto extends MockFacebookTestBase {
        @Test
        public void delete() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deletePhoto("583190938409862");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/583190938409862")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void delete_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deletePhoto("583190938409862");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/583190938409862")));

            assertThat(actual, is(true));
        }
    }

    public static class getPhoto extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/photo/photo.json");
            Photo actual = facebook.getPhoto("10151509108346729");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10151509108346729")));

            assertThat(actual.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StEh3RhPvjk.gif"));
            assertThat(actual.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=10151509108346729&set=a.494827881728.283935.20531316728&type=1"));
            assertThat(actual.getWidth(), is(720));
            assertThat(actual.getFrom().getId(), is("20531316728"));
            assertThat(actual.getFrom().getCategory(), is("Product/service"));
            assertThat(actual.getFrom().getName(), is("Facebook"));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2012-10-12T21:32:03+0000")));
            assertThat(actual.getId(), is("10151509108346729"));
            assertThat(actual.getPicture().toString(), is("https://fbcdn-photos-a-a.akamaihd.net/hphotos-ak-frc1/68051_10151509108346729_1731694342_s.png"));
            assertThat(actual.getHeight(), is(720));
            assertThat(actual.getSource().toString(), is("https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-frc1/s720x720/68051_10151509108346729_1731694342_n.png"));
            assertThat(actual.getLikes().size(), is(25));
            assertThat(actual.getLikes().get(0).getId(), is("100004756326584"));
            assertThat(actual.getLikes().get(0).getName(), is("น้อง บีบี เด็กจัน"));
            assertThat(actual.getLikes().get(24).getId(), is("100003963231548"));
            assertThat(actual.getLikes().get(24).getName(), is("Supun Nalaka"));
            assertThat(actual.getLikes().getPaging().getNext().toString(), is("https://graph.facebook.com/10151509108346729/likes?access_token=access_token&limit=25&after=MTAwMDAzOTYzMjMxNTQ4"));
            assertThat(actual.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAzOTYzMjMxNTQ4"));
            assertThat(actual.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDA0NzU2MzI2NTg0"));
            assertThat(actual.getImages().size(), is(8));
            assertThat(actual.getImages().get(0).getHeight(), is(731));
            assertThat(actual.getImages().get(0).getSource().toString(), is("https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-frc1/68051_10151509108346729_1731694342_n.png"));
            assertThat(actual.getImages().get(0).getHeight(), is(731));
            assertThat(actual.getImages().get(7).getHeight(), is(130));
            assertThat(actual.getImages().get(7).getSource().toString(), is("https://fbcdn-photos-a-a.akamaihd.net/hphotos-ak-frc1/s75x225/68051_10151509108346729_1731694342_s.png"));
            assertThat(actual.getImages().get(7).getHeight(), is(130));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2012-10-12T21:32:03+0000")));
            assertThat(actual.getComments().size(), is(25));
            assertThat(actual.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual.getComments().get(0).getMessage(), is("O q  séria  do país sem  lei sem governo"));
            assertThat(actual.getComments().get(0).getId(), is("10151509108346729_9528216"));
            assertThat(actual.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual.getComments().get(0).getFrom().getId(), is("100004421163218"));
            assertThat(actual.getComments().get(0).getFrom().getName(), is("Joaquina Joaquina Ou Jackeline"));
            assertThat(actual.getComments().get(0).canRemove(), is(false));
            assertThat(actual.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2012-10-12T21:36:15+0000")));
            assertThat(actual.getComments().get(24).isUserLikes(), is(false));
            assertThat(actual.getComments().get(24).getMessage().toString(), is("https://www.facebook.com/www.beaport.net?fref=ts"));
            assertThat(actual.getComments().get(24).getId(), is("10151509108346729_9531958"));
            assertThat(actual.getComments().get(24).getLikeCount(), is(2));
            assertThat(actual.getComments().get(24).getFrom().getId(), is("1053390528"));
            assertThat(actual.getComments().get(24).getFrom().getName(), is("Yusuf Özdemir"));
            assertThat(actual.getComments().get(24).canRemove(), is(false));
            assertThat(actual.getComments().get(24).getCreatedTime(), is(iso8601DateOf("2012-10-13T10:37:04+0000")));
            assertThat(actual.getComments().getPaging().getNext().toString(), is("https://graph.facebook.com/10151509108346729/comments?access_token=access_token&limit=25&after=MzE%3D"));
            assertThat(actual.getComments().getPaging().getCursors().getAfter(), is("MzE="));
            assertThat(actual.getComments().getPaging().getCursors().getBefore(), is("Mg=="));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/photo_source.json");
            Photo actual = facebook.getPhoto("10151509108346729", new Reading().fields("source"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10151509108346729")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "source"));

            assertThat(actual.getId(), is("10151509108346729"));
            assertThat(actual.getSource().toString(), is("https://fbcdn-sphotos-a-a.akamaihd.net/hphotos-ak-frc1/s720x720/68051_10151509108346729_1731694342_n.png"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2012-10-12T21:32:03+0000")));
        }
    }

    public static class getPhotoComments extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/photo/comments.json");
            ResponseList<Comment> actuals = facebook.getPhotoComments("10151509108346729");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10151509108346729/comments")));

            assertThat(actuals.size(), is(25));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.isUserLikes(), is(false));
            assertThat(actual1.getMessage(), is("O q  séria  do país sem  lei sem governo"));
            assertThat(actual1.getId(), is("10151509108346729_9528216"));
            assertThat(actual1.getLikeCount(), is(1));
            assertThat(actual1.getFrom().getId(), is("100004421163218"));
            assertThat(actual1.getFrom().getName(), is("Joaquina Joaquina Ou Jackeline"));
            assertThat(actual1.canRemove(), is(false));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-10-12T21:36:15+0000")));
            Comment actual25 = actuals.get(24);
            assertThat(actual25.isUserLikes(), is(false));
            assertThat(actual25.getMessage().toString(), is("https://www.facebook.com/www.beaport.net?fref=ts"));
            assertThat(actual25.getId(), is("10151509108346729_9531958"));
            assertThat(actual25.getLikeCount(), is(2));
            assertThat(actual25.getFrom().getId(), is("1053390528"));
            assertThat(actual25.getFrom().getName(), is("Yusuf Özdemir"));
            assertThat(actual25.canRemove(), is(false));
            assertThat(actual25.getCreatedTime(), is(iso8601DateOf("2012-10-13T10:37:04+0000")));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/10151509108346729/comments?access_token=access_token&limit=25&after=MzE%3D"));
            assertThat(actuals.getPaging().getCursors().getAfter(), is("MzE="));
            assertThat(actuals.getPaging().getCursors().getBefore(), is("Mg=="));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/comments_like_count.json");
            ResponseList<Comment> actuals = facebook.getPhotoComments("10151509108346729", new Reading().fields("like_count"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10151509108346729/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "like_count"));

            assertThat(actuals.size(), is(25));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("10151509108346729_9528216"));
            assertThat(actual1.getLikeCount(), is(1));
            Comment actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("10151509108346729_9528506"));
            assertThat(actual2.getLikeCount(), is(0));
            Comment actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("10151509108346729_9531958"));
            assertThat(actual25.getLikeCount(), is(2));
        }
    }

    public static class getPhotoSharedposts extends MockFacebookTestBase {

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/photo/sharedposts.json");
            ResponseList<Post> actuals = facebook.getPhotoSharedposts("10152660298856961");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10152660298856961/sharedposts")));

            assertThat(actuals.size(), is(1));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("958622217524832_1032148203505566"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/sharedposts_type_field_only.json");
            ResponseList<Post> actuals = facebook.getSharedPosts("10152660298856961", new Reading().fields("type"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10152660298856961/sharedposts")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "type"));

            assertThat(actuals.size(), is(1));
            assertThat(actuals.get(0).getType(), is("photo"));
        }

    }

    public static class commentPhoto extends MockFacebookTestBase {
        @Test
        public void comment() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.commentPhoto("500000000000001", "Test: comment to a photo.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void byCommentUpdate() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.commentPhoto("500000000000001", new CommentUpdate().message("Test: comment to a photo."));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withAttachmentId() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("Test: comment to a photo.")
                    .attachmentId("1122334455667788");
            String actual = facebook.commentPhoto("500000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withAttachmentUrl() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("Test: comment to a photo.")
                    .attachmentUrl("https://fortunedotcom.files.wordpress.com/2015/04/467495334.jpg?quality=80&w=840&h=485&crop=1");
            String actual = facebook.commentPhoto("500000000000001", source);
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
            String actual = facebook.commentPost("500000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class getPhotoLikes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/photo/likes.json");
            ResponseList<Like> actuals = facebook.getPhotoLikes("10151509108346729");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10151509108346729/likes")));

            assertThat(actuals.size(), is(25));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100004756326584"));
            assertThat(actual1.getName(), is("น้อง บีบี เด็กจัน"));
            Like actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("100003963231548"));
            assertThat(actual25.getName(), is("Supun Nalaka"));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/10151509108346729/likes?access_token=access_token&limit=25&after=MTAwMDAzOTYzMjMxNTQ4"));
            assertThat(actuals.getPaging().getCursors().getAfter(), is("MTAwMDAzOTYzMjMxNTQ4"));
            assertThat(actuals.getPaging().getCursors().getBefore(), is("MTAwMDA0NzU2MzI2NTg0"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/likes_last5.json");
            ResponseList<Like> actuals = facebook.getPhotoLikes("10151509108346729", new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10151509108346729/likes")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100004756326584"));
            assertThat(actual1.getName(), is("น้อง บีบี เด็กจัน"));
            Like actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("100006514060823"));
            assertThat(actual5.getName(), is("แดงสบไหล ยาวไกล้"));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/10151509108346729/likes?limit=5&access_token=access_token&after=MTAwMDA2NTE0MDYwODIz"));
            assertThat(actuals.getPaging().getCursors().getAfter(), is("MTAwMDA2NTE0MDYwODIz"));
            assertThat(actuals.getPaging().getCursors().getBefore(), is("MTAwMDA0NzU2MzI2NTg0"));
        }
    }

    public static class likePhoto extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.likePhoto("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class unlikePhoto extends MockFacebookTestBase {
        @Test
        public void unlike() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.unlikePhoto("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class getPhotoURL extends MockFacebookTestBase {
        @Test
        public void url() throws Exception {
            facebook.setMockJSON("mock_json/empty.json");
            URL actual = facebook.getPhotoURL("10151509108346729");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10151509108346729/picture")));

            assertThat(actual.toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash3/644169_573207722741517_740837405_a.jpg"));
        }
    }

    public static class getTagsOnPhoto extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/photo/tags.json");
            ResponseList<Tag> actuals = facebook.getTagsOnPhoto("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/tags")));

            assertThat(actuals.size(), is(3));
            Tag actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("300000000000001"));
            assertThat(actual1.getName(), is("Tag Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual1.getY(), is(58.125));
            assertThat(actual1.getX(), is(67.34375));
            Tag actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("300000000000002"));
            assertThat(actual3.getName(), is("Tag Name2"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2013-07-13T09:02:45+0000")));
            assertThat(actual3.getY(), is(53.541667938232));
            assertThat(actual3.getX(), is(28.906253814697));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000001/tags?access_token=access_token&limit=5000&offset=5000&__after_id=300000000000002"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/photo/tags_name.json");
            ResponseList<Tag> actuals = facebook.getTagsOnPhoto("500000000000001", new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/tags")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(actuals.size(), is(3));
            Tag actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("300000000000001"));
            assertThat(actual1.getName(), is("Tag Name1"));
            assertThat(actual1.getCreatedTime(), is(nullValue()));
            assertThat(actual1.getY(), is(nullValue()));
            assertThat(actual1.getX(), is(nullValue()));
            Tag actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("300000000000002"));
            assertThat(actual3.getName(), is("Tag Name2"));
            assertThat(actual3.getCreatedTime(), is(nullValue()));
            assertThat(actual3.getY(), is(nullValue()));
            assertThat(actual3.getX(), is(nullValue()));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/500000000000001/tags?fields=name&access_token=access_token&limit=5000&offset=5000&__after_id=300000000000002"));
        }
    }

    public static class addTagToPhoto extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.addTagToPhoto("500000000000001", "300000000000004");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/tags")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("to", "300000000000004"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.addTagToPhoto("500000000000001", "300000000000004");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/500000000000001/tags")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("to", "300000000000004"));

            assertThat(actual, is(true));
        }

        @Test
        public void ids() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.addTagToPhoto("500000000000001", Arrays.asList("300000000000005", "300000000000006"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/tags")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("tags", "[{\"tag_uid\":\"300000000000005\"},{\"tag_uid\":\"300000000000006\"}]"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void ids_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.addTagToPhoto("500000000000001", Arrays.asList("300000000000005", "300000000000006"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/500000000000001/tags")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("tags", "[{\"tag_uid\":\"300000000000005\"},{\"tag_uid\":\"300000000000006\"}]"));

            assertThat(actual, is(true));
        }

        @Test
        public void update() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            TagUpdate tagUpdate = new TagUpdate()
                                        .to("300000000000007")
                                        .tagText("tag text")
                                        .x(57.34375)
                                        .y(57.916664123535);
            boolean actual = facebook.addTagToPhoto("500000000000001", tagUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/tags")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("to", "300000000000007"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("tag_text", "tag text"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("x", "57.34375"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("y", "57.916664123535"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void update_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            TagUpdate tagUpdate = new TagUpdate()
                                        .to("300000000000007")
                                        .tagText("tag text")
                                        .x(57.34375)
                                        .y(57.916664123535);
            boolean actual = facebook.addTagToPhoto("500000000000001", tagUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/500000000000001/tags")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("to", "300000000000007"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("tag_text", "tag text"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("x", "57.34375"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("y", "57.916664123535"));

            assertThat(actual, is(true));
        }
    }

    public static class updateTagOnPhoto extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            TagUpdate tagUpdate = new TagUpdate()
                                        .to("300000000000007")
                                        .x(157.34375)
                                        .y(157.916664123535);
            boolean actual = facebook.updateTagOnPhoto("500000000000001", tagUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/tags")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("to", "300000000000007"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("x", "157.34375"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("y", "157.916664123535"));

            assertThat(actual, is(true));
        }
    }

    public static class deleteTagOnPhoto extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteTagOnPhoto("500000000000001", "300000000000007");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/tags")));
            assertThat(facebook.getEndpointURL(), hasParameter("to", "300000000000007"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteTagOnPhoto("500000000000001", "300000000000007");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/500000000000001/tags")));
            assertThat(facebook.getEndpointURL(), hasParameter("to", "300000000000007"));

            assertThat(actual, is(true));
        }
    }
    
    public static class photoReactions extends MockFacebookTestBase {
        @Test
        @FacebookAPIVersion("v2.6")
        public void reactions_v26() throws Exception {
            facebook.setMockJSON("mock_json/photo/reactions.json");
            
            ResponseList<Reaction> reactions = facebook.getPhotoReactions("10154129451953485");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.6/10154129451953485/reactions")));
            
            assertThat(reactions.size(), is(25));
        }
    }

}
