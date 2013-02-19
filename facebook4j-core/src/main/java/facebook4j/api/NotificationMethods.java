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
import facebook4j.Notification;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface NotificationMethods {
    /**
     * Returns the notifications for the current user.
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications() throws FacebookException;

    /**
     * Returns the notifications for the current user.
     * @param includeRead enables you to see notifications that the user has already read in addition to the ones which are unread
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications(boolean includeRead) throws FacebookException;

    /**
     * Returns the notifications for the current user.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications(Reading reading) throws FacebookException;

    /**
     * Returns the notifications for the current user.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @param includeRead enables you to see notifications that the user has already read in addition to the ones which are unread
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications(Reading reading, boolean includeRead) throws FacebookException;

    /**
     * Returns the notifications for a user.
     * @param userId the ID of a user
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications(String userId) throws FacebookException;

    /**
     * Returns the notifications for a user.
     * @param userId the ID of a user
     * @param includeRead enables you to see notifications that the user has already read in addition to the ones which are unread
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications(String userId, boolean includeRead) throws FacebookException;

    /**
     * Returns the notifications for a user.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications(String userId, Reading reading) throws FacebookException;

    /**
     * Returns the notifications for a user.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @param includeRead enables you to see notifications that the user has already read in addition to the ones which are unread
     * @return notifications
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    ResponseList<Notification> getNotifications(String userId, Reading reading, boolean includeRead) throws FacebookException;
    

    /**
     * Marks the notification as read.
     * @param notificationId the ID of the notification
     * @return true if mark as read is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notifications">User#notifications - Facebook Developers</a>
     */
    boolean markNotificationAsRead(String notificationId) throws FacebookException;
    
}
