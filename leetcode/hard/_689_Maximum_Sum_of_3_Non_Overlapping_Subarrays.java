package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.
 *
 * Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,6,7,5,1], k = 2
 * Output: [0,3,5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 * Example 2:
 *
 * Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
 * Output: [0,2,4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] < 2^16
 * 1 <= k <= floor(nums.length / 3)
 *
 */

public class _689_Maximum_Sum_of_3_Non_Overlapping_Subarrays {
    // Top Down approach
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] presum = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            if (i == 0) {
                presum[i] = nums[i];
            } else {
                presum[i] = presum[i - 1] + nums[i];
            }
        }
        int[][] memo = new int[presum.length][3];
        recur(presum, k, 0, 0, memo);
        List<Integer> ans = new ArrayList<>();
        dfs(presum, memo, ans, 0, 0, 0, k);
        int[] holder = new int[3];

        for (int i = 0; i < ans.size(); i++) {
            holder[i] = ans.get(i);
        }
        return holder;
    }

    private void dfs(int[] presum, int[][] memo, List<Integer> ans, int row, int col, int num, int k) {

        if (num == 3) {
            return;
        }
        int sum = 0;

        if (row == 0) {
            sum = presum[k - 1];
        } else {

            if (row + k - 1 < memo.length) {
                sum = presum[row + k - 1] - presum[row - 1];
            }
        }
        int inc = sum;
        int exc = 0;

        if (row + k < memo.length && col + 1 < memo[0].length) {
            inc = sum + memo[row + k][col + 1];
        }

        if (row + 1 < memo.length) {
            exc = memo[row + 1][col];
        }

        if (inc >= exc) {
            ans.add(row);
            dfs(presum, memo, ans, row + k, col + 1, num + 1, k);
        } else {
            dfs(presum, memo, ans, row + 1, col, num, k);
        }
    }

    private int recur(int[] presum, int k, int index, int num, int[][] memo) {

        if (num == 3) {
            return 0;
        }

        if (index == 6) {
            System.out.println();
        }

        if (index >= presum.length || index + k - 1 >= presum.length) {
            return Integer.MIN_VALUE;
        }

        if (memo[index][num] > 0) {
            return memo[index][num];
        }
        int start = index;
        int end = index + k - 1;
        int sum = index == 0 ? presum[end] : presum[end] - presum[start - 1];
        int inc = sum + recur(presum, k, index + k, num + 1, memo);
        int exc = recur(presum, k, index + 1, num, memo);
        int returnVal = 0;

        if (inc >= exc) {
            returnVal = inc;
        } else {
            returnVal = exc;
        }
        return memo[index][num] = returnVal;
    }
    //=============================================================================================
    //Bottom up Approach
    public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
        int[] presum = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            if (i == 0) {
                presum[i] = nums[i];
            } else {
                presum[i] = presum[i - 1] + nums[i];
            }
        }
        int[][] memo = new int[presum.length + k][4];
        //recur(presum, k, 0, 0, memo);

        for (int index = nums.length - 1; index >= 0; index--) {

            for (int num = 3; num >= 0; num--) {

                if (num == 3) {
                    memo[index][num] = 0;
                    continue;
                }

                if (index >= presum.length || index + k - 1 >= presum.length) {
                    memo[index][num] = Integer.MIN_VALUE;
                    continue;
                }
                int start = index;
                int end = index + k - 1;
                int sum = index == 0 ? presum[end] : presum[end] - presum[start - 1];
                int inc = sum + memo[index + k][num + 1];
                int exc = memo[index + 1][num];
                int returnVal = 0;

                if (inc >= exc) {
                    returnVal = inc;
                } else {
                    returnVal = exc;
                }
                memo[index][num] = returnVal;
            }
        }
        List<Integer> ans = new ArrayList<>();
        dfs(presum, memo, ans, 0, 0, 0, k);
        int[] holder = new int[3];

        for (int i = 0; i < ans.size(); i++) {
            holder[i] = ans.get(i);
        }
        return holder;
    }

}
