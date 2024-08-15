package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *         In an infinite chess board with coordinates from -infinity to
 *         +infinity, you have a knight at square [0, 0].
 * 
 *         A knight has 8 possible moves it can make, as illustrated below. Each
 *         move is two squares in a cardinal direction, then one square in an
 *         orthogonal direction.
 * 
 * 
 * 
 *         Return the minimum number of steps needed to move the knight to the
 *         square [x, y]. It is guaranteed the answer exists.
 * 
 *         Example 1:
 * 
 *         Input: x = 2, y = 1 Output: 1 Explanation: [0, 0] -> [2, 1]
 * 
 *         Example 2:
 * 
 *         Input: x = 5, y = 5 Output: 4 Explanation: [0, 0] -> [2, 1] -> [4, 2]
 *         -> [3, 4] -> [5, 5]
 * 
 * 
 *         Constraints:
 * 
 *         |x| + |y| <= 300
 *
 */

public class _1197_MinimumKnightMoves {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int minKnightMoves(int x, int y) {
		int memo[][] = new int[301][301];
		memo[0][0] = 0;
		memo[1][0] = 3;
		memo[0][1] = 3;
		memo[1][1] = 2;

		return minKnightMoves(x, y, memo);
	}
	//-ve values can be considered as mirror image of +ve ans can be treated the same
	public int minKnightMoves(int x, int y, int[][] memo) {

		if (x < 0 || y < 0) {
			return minKnightMoves(Math.abs(x), Math.abs(y), memo);
		}

		if (x == 0 && y == 0) {
			return 0;
		}

		if (memo[x][y] != 0) {
			return memo[x][y];
		}

		int min = 1 + Math.min(minKnightMoves(Math.abs(x - 1), Math.abs(y - 2), memo),
				minKnightMoves(Math.abs(x - 2), Math.abs(y - 1), memo));
		memo[x][y] = min;
		return min;
	}
}
