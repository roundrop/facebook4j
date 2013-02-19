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
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Subscribedto;
import facebook4j.Subscriber;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface SubscribeMethods {
    /**
     * Returns people the current user is subscribed to.
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribedto">User#subscribedto - Facebook Developers</a>
     */
    ResponseList<Subscribedto> getSubscribedto() throws FacebookException;

    /**
     * Returns people the current user is subscribed to.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribedto">User#subscribedto - Facebook Developers</a>
     */
    ResponseList<Subscribedto> getSubscribedto(Reading reading) throws FacebookException;

    /**
     * Returns people a user is subscribed to.
     * @param userId the ID of a user
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribedto">User#subscribedto - Facebook Developers</a>
     */
    ResponseList<Subscribedto> getSubscribedto(String userId) throws FacebookException;

    /**
     * Returns people a user is subscribed to.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribedto">User#subscribedto - Facebook Developers</a>
     */
    ResponseList<Subscribedto> getSubscribedto(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the current user's subscribers.
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribers">User#subscribers - Facebook Developers</a>
     */
    ResponseList<Subscriber> getSubscribers() throws FacebookException;

    /**
     * Returns the current user's subscribers.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribers">User#subscribers - Facebook Developers</a>
     */
    ResponseList<Subscriber> getSubscribers(Reading reading) throws FacebookException;

    /**
     * Returns a user's subscribers.
     * @param userId the ID of a user
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribers">User#subscribers - Facebook Developers</a>
     */
    ResponseList<Subscriber> getSubscribers(String userId) throws FacebookException;

    /**
     * Returns a user's subscribers.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return users
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#subscribers">User#subscribers - Facebook Developers</a>
     */
    ResponseList<Subscriber> getSubscribers(String userId, Reading reading) throws FacebookException;

}
