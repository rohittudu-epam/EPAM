package org.example;

import java.util.Arrays;

public class HouseRobber {
//    Memoization
    int robber(int[] nums, int idx, int[] dp){
        if (idx == 0) return dp[idx] = nums[idx];

        if (dp[idx] != -1) return dp[idx];

        int current = nums[idx];
        if (idx > 1){
            current += robber(nums, idx - 2, dp);
        }
        int prev = robber(nums, idx - 1, dp);

        return dp[idx] = Math.max(current, prev);
    }


//      Recursion
//    int robber(int[] nums, int idx){
//        if (idx == 0) return nums[idx];
//
//        int current = nums[idx];
//        if (idx > 1){
//            current += robber(nums, idx - 2);
//        }
//        int prev = robber(nums, idx - 1);
//
//        return Math.max(current, prev);
//    }

//    public int rob(int[] nums) {
//        // System.out.println(nums);
//        // System.out.println(nums.length);
//        int n = nums.length - 1;
//        System.out.println(n);
//        return robber(nums, n);
//        // return 0;
//    }

//    void main(){
//        int[] arr = {1, 2, 3, 1};
//        int n = arr.length;
//        int[] dp = new int[n];
//        Arrays.fill(dp, -1);
////        System.out.println(robber(arr, arr.length - 1));
//        System.out.println(robber(arr, n - 1, dp));
//    }

    void main(){
        Integer[] arr = {0, 1, 2, 3, 4, 5};
        Integer[] woutFirst = Arrays.copyOfRange(arr, 1, arr.length);
        Integer[] woutLast = Arrays.copyOfRange(arr, 0, arr.length - 1);

        for (int x: woutFirst) System.out.print(x);
        System.out.println();
        for (int x: woutLast) System.out.print(x);
    }
}
