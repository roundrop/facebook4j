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
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.json.DataObjectFactory;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Iterator;
import java.util.List;
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
public class BatchRequestsMethodsTest {
    public static class executeBatch extends MockFacebookTestBase {
        @Test
        public void simple() throws Exception {
            facebook.setMockJSON("mock_json/batch/simple.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.GET, "me"));
            batch.add(new BatchRequest(RequestMethod.GET, "me/friends?limit=50"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));

            assertThat(actuals.size(), is(2));

            BatchResponse actual1 = actuals.get(0);
            assertThat(actual1.getStatusCode(), is(200));
            Map<String,List<String>> headerFields = actual1.getResponseHeaderFields();
            assertThat(headerFields.size(), is(9));

            String contentType = actual1.getResponseHeader("Content-Type");
            assertThat(contentType, is("text/javascript; charset=UTF-8"));

            User user = DataObjectFactory.createUser(actual1.asString());
            assertThat(user.getId(), is("1234567890123456"));
            assertThat(user.getName(), is("Me Name"));
            assertThat(user.getFirstName(), is("Me"));
            assertThat(user.getLastName(), is("Name"));
            assertThat(user.getLink().toString(), is("https://www.facebook.com/roundrop"));
            assertThat(user.getBirthday(), is("01/23/1975"));
            assertThat(user.getHometown().getId(), is("186217314746492"));
            assertThat(user.getHometown().getName(), is("Hiroshima-shi, Hiroshima, Japan"));
            assertThat(user.getLocation().getId(), is("163173617067956"));
            assertThat(user.getLocation().getName(), is("Yokohama-shi, Kanagawa, Japan"));
            assertThat(user.getBio(), is("Facebook4J: http://facebook4j.org"));
            assertThat(user.getWork().size(), is(2));
            assertThat(user.getWork().get(0).getEmployer().getId(), is("20000000000000001"));
            assertThat(user.getWork().get(0).getEmployer().getName(), is("company1"));
            assertThat(user.getWork().get(0).getStartDate(), is("2012-01-01"));
            assertThat(user.getWork().get(1).getEmployer().getId(), is("3000000000000001"));
            assertThat(user.getWork().get(1).getEmployer().getName(), is("roundrop"));
            assertThat(user.getWork().get(1).getEndDate(), is("2011-12-31"));
            assertThat(user.getWork().get(1).getStartDate(), is("2011-09-01"));
            assertThat(user.getEducation().size(), is(2));
            assertThat(user.getEducation().get(0).getSchool().getId(), is("30000000000000001"));
            assertThat(user.getEducation().get(0).getSchool().getName(), is("High School"));
            assertThat(user.getEducation().get(0).getType(), is("High School"));
            assertThat(user.getEducation().get(0).getYear().getId(), is("135676686463386"));
            assertThat(user.getEducation().get(0).getYear().getName(), is("1994"));
            assertThat(user.getEducation().get(1).getSchool().getId(), is("300000000000002"));
            assertThat(user.getEducation().get(1).getSchool().getName(), is("College"));
            assertThat(user.getEducation().get(1).getType(), is("College"));
            assertThat(user.getEducation().get(1).getYear().getId(), is("144560162276732"));
            assertThat(user.getEducation().get(1).getYear().getName(), is("1998"));
            assertThat(user.getEmail(), is("roundrop@gmail.com"));
            assertThat(user.getGender(), is("male"));
            assertThat(user.getLocale(), is(Locale.US));
            assertThat(user.getTimezone().intValue(), is(9));
            assertThat(user.getUpdatedTime(), is(iso8601DateOf("2014-02-23T05:15:52+0000")));
            assertThat(user.getUsername(), is("roundrop"));
            assertThat(user.isVerified(), is(true));

            BatchResponse actual2 = actuals.get(1);
            assertThat(actual2.getStatusCode(), is(200));
            headerFields = actual2.getResponseHeaderFields();
            assertThat(headerFields.size(), is(8));

            contentType = actual2.getResponseHeader("ETag");
            assertThat(contentType, is("\"fb6e6a6a5742e14b39414015f31648f7f126fea5\""));

            ResponseList<JSONObject> responseList = actual2.asResponseList();
            assertThat(responseList.size(), is(3));
            Friend friend1 = DataObjectFactory.createFriend(responseList.get(0).toString());
            assertThat(friend1.getId(), is("5757575757"));
            assertThat(friend1.getName(), is("Michael Jackson"));
            Friend friend2 = DataObjectFactory.createFriend(responseList.get(1).toString());
            assertThat(friend2.getId(), is("6464646464"));
            assertThat(friend2.getName(), is("John Lennon"));
            Friend friend3 = DataObjectFactory.createFriend(responseList.get(2).toString());
            assertThat(friend3.getId(), is("6969696969"));
            assertThat(friend3.getName(), is("James Hendrix"));
        }

        @Test
        public void multipleMethods() throws Exception {
            facebook.setMockJSON("mock_json/batch/multiple_methods.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.POST, "me/feed")
                          .body("message=Test status update&link=http://developers.facebook.com/"));
            batch.add(new BatchRequest(RequestMethod.GET, "me/feed?limit=1"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));

            assertThat(actuals.size(), is(2));

            BatchResponse actual1 = actuals.get(0);
            assertThat(actual1.getStatusCode(), is(200));
            Map<String,List<String>> headerFields = actual1.getResponseHeaderFields();
            assertThat(headerFields.size(), is(7));

            JSONObject jsonObject1 = actual1.asJSONObject();
            assertThat(jsonObject1.getString("id"), is("1234567890123456_600000000000001"));

            BatchResponse actual2 = actuals.get(1);
            assertThat(actual2.getStatusCode(), is(200));
            headerFields = actual2.getResponseHeaderFields();
            assertThat(headerFields.size(), is(8));

            ResponseList<JSONObject> jsonObjectList = actual2.asResponseList();
            assertThat(jsonObjectList.size(), is(1));
            Paging<JSONObject> paging = jsonObjectList.getPaging();
            assertThat(paging.getPrevious().toString(), is("https://graph.facebook.com/1234567890123456/feed?limit=1&access_token=access_token&until=1313131313"));

            JSONObject jsonObject2 = jsonObjectList.get(0);
            Post post = DataObjectFactory.createPost(jsonObject2.toString());
            assertThat(post.getId(), is("1234567890123456_600000000000002"));
            assertThat(post.getFrom().getId(), is("1234567890123456"));
            assertThat(post.getFrom().getName(), is("Me Name"));
            assertThat(post.getStory(), is("Me Name shared a link."));
            assertThat(post.getLink().toString(), is("http://developers.facebook.com/"));
            assertThat(post.getName(), is("Facebook Developers"));
            assertThat(post.getCaption(), is("developers.facebook.com"));
            assertThat(post.getDescription(), is("Facebook Platform helps developers build, grow and monetize their business"));
            assertThat(post.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yD/r/aSaSaSaSaS.gif"));
            assertThat(post.getActions().size(), is(2));
            assertThat(post.getActions().get(0).getName(), is("Comment"));
            assertThat(post.getActions().get(1).getName(), is("Like"));
            assertThat(post.getPrivacy().getValue(), is(PrivacyType.EVERYONE));
            assertThat(post.getType(), is("link"));
            assertThat(post.getStatusType(), is("shared_story"));
            assertThat(post.getApplication().getId(), is("232323232323"));
        }

        @Test
        public void excludeHeaders() throws Exception {
            facebook.setMockJSON("mock_json/batch/exclude_headers.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.GET, "me"));
            batch.setIncludeHeaders(false);
            List<BatchResponse> actuals = facebook.executeBatch(batch);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("include_headers", "false"));

            assertThat(actuals.get(0).getResponseHeaderFields().size(), is(0));
        }

        @Test
        public void error() throws Exception {
            facebook.setMockJSON("mock_json/batch/error.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.POST, "me/feed").body("message=Test update"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));

            BatchResponse actual1 = actuals.get(0);
            assertThat(actual1.getStatusCode(), is(403));
            assertThat(actual1.getResponseHeaderFields().size(), is(2));
            assertThat(actual1.getResponseHeader("WWW-Authenticate"), is("OAuth"));
            assertThat(actual1.getResponseHeader("Content-Type"), is("text/javascript; charset=UTF-8"));

            JSONObject body = actual1.asJSONObject();
            assertThat(body.getJSONObject("error").getString("type"), is("OAuthException"));
        }

        @Test
        public void timeout() throws Exception {
            facebook.setMockJSON("mock_json/batch/timeout.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.GET, "me"));
            batch.add(new BatchRequest(RequestMethod.GET, "me/friends?limit=50"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));

            assertThat(actuals.size(), is(2));
            assertThat(actuals.get(0), is(notNullValue()));
            assertThat(actuals.get(1), is(nullValue()));
        }

        @Test
        public void fql() throws Exception {
            facebook.setMockJSON("mock_json/batch/fql.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.POST, "method/fql.query?query=select+name+from+user+where+uid=4"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/")));

            assertThat(actuals.size(), is(1));

            BatchResponse batchResponse = actuals.get(0);
            JSONArray jsonArray = batchResponse.asJSONArray();
            assertThat(jsonArray.getJSONObject(0).getString("name"), is("Mark Zuckerberg"));
        }

        @Test
        public void usingJSONPath() throws Exception {
            facebook.setMockJSON("mock_json/batch/json_path.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.GET, "me/friends?limit=5").name("get-friends"));
            batch.add(new BatchRequest(RequestMethod.GET, "?ids={result=get-friends:$.data.*.id}"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);

            assertThat(actuals.size(), is(2));

            BatchResponse actual1 = actuals.get(0);
            assertThat(actual1, is(nullValue()));

            BatchResponse actual2 = actuals.get(1);
            assertThat(actual2.getStatusCode(), is(200));
            JSONObject jsonObject = actual2.asJSONObject();
            Iterator keys = jsonObject.keys();
            String key1 = (String) keys.next();
            Friend friend1 = DataObjectFactory.createFriend(jsonObject.getString(key1));
            assertThat(friend1.getId(), is("500000001"));
            String key2 = (String) keys.next();
            Friend friend2 = DataObjectFactory.createFriend(jsonObject.getString(key2));
            assertThat(friend2.getId(), is("500000002"));
        }

        @Test
        public void omitResponseOnSuccess() throws Exception {
            facebook.setMockJSON("mock_json/batch/omit_response_on_success.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.GET, "me/friends?limit=5").name("get-friends").omitResponseOnSuccess(false));
            batch.add(new BatchRequest(RequestMethod.GET, "?ids={result=get-friends:$.data.*.id}"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);

            assertThat(actuals.size(), is(2));

            BatchResponse actual1 = actuals.get(0);
            assertThat(actual1, is(notNullValue()));
            assertThat(actual1.getStatusCode(), is(200));
            assertThat(actual1.getResponseHeaderFields().size(), is(8));
            ResponseList<JSONObject> responseList = actual1.asResponseList();
            assertThat(responseList.size(), is(2));
            assertThat(responseList.get(0).getString("id"), is("500000001"));
            assertThat(responseList.get(1).getString("id"), is("500000002"));

            BatchResponse actual2 = actuals.get(1);
            assertThat(actual2.getStatusCode(), is(200));
            JSONObject jsonObject = actual2.asJSONObject();
            Iterator keys = jsonObject.keys();
            String key1 = (String) keys.next();
            Friend friend1 = DataObjectFactory.createFriend(jsonObject.getString(key1));
            assertThat(friend1.getId(), is("500000001"));
            String key2 = (String) keys.next();
            Friend friend2 = DataObjectFactory.createFriend(jsonObject.getString(key2));
            assertThat(friend2.getId(), is("500000002"));
        }

        @Test
        public void dependsOn() throws Exception {
            facebook.setMockJSON("mock_json/batch/depends_on.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            batch.add(new BatchRequest(RequestMethod.POST, "me/feed").name("first").body("message=Test status update"));
            batch.add(new BatchRequest(RequestMethod.GET, "me/feed?limit=1").dependsOn("first"));
            List<BatchResponse> actuals = facebook.executeBatch(batch);

            assertThat(actuals.size(), is(2));

            BatchResponse actual1 = actuals.get(0);
            assertThat(actual1, is(nullValue()));

            BatchResponse actual2 = actuals.get(1);
            assertThat(actual2.getStatusCode(), is(200));
            Map<String,List<String>> headerFields = actual2.getResponseHeaderFields();
            assertThat(headerFields.size(), is(8));
            ResponseList<JSONObject> responseList = actual2.asResponseList();
            assertThat(responseList.size(), is(1));
            assertThat(responseList.get(0).getString("id"), is("1234567890123456_600000000000002"));
        }

        @Test
        public void uploadingBinaryData() throws Exception {
            facebook.setMockJSON("mock_json/batch/uploading_binary_data.json");
            BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
            Media file1 = new Media(new File("src/test/resources/test_image.png"));
            BatchAttachment attachment1 = new BatchAttachment("file1", file1);
            batch.add(new BatchRequest(RequestMethod.POST, "me/photos")
                            .body("message=My photo1")
                            .attachedFile(attachment1));
            Media file2 = new Media(new File("src/test/resources/500x500.png"));
            BatchAttachment attachment2 = new BatchAttachment("file2", file2);
            batch.add(new BatchRequest(RequestMethod.POST, "me/photos")
                            .body("message=My photo2")
                            .attachedFile(attachment2));
            List<BatchResponse> actuals = facebook.executeBatch(batch);
            assertThat(facebook.getHttpParameters(), hasPostParameter("file1"));
            assertThat(facebook.getHttpParameters(), hasPostParameter("file2"));

            JSONObject actual1 = actuals.get(0).asJSONObject();
            assertThat(actual1.getString("id"), is("696350953760526"));
            assertThat(actual1.getString("post_id"), is("1234567890123456_696350960427192"));
            JSONObject actual2 = actuals.get(1).asJSONObject();
            assertThat(actual2.getString("id"), is("696350950427193"));
            assertThat(actual2.getString("post_id"), is("1234567890123456_696350960427192"));
        }
    }
}
