package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [2]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 *
 *
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 */

public class _448_Find_All_Numbers_Disappeared_in_an_Array {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // Treating index as hash value and making it as negative so that
        // whichever is left positive can reverse hash to find value not present
        for (int num : nums) {
            int index = Math.abs(num) - 1;

            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
    //=============================================================================================
    public List<Integer> findDisappearedNumbers1(int[] nums) {

        for (int num : nums) {
            int index = Math.abs(num) - 1;

            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
