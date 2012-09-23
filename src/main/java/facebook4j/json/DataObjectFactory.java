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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import facebook4j.Album;
import facebook4j.Checkin;
import facebook4j.Comment;
import facebook4j.Event;
import facebook4j.FacebookException;
import facebook4j.Friendlist;
import facebook4j.Group;
import facebook4j.GroupDoc;
import facebook4j.Link;
import facebook4j.Message;
import facebook4j.Note;
import facebook4j.Photo;
import facebook4j.Place;
import facebook4j.Post;
import facebook4j.Question;
import facebook4j.RSVPStatus;
import facebook4j.Tag;
import facebook4j.User;
import facebook4j.Video;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public final class DataObjectFactory {
    private DataObjectFactory() {
        throw new AssertionError("not intended to be instantiated.");
    }

    private static final Constructor<Album> albumConstructor;
    private static final Constructor<Checkin> checkinConstructor;
    private static final Constructor<Comment> commentConstructor;
    private static final Constructor<Event> eventConstructor;
    private static final Constructor<Friendlist> friendlistConstructor;
    private static final Constructor<GroupDoc> groupDocConstructor;
    private static final Constructor<Group> groupConstructor;
    private static final Constructor<Link> linkConstructor;
    private static final Constructor<Message> messageConstructor;
    private static final Constructor<Note> noteConstructor;
    private static final Constructor<Photo> photoConstructor;
    private static final Constructor<Place> placeConstructor;
    private static final Constructor<Post> postConstructor;
    private static final Constructor<Question> questionConstructor;
    private static final Constructor<RSVPStatus> rsvpStatusConstructor;
    private static final Constructor<Tag> tagConstructor;
    private static final Constructor<User> userConstructor;
    private static final Constructor<Video> videoConstructor;

    static {
        try {
            albumConstructor = (Constructor<Album>) Class.forName("facebook4j.internal.json.AlbumJSONImpl").getDeclaredConstructor(JSONObject.class);
            albumConstructor.setAccessible(true);

            checkinConstructor = (Constructor<Checkin>) Class.forName("facebook4j.internal.json.CheckinJSONImpl").getDeclaredConstructor(JSONObject.class);
            checkinConstructor.setAccessible(true);

            commentConstructor = (Constructor<Comment>) Class.forName("facebook4j.internal.json.CommentJSONImpl").getDeclaredConstructor(JSONObject.class);
            commentConstructor.setAccessible(true);
            
            eventConstructor = (Constructor<Event>) Class.forName("facebook4j.internal.json.EventJSONImpl").getDeclaredConstructor(JSONObject.class);
            eventConstructor.setAccessible(true);
            
            friendlistConstructor = (Constructor<Friendlist>) Class.forName("facebook4j.internal.json.FriendlistJSONImpl").getDeclaredConstructor(JSONObject.class);
            friendlistConstructor.setAccessible(true);
            
            groupDocConstructor = (Constructor<GroupDoc>) Class.forName("facebook4j.internal.json.GroupDocJSONImpl").getDeclaredConstructor(JSONObject.class);
            groupDocConstructor.setAccessible(true);
            
            groupConstructor = (Constructor<Group>) Class.forName("facebook4j.internal.json.GroupJSONImpl").getDeclaredConstructor(JSONObject.class);
            groupConstructor.setAccessible(true);
            
            linkConstructor = (Constructor<Link>) Class.forName("facebook4j.internal.json.LinkJSONImpl").getDeclaredConstructor(JSONObject.class);
            linkConstructor.setAccessible(true);
            
            messageConstructor = (Constructor<Message>) Class.forName("facebook4j.internal.json.MessageJSONImpl").getDeclaredConstructor(JSONObject.class);
            messageConstructor.setAccessible(true);
            
            noteConstructor = (Constructor<Note>) Class.forName("facebook4j.internal.json.NoteJSONImpl").getDeclaredConstructor(JSONObject.class);
            noteConstructor.setAccessible(true);
            
            photoConstructor = (Constructor<Photo>) Class.forName("facebook4j.internal.json.PhotoJSONImpl").getDeclaredConstructor(JSONObject.class);
            photoConstructor.setAccessible(true);
            
            placeConstructor = (Constructor<Place>) Class.forName("facebook4j.internal.json.PlaceJSONImpl").getDeclaredConstructor(JSONObject.class);
            placeConstructor.setAccessible(true);
            
            postConstructor = (Constructor<Post>) Class.forName("facebook4j.internal.json.PostJSONImpl").getDeclaredConstructor(JSONObject.class);
            postConstructor.setAccessible(true);
            
            questionConstructor = (Constructor<Question>) Class.forName("facebook4j.internal.json.QuestionJSONImpl").getDeclaredConstructor(JSONObject.class);
            questionConstructor.setAccessible(true);
            
            rsvpStatusConstructor = (Constructor<RSVPStatus>) Class.forName("facebook4j.internal.json.RSVPStatusJSONImpl").getDeclaredConstructor(JSONObject.class);
            rsvpStatusConstructor.setAccessible(true);
            
            tagConstructor = (Constructor<Tag>) Class.forName("facebook4j.internal.json.TagJSONImpl").getDeclaredConstructor(JSONObject.class);
            tagConstructor.setAccessible(true);
            
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