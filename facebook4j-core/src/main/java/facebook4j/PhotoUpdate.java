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
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class PhotoUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -2679992754222742305L;

    private Media source;
    private URL url;
    private String message;
    private String place;
    private TargetingParameter targeting;
    private FeedTargetingParameter feedTargeting;
    private Boolean noStory;
    private PrivacyParameter privacy;
    private Boolean published;
    private Integer scheduledPublishTime;

    public PhotoUpdate(Media source) {
        this.source = source;
    }

    public PhotoUpdate(URL url) {
        this.url = url;
    }

    public Media getSource() {
        return source;
    }

    public URL getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PhotoUpdate message(String message) {
        setMessage(message);
        return this;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public PhotoUpdate place(String place) {
        setPlace(place);
        return this;
    }

    public TargetingParameter getTargeting() {
        return targeting;
    }

    public void setTargeting(TargetingParameter targeting) {
        this.targeting = targeting;
    }

    public PhotoUpdate targeting(TargetingParameter targetingParameter) {
        setTargeting(targetingParameter);
        return this;
    }

    public FeedTargetingParameter getFeedTargeting() {
        return feedTargeting;
    }

    public void setFeedTargeting(FeedTargetingParameter feedTargeting) {
        this.feedTargeting = feedTargeting;
    }

    public PhotoUpdate feedTargeting(FeedTargetingParameter feedTargeting) {
        setFeedTargeting(feedTargeting);
        return this;
    }

    public Boolean getNoStory() {
        return noStory;
    }

    public void setNoStory(boolean noStory) {
        this.noStory = noStory;
    }

    public PhotoUpdate noStory(boolean noStory) {
        setNoStory(noStory);
        return this;
    }

    public PrivacyParameter getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyParameter privacy) {
        this.privacy = privacy;
    }

    public PhotoUpdate privacy(PrivacyParameter privacy) {
        setPrivacy(privacy);
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public PhotoUpdate published(boolean published) {
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
    	if(scheduledPublishTime==null){
    		setScheduledPublishTime((Integer)null);
    	}
    	else{
    		long time = scheduledPublishTime.getTime() / 1000L;
    		setScheduledPublishTime(Long.valueOf(time).intValue());
    	}
    }

    public PhotoUpdate scheduledPublishTime(Integer scheduledPublishTime) {
        setScheduledPublishTime(scheduledPublishTime);
        return this;
    }

    public PhotoUpdate scheduledPublishTime(Date scheduledPublishTime) {
    	setScheduledPublishTime(scheduledPublishTime);
        return this;
    }


    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        if (source != null) {
            params.add(source.asHttpParameter("source"));
        }
        if (url != null) {
            params.add(new HttpParameter("url", url.toString()));
        }
        if (message != null) {
            params.add(new HttpParameter("message", message));
        }
        if (place != null) {
            params.add(new HttpParameter("place", place));
        }
        if (targeting != null) {
            params.add(new HttpParameter("targeting", targeting.asJSONString()));
        }
        if (feedTargeting != null) {
            params.add(new HttpParameter("feed_targeting", feedTargeting.asJSONString()));
        }
        if (noStory != null) {
            if (noStory) {
                params.add(new HttpParameter("no_story", 1));
            }
        }
        if (privacy != null) {
            params.add(new HttpParameter("privacy", privacy.asJSONString()));
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
        if (!(o instanceof PhotoUpdate)) return false;

        PhotoUpdate that = (PhotoUpdate) o;

        if (feedTargeting != null ? !feedTargeting.equals(that.feedTargeting) : that.feedTargeting != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (noStory != null ? !noStory.equals(that.noStory) : that.noStory != null) return false;
        if (targeting != null ? !targeting.equals(that.targeting) : that.targeting != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (privacy != null ? !privacy.equals(that.privacy) : that.privacy != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (scheduledPublishTime != null ? !scheduledPublishTime.equals(that.scheduledPublishTime) : that.scheduledPublishTime != null)
            return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (targeting != null ? targeting.hashCode() : 0);
        result = 31 * result + (feedTargeting != null ? feedTargeting.hashCode() : 0);
        result = 31 * result + (noStory != null ? noStory.hashCode() : 0);
        result = 31 * result + (privacy != null ? privacy.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (scheduledPublishTime != null ? scheduledPublishTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhotoUpdate{" +
                "source=" + source +
                ", url=" + url +
                ", message='" + message + '\'' +
                ", place='" + place + '\'' +
                ", noStory=" + noStory +
                ", privacy=" + privacy +
                ", published=" + published +
                ", scheduledPublishTime=" + scheduledPublishTime +
                '}';
    }
}
