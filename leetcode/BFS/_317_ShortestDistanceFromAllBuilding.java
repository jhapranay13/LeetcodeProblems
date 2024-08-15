package leetcode.BFS;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Pranay Jha
 *
 *         You are given an m x n grid grid of values 0, 1, or 2, where:
 *
 *         each 0 marks an empty land that you can pass by freely, each 1 marks
 *         a building that you cannot pass through, and each 2 marks an obstacle
 *         that you cannot pass through. You want to build a house on an empty
 *         land that reaches all buildings in the shortest total travel
 *         distance. You can only move up, down, left, and right.
 *
 *         Return the shortest travel distance for such a house. If it is not
 *         possible to build such a house according to the above rules, return
 *         -1.
 *
 *         The total travel distance is the sum of the distances between the
 *         houses of the friends and the meeting point.
 *
 *         The distance is calculated using Manhattan Distance, where
 *         distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 *
 *
 *         Example 1:
 *
 *         [1,0,2,0,1]
 *         [0,0,0,0,0]
 *         [0,0,1,0,0]
 *
 *         Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]] Output: 7
 *         Explanation: Given three buildings at (0,0), (0,4), (2,2), and an
 *         obstacle at (0,2). The point (1,2) is an ideal empty land to build a
 *         house, as the total travel distance of 3+3+1=7 is minimal. So return
 *         7.
 *
 *         Example 2:
 *
 *         Input: grid = [[1,0]] Output: 1
 *
 *         Example 3:
 *
 *         Input: grid = [[1]] Output: -1
 *
 *
 *         Constraints:
 *
 *         m == grid.length n == grid[i].length 1 <= m, n <= 100 grid[i][j] is
 *         either 0, 1, or 2. There will be at least one building in the grid.
 *
 */

public class _317_ShortestDistanceFromAllBuilding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// First version. Checking For all zeroes.
	public int shortestDistance(int[][] grid) {

		int numberOfBuildings = 0;

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] == 1) {
					numberOfBuildings++;
				}
			}
		}
		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				int[][] visited = new int[grid.length][grid[i].length];

				if (grid[i][j] == 0) {
					ans = Math.min(ans, bfs(grid, visited, numberOfBuildings, i, j));
				}
			}
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public int bfs(int[][] grid, int[][] visited, int numberOfBuildings, int row, int col) {
		int[] rowOffset = { 1, 0, -1, 0 };
		int[] colOffset = { 0, 1, 0, -1 };
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { row, col, 0 });
		int buildingCount = 0;
		int distance = 0;

		while (!queue.isEmpty()) {
			int[] node = queue.poll();
			int currRow = node[0];
			int currCol = node[1];
			int currDistance = node[2];

			if (visited[currRow][currCol] == 1) {
				continue;
			}
			visited[currRow][currCol] = 1;

			if (grid[currRow][currCol] == 1) {
				distance += currDistance;
				buildingCount++;
				continue;
			}

			for (int i = 0; i < rowOffset.length; i++) {
				int newRow = currRow + rowOffset[i];
				int newCol = currCol + colOffset[i];

				if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {

					if (visited[newRow][newCol] == 0 && grid[newRow][newCol] != 2) {
						queue.add(new int[] { newRow, newCol, currDistance + 1 });
					}
				}
			}
		}

		if (buildingCount == numberOfBuildings) {
			return distance;
		} else {
			return Integer.MAX_VALUE;
		}
	}

	// =====================================================================
	// Version 2
	public int shortestDistance2(int[][] grid) {
		int buildingCount = 0;

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] == 1) {
					buildingCount++;
				}
			}
		}
		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] == 0) {
					int[][] v = new int[grid.length][grid[0].length];
					ans = Math.min(bfs2(grid, v, i, j, buildingCount), ans);
				}
			}
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	private int dirs[][] = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	private int bfs2(int[][] g, int[][] v, int row, int col, int buildingCount) {
		Deque<int[]> q = new LinkedList<>();
		q.offer(new int[] { row, col });
		int count = 0;
		int ans = 0;
		int step = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				int[] pos = q.poll();
				int r = pos[0];
				int c = pos[1];

				if (v[r][c] == 1) {
					continue;
				}
				v[r][c] = 1;

				if (g[r][c] == 1) {
					count++;
					ans += step;
					continue;
				}

				for (int[] dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];

					if (nr < 0 || nc < 0 || nr >= g.length || nc >= g[0].length || v[nr][nc] == 1 || g[nr][nc] == 2) {
						continue;
					}
					q.offer(new int[] { nr, nc });
				}
			}
			step++;
		}

		if (buildingCount != count) {
			ans = Integer.MAX_VALUE;
		}
		return ans;
	}
	// =====================================================================
	// Passes new testcases
	class Solution {
		public int shortestDistance(int[][] grid) {
			int[][][] distance = new int[grid.length][grid[0].length][2];
			int houseCount = 0;

			for (int r = 0; r < grid.length; r++) {

				for (int c = 0; c < grid[0].length; c++) {

					if (grid[r][c] == 1) {
						houseCount++;
						recur(grid, distance, r, c);
					}
				}
			}
			int ans = Integer.MAX_VALUE;

			for (int r = 0; r < grid.length; r++) {

				for (int c = 0; c < grid[0].length; c++) {

					if (grid[r][c] == 0 && distance[r][c][0] != 0 && distance[r][c][1] == houseCount) {
						ans = Math.min(ans, distance[r][c][0]);
					}
				}
			}
			return ans == Integer.MAX_VALUE ? -1 : ans;
		}
		int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

		public void recur(int[][] grid, int[][][] distance, int r, int c) {
			Deque<int[]> q = new LinkedList<>();
			q.offer(new int[] {r, c});
			boolean[][] v = new boolean[grid.length][grid[0].length];
			v[r][c] = true;
			int currDistance = 0;

			while (!q.isEmpty()) {
				int size = q.size();

				while (size-- > 0) {
					int cr = q.peek()[0];
					int cc = q.peek()[1];
					q.poll();

					for (int[] dir : dirs) {
						int nr = dir[0] + cr;
						int nc = dir[1] + cc;

						if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length ||
								v[nr][nc] || grid[nr][nc] != 0) {
							continue;
						}
						q.offer(new int[] {nr, nc});
						v[nr][nc] = true;
						distance[nr][nc][0] += currDistance + 1;
						distance[nr][nc][1]++;
					}
				}
				currDistance++;
			}
		}
	}
}
