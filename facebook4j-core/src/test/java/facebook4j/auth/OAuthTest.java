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

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.FacebookTestBase;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.internal.http.HttpClientImpl;
import facebook4j.internal.http.HttpClientWrapper;
import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.http.HttpRequest;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.http.ListCookieHandler;
import facebook4j.internal.http.RequestMethod;
import facebook4j.junit.category.RealAPITests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.CookieHandler;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
        Facebook1.setOAuthAppId("appId", "appSecret");
        Facebook Facebook2 = new FacebookFactory().getInstance();
        Facebook2.setOAuthAppId("appId", "appSecret");
        assertThat(Facebook1, is(Facebook2));
    }

    @Category(RealAPITests.class)
    @Test
    public void OAuth() throws Exception {
        ConfigurationBuilder build = new ConfigurationBuilder();
        String oAuthAccessToken = p.getProperty("oauth.accessToken");
        String oAuthAppId = p.getProperty("oauth.appId");
        String oAuthAppSecret = p.getProperty("oauth.appSecret");
        build.setOAuthAccessToken(oAuthAccessToken);
        build.setOAuthAppId(oAuthAppId);
        build.setOAuthAppSecret(oAuthAppSecret);
        OAuthAuthorization auth = new OAuthAuthorization(build.build());
        Facebook fb = new FacebookFactory().getInstance(auth);
        fb.getId();
    }

    @Category(RealAPITests.class)
    @Test
    public void signinWithFacebook() throws Exception {
        CookieHandler.setDefault(new ListCookieHandler());

        Facebook facebook = new FacebookFactory().getInstance();
        HttpClientImpl http = new HttpClientImpl();
        HttpResponse response;
        
        String oAuthAppId = p.getProperty("oauth.appId");
        String oAuthAppSecret = p.getProperty("oauth.appSecret");
        facebook.setOAuthAppId(oAuthAppId, oAuthAppSecret);
        facebook.setOAuthPermissions("email");

        // auth
        String callbackURL = p.getProperty("login.callbackURL");
        String authorizationURL = facebook.getOAuthAuthorizationURL(callbackURL);
        response = http.get(authorizationURL);

        // login
        String loginURL = response.getResponseHeader("Location");
        response = http.get(loginURL);

        String resStr = response.asString();
        String authorizeURL = "https://www.facebook.com" + catchPattern(resStr, "<form id=\"login_form\" action=\"", "\" method=\"post\"");
        HttpParameter[] params = new HttpParameter[18];
        params[0] = new HttpParameter("lsd", catchPattern(resStr
                    , "<input type=\"hidden\" name=\"lsd\" value=\"", "\" autocomplete=\"off\" />"));
        params[1] = new HttpParameter("api_key", catchPattern(resStr
                    , "<input type=\"hidden\" autocomplete=\"off\" id=\"api_key\" name=\"api_key\" value=\"", "\" />"));
        params[2] = new HttpParameter("display", "page");
        params[3] = new HttpParameter("enable_profile_selector", "");
        params[4] = new HttpParameter("legacy_return", "1");
        params[5] = new HttpParameter("next", catchPattern(resStr
                    , "<input type=\"hidden\" autocomplete=\"off\" id=\"next\" name=\"next\" value=\"", "\" />"));
        params[6] = new HttpParameter("profile_selector_ids", "");
        params[7] = new HttpParameter("skip_api_login", "1");
        params[8] = new HttpParameter("signed_next", "1");
        params[9] = new HttpParameter("trynum", "1");
        params[10] = new HttpParameter("timezone", "");
        params[11] = new HttpParameter("lgnrnd", catchPattern(resStr
                    , "<input type=\"hidden\" name=\"lgnrnd\" value=\"", "\" />"));
        params[12] = new HttpParameter("lgnjs", catchPattern(resStr
                    , "<input type=\"hidden\" id=\"lgnjs\" name=\"lgnjs\" value=\"", "\" />"));
        params[13] = new HttpParameter("email", p.getProperty("login.email"));
        params[14] = new HttpParameter("pass", p.getProperty("login.password"));
        params[15] = new HttpParameter("persistent", "1");
        params[16] = new HttpParameter("default_persistent", "0");
        params[17] = new HttpParameter("login", "&#x30ed;&#x30b0;&#x30a4;&#x30f3;");

        response = http.request(new HttpRequest(RequestMethod.POST, authorizeURL, params, null, null));

        // dialog
        String dialogURL = response.getResponseHeader("Location").replaceAll("&amp%3B", "&");
        response = http.request(new HttpRequest(RequestMethod.GET, dialogURL, null, null, null));
        if (null != response.getResponseHeader("Location")) {
            String redirectURL = response.getResponseHeader("Location");
            assertThat(redirectURL.contains("code"), is(true));
            return;
        }

        // read
        resStr = response.asString();
        String readURL = "https://www.facebook.com" + catchPattern(resStr, "id=\\\"platformDialogForm\\\" action=\\\"", "\" method=\\\"post\\\"").replaceAll("\\\\", "");
        params = new HttpParameter[18];
        params[0] = new HttpParameter("fb_dtsg", catchPattern(resStr
                    , "name=\\\"fb_dtsg\\\" value=\\\"", "\\\" autocomplete=\\\"off\\\" \\/>"));
        params[1] = new HttpParameter("app_id", oAuthAppId);
        params[2] = new HttpParameter("redirect_uri", callbackURL);
        params[3] = new HttpParameter("display", "page");
        params[4] = new HttpParameter("access_token", "");
        params[5] = new HttpParameter("sdk", "");
        params[6] = new HttpParameter("from_post", "1");
        params[7] = new HttpParameter("public_info_nux", catchPattern(resStr
                    , "name=\\\"public_info_nux\\\" value=\\\"", "\\\" \\/>"));
        params[8] = new HttpParameter("login", catchPattern(resStr
                    , "name=\\\"login\\\" value=\\\"", "\\\" \\/>"));
        params[9] = new HttpParameter("read", catchPattern(resStr
                    , "name=\\\"read\\\" value=\\\"", "\\\" \\/>"));
        params[10] = new HttpParameter("write", catchPattern(resStr
                    , "name=\\\"write\\\" value=\\\"", "\\\" \\/>"));
        params[11] = new HttpParameter("extended", catchPattern(resStr
                    , "name=\\\"extended\\\" value=\\\"", "\\\" \\/>"));
        params[12] = new HttpParameter("confirm", catchPattern(resStr
                    , "name=\\\"confirm\\\" value=\\\"", "\\\" \\/>"));
        params[13] = new HttpParameter("auth_type", "");
        params[14] = new HttpParameter("auth_nonce", "");
        params[15] = new HttpParameter("return_format", catchPattern(resStr
                , "name=\\\"return_format\\\" value=\\\"", "\\\" \\/>"));
        params[16] = new HttpParameter("domain", "");
        params[17] = new HttpParameter("sso_device", "");

        response = http.request(new HttpRequest(RequestMethod.POST, readURL, params, null, null));

        String redirectURL = response.getResponseHeader("Location");
        assertThat(redirectURL.contains("code"), is(true));
    }

    @Test(expected = IllegalStateException.class)
    public void illegalStatus() throws Exception {
        new FacebookFactory().getInstance().getOAuthAccessToken();
    }

    @Category(RealAPITests.class)
    @Test
    public void accessToken_res() throws Exception {
        String appId = p.getProperty("oauth.appId");
        String appSecret = p.getProperty("oauth.appSecret");

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
    }

    @Category(RealAPITests.class)
    @Test
    public void accessToken_res_v23() throws Exception {
        System.setProperty("facebook4j.oauth.accessTokenURL", "https://graph.facebook.com/v2.3/oauth/access_token");

        String appId = p.getProperty("oauth.appId");
        String appSecret = p.getProperty("oauth.appSecret");

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
        assertThat(at.getType(), is(notNullValue()));
        assertThat(at.getExpires(), is(nullValue()));
    }

    @Test
    public void accessToken_string() throws Exception {
        AccessToken at = new AccessToken("6377362-kW0YV1ymaqEUCSHP29ux169mDeA4kQfhEuqkdvHk", 123456789012345L);
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

    private static String catchPattern(String body, String before, String after) {
        int beforeIndex = body.indexOf(before);
        int afterIndex = body.indexOf(after, beforeIndex);
        return body.substring(beforeIndex + before.length(), afterIndex);
    }
}
