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
 *         Determine if you are able to reach the last index.
 * 
 *         Example 1:
 * 
 *         Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from
 *         index 0 to 1, then 3 steps to the last index.
 * 
 *         Example 2:
 * 
 *         Input: nums = [3,2,1,0,4] Output: false Explanation: You will always
 *         arrive at index 3 no matter what. Its maximum jump length is 0, which
 *         makes it impossible to reach the last index.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 0 <= nums[i] <= 105
 *
 */

public class _55_JumpGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_55_JumpGame obj = new _55_JumpGame();
		obj.canJump1(new int[] { 1, 1, 1, 0 });
	}

	// Greedy Approach.
	public boolean canJump(int[] nums) {
		int window = 0;
		int maxJump = 0;

		for (int i = 0; i < nums.length; i++) {
			maxJump = Math.max(i + nums[i], maxJump);

			if (maxJump >= nums.length - 1) {
				return true;
			}

			if (maxJump < nums.length - 1 && nums[maxJump] == 0 && maxJump == i) {
				break;
			}

			if (window == i) {
				window = maxJump;
			}
		}
		return false;
	}
	// ===============================================================================
	// Top Down Solution

	public boolean canJump1(int[] nums) {
		Boolean[] memo = new Boolean[nums.length];
		return recur1(nums, 0, memo);
	}

	public boolean recur1(int[] nums, int index, Boolean[] memo) {

		if (index >= nums.length - 1) {
			return true;
		}

		if (nums[index] == 0) {
			return memo[index] = false;
		}

		if (memo[index] != null) {
			return memo[index];
		}
		int window = index + nums[index];
		boolean possible = false;

		for (int i = index + 1; i <= window; i++) {
			possible = recur1(nums, i, memo);

			if (possible) {
				break;
			}
		}
		memo[index] = possible;
		return memo[index].booleanValue();
	}

	// ====================================================================================
	// Bottom up DP approach
	public boolean canJump3(int[] nums) {
		Boolean[] memo = new Boolean[nums.length];

		for (int index = nums.length - 1; index >= 0; index--) {

			if (index >= nums.length - 1) {
				memo[index] = true;
				continue;
			}

			if (nums[index] == 0) {
				memo[index] = false;
				continue;
			}

			int window = index + nums[index];
			boolean possible = false;

			for (int i = index + 1; i <= window; i++) {
				possible = memo[i];

				if (possible) {
					break;
				}
			}
			memo[index] = possible;
		}
		return memo[0];
	}
}
