package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums, return the number of longest increasing
 *         subsequences.
 * 
 *         Notice that the sequence has to be strictly increasing.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,3,5,4,7] Output: 2 Explanation: The two longest
 *         increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 * 
 *         Example 2:
 * 
 *         Input: nums = [2,2,2,2,2] Output: 5 Explanation: The length of
 *         longest continuous increasing subsequence is 1, and there are 5
 *         subsequences' length is 1, so output 5.
 * 
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 2000 -106 <= nums[i] <= 106
 *
 */

public class _673_NumberOfLongestIncreasingSubSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ===========================================================================
	// Top Down approach
	public int findNumberOfLIS(int[] nums) {
		// Arrays.sort(nums);
		int longest = 0;
		int count = 0;
		Map<Integer, int[]> memo = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int[] temp = recur(nums, i, memo);

			if (temp[0] > longest) {
				longest = temp[0];
				count = temp[1];
			} else if (temp[0] == longest) {
				count += temp[1];
			}
		}
		return count;
	}

	private int[] recur(int[] nums, int index, Map<Integer, int[]> memo) {
		if (index == nums.length) {
			return new int[] { 0, 0 };
		}

		if (memo.containsKey(index)) {
			return memo.get(index);
		}
		int ans = 1;
		int count = 1;

		for (int i = index + 1; i < nums.length; i++) {

			if (nums[index] < nums[i]) {
				int[] holder = recur(nums, i, memo);

				if (ans < 1 + holder[0]) {
					ans = 1 + holder[0];
					count = holder[1];
				} else if (ans == 1 + holder[0]) {
					count += holder[1];
				}
			}
		}
		memo.put(index, new int[] { ans, count });
		return new int[] { ans, count };
	}

	// ============================================================================
	// Bottom up approach
	public int findNumberOfLIS1(int[] nums) {
		int longest = 0;
		int maxCount = 0;
		Map<Integer, int[]> memo = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			memo.put(i, new int[] { 1, 1 });
		}

		for (int index = nums.length - 1; index >= 0; index--) {
			int ans = 1;
			int count = 1;

			for (int i = index + 1; i < nums.length; i++) {

				if (nums[index] < nums[i]) {
					int[] holder = memo.get(i);

					if (ans < 1 + holder[0]) {
						ans = 1 + holder[0];
						count = holder[1];
					} else if (ans == 1 + holder[0]) {
						count += holder[1];
					}
				}
			}
			memo.put(index, new int[] { ans, count });
			int[] temp = memo.get(index);

			if (temp[0] > longest) {
				longest = temp[0];
				maxCount = temp[1];
			} else if (temp[0] == longest) {
				maxCount += temp[1];
			}
		}
		return maxCount;
	}
}
