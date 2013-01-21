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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Locale;

import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.ConfigurationBuilder;

/**
 * Initial setup program for unit tests
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class TestSetup {
    /*
     * rewrite the following fields with your environments
     */
    private static final String APP_ID = "********";
    private static final String APP_SECRET = "****************";
    private static final String SRC_TEST_RESOURCES_PATH = "/path/to/your/src/test/resources";
    
    private static Facebook facebook;

    public static void main(String[] args) throws Exception {
        validate();
        File prop = new File(SRC_TEST_RESOURCES_PATH + "/test.properties");
        if (prop.exists()) {
            throw new IllegalStateException("test.properties already exists.");
        }

        System.setProperty("facebook4j.debug", "true");
        System.setProperty("facebook4j.loggerFactory", "facebook4j.internal.logging.StdOutLoggerFactory");
        ConfigurationBuilder build = new ConfigurationBuilder();
        build.setOAuthAppId(APP_ID);
        build.setOAuthAppSecret(APP_SECRET);
        facebook = new FacebookFactory().getInstance(new OAuthAuthorization(build.build()));
        facebook.getOAuthAppAccessToken();
        
        TestUser id1 = setupId1();
        TestUser id2 = setupId2();
        TestUser bestFriend1 = setupBestFriend1();
        TestUser bestFriend2 = setupBestFriend2(bestFriend1);
        makeFriendConnectionAndTags(bestFriend1, bestFriend2);

        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(prop)));
        writer.println("debug=true");
        writer.println("loggerFactory=facebook4j.internal.logging.StdOutLoggerFactory");
        writer.println("http.prettyDebug=true");
        writer.println("jsonStoreEnabled=true");
        writer.println("");
        writer.println("oauth.appId=" + APP_ID);
        writer.println("oauth.appSecret=" + APP_SECRET);
        writer.println("");
        writer.println("id1.id=" + id1.getId());
        writer.println("");
        writer.println("id2.id=" + id2.getId());
        writer.println("");
        writer.println("bestFriend1.id=" + bestFriend1.getId());
        writer.println("");
        writer.println("bestFriend2.id=" + bestFriend2.getId());
        writer.println("");
        writer.println("# a real user");
        writer.println("real.oauth.appId=********");
        writer.println("real.oauth.appSecret=****************");
        writer.println("real.oauth.accessToken=************************");
        writer.close();
    }

    private static void validate() throws IllegalStateException {
        if (APP_ID.equals("********")) {
            throw new IllegalStateException("please rewrite APP_ID field by your App Id.");
        }
        if (APP_SECRET.equals("****************")) {
            throw new IllegalStateException("please rewrite APP_SECRET field by your App Secret.");
        }
        if (SRC_TEST_RESOURCES_PATH.equals("/path/to/your/src/test/resources")) {
            throw new IllegalStateException("please rewrite SRC_TEST_RESOURCES_PATH field by your environment.");
        }
    }

    private static TestUser setupId1() throws Exception {
        TestUser id1 = createTestUser("id one");
        Facebook f = new FacebookFactory().getInstance(new OAuthAuthorization(new ConfigurationBuilder().setOAuthAppId(APP_ID).setOAuthAppSecret(APP_SECRET).build()));
        f.setOAuthAccessToken(new AccessToken(id1.getAccessToken(), null));
        //post
        f.postStatusMessage("status message1");
        f.postFeed(new PostUpdate("I'm here").place("117329868321774"));
        //album
        String albumId = f.createAlbum(new AlbumCreate("test album1", "test message1"));
        f.createAlbum(new AlbumCreate("test album2", "test message2"));
        f.createAlbum(new AlbumCreate("test album3", "test message3"));
        File file = new File("src/test/resources/test_image.png");
        Media source = new Media(file);
        f.addAlbumPhoto(albumId, source);
        f.commentAlbum(albumId, "comment1");
        f.commentAlbum(albumId, "comment2");
        f.commentAlbum(albumId, "comment3");
        //checkin
        String place = "116972348358579";
        GeoLocation coordinates = new GeoLocation(35.659283081996, 139.70090532034);
        CheckinCreate checkin = new CheckinCreate(place, coordinates);
        f.checkin(checkin);
        //link
        f.postLink(new URL("http://facebook4j.org"));

        return id1;
    }
    
    private static TestUser setupId2() throws Exception {
        TestUser id2 = createTestUser("id two");
        return id2;
    }
    
    private static TestUser setupBestFriend1() throws Exception {
        TestUser bestFriend1 = createTestUser("bestfriend one");
        Facebook f = new FacebookFactory().getInstance(new OAuthAuthorization(new ConfigurationBuilder().setOAuthAppId(APP_ID).setOAuthAppSecret(APP_SECRET).build()));
        f.setOAuthAccessToken(new AccessToken(bestFriend1.getAccessToken(), null));
        //post
        f.postStatusMessage("message from bestFriend1");
        return bestFriend1;
    }

    private static TestUser setupBestFriend2(TestUser bestFriend1) throws Exception {
        TestUser bestFriend2 = createTestUser("bestfriend two");
        Facebook f = new FacebookFactory().getInstance(new OAuthAuthorization(new ConfigurationBuilder().setOAuthAppId(APP_ID).setOAuthAppSecret(APP_SECRET).build()));
        f.setOAuthAccessToken(new AccessToken(bestFriend2.getAccessToken(), null));
        //post
        f.postStatusMessage("message from bestFriend2");
        //comment
        ResponseList<Post> feed = f.getHome();
        for (Post post : feed) {
            if (post.getFrom().getId().equals(bestFriend1.getId())) {
                f.commentPost(post.getId(), "comment from bestFriend2");
                break;
            }
        }
        return bestFriend2;
    }
    
    private static void makeFriendConnectionAndTags(TestUser bestFriend1, TestUser bestFriend2) throws Exception {
        facebook.makeFriendTestUser(bestFriend1, bestFriend2);

        Facebook f = new FacebookFactory().getInstance(new OAuthAuthorization(new ConfigurationBuilder().setOAuthAppId(APP_ID).setOAuthAppSecret(APP_SECRET).build()));
        f.setOAuthAccessToken(new AccessToken(bestFriend2.getAccessToken(), null));
        String place = "116972348358579";
        GeoLocation coordinates = new GeoLocation(35.659283081996, 139.70090532034);
        CheckinCreate checkin = new CheckinCreate(place, coordinates).tags(bestFriend1.getId());
        f.checkin(checkin);
    }
    
    private static TestUser createTestUser(String name) throws Exception {
        return facebook.createTestUser(APP_ID, name, Locale.getDefault().toString(),
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
    }
}
