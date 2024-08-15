package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array nums which consists of non-negative integers and an
 *         integer m, you can split the array into m non-empty continuous
 *         subarrays.
 * 
 *         Write an algorithm to minimize the largest sum among these m
 *         subarrays.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [7,2,5,10,8], m = 2 Output: 18 Explanation: There are
 *         four ways to split nums into two subarrays. The best way is to split
 *         it into [7,2,5] and [10,8], where the largest sum among the two
 *         subarrays is only 18.
 * 
 *         Example 2:
 * 
 *         Input: nums = [1,2,3,4,5], m = 2 Output: 9
 * 
 *         Example 3:
 * 
 *         Input: nums = [1,4,4], m = 3 Output: 4
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 1000 0 <= nums[i] <= 106 1 <= m <= min(50,
 *         nums.length)
 *
 */

public class _410_SplitArrayLargestSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =======================================================================
	// Top Down approach
	public int splitArray(int[] nums, int m) {
		int[][] memo = new int[nums.length][m + 1];
		int ans = rec(nums, 0, 1, m, memo);
		return ans;
	}

	private int rec(int[] nums, int index, int split, int m, int[][] memo) {

		if (split == m + 1 && nums.length == index) {
			return 0;
		}

		if (split >= m + 1 || nums.length <= index) {
			return Integer.MAX_VALUE;
		}

		if (memo[index][split] > 0) {
			return memo[index][split];
		}
		int ans = Integer.MAX_VALUE;
		int currSum = 0;

		for (int i = index; i < nums.length; i++) {
			currSum += nums[i];
			int restSum = rec(nums, i + 1, split + 1, m, memo);
			int temp = Math.max(currSum, restSum);
			ans = Math.min(ans, temp);
		}
		return memo[index][split] = ans;
	}

	// ==========================================================================
	// Bottom up approach
	public int splitArray1(int[] nums, int m) {
		int[][] memo = new int[nums.length + 1][m + 2];

		for (int i = 0; i <= nums.length; i++) {

			for (int j = 0; j <= m; j++) {
				memo[i][j] = Integer.MAX_VALUE;
			}
		}
		memo[nums.length][m] = 0;

		for (int index = nums.length - 1; index >= 0; index--) {

			for (int split = m - 1; split >= 0; split--) {
				int ans = Integer.MAX_VALUE;
				int currSum = 0;

				for (int i = index; i < nums.length; i++) {
					currSum += nums[i];
					int restSum = memo[i + 1][split + 1];
					int temp = Math.max(currSum, restSum);
					ans = Math.min(ans, temp);
				}
				memo[index][split] = ans;
			}

		}
		return memo[0][0];
	}
}
