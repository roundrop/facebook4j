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

import java.util.List;

import facebook4j.internal.util.z_F4JInternalStringUtil;


/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class PrivacyBuilder {
    private PrivacyBean privacyBean = new PrivacyBean();
    
    public PrivacyBuilder setValue(PrivacyType value) {
        checkNotBuilt();
        privacyBean.setValue(value.toString());
        return this;
    }

    public PrivacyBuilder setFriends(PrivacyType friends) {
        checkNotBuilt();
        privacyBean.setFriends(friends.toString());
        return this;
    }

    public PrivacyBuilder setNetworks(String networks) {
        checkNotBuilt();
        privacyBean.setNetworks(networks);
        return this;
    }
    public PrivacyBuilder setNetworks(List<String> networks) {
        checkNotBuilt();
        privacyBean.setNetworks(z_F4JInternalStringUtil.join((String[]) networks.toArray(new String[networks.size()])));
        return this;
    }
    public PrivacyBuilder addNetwork(String network) {
        checkNotBuilt();
        String networks = privacyBean.getNetworks();
        if (networks == null) {
            privacyBean.setNetworks(network);
        } else {
            privacyBean.setNetworks(networks + "," + network);
        }
        return this;
    }

    public PrivacyBuilder setAllow(String allow) {
        checkNotBuilt();
        privacyBean.setAllow(allow);
        return this;
    }
    public PrivacyBuilder setAllow(List<String> allow) {
        checkNotBuilt();
        privacyBean.setAllow(z_F4JInternalStringUtil.join((String[]) allow.toArray(new String[allow.size()])));
        return this;
    }
    public PrivacyBuilder addAllow(String allow) {
        checkNotBuilt();
        String allows = privacyBean.getAllow();
        if (allows == null) {
            privacyBean.setAllow(allow);
        } else {
            privacyBean.setAllow(allows + "," + allow);
        }
        return this;
    }

    public PrivacyBuilder setDeny(String deny) {
        checkNotBuilt();
        privacyBean.setDeny(deny);
        return this;
    }
    public PrivacyBuilder setDeny(List<String> deny) {
        checkNotBuilt();
        privacyBean.setDeny(z_F4JInternalStringUtil.join((String[]) deny.toArray(new String[deny.size()])));
        return this;
    }
    public PrivacyBuilder addDeny(String deny) {
        checkNotBuilt();
        String denys = privacyBean.getDeny();
        if (denys == null) {
            privacyBean.setDeny(deny);
        } else {
            privacyBean.setDeny(denys + "," + deny);
        }
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
