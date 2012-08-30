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

}
