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

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class LinkMethodsTest {

    public static class getLinks extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/link/links.json");
            ResponseList<Link> actuals = facebook.getLinks();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/links")));

            assertThat(actuals.size(), is(3));
            Link actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzz.gif"));
            assertThat(actual1.getDescription(), is("Play!Framework - Facebook"));
            assertThat(actual1.getName(), is("Play!Framework - Facebook"));
            assertThat(actual1.getLink().toString(), is("http://www.example.com/blog/?p=123"));
            assertThat(actual1.getLikes().get(0).getId(), is("100000000000001"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("100000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMDQ4NzA3MTcz"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-23T16:08:36+0000")));
            Link actual2 = actuals.get(1);
            assertThat(actual2.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQCdDR30oyGZpfOE&w=154&h=154&url=http%3A%2F%2Fwww.example.com%2Fwp-content%2Fuploads%2F2013%2F05%2Ffafafafa.jpg"));
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/yyyyyyyyyyy.gif"));
            assertThat(actual2.getDescription(), is("Using a library called facebook4j ..."));
            assertThat(actual2.getName(), is("Access your Facebook feed and your friends feed with Java."));
            assertThat(actual2.getLink().toString(), is("http://www.example.com/using-a-library-called-facebook4j.html"));
            assertThat(actual2.getLikes().get(0).getId(), is("100000000000003"));
            assertThat(actual2.getLikes().get(0).getName(), is("Like Name3"));
            assertThat(actual2.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual2.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual2.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual2.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual2.getPrivacy().getAllow().size(), is(0));
            assertThat(actual2.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual2.getPrivacy().getDeny().size(), is(0));
            assertThat(actual2.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("From Name1"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-23T16:03:01+0000")));
            Link actual3 = actuals.get(2);
            assertThat(actual3.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQAwNCPIMEzKoJYo&w=154&h=154&url=http%3A%2F%2Fexample.com%2Fwp-content%2Fuploads%2Fjjghieieffjfds.jpg"));
            assertThat(actual3.getMessage(), is("textrans!"));
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/wwwwwwwwwww.gif"));
            assertThat(actual3.getDescription(), is("descriptionnn"));
            assertThat(actual3.getName(), is("Web service [textrans]"));
            assertThat(actual3.getLink().toString(), is("http://example.com/3916/"));
            assertThat(actual3.getLikes().get(0).getId(), is("400000000000001"));
            assertThat(actual3.getLikes().get(0).getName(), is("Like Name31"));
            assertThat(actual3.getLikes().get(1).getId(), is("400000000000002"));
            assertThat(actual3.getLikes().get(1).getName(), is("Like Name32"));
            assertThat(actual3.getLikes().get(2).getId(), is("400000000000003"));
            assertThat(actual3.getLikes().get(2).getName(), is("Like Name33"));
            assertThat(actual3.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwMzI1Mzk4NzAw"));
            assertThat(actual3.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMTg5OTc1Mjg4"));
            assertThat(actual3.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual3.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual3.getPrivacy().getAllow().size(), is(0));
            assertThat(actual3.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual3.getPrivacy().getDeny().size(), is(0));
            assertThat(actual3.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("From Name1"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2013-02-28T15:15:32+0000")));
            assertThat(actual3.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual3.getComments().get(0).getMessage(), is("Like!"));
            assertThat(actual3.getComments().get(0).getId(), is("500000000000001_5000004"));
            assertThat(actual3.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("22222222222222"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("Comment From1"));
            assertThat(actual3.getComments().get(0).canRemove(), is(true));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-02-28T17:28:55+0000")));
            assertThat(actual3.getComments().get(1).isUserLikes(), is(true));
            assertThat(actual3.getComments().get(1).getMessage(), is("Wow! Cool web service!"));
            assertThat(actual3.getComments().get(1).getId(), is("500000000000001_5000005"));
            assertThat(actual3.getComments().get(1).getLikeCount(), is(1));
            assertThat(actual3.getComments().get(1).getFrom().getId(), is("3333333333333333"));
            assertThat(actual3.getComments().get(1).getFrom().getName(), is("Comment From2"));
            assertThat(actual3.getComments().get(1).canRemove(), is(true));
            assertThat(actual3.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:24:49+0000")));
            assertThat(actual3.getComments().get(2).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(2).getMessage(), is("Give me apple-touchicon..."));
            assertThat(actual3.getComments().get(2).getId(), is("500000000000001_5000006"));
            assertThat(actual3.getComments().get(2).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getComments().get(2).getFrom().getName(), is("From Name1"));
            assertThat(actual3.getComments().get(2).canRemove(), is(true));
            assertThat(actual3.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:29:35+0000")));
            assertThat(actual3.getComments().get(3).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(3).getMessage(), is("So expensive..."));
            assertThat(actual3.getComments().get(3).getId(), is("500000000000001_5000007"));
            assertThat(actual3.getComments().get(3).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(3).getFrom().getId(), is("3333333333333333"));
            assertThat(actual3.getComments().get(3).getFrom().getName(), is("Comment From2"));
            assertThat(actual3.getComments().get(3).canRemove(), is(true));
            assertThat(actual3.getComments().get(3).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:57:21+0000")));
            assertThat(actual3.getComments().getPaging().getCursors().getAfter(), is("NA=="));
            assertThat(actual3.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/link/link_1.json");
            ResponseList<Link> actuals = facebook.getLinks(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/links")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Link actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzz.gif"));
            assertThat(actual1.getDescription(), is("Play!Framework - Facebook"));
            assertThat(actual1.getName(), is("Play!Framework - Facebook"));
            assertThat(actual1.getLink().toString(), is("http://www.example.com/blog/?p=123"));
            assertThat(actual1.getLikes().get(0).getId(), is("100000000000001"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("100000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMDQ4NzA3MTcz"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-23T16:08:36+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/link/links.json");
            ResponseList<Link> actuals = facebook.getLinks("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/links")));

            assertThat(actuals.size(), is(3));
            Link actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzz.gif"));
            assertThat(actual1.getDescription(), is("Play!Framework - Facebook"));
            assertThat(actual1.getName(), is("Play!Framework - Facebook"));
            assertThat(actual1.getLink().toString(), is("http://www.example.com/blog/?p=123"));
            assertThat(actual1.getLikes().get(0).getId(), is("100000000000001"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("100000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMDQ4NzA3MTcz"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-23T16:08:36+0000")));
            Link actual2 = actuals.get(1);
            assertThat(actual2.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQCdDR30oyGZpfOE&w=154&h=154&url=http%3A%2F%2Fwww.example.com%2Fwp-content%2Fuploads%2F2013%2F05%2Ffafafafa.jpg"));
            assertThat(actual2.getId(), is("500000000000002"));
            assertThat(actual2.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/yyyyyyyyyyy.gif"));
            assertThat(actual2.getDescription(), is("Using a library called facebook4j ..."));
            assertThat(actual2.getName(), is("Access your Facebook feed and your friends feed with Java."));
            assertThat(actual2.getLink().toString(), is("http://www.example.com/using-a-library-called-facebook4j.html"));
            assertThat(actual2.getLikes().get(0).getId(), is("100000000000003"));
            assertThat(actual2.getLikes().get(0).getName(), is("Like Name3"));
            assertThat(actual2.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual2.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual2.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual2.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual2.getPrivacy().getAllow().size(), is(0));
            assertThat(actual2.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual2.getPrivacy().getDeny().size(), is(0));
            assertThat(actual2.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual2.getFrom().getId(), is("1234567890123456"));
            assertThat(actual2.getFrom().getName(), is("From Name1"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-23T16:03:01+0000")));
            Link actual3 = actuals.get(2);
            assertThat(actual3.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQAwNCPIMEzKoJYo&w=154&h=154&url=http%3A%2F%2Fexample.com%2Fwp-content%2Fuploads%2Fjjghieieffjfds.jpg"));
            assertThat(actual3.getMessage(), is("textrans!"));
            assertThat(actual3.getId(), is("500000000000003"));
            assertThat(actual3.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/wwwwwwwwwww.gif"));
            assertThat(actual3.getDescription(), is("descriptionnn"));
            assertThat(actual3.getName(), is("Web service [textrans]"));
            assertThat(actual3.getLink().toString(), is("http://example.com/3916/"));
            assertThat(actual3.getLikes().get(0).getId(), is("400000000000001"));
            assertThat(actual3.getLikes().get(0).getName(), is("Like Name31"));
            assertThat(actual3.getLikes().get(1).getId(), is("400000000000002"));
            assertThat(actual3.getLikes().get(1).getName(), is("Like Name32"));
            assertThat(actual3.getLikes().get(2).getId(), is("400000000000003"));
            assertThat(actual3.getLikes().get(2).getName(), is("Like Name33"));
            assertThat(actual3.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwMzI1Mzk4NzAw"));
            assertThat(actual3.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMTg5OTc1Mjg4"));
            assertThat(actual3.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual3.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual3.getPrivacy().getAllow().size(), is(0));
            assertThat(actual3.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual3.getPrivacy().getDeny().size(), is(0));
            assertThat(actual3.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual3.getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getFrom().getName(), is("From Name1"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2013-02-28T15:15:32+0000")));
            assertThat(actual3.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual3.getComments().get(0).getMessage(), is("Like!"));
            assertThat(actual3.getComments().get(0).getId(), is("500000000000001_5000004"));
            assertThat(actual3.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual3.getComments().get(0).getFrom().getId(), is("22222222222222"));
            assertThat(actual3.getComments().get(0).getFrom().getName(), is("Comment From1"));
            assertThat(actual3.getComments().get(0).canRemove(), is(true));
            assertThat(actual3.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-02-28T17:28:55+0000")));
            assertThat(actual3.getComments().get(1).isUserLikes(), is(true));
            assertThat(actual3.getComments().get(1).getMessage(), is("Wow! Cool web service!"));
            assertThat(actual3.getComments().get(1).getId(), is("500000000000001_5000005"));
            assertThat(actual3.getComments().get(1).getLikeCount(), is(1));
            assertThat(actual3.getComments().get(1).getFrom().getId(), is("3333333333333333"));
            assertThat(actual3.getComments().get(1).getFrom().getName(), is("Comment From2"));
            assertThat(actual3.getComments().get(1).canRemove(), is(true));
            assertThat(actual3.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:24:49+0000")));
            assertThat(actual3.getComments().get(2).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(2).getMessage(), is("Give me apple-touchicon..."));
            assertThat(actual3.getComments().get(2).getId(), is("500000000000001_5000006"));
            assertThat(actual3.getComments().get(2).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actual3.getComments().get(2).getFrom().getName(), is("From Name1"));
            assertThat(actual3.getComments().get(2).canRemove(), is(true));
            assertThat(actual3.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:29:35+0000")));
            assertThat(actual3.getComments().get(3).isUserLikes(), is(false));
            assertThat(actual3.getComments().get(3).getMessage(), is("So expensive..."));
            assertThat(actual3.getComments().get(3).getId(), is("500000000000001_5000007"));
            assertThat(actual3.getComments().get(3).getLikeCount(), is(0));
            assertThat(actual3.getComments().get(3).getFrom().getId(), is("3333333333333333"));
            assertThat(actual3.getComments().get(3).getFrom().getName(), is("Comment From2"));
            assertThat(actual3.getComments().get(3).canRemove(), is(true));
            assertThat(actual3.getComments().get(3).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:57:21+0000")));
            assertThat(actual3.getComments().getPaging().getCursors().getAfter(), is("NA=="));
            assertThat(actual3.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/link/link_1.json");
            ResponseList<Link> actuals = facebook.getLinks("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/links")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Link actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("500000000000001"));
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/zzzzzzzzzzzz.gif"));
            assertThat(actual1.getDescription(), is("Play!Framework - Facebook"));
            assertThat(actual1.getName(), is("Play!Framework - Facebook"));
            assertThat(actual1.getLink().toString(), is("http://www.example.com/blog/?p=123"));
            assertThat(actual1.getLikes().get(0).getId(), is("100000000000001"));
            assertThat(actual1.getLikes().get(0).getName(), is("Like Name1"));
            assertThat(actual1.getLikes().get(1).getId(), is("100000000000002"));
            assertThat(actual1.getLikes().get(1).getName(), is("Like Name2"));
            assertThat(actual1.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAyMzE2NjEwMTc1"));
            assertThat(actual1.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMDQ4NzA3MTcz"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("From Name1"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-07-23T16:08:36+0000")));
        }
    }

    public static class getLink extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/link/link.json");
            Link actual = facebook.getLink("500000000000003");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003")));

            assertThat(actual.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQAwNCPIMEzKoJYo&w=154&h=154&url=http%3A%2F%2Fexample.com%2Fwp-content%2Fuploads%2Fjjghieieffjfds.jpg"));
            assertThat(actual.getMessage(), is("textrans!"));
            assertThat(actual.getId(), is("500000000000003"));
            assertThat(actual.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/wwwwwwwwwww.gif"));
            assertThat(actual.getDescription(), is("descriptionnn"));
            assertThat(actual.getName(), is("Web service [textrans]"));
            assertThat(actual.getLink().toString(), is("http://example.com/3916/"));
            assertThat(actual.getLikes().get(0).getId(), is("400000000000001"));
            assertThat(actual.getLikes().get(0).getName(), is("Like Name31"));
            assertThat(actual.getLikes().get(1).getId(), is("400000000000002"));
            assertThat(actual.getLikes().get(1).getName(), is("Like Name32"));
            assertThat(actual.getLikes().get(2).getId(), is("400000000000003"));
            assertThat(actual.getLikes().get(2).getName(), is("Like Name33"));
            assertThat(actual.getLikes().getPaging().getCursors().getAfter(), is("MTAwMDAwMzI1Mzk4NzAw"));
            assertThat(actual.getLikes().getPaging().getCursors().getBefore(), is("MTAwMDAwMTg5OTc1Mjg4"));
            assertThat(actual.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual.getPrivacy().getAllow().size(), is(0));
            assertThat(actual.getPrivacy().getDescription().get(0), is("Friends; Except: Restricted"));
            assertThat(actual.getPrivacy().getDeny().size(), is(0));
            assertThat(actual.getPrivacy().getValue(), is(PrivacyType.ALL_FRIENDS));
            assertThat(actual.getFrom().getId(), is("1234567890123456"));
            assertThat(actual.getFrom().getName(), is("From Name1"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-02-28T15:15:32+0000")));
            assertThat(actual.getComments().get(0).isUserLikes(), is(true));
            assertThat(actual.getComments().get(0).getMessage(), is("Like!"));
            assertThat(actual.getComments().get(0).getId(), is("500000000000001_5000004"));
            assertThat(actual.getComments().get(0).getLikeCount(), is(1));
            assertThat(actual.getComments().get(0).getFrom().getId(), is("22222222222222"));
            assertThat(actual.getComments().get(0).getFrom().getName(), is("Comment From1"));
            assertThat(actual.getComments().get(0).canRemove(), is(true));
            assertThat(actual.getComments().get(0).getCreatedTime(), is(iso8601DateOf("2013-02-28T17:28:55+0000")));
            assertThat(actual.getComments().get(1).isUserLikes(), is(true));
            assertThat(actual.getComments().get(1).getMessage(), is("Wow! Cool web service!"));
            assertThat(actual.getComments().get(1).getId(), is("500000000000001_5000005"));
            assertThat(actual.getComments().get(1).getLikeCount(), is(1));
            assertThat(actual.getComments().get(1).getFrom().getId(), is("3333333333333333"));
            assertThat(actual.getComments().get(1).getFrom().getName(), is("Comment From2"));
            assertThat(actual.getComments().get(1).canRemove(), is(true));
            assertThat(actual.getComments().get(1).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:24:49+0000")));
            assertThat(actual.getComments().get(2).isUserLikes(), is(false));
            assertThat(actual.getComments().get(2).getMessage(), is("Give me apple-touchicon..."));
            assertThat(actual.getComments().get(2).getId(), is("500000000000001_5000006"));
            assertThat(actual.getComments().get(2).getLikeCount(), is(0));
            assertThat(actual.getComments().get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actual.getComments().get(2).getFrom().getName(), is("From Name1"));
            assertThat(actual.getComments().get(2).canRemove(), is(true));
            assertThat(actual.getComments().get(2).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:29:35+0000")));
            assertThat(actual.getComments().get(3).isUserLikes(), is(false));
            assertThat(actual.getComments().get(3).getMessage(), is("So expensive..."));
            assertThat(actual.getComments().get(3).getId(), is("500000000000001_5000007"));
            assertThat(actual.getComments().get(3).getLikeCount(), is(0));
            assertThat(actual.getComments().get(3).getFrom().getId(), is("3333333333333333"));
            assertThat(actual.getComments().get(3).getFrom().getName(), is("Comment From2"));
            assertThat(actual.getComments().get(3).canRemove(), is(true));
            assertThat(actual.getComments().get(3).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:57:21+0000")));
            assertThat(actual.getComments().getPaging().getCursors().getAfter(), is("NA=="));
            assertThat(actual.getComments().getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/link/link_index.json");
            Link actual = facebook.getLink("500000000000003", new Reading().fields("message,link,from"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "message,link,from"));

            assertThat(actual.getMessage(), is("textrans!"));
            assertThat(actual.getId(), is("500000000000003"));
            assertThat(actual.getLink().toString(), is("http://example.com/3916/"));
            assertThat(actual.getFrom().getId(), is("1234567890123456"));
            assertThat(actual.getFrom().getName(), is("From Name1"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-02-28T15:15:32+0000")));
        }
    }

    public static class getLinkComments extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/link/comments.json");
            ResponseList<Comment> actuals = facebook.getLinkComments("500000000000003");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/comments")));

            assertThat(actuals.size(), is(4));
            assertThat(actuals.get(0).isUserLikes(), is(true));
            assertThat(actuals.get(0).getMessage(), is("Like!"));
            assertThat(actuals.get(0).getId(), is("500000000000001_5000004"));
            assertThat(actuals.get(0).getLikeCount(), is(1));
            assertThat(actuals.get(0).getFrom().getId(), is("22222222222222"));
            assertThat(actuals.get(0).getFrom().getName(), is("Comment From1"));
            assertThat(actuals.get(0).canRemove(), is(true));
            assertThat(actuals.get(0).getCreatedTime(), is(iso8601DateOf("2013-02-28T17:28:55+0000")));
            assertThat(actuals.get(1).isUserLikes(), is(true));
            assertThat(actuals.get(1).getMessage(), is("Wow! Cool web service!"));
            assertThat(actuals.get(1).getId(), is("500000000000001_5000005"));
            assertThat(actuals.get(1).getLikeCount(), is(1));
            assertThat(actuals.get(1).getFrom().getId(), is("3333333333333333"));
            assertThat(actuals.get(1).getFrom().getName(), is("Comment From2"));
            assertThat(actuals.get(1).canRemove(), is(true));
            assertThat(actuals.get(1).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:24:49+0000")));
            assertThat(actuals.get(2).isUserLikes(), is(false));
            assertThat(actuals.get(2).getMessage(), is("Give me apple-touchicon..."));
            assertThat(actuals.get(2).getId(), is("500000000000001_5000006"));
            assertThat(actuals.get(2).getLikeCount(), is(0));
            assertThat(actuals.get(2).getFrom().getId(), is("1234567890123456"));
            assertThat(actuals.get(2).getFrom().getName(), is("From Name1"));
            assertThat(actuals.get(2).canRemove(), is(true));
            assertThat(actuals.get(2).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:29:35+0000")));
            assertThat(actuals.get(3).isUserLikes(), is(false));
            assertThat(actuals.get(3).getMessage(), is("So expensive..."));
            assertThat(actuals.get(3).getId(), is("500000000000001_5000007"));
            assertThat(actuals.get(3).getLikeCount(), is(0));
            assertThat(actuals.get(3).getFrom().getId(), is("3333333333333333"));
            assertThat(actuals.get(3).getFrom().getName(), is("Comment From2"));
            assertThat(actuals.get(3).canRemove(), is(true));
            assertThat(actuals.get(3).getCreatedTime(), is(iso8601DateOf("2013-03-01T04:57:21+0000")));
            assertThat(actuals.getPaging().getCursors().getAfter(), is("NA=="));
            assertThat(actuals.getPaging().getCursors().getBefore(), is("MQ=="));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/link/comment.json");
            ResponseList<Comment> actuals = facebook.getLinkComments("500000000000003", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/comments")));

            assertThat(actuals.size(), is(1));
            assertThat(actuals.get(0).isUserLikes(), is(true));
            assertThat(actuals.get(0).getMessage(), is("Like!"));
            assertThat(actuals.get(0).getId(), is("500000000000001_5000004"));
            assertThat(actuals.get(0).getLikeCount(), is(1));
            assertThat(actuals.get(0).getFrom().getId(), is("22222222222222"));
            assertThat(actuals.get(0).getFrom().getName(), is("Comment From1"));
            assertThat(actuals.get(0).canRemove(), is(true));
            assertThat(actuals.get(0).getCreatedTime(), is(iso8601DateOf("2013-02-28T17:28:55+0000")));
        }
    }

    public static class commentLink extends MockFacebookTestBase {
        @Test
        public void comment() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.commentLink("500000000000003", "test comment");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/comments")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void byCommentUpdate() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.commentLink("500000000000003", new CommentUpdate().message("test"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/comments")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void withAttachmentId() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentId("1122334455667788");
            String actual = facebook.commentLink("500000000000003", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/comments")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void withAttachmentUrl() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .attachmentUrl("https://fortunedotcom.files.wordpress.com/2015/04/467495334.jpg?quality=80&w=840&h=485&crop=1");
            String actual = facebook.commentLink("500000000000003", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/comments")));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void withSource() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            CommentUpdate source = new CommentUpdate()
                    .message("test")
                    .source(new Media(new File("src/test/resources/test_image.png")));
            String actual = facebook.commentLink("500000000000003", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/comments")));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class getLinkLikes extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/link/likes.json");
            ResponseList<Like> actuals = facebook.getLinkLikes("500000000000003");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/likes")));

            assertThat(actuals.size(), is(5));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Like Name1"));
            Like actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("100000000000005"));
            assertThat(actual5.getName(), is("Like Name5"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/link/like.json");
            ResponseList<Like> actuals = facebook.getLinkLikes("500000000000003", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/likes")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Like actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Like Name1"));
        }
    }

    public static class likeLink extends MockFacebookTestBase {
        @Test
        public void like() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.likeLink("500000000000003");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/likes")));

            assertThat(actual, is(true));
        }
    }

    public static class unlikeLink extends MockFacebookTestBase {
        @Test
        public void unlike() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.unlikeLink("500000000000003");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000003/likes")));

            assertThat(actual, is(true));
        }
    }

}
