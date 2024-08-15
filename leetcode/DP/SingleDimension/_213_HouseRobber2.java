package leetcode.DP.SingleDimension;

/**
 * 
 * @author Pranay Jha
 *
 *You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:

Input: nums = [0]
Output: 0

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 *
 */

public class _213_HouseRobber2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach TLE
	public int rob(int[] nums) {

		if( nums.length == 1 ) {
			return nums[ 0 ];
		}
		int[] memo = new int[nums.length];
		int excludeLast = recur(nums, 0, nums.length - 2, memo);
		int first = memo[0];
		memo = new int[nums.length];
		int excludeFirst = recur(nums, 1, nums.length - 1, memo);
		int second = memo[1];
		return Math.max(first, second);
	}

	private int recur(int[] nums, int index, int hi, int[] memo) {

		if (index > hi) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int val = nums[index];
		int including = val + recur(nums, index + 2, hi, memo);
		int excluding = recur(nums, index + 1, hi, memo);
		return memo[index] = Math.max(including, excluding);
	}
	//=============================================================================
	//Bottom up approach DP
	public int rob1(int[] nums) {

		if( nums.length == 1 ) {
			return nums[ 0 ];
		}
		int[] memo = new int[nums.length + 2];

		for(int index = nums.length - 2; index >= 0; index--) {
			int val = nums[index];
			int including = val + memo[index + 2];
			int excluding = memo[index + 1];
			memo[index] = Math.max(including, excluding);
		}
		int first = memo[0];
		memo = new int[nums.length + 2];

		for (int index = nums.length - 1; index >= 1; index--) {
			int val = nums[index];
			int including = val + memo[index + 2];
			int excluding = memo[index + 1];
			memo[index] = Math.max(including, excluding);
		}
		int second = memo[1];
		return Math.max(first, second);
	}
}
