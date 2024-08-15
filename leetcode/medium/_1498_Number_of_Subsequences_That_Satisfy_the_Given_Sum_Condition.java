package leetcode.medium;

import java.util.Arrays;

/**
 *
 * You are given an array of integers nums and an integer target.
 *
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition.
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * Example 2:
 *
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * Example 3:
 *
 * Input: nums = [2,3,3,4,6,7], target = 12
 * Output: 61
 * Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
 * Number of valid subsequences (63 - 2 = 61).
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= target <= 10^6
 *
 */

public class _1498_Number_of_Subsequences_That_Satisfy_the_Given_Sum_Condition {
    // When you look at the pattern it is same as 2 ^ n where n is the differnece of indexes
    public int numSubseq(int[] nums, int target) {
        int mod = 1000000007;
        int[] pow = new int[nums.length];
        pow[0] = 1;

        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }
        Arrays.sort(nums);
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(nums, i, target);

            if (index != -1) {
                ans = (ans + pow[index - i]) % mod;
            }
        }
        return ans;
    }

    private int binarySearch(int[] nums, int start, int target) {
        int lo = start;
        int hi = nums.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (nums[pivot] + nums[start] <= target) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
