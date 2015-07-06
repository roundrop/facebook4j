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
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Matchers for facebook4j.internal.http.HttpParameter.
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class F4JHttpParameterMatchers {

    @Factory
    public static Matcher<HttpParameter[]> hasPostParameter(final String name, final String value) {
        return new TypeSafeMatcher<HttpParameter[]>() {
            private List<String> actualParams = new ArrayList<String>();

            @Override
            public boolean matchesSafely(HttpParameter[] actual) {
                for (HttpParameter param : actual) {
                    if (param.getName().equals(name)) {
                        actualParams.add(param.getName() + "=" + param.getValue());
                        if (param.getValue().equals(value)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            public void describeTo(Description desc) {
                desc.appendValue(name + "=" + value);
                if (actualParams.size() > 0) {
                    desc.appendText(" but actual is ");
                    desc.appendValue(actualParams.get(0));
                    for (int i = 1; i < actualParams.size(); i++) {
                        desc.appendText(", ");
                        desc.appendValue(actualParams.get(i));
                    }
                } else {
                    desc.appendText(" but actual has no '" + name + "' parameter");
                }
            }
        };
    }

    @Factory
    public static Matcher<HttpParameter[]> hasPostJsonParameter(final String name, final String expectedJsonObjectSource) {
        JSONObject expectedJsonObject;
        try {
            expectedJsonObject = new JSONObject(expectedJsonObjectSource);
        } catch (JSONException ex) {
            throw new AssertionError("failed to parse object source: " + expectedJsonObjectSource);
        }
        return hasPostJsonParameter(name, expectedJsonObject);
    }

    @Factory
    public static Matcher<HttpParameter[]> hasPostJsonParameter(final String name, final JSONObject expectedValue) {
        assertNotNull("expectedValue", expectedValue);
        return new TypeSafeMatcher<HttpParameter[]>() {

            private final List<String> actualParams = new ArrayList<String>();

            @Override
            public boolean matchesSafely(HttpParameter[] actual) {
                for (HttpParameter param : actual) {
                    if (param.getName().equals(name)) {
                        actualParams.add(param.getName() + "=" + param.getValue());
                        String paramValueStr = param.getValue();
                        if (paramValueStr != null) {
                            JSONObject paramValue;
                            try {
                                paramValue = new JSONObject(paramValueStr);
                                if (equals(expectedValue, paramValue)) {
                                    return true;
                                }
                            } catch (JSONException ignore) {
                            }
                        }
                    }
                }
                return false;
            }

            protected boolean equals(JSONObject a, JSONObject b) {
                // This is not efficient and may not be correct in all cases, but it's good enough for our purposes
                if (a == b) {
                    return true;
                }
                if (a == null || b == null) {
                    return false;
                }
                String aStr = a.toString();
                String bStr = b.toString();
                if (aStr == null || bStr == null) {
                    // null is returned when there is an error in stringifying, so even if both
                    // are null, it doesn't imply equality
                    return false;
                }
                return aStr.equals(bStr);
            }

            public void describeTo(Description desc) {
                desc.appendValue(name + "=" + expectedValue);
                if (actualParams.size() > 0) {
                    desc.appendText(" but actual is ");
                    desc.appendValue(actualParams.get(0));
                    for (int i = 1; i < actualParams.size(); i++) {
                        desc.appendText(", ");
                        desc.appendValue(actualParams.get(i));
                    }
                } else {
                    desc.appendText(" but actual has no '" + name + "' parameter");
                }
            }
        };
    }

    @Factory
    public static Matcher<HttpParameter[]> hasPostParameter(final String name) {
        return new TypeSafeMatcher<HttpParameter[]>() {
            private List<String> actualParams = new ArrayList<String>();

            @Override
            public boolean matchesSafely(HttpParameter[] actual) {
                for (HttpParameter param : actual) {
                    if (param.getName().equals(name)) {
                        return true;
                    }
                }
                return false;
            }

            public void describeTo(Description desc) {
                desc.appendText("actual has no '" + name + "' parameter");
            }
        };
    }

}
