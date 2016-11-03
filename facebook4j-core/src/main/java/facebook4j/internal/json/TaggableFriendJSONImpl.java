package facebook4j.internal.json;

import facebook4j.FacebookException;
import facebook4j.Picture;
import facebook4j.ResponseList;
import facebook4j.TaggableFriend;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;


final class TaggableFriendJSONImpl extends FacebookResponseImpl implements TaggableFriend, java.io.Serializable  {
    private static final long serialVersionUID = 181026591648082420L;

    private String token;
    private String name;
    private Picture picture;

    /* package */TaggableFriendJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /* package */TaggableFriendJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }


    private void init(JSONObject json) throws FacebookException {
        try {
            token = getRawString("id", json);
            name = getRawString("name", json);
             if (!json.isNull("picture")) {
                 String pictureRawString = getRawString("picture", json);
                 if (pictureRawString.startsWith("{")) {
                     JSONObject pictureJSONObject = json.getJSONObject("picture");
                     picture = new PictureJSONImpl(pictureJSONObject);
                 } else {
                     picture = new PictureJSONImpl(getURL("picture", json));
                 }
             }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public Picture getPicture() {
        return picture;
    }

     /*package*/
    static ResponseList<TaggableFriend> createTaggableFriendList(HttpResponse res, Configuration conf)
            throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<TaggableFriend> friends = new ResponseListImpl<TaggableFriend>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject friendJSONObject = list.getJSONObject(i);
                TaggableFriend friend = new TaggableFriendJSONImpl(friendJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(friend, friendJSONObject);
                }
                friends.add(friend);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(friends, list);
            }
            return friends;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TaggableFriendJSONImpl)) return false;
        return obj instanceof TaggableFriend && ((TaggableFriend) obj).getToken().equals(this.token);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        return result;
    }

     @Override
     public String toString() {
         return "TaggableFriendJSONImpl{" +
                 "id=" + token +
                 "name=" + name +
                 ", picture=" + picture +
                 '}';
     }

}
