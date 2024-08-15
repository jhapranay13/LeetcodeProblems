package leetcode.medium;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 *
 */

public class _198_HouseRobber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//==============================================================================
	//Top down approach
	public int rob(int[] nums) {
		int[] memo = new int[nums.length]; 
		Arrays.fill( memo, -1 );
		return recur(nums, 0, memo);
	}

	public int recur(int[] nums, int index, int[] memo) {

		if (index >= nums.length) {
			return 0;
		}

		if (memo[index] > -1) {
			return memo[index];
		}
		int include = nums[index] + recur(nums, index + 2, memo);
		int exclude = recur(nums, index + 1, memo);
		return memo[index] = Math.max(include, exclude);
	}
	//==============================================================================
	//Bottom up approach
	public int rob1(int[] nums) {
		int[] memo = new int[nums.length + 2]; 

		for (int index = nums.length - 1; index >= 0; index--) {
			int include = nums[index] + memo[index + 2];
			int exclude = memo[index + 1];
			memo[index] = Math.max(include, exclude);    
		}
		return memo[0];
	}
}
