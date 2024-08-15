package leetcode.DP;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 *
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 *
 * Return the maximum score you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
 * Example 2:
 *
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
 * Example 3:
 *
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length, k <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 */

public class _1696_Jump_Game_VI {
    // Similar to Max SLiding window. Can also be done using normal DP
    public int maxResult(int[] nums, int k) {
        int[] score = new int[nums.length];
        score[0] = nums[0];
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);

        for (int i = 1; i < nums.length; i++) {

            while (!q.isEmpty() && q.peekFirst() < i - k) {
                q.pollFirst();
            }
            score[i] = score[q.peekFirst()] + nums[i];

            while (!q.isEmpty() && score[q.peekLast()] <= score[i]) {
                q.pollLast();
            }
            q.offerLast(i);
        }
        return score[nums.length - 1];
    }
    //=============================================================================================
    // Bottom up Approach TLE
    public int maxResult1(int[] nums, int k) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);

        for (int index = nums.length - 1; index >= 0; index--) {
            int val = Integer.MIN_VALUE;

            for (int i = index + 1; i <= index + k && i < nums.length; i++) {
                val = Math.max(val, memo[i]);
            }
            memo[index] = val != Integer.MIN_VALUE ? val + nums[index] : nums[index];
        }
        return memo[0];
    }
    //=============================================================================================
    // Top Down Approach TLE
    public int maxResult2(int[] nums, int k) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);
        return recur(nums, k, 0, memo);
    }

    private int recur(int[] nums, int k, int index, int[] memo) {

        if (index >= nums.length) {
            return 0;
        }

        if (memo[index] != Integer.MIN_VALUE) {
            return memo[index];
        }
        int val = Integer.MIN_VALUE;

        for (int i = index + 1; i <= index + k && i < nums.length; i++) {
            val = Math.max(val, recur(nums, k, i, memo));
        }
        return memo[index] = val != Integer.MIN_VALUE ? val + nums[index] : nums[index];
    }
}
