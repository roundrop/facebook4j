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
import facebook4j.auth.OAuthSupport;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface Facebook extends java.io.Serializable,
    OAuthSupport,
    FacebookBase,
    UserMethods,
    AccountMethods,
    ActivityMethods,
    AlbumMethods,
    CheckinMethods,
    CommentMethods,
    DomainMethods,
    EventMethods,
    FamilyMethods,
    FavoriteMethods,
    FriendMethods,
    GameMethods,
    GroupMethods,
    LikeMethods,
    LinkMethods,
    LocationMethods,
    MessageMethods,
    ConversationMethods,
    NoteMethods,
    NotificationMethods,
    PageMethods,
    PermissionMethods,
    PhotoMethods,
    PokeMethods,
    PostMethods,
    QuestionMethods,
    SubscribeMethods,
    VideoMethods,
    InsightMethods,
    SearchMethods,
    TestUserMethods,
    FQLMethods,
    BatchRequestsMethods,
    RawAPIMethods {

    /**
     * @since Facebook4J 2.0.5
     */
    UserMethods users();

    /**
     * @since Facebook4J 2.0.5
     */
    AccountMethods accounts();

    /**
     * @since Facebook4J 2.0.5
     */
    ActivityMethods activities();

    /**
     * @since Facebook4J 2.0.5
     */
    AlbumMethods albums();

    /**
     * @since Facebook4J 2.0.5
     */
    CheckinMethods checkins();

    /**
     * @since Facebook4J 2.0.5
     */
    CommentMethods comments();

    /**
     * @since Facebook4J 2.0.5
     */
    DomainMethods domains();

    /**
     * @since Facebook4J 2.0.5
     */
    EventMethods events();

    /**
     * @since Facebook4J 2.0.5
     */
    FamilyMethods family();

    /**
     * @since Facebook4J 2.0.5
     */
    FavoriteMethods favorites();

    /**
     * @since Facebook4J 2.0.5
     */
    FriendMethods friends();

    /**
     * @since Facebook4J 2.0.5
     */
    GameMethods games();

    /**
     * @since Facebook4J 2.0.5
     */
    GroupMethods groups();

    /**
     * @since Facebook4J 2.0.5
     */
    LikeMethods likes();

    /**
     * @since Facebook4J 2.0.5
     */
    LinkMethods links();

    /**
     * @since Facebook4J 2.0.5
     */
    LocationMethods locations();

    /**
     * @since Facebook4J 2.0.5
     */
    MessageMethods messages();

    /**
     * @return Facebook4J 2.4.8
     */
    ConversationMethods conversations();

    /**
     * @since Facebook4J 2.0.5
     */
    NoteMethods notes();

    /**
     * @since Facebook4J 2.0.5
     */
    NotificationMethods notifications();

    /**
     * @since Facebook4J 2.0.5
     */
    PageMethods pages();

    /**
     * @since Facebook4J 2.0.5
     */
    PermissionMethods permissions();

    /**
     * @since Facebook4J 2.0.5
     */
    PhotoMethods photos();

    /**
     * @since Facebook4J 2.0.5
     */
    PokeMethods pokes();

    /**
     * @since Facebook4J 2.0.5
     */
    PostMethods posts();

    /**
     * @since Facebook4J 2.0.5
     */
    QuestionMethods questions();

    /**
     * @since Facebook4J 2.0.5
     */
    SubscribeMethods subscribes();

    /**
     * @since Facebook4J 2.0.5
     */
    VideoMethods videos();

    /**
     * @since Facebook4J 2.0.5
     */
    InsightMethods insights();

    /**
     * @since Facebook4J 2.0.5
     */
    SearchMethods search();

    /**
     * @since Facebook4J 2.0.5
     */
    TestUserMethods testUsers();

    /**
     * @since Facebook4J 2.0.5
     */
    FQLMethods fql();

    /**
     * @since Facebook4J 2.1.0
     */
    BatchRequestsMethods batch();

    /**
     * @since Facebook4J 2.1.0
     */
    RawAPIMethods rawAPI();
}
