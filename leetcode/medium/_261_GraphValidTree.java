package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You have a graph of n nodes labeled from 0 to n - 1. You are given an
 *         integer n and a list of edges where edges[i] = [ai, bi] indicates
 *         that there is an undirected edge between nodes ai and bi in the
 *         graph.
 * 
 *         Return true if the edges of the given graph make up a valid tree, and
 *         false otherwise.
 * 
 * 
 * 
 *         Example 1:
 * 								0
 * 							   /|\
 *                            / | \
 * 							 1  2  3
 * 							 |
 * 							 4
 * 			
 *         Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]] Output: true 
 *         
 *         Example 2:
 * 							0----- 1 ------2
 * 								  | \	  /
 * 								  |  \   /
 * 								  4	  \ /
 * 									   3					
 * 
 *         Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]] Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= 2000 <= n 0 <= edges.length <= 5000 edges[i].length == 2 0 <=
 *         ai, bi < n ai != bi There are no self-loops or repeated edges.
 *
 */

public class _261_GraphValidTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//DFS Approach
	public boolean validTree(int n, int[][] edges) {

		//For a tree there can be no cycle and number of connection between two nodes 
		//only be n - 1 where n is the number of nodes
		if (edges.length > n - 1) {
			return false;
		}
		Map<Integer, List<Integer>> adjacency = new HashMap<>();

		for (int[] edge : edges) {
			int node1 = edge[0];
			int node2 = edge[1];
			List<Integer> child1 = adjacency.getOrDefault(node1, new ArrayList<>());
			List<Integer> child2 = adjacency.getOrDefault(node2, new ArrayList<>());
			child1.add(node2);
			child2.add(node1);
			adjacency.put(node1, child1);
			adjacency.put(node2, child2);
		}
		Map<Integer, Integer> childToParentMap = new HashMap<>();
		Set<Integer> visited = new HashSet<>();

		if (!dfs(adjacency, childToParentMap, 0, -1)) {
			return false;
		}
		return childToParentMap.size() == n;
	}

	private boolean dfs(Map<Integer, List<Integer>>adjacency, 
			Map<Integer, Integer> childToParentMap, int node, int parent) {

		if (childToParentMap.containsKey(node)) {
			return false;
		}
		childToParentMap.put(node, parent);
		List<Integer> adj = adjacency.getOrDefault(node, new ArrayList<>());
		boolean flag = true;

		for (int child : adj) {

			if (child == parent) {
				continue;
			}
			flag = dfs(adjacency, childToParentMap, child, node);

			if (!flag) {
				break;
			}
		}
		return flag;
	}
	//=============================================================================
	//BFS Approach
	public boolean validTree1(int n, int[][] edges) {

		//For a tree there can be no cycle and number of connection between two nodes 
		//only be n - 1 where n is the number of nodes
		if (edges.length > n - 1) {
			return false;
		}
		Map<Integer, List<Integer>> adjacency = new HashMap<>();

		for (int[] edge : edges) {
			int node1 = edge[0];
			int node2 = edge[1];
			List<Integer> child1 = adjacency.getOrDefault(node1, new ArrayList<>());
			List<Integer> child2 = adjacency.getOrDefault(node2, new ArrayList<>());
			child1.add(node2);
			child2.add(node1);
			adjacency.put(node1, child1);
			adjacency.put(node2, child2);
		}
		Map<Integer, Integer> childToParentMap = new HashMap<>();
		Deque<Integer> queue = new LinkedList<>();
		childToParentMap.put(0, -1);
		queue.offer(0);

		while (!queue.isEmpty()) {
			int node = queue.poll();
			List<Integer> children = adjacency.getOrDefault(node, new ArrayList<>());

			for (int child : children) {

				if (child == childToParentMap.get(node)) {
					continue;
				}

				if (!childToParentMap.containsKey(child)) {
					childToParentMap.put(child, node);
				} else {
					int parent = childToParentMap.get(child);

					if (parent != node) {
						return false;
					}
				}
				queue.offer(child);
			}
		}
		return childToParentMap.size() == n;
	}
	//=============================================================================
	//Union Find Disjoint Set Approach
	public boolean validTree2(int n, int[][] edges) {

		if (edges.length > n - 1) {
			return false;
		}
		UnionFind uf = new UnionFind(n);

		for (int[] edge : edges) {

			if (!uf.union(edge[0], edge[1])) {
				return false;    
			}        
		}
		int[] relation = uf.relation;
		int count = 0;

		for (int i = 0; i < n; i++) {

			if (relation[i] == i) {
				count++;
			}
		}
		return count == 1;
	}

	class UnionFind {
		int[] relation;

		public UnionFind(int size) {
			this.relation = new int[size];

			for (int i = 0; i < size; i++) {
				relation[i] = i;
			}
		}

		public int find(int node) {

			if(relation[node] != node) {
				return find(relation[node]);
			}
			return node;
		}

		public boolean union(int x, int y) {
			int parentX = find(x);
			int parentY = find(y);

			if (parentX != parentY) {
				relation[parentY] = parentX;
				return true;
			}
			return false;
		}
	}
}
