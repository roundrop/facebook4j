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

import java.util.List;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class DomainMethodsTest {
    public static class getDomain extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/domain/facebook.json");
            Domain actual = facebook.getDomain("369296215699");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/369296215699")));

            assertThat(actual.getId(), is("369296215699"));
            assertThat(actual.getName(), is("Facebook.com"));
        }
    }

    public static class getDomainByName extends MockFacebookTestBase {
        @Test
        public void name() throws Exception {
            facebook.setMockJSON("mock_json/domain/facebook.json");
            Domain actual = facebook.getDomainByName("www.facebook.com");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));
            assertThat(facebook.getEndpointURL(), hasParameter("domain", "www.facebook.com"));

            assertThat(actual.getId(), is("369296215699"));
            assertThat(actual.getName(), is("Facebook.com"));
        }
    }

    public static class getDomainsByName extends MockFacebookTestBase {
        @Test
        public void names() throws Exception {
            facebook.setMockJSON("mock_json/domain/domains.json");
            List<Domain> actuals = facebook.getDomainsByName("www.facebook.com", "www.example.com");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));
            assertThat(facebook.getEndpointURL(), hasParameter("domains", "www.facebook.com,www.example.com"));

            assertThat(actuals.size(), is(2));
            Domain actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("345629607804"));
            assertThat(actual1.getName(), is("example.com"));
            Domain actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("369296215699"));
            assertThat(actual2.getName(), is("Facebook.com"));
        }
    }

}
