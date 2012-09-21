package facebook4j;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchMethodsTest extends FacebookTestBase {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchPosts() throws Exception {
        ResponseList<Post> posts = facebook1.searchPosts("watermelon"/*, new Reading().fields("name")*/);
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    public void searchUsers() throws Exception {
        ResponseList<User> users = real.searchUsers("roundrop"/*, new Reading().fields("name", "gender")*/);
        for (User user : users) {
            System.out.println(user);
        }
    }
    
    @Test
    public void searchEvents() throws Exception {
        ResponseList<Event> events = facebook1.searchEvents("conference");
        for (Event event : events) {
            System.out.println(event);
        }
    }
    
    @Test
    public void searchGroups() throws Exception {
        ResponseList<Group> groups = facebook1.searchGroups("programming");
        for (Group group : groups) {
            System.out.println(group);
        }
    }
    
    @Test
    public void searchPlaces() throws Exception {
        ResponseList<Place> places = facebook1.searchPlaces("coffee");
        for (Place place : places) {
            System.out.println(place);
        }
    }
    
    @Test
    public void searchPlacesWithGeoLocation() throws Exception {
        GeoLocation center = new GeoLocation(37.76, -122.427);
        int distance = 1000;
        ResponseList<Place> places = facebook1.searchPlaces("coffee", center, distance);
        for (Place place : places) {
            System.out.println(place);
        }
    }
    
    @Test
    public void searchCheckins() throws Exception {
        ResponseList<Checkin> checkins = real.searchCheckins();
        for (Checkin checkin : checkins) {
            System.out.println(checkin);
        }
    }
    
    @Test
    public void searchLocationsByGeo() throws Exception {
        GeoLocation center = new GeoLocation(35.466188, 139.622715);
        int distance = 1000;
        ResponseList<Location> locations = real.searchLocations(center, distance);
        for (Location location : locations) {
            System.out.println(location);
        }
    }
    
    @Test
    public void searchLocationsByPlace() throws Exception {
        String placeId = "166793820034304";
        ResponseList<Location> locations = real.searchLocations(placeId);
        for (Location location : locations) {
            System.out.println(location);
        }
    }

}
