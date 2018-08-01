package facebook4j;

/**
 * @author felipe.andrade
 *
 */
public interface PageBackedInstagramAccount {

	String getId();
    String getProfilePic();
    String getUsername();
    
    Integer getFollowCount();
    Integer getFollowedByCount();
    Integer getMediaCount();
}
