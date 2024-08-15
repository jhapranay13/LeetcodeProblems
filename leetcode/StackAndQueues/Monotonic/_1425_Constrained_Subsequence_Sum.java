package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 *
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 * Example 2:
 *
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 * Example 3:
 *
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 */

public class _1425_Constrained_Subsequence_Sum {
    //Maintaining a decreasing queue
    public int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> mono = new LinkedList<>();
        int[] holder = new int[nums.length];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            holder[i] = nums[i] + (mono.isEmpty() ? 0 : holder[mono.peekFirst()]);
            ans = Math.max(holder[i], ans);

            while (!mono.isEmpty() && holder[i] >= holder[mono.peekLast()]) {
                mono.pollLast();
            }

            if (holder[i] > 0) {
                mono.offer(i);
            }

            while (!mono.isEmpty() && mono.peekFirst() <= i - k) {
                mono.pollFirst();
            }
        }
        return ans;
    }
}
