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

import facebook4j.FacebookException;
import facebook4j.Summary;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.2.0
 */
public class SummaryJSONImpl extends FacebookResponseImpl implements Summary, java.io.Serializable {
    private static final long serialVersionUID = 1378907332444378409L;

    private final SummaryOrder order;
    private Integer totalCount;

    /*package*/SummaryJSONImpl(JSONObject json) throws FacebookException {
        super();
        if (json.isNull("order")) {
            order = null;
        } else {
            order = SummaryOrder.getInstance(getRawString("order", json));
        }

        if (json.isNull("total_count")) {
            totalCount = null;
        } else {
            totalCount = getInt("total_count", json);
        }
    }

    public SummaryOrder getOrder() {
        return order;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SummaryJSONImpl)) return false;

        SummaryJSONImpl that = (SummaryJSONImpl) o;

        if (order != that.order) return false;
        if (totalCount != null ? !totalCount.equals(that.totalCount) : that.totalCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = order != null ? order.hashCode() : 0;
        result = 31 * result + (totalCount != null ? totalCount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SummaryJSONImpl{" +
                "order=" + order +
                ", totalCount=" + totalCount +
                "} ";
    }
}
