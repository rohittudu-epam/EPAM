package org.example;

import java.util.HashMap;
import java.util.Map;

class MostFrequent {
    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        int result = nums[0];

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                int newCount = countMap.merge(target, 1, Integer::sum);
                if (newCount > maxCount) {
                    maxCount = newCount;
                    result = target;
                }
            }
        }
        return result;
    }
}

