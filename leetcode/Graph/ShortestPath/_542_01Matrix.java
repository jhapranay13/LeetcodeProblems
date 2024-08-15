package leetcode.Graph.ShortestPath;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an m x n binary matrix mat, return the distance of the nearest
 *         0 for each cell.
 * 
 *         The distance between two adjacent cells is 1.
 * 
 *         Example 1:
 * 
 *         Input: mat = [[0,0,0],[0,1,0],[0,0,0]] Output:
 *         [[0,0,0],[0,1,0],[0,0,0]] 
 *         
 *         Example 2:
 * 
 *         Input: mat = [[0,0,0],[0,1,0],[1,1,1]] Output:
 *         [[0,0,0],[0,1,0],[1,2,1]]
 * 
 * 
 *         Constraints:
 * 
 *         m == mat.length n == mat[i].length 1 <= m, n <= 104 1 <= m * n <= 104
 *         mat[i][j] is either 0 or 1. There is at least one 0 in mat.
 *
 */

public class _542_01Matrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[][] updateMatrix(int[][] mat) {
		Deque<int[]> queue = new LinkedList<>();

		for (int r = 0; r < mat.length; r++) {
			for (int c = 0; c < mat[0].length; c++) {

				if (mat[r][c] == 0) {
					queue.offer(new int[] { r, c });
				}
			}
		}
		int count = 0;
		boolean[][] visited = new boolean[mat.length][mat[0].length];
		int[][] ans = new int[mat.length][mat[0].length];
		int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		// Shortest Path
		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				int[] node = queue.poll();

				if (visited[node[0]][node[1]]) {
					continue;
				}
				visited[node[0]][node[1]] = true;

				if (mat[node[0]][node[1]] == 1) {
					ans[node[0]][node[1]] = count;
				}

				for (int[] dir : dirs) {
					int nr = node[0] + dir[0];
					int nc = node[1] + dir[1];

					if (nr < 0 || nc < 0 || nr == mat.length || nc == mat[0].length || mat[nr][nc] == 0
							|| visited[nr][nc]) {
						continue;
					}
					queue.add(new int[] { nr, nc });
				}
			}
			count++;
		}
		return ans;
	}
}
