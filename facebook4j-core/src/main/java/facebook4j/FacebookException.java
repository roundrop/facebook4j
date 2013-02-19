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

import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * An exception class that will be thrown when Facebook Graph API calls are failed.<br>
 * In case the Facebook server returned HTTP error code, you can get the HTTP status code using getStatusCode() method.
 *
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class FacebookException extends Exception {
    private static final long serialVersionUID = 8394133786720654641L;
    
    private int statusCode = -1;
    private HttpResponse response;
    private String errorType;
    private String errorMessage;
    private int errorCode = -1;

    public FacebookException(String message, Throwable cause) {
        super(message, cause);
        decode(message);
    }

    public FacebookException(String message, HttpResponse res) {
        this(message);
        this.response = res;
        this.statusCode = res.getStatusCode();
    }

    public FacebookException(String message) {
        this(message, (Throwable) null);
    }

    public FacebookException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public FacebookException(String message, Throwable cause, int statusCode) {
        this(message, cause);
        this.statusCode = statusCode;
    }

    private void decode(String str) {
        if (str != null && str.startsWith("{")) {
            try {
                JSONObject json = new JSONObject(str);
                if (!json.isNull("error")) {
                    JSONObject error = json.getJSONObject("error");
                    this.errorType = error.getString("type");
                    this.errorMessage = error.getString("message");
                    this.errorCode = error.getInt("code");
                }
            } catch (JSONException ignore) {
            }
        }
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public int getErrorCode() {
        return errorCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseHeader(String name) {
        String value = null;
        if (response != null) {
            List<String> header = response.getResponseHeaderFields().get(name);
            if (header.size() > 0) {
                value = header.get(0);
            }
        }
        return value;
    }

    /**
     * Tests if the exception is caused by network issue
     *
     * @return if the exception is caused by network issue
     */
    public boolean isCausedByNetworkIssue() {
        return getCause() instanceof java.io.IOException;
    }

    /**
     * Tests if the exception is caused by non-existing resource
     *
     * @return if the exception is caused by non-existing resource
     */
    public boolean resourceNotFound() {
        return statusCode == 404;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + errorCode;
        result = prime * result
                + ((errorMessage == null) ? 0 : errorMessage.hashCode());
        result = prime * result
                + ((errorType == null) ? 0 : errorType.hashCode());
        result = prime * result
                + ((response == null) ? 0 : response.hashCode());
        result = prime * result + statusCode;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FacebookException other = (FacebookException) obj;
        if (errorCode != other.errorCode)
            return false;
        if (errorMessage == null) {
            if (other.errorMessage != null)
                return false;
        } else if (!errorMessage.equals(other.errorMessage))
            return false;
        if (errorType == null) {
            if (other.errorType != null)
                return false;
        } else if (!errorType.equals(other.errorType))
            return false;
        if (response == null) {
            if (other.response != null)
                return false;
        } else if (!response.equals(other.response))
            return false;
        if (statusCode != other.statusCode)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FacebookException [statusCode=" + statusCode + ", response="
                + response + ", errorType=" + errorType + ", errorMessage="
                + errorMessage + ", errorCode=" + errorCode + "]";
    }

}
