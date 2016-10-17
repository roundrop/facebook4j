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

package facebook4j.api;

import facebook4j.Conversation;
import facebook4j.FacebookException;
import facebook4j.InboxResponseList;
import facebook4j.Reading;

public interface ConversationMethods {

    /**
     * Returns the conversations of the current page.
     *
     * @return conversations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/v2.7/page/conversations">Page / Conversations - Facebook Developers</a>
     */
    InboxResponseList<Conversation> getConversations() throws FacebookException;

    /**
     * Returns the conversations of the current page.
     *
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return conversations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/v2.7/page/conversations">Page / Conversations - Facebook Developers</a>
     */
    InboxResponseList<Conversation> getConversations(Reading reading) throws FacebookException;

    /**
     * Returns the conversations of a page.
     *
     * @param pageId the ID of a page
     * @return conversations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/v2.7/page/conversations">Page / Conversations - Facebook Developers</a>
     */
    InboxResponseList<Conversation> getConversations(String pageId) throws FacebookException;

    /**
     * Returns the conversations of a page.
     *
     * @param pageId the ID of a page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return conversations
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/v2.7/page/conversations">Page / Conversations - Facebook Developers</a>
     */
    InboxResponseList<Conversation> getConversations(String pageId, Reading reading) throws FacebookException;


    /**
     * Returns a single conversation.
     *
     * @param conversationId the ID of the conversation
     * @return conversation
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/v2.7/conversation/">Conversation - Facebook Developers</a>
     */
    Conversation getConversation(String conversationId) throws FacebookException;

    /**
     * Returns a single conversation.
     *
     * @param conversationId the ID of the conversation
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return conversation
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/v2.7/conversation/">Conversation - Facebook Developers</a>
     */
    Conversation getConversation(String conversationId, Reading reading) throws FacebookException;

    /**
     * Answer conversation.
     *
     * @param conversationId the ID of the conversation
     * @param conversation comment text
     * @return The new conversation ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/graph-api/reference/v2.7/conversation/">Conversation - Facebook Developers</a>
     */
    String answerConversation(String conversationId, String conversation) throws FacebookException;

}
