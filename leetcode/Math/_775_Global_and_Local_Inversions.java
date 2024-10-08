package leetcode.Math;

/**
 *
 * You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].
 *
 * The number of global inversions is the number of the different pairs (i, j) where:
 *
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * The number of local inversions is the number of indices i where:
 *
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * Return true if the number of global inversions is equal to the number of local inversions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion and 1 local inversion.
 * Example 2:
 *
 * Input: nums = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions and 1 local inversion.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] < n
 * All the integers of nums are unique.
 * nums is a permutation of all the numbers in the range [0, n - 1].
 *
 */

public class _775_Global_and_Local_Inversions {
    //According to the condition all local inversion is global inversion
    //So will check for any inversion which is not local meaning j - i >= 2 . if we have such inversion
    //then result is false
    public boolean isIdealPermutation(int[] nums) {
        int min =nums.length;

        for (int i = nums.length - 1; i >= 2; i--) {
            min = Math.min(min, nums[i]);

            if (nums[i - 2] > min) {
                return false;
            }
        }
        return true;
    }
}
