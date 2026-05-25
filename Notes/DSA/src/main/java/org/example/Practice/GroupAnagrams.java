package org.example.Practice;

import java.util.*;

import static java.util.Collections.sort;

public class GroupAnagrams {
    public List<String> groupAnagrams(List<String> strs){
         Map<String, List<String>> groups = new HashMap<>();

         for (String s: strs){
             char[] charArray = s.toCharArray();
             Arrays.sort(charArray);

             if (!groups.containsKey(charArray)){
                 groups.put(Arrays.toString(charArray), new ArrayList<>());
             }
         }

         return new ArrayList<String>();
    }
}
