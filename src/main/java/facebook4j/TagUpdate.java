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

import java.util.ArrayList;
import java.util.List;

import facebook4j.internal.http.HttpParameter;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class TagUpdate {
    private String tagText;
    private Integer x;
    private Integer y;

    public TagUpdate() {
    }
    public TagUpdate(String tagText) {
        this.tagText = tagText;
    }
    public TagUpdate(String tagText, Integer x, Integer y) {
        this.tagText = tagText;
        this.x = x;
        this.y = y;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        if (tagText != null) {
            params.add(new HttpParameter("tag_text", tagText));
        }
        if (x != null) {
            params.add(new HttpParameter("x", x));
        }
        if (y != null) {
            params.add(new HttpParameter("y", y));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

}
