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

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class AccountMethodsTest extends MockFacebookTestBase {

    @Test
    public void me_accounts() throws Exception {
        facebook.setMockJSON("mock_json/accounts/single.json");
        ResponseList<Account> accounts = facebook.getAccounts();
        assertThat(facebook.getEndpointURL(), is(pathOf("/me/accounts")));

        assertThat(accounts.size(), is(1));
        assertThat(accounts.getPaging().getNext().toString(), is("https://graph.facebook.com/100001568838021/accounts?access_token=access_token&limit=5000&offset=5000&__after_id=137246726435626"));
        assertThat(accounts.getPaging().getPrevious(), is(nullValue()));
        Account account = accounts.get(0);
        assertThat(account.getAccessToken(), is("access_token"));
        assertThat(account.getCategory(), is("Software"));
        assertThat(account.getId(), is("137246726435626"));
        assertThat(account.getName(), is("F4J"));
        //TODO perms
    }

}
