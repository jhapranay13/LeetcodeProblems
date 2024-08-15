package leetcode.ForBiginners.Graph.DFS.StartDFSFromNodesAtBoundry;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an m x n binary matrix grid, where 0 represents a sea
 *         cell and 1 represents a land cell.
 * 
 *         A move consists of walking from one land cell to another adjacent
 *         (4-directionally) land cell or walking off the boundary of the grid.
 * 
 *         Return the number of land cells in grid for which we cannot walk off
 *         the boundary of the grid in any number of moves.
 * 
 *         Example 1:
 * 
 * 
 *         Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]] Output: 3
 *         Explanation: There are three 1s that are enclosed by 0s, and one 1
 *         that is not enclosed because its on the boundary. 
 *         
 *         Example 2:
 * 
 * 
 *         Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]] Output: 0
 *         Explanation: All 1s are either on the boundary or can reach the
 *         boundary.
 * 
 * 
 *         Constraints:
 * 
 *         m == grid.length n == grid[i].length 1 <= m, n <= 500 grid[i][j] is
 *         either 0 or 1.
 *
 */

public class _1020_NumberOfEnclaves {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int numEnclaves(int[][] A) {
		int ans = 0;
		Deque<int[]> holder = new LinkedList<>();
		boolean[][] v = new boolean[A.length][A[0].length];

		for (int i = 0; i < A.length; i++) {

			for (int j = 0; j < A[i].length; j++) {

				if (A[i][j] == 0) {
					v[i][j] = true;
					continue;
				}

				if (i == 0 || i == A.length - 1) {

					if (A[i][j] == 1) {
						holder.offer(new int[] { i, j });
					}
				}

				if (j == 0 || j == A[i].length - 1) {

					if (A[i][j] == 1) {
						holder.offer(new int[] { i, j });
					}
				}
			}
		}

		while (!holder.isEmpty()) {
			int[] pos = holder.poll();

			if (!v[pos[0]][pos[1]]) {
				dfs(pos[0], pos[1], v, A);
			}
		}

		for (int i = 0; i < A.length; i++) {

			for (int j = 0; j < A[i].length; j++) {

				if (A[i][j] == 1 && !v[i][j]) {
					ans++;
				}
			}
		}
		return ans;
	}

	private void dfs(int r, int c, boolean[][] v, int[][] A) {
		v[r][c] = true;

		for (int[] dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];

			if (nr < 0 || nc < 0 || nr == v.length || nc == A[nr].length || A[nr][nc] == 0 || v[nr][nc]) {
				continue;
			}
			dfs(nr, nc, v, A);
		}
	}

	private int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
}
