package leetcode.medium;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array prices where prices[i] is the price of a given
 *         stock on the ith day.
 * 
 *         Find the maximum profit you can achieve. You may complete as many
 *         transactions as you like (i.e., buy one and sell one share of the
 *         stock multiple times) with the following restrictions:
 * 
 *         After you sell your stock, you cannot buy stock on the next day
 *         (i.e., cooldown one day). Note: You may not engage in multiple
 *         transactions simultaneously (i.e., you must sell the stock before you
 *         buy again).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: prices = [1,2,3,0,2] Output: 3 Explanation: transactions =
 *         [buy, sell, cooldown, buy, sell]
 * 
 *         Example 2:
 * 
 *         Input: prices = [1] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= prices.length <= 5000 0 <= prices[i] <= 1000
 *
 */

public class _309_BestTimeToBuyAndSellStockWithCooldown {

	public static void main(String args[]) {

	}

	// =============================================================================
	// Top Down Approach
	public int maxProfit(int[] prices) {

		if (prices == null || prices.length == 0) {
			return 0;
		}
		int[] memo = new int[prices.length];
		return recur(prices, 0, memo);
	}

	private int recur(int[] prices, int index, int[] memo) {

		if (index >= prices.length) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int include = 0;

		for (int i = index + 1; i < prices.length; i++) {

			if (prices[i] > prices[index]) {
				int profit = prices[i] - prices[index];
				int nextProfit = recur(prices, i + 2, memo);
				include = Math.max(include, profit + nextProfit);
			}
		}
		int exclude = recur(prices, index + 1, memo);
		return memo[index] = Math.max(include, exclude);
	}

	// ==============================================================================
	// Bottom Up approach
	public int maxProfit1(int[] prices) {

		if (prices == null || prices.length <= 1) {
			return 0;
		}
		int[] memo = new int[prices.length + 2];

		for (int index = prices.length - 1; index >= 0; index--) {
			int include = 0;

			for (int i = index + 1; i < prices.length; i++) {

				if (prices[i] > prices[index]) {
					int profit = prices[i] - prices[index];
					int nextProfit = memo[index + 2];
					include = Math.max(include, profit + memo[i + 2]);
				}
			}
			int exclude = memo[index + 1];
			memo[index] = Math.max(include, exclude);
		}
		return memo[0];
	}

	// ==========================================================================
	// Faster DP
	public int maxProfit2(int[] prices) {
		Integer[][] mem = new Integer[prices.length][prices.length];

		return maxProfitUtil(prices, 0, 0, mem);
	}

	// buy 0, sell 1, next buy d+2
	// start with buy
	// start with day 0

	int maxProfitUtil(int[] prices, int buyOrSell, int day, Integer[][] mem) {
		if (day >= prices.length)
			return 0;

		if (mem[day][buyOrSell] != null)
			return mem[day][buyOrSell];

		Integer profit = Integer.MIN_VALUE;

		if (buyOrSell == 0) {
			int buy = maxProfitUtil(prices, 1, day + 1, mem) - prices[day];
			int noBuy = maxProfitUtil(prices, 0, day + 1, mem);

			profit = Math.max(profit, Math.max(buy, noBuy));
		} else if (buyOrSell == 1) {
			int sell = maxProfitUtil(prices, 0, day + 2, mem) + prices[day];
			int noSell = maxProfitUtil(prices, 1, day + 1, mem);

			profit = Math.max(profit, Math.max(sell, noSell));
		}

		return mem[day][buyOrSell] = profit;
	}

	// ============================================================================
	// Another way to manage state
	public int maxProfi3t(int[] prices) {
		int n = prices.length;
		int[][] memo = new int[3][n + 1];
		for (int[] m : memo)
			Arrays.fill(m, -1);
		return findProfit(memo, prices, n, 0, 0);
	}

	int findProfit(int[][] memo, int[] prices, int n, int indx, int state) {
		if (memo[state][indx] != -1) {
			return memo[state][indx];
		}
		if (indx == n) {
			return memo[state][indx] = 0;
		}
		int max = 0;

		if (state == 0) { // buy or skip
			int buy = findProfit(memo, prices, n, indx + 1, 1) - prices[indx]; // next state will be [sell or skip->1]
			int skip = findProfit(memo, prices, n, indx + 1, state);
			max = Math.max(skip, buy);
		} else if (state == 1) { // sell or skip
			int sell = findProfit(memo, prices, n, indx + 1, 2) + prices[indx]; // next state will be [skip->2]
			int skip = findProfit(memo, prices, n, indx + 1, state);
			max = Math.max(skip, sell);
		} else {// skip
			int skip = findProfit(memo, prices, n, indx + 1, 0); // next state will be [buy or skip]
			max = skip;
		}
		return memo[state][indx] = max;
	}
	//=============================================================================================
	//Another approach
	public int maxProfit3(int[] prices) {
		int[][] memo = new int[2][prices.length];
		return recur4(prices, 0, 0, memo);
	}

	private int recur4(int[] prices, int state, int index, int[][] memo) {

		if (index >= prices.length) {
			return 0;
		}

		if (memo[state][index] > 0) {
			return memo[state][index];
		}
		int inc = 0;

		if (state == 0) {
			inc = recur4(prices, state ^ 1, index + 1, memo) - prices[index];
		} else {
			inc = recur4(prices, state ^ 1, index + 2, memo) + prices[index];
		}
		int exc = recur4(prices, state, index + 1, memo);
		return memo[state][index] = Math.max(inc, exc);
	}
}
