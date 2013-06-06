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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckinMethodsTest extends MockFacebookTestBase {

    @Test
    public void checkins_page() throws Exception {
        facebook.setMockJSON("mock_json/checkins/page.json");
        String pageId = "149655571740038";
        ResponseList<Checkin> checkins = facebook.getCheckins(pageId);

        assertThat(checkins.size(), is(2));

        Checkin checkin1 = checkins.get(0);
        assertThat(checkin1.getApplication().getId(), is("6628568379"));
        assertThat(checkin1.getApplication().getName(), is("Facebook for iPhone"));
        assertThat(checkin1.getApplication().getNamespace(), is("fbiphone"));
        assertThat(checkin1.getFrom().getId(), is("1111"));
        assertThat(checkin1.getFrom().getName(), is("From Name1"));
        assertThat(checkin1.getId(), is("2222"));
        assertThat(checkin1.getLikes().size(), is(3));
        assertThat(checkin1.getLikes().get(0).getId(), is("3331"));
        assertThat(checkin1.getLikes().get(0).getName(), is("Like Name1"));
        assertThat(checkin1.getLikes().get(1).getId(), is("3332"));
        assertThat(checkin1.getLikes().get(1).getName(), is("Like Name2"));
        assertThat(checkin1.getLikes().get(2).getId(), is("3333"));
        assertThat(checkin1.getLikes().get(2).getName(), is("Like Name3"));
        assertThat(checkin1.getMessage(), is("Message"));
        assertThat(checkin1.getPlace().getId(), is("149655571740038"));
        assertThat(checkin1.getPlace().getLocation().getCity(), is("Sumida-ku"));
        assertThat(checkin1.getPlace().getLocation().getCountry(), is("Japan"));
        assertThat(checkin1.getPlace().getLocation().getLatitude(), is(35.710235962898));
        assertThat(checkin1.getPlace().getLocation().getLongitude(), is(139.81112668973));
        assertThat(checkin1.getPlace().getLocation().getState(), is("Tokyo"));
        assertThat(checkin1.getPlace().getLocation().getStreet(), is("押上1-1-13"));
        assertThat(checkin1.getPlace().getLocation().getZip(), is("131-0045"));
        assertThat(checkin1.getPlace().getName(), is("東京スカイツリー (Tokyo Sky Tree)"));

        assertThat(checkins.get(1).getId(), is("5555"));

        assertThat(checkins.getPaging().getNext().toString(), is("https://graph.facebook.com/149655571740038/checkins?access_token=access_token&limit=25&until=1111111111&__paging_token=179367212156708"));
        assertThat(checkins.getPaging().getPrevious().toString(), is("https://graph.facebook.com/149655571740038/checkins?access_token=access_token&limit=25&since=2222222222&__paging_token=395794067108539&__previous=1"));
    }

/*
    private String checkin1() throws Exception {
        String place = "100404700021921";
        GeoLocation coordinates = new GeoLocation(35.675272122419, 139.69321689514);
        CheckinCreate checkin = new CheckinCreate(place, coordinates);
        return facebook1.checkin(checkin);
    }
    
    private String checkin2() throws Exception {
        String place = "154470644580235";
        GeoLocation coordinates = new GeoLocation(35.678745360759, 139.76759590553);
        String tags = null;
        String message = "test message";
        URL link = new URL("http://www.facebook.com/");
        URL picture = null;
        CheckinCreate checkin = new CheckinCreate(place, coordinates, tags, message, link, picture);
        return facebook1.checkin(checkin);
    }
    
    @Test
    public void create() throws Exception {
        String checkinId = checkin1();
        assertThat(checkinId, is(notNullValue()));
    }

    @Test(expected = FacebookException.class)
    public void createByOtherUser() throws Exception {
        String place = "100404700021921";
        GeoLocation coordinates = new GeoLocation(35.675272122419, 139.69321689514);
        CheckinCreate checkin = new CheckinCreate(place, coordinates);
        facebook2.checkin(id1.getId(), checkin);
    }
    
    @Test
    public void createWithLink() throws Exception {
        String checkinId = checkin2();
        assertThat(checkinId, is(notNullValue()));
    }

    @Test
    public void get() throws Exception {
        String checkinId = checkin1();
        
        Checkin checkin = facebook1.getCheckin(checkinId);
        assertThat(checkin, is(notNullValue()));
        assertThat(checkin.getId(), is(checkinId));
        
        //read by other user
        Checkin checkin2 = facebook2.getCheckin(checkinId, new Reading().fields("id"));
        assertThat(checkin2, is(notNullValue()));
        assertThat(checkin2.getId(), is(checkinId));
    }
    
    @Test
    public void gets() throws Exception {
        checkin1();

        ResponseList<Checkin> checkins = facebook1.getCheckins();
        assertThat(checkins.size(), is(1));

        //read by other user
        assertThat(facebook2.getCheckins(id1.getId()).size(), is(1));
    }

    @Test
    public void comment() throws Exception {
        String checkinId = checkin1();
        
        String commentId = facebook1.commentCheckin(checkinId, "This is comment for a checkin");
        assertThat(commentId, is(notNullValue()));
        
        //read comment
        ResponseList<Comment> comments = facebook1.getCheckinComments(checkinId);
        assertThat(comments.size(), is(1));
        
        //read by other user
        ResponseList<Comment> comments2 = facebook2.getCheckinComments(checkinId);
        assertThat(comments2.size(), is(1));
    }

    @Test
    public void like() throws Exception {
        String checkinId = checkin1();
        
        //like
        boolean likeResult = facebook1.likeCheckin(checkinId);
        assertThat(likeResult, is(true));

        //read likes
        ResponseList<Like> likes = facebook1.getCheckinLikes(checkinId);
        assertThat(likes.size(), is(1));

        //unlike
        boolean unlikeResult = facebook1.unlikeCheckin(checkinId);
        assertThat(unlikeResult, is(true));

        assertThat(facebook1.getCheckinLikes(checkinId).size(), is(0));
    }
*/
}
