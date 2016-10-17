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

import facebook4j.api.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @since Facebook4J 2.0.5
 */
public class NarrowDownAPIMethodsTest {

    private Facebook facebook;

    @Before
    public void setUp() throws Exception {
        facebook = FacebookFactory.getSingleton();
    }

    @Test
    public void users() throws Exception {
        assertThat(facebook.users(), instanceOf(UserMethods.class));
    }

    @Test
    public void accounts() throws Exception {
        assertThat(facebook.accounts(), instanceOf(AccountMethods.class));
    }

    @Test
    public void activities() throws Exception {
        assertThat(facebook.activities(), instanceOf(ActivityMethods.class));
    }

    @Test
    public void albums() throws Exception {
        assertThat(facebook.albums(), instanceOf(AlbumMethods.class));
    }

    @Test
    public void checkins() throws Exception {
        assertThat(facebook.checkins(), instanceOf(CheckinMethods.class));
    }

    @Test
    public void comments() throws Exception {
        assertThat(facebook.comments(), instanceOf(CommentMethods.class));
    }

    @Test
    public void domains() throws Exception {
        assertThat(facebook.domains(), instanceOf(DomainMethods.class));
    }

    @Test
    public void events() throws Exception {
        assertThat(facebook.events(), instanceOf(EventMethods.class));
    }

    @Test
    public void family() throws Exception {
        assertThat(facebook.family(), instanceOf(FamilyMethods.class));
    }

    @Test
    public void favorites() throws Exception {
        assertThat(facebook.favorites(), instanceOf(FavoriteMethods.class));
    }

    @Test
    public void friends() throws Exception {
        assertThat(facebook.friends(), instanceOf(FriendMethods.class));
    }

    @Test
    public void games() throws Exception {
        assertThat(facebook.games(), instanceOf(GameMethods.class));
    }

    @Test
    public void groups() throws Exception {
        assertThat(facebook.groups(), instanceOf(GroupMethods.class));
    }

    @Test
    public void likes() throws Exception {
        assertThat(facebook.likes(), instanceOf(LikeMethods.class));
    }

    @Test
    public void links() throws Exception {
        assertThat(facebook.links(), instanceOf(LinkMethods.class));
    }

    @Test
    public void locations() throws Exception {
        assertThat(facebook.locations(), instanceOf(LocationMethods.class));
    }

    @Test
    public void messages() throws Exception {
        assertThat(facebook.messages(), instanceOf(MessageMethods.class));
    }

    @Test
    public void conversations() throws Exception {
        assertThat(facebook.conversations(), instanceOf(ConversationMethods.class));
    }

    @Test
    public void notes() throws Exception {
        assertThat(facebook.notes(), instanceOf(NoteMethods.class));
    }

    @Test
    public void notifications() throws Exception {
        assertThat(facebook.notifications(), instanceOf(NotificationMethods.class));
    }

    @Test
    public void pages() throws Exception {
        assertThat(facebook.pages(), instanceOf(PageMethods.class));
    }

    @Test
    public void permissions() throws Exception {
        assertThat(facebook.permissions(), instanceOf(PermissionMethods.class));
    }

    @Test
    public void photos() throws Exception {
        assertThat(facebook.photos(), instanceOf(PhotoMethods.class));
    }

    @Test
    public void pokes() throws Exception {
        assertThat(facebook.pokes(), instanceOf(PokeMethods.class));
    }

    @Test
    public void posts() throws Exception {
        assertThat(facebook.posts(), instanceOf(PostMethods.class));
    }

    @Test
    public void questions() throws Exception {
        assertThat(facebook.questions(), instanceOf(QuestionMethods.class));
    }

    @Test
    public void subscribes() throws Exception {
        assertThat(facebook.subscribes(), instanceOf(SubscribeMethods.class));
    }

    @Test
    public void videos() throws Exception {
        assertThat(facebook.videos(), instanceOf(VideoMethods.class));
    }

    @Test
    public void insights() throws Exception {
        assertThat(facebook.insights(), instanceOf(InsightMethods.class));
    }

    @Test
    public void search() throws Exception {
        assertThat(facebook.search(), instanceOf(SearchMethods.class));
    }

    @Test
    public void testUsers() throws Exception {
        assertThat(facebook.testUsers(), instanceOf(TestUserMethods.class));
    }

    @Test
    public void fql() throws Exception {
        assertThat(facebook.fql(), instanceOf(FQLMethods.class));
    }

    @Test
    public void batch() throws Exception {
        assertThat(facebook.batch(), instanceOf(BatchRequestsMethods.class));
    }

    @Test
    public void rawAPI() throws Exception {
        assertThat(facebook.rawAPI(), instanceOf(RawAPIMethods.class));
    }
}
