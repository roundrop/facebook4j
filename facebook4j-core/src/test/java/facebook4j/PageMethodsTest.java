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

}
