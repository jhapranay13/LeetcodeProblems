package leetcode.medium;

/**
 *
 * Given an integer array nums, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.
 *
 * Test cases are generated such that partitioning exists.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * Example 2:
 *
 * Input: nums = [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^6
 * There is at least one valid answer for the given input.
 *
 */

public class _915_Partition_Array_into_Disjoint_Intervals {
    public int partitionDisjoint(int[] nums) {
        int[] leftToRightMax = new int[nums.length];
        leftToRightMax[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            leftToRightMax[i] = Math.max(leftToRightMax[i - 1], nums[i]);
        }
        int[] rightToLeftMin = new int[nums.length];
        rightToLeftMin[nums.length - 1] = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            rightToLeftMin[i] = Math.min(rightToLeftMin[i + 1], nums[i]);
        }
        int ans = -1;

        for (int i = 1; i < nums.length; i++) {

            if (leftToRightMax[i - 1] <= rightToLeftMin[i]) {
                return i;
            }
        }
        return ans;
    }
}
