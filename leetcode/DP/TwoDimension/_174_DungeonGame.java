package leetcode.DP.TwoDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         The demons had captured the princess and imprisoned her in the
 *         bottom-right corner of a dungeon. The dungeon consists of m x n rooms
 *         laid out in a 2D grid. Our valiant knight was initially positioned in
 *         the top-left room and must fight his way through dungeon to rescue
 *         the princess.
 * 
 *         The knight has an initial health point represented by a positive
 *         integer. If at any point his health point drops to 0 or below, he
 *         dies immediately.
 * 
 *         Some of the rooms are guarded by demons (represented by negative
 *         integers), so the knight loses health upon entering these rooms;
 *         other rooms are either empty (represented as 0) or contain magic orbs
 *         that increase the knight's health (represented by positive integers).
 * 
 *         To reach the princess as quickly as possible, the knight decides to
 *         move only rightward or downward in each step.
 * 
 *         Return the knight's minimum initial health so that he can rescue the
 *         princess.
 * 
 *         Note that any room can contain threats or power-ups, even the first
 *         room the knight enters and the bottom-right room where the princess
 *         is imprisoned.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]] Output: 7
 *         Explanation: The initial health of the knight must be at least 7 if
 *         he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
 * 
 *         Example 2:
 * 
 *         Input: dungeon = [[0]] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         m == dungeon.length n == dungeon[i].length 1 <= m, n <= 200 -1000 <=
 *         dungeon[i][j] <= 1000
 *
 */

public class _174_DungeonGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// Top Down Approach
	public int calculateMinimumHP(int[][] dungeon) {
		int[][] memo = new int[dungeon.length][dungeon[0].length];
		return recur(dungeon, 0, 0, memo);
	}

	private int recur(int[][] dungeon, int r, int c, int[][] memo) {
		if (r == dungeon.length - 1 && c == dungeon[0].length - 1) {
			return dungeon[r][c] > 0 ? 1 : -dungeon[r][c] + 1;
		}

		if (r >= dungeon.length || c >= dungeon[0].length) {
			return Integer.MAX_VALUE;
		}

		if (memo[r][c] > 0) {
			return memo[r][c];
		}
		// Since life cannot be -ve we make it as one
		int nrVal = recur(dungeon, r + 1, c, memo);
		int ncVal = recur(dungeon, r, c + 1, memo);
		int ans = Math.min(nrVal, ncVal) - dungeon[r][c];
		return memo[r][c] = ans <= 0 ? 1 : ans;
	}

	// ===========================================================================
	// Bottom up approach
	public int calculateMinimumHP1(int[][] dungeon) {
		int[][] memo = new int[dungeon.length][dungeon[0].length];

		for (int r = dungeon.length - 1; r >= 0; r--) {
			for (int c = dungeon[0].length - 1; c >= 0; c--) {

				if (r == dungeon.length - 1 && c == dungeon[0].length - 1) {
					memo[r][c] = dungeon[r][c] > 0 ? 1 : -dungeon[r][c] + 1;
					continue;
				}

				int nrVal = r + 1 >= dungeon.length ? Integer.MAX_VALUE : memo[r + 1][c];
				int ncVal = c + 1 >= dungeon[0].length ? Integer.MAX_VALUE : memo[r][c + 1];
				int ans = Math.min(nrVal, ncVal) - dungeon[r][c];
				memo[r][c] = ans <= 0 ? 1 : ans;
			}
		}
		return memo[0][0];
	}
}
