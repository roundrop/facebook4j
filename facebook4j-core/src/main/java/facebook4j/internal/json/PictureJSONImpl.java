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

package facebook4j.internal.json;

import java.net.URL;

import facebook4j.FacebookException;
import facebook4j.Picture;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.internal.util.z_F4JInternalParseUtil;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class PictureJSONImpl implements Picture, java.io.Serializable {
    private static final long serialVersionUID = 3087810796439324798L;

    private final URL url;
    private final Boolean isSilhouette;

    /*package*/PictureJSONImpl(JSONObject json) throws FacebookException {
        try {
            JSONObject pictureJSONObject = json.getJSONObject("data");
            url = z_F4JInternalParseUtil.getURL("url", pictureJSONObject);
            isSilhouette = z_F4JInternalParseUtil.getBoolean("is_silhouette", pictureJSONObject);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }
    /*package*/PictureJSONImpl(URL url) {
        this.url = url;
        isSilhouette = true;
    }

    public URL getURL() {
        return url;
    }

    public Boolean isSilhouette() {
        return isSilhouette;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((isSilhouette == null) ? 0 : isSilhouette.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        PictureJSONImpl other = (PictureJSONImpl) obj;
        if (isSilhouette == null) {
            if (other.isSilhouette != null)
                return false;
        } else if (!isSilhouette.equals(other.isSilhouette))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PictureJSONImpl [url=" + url + ", isSilhouette=" + isSilhouette
                + "]";
    }

}
