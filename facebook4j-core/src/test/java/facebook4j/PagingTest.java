package facebook4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PagingTest extends MockFacebookTestBase {
    @Test
    public void paging() throws Exception {
        facebook.setMockJSON("mock_json/paging/likes_p1.json");
        ResponseList<Like> page1 = facebook.getPostLikes("216311481960_10201168076257947", new Reading().limit(5));
        Paging<Like> paging = page1.getPaging();

        facebook.setMockJSON("mock_json/paging/likes_p2.json");
        ResponseList<Like> page2 = facebook.fetchNext(paging);
        assertThat(facebook.getEndpointURL().toString(), is("https://graph.facebook.com/216311481960_10201168076257947/likes?limit=5&access_token=access_token&after=MTcxMTAxNDE1MA%3D%3D"));

        facebook.setMockJSON("mock_json/paging/likes_p1.json");
        facebook.fetchPrevious(page2.getPaging());
        assertThat(facebook.getEndpointURL().toString(), is("https://graph.facebook.com/216311481960_10201168076257947/likes?limit=5&access_token=access_token&before=MTAwMDAyMjE5OTkzNzAx"));
    }

    @Test
    public void taggableFriend() throws Exception {
        facebook.setMockJSON("mock_json/friend/taggable_friends.json");
        ResponseList<TaggableFriend> taggableFriends = facebook.getTaggableFriends();
        Paging<TaggableFriend> paging = taggableFriends.getPaging();
        ResponseList<TaggableFriend> next = facebook.fetchNext(paging);
        assertThat(next.size(), is(not(0)));
    }
}
