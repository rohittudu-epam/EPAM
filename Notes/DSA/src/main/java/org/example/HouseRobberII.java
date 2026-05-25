package org.example;

import java.util.Arrays;

class Solution {
    public int robber(int[] nums, int idx, int[] dp) {
        if (idx < 0) return 0;
        if (idx == 0) return dp[idx] = nums[0];

        if (dp[idx] != -1) return dp[idx];

        int current = nums[idx] + robber(nums, idx - 2, dp);
        int prev = robber(nums, idx - 1, dp);

        return dp[idx] = Math.max(current, prev);
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int[] withoutLast = Arrays.copyOfRange(nums, 0, n - 1);
        int[] withoutFirst = Arrays.copyOfRange(nums, 1, n);

        int[] dp1 = new int[n - 1];
        int[] dp2 = new int[n - 1];
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);

        return Math.max(
                robber(withoutLast, n - 2, dp1),
                robber(withoutFirst, n - 2, dp2)
        );
    }
}


public class HouseRobberII {

}
