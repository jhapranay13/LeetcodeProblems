package leetcode.Arrays;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums and an integer k, return true if it is
 *         possible to divide this array into k non-empty subsets whose sums are
 *         all equal.
 * 
 *         Example 1:
 * 
 *         Input: nums = [4,3,2,3,5,2,1], k = 4 Output: true Explanation: It's
 *         possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with
 *         equal sums. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2,3,4], k = 3 Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= k <= nums.length <= 16 1 <= nums[i] <= 104 The frequency of each
 *         element is in the range [1, 4].
 *
 */

public class _698_PartitiontoKEqualSumSubset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;

		for (int n : nums) {
			sum += n;
		}

		if (sum % k != 0) {
			return false;
		}
		int target = sum / k;
		Arrays.sort(nums);

		if (target < nums[nums.length - 1]) {
			return false;
		}
		boolean[] v = new boolean[nums.length];
		return dfs(nums, v, nums.length - 1, k, 0, target);
	}

	private boolean dfs(int[] nums, boolean[] v, int hi, int k, int sum, int target) {

		if (k == 0) {
			return true;
		}

		if (sum == target && dfs(nums, v, nums.length - 1, k - 1, 0, target)) {
			return true;
		}

		for (int i = hi; i >= 0; i--) {

			if (!v[i] && sum + nums[i] <= target) {
				v[i] = true;

				if (dfs(nums, v, i - 1, k, sum + nums[i], target)) {
					return true;
				}
				v[i] = false;
			}
		}
		return false;
	}

}
