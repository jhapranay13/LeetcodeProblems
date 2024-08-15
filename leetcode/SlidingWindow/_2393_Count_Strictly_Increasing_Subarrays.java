package leetcode.SlidingWindow;

/**
 *
 * You are given an array nums consisting of positive integers.
 *
 * Return the number of subarrays of nums that are in strictly increasing order.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,4,4,6]
 * Output: 10
 * Explanation: The strictly increasing subarrays are the following:
 * - Subarrays of length 1: [1], [3], [5], [4], [4], [6].
 * - Subarrays of length 2: [1,3], [3,5], [4,6].
 * - Subarrays of length 3: [1,3,5].
 * The total number of subarrays is 6 + 3 + 1 = 10.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 15
 * Explanation: Every subarray is strictly increasing. There are 15 possible subarrays that we can take.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 */

public class _2393_Count_Strictly_Increasing_Subarrays {

    public long countSubarrays(int[] nums) {
        int slow = 0;
        int fast = 1;
        long ans = 0;
        long n = 0;

        while (fast < nums.length) {

            if (fast != 0 && nums[fast] <= nums[fast - 1]) {
                n = fast - slow;
                ans += n == 0 ? 1 : (n * (n + 1)) / 2;
                slow = fast;
            }
            fast++;
        }
        n = fast - slow;
        ans += (n * (n + 1)) / 2;
        return ans;
    }
    //=============================================================================================
    public long countSubarrays1(int[] nums) {
        int slow = 0;
        int fast = 0;
        long ans = 0;

        while (fast < nums.length) {

            if (fast != 0 && nums[fast] <= nums[fast - 1]) {
                slow = fast;
            }
            fast++;
            ans += fast - slow;
        }
        return ans;
    }
}
