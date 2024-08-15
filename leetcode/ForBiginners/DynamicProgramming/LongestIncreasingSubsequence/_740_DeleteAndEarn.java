package leetcode.ForBiginners.DynamicProgramming.LongestIncreasingSubsequence;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array nums. You want to maximize the number
 *         of points you get by performing the following operation any number of
 *         times:
 * 
 *         Pick any nums[i] and delete it to earn nums[i] points. Afterwards,
 *         you must delete every element equal to nums[i] - 1 and every element
 *         equal to nums[i] + 1. Return the maximum number of points you can
 *         earn by applying the above operation some number of times.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [3,4,2] Output: 6 Explanation: You can perform the
 *         following operations: - Delete 4 to earn 4 points. Consequently, 3 is
 *         also deleted. nums = [2]. - Delete 2 to earn 2 points. nums = []. You
 *         earn a total of 6 points. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [2,2,3,3,3,4] Output: 9 Explanation: You can perform
 *         the following operations: - Delete a 3 to earn 3 points. All 2's and
 *         4's are also deleted. nums = [3,3]. - Delete a 3 again to earn 3
 *         points. nums = [3]. - Delete a 3 once more to earn 3 points. nums =
 *         []. You earn a total of 9 points.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 2 * 104 1 <= nums[i] <= 104
 *
 */

public class _740_DeleteAndEarn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach
	public int deleteAndEarn(int[] nums) {
		Arrays.sort(nums);
		int[] memo = new int[nums.length];
		return recur(nums, 0, memo);
	}

	private int recur(int[] nums, int index, int[] memo) {
		if (index == nums.length) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int includeIndex = index;
		int excludeIndex = index;
		int counter = 0;
		int currVal = nums[index];

		while (excludeIndex < nums.length && nums[excludeIndex] == currVal) {
			excludeIndex++;
			counter++;
		}
		includeIndex = excludeIndex;
		// Dont't have to worry about deleteing n - 1 as it is sorted
		while (includeIndex < nums.length && nums[includeIndex] == currVal + 1) {
			includeIndex++;
		}
		int includeVal = counter * currVal;
		return memo[index] = Math.max(includeVal + recur(nums, includeIndex, memo), recur(nums, excludeIndex, memo));
	}
	//==============================================================================
	//Bottom Up Approach
	public int deleteAndEarn1(int[] nums) {
		Arrays.sort(nums);
		int[] memo = new int[nums.length + 1];

		for (int index = nums.length - 1; index >= 0; index--) {
			int includeIndex = index;
			int excludeIndex = index;
			int counter = 0;
			int currVal = nums[index];

			while (excludeIndex < nums.length && nums[excludeIndex] == currVal) {
				excludeIndex++;
				counter++;
			}
			includeIndex = excludeIndex;

			while (includeIndex < nums.length && nums[includeIndex] == currVal + 1) {
				includeIndex++;
			}
			int includeVal = counter * currVal;
			memo[index] = Math.max(includeVal + memo[includeIndex], memo[excludeIndex]);    
		}
		return memo[0];
	}
}
