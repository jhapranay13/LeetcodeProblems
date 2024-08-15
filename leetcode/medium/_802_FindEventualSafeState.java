package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         We start at some node in a directed graph, and every turn, we walk
 *         along a directed edge of the graph. If we reach a terminal node (that
 *         is, it has no outgoing directed edges), we stop.
 * 
 *         We define a starting node to be safe if we must eventually walk to a
 *         terminal node. More specifically, there is a natural number k, so
 *         that we must have stopped at a terminal node in less than k steps for
 *         any choice of where to walk.
 * 
 *         Return an array containing all the safe nodes of the graph. The
 *         answer should be sorted in ascending order.
 * 
 *         The directed graph has n nodes with labels from 0 to n - 1, where n
 *         is the length of graph. The graph is given in the following form:
 *         graph[i] is a list of labels j such that (i, j) is a directed edge of
 *         the graph, going from node i to node j.
 * 
 *         Example 1: ____________________________ \ |_______________
 *         _____|_______________ \ |_______ | / | ______ | V | V V/ | | V V 0 1
 *         2 3 4 5 6 |_______^ ^ |_______________|
 * 
 *         Illustration of graph Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 *         Output: [2,4,5,6] Explanation: The given graph is shown above.
 * 
 *         Example 2:
 * 
 *         Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]] Output: [4]
 * 
 * 
 *         Constraints:
 * 
 *         n == graph.length 1 <= n <= 104 0 <= graph[i].length <= n graph[i] is
 *         sorted in a strictly increasing order. The graph may contain
 *         self-loops. The number of edges in the graph will be in the range [1,
 *         4 * 104].
 *
 */

public class _802_FindEventualSafeState {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Integer> eventualSafeNodes(int[][] graph) {
		List<Integer> ans = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		boolean[] safe = new boolean[graph.length];

		for (int i = 0; i < graph.length; i++) {
			if (dfs(graph, i, visited, safe, ans)) {
				ans.add(i);
			}
		}
		return ans;
	}

	private boolean dfs(int[][] graph, int node, Set<Integer> visited, boolean[] safe, List<Integer> ans) {
		if (safe[node]) {
			return true;
		}

		if (visited.contains(node)) {
			return false;
		}
		visited.add(node);
		int[] adjacency = graph[node];

		for (int child : adjacency) {
			if (!dfs(graph, child, visited, safe, ans)) {
				return false;
			}
		}
		return safe[node] = true;
	}
}
