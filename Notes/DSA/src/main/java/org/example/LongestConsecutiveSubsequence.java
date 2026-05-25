package org.example;

class Solution2 {
    public int longestCommonSubsequence(String text1, String text2) {
        int r = text1.length();
        int c = text2.length();

        int[][] result = new int[r + 1][c + 1];

        for (int i = 1; i < r + 1; i++) {
            for (int j = 1; j < c + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    result[i][j] = result[i - 1][j - 1] + 1;
                if (text1.charAt(i - 1) != text2.charAt(j - 1))
                    result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
            }
        }

        return result[r][c];
    }
}

public class LongestConsecutiveSubsequence {
    
}
