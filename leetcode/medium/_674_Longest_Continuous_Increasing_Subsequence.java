package leetcode.medium;

/**
 *
 * Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.
 *
 * A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
 * Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
 * 4.
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
 * increasing.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * -109 <= nums[i] <= 10^9
 *
 */

public class _674_Longest_Continuous_Increasing_Subsequence {
    public int findLengthOfLCIS(int[] nums) {
        int fast = 0;
        int slow = 0;
        int ans = 0;

        while (fast < nums.length) {

            if ( fast != 0 && nums[fast] <= nums[fast - 1]) {
                slow = fast;
            }
            ans = Math.max(ans, fast - slow + 1);
            fast++;
        }
        return ans;
    }
}
