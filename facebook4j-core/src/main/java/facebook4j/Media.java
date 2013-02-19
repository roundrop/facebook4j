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

import java.io.File;
import java.io.InputStream;

import facebook4j.internal.http.HttpParameter;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class Media implements java.io.Serializable {
    private static final long serialVersionUID = 3675307580428350068L;

    private File mediaFile;
    private String mediaName;
    private transient InputStream mediaBody;

    public Media(File mediaFile) {
        this.mediaFile = mediaFile;
    }

    public Media(String mediaName, InputStream mediaBody) {
        this.mediaName = mediaName;
        this.mediaBody = mediaBody;
    }

    public File getMediaFile() {
        return mediaFile;
    }

    public String getMediaName() {
        return mediaName;
    }

    public InputStream getMediaBody() {
        return mediaBody;
    }
    
    public HttpParameter asHttpParameter(String parameterName) {
        if (mediaFile != null) {
            return new HttpParameter(parameterName, mediaFile);
        }
        return new HttpParameter(parameterName, mediaName, mediaBody);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((mediaBody == null) ? 0 : mediaBody.hashCode());
        result = prime * result
                + ((mediaFile == null) ? 0 : mediaFile.hashCode());
        result = prime * result
                + ((mediaName == null) ? 0 : mediaName.hashCode());
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
        Media other = (Media) obj;
        if (mediaBody == null) {
            if (other.mediaBody != null)
                return false;
        } else if (!mediaBody.equals(other.mediaBody))
            return false;
        if (mediaFile == null) {
            if (other.mediaFile != null)
                return false;
        } else if (!mediaFile.equals(other.mediaFile))
            return false;
        if (mediaName == null) {
            if (other.mediaName != null)
                return false;
        } else if (!mediaName.equals(other.mediaName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Media [mediaFile=" + mediaFile + ", mediaName=" + mediaName
                + ", mediaBody=" + mediaBody + "]";
    }
    
}
