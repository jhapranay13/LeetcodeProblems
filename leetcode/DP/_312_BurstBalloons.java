package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given n balloons, indexed from 0 to n - 1. Each balloon is
 *         painted with a number on it represented by an array nums. You are
 *         asked to burst all the balloons.
 * 
 *         If you burst the ith balloon, you will get nums[i - 1] * nums[i] *
 *         nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array,
 *         then treat it as if there is a balloon with a 1 painted on it.
 * 
 *         Return the maximum coins you can collect by bursting the balloons
 *         wisely.
 * 
 *         Example 1:
 * 
 *         Input: nums = [3,1,5,8] Output: 167 Explanation: nums = [3,1,5,8] -->
 *         [3,5,8] --> [3,8] --> [8] --> [] coins = 3*1*5 + 3*5*8 + 1*3*8 +
 *         1*8*1 = 167
 * 
 *         Example 2:
 * 
 *         Input: nums = [1,5] Output: 10
 * 
 * 
 *         Constraints:
 * 
 *         n == nums.length 1 <= n <= 500 0 <= nums[i] <= 100
 *
 */

public class _312_BurstBalloons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// Top Down Approach
	public int maxCoins(int[] nums) {
		int[][] memo = new int[nums.length][nums.length];
		return recur(nums, 0, nums.length - 1, memo);
	}

	private int recur(int[] nums, int lo, int hi, int[][] memo) {
		if (lo > hi) {
			return 0;
		}

		if (memo[lo][hi] > 0) {
			return memo[lo][hi];
		}
		int prev = lo == 0 ? 1 : nums[lo - 1];
		int next = hi == nums.length - 1 ? 1 : nums[hi + 1];
		int ans = 0;

		for (int i = lo; i <= hi; i++) {
			int left = recur(nums, lo, i - 1, memo);
			int right = recur(nums, i + 1, hi, memo);
			int val = left + right + (prev * next * nums[i]);
			ans = Math.max(ans, val);
		}
		return memo[lo][hi] = ans;
	}

	// ============================================================================
	// Bottom up approach
	public int maxCoins1(int[] nums) {
		int[][] memo = new int[nums.length][nums.length];

		//Pre condition. Not necessary
		//for (int i = 0; i < nums.length; i++) {
		//	int prev = i == 0 ? 1 : nums[i - 1];
		//	int next = i == nums.length - 1 ? 1 : nums[i + 1];
		//	memo[i][i] = prev * next * nums[i];
		//}

		for (int h = 0; h <= nums.length; h++) {
			for (int lo = 0; lo <= nums.length - h; lo++) {
				int hi = lo + h - 1;

				if (lo > hi) {
					continue;
				}

				int prev = lo - 1 >= 0 ? nums[lo - 1] : 1;
				int next = hi + 1 <= nums.length - 1 ? nums[hi + 1] : 1;
				int ans = 0;

				for (int i = lo; i <= hi; i++) {
					int left = i == 0 ? 0 : memo[lo][i - 1];
					int right = i == nums.length - 1 ? 0 : memo[i + 1][hi];
					int val = left + right + (prev * next * nums[i]);
					ans = Math.max(ans, val);
				}
				memo[lo][hi] = ans;
			}
		}
		return memo[0][nums.length - 1];
	}
}
