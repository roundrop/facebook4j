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
public interface Album {
    String getId();
    IdNameEntity getFrom();
    String getName();
    String getDescription();
    String getLocation();
    URL getLink();
    String getCoverPhoto();
    String getPrivacy();
    Integer getCount();
    String getType();
    Date getCreatedTime();
    Date getUpdatedTime();
    Boolean canUpload();
    
    List<Like> getLikes();
    
//    public static enum Type { NORMAL, MOBILE, PROFILE, WALL, FRIENDS_WALLS, UNKNOWN }
//    public static enum Privacy { EVERYONE, FRIENDS_OF_FRIENDS, FRIENDS, CUSTOM }     
}
