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
import facebook4j.internal.org.json.JSONObject;

import java.util.Arrays;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * Represents an access token (with expiration date).
 *
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class AccessToken implements java.io.Serializable {
    private static final long serialVersionUID = -569157870319118047L;

    private String token;
    private String type;
    private Long expires;
    private AuthType authType;
    private String authNonce;
    String[] responseStr = null;

    public AccessToken(HttpResponse res) throws FacebookException {
        // see: https://developers.facebook.com/docs/graph-api/using-graph-api/v2.3#apiversiondebug
        String version = res.getResponseHeader("facebook-api-version");
        if (Double.parseDouble(version.substring(1)) < 2.3 && !res.asString().startsWith("{")) {
            parseQueryString(res.asString());
            return;
        }
        JSONObject json = res.asJSONObject();
        this.token = getRawString("access_token", json);
        this.type = getRawString("token_type", json);
        this.expires = getLong("expires_in", json);
        this.authType = AuthType.of(getRawString("auth_type", json));
        this.authNonce = getRawString("auth_nonce", json);
    }

    public AccessToken(String string) {
        parseQueryString(string);
    }

    public AccessToken(String token, Long expires) {
        this.token = token;
        this.expires = expires;
    }

    private void parseQueryString(String string) {
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

    public String getType() {
        return type;
    }

    public Long getExpires() {
        return expires;
    }

    public String getAuthNonce() {
        return authNonce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccessToken)) return false;

        AccessToken that = (AccessToken) o;

        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", expires=" + expires +
                ", authType=" + authType +
                ", authNonce=" + authNonce +
                ", responseStr=" + Arrays.toString(responseStr) +
                '}';
    }
}
