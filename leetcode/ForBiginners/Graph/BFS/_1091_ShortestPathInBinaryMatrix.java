package leetcode.ForBiginners.Graph.BFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an n x n binary matrix grid, return the length of the shortest
 *         clear path in the matrix. If there is no clear path, return -1.
 * 
 *         A clear path in a binary matrix is a path from the top-left cell
 *         (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such
 *         that:
 * 
 *         All the visited cells of the path are 0. All the adjacent cells of
 *         the path are 8-directionally connected (i.e., they are different and
 *         they share an edge or a corner). The length of a clear path is the
 *         number of visited cells of this path.
 * 
 *         Example 1:
 * 
 *         Input: grid = [[0,1],[1,0]] Output: 2
 * 
 *         Example 2:
 * 
 *         Input: grid = [[0,0,0],[1,1,0],[1,1,0]] Output: 4
 * 
 *         Example 3:
 * 
 *         Input: grid = [[1,0,0],[1,1,0],[1,1,0]] Output: -1
 * 
 * 
 *         Constraints:
 * 
 *         n == grid.length n == grid[i].length 1 <= n <= 100 grid[i][j] is 0 or
 *         1
 *
 */

public class _1091_ShortestPathInBinaryMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int shortestPathBinaryMatrix(int[][] grid) {
		Deque<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0, 0 });

		if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
			return -1;
		}
		int[][] v = new int[grid.length][grid.length];
		v[0][0] = 1;

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int r = node[0];
			int c = node[1];
			int co = node[2];

			if (r == grid.length - 1 && c == grid.length - 1) {
				return co + 1;
			}

			for (int[] d : dir) {
				int nr = r + d[0];
				int nc = c + d[1];

				if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] == 1
						|| v[nr][nc] == 1) {
					continue;
				}

				v[nr][nc] = 1;
				q.offer(new int[] { nr, nc, co + 1 });
			}
		}
		return -1;
	}

	int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
}
