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
import java.util.Locale;
import java.util.Properties;

import org.junit.Before;

import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.conf.PropertyConfiguration;

public class FacebookTestBase {

    protected Facebook facebook, facebook1, facebook2, facebook3, facebook4, unauthenticated;
    protected Properties p = new Properties();

    protected Configuration conf1, conf2, conf3, conf4;

    protected String appId;
    protected String appSecret;

    @Before
    public void prepare() throws Exception {
        InputStream is = FacebookTestBase.class.getResourceAsStream("/test.properties");
        p.load(is);
        is.close();
        
        System.setProperty("facebook4j.debug", p.getProperty("facebook4j.debug"));
        System.setProperty("facebook4j.http.prettyDebug", p.getProperty("facebook4j.http.prettyDebug"));
        System.setProperty("facebook4j.loggerFactory", p.getProperty("facebook4j.loggerFactory"));
        System.setProperty("facebook4j.http.userAgent", p.getProperty("facebook4j.http.userAgent"));
        System.setProperty("facebook4j.jsonStoreEnabled", p.getProperty("facebook4j.jsonStoreEnabled"));

        appId = p.getProperty("app.oauth.appId");
        appSecret = p.getProperty("app.oauth.appSecret");

        conf1 = new PropertyConfiguration(p, "/id1");
        facebook = new FacebookFactory(conf1).getInstance();

        ConfigurationBuilder build1 = new ConfigurationBuilder();
        build1.setDebugEnabled(true);
        build1.setPrettyDebugEnabled(true);
        build1.setOAuthAppId(appId);
        build1.setOAuthAppSecret(appSecret);
        OAuthAuthorization auth1 = new OAuthAuthorization(build1.build());
        facebook1 = new FacebookFactory().getInstance(auth1);

        ConfigurationBuilder build2 = new ConfigurationBuilder();
        build2.setDebugEnabled(true);
        build2.setPrettyDebugEnabled(true);
        build2.setOAuthAppId(appId);
        build2.setOAuthAppSecret(appSecret);
        OAuthAuthorization auth2 = new OAuthAuthorization(build2.build());
        facebook2 = new FacebookFactory().getInstance(auth2);

        unauthenticated = new FacebookFactory(new ConfigurationBuilder().build()).getInstance();

    }

    protected AccessToken getAppAccessToken(Facebook _facebook) {
        try {
            return _facebook.getOAuthAppAccessToken();
        } catch (FacebookException e) {
            fail();
        }
        return null;
    }
    
    protected TestUser createTestUser(Facebook _facebook, AccessToken accessToken) {
        try {
            return _facebook.createTestUser(appId, "test", Locale.getDefault().toString(),
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
    
    protected boolean deleteTestUser(Facebook _facebook, TestUser testUser) {
        try {
            return _facebook.deleteTestUser(testUser.getId());
        } catch (FacebookException e) {
            fail();
        }
        return false;
    }
}
