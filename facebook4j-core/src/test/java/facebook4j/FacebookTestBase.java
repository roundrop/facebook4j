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

import facebook4j.auth.Authorization;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.ConfigurationBuilder;
import org.junit.Before;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

public abstract class FacebookTestBase {
    protected Properties p;

    @Before
    public void setUp() throws Exception {
        InputStream is = FacebookTestBase.class.getResourceAsStream("/test.properties");
        p = new Properties();
        p.load(is);
        is.close();

        System.setProperty("facebook4j.debug", p.getProperty("debug") == null ? "true" : p.getProperty("debug"));
        System.setProperty("facebook4j.loggerFactory", p.getProperty("loggerFactory") == null ? "facebook4j.internal.logging.StdOutLoggerFactory" : p.getProperty("loggerFactory"));
        System.setProperty("facebook4j.http.prettyDebug", p.getProperty("http.prettyDebug") == null ? "true" : p.getProperty("http.prettyDebug"));
        System.setProperty("facebook4j.jsonStoreEnabled", p.getProperty("jsonStoreEnabled") == null ? "true" : p.getProperty("jsonStoreEnabled"));
    }

    protected Facebook createFacebook() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthAppId(p.getProperty("oauth.appId")).setOAuthAppSecret(p.getProperty("oauth.appSecret"));
        cb.setOAuthAccessToken(p.getProperty("oauth.accessToken"));
        Authorization auth = new OAuthAuthorization(cb.build());
        return new FacebookFactory().getInstance(auth);
    }

    protected Calendar createCal(int year, int month, int day, int hour, int minute, int second, TimeZone tz) {
        Calendar cal = Calendar.getInstance(tz);
        cal.clear();
        cal.set(year, month-1, day, hour, minute, second);
        return cal;
    }
    protected Calendar createCal(int year, int month, int day, int hour, int minute, int second) {
        return createCal(year, month, day, hour, minute, second, TimeZone.getTimeZone("UTC"));
    }

}
