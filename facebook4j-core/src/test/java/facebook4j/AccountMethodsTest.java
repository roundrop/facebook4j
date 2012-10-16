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

public class AccountMethodsTest extends FacebookTestBase {

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
    public void get() throws Exception {
        ResponseList<Account> accounts = real.getAccounts();
//        System.out.println(accounts);
        assertThat(accounts.size() > 0, is(true));
        
        //use fields parameter
        ResponseList<Account> accountsWithFields = real.getAccounts(new Reading().fields("name"));
        for (Account account : accountsWithFields) {
            assertThat(account.getCategory(), is(nullValue()));
            assertThat(account.getName(), is(notNullValue()));
        }
        
        //id
        ResponseList<Account> accountsById = real.getAccounts(real.getId());
        assertThat(accountsById, is(accounts));
    }

}
