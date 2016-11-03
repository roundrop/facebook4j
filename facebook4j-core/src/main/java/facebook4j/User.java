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
import java.util.Locale;


/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface User extends FacebookResponse {
    String getId();
    String getName();
    String getFirstName();
    String getMiddleName();
    String getLastName();
    String getGender();
    Locale getLocale();
    List<IdNameEntity> getLanguages();
    URL getLink();
    String getUsername();
    String getThirdPartyId();
    Boolean isInstalled();
    Double getTimezone();
    Date getUpdatedTime();
    Boolean isVerified();
    String getBio();
    String getBirthday();
    String getTokenForBusiness();
    Cover getCover();
    List<User.Education> getEducation();
    String getEmail();
    IdNameEntity getHometown();
    List<String> getInterestedIn();
    IdNameEntity getLocation();
    String getPolitical();
    List<IdNameEntity> getFavoriteAthletes();
    List<IdNameEntity> getFavoriteTeams();
    Picture getPicture();
    String getQuotes();
    String getRelationshipStatus();
    String getReligion();
    IdNameEntity getSignificantOther();
    User.VideoUploadLimits getVideoUploadLimits();
    URL getWebsite();
    List<User.Work> getWork();
    User.AgeRange getAgeRange();

    interface Education {
        IdNameEntity getYear();
        String getType();
        IdNameEntity getSchool();
        IdNameEntity getDegree();
        List<IdNameEntity> getConcentration();
        List<User.EducationClass> getClasses();
        List<IdNameEntity> getWith();
    }

    interface EducationClass {
        List<IdNameEntity> getWith();
        String getDescription();
    }

    interface VideoUploadLimits {
        long getLength();
        long getSize();
    }

    interface Work {
        IdNameEntity getEmployer();
        IdNameEntity getLocation();
        IdNameEntity getPosition();
        String getStartDate();
        String getEndDate();

    }

     interface AgeRange {
         // one value could be null (13-17 / 18-20 / 21 - null)
        Integer getMin();
        Integer getMax();
    }


    String BIRTHDAY_DATE_FORMAT = "MM/dd/yyyy";

}
