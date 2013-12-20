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

package facebook4j.internal.http;

import facebook4j.auth.Authorization;
import facebook4j.auth.NullAuthorization;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.ConfigurationBuilder;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class HttpRequestTest {

    public static class minimum {
        @Test
        public void nulls() throws Exception {
            RequestMethod method = RequestMethod.GET;
            String url = "https://graph.facebook.com/foo/bar";
            HttpParameter[] parameters = null;
            Authorization authorization = null;
            Map<String, String> requestHeaders = null;

            HttpRequest actual = new HttpRequest(method, url, parameters, authorization, requestHeaders);
            assertThat(actual.getMethod(), is(RequestMethod.GET));
            assertThat(actual.getURL(), is("https://graph.facebook.com/foo/bar"));
            assertThat(actual.getParameters().length, is(0));
            assertThat(actual.getAuthorization(), is(nullValue()));
            assertThat(actual.getRequestHeaders(), is(nullValue()));
        }

        @Test
        public void empty() throws Exception {
            RequestMethod method = RequestMethod.GET;
            String url = "https://graph.facebook.com/foo/bar";
            HttpParameter[] parameters = new HttpParameter[0];
            Authorization authorization = NullAuthorization.getInstance();
            Map<String, String> requestHeaders = new HashMap<String, String>();

            HttpRequest actual = new HttpRequest(method, url, parameters, authorization, requestHeaders);
            assertThat(actual.getMethod(), is(RequestMethod.GET));
            assertThat(actual.getURL(), is("https://graph.facebook.com/foo/bar"));
            assertThat(actual.getParameters().length, is(0));
            assertThat(actual.getAuthorization(), is((Authorization) NullAuthorization.getInstance()));
            assertThat(actual.getRequestHeaders().size(), is(0));
        }
    }

    public static class withAccessToken {
        @Test
        public void get() throws Exception {
            RequestMethod method = RequestMethod.GET;
            String url = "https://graph.facebook.com/foo/bar";
            HttpParameter[] parameters = new HttpParameter[1];
            parameters[0] = new HttpParameter("hoge", "1");
            ConfigurationBuilder cb = new ConfigurationBuilder().setOAuthAccessToken("access_token");
            OAuthAuthorization authorization = new OAuthAuthorization(cb.build());
            Map<String, String> requestHeaders = new HashMap<String, String>();

            HttpRequest actual = new HttpRequest(method, url, parameters, authorization, requestHeaders);
            assertThat(new URL(actual.getURL()), is(pathOf("/foo/bar")));
            assertThat(new URL(actual.getURL()), hasParameter("access_token", "access_token"));
            assertThat(actual.getParameters().length, is(0));
        }

        @Test
        public void post() throws Exception {
            RequestMethod method = RequestMethod.POST;
            String url = "https://graph.facebook.com/foo/bar";
            HttpParameter[] parameters = new HttpParameter[1];
            parameters[0] = new HttpParameter("hoge", "1");
            ConfigurationBuilder cb = new ConfigurationBuilder().setOAuthAccessToken("access_token");
            OAuthAuthorization authorization = new OAuthAuthorization(cb.build());
            Map<String, String> requestHeaders = new HashMap<String, String>();

            HttpRequest actual = new HttpRequest(method, url, parameters, authorization, requestHeaders);
            assertThat(actual.getURL(), is("https://graph.facebook.com/foo/bar"));
            assertThat(actual.getParameters().length, is(2));
            assertThat(actual.getParameters()[0].getName(), is("hoge"));
            assertThat(actual.getParameters()[0].getValue(), is("1"));
            assertThat(actual.getParameters()[1].getName(), is("access_token"));
            assertThat(actual.getParameters()[1].getValue(), is("access_token"));
        }
    }

    public static class appSecretProofEnabled {
        @Test
        public void get() throws Exception {
            RequestMethod method = RequestMethod.GET;
            String url = "https://graph.facebook.com/foo/bar";
            HttpParameter[] parameters = new HttpParameter[1];
            parameters[0] = new HttpParameter("hoge", "1");
            ConfigurationBuilder cb = new ConfigurationBuilder()
                                        .setAppSecretProofEnabled(true)
                                        .setOAuthAppSecret("1234567890123456")
                                        .setOAuthAccessToken("access_token");
            OAuthAuthorization authorization = new OAuthAuthorization(cb.build());
            Map<String, String> requestHeaders = new HashMap<String, String>();

            HttpRequest actual = new HttpRequest(method, url, parameters, authorization, requestHeaders);
            assertThat(new URL(actual.getURL()), is(pathOf("/foo/bar")));
            assertThat(new URL(actual.getURL()), hasParameter("access_token", "access_token"));
            assertThat(new URL(actual.getURL()), hasParameter("appsecret_proof", "df1d2959f7845d115a06312c748b426b03dd77c017ee24fdf3b492ca2bd4436c"));
            assertThat(actual.getParameters().length, is(0));
        }

        @Test
        public void post() throws Exception {
            RequestMethod method = RequestMethod.POST;
            String url = "https://graph.facebook.com/foo/bar";
            HttpParameter[] parameters = new HttpParameter[1];
            parameters[0] = new HttpParameter("hoge", "1");
            ConfigurationBuilder cb = new ConfigurationBuilder()
                                        .setAppSecretProofEnabled(true)
                                        .setOAuthAppSecret("1234567890123456")
                                        .setOAuthAccessToken("access_token");
            OAuthAuthorization authorization = new OAuthAuthorization(cb.build());
            Map<String, String> requestHeaders = new HashMap<String, String>();

            HttpRequest actual = new HttpRequest(method, url, parameters, authorization, requestHeaders);
            assertThat(actual.getURL(), is("https://graph.facebook.com/foo/bar"));
            assertThat(actual.getParameters().length, is(3));
            assertThat(actual.getParameters()[0].getName(), is("hoge"));
            assertThat(actual.getParameters()[0].getValue(), is("1"));
            assertThat(actual.getParameters()[1].getName(), is("access_token"));
            assertThat(actual.getParameters()[1].getValue(), is("access_token"));
            assertThat(actual.getParameters()[2].getName(), is("appsecret_proof"));
            assertThat(actual.getParameters()[2].getValue(), is("df1d2959f7845d115a06312c748b426b03dd77c017ee24fdf3b492ca2bd4436c"));
        }
    }
}
