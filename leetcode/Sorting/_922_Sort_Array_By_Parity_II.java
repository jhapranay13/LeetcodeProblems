package leetcode.Sorting;

/**
 *
 * Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
 *
 * Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
 *
 * Return any answer array that satisfies this condition.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 * Example 2:
 *
 * Input: nums = [2,3]
 * Output: [2,3]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 2 * 104
 * nums.length is even.
 * Half of the integers in nums are even.
 * 0 <= nums[i] <= 1000
 *
 *
 * Follow Up: Could you solve it in-place?
 *
 */

public class _922_Sort_Array_By_Parity_II {
    public int[] sortArrayByParityII(int[] nums) {
        int[] ans = new int[nums.length];
        int index = 0;

        for (int num : nums) {

            if (num % 2 == 0) {
                ans[index] = num;
                index += 2;
            }
        }
        index = 1;

        for (int num : nums) {

            if (num % 2 == 1) {
                ans[index] = num;
                index += 2;
            }
        }
        return ans;
    }
    //=============================================================================================
    // In Place
    public int[] sortArrayByParityII2(int[] nums) {
        int oddIndex = 1;

        for (int i = 0; i < nums.length; i += 2) {

            if (nums[i] % 2 == 1) {

                while (nums[oddIndex] % 2 == 1) {
                    oddIndex += 2;
                }
                int temp = nums[i];
                nums[i] = nums[oddIndex];
                nums[oddIndex] = temp;
            }
        }
        return nums;
    }
}
