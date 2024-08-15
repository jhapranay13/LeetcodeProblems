package leetcode.Graph.UnionFind;

/**
 * 
 * @author Pranay Jha
 *
 *         In this problem, a rooted tree is a directed graph such that, there
 *         is exactly one node (the root) for which all other nodes are
 *         descendants of this node, plus every node has exactly one parent,
 *         except for the root node which has no parents.
 * 
 *         The given input is a directed graph that started as a rooted tree
 *         with n nodes (with distinct values from 1 to n), with one additional
 *         directed edge added. The added edge has two different vertices chosen
 *         from 1 to n, and was not an edge that already existed.
 * 
 *         The resulting graph is given as a 2D-array of edges. Each element of
 *         edges is a pair [ui, vi] that represents a directed edge connecting
 *         nodes ui and vi, where ui is a parent of child vi.
 * 
 *         Return an edge that can be removed so that the resulting graph is a
 *         rooted tree of n nodes. If there are multiple answers, return the
 *         answer that occurs last in the given 2D-array.
 *
 * 
 *         Example 1:
 * 						
 * 					1 ----> 3
 * 					|      /
 * 					|     /
 * 					V <__/
 * 					2
 * 
 *         Input: edges = [[1,2],[1,3],[2,3]] Output: [2,3] 
 *         
 *         Example 2:
 * 					
 * 					2 -----> 3
 * 					^		 |
 *                  |        V
 *                  1<-------4
 *                  |
 *                  V
 *                  5	
 * 
 * 
 *         Constraints:
 * 
 *         n == edges.length 3 <= n <= 1000 edges[i].length == 2 1 <= ui, vi <=
 *         n ui != vi
 *
 */

public class _685_RedundentConnection2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class UF {
		int[] parent;

		public UF(int size) {
			parent = new int[size];

			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {

			if (parent[x] != x) {
				return find(parent[x]);
			}
			return x;
		}

		public boolean union(int x, int y) {
			int px = find(x);
			int py = find(y);

			if (px == py) {
				return false;
			}
			parent[px] = py;
			return true;
		}
	}

	public int[] findRedundantDirectedConnection(int[][] edges) {
		int withTwoParents = -1;
		int incoming[] = new int[edges.length + 1];

		for (int[] edge : edges) {
			incoming[edge[1]]++;

			if (incoming[edge[1]] == 2) {
				withTwoParents = edge[1];
			}
		}

		if (withTwoParents == -1) {
			UF uf = new UF(edges.length + 1);

			for (int[] edge : edges) {

				if (!uf.union(edge[0], edge[1])) {
					return edge;
				}
			}
		}

		if (withTwoParents != -1) {

			for (int i = edges.length - 1; i >= 0; i--) {
				int[] edge = edges[i];

				if (edge[1] == withTwoParents) {

					if (checkCycle(edges, edge) == null) {
						return edge;
					}
				}
			}
		}
		return new int[2];
	}

	private int[] checkCycle(int[][] edges, int[] skip) {
		UF uf = new UF(edges.length + 1);

		for (int i = 0; i < edges.length; i++) {
			int[] edge = edges[i];

			if (edge[0] == skip[0] && edge[1] == skip[1]) {
				continue;
			}

			if (!uf.union(edge[0], edge[1])) {
				return edge;
			}
		}
		return null;
	}
}
