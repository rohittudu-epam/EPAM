package com.epam.campus;

/**
 * Demonstrates the usage of the LRUCache implementation.
 *
 * <p>This application shows how the LRUCache evicts the least recently used
 * entry when the cache reaches its maximum capacity. The cache is access-order,
 * meaning that accessing an entry updates its position in the eviction order.
 */
public class App {
    /**
     * Main method demonstrating LRUCache behavior.
     *
     * <p>Creates a cache with capacity 3, adds entries, accesses an entry
     * to update its recency, then adds another entry which triggers eviction
     * of the least recently used entry.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an LRU cache with a maximum capacity of 3 entries
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        // Add three entries to fill the cache
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        // Display cache contents: {1=One, 2=Two, 3=Three}
        System.out.println(cache);

        // Access key 2 to mark it as most recently used
        cache.get(2);

        // Add another entry, which triggers eviction of key 1
        // (the least recently used entry)
        cache.put(4, "Four");

        // Display updated cache contents: {2=Two, 3=Three, 4=Four}
        System.out.println(cache);
    }
}
