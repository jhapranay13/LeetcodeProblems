package leetcode.Graph.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         There are n cities numbered from 1 to n.
 * 
 *         You are given connections, where each connections[i] = [city1, city2,
 *         cost] represents the cost to connect city1 and city2 together. (A
 *         connection is bidirectional: connecting city1 and city2 is the same
 *         as connecting city2 and city1.)
 * 
 *         Return the minimum cost so that for every pair of cities, there
 *         exists a path of connections (possibly of length 1) that connects
 *         those two cities together. The cost is the sum of the connection
 *         costs used. If the task is impossible, return -1.
 * 
 * 
 * 
 *         Example 1:
 *         							5
 * 								1----------2
 * 								 \        /
 * 								6 \		 / 1
 * 								   \    /
 * 									\  /
 * 									  3	
 * 	
 *         Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]] Output: 6
 *         Explanation: Choosing any 2 edges will connect all cities so we
 *         choose the minimum 2. 
 *         
 *         Example 2:
 * 						      3
 * 						1-------------2
 * 			
 * 							  4
 * 						3-------------4	
 * 
 * 
 *         Input: n = 4, connections = [[1,2,3],[3,4,4]] Output: -1 Explanation:
 *         There is no way to connect all cities even if all edges are used.
 * 
 * 
 *         Note:
 * 
 *         1 <= n <= 10000 1 <= connections.length <= 10000 1 <=
 *         connections[i][0], connections[i][1] <= n 0 <= connections[i][2] <=
 *         105 connections[i][0] != connections[i][1]
 *
 */

public class _1135_ConnectingCitiesWithMinimumCost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Kruskul's Algorithm
	class UnionFind {
		int[] parent;

		public UnionFind(int size) {
			this.parent = new int[size + 1];

			for (int i = 0; i < size + 1; i++) {
				parent[i] = i;
			}
		}

		public int find(int node) {

			if (parent[node] != node) {
				return find(parent[node]);
			}
			return node;
		}

		public boolean union(int x, int y) {
			int parentX = find(x);
			int parentY = find(y);

			if (parentX != parentY) {
				parent[parentY] = parentX;
				return true;
			}
			return false;
		}
	}

	public int minimumCost(int n, int[][] connections) {
		int cost = kruskul(connections, n);
		return cost;
	}

	private int kruskul(int[][] connections, int n) {
		UnionFind uf = new UnionFind(n);
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {
				return x[2] - y[2];
			}
		});

		for (int[] connection : connections) {
			pq.offer(connection);
		}
		int edgeCount = 0;
		int sum = 0;

		while (!pq.isEmpty()) {
			int[] connection = pq.poll();

			if (uf.union(connection[0], connection[1])) {
				sum += connection[2];
				edgeCount++;
			}
		}
		return edgeCount == n - 1 ? sum : -1;
	}
	// =============================================================================
	// Prim's Algorithm
	public int minimumCost1(int n, int[][] connections) {
		int cost = prims(connections, n);
		return cost;
	}

	private int prims(int[][] connections, int n) {
		Map<Integer, List<int[]>> adjacency = new HashMap<>();

		for (int[] connection : connections) {
			List<int[]> list = adjacency.getOrDefault(connection[0], new ArrayList<>());
			list.add(new int[] {connection[1], connection[2]});
			adjacency.put(connection[0], list);
			list = adjacency.getOrDefault(connection[1], new ArrayList<>());
			list.add(new int[] {connection[0], connection[2]});
			adjacency.put(connection[1], list);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {
				return x[1] - y[1];
			}
		});
		Set<Integer> visited = new HashSet<>();
		pq.offer(new int[] {1, 0});
		int sum = 0;

		while (!pq.isEmpty()) {
			int[] edge = pq.poll();

			if (!visited.add(edge[0])) {
				continue;
			}
			sum += edge[1];
			List<int[]> list = adjacency.getOrDefault(edge[0], new ArrayList<>());

			for (int[] connection : list) {
				pq.add(connection);
			}
		}
		return visited.size() == n ? sum : -1;
	}
}
