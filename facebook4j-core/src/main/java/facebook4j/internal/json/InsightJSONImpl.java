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
import facebook4j.Insight;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class InsightJSONImpl extends FacebookResponseImpl implements Insight, java.io.Serializable {
    private static final long serialVersionUID = 5220288371199505577L;

    private String id;
    private String name;
    private String period;
    private List<Insight.Value> values;
    private String title;
    private String description;
    
    /*package*/InsightJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/InsightJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
        period = getRawString("period", json);
        values = createValueList(json);
        title = getRawString("title", json);
        description = getRawString("description", json);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPeriod() {
        return period;
    }

    public List<Insight.Value> getValues() {
        return values;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    
    /*package*/
    static ResponseList<Insight> createInsightList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Insight> insights = new ResponseListImpl<Insight>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject insightJSONObject = list.getJSONObject(i);
                Insight insight = new InsightJSONImpl(insightJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(insight, insightJSONObject);
                }
                insights.add(insight);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(insights, list);
            }
            return insights;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        InsightJSONImpl other = (InsightJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "InsightJSONImpl [id=" + id + ", name=" + name + ", period="
                + period + ", values=" + values + ", title=" + title
                + ", description=" + description + "]";
    }



    private final class ValueJSONImpl implements Insight.Value, java.io.Serializable {
        private static final long serialVersionUID = 764579592511865193L;

        private Value.Entry value;
        private Date endTime;

        ValueJSONImpl(JSONObject json) throws FacebookException {
            value = new ValueEntryJSONImpl(json);
            endTime = getISO8601Datetime("end_time", json);
        }

        public Value.Entry getValue() {
            return value;
        }

        public Date getEndTime() {
            return endTime;
        }

        @Override
        public String toString() {
            return "ValueJSONImpl [value=" + value + ", endTime=" + endTime + "]";
        }

        private final class ValueEntryJSONImpl implements Value.Entry, java.io.Serializable {
            private Map<String, Long> value;

            ValueEntryJSONImpl(JSONObject json) throws FacebookException {
                String valueRawString = getRawString("value", json);
                if (valueRawString.startsWith("{")) {
                    value = getLongMap("value", json);
                } else {
                    value = new HashMap<String, Long>();
                    value.put("", getLong("value", json));
                }
            }

            public Long get() {
                return value.values().iterator().next();
            }

            public Long get(String key) {
                return value.get(key);
            }

            public Iterator<String> keys() {
                return value.keySet().iterator();
            }

            public int size() {
                return value.size();
            }
        }
    }

    private List<Insight.Value> createValueList(JSONObject json) throws FacebookException {
        try {
            JSONArray list = json.getJSONArray("values");
            final int size = list.length();
            List<Insight.Value> values = new ArrayList<Insight.Value>(size);
            for (int i = 0; i < size; i++) {
                Insight.Value value = new ValueJSONImpl(list.getJSONObject(i));
                values.add(value);
            }
            return values;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }
    
}
