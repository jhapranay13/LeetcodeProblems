package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array coins representing coins of different
 *         denominations and an integer amount representing a total amount of
 *         money.
 * 
 *         Return the number of combinations that make up that amount. If that
 *         amount of money cannot be made up by any combination of the coins,
 *         return 0.
 * 
 *         You may assume that you have an infinite number of each kind of coin.
 * 
 *         The answer is guaranteed to fit into a signed 32-bit integer.
 * 
 *         Example 1:
 * 
 *         Input: amount = 5, coins = [1,2,5] Output: 4 Explanation: there are
 *         four ways to make up the amount: 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1
 *         
 *         Example 2:
 * 
 *         Input: amount = 3, coins = [2] Output: 0 Explanation: the amount of 3
 *         cannot be made up just with coins of 2. 
 *         
 *         Example 3:
 * 
 *         Input: amount = 10, coins = [10] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= coins.length <= 300 1 <= coins[i] <= 5000 All the values of
 *         coins are unique. 0 <= amount <= 5000
 *
 */

public class _518_CoinChange2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top down approach
	public int change1(int amount, int[] coins) {
        int[][] memo = new int[coins.length][amount];
        return recur(amount, coins, 0, 0, memo);
    }
    
    private int recur(int amount, int[] coins, int index, int sum, int[][] memo) {
        
        if (sum == amount) {
            return 1;
        }
        
        if (index >= coins.length || sum > amount) {
            return 0;
        }
        
        if (memo[index][sum] > 0) {
            return memo[index][sum];
        }
        int include = recur(amount, coins, index, sum + coins[index], memo);
        int exclude = recur(amount, coins, index + 1, sum, memo);
        return memo[index][sum] = include + exclude;
    }
	//=============================================================================
	//Bottom Up approach
	public int change(int amount, int[] coins) {
        int[][] memo = new int[coins.length + 1][amount + 1];
        
        for (int index = coins.length - 1; index >= 0; index--) {
            
            for (int sum = amount; sum >= 0; sum--) {
                
                if (sum == amount) {
                    memo[index][sum] = 1;
                    continue;
                }
                int include = sum + coins[index] <= amount ? 
                    memo[index][sum + coins[index]] : 0;
                int exclude = index + 1 <= coins.length ? memo[index + 1][sum] : 0;
                memo[index][sum] = include + exclude;        
            }
        }
        return memo[0][0];
    }
}
