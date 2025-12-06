package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are given an integer array nums and a positive integer k.
 *
 * Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
 *
 * A subarray is a contiguous sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,3], k = 2
 * Output: 6
 * Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
 * Example 2:
 *
 * Input: nums = [1,4,2,1], k = 3
 * Output: 0
 * Explanation: No subarray contains the element 4 at least 3 times.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= k <= 105
 *
 */

public class _2962_Count_Subarrays_Where_Max_Element_Appears_at_Least_K_Times {
    public long countSubarrays(int[] nums, int k) {
        int slow = 0;
        int fast = 0;
        int maxNum = 0;

        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        long ans = 0;

        while (fast < nums.length) {
            int num = nums[fast];
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            while (countMap.getOrDefault(maxNum, 0) == k) {
                ans++;
                ans += nums.length - fast - 1;
                int slowNumCnt = countMap.get(nums[slow]);

                if (slowNumCnt == 1) {
                    countMap.remove(nums[slow]);
                } else {
                    countMap.put(nums[slow], slowNumCnt - 1);
                }
                slow++;
            }
            fast++;
        }
        return ans;
    }
}
