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
public class PhotoMethodsTest {

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

/*
    @Test
    public void post_delete() throws Exception {
        File file = new File("src/test/resources/test_image.png");
        Media source = new Media(file);
        String photoId = facebook1.postPhoto(source, "test message", null, false);
        assertThat(photoId, is(notNullValue()));

        boolean deleteResult = facebook1.deletePhoto(photoId);
        assertThat(deleteResult, is(true));
    }
*/
}
