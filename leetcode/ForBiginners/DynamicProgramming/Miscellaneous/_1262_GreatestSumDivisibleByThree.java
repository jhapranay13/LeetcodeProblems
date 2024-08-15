package leetcode.ForBiginners.DynamicProgramming.Miscellaneous;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array nums of integers, we need to find the maximum possible
 *         sum of elements of the array such that it is divisible by three.
 * 
 *         Example 1:
 * 
 *         Input: nums = [3,6,5,1,8] Output: 18 Explanation: Pick numbers 3, 6,
 *         1 and 8 their sum is 18 (maximum sum divisible by 3).
 * 
 *         Example 2:
 * 
 *         Input: nums = [4] Output: 0 Explanation: Since 4 is not divisible by
 *         3, do not pick any number.
 * 
 *         Example 3:
 * 
 *         Input: nums = [1,2,3,4,4] Output: 12 Explanation: Pick numbers 1, 3,
 *         4 and 4 their sum is 12 (maximum sum divisible by 3).
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 4 * 10^4 1 <= nums[i] <= 10^4
 *
 */

public class _1262_GreatestSumDivisibleByThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Top Down approach
	public int maxSumDivThree(int[] nums) {
		int memo[][] = new int[nums.length][3];
		return recur(nums, 0, 0, memo);
	}

	private int recur(int[] nums, int index, int remainder, int[][] memo) {
		if (index == nums.length) {
			return remainder > 0 ? Integer.MIN_VALUE : 0;
		}

		if (memo[index][remainder] > 0) {
			return memo[index][remainder];
		}

		int include = nums[index] + recur(nums, index + 1, (remainder + nums[index]) % 3, memo);
		int exclude = recur(nums, index + 1, remainder, memo);
		return memo[index][remainder] = Math.max(include, exclude);
	}

	// ======================================================================
	// Bottom up approach
	public int maxSumDivThree1(int[] nums) {
		int memo[][] = new int[nums.length + 1][3];
		memo[nums.length][1] = Integer.MIN_VALUE;
		memo[nums.length][2] = Integer.MIN_VALUE;

		for (int index = nums.length - 1; index >= 0; index--) {
			for (int remainder = 2; remainder >= 0; remainder--) {
				int include = nums[index] + memo[index + 1][(remainder + nums[index]) % 3];
				int exclude = memo[index + 1][remainder];
				memo[index][remainder] = Math.max(include, exclude);
			}
		}
		return memo[0][0];
	}
}
