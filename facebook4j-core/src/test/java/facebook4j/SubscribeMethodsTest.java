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

import facebook4j.internal.http.RequestMethod;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class SubscribeMethodsTest {

    public static class getSubscribedto extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribedtos.json");
            ResponseList<Subscribedto> actuals = facebook.getSubscribedto();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/subscribedto")));

            assertThat(actuals.size(), is(3));
            Subscribedto actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100003403570846"));
            assertThat(actual1.getName(), is("安倍晋三"));
            Subscribedto actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("2424242424"));
            assertThat(actual3.getName(), is("Bar Name"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribedtos_gender.json");
            ResponseList<Subscribedto> actuals = facebook.getSubscribedto(new Reading().fields("gender").fields("first_name").fields("last_name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/subscribedto")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "gender,first_name,last_name"));

            assertThat(actuals.size(), is(3));
            Subscribedto actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100003403570846"));
            assertThat(actual1.getFirstName(), is("晋三"));
            assertThat(actual1.getLastName(), is("安倍"));
            assertThat(actual1.getGender(), is("male"));
            Subscribedto actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("2424242424"));
            assertThat(actual3.getFirstName(), is("Name"));
            assertThat(actual3.getLastName(), is("Bar"));
            assertThat(actual3.getGender(), is("female"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribedtos.json");
            ResponseList<Subscribedto> actuals = facebook.getSubscribedto("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/subscribedto")));

            assertThat(actuals.size(), is(3));
            Subscribedto actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100003403570846"));
            assertThat(actual1.getName(), is("安倍晋三"));
            Subscribedto actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("2424242424"));
            assertThat(actual3.getName(), is("Bar Name"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribedtos_gender.json");
            ResponseList<Subscribedto> actuals = facebook.getSubscribedto("1234567890123456", new Reading().fields("gender").fields("first_name").fields("last_name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/subscribedto")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "gender,first_name,last_name"));

            assertThat(actuals.size(), is(3));
            Subscribedto actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100003403570846"));
            assertThat(actual1.getFirstName(), is("晋三"));
            assertThat(actual1.getLastName(), is("安倍"));
            assertThat(actual1.getGender(), is("male"));
            Subscribedto actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("2424242424"));
            assertThat(actual3.getFirstName(), is("Name"));
            assertThat(actual3.getLastName(), is("Bar"));
            assertThat(actual3.getGender(), is("female"));
        }
    }

    public static class getSubscribers extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribers.json");
            ResponseList<Subscriber> actuals = facebook.getSubscribers();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/subscribers")));

            assertThat(actuals.size(), is(3));
            Subscriber actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Subscriber Name1"));
            Subscriber actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000000003"));
            assertThat(actual3.getName(), is("Subscriber Name3"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribers_gender.json");
            ResponseList<Subscriber> actuals = facebook.getSubscribers(new Reading().fields("name,gender"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/subscribers")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,gender"));

            assertThat(actuals.size(), is(3));
            Subscriber actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Subscriber Name1"));
            assertThat(actual1.getGender(), is("male"));
            Subscriber actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000000003"));
            assertThat(actual3.getName(), is("Subscriber Name3"));
            assertThat(actual3.getGender(), is("female"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribers.json");
            ResponseList<Subscriber> actuals = facebook.getSubscribers("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/subscribers")));

            assertThat(actuals.size(), is(3));
            Subscriber actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Subscriber Name1"));
            Subscriber actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000000003"));
            assertThat(actual3.getName(), is("Subscriber Name3"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/subscribe/subscribers_gender.json");
            ResponseList<Subscriber> actuals = facebook.getSubscribers("1234567890123456", new Reading().fields("name,gender"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/subscribers")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name,gender"));

            assertThat(actuals.size(), is(3));
            Subscriber actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Subscriber Name1"));
            assertThat(actual1.getGender(), is("male"));
            Subscriber actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("100000000000003"));
            assertThat(actual3.getName(), is("Subscriber Name3"));
            assertThat(actual3.getGender(), is("female"));
        }
    }

}
