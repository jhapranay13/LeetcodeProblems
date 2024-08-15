package leetcode.Graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a matrix of integers grid with m rows and n columns, find the
 *         maximum score of a path starting at [0,0] and ending at [m-1,n-1].
 * 
 *         The score of a path is the minimum value in that path. For example,
 *         the value of the path 8 -> 4 -> 5 -> 9 is 4.
 * 
 *         A path moves some number of times from one visited cell to any
 *         neighbouring unvisited cell in one of the 4 cardinal directions
 *         (north, east, west, south).
 * 
 *         Example 1:
 * 
 *         Input: [[5,4,5],[1,2,6],[7,4,6]] Output: 4 Explanation: The path with
 *         the maximum score is highlighted in yellow. 
 *         
 *         Example 2:
 * 
 *         Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]] Output: 2 
 *         
 *         Example 3:
 * 
 *         Input:
 *         [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 *         Output: 3
 * 
 * 
 *         Note:
 * 
 *         1 <= m, n <= 100 0 <= grid[i][j] <= 109
 *
 */

public class _1102_PathWithMaximumMinimumValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Dijkstra's
	public int maximumMinimumPath(int[][] grid) {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[2] - a[2]);
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		q.offer(new int[] { 0, 0, grid[0][0] });
		visited[0][0] = true;
		int ans = grid[0][0];

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			ans = Math.min(ans, pos[2]);

			if (pos[0] == grid.length - 1 && pos[1] == grid[0].length - 1) {
				break;
			}

			for (int[] dir : dirs) {
				int nr = dir[0] + pos[0];
				int nc = dir[1] + pos[1];

				if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length || visited[nr][nc]) {
					continue;
				}
				visited[nr][nc] = true;
				q.offer(new int[] { nr, nc, grid[nr][nc] });
			}
		}
		return ans;
	}

	// =============================================================================
	// DFS TLE
	public int maximumMinimumPath1(int[][] A) {
		Set<String> v = new HashSet<>();
		dfs(A, 0, 0, A[0][0], v);
		return ans;
	}

	private void dfs(int[][] A, int r, int c, int score, Set<String> v) {

		if (r == A.length - 1 && c == A[0].length - 1) {
			// System.out.println( score );
			ans = Math.max(ans, score);
			return;
		}
		String key = r + ", " + c;
		v.add(key);

		for (int[] dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];

			if (nr < 0 || nc < 0 || nr >= A.length || nc >= A[0].length) {
				continue;
			}
			String nkey = nr + ", " + nc;

			if (v.contains(nkey)) {
				continue;
			}
			int nval = A[nr][nc] < score ? A[nr][nc] : score;
			dfs(A, nr, nc, nval, v);
		}
		v.remove(key);

	}

	private int ans = Integer.MIN_VALUE;

	private int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
}
