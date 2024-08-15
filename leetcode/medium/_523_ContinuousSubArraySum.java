package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size
 * at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a
 * multiple of k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 *
 */

public class _523_ContinuousSubArraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        //Logically if we have prefixSum and sve the reaminder t every point
        //if we get the same remainder then from that index to this index
        //we have a sub array whihc is divisible by k
        Map<Integer, Integer> remainderIndexMap = new HashMap<>();
        int sum = 0; //to store prefixSum
        remainderIndexMap.put(0, -1); //base case;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;

            if (remainderIndexMap.containsKey(remainder)) {
                // + 1 because the start for next sum is next index
                if (remainderIndexMap.get(remainder) + 1 < i) {
                    return true;
                }
            } else {
                remainderIndexMap.put(remainder, i);
            }
        }
        return false;
    }
}
