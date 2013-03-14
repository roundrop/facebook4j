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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import facebook4j.Post.Action;
import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.org.json.JSONObject;

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
    private List<Post.Action> actions;
    
    private String place;
    private String tags;
    private PrivacyBean privacy;
    private String objectAttachment;

    private Targeting targeting;

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

    public List<Post.Action> getActions() {
        return actions;
    }

    public void setActions(List<Post.Action> actions) {
        this.actions = actions;
    }
    
    public PostUpdate actions(List<Post.Action> actions) {
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

    public PrivacyBean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyBean privacy) {
        this.privacy = privacy;
    }
    
    public PostUpdate privacy(PrivacyBean privacy) {
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

    public Targeting getTargeting() {
        return targeting;
    }

    public void setTargeting(Targeting targeting) {
        this.targeting = targeting;
    }

    public PostUpdate targeting(Targeting targeting) {
        setTargeting(targeting);
        return this;
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
            JSONObject json = new JSONObject(actions);
            params.add(new HttpParameter("actions", json.toString()));
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
                '}';
    }
}
