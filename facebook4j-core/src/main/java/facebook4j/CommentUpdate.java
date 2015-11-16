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

import facebook4j.internal.http.HttpParameter;

import java.util.ArrayList;
import java.util.List;

public final class CommentUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -2155334084962409798L;

    private String message;
    private String attachmentId;
    private String attachmentUrl;
    private Media source;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommentUpdate message(String message) {
        setMessage(message);
        return this;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public CommentUpdate attachmentId(String attachmentId) {
        setAttachmentId(attachmentId);
        return this;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public CommentUpdate attachmentUrl(String attachmentUrl) {
        setAttachmentUrl(attachmentUrl);
        return this;
    }

    public Media getSource() {
        return source;
    }

    public void setSource(Media source) {
        this.source = source;
    }

    public CommentUpdate source(Media source) {
        setSource(source);
        return this;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        if (message != null) {
            params.add(new HttpParameter("message", message));
        }
        if (attachmentId != null) {
            params.add(new HttpParameter("attachment_id", attachmentId));
        }
        if (attachmentUrl != null) {
            params.add(new HttpParameter("attachment_url", attachmentUrl));
        }
        if (source != null) {
            params.add(source.asHttpParameter("source"));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentUpdate)) return false;

        CommentUpdate that = (CommentUpdate) o;

        if (attachmentId != null ? !attachmentId.equals(that.attachmentId) : that.attachmentId != null) return false;
        if (attachmentUrl != null ? !attachmentUrl.equals(that.attachmentUrl) : that.attachmentUrl != null)
            return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (attachmentId != null ? attachmentId.hashCode() : 0);
        result = 31 * result + (attachmentUrl != null ? attachmentUrl.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentUpdate{" +
                "message='" + message + '\'' +
                ", attachmentId='" + attachmentId + '\'' +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                ", source=" + source +
                '}';
    }
}
