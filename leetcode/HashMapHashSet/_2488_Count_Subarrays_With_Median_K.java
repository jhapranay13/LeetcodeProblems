package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.
 *
 * Return the number of non-empty subarrays in nums that have a median equal to k.
 *
 * Note:
 *
 * The median of an array is the middle element after sorting the array in ascending order. If the array is of even length, the median is the left middle element.
 * For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
 * A subarray is a contiguous part of an array.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,4,5], k = 4
 * Output: 3
 * Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
 * Example 2:
 *
 * Input: nums = [2,3,1], k = 3
 * Output: 1
 * Explanation: [3] is the only subarray that has a median equal to 3.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i], k <= n
 * The integers in nums are distinct.
 *
 */

public class _2488_Count_Subarrays_With_Median_K {
    public int countSubarrays(int[] nums, int k) {
        // for an array 1, 4, 5 where k == 4 Subbarrays are [4], [4, 5], [1, 4, 5]
        // so one more greater element leads to another Subarray
        int pos = 0;

        while (pos < nums.length) {

            if (nums[pos] == k) {
                break;
            }
            pos++;
        }
        Map<Integer, Integer> leftSumMap = new HashMap<>();
        int ans = 1;
        int sum = 0;

        for(int i = pos - 1; i >= 0; i--) {

            if (nums[i] < k) {
                sum--;
            } else if (nums[i] > k){
                sum++;
            }
            leftSumMap.put(sum, leftSumMap.getOrDefault(sum, 0) + 1);
            // sum == 0 means greater than and lesser than are equal.
            // 1 means there is one more which is for the even lengths
            if (sum == 0 || sum == 1) {
                ans++;
            }
        }

        Map<Integer, Integer> rightSumMap = new HashMap<>();
        sum = 0;

        for(int i = pos + 1; i < nums.length; i++) {

            if (nums[i] < k) {
                sum--;
            } else if (nums[i] > k){
                sum++;
            }
            rightSumMap.put(sum, rightSumMap.getOrDefault(sum, 0) + 1);
            // sum == 0 means greater than and lesser than are equal.
            // 1 means there is one more which is for the even lengths
            if (sum == 0 || sum == 1) {
                ans++;
            }
        }
        // As earlier stated for every right value if we have opposite left value or leftvalue
        // 1 - rightVlaue

        for (Map.Entry<Integer, Integer> entrySet : rightSumMap.entrySet()) {
            int rightSum = entrySet.getKey();
            int rightFreq = entrySet.getValue();
            // Negative sign coz we are incrementing for heigher and decrementing for lower
            int leftFreqEqual = leftSumMap.getOrDefault( -rightSum, 0);
            int leftFreqOneLess = leftSumMap.getOrDefault(-(rightSum - 1), 0);
            int totalLeftFreq = leftFreqEqual + leftFreqOneLess;
            ans += rightFreq * totalLeftFreq;
        }
        return ans;
    }
}
