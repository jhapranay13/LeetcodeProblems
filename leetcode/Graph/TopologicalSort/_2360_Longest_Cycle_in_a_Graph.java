package leetcode.Graph.TopologicalSort;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.
 *
 * Return the length of the longest cycle in the graph. If no cycle exists, return -1.
 *
 * A cycle is a path that starts and ends at the same node.
 *
 *
 *
 * Example 1:
 *              1          4
 *                \      / ^
 *                  V  V   |
 *                   3     |
 *                  ^  \   |
 *                /      V |
 *               0         2
 *
 * Input: edges = [3,3,4,2,3]
 * Output: 3
 * Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
 * The length of this cycle is 3, so 3 is returned.
 * Example 2:
 *
 *      3    0
 *      | ^  |
 *      V   \V
 *      1    2
 *
 * Input: edges = [2,-1,3,1]
 * Output: -1
 * Explanation: There are no cycles in this graph.
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 2 <= n <= 10^5
 * -1 <= edges[i] < n
 * edges[i] != i
 *
 */

public class _2360_Longest_Cycle_in_a_Graph {

    public int longestCycle(int[] edges) {
        int[] visited = new int[edges.length];

        for (int i = 0; i < edges.length; i++) {
            Map<Integer, Integer> discoveryTime = new HashMap<>();

            if (visited[i] == 0 && edges[i] != -1) {
                visited[i] = 1;
                discoveryTime.put(i, 1);
                recur(edges, visited, i, discoveryTime);
            }
        }
        return ans;
    }
    private int ans = -1;

    private void recur(int[] edges, int[] visited, int node, Map<Integer, Integer> discoveryTime) {
        int next = edges[node];

        if (next != -1) {
            if (visited[next] == 0) {
                visited[next] = 1;
                discoveryTime.put(next, discoveryTime.get(node) + 1);
                recur(edges, visited, next, discoveryTime);
            } else if (discoveryTime.containsKey(next)) {
                ans = Math.max(ans, discoveryTime.get(node) - discoveryTime.get(next) + 1);
            }
        }
    }
    //=============================================================================================
    // Kahn's Algo
    // if there is cycle then all the nodes won't be visited
    public int longestCycle1(int[] edges) {
        int[] incoming = new int[edges.length];

        for (int edge : edges) {

            if (edge != -1) {
                incoming[edge]++;
            }
        }
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < incoming.length; i++) {

            if (incoming[i] == 0) {
                q.offer(i);
            }
        }
        boolean[] v = new boolean[edges.length];

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int node = q.poll();
                v[node] = true;
                int next = edges[node];

                if (next != -1 && !v[next]) {
                    incoming[next]--;

                    if (incoming[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        int ans = -1;

        for (int i = 0; i < edges.length; i++) {

            if (!v[i]) {
                int next = edges[i];
                int count = 1;
                v[i] = true;

                while (next != i) {
                    v[next] = true;
                    count++;
                    next = edges[next];
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

}
