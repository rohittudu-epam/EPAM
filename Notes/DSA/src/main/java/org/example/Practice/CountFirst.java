package org.example.Practice;

import java.util.Map;
import java.util.HashMap;

public class CountFirst {

    public int countFrequencies(int[] nums){
        Map<Integer, Integer> counts = new HashMap<>();

        for (int i: nums){
            counts.put(i, counts.getOrDefault(i, 0) + 1);
        }

        for (int i: counts.keySet()){
            if (counts.get(i) == 1){
                return i;
            }
        }

        return -1;
    }
}
