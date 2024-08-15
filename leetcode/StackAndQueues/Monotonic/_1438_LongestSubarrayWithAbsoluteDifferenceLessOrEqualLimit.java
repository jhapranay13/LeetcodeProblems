package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers nums and an integer limit, return the size
 *         of the longest non-empty subarray such that the absolute difference
 *         between any two elements of this subarray is less than or equal to
 *         limit.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [8,2,4,7], limit = 4 Output: 2 Explanation: All
 *         subarrays are: [8] with maximum absolute diff |8-8| = 0 <= 4. [8,2]
 *         with maximum absolute diff |8-2| = 6 > 4. [8,2,4] with maximum
 *         absolute diff |8-2| = 6 > 4. [8,2,4,7] with maximum absolute diff
 *         |8-2| = 6 > 4. [2] with maximum absolute diff |2-2| = 0 <= 4. [2,4]
 *         with maximum absolute diff |2-4| = 2 <= 4. [2,4,7] with maximum
 *         absolute diff |2-7| = 5 > 4. [4] with maximum absolute diff |4-4| = 0
 *         <= 4. [4,7] with maximum absolute diff |4-7| = 3 <= 4. [7] with
 *         maximum absolute diff |7-7| = 0 <= 4. Therefore, the size of the
 *         longest subarray is 2.
 * 
 *         Example 2:
 * 
 *         Input: nums = [10,1,2,4,7,2], limit = 5 Output: 4 Explanation: The
 *         subarray [2,4,7,2] is the longest since the maximum absolute diff is
 *         |2-7| = 5 <= 5.
 * 
 *         Example 3:
 * 
 *         Input: nums = [4,2,2,2,4,4,2,2], limit = 0 Output: 3
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 1 <= nums[i] <= 109 0 <= limit <= 109
 *
 */

public class _1438_LongestSubarrayWithAbsoluteDifferenceLessOrEqualLimit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int longestSubarray(int[] nums, int limit) {
		Deque<Integer> monoIncreasing = new LinkedList<>();// should store min index
		Deque<Integer> monoDecreasing = new LinkedList<>();// Should Store max Index
		int slow = 0;
		int ans = 0;

		for (int fast = 0; fast < nums.length; fast++) {

			while (!monoDecreasing.isEmpty() && nums[monoDecreasing.peekLast()] < nums[fast]) {
				monoDecreasing.pollLast();
			}
			monoDecreasing.offerLast(fast);

			while (!monoIncreasing.isEmpty() && nums[monoIncreasing.peekLast()] > nums[fast]) {
				monoIncreasing.pollLast();
			}
			monoIncreasing.offerLast(fast);

			if (Math.abs(nums[monoDecreasing.peek()] - nums[monoIncreasing.peek()]) > limit) {
				if (!monoIncreasing.isEmpty() && slow == monoIncreasing.peek()) {
					monoIncreasing.pollFirst();
				}

				if (!monoDecreasing.isEmpty() && slow == monoDecreasing.peek()) {
					monoDecreasing.pollFirst();
				}
				slow++;
			}
			ans = Math.max(ans, fast - slow + 1);
		}
		return ans;
	}
}
