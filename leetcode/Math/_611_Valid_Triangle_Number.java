package leetcode.Math;

import java.util.Arrays;

/**
 *
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 *
 * Input: nums = [4,2,3,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 *
 */

public class _611_Valid_Triangle_Number {
    // Only those sides can form a triangle where a + b > c
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;

        for (int i = 0; i < nums.length - 2; i++) {

            for (int j = i + 1; j < nums.length - 1; j++) {
                int k = binarySearchJustLessThan(nums, j, nums.length - 1, nums[i] + nums[j]);
                ans += k - j;
            }
        }
        return ans;
    }

    private int binarySearchJustLessThan(int[] nums, int lo, int hi, int target) {

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (hi - lo == 1) {
                if (nums[hi] < target) {
                    return hi;
                }
                return lo;
            }

            if (nums[pivot] < target) {
                lo = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        return lo;
    }
}
