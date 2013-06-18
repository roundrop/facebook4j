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

import static facebook4j.junit.URLMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class VideoMethodsTest extends MockFacebookTestBase {

    @Test
    public void getVideos() throws Exception {
        facebook.setMockJSON("mock_json/videos/page.json");
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
