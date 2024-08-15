package leetcode.Arrays;

import java.util.Arrays;

/**
 * The distance of a pair of integers a and b is defined as the absolute difference between a and b.
 *
 * Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1], k = 1
 * Output: 0
 * Explanation: Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Example 2:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1,6,1], k = 3
 * Output: 5
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 10^4
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= n * (n - 1) / 2
 *
 *
 */

public class _719_Find_Kth_Smallest_Pair_Distance {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        //Maximum Difference possible
        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            int count = 0;
            //count the number of pairs for difference less than or equal to value pivot.
            int slow = 0;
            int fast = 1;

            while (fast < nums.length) {
                //If the difference is greater increment slow
                while (nums[fast] - nums[slow] > pivot) {
                    slow++;
                    continue;
                }
                //now all the values between slow and fast will have difference less than or equal to pivot
                count += fast - slow;
                fast++;
            }

            if (count >= k) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return hi;
    }
}
