package leetcode.ForBiginners.DynamicProgramming.HashPlusDP;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array nums of integers, return the length of the longest
 *         arithmetic subsequence in nums.
 * 
 *         Recall that a subsequence of an array nums is a list nums[i1],
 *         nums[i2], ..., nums[ik] with 0 <= i1 < i2 < ... < ik <= nums.length -
 *         1, and that a sequence seq is arithmetic if seq[i+1] - seq[i] are all
 *         the same value (for 0 <= i < seq.length - 1).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [3,6,9,12] Output: 4 Explanation: The whole array is an
 *         arithmetic sequence with steps of length = 3. Example 2:
 * 
 *         Input: nums = [9,4,7,2,10] Output: 3 Explanation: The longest
 *         arithmetic subsequence is [4,7,10]. Example 3:
 * 
 *         Input: nums = [20,1,15,3,10,5,8] Output: 4 Explanation: The longest
 *         arithmetic subsequence is [20,15,10,5].
 * 
 * 
 *         Constraints:
 * 
 *         2 <= nums.length <= 1000 0 <= nums[i] <= 500
 *
 */

public class _1027_LongestArithmeticSubSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down approach
	public int longestArithSeqLength(int[] nums) {
		// According to constaint as nums[i] <= 500
		int memo[][] = new int[nums.length][500 + 500 + 1];
		int ans = 0;

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int diff = nums[j] - nums[i];
				ans = Math.max(ans, recur(nums, i, diff, memo));
			}
		}
		return ans;
	}

	private int recur(int[] nums, int index, int diff, int[][] memo) {
		if (index == nums.length) {
			return 0;
		}

		if (memo[index][diff + 500] > 0) {
			return memo[index][diff + 500];
		}
		int ans = 1;

		for (int i = index + 1; i < nums.length; i++) {

			if (diff == nums[i] - nums[index]) {
				ans = Math.max(ans, 1 + recur(nums, i, diff, memo));
			}
		}
		return memo[index][diff + 500] = ans;
	}

	// =============================================================================
	// Faster Top Down
	public int longestArithSeqLength1(int[] nums) {
		int[][] memo = new int[nums.length][500 + 500 + 2];
		int ans = 0;

		for (int i = 0; i < nums.length; i++) {
			// 501 indicates that diff has not been calculated yet.
			ans = Math.max(ans, recur(nums, i, 501, memo));
		}
		return ans;
	}

	private int recur2(int[] nums, int index, int diff, int[][] memo) {
		if (index == nums.length) {
			return 0;
		}

		if (memo[index][diff + 500] > 0) {
			return memo[index][diff + 500];
		}
		int ans = 1;

		for (int i = index + 1; i < nums.length; i++) {

			if (diff == 501 || diff == nums[i] - nums[index]) {
				ans = Math.max(ans, 1 + recur2(nums, i, nums[i] - nums[index], memo));
			}
		}
		return memo[index][diff + 500] = ans;
	}

	// ============================================================================
	// HashMap top down
	public int longestArithSeqLength2(int[] nums) {
		Map<String, Integer> memo = new HashMap<>();
		int ans = 0;

		for (int i = 0; i < nums.length; i++) {
			ans = Math.max(ans, recur2(nums, i, 501, memo));
		}
		return ans;
	}

	private int recur2(int[] nums, int index, int diff, Map<String, Integer> memo) {
		if (index == nums.length) {
			return 0;
		}
		String state = index + "," + diff;

		if (memo.get(state) != null) {
			return memo.get(state);
		}
		int ans = 1;

		for (int i = index + 1; i < nums.length; i++) {

			if (diff == 501 || diff == nums[i] - nums[index]) {
				ans = Math.max(ans, 1 + recur2(nums, i, nums[i] - nums[index], memo));
			}
		}
		memo.put(state, ans);
		return ans;
	}

	// ============================================================================
	// Bottom up
	public int longestArithSeqLength3(int[] A) {
		Map<Integer, Integer>[] map = new HashMap[A.length];

		for (int i = 0; i < A.length; i++) {
			map[i] = new HashMap<>();
		}
		int max = 0;

		for (int i = 1; i < A.length; i++) {

			for (int j = 0; j < i; j++) {
				int diff = A[i] - A[j];
				int seqNum = map[j].getOrDefault(diff, 1) + 1;
				map[i].put(diff, seqNum);
				max = Math.max(seqNum, max);
			}
		}
		return max;
	}
}
