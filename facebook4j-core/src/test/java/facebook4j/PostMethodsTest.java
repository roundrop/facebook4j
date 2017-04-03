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
import java.util.HashSet;
import java.util.Set;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class PostMethodsTest extends MockFacebookTestBase {

    public static class getFeed extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post/feed.json");
            ResponseList<Post> actuals = facebook.getFeed();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));

            assertThat(actuals.size(), is(6));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yx/r/zzzzzzzz.gif"));
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=570000000000001&set=pcb.500000000000001&type=1&relevant_count=2"));
            assertThat(actual1.getObjectId(), is("570000000000001"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("My Name"));
            assertThat(actual1.getType(), is("photo"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-06T10:36:10+0000")));
            assertThat(actual1.getId(), is("1234567890123456_500000000000001"));
            assertThat(actual1.getMessage(), is("Sunrise"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-a-a.akamaihd.net/hphotos-ak-prn2/1098080_570000000000001_1269337662_s.jpg"));
            assertThat(actual1.getFullPicture().toString(), is("https://fbcdn-photos-a-a.akamaihd.net/hphotos-ak-prn2/1098080_570000000000001_1269337662_full_picture.jpg"));
            assertThat(actual1.getStatusType(), is("mobile_status_update"));
            assertThat(actual1.getLikes().getCount(), is(13));
            assertThat(actual1.getLikes().get(0).getId(), is("100000000000011"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name11"));
            assertThat(actual1.getLikes().get(1).getId(), is("100000000000012"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name12"));
            assertThat(actual1.getLikes().get(2).getId(), is("100000000000013"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name13"));
            assertThat(actual1.getLikes().get(3).getId(), is("100000000000014"));
            assertThat(actual1.getLikes().get(3).getName(), is("Like Name14"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-06T10:36:10+0000")));
            assertThat(actual1.getActions().get(0).getName(), is("Comment"));
            assertThat(actual1.getActions().get(0).getLink().toString(), is("https://www.facebook.com/1234567890123456/posts/500000000000001"));
            assertThat(actual1.getActions().get(1).getName(), is("Like"));
            assertThat(actual1.getActions().get(1).getLink().toString(), is("https://www.facebook.com/1234567890123456/posts/500000000000001"));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getPlace().getId(), is("262956980385539"));
            assertThat(actual2.getPlace().getLocation().getZip(), is(""));
            assertThat(actual2.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual2.getPlace().getLocation().getLongitude(), is(131.88856357));
            assertThat(actual2.getPlace().getLocation().getLatitude(), is(34.75762856));
            assertThat(actual2.getPlace().getName(), is("Araisokan"));
            Post actual3 = actuals.get(2);
            assertThat(actual3.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual3.getComments().get(0).getMessage(), is("ww"));
            assertThat(actual3.getComments().get(0).getId(), is("500000000000003_51515151"));
            assertThat(actual3.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("696969696969"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("Comment Name31"));
            assertThat(actual3.getComments().get(0).canRemove(), is(true));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-02T03:41:45+0000")));
            assertThat(actual3.getComments().getPaging().getCursors().getAfter(), is("MQ=="));
            assertThat(actual3.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Post actual6 = actuals.get(5);
            assertThat(actual6.getTo().get(0).getId(), is("1234567890123456"));
            assertThat(actual6.getTo().get(0).getName(), is("My Name"));
            assertThat(actual6.getTo().get(1).getId(), is("100000000000086"));
            assertThat(actual6.getTo().get(1).getName(), is("With Name"));
            assertThat(actual6.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual6.getWithTags().get(0).getId(), is("1234567890123456"));
            assertThat(actual6.getWithTags().get(0).getName(), is("My Name"));
            assertThat(actual6.getWithTags().get(1).getId(), is("100000000000086"));
            assertThat(actual6.getWithTags().get(1).getName(), is("With Name"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/with_location.json");
            ResponseList<Post> actuals = facebook.getFeed(new Reading().withLocation());
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getEndpointURL(), hasParameter("with", "location"));

            assertThat(actuals.size(), is(2));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/feed.json");
            ResponseList<Post> actuals = facebook.getFeed("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/feed")));

            assertThat(actuals.size(), is(6));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yx/r/zzzzzzzz.gif"));
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=570000000000001&set=pcb.500000000000001&type=1&relevant_count=2"));
            assertThat(actual1.getObjectId(), is("570000000000001"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("My Name"));
            assertThat(actual1.getType(), is("photo"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-06T10:36:10+0000")));
            assertThat(actual1.getId(), is("1234567890123456_500000000000001"));
            assertThat(actual1.getMessage(), is("Sunrise"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-a-a.akamaihd.net/hphotos-ak-prn2/1098080_570000000000001_1269337662_s.jpg"));
            assertThat(actual1.getFullPicture().toString(), is("https://fbcdn-photos-a-a.akamaihd.net/hphotos-ak-prn2/1098080_570000000000001_1269337662_full_picture.jpg"));
            assertThat(actual1.getStatusType(), is("mobile_status_update"));
            assertThat(actual1.getLikes().getCount(), is(13));
            assertThat(actual1.getLikes().get(0).getId(), is("100000000000011"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name11"));
            assertThat(actual1.getLikes().get(1).getId(), is("100000000000012"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name12"));
            assertThat(actual1.getLikes().get(2).getId(), is("100000000000013"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name13"));
            assertThat(actual1.getLikes().get(3).getId(), is("100000000000014"));
            assertThat(actual1.getLikes().get(3).getName(), is("Like Name14"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-06T10:36:10+0000")));
            assertThat(actual1.getActions().get(0).getName(), is("Comment"));
            assertThat(actual1.getActions().get(0).getLink().toString(), is("https://www.facebook.com/1234567890123456/posts/500000000000001"));
            assertThat(actual1.getActions().get(1).getName(), is("Like"));
            assertThat(actual1.getActions().get(1).getLink().toString(), is("https://www.facebook.com/1234567890123456/posts/500000000000001"));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getPlace().getId(), is("262956980385539"));
            assertThat(actual2.getPlace().getLocation().getZip(), is(""));
            assertThat(actual2.getPlace().getLocation().getStreet(), is(""));
            assertThat(actual2.getPlace().getLocation().getLongitude(), is(131.88856357));
            assertThat(actual2.getPlace().getLocation().getLatitude(), is(34.75762856));
            assertThat(actual2.getPlace().getName(), is("Araisokan"));
            Post actual3 = actuals.get(2);
            assertThat(actual3.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual3.getComments().get(0).getMessage(), is("ww"));
            assertThat(actual3.getComments().get(0).getId(), is("500000000000003_51515151"));
            assertThat(actual3.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("696969696969"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("Comment Name31"));
            assertThat(actual3.getComments().get(0).canRemove(), is(true));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-02T03:41:45+0000")));
            assertThat(actual3.getComments().getPaging().getCursors().getAfter(), is("MQ=="));
            assertThat(actual3.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Post actual6 = actuals.get(5);
            assertThat(actual6.getTo().get(0).getId(), is("1234567890123456"));
            assertThat(actual6.getTo().get(0).getName(), is("My Name"));
            assertThat(actual6.getTo().get(1).getId(), is("100000000000086"));
            assertThat(actual6.getTo().get(1).getName(), is("With Name"));
            assertThat(actual6.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual6.getWithTags().get(0).getId(), is("1234567890123456"));
            assertThat(actual6.getWithTags().get(0).getName(), is("My Name"));
            assertThat(actual6.getWithTags().get(1).getId(), is("100000000000086"));
            assertThat(actual6.getWithTags().get(1).getName(), is("With Name"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/with_location.json");
            ResponseList<Post> actuals = facebook.getFeed("1234567890123456", new Reading().withLocation());
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/feed")));
            assertThat(facebook.getEndpointURL(), hasParameter("with", "location"));

            assertThat(actuals.size(), is(2));
        }
    }

    public static class getHome extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post/home.json");
            ResponseList<Post> actuals = facebook.getHome();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/home")));

            assertThat(actuals.size(), is(3));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yx/r/zzzz1.gif"));
            assertThat(actual1.getApplication().getId(), is("6628568379"));
            assertThat(actual1.getApplication().getName(), is("Facebook for iPhone"));
            assertThat(actual1.getApplication().getNamespace(), is("fbiphone"));
            assertThat(actual1.getLink().toString(), is("https://www.facebook.com/photo.php?fbid=410000000000002&set=a.154985034612229.29904.100000000000001&type=1&relevant_count=1"));
            assertThat(actual1.getObjectId(), is("410000000000002"));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual1.getFrom().getId(), is("100000000000001"));
            assertThat(actual1.getFrom().getName(), is("Friend Name1"));
            assertThat(actual1.getType(), is("photo"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-18T10:15:04+0000")));
            assertThat(actual1.getId(), is("100000000000001_410000000000001"));
            assertThat(actual1.getMessage(), is("Ebi"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-photos-g-a.akamaihd.net/hphotos-ak-frc3/1185014_410000000000002_1666290908_s.jpg"));
            assertThat(actual1.getFullPicture().toString(), is("https://fbcdn-photos-g-a.akamaihd.net/hphotos-ak-frc3/1185014_410000000000002_1666290908_full_picture.jpg"));
            assertThat(actual1.getStatusType(), is("added_photos"));
            assertThat(actual1.getLikes().getCount(), is(1));
            assertThat(actual1.getLikes().get(0).getId(), is("100000300000000"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name11"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-18T10:15:04+0000")));
            assertThat(actual1.getActions().get(0).getName(), is("Comment"));
            assertThat(actual1.getActions().get(0).getLink().toString(), is("https://www.facebook.com/100000000000001/posts/410000000000001"));
            assertThat(actual1.getActions().get(1).getName(), is("Like"));
            assertThat(actual1.getActions().get(1).getLink().toString(), is("https://www.facebook.com/100000000000001/posts/410000000000001"));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000000002_410000000000003"));
            Post actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000000003_410000000000005"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/post/home_last.json");
            ResponseList<Post> actuals = facebook.getHome(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/home")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001_410000000000001"));
        }
    }

    public static class getPosts extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post/feed.json");
            ResponseList<Post> actuals = facebook.getPosts();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/posts")));

            assertThat(actuals.size(), is(6));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/with_location.json");
            ResponseList<Post> actuals = facebook.getPosts(new Reading().withLocation());
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/posts")));
            assertThat(facebook.getEndpointURL(), hasParameter("with", "location"));

            assertThat(actuals.size(), is(2));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/feed.json");
            ResponseList<Post> actuals = facebook.getPosts("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/posts")));

            assertThat(actuals.size(), is(6));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/with_location.json");
            ResponseList<Post> actuals = facebook.getPosts("1234567890123456", new Reading().withLocation());
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/posts")));
            assertThat(facebook.getEndpointURL(), hasParameter("with", "location"));

            assertThat(actuals.size(), is(2));
        }

        @Test
        public void sharesCount() throws Exception {
            facebook.setMockJSON("mock_json/post/shares_count.json");
            ResponseList<Post> actuals = facebook.getPosts("BillGates", new Reading().fields("shares").limit(5));

            Post actual1 = actuals.get(0);
            assertThat(actual1.getSharesCount(), is(4198));
            Post actual5 = actuals.get(4);
            assertThat(actual5.getSharesCount(), is(1345));
        }
    }

    public static class getStatuses extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post/statuses.json");
            ResponseList<Post> actuals = facebook.getStatuses();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/statuses")));

            assertThat(actuals.size(), is(3));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is("Sunrise"));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getLikes().get(0).getId(), is("100000000000001"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name11"));
            assertThat(actual1.getLikes().get(1).getId(), is("100000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name12"));
            assertThat(actual1.getLikes().get(2).getId(), is("100000000000003"));
            assertThat(actual1.getLikes().get(2).getName(), is("Like Name13"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwMDQ4NzA3MTcz"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyNTU4NDQ3Nzg5"));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("My Name"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-06T10:36:10+0000")));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getMessage(), is("3G"));
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getLikes().get(0).getId(), is("110000000000001"));
            assertThat(actual2.getLikes().get(0).getName(), is("Like Name21"));
            assertThat(actual2.getLikes().getPaging().getCursors().getAfter(), is("NjkyNzY5MzQ0"));
            assertThat(actual2.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMTg5OTc1Mjg4"));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("My Name"));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-08-02T03:39:56+0000")));
            assertThat(actual2.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual2.getComments().get(0).getMessage(), is("ww"));
            assertThat(actual2.getComments().get(0).getId(), is("500000000000002_5100003"));
            assertThat(actual2.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual2.getComments().get(0).getFrom().getId(), is("200000000000001"));
            assertThat(actual2.getComments().get(0).getFrom().getName(), is("Comment Name11"));
            assertThat(actual2.getComments().get(0).canRemove(), is(true));
            assertThat(actual2.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-08-02T03:41:45+0000")));
            assertThat(actual2.getComments().getPaging().getCursors().getAfter(), is("MQ=="));
            assertThat(actual2.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
            Post actual3 = actuals.get(2);
            assertThat(actual3.getMessage(), is("New!"));
            assertThat(actual3.getId(), is("565425746853048"));
            assertThat(actual3.getLikes().get(0).getId(), is("120000000000001"));
            assertThat(actual3.getLikes().get(0).getName(), is("Like Name31"));
            assertThat(actual3.getLikes().get(1).getId(), is("120000000000002"));
            assertThat(actual3.getLikes().get(1).getName(), is("Like Name32"));
            assertThat(actual3.getLikes().get(2).getId(), is("120000000000003"));
            assertThat(actual3.getLikes().get(2).getName(), is("Like Name33"));
            assertThat(actual3.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAxNTQ4NTEzNjU4"));
            assertThat(actual3.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyODU4NTI3NDE5"));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("My Name"));
            assertThat(actual3.getPlace().getId(), is("172643522795721"));
            assertThat(actual3.getPlace().getLocation().getZip(), is(""));
            assertThat(actual3.getPlace().getLocation().getStreet(), is("aabbcc44"));
            assertThat(actual3.getPlace().getLocation().getState(), is("Hokkaido"));
            assertThat(actual3.getPlace().getLocation().getLongitude(), is(140.75461653069));
            assertThat(actual3.getPlace().getLocation().getLatitude(), is(41.795708973186));
            assertThat(actual3.getPlace().getLocation().getCountry(), is("Japan"));
            assertThat(actual3.getPlace().getLocation().getCity(), is("Hakodate-shi"));
            assertThat(actual3.getPlace().getName(), is("Park"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-07-14T02:58:34+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/statuses_message.json");
            ResponseList<Post> actuals = facebook.getStatuses(new Reading().fields("message"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/statuses")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "message"));

            assertThat(actuals.size(), is(3));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getMessage(), is("Sunrise"));
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-08-06T10:36:10+0000")));
            assertThat(actual1.getLikes().size(), is(0));
            Post actual3 = actuals.get(2);
            assertThat(actual3.getMessage(), is("New!"));
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getUpdatedTime(), is(iso8601DateOf("2013-07-14T02:58:34+0000")));
            assertThat(actual3.getPlace(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/statuses.json");
            ResponseList<Post> actuals = facebook.getStatuses("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/statuses")));

            assertThat(actuals.size(), is(3));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/statuses_message.json");
            ResponseList<Post> actuals = facebook.getStatuses("1234567890123456", new Reading().fields("message"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/statuses")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "message"));

            assertThat(actuals.size(), is(3));
        }

        @Test
        public void page() throws Exception {
            facebook.setMockJSON("mock_json/post/page.json");
            String pageId = "19292868552";
            ResponseList<Post> statuses = facebook.getStatuses(pageId);

            assertThat(statuses.size(), is(25));

            Post status = statuses.get(0);
            PagableList<Comment> comments = status.getComments();
            assertThat(comments.size(), is(25));
            assertThat(comments.get(0).canRemove(), is(false));
            assertThat(comments.get(0).getFrom().getId(), is("625087546"));
            assertThat(comments.get(0).getFrom().getName(), is("Kevin Cooke"));
            assertThat(comments.get(0).getId(), is("10150293984393553_18352943"));
            assertThat(comments.get(0).getLikeCount(), is(0));
            assertThat(comments.get(0).getMessage(), is("mogotix is non-responsive.  gateway time out error..."));
            assertThat(comments.get(0).isUserLikes(), is(false));
            assertThat(comments.get(7).canRemove(), is(false));
            assertThat(comments.get(7).getFrom().getId(), is("100000095949504"));
            assertThat(comments.get(7).getFrom().getName(), is("زياد بن علي الرويشان"));
            assertThat(comments.get(7).getId(), is("10150293984393553_18353012"));
            assertThat(comments.get(7).getLikeCount(), is(1));
            assertThat(comments.get(7).getMessage(), is("جميل"));
            assertThat(comments.get(7).isUserLikes(), is(false));
            assertThat(comments.get(24).canRemove(), is(false));
            assertThat(comments.get(24).getFrom().getId(), is("100001590657464"));
            assertThat(comments.get(24).getFrom().getName(), is("Karan Kantaria"));
            assertThat(comments.get(24).getId(), is("10150293984393553_18353134"));
            assertThat(comments.get(24).getLikeCount(), is(0));
            assertThat(comments.get(24).getMessage(), is("aa su chhee."));
            assertThat(comments.get(24).isUserLikes(), is(false));
            assertThat(comments.getPaging().getNext().toString(), is("https://graph.facebook.com/10150293984393553/comments?access_token=access_token&limit=25&after=MzI%3D"));
            assertThat(comments.getPaging().getPrevious(), is(nullValue()));
            assertThat(status.getFrom().getId(), is("19292868552"));
            assertThat(status.getFrom().getName(), is("Facebook Developers"));
//        assertThat(status.getFrom().getCategory(), is("Product/service"));  //TODO
            assertThat(status.getId(), is("10150293984393553"));
            PagableList<Like> likes = status.getLikes();
            assertThat(likes.size(), is(25));
            assertThat(likes.get(0).getId(), is("100002589774742"));
            assertThat(likes.get(0).getName(), is("Katsumi Ohmuro"));
            assertThat(likes.get(23).getId(), is("100002947834268"));
            assertThat(likes.get(23).getName(), is("منى الفهد"));
            assertThat(likes.get(24).getId(), is("100002011318315"));
            assertThat(likes.get(24).getName(), is("Ḿǿẩid Cǿǿl"));
            assertThat(likes.getPaging().getNext().toString(), is("https://graph.facebook.com/10150293984393553/likes?access_token=access_token&limit=25&after=MTAwMDAyMDExMzE4MzE1"));
            assertThat(likes.getPaging().getPrevious(), is(nullValue()));
            assertThat(status.getMessage(), is("The 2nd wave of registration is now open for f8, our developer conference on Sept. 22 in San Francisco. We expect them to sell out quickly, so be sure to buy yours now at f8.facebook.com. Also, don't forget to like the f8 Page for the latest updates on the event."));

            assertThat(statuses.getPaging().getNext().toString(), is("https://graph.facebook.com/19292868552/statuses?access_token=access_token&limit=25&until=1251825600&__paging_token=124014878930"));
            assertThat(statuses.getPaging().getPrevious().toString(), is("https://graph.facebook.com/19292868552/statuses?access_token=access_token&limit=25&since=1314979729&__paging_token=10150293984393553&__previous=1"));
        }
    }

    public static class getTagged extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post/tagged.json");
            ResponseList<Post> actuals = facebook.getTagged();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tagged")));

            assertThat(actuals.size(), is(1));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/tagged_with.json");
            ResponseList<Post> actuals = facebook.getTagged(new Reading().fields("with_tags"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tagged")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "with_tags"));

            assertThat(actuals.size(), is(1));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1234567890123456_500000000000006"));
            assertThat(actual1.getWithTags().get(0).getId(), is("1234567890123456"));
            assertThat(actual1.getWithTags().get(0).getName(), is("My Name"));
            assertThat(actual1.getWithTags().get(1).getId(), is("100000000000086"));
            assertThat(actual1.getWithTags().get(1).getName(), is("With Name"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-13T11:58:24+0000")));
            assertThat(actual1.getMessage(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/tagged.json");
            ResponseList<Post> actuals = facebook.getTagged("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/tagged")));

            assertThat(actuals.size(), is(1));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/post/tagged_with.json");
            ResponseList<Post> actuals = facebook.getTagged("1234567890123456", new Reading().fields("with_tags"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/tagged")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "with_tags"));

            assertThat(actuals.size(), is(1));
        }
    }

    public static class getPost extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/post.json");
            Post actual = facebook.getPost("19292868552_10150189643478553");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/19292868552_10150189643478553")));

            assertThat(actual.getIcon().toString(), is("https://fbcdn-photos-g-a.akamaihd.net/hphotos-ak-prn1/851582_10151414659411134_455889750_n.gif"));
            assertThat(actual.getApplication().getId(), is("9953271133"));
            assertThat(actual.getApplication().getName(), is("NetworkedBlogs"));
            assertThat(actual.getApplication().getNamespace(), is("blognetworks"));
            assertThat(actual.getLink().toString(), is("http://developers.facebook.com/blog/post/497"));
            assertThat(actual.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual.getFrom().getId(), is("19292868552"));
            assertThat(actual.getFrom().getCategory(), is("Product/service"));
            assertThat(actual.getFrom().getName(), is("Facebook Developers"));
            assertThat(actual.getProperties().size(), is(2));
            assertThat(actual.getProperties().get(0).getText(), is("Official Facebook Developer Blog"));
            assertThat(actual.getProperties().get(0).getName(), is("source"));
            assertThat(actual.getProperties().get(0).getHref(), is("http://apps.facebook.com/blognetworks/blog/official_facebook_developer_blog"));
            assertThat(actual.getProperties().get(1).getText(), is("Full Article..."));
            assertThat(actual.getProperties().get(1).getName(), is("link"));
            assertThat(actual.getProperties().get(1).getHref(), is("http://developers.facebook.com/blog/post/497"));
            assertThat(actual.getType(), is("link"));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-08-18T12:03:22+0000")));
            assertThat(actual.getId(), is("19292868552_10150189643478553"));
            assertThat(actual.isHidden(), is(false));
            assertThat(actual.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/app_full_proxy.php?app=9953271133&v=3&size=z&cksum=e15ac22d55f6a9501d3b3ac64c5fb763&src=http%3A%2F%2Fimg.bitpixels.com%2Fgetthumbnail%3Fcode%3D78793%26size%3D120%26url%3Dhttp%3A%2F%2Fdevelopers.facebook.com%2Fblog%2F"));
            assertThat(actual.getFullPicture().toString(), is("https://fbexternal-a.akamaihd.net/app_full_proxy.php?app=9953271133&v=3&size=z&cksum=e15ac22d55f6a9501d3b3ac64c5fb763&src=http%3A%2F%2Fimg.bitpixels.com%2Fgetthumbnail%3Fcode%3D78793%26size%3D120%26url%3Dhttp%3A%2F%2Fdevelopers.facebook.com%2Fblog%2F&full_picture"));
            assertThat(actual.getStatusType(), is("app_created_story"));
            assertThat(actual.getDescription(), is("\nWe continue to make Platform more secure for users. Earlier this year, we introduced the ability for users to browse Facebook over HTTPS. As a result, we provided \u201cSecure Canvas URL\u201d and \u201cSecure Tab URL\u201d fields in the Developer App for developers to serve their apps through an H"));
            assertThat(actual.getLikes().getCount(), is(8064));
            assertThat(actual.getLikes().get(0).getId(), is("100000670927963"));
            assertThat(actual.getLikes().get(0).getName(), is("Xiaoguang Wang"));
            assertThat(actual.getLikes().get(1).getId(), is("1011399360"));
            assertThat(actual.getLikes().get(1).getName(), is("Samuel Silva"));
            assertThat(actual.getLikes().get(2).getId(), is("100002285148138"));
            assertThat(actual.getLikes().get(2).getName(), is("Yo Yo Chen"));
            assertThat(actual.getLikes().get(3).getId(), is("100002741111992"));
            assertThat(actual.getLikes().get(3).getName(), is("Tony Tang"));
            assertThat(actual.getName(), is("Developer Roadmap Update: Moving to OAuth 2.0 + HTTPS"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2011-05-10T18:35:38+0000")));
            assertThat(actual.getActions().size(), is(3));
            assertThat(actual.getActions().get(0).getName(), is("Comment"));
            assertThat(actual.getActions().get(0).getLink().toString(), is("https://www.facebook.com/19292868552/posts/10150189643478553"));
            assertThat(actual.getActions().get(1).getName(), is("Like"));
            assertThat(actual.getActions().get(1).getLink().toString(), is("https://www.facebook.com/19292868552/posts/10150189643478553"));
            assertThat(actual.getActions().get(2).getName(), is("Share"));
            assertThat(actual.getActions().get(2).getLink().toString(), is("http://networkedblogs.com/hGWk3?a=share"));
            assertThat(actual.getScheduledPublishTime(), is(nullValue()));
        }

        @Test
        public void idWithParent() throws Exception {
            facebook.setMockJSON("mock_json/post/post_with_parent.json");
            Post actual = facebook.getPost("158798184134250_1348514785162578");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/158798184134250_1348514785162578")));

            assertThat(actual.getId(), is("158798184134250_1348514785162578"));
            assertThat(actual.getParentId(), is("12546364967_10154962152799968"));
        }

        @Test
        public void idWithSummaries() throws Exception {
            facebook.setMockJSON("mock_json/post/post_with_summaries.json");
            Post actual = facebook.getPost("19292868552_10150189643478553");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/19292868552_10150189643478553")));

            assertThat(actual.getIcon().toString(), is("https://fbcdn-photos-g-a.akamaihd.net/hphotos-ak-prn1/851582_10151414659411134_455889750_n.gif"));
            assertThat(actual.getApplication().getId(), is("9953271133"));
            assertThat(actual.getApplication().getName(), is("NetworkedBlogs"));
            assertThat(actual.getApplication().getNamespace(), is("blognetworks"));
            assertThat(actual.getLink().toString(), is("http://developers.facebook.com/blog/post/497"));
            assertThat(actual.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(actual.getFrom().getId(), is("19292868552"));
            assertThat(actual.getFrom().getCategory(), is("Product/service"));
            assertThat(actual.getFrom().getName(), is("Facebook Developers"));
            assertThat(actual.getProperties().size(), is(2));
            assertThat(actual.getProperties().get(0).getText(), is("Official Facebook Developer Blog"));
            assertThat(actual.getProperties().get(0).getName(), is("source"));
            assertThat(actual.getProperties().get(0).getHref(), is("http://apps.facebook.com/blognetworks/blog/official_facebook_developer_blog"));
            assertThat(actual.getProperties().get(1).getText(), is("Full Article..."));
            assertThat(actual.getProperties().get(1).getName(), is("link"));
            assertThat(actual.getProperties().get(1).getHref(), is("http://developers.facebook.com/blog/post/497"));
            assertThat(actual.getType(), is("link"));
            assertThat(actual.getUpdatedTime(), is(iso8601DateOf("2013-08-18T12:03:22+0000")));
            assertThat(actual.getId(), is("19292868552_10150189643478553"));
            assertThat(actual.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/app_full_proxy.php?app=9953271133&v=3&size=z&cksum=e15ac22d55f6a9501d3b3ac64c5fb763&src=http%3A%2F%2Fimg.bitpixels.com%2Fgetthumbnail%3Fcode%3D78793%26size%3D120%26url%3Dhttp%3A%2F%2Fdevelopers.facebook.com%2Fblog%2F"));
            assertThat(actual.getFullPicture().toString(), is("https://fbexternal-a.akamaihd.net/app_full_proxy.php?app=9953271133&v=3&size=z&cksum=e15ac22d55f6a9501d3b3ac64c5fb763&src=http%3A%2F%2Fimg.bitpixels.com%2Fgetthumbnail%3Fcode%3D78793%26size%3D120%26url%3Dhttp%3A%2F%2Fdevelopers.facebook.com%2Fblog%2F&full_picture"));
            assertThat(actual.getStatusType(), is("app_created_story"));
            assertThat(actual.getDescription(), is("\nWe continue to make Platform more secure for users. Earlier this year, we introduced the ability for users to browse Facebook over HTTPS. As a result, we provided \u201cSecure Canvas URL\u201d and \u201cSecure Tab URL\u201d fields in the Developer App for developers to serve their apps through an H"));
            assertThat(actual.getLikes().getCount(), is(8064));
            assertThat(actual.getLikes().get(0).getId(), is("100000670927963"));
            assertThat(actual.getLikes().get(0).getName(), is("Xiaoguang Wang"));
            assertThat(actual.getLikes().get(1).getId(), is("1011399360"));
            assertThat(actual.getLikes().get(1).getName(), is("Samuel Silva"));
            assertThat(actual.getLikes().get(2).getId(), is("100002285148138"));
            assertThat(actual.getLikes().get(2).getName(), is("Yo Yo Chen"));
            assertThat(actual.getLikes().get(3).getId(), is("100002741111992"));
            assertThat(actual.getLikes().get(3).getName(), is("Tony Tang"));
            assertThat(actual.getLikes().getSummary(), is(notNullValue()));
            assertThat(actual.getLikes().getSummary().getTotalCount(), is(7882));
            assertThat(actual.getName(), is("Developer Roadmap Update: Moving to OAuth 2.0 + HTTPS"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2011-05-10T18:35:38+0000")));
            assertThat(actual.getActions().size(), is(3));
            assertThat(actual.getActions().get(0).getName(), is("Comment"));
            assertThat(actual.getActions().get(0).getLink().toString(), is("https://www.facebook.com/19292868552/posts/10150189643478553"));
            assertThat(actual.getActions().get(1).getName(), is("Like"));
            assertThat(actual.getActions().get(1).getLink().toString(), is("https://www.facebook.com/19292868552/posts/10150189643478553"));
            assertThat(actual.getActions().get(2).getName(), is("Share"));
            assertThat(actual.getActions().get(2).getLink().toString(), is("http://networkedblogs.com/hGWk3?a=share"));
            assertThat(actual.getScheduledPublishTime(), is(nullValue()));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/post/post_picture.json");
            Post actual = facebook.getPost("19292868552_10150189643478553", new Reading().fields("picture"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/19292868552_10150189643478553")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "picture"));

            assertThat(actual.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/app_full_proxy.php?app=9953271133&v=3&size=z&cksum=e15ac22d55f6a9501d3b3ac64c5fb763&src=http%3A%2F%2Fimg.bitpixels.com%2Fgetthumbnail%3Fcode%3D78793%26size%3D120%26url%3Dhttp%3A%2F%2Fdevelopers.facebook.com%2Fblog%2F"));
            assertThat(actual.getId(), is("19292868552_10150189643478553"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2011-05-10T18:35:38+0000")));
        }

        @Test
        public void getStatus_statusType() throws Exception {
            facebook.setMockJSON("mock_json/post/with_status_type.json");
            Post post = facebook.getPost("1111_2222");
            assertThat(post.getStatusType(), is("approved_friend"));
        }

        @Test
        public void permalink_url() throws Exception {
            facebook.setMockJSON("mock_json/post/permalink_url.json");
            Post post = facebook.getPost("144105014310_10155533674019311", new Reading().fields("permalink_url"));
            assertThat(post.getPermalinkUrl().toString(), is("https://www.facebook.com/Panasonic/posts/10155533674019311"));
        }
    }

    public static class getPostComments extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/comments.json");
            ResponseList<Comment> actuals = facebook.getPostComments("216311481960_10201168076257947");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/216311481960_10201168076257947/comments")));

            assertThat(actuals.size(), is(25));
            Comment actual1 = actuals.get(0);
            assertThat(actual1.isUserLikes(), is(false));
            assertThat(actual1.getMessage(), is("true (Y)"));
            assertThat(actual1.getId(), is("10201168076257947_64183644"));
            assertThat(actual1.getLikeCount(), is(0));
            assertThat(actual1.getFrom().getId(), is("100002080567255"));
            assertThat(actual1.getFrom().getName(), is("Rishu Rish"));
            assertThat(actual1.canRemove(), is(false));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-16T17:49:28+0000")));
            Comment actual7 = actuals.get(6);
            assertThat(actual7.isUserLikes(), is(false));
            assertThat(actual7.getMessage(), is("Do you know the Aerotrem project, Bill? - with Guilherme Rebechi Fonseca, Rafael Abraham"));
            assertThat(actual7.getId(), is("10201168076257947_64183655"));
            //TODO message_tags
            assertThat(actual7.getLikeCount(), is(1));
            assertThat(actual7.getFrom().getId(), is("1142574430"));
            assertThat(actual7.getFrom().getName(), is("André Kliousoff Junior"));
            assertThat(actual7.canRemove(), is(false));
            assertThat(actual7.getCreatedTime(), is(iso8601DateOf("2013-08-16T17:50:23+0000")));
            Comment actual25 = actuals.get(24);
            assertThat(actual25.isUserLikes(), is(false));
            assertThat(actual25.getMessage(), is("Imagine exploring 13.7 billion dollars"));
            assertThat(actual25.getId(), is("10201168076257947_64183685"));
            assertThat(actual25.getLikeCount(), is(0));
            assertThat(actual25.getFrom().getId(), is("578541718"));
            assertThat(actual25.getFrom().getName(), is("Vincent LaRoche"));
            assertThat(actual25.canRemove(), is(false));
            assertThat(actual25.getCreatedTime(), is(iso8601DateOf("2013-08-16T17:52:30+0000")));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/post/comments_last3.json");
            ResponseList<Comment> actuals = facebook.getPostComments("216311481960_10201168076257947", new Reading().limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/216311481960_10201168076257947/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));

            Summary summary = actuals.getSummary();
        }

        @Test
        @FacebookAPIVersion("v2.1")
        public void withoutSummary() throws Exception {
            facebook.setMockJSON("mock_json/post/comments_without_summary.json");
            ResponseList<Comment> actuals = facebook.getPostComments("25781101980_10152312644461981");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.1/25781101980_10152312644461981/comments")));

            Summary summary = actuals.getSummary();
            assertThat(summary, is(nullValue()));
        }

        @Test
        @FacebookAPIVersion("v2.1")
        public void withSummary() throws Exception {
            facebook.setMockJSON("mock_json/post/comments_with_summary.json");
            ResponseList<Comment> actuals = facebook.getPostComments("25781101980_10152312644461981", new Reading().summary());
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.1/25781101980_10152312644461981/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("summary", "true"));

            Summary summary = actuals.getSummary();
            assertThat(summary, is(notNullValue()));
            assertThat(summary.getOrder(), is(Summary.SummaryOrder.ranked));
            assertThat(summary.getTotalCount(), is(37));
        }

        @Test
        @FacebookAPIVersion("v2.1")
        public void withSummaryAsStream() throws Exception {
            facebook.setMockJSON("mock_json/post/comments_with_summary_filter_stream.json");
            ResponseList<Comment> actuals = facebook.getPostComments("25781101980_10152312644461981", new Reading().summary().filter("stream"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.1/25781101980_10152312644461981/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("summary", "true"));
            assertThat(facebook.getEndpointURL(), hasParameter("filter", "stream"));

            Summary summary = actuals.getSummary();
            assertThat(summary, is(notNullValue()));
            assertThat(summary.getOrder(), is(Summary.SummaryOrder.chronological));
            assertThat(summary.getTotalCount(), is(37));
        }
    }

    public static class getPostLikes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/likes.json");
            ResponseList<Like> actuals = facebook.getPostLikes("216311481960_10201168076257947");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/216311481960_10201168076257947/likes")));

            assertThat(actuals.size(), is(25));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("1537490351"));
            assertThat(actual1.getName(), is("Tsuneki Kusaba"));
            Like actual25 = actuals.get(24);
            assertThat(actual25.getId(), is("622365451"));
            assertThat(actual25.getName(), is("Dilip Mhaske"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/post/likes_last5.json");
            ResponseList<Like> actuals = facebook.getPostLikes("216311481960_10201168076257947", new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/216311481960_10201168076257947/likes")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
        }

        @Test
        @FacebookAPIVersion("v2.1")
        public void withSummary() throws Exception {
            facebook.setMockJSON("mock_json/post/likes_with_summary.json");
            ResponseList<Like> actuals = facebook.getPostLikes("25781101980_10152312644461981", new Reading().summary());
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.1/25781101980_10152312644461981/likes")));
            assertThat(facebook.getEndpointURL(), hasParameter("summary", "true"));

            Summary summary = actuals.getSummary();
            assertThat(summary, is(notNullValue()));
            assertThat(summary.getTotalCount(), is(2596));
        }
    }

    public static class getSharedPosts extends MockFacebookTestBase {

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post/shares.json");
            ResponseList<Post> actuals = facebook.getSharedPosts("216311481960_10201168076257947");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/216311481960_10201168076257947/sharedposts")));

            assertThat(actuals.size(), is(14));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("188925184835149_172970369763964"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/post/shares_last5.json");
            ResponseList<Post> actuals = facebook.getSharedPosts("216311481960_10201168076257947", new Reading().limit(5));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/216311481960_10201168076257947/sharedposts")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));

            assertThat(actuals.size(), is(5));
        }

    }

    public static class commentPost extends MockFacebookTestBase {
        @Test
        public void comment() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.commentPost("600000000000001", "test");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void byCommentUpdate() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.commentPost("600000000000001", new CommentUpdate().message("test"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withAttachmentId() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentId("1122334455667788");
            String actual = facebook.commentPost("600000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withAttachmentUrl() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentUrl("https://fortunedotcom.files.wordpress.com/2015/04/467495334.jpg?quality=80&w=840&h=485&crop=1");
            String actual = facebook.commentPost("600000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void withSource() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            CommentUpdate source = new CommentUpdate()
                                    .message("test")
                                    .source(new Media(new File("src/test/resources/test_image.png")));
            String actual = facebook.commentPost("600000000000001", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/600000000000001/comments")));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class likePost extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.likePost("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class unlikePost extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.unlikePost("500000000000001");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class postFeed extends MockFacebookTestBase {
        @Test
        public void me_onlyMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            PostUpdate postUpdate = new PostUpdate("test message");
            String actual = facebook.postFeed(postUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test message"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void id_onlyURL() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            PostUpdate postUpdate = new PostUpdate(new URL("http://facebook4j.org"));
            String actual = facebook.postFeed("21212121212121", postUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void share() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            PostUpdate postUpdate = new PostUpdate(new URL("http://facebook4j.org"))
                    .picture(new URL("http://facebook4j.org/images/hero.png"))
                    .name("Facebook4J - A Java library for the Facebook Graph API")
                    .caption("facebook4j.org")
                    .description("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library.");
            String actual = facebook.postFeed(postUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("picture", "http://facebook4j.org/images/hero.png"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("name", "Facebook4J - A Java library for the Facebook Graph API"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("caption", "facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void pagePostUpdate() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            PagePostUpdate pagePostUpdate = new PagePostUpdate("test message");
            FeedTargetingParameter feedTargeting = new FeedTargetingParameter().genders(FeedTargetingParameter.Gender.Male);
            feedTargeting.setAgeMin(20);
            feedTargeting.setAgeMax(40);
            pagePostUpdate.setFeedTargeting(feedTargeting);
            String actual = facebook.postFeed("eclipse", pagePostUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/eclipse/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test message"));
            assertThat(facebook.getHttpParameters(), hasPostJsonParameter("feed_targeting", "{\"age_min\":20,\"genders\":{\"value\":1},\"age_max\":40}"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

    }

    public static class postLink extends MockFacebookTestBase {
        @Test
        public void me_onlyLink() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postLink(new URL("http://facebook4j.org"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void me_withMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postLink(new URL("http://facebook4j.org"), "The best Facebook API wrapper!");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "The best Facebook API wrapper!"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void id_onlyLink() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postLink("21212121212121", new URL("http://facebook4j.org"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void id_withMessage() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postLink("21212121212121", new URL("http://facebook4j.org"), "The best Facebook API wrapper!");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("link", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "The best Facebook API wrapper!"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class postStatusMessage extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postStatusMessage("Facebook4J is a Java library for the Facebook Graph API.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "Facebook4J is a Java library for the Facebook Graph API."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String actual = facebook.postStatusMessage("21212121212121", "Facebook4J is a Java library for the Facebook Graph API.");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/21212121212121/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "Facebook4J is a Java library for the Facebook Graph API."));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void page() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            String postId = facebook.postStatusMessage("137246726435626", "page's status update");
            assertThat(postId, is("137246726435626_185932178233747"));
        }
    }

    public static class deletePost extends MockFacebookTestBase {
        @Test
        public void delete() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deletePost("500000000000001_1122334455");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001_1122334455")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void delete_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deletePost("500000000000001_1122334455");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/500000000000001_1122334455")));

            assertThat(actual, is(true));
        }
    }

    public static class postReactions extends MockFacebookTestBase {
        @Test
        @FacebookAPIVersion("v2.6")
        public void reactions_v26() throws Exception {
            facebook.setMockJSON("mock_json/post/post_reactions.json");
            Post actual = facebook.getPost("19292868552_10153627866518553", new Reading().fields("reactions"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "reactions"));

            assertThat(actual.getId(), is("19292868552_10153627866518553"));
            assertThat(actual.getReactions().size(), is(100));
        }
    }


}
