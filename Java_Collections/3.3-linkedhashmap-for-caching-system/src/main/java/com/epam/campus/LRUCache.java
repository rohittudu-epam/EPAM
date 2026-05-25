package com.epam.campus;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRUCache (Least Recently Used Cache) is a fixed-size cache implementation
 * that automatically evicts the least recently used entry when the maximum
 * capacity is exceeded.
 *
 * <p>This implementation uses {@link LinkedHashMap} with access-order mode
 * to track the order of element access. When a new entry is added and the
 * cache is full, the least recently accessed entry is automatically removed.
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V>{
    private final int maxCapacity;

    /**
     * Constructs an LRUCache with the specified maximum capacity.
     *
     * @param capacity the maximum number of entries this cache can hold
     *                 before evicting the least recently used entry
     */
    public LRUCache(int capacity) {
        // Initialize LinkedHashMap with:
        // - capacity: initial table size
        // - 0.75f: load factor for hash table resizing
        // - true: access-order (tracks element access, not insertion order)
        super(capacity, 0.75f, true);
        this.maxCapacity = capacity;
    }

    /**
     * Removes the eldest (least recently used) entry when the cache
     * exceeds its maximum capacity.
     *
     * <p>This method is called automatically by LinkedHashMap after each
     * {@code put()} operation to determine if an entry should be evicted.
     *
     * @param eldest the least recently used entry
     * @return {@code true} if the eldest entry should be removed,
     *         {@code false} otherwise
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }
}
