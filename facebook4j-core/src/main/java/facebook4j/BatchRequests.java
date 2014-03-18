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
import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @since Facebook4J 2.1.0
 */
public class BatchRequests<T extends BatchRequest> extends ArrayList<T> {
    private static final long serialVersionUID = -9123525320485721265L;

    private Boolean includeHeaders;

    public Boolean getIncludeHeaders() {
        return includeHeaders;
    }

    public void setIncludeHeaders(Boolean includeHeaders) {
        this.includeHeaders = includeHeaders;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();

        if (includeHeaders != null) {
            params.add(new HttpParameter("include_headers", includeHeaders));
        }

        List<String> jsons = new ArrayList<String>();
        for (int i = 0; i < size(); i++) {
            T batch = get(i);
            jsons.add(batch.asJSONString());
            if (batch.getAttachedFiles() != null) {
                for (BatchAttachment attachedFile : batch.getAttachedFiles()) {
                    params.add(attachedFile.asHttpParameter());
                }
            }
        }
        String batchJSONString = "[" + z_F4JInternalStringUtil.join((String[]) jsons.toArray(new String[jsons.size()])) + "]";
        params.add(new HttpParameter("batch", batchJSONString));

        return params.toArray(new HttpParameter[params.size()]);
    }
}
