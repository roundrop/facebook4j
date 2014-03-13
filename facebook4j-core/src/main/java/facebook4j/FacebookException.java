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

import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.List;

/**
 * An exception class that will be thrown when Facebook Graph API calls are failed.<br>
 * In case the Facebook server returned HTTP error code, you can get the HTTP status code using getStatusCode() method.
 *
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class FacebookException extends Exception {
    private static final long serialVersionUID = 1200504652249544235L;

    private int statusCode = -1;
    private HttpResponse response;
    private String errorType;
    private String errorMessage;
    private int errorCode = -1;
    private int errorSubcode = -1;

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

    @Override
    public String getMessage() {
        StringBuilder value = new StringBuilder();
        if (errorMessage != null && errorCode != -1) {
            value.append("message - ").append(errorMessage).append("\n");
            value.append("code - ").append(errorCode).append("\n");
            if (errorSubcode != -1) {
                value.append("subcode - ").append(errorSubcode).append("\n");
            }
            value.append("Relevant information for error recovery can be found on the Facebook Developers Document:\n")
                 .append("\thttps://developers.facebook.com/docs/graph-api/using-graph-api/#errors\n");
        } else {
            value.append(super.getMessage());
        }
        return value.toString();
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
                    this.errorSubcode = error.getInt("error_subcode");
                }
            } catch (JSONException ignore) {
            }
        }
    }

    public int getStatusCode() {
        return this.statusCode;
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

    public int getErrorSubcode() {
        return errorSubcode;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacebookException)) return false;

        FacebookException that = (FacebookException) o;

        if (errorCode != that.errorCode) return false;
        if (errorSubcode != that.errorSubcode) return false;
        if (statusCode != that.statusCode) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        if (errorType != null ? !errorType.equals(that.errorType) : that.errorType != null) return false;
        if (response != null ? !response.equals(that.response) : that.response != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = statusCode;
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (errorType != null ? errorType.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + errorCode;
        result = 31 * result + errorSubcode;
        return result;
    }

    @Override
    public String toString() {
        return getMessage() +
                "\nFacebookException{" +
                "statusCode=" + statusCode +
                ", errorType='" + errorType + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorCode=" + errorCode +
                ", errorSubcode=" + errorSubcode +
                ", version=" + Version.getVersion() +
                '}';
    }
}
