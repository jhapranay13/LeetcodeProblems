package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array arr, partition the array into (contiguous)
 *         subarrays of length at most k. After partitioning, each subarray has
 *         their values changed to become the maximum value of that subarray.
 * 
 *         Return the largest sum of the given array after partitioning. Test
 *         cases are generated so that the answer fits in a 32-bit integer.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: arr = [1,15,7,9,2,5,10], k = 3 Output: 84 Explanation: arr
 *         becomes [15,15,15,9,10,10,10]
 * 
 *         Example 2:
 * 
 *         Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4 Output: 83
 * 
 *         Example 3:
 * 
 *         Input: arr = [1], k = 1 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 500 0 <= arr[i] <= 109 1 <= k <= arr.length
 *
 */

public class _1043_PartitionArrayForMaximumSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach
	public int maxSumAfterPartitioning(int[] arr, int k) {
		int memo[] = new int[arr.length];
		int max = recur(arr, k, 0, memo);
		return max;
	}

	public int recur(int[] arr, int k, int index, int[] memo) {

		if (index >= arr.length) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int maxVal = 0;
		int ans = 0;

		for (int i = index; i < index + k && i < arr.length; i++) {
			maxVal = Math.max(maxVal, arr[i]);
			int length = i - index + 1;
			int next = recur(arr, k, i + 1, memo);
			ans = Math.max(ans, maxVal * length + next);
		}
		return memo[index] = ans;
	}

	// =============================================================================
	// Bottom up approach
	public int maxSumAfterPartitioning1(int[] arr, int k) {
		int memo[] = new int[arr.length + 1];

		for (int index = arr.length - 1; index >= 0; index--) {
			int maxVal = 0;
			int ans = 0;

			for (int i = index; i < index + k && i < arr.length; i++) {
				maxVal = Math.max(maxVal, arr[i]);
				int length = i - index + 1;
				int next = memo[i + 1];
				ans = Math.max(ans, maxVal * length + next);
			}
			memo[index] = ans;
		}
		return memo[0];
	}
}
