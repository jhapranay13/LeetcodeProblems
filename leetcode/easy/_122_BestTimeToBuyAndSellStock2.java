package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e., max profit = 0.

Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
 *
 */

public class _122_BestTimeToBuyAndSellStock2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//==============================================================================
	//Top Down approach
	public int maxProfit(int[] prices) {
		int[] memo = new int[prices.length];
		return recur(prices, 0, memo);
	}

	private int recur(int[] prices, int index, int[] memo) {

		if(index == prices.length) {
			return 0;
		}

		if (memo[index] >  0) {
			return memo[index];
		}
		int indexVal = prices[index];
		int maxProfitInc = 0;

		for (int i = index + 1; i < prices.length; i++) {

			if(prices[i] > indexVal) {
				int currProfit = prices[i] - indexVal;
				maxProfitInc = Math.max(maxProfitInc, currProfit + 
						recur(prices, i + 1, memo));
			}
		}
		int maxProfitExc = recur(prices, index + 1, memo);
		return memo[index] = Math.max(maxProfitInc, maxProfitExc);
	}
	//==============================================================================
	//Bottom Up DP approach
	public int maxProfit1(int[] prices) {
		int[] memo = new int[prices.length + 1];

		for (int index = prices.length - 1; index >= 0; index--) {
			int indexVal = prices[index];
			int maxProfitInc = 0;

			for (int i = index + 1; i < prices.length; i++) {

				if(prices[i] > indexVal) {
					int currProfit = prices[i] - indexVal;
					maxProfitInc = Math.max(maxProfitInc, currProfit + 
							memo[i + 1]);
				}
			}
			int maxProfitExc = memo[index + 1];
			memo[index] = Math.max(maxProfitInc, maxProfitExc);    
		}
		return memo[0];
	}
}
