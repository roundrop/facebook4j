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
public class PageCoverUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -2169356652157356642L;

    private final String photoId;
    private Integer offsetY;
    private Boolean noFeedStory;

    public PageCoverUpdate(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoId() {
        return photoId;
    }

    public Integer getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Integer offsetY) {
        this.offsetY = offsetY;
    }

    public PageCoverUpdate offsetY(Integer offsetY) {
        setOffsetY(offsetY);
        return this;
    }

    public Boolean getNoFeedStory() {
        return noFeedStory;
    }

    public void setNoFeedStory(Boolean noFeedStory) {
        this.noFeedStory = noFeedStory;
    }

    public PageCoverUpdate noFeedStory(Boolean noFeedStory) {
        setNoFeedStory(noFeedStory);
        return this;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("cover", photoId));
        if (offsetY != null) {
            params.add(new HttpParameter("offset_y", offsetY));
        }
        if (noFeedStory != null) {
            params.add(new HttpParameter("no_feed_story", noFeedStory));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageCoverUpdate)) return false;

        PageCoverUpdate that = (PageCoverUpdate) o;

        if (noFeedStory != null ? !noFeedStory.equals(that.noFeedStory) : that.noFeedStory != null) return false;
        if (offsetY != null ? !offsetY.equals(that.offsetY) : that.offsetY != null) return false;
        if (photoId != null ? !photoId.equals(that.photoId) : that.photoId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + (offsetY != null ? offsetY.hashCode() : 0);
        result = 31 * result + (noFeedStory != null ? noFeedStory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageCoverUpdate{" +
                "photoId='" + photoId + '\'' +
                ", offsetY=" + offsetY +
                ", noFeedStory=" + noFeedStory +
                '}';
    }
}
