package leetcode.DP.TwoDimension;

import java.util.List;

/**
 *
 *
 * There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.
 *
 * In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
 *
 * Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: piles = [[1,100,3],[7,8,9]], k = 2
 * Output: 101
 * Explanation:
 * The above diagram shows the different ways we can choose k coins.
 * The maximum total we can obtain is 101.
 * Example 2:
 *
 * Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 * Output: 706
 * Explanation:
 * The maximum total can be obtained if we choose all coins from the last pile.
 *
 *
 * Constraints:
 *
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 105
 * 1 <= k <= sum(piles[i].length) <= 2000
 *
 */

public class _2218_Maximum_Value_of_K_Coins_From_Piles {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[][] memo = new int[k + 1][piles.size()];
        return recur(piles, k, 0, memo);
    }

    private int recur(List<List<Integer>> piles, int numberOfCoins, int index, int[][] memo) {

        if (numberOfCoins == 0 || index == piles.size()) {
            return 0;
        }

        if (memo[numberOfCoins][index] > 0) {
            return memo[numberOfCoins][index];
        }
        int exclude = recur(piles, numberOfCoins, index + 1, memo);
        List<Integer> pile = piles.get(index);
        int sum = 0;
        int include = 0;
        int nextCoinNum = numberOfCoins;

        for (int coin : pile) {
            sum += coin;

            if (nextCoinNum == 0) {
                break;
            }
            nextCoinNum--;
            include = Math.max(include, sum + recur(piles, nextCoinNum, index + 1, memo));
        }
        return memo[numberOfCoins][index] = Math.max(include, exclude);
    }
}
