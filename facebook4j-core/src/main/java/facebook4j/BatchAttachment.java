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

/**
 * @since Facebook4J 2.1.0
 */
public class BatchAttachment implements java.io.Serializable {
    private static final long serialVersionUID = -1608928794348894506L;

    private final String name;
    private final Media data;

    public BatchAttachment(String name, Media data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public Media getData() {
        return data;
    }

    public HttpParameter asHttpParameter() {
        return data.asHttpParameter(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BatchAttachment)) return false;

        BatchAttachment that = (BatchAttachment) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BatchAttachment{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
