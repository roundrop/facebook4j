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


/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class PrivacyBuilder {
    private PrivacyBean privacyBean = new PrivacyBean();
    
    public PrivacyBuilder setValue(String value) {
        checkNotBuilt();
        privacyBean.setValue(value);
        return this;
    }
    public PrivacyBuilder setFriends(String friends) {
        checkNotBuilt();
        privacyBean.setFriends(friends);
        return this;
    }
    public PrivacyBuilder setNetworks(String networks) {
        checkNotBuilt();
        privacyBean.setNetworks(networks);
        return this;
    }
    public PrivacyBuilder setAllow(String allow) {
        checkNotBuilt();
        privacyBean.setAllow(allow);
        return this;
    }
    public PrivacyBuilder setDeny(String deny) {
        checkNotBuilt();
        privacyBean.setDeny(deny);
        return this;
    }

    public PrivacyBean build() {
        checkNotBuilt();
        try {
            return privacyBean;
        } finally {
            privacyBean = null;
        }
    }

    private void checkNotBuilt() {
        if (privacyBean == null) {
            throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
        }
    }
}
