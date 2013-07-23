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

import facebook4j.auth.MockAuthorization;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationContext;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class MockFacebookFactory implements java.io.Serializable {
    private static final long serialVersionUID = -1602081543322156808L;

    public static MockFacebook create() {
        return create(ConfigurationContext.getInstance());
    }

    public static MockFacebook create(Configuration conf) {
        return new MockFacebookImpl(conf, new MockAuthorization());
    }
}
