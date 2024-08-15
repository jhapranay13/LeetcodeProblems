package leetcode.easy;

import java.util.Arrays;

/**
 *
 * Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 6
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: 24
 * Example 3:
 *
 * Input: nums = [-1,-2,-3]
 * Output: -6
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 *
 */

public class _628_Maximum_Product_of_Three_Numbers {
    public int maximumProduct(int[] nums) {
        int start = nums.length - 3;
        Arrays.sort(nums);
        int ans = Integer.MIN_VALUE;

        while(start < nums.length) {
            int product = nums[start++];

            for (int i = start; i < start + 2; i++) {
                int index = i % nums.length;
                product *= nums[index];
            }
            ans = Math.max(ans, product);
        }
        return ans;
    }
}
