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
import java.util.Iterator;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface Insight extends FacebookResponse {
    String getId();
    String getName();
    String getPeriod();
    List<Insight.Value> getValues();
    String getTitle();
    String getDescription();
    
    interface Value {
        Value.Entry getValue();
        Date getEndTime();

        /**
         * @since Facebook4J 2.0.0
         */
        interface Entry {
            Long get();
            Long get(String key);
            Iterator<String> keys();
            int size();
        }
    }
}
