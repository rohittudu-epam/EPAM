package org.example;

public class LC_1545 {
    public static String invertAndReverse(String s) {
        int n = s.length();
        char[] result = new char[n];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(n - 1 - i);  // reverse
            result[i] = (char) (c ^ 1);    // invert
        }

        return new String(result);
    }

    public char findKthBit(int n, int k) {
        StringBuilder result = new StringBuilder();
        result.append("0");

        for (int i = 0; i < n; i++){
            result.append("1" + invertAndReverse(result.toString()));
        }

        return result.charAt(k);
    }
}
