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
import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.internal.matchers.TypeSafeMatcher;

import java.io.InputStream;
import java.util.*;

public abstract class FacebookTestBase {
    protected Properties p;

    @Before
    public void setUp() throws Exception {
        InputStream is = FacebookTestBase.class.getResourceAsStream("/test.properties");
        p = new Properties();
        if (is != null) {
            p.load(is);
            is.close();
        }

        System.setProperty("facebook4j.debug", p.getProperty("debug", "true"));
        System.setProperty("facebook4j.loggerFactory", p.getProperty("loggerFactory", "facebook4j.internal.logging.StdOutLoggerFactory"));
        System.setProperty("facebook4j.http.prettyDebug", p.getProperty("http.prettyDebug", "true"));
        System.setProperty("facebook4j.jsonStoreEnabled", p.getProperty("jsonStoreEnabled", "true"));
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

    protected Matcher<HttpParameter[]> hasTargetingParameterWithCountries(String... expectedCountryCodes) {
        final Set<String> expectedCountriesSet = new HashSet<String>(Arrays.asList(expectedCountryCodes));
        return new TypeSafeMatcher<HttpParameter[]>(HttpParameter[].class) {

            private final List<String> actualParams = new ArrayList<String>();

            @Override
            public boolean matchesSafely(HttpParameter[] actual) {
                for (HttpParameter param : actual) {
                    if (param.getName().equals("targeting")) {
                        actualParams.add(param.getName() + "=" + param.getValue());
                        if (matches(param.getValue())) {
                            return true;
                        }
                    }
                }
                return false;
            }

            protected boolean matches(String targetingParamValue) {
                try {
                    JSONObject targetingParamValueObject = new JSONObject(targetingParamValue);
                    JSONArray actualCountriesArray = targetingParamValueObject.getJSONArray("countries");
                    Set<String> actualCountriesSet = new HashSet<String>();
                    for (int i = 0; i < actualCountriesArray.length(); i++) {
                        String country = actualCountriesArray.getString(i);
                        actualCountriesSet.add(country);
                    }
                    return expectedCountriesSet.equals(actualCountriesSet);
                } catch (JSONException ignore) {
                    return false;
                }
            }

            public void describeTo(Description desc) {
                desc.appendValue("targeting=" + expectedCountriesSet);
                if (actualParams.size() > 0) {
                    desc.appendText(" but actual is ");
                    desc.appendValue(actualParams.get(0));
                    for (int i = 1; i < actualParams.size(); i++) {
                        desc.appendText(", ");
                        desc.appendValue(actualParams.get(i));
                    }
                } else {
                    desc.appendText(" but actual has no 'targeting' parameter");
                }
            }
        };
    }

}
