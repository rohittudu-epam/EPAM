package org.example;

import java.util.HashSet;
import java.util.Set;

public class LC_1461 {

    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - k; i++){
            set.add(s.substring(i, i + k));
        }

        return set.size() == (1 << k);
    }
}
