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

import facebook4j.Image;
import facebook4j.internal.org.json.JSONObject;

import java.net.URL;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class ImageJSONImpl implements Image, java.io.Serializable {
    private static final long serialVersionUID = -61132555094355975L;

    private Integer height;
    private Integer width;
    private URL source;

    /*package*/ImageJSONImpl(JSONObject json) {
        if (!json.isNull("height")) {
            height = getPrimitiveInt("height", json);
        }
        if (!json.isNull("width")) {
            width = getPrimitiveInt("width", json);
        }
        source = getURL("source", json);
        if (source == null) {
            source = getURL("src", json);
        }
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public URL getSource() {
        return source;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((height == null) ? 0 : height.hashCode());
        result = prime * result
                + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((width == null) ? 0 : width.hashCode());
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
        ImageJSONImpl other = (ImageJSONImpl) obj;
        if (height == null) {
            if (other.height != null)
                return false;
        } else if (!height.equals(other.height))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (width == null) {
            if (other.width != null)
                return false;
        } else if (!width.equals(other.width))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ImageJSONImpl [height=" + height + ", width=" + width
                + ", source=" + source + "]";
    }

}
