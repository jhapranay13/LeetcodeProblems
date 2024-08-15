package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Return the minimum cost to paint all houses.
 *
 *
 *
 * Example 1:
 *
 * Input: costs = [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation:
 * Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 * Example 2:
 *
 * Input: costs = [[1,3],[2,4]]
 * Output: 5
 *
 *
 * Constraints:
 *
 * costs.length == n
 * costs[i].length == k
 * 1 <= n <= 100
 * 2 <= k <= 20
 * 1 <= costs[i][j] <= 20
 *
 */

public class _265_Paint_House_II {
    // Top down
    public int minCostII(int[][] costs) {
        int[][] memo = new int[costs.length][costs[0].length + 1];
        return recur(costs, 0, -1, memo);
    }

    private int recur(int[][] costs, int index, int prevCostIndex, int[][] memo) {

        if (index == costs.length) {
            return 0;
        }

        if (memo[index][prevCostIndex + 1] > 0) {
            return memo[index][prevCostIndex + 1];
        }
        int[] cost = costs[index];
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < cost.length; i++) {

            if (i != prevCostIndex) {
                ans = Math.min(ans, cost[i] + recur(costs, index + 1, i, memo));
            }
        }
        return memo[index][prevCostIndex + 1] = ans;
    }
    //=============================================================================================
    //Bottom up
    public int minCostII1(int[][] costs) {
        int[][] memo = new int[costs.length + 1][costs[0].length + 1];

        for (int index = costs.length - 1; index >= 0; index--) {

            for (int prevCostIndex = costs[0].length - 1; prevCostIndex >= 0; prevCostIndex--) {
                int[] cost = costs[index];
                int ans = Integer.MAX_VALUE;

                for (int i = 0; i < cost.length; i++) {

                    if (i != prevCostIndex) {
                        ans = Math.min(ans, cost[i] + memo[index + 1][i]);
                    }
                }
                memo[index][prevCostIndex] = ans;
            }
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < costs[0].length; i++) {
            ans = ans = Math.min(ans, memo[0][i]);
        }
        return ans;
    }
    //=============================================================================================
    //Other bottom up approach
    private int n;
    private int k;
    private int[][] costs;
    private Map<String, Integer> memo;

    public int minCostII2(int[][] costs) {
        if (costs.length == 0) return 0;
        this.k = costs[0].length;
        this.n = costs.length;
        this.costs = costs;
        this.memo = new HashMap<>();
        int minCost = Integer.MAX_VALUE;
        for (int color = 0; color < k; color++) {
            minCost = Math.min(minCost, memoSolve(0, color));
        }
        return minCost;
    }

    private int memoSolve(int houseNumber, int color) {

        // Base case: There are no more houses after this one.
        if (houseNumber == n - 1) {
            return costs[houseNumber][color];
        }

        // Memoization lookup case: Have we already solved this subproblem?
        if (memo.containsKey(getKey(houseNumber, color))) {
            return memo.get(getKey(houseNumber, color));
        }

        // Recursive case: Determine the minimum cost for the remainder.
        int minRemainingCost = Integer.MAX_VALUE;
        for (int nextColor = 0; nextColor < k; nextColor++) {
            if (color == nextColor) continue;
            int currentRemainingCost = memoSolve(houseNumber + 1, nextColor);
            minRemainingCost = Math.min(currentRemainingCost, minRemainingCost);
        }
        int totalCost = costs[houseNumber][color] + minRemainingCost;
        memo.put(getKey(houseNumber, color), totalCost);
        return totalCost;
    }

    // Convert a house number and color into a simple string key for the memo.
    private String getKey(int n, int color) {
        return String.valueOf(n) + " " + String.valueOf(color);
    }
}
