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

package facebook4j.json;

import facebook4j.MockFacebook;
import facebook4j.MockFacebookFactory;
import facebook4j.Page;
import facebook4j.auth.AccessToken;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class DataObjectFactoryTest {

    protected MockFacebook facebook;

    @Before
    public void setUp() throws Exception {
        Configuration conf = new ConfigurationBuilder().setJSONStoreEnabled(true).build();
        facebook = MockFacebookFactory.create(conf);
        facebook.setOAuthAppId("mock", "json");
        facebook.setOAuthAccessToken(new AccessToken("required"));
    }

    @Test
    public void getRawJSON() throws Exception {
        facebook.setMockJSON("mock_json/page/f4j.json");
        Page page = facebook.getPage();
        String rawJSON = DataObjectFactory.getRawJSON(page);
        assertThat(rawJSON, is(notNullValue()));
    }

    @Test
    public void createPage() throws Exception {
        facebook.setMockJSON("mock_json/page/f4j.json");
        Page page = facebook.getPage();
        String rawJSON = DataObjectFactory.getRawJSON(page);
        Page actual = DataObjectFactory.createPage(rawJSON);
        assertThat(actual, is(page));
        assertThat(actual.toString(), is(page.toString()));
    }

}
