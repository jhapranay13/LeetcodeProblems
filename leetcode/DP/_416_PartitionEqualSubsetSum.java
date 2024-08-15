package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a non-empty array nums containing only positive integers, find
 *         if the array can be partitioned into two subsets such that the sum of
 *         elements in both subsets is equal.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,5,11,5] Output: true Explanation: The array can be
 *         partitioned as [1, 5, 5] and [11]. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2,3,5] Output: false Explanation: The array cannot
 *         be partitioned into equal sum subsets.
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 200 1 <= nums[i] <= 100
 *
 */

public class _416_PartitionEqualSubsetSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===============================================================================
	//Top Down Approach
	public boolean canPartition(int[] nums) {
		int sum = 0;

		for (int num : nums) {
			sum += num;
		}

		if (sum % 2 != 0) {
			return false;
		}
		Boolean[][] memo = new Boolean[nums.length][sum / 2];
		return recur(nums, sum, 0, 0, memo);
	}

	private boolean recur(int[] nums, int sum, int index, int currSum, Boolean[][] memo) {

		if (index == nums.length || currSum > sum / 2) {
			return false;
		}

		if (currSum == sum / 2) {
			return true;
		}

		if (memo[index][currSum] != null) {
			return memo[index][currSum];
		}
		int includeSum = currSum + nums[index];
		return memo[index][currSum] = recur(nums, sum, index + 1, includeSum, memo) ||
				recur(nums, sum, index + 1, currSum, memo);
	}
	//=============================================================================
	//Bottom Up approach
	public boolean canPartition1(int[] nums) {
		int sum = 0;

		for (int num : nums) {
			sum += num;
		}

		if (sum % 2 != 0) {
			return false;
		}
		boolean[][] memo = new boolean[nums.length + 1][sum + 1];

		for (int index = nums.length - 1; index >= 0; index--) {

			for (int currSum = sum / 2; currSum >= 0; currSum--) {

				if (currSum == sum / 2) {
					memo[index][currSum] = true;
					continue;
				}
				int includeSum = currSum + nums[index];

				if (includeSum > sum / 2) {
					memo[index][currSum] = memo[index + 1][currSum];
					continue;
				} 
				memo[index][currSum] = memo[index + 1][includeSum] || 
						memo[index + 1][currSum];
			}
		}
		return memo[0][0];
	}
}
