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

import java.util.ArrayList;
import java.util.List;

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

}
