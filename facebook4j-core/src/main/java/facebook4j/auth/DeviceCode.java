/*
 * Copyright 2007 Yusuke Yamamoto
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

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.3.3
 */
public class DeviceCode implements java.io.Serializable {
    private static final long serialVersionUID = -1050681649334378859L;

    private final String code;
    private final String userCode;
    private final String verificationUri;
    private final Integer expiresIn;
    private final Integer interval;

    public DeviceCode(HttpResponse res) throws FacebookException {
        JSONObject json = res.asJSONObject();
        code = getRawString("code", json);
        userCode = getRawString("user_code", json);
        verificationUri = getRawString("verification_uri", json);
        expiresIn = getInt("expires_in", json);
        interval = getInt("interval", json);
    }

    public String getCode() {
        return code;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getVerificationUri() {
        return verificationUri;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public Integer getInterval() {
        return interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceCode)) return false;

        DeviceCode that = (DeviceCode) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (expiresIn != null ? !expiresIn.equals(that.expiresIn) : that.expiresIn != null) return false;
        if (interval != null ? !interval.equals(that.interval) : that.interval != null) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;
        if (verificationUri != null ? !verificationUri.equals(that.verificationUri) : that.verificationUri != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (verificationUri != null ? verificationUri.hashCode() : 0);
        result = 31 * result + (expiresIn != null ? expiresIn.hashCode() : 0);
        result = 31 * result + (interval != null ? interval.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceCode{" +
                "code='" + code + '\'' +
                ", userCode='" + userCode + '\'' +
                ", verificationUri='" + verificationUri + '\'' +
                ", expiresIn=" + expiresIn +
                ", interval=" + interval +
                '}';
    }
}
