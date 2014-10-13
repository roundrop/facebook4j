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
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class VideoUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -757292229143435735L;

    private Media source;
    private String title;
    private String description;
    private Boolean published;
    private Integer scheduledPublishTime;

    public VideoUpdate(Media source) {
        super();
        this.source = source;
    }

    public Media getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VideoUpdate title(String title) {
        setTitle(title);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoUpdate description(String description) {
        setDescription(description);
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public VideoUpdate published(boolean published) {
        setPublished(published);
        return this;
    }

    public Integer getScheduledPublishTime() {
        return scheduledPublishTime;
    }

    public void setScheduledPublishTime(Integer scheduledPublishTime) {
        this.scheduledPublishTime = scheduledPublishTime;
    }

    public void setScheduledPublishTime(Date scheduledPublishTime) {
        long time = scheduledPublishTime.getTime() / 1000L;
        setScheduledPublishTime(Long.valueOf(time).intValue());
    }

    public VideoUpdate scheduledPublishTime(Integer scheduledPublishTime) {
        setScheduledPublishTime(scheduledPublishTime);
        return this;
    }

    public VideoUpdate scheduledPublishTime(Date scheduledPublishTime) {
        long time = scheduledPublishTime.getTime() / 1000L;
        return scheduledPublishTime(Long.valueOf(time).intValue());
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(source.asHttpParameter("source"));
        if (title != null) {
            params.add(new HttpParameter("title", title));
        }
        if (description != null) {
            params.add(new HttpParameter("description", description));
        }
        if (published != null) {
            params.add(new HttpParameter("published", published));
        }
        if (scheduledPublishTime != null) {
            params.add(new HttpParameter("scheduled_publish_time", scheduledPublishTime));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoUpdate)) return false;

        VideoUpdate that = (VideoUpdate) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (scheduledPublishTime != null ? !scheduledPublishTime.equals(that.scheduledPublishTime) : that.scheduledPublishTime != null)
            return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (scheduledPublishTime != null ? scheduledPublishTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideoUpdate{" +
                "source=" + source +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", scheduledPublishTime=" + scheduledPublishTime +
                '}';
    }
}
