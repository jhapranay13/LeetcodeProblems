package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: nums = [1]
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 *
 */

public class _229_Majority_Element_II {
    /*
       There can be at most one majority element which is more than ⌊n/2⌋ times.
       There can be at most two majority elements which are more than ⌊n/3⌋ times.
       There can be at most three majority elements which are more than ⌊n/4⌋ times.
   */
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0;
        int count2 = 0;
        Integer element1 = null;
        Integer element2 = null;
        List<Integer> ans = new ArrayList<>();

        for (int num : nums) {

            if (element1 != null && element1 == num) {
                count1++;
            } else if (element2 != null && element2 == num) {
                count2++;
            } else if (count1 == 0) {
                element1 = num;
                count1++;
            } else if (count2 == 0) {
                element2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        //Second pass checking and verifying the count for remaining candidates
        count1 = 0;
        count2 = 0;

        for (int num : nums) {

            if (element1 != null && element1 == num) {
                count1++;
            }

            if (element2 != null && element2 == num) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            ans.add(element1);
        }

        if (count2 > nums.length / 3) {
            ans.add(element2);
        }
        return ans;
    }
}
