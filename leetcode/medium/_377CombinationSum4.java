package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of distinct integers nums and a target integer target,
 *         return the number of possible combinations that add up to target.
 * 
 *         The answer is guaranteed to fit in a 32-bit integer.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3], target = 4 Output: 7 Explanation: The possible
 *         combination ways are: (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1,
 *         1) (2, 2) (3, 1) Note that different sequences are counted as
 *         different combinations. Example 2:
 * 
 *         Input: nums = [9], target = 3 Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 200 1 <= nums[i] <= 1000 All the elements of nums
 *         are unique. 1 <= target <= 1000
 * 
 * 
 *         Follow up: What if negative numbers are allowed in the given array?
 *         How does it change the problem? What limitation we need to add to the
 *         question to allow negative numbers?
 *
 */

public class _377CombinationSum4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach TLE
	public int combinationSum4(int[] nums, int target) {
		int memo[] = new int[target + 1];
		return recur(nums, target, memo);
	}

	private int recur(int[] nums, int target, int[] memo) {
		if (target == 0) {
			return 1;
		}

		if (target < 0) {
			return 0;
		}

		if (memo[target] > 0) {
			return memo[target];
		}
		int ans = 0;

		for (int num : nums) {
			ans += recur(nums, target - num, memo);
		}
		return memo[target] = ans;
	}

	// ===========================================================================
	// Bottom up approach
	public int combinationSum4I(int[] nums, int target) {
		int memo[] = new int[target + 1];
		memo[0] = 1;

		for (int i = 1; i <= target; i++) {
			int ans = 0;

			for (int num : nums) {

				if (i - num < 0) {
					continue;
				}
				ans += memo[i - num];
			}
			memo[i] = ans;
		}
		return memo[target];
	}
}
