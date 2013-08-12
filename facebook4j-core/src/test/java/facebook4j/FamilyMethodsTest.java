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
public class FamilyMethodsTest {

    public static class getFamily extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/family/default.json");
            ResponseList<Family> actuals = facebook.getFamily();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/family")));

            assertThat(actuals.size(), is(2));
            Family actual1 = actuals.get(0);
            assertThat(actual1.getRelationship(), is("brother"));
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Family Name1"));
            Family actual2 = actuals.get(1);
            assertThat(actual2.getRelationship(), is("brother"));
            assertThat(actual2.getId(), is("100000000000002"));
            assertThat(actual2.getName(), is("Family Name2"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/family/more_fields.json");
            ResponseList<Family> actuals = facebook.getFamily(new Reading().fields("first_name,last_name,birthday,education"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/family")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "first_name,last_name,birthday,education"));

            assertThat(actuals.size(), is(2));
            Family actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getFirstName(), is("Name1"));
            assertThat(actual1.getLastName(), is("Family"));
            Family actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000000002"));
            assertThat(actual2.getFirstName(), is("Name2"));
            assertThat(actual2.getBirthday(), is("08/21/1981"));
            assertThat(actual2.getLastName(), is("Family"));
            assertThat(actual2.getEducation().get(0).getSchool().getId(), is("111111111111111"));
            assertThat(actual2.getEducation().get(0).getSchool().getName(), is("School Name"));
            assertThat(actual2.getEducation().get(0).getType(), is("High School"));
            assertThat(actual2.getEducation().get(0).getYear().getId(), is("22222222222222"));
            assertThat(actual2.getEducation().get(0).getYear().getName(), is("1998"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/family/default.json");
            ResponseList<Family> actuals = facebook.getFamily("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/family")));

            assertThat(actuals.size(), is(2));
            Family actual1 = actuals.get(0);
            assertThat(actual1.getRelationship(), is("brother"));
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getName(), is("Family Name1"));
            Family actual2 = actuals.get(1);
            assertThat(actual2.getRelationship(), is("brother"));
            assertThat(actual2.getId(), is("100000000000002"));
            assertThat(actual2.getName(), is("Family Name2"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/family/more_fields.json");
            ResponseList<Family> actuals = facebook.getFamily("1234567890123456", new Reading().fields("first_name,last_name,birthday,education"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/family")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "first_name,last_name,birthday,education"));

            assertThat(actuals.size(), is(2));
            Family actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("100000000000001"));
            assertThat(actual1.getFirstName(), is("Name1"));
            assertThat(actual1.getLastName(), is("Family"));
            Family actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("100000000000002"));
            assertThat(actual2.getFirstName(), is("Name2"));
            assertThat(actual2.getBirthday(), is("08/21/1981"));
            assertThat(actual2.getLastName(), is("Family"));
            assertThat(actual2.getEducation().get(0).getSchool().getId(), is("111111111111111"));
            assertThat(actual2.getEducation().get(0).getSchool().getName(), is("School Name"));
            assertThat(actual2.getEducation().get(0).getType(), is("High School"));
            assertThat(actual2.getEducation().get(0).getYear().getId(), is("22222222222222"));
            assertThat(actual2.getEducation().get(0).getYear().getName(), is("1998"));
        }

    }

}
