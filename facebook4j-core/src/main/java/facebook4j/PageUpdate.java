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
public class PageUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 6472977369112573757L;

    private String about;       // Short Description
    private String description;
    private String generalInfo;
    private String website;
    private String phone;

    public PageUpdate() {
        super();
    }

    public PageUpdate(String about, String description, String generalInfo, String website, String phone) {
        super();
        this.about = about;
        this.description = description;
        this.generalInfo = generalInfo;
        this.website = website;
        this.phone = phone;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public PageUpdate about(String about) {
        this.about = about;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PageUpdate description(String description) {
        this.description = description;
        return this;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }

    public PageUpdate generalInfo(String generalInfo) {
        this.generalInfo = generalInfo;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public PageUpdate website(String website) {
        this.website = website;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PageUpdate phone(String phone) {
        this.phone = phone;
        return this;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        if (about != null) {
            params.add(new HttpParameter("about", about));
        }
        if (description != null) {
            params.add(new HttpParameter("description", description));
        }
        if (generalInfo != null) {
            params.add(new HttpParameter("general_info", generalInfo));
        }
        if (website != null) {
            params.add(new HttpParameter("website", website));
        }
        if (phone != null) {
            params.add(new HttpParameter("phone", phone));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((about == null) ? 0 : about.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result
                + ((generalInfo == null) ? 0 : generalInfo.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((website == null) ? 0 : website.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PageUpdate other = (PageUpdate) obj;
        if (about == null) {
            if (other.about != null)
                return false;
        } else if (!about.equals(other.about))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (generalInfo == null) {
            if (other.generalInfo != null)
                return false;
        } else if (!generalInfo.equals(other.generalInfo))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (website == null) {
            if (other.website != null)
                return false;
        } else if (!website.equals(other.website))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PageUpdate [about=" + about + ", description=" + description
                + ", generalInfo=" + generalInfo + ", website=" + website
                + ", phone=" + phone + "]";
    }

}
