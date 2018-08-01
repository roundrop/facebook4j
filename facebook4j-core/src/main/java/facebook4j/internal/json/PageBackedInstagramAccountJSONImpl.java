package facebook4j.internal.json;

import static facebook4j.internal.util.z_F4JInternalParseUtil.getInt;
import static facebook4j.internal.util.z_F4JInternalParseUtil.getRawString;

import java.io.Serializable;

import facebook4j.PageBackedInstagramAccount;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author felipe.andrade
 *
 */
final class PageBackedInstagramAccountJSONImpl extends FacebookResponseImpl implements PageBackedInstagramAccount, Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String profilePic;

	private String username;

	private Integer followCount;

	private Integer followedByCount;

	private Integer mediaCount;

	PageBackedInstagramAccountJSONImpl(JSONObject json) {
        super();
        init(json);
    }
	
	private void init(JSONObject json) {
        id = getRawString("id", json);
        profilePic = getRawString("profile_pic", json);
        followCount = getInt("follow_count", json);
        followedByCount = getInt("followed_by_count", json);
        mediaCount = getInt("media_count", json);
        username = getRawString("username", json);
    }

	public String getId() {
		return id;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public String getUsername() {
		return username;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public Integer getFollowedByCount() {
		return followedByCount;
	}

	public Integer getMediaCount() {
		return mediaCount;
	}

}
