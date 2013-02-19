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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void getPageFeed() throws Exception {
        String pageId = "19292868552"; //The Page for Facebook Platform
        ResponseList<Post> feed = facebook1.getPageFeed(pageId);
        for (Post post : feed) {
            System.out.println(post);
        }
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

}
