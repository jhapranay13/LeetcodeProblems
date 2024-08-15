package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
 *
 * A connected trio is a set of three nodes where there is an edge between every pair of them.
 *
 * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
 *
 * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
 *
 *
 *
 * Example 1:
 *
 *           4 --- 1 ---- 2 ---- 5
 *                 |    /
 *                 |  /
 *                  3 ---- 6
 *
 * Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * Output: 3
 * Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.
 *
 * Example 2:
 *
 *          4 --- 1      2 --- 5
 *           \   /       | \  |
 *            \/         |___\|
 *            3          6    7
 *
 * Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * Output: 0
 * Explanation: There are exactly three trios:
 * 1) [1,4,3] with degree 0.
 * 2) [2,5,6] with degree 2.
 * 3) [5,6,7] with degree 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 400
 * edges[i].length == 2
 * 1 <= edges.length <= n * (n-1) / 2
 * 1 <= ui, vi <= n
 * ui != vi
 * There are no repeated edges.
 *
 */

public class _1761_Minimum_Degree_of_a_Connected_Trio_in_a_Graph {
    public int minTrioDegree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjacency = new HashMap<>();
        Map<Integer, Integer> degree = new HashMap<>();

        for (int[] edge : edges) {
            Set<Integer> adj = adjacency.getOrDefault(edge[0], new HashSet<>());
            adj.add(edge[1]);
            adjacency.put(edge[0], adj);
            adj = adjacency.getOrDefault(edge[1], new HashSet<>());
            adj.add(edge[0]);
            adjacency.put(edge[1], adj);
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {

            for (int j = i + 1; j <= n; j++) {

                if (adjacency.getOrDefault(i, new HashSet<>()).contains(j)) {

                    for (int k = j + 1; k <= n; k++) {

                        if (adjacency.getOrDefault(j, new HashSet<>()).contains(k) &&
                                adjacency.getOrDefault(k, new HashSet<>()).contains(i)) {
                            ans = Math.min(ans, adjacency.get(i).size() + adjacency.get(j).size() +
                                    adjacency.get(k).size() - 6);
                        }
                    }
                }
            }
        }

        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
}
