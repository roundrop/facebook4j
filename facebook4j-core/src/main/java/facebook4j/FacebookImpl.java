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

import facebook4j.Question.Option;
import facebook4j.api.AccountMethods;
import facebook4j.api.ActivityMethods;
import facebook4j.api.AlbumMethods;
import facebook4j.api.BatchRequestsMethods;
import facebook4j.api.CheckinMethods;
import facebook4j.api.CommentMethods;
import facebook4j.api.ConversationMethods;
import facebook4j.api.DomainMethods;
import facebook4j.api.EventMethods;
import facebook4j.api.FQLMethods;
import facebook4j.api.FamilyMethods;
import facebook4j.api.FavoriteMethods;
import facebook4j.api.FriendMethods;
import facebook4j.api.GameMethods;
import facebook4j.api.GroupMethods;
import facebook4j.api.InsightMethods;
import facebook4j.api.LikeMethods;
import facebook4j.api.LinkMethods;
import facebook4j.api.LocationMethods;
import facebook4j.api.MessageMethods;
import facebook4j.api.NoteMethods;
import facebook4j.api.NotificationMethods;
import facebook4j.api.PageMethods;
import facebook4j.api.PermissionMethods;
import facebook4j.api.PhotoMethods;
import facebook4j.api.PokeMethods;
import facebook4j.api.PostMethods;
import facebook4j.api.QuestionMethods;
import facebook4j.api.RawAPIMethods;
import facebook4j.api.SearchMethods;
import facebook4j.api.SubscribeMethods;
import facebook4j.api.TestUserMethods;
import facebook4j.api.UserMethods;
import facebook4j.api.VideoMethods;
import facebook4j.auth.Authorization;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * A java representation of the <a href="https://developers.facebook.com/docs/reference/api/">Facebook Graph API</a><br>
 * This class is thread safe and can be cached/re-used and used concurrently.<br>
 * Currently this class is not carefully designed to be extended. It is suggested to extend this class only for mock testing purpose.<br>
 *
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
class FacebookImpl extends FacebookBaseImpl implements Facebook {
    private static final long serialVersionUID = 6277119018105563020L;

    /*package*/
    FacebookImpl(Configuration conf, Authorization auth) {
        super(conf, auth);
    }

    private String buildEndpoint(String id) {
        return buildEndpoint(id, null, null);
    }
    private String buildEndpoint(String id, Reading reading) {
        return buildEndpoint(id, null, reading);
    }
    private String buildEndpoint(String id, String connection) {
        return buildEndpoint(id, connection, null);
    }
    private String buildEndpoint(String id, String connection, Reading reading) {
        StringBuilder url = new StringBuilder()
                            .append(conf.getRestBaseURL() + id)
                            .append(connection == null ? "" : "/" + connection)
                            .append(reading == null ? "" : "?" + reading.getQuery());
        return url.toString();
    }
    private String buildEndpoint(String path, Map<String, String> parameters) {
        StringBuilder url = new StringBuilder();
        url.append(conf.getRestBaseURL() + path);
        if (parameters != null && parameters.size() > 0) {

            url.append("?");

            int i = 0;
            for (final String k : parameters.keySet()) {
                if (i > 0) {
                    url.append("&");
                }

                try {
                    url.append(URLEncoder.encode(k, "UTF-8"))
                       .append("=")
                       .append(URLEncoder.encode(parameters.get(k), "UTF-8"));
                } catch (UnsupportedEncodingException ignore) {
                }
                i++;
            }
        }
        return url.toString();
    }

    private String buildVideoEndpoint(String id, String connection) {
        return buildVideoEndpoint(id, connection, null);
    }
    private String buildVideoEndpoint(String id, String connection, Reading reading) {
        StringBuilder url = new StringBuilder()
                            .append(conf.getVideoBaseURL() + id)
                            .append(connection == null ? "" : "/" + connection)
                            .append(reading == null ? "" : "?" + reading.getQuery());
        return url.toString();
    }

    private String buildSearchEndpoint(String query, String objectType, Reading reading) {
        String q = null;
        if (query != null) {
            q = HttpParameter.encode(query);
        }
        StringBuilder url = new StringBuilder()
                            .append(buildEndpoint("search"))
                            .append(objectType == null ? "" : "?type=" + objectType)
                            .append(q == null ? "" : objectType == null ? "?q=" + q : "&q=" + q)
                            .append(reading == null ? "" : "&" + reading.getQuery());
        return url.toString();
    }

    /* User Methods */

    public User getMe() throws FacebookException {
        return getMe(null);
    }
    public User getMe(Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        User user = factory.createUser(get(buildEndpoint("me", reading)));
        return user;
    }

    public User getUser(String userId) throws FacebookException {
        return getUser(userId, null);
    }
    public User getUser(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        User user = factory.createUser(get(buildEndpoint(userId, reading)));
        return user;
    }

    public URL getPictureURL() throws FacebookException {
        ensureAuthorizationEnabled();
        return getPictureURL("me");
    }
    public URL getPictureURL(PictureSize size) throws FacebookException {
        ensureAuthorizationEnabled();
        return getPictureURL("me", size);
    }
    public URL getPictureURL(int width, int height) throws FacebookException {
        ensureAuthorizationEnabled();
        return getPictureURL("me", width, height);
    }
    public URL getPictureURL(String userId) throws FacebookException {
        return getPictureURL(userId, null);
    }
    public URL getPictureURL(String userId, PictureSize size) throws FacebookException {
        return _getPictureURL(userId, size);
    }
     public URL getPictureURL(String userId, int width, int height) throws FacebookException {
        return _getPictureURL(userId, width, height);
    }

    public URL getSSLPictureURL() throws FacebookException {
        ensureAuthorizationEnabled();
        return getSSLPictureURL("me");
    }
    public URL getSSLPictureURL(PictureSize size) throws FacebookException {
        ensureAuthorizationEnabled();
        return getSSLPictureURL("me", size);
    }
    public URL getSSLPictureURL(String userId) throws FacebookException {
        return getSSLPictureURL(userId, null);
    }
    public URL getSSLPictureURL(String userId, PictureSize size) throws FacebookException {
        return _getSSLPictureURL(userId, size);
    }

