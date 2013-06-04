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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PageMethodsTest extends FacebookTestBase {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

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

}
