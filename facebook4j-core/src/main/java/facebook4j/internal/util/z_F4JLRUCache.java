package facebook4j.internal.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class z_F4JLRUCache<K, V> {

    private final Map<K, V> cacheMap;

    public z_F4JLRUCache(final int maxSize) {

        // true = use access order instead of insertion order.
        this.cacheMap = new LinkedHashMap<K, V>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxSize;
            }
        };
    }

    public synchronized void put(K key, V elem) {
        cacheMap.put(key, elem);
    }

    public synchronized V get(K key) {
        return cacheMap.get(key);
    }

    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }
}
