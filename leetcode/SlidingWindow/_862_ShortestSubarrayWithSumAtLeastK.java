package leetcode.SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given an integer array nums and an integer k, return the length of
 *         the shortest non-empty subarray of nums with a sum of at least k. If
 *         there is no such subarray, return -1.
 * 
 *         A subarray is a contiguous part of an array.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1], k = 1 Output: 1 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2], k = 4 Output: -1 
 *         
 *         Example 3:
 * 
 *         Input: nums = [2,-1,2], k = 3 Output: 3
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 -105 <= nums[i] <= 105 1 <= k <= 109
 *
 *
 */

public class _862_ShortestSubarrayWithSumAtLeastK {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int shortestSubarray(int[] nums, int k) {
		Deque<Integer> monoQueue = new LinkedList<>();
		int[] preSum = new int[nums.length + 1];
		int ans = Integer.MAX_VALUE;

		for (int i = 1; i < preSum.length; i++) {
			preSum[i] = nums[i - 1] + preSum[i - 1];
		}

		for (int i = 0; i < preSum.length; i++) {
			while (!monoQueue.isEmpty() && preSum[monoQueue.getLast()] >= preSum[i]) {
				monoQueue.pollLast();
			}

			while (!monoQueue.isEmpty() && preSum[i] - preSum[monoQueue.getFirst()] >= k) {
				ans = Math.min(ans, i - monoQueue.pollFirst());
			}
			monoQueue.offer(i);
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
	//=============================================================================================
	//Changing the presum to long to avoid overflow
	public int shortestSubarray1(int[] nums, int k) {
		Deque<Integer> mono = new LinkedList<>();
		int ans = Integer.MAX_VALUE;
		long preSum[] = new long[nums.length + 1];

		for (int i = 1; i < preSum.length; i++) {
			preSum[i] = nums[i - 1] + preSum[i - 1];
		}

		for (int i = 0; i < preSum.length; i++) {

			while (!mono.isEmpty() && preSum[mono.peekLast()] >= preSum[i]) {
				mono.pollLast();
			}

			while (!mono.isEmpty() && preSum[i] - preSum[mono.peekFirst()] >= k) {
				ans = Math.min(ans, i - mono.pollFirst());
			}
			mono.offer(i);
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
}
