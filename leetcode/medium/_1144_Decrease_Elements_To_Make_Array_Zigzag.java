package leetcode.medium;

/**
 *
 * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
 *
 * An array A is a zigzag array if either:
 *
 * Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * Return the minimum number of moves to transform the given array nums into a zigzag array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: We can decrease 2 to 0 or 3 to 1.
 * Example 2:
 *
 * Input: nums = [9,6,1,6,2]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 *
 */

public class _1144_Decrease_Elements_To_Make_Array_Zigzag {
    public int movesToMakeZigzag(int[] nums) {
        int costEven = 0;
        // For scenario a > b < c > d
        for (int i = 0; i < nums.length; i += 2) {
            int min = Integer.MAX_VALUE;

            if (i > 0) {
                min = Math.min(min, nums[i - 1]);
            }

            if (i < nums.length - 1) {
                min = Math.min(min, nums[i + 1]);
            }

            if (min <= nums[i]) {
                costEven += nums[i] - min + 1;
            }
        }
        int costOdd = 0;
        // For scenario a < b > c < d
        for (int i = 1; i < nums.length; i += 2) {
            int min = Integer.MAX_VALUE;

            if (i > 0) {
                min = Math.min(min, nums[i - 1]);
            }

            if (i < nums.length - 1) {
                min = Math.min(min, nums[i + 1]);
            }

            if (min <= nums[i]) {
                costOdd += nums[i] - min + 1;
            }
        }
        return Math.min(costEven, costOdd);
    }
}
