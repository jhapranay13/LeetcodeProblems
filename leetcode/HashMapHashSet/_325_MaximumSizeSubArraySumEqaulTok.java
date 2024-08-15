package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k.
 * If there isn't one, return 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-1,5,-2,3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * Example 2:
 *
 * Input: nums = [-2,-1,2,1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 105
 * -104 <= nums[i] <= 104
 * -109 <= k <= 109
 *
 */

public class _325_MaximumSizeSubArraySumEqaulTok {

    //instead of presum array a single variable would ork also
    public int maxSubArrayLen(int[] nums, int k) {
        int []presum = new int[nums.length];
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            if (i == 0) {
                presum[i] = nums[i];
            } else {
                presum[i] = presum[i - 1] + nums[i];
            }
            int sum = presum[i];
            int diff = sum - k;

            if (sum == k) {
                ans = i + 1;
            }

            if (sumIndexMap.containsKey(diff)) {
                ans = Math.max(ans, Math.abs(i - sumIndexMap.get(diff)));
            }

            if (!sumIndexMap.containsKey(presum[i])) {
                sumIndexMap.put(presum[i], i);
            }
        }
        return ans;
    }
}
