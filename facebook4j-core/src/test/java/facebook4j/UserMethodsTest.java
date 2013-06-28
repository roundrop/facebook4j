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

import java.net.URL;
import java.util.List;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class UserMethodsTest extends MockFacebookTestBase {

    @Test
    public void getMe() throws Exception {
        facebook.setMockJSON("mock_json/user/me.json");
        User me = facebook.getMe();

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
        assertThat(me.getLocale().toString(), is("en_us"));
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
    public void getMe_fields() throws Exception {
        facebook.setMockJSON("mock_json/user/me_fields.json");
        User me = facebook.getMe(new Reading().fields("gender").fields("email"));

        assertThat(me.getGender(), is("male"));
        assertThat(me.getEmail(), is("roundrop@gmail.com"));
        assertThat(me.getId(), is("6666"));

        assertThat(me.getBirthday(), is(nullValue()));
        assertThat(me.getEducation().size(), is(0));
        assertThat(me.getHometown(), is(nullValue()));
    }

    @Test
    public void getUser() throws Exception {
        facebook.setMockJSON("mock_json/user/me.json");
        User user = facebook.getUser("6666");

        assertThat(user.getBio(), is("biography"));
        assertThat(user.getBirthday(), is("01/23/1975"));
        assertThat(user.getEducation().size(), is(2));
        assertThat(user.getEducation().get(0).getSchool().getId(), is("1111"));
        assertThat(user.getEducation().get(0).getSchool().getName(), is("High School Name"));
        assertThat(user.getEducation().get(0).getType(), is("High School"));
        assertThat(user.getEducation().get(0).getYear().getId(), is("2222"));
        assertThat(user.getEducation().get(0).getYear().getName(), is("1994"));
        assertThat(user.getEducation().get(1).getSchool().getId(), is("3333"));
        assertThat(user.getEducation().get(1).getSchool().getName(), is("College Name"));
        assertThat(user.getEducation().get(1).getType(), is("College"));
        assertThat(user.getEducation().get(1).getYear().getId(), is("4444"));
        assertThat(user.getEducation().get(1).getYear().getName(), is("1998"));
        assertThat(user.getEmail(), is("roundrop@gmail.com"));
        assertThat(user.getFirstName(), is("Firstname"));
        assertThat(user.getGender(), is("male"));
        assertThat(user.getHometown().getId(), is("5555"));
        assertThat(user.getHometown().getName(), is("Hometown Name"));
        assertThat(user.getId(), is("6666"));
        assertThat(user.getLastName(), is("Lastname"));
        assertThat(user.getLink().toString(), is("http://www.facebook.com/roundrop"));
        assertThat(user.getLocale().toString(), is("en_us"));
        assertThat(user.getLocation().getId(), is("7777"));
        assertThat(user.getLocation().getName(), is("Location Name"));
        assertThat(user.getName(), is("Firstname Lastname"));
        assertThat(user.getTimezone().intValue(), is(9));
        assertThat(user.getUsername(), is("roundrop"));
        assertThat(user.isVerified(), is(true));
        assertThat(user.getWork().size(), is(2));
        assertThat(user.getWork().get(0).getEmployer().getId(), is("11111"));
        assertThat(user.getWork().get(0).getEmployer().getName(), is("company1"));
        assertThat(user.getWork().get(0).getLocation().getId(), is("11112"));
        assertThat(user.getWork().get(0).getLocation().getName(), is("location1"));
        assertThat(user.getWork().get(0).getPosition().getId(), is("11113"));
        assertThat(user.getWork().get(0).getPosition().getName(), is("position1"));
        assertThat(user.getWork().get(0).getStartDate(), is("2012-01"));
        assertThat(user.getWork().get(1).getEmployer().getId(), is("22221"));
        assertThat(user.getWork().get(1).getEmployer().getName(), is("company2"));
        assertThat(user.getWork().get(1).getEndDate(), is("2011-12"));
        assertThat(user.getWork().get(1).getStartDate(), is("2011-09"));
    }

    @Test
    public void getUser_fields() throws Exception {
        facebook.setMockJSON("mock_json/user/me_fields.json");
        User user = facebook.getUser("6666", new Reading().fields("gender,email"));

        assertThat(user.getGender(), is("male"));
        assertThat(user.getEmail(), is("roundrop@gmail.com"));
        assertThat(user.getId(), is("6666"));

        assertThat(user.getLink(), is(nullValue()));
        assertThat(user.getLocale(), is(nullValue()));
        assertThat(user.getTimezone(), is((double) -1));
        assertThat(user.isInstalled(), is(false));
    }

    @Test
    public void pictureURL() throws Exception {
        facebook.setMockJSON("mock_json/user/me_picture.json");
        URL url = facebook.getPictureURL();
        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
        url = facebook.getPictureURL("22222");
        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
    }

    @Test
    public void pictureURL_size() throws Exception {
        facebook.setMockJSON("mock_json/user/me_picture.json");
        URL url = facebook.getPictureURL(PictureSize.large);
        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
        url = facebook.getPictureURL("22222", PictureSize.large);
        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
    }

    @Test
    public void getSSLPictureURL() throws Exception {
        facebook.setMockJSON("mock_json/user/me_picture.json");
        URL url = facebook.getSSLPictureURL();
        assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
        assertThat(facebook.getEndpointURL(), is(pathOf("/me/picture")));
        assertThat(facebook.getEndpointURL(), hasParameter("return_ssl_resources", "1"));

        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
    }

    @Test
    public void getSSLPictureURL_size() throws Exception {
        facebook.setMockJSON("mock_json/user/me_picture.json");
        URL url = facebook.getSSLPictureURL(PictureSize.large);
        assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
        assertThat(facebook.getEndpointURL(), is(pathOf("/me/picture")));
        assertThat(facebook.getEndpointURL(), hasParameter("type", "large"));
        assertThat(facebook.getEndpointURL(), hasParameter("return_ssl_resources", "1"));

        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
    }

    @Test
    public void getSSLPictureURL_id() throws Exception {
        facebook.setMockJSON("mock_json/user/me_picture.json");
        URL url = facebook.getSSLPictureURL("22222");
        assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
        assertThat(facebook.getEndpointURL(), is(pathOf("/22222/picture")));
        assertThat(facebook.getEndpointURL(), hasParameter("return_ssl_resources", "1"));

        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
    }

    @Test
    public void getSSLPictureURL_id_size() throws Exception {
        facebook.setMockJSON("mock_json/user/me_picture.json");
        URL url = facebook.getSSLPictureURL("22222", PictureSize.large);
        assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
        assertThat(facebook.getEndpointURL(), is(pathOf("/22222/picture")));
        assertThat(facebook.getEndpointURL(), hasParameter("type", "large"));
        assertThat(facebook.getEndpointURL(), hasParameter("return_ssl_resources", "1"));

        assertThat(url.toString(), is("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/1111_22222_333333_q.jpg"));
    }

    @Test
    public void users() throws Exception {
        facebook.setMockJSON("mock_json/user/users.json");
        List<User> users = facebook.getUsers("4", "BillGates");

        assertThat(users.size(), is(2));
    }

}
