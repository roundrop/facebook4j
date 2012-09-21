/*
 * Copyright 2007 Yusuke Yamamoto
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

package facebook4j.auth;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.FacebookTestBase;
import facebook4j.TestUser;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.http.HttpClientImpl;
import facebook4j.internal.http.HttpClientWrapper;
import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.http.HttpRequest;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.http.HttpResponseCode;
import facebook4j.internal.http.RequestMethod;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class OAuthTest extends FacebookTestBase {
    
    @Test
    public void deterministic() throws Exception {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        assertThat(list1, is(list2));
        Facebook Facebook1 = new FacebookFactory().getInstance();
        Facebook1.setOAuthAppId(appId, appSecret);
        Facebook Facebook2 = new FacebookFactory().getInstance();
        Facebook2.setOAuthAppId(appId, appSecret);
        assertThat(Facebook1, is(Facebook2));
    }

    @Test
    public void OAuth() throws Exception {
        ConfigurationBuilder build = new ConfigurationBuilder();
        String oAuthAccessToken = p.getProperty("real.oauth.accessToken");
        String oAuthAppId = p.getProperty("real.oauth.appId");
        String oAuthAppSecret = p.getProperty("real.oauth.appSecret");
        build.setOAuthAccessToken(oAuthAccessToken);
        build.setOAuthAppId(oAuthAppId);
        build.setOAuthAppSecret(oAuthAppSecret);
        OAuthAuthorization auth = new OAuthAuthorization(build.build());
        Facebook fb = new FacebookFactory().getInstance(auth);
        fb.getId();
    }

    @Test(expected = IllegalStateException.class)
    public void illegalStatus() throws Exception {
        new FacebookFactory().getInstance().getOAuthAccessToken();
        fail("should throw IllegalStateException since AppID hasn't been acquired.");
    }

    @Test
    public void signinWithFacebook() throws Exception {
        HttpResponse response;
        String resStr;
        String authorizeURL;
        HttpParameter[] params;
        HttpClientImpl http = new HttpClientImpl(conf);

        // unauthenticated
        unauthenticated.setOAuthAppId(appId, appSecret);
        // trying to get an access token without permitting the request token.
        try {
            unauthenticated.getOAuthAccessToken();
            fail();
        } catch (IllegalStateException ise) {
            assertEquals(ise.getMessage(), "No Token available.");
        }

        // authorization
        TestUser testUser = null;
        try {
            testUser = createTestUser(facebook1);

            // dialog
            String callbackURL = p.getProperty("app.callbackURL");
            response = http.get(facebook1.getOAuthAuthorizationURL(callbackURL));
            if (response.getStatusCode() != HttpResponseCode.FOUND) {
                fail("should redirect to 'https://www.facebook.com/login.php'");
            }
            
            // login form
            String loginURL = response.getResponseHeader("Location");
            response = http.get(loginURL);
            
            Map<String, String> props = new HashMap<String, String>();
            Map<String, List<String>> headers = response.getResponseHeaderFields();
            for (String key : headers.keySet()) {
                if (key == null) continue;
                if (!key.equals("Set-Cookie")) {
                    continue;
                }
                List<String> cookies = headers.get(key);
                StringBuilder sb = new StringBuilder();
                for (String c : cookies) {
                    String[] split = c.split(";");
                    for (String s : split) {
                        if (s.startsWith("datr=")
                                || s.startsWith("lu=")
                                || s.startsWith("locale=")
                                || s.startsWith("c_user=")
                                || s.startsWith("s=")
                                || s.startsWith("xs=")) {
                            sb.append(s + "; ");
                        }
                    }
                }
                props.put("Cookie", sb.toString());
            }
            
            resStr = response.asString();
            authorizeURL = catchPattern(resStr, "<form id=\"login_form\" action=\"", "\" method=\"post\"");
            params = new HttpParameter[16];
            params[0] = new HttpParameter("lsd", catchPattern(resStr, "name=\"lsd\" value=\"", "\" autocomplete=\"off\" />"));
            params[1] = new HttpParameter("next", catchPattern(resStr, "name=\"next\" value=\"", "\" />"));
            params[2] = new HttpParameter("api_key", catchPattern(resStr, "name=\"api_key\" value=\"", "\" />"));
            params[3] = new HttpParameter("return_session", catchPattern(resStr, "name=\"return_session\" value=\"", "\" />"));
            params[4] = new HttpParameter("cancel_url", catchPattern(resStr, "name=\"cancel_url\" value=\"", "\" />"));
            params[5] = new HttpParameter("legacy_return", catchPattern(resStr, "name=\"legacy_return\" value=\"", "\" />"));
            params[6] = new HttpParameter("display", catchPattern(resStr, "name=\"display\" value=\"", "\" />"));
            params[7] = new HttpParameter("session_key_only", catchPattern(resStr, "name=\"session_key_only\" value=\"", "\" />"));
            params[8] = new HttpParameter("skip_api_login", catchPattern(resStr, "name=\"skip_api_login\" value=\"", "\" />"));
            params[9] = new HttpParameter("trynum", catchPattern(resStr, "name=\"trynum\" value=\"", "\" />"));
            params[10] = new HttpParameter("charset_test", catchPattern(resStr, "name=\"charset_test\" value=\"", "\" />"));
            params[11] = new HttpParameter("timezone", catchPattern(resStr, "name=\"timezone\" value=\"", "\" id=\""));
            params[12] = new HttpParameter("lgnrnd", catchPattern(resStr, "name=\"lgnrnd\" value=\"", "\" />"));
            params[13] = new HttpParameter("lgnjs", catchPattern(resStr, "name=\"lgnjs\" value=\"", "\" />"));
            params[14] = new HttpParameter("email", testUser.getEmail());
            params[15] = new HttpParameter("pass", testUser.getPassword());
            response = http.request(new HttpRequest(RequestMethod.POST, authorizeURL, params, null, props));
            if (response.getStatusCode() != HttpResponseCode.FOUND) {
                fail("should redirect to 'https://www.facebook.com/dialog/permissions.request'");
            }
            
            // permissions
            String permissionsReqURL = response.getResponseHeader("Location");
            response = http.get(permissionsReqURL);
            if (response.getStatusCode() != HttpResponseCode.FOUND) {
                fail("should redirect to '" + callbackURL + "'");
            }
        } finally {
            if (testUser != null) {
                deleteTestUser(facebook1, testUser);
            }
        }
    }

    private static String catchPattern(String body, String before, String after) {
        int beforeIndex = body.indexOf(before);
        int afterIndex = body.indexOf(after, beforeIndex);
        return body.substring(beforeIndex + before.length(), afterIndex);
    }

    @Test
    public void accessToken() throws Exception {
        ConfigurationBuilder build = new ConfigurationBuilder();
        build.setOAuthAppId(appId);
        build.setOAuthAppSecret(appSecret);
        Configuration configuration = build.build();
        HttpClientWrapper http = new HttpClientWrapper(configuration);
        HttpResponse res = http.get(configuration.getOAuthAccessTokenURL() +
                                    "?client_id=" + appId +
                                    "&client_secret=" + appSecret +
                                    "&grant_type=client_credentials");
        AccessToken at = new AccessToken(res);
        assertThat(at.getToken(), is(notNullValue()));
        assertThat(at.getExpires(), is(nullValue()));

        at = new AccessToken("6377362-kW0YV1ymaqEUCSHP29ux169mDeA4kQfhEuqkdvHk", 123456789012345L);
        assertThat(at.getToken(), is("6377362-kW0YV1ymaqEUCSHP29ux169mDeA4kQfhEuqkdvHk"));
        assertThat(at.getExpires(), is(123456789012345L));

        at = new AccessToken("6377362-kW0YV1ymaqEUCSHP29ux169mDeA4kQfhEuqkdvHk");
        assertThat(at.getToken(), is("6377362-kW0YV1ymaqEUCSHP29ux169mDeA4kQfhEuqkdvHk"));
        assertThat(at.getExpires(), is(nullValue()));

        at = new AccessToken("access_token=6377362-kW0YV1ymaqEUCSHP29ux169mDeA4kQfhEuqkdvHk&expires=123456789012345");
        assertThat(at.getToken(), is("6377362-kW0YV1ymaqEUCSHP29ux169mDeA4kQfhEuqkdvHk"));
        assertThat(at.getExpires(), is(123456789012345L));
    }

    @Test
    public void encodeParameter() throws Exception {
        //http://wiki.oauth.net/TestCases
        assertThat(HttpParameter.encode("abcABC123"), is("abcABC123"));
        assertThat(HttpParameter.encode("-._~"), is("-._~"));
        assertThat(HttpParameter.encode("%"), is("%25"));
        assertThat(HttpParameter.encode("+"), is("%2B"));
        assertThat(HttpParameter.encode("&=*"), is("%26%3D%2A"));
        assertThat(HttpParameter.encode("\n"), is("%0A"));
        assertThat(HttpParameter.encode("\u0020"), is("%20"));
        assertThat(HttpParameter.encode("\u007F"), is("%7F"));
        assertThat(HttpParameter.encode("\u0080"), is("%C2%80"));
        assertThat(HttpParameter.encode("\u3001"), is("%E3%80%81"));

        String unreserved = "abcdefghijklmnopqrstuvwzyxABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._~";
        assertThat(HttpParameter.encode(unreserved), is(unreserved));
    }

}
