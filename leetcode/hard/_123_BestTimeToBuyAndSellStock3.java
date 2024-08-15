package leetcode.hard;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array prices where prices[i] is the price of a given
 *         stock on the ith day.
 * 
 *         Find the maximum profit you can achieve. You may complete at most two
 *         transactions.
 * 
 *         Note: You may not engage in multiple transactions simultaneously
 *         (i.e., you must sell the stock before you buy again).
 * 
 *         Example 1:
 * 
 *         Input: prices = [3,3,5,0,0,3,1,4] Output: 6 Explanation: Buy on day 4
 *         (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3. Then buy
 *         on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * 
 *         Example 2:
 * 
 *         Input: prices = [1,2,3,4,5] Output: 4 Explanation: Buy on day 1
 *         (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4. Note
 *         that you cannot buy on day 1, buy on day 2 and sell them later, as
 *         you are engaging multiple transactions at the same time. You must
 *         sell before buying again.
 * 
 *         Example 3:
 * 
 *         Input: prices = [7,6,4,3,1] Output: 0 Explanation: In this case, no
 *         transaction is done, i.e. max profit = 0.
 * 
 *         Example 4:
 * 
 *         Input: prices = [1] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= prices.length <= 105 0 <= prices[i] <= 105
 *
 */

public class _123_BestTimeToBuyAndSellStock3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// Top Down Approach
	public int maxProfit(int[] prices) {
		int[][] memo = new int[prices.length][3];
		return recur(prices, 2, 0, memo);
	}

	private int recur(int[] prices, int transaction, int index, int[][] memo) {
		if (index == prices.length || transaction == 0) {
			return 0;
		}

		if (memo[index][transaction] > 0) {
			return memo[index][transaction];
		}
		int include = 0;

		for (int i = index + 1; i < prices.length; i++) {

			if (prices[i] > prices[index]) {
				int profit = prices[i] - prices[index];
				int tempInclude = recur(prices, transaction - 1, i + 1, memo);
				include = Math.max(include, profit + tempInclude);
			}
		}
		int exclude = recur(prices, transaction, index + 1, memo);
		return memo[index][transaction] = Math.max(include, exclude);
	}

	// ============================================================================
	// Bottom up approach
	public int maxProfit1(int[] prices) {
		int[][] memo = new int[prices.length + 1][3];

		for (int index = prices.length - 1; index >= 0; index--) {
			for (int transaction = 1; transaction <= 2; transaction++) {
				int include = 0;

				for (int i = index + 1; i < prices.length; i++) {

					if (prices[i] > prices[index]) {
						int profit = prices[i] - prices[index];
						int tempInclude = memo[i + 1][transaction - 1];
						include = Math.max(include, profit + tempInclude);
					}
				}
				int exclude = recur(prices, transaction, index + 1, memo);
				memo[index][transaction] = Math.max(include, exclude);
			}
		}
		return memo[0][2];
	}

	// =============================================================================
	// Another Top down approach
	public int maxProfit2(int[] prices) {
		int[][] memo = new int[2][prices.length];
		for (int[] mem : memo)
			Arrays.fill(mem, -1);
		return dfs(2, prices, 0, memo);
	}

	private int dfs(int k, int[] prices, int d, int[][] memo) {
		if (k == 0 || d > prices.length - k)
			return 0;
		if (memo[k - 1][d] != -1)
			return memo[k - 1][d];

		int min = Integer.MAX_VALUE;
		int max = 0;
		for (int i = d; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			max = Math.max(max, prices[i] - min + dfs(k - 1, prices, i + 1, memo));
		}

		return memo[k - 1][d] = max;
	}

	// ==========================================================================
	// Best solution
	public int maxProfit3(int[] prices) {
		int[] leftProfit = new int[prices.length];
		int leftMin = prices[0];

		for (int i = 1; i < prices.length; i++) {
			leftMin = Math.min(leftMin, prices[i]);
			// Checking if last profit was greater or current profit
			leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - leftMin);
		}
		// assigning one more for convenience in future calculation i + 1
		int[] rightProfit = new int[prices.length + 1];
		int rightMax = prices[prices.length - 1];

		for (int i = prices.length - 1; i >= 0; i--) {
			rightMax = Math.max(rightMax, prices[i]);
			// checking if this profit or next profit is greater
			rightProfit[i] = Math.max(rightProfit[i + 1], rightMax - prices[i]);
		}
		int ans = 0;

		for (int i = 0; i < prices.length; i++) {
			ans = Math.max(ans, leftProfit[i] + rightProfit[i + 1]);
		}
		return ans;
	}
	//=============================================================================================
	//State Machine working Approach Bottom up and top down
	public int maxProfit4(int[] prices) {
		int[][][] memo = new int[3][prices.length + 1][2];

		for (int t = 1; t <= 2; t++) {

			for (int index = prices.length - 1; index >= 0; index--) {

				for (int op = 1; op >= 0; op--) {
					int ans = 0;

					if (op == 0) {
						int val = memo[t][index + 1][op ^ 1] - prices[index];
						ans = Math.max(ans, val);
					} else {
						int val = memo[t - 1][index + 1][op ^ 1] + prices[index];
						ans = Math.max(ans, val);
					}
					ans = Math.max(ans, memo[t][index + 1][op]);
					memo[t][index][op] = ans;
				}
			}
		}
		return memo[2][0][0];
	}

	private int recur(int[] prices, int t, int index, int op, int[][][] memo) {

		if (t == 0 || index == prices.length) {
			return 0;
		}

		if (memo[t][index][op] > 0) {
			return memo[t][index][op];
		}
		int ans = 0;

		if (op == 0) {
			int val = recur(prices, t, index + 1, op ^ 1,memo) - prices[index];
			ans = Math.max(ans, val);
		} else {
			int val = recur(prices, t - 1, index + 1, op ^ 1, memo) + prices[index];
			ans = Math.max(ans, val);
		}
		ans = Math.max(ans, recur(prices, t, index + 1, op, memo));
		return memo[t][index][op] = ans;
	}

}
