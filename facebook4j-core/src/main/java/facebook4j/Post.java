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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface Post extends FacebookResponse {
    String getId();
    Category getFrom();
    List<IdNameEntity> getTo();
    String getMessage();
    List<Tag> getMessageTags();
    URL getPicture();
    URL getFullPicture();
    URL getLink();
    String getName();
    String getCaption();
    String getDescription();
    URL getSource();
    List<Post.Property> getProperties();
    URL getIcon();
    List<Post.Action> getActions();
    Privacy getPrivacy();
    String getType();
    Integer getSharesCount();
    PagableList<Like> getLikes();
    Place getPlace();
    String getStatusType();
    String getStory();
    Map<String, Tag[]> getStoryTags();
    List<IdNameEntity> getWithTags();
    PagableList<Comment> getComments();
    List<Attachment> getAttachments();
    String getObjectId();
    String getParentId();
    URL getPermalinkUrl();
    Application getApplication();
    Date getCreatedTime();
    Date getUpdatedTime();
    Boolean isPublished();
    Boolean isHidden();
    Date getScheduledPublishTime();
    Targeting getTargeting();
    PagableList<Reaction> getReactions();

    interface Action {
        String getName();
        String getLink();
    }

    interface Property {
        String getName();
        String getText();
        String getHref();
    }

    interface Attachment {
        String getId();
        String getTitle();
        String getType();
        String getUrl();
        List<Attachment> getSubattachments();
    }

}
