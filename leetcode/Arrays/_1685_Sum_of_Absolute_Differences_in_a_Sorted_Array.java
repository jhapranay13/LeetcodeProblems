package leetcode.Arrays;

/**
 *
 * You are given an integer array nums sorted in non-decreasing order.
 *
 * Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.
 *
 * In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,5]
 * Output: [4,3,5]
 * Explanation: Assuming the arrays are 0-indexed, then
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
 * Example 2:
 *
 * Input: nums = [1,4,6,8,10]
 * Output: [24,15,13,15,21]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= nums[i + 1] <= 10^4
 *
 */

public class _1685_Sum_of_Absolute_Differences_in_a_Sorted_Array {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int[] presum = new int[nums.length];
        presum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int left = 0;
            int right = 0;
            int leftCurr = 0;
            int rightCurr = 0;

            if (i > 0) {
                left = presum[i - 1];
                leftCurr = i  * nums[i];
            }

            if (right < nums.length - 1) {
                right = presum[nums.length - 1] - presum[i];
                rightCurr = ((nums.length - 1) - i) * nums[i];
            }
            ans[i] = Math.abs(left - leftCurr) + Math.abs(right - rightCurr);
        }
        return ans;
    }
}
