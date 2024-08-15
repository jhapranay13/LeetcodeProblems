package leetcode.Graph.UnionFind;

/**
 * 
 * @author Pranay Jha
 *
 *         There are n cities. Some of them are connected, while some are not.
 *         If city a is connected directly with city b, and city b is connected
 *         directly with city c, then city a is connected indirectly with city
 *         c.
 * 
 *         A province is a group of directly or indirectly connected cities and
 *         no other cities outside of the group.
 * 
 *         You are given an n x n matrix isConnected where isConnected[i][j] = 1
 *         if the ith city and the jth city are directly connected, and
 *         isConnected[i][j] = 0 otherwise.
 * 
 *         Return the total number of provinces.
 * 
 *         Example 1:
 * 						1--------2
 * 	
 * 						3
 * 
 *         Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]] Output: 2 
 *         
 *         Example 2:
 * 						1			2
 * 								3
 * 
 *         Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]] Output: 3
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 200 n == isConnected.length n == isConnected[i].length
 *         isConnected[i][j] is 1 or 0. isConnected[i][i] == 1 isConnected[i][j]
 *         == isConnected[j][i]
 *
 */

public class _547_NumberOfProvinces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===============================================================================
	//Using Union Find Disjoint set.
	public int findCircleNum(int[][] isConnected) {
		UnionFind uf = new UnionFind(isConnected.length);

		for (int i = 0; i < isConnected.length; i++) {
			for (int j = 0; j < isConnected.length; j++) {
				if (i != j && isConnected[i][j] == 1) {
					uf.union(i, j);
				}
			}
		}
		int ans = 0;
		int[] parent = uf.parent;

		for (int i = 0; i < parent.length; i++) {
			if(parent[i] == i) {
				ans++;
			}
		}
		return ans;
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

			if (parent[node] != node) {
				return find(parent[node]);
			}
			return node;
		}

		public void union(int node1, int node2) {
			int pn1 = find(node1);
			int pn2 = find(node2);

			if (pn1 != pn2) {
				parent[pn2] = pn1;
			}
		}
	}
}
