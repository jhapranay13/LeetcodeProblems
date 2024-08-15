package leetcode.hard;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * You are given an integer array nums and a positive integer k. You can choose any subsequence of the array and sum all of its elements together.
 *
 * We define the K-Sum of the array as the kth largest subsequence sum that can be obtained (not necessarily distinct).
 *
 * Return the K-Sum of the array.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Note that the empty subsequence is considered to have a sum of 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,4,-2], k = 5
 * Output: 2
 * Explanation: All the possible subsequence sums that we can obtain are the following sorted in decreasing order:
 * - 6, 4, 4, 2, 2, 0, 0, -2.
 * The 5-Sum of the array is 2.
 * Example 2:
 *
 * Input: nums = [1,-2,3,4,-10,12], k = 16
 * Output: 10
 * Explanation: The 16-Sum of the array is 10.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 1 <= k <= min(2000, 2^n)
 *
 */

public class _2386_Find_the_K_Sum_of_an_Array {
    public long kSum(int[] nums, int k) {
        long sum = 0;
        Queue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        for (int i = 0; i < nums.length; i++) {
            sum = sum + Math.max(0, nums[i]);//since Max sum will be sum of all +ve
            nums[i] = Math.abs(nums[i]);//changing all to absolute value as it is minus in any case
        }
        Arrays.sort(nums);
        long result = sum;
        pq.offer(new long[] {sum - nums[0], 0});

        while (--k > 0) {
            long[] pair = pq.poll();
            result = pair[0];
            int index = (int)pair[1];

            if (index < nums.length - 1) {
                //index + 1 is the current value
                //keeping the last value and subtracting the current one
                pq.offer(new long[] {result + nums[index] - nums[index + 1], index + 1});
                //subtracting the current value
                pq.offer(new long[] {result - nums[index + 1], index + 1});
            }
        }
        return result;
    }
}
