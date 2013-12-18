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

package facebook4j.internal.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class z_F4JLRUCacheTest {

    @Test
    public void put_get() throws Exception {
        z_F4JLRUCache<String, String> cache = new z_F4JLRUCache<String, String>(5);
        cache.put("key1", "value1");
        assertThat(cache.get("key1"), is("value1"));

        cache.put("key2", null);
        assertThat(cache.get("key2"), is(nullValue()));

        cache.put(null, "");
        assertThat(cache.get(null), is(""));
    }

    @Test
    public void containsKey() throws Exception {
        z_F4JLRUCache<String, String> cache = new z_F4JLRUCache<String, String>(5);
        cache.put("key1", "value1");
        assertThat(cache.containsKey("key1"), is(true));
        assertThat(cache.containsKey("key2"), is(false));
        assertThat(cache.containsKey(null), is(false));
    }

    @Test
    public void LRU() throws Exception {
        z_F4JLRUCache<String, String> cache = new z_F4JLRUCache<String, String>(5);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        cache.put("key5", "value5");

        cache.get("key3");
        cache.get("key4");
        cache.get("key5");
        cache.get("key1");
        cache.get("key2");

        cache.put("key6", "value6");
        assertThat(cache.containsKey("key1"), is(true));
        assertThat(cache.containsKey("key2"), is(true));
        assertThat(cache.containsKey("key3"), is(false));   //removed
        assertThat(cache.containsKey("key4"), is(true));
        assertThat(cache.containsKey("key5"), is(true));
        assertThat(cache.containsKey("key6"), is(true));

        cache.put("key7", "value7");
        assertThat(cache.containsKey("key1"), is(true));
        assertThat(cache.containsKey("key2"), is(true));
        assertThat(cache.containsKey("key3"), is(false));
        assertThat(cache.containsKey("key4"), is(false));   //removed
        assertThat(cache.containsKey("key5"), is(true));
        assertThat(cache.containsKey("key6"), is(true));
        assertThat(cache.containsKey("key7"), is(true));

        cache.get("key5");
        cache.put("key6", "******");
        cache.put("key8", "value8");
        assertThat(cache.containsKey("key1"), is(false));   //removed
        assertThat(cache.containsKey("key2"), is(true));
        assertThat(cache.containsKey("key3"), is(false));
        assertThat(cache.containsKey("key4"), is(false));
        assertThat(cache.containsKey("key5"), is(true));    //alive
        assertThat(cache.containsKey("key6"), is(true));
        assertThat(cache.containsKey("key7"), is(true));
        assertThat(cache.containsKey("key8"), is(true));

    }
}
