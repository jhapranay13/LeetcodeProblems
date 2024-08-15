package leetcode.Graph.ShortestPath;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an n x n grid containing only values 0 and 1, where 0
 *         represents water and 1 represents land, find a water cell such that
 *         its distance to the nearest land cell is maximized, and return the
 *         distance. If no land or water exists in the grid, return -1.
 * 
 *         The distance used in this problem is the Manhattan distance: the
 *         distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 -
 *         y1|.
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: grid = [[1,0,1],[0,0,0],[1,0,1]] Output: 2 Explanation: The
 *         cell (1, 1) is as far as possible from all the land with distance 2.
 * 
 * 
 *         Example 2:
 * 
 * 
 *         Input: grid = [[1,0,0],[0,0,0],[0,0,0]] Output: 4 Explanation: The
 *         cell (2, 2) is as far as possible from all the land with distance 4.
 * 
 * 
 *         Constraints:
 * 
 *         n == grid.length n == grid[i].length 1 <= n <= 100 grid[i][j] is 0 or
 *         1
 *
 */

public class _1162_AsFarFromLandAsPossible {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxDistance(int[][] grid) {
		Deque<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[grid.length][grid.length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {

				if (grid[i][j] == 1) {
					q.offer(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}

		if (q.size() == grid.length * grid.length) {
			return -1;
		}
		int ans = -1;

		while (!q.isEmpty()) {
			int size = q.size();
			ans++;

			while (size-- > 0) {
				int[] pos = q.poll();

				for (int[] dir : dirs) {
					int nr = pos[0] + dir[0];
					int nc = pos[1] + dir[1];

					if (nr < 0 || nc < 0 || nr == grid.length || nc == grid.length || visited[nr][nc]
							|| grid[nr][nc] == 1) {
						continue;
					}
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		return ans;
	}

	private int dirs[][] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
}
