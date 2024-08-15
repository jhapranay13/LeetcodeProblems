package leetcode.ForBiginners.DynamicProgramming.StateMachine;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array prices where prices[i] is the price of
 *         a given stock on the ith day, and an integer k.
 * 
 *         Find the maximum profit you can achieve. You may complete at most k
 *         transactions.
 * 
 *         Note: You may not engage in multiple transactions simultaneously
 *         (i.e., you must sell the stock before you buy again).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: k = 2, prices = [2,4,1] Output: 2 Explanation: Buy on day 1
 *         (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * 
 *         Example 2:
 * 
 *         Input: k = 2, prices = [3,2,6,5,0,3] Output: 7 Explanation: Buy on
 *         day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *         Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit =
 *         3-0 = 3.
 * 
 * 
 *         Constraints:
 * 
 *         0 <= k <= 100 0 <= prices.length <= 1000 0 <= prices[i] <= 1000
 *
 *
 */

public class _188_BestTimeToBuyAndSellStock4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ===========================================================================
	// Top Down approach
	public int maxProfit(int k, int[] prices) {
		int[][] memo = new int[k + 1][prices.length];
		for (int[] mem : memo)
			Arrays.fill(mem, -1);
		return dfs(k, prices, 0, memo);
	}

	private int dfs(int k, int[] prices, int d, int[][] memo) {
		if (k == 0 || d >= prices.length)
			return 0;
		if (memo[k - 1][d] != -1)
			return memo[k - 1][d];

		int min = prices[d];
		int max = 0;
		for (int i = d + 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			max = Math.max(max, prices[i] - min + dfs(k - 1, prices, i + 1, memo));
		}

		return memo[k - 1][d] = max;
	}

	// ===========================================================================
	// Bottom up approach
	public int maxProfit1(int n, int[] prices) {
		int[][] memo = new int[n + 1][prices.length + 1];
		// for(int[] mem : memo) Arrays.fill(mem, -1);

		for (int k = 1; k <= n; k++) {
			for (int d = prices.length - 1; d >= 0; d--) {
				int min = Integer.MAX_VALUE;
				int max = 0;

				for (int i = d; i < prices.length; i++) {
					min = Math.min(min, prices[i]);
					max = Math.max(max, prices[i] - min + memo[k - 1][i + 1]);
				}

				memo[k][d] = max;
			}
		}
		return memo[n][0];
	}
}
