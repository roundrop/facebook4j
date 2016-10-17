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

import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface Comment extends FacebookResponse {
    String getId();
    Category getFrom();
    String getMessage();
    List<Tag> getMessageTags();
    Boolean canComment();
    Boolean canRemove();
    Boolean canHide();
    Boolean canLike();
    Date getCreatedTime();
    Integer getLikeCount();
    Integer getCommentCount();
    Boolean isUserLikes();
    Boolean isHidden();

    Attachment getAttachment();

    Comment getParent();

    PagableList<Comment> getComments();

    interface Attachment {
        String getDescription();
        AttachmentMedia getMedia();
        AttachmentTarget getTarget();
        String getTitle();
        String getType();
        String getUrl();

        interface AttachmentMedia {
            Image getImage();
        }

        interface AttachmentTarget {
            String getId();
            String getUrl();
        }
    }
}
