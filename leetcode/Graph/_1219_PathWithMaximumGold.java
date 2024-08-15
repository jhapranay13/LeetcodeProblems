package leetcode.Graph;

/**
 * 
 * @author Pranay Jha
 *
 *         In a gold mine grid of size m x n, each cell in this mine has an
 *         integer representing the amount of gold in that cell, 0 if it is
 *         empty.
 * 
 *         Return the maximum amount of gold you can collect under the
 *         conditions:
 * 
 *         Every time you are located in a cell you will collect all the gold in
 *         that cell. From your position, you can walk one step to the left,
 *         right, up, or down. You can't visit the same cell more than once.
 *         Never visit a cell with 0 gold. You can start and stop collecting
 *         gold from any position in the grid that has some gold.
 * 
 * 
 *         Example 1:
 * 
 *         Input: grid = [[0,6,0],[5,8,7],[0,9,0]] Output: 24 Explanation:
 *         [[0,6,0], [5,8,7], [0,9,0]] Path to get the maximum gold, 9 -> 8 ->
 *         7.
 * 
 *         Example 2:
 * 
 *         Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]] Output: 28
 *         Explanation: [[1,0,7], [2,0,6], [3,4,5], [0,3,0], [9,0,20]] Path to
 *         get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 * 
 * 
 *         Constraints:
 * 
 *         m == grid.length n == grid[i].length 1 <= m, n <= 15 0 <= grid[i][j]
 *         <= 100 There are at most 25 cells containing gold.
 *
 *
 */

public class _1219_PathWithMaximumGold {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int getMaximumGold(int[][] grid) {
		int ans = Integer.MIN_VALUE;
		boolean[][] visited = new boolean[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] != 0) {
					int temp = dfs(i, j, visited, grid);
					ans = Math.max(temp, ans);
				} else {
					visited[i][j] = true;
				}
			}
		}
		return ans;
	}

	private int dfs(int r, int c, boolean[][] visited, int[][] grid) {
		visited[r][c] = true;
		int ans = grid[r][c];
		int temp = 0;

		for (int[] dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];

			if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length || visited[nr][nc] || grid[nr][nc] == 0) {
				continue;
			}
			temp = Math.max(temp, dfs(nr, nc, visited, grid));
		}
		visited[r][c] = false;
		return ans + temp;
	}

	private int dirs[][] = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
}
