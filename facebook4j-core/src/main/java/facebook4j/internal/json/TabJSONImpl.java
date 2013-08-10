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

import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.ResponseList;
import facebook4j.Tab;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.URL;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class TabJSONImpl extends FacebookResponseImpl implements Tab, java.io.Serializable {
    private static final long serialVersionUID = -7967769244971110725L;

    private String id;
    private String name;
    private URL link;
    private IdNameEntity application;
    private String customName;
    private Boolean isPermanent;
    private Integer position;
    private Boolean isNonConnectionLandingTab;
    private URL imageURL;
    private URL customImageURL;

    /*package*/TabJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/TabJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            name = getRawString("name", json);
            link = getURL("link", json);
            if (!json.isNull("application")) {
                application = new IdNameEntityJSONImpl(json.getJSONObject("application"));
            }
            customName = getRawString("custom_name", json);
            isPermanent = getBoolean("is_permanent", json);
            position = getInt("position", json);
            isNonConnectionLandingTab = getBoolean("is_non_connection_landing_tab", json);
            imageURL = getURL("image_url", json);
            customImageURL = getURL("custom_image_url", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public URL getLink() {
        return link;
    }

    public IdNameEntity getApplication() {
        return application;
    }

    public String getCustomName() {
        return customName;
    }

    public Boolean isPermanent() {
        return isPermanent;
    }

    public Integer getPosition() {
        return position;
    }

    public Boolean isNonConnectionLandingTab() {
        return isNonConnectionLandingTab;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public URL getCustomImageURL() {
        return customImageURL;
    }

    /*package*/
    static ResponseList<Tab> createTabList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Tab> tabs = new ResponseListImpl<Tab>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject tabJSONObject = list.getJSONObject(i);
                Tab tab = new TabJSONImpl(tabJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(tab, tabJSONObject);
                }
                tabs.add(tab);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(tabs, list);
            }
            return tabs;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TabJSONImpl)) return false;

        TabJSONImpl tabJSON = (TabJSONImpl) o;

        if (id != null ? !id.equals(tabJSON.id) : tabJSON.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TabJSONImpl{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link=" + link +
                ", application=" + application +
                ", customName='" + customName + '\'' +
                ", isPermanent=" + isPermanent +
                ", position=" + position +
                ", isNonConnectionLandingTab=" + isNonConnectionLandingTab +
                ", imageURL=" + imageURL +
                ", customImageURL=" + customImageURL +
                '}';
    }
}
