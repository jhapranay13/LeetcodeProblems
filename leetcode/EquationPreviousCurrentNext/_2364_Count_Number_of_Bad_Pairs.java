package leetcode.EquationPreviousCurrentNext;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].
 *
 * Return the total number of bad pairs in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,1,3,3]
 * Output: 5
 * Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
 * The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
 * The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
 * The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
 * The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
 * There are a total of 5 bad pairs, so we return 5.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 0
 * Explanation: There are no bad pairs.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 */

public class _2364_Count_Number_of_Bad_Pairs {
    public long countBadPairs(int[] nums) {
        // Solving the equation  j - i != nums[j] - nums[i] to
        // j - nums[j] != i - nums[i]
        // So this can be placed in hashmap and check.
        Map<Integer, Integer> diffFreqMap = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int diff = i - nums[i];

            if (diffFreqMap.containsKey(diff)) {
                ans += (i - diffFreqMap.get(diff));
            } else {
                ans += i;
            }
            diffFreqMap.put(diff, diffFreqMap.getOrDefault(diff, 0) + 1);
        }
        return ans;
    }

    //=============================================================================================
    // Version 2
    class Solution {
        public long countBadPairs(int[] nums) {
            // Solving the equation  j - i != nums[j] - nums[i] to
            // j - nums[j] != i - nums[i]
            // So this can be placed in hashmap and check.
            // idea is to group pair by difference and then subtract pair formed bu them
            // with total pairs to get the bad pairs. as i - nums[i] is equal
            // then they are good pairs
            Map<Long, Long> diffFreqMap = new HashMap<>();
            long ans = 0;
            long n = nums.length;

            for (int i = 0; i < nums.length; i++) {
                long diff = i - nums[i];
                diffFreqMap.put(diff, diffFreqMap.getOrDefault(diff, 0L) + 1L);
            }
            long totalPairs = n * (n - 1) / 2; // since the pair will be n - 1 if total is n.

            for (Long sameDiffCount : diffFreqMap.values()) {
                totalPairs -= sameDiffCount * (sameDiffCount - 1L) / 2;
            }
            ans = totalPairs;
            return ans;
        }
    }
}
