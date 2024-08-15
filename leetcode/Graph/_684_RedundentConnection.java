package leetcode.Graph;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         In this problem, a tree is an undirected graph that is connected and
 *         has no cycles.
 * 
 *         You are given a graph that started as a tree with n nodes labeled
 *         from 1 to n, with one additional edge added. The added edge has two
 *         different vertices chosen from 1 to n, and was not an edge that
 *         already existed. The graph is represented as an array edges of length
 *         n where edges[i] = [ai, bi] indicates that there is an edge between
 *         nodes ai and bi in the graph.
 * 
 *         Return an edge that can be removed so that the resulting graph is a
 *         tree of n nodes. If there are multiple answers, return the answer
 *         that occurs last in the input.
 * 
 * 
 * 
 *         Example 1:
 * 						1----2
 * 						|   /
 *                      |  /
 *                      | / 
 *                      |/
 *                      3
 *            
 *                         	
 *         Input: edges = [[1,2],[1,3],[2,3]] Output: [2,3] 
 *         
 *         
 *         Example 2:
 * 						2---------1---------5
 *                      |         |
 *                      |         |
 *                      3---------4
 * 
 *         Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]] Output: [1,4]
 * 
 * 
 *         Constraints:
 * 
 *         n == edges.length 3 <= n <= 1000 edges[i].length == 2 1 <= ai < bi <=
 *         edges.length ai != bi There are no repeated edges. The given graph is
 *         connected.
 *
 */

public class _684_RedundentConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Union Find Approach
	class UnionFind {
		int[] parent;

		public UnionFind(int size) {
			this.parent = new int[size];

			for (int i = 0; i < size; i++) {
				this.parent[i] = i;
			}
		}

		public int find(int node) {
			if (this.parent[node] != node) {
				return find(this.parent[node]);
			} 
			return this.parent[node];
		}

		public boolean union(int node1, int node2) {
			int parent1 = find(node1);
			int parent2 = find(node2);

			if (parent1 != parent2) {
				this.parent[parent2] = parent1;
				return true;
			}
			return false;
		}
	}

	public int[] findRedundantConnection(int[][] edges) {
		UnionFind uf = new UnionFind(edges.length + 1);

		for (int[] edge : edges) {

			if (!uf.union(edge[0], edge[1])) {
				return edge;
			}
		}
		return null;
	}
	//============================================================================
	//DFS Approach
	Set<Integer> seen = new HashSet();
	int MAX_EDGE_VAL = 1000;

	public int[] findRedundantConnection1(int[][] edges) {
		ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
		for (int i = 0; i <= MAX_EDGE_VAL; i++) {
			graph[i] = new ArrayList();
		}

		for (int[] edge: edges) {
			seen.clear();
			if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
					dfs(graph, edge[0], edge[1])) {
				return edge;
			}
			graph[edge[0]].add(edge[1]);
			graph[edge[1]].add(edge[0]);
		}
		throw new AssertionError();
	}
	public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
		if (!seen.contains(source)) {
			seen.add(source);
			if (source == target) return true;
			for (int nei: graph[source]) {
				if (dfs(graph, nei, target)) return true;
			}
		}
		return false;
	}
	//=============================================================================================
	//DFS based approach
	public int[] findRedundantConnection2(int[][] edges) {
		Map<Integer, List<Integer>> adjMap = new HashMap<>();

		for (int[] edge : edges) {
			Set<Integer> v = new HashSet<>();
			int cycleFlag = recur(adjMap, v, edge[0], edge[1]);

			if (cycleFlag == 1) {
				return edge;
			}
			List<Integer> l1 = adjMap.getOrDefault(edge[0], new ArrayList<>());
			List<Integer> l2 = adjMap.getOrDefault(edge[1], new ArrayList<>());
			l1.add(edge[1]);
			l2.add(edge[0]);
			adjMap.put(edge[0], l1);
			adjMap.put(edge[1], l2);
		}
		return new int[2];
	}

	private int recur(Map<Integer, List<Integer>> adjMap, Set<Integer> v, int src, int dest) {
		//If true it is a cycle
		if (src == dest) {
			return 1;
		}
		int flag = 0;
		List<Integer> list = adjMap.getOrDefault(dest, new ArrayList<>());
		v.add(dest);

		for (int next : list) {

			if (!v.contains(next)) {
				flag = recur(adjMap, v, src, next);
			}

			if (flag == 1) {
				return flag;
			}
		}
		v.remove(dest);
		return flag;
	}
}
