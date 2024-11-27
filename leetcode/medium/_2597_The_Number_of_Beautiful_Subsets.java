package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are given an array nums of positive integers and a positive integer k.
 *
 * A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
 *
 * Return the number of non-empty beautiful subsets of the array nums.
 *
 * A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,6], k = 2
 * Output: 4
 * Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
 * It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: 1
 * Explanation: The beautiful subset of the array nums is [1].
 * It can be proved that there is only 1 beautiful subset in the array [1].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 20
 * 1 <= nums[i], k <= 1000
 *
 */

public class _2597_The_Number_of_Beautiful_Subsets {
    public int beautifulSubsets(int[] nums, int k) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(nums, k, 0, 0, memo);
    }

    private int recur(int[] nums, int k, int mask, int index, Map<String, Integer> memo) {

        if (index == nums.length) {
            return mask > 0 ? 1 : 0;
        }
        String key = mask + "|" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int skip = recur(nums, k, mask, index + 1, memo);
        boolean isValid = true;

        for (int i = 0; i < index; i++) {

            if ((mask & (1 << i)) > 0) {

                if (Math.abs(nums[i] - nums[index]) == k) {
                    isValid = false;
                    break;
                }
            }
        }
        int take = 0;

        if (isValid) {
            int newMask = mask | 1 << index;
            take = recur(nums, k, newMask, index + 1, memo);
        }
        memo.put(key, skip + take);
        return skip + take;
    }
}
