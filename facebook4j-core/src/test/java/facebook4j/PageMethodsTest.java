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

import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.http.RequestMethod;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.junit.FacebookAPIVersion;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;

import java.io.File;
import java.net.URL;
import java.util.*;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class PageMethodsTest {

    public static class getPage extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/f4j.json");
            Page page = facebook.getPage();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me")));

            assertThat(page.getId(), is("137246726435626"));
            assertThat(page.getName(), is("F4J"));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/page/f4j_name.json");
            Page page = facebook.getPage(new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(page.getId(), is("137246726435626"));
            assertThat(page.getName(), is("F4J"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/f4j.json");
            Page page = facebook.getPage("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626")));

            assertThat(page.getId(), is("137246726435626"));
            assertThat(page.getName(), is("F4J"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/f4j_name.json");
            Page page = facebook.getPage("137246726435626", new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(page.getId(), is("137246726435626"));
            assertThat(page.getName(), is("F4J"));
        }

        @Test
        public void about_and_username() throws Exception {
            facebook.setMockJSON("mock_json/page/platform.json");
            Page actual = facebook.getPage("platform");

            assertThat(actual.getAbout(), is("Build, grow, and monetize your app with Facebook.\nhttps://developers.facebook.com/"));
            assertThat(actual.getUsername(), is("FacebookDevelopers"));
        }

        @Test
        @FacebookAPIVersion("v2.6")
        public void company_overview_and_fan_count() throws Exception {
            facebook.setMockJSON("mock_json/page/company_overview_and_fan_count.json");
            Page actual = facebook.getPage("Panasonic", new Reading().fields("company_overview", "fan_count"));

            assertThat(actual.getCompanyOverview(), is("International Panasonic Facebook Pages:\n\nAsia - www.Facebook.com/PanasonicAsia"));
            assertThat(actual.getFanCount(), is(333883));
        }

        @Test
        @FacebookAPIVersion("v2.1")
        public void pr101() throws Exception {
            facebook.setMockJSON("mock_json/page/eclipse.json");
            Page actual = facebook.getPage("eclipse", new Reading().fields("cover", "picture"));

            assertThat(actual.getCover().getId(), is("383818738305213"));
            assertThat(actual.getPicture().toString(), is("https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/547126_383818641638556_2000440510_n.jpg?oh=b14a3683ba8331770c24f7bfadae00f2&oe=58A20A25"));
        }

        @Test
        public void mission() throws Exception {
            facebook.setMockJSON("mock_json/page/mission.json");
            Page actual = facebook.getPage("softbank", new Reading().fields("company_overview", "mission"));

            assertThat(actual.getCompanyOverview(), is("ソフトバンクグループは、インターネットを事業基盤として成長を続けてきました。現在では、「移動体通信事業」「ブロードバンド・インフラ事業」「固定通信事業」「インターネット・カルチャー事業」など、情報産業の中でさまざまな事業を展開しています。"));
            assertThat(actual.getMission(), is("情報革命で人々を幸せに\n\nソフトバンクグループは、創業以来一貫して、情報革命を通じた人類と社会への貢献を推進してきました。\n\n人々にとって幸せとは何か。\n\n「愛し愛されること」「日々生きていること」「自己実現」「笑顔」、多くの答えがあると思いますが、幸せとは、感動することと同義であると考えます。ソフトバンクグループが、何のために事業をしているのか、何を成したいのかといえば、一人でも多くの人に喜び、感動を伝えたい、ということに尽きます。我々の創業以来の志が、この理念に凝縮されているのです。\n\nコンピュータのパフォーマンスが飛躍的に増大し、超知性のコンピュータすら使いこなせる、今後人類が迎えるそうした情報のビッグバン「情報革命」の無限のパワーを、人々の幸福のために正しく発展させていくこと。今後もこの志を原動力に、ソフトバンクグループは成長を続けてまいります。"));
        }

        @Test
        public void hours() throws Exception {
            facebook.setMockJSON("mock_json/page/hours.json");
            Page actual = facebook.getPage("gion.endo", new Reading().fields("hours"));

            assertThat(actual.getId(), is("214470098607468"));
            assertThat(actual.getHours().size(), is(28));
            assertThat(actual.getHours().get("sat_2_close"), is("22:00"));
        }
    }

    public static class getPagePictureURL extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/picture.json");
            URL actual = facebook.getPagePictureURL();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/picture")));

            assertThat(actual.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/211200_137246726435626_559668710_q.jpg"));
        }

        @Test
        public void size() throws Exception {
            facebook.setMockJSON("mock_json/page/picture.json");
            URL actual = facebook.getPagePictureURL(PictureSize.large);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/picture")));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "large"));

            assertThat(actual.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/211200_137246726435626_559668710_q.jpg"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/picture.json");
            URL actual = facebook.getPagePictureURL("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/picture")));

            assertThat(actual.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/211200_137246726435626_559668710_q.jpg"));
        }

        @Test
        public void id_size() throws Exception {
            facebook.setMockJSON("mock_json/page/picture.json");
            URL actual = facebook.getPagePictureURL("137246726435626", PictureSize.large);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/picture")));
            assertThat(facebook.getEndpointURL(), hasParameter("type", "large"));

            assertThat(actual.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-prn2/211200_137246726435626_559668710_q.jpg"));
        }
    }

    public static class getPromotablePosts extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/promotable_posts.json");
            ResponseList<Post> actuals = facebook.getPromotablePosts();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/promotable_posts")));

            assertThat(actuals.size(), is(8));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/DggDhA4z4tO.gif"));
            assertThat(actual1.getApplication().getId(), is("145634995501895"));
            assertThat(actual1.getApplication().getName(), is("Graph API Explorer"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/photo.php?v=188027738024191"));
            assertThat(actual1.getObjectId(), is("188027738024191"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Public"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.EVERYONE));
            assertThat(actual1.getFrom().getId(), is("137246726435626"));
            assertThat(actual1.getFrom().getCategory(), is("Software"));
            assertThat(actual1.getFrom().getName(), is("F4J"));
            assertThat(actual1.getProperties().size(), is(1));
            assertThat(actual1.getProperties().get(0).getText(), is("0:24"));
            assertThat(actual1.getProperties().get(0).getName(), is("Length"));
            assertThat(actual1.getType(), is("video"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-06-19T16:04:25+0000")));
            assertThat(actual1.getId(), is("137246726435626_188027738024191"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash4/245372_188027814690850_188027738024191_43297_2415_b.jpg"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-ash2/v/1033172_188027771357521_581032840_n.mp4?oh=882dbc6a693971c04fcdc839ad76c81a&oe=51D66588&__gda__=1373048358_4b16384645d1ee556ea065ce9fab293b"));
            assertThat(actual1.getStatusType(), is("added_video"));
            assertThat(actual1.getName(), is("Jun 20, 2013 1:04am"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-06-19T16:04:25+0000")));
            assertThat(actual1.isPublished(), is(true));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getMessage(), is("page's status update"));
            assertThat(actual2.getId(), is("137246726435626_185932178233747"));
            assertThat(actual2.getApplication().getId(), is("145634995501895"));
            assertThat(actual2.getApplication().getName(), is("Graph API Explorer"));
            assertThat(actual2.getStatusType(), is("mobile_status_update"));
            assertThat(actual2.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual2.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual2.getPrivacy().getAllow().size(), is(0));
            assertThat(actual2.getPrivacy().getDescription().get(0), is("Public"));
            assertThat(actual2.getPrivacy().getDeny().size(), is(0));
            assertThat(actual2.getPrivacy().getValue(), is(PrivacyType.EVERYONE));
            assertThat(actual2.getFrom().getId(), is("137246726435626"));
            assertThat(actual2.getFrom().getCategory(), is("Software"));
            assertThat(actual2.getFrom().getName(), is("F4J"));
            assertThat(actual2.getType(), is("status"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-06-13T16:17:44+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-06-13T16:17:44+0000")));
            assertThat(actual2.isPublished(), is(true));
            Post actual7 = actuals.get(6);
            assertThat(actual7.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/aS8ecmYRys0.gif"));
            assertThat(actual7.getApplication().getId(), is("145634995501895"));
            assertThat(actual7.getApplication().getName(), is("Graph API Explorer"));
            assertThat(actual7.getTargeting().getCountries().get(0), is("US"));
            assertThat(actual7.getTargeting().getCountries().get(1), is("GB"));
            assertThat(actual7.getLink().toString(), is("http://facebook4j.org/"));
            assertThat(actual7.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual7.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual7.getPrivacy().getAllow().size(), is(0));
            assertThat(actual7.getPrivacy().getDescription().get(0), is("United States"));
            assertThat(actual7.getPrivacy().getDescription().get(1), is("United Kingdom"));
            assertThat(actual7.getPrivacy().getDeny().size(), is(0));
            assertThat(actual7.getPrivacy().getValue(), is(PrivacyType.CUSTOM));
            assertThat(actual7.getCaption(), is("facebook4j.org"));
            assertThat(actual7.getFrom().getId(), is("137246726435626"));
            assertThat(actual7.getFrom().getCategory(), is("Software"));
            assertThat(actual7.getFrom().getName(), is("F4J"));
            assertThat(actual7.getType(), is("link"));
            assertThat(actual7.getUpdatedTime(), is(iso8601DateOf("2013-03-14T06:45:49+0000")));
            assertThat(actual7.getId(), is("137246726435626_492351237497482"));
            assertThat(actual7.getMessage(), is("Testing. Succeeded?"));
            assertThat(actual7.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQBcXOUoJ_WBlQ2G&w=154&h=154&url=http%3A%2F%2Ffacebook4j.org%2Fimages%2Fhero.png"));
            assertThat(actual7.getStatusType(), is("shared_story"));
            assertThat(actual7.getDescription(), is("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library."));
            assertThat(actual7.getName(), is("Facebook4J - A Java library for the Facebook Graph API"));
            assertThat(actual7.getCreatedTime(), is(iso8601DateOf("2013-03-14T06:45:49+0000")));
            assertThat(actual7.isPublished(), is(true));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/page/promotable_posts_fields_limit3.json");
            ResponseList<Post> actuals = facebook.getPromotablePosts(new Reading().fields("name,link").limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/promotable_posts")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,link"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));

            Post actual1 = actuals.get(0);
            assertThat(actual1.getIcon(), is(nullValue()));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/photo.php?v=188027738024191"));
            assertThat(actual1.getObjectId(), is(nullValue()));
            assertThat(actual1.getPrivacy(), is(nullValue()));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getProperties().size(), is(0));
            assertThat(actual1.getType(), is(nullValue()));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
            assertThat(actual1.getId(), is("137246726435626_188027738024191"));
            assertThat(actual1.getPicture(), is(nullValue()));
            assertThat(actual1.getSource(), is(nullValue()));
            assertThat(actual1.getStatusType(), is(nullValue()));
            assertThat(actual1.getName(), is("Jun 20, 2013 1:04am"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-06-19T16:04:25+0000")));
            assertThat(actual1.isPublished(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/promotable_posts.json");
            ResponseList<Post> actuals = facebook.getPromotablePosts("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/promotable_posts")));

            assertThat(actuals.size(), is(8));
            Post actual1 = actuals.get(0);
            assertThat(actual1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/DggDhA4z4tO.gif"));
            assertThat(actual1.getApplication().getId(), is("145634995501895"));
            assertThat(actual1.getApplication().getName(), is("Graph API Explorer"));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/photo.php?v=188027738024191"));
            assertThat(actual1.getObjectId(), is("188027738024191"));
            assertThat(actual1.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual1.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual1.getPrivacy().getAllow().size(), is(0));
            assertThat(actual1.getPrivacy().getDescription().get(0), is("Public"));
            assertThat(actual1.getPrivacy().getDeny().size(), is(0));
            assertThat(actual1.getPrivacy().getValue(), is(PrivacyType.EVERYONE));
            assertThat(actual1.getFrom().getId(), is("137246726435626"));
            assertThat(actual1.getFrom().getCategory(), is("Software"));
            assertThat(actual1.getFrom().getName(), is("F4J"));
            assertThat(actual1.getProperties().size(), is(1));
            assertThat(actual1.getProperties().get(0).getText(), is("0:24"));
            assertThat(actual1.getProperties().get(0).getName(), is("Length"));
            assertThat(actual1.getType(), is("video"));
            assertThat(actual1.getUpdatedTime(), is(iso8601DateOf("2013-06-19T16:04:25+0000")));
            assertThat(actual1.getId(), is("137246726435626_188027738024191"));
            assertThat(actual1.getPicture().toString(), is("https://fbcdn-vthumb-a.akamaihd.net/hvthumb-ak-ash4/245372_188027814690850_188027738024191_43297_2415_b.jpg"));
            assertThat(actual1.getSource().toString(), is("https://fbcdn-video-a.akamaihd.net/hvideo-ak-ash2/v/1033172_188027771357521_581032840_n.mp4?oh=882dbc6a693971c04fcdc839ad76c81a&oe=51D66588&__gda__=1373048358_4b16384645d1ee556ea065ce9fab293b"));
            assertThat(actual1.getStatusType(), is("added_video"));
            assertThat(actual1.getName(), is("Jun 20, 2013 1:04am"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-06-19T16:04:25+0000")));
            assertThat(actual1.isPublished(), is(true));
            Post actual2 = actuals.get(1);
            assertThat(actual2.getMessage(), is("page's status update"));
            assertThat(actual2.getId(), is("137246726435626_185932178233747"));
            assertThat(actual2.getApplication().getId(), is("145634995501895"));
            assertThat(actual2.getApplication().getName(), is("Graph API Explorer"));
            assertThat(actual2.getStatusType(), is("mobile_status_update"));
            assertThat(actual2.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual2.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual2.getPrivacy().getAllow().size(), is(0));
            assertThat(actual2.getPrivacy().getDescription().get(0), is("Public"));
            assertThat(actual2.getPrivacy().getDeny().size(), is(0));
            assertThat(actual2.getPrivacy().getValue(), is(PrivacyType.EVERYONE));
            assertThat(actual2.getFrom().getId(), is("137246726435626"));
            assertThat(actual2.getFrom().getCategory(), is("Software"));
            assertThat(actual2.getFrom().getName(), is("F4J"));
            assertThat(actual2.getType(), is("status"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-06-13T16:17:44+0000")));
            assertThat(actual2.getUpdatedTime(), is(iso8601DateOf("2013-06-13T16:17:44+0000")));
            assertThat(actual2.isPublished(), is(true));
            Post actual7 = actuals.get(6);
            assertThat(actual7.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/aS8ecmYRys0.gif"));
            assertThat(actual7.getApplication().getId(), is("145634995501895"));
            assertThat(actual7.getApplication().getName(), is("Graph API Explorer"));
            assertThat(actual7.getTargeting().getCountries().get(0), is("US"));
            assertThat(actual7.getTargeting().getCountries().get(1), is("GB"));
            assertThat(actual7.getLink().toString(), is("http://facebook4j.org/"));
            assertThat(actual7.getPrivacy().getNetworks().size(), is(0));
            assertThat(actual7.getPrivacy().getFriends(), is(PrivacyType.EMPTY));
            assertThat(actual7.getPrivacy().getAllow().size(), is(0));
            assertThat(actual7.getPrivacy().getDescription().get(0), is("United States"));
            assertThat(actual7.getPrivacy().getDescription().get(1), is("United Kingdom"));
            assertThat(actual7.getPrivacy().getDeny().size(), is(0));
            assertThat(actual7.getPrivacy().getValue(), is(PrivacyType.CUSTOM));
            assertThat(actual7.getCaption(), is("facebook4j.org"));
            assertThat(actual7.getFrom().getId(), is("137246726435626"));
            assertThat(actual7.getFrom().getCategory(), is("Software"));
            assertThat(actual7.getFrom().getName(), is("F4J"));
            assertThat(actual7.getType(), is("link"));
            assertThat(actual7.getUpdatedTime(), is(iso8601DateOf("2013-03-14T06:45:49+0000")));
            assertThat(actual7.getId(), is("137246726435626_492351237497482"));
            assertThat(actual7.getMessage(), is("Testing. Succeeded?"));
            assertThat(actual7.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQBcXOUoJ_WBlQ2G&w=154&h=154&url=http%3A%2F%2Ffacebook4j.org%2Fimages%2Fhero.png"));
            assertThat(actual7.getStatusType(), is("shared_story"));
            assertThat(actual7.getDescription(), is("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library."));
            assertThat(actual7.getName(), is("Facebook4J - A Java library for the Facebook Graph API"));
            assertThat(actual7.getCreatedTime(), is(iso8601DateOf("2013-03-14T06:45:49+0000")));
            assertThat(actual7.isPublished(), is(true));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/promotable_posts_fields_limit3.json");
            ResponseList<Post> actuals = facebook.getPromotablePosts("137246726435626", new Reading().fields("name,link").limit(3));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/promotable_posts")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,link"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));

            assertThat(actuals.size(), is(3));

            Post actual1 = actuals.get(0);
            assertThat(actual1.getIcon(), is(nullValue()));
            assertThat(actual1.getApplication(), is(nullValue()));
            assertThat(actual1.getLink().toString(), is("http://www.facebook.com/photo.php?v=188027738024191"));
            assertThat(actual1.getObjectId(), is(nullValue()));
            assertThat(actual1.getPrivacy(), is(nullValue()));
            assertThat(actual1.getFrom(), is(nullValue()));
            assertThat(actual1.getProperties().size(), is(0));
            assertThat(actual1.getType(), is(nullValue()));
            assertThat(actual1.getUpdatedTime(), is(nullValue()));
            assertThat(actual1.getId(), is("137246726435626_188027738024191"));
            assertThat(actual1.getPicture(), is(nullValue()));
            assertThat(actual1.getSource(), is(nullValue()));
            assertThat(actual1.getStatusType(), is(nullValue()));
            assertThat(actual1.getName(), is("Jun 20, 2013 1:04am"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-06-19T16:04:25+0000")));
            assertThat(actual1.isPublished(), is(nullValue()));
        }
    }

    public static class updatePageBasicAttributes extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            PageUpdate pageUpdate = new PageUpdate()
                                    .about("Facebook4J: A Java library for the Facebook Graph API.")
                                    .description("Facebook4J: A Java library for the Facebook Graph API.\n" +
                                            "This library provides the ease of use like Twitter4J.\n" +
                                            "Facebook4J is an unofficial library.")
                                    .generalInfo("Facebook4J is an unofficial Java library for the Facebook Graph API which is released under the Apache License 2.0.")
                                    .website("http://facebook4j.org")
                                    .phone("");
            boolean actual = facebook.updatePageBasicAttributes(pageUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("about", "Facebook4J: A Java library for the Facebook Graph API."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "Facebook4J: A Java library for the Facebook Graph API.\n" +
                                                                        "This library provides the ease of use like Twitter4J.\n" +
                                                                        "Facebook4J is an unofficial library."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("general_info", "Facebook4J is an unofficial Java library for the Facebook Graph API which is released under the Apache License 2.0."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("website", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("phone", ""));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            PageUpdate pageUpdate = new PageUpdate()
                                    .about("Facebook4J: A Java library for the Facebook Graph API.")
                                    .description("Facebook4J: A Java library for the Facebook Graph API.\n" +
                                            "This library provides the ease of use like Twitter4J.\n" +
                                            "Facebook4J is an unofficial library.")
                                    .generalInfo("Facebook4J is an unofficial Java library for the Facebook Graph API which is released under the Apache License 2.0.")
                                    .website("http://facebook4j.org")
                                    .phone("");
            boolean actual = facebook.updatePageBasicAttributes("137246726435626", pageUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("about", "Facebook4J: A Java library for the Facebook Graph API."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "Facebook4J: A Java library for the Facebook Graph API.\n" +
                                                                        "This library provides the ease of use like Twitter4J.\n" +
                                                                        "Facebook4J is an unofficial library."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("general_info", "Facebook4J is an unofficial Java library for the Facebook Graph API which is released under the Apache License 2.0."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("website", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("phone", ""));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            PageUpdate pageUpdate = new PageUpdate()
                                    .about("Facebook4J: A Java library for the Facebook Graph API.")
                                    .description("Facebook4J: A Java library for the Facebook Graph API.\n" +
                                            "This library provides the ease of use like Twitter4J.\n" +
                                            "Facebook4J is an unofficial library.")
                                    .generalInfo("Facebook4J is an unofficial Java library for the Facebook Graph API which is released under the Apache License 2.0.")
                                    .website("http://facebook4j.org")
                                    .phone("");
            boolean actual = facebook.updatePageBasicAttributes("137246726435626", pageUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("about", "Facebook4J: A Java library for the Facebook Graph API."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "Facebook4J: A Java library for the Facebook Graph API.\n" +
                                                                        "This library provides the ease of use like Twitter4J.\n" +
                                                                        "Facebook4J is an unofficial library."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("general_info", "Facebook4J is an unofficial Java library for the Facebook Graph API which is released under the Apache License 2.0."));
            assertThat(facebook.getHttpParameters(), hasPostParameter("website", "http://facebook4j.org"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("phone", ""));

            assertThat(actual, is(true));
        }
    }

    public static class updatePageProfilePhoto extends MockFacebookTestBase {
        @Test
        public void me_url() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.updatePageProfilePhoto(new URL("http://facebook4j.org/images/ogp.png"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/picture")));

            assertThat(actual, is(true));
        }

        @Test
        public void id_url() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.updatePageProfilePhoto("137246726435626", new URL("http://facebook4j.org/images/ogp.png"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/picture")));

            assertThat(actual, is(true));
        }

        @Test
        public void me_media() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            File file = new File("src/test/resources/500x500.png");
            Media source = new Media(file);
            boolean actual = facebook.updatePageProfilePhoto(source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/picture")));

            assertThat(actual, is(true));
        }

        @Test
        public void id_media() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            File file = new File("src/test/resources/500x500.png");
            Media source = new Media(file);
            boolean actual = facebook.updatePageProfilePhoto("137246726435626", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/picture")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_media_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            File file = new File("src/test/resources/500x500.png");
            Media source = new Media(file);
            boolean actual = facebook.updatePageProfilePhoto("137246726435626", source);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626/picture")));

            assertThat(actual, is(true));
        }
    }

    public static class getPageSettings extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/settings.json");
            ResponseList<PageSetting> actuals = facebook.getPageSettings();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/settings")));

            assertThat(actuals.size(), is(8));

            PageSetting actual1 = actuals.get(0);
            assertThat(actual1.getSetting(), is("USERS_CAN_POST"));
            assertThat(actual1.getValue(), is(true));
            PageSetting actual2 = actuals.get(1);
            assertThat(actual2.getSetting(), is("USERS_CAN_MESSAGE"));
            assertThat(actual2.getValue(), is(true));
            PageSetting actual3 = actuals.get(2);
            assertThat(actual3.getSetting(), is("PAGE_MODERATION_BLACKLIST"));
            assertThat(actual3.getValue(), is(nullValue()));
            PageSetting actual4 = actuals.get(3);
            assertThat(actual4.getSetting(), is("USERS_CAN_POST_PHOTOS"));
            assertThat(actual4.getValue(), is(true));
            PageSetting actual5 = actuals.get(4);
            assertThat(actual5.getSetting(), is("USERS_CAN_TAG_PHOTOS"));
            assertThat(actual5.getValue(), is(false));
            PageSetting actual6 = actuals.get(5);
            assertThat(actual6.getSetting(), is("WALL_COMBINED_POSTS"));
            assertThat(actual6.getValue(), is(false));
            PageSetting actual7 = actuals.get(6);
            assertThat(actual7.getSetting(), is("PLATFORM_OPTOUTS_CAN_POST"));
            assertThat(actual7.getValue(), is(true));
            PageSetting actual8 = actuals.get(7);
            assertThat(actual8.getSetting(), is("SHOW_RECENT_POSTS_BY_OTHERS"));
            assertThat(actual8.getValue(), is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/settings.json");
            ResponseList<PageSetting> actuals = facebook.getPageSettings("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/settings")));

            assertThat(actuals.size(), is(8));

            PageSetting actual1 = actuals.get(0);
            assertThat(actual1.getSetting(), is("USERS_CAN_POST"));
            assertThat(actual1.getValue(), is(true));
            PageSetting actual2 = actuals.get(1);
            assertThat(actual2.getSetting(), is("USERS_CAN_MESSAGE"));
            assertThat(actual2.getValue(), is(true));
            PageSetting actual3 = actuals.get(2);
            assertThat(actual3.getSetting(), is("PAGE_MODERATION_BLACKLIST"));
            assertThat(actual3.getValue(), is(nullValue()));
            PageSetting actual4 = actuals.get(3);
            assertThat(actual4.getSetting(), is("USERS_CAN_POST_PHOTOS"));
            assertThat(actual4.getValue(), is(true));
            PageSetting actual5 = actuals.get(4);
            assertThat(actual5.getSetting(), is("USERS_CAN_TAG_PHOTOS"));
            assertThat(actual5.getValue(), is(false));
            PageSetting actual6 = actuals.get(5);
            assertThat(actual6.getSetting(), is("WALL_COMBINED_POSTS"));
            assertThat(actual6.getValue(), is(false));
            PageSetting actual7 = actuals.get(6);
            assertThat(actual7.getSetting(), is("PLATFORM_OPTOUTS_CAN_POST"));
            assertThat(actual7.getValue(), is(true));
            PageSetting actual8 = actuals.get(7);
            assertThat(actual8.getSetting(), is("SHOW_RECENT_POSTS_BY_OTHERS"));
            assertThat(actual8.getValue(), is(true));
        }
    }

    public static class getGlobalBrandChildren extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/global_brand_children.json");
            ResponseList<Page> globalBrandChildren = facebook.getGlobalBrandChildren("74100576336");

            assertThat(globalBrandChildren.size(), is(17));

            Page child1 = globalBrandChildren.get(0);
            assertThat(child1.getCategory(), is("Consulting/business services"));
            assertThat(child1.getId(), is("128848373859074"));
            assertThat(child1.getName(), is("Facebook Marketing"));

            Page child11 = globalBrandChildren.get(10);
            assertThat(child11.getCategory(), is("Product/service"));
            assertThat(child11.getId(), is("242158515871607"));
            assertThat(child11.getName(), is("Facebookマーケティング"));

            Page child17 = globalBrandChildren.get(16);
            assertThat(child17.getCategory(), is("Consulting/business services"));
            assertThat(child17.getId(), is("504114196284326"));
            assertThat(child17.getName(), is("Facebook Marketing"));

            Paging<Page> paging = globalBrandChildren.getPaging();
            assertThat(paging.getNext().toString(), is("https://graph.facebook.com/74100576336/global_brand_children?access_token=access_token&limit=25&offset=25&__after_id=504114196284326"));
            assertThat(paging.getPrevious(), is(nullValue()));
        }

        @Test
        public void reading() throws Exception {
            facebook.setMockJSON("mock_json/page/global_brand_children_reading.json");
            ResponseList<Page> globalBrandChildren = facebook.getGlobalBrandChildren("74100576336",
                    new Reading().fields("website").limit(4));
            URL endpointURL = facebook.getEndpointURL();
            assertThat(endpointURL, is(pathOf("/74100576336/global_brand_children")));
            assertThat(endpointURL, hasParameter("fields", "website"));
            assertThat(endpointURL, hasParameter("limit", "4"));

            assertThat(globalBrandChildren.size(), is(4));
            Page child1 = globalBrandChildren.get(0);
            assertThat(child1.getId(), is("128848373859074"));
            assertThat(child1.getWebsite(), is("http://www.facebook.com/advertising http://www.facebook.com/ads/manage http://www.facebook.com/adsmarketing http://www.facebook.com/adshelp"));

            Page child4 = globalBrandChildren.get(3);
            assertThat(child4.getId(), is("175095239211182"));
            assertThat(child4.getWebsite(), is("www.facebook.com/FacebookMarketingkonzepte"));

            Paging<Page> paging = globalBrandChildren.getPaging();
            assertThat(paging.getNext().toString(), is("https://graph.facebook.com/74100576336/global_brand_children?fields=website,products&limit=5&access_token=access_token&offset=5&__after_id=175095239211182"));
            assertThat(paging.getPrevious(), is(nullValue()));
        }
    }

    public static class getPageInsights extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/insights.json");
            ResponseList<Insight> insights = facebook.getPageInsights("74100576336");

            assertThat(insights.size(), is(4));

            Insight insight1 = insights.get(0);
            assertThat(insight1.getId(), is("74100576336/insights/page_fans_country/lifetime"));
            assertThat(insight1.getName(), is("page_fans_country"));
            assertThat(insight1.getPeriod(), is("lifetime"));
            assertThat(insight1.getValues().size(), is(3));
            assertThat(insight1.getValues().get(0).getValue().size(), is(45));
//        Iterator<String> keys = insight1.getValues().get(0).getValue().keys();
//        while (keys.hasNext()) {
//            String key = keys.next();
//            System.out.println(key + " => " + insight1.getValues().get(0).getValue().get(key));
//        }
            assertThat(insight1.getValues().get(0).getValue().get("US"), is(650484L));
            assertThat(insight1.getValues().get(0).getValue().get("JP"), is(24157L));
            assertThat(insight1.getValues().get(0).getValue().get("BO"), is(8635L));
            assertThat(insight1.getValues().get(1).getValue().size(), is(45));
            assertThat(insight1.getValues().get(1).getValue().get("US").toString(), is("650594"));
            assertThat(insight1.getValues().get(2).getValue().size(), is(45));
            assertThat(insight1.getValues().get(2).getValue().get("BO").toString(), is("8644"));
            assertThat(insight1.getTitle(), is("Lifetime Likes by Country"));
            assertThat(insight1.getDescription(), is("Lifetime Aggregated Facebook location data, sorted by country, about the people who like your Page. (Unique Users)"));

            Insight insight2 = insights.get(1);
            assertThat(insight2.getId(), is("74100576336/insights/page_storytellers_by_country/day"));
            assertThat(insight2.getName(), is("page_storytellers_by_country"));
            assertThat(insight2.getPeriod(), is("day"));
            assertThat(insight2.getValues().size(), is(3));
            assertThat(insight2.getValues().get(0).getValue().size(), is(45));
            assertThat(insight2.getValues().get(0).getValue().size(), is(45));
            assertThat(insight2.getValues().get(0).getValue().get("TR").toString(), is("595"));
            assertThat(insight2.getValues().get(0).getValue().get("JP").toString(), is("153"));
            assertThat(insight2.getValues().get(0).getValue().get("NG").toString(), is("12"));
            assertThat(insight2.getValues().get(1).getValue().size(), is(45));
            assertThat(insight2.getValues().get(1).getValue().get("TR").toString(), is("1002"));
            assertThat(insight2.getValues().get(2).getValue().size(), is(45));
            assertThat(insight2.getValues().get(2).getValue().get("PL").toString(), is("9"));
            assertThat(insight2.getTitle(), is("Daily Country: People Talking About This"));
            assertThat(insight2.getDescription(), is("Daily The number of People Talking About the Page by user country (Unique Users)"));

            Insight insight3 = insights.get(2);
            assertThat(insight3.getId(), is("74100576336/insights/page_storytellers_by_country/week"));
            assertThat(insight3.getName(), is("page_storytellers_by_country"));
            assertThat(insight3.getPeriod(), is("week"));
            assertThat(insight3.getValues().size(), is(3));
            assertThat(insight3.getValues().get(0).getValue().size(), is(45));
            assertThat(insight3.getValues().get(0).getValue().size(), is(45));
            assertThat(insight3.getValues().get(0).getValue().get("IN").toString(), is("3922"));
            assertThat(insight3.getValues().get(0).getValue().get("JP").toString(), is("1053"));
            assertThat(insight3.getValues().get(0).getValue().get("PL").toString(), is("66"));
            assertThat(insight3.getValues().get(1).getValue().size(), is(45));
            assertThat(insight3.getValues().get(1).getValue().get("TR").toString(), is("4512"));
            assertThat(insight3.getValues().get(2).getValue().size(), is(45));
            assertThat(insight3.getValues().get(2).getValue().get("HK").toString(), is("71"));
            assertThat(insight3.getTitle(), is("Weekly Country: People Talking About This"));
            assertThat(insight3.getDescription(), is("Weekly The number of People Talking About the Page by user country (Unique Users)"));

            Insight insight4 = insights.get(3);
            assertThat(insight4.getId(), is("74100576336/insights/page_storytellers_by_country/days_28"));
            assertThat(insight4.getName(), is("page_storytellers_by_country"));
            assertThat(insight4.getPeriod(), is("days_28"));
            assertThat(insight4.getValues().size(), is(3));
            assertThat(insight4.getValues().get(0).getValue().size(), is(45));
            assertThat(insight4.getValues().get(0).getValue().size(), is(45));
            assertThat(insight4.getValues().get(0).getValue().get("IN").toString(), is("16044"));
            assertThat(insight4.getValues().get(0).getValue().get("JP").toString(), is("5140"));
            assertThat(insight4.getValues().get(0).getValue().get("BE").toString(), is("327"));
            assertThat(insight4.getValues().get(1).getValue().size(), is(45));
            assertThat(insight4.getValues().get(1).getValue().get("IN").toString(), is("15614"));
            assertThat(insight4.getValues().get(2).getValue().size(), is(45));
            assertThat(insight4.getValues().get(2).getValue().get("BE").toString(), is("307"));
            assertThat(insight4.getTitle(), is("28 Days Country: People Talking About This"));
            assertThat(insight4.getDescription(), is("28 Days The number of People Talking About the Page by user country (Unique Users)"));

            Paging<Insight> paging = insights.getPaging();
            assertThat(paging.getPrevious().toString(), is("https://graph.facebook.com/74100576336/insights?since=1370145751&until=1370404951"));
            assertThat(paging.getNext().toString(), is("https://graph.facebook.com/74100576336/insights?since=1370664151&until=1370923351"));
        }
    }

    public static class getPageTagged extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/tagged.json");
            ResponseList<Tagged> taggeds = facebook.getPageTagged("65341080064");

            assertThat(taggeds.size(), is(25));

            Tagged tagged1 = taggeds.get(0);
            assertThat(tagged1.getId(), is("65341080064_203494769804107"));
            assertThat(tagged1.getType(), is("photo"));
            assertThat(tagged1.getPost(), is(nullValue()));
            assertThat(tagged1.getVideo(), is(nullValue()));
            Photo photo = tagged1.getPhoto();
            assertThat(photo, is(not(nullValue())));
            assertThat(photo.getCreatedTime(), is(iso8601DateOf("2013-06-10T15:06:07+0000")));
            assertThat(photo.getFrom().getId(), is("100004307554645"));
            assertThat(photo.getFrom().getName(), is("Mein Herr Schmidt"));
            assertThat(photo.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yz/r/StEh3RhPvjk.gif"));
            assertThat(photo.getLikes().getCount(), is(1));
            assertThat(photo.getLikes().size(), is(1));
            assertThat(photo.getLikes().get(0).getId(), is("1143846376"));
            assertThat(photo.getLikes().get(0).getName(), is("Lisa McGee"));
            assertThat(photo.getLink().toString(), is("http://www.facebook.com/photo.php?fbid=203494769804107&set=o.65341080064&type=1"));
            assertThat(photo.getPicture().toString(), is("https://fbcdn-sphotos-f-a.akamaihd.net/hphotos-ak-frc1/q75/s480x480/1001458_203494769804107_274974554_n.jpg"));
            assertThat(tagged1.getMessage(), is("Wow!"));
            assertThat(tagged1.getMessageTags().size(), is(0));
            assertThat(tagged1.getObjectId(), is("203494769804107"));
            assertThat(tagged1.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(tagged1.getTo().size(), is(1));
            assertThat(tagged1.getTo().get(0).getCategory(), is("Travel/leisure"));
            assertThat(tagged1.getTo().get(0).getId(), is("65341080064"));
            assertThat(tagged1.getTo().get(0).getName(), is("Grand Canyon"));
            assertThat(tagged1.getUpdatedTime(), is(iso8601DateOf("2013-06-10T15:06:07+0000")));

            Tagged tagged4 = taggeds.get(3);
            assertThat(tagged4.getId(), is("65341080064_10152888395585065"));
            assertThat(tagged4.getType(), is("link"));
            assertThat(tagged4.getPhoto(), is(nullValue()));
            assertThat(tagged4.getVideo(), is(nullValue()));
            Post post = tagged4.getPost();
            assertThat(post, is(not(nullValue())));
            assertThat(post.getCaption(), is("www.runningtowardshome.com"));
            assertThat(post.getCreatedTime(), is(iso8601DateOf("2013-06-06T14:16:16+0000")));
            assertThat(post.getFrom().getId(), is("1558890061"));
            assertThat(post.getFrom().getName(), is("Joshua Snow Hansén"));
            assertThat(post.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/aS8ecmYRys0.gif"));
            assertThat(post.getLink().toString(), is("http://www.runningtowardshome.com/2013/06/grand-canyon-r2r2r.html"));
            assertThat(post.getName(), is("Running Towards Home | ©: My Quest to Run the Grand Canyon R2R2R"));
            assertThat(post.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQCk6K-KS0vS1qlM&w=154&h=154&url=http%3A%2F%2F3.bp.blogspot.com%2F-rbFFZFuvNiQ%2FUX33eAIvYjI%2FAAAAAAAAN2g%2FmJUZSlj7B1M%2Fs240%2FBeforeAfter2.png"));
            assertThat(post.getFullPicture().toString(), is("https://fbcdn-sphotos-f-a.akamaihd.net/hphotos-ak-frc1/q75/s480x480/1001458_203494769804107_274974554_full_picture.jpg"));
            assertThat(tagged4.getMessage(), is("I've gotten the bug. My new addition to my bucket list? Run the Grand Canyon Rim to Rim to Rim. This needs to happen. 2014 anyone?"));
            assertThat(tagged4.getMessageTags().size(), is(1));
            assertThat(tagged4.getMessageTags().get("64").length, is(1));
            assertThat(tagged4.getMessageTags().get("64")[0].getId(), is("65341080064"));
            assertThat(tagged4.getMessageTags().get("64")[0].getLength(), is(12));
            assertThat(tagged4.getMessageTags().get("64")[0].getName(), is("Grand Canyon"));
            assertThat(tagged4.getMessageTags().get("64")[0].getOffset(), is(64));
            assertThat(tagged4.getMessageTags().get("64")[0].getType(), is("page"));
            assertThat(tagged4.getObjectId(), is(nullValue()));
            assertThat(tagged4.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(tagged4.getTo().size(), is(1));
            assertThat(tagged4.getTo().get(0).getCategory(), is("Travel/leisure"));
            assertThat(tagged4.getTo().get(0).getId(), is("65341080064"));
            assertThat(tagged4.getTo().get(0).getName(), is("Grand Canyon"));
            assertThat(tagged4.getUpdatedTime(), is(iso8601DateOf("2013-06-06T14:16:16+0000")));

            Tagged tagged9 = taggeds.get(8);
            assertThat(tagged9.getId(), is("65341080064_10151515892704401"));
            assertThat(tagged9.getType(), is("video"));
            assertThat(tagged9.getPhoto(), is(nullValue()));
            assertThat(tagged9.getPost(), is(nullValue()));
            Video video = tagged9.getVideo();
            assertThat(video, is(not(nullValue())));
            assertThat(video.getDescription(), is("Sunset at Grand Canyon National Park from Desert View Point."));
            assertThat(video.getCreatedTime(), is(iso8601DateOf("2013-05-30T18:30:57+0000")));
            assertThat(video.getFrom().getId(), is("691374400"));
            assertThat(video.getFrom().getName(), is("John DeLanghe"));
            assertThat(video.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yj/r/v2OnaTyTQZE.gif"));
            assertThat(video.getLink().toString(), is("https://www.youtube.com/watch?v=8P-VE8K1O6U"));
            assertThat(video.getName(), is("Welcome to: Grand Canyon National Park at sunset - Arizona, United States"));
            assertThat(video.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQDEyOas7zVTfgSY&w=130&h=130&url=http%3A%2F%2Fi1.ytimg.com%2Fvi%2F8P-VE8K1O6U%2Fmaxresdefault.jpg%3Ffeature%3Dog"));
            assertThat(video.getSource().toString(), is("http://www.youtube.com/v/8P-VE8K1O6U?version=3&autohide=1&autoplay=1"));
            assertThat(tagged9.getMessage(), is("WOW! Beautiful!"));
            assertThat(tagged9.getMessageTags().size(), is(0));
            assertThat(tagged9.getObjectId(), is(nullValue()));
            assertThat(tagged9.getPrivacy().getValue(), is(PrivacyType.EMPTY));
            assertThat(tagged9.getTo().size(), is(1));
            assertThat(tagged9.getTo().get(0).getCategory(), is("Travel/leisure"));
            assertThat(tagged9.getTo().get(0).getId(), is("65341080064"));
            assertThat(tagged9.getTo().get(0).getName(), is("Grand Canyon"));
            assertThat(tagged9.getUpdatedTime(), is(iso8601DateOf("2013-05-30T18:30:57+0000")));
        }
    }

    public static class getMilestones extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/milestones.json");
            ResponseList<Milestone> milestones = facebook.getMilestones();
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/milestones")));

            assertThat(milestones.size(), is(3));

            Milestone milestone1 = milestones.get(0);
            assertThat(milestone1.getId(), is("186353804858251"));
            assertThat(milestone1.getTitle(), is("30 stars!!!"));
            assertThat(milestone1.getDescription(), is("https://github.com/roundrop/facebook4j"));
            assertThat(milestone1.getFrom().getCategory(), is("Software"));
            assertThat(milestone1.getFrom().getId(), is("137246726435626"));
            assertThat(milestone1.getFrom().getName(), is("F4J"));
            assertThat(milestone1.getStartTime(), is(iso8601DateOf("2013-04-24T19:01:00+0000")));
            assertThat(milestone1.getEndTime(), is(iso8601DateOf("2013-04-24T19:02:00+0000")));
            assertThat(milestone1.getCreatedTime(), is(iso8601DateOf("2013-06-14T10:37:32+0000")));
            assertThat(milestone1.getUpdatedTime(), is(iso8601DateOf("2013-06-14T10:37:33+0000")));

            Milestone milestone3 = milestones.get(2);
            assertThat(milestone3.getId(), is("186353611524937"));
            assertThat(milestone3.getTitle(), is("10 stars!"));
            assertThat(milestone3.getDescription(), is("https://github.com/roundrop/facebook4j"));
            assertThat(milestone3.getFrom().getCategory(), is("Software"));
            assertThat(milestone3.getFrom().getId(), is("137246726435626"));
            assertThat(milestone3.getFrom().getName(), is("F4J"));
            assertThat(milestone3.getStartTime(), is(iso8601DateOf("2012-12-02T20:0:01+0000")));
            assertThat(milestone3.getEndTime(), is(iso8601DateOf("2012-12-02T20:0:02+0000")));
            assertThat(milestone3.getCreatedTime(), is(iso8601DateOf("2013-06-14T10:36:08+0000")));
            assertThat(milestone3.getUpdatedTime(), is(iso8601DateOf("2013-06-14T10:36:09+0000")));

            assertThat(milestones.getPaging().getNext().toString(), is("https://graph.facebook.com/137246726435626/milestones?access_token=access_token&limit=5000&offset=5000&__after_id=186353611524937"));
            assertThat(milestones.getPaging().getPrevious(), is(nullValue()));
        }

        @Test
        public void me_limit1() throws Exception {
            facebook.setMockJSON("mock_json/page/milestones_limit1.json");
            ResponseList<Milestone> milestones = facebook.getMilestones(new Reading().limit(1));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/milestones")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(milestones.size(), is(1));

            Milestone milestone1 = milestones.get(0);
            assertThat(milestone1.getId(), is("186353804858251"));
            assertThat(milestone1.getTitle(), is("30 stars!!!"));
            assertThat(milestone1.getDescription(), is("https://github.com/roundrop/facebook4j"));
            assertThat(milestone1.getFrom().getCategory(), is("Software"));
            assertThat(milestone1.getFrom().getId(), is("137246726435626"));
            assertThat(milestone1.getFrom().getName(), is("F4J"));
            assertThat(milestone1.getStartTime(), is(iso8601DateOf("2013-04-24T19:01:00+0000")));
            assertThat(milestone1.getEndTime(), is(iso8601DateOf("2013-04-24T19:02:00+0000")));
            assertThat(milestone1.getCreatedTime(), is(iso8601DateOf("2013-06-14T10:37:32+0000")));
            assertThat(milestone1.getUpdatedTime(), is(iso8601DateOf("2013-06-14T10:37:33+0000")));

            assertThat(milestones.getPaging().getNext().toString(), is("https://graph.facebook.com/137246726435626/milestones?access_token=access_token&limit=5000&offset=5000&__after_id=186353611524937"));
            assertThat(milestones.getPaging().getPrevious(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/milestones.json");
            ResponseList<Milestone> milestones = facebook.getMilestones("137246726435626");
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/milestones")));

            assertThat(milestones.size(), is(3));

            Milestone milestone1 = milestones.get(0);
            assertThat(milestone1.getId(), is("186353804858251"));
            assertThat(milestone1.getTitle(), is("30 stars!!!"));
            assertThat(milestone1.getDescription(), is("https://github.com/roundrop/facebook4j"));
            assertThat(milestone1.getFrom().getCategory(), is("Software"));
            assertThat(milestone1.getFrom().getId(), is("137246726435626"));
            assertThat(milestone1.getFrom().getName(), is("F4J"));
            assertThat(milestone1.getStartTime(), is(iso8601DateOf("2013-04-24T19:01:00+0000")));
            assertThat(milestone1.getEndTime(), is(iso8601DateOf("2013-04-24T19:02:00+0000")));
            assertThat(milestone1.getCreatedTime(), is(iso8601DateOf("2013-06-14T10:37:32+0000")));
            assertThat(milestone1.getUpdatedTime(), is(iso8601DateOf("2013-06-14T10:37:33+0000")));

            Milestone milestone3 = milestones.get(2);
            assertThat(milestone3.getId(), is("186353611524937"));
            assertThat(milestone3.getTitle(), is("10 stars!"));
            assertThat(milestone3.getDescription(), is("https://github.com/roundrop/facebook4j"));
            assertThat(milestone3.getFrom().getCategory(), is("Software"));
            assertThat(milestone3.getFrom().getId(), is("137246726435626"));
            assertThat(milestone3.getFrom().getName(), is("F4J"));
            assertThat(milestone3.getStartTime(), is(iso8601DateOf("2012-12-02T20:0:01+0000")));
            assertThat(milestone3.getEndTime(), is(iso8601DateOf("2012-12-02T20:0:02+0000")));
            assertThat(milestone3.getCreatedTime(), is(iso8601DateOf("2013-06-14T10:36:08+0000")));
            assertThat(milestone3.getUpdatedTime(), is(iso8601DateOf("2013-06-14T10:36:09+0000")));

            assertThat(milestones.getPaging().getNext().toString(), is("https://graph.facebook.com/137246726435626/milestones?access_token=access_token&limit=5000&offset=5000&__after_id=186353611524937"));
            assertThat(milestones.getPaging().getPrevious(), is(nullValue()));
        }

        @Test
        public void id_limit1() throws Exception {
            facebook.setMockJSON("mock_json/page/milestones_limit1.json");
            ResponseList<Milestone> milestones = facebook.getMilestones("137246726435626", new Reading().limit(1));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/milestones")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(milestones.size(), is(1));

            Milestone milestone1 = milestones.get(0);
            assertThat(milestone1.getId(), is("186353804858251"));
            assertThat(milestone1.getTitle(), is("30 stars!!!"));
            assertThat(milestone1.getDescription(), is("https://github.com/roundrop/facebook4j"));
            assertThat(milestone1.getFrom().getCategory(), is("Software"));
            assertThat(milestone1.getFrom().getId(), is("137246726435626"));
            assertThat(milestone1.getFrom().getName(), is("F4J"));
            assertThat(milestone1.getStartTime(), is(iso8601DateOf("2013-04-24T19:01:00+0000")));
            assertThat(milestone1.getEndTime(), is(iso8601DateOf("2013-04-24T19:02:00+0000")));
            assertThat(milestone1.getCreatedTime(), is(iso8601DateOf("2013-06-14T10:37:32+0000")));
            assertThat(milestone1.getUpdatedTime(), is(iso8601DateOf("2013-06-14T10:37:33+0000")));

            assertThat(milestones.getPaging().getNext().toString(), is("https://graph.facebook.com/137246726435626/milestones?access_token=access_token&limit=5000&offset=5000&__after_id=186353611524937"));
            assertThat(milestones.getPaging().getPrevious(), is(nullValue()));
        }
    }

    public static class createMilestone extends MockFacebookTestBase {
        @Test
        public void me() throws FacebookException {
            facebook.setMockJSON("mock_json/id.json");
            Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            startTime.set(2013, 4, 25, 0, 0, 0);
            String milestoneId = facebook.createMilestone(new MilestoneUpdate("test", "description", startTime));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/milestones")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("title", "test"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("description", "description"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("start_time", "2013-05-25T00:00:00+0000"));

            assertThat(milestoneId, is(notNullValue()));
        }
    }

    public static class deleteMilestone extends MockFacebookTestBase {
        @Test
        public void id() throws FacebookException {
            facebook.setMockJSON("mock_json/true.json");
            boolean result = facebook.deleteMilestone("187182304775401");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/187182304775401")));

            assertThat(result, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws FacebookException {
            facebook.setMockJSON("mock_json/success.json");
            boolean result = facebook.deleteMilestone("187182304775401");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/187182304775401")));

            assertThat(result, is(true));
        }
    }

    public static class getPageAdmins extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/admins.json");
            ResponseList<Admin> admins = facebook.getPageAdmins();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/admins")));

            assertThat(admins.size(), is(1));
            assertThat(admins.get(0).getId(), is("100001568838021"));
            assertThat(admins.get(0).getName(), is("Ryuji Yamashita"));
            assertThat(admins.get(0).getPerms().size(), is(6));
            assertThat(admins.get(0).getPerms().get(0), is("ADMINISTER"));
            assertThat(admins.get(0).getPerms().get(1), is("EDIT_PROFILE"));
            assertThat(admins.get(0).getPerms().get(2), is("CREATE_CONTENT"));
            assertThat(admins.get(0).getPerms().get(3), is("MODERATE_CONTENT"));
            assertThat(admins.get(0).getPerms().get(4), is("CREATE_ADS"));
            assertThat(admins.get(0).getPerms().get(5), is("BASIC_ADMIN"));
            assertThat(admins.get(0).getRole(), is("MANAGER"));

            assertThat(admins.getPaging().getNext().toString(), is("https://graph.facebook.com/137246726435626/admins?access_token=access_token&limit=5000&offset=5000&__after_id=100001568838021"));
            assertThat(admins.getPaging().getPrevious(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/admins.json");
            ResponseList<Admin> admins = facebook.getPageAdmins("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/admins")));

            assertThat(admins.size(), is(1));
            assertThat(admins.get(0).getId(), is("100001568838021"));
            assertThat(admins.get(0).getName(), is("Ryuji Yamashita"));
            assertThat(admins.get(0).getPerms().size(), is(6));
            assertThat(admins.get(0).getPerms().get(0), is("ADMINISTER"));
            assertThat(admins.get(0).getPerms().get(1), is("EDIT_PROFILE"));
            assertThat(admins.get(0).getPerms().get(2), is("CREATE_CONTENT"));
            assertThat(admins.get(0).getPerms().get(3), is("MODERATE_CONTENT"));
            assertThat(admins.get(0).getPerms().get(4), is("CREATE_ADS"));
            assertThat(admins.get(0).getPerms().get(5), is("BASIC_ADMIN"));
            assertThat(admins.get(0).getRole(), is("MANAGER"));

            assertThat(admins.getPaging().getNext().toString(), is("https://graph.facebook.com/137246726435626/admins?access_token=access_token&limit=5000&offset=5000&__after_id=100001568838021"));
            assertThat(admins.getPaging().getPrevious(), is(nullValue()));
        }
    }

    public static class getTabs extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/tabs.json");
            ResponseList<Tab> tabs = facebook.getTabs();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs")));

            assertThat(tabs.size(), is(3));

            Tab tab = tabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/photos"));
            assertThat(tab.getName(), is("Photos"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=photos"));
            assertThat(tab.getApplication().getId(), is("2305272732"));
            assertThat(tab.getApplication().getName(), is("Photos"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(1));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn1/851586_10151609549247733_1069686154_n.gif"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));

            tab = tabs.get(1);
            assertThat(tab.getId(), is("137246726435626/tabs/likes"));
            assertThat(tab.getName(), is("Likes"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=likes"));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(true));
            assertThat(tab.getPosition(), is(2));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL(), is(nullValue()));
            assertThat(tab.getCustomImageURL(), is(nullValue()));

            tab = tabs.get(2);
            assertThat(tab.getId(), is("137246726435626/tabs/events"));
            assertThat(tab.getName(), is("Events"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=events"));
            assertThat(tab.getApplication().getId(), is("2344061033"));
            assertThat(tab.getApplication().getName(), is("Events"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(3));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-f-a.akamaihd.net/hphotos-ak-prn1/851576_10151412710481034_396386689_n.png"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/tabs_fields_limit.json");
            ResponseList<Tab> tabs = facebook.getTabs(new Reading().fields("name").fields("position").limit(2));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,position"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "2"));

            assertThat(tabs.size(), is(2));

            Tab tab = tabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/photos"));
            assertThat(tab.getName(), is("Photos"));
            assertThat(tab.getLink(), is(nullValue()));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(nullValue()));
            assertThat(tab.getPosition(), is(1));
            assertThat(tab.isNonConnectionLandingTab(), is(nullValue()));
            assertThat(tab.getImageURL(), is(nullValue()));
            assertThat(tab.getCustomImageURL(), is(nullValue()));

            tab = tabs.get(1);
            assertThat(tab.getId(), is("137246726435626/tabs/likes"));
            assertThat(tab.getName(), is("Likes"));
            assertThat(tab.getLink(), is(nullValue()));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(nullValue()));
            assertThat(tab.getPosition(), is(2));
            assertThat(tab.isNonConnectionLandingTab(), is(nullValue()));
            assertThat(tab.getImageURL(), is(nullValue()));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/tabs.json");
            ResponseList<Tab> tabs = facebook.getTabs("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/tabs")));

            assertThat(tabs.size(), is(3));

            Tab tab = tabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/photos"));
            assertThat(tab.getName(), is("Photos"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=photos"));
            assertThat(tab.getApplication().getId(), is("2305272732"));
            assertThat(tab.getApplication().getName(), is("Photos"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(1));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn1/851586_10151609549247733_1069686154_n.gif"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));

            tab = tabs.get(1);
            assertThat(tab.getId(), is("137246726435626/tabs/likes"));
            assertThat(tab.getName(), is("Likes"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=likes"));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(true));
            assertThat(tab.getPosition(), is(2));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL(), is(nullValue()));
            assertThat(tab.getCustomImageURL(), is(nullValue()));

            tab = tabs.get(2);
            assertThat(tab.getId(), is("137246726435626/tabs/events"));
            assertThat(tab.getName(), is("Events"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=events"));
            assertThat(tab.getApplication().getId(), is("2344061033"));
            assertThat(tab.getApplication().getName(), is("Events"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(3));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-f-a.akamaihd.net/hphotos-ak-prn1/851576_10151412710481034_396386689_n.png"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/tabs_fields_limit.json");
            ResponseList<Tab> tabs = facebook.getTabs("137246726435626", new Reading().fields("name").fields("position").limit(2));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/tabs")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,position"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "2"));

            assertThat(tabs.size(), is(2));

            Tab tab = tabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/photos"));
            assertThat(tab.getName(), is("Photos"));
            assertThat(tab.getLink(), is(nullValue()));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(nullValue()));
            assertThat(tab.getPosition(), is(1));
            assertThat(tab.isNonConnectionLandingTab(), is(nullValue()));
            assertThat(tab.getImageURL(), is(nullValue()));
            assertThat(tab.getCustomImageURL(), is(nullValue()));

            tab = tabs.get(1);
            assertThat(tab.getId(), is("137246726435626/tabs/likes"));
            assertThat(tab.getName(), is("Likes"));
            assertThat(tab.getLink(), is(nullValue()));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(nullValue()));
            assertThat(tab.getPosition(), is(2));
            assertThat(tab.isNonConnectionLandingTab(), is(nullValue()));
            assertThat(tab.getImageURL(), is(nullValue()));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }
    }

    public static class getInstalledTabs extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/installed_tabs.json");
            List<String> appId = new ArrayList<String>();
            appId.add("2344061033");
            ResponseList<Tab> installedTabs = facebook.getInstalledTabs(appId);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs/2344061033")));

            assertThat(installedTabs.size(), is(1));

            Tab tab = installedTabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/events"));
            assertThat(tab.getName(), is("Events"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=events"));
            assertThat(tab.getApplication().getId(), is("2344061033"));
            assertThat(tab.getApplication().getName(), is("Events"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(3));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-f-a.akamaihd.net/hphotos-ak-prn1/851576_10151412710481034_396386689_n.png"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/installed_tabs_fields.json");
            List<String> appId = new ArrayList<String>();
            appId.add("2344061033");
            ResponseList<Tab> installedTabs = facebook.getInstalledTabs(appId, new Reading().fields("name,image_url,position"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs/2344061033")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,image_url,position"));

            assertThat(installedTabs.size(), is(1));

            Tab tab = installedTabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/events"));
            assertThat(tab.getName(), is("Events"));
            assertThat(tab.getLink(), is(nullValue()));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(nullValue()));
            assertThat(tab.getPosition(), is(3));
            assertThat(tab.isNonConnectionLandingTab(), is(nullValue()));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-f-a.akamaihd.net/hphotos-ak-prn1/851576_10151412710481034_396386689_n.png"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

        @Test
        public void me_appids() throws Exception {
            facebook.setMockJSON("mock_json/page/installed_tabs_2.json");
            List<String> appIds = new ArrayList<String>();
            appIds.add("2344061033");
            appIds.add("photos");
            appIds.add("zzzz");
            ResponseList<Tab> installedTabs = facebook.getInstalledTabs(appIds);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs/2344061033,photos,zzzz")));

            assertThat(installedTabs.size(), is(2));

            Tab tab = installedTabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/photos"));
            assertThat(tab.getName(), is("Photos"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=photos"));
            assertThat(tab.getApplication().getId(), is("2305272732"));
            assertThat(tab.getApplication().getName(), is("Photos"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(1));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-prn1/851586_10151609549247733_1069686154_n.gif"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));

            tab = installedTabs.get(1);
            assertThat(tab.getId(), is("137246726435626/tabs/events"));
            assertThat(tab.getName(), is("Events"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=events"));
            assertThat(tab.getApplication().getId(), is("2344061033"));
            assertThat(tab.getApplication().getName(), is("Events"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(3));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-f-a.akamaihd.net/hphotos-ak-prn1/851576_10151412710481034_396386689_n.png"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

        @Test
        public void id_appid() throws Exception {
            facebook.setMockJSON("mock_json/page/installed_tabs.json");
            List<String> appId = new ArrayList<String>();
            appId.add("2344061033");
            ResponseList<Tab> installedTabs = facebook.getInstalledTabs("137246726435626", appId);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/tabs/2344061033")));

            assertThat(installedTabs.size(), is(1));

            Tab tab = installedTabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/events"));
            assertThat(tab.getName(), is("Events"));
            assertThat(tab.getLink().toString(), is("http://www.facebook.com/pages/F4J/137246726435626?sk=events"));
            assertThat(tab.getApplication().getId(), is("2344061033"));
            assertThat(tab.getApplication().getName(), is("Events"));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(false));
            assertThat(tab.getPosition(), is(3));
            assertThat(tab.isNonConnectionLandingTab(), is(false));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-f-a.akamaihd.net/hphotos-ak-prn1/851576_10151412710481034_396386689_n.png"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/installed_tabs_fields.json");
            List<String> appId = new ArrayList<String>();
            appId.add("2344061033");
            ResponseList<Tab> installedTabs = facebook.getInstalledTabs("137246726435626", appId, new Reading().fields("name,image_url,position"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/tabs/2344061033")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,image_url,position"));

            assertThat(installedTabs.size(), is(1));

            Tab tab = installedTabs.get(0);
            assertThat(tab.getId(), is("137246726435626/tabs/events"));
            assertThat(tab.getName(), is("Events"));
            assertThat(tab.getLink(), is(nullValue()));
            assertThat(tab.getApplication(), is(nullValue()));
            assertThat(tab.getCustomName(), is(nullValue()));
            assertThat(tab.isPermanent(), is(nullValue()));
            assertThat(tab.getPosition(), is(3));
            assertThat(tab.isNonConnectionLandingTab(), is(nullValue()));
            assertThat(tab.getImageURL().toString(), is("https://fbcdn-photos-f-a.akamaihd.net/hphotos-ak-prn1/851576_10151412710481034_396386689_n.png"));
            assertThat(tab.getCustomImageURL(), is(nullValue()));
        }

    }

    public static class installTab extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.installTab("2344061033");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("app_id", "2344061033"));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.installTab("137246726435626", "2344061033");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/tabs")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("app_id", "2344061033"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.installTab("137246726435626", "2344061033");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626/tabs")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("app_id", "2344061033"));

            assertThat(actual, is(true));
        }
    }

    public static class getBlocked extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/blocked_2.json");
            ResponseList<User> blocked = facebook.getBlocked();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/blocked")));

            assertThat(blocked.size(), is(2));
            assertThat(blocked.get(0).getId(), is("1111111111"));
            assertThat(blocked.get(0).getName(), is("Foo Name"));
            assertThat(blocked.get(1).getId(), is("2222222222"));
            assertThat(blocked.get(1).getName(), is("Bar Name"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/blocked.json");
            ResponseList<User> blocked = facebook.getBlocked(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/blocked")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(blocked.size(), is(1));
            assertThat(blocked.get(0).getId(), is("1234567890"));
            assertThat(blocked.get(0).getName(), is("Foo Bar"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/blocked_2.json");
            ResponseList<User> blocked = facebook.getBlocked("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/blocked")));

            assertThat(blocked.size(), is(2));
            assertThat(blocked.get(0).getId(), is("1111111111"));
            assertThat(blocked.get(0).getName(), is("Foo Name"));
            assertThat(blocked.get(1).getId(), is("2222222222"));
            assertThat(blocked.get(1).getName(), is("Bar Name"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/blocked.json");
            ResponseList<User> blocked = facebook.getBlocked("137246726435626", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/blocked")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(blocked.size(), is(1));
            assertThat(blocked.get(0).getId(), is("1234567890"));
            assertThat(blocked.get(0).getName(), is("Foo Bar"));
        }
    }

    public static class block extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/block.json");
            List<String> userIds = new ArrayList<String>();
            String uid1 = "1111111111";
            userIds.add(uid1);
            String uid2 = "2222222222";
            userIds.add(uid2);
            Map<String,Boolean> block = facebook.block(userIds);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/blocked")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("uid", uid1 + "," + uid2));

            assertThat(block.size(), is(2));
            assertThat(block.get(uid1), is(true));
            assertThat(block.get(uid2), is(false));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/block.json");
            List<String> userIds = new ArrayList<String>();
            String uid1 = "1111111111";
            userIds.add(uid1);
            String uid2 = "2222222222";
            userIds.add(uid2);
            Map<String,Boolean> block = facebook.block("137246726435626", userIds);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/blocked")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("uid", uid1 + "," + uid2));

            assertThat(block.size(), is(2));
            assertThat(block.get(uid1), is(true));
            assertThat(block.get(uid2), is(false));
        }
    }

    public static class unblock extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            String uid = "1111111111";
            boolean unblock = facebook.unblock(uid);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/blocked")));
            assertThat(facebook.getEndpointURL(), hasParameter("uid", uid));

            assertThat(unblock, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            String uid = "1111111111";
            boolean unblock = facebook.unblock("137246726435626", uid);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/blocked")));
            assertThat(facebook.getEndpointURL(), hasParameter("uid", uid));

            assertThat(unblock, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            String uid = "1111111111";
            boolean unblock = facebook.unblock("137246726435626", uid);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626/blocked")));
            assertThat(facebook.getEndpointURL(), hasParameter("uid", uid));

            assertThat(unblock, is(true));
        }
    }

    public static class getOffers extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/offers.json");
            ResponseList<Offer> offers = facebook.getOffers();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/offers")));

            assertThat(offers.size(), is(1));

            Offer offer = offers.get(0);
            assertThat(offer.getId(), is("11111111"));
            assertThat(offer.getFrom().getId(), is("22222222"));
            assertThat(offer.getFrom().getName(), is("Page Name"));
            assertThat(offer.getFrom().getCategory(), is("Page Category"));
            assertThat(offer.getTitle(), is("The title of the Offer"));
            assertThat(offer.getCreatedTime(), is(iso8601DateOf("2013-06-27T19:00:00+0000")));
            assertThat(offer.getExpirationTime(), is(iso8601DateOf("2014-03-31T12:30:00+0000")));
            assertThat(offer.getTerms(), is("The description of the terms under which the offer can be claimed"));
            assertThat(offer.getImageURL().toString(), is("http://facebook4j.org/image.png"));
            assertThat(offer.getClaimLimit(), is(300));
            assertThat(offer.getCouponType(), is("online_only"));
            assertThat(offer.getRedemptionLink().toString(), is("http://facebook4j.org/redemption"));
            assertThat(offer.getRedemptionCode(), is("998877"));
            assertThat(offer.isPublished(), is(true));
            assertThat(offer.getScheduledPublishTime(), is(1372331021));
            assertThat(offer.getReminderTime(), is(iso8601DateOf("2013-07-27T19:00:00+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/offers.json");
            ResponseList<Offer> offers = facebook.getOffers(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/offers")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(offers.size(), is(1));

            Offer offer = offers.get(0);
            assertThat(offer.getId(), is("11111111"));
            assertThat(offer.getFrom().getId(), is("22222222"));
            assertThat(offer.getFrom().getName(), is("Page Name"));
            assertThat(offer.getFrom().getCategory(), is("Page Category"));
            assertThat(offer.getTitle(), is("The title of the Offer"));
            assertThat(offer.getCreatedTime(), is(iso8601DateOf("2013-06-27T19:00:00+0000")));
            assertThat(offer.getExpirationTime(), is(iso8601DateOf("2014-03-31T12:30:00+0000")));
            assertThat(offer.getTerms(), is("The description of the terms under which the offer can be claimed"));
            assertThat(offer.getImageURL().toString(), is("http://facebook4j.org/image.png"));
            assertThat(offer.getClaimLimit(), is(300));
            assertThat(offer.getCouponType(), is("online_only"));
            assertThat(offer.getRedemptionLink().toString(), is("http://facebook4j.org/redemption"));
            assertThat(offer.getRedemptionCode(), is("998877"));
            assertThat(offer.isPublished(), is(true));
            assertThat(offer.getScheduledPublishTime(), is(1372331021));
            assertThat(offer.getReminderTime(), is(iso8601DateOf("2013-07-27T19:00:00+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/offers.json");
            ResponseList<Offer> offers = facebook.getOffers("137246726435626");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/offers")));

            assertThat(offers.size(), is(1));

            Offer offer = offers.get(0);
            assertThat(offer.getId(), is("11111111"));
            assertThat(offer.getFrom().getId(), is("22222222"));
            assertThat(offer.getFrom().getName(), is("Page Name"));
            assertThat(offer.getFrom().getCategory(), is("Page Category"));
            assertThat(offer.getTitle(), is("The title of the Offer"));
            assertThat(offer.getCreatedTime(), is(iso8601DateOf("2013-06-27T19:00:00+0000")));
            assertThat(offer.getExpirationTime(), is(iso8601DateOf("2014-03-31T12:30:00+0000")));
            assertThat(offer.getTerms(), is("The description of the terms under which the offer can be claimed"));
            assertThat(offer.getImageURL().toString(), is("http://facebook4j.org/image.png"));
            assertThat(offer.getClaimLimit(), is(300));
            assertThat(offer.getCouponType(), is("online_only"));
            assertThat(offer.getRedemptionLink().toString(), is("http://facebook4j.org/redemption"));
            assertThat(offer.getRedemptionCode(), is("998877"));
            assertThat(offer.isPublished(), is(true));
            assertThat(offer.getScheduledPublishTime(), is(1372331021));
            assertThat(offer.getReminderTime(), is(iso8601DateOf("2013-07-27T19:00:00+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/offers.json");
            ResponseList<Offer> offers = facebook.getOffers("137246726435626", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/offers")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(offers.size(), is(1));

            Offer offer = offers.get(0);
            assertThat(offer.getId(), is("11111111"));
            assertThat(offer.getFrom().getId(), is("22222222"));
            assertThat(offer.getFrom().getName(), is("Page Name"));
            assertThat(offer.getFrom().getCategory(), is("Page Category"));
            assertThat(offer.getTitle(), is("The title of the Offer"));
            assertThat(offer.getCreatedTime(), is(iso8601DateOf("2013-06-27T19:00:00+0000")));
            assertThat(offer.getExpirationTime(), is(iso8601DateOf("2014-03-31T12:30:00+0000")));
            assertThat(offer.getTerms(), is("The description of the terms under which the offer can be claimed"));
            assertThat(offer.getImageURL().toString(), is("http://facebook4j.org/image.png"));
            assertThat(offer.getClaimLimit(), is(300));
            assertThat(offer.getCouponType(), is("online_only"));
            assertThat(offer.getRedemptionLink().toString(), is("http://facebook4j.org/redemption"));
            assertThat(offer.getRedemptionCode(), is("998877"));
            assertThat(offer.isPublished(), is(true));
            assertThat(offer.getScheduledPublishTime(), is(1372331021));
            assertThat(offer.getReminderTime(), is(iso8601DateOf("2013-07-27T19:00:00+0000")));
        }
    }

    public static class getLikedPage extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/page/kitkatit.json");
            Page actual = facebook.getLikedPage("160430023972443");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/likes/160430023972443")));

            assertThat(actual.getId(), is("160430023972443"));
            assertThat(actual.getCategory(), is("Company"));
            assertThat(actual.getName(), is("Kit Kat"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-08-29T04:56:49+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/kitkatit_website.json");
            Page actual = facebook.getLikedPage("160430023972443", new Reading().fields("name").fields("website"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/likes/160430023972443")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,website"));

            assertThat(actual.getId(), is("160430023972443"));
            assertThat(actual.getWebsite(), is("www.kitkat.it"));
            assertThat(actual.getName(), is("Kit Kat"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/kitkatit.json");
            Page actual = facebook.getLikedPage("1234567890123456", "160430023972443");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/likes/160430023972443")));

            assertThat(actual.getId(), is("160430023972443"));
            assertThat(actual.getCategory(), is("Company"));
            assertThat(actual.getName(), is("Kit Kat"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-08-29T04:56:49+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/page/kitkatit_website.json");
            Page actual = facebook.getLikedPage("1234567890123456", "160430023972443", new Reading().fields("name").fields("website"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/likes/160430023972443")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,website"));

            assertThat(actual.getId(), is("160430023972443"));
            assertThat(actual.getWebsite(), is("www.kitkat.it"));
            assertThat(actual.getName(), is("Kit Kat"));
        }
    }

    public static class createOffer extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            OfferUpdate offerUpdate = new OfferUpdate()
                                      .title("title")
                                      .expirationTime(createCal(2013, 6, 27, 19, 0, 0))
                                      .terms("terms")
                                      .imageURL(new URL("http://facebook4j.org/image_url"))
                                      .claimLimit(300)
                                      .couponType("online_only")
                                      .qrcode("qrcode")
                                      .barcode("barcode")
                                      .redemptionLink(new URL("http://facebook4j.org/redemption_link"))
                                      .redemptionCode("redemption_code")
                                      .isPublished(true)
                                      .scheduledPublishTime(1372331021)
                                      .reminderTime(createCal(2013, 7, 27, 19, 0, 0));
            String offerId = facebook.createOffer(offerUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/offers")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("title", "title"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("expiration_time", "2013-06-27T19:00:00+0000"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("terms", "terms"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("image_url", "http://facebook4j.org/image_url"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("claim_limit", "300"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("coupon_type", "online_only"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("qrcode", "qrcode"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("barcode", "barcode"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("redemption_link", "http://facebook4j.org/redemption_link"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("redemption_code", "redemption_code"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("published", "true"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("scheduled_publish_time", "1372331021"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("reminder_time", "2013-07-27T19:00:00+0000"));

            assertThat(offerId, is("1234567890123456"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            OfferUpdate offerUpdate = new OfferUpdate()
                                      .title("title")
                                      .expirationTime(createCal(2013, 6, 27, 19, 0, 0))
                                      .terms("terms")
                                      .imageURL(new URL("http://facebook4j.org/image_url"))
                                      .claimLimit(300)
                                      .couponType("online_only")
                                      .qrcode("qrcode")
                                      .barcode("barcode")
                                      .redemptionLink(new URL("http://facebook4j.org/redemption_link"))
                                      .redemptionCode("redemption_code")
                                      .isPublished(true)
                                      .scheduledPublishTime(1372331021)
                                      .reminderTime(createCal(2013, 7, 27, 19, 0, 0));
            String offerId = facebook.createOffer("137246726435626", offerUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/offers")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("title", "title"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("expiration_time", "2013-06-27T19:00:00+0000"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("terms", "terms"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("image_url", "http://facebook4j.org/image_url"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("claim_limit", "300"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("coupon_type", "online_only"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("qrcode", "qrcode"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("barcode", "barcode"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("redemption_link", "http://facebook4j.org/redemption_link"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("redemption_code", "redemption_code"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("published", "true"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("scheduled_publish_time", "1372331021"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("reminder_time", "2013-07-27T19:00:00+0000"));

            assertThat(offerId, is("1234567890123456"));
        }

        @Test
        public void id_media_scheduledPublishTimeAsDate() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            OfferUpdate offerUpdate = new OfferUpdate()
                                      .title("title")
                                      .expirationTime(createCal(2013, 6, 27, 19, 0, 0))
                                      .terms("terms")
                                      .image(new Media(new File("src/test/resources/test_image.png")))
                                      .claimLimit(300)
                                      .couponType("online_only")
                                      .qrcode("qrcode")
                                      .barcode("barcode")
                                      .redemptionLink(new URL("http://facebook4j.org/redemption_link"))
                                      .redemptionCode("redemption_code")
                                      .isPublished(true)
                                      .scheduledPublishTime(new Date(1372331021000L))
                                      .reminderTime(createCal(2013, 7, 27, 19, 0, 0));
            String offerId = facebook.createOffer("137246726435626", offerUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/offers")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("title", "title"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("expiration_time", "2013-06-27T19:00:00+0000"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("terms", "terms"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("claim_limit", "300"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("coupon_type", "online_only"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("qrcode", "qrcode"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("barcode", "barcode"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("redemption_link", "http://facebook4j.org/redemption_link"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("redemption_code", "redemption_code"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("published", "true"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("scheduled_publish_time", "1372331021"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("reminder_time", "2013-07-27T19:00:00+0000"));

            assertThat(offerId, is("1234567890123456"));
        }
    }

    public static class deleteOffer extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean result = facebook.deleteOffer("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456")));

            assertThat(result, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean result = facebook.deleteOffer("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/1234567890123456")));

            assertThat(result, is(true));
        }
    }

    public static class getOffer extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/page/offer.json");
            Offer actual = facebook.getOffer("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456")));

            assertThat(actual.getRedemptionCode(), is("998877"));
            assertThat(actual.getReminderTime(), is(iso8601DateOf("2013-07-27T19:00:00+0000")));
            assertThat(actual.getClaimLimit(), is(300));
            assertThat(actual.getTerms(), is("The description of the terms under which the offer can be claimed"));
            assertThat(actual.getCouponType(), is("online_only"));
            assertThat(actual.getScheduledPublishTime(), is(1372331021));
            assertThat(actual.getFrom().getId(), is("22222222"));
            assertThat(actual.getFrom().getCategory(), is("Page Category"));
            assertThat(actual.getFrom().getName(), is("Page Name"));
            assertThat(actual.getExpirationTime(), is(iso8601DateOf("2014-03-31T12:30:00+0000")));
            assertThat(actual.getRedemptionLink().toString(), is("http://facebook4j.org/redemption"));
            assertThat(actual.getId(), is("11111111"));
            assertThat(actual.getTitle(), is("The title of the Offer"));
            assertThat(actual.getImageURL().toString(), is("http://facebook4j.org/image.png"));
            assertThat(actual.getCreatedTime(), is(iso8601DateOf("2013-06-27T19:00:00+0000")));
            assertThat(actual.isPublished(), is(true));
        }
    }

    public static class postBackdatingFeed extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            BackdatingPostUpdate backdatingPostUpdate = new BackdatingPostUpdate("backdating post test.")
                                                            .backdatedTime(1362249213)
                                                            .backdatedTimeGranularity(BackdatedTimeGranularity.day);
            String actual = facebook.postBackdatingFeed(backdatingPostUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("backdated_time", "1362249213"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("backdated_time_granularity", "day"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            BackdatingPostUpdate backdatingPostUpdate = new BackdatingPostUpdate("backdating post test.")
                                                            .backdatedTime(1362249213)
                                                            .backdatedTimeGranularity(BackdatedTimeGranularity.month);
            String actual = facebook.postBackdatingFeed("137246726435626", backdatingPostUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("backdated_time", "1362249213"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("backdated_time_granularity", "month"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class postPagePhoto extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            Media source = new Media(new File("src/test/resources/test_image.png"));
            PagePhotoUpdate pagePhotoUpdate = new PagePhotoUpdate(source).message("upload photo to the page test.");
            Set<String> countries = new HashSet<String>();
            countries.add("US");
            countries.add("GB");
            TargetingParameter targeting = new TargetingParameter().countries(countries);
            pagePhotoUpdate.setTargeting(targeting);
            FeedTargetingParameter feedTargeting = new FeedTargetingParameter().genders(FeedTargetingParameter.Gender.Male);
            feedTargeting.setAgeMin(20);
            feedTargeting.setAgeMax(40);
            pagePhotoUpdate.setFeedTargeting(feedTargeting);
            String actual = facebook.postPagePhoto(pagePhotoUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "upload photo to the page test."));
            assertThat(facebook.getHttpParameters(), hasTargetingParameterWithCountries("US", "GB"));
            assertThat(facebook.getHttpParameters(), hasPostJsonParameter("feed_targeting", "{\"age_min\":20,\"genders\":{\"value\":1},\"age_max\":40}"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            Media source = new Media(new File("src/test/resources/test_image.png"));
            PagePhotoUpdate pagePhotoUpdate = new PagePhotoUpdate(source).message("upload photo to the page test.");
            Set<String> countries = new HashSet<String>();
            countries.add("US");
            countries.add("GB");
            TargetingParameter targeting = new TargetingParameter().countries(countries);
            pagePhotoUpdate.setTargeting(targeting);
            FeedTargetingParameter feedTargeting = new FeedTargetingParameter().genders(FeedTargetingParameter.Gender.Male);
            feedTargeting.setAgeMin(20);
            feedTargeting.setAgeMax(40);
            pagePhotoUpdate.setFeedTargeting(feedTargeting);
            String actual = facebook.postPagePhoto("137246726435626", pagePhotoUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/photos")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "upload photo to the page test."));
            assertThat(facebook.getHttpParameters(), hasTargetingParameterWithCountries("US", "GB"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("feed_targeting", "{\"age_min\":20,\"genders\":{\"value\":1},\"age_max\":40}"));

            assertThat(actual, is("137246726435626_185932178233747"));
        }
    }

    public static class updatePageSetting extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            PageSettingUpdate pageSettingUpdate = new PageSettingUpdate("USERS_CAN_POST", false);
            boolean actual = facebook.updatePageSetting(pageSettingUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/settings")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("setting", "USERS_CAN_POST"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("value", "false"));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            PageSettingUpdate pageSettingUpdate = new PageSettingUpdate("USERS_CAN_POST", false);
            boolean actual = facebook.updatePageSetting("137246726435626", pageSettingUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/settings")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("setting", "USERS_CAN_POST"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("value", "false"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            PageSettingUpdate pageSettingUpdate = new PageSettingUpdate("USERS_CAN_POST", false);
            boolean actual = facebook.updatePageSetting("137246726435626", pageSettingUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626/settings")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("setting", "USERS_CAN_POST"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("value", "false"));

            assertThat(actual, is(true));
        }
    }

    public static class updatePageCoverPhoto extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            PageCoverUpdate pageCoverUpdate = new PageCoverUpdate("193816910778607").offsetY(20).noFeedStory(true);
            boolean actual = facebook.updatePageCoverPhoto(pageCoverUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("cover", "193816910778607"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("offset_y", "20"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("no_feed_story", "true"));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            PageCoverUpdate pageCoverUpdate = new PageCoverUpdate("193816910778607").offsetY(20).noFeedStory(true);
            boolean actual = facebook.updatePageCoverPhoto("137246726435626", pageCoverUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("cover", "193816910778607"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("offset_y", "20"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("no_feed_story", "true"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            PageCoverUpdate pageCoverUpdate = new PageCoverUpdate("193816910778607").offsetY(20).noFeedStory(true);
            boolean actual = facebook.updatePageCoverPhoto("137246726435626", pageCoverUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("cover", "193816910778607"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("offset_y", "20"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("no_feed_story", "true"));

            assertThat(actual, is(true));
        }
    }

    public static class displayPagePost extends MockFacebookTestBase {
        @Test
        public void hide() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.displayPagePost("137246726435626_210759175751047", true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626_210759175751047")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("is_hidden", "true"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void hide_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.displayPagePost("137246726435626_210759175751047", true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626_210759175751047")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("is_hidden", "true"));

            assertThat(actual, is(true));
        }

        @Test
        public void show() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.displayPagePost("137246726435626_210759175751047", false);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626_210759175751047")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("is_hidden", "false"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void show_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.displayPagePost("137246726435626_210759175751047", false);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626_210759175751047")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("is_hidden", "false"));

            assertThat(actual, is(true));
        }
    }

    public static class updateTab extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            TabUpdate tabUpdate = new TabUpdate()
                                    .position(4)
                                    .nonConnectionLandingTab(true);
            boolean actual = facebook.updateTab("notes", tabUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs/notes")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("position", "4"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("is_non_connection_landing_tab", "true"));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            TabUpdate tabUpdate = new TabUpdate()
                                    .position(4)
                                    .nonConnectionLandingTab(true);
            boolean actual = facebook.updateTab("137246726435626", "notes", tabUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/tabs/notes")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("position", "4"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("is_non_connection_landing_tab", "true"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            TabUpdate tabUpdate = new TabUpdate()
                                    .position(4)
                                    .nonConnectionLandingTab(true);
            boolean actual = facebook.updateTab("137246726435626", "notes", tabUpdate);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626/tabs/notes")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("position", "4"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("is_non_connection_landing_tab", "true"));

            assertThat(actual, is(true));
        }
    }

    public static class deleteTab extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteTab("notes");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/tabs/notes")));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteTab("137246726435626", "notes");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/137246726435626/tabs/notes")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteTab("137246726435626", "notes");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/137246726435626/tabs/notes")));

            assertThat(actual, is(true));
        }
    }

}
