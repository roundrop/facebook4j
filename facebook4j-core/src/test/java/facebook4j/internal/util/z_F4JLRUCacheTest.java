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
        assertThat(cache.get("key2"), is(notNullValue()));
        assertThat(cache.get("key3"), is(nullValue()));   //removed

        cache.put("key7", "value7");
        assertThat(cache.get("key2"), is(notNullValue()));
        assertThat(cache.get("key3"), is(nullValue()));
        assertThat(cache.get("key4"), is(nullValue()));   //removed

        cache.get("key5");
        cache.put("key6", "******");
        cache.put("key8", "value8");
        assertThat(cache.get("key1"), is(nullValue()));   //removed
        assertThat(cache.get("key2"), is(notNullValue()));
        assertThat(cache.get("key3"), is(nullValue()));
        assertThat(cache.get("key4"), is(nullValue()));
        assertThat(cache.get("key5"), is(notNullValue()));    //alive
    }
}
