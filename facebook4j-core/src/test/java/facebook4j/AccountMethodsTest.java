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

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class AccountMethodsTest {

    public static class getAccounts extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/account/f4j.json");
            ResponseList<Account> actuals = facebook.getAccounts();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/accounts")));

            assertThat(actuals.size(), is(1));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/100001568838021/accounts?access_token=access_token&limit=5000&offset=5000&__after_id=137246726435626"));
            assertThat(actuals.getPaging().getPrevious(), is(nullValue()));
            Account actual1 = actuals.get(0);
            assertThat(actual1.getAccessToken(), is("access_token"));
            assertThat(actual1.getCategory(), is("Software"));
            assertThat(actual1.getId(), is("137246726435626"));
            assertThat(actual1.getName(), is("F4J"));
            assertThat(actual1.getPerms().size(), is(6));
            assertThat(actual1.getPerms().get(0), is("ADMINISTER"));
            assertThat(actual1.getPerms().get(1), is("EDIT_PROFILE"));
            assertThat(actual1.getPerms().get(2), is("CREATE_CONTENT"));
            assertThat(actual1.getPerms().get(3), is("MODERATE_CONTENT"));
            assertThat(actual1.getPerms().get(4), is("CREATE_ADS"));
            assertThat(actual1.getPerms().get(5), is("BASIC_ADMIN"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/account/f4j_name.json");
            ResponseList<Account> actuals = facebook.getAccounts(new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/accounts")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(actuals.size(), is(1));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/100001568838021/accounts?fields=name&access_token=access_token&limit=5000&offset=5000&__after_id=137246726435626"));
            assertThat(actuals.getPaging().getPrevious(), is(nullValue()));
            Account actual1 = actuals.get(0);
            assertThat(actual1.getAccessToken(), is(nullValue()));
            assertThat(actual1.getCategory(), is(nullValue()));
            assertThat(actual1.getId(), is("137246726435626"));
            assertThat(actual1.getName(), is("F4J"));
            assertThat(actual1.getPerms().size(), is(0));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/account/f4j.json");
            ResponseList<Account> actuals = facebook.getAccounts("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/accounts")));

            assertThat(actuals.size(), is(1));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/100001568838021/accounts?access_token=access_token&limit=5000&offset=5000&__after_id=137246726435626"));
            assertThat(actuals.getPaging().getPrevious(), is(nullValue()));
            Account actual1 = actuals.get(0);
            assertThat(actual1.getAccessToken(), is("access_token"));
            assertThat(actual1.getCategory(), is("Software"));
            assertThat(actual1.getId(), is("137246726435626"));
            assertThat(actual1.getName(), is("F4J"));
            assertThat(actual1.getPerms().size(), is(6));
            assertThat(actual1.getPerms().get(0), is("ADMINISTER"));
            assertThat(actual1.getPerms().get(1), is("EDIT_PROFILE"));
            assertThat(actual1.getPerms().get(2), is("CREATE_CONTENT"));
            assertThat(actual1.getPerms().get(3), is("MODERATE_CONTENT"));
            assertThat(actual1.getPerms().get(4), is("CREATE_ADS"));
            assertThat(actual1.getPerms().get(5), is("BASIC_ADMIN"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/account/f4j_name.json");
            ResponseList<Account> actuals = facebook.getAccounts("1234567890123456", new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/accounts")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(actuals.size(), is(1));
            assertThat(actuals.getPaging().getNext().toString(), is("https://graph.facebook.com/100001568838021/accounts?fields=name&access_token=access_token&limit=5000&offset=5000&__after_id=137246726435626"));
            assertThat(actuals.getPaging().getPrevious(), is(nullValue()));
            Account actual1 = actuals.get(0);
            assertThat(actual1.getAccessToken(), is(nullValue()));
            assertThat(actual1.getCategory(), is(nullValue()));
            assertThat(actual1.getId(), is("137246726435626"));
            assertThat(actual1.getName(), is("F4J"));
            assertThat(actual1.getPerms().size(), is(0));
        }

    }

}
