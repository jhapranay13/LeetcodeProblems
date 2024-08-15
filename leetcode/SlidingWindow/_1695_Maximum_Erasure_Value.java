package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 * Example 2:
 *
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 *
 */

public class _1695_Maximum_Erasure_Value {
    public int maximumUniqueSubarray(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int fast = 0;
        int slow = 0;
        int sum = 0;
        int ans = 0;

        while (fast < nums.length) {
            sum += nums[fast];
            int freq = freqMap.getOrDefault(nums[fast], 0);
            freqMap.put(nums[fast], ++freq);

            while (freqMap.get(nums[fast]) > 1) {
                sum -= nums[slow];

                if (freqMap.get(nums[slow]) == 1) {
                    freqMap.remove(nums[slow]);
                } else {
                    freqMap.put(nums[slow], freqMap.get(nums[slow]) - 1);
                }
                slow++;
            }
            ans = Math.max(ans, sum);
            fast++;
        }
        return ans;
    }
}
