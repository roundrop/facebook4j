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

import java.net.URL;
import java.util.List;
import java.util.Locale;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class TestUserMethodsTest extends MockFacebookTestBase {

    public static class getTestUsers extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
      	   int page = 1;
            facebook.setMockJSON("mock_json/testUser/page" + page + ".json");

				ResponseList<TestUser> testUsersResponseList = facebook.getTestUsers("appId_XXXXXXXXXXXX", 10);
				while (testUsersResponseList != null && testUsersResponseList.size() > 0) {
					for (TestUser u : testUsersResponseList) {
						assertNotNull(u.getId());
						assertNotNull(u.getAccessToken());
					}
					if (testUsersResponseList != null) {
						Paging<TestUser> paging = testUsersResponseList.getPaging();
						if (paging != null && paging.getNext() != null) {
			            facebook.setMockJSON("mock_json/testUser/page" + ++page + ".json");
							testUsersResponseList = facebook.fetchNext(paging);
						} else {
							testUsersResponseList = null;
						}
					}

				}
            
        }
        
    }

}
