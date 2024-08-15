package leetcode.hard;

/**
 *
 * You are given two 0-indexed integer arrays nums and multipliers of size n and m respectively, where n >= m.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (0-indexed) you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Note that multipliers[0] corresponds to the first operation, multipliers[1] to the second operation, and so on.
 * Remove x from nums.
 * Return the maximum score after performing m operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * Example 2:
 *
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 300
 * m <= n <= 10^5
 * -1000 <= nums[i], multipliers[i] <= 1000
 *
 */

public class _1770_Maximum_Score_from_Performing_Multiplication_Operations {
    //=============================================================================================
    // Bottom up
    public int maximumScore(int[] nums, int[] multipliers) {
        // multipliers.length since only that many elements will be used
        int[][] memo = new int[multipliers.length + 1][multipliers.length + 1];

        for (int index = multipliers.length - 1; index >= 0; index--) {

            for (int start = index; start >= 0; start--) {
                int fromStart = nums[start] * multipliers[index] + memo[index + 1][start + 1];
                // Index - start will give us the index from hte end to consider for this
                int fromEnd = nums[nums.length - 1 - (index - start)] *  multipliers[index] +
                        memo[index + 1][start];
                memo[index][start] = Math.max(fromStart, fromEnd);
            }
        }
        return memo[0][0];
    }
    //=============================================================================================
    // Top Down
    public int maximumScore1(int[] nums, int[] multipliers) {
        Integer[][] memo = new Integer[multipliers.length][nums.length];
        return recur(nums, multipliers, 0, 0, memo);
    }

    private int recur(int[] nums, int[] multipliers, int index, int start, Integer[][] memo) {

        if (index == multipliers.length) {
            return 0;
        }

        if(memo[index][start] != null) {
            return memo[index][start];
        }
        int fromStart = nums[start] * multipliers[index] +
                recur(nums, multipliers, index + 1, start + 1, memo);
        // Index - start will give us the index from hte end to consider for this
        int fromEnd = nums[nums.length - 1 - (index - start)] *  multipliers[index] +
                recur(nums, multipliers, index + 1, start, memo);
        return memo[index][start] = Math.max(fromStart, fromEnd);
    }
    //=============================================================================================
    // MLE
    public int maximumScore3(int[] nums, int[] multipliers) {
        int[][][] memo = new int[multipliers.length][nums.length][nums.length];
        return recur(nums, multipliers, 0, 0, nums.length - 1, memo);
    }

    private int recur(int[] nums, int[] multipliers, int index, int start, int end, int[][][] memo) {

        if (index == multipliers.length) {
            return 0;
        }

        if (start == end) {
            return nums[start] * multipliers[index];
        }

        if(memo[index][start][end] > 0) {
            return memo[index][start][end];
        }
        int fromStart = nums[start] * multipliers[index] +
                recur(nums, multipliers, index + 1, start + 1, end, memo);
        int fromEnd = nums[end] *  multipliers[index] +
                recur(nums, multipliers, index + 1, start, end - 1, memo);
        return memo[index][start][end] = Math.max(fromStart, fromEnd);
    }
}
