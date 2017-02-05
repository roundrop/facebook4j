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
import java.util.Map;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public interface Page {
    String getId();
    String getName();
    URL getLink();
    String getCategory();
    Boolean isPublished();
    Boolean canPost();

    /**
     * Number of pages this page likes.
     * @return
     */
    Integer getLikes();
    Place.Location getLocation();
    String getPhone();
    Integer getCheckins();
    URL getPicture();
    Cover getCover();
    String getWebsite();

    /**
     * Company overview about this page.
     * @return
     */
    String getCompanyOverview();
    Integer getTalkingAboutCount();
    String getAccessToken();
    Boolean isCommunityPage();
    Integer getWereHereCount();

    /**
     * Number of people who like this page.
     * @return
     */
    Integer getFanCount();
    Date getCreatedTime();
    String getAbout();
    String getUsername();
    String getMission();
    Map<String,String> getHours();

}
