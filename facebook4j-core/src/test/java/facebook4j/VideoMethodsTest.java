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

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class VideoMethodsTest extends MockFacebookTestBase {

    public static class getVideos extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/video/videos.json");
            ResponseList<Video> actuals = facebook.getVideos();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/videos")));

            assertThat(actuals.size(), is(3));
            Video actual1 = actuals.get(0);
            assertThat(actual1.getTags().get(0).getId(), is("300000000000005"));
            assertThat(actual1.getTags().get(0).getName(), is("Tags Name1"));
            assertThat(actual1.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:25:02+0000")));
            assertThat(actual1.getTags().get(1).getId(), is("10000000000001"));
            assertThat(actual1.getTags().get(1).getName(), is("My Name"));
            assertThat(actual1.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:25:01+0000")));
            assertThat(actual1.getTags().get(2).getId(), is("300000000000001"));
            assertThat(actual1.getTags().get(2).getName(), is("Comment Name1"));
            assertThat(actual1.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:25:00+0000")));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzzz.gif"));
            assertThat(actual1.getFormat().size(), is(4));
            assertThat(actual1.getFormat().get(0).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000001\" width=\"130\" height=\"78\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getFormat().get(0).getFilter(), is("130x130"));
            assertThat(actual1.getFormat().get(0).getHeight(), is(78));
            assertThat(actual1.getFormat().get(0).getWidth(), is(130));
            assertThat(actual1.getFormat().get(0).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/s130x130/40001_500000000000005_500000000000001_3067_1699_t.jpg"));
            assertThat(actual1.getFormat().get(3).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000001\" width=\"800\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getFormat().get(3).getFilter(), is("native"));
            assertThat(actual1.getFormat().get(3).getHeight(), is(480));
            assertThat(actual1.getFormat().get(3).getWidth(), is(800));
            assertThat(actual1.getFormat().get(3).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/40001_500000000000005_500000000000001_3067_1699_b.jpg"));
            assertThat(actual1.getFrom().getId(), is("100000000000003"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-01-15T17:29:53+0000")));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/40001_500000000000005_500000000000001_3067_1699_t.jpg"));
            assertThat(actual1.getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000001\" width=\"800\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-frc1/v/700001_540000000000001_60001_n.mp4?oh=zxzxzxzxzxzxzxz&oe=521B87B4&__gda__=1300000005_132b939affe2e6c312cfc7658095331b"));
            assertThat(actual1.getDescription(), is("tour"));
            assertThat(actual1.getName(), is("GGGGG"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-01-15T17:29:53+0000")));
            assertThat(actual1.getComments().size(), is(3));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(0).getMessage(), is(":("));
            assertThat(actual1.getComments().get(0).getId(), is("500000000000001_90000005"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("300000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Comment Name1"));
            assertThat(actual1.getComments().get(0).canRemove(), is(false));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:31:35+0000")));
            assertThat(actual1.getComments().get(2).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(2).getMessage(), is("volume"));
            assertThat(actual1.getComments().get(2).getId(), is("500000000000001_95565969"));
            assertThat(actual1.getComments().get(2).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(2).getFrom().getId(), is("300000000000002"));
            assertThat(actual1.getComments().get(2).getFrom().getName(), is("Comment Name2"));
            assertThat(actual1.getComments().get(2).canRemove(), is(false));
            assertThat(actual1.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:41:50+0000")));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("MTI="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Video actual3 = actuals.get(2);
            assertThat(actual3.getTags().get(0).getId(), is("10000000000001"));
            assertThat(actual3.getTags().get(0).getName(), is("My Name"));
            assertThat(actual3.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:39+0000")));
            assertThat(actual3.getTags().get(1).getId(), is("20000001"));
            assertThat(actual3.getTags().get(1).getName(), is("Tags Name31"));
            assertThat(actual3.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:19+0000")));
            assertThat(actual3.getTags().get(2).getId(), is("1900001"));
            assertThat(actual3.getTags().get(2).getName(), is("Tags Name32"));
            assertThat(actual3.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:14+0000")));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzzz.gif"));
            assertThat(actual3.getFormat().size(), is(3));
            assertThat(actual3.getFormat().get(0).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000003\" width=\"130\" height=\"87\" frameborder=\"0\"></iframe>"));
            assertThat(actual3.getFormat().get(0).getFilter(), is("130x130"));
            assertThat(actual3.getFormat().get(0).getHeight(), is(87));
            assertThat(actual3.getFormat().get(0).getWidth(), is(130));
            assertThat(actual3.getFormat().get(0).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/s130x130/41006_460000000003_500000000000003_27034_1465_t.jpg"));
            assertThat(actual3.getFormat().get(2).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000003\" width=\"720\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual3.getFormat().get(2).getFilter(), is("native"));
            assertThat(actual3.getFormat().get(2).getHeight(), is(480));
            assertThat(actual3.getFormat().get(2).getWidth(), is(720));
            assertThat(actual3.getFormat().get(2).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/41006_460000000003_500000000000003_27034_1465_b.jpg"));
            assertThat(actual3.getFrom().getId(), is("10000000000001"));
            assertThat(actual3.getFrom().getName(), is("My Name"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2010-12-07T13:15:35+0000")));
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/41006_460000000003_500000000000003_27034_1465_t.jpg"));
            assertThat(actual3.getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000003\" width=\"720\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual3.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/cfs-ak-prn1/v/78921/936/500000000000003_64755.mp4?oh=88fa0214346fsdfs54&oe=521BD433&__gda__=1377585953_5aff4628dfe214079719812a0641b68e"));
            assertThat(actual3.getDescription(), is("Yell"));
            assertThat(actual3.getName(), is("Second"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2010-12-07T13:15:35+0000")));
            assertThat(actual3.getComments().size(), is(2));
            assertThat(actual3.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(0).getMessage(), is("huh?"));
            assertThat(actual3.getComments().get(0).getId(), is("500000000000003_14239553"));
            assertThat(actual3.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("10000000000001"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("My Name"));
            assertThat(actual3.getComments().get(0).canRemove(), is(false));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:05+0000")));
            assertThat(actual3.getComments().get(1).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(1).getMessage(), is("WOW"));
            assertThat(actual3.getComments().get(1).getId(), is("500000000000003_14240609"));
            assertThat(actual3.getComments().get(1).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(1).getFrom().getId(), is("1100110011001100"));
            assertThat(actual3.getComments().get(1).getFrom().getName(), is("Comment Name31"));
            assertThat(actual3.getComments().get(1).canRemove(), is(false));
            assertThat(actual3.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2010-12-08T00:28:58+0000")));
            assertThat(actual3.getComments().getPaging().getCursors().getAfter(), is("MTI="));
            assertThat(actual3.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/video/videos_source.json");
            ResponseList<Video> actuals = facebook.getVideos(new Reading().fields("source"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/videos")));

            assertThat(actuals.size(), is(3));
            Video actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("200000000000001"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-frc1/v/700009_540000000000311_640003_n.mp4?oh=bccf9586be2b3fce7b458b92f2174493&oe=521B87B4&__gda__=1377578675_132b939affe2e6c312cfc7658095331b"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-01-15T17:29:53+0000")));
            Video actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("200000000000003"));
            assertThat(actual3.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/cfs-ak-prn1/v/78921/936/200000000000003_64755.mp4?oh=830a9257182dcf18f223737a2ea32014&oe=521BD433&__gda__=1377585953_5aff4628dfe214079719812a0641b68e"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2010-12-07T13:15:35+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/video/videos.json");
            ResponseList<Video> actuals = facebook.getVideos("10000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10000000000001/videos")));

            assertThat(actuals.size(), is(3));
            Video actual1 = actuals.get(0);
            assertThat(actual1.getTags().get(0).getId(), is("300000000000005"));
            assertThat(actual1.getTags().get(0).getName(), is("Tags Name1"));
            assertThat(actual1.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:25:02+0000")));
            assertThat(actual1.getTags().get(1).getId(), is("10000000000001"));
            assertThat(actual1.getTags().get(1).getName(), is("My Name"));
            assertThat(actual1.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:25:01+0000")));
            assertThat(actual1.getTags().get(2).getId(), is("300000000000001"));
            assertThat(actual1.getTags().get(2).getName(), is("Comment Name1"));
            assertThat(actual1.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:25:00+0000")));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzzz.gif"));
            assertThat(actual1.getFormat().size(), is(4));
            assertThat(actual1.getFormat().get(0).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000001\" width=\"130\" height=\"78\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getFormat().get(0).getFilter(), is("130x130"));
            assertThat(actual1.getFormat().get(0).getHeight(), is(78));
            assertThat(actual1.getFormat().get(0).getWidth(), is(130));
            assertThat(actual1.getFormat().get(0).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/s130x130/40001_500000000000005_500000000000001_3067_1699_t.jpg"));
            assertThat(actual1.getFormat().get(3).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000001\" width=\"800\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getFormat().get(3).getFilter(), is("native"));
            assertThat(actual1.getFormat().get(3).getHeight(), is(480));
            assertThat(actual1.getFormat().get(3).getWidth(), is(800));
            assertThat(actual1.getFormat().get(3).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/40001_500000000000005_500000000000001_3067_1699_b.jpg"));
            assertThat(actual1.getFrom().getId(), is("100000000000003"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-01-15T17:29:53+0000")));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/40001_500000000000005_500000000000001_3067_1699_t.jpg"));
            assertThat(actual1.getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000001\" width=\"800\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-frc1/v/700001_540000000000001_60001_n.mp4?oh=zxzxzxzxzxzxzxz&oe=521B87B4&__gda__=1300000005_132b939affe2e6c312cfc7658095331b"));
            assertThat(actual1.getDescription(), is("tour"));
            assertThat(actual1.getName(), is("GGGGG"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-01-15T17:29:53+0000")));
            assertThat(actual1.getComments().size(), is(3));
            assertThat(actual1.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(0).getMessage(), is(":("));
            assertThat(actual1.getComments().get(0).getId(), is("500000000000001_90000005"));
            assertThat(actual1.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(0).getFrom().getId(), is("300000000000001"));
            assertThat(actual1.getComments().get(0).getFrom().getName(), is("Comment Name1"));
            assertThat(actual1.getComments().get(0).canRemove(), is(false));
            assertThat(actual1.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:31:35+0000")));
            assertThat(actual1.getComments().get(2).isUserLikes(), is(false));
            assertThat(actual1.getComments().get(2).getMessage(), is("volume"));
            assertThat(actual1.getComments().get(2).getId(), is("500000000000001_95565969"));
            assertThat(actual1.getComments().get(2).getLikeCount(), is(0));
            assertThat(actual1.getComments().get(2).getFrom().getId(), is("300000000000002"));
            assertThat(actual1.getComments().get(2).getFrom().getName(), is("Comment Name2"));
            assertThat(actual1.getComments().get(2).canRemove(), is(false));
            assertThat(actual1.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-01-15T17:41:50+0000")));
            assertThat(actual1.getComments().getPaging().getCursors().getAfter(), is("MTI="));
            assertThat(actual1.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Video actual3 = actuals.get(2);
            assertThat(actual3.getTags().get(0).getId(), is("10000000000001"));
            assertThat(actual3.getTags().get(0).getName(), is("My Name"));
            assertThat(actual3.getTags().get(0).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:39+0000")));
            assertThat(actual3.getTags().get(1).getId(), is("20000001"));
            assertThat(actual3.getTags().get(1).getName(), is("Tags Name31"));
            assertThat(actual3.getTags().get(1).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:19+0000")));
            assertThat(actual3.getTags().get(2).getId(), is("1900001"));
            assertThat(actual3.getTags().get(2).getName(), is("Tags Name32"));
            assertThat(actual3.getTags().get(2).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:14+0000")));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzzz.gif"));
            assertThat(actual3.getFormat().size(), is(3));
            assertThat(actual3.getFormat().get(0).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000003\" width=\"130\" height=\"87\" frameborder=\"0\"></iframe>"));
            assertThat(actual3.getFormat().get(0).getFilter(), is("130x130"));
            assertThat(actual3.getFormat().get(0).getHeight(), is(87));
            assertThat(actual3.getFormat().get(0).getWidth(), is(130));
            assertThat(actual3.getFormat().get(0).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/s130x130/41006_460000000003_500000000000003_27034_1465_t.jpg"));
            assertThat(actual3.getFormat().get(2).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000003\" width=\"720\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual3.getFormat().get(2).getFilter(), is("native"));
            assertThat(actual3.getFormat().get(2).getHeight(), is(480));
            assertThat(actual3.getFormat().get(2).getWidth(), is(720));
            assertThat(actual3.getFormat().get(2).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/41006_460000000003_500000000000003_27034_1465_b.jpg"));
            assertThat(actual3.getFrom().getId(), is("10000000000001"));
            assertThat(actual3.getFrom().getName(), is("My Name"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2010-12-07T13:15:35+0000")));
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/41006_460000000003_500000000000003_27034_1465_t.jpg"));
            assertThat(actual3.getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=500000000000003\" width=\"720\" height=\"480\" frameborder=\"0\"></iframe>"));
            assertThat(actual3.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/cfs-ak-prn1/v/78921/936/500000000000003_64755.mp4?oh=88fa0214346fsdfs54&oe=521BD433&__gda__=1377585953_5aff4628dfe214079719812a0641b68e"));
            assertThat(actual3.getDescription(), is("Yell"));
            assertThat(actual3.getName(), is("Second"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2010-12-07T13:15:35+0000")));
            assertThat(actual3.getComments().size(), is(2));
            assertThat(actual3.getComments().get(0).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(0).getMessage(), is("huh?"));
            assertThat(actual3.getComments().get(0).getId(), is("500000000000003_14239553"));
            assertThat(actual3.getComments().get(0).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("10000000000001"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("My Name"));
            assertThat(actual3.getComments().get(0).canRemove(), is(false));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2010-12-07T20:53:05+0000")));
            assertThat(actual3.getComments().get(1).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(1).getMessage(), is("WOW"));
            assertThat(actual3.getComments().get(1).getId(), is("500000000000003_14240609"));
            assertThat(actual3.getComments().get(1).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(1).getFrom().getId(), is("1100110011001100"));
            assertThat(actual3.getComments().get(1).getFrom().getName(), is("Comment Name31"));
            assertThat(actual3.getComments().get(1).canRemove(), is(false));
            assertThat(actual3.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2010-12-08T00:28:58+0000")));
            assertThat(actual3.getComments().getPaging().getCursors().getAfter(), is("MTI="));
            assertThat(actual3.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/video/videos_source.json");
            ResponseList<Video> actuals = facebook.getVideos("10000000000001", new Reading().fields("source"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10000000000001/videos")));

            assertThat(actuals.size(), is(3));
            Video actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("200000000000001"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-frc1/v/700009_540000000000311_640003_n.mp4?oh=bccf9586be2b3fce7b458b92f2174493&oe=521B87B4&__gda__=1377578675_132b939affe2e6c312cfc7658095331b"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-01-15T17:29:53+0000")));
            Video actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("200000000000003"));
            assertThat(actual3.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/cfs-ak-prn1/v/78921/936/200000000000003_64755.mp4?oh=830a9257182dcf18f223737a2ea32014&oe=521BD433&__gda__=1377585953_5aff4628dfe214079719812a0641b68e"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2010-12-07T13:15:35+0000")));
        }

        @Test
        public void page() throws Exception {
            facebook.setMockJSON("mock_json/video/videos_page.json");
            ResponseList<Video> videos = facebook.getVideos("19292868552");
            assertThat(facebook.getEndpointURL(), is(pathOf("/19292868552/videos")));

            Video video1 = videos.get(0);
            assertThat(video1.getComments().size(), is(21));
            Comment comment11 = video1.getComments().get(0);
            assertThat(comment11.canRemove(), is(false));
            assertThat(comment11.getCreatedTime(), is(iso8601DateOf("2013-03-27T01:54:53+0000")));
            assertThat(comment11.getFrom().getId(), is("672887140"));
            assertThat(comment11.getFrom().getName(), is("Emin Özlem"));
            assertThat(comment11.getId(), is("10100562878508753_5868893"));
            assertThat(comment11.getLikeCount(), is(5));
            assertThat(comment11.getMessage(), is("20 percent text limit will not apply to regular images right ?!!"));
            assertThat(comment11.isUserLikes(), is(false));
            Comment comment121 = video1.getComments().get(20);
            assertThat(comment121.canRemove(), is(false));
            assertThat(comment121.getCreatedTime(), is(iso8601DateOf("2013-03-27T03:05:11+0000")));
            assertThat(comment121.getFrom().getId(), is("100003357247721"));
            assertThat(comment121.getFrom().getName(), is("Jose Brasil"));
            assertThat(comment121.getId(), is("10100562878508753_5869290"));
            assertThat(comment121.getLikeCount(), is(1));
            assertThat(comment121.getMessage(), is("!"));
            assertThat(comment121.isUserLikes(), is(false));
            assertThat(video1.getCreatedTime(), is(iso8601DateOf("2013-03-27T01:53:04+0000")));
            assertThat(video1.getEmbedHtml(), is("<iframe src=\"http://www.facebook.com/video/embed?video_id=10100562878508753\" width=\"1280\" height=\"720\" frameborder=\"0\"></iframe>"));
            assertThat(video1.getFormat().size(), is(4));
            assertThat(video1.getFormat().get(0).getEmbedHtml(), is("<iframe src=\"http://www.facebook.com/video/embed?video_id=10100562878508753\" width=\"130\" height=\"73\" frameborder=\"0\"></iframe>"));
            assertThat(video1.getFormat().get(0).getFilter(), is("130x130"));
            assertThat(video1.getFormat().get(0).getHeight(), is(73));
            assertThat(video1.getFormat().get(0).getWidth(), is(130));
            assertThat(video1.getFormat().get(0).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/s130x130/632744_10100562885080583_10100562878508753_42200_702_t.jpg"));
            assertThat(video1.getFrom().getCategory(), is("Product/service"));
            assertThat(video1.getFrom().getId(), is("19292868552"));
            assertThat(video1.getFrom().getName(), is("Facebook Developers"));
            assertThat(video1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/DggDhA4z4tO.gif"));
            assertThat(video1.getId(), is("10100562878508753"));
            assertThat(video1.getName(), is("Best Practices for Mobile App Install Ads"));
            assertThat(video1.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-prn1/632744_10100562885080583_10100562878508753_42200_702_t.jpg"));
            assertThat(video1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-prn1/v/746464_10100562884965813_2113083078_n.mp4?oh=82c819e5b30e1a4d107b3436395ab73d&oe=51C17992&__gda__=1371638871_837a2907288a38125ae0c8828cdf68ef"));
            assertThat(video1.getUpdatedTime(), is(iso8601DateOf("2013-03-27T01:53:04+0000")));
        }

    }

    public static class postVideo extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            File videoFile = new File("src/test/resources/test.mov");
            String videoId = facebook.postVideo(new VideoUpdate(new Media(videoFile)));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/videos")));

            assertThat(videoId, is("137246726435626_185932178233747"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            File videoFile = new File("src/test/resources/test.mov");
            String videoId = facebook.postVideo("1234567890123456", new VideoUpdate(new Media(videoFile)));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/videos")));

            assertThat(videoId, is("137246726435626_185932178233747"));
        }

        @Test
        public void page() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String pageId = "137246726435626";
            File videoFile = new File("src/test/resources/test.mov");
            PageVideoUpdate pageVideoUpdate = new PageVideoUpdate(new Media(videoFile));
            Set<String> countries = new HashSet<String>();
            countries.add("US");
            countries.add("GB");
            TargetingParameter targeting = new TargetingParameter().countries(countries);
            pageVideoUpdate.setTargeting(targeting);
            FeedTargetingParameter feedTargeting = new FeedTargetingParameter().genders(FeedTargetingParameter.Gender.Male);
            feedTargeting.setAgeMin(20);
            feedTargeting.setAgeMax(40);
            pageVideoUpdate.setFeedTargeting(feedTargeting);
            String videoId = facebook.postVideo(pageId, pageVideoUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/videos")));
            assertThat(facebook.getHttpParameters(), hasTargetingParameterWithCountries("US", "GB"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("feed_targeting", "{\"age_min\":20,\"genders\":{\"value\":1},\"age_max\":40}"));

            assertThat(videoId, is("137246726435626_185932178233747"));
        }
    }

    public static class getVideo extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/video/video.json");
            Video actual = facebook.getVideo("600000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001")));

            assertThat(actual.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/1000004_6040000000000440_600000000000001_6005_2006_t.jpg"));
            assertThat(actual.getId(), is("600000000000001"));
            assertThat(actual.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/DggDhA4z4tO.gif"));
            assertThat(actual.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-ash3/v/800004_600000000000778_300000002_n.mp4?oh=9b5a2216296fagsd4ge97b3b6257685&oe=521B9554&__gda__=1377581619_d3f557f690d446f5ab0620912f290e69"));
            assertThat(actual.getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=600000000000001\" width=\"568\" height=\"320\" frameborder=\"0\"></iframe>"));
            assertThat(actual.getName(), is("TOKYO HANABI FESTIVAL 2013"));
            assertThat(actual.getFrom().getId(), is("100000000000001"));
            assertThat(actual.getFrom().getName(), is("Uploader Name"));
            assertThat(actual.getFormat().size(), is(3));
            assertThat(actual.getFormat().get(0).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=600000000000001\" width=\"130\" height=\"73\" frameborder=\"0\"></iframe>"));
            assertThat(actual.getFormat().get(0).getFilter(), is("130x130"));
            assertThat(actual.getFormat().get(0).getHeight(), is(73));
            assertThat(actual.getFormat().get(0).getWidth(), is(130));
            assertThat(actual.getFormat().get(0).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/s130x130/1096084_604361702918440_600000000000001_6875_2286_t.jpg"));
            assertThat(actual.getFormat().get(2).getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=600000000000001\" width=\"568\" height=\"320\" frameborder=\"0\"></iframe>"));
            assertThat(actual.getFormat().get(2).getFilter(), is("native"));
            assertThat(actual.getFormat().get(2).getHeight(), is(320));
            assertThat(actual.getFormat().get(2).getWidth(), is(568));
            assertThat(actual.getFormat().get(2).getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash3/1096084_604361702918440_600000000000001_6875_2286_b.jpg"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-08-12T04:20:15+0000")));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-08-12T04:20:15+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/video/video_html.json");
            Video actual = facebook.getVideo("600000000000001", new Reading().fields("embed_html"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "embed_html"));

            assertThat(actual.getId(), is("600000000000001"));
            assertThat(actual.getEmbedHtml(), is("<iframe src=\"https://www.facebook.com/video/embed?video_id=600000000000001\" width=\"568\" height=\"320\" frameborder=\"0\"></iframe>"));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-08-12T04:20:15+0000")));
            assertThat(actual.getSource(), is(nullValue()));
            assertThat(actual.getFormat().size(), is(0));
        }
    }

    public static class getVideoLikes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/video/likes.json");
            ResponseList<Like> actuals = facebook.getVideoLikes("600000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/likes")));

            assertThat(actuals.size(), is(6));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Like Name1"));
            Like actual6 = actuals.get(5);
            assertThat(actual6.getId(), is("100000000000001"));
            assertThat(actual6.getName(), is("Like Name6"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/video/likes_1.json");
            ResponseList<Like> actuals = facebook.getVideoLikes("600000000000001", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/likes")));

            assertThat(actuals.size(), is(1));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Like Name1"));
        }
    }

    public static class likeVideo extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.likeVideo("600000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class unlikeVideo extends MockFacebookTestBase {
        @Test
        public void unlike() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.unlikeVideo("600000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class getVideoComments extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/video/comments.json");
            ResponseList<Comment> actuals = facebook.getVideoComments("600000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actuals.size(), is(2));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.isUserLikes(), is(false));
            assertThat(actual1.getMessage(), is("message1"));
            assertThat(actual1.getId(), is("500000000000001_4726985"));
            assertThat(actual1.getLikeCount(), is(0));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name  Name1"));
            assertThat(actual1.canRemove(), is(true));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-03-19T09:55:03+0000")));
            Comment actual2 = actuals.get(1);
            assertThat(actual2.isUserLikes(), is(false));
            assertThat(actual2.getMessage(), is("message2"));
            assertThat(actual2.getId(), is("500000000000001_4727422"));
            assertThat(actual2.getLikeCount(), is(0));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("Name Name2"));
            assertThat(actual2.canRemove(), is(true));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-03-19T13:03:26+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/video/comments_from.json");
            ResponseList<Comment> actuals = facebook.getVideoComments("600000000000001", new Reading().fields("from"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "from"));

            assertThat(actuals.size(), is(2));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001_4726985"));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Name  Name1"));
            Comment actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("500000000000001_4727422"));
            assertThat(actual2.getFrom().getId(), is("100000000000002"));
            assertThat(actual2.getFrom().getName(), is("Name Name2"));
        }
    }

    public static class commentVideo extends MockFacebookTestBase {
        @Test
        public void comment() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.commentVideo("600000000000001", "comment message");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "comment message"));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void byCommentUpdate() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.commentVideo("600000000000001", new CommentUpdate().message("test"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void withAttachmentId() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentId("1122334455667788");
            String actual = facebook.commentVideo("600000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void withAttachmentUrl() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentUrl("https://fortunedotcom.files.wordpress.com/2015/04/467495334.jpg?quality=80&w=840&h=485&crop=1");
            String actual = facebook.commentVideo("600000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void withSource() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .source(new Media(new File("src/test/resources/test_image.png")));
            String actual = facebook.commentVideo("600000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class getVideoCover extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/empty.json");
            URL actual = facebook.getVideoCover("600000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));

            assertThat(actual.toString(), is("https://fbcdn-photos-b-a.akamaihd.net/hphotos-ak-ash3/644169_573207722741517_740837405_a.jpg"));
        }
    }

    public static class getVideoSharedposts extends MockFacebookTestBase {

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/video/sharedposts.json");
            ResponseList<Post> actuals = facebook.getVideoSharedposts("10153645784876961");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10153645784876961/sharedposts")));

            assertThat(actuals.size(), is(3));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("101840300335_10154119268640336"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/video/sharedposts_type_field_only.json");
            ResponseList<Post> actuals = facebook.getSharedPosts("10153645784876961", new Reading().fields("type"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/10153645784876961/sharedposts")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "type"));

            assertThat(actuals.size(), is(3));
            assertThat(actuals.get(0).getType(), is("video"));
        }

    }

}
