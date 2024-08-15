package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an m x n integer array grid where grid[i][j] could be:
 * 
 *         1 representing the starting square. There is exactly one starting
 *         square. 2 representing the ending square. There is exactly one ending
 *         square. 0 representing empty squares we can walk over. -1
 *         representing obstacles that we cannot walk over. Return the number of
 *         4-directional walks from the starting square to the ending square,
 *         that walk over every non-obstacle square exactly once.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]] Output: 2 Explanation:
 *         We have the following two paths: 1.
 *         (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2) 2.
 *         (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * 
 *         Example 2:
 * 
 * 
 *         Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]] Output: 4 Explanation:
 *         We have the following four paths: 1.
 *         (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 *         2.
 *         (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 *         3.
 *         (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 *         4.
 *         (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * 
 *         Example 3:
 * 
 * 
 *         Input: grid = [[0,1],[2,0]] Output: 0 Explanation: There is no path
 *         that walks over every empty square exactly once. Note that the
 *         starting and ending square can be anywhere in the grid.
 * 
 * 
 *         Constraints:
 * 
 *         m == grid.length n == grid[i].length 1 <= m, n <= 20 1 <= m * n <= 20
 *         -1 <= grid[i][j] <= 2 There is exactly one starting cell and one
 *         ending cell.
 *
 */

public class _980_UniquePaths3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int uniquePathsIII(int[][] grid) {
		int sr = -1;
		int sc = -1;
		int er = -1;
		int ec = -1;
		int validCount = 0;//Since we have to traverse every point exactly once we are keeping count of
		//number of valid points;
		int[][] v = new int[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[0].length; j++) {
				int val = grid[i][j];

				if (val == 1) {
					sr = i;
					sc = j;
				}

				if (val == 2) {
					er = i;
					ec = j;
				}

				if (val != -1) {
					validCount++;
				}

				if (val == -1) {
					v[i][j] = 1;
				}
			}
		}
		int ans = dfs(grid, sr, sc, er, ec, validCount, 1, v);
		return ans;
	}

	private int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public int dfs(int[][] grid, int sr, int sc, int er, int ec, int validCount, int step, int[][] v) {
		//ans is one only if we traverse all the points else is zero
		if (sc == ec && sr == er && validCount == step) {
			return 1;
		}

		if (sc == ec && sr == er) {
			return 0;
		}
		int ans = 0;
		v[sr][sc] = 1;

		for (int[] dir : dirs) {
			int nr = sr + dir[0];
			int nc = sc + dir[1];

			if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || v[nr][nc] == 1) {
				continue;
			}
			ans += dfs(grid, nr, nc, er, ec, validCount, step + 1, v);
		}
		v[sr][sc] = 0;
		return ans;
	}
}
