package leetcode.SlidingWindow;

import java.util.Arrays;

/**
 *
 * You are given an integer array nums. In one move, you can choose one element of nums and change
 * it by any value.
 *
 * Return the minimum difference between the largest and smallest value of nums after performing
 * at most three moves.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 * Example 2:
 *
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */

public class _1509_MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
    public int minDifference(int[] nums) {

        if (nums.length <= 3) {
            return 0;
        }
        Arrays.sort(nums);
        int start = nums.length - 3;//Rotating sliding window
        int end = start + 2;
        int ans = Integer.MAX_VALUE;
        //if we change the value the max will become start - 1 and min will become end + 1 % nums.length
        for (int i = 0; i <= 3; i++) {
            ans = Math.min(ans, nums[(start - 1) % nums.length] - nums[(end + 1) % nums.length]);
            start++;
            end++;
        }
        return ans;
    }
}
