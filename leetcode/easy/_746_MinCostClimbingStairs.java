package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array cost where cost[i] is the cost of ith
 *         step on a staircase. Once you pay the cost, you can either climb one
 *         or two steps.
 * 
 *         You can either start from the step with index 0, or the step with
 *         index 1.
 * 
 *         Return the minimum cost to reach the top of the floor.
 * 
 *         Example 1:
 * 
 *         Input: cost = [10,15,20] Output: 15 Explanation: Cheapest is: start
 *         on cost[1], pay that cost, and go to the top.
 * 
 *         Example 2:
 * 
 *         Input: cost = [1,100,1,1,1,100,1,1,100,1] Output: 6 Explanation:
 *         Cheapest is: start on cost[0], and only step on 1s, skipping cost[3].
 * 
 * 
 *         Constraints:
 * 
 *         2 <= cost.length <= 1000 0 <= cost[i] <= 999
 *
 */

public class _746_MinCostClimbingStairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach
	public int minCostClimbingStairs(int[] cost) {
		int[] memoZero = new int[cost.length];
		int startZero = recur(cost, 0, memoZero);
		int[] memoOne = new int[cost.length];
		int startOne = recur(cost, 1, memoOne);
		return Math.min(startZero, startOne);
	}

	private int recur(int[] cost, int index, int[] memo) {
		if (index >= cost.length) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int currCost = cost[index];
		int nextCost = Math.min(recur(cost, index + 1, memo), recur(cost, index + 2, memo));
		return memo[index] = nextCost + currCost;
	}
	//=============================================================================
	//Bottom up approach
	public int minCostClimbingStairs1(int[] cost) {
        int[] memoZero = new int[cost.length + 2];
        int[] memoOne = new int[cost.length + 2];
        
        for (int index = cost.length - 1; index >= 0; index--) {
            int currCost = cost[index];
            int nextCost = Math.min(memoZero[index + 1], memoZero[index + 2]);
            memoZero[index] = nextCost + currCost;
        }
        
        for (int index = cost.length - 1; index >= 1; index--) {
            int currCost = cost[index];
            int nextCost = Math.min(memoOne[index + 1], memoOne[index + 2]);
            memoOne[index] = nextCost + currCost;
        }
        return Math.min(memoZero[0], memoOne[1]);
    }
}
