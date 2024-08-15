package leetcode.DP.TwoDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         There is a row of n houses, where each house can be painted one of
 *         three colors: red, blue, or green. The cost of painting each house
 *         with a certain color is different. You have to paint all the houses
 *         such that no two adjacent houses have the same color.
 * 
 *         The cost of painting each house with a certain color is represented
 *         by an n x 3 cost matrix costs.
 * 
 *         For example, costs[0][0] is the cost of painting house 0 with the
 *         color red; costs[1][2] is the cost of painting house 1 with color
 *         green, and so on... Return the minimum cost to paint all houses.
 * 
 *         Example 1:
 * 
 *         Input: costs = [[17,2,17],[16,16,5],[14,3,19]] Output: 10
 *         Explanation: Paint house 0 into blue, paint house 1 into green, paint
 *         house 2 into blue. Minimum cost: 2 + 5 + 3 = 10. 
 *         
 *         Example 2:
 * 
 *         Input: costs = [[7,6,2]] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         costs.length == n costs[i].length == 3 1 <= n <= 100 1 <= costs[i][j]
 *         <= 20
 *
 */

public class _256_PaintAHouse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down DP approach
	public int minCost(int[][] costs) {
		int cost = Integer.MAX_VALUE;
		int[][] memo = new int[costs.length][3];

		//0, 1 and 3 Represents 3 colors
		for (int i = 0; i < 3; i++) {
			cost = Math.min(cost, recur(costs, 0, i, memo));
		}
		return cost;
	}

	public int recur(int[][] costs, int index, int color, int[][] memo) {

		if (index == costs.length) {
			return 0;
		}

		if (memo[index][color] > 0) {
			return memo[index][color];
		}
		int cost = Integer.MAX_VALUE;

		switch(color) {
		case 0 : {
			cost = costs[index][color] + Math.min(recur(costs, index + 1, 1, memo),
					recur(costs, index + 1, 2, memo));
		};break;
		case 1 : {
			cost = costs[index][color] + Math.min(recur(costs, index + 1, 0, memo),
					recur(costs, index + 1, 2, memo));
		};break;
		case 2 : {
			cost = costs[index][color] + Math.min(recur(costs, index + 1, 1, memo),
					recur(costs, index + 1, 0, memo));
		};break;                                          
		}
		return memo[index][color] = cost;
	}
	//=============================================================================
	//Bottom up DP approach
	public int minCost1(int[][] costs) {

		if (costs == null || costs.length == 0) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		int memo[][] = new int[costs.length + 1][3];


		for (int r = costs.length - 1; r >= 0; r--) {
			memo[r][0] = costs[r][0] + Math.min(memo[r + 1][1], memo[r + 1][2]);
			memo[r][1] = costs[r][1] + Math.min(memo[r + 1][0], memo[r + 1][2]);
			memo[r][2] = costs[r][2] + Math.min(memo[r + 1][0], memo[r + 1][1]);
		}
		return Math.min(memo[0][0], Math.min(memo[0][1], memo[0][2]));
	}
}
