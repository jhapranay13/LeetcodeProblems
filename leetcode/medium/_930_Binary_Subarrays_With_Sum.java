package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * Example 2:
 *
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * nums[i] is either 0 or 1.
 * 0 <= goal <= nums.length
 *
 */

public class _930_Binary_Subarrays_With_Sum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> sumFreq = new HashMap<>();
        int sum = 0;
        int ans = 0;
        sumFreq.put(0, 1); //base case
        // Since it is binary it will either be same or will be increasing
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += sumFreq.getOrDefault(sum - goal, 0);
            sumFreq.put(sum, sumFreq.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
