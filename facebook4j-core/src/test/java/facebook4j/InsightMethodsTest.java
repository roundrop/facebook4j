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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class InsightMethodsTest extends MockFacebookTestBase {

    @Test
    public void getInsights() throws Exception {
        facebook.setMockJSON("mock_json/insight/application_active_users.json");
        ResponseList<Insight> insights = facebook.getInsights("2439131959", "application_active_users");

        assertThat(insights.size(), is(3));

        Insight insight1 = insights.get(0);
        assertThat(insight1.getId(), is("2439131959/insights/application_active_users/day"));
        assertThat(insight1.getName(), is("application_active_users"));
        assertThat(insight1.getPeriod(), is("day"));
        assertThat(insight1.getValues().size(), is(3));
        assertThat(insight1.getValues().get(0).getValue().size(), is(1));
        assertThat(insight1.getValues().get(0).getValue().get(), is(2982L));
        assertThat(insight1.getValues().get(1).getValue().get(), is(3506L));
        assertThat(insight1.getValues().get(2).getValue().get(), is(3359L));
        assertThat(insight1.getTitle(), is("Daily Active Users"));
        assertThat(insight1.getDescription(), is("Daily 1 day, 7 day, and 30 day counts of users who have engaged with your app or viewed your app (Unique Users)"));

        Insight insight2 = insights.get(1);
        assertThat(insight2.getId(), is("2439131959/insights/application_active_users/week"));
        assertThat(insight2.getName(), is("application_active_users"));
        assertThat(insight2.getPeriod(), is("week"));
        assertThat(insight2.getValues().size(), is(3));
        assertThat(insight2.getValues().get(0).getValue().get(), is(12916L));
        assertThat(insight2.getValues().get(1).getValue().get(), is(13099L));
        assertThat(insight2.getValues().get(2).getValue().get(), is(12823L));
        assertThat(insight2.getTitle(), is("Weekly Active Users"));
        assertThat(insight2.getDescription(), is("Weekly 1 day, 7 day, and 30 day counts of users who have engaged with your app or viewed your app (Unique Users)"));

        Insight insight3 = insights.get(2);
        assertThat(insight3.getId(), is("2439131959/insights/application_active_users/month"));
        assertThat(insight3.getName(), is("application_active_users"));
        assertThat(insight3.getPeriod(), is("month"));
        assertThat(insight3.getValues().size(), is(3));
        assertThat(insight3.getValues().get(0).getValue().get(), is(42374L));
        assertThat(insight3.getValues().get(1).getValue().get(), is(42541L));
        assertThat(insight3.getValues().get(2).getValue().get(), is(42590L));
        assertThat(insight3.getTitle(), is("Monthly Active Users"));
        assertThat(insight3.getDescription(), is("Monthly 1 day, 7 day, and 30 day counts of users who have engaged with your app or viewed your app (Unique Users)"));

        Paging<Insight> paging = insights.getPaging();
        assertThat(paging.getPrevious().toString(), is("https://graph.facebook.com/2439131959/insights/application_active_users?since=1370152138&until=1370411338"));
        assertThat(paging.getNext().toString(), is("https://graph.facebook.com/2439131959/insights/application_active_users?since=1370670538&until=1370929738"));
    }

}
