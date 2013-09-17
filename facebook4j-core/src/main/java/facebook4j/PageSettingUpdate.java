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

import facebook4j.internal.http.HttpParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class PageSettingUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -4906471942303848646L;

    private String setting;
    private boolean value;

    public PageSettingUpdate() {
        super();
    }

    public PageSettingUpdate(String setting, boolean value) {
        this.setting = setting;
        this.value = value;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public PageSettingUpdate setting(String setting) {
        setSetting(setting);
        return this;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public PageSettingUpdate value(boolean value) {
        setValue(value);
        return this;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("setting", setting));
        params.add(new HttpParameter("value", value));
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageSettingUpdate)) return false;

        PageSettingUpdate that = (PageSettingUpdate) o;

        if (value != that.value) return false;
        if (setting != null ? !setting.equals(that.setting) : that.setting != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = setting != null ? setting.hashCode() : 0;
        result = 31 * result + (value ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageSettingUpdate{" +
                "setting='" + setting + '\'' +
                ", value=" + value +
                '}';
    }
}
