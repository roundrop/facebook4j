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

import facebook4j.Conversation;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.InboxResponseList;
import facebook4j.Message;
import facebook4j.PagableList;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Oli Schmid
 */
/*package*/ final class ConversationJSONImpl extends FacebookResponseImpl implements Conversation, java.io.Serializable {
    private static final long serialVersionUID = -2666008917993827020L;

    private String id;
    private List<IdNameEntity> senders;
    private String conversation;
    private Date updatedTime;
    private PagableList<Message> messages;
    private Integer unread_count;
    private Integer message_count;

    /*package*/ConversationJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/ConversationJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("senders")) {
                JSONObject toJSONObject = json.getJSONObject("senders");
                JSONArray toJSONArray = toJSONObject.getJSONArray("data");
                senders = new ArrayList<IdNameEntity>();
                for (int i = 0; i < toJSONArray.length(); i++) {
                    senders.add(new IdNameEntityJSONImpl(toJSONArray.getJSONObject(i)));
                }
            } else {
                senders = Collections.emptyList();
            }
            conversation = getRawString("snippet", json);
            updatedTime = getISO8601Datetime("updated_time", json);

            if (!json.isNull("messages")) {
                JSONObject messagesJSONObject = json.getJSONObject("messages");
                if (!messagesJSONObject.isNull("data")) {
                    JSONArray list = messagesJSONObject.getJSONArray("data");
                    final int size = list.length();
                    messages = new PagableListImpl<Message>(size, messagesJSONObject);
                    for (int i = 0; i < size; i++) {
                        MessageJSONImpl message = new MessageJSONImpl(list.getJSONObject(i));
                        messages.add(message);
                    }
                }
                else {
                    messages = new PagableListImpl<Message>(1, messagesJSONObject);
                }
            }
            if (!json.isNull("unread_count")) {
                unread_count = getPrimitiveInt("unread_count", json);
            }
            if (!json.isNull("message_count")) {
                message_count = getPrimitiveInt("message_count", json);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }
    public List<IdNameEntity> getSenders() {
        return senders;
    }
    public String getConversation() {
        return conversation;
    }
    public Date getUpdatedTime() {
        return updatedTime;
    }
    public PagableList<Message> getMessages() {
        return messages;
    }
    public Integer getUnreadCount() {
        return unread_count;
    }

    public Integer getMessageCount() {
        return message_count;
    }



    /*package*/
    static ResponseList<Conversation> createConversationList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Conversation> conversations = new ResponseListImpl<Conversation>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject conversationJSONObject = list.getJSONObject(i);
                Conversation conversation = new ConversationJSONImpl(conversationJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(conversation, conversationJSONObject);
                }
                conversations.add(conversation);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(conversations, list);
            }
            return conversations;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    /*package*/
    static InboxResponseList<Conversation> createInboxConversationList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            InboxResponseList<Conversation> conversations = new InboxResponseListImpl<Conversation>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject conversationJSONObject = list.getJSONObject(i);
                Conversation conversation = new ConversationJSONImpl(conversationJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(conversation, conversationJSONObject);
                }
                conversations.add(conversation);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(conversations, list);
            }
            return conversations;
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
        ConversationJSONImpl other = (ConversationJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MessageJSONImpl{" +
                "id='" + id + '\'' +
                ", senders=" + senders +
                ", conversation='" + conversation + '\'' +
                ", updatedTime=" + updatedTime +
                ", messages=" + messages +
                ", unread_count=" + unread_count +
                ", message_count=" + message_count +
                '}';
    }
}
