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

import facebook4j.internal.http.RequestMethod;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since Facebook4J 2.1.0
 */
public final class BatchRequest implements java.io.Serializable {
    private static final long serialVersionUID = -7720882981733337599L;

    private final RequestMethod method;
    private final String relativeUrl;
    private String name;
    private String body;
    private Boolean omitResponseOnSuccess;
    private String dependsOn;
    private String accessToken;
    private List<BatchAttachment> attachedFiles;

    public BatchRequest(RequestMethod method, String relativeUrl) {
        this.method = method;
        this.relativeUrl = relativeUrl;
    }

    public String getMethod() {
        return method.name();
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BatchRequest name(String name) {
        setName(name);
        return this;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BatchRequest body(String body) {
        setBody(body);
        return this;
    }

    public Boolean isOmitResponseOnSuccess() {
        return omitResponseOnSuccess;
    }

    public void setOmitResponseOnSuccess(Boolean omitResponseOnSuccess) {
        this.omitResponseOnSuccess = omitResponseOnSuccess;
    }

    public BatchRequest omitResponseOnSuccess(Boolean omitResponseOnSuccess) {
        setOmitResponseOnSuccess(omitResponseOnSuccess);
        return this;
    }

    public String getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String dependsOn) {
        this.dependsOn = dependsOn;
    }

    public BatchRequest dependsOn(String dependsOn) {
        setDependsOn(dependsOn);
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public BatchRequest accessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

    public List<BatchAttachment> getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(List<BatchAttachment> attachedFiles) {
        this.attachedFiles = attachedFiles;
    }

    public BatchRequest attachedFiles(List<BatchAttachment> attachedFiles) {
        setAttachedFiles(attachedFiles);
        return this;
    }

    public void addAttachedFile(BatchAttachment attachedFile) {
        if (this.attachedFiles == null) {
            this.attachedFiles = new ArrayList<BatchAttachment>();
        }
        this.attachedFiles.add(attachedFile);
    }

    public BatchRequest attachedFile(BatchAttachment attachedFile) {
        addAttachedFile(attachedFile);
        return this;
    }

    private JSONObject json = null;

    public JSONObject asJSONObject() {
        if (json == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("method", getMethod());
            map.put("relative_url", getRelativeUrl());
            map.put("name", getName());
            map.put("body", getBody());
            map.put("omit_response_on_success", isOmitResponseOnSuccess());
            map.put("depends_on", getDependsOn());
            map.put("access_token", getAccessToken());
            if (attachedFiles != null && !attachedFiles.isEmpty()) {
                List<String> filenames = new ArrayList<String>(attachedFiles.size());
                for (BatchAttachment attachedFile : attachedFiles) {
                    filenames.add(attachedFile.getName());
                }
                map.put("attached_files", z_F4JInternalStringUtil.join(filenames.toArray(new String[filenames.size()]), ","));
            }
            json = new JSONObject(map);
        }
        return json;
    }

    public String asJSONString() {
        return asJSONObject().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BatchRequest)) return false;

        BatchRequest that = (BatchRequest) o;

        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (attachedFiles != null ? !attachedFiles.equals(that.attachedFiles) : that.attachedFiles != null)
            return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (dependsOn != null ? !dependsOn.equals(that.dependsOn) : that.dependsOn != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (omitResponseOnSuccess != null ? !omitResponseOnSuccess.equals(that.omitResponseOnSuccess) : that.omitResponseOnSuccess != null)
            return false;
        if (relativeUrl != null ? !relativeUrl.equals(that.relativeUrl) : that.relativeUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (relativeUrl != null ? relativeUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (omitResponseOnSuccess != null ? omitResponseOnSuccess.hashCode() : 0);
        result = 31 * result + (dependsOn != null ? dependsOn.hashCode() : 0);
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (attachedFiles != null ? attachedFiles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BatchRequest{" +
                "method=" + method.name() +
                ", relativeUrl='" + relativeUrl + '\'' +
                ", name='" + name + '\'' +
                ", body=" + body +
                ", omitResponseOnSuccess=" + omitResponseOnSuccess +
                ", dependsOn='" + dependsOn + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", attachedFiles='" + attachedFiles + '\'' +
                '}';
    }
}
