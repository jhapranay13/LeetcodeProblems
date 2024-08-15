package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 *
 * A valid path is defined as follows:
 *
 * Choose array nums1 or nums2 to traverse (from index-0).
 * Traverse the current array from left to right.
 * If you are reading any value that is present in nums1 and nums2 you are allowed to change your path to the other array. (Only one repeated value is considered in the valid path).
 * The score is defined as the sum of uniques values in a valid path.
 *
 * Return the maximum score you can obtain of all possible valid paths. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *                  [2,4,5,8,10]
 *                  --/___/
 *                  [4,6,8,9]
 *
 * Input: nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * Output: 30
 * Explanation: Valid paths:
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
 * The maximum is obtained with the path in green [2,4,6,8,10].
 * Example 2:
 *
 * Input: nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 * Output: 109
 * Explanation: Maximum sum is obtained with the path [1,3,5,100].
 * Example 3:
 *
 * Input: nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 * Output: 40
 * Explanation: There are no common elements between nums1 and nums2.
 * Maximum sum is obtained with the path [6,7,8,9,10].
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * 1 <= nums1[i], nums2[i] <= 107
 * nums1 and nums2 are strictly increasing.
 *
 */

public class _1537_GetTheMaximumScore {

    public int maxSum(int[] nums1, int[] nums2) {
        initMap(valIndexMap1, nums1);
        initMap(valIndexMap2, nums2);
        int length = Math.max(nums1.length, nums2.length);
        long[][] memo = new long[length][2];
        long max1 = recur(nums1, nums2, 0, 0, memo);
        long max2 = recur(nums1, nums2, 0, 1, memo);
        return (int)(Math.max(max1, max2) % 1000000007);
    }
    Map<Integer, Integer> valIndexMap1 = new HashMap<>();
    Map<Integer, Integer> valIndexMap2 = new HashMap<>();

    private long recur(int[] nums1, int[] nums2, int index, int swapped, long[][] memo) {

        if (swapped == 0 && index == nums1.length) {
            return 0;
        }

        if (swapped == 1 && index == nums2.length) {
            return 0;
        }

        if (memo[index][swapped] > 0) {
            return memo[index][swapped];
        }
        long noSwap = 0;
        long swap = 0;

        if (swapped == 0) {
            noSwap = nums1[index] + recur(nums1, nums2, index + 1, swapped, memo);

            if (valIndexMap2.containsKey(nums1[index])) {
                int tempIndex = valIndexMap2.get(nums1[index]);
                swap = nums2[tempIndex] + recur(nums1, nums2, tempIndex + 1, swapped ^ 1, memo);
            }
        } else {
            noSwap = nums2[index] + recur(nums1, nums2, index + 1, swapped, memo);

            if (valIndexMap1.containsKey(nums2[index])) {
                int tempIndex = valIndexMap1.get(nums2[index]);
                swap = nums1[tempIndex] + recur(nums1, nums2, tempIndex + 1, swapped ^ 1, memo);
            }
        }
        return memo[index][swapped] = Math.max(noSwap, swap);
    }

    private void initMap(Map<Integer, Integer> valIndexMap, int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            valIndexMap.put(nums[i], i);
        }
    }
}
