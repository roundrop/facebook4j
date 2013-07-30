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

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ActivitiesMethodsTest {

    public static class getActivities extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/activity/activities.json");
            ResponseList<Activity> actuals = facebook.getActivities();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/activities")));

            assertThat(actuals.size(), is(2));
            Activity actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111111111111111"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Aaaaaaaa"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-17T14:20:00+0000")));
            Activity actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("222222222222222"));
            assertThat(actual2.getCategory(), is("Interest"));
            assertThat(actual2.getName(), is("Bbbbbbbb"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-04-13T12:09:11+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/activity/activities_name.json");
            ResponseList<Activity> actuals = facebook.getActivities(new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/activities")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));

            assertThat(actuals.size(), is(2));
            Activity actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111111111111111"));
            assertThat(actual1.getCategory(), is(nullValue()));
            assertThat(actual1.getName(), is("Aaaaaaaa"));
            assertThat(actual1.getCreatedTime(), is(nullValue()));
            Activity actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("222222222222222"));
            assertThat(actual2.getCategory(), is(nullValue()));
            assertThat(actual2.getName(), is("Bbbbbbbb"));
            assertThat(actual2.getCreatedTime(), is(nullValue()));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/activity/activities.json");
            ResponseList<Activity> actuals = facebook.getActivities("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/activities")));

            assertThat(actuals.size(), is(2));
            Activity actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111111111111111"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Aaaaaaaa"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-17T14:20:00+0000")));
            Activity actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("222222222222222"));
            assertThat(actual2.getCategory(), is("Interest"));
            assertThat(actual2.getName(), is("Bbbbbbbb"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2012-04-13T12:09:11+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/activity/activities_name.json");
            ResponseList<Activity> actuals = facebook.getActivities("1234567890123456", new Reading().fields("name"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/activities")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "name"));


            assertThat(actuals.size(), is(2));
            Activity actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("111111111111111"));
            assertThat(actual1.getCategory(), is(nullValue()));
            assertThat(actual1.getName(), is("Aaaaaaaa"));
            assertThat(actual1.getCreatedTime(), is(nullValue()));
            Activity actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("222222222222222"));
            assertThat(actual2.getCategory(), is(nullValue()));
            assertThat(actual2.getName(), is("Bbbbbbbb"));
            assertThat(actual2.getCreatedTime(), is(nullValue()));
        }

    }

}
