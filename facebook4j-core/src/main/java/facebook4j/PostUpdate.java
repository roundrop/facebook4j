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
import facebook4j.internal.org.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class PostUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 7540889634334208530L;

    private String message;
    private URL link;
    private URL picture;
    private String name;
    private String caption;
    private String description;
    private List<PostAction> actions;
    
    private String place;
    private String tags;
    private PrivacyParameter privacy;
    private String objectAttachment;

    private TargetingParameter targeting;

    private Boolean published;
    private Integer scheduledPublishTime;

    public PostUpdate(String message) {
        this.message = message;
    }

    public PostUpdate(URL link) {
        this.link = link;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public PostUpdate message(String message) {
        setMessage(message);
        return this;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }
    
    public PostUpdate link(URL link) {
        setLink(link);
        return this;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }
    
    public PostUpdate picture(URL picture) {
        setPicture(picture);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public PostUpdate name(String name) {
        setName(name);
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    
    public PostUpdate caption(String caption) {
        setCaption(caption);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public PostUpdate description(String description) {
        setDescription(description);
        return this;
    }

    public List<PostAction> getActions() {
        return actions;
    }

    public void setActions(List<PostAction> actions) {
        this.actions = actions;
    }
    
    public PostUpdate actions(List<PostAction> actions) {
        setActions(actions);
        return this;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    public PostUpdate place(String place) {
        setPlace(place);
        return this;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public PostUpdate tags(String tags) {
        setTags(tags);
        return this;
    }

    public PrivacyParameter getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyParameter privacy) {
        this.privacy = privacy;
    }
    
    public PostUpdate privacy(PrivacyParameter privacy) {
        setPrivacy(privacy);
        return this;
    }

    public String getObjectAttachment() {
        return objectAttachment;
    }

    public void setObjectAttachment(String objectAttachment) {
        this.objectAttachment = objectAttachment;
    }
    
    public PostUpdate objectAttachment(String objectAttachment) {
        setObjectAttachment(objectAttachment);
        return this;
    }

    public TargetingParameter getTargeting() {
        return targeting;
    }

    public void setTargeting(TargetingParameter targeting) {
        this.targeting = targeting;
    }

    public PostUpdate targeting(TargetingParameter targetingParameter) {
        setTargeting(targetingParameter);
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public PostUpdate published(Boolean published) {
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

    public PostUpdate scheduledPublishTime(Integer scheduledPublishTime) {
        setScheduledPublishTime(scheduledPublishTime);
        return this;
    }

    public PostUpdate scheduledPublishTime(Date scheduledPublishTime) {
        long time = scheduledPublishTime.getTime() / 1000L;
        return scheduledPublishTime(Long.valueOf(time).intValue());
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        if (message != null) {
            params.add(new HttpParameter("message", message));
        }
        if (link != null) {
            params.add(new HttpParameter("link", link.toString()));
        }
        if (picture != null) {
            params.add(new HttpParameter("picture", picture.toString()));
        }
        if (name != null) {
            params.add(new HttpParameter("name", name));
        }
        if (caption != null) {
            params.add(new HttpParameter("caption", caption));
        }
        if (description != null) {
            params.add(new HttpParameter("description", description));
        }
        if (actions != null && actions.size() != 0) {
            JSONArray jsonArray = new JSONArray(actions);
            params.add(new HttpParameter("actions", jsonArray.toString()));
        }
        if (place != null) {
            params.add(new HttpParameter("place", place));
        }
        if (tags != null) {
            params.add(new HttpParameter("tags", tags));
        }
        if (privacy != null) {
            params.add(new HttpParameter("privacy", privacy.asJSONString()));
        }
        if (objectAttachment != null) {
            params.add(new HttpParameter("object_attachment", objectAttachment));
        }
        if (targeting != null) {
            params.add(new HttpParameter("targeting", targeting.asJSONString()));
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
        if (!(o instanceof PostUpdate)) return false;

        PostUpdate that = (PostUpdate) o;

        if (actions != null ? !actions.equals(that.actions) : that.actions != null) return false;
        if (caption != null ? !caption.equals(that.caption) : that.caption != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (objectAttachment != null ? !objectAttachment.equals(that.objectAttachment) : that.objectAttachment != null)
            return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (privacy != null ? !privacy.equals(that.privacy) : that.privacy != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (targeting != null ? !targeting.equals(that.targeting) : that.targeting != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (scheduledPublishTime != null ? !scheduledPublishTime.equals(that.scheduledPublishTime) : that.scheduledPublishTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (caption != null ? caption.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (privacy != null ? privacy.hashCode() : 0);
        result = 31 * result + (objectAttachment != null ? objectAttachment.hashCode() : 0);
        result = 31 * result + (targeting != null ? targeting.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (scheduledPublishTime != null ? scheduledPublishTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostUpdate{" +
                "message='" + message + '\'' +
                ", link=" + link +
                ", picture=" + picture +
                ", name='" + name + '\'' +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", actions=" + actions +
                ", place='" + place + '\'' +
                ", tags='" + tags + '\'' +
                ", privacy=" + privacy +
                ", objectAttachment='" + objectAttachment + '\'' +
                ", targeting=" + targeting +
                ", published=" + published +
                ", scheduledPublishTime=" + scheduledPublishTime +
                '}';
    }

    /**
     * @since Facebook4J 2.0.0
     */
    public static class PostAction implements Post.Action, java.io.Serializable {
        private static final long serialVersionUID = 2016068387645669580L;

        private String name;
        private String link;

        public PostAction(String name, String link) {
            this.name = name;
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }
    }
}
