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
import facebook4j.junit.FacebookAPIVersion;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.List;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class PermissionMethodsTest {

    public static class getPermissions extends MockFacebookTestBase {

        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/permission/all.json");
            List<Permission> actuals = facebook.getPermissions();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/permissions")));

            assertThat(actuals.size(), is(80));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/permission/all.json");
            List<Permission> actuals = facebook.getPermissions("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/permissions")));

            assertThat(actuals.size(), is(80));
        }

    }

    public static class revokeAllPermission extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.revokeAllPermissions();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/permissions")));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.revokeAllPermissions("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/permissions")));

            assertThat(actual, is(true));
        }
    }

    public static class revokePermission extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.revokePermission("email");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/permissions/email")));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.revokePermission("1234567890123456", "email");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/permissions/email")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.revokePermission("1234567890123456", "email");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/1234567890123456/permissions/email")));

            assertThat(actual, is(true));
        }
    }

    public static class deleteAllPermission extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteAllPermissions();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/permissions")));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteAllPermissions("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/permissions")));

            assertThat(actual, is(true));
        }
    }

    public static class deletePermission extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deletePermission("email");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/permissions/email")));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deletePermission("1234567890123456", "email");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/permissions/email")));

            assertThat(actual, is(true));
        }
    }

}
