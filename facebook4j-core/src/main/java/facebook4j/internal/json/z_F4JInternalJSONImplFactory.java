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

import facebook4j.Account;
import facebook4j.Achievement;
import facebook4j.Activity;
import facebook4j.Admin;
import facebook4j.Album;
import facebook4j.Application;
import facebook4j.Book;
import facebook4j.Checkin;
import facebook4j.Comment;
import facebook4j.Conversation;
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
import facebook4j.IdNameEntity;
import facebook4j.InboxResponseList;
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
import facebook4j.Permission;
import facebook4j.Photo;
import facebook4j.Place;
import facebook4j.PlaceTag;
import facebook4j.Poke;
import facebook4j.Post;
import facebook4j.Question;
import facebook4j.QuestionVotes;
import facebook4j.RSVPStatus;
import facebook4j.Reaction;
import facebook4j.ResponseList;
import facebook4j.Score;
import facebook4j.Subscribedto;
import facebook4j.Subscriber;
import facebook4j.Tab;
import facebook4j.Tag;
import facebook4j.TaggableFriend;
import facebook4j.Tagged;
import facebook4j.Television;
import facebook4j.TestUser;
import facebook4j.User;
import facebook4j.Video;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONObject;

import java.util.List;

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

    public ResponseList<TaggableFriend> createTaggableFriendList(HttpResponse res) throws FacebookException {
        return TaggableFriendJSONImpl.createTaggableFriendList(res, conf);
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

    public InboxResponseList<Message> createInboxList(HttpResponse res) throws FacebookException {
        return MessageJSONImpl.createInboxMessageList(res, conf);
    }

    public InboxResponseList<Conversation> createConversationList(HttpResponse res) throws FacebookException {
        return ConversationJSONImpl.createInboxConversationList(res, conf);
    }

    public Conversation createConversation(HttpResponse res) throws FacebookException {
        return new ConversationJSONImpl(res, conf);
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

    public ResponseList<PlaceTag> createPlaceTagList(HttpResponse res) throws FacebookException {
        return PlaceTagJSONImpl.createPlaceTagList(res, conf);
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

    public Page createPage(HttpResponse res) throws FacebookException {
        return new PageJSONImpl(res, conf);
    }

    public ResponseList<Page> createPageList(HttpResponse res) throws FacebookException {
        return PageJSONImpl.createPageList(res, conf);
    }

    public ResponseList<PageSetting> createPageSettingList(HttpResponse res) throws FacebookException {
        return PageSettingJSONImpl.createLikeList(res, conf);
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

    public ResponseList<QuestionVotes> createQuestionVotesList(HttpResponse res) throws FacebookException {
        return QuestionVotesJSONImpl.createQuestionVotesList(res, conf);
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

    public ResponseList<GroupMember> createGroupMemberList(HttpResponse res) throws FacebookException {
        return GroupMemberJSONImpl.createGroupMemberList(res, conf);
    }

    public ResponseList<Insight> createInsightList(HttpResponse res) throws FacebookException {
        return InsightJSONImpl.createInsightList(res, conf);
    }

    public ResponseList<Tagged> createTaggedList(HttpResponse res) throws FacebookException {
        return TaggedJSONImpl.createTaggedList(res, conf);
    }

    public ResponseList<Milestone> createMilestoneList(HttpResponse res) throws FacebookException {
        return MilestoneJSONImpl.createMilestoneList(res, conf);
    }

    public ResponseList<Admin> createAdminList(HttpResponse res) throws FacebookException {
        return AdminJSONImpl.createAdminList(res, conf);
    }

    public ResponseList<Tab> createTabList(HttpResponse res) throws FacebookException {
        return TabJSONImpl.createTabList(res, conf);
    }

    public ResponseList<Offer> createOfferList(HttpResponse res) throws FacebookException {
        return OfferJSONImpl.createOfferList(res, conf);
    }

    public Offer createOffer(HttpResponse res) throws FacebookException {
        return new OfferJSONImpl(res, conf);
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

    public ResponseList<TestUser> createTestUserList(HttpResponse res) throws FacebookException {
       return TestUserJSONImpl.createTestUserList(res, conf);
   }

    public ResponseList<JSONObject> createJSONObjectList(HttpResponse res) throws FacebookException {
        return ResponseListImpl.createJSONObjectList(res, conf);
    }

    public ResponseList<JSONObject> createJSONObjectList(JSONObject json) throws FacebookException {
        return ResponseListImpl.createJSONObjectList(json);
    }

    public Reaction createReaction(HttpResponse res) throws FacebookException {
        return new ReactionJSONImpl(res, conf);
    }

    public ResponseList<Reaction> createReactionList(HttpResponse res) throws FacebookException {
        return ReactionJSONImpl.createReactionsList(res, conf);
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

    @SuppressWarnings("unchecked")
    public <T> ResponseList<T> createResponseList(HttpResponse res, Class<T> jsonObjectType) throws FacebookException {
        if (jsonObjectType == Account.class) {
            return (ResponseList<T>) createAccountList(res);
        }
        if (jsonObjectType == Activity.class) {
            return (ResponseList<T>) createActivityList(res);
        }
        if (jsonObjectType == Album.class) {
            return (ResponseList<T>) createAlbumList(res);
        }
        if (jsonObjectType == Photo.class) {
            return (ResponseList<T>) createPhotoList(res);
        }
        if (jsonObjectType == Comment.class) {
            return (ResponseList<T>) createCommentList(res);
        }
        if (jsonObjectType == Like.class) {
            return (ResponseList<T>) createLikeList(res);
        }
        if (jsonObjectType == Checkin.class) {
            return (ResponseList<T>) createCheckinList(res);
        }
        if (jsonObjectType == Event.class) {
            return (ResponseList<T>) createEventList(res);
        }
        if (jsonObjectType == Post.class) {
            return (ResponseList<T>) createPostList(res);
        }
        if (jsonObjectType == RSVPStatus.class) {
            return (ResponseList<T>) createRSVPStatusList(res);
        }
        if (jsonObjectType == Video.class) {
            return (ResponseList<T>) createVideoList(res);
        }
        if (jsonObjectType == Family.class) {
            return (ResponseList<T>) createFamilyList(res);
        }
        if (jsonObjectType == Book.class) {
            return (ResponseList<T>) createBookList(res);
        }
        if (jsonObjectType == Game.class) {
            return (ResponseList<T>) createGameList(res);
        }
        if (jsonObjectType == Movie.class) {
            return (ResponseList<T>) createMovieList(res);
        }
        if (jsonObjectType == Music.class) {
            return (ResponseList<T>) createMusicList(res);
        }
        if (jsonObjectType == Television.class) {
            return (ResponseList<T>) createTelevisionList(res);
        }
        if (jsonObjectType == Interest.class) {
            return (ResponseList<T>) createInterestList(res);
        }
        if (jsonObjectType == Friend.class) {
            return (ResponseList<T>) createFriendList(res);
        }
        if (jsonObjectType == Friendlist.class) {
            return (ResponseList<T>) createFriendlistList(res);
        }
        if (jsonObjectType == FriendRequest.class) {
            return (ResponseList<T>) createFriendRequestList(res);
        }
        if (jsonObjectType == Achievement.class) {
            return (ResponseList<T>) createAchievementList(res);
        }
        if (jsonObjectType == Score.class) {
            return (ResponseList<T>) createScoreList(res);
        }
        if (jsonObjectType == Group.class) {
            return (ResponseList<T>) createGroupList(res);
        }
        if (jsonObjectType == GroupMember.class) {
            return (ResponseList<T>) createGroupMemberList(res);
        }
        if (jsonObjectType == GroupDoc.class) {
            return (ResponseList<T>) createGroupDocList(res);
        }
        if (jsonObjectType == Insight.class) {
            return (ResponseList<T>) createInsightList(res);
        }
        if (jsonObjectType == Location.class) {
            return (ResponseList<T>) createLocationList(res);
        }
        if (jsonObjectType == Conversation.class) {
            return (ResponseList<T>) createConversationList(res);
        }
        if (jsonObjectType == Message.class) {
            return (ResponseList<T>) createMessageList(res);
        }
        if (jsonObjectType == Note.class) {
            return (ResponseList<T>) createNoteList(res);
        }
        if (jsonObjectType == Notification.class) {
            return (ResponseList<T>) createNotificationList(res);
        }
        if (jsonObjectType == Tag.class) {
            return (ResponseList<T>) createTagList(res);
        }
        if (jsonObjectType == Poke.class) {
            return (ResponseList<T>) createPokeList(res);
        }
        if (jsonObjectType == Link.class) {
            return (ResponseList<T>) createLinkList(res);
        }
        if (jsonObjectType == Question.class) {
            return (ResponseList<T>) createQuestionList(res);
        }
        if (jsonObjectType == Question.Option.class) {
            return (ResponseList<T>) createQuestionOptionList(res);
        }
        if (jsonObjectType == QuestionVotes.class) {
            return (ResponseList<T>) createQuestionVotesList(res);
        }
        if (jsonObjectType == User.class) {
            return (ResponseList<T>) createUserList(res);
        }
        if (jsonObjectType == Place.class) {
            return (ResponseList<T>) createPlaceList(res);
        }
        if (jsonObjectType == Subscribedto.class) {
            return (ResponseList<T>) createSubscribedtoList(res);
        }
        if (jsonObjectType == Subscriber.class) {
            return (ResponseList<T>) createSubscriberList(res);
        }
        if (jsonObjectType == Page.class) {
            return (ResponseList<T>) createPageList(res);
        }
        if (jsonObjectType == PageSetting.class) {
            return (ResponseList<T>) createPageSettingList(res);
        }
        if (jsonObjectType == Tagged.class) {
            return (ResponseList<T>) createTaggedList(res);
        }
        if (jsonObjectType == Milestone.class) {
            return (ResponseList<T>) createMilestoneList(res);
        }
        if (jsonObjectType == Admin.class) {
            return (ResponseList<T>) createAdminList(res);
        }
        if (jsonObjectType == Tab.class) {
            return (ResponseList<T>) createTabList(res);
        }
        if (jsonObjectType == Offer.class) {
            return (ResponseList<T>) createOfferList(res);
        }
        if (jsonObjectType == TaggableFriend.class) {
            return (ResponseList<T>) createTaggableFriendList(res);
        }
        if (jsonObjectType == Reaction.class) {
            return (ResponseList<T>) createReactionList(res);
        }
        if (jsonObjectType == TestUser.class) {
            return (ResponseList<T>) createTestUserList(res);
        }
        if (jsonObjectType == JSONObject.class) {
            return (ResponseList<T>) createJSONObjectList(res);
        }
        throw new FacebookException("The json object type: '" + jsonObjectType + "' is unrecognized.");
    }

}
