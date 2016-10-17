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
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONObject;

import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface z_F4JInternalFactory extends java.io.Serializable {

    User createUser(JSONObject json) throws FacebookException;
    User createUser(HttpResponse res) throws FacebookException;
    ResponseList<User> createUserList(HttpResponse res) throws FacebookException;
    List<User> createUserArray(HttpResponse res) throws FacebookException;

    IdNameEntity createIdNameEntity(HttpResponse res) throws FacebookException;
    ResponseList<IdNameEntity> createIdNameEntityList(HttpResponse res) throws FacebookException;

    ResponseList<Account> createAccountList(HttpResponse res) throws FacebookException;

    ResponseList<Achievement> createAchievementList(HttpResponse res) throws FacebookException;

    ResponseList<Activity> createActivityList(HttpResponse res) throws FacebookException;

    Album createAlbum(HttpResponse res) throws FacebookException;
    ResponseList<Album> createAlbumList(HttpResponse res) throws FacebookException;

    ResponseList<Book> createBookList(HttpResponse res) throws FacebookException;

    Checkin createCheckin(HttpResponse res) throws FacebookException;
    ResponseList<Checkin> createCheckinList(HttpResponse res) throws FacebookException;

    Comment createComment(HttpResponse res) throws FacebookException;
    ResponseList<Comment> createCommentList(HttpResponse res) throws FacebookException;

    Domain createDomain(HttpResponse res) throws FacebookException;
    List<Domain> createDomainArray(HttpResponse res) throws FacebookException;

    Event createEvent(HttpResponse res) throws FacebookException;
    ResponseList<Event> createEventList(HttpResponse res) throws FacebookException;
    ResponseList<RSVPStatus> createRSVPStatusList(HttpResponse res) throws FacebookException;

    ResponseList<Family> createFamilyList(HttpResponse res) throws FacebookException;

    ResponseList<Post> createPostList(HttpResponse res) throws FacebookException;
    Post createPost(HttpResponse res) throws FacebookException;

    Friendlist createFriendlist(HttpResponse res) throws FacebookException;
    ResponseList<Friendlist> createFriendlistList(HttpResponse res) throws FacebookException;

    ResponseList<FriendRequest> createFriendRequestList(HttpResponse res) throws FacebookException;

    ResponseList<Friend> createFriendList(HttpResponse res) throws FacebookException;
    ResponseList<TaggableFriend> createTaggableFriendList(HttpResponse res) throws FacebookException;

    ResponseList<Game> createGameList(HttpResponse res) throws FacebookException;

    Group createGroup(HttpResponse res) throws FacebookException;
    ResponseList<Group> createGroupList(HttpResponse res) throws FacebookException;

    ResponseList<GroupDoc> createGroupDocList(HttpResponse res) throws FacebookException;
    InboxResponseList<Message> createInboxList(HttpResponse res) throws FacebookException;

    InboxResponseList<Conversation> createConversationList(HttpResponse res) throws FacebookException;

    Conversation createConversation(HttpResponse res) throws FacebookException;

    ResponseList<Interest> createInterestList(HttpResponse res) throws FacebookException;

    ResponseList<Like> createLikeList(HttpResponse res) throws FacebookException;

    Link createLink(HttpResponse res) throws FacebookException;
    ResponseList<Link> createLinkList(HttpResponse res) throws FacebookException;

    ResponseList<Location> createLocationList(HttpResponse res) throws FacebookException;
    ResponseList<PlaceTag> createPlaceTagList(HttpResponse res) throws FacebookException;

    ResponseList<Movie> createMovieList(HttpResponse res) throws FacebookException;

    ResponseList<Music> createMusicList(HttpResponse res) throws FacebookException;

    Note createNote(HttpResponse res) throws FacebookException;
    ResponseList<Note> createNoteList(HttpResponse res) throws FacebookException;

    ResponseList<Notification> createNotificationList(HttpResponse res) throws FacebookException;

    Page createPage(HttpResponse res) throws FacebookException;
    ResponseList<Page> createPageList(HttpResponse res) throws FacebookException;

    ResponseList<PageSetting> createPageSettingList(HttpResponse res) throws FacebookException;

    Message createMessage(HttpResponse res) throws FacebookException;
    ResponseList<Message> createMessageList(HttpResponse res) throws FacebookException;

    List<Permission> createPermissions(HttpResponse res) throws FacebookException;

    ResponseList<Place> createPlaceList(HttpResponse res) throws FacebookException;

    Photo createPhoto(HttpResponse res) throws FacebookException;
    ResponseList<Photo> createPhotoList(HttpResponse res) throws FacebookException;

    ResponseList<Poke> createPokeList(HttpResponse res) throws FacebookException;

    Question createQuestion(HttpResponse res) throws FacebookException;
    ResponseList<Question> createQuestionList(HttpResponse res) throws FacebookException;
    ResponseList<Question.Option> createQuestionOptionList(HttpResponse res) throws FacebookException;
    ResponseList<QuestionVotes> createQuestionVotesList(HttpResponse res) throws FacebookException;

    ResponseList<Score> createScoreList(HttpResponse res) throws FacebookException;

    ResponseList<Subscribedto> createSubscribedtoList(HttpResponse res) throws FacebookException;

    ResponseList<Subscriber> createSubscriberList(HttpResponse res) throws FacebookException;

    ResponseList<Television> createTelevisionList(HttpResponse res) throws FacebookException;

    Video createVideo(HttpResponse res) throws FacebookException;
    ResponseList<Video> createVideoList(HttpResponse res) throws FacebookException;

    ResponseList<Tag> createTagList(HttpResponse res) throws FacebookException;

    ResponseList<GroupMember> createGroupMemberList(HttpResponse res) throws FacebookException;

    ResponseList<Insight> createInsightList(HttpResponse res) throws FacebookException;

    ResponseList<Tagged> createTaggedList(HttpResponse res) throws FacebookException;

    ResponseList<Milestone> createMilestoneList(HttpResponse res) throws FacebookException;

    ResponseList<Admin> createAdminList(HttpResponse res) throws FacebookException;

    ResponseList<Tab> createTabList(HttpResponse res) throws FacebookException;

    ResponseList<Offer> createOfferList(HttpResponse res) throws FacebookException;
    Offer createOffer(HttpResponse res) throws FacebookException;


    Application createApplication(HttpResponse res) throws FacebookException;


    TestUser createTestUser(HttpResponse res) throws FacebookException;

    TestUser createTestUser(JSONObject json) throws FacebookException;

    ResponseList<TestUser> createTestUserList(HttpResponse res) throws FacebookException;

    ResponseList<JSONObject> createJSONObjectList(HttpResponse res) throws FacebookException;
    ResponseList<JSONObject> createJSONObjectList(JSONObject json) throws FacebookException;

    <T> ResponseList<T> createResponseList(HttpResponse res, Class<T> jsonObjectType) throws FacebookException;

    ResponseList<Reaction> createReactionList(HttpResponse res) throws FacebookException;
}
