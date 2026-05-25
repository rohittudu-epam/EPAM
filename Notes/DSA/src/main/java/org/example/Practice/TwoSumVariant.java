package org.example.Practice;

import java.util.HashMap;
import java.util.Map;

public class TwoSumVariant {
    public int[] twoSum(int[] arr, int target){
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++){
            int rem = target - arr[i];

            if (map.containsKey(rem)){
                return new int[]{map.get(rem), i};
            }
            map.put(arr[i], i);
        }

        return new int[]{-1, -1};
    }
}
