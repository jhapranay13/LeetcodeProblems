package leetcode.Math;

/**
 *
 * You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).
 *
 * The triangular sum of nums is the value of the only element present in nums after the following process terminates:
 *
 * Let nums comprise of n elements. If n == 1, end the process. Otherwise, create a new 0-indexed integer array newNums of length n - 1.
 * For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10, where % denotes modulo operator.
 * Replace the array nums with newNums.
 * Repeat the entire process starting from step 1.
 * Return the triangular sum of nums.
 *
 *        1       2       3       4       5
 *          \   /   \   /  \    /   \    /
 *            3       5      7         9
 *             \    /  \    /  \      /
 *                8       2       6
 *                  \   /   \    /
 *                    0       8
 *                     \    /
 *                        8
 *
 * Example 1:
 *
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 8
 * Explanation:
 * The above diagram depicts the process from which we obtain the triangular sum of the array.
 * Example 2:
 *
 * Input: nums = [5]
 * Output: 5
 * Explanation:
 * Since there is only one element in nums, the triangular sum is the value of that element itself.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 9
 *
 */

public class _2221_Find_Triangular_Sum_of_an_Array {

    public int triangularSum(int[] nums) {
        int[] process = nums;
        int ans = -1;

        if (nums.length == 1) {
            ans = nums[0];
        }
        //As per example it takes nums.length - 1 steps
        for (int i = 0; i <= nums.length - 1; i++) {
            int[] next = new int[process.length - 1];

            for (int j = 1; j < process.length; j++) {
                next[j - 1] = (process[j] + process[j - 1]) % 10;
            }

            if (next.length == 1) {
                ans = next[0];
            }
            process = next;
        }
        return ans;
    }
}
