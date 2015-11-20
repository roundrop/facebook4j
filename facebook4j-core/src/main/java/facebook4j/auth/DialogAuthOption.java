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

package facebook4j.auth;

import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.4.0
 */
public final class DialogAuthOption implements AuthOption, java.io.Serializable {
    private static final long serialVersionUID = -3742407703218253370L;

    private String state;
    private Display display;
    private AuthType authType;
    private String authNonce;

    public String getQuery(String prefix) {
        List<String> q = new ArrayList<String>();
        if (state != null) {
            q.add("state=" + state);
        }
        if (display != null) {
            q.add("display=" + display.toString().toLowerCase());
        }
        if (authType != null) {
            q.add("auth_type=" + authType.toString().toLowerCase());
        }
        if (authNonce != null) {
            q.add("auth_nonce=" + authNonce);
        }
        return prefix + z_F4JInternalStringUtil.join(q.toArray(new String[q.size()]), "&");
    }

    public DialogAuthOption state(String state) {
        this.state = state;
        return this;
    }

    public DialogAuthOption display(Display display) {
        this.display = display;
        return this;
    }

    public DialogAuthOption authType(AuthType authType) {
        this.authType = authType;
        return this;
    }

    public DialogAuthOption authNonce(String authNonce) {
        this.authNonce = authNonce;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DialogAuthOption)) return false;

        DialogAuthOption that = (DialogAuthOption) o;

        if (authNonce != null ? !authNonce.equals(that.authNonce) : that.authNonce != null) return false;
        if (authType != that.authType) return false;
        if (display != that.display) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (display != null ? display.hashCode() : 0);
        result = 31 * result + (authType != null ? authType.hashCode() : 0);
        result = 31 * result + (authNonce != null ? authNonce.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DialogAuthOption{" +
                "state='" + state + '\'' +
                ", display=" + display +
                ", authType=" + authType +
                ", authNonce='" + authNonce + '\'' +
                '}';
    }
}
