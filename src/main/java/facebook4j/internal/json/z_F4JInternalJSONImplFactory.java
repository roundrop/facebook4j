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

package facebook4j.internal.json;

import java.util.List;

import facebook4j.Account;
import facebook4j.Achievement;
import facebook4j.Activity;
import facebook4j.Album;
import facebook4j.Application;
import facebook4j.Book;
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
import facebook4j.IdNameEntity;
import facebook4j.Inbox;
import facebook4j.InboxResponseList;
import facebook4j.Insight;
import facebook4j.Interest;
import facebook4j.Like;
import facebook4j.Link;
import facebook4j.Location;
import facebook4j.MemberEntity;
import facebook4j.Message;
import facebook4j.Movie;
import facebook4j.Music;
import facebook4j.Note;
import facebook4j.Notification;
import facebook4j.Permission;
import facebook4j.Photo;
import facebook4j.Place;
import facebook4j.Poke;
import facebook4j.Post;
import facebook4j.Question;
import facebook4j.RSVPStatus;
import facebook4j.ResponseList;
import facebook4j.Score;
import facebook4j.Subscribedto;
import facebook4j.Subscriber;
import facebook4j.Tag;
import facebook4j.Television;
import facebook4j.TestUser;
import facebook4j.User;
import facebook4j.Video;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class z_F4JInternalJSONImplFactory implements z_F4JInternalFactory {
    private static final long serialVersionUID = 1685018292808157316L;

    private Configuration conf;

    public z_F4JInternalJSONImplFactory(Configuration conf) {
        this.conf = conf;
    }

    public User createUser(HttpResponse res) throws FacebookException {
        return new UserJSONImpl(res, conf);
    }

    public User createUser(JSONObject json) throws FacebookException {
        return new UserJSONImpl(json);
    }
    
    public ResponseList<User> createUserList(HttpResponse res) throws FacebookException {
        return UserJSONImpl.createUserList(res, conf);
    }
    
    public List<User> createUserArray(HttpResponse res) throws FacebookException {
        return UserJSONImpl.createUserArray(res, conf);
    }

    public IdNameEntity createIdNameEntity(HttpResponse res) throws FacebookException {
        return new IdNameEntityJSONImpl(res, conf);
    }

    public ResponseList<IdNameEntity> createIdNameEntityList(HttpResponse res) throws FacebookException {
        return IdNameEntityJSONImpl.createIdNameEntityList(res, conf);
    }

    public ResponseList<Account> createAccountList(HttpResponse res) throws FacebookException {
        return AccountJSONImpl.createAccountList(res, conf);
    }

    public ResponseList<Achievement> createAchievementList(HttpResponse res) throws FacebookException {
        return AchievementJSONImpl.createAchievementList(res, conf);
    }

    public ResponseList<Activity> createActivityList(HttpResponse res) throws FacebookException {
        return ActivityJSONImpl.createActivityList(res, conf);
    }

    public Album createAlbum(HttpResponse res) throws FacebookException {
        return new AlbumJSONImpl(res, conf);
    }
    public ResponseList<Album> createAlbumList(HttpResponse res) throws FacebookException {
        return AlbumJSONImpl.createAlbumList(res, conf);
    }

    public ResponseList<Book> createBookList(HttpResponse res) throws FacebookException {
        return BookJSONImpl.createBookList(res, conf);
    }

    public Checkin createCheckin(HttpResponse res) throws FacebookException {
        return new CheckinJSONImpl(res, conf);
    }

    public ResponseList<Checkin> createCheckinList(HttpResponse res) throws FacebookException {
        return CheckinJSONImpl.createCheckinList(res, conf);
    }
    
    public Comment createComment(HttpResponse res) throws FacebookException {
        return new CommentJSONImpl(res, conf);
    }

    public ResponseList<Comment> createCommentList(HttpResponse res) throws FacebookException {
        return CommentJSONImpl.createCommentList(res, conf);
    }

    public Domain createDomain(HttpResponse res) throws FacebookException {
        return new DomainJSONImpl(res, conf);
    }

    public List<Domain> createDomainArray(HttpResponse res) throws FacebookException {
        return DomainJSONImpl.createDomainArray(res, conf);
    }

    public Event createEvent(HttpResponse res) throws FacebookException {
        return new EventJSONImpl(res, conf);
    }

    public ResponseList<Event> createEventList(HttpResponse res) throws FacebookException {
        return EventJSONImpl.createEventList(res, conf);
    }

    public ResponseList<RSVPStatus> createRSVPStatusList(HttpResponse res) throws FacebookException {
        return RSVPStatusJSONImpl.createRSVPStatusList(res, conf);
    }

    public ResponseList<Family> createFamilyList(HttpResponse res) throws FacebookException {
        return FamilyJSONImpl.createFamilyList(res, conf);
    }

    public Post createPost(HttpResponse res) throws FacebookException {
        return new PostJSONImpl(res, conf);
    }

    public ResponseList<Post> createPostList(HttpResponse res) throws FacebookException {
        return PostJSONImpl.createPostList(res, conf);
    }
    
    public Friendlist createFriendlist(HttpResponse res) throws FacebookException {
        return new FriendlistJSONImpl(res, conf);
    }

    public ResponseList<Friendlist> createFriendlistList(HttpResponse res) throws FacebookException {
        return FriendlistJSONImpl.createFriendlistList(res, conf);
    }
    
    public ResponseList<FriendRequest> createFriendRequestList(HttpResponse res) throws FacebookException {
        return FriendRequestJSONImpl.createFriendRequestList(res, conf);
    }

    public ResponseList<Friend> createFriendList(HttpResponse res) throws FacebookException {
        return FriendJSONImpl.createFriendList(res, conf);
    }

    public ResponseList<Game> createGameList(HttpResponse res) throws FacebookException {
        return GameJSONImpl.createGameList(res, conf);
    }

    public Group createGroup(HttpResponse res) throws FacebookException {
        return new GroupJSONImpl(res, conf);
    }

    public ResponseList<Group> createGroupList(HttpResponse res) throws FacebookException {
        return GroupJSONImpl.createGroupList(res, conf);
    }

    public ResponseList<GroupDoc> createGroupDocList(HttpResponse res) throws FacebookException {
        return GroupDocJSONImpl.createGroupDocList(res, conf);
    }

    public InboxResponseList<Inbox> createInboxList(HttpResponse res) throws FacebookException {
        return InboxJSONImpl.createInboxList(res, conf);
    }

    public ResponseList<Interest> createInterestList(HttpResponse res) throws FacebookException {
        return InterestJSONImpl.createInterestList(res, conf);
    }

    public ResponseList<Like> createLikeList(HttpResponse res) throws FacebookException {
        return LikeJSONImpl.createLikeList(res, conf);
    }

    public Link createLink(HttpResponse res) throws FacebookException {
        return new LinkJSONImpl(res, conf);
    }
    public ResponseList<Link> createLinkList(HttpResponse res) throws FacebookException {
        return LinkJSONImpl.createLinkList(res, conf);
    }

    public ResponseList<Location> createLocationList(HttpResponse res) throws FacebookException {
        return LocationJSONImpl.createLocationList(res, conf);
    }

    public ResponseList<Movie> createMovieList(HttpResponse res) throws FacebookException {
        return MovieJSONImpl.createMovieList(res, conf);
    }

    public ResponseList<Music> createMusicList(HttpResponse res) throws FacebookException {
        return MusicJSONImpl.createMusicList(res, conf);
    }

    public ResponseList<Note> createNoteList(HttpResponse res) throws FacebookException {
        return NoteJSONImpl.createNoteList(res, conf);
    }

    public ResponseList<Notification> createNotificationList(HttpResponse res) throws FacebookException {
        return NotificationJSONImpl.createNotificationList(res, conf);
    }

    public Message createMessage(HttpResponse res) throws FacebookException {
        return new MessageJSONImpl(res, conf);
    }

    public ResponseList<Message> createMessageList(HttpResponse res) throws FacebookException {
        return MessageJSONImpl.createMessageList(res, conf);
    }

    public Note createNote(HttpResponse res) throws FacebookException {
        return new NoteJSONImpl(res, conf);
    }

    public List<Permission> createPermissions(HttpResponse res) throws FacebookException {
        return PermissionJSONImpl.createPermissionArray(res, conf);
    }

    public ResponseList<Place> createPlaceList(HttpResponse res) throws FacebookException {
        return PlaceJSONImpl.createPlaceList(res, conf);
    }

    public Photo createPhoto(HttpResponse res) throws FacebookException {
        return new PhotoJSONImpl(res, conf);
    }
    public ResponseList<Photo> createPhotoList(HttpResponse res) throws FacebookException {
        return PhotoJSONImpl.createPhotoList(res, conf);
    }

    public ResponseList<Poke> createPokeList(HttpResponse res) throws FacebookException {
        return PokeJSONImpl.createPokeList(res, conf);
    }

    public Question createQuestion(HttpResponse res) throws FacebookException {
        return new QuestionJSONImpl(res, conf);
    }
    public ResponseList<Question> createQuestionList(HttpResponse res) throws FacebookException {
        return QuestionJSONImpl.createQuestionList(res, conf);
    }
    
    public ResponseList<Question.Option> createQuestionOptionList(HttpResponse res) throws FacebookException {
        return QuestionJSONImpl.createOptionList(res, conf);
    }

    public ResponseList<Score> createScoreList(HttpResponse res) throws FacebookException {
        return ScoreJSONImpl.createScoreList(res, conf);
    }

    public ResponseList<Subscribedto> createSubscribedtoList(HttpResponse res) throws FacebookException {
        return SubscribedtoJSONImpl.createSubscribedtoList(res, conf);
    }

    public ResponseList<Subscriber> createSubscriberList(HttpResponse res) throws FacebookException {
        return SubscriberJSONImpl.createSubscriberList(res, conf);
    }

    public ResponseList<Tag> createTagList(HttpResponse res) throws FacebookException {
        return TagJSONImpl.createTagList(res, conf);
    }

    public ResponseList<Television> createTelevisionList(HttpResponse res) throws FacebookException {
        return TelevisionJSONImpl.createTelevisionList(res, conf);
    }

    public Video createVideo(HttpResponse res) throws FacebookException {
        return new VideoJSONImpl(res, conf);
    }
    
    public ResponseList<Video> createVideoList(HttpResponse res) throws FacebookException {
        return VideoJSONImpl.createVideoList(res, conf);
    }

    public ResponseList<MemberEntity> createMemberEntityList(HttpResponse res) throws FacebookException {
        return MemberEntityJSONImpl.createMemberEntityList(res, conf);
    }

    public ResponseList<Insight> createInsightList(HttpResponse res) throws FacebookException {
        return InsightJSONImpl.createInsightList(res, conf);
    }

    public Application createApplication(HttpResponse res) throws FacebookException {
        return new ApplicationJSONImpl(res, conf);
    }

    public TestUser createTestUser(HttpResponse res) throws FacebookException {
        return new TestUserJSONImpl(res);
    }

    public TestUser createTestUser(JSONObject json) throws FacebookException {
        return new TestUserJSONImpl(json);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof z_F4JInternalJSONImplFactory)) return false;
    
        z_F4JInternalJSONImplFactory that = (z_F4JInternalJSONImplFactory) o;
    
        if (conf != null ? !conf.equals(that.conf) : that.conf != null)
            return false;
    
        return true;
    }

    @Override
    public int hashCode() {
        return conf != null ? conf.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "JSONImplFactory{" +
                "conf=" + conf +
                '}';
    }
    
}
