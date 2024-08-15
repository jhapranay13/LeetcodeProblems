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
 *         There are n houses in a village. We want to supply water for all the
 *         houses by building wells and laying pipes.
 * 
 *         For each house i, we can either build a well inside it directly with
 *         cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water
 *         from another well to it. The costs to lay pipes between houses are
 *         given by the array pipes, where each pipes[j] = [house1j, house2j,
 *         costj] represents the cost to connect house1j and house2j together
 *         using a pipe. Connections are bidirectional.
 * 
 *         Return the minimum total cost to supply water to all houses.
 * 
 * 
 * 
 *         Example 1: 
 *
 *							1
 * 						   /
 *                       1/ 
 *                       /
 *                      2----------3
 *                            1
 * 
 *         Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]] Output: 3
 *         Explanation: The image shows the costs of connecting houses using
 *         pipes. The best strategy is to build a well in the first house with
 *         cost 1 and connect the other houses to it with cost 2 so the total
 *         cost is 3.
 * 
 * 
 *         Example 2:
 * 
 *         Input: n = 2, wells = [1,1], pipes = [[1,2,1]] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         2 <= n <= 104 wells.length == n 0 <= wells[i] <= 105 1 <=
 *         pipes.length <= 104 pipes[j].length == 3 1 <= house1j, house2j <= n 0
 *         <= costj <= 105 house1j != house2j
 *
 */

public class _1168_OptimizeWaterDistributionInAVillage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Kruskul's implementation
	class Uf {
		int[] parent;

		public Uf(int size) {
			parent = new int[size];

			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}

		public int find(int i) {

			if (parent[i] != i) {
				return find(parent[i]);
			}
			return i;
		}

		public boolean union(int i, int j) {
			int pi = find(i);
			int pj = find(j);

			if (pi == pj) {
				return false;
			}
			parent[pi] = pj;
			return true;
		}
	}

	public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
		return kruskal(n, wells, pipes);
	}

	private int kruskal(int n, int[] wells, int[][] pipes) {
		Uf uf = new Uf(n + 1);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			public int compare(int[] i1, int[] i2) {
				return i1[2] - i2[2];
			}
		});

		for (int i = 0; i < wells.length; i++) {
			pq.offer(new int[] { 0, i + 1, wells[i] });
		}

		for (int[] pipe : pipes) {
			pq.offer(pipe);
		}
		int mst = 0;

		while (!pq.isEmpty()) {
			int[] pipe = pq.poll();

			if (uf.union(pipe[0], pipe[1])) {
				mst += pipe[2];
			}
		}
		return mst;
	}

	// =====================================================================
	// Prim's Implementation
	public int minCostToSupplyWater1(int n, int[] wells, int[][] pipes) {
		Map<Integer, List<int[]>> graph = new HashMap<>();
		createGraph(pipes, graph);
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {

			public int compare(int[] i1, int[] i2) {
				return i1[2] - i2[2];
			}
		});
		Set<Integer> settle = new HashSet<>();

		for (int i = 0; i < wells.length; i++) {
			q.offer(new int[] { 0, i + 1, wells[i] });
		}
		int cost = 0;

		while (!q.isEmpty()) {
			int[] edge = q.poll();

			if (settle.contains(edge[1])) {
				continue;
			}
			settle.add(edge[1]);
			cost += edge[2];
			List<int[]> adj = graph.getOrDefault(edge[1], new ArrayList<>());

			for (int[] ad : adj) {
				if (settle.contains(ad[0])) {
					continue;
				}
				q.offer(new int[] { edge[1], ad[0], ad[1] });
			}
		}
		return cost;
	}

	private void createGraph(int[][] pipes, Map<Integer, List<int[]>> g) {

		for (int[] p : pipes) {
			List<int[]> adj = g.getOrDefault(p[0], new ArrayList<>());
			adj.add(new int[] { p[1], p[2] });
			g.put(p[0], adj);
			adj = g.getOrDefault(p[1], new ArrayList<>());
			adj.add(new int[] { p[0], p[2] });
			g.put(p[1], adj);
		}
	}
}
