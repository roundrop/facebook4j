package facebook4j;

import facebook4j.internal.http.RequestMethod;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class TestUserMethodsTest {

    public static class createTestUser extends MockFacebookTestBase {
        @Test
        public void appId() throws Exception {
            facebook.setMockJSON("mock_json/testuser/installed.json");
            TestUser testUser = facebook.createTestUser("appId");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "en_US"));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "true"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is("access_token"));
        }

        @Test
        public void installed_default() throws Exception {
            facebook.setMockJSON("mock_json/testuser/installed.json");
            TestUser testUser = facebook.createTestUser("appId", "test", "en_US", "user_about_me");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("name", "test"));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "en_US"));
            assertThat(facebook.getEndpointURL(), hasParameter("permissions", "user_about_me"));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "true"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is("access_token"));
        }

        @Test
        public void installed_true() throws Exception {
            facebook.setMockJSON("mock_json/testuser/installed.json");
            TestUser testUser = facebook.createTestUser("appId", "test", "en_US", "user_about_me", true);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("name", "test"));
            assertThat(facebook.getEndpointURL(), hasParameter("locale", "en_US"));
            assertThat(facebook.getEndpointURL(), hasParameter("permissions", "user_about_me"));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "true"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is("access_token"));
        }

        @Test
        public void notInstalled() throws Exception {
            facebook.setMockJSON("mock_json/testuser/not_installed.json");
            TestUser testUser = facebook.createTestUser("appId", "test", "en_US", "user_about_me", false);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/appId/accounts/test-users")));
            assertThat(facebook.getEndpointURL(), hasParameter("installed", "false"));

            assertThat(testUser.getId(), is("100008791899167"));
            assertThat(testUser.getEmail(), is("test_yzrvlnj_test@tfbnw.net"));
            assertThat(testUser.getLoginUrl(), is("https://developers.facebook.com/checkpoint/test-user-login/100008791899167/"));
            assertThat(testUser.getPassword(), is("1073749147"));
            assertThat(testUser.getAccessToken(), is(nullValue()));
        }
    }

}
