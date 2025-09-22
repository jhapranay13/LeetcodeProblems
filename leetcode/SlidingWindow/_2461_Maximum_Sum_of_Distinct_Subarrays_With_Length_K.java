package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
 *
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
 * Example 2:
 *
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is repeated.
 * We return 0 because no subarrays meet the conditions.
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 */
public class _2461_Maximum_Sum_of_Distinct_Subarrays_With_Length_K {
    public long maximumSubarraySum(int[] nums, int k) {
        long currSum = 0;
        int fast = 0;
        int slow = 0;
        long ans = 0;
        Map<Integer, Long> freqMap = new HashMap<>();

        while (fast < nums.length) {
            int num = nums[fast];
            long freq = freqMap.getOrDefault(num, 0L);
            freqMap.put(num, ++freq);
            currSum += num;

            while (slow <= fast && (freqMap.get(num) > 1 || fast - slow + 1 > k)) {
                int sNum = nums[slow++];
                currSum -= sNum;
                long sFreq = freqMap.get(sNum);

                if (sFreq == 1) {
                    freqMap.remove(sNum);
                } else {
                    freqMap.put(num, --sFreq);
                }
            }

            if (fast - slow + 1 == k) {
                ans = Math.max(ans, currSum);
            }
            fast++;
        }
        return ans;
    }
}
