package leetcode.DP.TwoDimension;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         There is an m x n grid with a ball. The ball is initially at the
 *         position [startRow, startColumn]. You are allowed to move the ball to
 *         one of the four adjacent cells in the grid (possibly out of the grid
 *         crossing the grid boundary). You can apply at most maxMove moves to
 *         the ball.
 * 
 *         Given the five integers m, n, maxMove, startRow, startColumn, return
 *         the number of paths to move the ball out of the grid boundary. Since
 *         the answer can be very large, return it modulo 109 + 7.
 * 
 * 
 * 
 *         Example 1:
 * 			 _________		
 * 			|_O__|____|
 * 			|____|____|
 *    		
 * 
 *         Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 *         Output: 6 
 *         
 *         Example 2:    _______________
 * 						|____|_O__|_____|     
 * 
 *         Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 *         Output: 12
 * 
 * 
 *         Constraints:
 * 
 *         1 <= m, n <= 50 0 <= maxMove <= 50 0 <= startRow < m 0 <= startColumn
 *         < n
 *
 */

public class _576_OutOfBoundryPaths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int findPaths(int m, int n, int N, int i, int j) {
		int[][][] memo = new int[m][n][N + 1];

		for (int[][] l : memo) 
			for (int[] sl : l) 
				Arrays.fill(sl, -1);
		int paths = dfs(m, n, N, i, j, memo);
		return paths;
	}
	private int M = 1000000007;

	public int dfs(int m, int n, int N, int i, int j, int[][][] memo) {

		if (i < 0 || j < 0 || i == m || j == n) {
			return 1;
		}

		if (N == 0) {
			return 0;
		}

		if (memo[i][j][N] >= 0) {
			return memo[i][j][N];
		}
		memo[i][j][N] = (
				(dfs(m, n, N - 1, i - 1, j, memo) + 
						dfs(m, n, N - 1, i + 1, j, memo)) % M +
				(dfs(m, n, N - 1, i, j - 1, memo) + 
						dfs(m, n, N - 1, i, j + 1, memo)) % M
				) % M;
		return memo[i][j][N];
	}
	//===============================================================================
	//Bottom up solution
	public int findPaths1(int m, int n, int N, int x, int y) {
		int M = 1000000000 + 7;
		int dp[][] = new int[m][n];
		dp[x][y] = 1;
		int count = 0;
		for (int moves = 1; moves <= N; moves++) {
			int[][] temp = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (i == m - 1) count = (count + dp[i][j]) % M;
					if (j == n - 1) count = (count + dp[i][j]) % M;
					if (i == 0) count = (count + dp[i][j]) % M;
					if (j == 0) count = (count + dp[i][j]) % M;
					temp[i][j] = (
							((i > 0 ? dp[i - 1][j] : 0) + (i < m - 1 ? dp[i + 1][j] : 0)) % M +
							((j > 0 ? dp[i][j - 1] : 0) + (j < n - 1 ? dp[i][j + 1] : 0)) % M
							) % M;
				}
			}
			dp = temp;
		}
		return count;
	}
}
