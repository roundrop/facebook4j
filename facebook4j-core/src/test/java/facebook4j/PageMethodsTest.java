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

import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import static facebook4j.junit.URLMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PageMethodsTest extends MockFacebookTestBase {

    @Test
    public void getGlobalBrandChildren() throws Exception {
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
    public void getGlobalBrandChildren_Reading() throws Exception {
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

    @Test
    public void getPageInsights() throws Exception {
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

    @Test
    public void getPageTagged() throws Exception {
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
        assertThat(tagged1.getMessageTags(), is(nullValue()));
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
        assertThat(post.getIcon(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/aS8ecmYRys0.gif"));
        assertThat(post.getLink().toString(), is("http://www.runningtowardshome.com/2013/06/grand-canyon-r2r2r.html"));
        assertThat(post.getName(), is("Running Towards Home | ©: My Quest to Run the Grand Canyon R2R2R"));
        assertThat(post.getPicture().toString(), is("https://fbexternal-a.akamaihd.net/safe_image.php?d=AQCk6K-KS0vS1qlM&w=154&h=154&url=http%3A%2F%2F3.bp.blogspot.com%2F-rbFFZFuvNiQ%2FUX33eAIvYjI%2FAAAAAAAAN2g%2FmJUZSlj7B1M%2Fs240%2FBeforeAfter2.png"));
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
        assertThat(tagged9.getMessageTags(), is(nullValue()));
        assertThat(tagged9.getObjectId(), is(nullValue()));
        assertThat(tagged9.getPrivacy().getValue(), is(PrivacyType.EMPTY));
        assertThat(tagged9.getTo().size(), is(1));
        assertThat(tagged9.getTo().get(0).getCategory(), is("Travel/leisure"));
        assertThat(tagged9.getTo().get(0).getId(), is("65341080064"));
        assertThat(tagged9.getTo().get(0).getName(), is("Grand Canyon"));
        assertThat(tagged9.getUpdatedTime(), is(iso8601DateOf("2013-05-30T18:30:57+0000")));
    }

    @Test
    public void getMilestones() throws Exception {
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
    public void getMilestones_limit1() throws Exception {
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
    public void getMilestones_id() throws Exception {
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
    public void getMilestones_id_limit1() throws Exception {
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

    @Test
    public void createMilestone() throws FacebookException {
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

    @Test
    public void deleteMilestone() throws FacebookException {
        facebook.setMockJSON("mock_json/empty.json");
        boolean result = facebook.deleteMilestone("187182304775401");
        assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
        assertThat(facebook.getEndpointURL(), is(pathOf("/187182304775401")));

        assertThat(result, is(true));
    }

/*
    @Test
    public void getLikedPage() throws Exception {
        Page page = real.getLikedPage("259655700571");    //Eclipse Facebook Page
        assertThat(page, is(notNullValue()));
        assertThat(page.getId(), is("259655700571"));
        assertThat(page.getName(), is("Eclipse"));
        assertThat(page.getCategory(), is("Computers/technology"));
        assertThat(page.getCreatedTime(), is(notNullValue()));

        page = real.getLikedPage("259655700571", new Reading().fields("can_post,cover,website,likes,link,name,talking_about_count"));
        System.out.println(page);
    }

    @Test
    public void getPage() throws Exception {
        String pageId = "19292868552"; //The Page for Facebook Platform
        Page page = facebook1.getPage(pageId);
        assertThat(page, is(notNullValue()));
        assertThat(page.getId(), is("19292868552"));
        assertThat(page.getName(), is("Facebook Developers"));
        assertThat(page.getCategory(), is("Product/service"));
        assertThat(page.getLink().toString(), is("http://www.facebook.com/FacebookDevelopers"));
        assertThat(page.getWebsite().toString(), is("http://developers.facebook.com"));
    }
    @Test
    public void getPage_withReading() throws Exception {
        String pageId = "19292868552"; //The Page for Facebook Platform
        Page page = facebook1.getPage(pageId, new Reading().fields("name"));
        assertThat(page, is(notNullValue()));
        assertThat(page.getId(), is("19292868552"));
        assertThat(page.getName(), is("Facebook Developers"));
        assertThat(page.getCategory(), is(nullValue()));
        assertThat(page.getLink(), is(nullValue()));
        assertThat(page.getWebsite(), is(nullValue()));
    }

    @Test
    public void getPromotablePosts() throws Exception {
        String pageId = "19292868552"; //The Page for Facebook Platform
        ResponseList<Post> promotablePosts = facebook1.getPromotablePosts(pageId);
        for (Post post : promotablePosts) {
            assertThat(post.getFrom().getId(), is("19292868552"));
            System.out.println(post);
        }
    }

    @Test
    public void getPromotablePosts_me() throws Exception {
        // require page access_token
        // replace to your page id
        String pageId = "137246726435626";
        ResponseList<Post> promotablePosts = real.getPromotablePosts();
        for (Post post : promotablePosts) {
            System.out.println(post);
        }
    }

    @Test
    public void postPageFeed() throws Exception {
        // require manage_pages permission
        // replace to your page id
        String pageId = "137246726435626";
        PostUpdate postUpdate = new PostUpdate("Testing. Succeeded?")
                .link(new URL("http://facebook4j.org"))
                .picture(new URL("http://facebook4j.org/images/hero.png"))
                .name("Facebook4J - A Java library for the Facebook Graph API")
                .caption("facebook4j.org")
                .description("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library.")
                .targeting(new Targeting().country("US").country("GB"));
        String postId = real.postFeed(pageId, postUpdate);
        assertThat(postId, is(notNullValue()));
        Post post = real.getPost(postId);
        assertThat(post.getPrivacy().getDescription().get(0), is("United States"));
        assertThat(post.getPrivacy().getDescription().get(1), is("United Kingdom"));
    }

    @Test
    public void postPageFeed_Promotable() throws Exception {
        // require manage_pages permission
        // replace to your page id
        String pageId = "137246726435626";
        PostUpdate postUpdate = new PostUpdate("Testing. Succeeded?")
                .link(new URL("http://facebook4j.org"))
                .picture(new URL("http://facebook4j.org/images/hero.png"))
                .name("Facebook4J - A Java library for the Facebook Graph API")
                .caption("facebook4j.org")
                .description("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library.")
                .published(false)
                .scheduledPublishTime(new Date(new Date().getTime() + 1000 * 60 * 15));
        String postId = real.postFeed(pageId, postUpdate);
        assertThat(postId, is(notNullValue()));
        Post post = real.getPost(postId);
        System.out.println(post);
    }

    @Test
    public void updatePageBasicAttributes() throws Exception {
        // require page access token
        // replace to your page id
        String pageId = "137246726435626";
        PageUpdate pageUpdate = new PageUpdate()
                                .about("Facebook4J: A Java library for the Facebook Graph API.")
                                .description("Facebook4J: A Java library for the Facebook Graph API.\nThis library provides the ease of use like Twitter4J.\nFacebook4J is an unofficial library.")
                                .generalInfo("Facebook4J is an unofficial Java library for the Facebook Graph API which is released under the Apache License 2.0.")
                                .website("http://facebook4j.org")
                                .phone("045-1111-2222");
        real.updatePageBasicAttributes(pageId, pageUpdate);
    }

    @Test
    public void updatePageProfilePhoto_URL() throws Exception {
        // require page access token
        // replace to your page id
        String pageId = "137246726435626";
        real.updatePageProfilePhoto(pageId, new URL("https://secure.gravatar.com/avatar/3e40bc0f7067d4b83e2b1d6316338bfb?s=420&d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png"));
    }

    @Test
    public void updatePageProfilePhoto_Media() throws Exception {
        // require page access token
        // replace to your page id
        String pageId = "137246726435626";
        File file = new File("src/test/resources/500x500.png");
        Media media = new Media(file);
        real.updatePageProfilePhoto(pageId, media);
    }

    @Test
    public void getPictureURL() throws Exception {
        String pageId = "19292868552"; //The Page for Facebook Platform
        URL pictureURL = facebook1.getPagePictureURL(pageId);
        assertThat(pictureURL, is(notNullValue()));

        pageId = "19292868552"; //The Page for Facebook Platform
        pictureURL = facebook1.getPagePictureURL(pageId, PictureSize.large);
        assertThat(pictureURL, is(notNullValue()));
    }

    @Test
    public void getPictureURL_page_access_token() throws Exception {
        // require page access token
        // replace to your page id
        String pageId = "137246726435626";
        URL pictureURL = real.getPagePictureURL();
        assertThat(pictureURL, is(notNullValue()));

        pictureURL = real.getPagePictureURL(PictureSize.large);
        assertThat(pictureURL, is(notNullValue()));
    }

    @Test
    public void getPageSettings() throws Exception {
        // require page access token
        // replace to your page id
        String pageId = "137246726435626";
        ResponseList<PageSetting> settings = real.getPageSettings(pageId);
        for (PageSetting setting : settings) {
            System.out.println(setting);
        }
    }

    @Test
    public void updatePageSetting() throws Exception {
        // require page access token
        // require manage_pages permission
        // replace to your page id
        String pageId = "137246726435626";
        PageSettingUpdate pageSettingUpdate = new PageSettingUpdate("USERS_CAN_POST", false);
        boolean result = real.updatePageSetting(pageId, pageSettingUpdate);
        assertThat(result, is(true));
        ResponseList<PageSetting> settings = real.getPageSettings(pageId);
        for (PageSetting setting : settings) {
            if (setting.getSetting().equals("USERS_CAN_POST")) {
                assertThat(setting.getValue(), is(false));
            }
        }

        pageSettingUpdate = new PageSettingUpdate("USERS_CAN_POST", true);
        result = real.updatePageSetting(pageId, pageSettingUpdate);
        assertThat(result, is(true));
        settings = real.getPageSettings(pageId);
        for (PageSetting setting : settings) {
            if (setting.getSetting().equals("USERS_CAN_POST")) {
                assertThat(setting.getValue(), is(true));
            }
        }
    }

    @Test
    public void postPagePhoto() throws Exception {
        String pageId = "137246726435626";
        File file = new File("src/test/resources/test_image.png");
        Media source = new Media(file);
        PagePhotoUpdate pagePhotoUpdate = new PagePhotoUpdate(source).message("test message");
        Set<String> countries = new HashSet<String>();
        countries.add("US");
        countries.add("GB");
        Targeting targeting = new Targeting().countries(countries);
        pagePhotoUpdate.setTargeting(targeting);
        FeedTargeting feedTargeting = new FeedTargeting().genders(FeedTargeting.Gender.Male);
        feedTargeting.setAgeMin(20);
        feedTargeting.setAgeMax(40);
        pagePhotoUpdate.setFeedTargeting(feedTargeting);
        String photoId = real.postPagePhoto(pageId, pagePhotoUpdate);
        System.out.println(photoId);
    }
*/
}
