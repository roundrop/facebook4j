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
import facebook4j.PageSetting;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 1.2.0
 */
public class PageSettingJSONImpl extends FacebookResponseImpl implements PageSetting, java.io.Serializable {
    private static final long serialVersionUID = -6960164932151941721L;

    private String setting;
    private Boolean value;

    /*package*/PageSettingJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/PageSettingJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) {
        setting = getRawString("setting", json);
        value = getBoolean("value", json);
    }

    public String getSetting() {
        return setting;
    }

    public Boolean getValue() {
        return value;
    }

    /*package*/
    static ResponseList<PageSetting> createLikeList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<PageSetting> settings = new ResponseListImpl<PageSetting>(size, json);
            for (int i = 0; i < size; i++) {
                PageSetting setting = new PageSettingJSONImpl(list.getJSONObject(i));
                settings.add(setting);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(settings, json);
            }
            return settings;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageSettingJSONImpl)) return false;

        PageSettingJSONImpl that = (PageSettingJSONImpl) o;

        if (setting != null ? !setting.equals(that.setting) : that.setting != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = setting != null ? setting.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageSettingJSONImpl{" +
                "setting='" + setting + '\'' +
                ", value=" + value +
                '}';
    }
}
