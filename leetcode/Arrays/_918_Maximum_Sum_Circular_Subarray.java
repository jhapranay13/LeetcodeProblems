package leetcode.Arrays;

/**
 *
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 * Example 2:
 *
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 * Example 3:
 *
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 */

public class _918_Maximum_Sum_Circular_Subarray {
    // The whole idea is to find maxSum and find Min sum and total sum.
    // if total sum = min that means all values are -ve so return max.
    // else return max of maximum of max or totalSum - min
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int max = nums[0];
        int curr = nums[0];
        //Kadane
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            max = Math.max(max, curr);
        }

        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        int min = nums[0];
        curr = nums[0];
        // Kadane
        for (int i = 1; i < nums.length; i++) {
            curr = Math.min(nums[i], curr + nums[i]);
            min = Math.min(min, curr);
        }

        if (min == totalSum) {
            return max;
        }
        return Math.max(max, totalSum - min);
    }
}
