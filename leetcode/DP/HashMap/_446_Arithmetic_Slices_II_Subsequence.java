package leetcode.DP.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Given an integer array nums, return the number of all the arithmetic subsequences of nums.
 *
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
 * For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 *
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * The test cases are generated so that the answer fits in 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,6,8,10]
 * Output: 7
 * Explanation: All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 * Example 2:
 *
 * Input: nums = [7,7,7,7,7]
 * Output: 16
 * Explanation: Any subsequence of this array is arithmetic.
 *
 *
 * Constraints:
 *
 * 1  <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 *
 *
 */

public class _446_Arithmetic_Slices_II_Subsequence {

    public int numberOfArithmeticSlices(int[] nums) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(nums, -1L, 0, 0, memo);
    }

    private int recur(int[] nums, long diff, int size, int index, Map<String, Integer> memo) {

        if (index == nums.length) {
            return 0;
        }
        String key = diff + "|" + size + "|" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = 0;

        for (int i = index; i < nums.length; i++) {

            if (size == 0) {
                ans += recur(nums, 0, size + 1, i, memo);
            } else if (size == 1 && index != i) {
                ans += recur(nums, (long)nums[index] - (long)nums[i], size + 1, i, memo);
            } else if (size >= 2 && index != i) {
                long d = (long)nums[index] - (long)nums[i];

                if (d == diff) {
                    ans += 1 + recur(nums, diff, size + 1, i, memo);
                }
            }
        }
        memo.put(key, ans);
        return ans;
    }
    //=============================================================================================
    // Another approach
    public int numberOfArithmeticSlices1(int[] nums) {
        int ans = 0;
        Map<String, Integer> memo = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {
                ans += recur(nums, (long)nums[i] - (long)nums[j], j, memo);
            }
        }
        return ans;
    }

    private int recur(int[] nums, long diff, int index, Map<String, Integer> memo) {

        if (index == nums.length) {
            return 0;
        }
        String key = diff + "|" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = 0;

        for (int k = index + 1; k < nums.length; k++) {
            //System.out.println("diff " + (nums[index] - nums[k]));
            if ((long)nums[index] - (long)nums[k] == diff) {
                ans += 1 + recur(nums, diff, k, memo);
            }
        }
        memo.put(key, ans);
        return ans;
    }
}
