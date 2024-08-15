package leetcode.DP.SingleDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         You are climbing a staircase. It takes n steps to reach the top.
 * 
 *         Each time you can either climb 1 or 2 steps. In how many distinct
 *         ways can you climb to the top?
 * 
 *         Example 1:
 * 
 *         Input: n = 2 Output: 2 Explanation: There are two ways to climb to
 *         the top. 1. 1 step + 1 step 2. 2 steps
 * 
 *         Example 2:
 * 
 *         Input: n = 3 Output: 3 Explanation: There are three ways to climb to
 *         the top. 1. 1 step + 1 step + 1 step 2. 1 step + 2 steps 3. 2 steps +
 *         1 step
 * 
 *         Constraints:
 * 
 *         1 <= n <= 45
 */

public class _70_ClimbingStairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =========================================================================================
	// Top down approach recursive.
	public int climbStairs(int n) {
		int[] memo = new int[n + 1];
		return recur(n, memo);
	}

	private int recur(int n, int[] memo) {

		if (n <= 1) {
			return 1;
		}

		if (memo[n] > 0) {
			return memo[n];
		}
		return memo[n] = recur(n - 1, memo) + recur(n - 2, memo);
	}

	// =========================================================================================
	// Bottom up approach.

	public int climbStairs1(int m) {
		int[] memo = new int[m + 1];

		for (int n = 0; n <= m; n++) {

			if (n <= 1) {
				memo[n] = 1;
				continue;
			}
			int one = (n - 1 >= 0) ? memo[n - 1] : 0;
			int two = (n - 2 >= 0) ? memo[n - 2] : 0;
			memo[n] = one + two;
		}
		return memo[m];
	}

	// =======================================================================
	// Another approch for topdown and bottom up
	public int climbStairs2(int n) {
		int[] memo = new int[n + 2];

		for (int step = n; step >= 0; step--) {

			if (n == step) {
				memo[step] = 1;
				continue;
			}

			int ans = 0;

			for (int i = 1; i <= 2; i++) {
				ans += memo[step + i];
			}
			memo[step] = ans;
		}
		return memo[0];
	}

	private int recur(int n, int step, int[] memo) {

		if (n == step) {
			return 1;
		}

		if (n < step) {
			return 0;
		}

		if (memo[step] > 0) {
			return memo[step];
		}
		int ans = 0;

		for (int i = 1; i <= 2; i++) {
			ans += recur(n, step + i, memo);
		}
		return memo[step] = ans;
	}
}
