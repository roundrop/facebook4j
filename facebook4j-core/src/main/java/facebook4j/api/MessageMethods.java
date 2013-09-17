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

import facebook4j.FacebookException;
import facebook4j.InboxResponseList;
import facebook4j.Message;
import facebook4j.Reading;
import facebook4j.ResponseList;

public interface MessageMethods {
    /**
     * Returns the messages in the current user's inbox.
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - inbox
     */
    InboxResponseList<Message> getInbox() throws FacebookException;

    /**
     * Returns the messages in the current user's inbox.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - inbox
     */
    InboxResponseList<Message> getInbox(Reading reading) throws FacebookException;

    /**
     * Returns the messages in a user's inbox.
     * @param userId the ID of a user
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - inbox
     */
    InboxResponseList<Message> getInbox(String userId) throws FacebookException;

    /**
     * Returns the messages in a user's inbox.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - inbox
     */
    InboxResponseList<Message> getInbox(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the messages in the current user's outbox.
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - outbox
     */
    ResponseList<Message> getOutbox() throws FacebookException;

    /**
     * Returns the messages in the current user's outbox.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - outbox
     */
    ResponseList<Message> getOutbox(Reading reading) throws FacebookException;

    /**
     * Returns the messages in a user's outbox.
     * @param userId the ID of a user
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - outbox
     */
    ResponseList<Message> getOutbox(String userId) throws FacebookException;

    /**
     * Returns the messages in a user's outbox.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - outbox
     */
    ResponseList<Message> getOutbox(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the updates in the current user's inbox.
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - updates
     */
    ResponseList<Message> getUpdates() throws FacebookException;

    /**
     * Returns the updates in the current user's inbox.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - updates
     */
    ResponseList<Message> getUpdates(Reading reading) throws FacebookException;

    /**
     * Returns the updates in a user's inbox.
     * @param userId the ID of a user
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - updates
     */
    ResponseList<Message> getUpdates(String userId) throws FacebookException;

    /**
     * Returns the updates in a user's inbox.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return messages
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - updates
     */
    ResponseList<Message> getUpdates(String userId, Reading reading) throws FacebookException;


    /**
     * Returns a single message.
     * @param messageId the ID of the message
     * @return message
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/message/">Message - Facebook Developers</a>
     */
    Message getMessage(String messageId) throws FacebookException;

    /**
     * Returns a single message.
     * @param messageId the ID of the message
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return message
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/message/">Message - Facebook Developers</a>
     */
    Message getMessage(String messageId, Reading reading) throws FacebookException;
    
}
