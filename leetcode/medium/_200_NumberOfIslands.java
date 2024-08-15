package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 *
 */

public class _200_NumberOfIslands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
		_200_NumberOfIslands obj = new _200_NumberOfIslands();
		obj.numIslands1(grid);
	}
	//=============================================================================
	//DFS Approach
	public int numIslands(char[][] grid) {
		int ans = 0;

		if (grid == null || grid.length == 0) {
			return ans;
		}
		int[][] visited = new int[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] == '1' && visited[i][j] != 1) {
					dfs(grid, visited, i, j);
					ans++;
				}
			}
		}
		return ans++;
	}
	int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	private void dfs(char[][] grid, int[][] visited, int row, int col) {
		visited[row][col] = 1;

		for (int[] dir : dirs) {
			int nr = row + dir[0];
			int nc = col + dir[1];

			if (nr < grid.length && nr >= 0 && nc < grid[0].length && nc >= 0 && 
					grid[nr][nc] == '1' && visited[nr][nc] != 1) {
				dfs(grid, visited, nr, nc);
			}
		}
	}
	//=============================================================================
	//BFS Approach
	public int numIslands1(char[][] grid) {

		if (grid == null || grid.length == 0) {
			return 0;
		}
		int ans = 0;
		int colNum = grid[0].length;

		for (int i = 0; i < grid.length; i++) {

			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] == '1') {
					ans++;
					Deque<Integer> queue = new LinkedList<>();
					queue.offer(i * colNum + j);

					while (!queue.isEmpty()) {
						int id = queue.poll();
						int row = id / colNum;
						int col = id % colNum;

						if (row - 1 >= 0 && grid[row-1][col] == '1') {
							queue.add((row-1) * colNum + col);
							grid[row-1][col] = '0';
						}
						if (row + 1 < grid.length && grid[row+1][col] == '1') {
							queue.add((row+1) * colNum + col);
							grid[row+1][col] = '0';
						}
						if (col - 1 >= 0 && grid[row][col-1] == '1') {
							queue.add(row * colNum + col-1);
							grid[row][col-1] = '0';
						}
						if (col + 1 < colNum && grid[row][col+1] == '1') {
							queue.add(row * colNum + col+1);
							grid[row][col+1] = '0';
						}
					}
				}
			}
		}
		return ans;
	}
	//=============================================================================
	//Union Find Approach
	class UnionFind {
		int count; // # of connected components
		int[] parent;
		int[] rank;

		public UnionFind(char[][] grid) { // for problem 200
			count = 0;
			int m = grid.length;
			int n = grid[0].length;
			parent = new int[m * n];
			rank = new int[m * n];
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (grid[i][j] == '1') {
						parent[i * n + j] = i * n + j;
						++count;
					}
					rank[i * n + j] = 0;
				}
			}
		}

		public int find(int i) { // path compression
			if (parent[i] != i) parent[i] = find(parent[i]);
			return parent[i];
		}

		public void union(int x, int y) { // union with rank
			int rootx = find(x);
			int rooty = find(y);
			if (rootx != rooty) {
				if (rank[rootx] > rank[rooty]) {
					parent[rooty] = rootx;
				} else if (rank[rootx] < rank[rooty]) {
					parent[rootx] = rooty;
				} else {
					parent[rooty] = rootx; rank[rootx] += 1;
				}
				--count;
			}
		}

		public int getCount() {
			return count;
		}
	}

	public int numIslands2(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int nr = grid.length;
		int nc = grid[0].length;
		int num_islands = 0;
		UnionFind uf = new UnionFind(grid);
		
		for (int r = 0; r < nr; ++r) {
			
			for (int c = 0; c < nc; ++c) {
				
				if (grid[r][c] == '1') {
					grid[r][c] = '0';
					
					if (r - 1 >= 0 && grid[r-1][c] == '1') {
						uf.union(r * nc + c, (r-1) * nc + c);
					}
					
					if (r + 1 < nr && grid[r+1][c] == '1') {
						uf.union(r * nc + c, (r+1) * nc + c);
					}
					
					if (c - 1 >= 0 && grid[r][c-1] == '1') {
						uf.union(r * nc + c, r * nc + c - 1);
					}
					
					if (c + 1 < nc && grid[r][c+1] == '1') {
						uf.union(r * nc + c, r * nc + c + 1);
					}
				}
			}
		}
	    return uf.getCount();
	}
}