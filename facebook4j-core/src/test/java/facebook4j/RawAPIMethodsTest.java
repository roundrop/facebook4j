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

import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.http.RequestMethod;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.json.DataObjectFactory;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @since Facebook4J 2.1.0
 */
@RunWith(Enclosed.class)
public class RawAPIMethodsTest {
    public static class callGetAPI extends MockFacebookTestBase {
        @Test
        public void noParameter() throws Exception {
            facebook.setMockJSON("mock_json/user/me.json");
            RawAPIResponse actual = facebook.callGetAPI("me");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), pathOf("/me"));

            assertThat(actual.isJSONObject(), is(true));
            assertThat(actual.isJSONArray(), is(false));
            assertThat(actual.isBoolean(), is(false));

            JSONObject jsonObject = actual.asJSONObject();
            assertThat(jsonObject.getString("id"), is("6666"));

            User me = DataObjectFactory.createUser(actual.asString());
            assertThat(me.getBio(), is("biography"));
            assertThat(me.getBirthday(), is("01/23/1975"));
            assertThat(me.getEducation().size(), is(2));
            assertThat(me.getEducation().get(0).getSchool().getId(), is("1111"));
            assertThat(me.getEducation().get(0).getSchool().getName(), is("High School Name"));
            assertThat(me.getEducation().get(0).getType(), is("High School"));
            assertThat(me.getEducation().get(0).getYear().getId(), is("2222"));
            assertThat(me.getEducation().get(0).getYear().getName(), is("1994"));
            assertThat(me.getEducation().get(1).getSchool().getId(), is("3333"));
            assertThat(me.getEducation().get(1).getSchool().getName(), is("College Name"));
            assertThat(me.getEducation().get(1).getType(), is("College"));
            assertThat(me.getEducation().get(1).getYear().getId(), is("4444"));
            assertThat(me.getEducation().get(1).getYear().getName(), is("1998"));
            assertThat(me.getEmail(), is("roundrop@gmail.com"));
            assertThat(me.getFirstName(), is("Firstname"));
            assertThat(me.getGender(), is("male"));
            assertThat(me.getHometown().getId(), is("5555"));
            assertThat(me.getHometown().getName(), is("Hometown Name"));
            assertThat(me.getId(), is("6666"));
            assertThat(me.getLastName(), is("Lastname"));
            assertThat(me.getLink().toString(), is("http://www.facebook.com/roundrop"));
            assertThat(me.getLocale(), is(Locale.US));
            assertThat(me.getLocation().getId(), is("7777"));
            assertThat(me.getLocation().getName(), is("Location Name"));
            assertThat(me.getName(), is("Firstname Lastname"));
            assertThat(me.getTimezone().intValue(), is(9));
            assertThat(me.getUpdatedTime(), is(iso8601DateOf("2013-05-11T16:08:47+0000")));
            assertThat(me.getUsername(), is("roundrop"));
            assertThat(me.isVerified(), is(true));
            assertThat(me.getWork().size(), is(2));
            assertThat(me.getWork().get(0).getEmployer().getId(), is("11111"));
            assertThat(me.getWork().get(0).getEmployer().getName(), is("company1"));
            assertThat(me.getWork().get(0).getLocation().getId(), is("11112"));
            assertThat(me.getWork().get(0).getLocation().getName(), is("location1"));
            assertThat(me.getWork().get(0).getPosition().getId(), is("11113"));
            assertThat(me.getWork().get(0).getPosition().getName(), is("position1"));
            assertThat(me.getWork().get(0).getStartDate(), is("2012-01"));
            assertThat(me.getWork().get(1).getEmployer().getId(), is("22221"));
            assertThat(me.getWork().get(1).getEmployer().getName(), is("company2"));
            assertThat(me.getWork().get(1).getEndDate(), is("2011-12"));
            assertThat(me.getWork().get(1).getStartDate(), is("2011-09"));
        }

        @Test
        public void startsWithSlash() throws Exception {
            facebook.setMockJSON("mock_json/user/me.json");
            facebook.callGetAPI("/me");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), pathOf("/me"));
        }

        @Test
        public void withParameter() throws Exception {
            facebook.setMockJSON("mock_json/post/comments_last3.json");
            Map<String, String> params = new HashMap<String, String>();
            params.put("limit", "3");
            facebook.callGetAPI("216311481960_10201168076257947/comments", params);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/216311481960_10201168076257947/comments")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "3"));
        }

        @Test
        public void withParameters() throws Exception {
            facebook.setMockJSON("mock_json/post/shares_count.json");
            Map<String, String> params = new HashMap<String, String>();
            params.put("fields", "shares");
            params.put("limit", "5");
            facebook.callGetAPI("BillGates", params);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/BillGates")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "shares"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));
        }

        @Test
        public void usingHttpParameterObject() throws Exception {
            facebook.setMockJSON("mock_json/post/shares_count.json");
            HttpParameter p1 = new HttpParameter("fields", "shares");
            HttpParameter p2 = new HttpParameter("limit", "5");
            facebook.callGetAPI("BillGates", p1, p2);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/BillGates")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "shares"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "5"));
        }
    }

    public static class callPostAPI extends MockFacebookTestBase {
        @Test
        public void json() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            Map<String, String> params = new HashMap<String, String>();
            params.put("message", "test message");
            RawAPIResponse actual = facebook.callPostAPI("me/feed", params);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test message"));

            assertThat(actual.isJSONObject(), is(true));
            assertThat(actual.isJSONArray(), is(false));
            assertThat(actual.isBoolean(), is(false));

            JSONObject jsonObject = actual.asJSONObject();
            assertThat(jsonObject.getString("id"), is("137246726435626_185932178233747"));
        }

        @Test
        public void array() throws Exception {
            facebook.setMockJSON("mock_json/batch/simple.json");
            Map<String, String> params = new HashMap<String, String>();
            params.put("batch", "[{\"method\":\"GET\", \"relative_url\":\"me\"},{\"method\":\"GET\", \"relative_url\":\"me/friends?limit=50\"}]");
            RawAPIResponse actual = facebook.callPostAPI("", params);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));

            assertThat(actual.isJSONObject(), is(false));
            assertThat(actual.isJSONArray(), is(true));
            assertThat(actual.isBoolean(), is(false));

            JSONArray jsonArray = actual.asJSONArray();
            assertThat(jsonArray.length(), is(2));
        }

        @Test
        public void bool() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            RawAPIResponse actual = facebook.callPostAPI("500000000000001/likes");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual.isJSONObject(), is(false));
            assertThat(actual.isJSONArray(), is(false));
            assertThat(actual.isBoolean(), is(true));

            boolean bool = actual.asBoolean();
            assertThat(bool, is(true));
        }

        @Test
        public void startsWithSlash() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            Map<String, String> params = new HashMap<String, String>();
            params.put("message", "test message");
            RawAPIResponse actual = facebook.callPostAPI("/me/feed", params);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test message"));
        }

        @Test
        public void usingHttpParameterObject() throws Exception {
            facebook.setMockJSON("mock_json/post_id.json");
            HttpParameter p1 = new HttpParameter("message", "test message");
            RawAPIResponse actual = facebook.callPostAPI("me/feed", p1);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/feed")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("message", "test message"));

            assertThat(actual.isJSONObject(), is(true));
            assertThat(actual.isJSONArray(), is(false));
            assertThat(actual.isBoolean(), is(false));

            JSONObject jsonObject = actual.asJSONObject();
            assertThat(jsonObject.getString("id"), is("137246726435626_185932178233747"));
        }
    }

    public static class callDeleteAPI extends MockFacebookTestBase {
        @Test
        public void bool() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            RawAPIResponse actual = facebook.callDeleteAPI("500000000000001/likes");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));

            assertThat(actual.isJSONObject(), is(false));
            assertThat(actual.isJSONArray(), is(false));
            assertThat(actual.isBoolean(), is(true));

            assertThat(actual.asBoolean(), is(true));
        }

        @Test
        public void withParameter() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            Map<String, String> params = new HashMap<String, String>();
            params.put("uid", "1111111111");
            RawAPIResponse actual = facebook.callDeleteAPI("me/blocked", params);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/blocked")));
            assertThat(facebook.getEndpointURL(), hasParameter("uid", "1111111111"));

            assertThat(actual.asBoolean(), is(true));
        }

        @Test
        public void startsWithSlash() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            RawAPIResponse actual = facebook.callDeleteAPI("/500000000000001/likes");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/500000000000001/likes")));
        }
    }
}