    public List<User> getUsers(String... ids) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createUserArray(get(conf.getRestBaseURL(), new HttpParameter[] {
                                        new HttpParameter("ids", z_F4JInternalStringUtil.join(ids))}));
    }

    /* Account Methods */

    public ResponseList<Account> getAccounts() throws FacebookException {
        return getAccounts("me", null);
    }
    public ResponseList<Account> getAccounts(Reading reading) throws FacebookException {
        return getAccounts("me", reading);
    }
    public ResponseList<Account> getAccounts(String userId) throws FacebookException {
        return getAccounts(userId, null);
    }
    public ResponseList<Account> getAccounts(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createAccountList(get(buildEndpoint(userId, "accounts", reading)));
    }

    /* Achievement Methods */

    public ResponseList<Achievement> getAchievements() throws FacebookException {
        return getAchievements("me", null);
    }
    public ResponseList<Achievement> getAchievements(Reading reading) throws FacebookException {
        return getAchievements("me", reading);
    }
    public ResponseList<Achievement> getAchievements(String userId) throws FacebookException {
        return getAchievements(userId, null);
    }
    public ResponseList<Achievement> getAchievements(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createAchievementList(get(buildEndpoint(userId, "achievements", reading)));
    }

    public String postAchievement(URL achievementURL) throws FacebookException {
        return postAchievement("me", achievementURL);
    }
    public String postAchievement(String userId, URL achievementURL) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(userId, "achievements"),
                            new HttpParameter[] {new HttpParameter("achievement", achievementURL.toString())}
                          ).asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public boolean deleteAchievement(URL achievementURL) throws FacebookException {
        return deleteAchievement("me", achievementURL);
    }
    public boolean deleteAchievement(String userId, URL achievementURL) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(userId, "achievements"),
                            new HttpParameter[] {new HttpParameter("achievement", achievementURL.toString())});
        return parseBoolean(res);
    }

    /* Activity Methods */

    public ResponseList<Activity> getActivities() throws FacebookException {
        return getActivities("me", null);
    }
    public ResponseList<Activity> getActivities(Reading reading) throws FacebookException {
        return getActivities("me", reading);
    }
    public ResponseList<Activity> getActivities(String userId) throws FacebookException {
        return getActivities(userId, null);
    }
    public ResponseList<Activity> getActivities(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createActivityList(get(buildEndpoint(userId, "activities", reading)));
    }

    /* Album Methods */

    public ResponseList<Album> getAlbums() throws FacebookException {
        return getAlbums("me", null);
    }
    public ResponseList<Album> getAlbums(Reading reading) throws FacebookException {
        return getAlbums("me", reading);
    }
    public ResponseList<Album> getAlbums(String id) throws FacebookException {
        return getAlbums(id, null);
    }
    public ResponseList<Album> getAlbums(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createAlbumList(get(buildEndpoint(id, "albums", reading)));
    }

    public Album getAlbum(String albumId) throws FacebookException {
        return getAlbum(albumId, null);
    }
    public Album getAlbum(String albumId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createAlbum(get(buildEndpoint(albumId, reading)));
    }

    public String createAlbum(AlbumUpdate albumUpdate) throws FacebookException {
        return createAlbum("me", albumUpdate);
    }
    public String createAlbum(String userId, AlbumUpdate albumUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(userId, "albums"), albumUpdate.asHttpParameterArray())
                          .asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public ResponseList<Photo> getAlbumPhotos(String albumId) throws FacebookException {
        return getAlbumPhotos(albumId, null);
    }
    public ResponseList<Photo> getAlbumPhotos(String albumId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPhotoList(get(buildEndpoint(albumId, "photos", reading)));
    }

    public String addAlbumPhoto(String albumId, Media source) throws FacebookException {
        return addAlbumPhoto(albumId, source, null);
    }
    public String addAlbumPhoto(String albumId, Media source, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        List<HttpParameter> httpParams = new ArrayList<HttpParameter>();
        httpParams.add(source.asHttpParameter("source"));
        if (message != null) {
            httpParams.add(new HttpParameter("message", message));
        }
        JSONObject json = post(buildEndpoint(albumId, "photos"),
                               httpParams.toArray(new HttpParameter[httpParams.size()]))
                          .asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public ResponseList<Comment> getAlbumComments(String albumId) throws FacebookException {
        return getAlbumComments(albumId, null);
    }
    public ResponseList<Comment> getAlbumComments(String albumId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getComments(albumId, reading);
    }

    public String commentAlbum(String albumId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _comment(albumId, message);
    }

    public String commentAlbum(String albumId, CommentUpdate commentUpdate) throws FacebookException {
        return _comment(albumId, commentUpdate);
    }

    public ResponseList<Like> getAlbumLikes(String albumId) throws FacebookException {
        return getAlbumLikes(albumId, null);
    }
    public ResponseList<Like> getAlbumLikes(String albumId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLikeList(get(buildEndpoint(albumId, "likes", reading)));
    }

    public boolean likeAlbum(String albumId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(albumId);
    }
    public boolean unlikeAlbum(String albumId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(albumId);
    }

    public URL getAlbumCoverPhoto(String albumId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = get(buildEndpoint(albumId, "picture"));
        try {
            return new URL(res.getResponseHeader("Location"));
        } catch (MalformedURLException urle) {
            throw new FacebookException(urle.getMessage(), urle);
        }
    }

    /* Book Methods */

    public ResponseList<Book> getBooks() throws FacebookException {
        return getBooks("me", null);
    }
    public ResponseList<Book> getBooks(Reading reading) throws FacebookException {
        return getBooks("me", reading);
    }
    public ResponseList<Book> getBooks(String userId) throws FacebookException {
        return getBooks(userId, null);
    }
    public ResponseList<Book> getBooks(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createBookList(get(buildEndpoint(userId, "books", reading)));
    }

    /* Checkin Methods */

    public ResponseList<Checkin> getCheckins() throws FacebookException {
        return getCheckins("me", null);
    }
    public ResponseList<Checkin> getCheckins(Reading reading) throws FacebookException {
        return getCheckins("me", reading);
    }
    public ResponseList<Checkin> getCheckins(String id) throws FacebookException {
        return getCheckins(id, null);
    }
    public ResponseList<Checkin> getCheckins(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createCheckinList(get(buildEndpoint(id, "checkins", reading)));
    }

    public String checkin(CheckinUpdate checkinUpdate) throws FacebookException {
        return checkin("me", checkinUpdate);
    }
    public String checkin(String userId, CheckinUpdate checkinUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(userId, "checkins"), checkinUpdate.asHttpParameterArray())
                          .asJSONObject();
        return getRawString("id", json);
    }

    public Checkin getCheckin(String checkinId) throws FacebookException {
        return getCheckin(checkinId, null);
    }
    public Checkin getCheckin(String checkinId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createCheckin(get(buildEndpoint(checkinId, reading)));
    }

    public ResponseList<Comment> getCheckinComments(String checkinId) throws FacebookException {
        return getCheckinComments(checkinId, null);
    }
    public ResponseList<Comment> getCheckinComments(String checkinId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getComments(checkinId, reading);
    }
    public String commentCheckin(String checkinId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _comment(checkinId, message);
    }

    public ResponseList<Like> getCheckinLikes(String checkinId) throws FacebookException {
        return getCheckinLikes(checkinId, null);
    }
    public ResponseList<Like> getCheckinLikes(String checkinId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLikeList(get(buildEndpoint(checkinId, "likes", reading)));
    }
    public boolean likeCheckin(String checkinId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(checkinId);
    }
    public boolean unlikeCheckin(String checkinId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(checkinId);
    }

    /* Domain Methods */

    public Domain getDomain(String domainId) throws FacebookException {
        return factory.createDomain(get(buildEndpoint(domainId)));
    }
    public Domain getDomainByName(String domainName) throws FacebookException {
        return factory.createDomain(get(conf.getRestBaseURL(),
                new HttpParameter[]{new HttpParameter("domain", domainName)}));
    }
    public List<Domain> getDomainsByName(String... domainName) throws FacebookException {
        String domainNames = z_F4JInternalStringUtil.join(domainName);
        return factory.createDomainArray(get(conf.getRestBaseURL(),
                new HttpParameter[]{new HttpParameter("domains", domainNames)}));
    }

    /* Event Methods */

    public ResponseList<Event> getEvents() throws FacebookException {
        return getEvents("me", null);
    }
    public ResponseList<Event> getEvents(Reading reading) throws FacebookException {
        return getEvents("me", reading);
    }
    public ResponseList<Event> getEvents(String id) throws FacebookException {
        return getEvents(id, null);
    }
    public ResponseList<Event> getEvents(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createEventList(get(buildEndpoint(id, "events", reading)));
    }

    public String createEvent(EventUpdate eventUpdate) throws FacebookException {
        return createEvent("me", eventUpdate);
    }
    public String createEvent(String id, EventUpdate eventUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(id, "events"), eventUpdate.asHttpParameterArray())
                          .asJSONObject();
        return getRawString("id", json);
    }

    public Event getEvent(String eventId) throws FacebookException {
        return getEvent(eventId, null);
    }
    public Event getEvent(String eventId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = get(buildEndpoint(eventId, reading));
        String resStr = res.asString().trim();
        if (resStr.equals("false")) {
            return null;
        }
        return factory.createEvent(res);
    }

    public boolean editEvent(String eventId, EventUpdate eventUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(eventId), eventUpdate.asHttpParameterArray());
        return parseBoolean(res);
    }

    public boolean deleteEvent(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(eventId));
        return parseBoolean(res);
    }

    public String postEventLink(String eventId, URL link) throws FacebookException {
        return postEventLink(eventId, link, null);
    }
    public String postEventLink(String eventId, URL link, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _postLink(eventId, link, message);
    }

    public String postEventStatusMessage(String eventId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _postStatusMessage(eventId, message);
    }

    public ResponseList<RSVPStatus> getRSVPStatusAsNoreply(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "noreply")));
    }
    public ResponseList<RSVPStatus> getRSVPStatusAsNoreply(String eventId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "noreply/" + userId)));
    }

    public ResponseList<RSVPStatus> getRSVPStatusAsInvited(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "invited")));
    }
    public ResponseList<RSVPStatus> getRSVPStatusAsInvited(String eventId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "invited/" + userId)));
    }

    public boolean inviteToEvent(String eventId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(eventId, "invited/" + userId));
        return parseBoolean(res);
    }
    public boolean inviteToEvent(String eventId, String[] userIds) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(eventId, "invited"), new HttpParameter[] {
                                    new HttpParameter("users", z_F4JInternalStringUtil.join(userIds))});
        return parseBoolean(res);
    }

    public boolean uninviteFromEvent(String eventId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(eventId, "invited/" + userId));
        return parseBoolean(res);
    }

    public ResponseList<RSVPStatus> getRSVPStatusInAttending(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "attending")));
    }
    public ResponseList<RSVPStatus> getRSVPStatusInAttending(String eventId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "attending/" + userId)));
    }

    public boolean rsvpEventAsAttending(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(eventId, "attending"));
        return parseBoolean(res);
    }

    public ResponseList<RSVPStatus> getRSVPStatusInMaybe(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "maybe")));
    }
    public ResponseList<RSVPStatus> getRSVPStatusInMaybe(String eventId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "maybe/" + userId)));
    }

    public boolean rsvpEventAsMaybe(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(eventId, "maybe"));
        return parseBoolean(res);
    }

    public ResponseList<RSVPStatus> getRSVPStatusInDeclined(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "declined")));
    }
    public ResponseList<RSVPStatus> getRSVPStatusInDeclined(String eventId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createRSVPStatusList(get(buildEndpoint(eventId, "declined/" + userId)));
    }

    public boolean rsvpEventAsDeclined(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(eventId, "declined"));
        return parseBoolean(res);
    }

    public URL getEventPictureURL(String eventId) throws FacebookException {
        return getEventPictureURL(eventId, null);
    }
    public URL getEventPictureURL(String eventId, PictureSize size) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getPictureURL(eventId, size);
    }

    public boolean updateEventPicture(String eventId, Media source) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(eventId, "picture"),
                                new HttpParameter[] {source.asHttpParameter("source")});
        return parseBoolean(res);
    }

    public boolean deleteEventPicture(String eventId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(eventId, "picture"));
        return parseBoolean(res);
    }

    public ResponseList<Photo> getEventPhotos(String eventId) throws FacebookException {
        return getEventPhotos(eventId, null);
    }
    public ResponseList<Photo> getEventPhotos(String eventId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPhotoList(get(buildEndpoint(eventId, "photos", reading)));
    }

    public String postEventPhoto(String eventId, Media source) throws FacebookException {
        return postEventPhoto(eventId, source, null);
    }
    public String postEventPhoto(String eventId, Media source, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpParameter[] httpParameters = new HttpParameter[] {source.asHttpParameter("source")};
        if (message != null) {
            httpParameters = HttpParameter.merge(httpParameters,
                                new HttpParameter[]{new HttpParameter("message", message)});
        }
        JSONObject json = post(buildEndpoint(eventId, "photos"), httpParameters).asJSONObject();
        return getRawString("id", json);
    }

    public ResponseList<Video> getEventVideos(String eventId) throws FacebookException {
        return getEventVideos(eventId, null);
    }
    public ResponseList<Video> getEventVideos(String eventId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createVideoList(get(buildEndpoint(eventId, "videos", reading)));
    }

    public String postEventVideo(String eventId, Media source) throws FacebookException {
        return postEventVideo(eventId, source, null, null);
    }
    public String postEventVideo(String eventId, Media source, String title, String description) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpParameter[] httpParameters = new HttpParameter[] {source.asHttpParameter("source")};
        if (title != null) {
            httpParameters = HttpParameter.merge(httpParameters,
                                new HttpParameter[]{new HttpParameter("title", title)});
        }
        if (description != null) {
            httpParameters = HttpParameter.merge(httpParameters,
                    new HttpParameter[]{new HttpParameter("description", description)});
        }
        JSONObject json = post(buildVideoEndpoint(eventId, "videos"), httpParameters).asJSONObject();
        return getRawString("id", json);
    }

    /* Family Methods */

    public ResponseList<Family> getFamily() throws FacebookException {
        return getFamily("me", null);
    }
    public ResponseList<Family> getFamily(Reading reading) throws FacebookException {
        return getFamily("me", reading);
    }
    public ResponseList<Family> getFamily(String userId) throws FacebookException {
        return getFamily(userId, null);
    }
    public ResponseList<Family> getFamily(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFamilyList(get(buildEndpoint(userId, "family", reading)));
    }

    /* Post Methods */

    public ResponseList<Post> getFeed() throws FacebookException {
        return getFeed("me", null);
    }
    public ResponseList<Post> getFeed(Reading reading) throws FacebookException {
        return getFeed("me", reading);
    }
    public ResponseList<Post> getFeed(String id) throws FacebookException {
        return getFeed(id, null);
    }
    public ResponseList<Post> getFeed(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPostList(get(buildEndpoint(id, "feed", reading)));
    }

    public ResponseList<Post> getHome() throws FacebookException {
        return getHome(null);
    }
    public ResponseList<Post> getHome(Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPostList(get(buildEndpoint("me", "home", reading)));
    }

    public ResponseList<Post> getPosts() throws FacebookException {
        return getPosts("me", null);
    }
    public ResponseList<Post> getPosts(Reading reading) throws FacebookException {
        return getPosts("me", reading);
    }
    public ResponseList<Post> getPosts(String id) throws FacebookException {
        return getPosts(id, null);
    }
    public ResponseList<Post> getPosts(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPostList(get(buildEndpoint(id, "posts", reading)));
    }

    public ResponseList<Post> getStatuses() throws FacebookException {
        return getStatuses("me", null);
    }
    public ResponseList<Post> getStatuses(Reading reading) throws FacebookException {
        return getStatuses("me", reading);
    }
    public ResponseList<Post> getStatuses(String id) throws FacebookException {
        return getStatuses(id, null);
    }
    public ResponseList<Post> getStatuses(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPostList(get(buildEndpoint(id, "statuses", reading)));
    }

    public Post getPost(String postId) throws FacebookException {
        return getPost(postId, null);
    }
    public Post getPost(String postId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPost(get(buildEndpoint(postId, reading)));
    }

    public boolean deletePost(String postId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(postId));
        return parseBoolean(res);
    }

    public ResponseList<Comment> getPostComments(String postId) throws FacebookException {
        return getPostComments(postId, null);
    }
    public ResponseList<Comment> getPostComments(String postId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getComments(postId, reading);
    }

    public String answerConversation(String conversationId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _answer(conversationId, message);
    }

    public String commentPost(String postId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _comment(postId, message);
    }

    public String commentPost(String postId, CommentUpdate commentUpdate) throws FacebookException {
        return _comment(postId, commentUpdate);
    }

    public ResponseList<Like> getPostLikes(String postId) throws FacebookException {
        return getPostLikes(postId, null);
    }
    public ResponseList<Like> getPostLikes(String postId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLikeList(get(buildEndpoint(postId, "likes", reading)));
    }

    public ResponseList<Post> getSharedPosts(String postId) throws FacebookException {
        return getSharedPosts(postId, null);
    }

    public ResponseList<Post> getSharedPosts(String postId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getSharedPosts(postId, reading);
    }

    public boolean likePost(String postId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(postId);
    }
    public boolean unlikePost(String postId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(postId);
    }

    public ResponseList<Insight> getPostInsights(String postId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createInsightList(get(buildEndpoint(postId, "insights")));
    }

    public String postFeed(PostUpdate postUpdate) throws FacebookException {
        return postFeed("me", postUpdate);
    }
    public String postFeed(String id, PostUpdate postUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(id, "feed"), postUpdate.asHttpParameterArray())
                          .asJSONObject();
        return getRawString("id", json);
    }

    public String postStatusMessage(String message) throws FacebookException {
        return postStatusMessage("me", message);
    }
    public String postStatusMessage(String id, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _postStatusMessage(id, message);
    }

    public ResponseList<Post> getTagged() throws FacebookException {
        return getTagged("me", null);
    }
    public ResponseList<Post> getTagged(Reading reading) throws FacebookException {
        return getTagged("me", reading);
    }
    public ResponseList<Post> getTagged(String userId) throws FacebookException {
        return getTagged(userId, null);
    }
    public ResponseList<Post> getTagged(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPostList(get(buildEndpoint(userId, "tagged", reading)));
    }

    /* Friend Methods */

    public ResponseList<Friendlist> getFriendlists() throws FacebookException {
        return getFriendlists("me", null);
    }
    public ResponseList<Friendlist> getFriendlists(Reading reading) throws FacebookException {
        return getFriendlists("me", reading);
    }
    public ResponseList<Friendlist> getFriendlists(String userId) throws FacebookException {
        return getFriendlists(userId, null);
    }
    public ResponseList<Friendlist> getFriendlists(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFriendlistList(get(buildEndpoint(userId, "friendlists", reading)));
    }

    public ResponseList<FriendRequest> getFriendRequests() throws FacebookException {
        return getFriendRequests("me", null);
    }
    public ResponseList<FriendRequest> getFriendRequests(Reading reading) throws FacebookException {
        return getFriendRequests("me", reading);
    }
    public ResponseList<FriendRequest> getFriendRequests(String userId) throws FacebookException {
        return getFriendRequests(userId, null);
    }
    public ResponseList<FriendRequest> getFriendRequests(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFriendRequestList(get(buildEndpoint(userId, "friendrequests", reading)));
    }

    public ResponseList<Friend> getFriends() throws FacebookException {
        return getFriends("me", null);
    }
    public ResponseList<Friend> getFriends(Reading reading) throws FacebookException {
        return getFriends("me", reading);
    }
    public ResponseList<Friend> getFriends(String userId) throws FacebookException {
        return getFriends(userId, null);
    }
    public ResponseList<Friend> getFriends(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFriendList(get(buildEndpoint(userId, "friends", reading)));
    }

    public ResponseList<TaggableFriend> getTaggableFriends() throws FacebookException {
        return getTaggableFriends("me", null);
    }
    public ResponseList<TaggableFriend> getTaggableFriends(Reading reading) throws FacebookException {
        return getTaggableFriends("me", reading);
    }
    public ResponseList<TaggableFriend> getTaggableFriends(String userId) throws FacebookException {
        return getTaggableFriends(userId, null);
    }
    public ResponseList<TaggableFriend> getTaggableFriends(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createTaggableFriendList(get(buildEndpoint(userId, "taggable_friends", reading)));
    }

    public ResponseList<Friend> getMutualFriends(String friendUserId) throws FacebookException {
        return getMutualFriends("me", friendUserId, null);
    }
    public ResponseList<Friend> getMutualFriends(String friendUserId, Reading reading) throws FacebookException {
        return getMutualFriends("me", friendUserId, reading);
    }
    public ResponseList<Friend> getMutualFriends(String userId1, String userId2) throws FacebookException {
        return getMutualFriends(userId1, userId2, null);
    }
    public ResponseList<Friend> getMutualFriends(String userId1, String userId2, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFriendList(get(buildEndpoint(userId1, "mutualfriends/" + userId2, reading)));
    }

    public String createFriendlist(String friendlistName) throws FacebookException {
        return createFriendlist("me", friendlistName);
    }
    public String createFriendlist(String userId, String friendlistName) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(userId, "friendlists"),
                                new HttpParameter[]{
                                    new HttpParameter("name", friendlistName)
                                })
                          .asJSONObject();
        return getRawString("id", json);
    }
    public boolean deleteFriendlist(String friendlistId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(friendlistId));
        return parseBoolean(res);
    }

    public boolean addFriendlistMember(String friendlistId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(friendlistId + "/members/" + userId));
        return parseBoolean(res);
    }

    public boolean removeFriendlistMember(String friendlistId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(friendlistId + "/members/" + userId));
        return parseBoolean(res);
    }

    public boolean deleteFriendlistMember(String friendlistId, String userId) throws FacebookException {
        return removeFriendlistMember(friendlistId, userId);
    }

    public Friendlist getFriendlist(String friendlistId) throws FacebookException {
        return getFriendlist(friendlistId, null);
    }
    public Friendlist getFriendlist(String friendlistId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFriendlist(get(buildEndpoint(friendlistId, reading)));
    }

    public ResponseList<Friend> getFriendlistMembers(String friendlistId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFriendList(get(buildEndpoint(friendlistId + "/members")));
    }

    public ResponseList<Friend> getBelongsFriend(String friendId) throws FacebookException {
        return getBelongsFriend("me", friendId);
    }
    public ResponseList<Friend> getBelongsFriend(String friendId, Reading reading) throws FacebookException {
        return getBelongsFriend("me", friendId, reading);
    }
    public ResponseList<Friend> getBelongsFriend(String userId, String friendId) throws FacebookException {
        return getBelongsFriend(userId, friendId, null);
    }
    public ResponseList<Friend> getBelongsFriend(String userId, String friendId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createFriendList(get(buildEndpoint(userId, "friends/" + friendId, reading)));
    }

    /* Favorite Methods */

    public ResponseList<Game> getGames() throws FacebookException {
        return getGames("me", null);
    }
    public ResponseList<Game> getGames(Reading reading) throws FacebookException {
        return getGames("me", reading);
    }
    public ResponseList<Game> getGames(String userId) throws FacebookException {
        return getGames(userId, null);
    }
    public ResponseList<Game> getGames(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createGameList(get(buildEndpoint(userId, "games", reading)));
    }

    public ResponseList<Movie> getMovies() throws FacebookException {
        return getMovies("me", null);
    }
    public ResponseList<Movie> getMovies(Reading reading) throws FacebookException {
        return getMovies("me", reading);
    }
    public ResponseList<Movie> getMovies(String userId) throws FacebookException {
        return getMovies(userId, null);
    }
    public ResponseList<Movie> getMovies(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createMovieList(get(buildEndpoint(userId, "movies", reading)));
    }

    public ResponseList<Music> getMusic() throws FacebookException {
        return getMusic("me", null);
    }
    public ResponseList<Music> getMusic(Reading reading) throws FacebookException {
        return getMusic("me", reading);
    }
    public ResponseList<Music> getMusic(String userId) throws FacebookException {
        return getMusic(userId, null);
    }
    public ResponseList<Music> getMusic(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createMusicList(get(buildEndpoint(userId, "music", reading)));
    }

    public ResponseList<Television> getTelevision() throws FacebookException {
        return getTelevision("me", null);
    }
    public ResponseList<Television> getTelevision(Reading reading) throws FacebookException {
        return getTelevision("me", reading);
    }
    public ResponseList<Television> getTelevision(String userId) throws FacebookException {
        return getTelevision(userId, null);
    }
    public ResponseList<Television> getTelevision(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createTelevisionList(get(buildEndpoint(userId, "television", reading)));
    }

    public ResponseList<Interest> getInterests() throws FacebookException {
        return getInterests("me", null);
    }
    public ResponseList<Interest> getInterests(Reading reading) throws FacebookException {
        return getInterests("me", reading);
    }
    public ResponseList<Interest> getInterests(String userId) throws FacebookException {
        return getInterests(userId, null);
    }
    public ResponseList<Interest> getInterests(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createInterestList(get(buildEndpoint(userId, "interests", reading)));
    }

    /* Group Methods */

    public ResponseList<Group> getGroups() throws FacebookException {
        return getGroups("me", null);
    }
    public ResponseList<Group> getGroups(Reading reading) throws FacebookException {
        return getGroups("me", reading);
    }
    public ResponseList<Group> getGroups(String userId) throws FacebookException {
        return getGroups(userId, null);
    }
    public ResponseList<Group> getGroups(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createGroupList(get(buildEndpoint(userId, "groups", reading)));
    }

    public Group getGroup(String groupId) throws FacebookException {
        return getGroup(groupId, null);
    }
    public Group getGroup(String groupId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createGroup(get(buildEndpoint(groupId, reading)));
    }

    public ResponseList<Post> getGroupFeed(String groupId) throws FacebookException {
        return getGroupFeed(groupId, null);
    }
    public ResponseList<Post> getGroupFeed(String groupId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPostList(get(buildEndpoint(groupId, "feed", reading)));
    }

    public ResponseList<GroupMember> getGroupMembers(String groupId) throws FacebookException {
        return getGroupMembers(groupId, null);
    }
    public ResponseList<GroupMember> getGroupMembers(String groupId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createGroupMemberList(get(buildEndpoint(groupId, "members", reading)));
    }

    public URL getGroupPictureURL(String groupId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = get(buildEndpoint(groupId, "picture"));
        try {
            return new URL(res.getResponseHeader("Location"));
        } catch (MalformedURLException urle) {
            throw new FacebookException(urle.getMessage(), urle);
        }
    }

    public ResponseList<GroupDoc> getGroupDocs(String groupId) throws FacebookException {
        return getGroupDocs(groupId, null);
    }
    public ResponseList<GroupDoc> getGroupDocs(String groupId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createGroupDocList(get(buildEndpoint(groupId, "docs", reading)));
    }

    public String postGroupLink(String groupId, URL linkURL) throws FacebookException {
        return postGroupLink(groupId, linkURL, null);
    }
    public String postGroupLink(String groupId, URL linkURL, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpParameter[] httpParameters = new HttpParameter[]{new HttpParameter("link", linkURL.toString())};
        if (message != null) {
            httpParameters = HttpParameter.merge(httpParameters,
                                new HttpParameter[] {new HttpParameter("message", message)});
        }
        JSONObject json = post(buildEndpoint(groupId, "feed"), httpParameters).asJSONObject();
        return getRawString("id", json);
    }
    public String postGroupFeed(String groupId, PostUpdate postUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(groupId, "feed"),
                            postUpdate.asHttpParameterArray()
                          ).asJSONObject();
        return getRawString("id", json);
    }
    public String postGroupStatusMessage(String groupId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(groupId, "feed"), new HttpParameter[]{
                            new HttpParameter("message", message)
                          }).asJSONObject();
        return getRawString("id", json);
    }

    /* Message Methods */

    public InboxResponseList<Message> getInbox() throws FacebookException {
        return getInbox("me", null);
    }
    public InboxResponseList<Message> getInbox(Reading reading) throws FacebookException {
        return getInbox("me", reading);
    }
    public InboxResponseList<Message> getInbox(String userId) throws FacebookException {
        return getInbox(userId, null);
    }
    public InboxResponseList<Message> getInbox(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createInboxList(get(buildEndpoint(userId, "inbox", reading)));
    }

    public ResponseList<Message> getOutbox() throws FacebookException {
        return getOutbox("me", null);
    }
    public ResponseList<Message> getOutbox(Reading reading) throws FacebookException {
        return getOutbox("me", reading);
    }
    public ResponseList<Message> getOutbox(String userId) throws FacebookException {
        return getOutbox(userId, null);
    }
    public ResponseList<Message> getOutbox(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createMessageList(get(buildEndpoint(userId, "outbox", reading)));
    }

    public ResponseList<Message> getUpdates() throws FacebookException {
        return getUpdates("me", null);
    }
    public ResponseList<Message> getUpdates(Reading reading) throws FacebookException {
        return getUpdates("me", reading);
    }
    public ResponseList<Message> getUpdates(String userId) throws FacebookException {
        return getUpdates(userId, null);
    }
    public ResponseList<Message> getUpdates(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createMessageList(get(buildEndpoint(userId, "updates", reading)));
    }

    public Message getMessage(String messageId) throws FacebookException {
        return getMessage(messageId, null);
    }
    public Message getMessage(String messageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createMessage(get(buildEndpoint(messageId, reading)));
    }

    /* Conversation Methods */

    public InboxResponseList<Conversation> getConversations() throws FacebookException {
        return getConversations("me", null);
    }
    public InboxResponseList<Conversation> getConversations(Reading reading) throws FacebookException {
        return getConversations("me", reading);
    }
    public InboxResponseList<Conversation> getConversations(String pageId) throws FacebookException {
        return getConversations(pageId, null);
    }

    public InboxResponseList<Conversation> getConversations(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createConversationList(get(buildEndpoint(pageId, "conversations", reading)));
    }

    public Conversation getConversation(String conversationId) throws FacebookException {
        return getConversation(conversationId, null);
    }
    public Conversation getConversation(String conversationId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createConversation(get(buildEndpoint(conversationId, reading)));
    }



    /* Like Methods */

    public ResponseList<Like> getUserLikes() throws FacebookException {
        return _getLikes("me", null);
    }
    public ResponseList<Like> getUserLikes(Reading reading) throws FacebookException {
        return _getLikes("me", reading);
    }
    public ResponseList<Like> getUserLikes(String userId) throws FacebookException {
        return _getLikes(userId, null);
    }
    public ResponseList<Like> getUserLikes(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLikeList(get(buildEndpoint(userId, "likes", reading)));
    }

    /* Comment Methods */

    public Comment getComment(String commentId) throws FacebookException {
        return getComment(commentId, null);
    }

    public Comment getComment(String commentId, Reading reading) throws FacebookException {
        return factory.createComment(get(buildEndpoint(commentId, reading)));
    }

    public ResponseList<Comment> getCommentReplies(String commentId) throws FacebookException {
        return getCommentReplies(commentId, null);
    }

    public ResponseList<Comment> getCommentReplies(String commentId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createCommentList(get(buildEndpoint(commentId, "comments", reading)));
    }

    public boolean deleteComment(String commentId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(commentId));
        return parseBoolean(res);
    }

    public ResponseList<Like> getCommentLikes(String commentId) throws FacebookException {
        return getCommentLikes(commentId, null);
    }
    public ResponseList<Like> getCommentLikes(String commentId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLikeList(get(buildEndpoint(commentId, "likes", reading)));
    }
    public boolean likeComment(String commentId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(commentId);
    }
    public boolean unlikeComment(String commentId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(commentId);
    }

    /* Link Methods */

    public ResponseList<Link> getLinks() throws FacebookException {
        return getLinks("me", null);
    }
    public ResponseList<Link> getLinks(Reading reading) throws FacebookException {
        return getLinks("me", reading);
    }
    public ResponseList<Link> getLinks(String id) throws FacebookException {
        return getLinks(id, null);
    }
    public ResponseList<Link> getLinks(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLinkList(get(buildEndpoint(id, "links", reading)));
    }

    public Link getLink(String linkId) throws FacebookException {
        return getLink(linkId, null);
    }
    public Link getLink(String linkId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLink(get(buildEndpoint(linkId, reading)));
    }

    public ResponseList<Comment> getLinkComments(String linkId) throws FacebookException {
        return getLinkComments(linkId, null);
    }
    public ResponseList<Comment> getLinkComments(String linkId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getComments(linkId, reading);
    }

    public String commentLink(String linkId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _comment(linkId, message);
    }

    public String commentLink(String linkId, CommentUpdate commentUpdate) throws FacebookException {
        return _comment(linkId, commentUpdate);
    }

    public ResponseList<Like> getLinkLikes(String linkId) throws FacebookException {
        return getLinkLikes(linkId, null);
    }
    public ResponseList<Like> getLinkLikes(String linkId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getLikes(linkId, reading);
    }

    public boolean likeLink(String linkId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(linkId);
    }
    public boolean unlikeLink(String linkId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(linkId);
    }

    public String postLink(URL link) throws FacebookException {
        ensureAuthorizationEnabled();
        return _postLink("me", link, null);
    }
    public String postLink(URL link, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _postLink("me", link, message);
    }
    public String postLink(String userId, URL link) throws FacebookException {
        ensureAuthorizationEnabled();
        return _postLink(userId, link, null);
    }
    public String postLink(String userId, URL link, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _postLink(userId, link, message);
    }

    /* Location Methods */

    public ResponseList<Location> getLocations() throws FacebookException {
        return getLocations("me", null);
    }
    public ResponseList<Location> getLocations(Reading reading) throws FacebookException {
        return getLocations("me", reading);
    }
    public ResponseList<Location> getLocations(String userId) throws FacebookException {
        return getLocations(userId, null);
    }
    public ResponseList<Location> getLocations(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createLocationList(get(buildEndpoint(userId, "locations", reading)));
    }

    public ResponseList<PlaceTag> getTaggedPlaces() throws FacebookException {
        return getTaggedPlaces("me", null);
    }

    public ResponseList<PlaceTag> getTaggedPlaces(Reading reading) throws FacebookException {
        return getTaggedPlaces("me", reading);
    }

    public ResponseList<PlaceTag> getTaggedPlaces(String userId) throws FacebookException {
        return getTaggedPlaces(userId, null);
    }

    public ResponseList<PlaceTag> getTaggedPlaces(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPlaceTagList(get(buildEndpoint(userId, "tagged_places", reading)));
    }

    /* Note Methods */

    public ResponseList<Note> getNotes() throws FacebookException {
        return getNotes("me", null);
    }
    public ResponseList<Note> getNotes(Reading reading) throws FacebookException {
        return getNotes("me", reading);
    }
    public ResponseList<Note> getNotes(String id) throws FacebookException {
        return getNotes(id, null);
    }
    public ResponseList<Note> getNotes(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createNoteList(get(buildEndpoint(id, "notes", reading)));
    }

    public String createNote(String subject, String message) throws FacebookException {
        return createNote("me", subject, message);
    }
    public String createNote(String id, String subject, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(id, "notes"), new HttpParameter[] {
                            new HttpParameter("subject", subject),
                            new HttpParameter("message", message)
                          }).asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public Note getNote(String noteId) throws FacebookException {
        return getNote(noteId, null);
    }
    public Note getNote(String noteId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createNote(get(buildEndpoint(noteId, reading)));
    }

    public ResponseList<Comment> getNoteComments(String noteId) throws FacebookException {
        return getNoteComments(noteId, null);
    }
    public ResponseList<Comment> getNoteComments(String noteId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getComments(noteId, reading);
    }

    public String commentNote(String noteId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _comment(noteId, message);
    }

    public ResponseList<Like> getNoteLikes(String noteId) throws FacebookException {
        return getNoteLikes(noteId, null);
    }
    public ResponseList<Like> getNoteLikes(String noteId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getLikes(noteId, reading);
    }

    public boolean likeNote(String noteId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(noteId);
    }
    public boolean unlikeNote(String noteId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(noteId);
    }

    /* Notification Methods */

    public ResponseList<Notification> getNotifications() throws FacebookException {
        return getNotifications("me", null);
    }
    public ResponseList<Notification> getNotifications(boolean includeRead) throws FacebookException {
        return getNotifications("me", null, includeRead);
    }
    public ResponseList<Notification> getNotifications(Reading reading) throws FacebookException {
        return getNotifications("me", reading);
    }
    public ResponseList<Notification> getNotifications(Reading reading, boolean includeRead) throws FacebookException {
        return getNotifications("me", reading, includeRead);
    }
    public ResponseList<Notification> getNotifications(String userId) throws FacebookException {
        return getNotifications(userId, null);
    }
    public ResponseList<Notification> getNotifications(String userId, boolean includeRead) throws FacebookException {
        return getNotifications(userId, null, includeRead);
    }
    public ResponseList<Notification> getNotifications(String userId, Reading reading) throws FacebookException {
        return getNotifications(userId, reading, false);
    }
    public ResponseList<Notification> getNotifications(String userId, Reading reading, boolean includeRead) throws FacebookException {
        ensureAuthorizationEnabled();
        String url = buildEndpoint(userId, "notifications", reading);
        HttpResponse res;
        if (includeRead) {
            res = get(url, new HttpParameter[]{new HttpParameter("include_read", 1)});
        } else {
            res = get(url);
        }
        return factory.createNotificationList(res);
    }

    public boolean markNotificationAsRead(String notificationId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(notificationId), new HttpParameter[] {new HttpParameter("unread", 0)});
        return parseBoolean(res);
    }

    /* Page Methods */

    public Page getPage() throws FacebookException {
        return getPage("me", null);
    }
    public Page getPage(Reading reading) throws FacebookException {
        return getPage("me", reading);
    }
    public Page getPage(String pageId) throws FacebookException {
        return getPage(pageId, null);
    }
    public Page getPage(String pageId, Reading reading) throws FacebookException {
        HttpResponse res = get(buildEndpoint(pageId, reading));
        return factory.createPage(res);
    }

    public URL getPagePictureURL() throws FacebookException {
        return getPagePictureURL("me", null);
    }
    public URL getPagePictureURL(PictureSize size) throws FacebookException {
        return getPagePictureURL("me", size);
    }
    public URL getPagePictureURL(String pageId) throws FacebookException {
        return getPagePictureURL(pageId, null);
    }
    public URL getPagePictureURL(String pageId, PictureSize size) throws FacebookException {
        return _getPictureURL(pageId, size);
    }

    public ResponseList<Post> getPromotablePosts() throws FacebookException {
        return getPromotablePosts("me", null);
    }
    public ResponseList<Post> getPromotablePosts(Reading reading) throws FacebookException {
        return getPromotablePosts("me", reading);
    }
    public ResponseList<Post> getPromotablePosts(String pageId) throws FacebookException {
        return getPromotablePosts(pageId, null);
    }
    public ResponseList<Post> getPromotablePosts(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPostList(get(buildEndpoint(pageId, "promotable_posts", reading)));
    }

    public boolean updatePageBasicAttributes(PageUpdate pageUpdate) throws FacebookException {
        return updatePageBasicAttributes("me", pageUpdate);
    }
    public boolean updatePageBasicAttributes(String pageId, PageUpdate pageUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(pageId), pageUpdate.asHttpParameterArray());
        return parseBoolean(res);
    }

    public boolean updatePageProfilePhoto(URL picture) throws FacebookException {
        return updatePageProfilePhoto("me", picture);
    }
    public boolean updatePageProfilePhoto(String pageId, URL picture) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(pageId, "picture"), new HttpParameter[]{new HttpParameter("picture", picture.toString())});
        return parseBoolean(res);
    }

    public boolean updatePageProfilePhoto(Media source) throws FacebookException {
        return updatePageProfilePhoto("me", source);
    }
    public boolean updatePageProfilePhoto(String pageId, Media source) throws FacebookException {
        ensureAuthorizationEnabled();
        List<HttpParameter> httpParams = new ArrayList<HttpParameter>();
        httpParams.add(source.asHttpParameter("source"));
        HttpResponse res = post(buildEndpoint(pageId, "picture"), httpParams.toArray(new HttpParameter[httpParams.size()]));
        return parseBoolean(res);
    }

    public boolean updatePageCoverPhoto(PageCoverUpdate pageCoverUpdate) throws FacebookException {
        return updatePageCoverPhoto("me", pageCoverUpdate);
    }
    public boolean updatePageCoverPhoto(String pageId, PageCoverUpdate pageCoverUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(pageId), pageCoverUpdate.asHttpParameterArray());
        return parseBoolean(res);
    }


    public ResponseList<PageSetting> getPageSettings() throws FacebookException {
        return getPageSettings("me");
    }
    public ResponseList<PageSetting> getPageSettings(String pageId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = get(buildEndpoint(pageId, "settings"));
        return factory.createPageSettingList(res);
    }

    public boolean updatePageSetting(PageSettingUpdate pageSettingUpdate) throws FacebookException {
        return updatePageSetting("me", pageSettingUpdate);
    }
    public boolean updatePageSetting(String pageId, PageSettingUpdate pageSettingUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(pageId, "settings"), pageSettingUpdate.asHttpParameterArray());
        return parseBoolean(res);
    }

    public String postBackdatingFeed(BackdatingPostUpdate backdatingPostUpdate) throws FacebookException {
        return postBackdatingFeed("me", backdatingPostUpdate);
    }
    public String postBackdatingFeed(String pageId, BackdatingPostUpdate backdatingPostUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(pageId, "feed"), backdatingPostUpdate.asHttpParameterArray())
                .asJSONObject();
        return getRawString("id", json);
    }

    public String postPagePhoto(PagePhotoUpdate pagePhotoUpdate) throws FacebookException {
        return postPagePhoto("me", pagePhotoUpdate);
    }
    public String postPagePhoto(String pageId, PagePhotoUpdate pagePhotoUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(pageId, "photos"), pagePhotoUpdate.asHttpParameterArray()).asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public ResponseList<Page> getGlobalBrandChildren(String pageId) throws FacebookException {
        return getGlobalBrandChildren(pageId, null);
    }
    public ResponseList<Page> getGlobalBrandChildren(String pageId, Reading reading) throws FacebookException {
        HttpResponse res = get(buildEndpoint(pageId, "global_brand_children", reading));
        return factory.createPageList(res);
    }

    public ResponseList<Insight> getPageInsights(String pageId) throws FacebookException {
        return getPageInsights(pageId, null);
    }
    public ResponseList<Insight> getPageInsights(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createInsightList(get(buildEndpoint(pageId, "insights", reading)));
    }

    public ResponseList<Tagged> getPageTagged(String pageId) throws FacebookException {
        return getPageTagged(pageId, null);
    }

    public ResponseList<Tagged> getPageTagged(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createTaggedList(get(buildEndpoint(pageId, "tagged", reading)));
    }

    public ResponseList<Milestone> getMilestones() throws FacebookException {
        return getMilestones("me", null);
    }
    public ResponseList<Milestone> getMilestones(Reading reading) throws FacebookException {
        return getMilestones("me", reading);
    }
    public ResponseList<Milestone> getMilestones(String pageId) throws FacebookException {
        return getMilestones(pageId, null);
    }
    public ResponseList<Milestone> getMilestones(String pageId, Reading reading) throws FacebookException {
        return factory.createMilestoneList(get(buildEndpoint(pageId, "milestones", reading)));
    }

    public String createMilestone(MilestoneUpdate milestoneUpdate) throws FacebookException {
        return createMilestone("me", milestoneUpdate);
    }
    public String createMilestone(String pageId, MilestoneUpdate milestoneUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(pageId, "milestones"), milestoneUpdate.asHttpParameterArray())
                          .asJSONObject();
        return getRawString("id", json);
    }

    public boolean deleteMilestone(String milestoneId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(milestoneId));
        return parseBoolean(res);
    }

    public ResponseList<Admin> getPageAdmins() throws FacebookException {
        return getPageAdmins("me", null);
    }
    public ResponseList<Admin> getPageAdmins(Reading reading) throws FacebookException {
        return getPageAdmins("me", reading);
    }
    public ResponseList<Admin> getPageAdmins(String pageId) throws FacebookException {
        return getPageAdmins(pageId, null);
    }
    public ResponseList<Admin> getPageAdmins(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createAdminList(get(buildEndpoint(pageId, "admins", reading)));
    }

    public ResponseList<Tab> getTabs() throws FacebookException {
        return getTabs("me", null);
    }
    public ResponseList<Tab> getTabs(Reading reading) throws FacebookException {
        return getTabs("me", reading);
    }
    public ResponseList<Tab> getTabs(String pageId) throws FacebookException {
        return getTabs(pageId, null);
    }
    public ResponseList<Tab> getTabs(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createTabList(get(buildEndpoint(pageId, "tabs", reading)));
    }

    public ResponseList<Tab> getInstalledTabs(List<String> appIds) throws FacebookException {
        return getInstalledTabs("me", appIds, null);
    }
    public ResponseList<Tab> getInstalledTabs(List<String> appIds, Reading reading) throws FacebookException {
        return getInstalledTabs("me", appIds, reading);
    }
    public ResponseList<Tab> getInstalledTabs(String pageId, List<String> appIds) throws FacebookException {
        return getInstalledTabs(pageId, appIds, null);
    }
    public ResponseList<Tab> getInstalledTabs(String pageId, List<String> appIds, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        String _appIds = z_F4JInternalStringUtil.join(appIds.toArray(new String[appIds.size()]), ",");
        return factory.createTabList(get(buildEndpoint(pageId, "tabs/" + _appIds, reading)));
    }

    public boolean installTab(String appId) throws FacebookException {
        return installTab("me", appId);
    }
    public boolean installTab(String pageId, String appId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(pageId, "tabs"), new HttpParameter[]{new HttpParameter("app_id", appId)});
        return parseBoolean(res);
    }

    public boolean updateTab(String tabId, TabUpdate tabUpdate) throws FacebookException {
        return updateTab("me", tabId, tabUpdate);
    }
    public boolean updateTab(String pageId, String tabId, TabUpdate tabUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(pageId, "tabs/" + tabId), tabUpdate.asHttpParameterArray());
        return parseBoolean(res);
    }

    public boolean deleteTab(String tabId) throws FacebookException {
        return deleteTab("me", tabId);
    }
    public boolean deleteTab(String pageId, String tabId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(pageId, "tabs/" + tabId));
        return parseBoolean(res);
    }

    public boolean displayPagePost(String postId, boolean isHidden) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(postId), new HttpParameter[]{new HttpParameter("is_hidden", isHidden)});
        return parseBoolean(res);
    }

    public ResponseList<User> getBlocked() throws FacebookException {
        return getBlocked("me", null);
    }
    public ResponseList<User> getBlocked(Reading reading) throws FacebookException {
        return getBlocked("me", reading);
    }
    public ResponseList<User> getBlocked(String pageId) throws FacebookException {
        return getBlocked(pageId, null);
    }
    public ResponseList<User> getBlocked(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createUserList(get(buildEndpoint(pageId, "blocked", reading)));
    }

    public Map<String, Boolean> block(List<String> userIds) throws FacebookException {
        return block("me", userIds);
    }
    public Map<String, Boolean> block(String pageId, List<String> userIds) throws FacebookException {
        ensureAuthorizationEnabled();
        String _userIds = z_F4JInternalStringUtil.join(userIds.toArray(new String[userIds.size()]), ",");
        HttpResponse res = post(buildEndpoint(pageId, "blocked"), new HttpParameter[]{new HttpParameter("uid", _userIds)});
        Map<String, Boolean> blocks = new HashMap<String, Boolean>();
        JSONObject jsonObject = res.asJSONObject();
        Iterator<String> uids = jsonObject.keys();
        while (uids.hasNext()) {
            String uid = uids.next();
            boolean result = getBoolean(uid, jsonObject);
            blocks.put(uid, result);
        }
        return blocks;
    }

    public boolean unblock(String userId) throws FacebookException {
        return unblock("me", userId);
    }
    public boolean unblock(String pageId, String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(pageId, "blocked"), new HttpParameter[]{new HttpParameter("uid", userId)});
        return parseBoolean(res);
    }

    public ResponseList<Offer> getOffers() throws FacebookException {
        return getOffers("me", null);
    }
    public ResponseList<Offer> getOffers(Reading reading) throws FacebookException {
        return getOffers("me", reading);
    }
    public ResponseList<Offer> getOffers(String pageId) throws FacebookException {
        return getOffers(pageId, null);
    }
    public ResponseList<Offer> getOffers(String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createOfferList(get(buildEndpoint(pageId, "offers", reading)));
    }

    public String createOffer(OfferUpdate offerUpdate) throws FacebookException {
        return createOffer("me", offerUpdate);
    }
    public String createOffer(String pageId, OfferUpdate offerUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(pageId, "offers"), offerUpdate.asHttpParameterArray())
                          .asJSONObject();
        return getRawString("id", json);
    }

    public boolean deleteOffer(String offerId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(offerId));
        return parseBoolean(res);
    }

    public Offer getOffer(String offerId) throws FacebookException {
        return factory.createOffer(get(buildEndpoint(offerId)));
    }

    public Page getLikedPage(String pageId) throws FacebookException {
        return getLikedPage("me", pageId, null);
    }
    public Page getLikedPage(String pageId, Reading reading) throws FacebookException {
        return getLikedPage("me", pageId, reading);
    }
    public Page getLikedPage(String userId, String pageId) throws FacebookException {
        return getLikedPage(userId, pageId, null);
    }
    public Page getLikedPage(String userId, String pageId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = get(buildEndpoint(userId, "likes/" + pageId, reading));
        ResponseList<Page> list = factory.createPageList(res);
        return list.size() == 0 ? null : list.get(0);
    }

    /* Permission Methods */

    public List<Permission> getPermissions() throws FacebookException {
        return getPermissions("me");
    }
    public List<Permission> getPermissions(String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPermissions(get(buildEndpoint(userId, "permissions")));
    }

    public boolean revokeAllPermissions() throws FacebookException {
        return revokeAllPermissions("me");
    }

    public boolean revokeAllPermissions(String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(userId, "permissions"));
        return parseBoolean(res);
    }

    public boolean revokePermission(String permissionName) throws FacebookException {
        return revokePermission("me", permissionName);
    }
    public boolean revokePermission(String userId, String permissionName) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(userId, "permissions/" + permissionName));
        return parseBoolean(res);
    }

    public boolean deleteAllPermissions() throws FacebookException {
        return revokeAllPermissions();
    }

    public boolean deleteAllPermissions(String userId) throws FacebookException {
        return revokeAllPermissions(userId);
    }

    public boolean deletePermission(String permissionName) throws FacebookException {
        return revokePermission(permissionName);
    }
    public boolean deletePermission(String userId, String permissionName) throws FacebookException {
        return revokePermission(userId, permissionName);
    }

    /* Photo Methods */

    public ResponseList<Photo> getUploadedPhotos() throws FacebookException {
        return getUploadedPhotos("me", null);
    }
    public ResponseList<Photo> getUploadedPhotos(Reading reading) throws FacebookException {
        return getUploadedPhotos("me", reading);
    }
    public ResponseList<Photo> getUploadedPhotos(String id) throws FacebookException {
        return getUploadedPhotos(id, null);
    }
    public ResponseList<Photo> getUploadedPhotos(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPhotoList(get(buildEndpoint(id, "photos/uploaded", reading)));
    }

    public ResponseList<Photo> getPhotos() throws FacebookException {
        return getPhotos("me", null);
    }
    public ResponseList<Photo> getPhotos(Reading reading) throws FacebookException {
        return getPhotos("me", reading);
    }
    public ResponseList<Photo> getPhotos(String id) throws FacebookException {
        return getPhotos(id, null);
    }
    public ResponseList<Photo> getPhotos(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPhotoList(get(buildEndpoint(id, "photos", reading)));
    }

    public Photo getPhoto(String photoId) throws FacebookException {
        return getPhoto(photoId, null);
    }
    public Photo getPhoto(String photoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPhoto(get(buildEndpoint(photoId, reading)));
    }

    public ResponseList<Comment> getPhotoComments(String photoId) throws FacebookException {
        return getPhotoComments(photoId, null);
    }
    public ResponseList<Comment> getPhotoComments(String photoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getComments(photoId, reading);
    }

    public ResponseList<Post> getPhotoSharedposts(String photoId) throws FacebookException {
        return getPhotoSharedposts(photoId, null);
    }

    public ResponseList<Post> getPhotoSharedposts(String photoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getSharedPosts(photoId, reading);
    }

    public String commentPhoto(String photoId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _comment(photoId, message);
    }

    public String commentPhoto(String photoId, CommentUpdate commentUpdate) throws FacebookException {
        return _comment(photoId, commentUpdate);
    }

    public ResponseList<Like> getPhotoLikes(String photoId) throws FacebookException {
        return getPhotoLikes(photoId, null);
    }
    public ResponseList<Like> getPhotoLikes(String photoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getLikes(photoId, reading);
    }

    public boolean likePhoto(String photoId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(photoId);
    }
    public boolean unlikePhoto(String photoId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(photoId);
    }

    public URL getPhotoURL(String photoId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = get(buildEndpoint(photoId, "picture"));
        try {
            return new URL(res.getResponseHeader("Location"));
        } catch (MalformedURLException urle) {
            throw new FacebookException(urle.getMessage(), urle);
        }
    }

    public ResponseList<Tag> getTagsOnPhoto(String photoId) throws FacebookException {
        return getTagsOnPhoto(photoId, null);
    }
    public ResponseList<Tag> getTagsOnPhoto(String photoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createTagList(get(buildEndpoint(photoId, "tags", reading)));
    }

    public boolean addTagToPhoto(String photoId, String toUserId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(photoId, "tags"), new HttpParameter[]{new HttpParameter("to", toUserId)});
        return parseBoolean(res);
    }

    public boolean addTagToPhoto(String photoId, TagUpdate tagUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(photoId, "tags"), tagUpdate.asHttpParameterArray());
        return parseBoolean(res);
   }

    public boolean addTagToPhoto(String photoId, List<String> toUserIds) throws FacebookException {
        ensureAuthorizationEnabled();
        if (toUserIds.size() == 0) throw new IllegalArgumentException("toUserIds size 0");
        if (toUserIds.size() == 1) {
            return addTagToPhoto(photoId, toUserIds.get(0));
        }
        List<Map<String, String>> tags = new ArrayList<Map<String, String>>(toUserIds.size());
        for (String toUserId : toUserIds) {
            Map<String, String> map = new HashMap<String, String>(1);
            map.put("tag_uid", toUserId);
            tags.add(map);
        }
        HttpResponse res = post(buildEndpoint(photoId, "tags"), new HttpParameter[]{new HttpParameter("tags", new JSONArray(tags).toString())});
        return parseBoolean(res);
    }

    public boolean updateTagOnPhoto(String photoId, TagUpdate tagUpdate) throws FacebookException {
        return addTagToPhoto(photoId, tagUpdate);
    }

    public boolean deleteTagOnPhoto(String photoId, String toUserId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(photoId, "tags"), new HttpParameter[]{new HttpParameter("to", toUserId)});
        return parseBoolean(res);
    }

    public String postPhoto(Media source) throws FacebookException {
        return postPhoto("me", source);
    }
    public String postPhoto(PhotoUpdate photoUpdate) throws FacebookException {
        return postPhoto("me", photoUpdate);
    }
    public String postPhoto(String userId, Media source) throws FacebookException {
        return postPhoto(userId, new PhotoUpdate(source));
    }
    public String postPhoto(String userId, PhotoUpdate photoUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(userId, "photos"), photoUpdate.asHttpParameterArray()).asJSONObject();
        return getRawString("id", json);
    }

    public boolean deletePhoto(String photoId) throws FacebookException {
        HttpResponse res = delete(buildEndpoint(photoId));
        return parseBoolean(res);
    }

    /* Poke Methods */

    public ResponseList<Poke> getPokes() throws FacebookException {
        return getPokes("me", null);
    }
    public ResponseList<Poke> getPokes(Reading reading) throws FacebookException {
        return getPokes("me", reading);
    }
    public ResponseList<Poke> getPokes(String userId) throws FacebookException {
        return getPokes(userId, null);
    }
    public ResponseList<Poke> getPokes(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createPokeList(get(buildEndpoint(userId, "pokes", reading)));
    }

    /* Question Methods */

    public ResponseList<Question> getQuestions() throws FacebookException {
        return getQuestions("me", null);
    }
    public ResponseList<Question> getQuestions(Reading reading) throws FacebookException {
        return getQuestions("me", reading);
    }
    public ResponseList<Question> getQuestions(String id) throws FacebookException {
        return getQuestions(id, null);
    }
    public ResponseList<Question> getQuestions(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createQuestionList(get(buildEndpoint(id, "questions", reading)));
    }

    public Question getQuestion(String questionId) throws FacebookException {
        return getQuestion(questionId, null);
    }
    public Question getQuestion(String questionId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createQuestion(get(buildEndpoint(questionId, reading)));
    }

    public String createQuestion(QuestionUpdate questionUpdate) throws FacebookException {
        return createQuestion("me", questionUpdate);
    }
    public String createQuestion(String id, QuestionUpdate questionUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(id, "questions"), questionUpdate.asHttpParameterArray())
                          .asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public boolean deleteQuestion(String questionId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(questionId));
        return parseBoolean(res);
   }

    public ResponseList<Option> getQuestionOptions(String questionId) throws FacebookException {
        return getQuestionOptions(questionId, null);
    }
    public ResponseList<Option> getQuestionOptions(String questionId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createQuestionOptionList(get(buildEndpoint(questionId, "options", reading)));
    }

    public String addQuestionOption(String questionId, String optionDescription) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(questionId, "options"),
                                new HttpParameter[]{new HttpParameter("option", optionDescription)})
                          .asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
   }

    public ResponseList<QuestionVotes> getQuestionOptionVotes(String questionId) throws FacebookException {
        ensureAuthorizationEnabled();
        Reading reading = new Reading().fields("votes");
        return factory.createQuestionVotesList(get(buildEndpoint(questionId, "options", reading)));
    }

    /* Game Methods */

    public ResponseList<Score> getScores() throws FacebookException {
        return getScores("me", null);
    }
    public ResponseList<Score> getScores(Reading reading) throws FacebookException {
        return getScores("me", reading);
    }
    public ResponseList<Score> getScores(String userId) throws FacebookException {
        return getScores(userId, null);
    }
    public ResponseList<Score> getScores(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createScoreList(get(buildEndpoint(userId, "scores", reading)));
    }

    public boolean postScore(int scoreValue) throws FacebookException {
        return postScore("me", scoreValue);
    }
    public boolean postScore(String userId, int scoreValue) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(userId, "scores"),
                            new HttpParameter[] {new HttpParameter("score", scoreValue)});
        return parseBoolean(res);
    }

    public boolean deleteScore() throws FacebookException {
        return deleteScore("me");
    }
    public boolean deleteScore(String userId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(userId, "scores"));
        return parseBoolean(res);
    }

    /* Subscribe Methods */

    public ResponseList<Subscribedto> getSubscribedto() throws FacebookException {
        return getSubscribedto("me", null);
    }
    public ResponseList<Subscribedto> getSubscribedto(Reading reading) throws FacebookException {
        return getSubscribedto("me", reading);
    }
    public ResponseList<Subscribedto> getSubscribedto(String userId) throws FacebookException {
        return getSubscribedto(userId, null);
    }
    public ResponseList<Subscribedto> getSubscribedto(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createSubscribedtoList(get(buildEndpoint(userId, "subscribedto", reading)));
    }

    public ResponseList<Subscriber> getSubscribers() throws FacebookException {
        return getSubscribers("me", null);
    }
    public ResponseList<Subscriber> getSubscribers(Reading reading) throws FacebookException {
        return getSubscribers("me", reading);
    }
    public ResponseList<Subscriber> getSubscribers(String userId) throws FacebookException {
        return getSubscribers(userId, null);
    }
    public ResponseList<Subscriber> getSubscribers(String userId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createSubscriberList(get(buildEndpoint(userId, "subscribers", reading)));
    }

    /* Video Methods */

    public ResponseList<Video> getVideos() throws FacebookException {
        return getVideos("me", null);
    }
    public ResponseList<Video> getVideos(Reading reading) throws FacebookException {
        return getVideos("me", reading);
    }
    public ResponseList<Video> getVideos(String id) throws FacebookException {
        return getVideos(id, null);
    }
    public ResponseList<Video> getVideos(String id, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createVideoList(get(buildEndpoint(id, "videos", reading)));
    }

    public String postVideo(VideoUpdate videoUpdate) throws FacebookException {
        return postVideo("me", videoUpdate);
    }
    public String postVideo(String id, VideoUpdate videoUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildVideoEndpoint(id, "videos"), videoUpdate.asHttpParameterArray()).asJSONObject();
        return getRawString("id", json);
    }

    public Video getVideo(String videoId) throws FacebookException {
        return getVideo(videoId, null);
    }
    public Video getVideo(String videoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createVideo(get(buildEndpoint(videoId, reading)));
    }

    public ResponseList<Like> getVideoLikes(String videoId) throws FacebookException {
        return getVideoLikes(videoId, null);
    }
    public ResponseList<Like> getVideoLikes(String videoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getLikes(videoId, reading);
    }

    public ResponseList<Post> getVideoSharedposts(String videoId) throws FacebookException {
        return getVideoSharedposts(videoId, null);
    }

    public ResponseList<Post> getVideoSharedposts(String videoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getSharedPosts(videoId, reading);
    }

    public boolean likeVideo(String videoId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _like(videoId);
    }
    public boolean unlikeVideo(String videoId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _unlike(videoId);
    }

    public ResponseList<Comment> getVideoComments(String videoId) throws FacebookException {
        return getVideoComments(videoId, null);
    }
    public ResponseList<Comment> getVideoComments(String videoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getComments(videoId, reading);
    }

    public String commentVideo(String videoId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        return _comment(videoId, message);
    }

    public String commentVideo(String videoId, CommentUpdate commentUpdate) throws FacebookException {
        return _comment(videoId, commentUpdate);
    }

    public URL getVideoCover(String videoId) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getPictureURL(videoId, null);
    }

    /* Insight Methods */

    public ResponseList<Insight> getInsights(String objectId, String metric) throws FacebookException {
        return getInsights(objectId, metric, null);
    }
    public ResponseList<Insight> getInsights(String objectId, String metric, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return factory.createInsightList(get(buildEndpoint(objectId, "insights/" + metric, reading)));
    }

    /* Search Methods */

    public ResponseList<Post> searchPosts(String query) throws FacebookException {
        return searchPosts(query, null);
    }

    public ResponseList<Post> searchPosts(String query, Reading reading) throws FacebookException {
        return factory.createPostList(get(buildSearchEndpoint(query, "post", reading)));
    }

    public ResponseList<User> searchUsers(String query) throws FacebookException {
        return searchUsers(query, null);
    }

    public ResponseList<User> searchUsers(String query, Reading reading) throws FacebookException {
        return factory.createUserList(get(buildSearchEndpoint(query, "user", reading)));
    }

    public ResponseList<Event> searchEvents(String query) throws FacebookException {
        return searchEvents(query, null);
    }

    public ResponseList<Event> searchEvents(String query, Reading reading) throws FacebookException {
        return factory.createEventList(get(buildSearchEndpoint(query, "event", reading)));
    }

    public ResponseList<Group> searchGroups(String query) throws FacebookException {
        return searchGroups(query, null);
    }

    public ResponseList<Group> searchGroups(String query, Reading reading) throws FacebookException {
        return factory.createGroupList(get(buildSearchEndpoint(query, "group", reading)));
    }

    public ResponseList<Place> searchPlaces(String query) throws FacebookException {
        return searchPlaces(query, null);
    }

    public ResponseList<Place> searchPlaces(String query, Reading reading) throws FacebookException {
        return factory.createPlaceList(get(buildSearchEndpoint(query, "place", reading)));
    }

    public ResponseList<Place> searchPlaces(String query, GeoLocation center, int distance) throws FacebookException {
        return searchPlaces(query, center, distance, null);
    }

    public ResponseList<Place> searchPlaces(String query, GeoLocation center, int distance, Reading reading) throws FacebookException {
        String url = buildSearchEndpoint(query, "place", reading)
                        + "&center=" + HttpParameter.encode(center.asParameterString())
                        + "&distance=" + distance;
        return factory.createPlaceList(get(url));
    }

    public ResponseList<Checkin> searchCheckins() throws FacebookException {
        return searchCheckins(null);
    }

    public ResponseList<Checkin> searchCheckins(Reading reading) throws FacebookException {
        return factory.createCheckinList(get(buildSearchEndpoint(null, "checkin", reading)));
    }

    public ResponseList<Location> searchLocations(GeoLocation center, int distance) throws FacebookException {
        return searchLocations(center, distance, null);
    }

    public ResponseList<Location> searchLocations(GeoLocation center, int distance, Reading reading) throws FacebookException {
        String url = buildSearchEndpoint(null, "location", reading)
                + "&center=" + HttpParameter.encode(center.asParameterString())
                + "&distance=" + distance;
        return factory.createLocationList(get(url));
    }

    public ResponseList<Location> searchLocations(String placeId) throws FacebookException {
        return searchLocations(placeId, null);
    }

    public ResponseList<Location> searchLocations(String placeId, Reading reading) throws FacebookException {
        String url = buildSearchEndpoint(null, "location", reading)
                        + "&place=" + placeId;
        return factory.createLocationList(get(url));
    }

    public ResponseList<JSONObject> search(String query) throws FacebookException {
        return search(query, null);
    }

    public ResponseList<JSONObject> search(String query, Reading reading) throws FacebookException {
        return search(query, null, reading);
    }

    public ResponseList<JSONObject> search(String query, String type, Reading reading) throws FacebookException {
        String url = buildSearchEndpoint(query, type, reading);
        return factory.createJSONObjectList(get(url));
    }

    public ResponseList<Page> searchPages(String query) throws FacebookException {
        return searchPages(query, null);
    }

    public ResponseList<Page> searchPages(String query, Reading reading) throws FacebookException {
        String url = buildSearchEndpoint(query, "page", reading);
        return factory.createPageList(get(url));
    }

    /* Reactions Methods */

    public ResponseList<Reaction> getAlbumReactions(String albumId) throws FacebookException {
        return _getReactions(albumId, null);
    }

    public ResponseList<Reaction> getAlbumReactions(String albumId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getReactions(albumId, reading);
    }

    public ResponseList<Reaction> getPhotoReactions(String photoId) throws FacebookException {
        return _getReactions(photoId, null);
    }

    public ResponseList<Reaction> getPhotoReactions(String photoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getReactions(photoId, reading);
    }

    public ResponseList<Reaction> getPostReactions(String postId) throws FacebookException {
        return _getReactions(postId, null);
    }

    public ResponseList<Reaction> getPostReactions(String postId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getReactions(postId, reading);
    }

    public ResponseList<Reaction> getVideoReactions(String videoId) throws FacebookException {
        return _getReactions(videoId, null);
    }

    public ResponseList<Reaction> getVideoReactions(String videoId, Reading reading) throws FacebookException {
        ensureAuthorizationEnabled();
        return _getReactions(videoId, reading);
    }

    /* FQL Methods */

    public JSONArray executeFQL(String query) throws FacebookException {
        return executeFQL(query, null);
    }
    public JSONArray executeFQL(String query, Locale locale) throws FacebookException {
        ensureAuthorizationEnabled();
        String url = "";
        try {
            url = conf.getRestBaseURL() + "fql?q=" + URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
        }
        if (locale != null) {
            url += "&locale=" + HttpParameter.encode(locale.toString());
        }

        JSONObject json = get(url).asJSONObject();
        try {
            return json.getJSONArray("data");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public Map<String, JSONArray> executeMultiFQL(Map<String, String> queries) throws FacebookException {
        return executeMultiFQL(queries, null);
    }
    public Map<String, JSONArray> executeMultiFQL(Map<String, String> queries, Locale locale) throws FacebookException {
        ensureAuthorizationEnabled();
        String url = conf.getRestBaseURL() + "fql?q=" + convertQueriesToJson(queries);
        if (locale != null) {
            url += "&locale=" + HttpParameter.encode(locale.toString());
        }
        JSONObject json = get(url)
                .asJSONObject();
        Map<String, JSONArray> result = new HashMap<String, JSONArray>();
        try {
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                JSONArray resultSets = jsonObject.getJSONArray("fql_result_set");
                result.put(name, resultSets);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
        return result;
    }

    private String convertQueriesToJson(Map<String, String> queries) {
        List<String> jsons = new ArrayList<String>();
        for (String name : queries.keySet()) {
            String json = "";
            try {
                json = "%22" + URLEncoder.encode(name, "UTF-8") + "%22" + ":" + "%22" + URLEncoder.encode(queries.get(name), "UTF-8") + "%22";
            } catch (UnsupportedEncodingException ignore) {
            }
            jsons.add(json);
        }
        return "{" + z_F4JInternalStringUtil.join((String[]) jsons.toArray(new String[jsons.size()])) + "}";
    }

    /* Test User Methods */

    public TestUser createTestUser(String appId) throws FacebookException {
        return createTestUser(appId, null, null, null);
    }

    public TestUser createTestUser(String appId, String name, String locale, String permissions) throws FacebookException {
        return createTestUser(appId, name, locale, permissions, true);
    }

    public TestUser createTestUser(String appId, String name, String locale, String permissions, boolean installed) throws FacebookException {
        ensureAuthorizationEnabled();
        String _locale = "en_US";
        if (locale != null) _locale = locale;
        String url = conf.getRestBaseURL() + appId + "/accounts/test-users" +
                    "?installed=" + Boolean.toString(installed) +
                    "&locale=" + HttpParameter.encode(_locale);
        if (name != null) {
            url += "&name=" + HttpParameter.encode(name);
        }
        if (permissions != null) {
            url += "&permissions=" + HttpParameter.encode(permissions);
        }
        return factory.createTestUser(post(url));
    }

    public ResponseList<TestUser> getTestUsers(String appId) throws FacebookException {
        return getTestUsers(appId, null);
    }


    public ResponseList<TestUser> getTestUsers(String appId, Integer limit) throws FacebookException {
       ensureAuthorizationEnabled();
       HttpResponse res = get(conf.getRestBaseURL() + appId + "/accounts/test-users" + (limit != null ? "?limit=" + limit : ""));
       return factory.createTestUserList(res);
   }

    public boolean deleteTestUser(String testUserId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(conf.getRestBaseURL() + testUserId);
        return parseBoolean(res);
    }

    public boolean makeFriendTestUser(TestUser testUser1, TestUser testUser2) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(testUser1.getId(), "friends/" + testUser2.getId()),
                                new HttpParameter[]{new HttpParameter("access_token", testUser1.getAccessToken())});
        if (!parseBoolean(res)) {
            return false;
        }
        res = post(buildEndpoint(testUser2.getId(), "friends/" + testUser1.getId()),
                                new HttpParameter[]{new HttpParameter("access_token", testUser2.getAccessToken())});
        return parseBoolean(res);
    }

    /* Paging */

    @SuppressWarnings("unchecked")
    public <T> ResponseList<T> fetchNext(Paging<T> paging) throws FacebookException {
        ensureAuthorizationEnabled();
        URL url = paging.getNext();
        if (url == null) {
            return null;
        }
        return (ResponseList<T>) fetchPaging(url, paging.getJSONObjectType());
    }

    @SuppressWarnings("unchecked")
    public <T> ResponseList<T> fetchPrevious(Paging<T> paging) throws FacebookException {
        ensureAuthorizationEnabled();
        URL url = paging.getPrevious();
        if (url == null) {
            return null;
        }
        return (ResponseList<T>) fetchPaging(url, paging.getJSONObjectType());
    }

    private <T> ResponseList<T> fetchPaging(URL url, Class<T> jsonObjectType) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = getRaw(url.toString());
        return (ResponseList<T>) factory.createResponseList(res, jsonObjectType);
    }


    /* Batch Requests Methods */

    public List<BatchResponse> executeBatch(BatchRequests<BatchRequest> requests) throws FacebookException {
        ensureAuthorizationEnabled();

        JSONArray jsonArray = post(buildEndpoint(""), requests.asHttpParameterArray()).asJSONArray();
        int size = jsonArray.length();
        List<BatchResponse> result = new ArrayList<BatchResponse>(size);
        for (int i = 0; i < size; i++) {
            try {
                if (jsonArray.isNull(i)) {
                    result.add(null);
                } else {
                    JSONObject json = jsonArray.getJSONObject(i);
                    result.add(new BatchResponseImpl(json));
                }
            } catch (JSONException e) {
                throw new FacebookException(e);
            }
        }
        return result;
    }


    /* raw api methods */

    public RawAPIResponse callGetAPI(String relativeUrl) throws FacebookException {
        return callGetAPI(relativeUrl, new HashMap<String, String>());
    }
    public RawAPIResponse callGetAPI(String relativeUrl, Map<String, String> parameters) throws FacebookException {
        ensureAuthorizationEnabled();

        String path = relativeUrl;
        if (relativeUrl.startsWith("/")) {
            path = relativeUrl.substring(1);
        }

        // not supports "JSONStore" option because this method returns the json object itself.
        HttpResponse res = get(buildEndpoint(path, parameters));
        return new RawAPIResponseImpl(res);
    }
    public RawAPIResponse callGetAPI(String relativeUrl, HttpParameter... parameters) throws FacebookException {
        ensureAuthorizationEnabled();

        String path = relativeUrl;
        if (relativeUrl.startsWith("/")) {
            path = relativeUrl.substring(1);
        }

        // not supports "JSONStore" option because this method returns the json object itself.
        HttpResponse res = get(buildEndpoint(path), parameters);
        return new RawAPIResponseImpl(res);
    }

    public RawAPIResponse callPostAPI(String relativeUrl) throws FacebookException {
        return callPostAPI(relativeUrl, new HashMap<String, String>());
    }
    public RawAPIResponse callPostAPI(String relativeUrl, Map<String, String> parameters) throws FacebookException {
        ensureAuthorizationEnabled();

        String path = relativeUrl;
        if (relativeUrl.startsWith("/")) {
            path = relativeUrl.substring(1);
        }

        HttpResponse res;
        if (parameters != null && parameters.size() > 0) {
            final HttpParameter[] httpParameters = new HttpParameter[parameters.size()];
            int i = 0;
            for (final String p : parameters.keySet()) {
                httpParameters[i++] = new HttpParameter(p, parameters.get(p));
            }
            res = post(buildEndpoint(path), httpParameters);
        } else {
            res = post(buildEndpoint(path));
        }

        // not supports "JSONStore" option because this method returns the json object itself.
        return new RawAPIResponseImpl(res);
    }
    public RawAPIResponse callPostAPI(String relativeUrl, HttpParameter... parameters) throws FacebookException {
        ensureAuthorizationEnabled();

        String path = relativeUrl;
        if (relativeUrl.startsWith("/")) {
            path = relativeUrl.substring(1);
        }

        HttpResponse res;
        if (parameters != null && parameters.length > 0) {
            res = post(buildEndpoint(path), parameters);
        } else {
            res = post(buildEndpoint(path));
        }

        // not supports "JSONStore" option because this method returns the json object itself.
        return new RawAPIResponseImpl(res);
    }

    public RawAPIResponse callDeleteAPI(String relativeUrl) throws FacebookException {
        return callDeleteAPI(relativeUrl, null);
    }
    public RawAPIResponse callDeleteAPI(String relativeUrl, Map<String, String> parameters) throws FacebookException {
        ensureAuthorizationEnabled();

        String path = relativeUrl;
        if (relativeUrl.startsWith("/")) {
            path = relativeUrl.substring(1);
        }

        // not supports "JSONStore" option because this method returns the json object itself.
        HttpResponse res = delete(buildEndpoint(path, parameters));
        return new RawAPIResponseImpl(res);
    }

    /* common methods */

    private ResponseList<Comment> _getComments(String objectId, Reading reading) throws FacebookException {
        return factory.createCommentList(get(buildEndpoint(objectId, "comments", reading)));
    }

    private ResponseList<Like> _getLikes(String objectId, Reading reading) throws FacebookException {
        return factory.createLikeList(get(buildEndpoint(objectId, "likes", reading)));
    }

    private ResponseList<Reaction> _getReactions(String objectId, Reading reading) throws FacebookException {
        return factory.createReactionList(get(buildEndpoint(objectId, "reactions", reading)));
    }

    private URL _getPictureURL(String objectId, PictureSize size) throws FacebookException {
        String url = buildEndpoint(objectId, "picture");
        HttpResponse res;
        if (size != null) {
            res = get(url, new HttpParameter[]{new HttpParameter("type", size.toString())});
        } else {
            res = get(url);
        }
        try {
            return new URL(res.getResponseHeader("Location"));
        } catch (MalformedURLException urle) {
            throw new FacebookException(urle.getMessage(), urle);
        }
    }
     private URL _getPictureURL(String objectId, int width, int height) throws FacebookException {
        String url = buildEndpoint(objectId, "picture");
        HttpResponse res;
        if (width > 0 && height > 0) {
            res = get(url, new HttpParameter[] { new HttpParameter("width", Integer.toString(width)), new HttpParameter("height", Integer.toString(height)) });
        } else {
            res = get(url);
        }
        try {
            return new URL(res.getResponseHeader("Location"));
        } catch (MalformedURLException urle) {
            throw new FacebookException(urle.getMessage(), urle);
        }
    }

    private URL _getSSLPictureURL(String objectId, PictureSize size) throws FacebookException {
        String url = buildEndpoint(objectId, "picture");
        HttpResponse res;
        HttpParameter[] params = new HttpParameter[]{new HttpParameter("return_ssl_resources", "1")};
        if (size != null) {
            params = HttpParameter.merge(params, new HttpParameter("type", size.toString()));
        }
        res = get(url, params);
        try {
            return new URL(res.getResponseHeader("Location"));
        } catch (MalformedURLException urle) {
            throw new FacebookException(urle.getMessage(), urle);
        }
    }

    private ResponseList<Post> _getSharedPosts(String objectId, Reading reading) throws FacebookException {
        return factory.createPostList(get(buildEndpoint(objectId, "sharedposts", reading)));
    }

    private String _answer(String objectId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(objectId, "messages"),
                new HttpParameter[]{new HttpParameter("message", message)})
                .asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    private String _comment(String objectId, String message) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(objectId, "comments"),
                                new HttpParameter[]{new HttpParameter("message", message)})
                          .asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    private String _comment(String objectId, CommentUpdate commentUpdate) throws FacebookException {
        ensureAuthorizationEnabled();
        JSONObject json = post(buildEndpoint(objectId, "comments"), commentUpdate.asHttpParameterArray())
                .asJSONObject();
        return getRawString("id", json);
    }

    private boolean _like(String objectId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = post(buildEndpoint(objectId, "likes"));
        return parseBoolean(res);
    }

    private boolean _unlike(String objectId) throws FacebookException {
        ensureAuthorizationEnabled();
        HttpResponse res = delete(buildEndpoint(objectId, "likes"));
        return parseBoolean(res);
    }

    private String _postLink(String objectId, URL link, String message)
            throws FacebookException {
        HttpParameter[] httpParameters = {new HttpParameter("link", link.toString())};
        if (message != null) {
            httpParameters = HttpParameter.merge(
                                httpParameters,
                                new HttpParameter[]{new HttpParameter("message", message)}
                             );
        }
        JSONObject json = post(buildEndpoint(objectId, "feed"), httpParameters)
                          .asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    private String _postStatusMessage(String objectId, String message) throws FacebookException {
        JSONObject json = post(buildEndpoint(objectId, "feed"),
                            new HttpParameter[] {new HttpParameter("message", message)}
                          ).asJSONObject();
        try {
            return json.getString("id");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    private boolean parseBoolean(HttpResponse res) throws FacebookException {
        String s = res.asString().trim();
        if (!s.startsWith("{")) {
            return Boolean.valueOf(s);
        }
        try {
            return res.asJSONObject().getBoolean("success");
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }


    /* http methods */

    private HttpResponse get(String url) throws FacebookException {
        if (!conf.isMBeanEnabled()) {
            return http.get(url, auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.get(url, auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                FacebookAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    private HttpResponse get(String url, HttpParameter[] parameters) throws FacebookException {
        if (!conf.isMBeanEnabled()) {
            return http.get(url, parameters, (containsAccessToken(parameters) ? null : auth));
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.get(url, parameters, (containsAccessToken(parameters) ? null : auth));
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                FacebookAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    private HttpResponse getRaw(String url) throws FacebookException {
        if (!conf.isMBeanEnabled()) {
            return http.get(url);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.get(url);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                FacebookAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    private HttpResponse post(String url) throws FacebookException {
        if (!conf.isMBeanEnabled()) {
            return http.post(url, auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.post(url, auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                FacebookAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    private HttpResponse post(String url, HttpParameter[] parameters) throws FacebookException {
        if (!conf.isMBeanEnabled()) {
            return http.post(url, parameters, (containsAccessToken(parameters) ? null : auth));
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.post(url, parameters, (containsAccessToken(parameters) ? null : auth));
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                FacebookAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    private HttpResponse delete(String url) throws FacebookException {
        if (!conf.isMBeanEnabled()) {
            return http.delete(url, auth);
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.delete(url, auth);
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                FacebookAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }

    private HttpResponse delete(String url, HttpParameter[] parameters) throws FacebookException {
        if (!conf.isMBeanEnabled()) {
            return http.delete(url, parameters, (containsAccessToken(parameters) ? null : auth));
        } else {
            // intercept HTTP call for monitoring purposes
            HttpResponse response = null;
            long start = System.currentTimeMillis();
            try {
                response = http.delete(url, parameters, (containsAccessToken(parameters) ? null : auth));
            } finally {
                long elapsedTime = System.currentTimeMillis() - start;
                FacebookAPIMonitor.getInstance().methodCalled(url, elapsedTime, isOk(response));
            }
            return response;
        }
    }


    private boolean isOk(HttpResponse response) {
        return response != null && response.getStatusCode() < 300;
    }

    private boolean containsAccessToken(HttpParameter[] parameters) throws FacebookException {
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getName().equals("access_token")) {
                return true;
            }
        }
        return false;
    }

    /* narrow down API methods */

    public UserMethods users() {
        return this;
    }

    public AccountMethods accounts() {
        return this;
    }

    public ActivityMethods activities() {
        return this;
    }

    public AlbumMethods albums() {
        return this;
    }

    public CheckinMethods checkins() {
        return this;
    }

    public CommentMethods comments() {
        return this;
    }

    public DomainMethods domains() {
        return this;
    }

    public EventMethods events() {
        return this;
    }

    public FamilyMethods family() {
        return this;
    }

    public FavoriteMethods favorites() {
        return this;
    }

    public FriendMethods friends() {
        return this;
    }

    public GameMethods games() {
        return this;
    }

    public GroupMethods groups() {
        return this;
    }

    public LikeMethods likes() {
        return this;
    }

    public LinkMethods links() {
        return this;
    }

    public LocationMethods locations() {
        return this;
    }

    public MessageMethods messages() {
        return this;
    }

    public ConversationMethods conversations() {
        return this;
    }

    public NoteMethods notes() {
        return this;
    }

    public NotificationMethods notifications() {
        return this;
    }

    public PageMethods pages() {
        return this;
    }

    public PermissionMethods permissions() {
        return this;
    }

    public PhotoMethods photos() {
        return this;
    }

    public PokeMethods pokes() {
        return this;
    }

    public PostMethods posts() {
        return this;
    }

    public QuestionMethods questions() {
        return this;
    }

    public SubscribeMethods subscribes() {
        return this;
    }

    public VideoMethods videos() {
        return this;
    }

    public InsightMethods insights() {
        return this;
    }

    public SearchMethods search() {
        return this;
    }

    public TestUserMethods testUsers() {
        return this;
    }

    public FQLMethods fql() {
        return this;
    }

    public BatchRequestsMethods batch() {
        return this;
    }

    public RawAPIMethods rawAPI() {
        return this;
    }

}
