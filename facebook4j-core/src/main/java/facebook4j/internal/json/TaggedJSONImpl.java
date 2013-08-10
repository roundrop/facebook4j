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

import facebook4j.*;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.*;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class TaggedJSONImpl extends FacebookResponseImpl implements Tagged, java.io.Serializable {
    private static final long serialVersionUID = -6025394310112011794L;

    private String id;
    private String type;
    private Post post;
    private Photo photo;
    private Video video;
    private String message;
    private Map<String, Tag[]> messageTags;
    private String objectId;
    private Privacy privacy;
    private List<Category> to;
    private Date updatedTime;

    /*package*/TaggedJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/TaggedJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            type = getRawString("type", json);
            if (type.equals("photo")) {
                photo = new PhotoJSONImpl(json);
            } else
            if (type.equals("video")) {
                video = new VideoJSONImpl(json);
            } else {
                post = new PostJSONImpl(json);
            }
            if (!json.isNull("message")) {
                message = getRawString("message", json);
            }
            if (!json.isNull("message_tags")) {
                JSONObject messageTagsJSONObject = json.getJSONObject("message_tags");
                messageTags = new HashMap<String, Tag[]>();
                @SuppressWarnings("unchecked")
                Iterator<String> keys = messageTagsJSONObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    JSONArray messageTagsJSONArray = messageTagsJSONObject.getJSONArray(key);
                    Tag[] tags = new Tag[messageTagsJSONArray.length()];
                    for (int i = 0; i < messageTagsJSONArray.length(); i++) {
                        JSONObject tag = messageTagsJSONArray.getJSONObject(i);
                        tags[i] = new TagJSONImpl(tag);
                    }
                    messageTags.put(key, tags);
                }
            } else {
                messageTags = Collections.emptyMap();
            }
            if (!json.isNull("object_id")) {
                objectId = getRawString("object_id", json);
            }
            privacy = new PrivacyJSONImpl(json.getJSONObject("privacy"));
            if (!json.isNull("to")) {
                JSONArray toJSONArray = json.getJSONObject("to").getJSONArray("data");
                to = new ArrayList<Category>();
                for (int i = 0; i < toJSONArray.length(); i++) {
                    JSONObject toJSONObject = toJSONArray.getJSONObject(i);
                    to.add(new CategoryJSONImpl(toJSONObject));
                }
            } else {
                to = Collections.emptyList();
            }
            updatedTime = getISO8601Datetime("updated_time", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Post getPost() {
        return post;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Video getVideo() {
        return video;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Tag[]> getMessageTags() {
        return messageTags;
    }

    public String getObjectId() {
        return objectId;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public List<Category> getTo() {
        return to;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    /*package*/
    static ResponseList<Tagged> createTaggedList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Tagged> taggeds = new ResponseListImpl<Tagged>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject taggedJSONObject = list.getJSONObject(i);
                Tagged tagged = new TaggedJSONImpl(taggedJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(tagged, taggedJSONObject);
                }
                taggeds.add(tagged);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(taggeds, list);
            }
            return taggeds;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaggedJSONImpl)) return false;

        TaggedJSONImpl that = (TaggedJSONImpl) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TaggedJSONImpl{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", post=" + post +
                ", photo=" + photo +
                ", video=" + video +
                ", message='" + message + '\'' +
                ", messageTags=" + messageTags +
                ", objectId='" + objectId + '\'' +
                ", privacy=" + privacy +
                ", to=" + to +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
