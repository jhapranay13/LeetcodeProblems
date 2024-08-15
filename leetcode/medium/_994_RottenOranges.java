package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an m x n grid where each cell can have one of three
 *         values:
 * 
 *         0 representing an empty cell, 1 representing a fresh orange, or 2
 *         representing a rotten orange. Every minute, any fresh orange that is
 *         4-directionally adjacent to a rotten orange becomes rotten.
 * 
 *         Return the minimum number of minutes that must elapse until no cell
 *         has a fresh orange. If this is impossible, return -1.
 * 
 *         Example 1:
 * 
 *         Input: grid = [[2,1,1],[1,1,0],[0,1,1]] Output: 4
 * 
 *         Example 2:
 * 
 *         Input: grid = [[2,1,1],[0,1,1],[1,0,1]] Output: -1 Explanation: The
 *         orange in the bottom left corner (row 2, column 0) is never rotten,
 *         because rotting only happens 4-directionally.
 * 
 *         Example 3:
 * 
 *         Input: grid = [[0,2]] Output: 0 Explanation: Since there are already
 *         no fresh oranges at minute 0, the answer is just 0.
 * 
 * 
 *         Constraints:
 * 
 *         m == grid.length n == grid[i].length 1 <= m, n <= 10 grid[i][j] is 0,
 *         1, or 2.
 *
 */

public class _994_RottenOranges {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int orangesRotting(int[][] grid) {
		Deque<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[grid.length][grid[0].length];

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {

				if (grid[r][c] == 2) {
					queue.offer(new int[] { r, c });
					visited[r][c] = true;
				} else if (grid[r][c] == 0) {
					visited[r][c] = true;
				}

			}
		}
		int ans = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				int[] pos = queue.poll();

				for (int[] dir : dirs) {
					int nr = pos[0] + dir[0];
					int nc = pos[1] + dir[1];

					if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] != 1) {
						continue;
					}

					if (visited[nr][nc]) {
						continue;
					}
					visited[nr][nc] = true;
					queue.offer(new int[] { nr, nc });
				}
			}

			if (queue.isEmpty()) {
				break;
			}
			ans++;
		}

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {

				if (!visited[r][c]) {
					return -1;
				}
			}
		}
		return ans;
	}

	private int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
}
