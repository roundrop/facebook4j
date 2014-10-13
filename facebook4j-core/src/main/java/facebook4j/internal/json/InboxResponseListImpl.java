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
import facebook4j.InboxResponseList;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

public class InboxResponseListImpl<T> extends ResponseListImpl<T> implements InboxResponseList<T> {
    private static final long serialVersionUID = -392100352680662139L;
    
    private InboxSummary inboxSummary;

    /*package*/InboxResponseListImpl(JSONObject json, T... t) throws FacebookException {
        super(json, t);
        init(json);
    }

    /*package*/InboxResponseListImpl(int size, JSONObject json, T... t) throws FacebookException {
        super(size, json, t);
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            if (!json.isNull("summary")) {
                JSONObject summaryJSONObject = json.getJSONObject("summary");
                inboxSummary = new InboxSummaryJSONImpl(summaryJSONObject);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public InboxSummary getInboxSummary() {
        return inboxSummary;
    }
    
    @Override
    public String toString() {
        return "InboxResponseListImpl [summary=" + inboxSummary + "]";
    }



    private class InboxSummaryJSONImpl implements InboxSummary, java.io.Serializable {
        private static final long serialVersionUID = 1988071486977638655L;

        private Integer unseenCount;
        private Integer unreadCount;
        private Date updatedTime;

        public InboxSummaryJSONImpl(JSONObject json) throws FacebookException {
            if (!json.isNull("unseen_count")) {
                unseenCount = getPrimitiveInt("unseen_count", json);
            }
            if (!json.isNull("unread_count")) {
                unreadCount = getPrimitiveInt("unread_count", json);
            }
            updatedTime = getISO8601Datetime("updated_time", json);
        }

        public Integer getUnseenCount() {
            return unseenCount;
        }

        public Integer getUnreadCount() {
            return unreadCount;
        }

        public Date getUpdatedTime() {
            return updatedTime;
        }

        @Override
        public String toString() {
            return "SummaryJSONImpl [unseenCount=" + unseenCount
                    + ", unreadCount=" + unreadCount + ", updatedTime="
                    + updatedTime + "]";
        }
        
    }
}
