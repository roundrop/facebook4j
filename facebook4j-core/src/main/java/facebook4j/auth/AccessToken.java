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

import facebook4j.FacebookException;
import facebook4j.internal.http.HttpResponse;

/**
 * Represents an access token (with expiration date).
 *
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class AccessToken implements java.io.Serializable {
    private static final long serialVersionUID = -1373191811934285453L;

    private String token;
    private Long expires;
    String[] responseStr = null;

    public AccessToken(HttpResponse res) throws FacebookException {
        this(res.asString());
    }

    public AccessToken(String string) {
        if (string.contains("access_token=")) {
            this.responseStr = string.split("&");
            this.token = getParameter("access_token");
            if (this.responseStr.length > 1) {
                this.expires = Long.valueOf(getParameter("expires"));
            }
        } else {
            this.token = string;
            this.expires = null;
        }
    }

    public AccessToken(String token, Long expires) {
        this.token = token;
        this.expires = expires;
    }
    
    public String getParameter(String parameter) {
        String value = null;
        for (String str : responseStr) {
            if (str.startsWith(parameter + '=')) {
                value = str.split("=")[1].trim();
                break;
            }
        }
        return value;
    }

    public String getToken() {
        return token;
    }

    public Long getExpires() {
        return expires;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AccessToken other = (AccessToken) obj;
        if (expires == null) {
            if (other.expires != null)
                return false;
        } else if (!expires.equals(other.expires))
            return false;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expires == null) ? 0 : expires.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "AccessToken [token=" + token + ", expires=" + expires + "]";
    }

}
