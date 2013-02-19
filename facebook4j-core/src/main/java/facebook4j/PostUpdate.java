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
    private static final long serialVersionUID = 7484605694572912923L;

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
    
    public PostUpdate(String message) {
        this.message = message;
    }

    public PostUpdate(URL link) {
        this.link = link;
    }
    
    // for post group feed
    public PostUpdate(String message, URL link, URL picture, String name,
            String caption, String description, List<Action> actions) {
        this.message = message;
        this.link = link;
        this.picture = picture;
        this.name = name;
        this.caption = caption;
        this.description = description;
        this.actions = actions;
    }

    // for publish stream
    public PostUpdate(String message, URL link, URL picture, String name,
            String caption, String description, List<Action> actions,
            String place, String tags, PrivacyBean privacy, String objectAttachment) {
        this.message = message;
        this.link = link;
        this.picture = picture;
        this.name = name;
        this.caption = caption;
        this.description = description;
        this.actions = actions;
        this.place = place;
        this.tags = tags;
        this.privacy = privacy;
        this.objectAttachment = objectAttachment;
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
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actions == null) ? 0 : actions.hashCode());
        result = prime * result + ((caption == null) ? 0 : caption.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime
                * result
                + ((objectAttachment == null) ? 0 : objectAttachment.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((place == null) ? 0 : place.hashCode());
        result = prime * result + ((privacy == null) ? 0 : privacy.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
        PostUpdate other = (PostUpdate) obj;
        if (actions == null) {
            if (other.actions != null)
                return false;
        } else if (!actions.equals(other.actions))
            return false;
        if (caption == null) {
            if (other.caption != null)
                return false;
        } else if (!caption.equals(other.caption))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (link == null) {
            if (other.link != null)
                return false;
        } else if (!link.equals(other.link))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (objectAttachment == null) {
            if (other.objectAttachment != null)
                return false;
        } else if (!objectAttachment.equals(other.objectAttachment))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (place == null) {
            if (other.place != null)
                return false;
        } else if (!place.equals(other.place))
            return false;
        if (privacy == null) {
            if (other.privacy != null)
                return false;
        } else if (!privacy.equals(other.privacy))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PostUpdate [message=" + message + ", link=" + link
                + ", picture=" + picture + ", name=" + name + ", caption="
                + caption + ", description=" + description + ", actions="
                + actions + ", place=" + place + ", tags=" + tags
                + ", privacy=" + privacy + ", objectAttachment="
                + objectAttachment + "]";
    }

}
