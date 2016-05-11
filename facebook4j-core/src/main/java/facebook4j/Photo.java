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

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface Photo extends FacebookResponse {
    String getId();
    Category getFrom();
    PagableList<Tag> getTags();
    String getName();
    URL getIcon();
    URL getPicture();
    URL getSource();
    Integer getHeight();
    Integer getWidth();
    List<Image> getImages();
    URL getLink();
    Place getPlace();
    Date getCreatedTime();
    Date getUpdatedTime();
    Integer getPosition();
    PagableList<Comment> getComments();
    PagableList<Like> getLikes();
    Category getAlbum();
    PagableList<Reaction> getReactions();

}
