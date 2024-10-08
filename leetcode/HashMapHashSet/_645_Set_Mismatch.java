package leetcode.HashMapHashSet;

/**
 *
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 *
 */

public class _645_Set_Mismatch {
    public int[] findErrorNums(int[] nums) {
        int duplicate = -1;
        int missing = -1;
        // Can also be done using HashMap

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                duplicate = index + 1;
            } else {
                nums[index] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) {
                missing = i + 1;
                break;
            }
        }
        return new int[] {duplicate, missing};
    }
}
