package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd indices.
 *
 * For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
 * Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).
 *
 * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,5,3]
 * Output: 7
 * Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
 * Example 2:
 *
 * Input: nums = [5,6,7,8]
 * Output: 8
 * Explanation: It is optimal to choose the subsequence [8] with alternating sum 8.
 * Example 3:
 *
 * Input: nums = [6,2,1,2,4,5]
 * Output: 10
 * Explanation: It is optimal to choose the subsequence [6,1,5] with alternating sum (6 + 5) - 1 = 10.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 */

public class _1911_Maximum_Alternating_Subsequence_Sum {
    // Memoization Working Solution
    public long maxAlternatingSum(int[] nums) {
        Map<String, Long> memo = new HashMap<>();

        return recur(nums, 0, 0, memo);
    }

    private long recur(int[] nums, int index, int reIndex, Map<String, Long> memo) {

        if (index == nums.length) {
            return 0L;
        }
        // Since there are only to states weather it is even or not
        String key = index + "|" + (reIndex % 2 == 0);

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long include = 0;
        long exclude = 0;

        if (reIndex % 2 == 0) {
            include = nums[index] + recur(nums, index + 1, reIndex + 1, memo);
        } else {
            include = recur(nums, index + 1, reIndex + 1, memo) - nums[index];
        }
        exclude = recur(nums, index + 1, reIndex, memo);
        memo.put(key, Math.max(include, exclude));
        return  Math.max(include, exclude);
    }
    //=============================================================================================
    //TLE
    public long maxAlternatingSum1(int[] nums) {
        Map<String, Long> memo = new HashMap<>();

        return recur1(nums, 0, 0, memo);
    }

    private long recur1(int[] nums, int index, int reIndex, Map<String, Long> memo) {

        if (index == nums.length) {
            return 0L;
        }
        String key = index + "|" + reIndex;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long include = 0;
        long exclude = 0;

        if (reIndex % 2 == 0) {
            include = nums[index] + recur1(nums, index + 1, reIndex + 1, memo);
        } else {
            include = recur1(nums, index + 1, reIndex + 1, memo) - nums[index];
        }
        exclude = recur1(nums, index + 1, reIndex, memo);
        memo.put(key, Math.max(include, exclude));
        return  Math.max(include, exclude);
    }
    //=============================================================================================
    // MLE
    public long maxAlternatingSum2(int[] nums) {
        long[][] memo = new long[nums.length][nums.length];
        return recur(nums, 0, 0, memo);
    }

    private long recur(int[] nums, int index, int reIndex, long[][] memo) {

        if (index == nums.length) {
            return 0;
        }

        if (memo[index][reIndex] > 0) {
            return memo[index][reIndex];
        }
        long include = 0;
        long exclude = 0;

        if (reIndex % 2 == 0) {
            include = nums[index] + recur(nums, index + 1, reIndex + 1, memo);
        } else {
            include = recur(nums, index + 1, reIndex + 1, memo) - nums[index];
        }
        exclude = recur(nums, index + 1, reIndex, memo);
        return memo[index][reIndex] = Math.max(include, exclude);
    }
}
