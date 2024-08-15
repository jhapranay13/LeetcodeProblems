package leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.
 *
 * The value of |x| is defined as:
 *
 * x if x >= 0.
 * -x if x < 0.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,1], k = 1
 * Output: 4
 * Explanation: The pairs with an absolute difference of 1 are:
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * Example 2:
 *
 * Input: nums = [1,3], k = 3
 * Output: 0
 * Explanation: There are no pairs with an absolute difference of 3.
 * Example 3:
 *
 * Input: nums = [3,2,1,5,4], k = 2
 * Output: 3
 * Explanation: The pairs with an absolute difference of 2 are:
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 *
 */

public class _2006_Count_Number_of_Pairs_With_Absolute_Difference_K {
    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Set<Integer>> freq = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = freq.getOrDefault(nums[i], new HashSet<>());
            set.add(i);
            freq.put(nums[i], set);
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (freq.containsKey(num + k)) {
                Set<Integer> indexes = freq.get(num + k);
                ans += indexes.size();
            }

            if (freq.containsKey(num - k)) {
                Set<Integer> indexes = freq.get(num - k);
                ans += indexes.size();
            }
            Set<Integer> indexes = freq.get(num);
            indexes.remove(i);
        }
        return ans;
    }
    //=============================================================================================
    // Another easier approach
    public int countKDifference1(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Set<Integer>> freq = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (freq.containsKey(num + k)) {
                Set<Integer> indexes = freq.get(num + k);
                ans += indexes.size();
            }

            if (freq.containsKey(num - k)) {
                Set<Integer> indexes = freq.get(num - k);
                ans += indexes.size();
            }
            Set<Integer> set = freq.getOrDefault(nums[i], new HashSet<>());
            set.add(i);
            freq.put(nums[i], set);
        }
        return ans;
    }
}
