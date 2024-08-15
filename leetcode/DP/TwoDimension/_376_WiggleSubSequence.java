package leetcode.DP.TwoDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         A wiggle sequence is a sequence where the differences between
 *         successive numbers strictly alternate between positive and negative.
 *         The first difference (if one exists) may be either positive or
 *         negative. A sequence with one element and a sequence with two
 *         non-equal elements are trivially wiggle sequences.
 * 
 *         For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the
 *         differences (6, -3, 5, -7, 3) alternate between positive and
 *         negative. In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not
 *         wiggle sequences. The first is not because its first two differences
 *         are positive, and the second is not because its last difference is
 *         zero. A subsequence is obtained by deleting some elements (possibly
 *         zero) from the original sequence, leaving the remaining elements in
 *         their original order.
 * 
 *         Given an integer array nums, return the length of the longest wiggle
 *         subsequence of nums.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,7,4,9,2,5] Output: 6 Explanation: The entire
 *         sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,17,5,10,13,15,10,5,16,8] Output: 7 Explanation:
 *         There are several subsequences that achieve this length. One is [1,
 *         17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
 *         Example 3:
 * 
 *         Input: nums = [1,2,3,4,5,6,7,8,9] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 1000 0 <= nums[i] <= 1000
 * 
 * 
 *         Follow up: Could you solve this in O(n) time?
 *
 */

public class _376_WiggleSubSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int wiggleMaxLength(int[] nums) {
		int[][] memo = new int[nums.length][2];
		int ans = 0; 

		for (int i = 0; i <= nums.length - 1; i++) {
			ans = Math.max( ans, 1 + Math.max(dfs(nums, i + 1, i, 0, memo), 
					dfs(nums, i + 1, i, 1, memo)));
		}
		return ans;
	}

	public int dfs(int[] nums, int currIndex, int prevIndex, int positive, int[][] memo) {

		if (currIndex == nums.length) {
			return 0;
		}

		if (memo[currIndex][positive] > 0) {
			return memo[currIndex][positive];
		}
		int ans = 0;

		for (int i = currIndex; i < nums.length; i++) {

			if (positive == 0 && nums[prevIndex] > nums[i]) {
				ans = Math.max(ans, 1 + dfs(nums, i + 1, i, 1, memo));
			} 

			if (positive == 1 && nums[prevIndex] < nums[i]) {
				ans = Math.max(ans, 1 + dfs(nums, i + 1, i, 0, memo));
			}
		}
		return memo[currIndex][positive] = ans;
	}
	//==============================================================================
	//Bottom Up approach
	
}
