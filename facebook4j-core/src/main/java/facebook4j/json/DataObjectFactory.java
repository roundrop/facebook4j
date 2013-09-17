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

package facebook4j.json;

import facebook4j.Account;
import facebook4j.Achievement;
import facebook4j.Activity;
import facebook4j.Admin;
import facebook4j.Album;
import facebook4j.Book;
import facebook4j.Category;
import facebook4j.Checkin;
import facebook4j.Comment;
import facebook4j.Domain;
import facebook4j.Event;
import facebook4j.FacebookException;
import facebook4j.Family;
import facebook4j.Friend;
import facebook4j.FriendRequest;
import facebook4j.Friendlist;
import facebook4j.Game;
import facebook4j.Group;
import facebook4j.GroupDoc;
import facebook4j.GroupMember;
import facebook4j.Insight;
import facebook4j.Interest;
import facebook4j.Like;
import facebook4j.Link;
import facebook4j.Location;
import facebook4j.Message;
import facebook4j.Milestone;
import facebook4j.Movie;
import facebook4j.Music;
import facebook4j.Note;
import facebook4j.Notification;
import facebook4j.Offer;
import facebook4j.Page;
import facebook4j.PageSetting;
import facebook4j.Photo;
import facebook4j.Place;
import facebook4j.Poke;
import facebook4j.Post;
import facebook4j.Question;
import facebook4j.QuestionVotes;
import facebook4j.RSVPStatus;
import facebook4j.Score;
import facebook4j.Subscribedto;
import facebook4j.Subscriber;
import facebook4j.Tab;
import facebook4j.Tag;
import facebook4j.Tagged;
import facebook4j.Television;
import facebook4j.User;
import facebook4j.Video;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class DataObjectFactory {
    private DataObjectFactory() {
        throw new AssertionError("not intended to be instantiated.");
    }

    private static final Constructor<Account> accountConstructor;
    private static final Constructor<Achievement> achievementConstructor;
    private static final Constructor<Activity> activityConstructor;
    private static final Constructor<Admin> adminConstructor;
    private static final Constructor<Album> albumConstructor;
    private static final Constructor<Book> bookConstructor;
    private static final Constructor<Category> categoryConstructor;
    private static final Constructor<Checkin> checkinConstructor;
    private static final Constructor<Comment> commentConstructor;
    private static final Constructor<Domain> domainConstructor;
    private static final Constructor<Event> eventConstructor;
    private static final Constructor<Family> familyConstructor;
    private static final Constructor<Friend> friendConstructor;
    private static final Constructor<Friendlist> friendlistConstructor;
    private static final Constructor<FriendRequest> friendRequestConstructor;
    private static final Constructor<Game> gameConstructor;
    private static final Constructor<GroupDoc> groupDocConstructor;
    private static final Constructor<Group> groupConstructor;
    private static final Constructor<GroupMember> groupMemberConstructor;
    private static final Constructor<Insight> insightConstructor;
    private static final Constructor<Interest> interestConstructor;
    private static final Constructor<Like> likeConstructor;
    private static final Constructor<Link> linkConstructor;
    private static final Constructor<Location> locationConstructor;
    private static final Constructor<Message> messageConstructor;
    private static final Constructor<Milestone> milestoneConstructor;
    private static final Constructor<Movie> movieConstructor;
    private static final Constructor<Music> musicConstructor;
    private static final Constructor<Note> noteConstructor;
    private static final Constructor<Notification> notificationConstructor;
    private static final Constructor<Offer> offerConstructor;
    private static final Constructor<Page> pageConstructor;
    private static final Constructor<PageSetting> pageSettingConstructor;
    private static final Constructor<Photo> photoConstructor;
    private static final Constructor<Place> placeConstructor;
    private static final Constructor<Poke> pokeConstructor;
    private static final Constructor<Post> postConstructor;
    private static final Constructor<Question> questionConstructor;
    private static final Constructor<QuestionVotes> questionVotesConstructor;
    private static final Constructor<RSVPStatus> rsvpStatusConstructor;
    private static final Constructor<Score> scoreConstructor;
    private static final Constructor<Subscribedto> subscribedtoConstructor;
    private static final Constructor<Subscriber> subscriberConstructor;
    private static final Constructor<Tab> tabConstructor;
    private static final Constructor<Tagged> taggedConstructor;
    private static final Constructor<Tag> tagConstructor;
    private static final Constructor<Television> televisionConstructor;
    private static final Constructor<User> userConstructor;
    private static final Constructor<Video> videoConstructor;

    static {
        try {
            accountConstructor = (Constructor<Account>) Class.forName("facebook4j.internal.json.AccountJSONImpl").getDeclaredConstructor(JSONObject.class);
            accountConstructor.setAccessible(true);

            achievementConstructor = (Constructor<Achievement>) Class.forName("facebook4j.internal.json.AchievementJSONImpl").getDeclaredConstructor(JSONObject.class);
            achievementConstructor.setAccessible(true);

            activityConstructor = (Constructor<Activity>) Class.forName("facebook4j.internal.json.ActivityJSONImpl").getDeclaredConstructor(JSONObject.class);
            activityConstructor.setAccessible(true);

            adminConstructor = (Constructor<Admin>) Class.forName("facebook4j.internal.json.AdminJSONImpl").getDeclaredConstructor(JSONObject.class);
            adminConstructor.setAccessible(true);

            albumConstructor = (Constructor<Album>) Class.forName("facebook4j.internal.json.AlbumJSONImpl").getDeclaredConstructor(JSONObject.class);
            albumConstructor.setAccessible(true);

            bookConstructor = (Constructor<Book>) Class.forName("facebook4j.internal.json.BookJSONImpl").getDeclaredConstructor(JSONObject.class);
            bookConstructor.setAccessible(true);

            categoryConstructor = (Constructor<Category>) Class.forName("facebook4j.internal.json.CategoryJSONImpl").getDeclaredConstructor(JSONObject.class);
            categoryConstructor.setAccessible(true);

            checkinConstructor = (Constructor<Checkin>) Class.forName("facebook4j.internal.json.CheckinJSONImpl").getDeclaredConstructor(JSONObject.class);
            checkinConstructor.setAccessible(true);

            commentConstructor = (Constructor<Comment>) Class.forName("facebook4j.internal.json.CommentJSONImpl").getDeclaredConstructor(JSONObject.class);
            commentConstructor.setAccessible(true);
            
            domainConstructor = (Constructor<Domain>) Class.forName("facebook4j.internal.json.DomainJSONImpl").getDeclaredConstructor(JSONObject.class);
            domainConstructor.setAccessible(true);

            eventConstructor = (Constructor<Event>) Class.forName("facebook4j.internal.json.EventJSONImpl").getDeclaredConstructor(JSONObject.class);
            eventConstructor.setAccessible(true);
            
            familyConstructor = (Constructor<Family>) Class.forName("facebook4j.internal.json.FamilyJSONImpl").getDeclaredConstructor(JSONObject.class);
            familyConstructor.setAccessible(true);

            friendConstructor = (Constructor<Friend>) Class.forName("facebook4j.internal.json.FriendJSONImpl").getDeclaredConstructor(JSONObject.class);
            friendConstructor.setAccessible(true);
            
            friendlistConstructor = (Constructor<Friendlist>) Class.forName("facebook4j.internal.json.FriendlistJSONImpl").getDeclaredConstructor(JSONObject.class);
            friendlistConstructor.setAccessible(true);

            friendRequestConstructor = (Constructor<FriendRequest>) Class.forName("facebook4j.internal.json.FriendRequestJSONImpl").getDeclaredConstructor(JSONObject.class);
            friendRequestConstructor.setAccessible(true);

            gameConstructor = (Constructor<Game>) Class.forName("facebook4j.internal.json.GameJSONImpl").getDeclaredConstructor(JSONObject.class);
            gameConstructor.setAccessible(true);

            groupDocConstructor = (Constructor<GroupDoc>) Class.forName("facebook4j.internal.json.GroupDocJSONImpl").getDeclaredConstructor(JSONObject.class);
            groupDocConstructor.setAccessible(true);
            
            groupConstructor = (Constructor<Group>) Class.forName("facebook4j.internal.json.GroupJSONImpl").getDeclaredConstructor(JSONObject.class);
            groupConstructor.setAccessible(true);
            
            groupMemberConstructor = (Constructor<GroupMember>) Class.forName("facebook4j.internal.json.GroupMemberJSONImpl").getDeclaredConstructor(JSONObject.class);
            groupMemberConstructor.setAccessible(true);

            insightConstructor = (Constructor<Insight>) Class.forName("facebook4j.internal.json.InsightJSONImpl").getDeclaredConstructor(JSONObject.class);
            insightConstructor.setAccessible(true);

            interestConstructor = (Constructor<Interest>) Class.forName("facebook4j.internal.json.InterestJSONImpl").getDeclaredConstructor(JSONObject.class);
            interestConstructor.setAccessible(true);

            likeConstructor = (Constructor<Like>) Class.forName("facebook4j.internal.json.LikeJSONImpl").getDeclaredConstructor(JSONObject.class);
            likeConstructor.setAccessible(true);

            linkConstructor = (Constructor<Link>) Class.forName("facebook4j.internal.json.LinkJSONImpl").getDeclaredConstructor(JSONObject.class);
            linkConstructor.setAccessible(true);

            locationConstructor = (Constructor<Location>) Class.forName("facebook4j.internal.json.LocationJSONImpl").getDeclaredConstructor(JSONObject.class);
            locationConstructor.setAccessible(true);

            messageConstructor = (Constructor<Message>) Class.forName("facebook4j.internal.json.MessageJSONImpl").getDeclaredConstructor(JSONObject.class);
            messageConstructor.setAccessible(true);
            
            milestoneConstructor = (Constructor<Milestone>) Class.forName("facebook4j.internal.json.MilestoneJSONImpl").getDeclaredConstructor(JSONObject.class);
            milestoneConstructor.setAccessible(true);

            movieConstructor = (Constructor<Movie>) Class.forName("facebook4j.internal.json.MovieJSONImpl").getDeclaredConstructor(JSONObject.class);
            movieConstructor.setAccessible(true);

            musicConstructor = (Constructor<Music>) Class.forName("facebook4j.internal.json.MusicJSONImpl").getDeclaredConstructor(JSONObject.class);
            musicConstructor.setAccessible(true);

            noteConstructor = (Constructor<Note>) Class.forName("facebook4j.internal.json.NoteJSONImpl").getDeclaredConstructor(JSONObject.class);
            noteConstructor.setAccessible(true);

            notificationConstructor = (Constructor<Notification>) Class.forName("facebook4j.internal.json.NotificationJSONImpl").getDeclaredConstructor(JSONObject.class);
            notificationConstructor.setAccessible(true);

            offerConstructor = (Constructor<Offer>) Class.forName("facebook4j.internal.json.OfferJSONImpl").getDeclaredConstructor(JSONObject.class);
            offerConstructor.setAccessible(true);

            pageConstructor = (Constructor<Page>) Class.forName("facebook4j.internal.json.PageJSONImpl").getDeclaredConstructor(JSONObject.class);
            pageConstructor.setAccessible(true);

            pageSettingConstructor = (Constructor<PageSetting>) Class.forName("facebook4j.internal.json.PageSettingJSONImpl").getDeclaredConstructor(JSONObject.class);
            pageSettingConstructor.setAccessible(true);

            photoConstructor = (Constructor<Photo>) Class.forName("facebook4j.internal.json.PhotoJSONImpl").getDeclaredConstructor(JSONObject.class);
            photoConstructor.setAccessible(true);
            
            placeConstructor = (Constructor<Place>) Class.forName("facebook4j.internal.json.PlaceJSONImpl").getDeclaredConstructor(JSONObject.class);
            placeConstructor.setAccessible(true);

            pokeConstructor = (Constructor<Poke>) Class.forName("facebook4j.internal.json.PokeJSONImpl").getDeclaredConstructor(JSONObject.class);
            pokeConstructor.setAccessible(true);

            postConstructor = (Constructor<Post>) Class.forName("facebook4j.internal.json.PostJSONImpl").getDeclaredConstructor(JSONObject.class);
            postConstructor.setAccessible(true);
            
            questionConstructor = (Constructor<Question>) Class.forName("facebook4j.internal.json.QuestionJSONImpl").getDeclaredConstructor(JSONObject.class);
            questionConstructor.setAccessible(true);
            
            questionVotesConstructor = (Constructor<QuestionVotes>) Class.forName("facebook4j.internal.json.QuestionVotesJSONImpl").getDeclaredConstructor(JSONObject.class);
            questionVotesConstructor.setAccessible(true);

            rsvpStatusConstructor = (Constructor<RSVPStatus>) Class.forName("facebook4j.internal.json.RSVPStatusJSONImpl").getDeclaredConstructor(JSONObject.class);
            rsvpStatusConstructor.setAccessible(true);
            
            scoreConstructor = (Constructor<Score>) Class.forName("facebook4j.internal.json.ScoreJSONImpl").getDeclaredConstructor(JSONObject.class);
            scoreConstructor.setAccessible(true);

            subscribedtoConstructor = (Constructor<Subscribedto>) Class.forName("facebook4j.internal.json.SubscribedtoJSONImpl").getDeclaredConstructor(JSONObject.class);
            subscribedtoConstructor.setAccessible(true);

            subscriberConstructor = (Constructor<Subscriber>) Class.forName("facebook4j.internal.json.SubscriberJSONImpl").getDeclaredConstructor(JSONObject.class);
            subscriberConstructor.setAccessible(true);

            tabConstructor = (Constructor<Tab>) Class.forName("facebook4j.internal.json.TabJSONImpl").getDeclaredConstructor(JSONObject.class);
            tabConstructor.setAccessible(true);
            
            taggedConstructor = (Constructor<Tagged>) Class.forName("facebook4j.internal.json.TaggedJSONImpl").getDeclaredConstructor(JSONObject.class);
            taggedConstructor.setAccessible(true);

            tagConstructor = (Constructor<Tag>) Class.forName("facebook4j.internal.json.TagJSONImpl").getDeclaredConstructor(JSONObject.class);
            tagConstructor.setAccessible(true);

            televisionConstructor = (Constructor<Television>) Class.forName("facebook4j.internal.json.TelevisionJSONImpl").getDeclaredConstructor(JSONObject.class);
            televisionConstructor.setAccessible(true);

            userConstructor = (Constructor<User>) Class.forName("facebook4j.internal.json.UserJSONImpl").getDeclaredConstructor(JSONObject.class);
            userConstructor.setAccessible(true);

            videoConstructor = (Constructor<Video>) Class.forName("facebook4j.internal.json.VideoJSONImpl").getDeclaredConstructor(JSONObject.class);
            videoConstructor.setAccessible(true);
            
        } catch (NoSuchMethodException e) {
            throw new ExceptionInInitializerError(e);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static final ThreadLocal<Map> rawJsonMap = new ThreadLocal<Map>() {
        @Override
        protected Map initialValue() {
            return new HashMap();
        }
    };

    /**
     * Returns a raw JSON form of the provided object.<br>
     * Note that raw JSON forms can be retrieved only from the same thread invoked the last method call and will become inaccessible once another method call
     *
     * @param obj
     * @return raw JSON
     */
    public static String getRawJSON(Object obj) {
        Object json = rawJsonMap.get().get(obj);
        if (json instanceof String) {
            return (String) json;
        } else if (json != null) {
            // object must be instance of JSONObject
            return json.toString();
        } else {
            return null;
        }
    }

    /**
     * Constructs a Account object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Account
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Account createAccount(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return accountConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Achievement object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Achievement
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Achievement createAchievement(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return achievementConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Activity object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Activity
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Activity createActivity(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return activityConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Admin object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Admin
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Admin createAdmin(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return adminConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Album object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Album
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Album createAlbum(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return albumConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Book object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Book
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Book createBook(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return bookConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Category object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Category
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Category createCategory(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return categoryConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Checkin object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Checkin
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Checkin createCheckin(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return checkinConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Comment object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Comment
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Comment createComment(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return commentConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Domain object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Domain
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Domain createDomain(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return domainConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Event object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Event
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Event createEvent(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return eventConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Family object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Family
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Family createFamily(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return familyConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Friend object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Friend
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Friend createFriend(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return friendConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Friendlist object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Friendlist
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Friendlist createFriendlist(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return friendlistConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a FriendRequest object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return FriendRequest
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static FriendRequest createFriendRequest(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return friendRequestConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Game object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Game
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Game createGame(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return gameConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a GroupDoc object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return GroupDoc
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static GroupDoc createGroupDoc(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return groupDocConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Group object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Group
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Group createGroup(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return groupConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a GroupMember object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return GroupMember
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static GroupMember createGroupMember(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return groupMemberConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Insight object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Insight
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Insight createInsight(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return insightConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Interest object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Interest
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Interest createInterest(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return interestConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Like object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Like
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Like createLike(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return likeConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Link object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Link
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Link createLink(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return linkConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Location object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Location
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Location createLocation(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return locationConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Message object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Message
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Message createMessage(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return messageConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Milestone object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Milestone
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Milestone createMilestone(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return milestoneConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Movie object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Movie
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Movie createMovie(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return movieConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Music object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Music
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Music createMusic(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return musicConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Note object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Note
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Note createNote(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return noteConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Notification object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Notification
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Notification createNotification(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return notificationConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Offer object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Offer
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Offer createOffer(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return offerConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Page object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Page
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Page createPage(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return pageConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a PageSetting object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return PageSetting
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static PageSetting createPageSetting(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return pageSettingConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Photo object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Photo
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Photo createPhoto(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return photoConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Place object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Place
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Place createPlace(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return placeConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Poke object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Poke
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Poke createPoke(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return pokeConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Post object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Post
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Post createPost(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return postConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Question object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Question
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Question createQuestion(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return questionConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a QuestionVotes object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return QuestionVotes
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static QuestionVotes createQuestionVotes(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return questionVotesConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a RSVPStatus object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return RSVPStatus
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static RSVPStatus createRSVPStatus(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return rsvpStatusConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Score object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Score
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Score createScore(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return scoreConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Subscribedto object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Subscribedto
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Subscribedto createSubscribedto(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return subscribedtoConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Subscriber object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Subscriber
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Subscriber createSubscriber(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return subscriberConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Tab object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Tab
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Tab createTab(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return tabConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Tagged object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Tagged
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Tagged createTagged(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return taggedConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Tag object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Tag
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Tag createTag(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return tagConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * Constructs a Television object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Television
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Television createTelevision(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return televisionConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a User object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return User
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static User createUser(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return userConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }

    /**
     * Constructs a Video object from rawJSON string.
     *
     * @param rawJSON raw JSON form as String
     * @return Video
     * @throws FacebookException when provided string is not a valid JSON string.
     */
    public static Video createVideo(String rawJSON) throws FacebookException {
        try {
            JSONObject json = new JSONObject(rawJSON);
            return videoConstructor.newInstance(json);
        } catch (InstantiationException e) {
            throw new FacebookException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new FacebookException(e);
        } catch (JSONException e) {
            throw new FacebookException(e);
        }
    }
    
    /**
     * clear raw JSON forms associated with the current thread.<br>
     * Currently this method is called indirectly by facebook4j.internal.util.DataObjectFactoryUtil, and should be called directly once *JSONImpl classes are migrated to facebook4j.json.* package.
     */
    static void clearThreadLocalMap() {
        rawJsonMap.get().clear();
    }

    /**
     * associate a raw JSON form to the current thread<br>
     * Currently this method is called indirectly by facebook4j.internal.util.DataObjectFactoryUtil, and should be called directly once *JSONImpl classes are migrated to facebook4j.json.* package.
     */
    static <T> T registerJSONObject(T key, Object json) {
        rawJsonMap.get().put(key, json);
        return key;
    }
}