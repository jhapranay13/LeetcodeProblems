package leetcode.easy;

import java.util.*;

/**
 *
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 *
 *
 *
 * Example 1:
 *                 0 -------- 1
 *                  \       /
 *                   \    /
 *                    \ /
 *                     2
 *
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 *
 * Example 2:
 *
 *            1                     3
 *          /                       | \
 *         0                        |  5
 *          \                       | /
 *           2                       4
 *
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 *
 */

public class _1971_Find_if_Path_Exists_in_Graph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            adjacency.put(edge[0], list);
            list = adjacency.getOrDefault(edge[1], new ArrayList<>());
            list.add(edge[0]);
            adjacency.put(edge[1], list);
        }
        Set<Integer> v = new HashSet<>();
        Deque<Integer> q = new LinkedList<>();
        q.offer(source);

        while (!q.isEmpty()) {
            int node = q.poll();

            if (node == destination) {
                return true;
            }
            v.add(node);
            List<Integer> adj = adjacency.get(node);

            for (int ad : adj) {

                if (!v.contains(ad)) {
                    q.offer(ad);
                }
            }
        }
        return false;
    }
}
