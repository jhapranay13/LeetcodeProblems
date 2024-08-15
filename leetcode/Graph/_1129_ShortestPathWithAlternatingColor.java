package leetcode.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Consider a directed graph, with nodes labelled 0, 1, ..., n-1. In
 *         this graph, each edge is either red or blue, and there could be
 *         self-edges or parallel edges.
 * 
 *         Each [i, j] in red_edges denotes a red directed edge from node i to
 *         node j. Similarly, each [i, j] in blue_edges denotes a blue directed
 *         edge from node i to node j.
 * 
 *         Return an array answer of length n, where each answer[X] is the
 *         length of the shortest path from node 0 to node X such that the edge
 *         colors alternate along the path (or -1 if such a path doesn't exist).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = [] Output:
 *         [0,1,-1]
 * 
 *         Example 2:
 * 
 *         Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]] Output:
 *         [0,1,-1]
 * 
 *         Example 3:
 * 
 *         Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]] Output:
 *         [0,-1,-1]
 * 
 *         Example 4:
 * 
 *         Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]] Output:
 *         [0,1,2]
 * 
 *         Example 5:
 * 
 *         Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]] Output:
 *         [0,1,1]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 100 red_edges.length <= 400 blue_edges.length <= 400
 *         red_edges[i].length == blue_edges[i].length == 2 0 <=
 *         red_edges[i][j], blue_edges[i][j] < n
 *
 */

public class _1129_ShortestPathWithAlternatingColor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
		Map<Integer, List<Integer>> redAdjacency = getAdjacency(red_edges);
		Map<Integer, List<Integer>> blueAdjacency = getAdjacency(blue_edges);
		int[] ansRed = new int[n];
		Arrays.fill(ansRed, Integer.MAX_VALUE);
		dijkstra(redAdjacency, blueAdjacency, 0, ansRed);
		int[] ansBlue = new int[n];
		Arrays.fill(ansBlue, Integer.MAX_VALUE);
		dijkstra(redAdjacency, blueAdjacency, 1, ansBlue);
		int[] ans = new int[n];

		for (int i = 0; i < n; i++) {
			ans[i] = Math.min(ansRed[i], ansBlue[i]);
			ans[i] = ans[i] == Integer.MAX_VALUE ? -1 : ans[i];
		}
		return ans;
	}

	private void dijkstra(Map<Integer, List<Integer>> redAdjacency, Map<Integer, List<Integer>> blueAdjacency,
			int startColor, int[] ans) {
		Deque<Integer> q = new LinkedList<>();
		q.offer(0);
		int distance = 0;
		boolean[] visitedRed = new boolean[ans.length];
		boolean[] visitedBlue = new boolean[ans.length];

		while (!q.isEmpty()) {
			int size = q.size();
			Map<Integer, List<Integer>> adjacency = startColor == 0 ? redAdjacency : blueAdjacency;
			boolean[] visited = startColor == 0 ? visitedRed : visitedBlue;
			startColor ^= 1; // ternary operator can also be used to toggel between 0 and 1

			while (size-- > 0) {
				int node = q.poll();

				if (visited[node]) {
					continue;
				}
				visited[node] = true;
				ans[node] = Math.min(ans[node], distance);
				List<Integer> childList = adjacency.getOrDefault(node, new ArrayList<>());

				for (int child : childList) {
					q.offer(child);
				}
			}
			distance++;
		}
	}

	private Map<Integer, List<Integer>> getAdjacency(int[][] edges) {
		Map<Integer, List<Integer>> adjacency = new HashMap<>();

		for (int[] edge : edges) {
			List<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
			list.add(edge[1]);
			adjacency.put(edge[0], list);
		}
		return adjacency;
	}
}
