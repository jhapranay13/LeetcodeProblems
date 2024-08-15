package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an integer array nums and an integer k.
 *
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 *
 * Return the maximum number of operations you can perform on the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * Example 2:
 *
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 *
 */

public class _1679_Max_Number_of_K_Sum_Pairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> numFreqMap = new HashMap<>();

        for (int num : nums) {
            numFreqMap.put(num, numFreqMap.getOrDefault(num, 0) + 1);
        }
        int ans = 0;

        for (int num : nums) {
            int diff = k - num;

            if (numFreqMap.containsKey(diff) && numFreqMap.get(num) > 0 && numFreqMap.get(diff) > 0) {

                if (num == diff && numFreqMap.get(num) < 2) {
                    continue;
                }
                numFreqMap.put(num, numFreqMap.getOrDefault(num, 0) - 1);
                numFreqMap.put(diff, numFreqMap.getOrDefault(diff, 0) - 1);
                ans++;
            }
        }
        return ans;
    }
}
