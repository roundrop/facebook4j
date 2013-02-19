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
 * An enum represents the privacy setting of a group
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @see <a href="https://developers.facebook.com/docs/reference/api/group/">Group - Facebook Developers</a>
 */
public enum GroupPrivacyType {
    OPEN,
    SECRET,
    CLOSED,
    ;
    
    public static GroupPrivacyType getInstance(String groupPrivacyTypeString) {
        if (groupPrivacyTypeString == null) {
            return null;
        }
        for (GroupPrivacyType groupPrivacyType : GroupPrivacyType.values()) {
            if (groupPrivacyType.toString().equals(groupPrivacyTypeString)) {
                return groupPrivacyType;
            }
        }
        return null;
    }
}
