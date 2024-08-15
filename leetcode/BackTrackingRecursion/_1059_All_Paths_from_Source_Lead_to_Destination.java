package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
 *
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 *
 *
 *
 * Example 1:
 *                  0
 *                 / \
 *                1   2
 *
 * Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
 * Output: false
 * Explanation: It is possible to reach and get stuck on both node 1 and node 2.
 * Example 2:
 *
 *                  0
 *                /   \
 *               1     3
 *              ^ \
 *              |__2
 *
 *
 * Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 * Output: false
 * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
 * Example 3:
 *                   0
 *                 /   \
 *                2     1
 *                 \  /
 *                  3
 *
 * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 * 0 <= edges.length <= 10^4
 * edges.length == 2
 * 0 <= ai, bi <= n - 1
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 * The given graph may have self-loops and parallel edges.
 *
 */

public class _1059_All_Paths_from_Source_Lead_to_Destination {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            int origin = edge[0];
            int dest = edge[1];
            List<Integer> list = adj.getOrDefault(origin, new ArrayList<>());
            list.add(dest);
            adj.put(origin, list);
        }
        boolean[] v = new boolean[n];
        boolean[] canReachDest = new boolean[n];
        return recur(adj, v, canReachDest, destination, source);
    }

    public boolean recur(Map<Integer, List<Integer>> adj, boolean[] v,
                         boolean[] canReachDest, int dest, int curr) {

        if (v[curr]) {
            return canReachDest[curr];
        }
        v[curr] = true;
        List<Integer> list = adj.get(curr);

        if (list == null) {
            return canReachDest[curr] = curr == dest;
        }

        for (int next : list) {

            if (!recur(adj, v, canReachDest, dest, next)) {
                return false;
            }
        }
        return canReachDest[curr] = true;
    }
}
