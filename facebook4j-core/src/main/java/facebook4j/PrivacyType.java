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

/**
 * An enum represents the privacy settings.
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public enum PrivacyType {
    EVERYONE,
    ALL_FRIENDS,
    NETWORKS_FRIENDS,
    FRIENDS_OF_FRIENDS,
    SOME_FRIENDS,
    NO_FRIENDS,
    SELF,
    CUSTOM,
    ;
    
    public static PrivacyType getInstance(String privacyTypeString) {
        if (privacyTypeString == null) {
            return null;
        }
        for (PrivacyType eventPrivacyType : PrivacyType.values()) {
            if (eventPrivacyType.toString().equals(privacyTypeString)) {
                return eventPrivacyType;
            }
        }
        return null;
    }
}
