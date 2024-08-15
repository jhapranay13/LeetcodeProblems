package leetcode.ForBiginners.Graph.DFS.FromEachUnvisitedNode;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given a 2D grid consists of 0s (land) and 1s (water). An island is a
 *         maximal 4-directionally connected group of 0s and a closed island is
 *         an island totally (all left, top, right, bottom) surrounded by 1s.
 * 
 *         Return the number of closed islands.
 * 
 *         Example 1:
 * 
 *         Input: grid =
 *         [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 *         Output: 2 Explanation: Islands in gray are closed because they are
 *         completely surrounded by water (group of 1s). 
 *         
 *         Example 2:
 * 
 *         Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]] Output: 1 
 *         
 *         Example 3:
 * 
 *         Input: grid = [[1,1,1,1,1,1,1], [1,0,0,0,0,0,1], [1,0,1,1,1,0,1],
 *         [1,0,1,0,1,0,1], [1,0,1,1,1,0,1], [1,0,0,0,0,0,1], [1,1,1,1,1,1,1]]
 *         Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= grid.length, grid[0].length <= 100 0 <= grid[i][j] <=1
 *
 */

public class _1254_NumberOfClosedIslands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int closedIsland(int[][] grid) {
		boolean visited[][] = new boolean[grid.length][grid[0].length];
		int count = 0;

		for (int r = 1; r < grid.length - 1; r++) {
			for (int c = 1; c < grid[0].length - 1; c++) {

				if (grid[r][c] == 0 && !visited[r][c]) {

					if (dfs(grid, visited, r, c)) {
						count++;
					}
				}
			}
		}
		return count;
	}

	private int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private boolean dfs(int[][] grid, boolean[][] visited, int row, int col) {
		visited[row][col] = true;

		if (row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1) {
			return false;
		}
		boolean flag = true;

		for (int[] dir : dirs) {
			int nr = row + dir[0];
			int nc = col + dir[1];

			if (visited[nr][nc] || grid[nr][nc] == 1) {
				continue;
			}

			if (!dfs(grid, visited, nr, nc)) {
				flag = false;
			}
		}
		return flag;
	}

}
