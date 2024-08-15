package leetcode.StackAndQueues.Monotonic;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         iven an integer array nums, you need to find one continuous subarray
 *         that if you only sort this subarray in ascending order, then the
 *         whole array will be sorted in ascending order.
 * 
 *         Return the shortest such subarray and output its length.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [2,6,4,8,10,9,15] Output: 5 Explanation: You need to
 *         sort [6, 4, 8, 10, 9] in ascending order to make the whole array
 *         sorted in ascending order. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2,3,4] Output: 0 
 *         
 *         Example 3:
 * 
 *         Input: nums = [1] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 -105 <= nums[i] <= 105
 * 
 * 
 *         Follow up: Can you solve it in O(n) time complexity?
 *
 */

public class _581_ShortestUnsortedContinuiousSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Using sorting
	public int findUnsortedSubarray(int[] nums) {
		int[] tempNums = nums.clone();
		Arrays.sort(tempNums);
		int start = nums.length;
		int end = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != tempNums[i]) {
				start = Math.min(start, i);
				end = Math.max(end, i);
			}
		}
		return end - start >= 0 ? end - start + 1 : 0;
	}

	// =============================================================================
	// Using Monotonic Queue. 132222 123333
	public int findUnsortedSubarray1(int[] nums) {

		if (nums == null || nums.length <= 1) {
			return 0;
		}
		Deque<Integer> stack = new LinkedList<>();
		int start = -1;
		int end = -1;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] >= max) {
				max = nums[i];
				stack.push(i);
				continue;
			}

			while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
				stack.pop();
			}
			stack.push(i);

			if (start == -1 || start > stack.size() - 1) {
				start = stack.size() - 1;
				end = i;
			} else {
				end = i;
			}
		}
		return start == -1 ? 0 : end - start + 1;
	}

	// =============================================================================
	// Simpler Monotonic Queue
	public int findUnsortedSubarray2(int[] nums) {
		Deque<Integer> monoStack = new LinkedList<>();
		int start = nums.length - 1;
		int end = 0;

		for (int i = 0; i < nums.length; i++) {

			while (!monoStack.isEmpty() && nums[monoStack.peek()] > nums[i]) {
				start = Math.min(start, monoStack.pop());
			}
			monoStack.push(i);
		}

		for (int i = nums.length - 1; i >= 0; i--) {

			while (!monoStack.isEmpty() && nums[monoStack.peek()] < nums[i]) {
				end = Math.max(end, monoStack.pop());
			}
			monoStack.push(i);
		}

		return start < end ? end - start + 1 : 0;
	}
}
