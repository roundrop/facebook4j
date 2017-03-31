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

import java.net.URL;
import java.util.Date;
import java.util.Map;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
/*package*/ final class PageJSONImpl extends FacebookResponseImpl implements Page, java.io.Serializable {
    private static final long serialVersionUID = -5784064954222183184L;

    private String id;
    private String name;
    private String category;
    private Date createdTime;

    private URL link;
    private Boolean isPublished;
    private Boolean canPost;
    private Integer likes;
    private Place.Location location;
    private String phone;
    private Integer checkins;
    private Picture picture;
    private Cover cover;
    private String website;
    private String companyOverview;
    private Integer talkingAboutCount;
    private String accessToken;
    private Boolean isCommunityPage;
    private Integer wereHereCount;
    private Integer fanCount;
    private String about;
    private String username;
    private String mission;
    private Map<String,String> hours;

    /*package*/PageJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/PageJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
        category = getRawString("category", json);
        createdTime = getISO8601Datetime("created_time", json);
        try {
            link = getURL("link", json);
            isPublished = getBoolean("is_published", json);
            canPost = getBoolean("can_post", json);
            likes = getInt("likes", json);
            if (!json.isNull("location")) {
                JSONObject locationJSONObject = json.getJSONObject("location");
                location = new PlaceJSONImpl.LocationJSONImpl(locationJSONObject);
            }
            phone = getRawString("phone", json);
            checkins = getInt("checkins", json);
            if (!json.isNull("picture")) {
                JSONObject pictureJSONObject = json.getJSONObject("picture");
                picture = new PictureJSONImpl(pictureJSONObject);
            }
            if (!json.isNull("cover")) {
                JSONObject coverJSONObject = json.getJSONObject("cover");
                cover = new CoverJSONImpl(coverJSONObject);
            }
            website = getRawString("website", json);
            companyOverview = getRawString("company_overview", json);
            talkingAboutCount = getInt("talking_about_count", json);
            accessToken = getRawString("access_token", json);
            isCommunityPage = getBoolean("is_community_page", json);
            wereHereCount = getInt("were_here_count", json);
            fanCount = getInt("fan_count", json);
            about = getRawString("about", json);
            username = getRawString("username", json);
            mission = getRawString("mission", json);
            hours = getStringMap("hours", json);

        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Date getCreatedTime() {
        return createdTime;
    }


    public URL getLink() {
        return link;
    }

    public Boolean isPublished() {
        return isPublished;
    }

    public Boolean canPost() {
        return canPost;
    }

    public Integer getLikes() {
        return likes;
    }

    public Place.Location getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getCheckins() {
        return checkins;
    }

    public URL getPicture() {
        return picture.getURL();
    }
    
    public Picture getPagePicture() {
        return picture;
    }

    public Cover getCover() {
        return cover;
    }

    public String getWebsite() {
        return website;
    }

    public String getCompanyOverview() {
        return companyOverview;
    }

    public Integer getTalkingAboutCount() {
        return talkingAboutCount;
    }
    
    public String getAccessToken() {
        return accessToken;
    }

    public Boolean isCommunityPage() {
        return isCommunityPage;
    }

    public Integer getWereHereCount() {
        return wereHereCount;
    }

    public Integer getFanCount() {
        return fanCount;
    }

    public String getAbout() {
        return about;
    }

    public String getUsername() {
        return username;
    }

    public String getMission() {
        return mission;
    }

    public Map<String,String> getHours() {
        return hours;
    }

    /*package*/
    static ResponseList<Page> createPageList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Page> pages = new ResponseListImpl<Page>(size, json);
            for (int i = 0; i < size; i++) {
                Page page = new PageJSONImpl(list.getJSONObject(i));
                pages.add(page);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(pages, json);
            }
            return pages;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageJSONImpl)) return false;

        PageJSONImpl pageJSON = (PageJSONImpl) o;

        if (id != null ? !id.equals(pageJSON.id) : pageJSON.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PageJSONImpl [link=" + link + ", isPublished=" + isPublished
                + ", canPost=" + canPost + ", likes=" + likes + ", location="
                + location + ", phone=" + phone + ", checkins=" + checkins
                + ", picture=" + picture + ", cover=" + cover + ", website=" + website
                + ", companyOverview=" + companyOverview + ", talkingAboutCount=" + talkingAboutCount
                + ", accessToken=" + accessToken + ", isCommunityPage="
                + isCommunityPage + ", wereHereCount=" + wereHereCount
                + ", fanCount=" + fanCount
                + ", id=" + id + ", name=" + name + ", category=" + category
                + ", createdTime=" + createdTime + ", about=" + about
                + ", username=" + username + ", mission=" + mission
                + ", hours=" + hours +"]";
    }

}
