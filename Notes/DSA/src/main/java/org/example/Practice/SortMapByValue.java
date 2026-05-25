package org.example.Practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SortMapByValue {
    public void sortMap(Map<String, Integer> map){
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(map.entrySet());

        sorted.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));

        System.out.println(sorted);
    }
}
