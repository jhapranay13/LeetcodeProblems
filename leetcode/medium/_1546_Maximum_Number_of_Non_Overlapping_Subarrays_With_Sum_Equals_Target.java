package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array nums and an integer target, return the maximum number of non-empty non-overlapping subarrays such that the sum of values in each subarray is equal to target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 2
 * Output: 2
 * Explanation: There are 2 non-overlapping subarrays [1,1,1,1,1] with sum equals to target(2).
 * Example 2:
 *
 * Input: nums = [-1,3,5,1,4,2,-9], target = 6
 * Output: 2
 * Explanation: There are 3 subarrays with sum equal to 6.
 * ([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -104 <= nums[i] <= 10^4
 * 0 <= target <= 10^6
 *
 */

public class _1546_Maximum_Number_of_Non_Overlapping_Subarrays_With_Sum_Equals_Target {
    //HashMap solution
    public int maxNonOverlapping(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        int ans = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (cache.containsKey(sum - target)) {
                ans += cache.get(sum - target);
                cache.clear();
            }
            cache.put(sum, 1);
        }
        return ans;
    }
    //=============================================================================================
    //Bottom up Approach TLE
    public int maxNonOverlapping1(int[] nums, int target) {
        int[] memo = new int[nums.length + 1];

        for (int index = nums.length - 1; index >= 0; index--) {
            int ans = 0;
            int sum = 0;

            for (int i = index; i < nums.length; i++) {
                sum += nums[i];

                if (sum == target) {
                    ans = Math.max(ans, 1 + recur(nums, target, i + 1, memo));
                    break;
                }
                ans = Math.max(ans, recur(nums, target, i + 1, memo));
            }
            memo[index] = ans;
        }
        return memo[0];
    }
    //=============================================================================================
    //Top Down Approach TLE
    public int maxNonOverlapping2(int[] nums, int target) {
        int[] memo = new int[nums.length];
        return recur(nums, target, 0, memo);
    }

    private int recur(int[] nums, int target, int index, int[] memo) {

        if (index == nums.length) {
            return 0;
        }

        if (memo[0] > 0) {
            return memo[0];
        }
        int ans = 0;
        int sum = 0;

        for (int i = index; i < nums.length; i++) {
            sum += nums[i];

            if (sum == target) {
                ans = Math.max(ans, 1 + recur(nums, target, i + 1, memo));
                break;
            }
            ans = Math.max(ans, recur(nums, target, i + 1, memo));
        }
        return memo[index] = ans;
    }
}
