package org.example.Practice;

import java.util.HashSet;
import java.util.Set;

public class DistinctElements {
    public int countDistinct(int[] arr){
        Set<Integer> set = new HashSet<>();

        for (int i: arr){
            set.add(i);
        }

        return set.size();
    }
}
