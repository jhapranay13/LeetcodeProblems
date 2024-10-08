package leetcode.medium;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You have d dice and each die has f faces numbered 1, 2, ..., f.
 * 
 *         Return the number of possible ways (out of fd total ways) modulo 109
 *         + 7 to roll the dice so the sum of the face-up numbers equals target.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: d = 1, f = 6, target = 3 Output: 1 Explanation: You throw one
 *         die with 6 faces. There is only one way to get a sum of 3. Example 2:
 * 
 *         Input: d = 2, f = 6, target = 7 Output: 6 Explanation: You throw two
 *         dice, each with 6 faces. There are 6 ways to get a sum of 7: 1+6,
 *         2+5, 3+4, 4+3, 5+2, 6+1. Example 3:
 * 
 *         Input: d = 2, f = 5, target = 10 Output: 1 Explanation: You throw two
 *         dice, each with 5 faces. There is only one way to get a sum of 10:
 *         5+5. Example 4:
 * 
 *         Input: d = 1, f = 2, target = 3 Output: 0 Explanation: You throw one
 *         die with 2 faces. There is no way to get a sum of 3. Example 5:
 * 
 *         Input: d = 30, f = 30, target = 500 Output: 222616187 Explanation:
 *         The answer must be returned modulo 10^9 + 7.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= d, f <= 30 1 <= target <= 1000
 *
 */

public class _1155_NumberberOfDiceRollsWithTargetSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach
	private static final int MOD = (int) 1e9 + 7;

	public int numRollsToTarget(int D, int f, int target) {
		int memo[][] = new int[D + 1][target + 1];

		for (int[] arr : memo) {
			Arrays.fill(arr, -1);
		}
		int ways = dfs(D, f, target, memo);
		return ways;
	}

	public int dfs(int diceCount, int faces, int target, int[][] memo) {

		if (target < 0 || diceCount < 0) {
			return 0;
		}

		if (memo[diceCount][target] > -1) {
			return memo[diceCount][target];
		}

		if (diceCount == 0 && target > 0) {
			return 0;
		}

		if (diceCount == 0 && target == 0) {
			return 1;
		}
		long answer = 0;
		for (int i = 1; i <= faces; i++) {
			answer += dfs(diceCount - 1, faces, target - i, memo);
		}

		return memo[diceCount][target] = (int) (answer % MOD);
	}

	// =============================================================================
	// Bottom Up approach
	public int numRollsToTarget1(int D, int f, int target) {
		int memo[][] = new int[D + 1][target + 1];
		memo[0][0] = 1;

		for (int d = 1; d <= D; d++) {

			for (int t = 1; t <= target; t++) {
				long answer = 0;

				for (int i = 1; i <= f; i++) {

					if (t - i < 0) {
						continue;
					}
					answer += memo[d - 1][t - i];
				}
				memo[d][t] = (int) (answer % MOD);
			}
		}
		int ways = memo[D][target];
		return ways;
	}
}
