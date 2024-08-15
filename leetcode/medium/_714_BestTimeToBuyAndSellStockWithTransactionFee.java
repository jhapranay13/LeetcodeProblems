package leetcode.medium;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array prices where prices[i] is the price of a given
 *         stock on the ith day, and an integer fee representing a transaction
 *         fee.
 * 
 *         Find the maximum profit you can achieve. You may complete as many
 *         transactions as you like, but you need to pay the transaction fee for
 *         each transaction.
 * 
 *         Note: You may not engage in multiple transactions simultaneously
 *         (i.e., you must sell the stock before you buy again).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: prices = [1,3,2,8,4,9], fee = 2 Output: 8 Explanation: The
 *         maximum profit can be achieved by: - Buying at prices[0] = 1 -
 *         Selling at prices[3] = 8 - Buying at prices[4] = 4 - Selling at
 *         prices[5] = 9 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *         
 *         Example 2:
 * 
 *         Input: prices = [1,3,7,5,10,3], fee = 3 Output: 6
 * 
 * 
 *         Constraints:
 * 
 *         1 <= prices.length <= 5 * 104 1 <= prices[i] < 5 * 104 0 <= fee < 5 *
 *         104
 *
 * 
 */

public class _714_BestTimeToBuyAndSellStockWithTransactionFee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxProfit(int[] prices, int fee) {
		if (prices == null || prices.length == 0)
			return 0;
		int[] buy = new int[prices.length];
		int[] sell = new int[prices.length];
		buy[0] = -prices[0];
		// if we buy on the first day the price will be substracted from our cash
		// above statement is like that.
		for (int i = 1; i < prices.length; i++) {
			// If we buy at ith day we are selling on the day before that
			buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
			// if we are seeling on that day we have bought it on any or previous days
			sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
		}
		return sell[prices.length - 1];
	}
	//=============================================================================================
	//Top Down TLE
	public int maxProfit1(int[] prices, int fee) {
		int[] memo = new int[ prices.length ];
		Arrays.fill( memo, -1 );
		int max = dfs( prices, fee, 0, memo );
		return max;
	}

	private int dfs( int[] p, int f, int i, int[] memo ) {

		if( i >= p.length ) {
			return 0;
		}

		if( memo[ i ] > -1 ) {
			return memo[ i ];
		}
		int max = 0;

		for( int j = i; j < p.length; j++ ) {

			if( p[ j ] - p[ i ] - f > 0 ) {
				int temp = dfs( p, f, j + 1, memo );
				max = Math.max( max, temp + p[ j ] - p[ i ] - f );
			}
		}
		int exclude = dfs( p, f, i + 1, memo );
		max = Math.max( max, exclude );
		return memo[ i ] = max;
	}
	//=============================================================================================
	//Bottom Up TLE
	public int maxProfit2(int[] p, int f) {
		int[] memo = new int[ p.length + 1 ];
		//int max = dfs( prices, fee, 0, memo );

		for( int i = p.length - 1; i >= 0; i-- ) {
			int max = 0;

			for( int j = i; j < p.length; j++ ) {

				if( p[ j ] - p[ i ] - f > 0 ) {
					int temp = memo[ j + 1 ];
					max = Math.max( max, temp + p[ j ] - p[ i ] - f );
				}
			}
			int exclude = memo[ i + 1 ];
			max = Math.max( max, exclude );
			memo[ i ] = max;
		}
		return memo[ 0 ];
	}
	//===========================================================================================
	//Top down Passes all test cases
	public int maxProfit3(int[] prices, int fee) {
		int [][] memo = new int[prices.length][2];
		return recur(prices, fee, 0, 0, memo);
	}

	private int recur(int[] prices, int fee, int buySellNothing, int index, int[][] memo) {

		if (index == prices.length) {
			return 0;
		}

		if (memo[index][buySellNothing] > 0) {
			return memo[index][buySellNothing];
		}
		int buyNothing = 0;
		int sellNothing = 0;

		if (buySellNothing == 0) {
			//subtracting from the profit since I am spending to buy
			buyNothing = recur(prices, fee, 1, index + 1, memo) - prices[index];
			int doNothing = recur(prices, fee, buySellNothing, index + 1, memo);
			buyNothing = Math.max(buyNothing, doNothing);
		} else {
			//adding to the profit - fee as I am gaining money by selling
			sellNothing = recur(prices, fee, 0, index + 1, memo) + prices[index] - fee;
			int doNothing = recur(prices, fee, buySellNothing, index + 1, memo);
			sellNothing = Math.max(sellNothing, doNothing);
		}
		return memo[index][buySellNothing] = Math.max(buyNothing, sellNothing);
	}
	//=============================================================================================
	//Bottom up that works
	public int maxProfit4(int[] prices, int fee) {
		int [][] memo = new int[prices.length + 1][2];

		for (int index = prices.length - 1; index >= 0; index--) {

			for (int buySellNothing = 0; buySellNothing <= 1; buySellNothing++) {
				int buyNothing = 0;
				int sellNothing = 0;

				if (buySellNothing == 0) {
					//subtracting from the profit since I am spending to buy
					buyNothing = memo[index + 1][1] - prices[index];
					int doNothing = memo[index + 1][buySellNothing];
					buyNothing = Math.max(buyNothing, doNothing);
				} else {
					//adding to the profit - fee as I am gaining money by selling
					sellNothing = memo[index + 1][0] + prices[index] - fee;
					int doNothing = memo[index + 1][buySellNothing];;
					sellNothing = Math.max(sellNothing, doNothing);
				}
				memo[index][buySellNothing] = Math.max(buyNothing, sellNothing);
			}
		}
		return memo[0][0];
	}
}
