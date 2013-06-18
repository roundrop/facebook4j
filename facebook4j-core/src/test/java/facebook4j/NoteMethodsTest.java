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

import facebook4j.internal.http.RequestMethod;
import org.junit.Test;

import static facebook4j.junit.URLMatchers.pathOf;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class NoteMethodsTest extends MockFacebookTestBase {

    @Test
    public void getNotes() throws Exception {
        facebook.setMockJSON("mock_json/notes/page.json");
        ResponseList<Note> notes = facebook.getNotes("74100576336");
        assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
        assertThat(facebook.getEndpointURL(), is(pathOf("/74100576336/notes")));

        assertThat(notes.size(), is(24));

        Note note1 = notes.get(0);
        assertThat(note1.getComments().size(), is(16));
        Comment comment11 = note1.getComments().get(0);
        assertThat(comment11.canRemove(), is(false));
        assertThat(comment11.getCreatedTime(), is(iso8601DateOf("2012-05-31T17:34:44+0000")));
        assertThat(comment11.getFrom().getId(), is("511143774"));
        assertThat(comment11.getFrom().getName(), is("Matteo Gamba"));
        assertThat(comment11.getId(), is("10150827964117217_21854759"));
        assertThat(comment11.getLikeCount(), is(2));
        assertThat(comment11.getMessage(), is("when are they gonna be available for everybody?"));
        assertThat(comment11.isUserLikes(), is(false));
        Comment comment1f = note1.getComments().get(15);
        assertThat(comment1f.canRemove(), is(false));
        assertThat(comment1f.getCreatedTime(), is(iso8601DateOf("2012-10-12T18:51:01+0000")));
        assertThat(comment1f.getFrom().getCategory(), is("Business services"));
        assertThat(comment1f.getFrom().getId(), is("157467027682541"));
        assertThat(comment1f.getFrom().getName(), is("Pixels V"));
        assertThat(comment1f.getId(), is("10150827964117217_23346911"));
        assertThat(comment1f.getLikeCount(), is(0));
        assertThat(comment1f.getMessage(), is("Something like that MrMonetize posted a few days ago. Please do that as soon as the better. Thanks..."));
        assertThat(comment1f.isUserLikes(), is(false));
        assertThat(note1.getCreatedTime(), is(iso8601DateOf("2012-05-31T17:33:56+0000")));
        assertThat(note1.getFrom().getCategory(), is("Website"));
        assertThat(note1.getFrom().getId(), is("74100576336"));
        assertThat(note1.getFrom().getName(), is("Facebook Marketing"));
        assertThat(note1.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yY/r/1gBp2bDGEuh.gif"));
        assertThat(note1.getId(), is("10150827964117217"));
        assertThat(note1.getMessage(), is("<div><p><span class=\"fbUnderline\"><strong>Overview on what is launching</strong></span></p><p>We are launching two Pages management features.  These features are admin permissions and Page post scheduling. We  are excited to open up more Pages features that allow you to more  effectively manage your Facebook Page. These features give you more  control over your Page.</p><ul><li><span class=\"fbUnderline\"><strong>Admin  permissions</strong></span> allows you to designate different permission levels to  other admins on your Page. There are five different permission levels:  Manager, content creator, moderator, advertiser, and insights analysit.  Note that at default, all admins will have the highest permission level  of Manager. Please take the time to decide the permission levels for  the admins on your Page. You can view and change permission levels under  Edit Page &gt; Manage Admins. You can find more information here: <a href=\"https://www.facebook.com/help/?page=394501407235259\">https://www.facebook.com/help/?page=394501407235259</a>.</li></ul><ul><li><span class=\"fbUnderline\"><strong>Page  post scheduling</strong></span> allows you to schedule your Page posts up to 6 months  in advance, and as frequently as every 15 minutes. This is a great way  for you to plan out your Page posts and prepare them in advance. You can  find more information here: <a href=\"https://www.facebook.com/help/?faq=389849807718635&amp;in_context\">https://www.facebook.com/help/?faq=389849807718635&amp;in_context</a>.</li></ul><p>We  believe that these features will help you better manage your presence  on Facebook. Let us know if you have any other questions.</p></div>"));
        assertThat(note1.getSubject(), is("Admin permissions and Page post scheduling"));
        assertThat(note1.getUpdatedTime(), is(iso8601DateOf("2012-05-31T17:33:56+0000")));

        Note note24 = notes.get(23);
        assertThat(note24.getComments().size(), is(8));
        Comment comment231 = note24.getComments().get(0);
        assertThat(comment231.canRemove(), is(false));
        assertThat(comment231.getCreatedTime(), is(iso8601DateOf("2009-03-23T16:59:16+0000")));
        assertThat(comment231.getFrom().getId(), is("517791959"));
        assertThat(comment231.getFrom().getName(), is("Jed Sundwall"));
        assertThat(comment231.getId(), is("75446527216_1143788"));
        assertThat(comment231.getLikeCount(), is(0));
        assertThat(comment231.getMessage(), is("Re: simple point #2 - I'd like to know if there's any way to respond to commenters with more than 160 characters. I'm also concerned that our responses to commenters via the Wall are also used as status updates. Is there a better way to publicly engage commenters via the Wall?"));
        assertThat(comment231.isUserLikes(), is(false));
        assertThat(note24.getCreatedTime(), is(iso8601DateOf("2009-03-23T12:48:48+0000")));
        assertThat(note24.getFrom().getCategory(), is("Website"));
        assertThat(note24.getFrom().getId(), is("74100576336"));
        assertThat(note24.getFrom().getName(), is("Facebook Marketing"));
        assertThat(note24.getIcon().toString(), is("https://fbstatic-a.akamaihd.net/rsrc.php/v2/yY/r/1gBp2bDGEuh.gif"));
        assertThat(note24.getId(), is("75446527216"));
        assertThat(note24.getMessage(), is("<div><b>Getting Started</b> <br />Now that Facebook Pages have the ability to generate stories in its Fans\u2019 News Feeds, the<br />content and frequency of sharing has become more important than ever.</div>"));
        assertThat(note24.getSubject(), is("Make the Most of your New Page for Brands"));
        assertThat(note24.getUpdatedTime(), is(iso8601DateOf("2009-04-12T16:42:19+0000")));

        assertThat(notes.getPaging().getNext().toString(), is("https://graph.facebook.com/74100576336/notes?access_token=access_token&limit=25&after=NzU0NDY1MjcyMTY%3D"));
        assertThat(notes.getPaging().getPrevious(), is(nullValue()));
    }

}
