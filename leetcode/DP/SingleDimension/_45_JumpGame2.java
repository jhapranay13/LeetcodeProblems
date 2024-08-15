package leetcode.DP.SingleDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of non-negative integers nums, you are initially
 *         positioned at the first index of the array.
 * 
 *         Each element in the array represents your maximum jump length at that
 *         position.
 * 
 *         Your goal is to reach the last index in the minimum number of jumps.
 * 
 *         You can assume that you can always reach the last index.
 * 
 *         Example 1:
 * 
 *         Input: nums = [2,3,1,1,4] Output: 2 Explanation: The minimum number
 *         of jumps to reach the last index is 2. Jump 1 step from index 0 to 1,
 *         then 3 steps to the last index.
 * 
 *         Example 2:
 * 
 *         Input: nums = [2,3,0,1,4] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 0 <= nums[i] <= 1000
 *
 */

public class _45_JumpGame2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Greedy Approach
	public int jump(int[] nums) {

		if (nums.length <= 1) {
			return 0;
		}
		int jump = 0;
		int window = 0;
		int maxJump = 0;

		for (int i = 0; i < nums.length; i++) {
			maxJump = Math.max(maxJump, i + nums[i]);

			if (maxJump >= nums.length - 1) {
				jump++;
				break;
			}

			if (i == window) {
				window = maxJump;
				jump++;
			}
		}
		return jump;
	}

	// =====================================================================================
	// Top Down Approach
	public int jump1(int[] nums) {
		int[] memo = new int[nums.length];
		int ans = recur(nums, 0, memo);
		return ans;
	}

	private int recur(int[] nums, int index, int[] memo) {

		if (index >= nums.length - 1) {
			return 0;
		}

		if (nums[index] == 0) {
			return memo[index] = Integer.MAX_VALUE;
		}

		if (memo[index] != 0) {
			return memo[index];
		}
		int maxPos = index + nums[index];
		int jumps = Integer.MAX_VALUE;

		for (int i = index + 1; i <= maxPos; i++) {
			jumps = Math.min(jumps, recur(nums, i, memo));
		}
		return memo[index] = jumps == Integer.MAX_VALUE ? Integer.MAX_VALUE : jumps + 1;
	}

	// =====================================================================================
	// Bottom Up Approach
	public int jump2(int[] nums) {
		int[] memo = new int[nums.length + 1];

		for (int index = nums.length - 2; index >= 0; index--) {
			if (nums[index] == 0) {
				memo[index] = Integer.MAX_VALUE;
				continue;
			}
			int ans = Integer.MAX_VALUE;

			for (int i = index + 1; i <= nums[index] + index && i < nums.length; i++) {
				int temp = memo[i];
				ans = temp == Integer.MAX_VALUE ? ans : Math.min(ans, temp + 1);
			}
			memo[index] = ans;
		}
		return memo[0];
	}
}
