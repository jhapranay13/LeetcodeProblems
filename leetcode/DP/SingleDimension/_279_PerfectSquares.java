package leetcode.DP.SingleDimension;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha Given an integer n, return the least number of perfect
 *         square numbers that sum to n.
 * 
 *         A perfect square is an integer that is the square of an integer; in
 *         other words, it is the product of some integer with itself. For
 *         example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: n = 12 Output: 3 Explanation: 12 = 4 + 4 + 4.
 *         
 *         Example 2:
 * 
 *         Input: n = 13 Output: 2 Explanation: 13 = 4 + 9.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 104
 *
 */

public class _279_PerfectSquares {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach
	public int numSquares(int n) {
		int[] memo = new int[n];
		int ans = recur(n, 0, memo);
		return ans == Integer.MAX_VALUE ? 0 : ans;
	}

	private int recur(int n, int sum, int[] memo) {

		if (sum == n) {
			return 0;
		}

		if (sum > n) {
			return Integer.MAX_VALUE;
		}

		if (memo[sum] > 0) {
			return memo[sum];
		}
		int ans = Integer.MAX_VALUE;

		for (int i = 1; i <= Math.sqrt(n); i++) {
			int currVal = i * i;
			int next = recur(n, sum + currVal, memo);

			if (next != Integer.MAX_VALUE) {
				ans = Math.min(ans, 1 + next);
			}
		}
		return memo[sum] = ans;
	}
	// =============================================================================
	// Bottom up Approach
	public int numSquares1(int n) {
		int[] memo = new int[n + 1];
		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[n] = 0;

		for (int sum = n - 1; sum >= 0; sum--) {

			int ans = Integer.MAX_VALUE;

			for (int i = 1; i <= Math.sqrt(n); i++) {
				int currVal = i * i;

				if (currVal + sum > n) {
					continue;
				}
				int next = memo[sum + currVal];

				if (next != Integer.MAX_VALUE) {
					ans = Math.min(ans, 1 + next);
				}
			}
			memo[sum] = ans;
		}
		return  memo[0] == Integer.MAX_VALUE ? 0 : memo[0];
	}
}
