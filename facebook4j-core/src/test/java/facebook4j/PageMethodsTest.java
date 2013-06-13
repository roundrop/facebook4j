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

import java.net.URL;

import static facebook4j.junit.URLMatchers.*;
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
