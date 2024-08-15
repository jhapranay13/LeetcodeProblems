package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array coins representing coins of different
 *         denominations and an integer amount representing a total amount of
 *         money.
 * 
 *         Return the fewest number of coins that you need to make up that
 *         amount. If that amount of money cannot be made up by any combination
 *         of the coins, return -1.
 * 
 *         You may assume that you have an infinite number of each kind of coin.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: coins = [1,2,5], amount = 11 Output: 3 Explanation: 11 = 5 + 5
 *         + 1 
 *         
 *         Example 2:
 * 
 *         Input: coins = [2], amount = 3 Output: -1 
 *         
 *         Example 3:
 * 
 *         Input: coins = [1], amount = 0 Output: 0 
 *         
 *         Example 4:
 * 
 *         Input: coins = [1], amount = 1 Output: 1 
 *         
 *         Example 5:
 * 
 *         Input: coins = [1], amount = 2 Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= coins.length <= 12 1 <= coins[i] <= 231 - 1 0 <= amount <= 104
 *
 */

public class _322_CoinChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int coinChange(int[] coins, int amount) {
		int[] memo = new int[amount + 1];
		int ans = recur(coins, amount, memo);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public int recur(int[] coins, int amount, int[] memo) {

		if (amount == 0) {
			return 0;
		}

		if (amount < 0) {
			return Integer.MAX_VALUE;
		}

		if (memo[amount] > 0) {
			return memo[amount];
		}
		int ans = Integer.MAX_VALUE;

		for (int coin : coins) {
			int number = recur(coins, amount - coin, memo);

			if (number < Integer.MAX_VALUE) {
				number++;
			}
			ans = Math.min(ans, number);        

		}
		return memo[amount] = ans;
	}
	//=============================================================================
	//Bottom up approach
	public int coinChange1(int[] coins, int amountParam) {
		int[] memo = new int[amountParam + 1];

		for (int amount = 1; amount <= amountParam; amount++) {
			int ans = Integer.MAX_VALUE;

			for (int coin : coins) {

				if(amount - coin < 0) {
					continue;
				}
				int number = memo[amount - coin];

				if (number < Integer.MAX_VALUE) {
					number++;
				}
				ans = Math.min(ans, number);        

			}
			memo[amount] = ans;    
		}
		return memo[amountParam] == Integer.MAX_VALUE ? -1 : memo[amountParam];
	}
}
