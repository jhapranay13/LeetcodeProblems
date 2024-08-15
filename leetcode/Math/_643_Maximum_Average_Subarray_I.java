package leetcode.Math;

/**
 *
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -104 <= nums[i] <= 10^4
 *
 */

public class _643_Maximum_Average_Subarray_I {
    public double findMaxAverage(int[] nums, int k) {
        double ans = Integer.MIN_VALUE;
        double sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (i >= k - 1) {
                ans = Math.max(sum, ans);
                sum -= nums[i - k + 1];
            }
        }
        return ans / k;
    }
}
