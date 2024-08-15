package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         An n x n grid is composed of 1 x 1 squares where each 1 x 1 square
 *         consists of a '/', '\', or blank space ' '. These characters divide
 *         the square into contiguous regions.
 * 
 *         Given the grid grid represented as a string array, return the number
 *         of regions.
 * 
 *         Note that backslash characters are escaped, so a '\' is represented
 *         as '\\'.
 * 
 * 
 * 
 *         Example 1:
 * 					 __
 * 					| /|
 *                  |/_|  
 *      
 *         Input: grid = [" /","/ "] Output: 2 
 *         
 *         Example 2:
 * 					
 * 					 ___
 * 					|  /|
 *                  |___|  
 * 
 *         Input: grid = [" /"," "] Output: 1 
 *         
 *         Example 3:
 * 					
 * 					 __
 * 					|\/|
 *                  |/\|  
 * 					 ---
 * 
 *         Input: grid = ["\\/","/\\"] Output: 4 Explanation: (Recall that
 *         because \ characters are escaped, "\\/" refers to \/, and "/\\"
 *         refers to /\.) 
 *         
 *         Example 4:
 * 					
 * 				____
 * 				|/\|
 * 				|\/| 		
 * 				----			
 * 
 *         Input: grid = ["/\\","\\/"] Output: 5 Explanation: (Recall that
 *         because \ characters are escaped, "\\/" refers to \/, and "/\\"
 *         refers to /\.) 
 *         
 *         Example 5:
 * 
 * 					_________
 * 					|  /  / |
 * 					| /  /  |
 * 					|/  /   |
 * 					|  /    |
 * 					| /     |
 * 					|/______|	
 * 
 *         Input: grid = ["//","/ "] Output: 3
 * 
 * 
 *         Constraints:
 * 
 *         n == grid.length n == grid[i].length 1 <= n <= 30 grid[i][j] is
 *         either '/', '\', or ' '.
 *
 */

public class _959_RegionCutBySlashes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class UnionFind {
		int[] parent;

		public UnionFind(int size) {
			this.parent = new int[size];

			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}

		public int find(int node) {
			//Path Compression
			if (parent[node] != node) {
				return find(parent[node]);
			}
			return node;
		}

		public void union(int a, int b) {
			int parentA = find(a);
			int parentB = find(b);

			if (parentA != parentB) {
				parent[parentB] = parentA;
			}
		}
	}

	//Dividing each cocordinate into 4 parts
	public int regionsBySlashes(String[] grid) {
		int N = grid.length;
		int length = N * N * 4;
		UnionFind uf = new UnionFind(length);

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid.length; col++) {
				int node = 4 * (row * N + col);
				char val = grid[row].charAt(col);

				if (val != '\\') {
					uf.union(node + 0, node + 1);
					uf.union(node + 2, node + 3);
				}

				if (val != '/') {
					uf.union(node + 0, node + 2);
					uf.union(node + 1, node + 3);
				}

				if (row + 1 < N) {
					uf.union(node + 3, (node + 4 * N) + 0);
				}

				if (row - 1 >= 0) {
					uf.union(node + 0, (node - 4 * N) + 3);
				}

				if (col + 1 < N) {
					uf.union(node + 2, node + 4 + 1);
				}

				if (col - 1 >= 0) {
					uf.union(node + 1, node - 4 + 2);
				}
			}
		}
		int ans = 0;
		int[] parent = uf.parent;

		for (int i = 0; i < length; i++) {
			if (parent[i] == i) {
				ans++;
			}
		}
		return ans;
	}
	//=============================================================================================
	//Slightly different approach

	class UnionFind1 {
		private int[] parent;

		public UnionFind1(int n) {
			this.parent = new int[n];

			for (int i = 0; i < n; i++) {
				this.parent[i] = i;
			}
		}

		public int find(int x) {

			if (parent[x] != x) {
				return find(parent[x]);
			}
			return x;
		}

		public void union(int x, int y) {
			int px = find(x);
			int py = find(y);

			if (px != py) {
				parent[py] = px;
			}
		}

		public int countCuts() {
			int ans = 0;

			for (int i = 0; i < parent.length; i++) {

				if (parent[i] == i) {
					ans++;
				}
			}
			return ans;
		}
	}

	public int regionsBySlashes1(String[] grid) {
		int length = grid.length;
		UnionFind1 uf = new UnionFind1(length * length * 4);

		for (int r = 0; r < length; r++) {

			for (int c = 0; c < length; c++) {
				int node = 4 * (r * length + c);

				if (grid[r].charAt(c) != '\\') {
					uf.union(node + 0, node + 3);
					uf.union(node + 1, node + 2);
				}

				if (grid[r].charAt(c) != '/') {
					uf.union(node + 0, node + 1);
					uf.union(node + 2, node + 3);
				}

				if (r < length - 1) {
					uf.union(node + 1, node + (length * 4) + 3);
				}

				if (c < length - 1) {
					uf.union(node + 2, node + 4);
				}
			}
		}
		return uf.countCuts();
	}
}
