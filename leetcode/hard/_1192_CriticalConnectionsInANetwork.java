package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         There are n servers numbered from 0 to n - 1 connected by undirected
 *         server-to-server connections forming a network where connections[i] =
 *         [ai, bi] represents a connection between servers ai and bi. Any
 *         server can reach other servers directly or indirectly through the
 *         network.
 * 
 *         A critical connection is a connection that, if removed, will make
 *         some servers unable to reach some other server.
 * 
 *         Return all critical connections in the network in any order.
 * 
 * 
 * 
 *         Example 1:
 * 								2
 * 							   /|
 *                            / |
 *                           / 	|
 *                          1---0
 *                          |
 *                          |
 *                          3
 *                          
 *                          
 *         Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]] Output: [[1,3]]
 *         Explanation: [[3,1]] is also accepted. Example 2:
 * 
 *         Input: n = 2, connections = [[0,1]] Output: [[0,1]]
 * 
 * 
 *         Constraints:
 * 
 *         2 <= n <= 105 n - 1 <= connections.length <= 105 0 <= ai, bi <= n - 1
 *         ai != bi There are no repeated connections.
 *
 */

public class _1192_CriticalConnectionsInANetwork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Tarjan's Algorithm
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		Map<Integer, List<Integer>> adjMap = new HashMap<>();
		buildAdj(connections, adjMap);
		List<List<Integer>> ans = new ArrayList<>();
		int v[] = new int[n];
		Arrays.fill(v, -1);
		dfs(adjMap, v, -1, 0, ans, 0);
		return ans;
	}

	private void dfs(Map<Integer, List<Integer>> adjMap, int[] v, int prev, int curr, List<List<Integer>> ans,
			int discoveryTime) {
		int currTime = discoveryTime;
		v[curr] = currTime;
		List<Integer> list = adjMap.get(curr);
		int childCount = 0;
		boolean flag = false;

		for (int ad : list) {

			if (ad == prev) {
				continue;
			} else if (v[ad] == -1) {
				dfs(adjMap, v, curr, ad, ans, ++discoveryTime);
				v[curr] = Math.min(v[curr], v[ad]);

				if (v[ad] >= currTime) {
					flag = true;
				}
				childCount++;
			} else {
				v[curr] = Math.min(v[curr], v[ad]);
			}
		}

		if (prev != -1 && currTime == v[curr]) {
			List<Integer> p = new ArrayList<>();
			p.add(prev);
			p.add(curr);
			ans.add(p);
		}
	}

	private void buildAdj(List<List<Integer>> conn, Map<Integer, List<Integer>> adjMap) {

		for (List<Integer> list : conn) {
			int op1 = list.get(0);
			int op2 = list.get(1);
			List<Integer> l = adjMap.getOrDefault(op1, new ArrayList<>());
			l.add(op2);
			adjMap.put(op1, l);
			l = adjMap.getOrDefault(op2, new ArrayList<>());
			l.add(op1);
			adjMap.put(op2, l);
		}
	}
}
