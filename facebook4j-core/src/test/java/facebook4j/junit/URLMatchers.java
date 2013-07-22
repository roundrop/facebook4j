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

package facebook4j.junit;

import facebook4j.internal.http.HttpParameter;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Matchers for java.net.URL.
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class URLMatchers {

    @Factory
    public static Matcher<URL> pathOf(final String path) {
        return new TypeSafeMatcher<URL>() {
            private URL actual;

            @Override
            public boolean matchesSafely(URL actual) {
                this.actual = actual;

                if (path == null) return false;
                if (!path.equals(actual.getPath())) return false;
                return true;
            }

            public void describeTo(Description desc) {
                desc.appendValue(path);
                if (actual != null) {
                    desc.appendText(" but actual is ");
                    desc.appendValue(actual.getPath());
                }
            }
        };
    }

    @Factory
    public static Matcher<URL> hasParameter(final String name, final String value) {
        return new TypeSafeMatcher<URL>() {
            private List<String> actualParams = new ArrayList<String>();
            private String encodedName;
            private String encodedValue;

            @Override
            public boolean matchesSafely(URL actual) {
                if (name != null) {
                    encodedName = HttpParameter.encode(name);
                }
                if (value != null) {
                    encodedValue = HttpParameter.encode(value);
                }
                String query = actual.getQuery();
                if (query == null) return false;
                String[] params = query.split("&");
                for (String param : params) {
                    String[] nameAndValue = param.split("=");
                    if (nameAndValue[0].equals(encodedName)) {
                        actualParams.add(nameAndValue[0] + "=" + nameAndValue[1]);
                        if (nameAndValue[1].equals(encodedValue)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            public void describeTo(Description desc) {
                desc.appendValue(encodedName + "=" + encodedValue);
                if (actualParams.size() > 0) {
                    desc.appendText(" but actual is ");
                    desc.appendValue(actualParams.get(0));
                    for (int i = 1; i < actualParams.size(); i++) {
                        desc.appendText(", ");
                        desc.appendValue(actualParams.get(i));
                    }
                } else {
                    desc.appendText(" but actual has no '" + encodedName + "' parameter");
                }
            }
        };
    }

}
