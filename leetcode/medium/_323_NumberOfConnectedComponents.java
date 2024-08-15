package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         You have a graph of n nodes. You are given an integer n and an array
 *         edges where edges[i] = [ai, bi] indicates that there is an edge
 *         between ai and bi in the graph.
 * 
 *         Return the number of connected components in the graph.
 * 
 *         Example 1:
 * 						0 -------- 1    3
 * 								   |	|
 *                                 |    |
 *                                 2    4
 *                                  		
 *         Input: n = 5, edges = [[0,1],[1,2],[3,4]] Output: 2 
 *         
 *         Example 2:
 * 						0 -------- 1    3
 * 								   |   /|
 *                                 |  / |
 *                                 | /  |
 *                                  2   4
 * 
 *         Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 2000 1 <= edges.length <= 5000 edges[i].length == 2 0 <= ai
 *         <= bi < n ai != bi There are no repeated edges.
 *
 */

public class _323_NumberOfConnectedComponents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===============================================================================
	//Union Find Disjoint Set Approach
	class UnionFind {

		int[] parent;

		public UnionFind(int n) {
			this.parent = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int node) {

			if (parent[node] != node) {
				return find(parent[node]);
			}
			return node;
		}

		public void union(int node1, int node2) {
			int parent1 = find(node1);
			int parent2 = find(node2);

			if (parent1 != parent2) {
				parent[parent2] = parent1;
			}
		}
	}

	public int countComponents(int n, int[][] edges) {
		UnionFind uf = new UnionFind(n);

		for (int[] edge : edges) {
			uf.union(edge[0], edge[1]);
		}
		int ans = 0;
		int[] parent = uf.parent;

		for (int i = 0; i < parent.length; i++) {

			if (parent[i] == i) {
				ans++;
			}
		}
		return ans;
	}
}
