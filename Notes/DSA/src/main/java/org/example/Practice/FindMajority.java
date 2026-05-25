package org.example.Practice;

import java.util.HashMap;
import java.util.Map;

public class FindMajority {
    public int getMajority(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();

        for (int i: nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i: map.keySet()){
            if (map.get(i) > nums.length / 2) return i;
        }

        return -1;
    }

}
