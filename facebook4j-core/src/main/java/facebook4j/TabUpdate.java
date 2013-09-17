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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class TabUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 7358979543362576699L;

    private Integer position;
    private String customName;
    private Boolean isNonConnectionLandingTab;
    private URL customImageUrl;
    private Media customImage;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    public TabUpdate position(Integer position) {
        setPosition(position);
        return this;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
    public TabUpdate customName(String customName) {
        setCustomName(customName);
        return this;
    }

    public Boolean getNonConnectionLandingTab() {
        return isNonConnectionLandingTab;
    }

    public void setNonConnectionLandingTab(Boolean nonConnectionLandingTab) {
        isNonConnectionLandingTab = nonConnectionLandingTab;
    }
    public TabUpdate nonConnectionLandingTab(Boolean nonConnectionLandingTab) {
        setNonConnectionLandingTab(nonConnectionLandingTab);
        return this;
    }

    public URL getCustomImageUrl() {
        return customImageUrl;
    }

    public void setCustomImageUrl(URL customImageUrl) {
        this.customImageUrl = customImageUrl;
    }
    public TabUpdate customImageUrl(URL customImageUrl) {
        setCustomImageUrl(customImageUrl);
        return this;
    }

    public Media getCustomImage() {
        return customImage;
    }

    public void setCustomImage(Media customImage) {
        this.customImage = customImage;
    }
    public TabUpdate customImage(Media customImage) {
        setCustomImage(customImage);
        return this;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        if (position != null) {
            params.add(new HttpParameter("position", position));
        }
        if (customName != null) {
            params.add(new HttpParameter("custom_name", customName));
        }
        if (isNonConnectionLandingTab != null) {
            params.add(new HttpParameter("is_non_connection_landing_tab", isNonConnectionLandingTab));
        }
        if (customImageUrl != null) {
            params.add(new HttpParameter("custom_image_url", customImageUrl.toString()));
        }
        if (customImage != null) {
            params.add(customImage.asHttpParameter("custom_image"));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TabUpdate)) return false;

        TabUpdate tabUpdate = (TabUpdate) o;

        if (customImage != null ? !customImage.equals(tabUpdate.customImage) : tabUpdate.customImage != null)
            return false;
        if (customImageUrl != null ? !customImageUrl.equals(tabUpdate.customImageUrl) : tabUpdate.customImageUrl != null)
            return false;
        if (customName != null ? !customName.equals(tabUpdate.customName) : tabUpdate.customName != null) return false;
        if (isNonConnectionLandingTab != null ? !isNonConnectionLandingTab.equals(tabUpdate.isNonConnectionLandingTab) : tabUpdate.isNonConnectionLandingTab != null)
            return false;
        if (position != null ? !position.equals(tabUpdate.position) : tabUpdate.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (customName != null ? customName.hashCode() : 0);
        result = 31 * result + (isNonConnectionLandingTab != null ? isNonConnectionLandingTab.hashCode() : 0);
        result = 31 * result + (customImageUrl != null ? customImageUrl.hashCode() : 0);
        result = 31 * result + (customImage != null ? customImage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TabUpdate{" +
                "position=" + position +
                ", customName='" + customName + '\'' +
                ", isNonConnectionLandingTab=" + isNonConnectionLandingTab +
                ", customImageUrl=" + customImageUrl +
                ", customImage=" + customImage +
                '}';
    }
}