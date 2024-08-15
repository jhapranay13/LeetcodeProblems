package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an m x n binary matrix grid. An island is a group of
 *         1's (representing land) connected 4-directionally (horizontal or
 *         vertical.) You may assume all four edges of the grid are surrounded
 *         by water.
 * 
 *         The area of an island is the number of cells with a value 1 in the
 *         island.
 * 
 *         Return the maximum area of an island in grid. If there is no island,
 *         return 0.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: grid =
 *         [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *         Output: 6 Explanation: The answer is not 11, because the island must
 *         be connected 4-directionally. Example 2:
 * 
 *         Input: grid = [[0,0,0,0,0,0,0,0]] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         m == grid.length n == grid[i].length 1 <= m, n <= 50 grid[i][j] is
 *         either 0 or 1.
 *
 */

public class _695_MaxAreaOfTheIsland {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxAreaOfIsland(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int ans = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					ans = Math.max(ans, dfs(grid, visited, i, j));
				}
			}
		}
		return ans;
	}

	private int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private int dfs(int[][] grid, boolean[][] visited, int row, int col) {
		int ans = 1;
		visited[row][col] = true;

		for (int[] dir : dirs) {
			int nr = dir[0] + row;
			int nc = dir[1] + col;

			if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length || grid[nr][nc] == 0 || visited[nr][nc]) {
				continue;
			}
			ans += dfs(grid, visited, nr, nc);
		}
		return ans;
	}
}
