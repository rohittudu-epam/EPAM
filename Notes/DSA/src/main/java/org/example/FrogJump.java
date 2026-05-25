package org.example;

import java.util.Arrays;

public class FrogJump {


//    int solver(int[] heights, int idx, int[] dp){
//        if (idx == 0) return 0;
//
//        if (dp[idx] != -1) return dp[idx];
//
//        int one = dp[idx - 1] + Math.abs(heights[idx] - heights[idx - 1]);
//
//        int two = Integer.MAX_VALUE;
//        if (idx > 1){
//            two = dp[idx - 2] + Math.abs(heights[idx] - heights[idx - 2]);
//        }
//
//        return dp[idx] = Math.min(one, two);
//    }


//  Tabulation Approach
//    int minCost(int[] height) {
//        int n = height.length;
//        int[] dp = new int[n];
//
//        dp[0] = 0; // base case
//
//        for (int i = 1; i < n; i++) {
//            int one = Math.abs(height[i] - height[i - 1]) + dp[i - 1];
//            int two = Integer.MAX_VALUE;
//
//            if (i > 1) {
//                two = Math.abs(height[i] - height[i - 2]) + dp[i - 2];
//            }
//
//            dp[i] = Math.min(one, two);
//        }
//
//        return dp[n - 1];
//    }

//    int jump(int[] height, int idx){
//        if (idx == 0) return 0;
//
//        int one = jump(height, idx - 1) + Math.abs(height[idx] - height[idx - 1]);
//
//        int two = Integer.MAX_VALUE;
//        if (idx > 1){
//            two = jump(height, idx - 2) + Math.abs(height[idx] - height[idx - 2]);
//        }
//
//        return Math.min(one, two);
//    }

//    memoization Solution
//    int jump(int[] height, int idx, int[] arr){
//        if (idx == 0) return 0;
//
//        if (arr[idx] != -1) return arr[idx];
//
//        int one = jump(height, idx - 1, arr) + Math.abs(height[idx] - height[idx - 1]);
//
//
//        int two = Integer.MAX_VALUE;
//        if (idx > 1){
//            two = jump(height, idx - 2, arr) + Math.abs(height[idx] - height[idx - 2]);
//        }
//
//        return arr[idx] = Math.min(one, two);
//    }


//    int minCost(int[] height) {
//        // code here
//        int[] dp = new int[height.length];
//        Arrays.fill(dp, -1);
//
//        return jump(height, height.length - 1, dp);
//    }
}
