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

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.junit.Before;

import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.conf.PropertyConfiguration;

public class FacebookTestBase {

    protected Facebook real, facebook1, facebook2, facebook3, facebook4, unauthenticated;
    protected Properties p = new Properties();

    protected TestUser id1, id2, id3, id4;
    protected Configuration conf;

    protected String appId;
    protected String appSecret;

    @Before
    public void prepare() throws Exception {
        InputStream is = FacebookTestBase.class.getResourceAsStream("/test.properties");
        p.load(is);
        is.close();
        
        System.setProperty("facebook4j.debug", p.getProperty("debug"));
        System.setProperty("facebook4j.loggerFactory", p.getProperty("loggerFactory"));

        appId = p.getProperty("app.oauth.appId");
        appSecret = p.getProperty("app.oauth.appSecret");

        // setup test users
        ConfigurationBuilder build = new ConfigurationBuilder();
        build.setOAuthAppId(appId);
        build.setOAuthAppSecret(appSecret);
        facebook1 = new FacebookFactory().getInstance(new OAuthAuthorization(build.build()));
        facebook1.getOAuthAppAccessToken();
        List<TestUser> testUsers = facebook1.getTestUsers(appId);
        for (TestUser testUser : testUsers) {
            if (testUser.getId().equals(p.getProperty("id1.id"))) {
                id1 = testUser;
            } else
            if (testUser.getId().equals(p.getProperty("id2.id"))) {
                id2 = testUser;
//            } else
//            if (testUser.getId().equals(p.getProperty("id3.id"))) {
//                id3 = testUser;
//            } else
//            if (testUser.getId().equals(p.getProperty("id4.id"))) {
//                id4 = testUser;
            }
        }
        facebook1.setOAuthAccessToken(new AccessToken(id1.getAccessToken(), null));
        facebook2 = new FacebookFactory().getInstance(new OAuthAuthorization(new ConfigurationBuilder().setOAuthAppId(appId).setOAuthAppSecret(appSecret).build()));
        facebook2.setOAuthAccessToken(new AccessToken(id2.getAccessToken(), null));
//        facebook3 = new FacebookFactory().getInstance(new OAuthAuthorization(new ConfigurationBuilder().setOAuthAppId(appId).setOAuthAppSecret(appSecret).build()));
//        facebook3.setOAuthAccessToken(new AccessToken(id3.getAccessToken(), null));
//        facebook4 = new FacebookFactory().getInstance(new OAuthAuthorization(new ConfigurationBuilder().setOAuthAppId(appId).setOAuthAppSecret(appSecret).build()));
//        facebook4.setOAuthAccessToken(new AccessToken(id4.getAccessToken(), null));
        
        // setup unauthenticated
        unauthenticated = new FacebookFactory(new ConfigurationBuilder().build()).getInstance();

        // setup real user
        conf = new PropertyConfiguration(p, "/real");
        real = new FacebookFactory(conf).getInstance();

    }

    
    protected TestUser createTestUser(Facebook facebook) {
        try {
            return facebook.createTestUser(appId, "test", Locale.getDefault().toString(),
                        "email" +
                        ",publish_actions" +
                        ",user_about_me" +
                        ",user_activities" +
                        ",user_birthday" +
                        ",user_checkins" +
                        ",user_education_history" +
                        ",user_events" +
                        ",user_games_activity" +
                        ",user_groups" +
                        ",user_hometown" +
                        ",user_interests" +
                        ",user_likes" +
                        ",user_location" +
                        ",user_notes" +
                        ",user_photos" +
                        ",user_questions" +
                        ",user_relationship_details" +
                        ",user_relationships" +
                        ",user_religion_politics" +
                        ",user_status" +
                        ",user_subscriptions" +
                        ",user_videos" +
                        ",user_website" +
                        ",user_work_history" +
                        ",friends_about_me" +
                        ",friends_activities" +
                        ",friends_birthday" +
                        ",friends_checkins" +
                        ",friends_education_history" +
                        ",friends_events" +
                        ",friends_games_activity" +
                        ",friends_groups" +
                        ",friends_hometown" +
                        ",friends_interests" +
                        ",friends_likes" +
                        ",friends_location" +
                        ",friends_notes" +
                        ",friends_photos" +
                        ",friends_questions" +
                        ",friends_relationship_details" +
                        ",friends_relationships" +
                        ",friends_religion_politics" +
                        ",friends_status" +
                        ",friends_subscriptions" +
                        ",friends_videos" +
                        ",friends_website" +
                        ",friends_work_history" +
                        ",ads_management" +
                        ",create_event" +
                        ",create_note" +
                        ",export_stream" +
                        ",friends_online_presence" +
                        ",manage_friendlists" +
                        ",manage_notifications" +
                        ",manage_pages" +
                        ",offline_access" +
                        ",photo_upload" +
                        ",publish_checkins" +
                        ",publish_stream" +
                        ",read_friendlists" +
                        ",read_insights" +
                        ",read_mailbox" +
                        ",read_page_mailboxes" +
                        ",read_requests" +
                        ",read_stream" +
                        ",rsvp_event" +
                        ",share_item" +
                        ",sms" +
                        ",status_update" +
                        ",user_online_presence" +
                        ",video_upload" +
                        ",xmpp_login"
                   );

        } catch (FacebookException e) {
            fail();
        }
        return null;
    }
    
    protected boolean deleteTestUser(Facebook facebook, TestUser testUser) {
        try {
            return facebook.deleteTestUser(testUser.getId());
        } catch (FacebookException e) {
            fail();
        }
        return false;
    }
}
