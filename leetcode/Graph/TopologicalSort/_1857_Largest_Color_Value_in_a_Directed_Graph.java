package leetcode.Graph.TopologicalSort;

import java.util.*;

/**
 *
 *
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 *
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 *
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 *
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 *
 *
 *
 * Example 1:
 *
 *          0 -> 2 -> 3 -> 4
 *          |
 *          V
 *          1
 *
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 * Example 2:
 *
 *          0 -
 *          ^_|
 *
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 *
 *
 * Constraints:
 *
 * n == colors.length
 * m == edges.length
 * 1 <= n <= 10^5
 * 0 <= m <= 10^5
 * colors consists of lowercase English letters.
 * 0 <= aj, bj < n
 *
 *
 */

public class _1857_Largest_Color_Value_in_a_Directed_Graph {
    public int largestPathValue(String colors, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] incoming = new int[colors.length()];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            incoming[to]++;
            List<Integer> list = adj.getOrDefault(from, new ArrayList<>());
            list.add(to);
            adj.put(from, list);
        }
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < incoming.length; i++) {

            if (incoming[i] == 0) {
                q.offer(i);
            }
        }
        int countVisited = 0;
        int[][] nodeColorFreq = new int[incoming.length][26];
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int node = q.poll();
                countVisited++;
                char color = colors.charAt(node);
                nodeColorFreq[node][color - 'a']++;
                ans = Math.max(ans, nodeColorFreq[node][color - 'a']);
                List<Integer> adjList = adj.getOrDefault(node, new ArrayList<>());

                for (int next : adjList) {

                    for (int i = 0; i < 26; i++) {
                        nodeColorFreq[next][i] =
                                Math.max(nodeColorFreq[node][i], nodeColorFreq[next][i]);
                    }
                    incoming[next]--;

                    if (incoming[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
        return countVisited < colors.length() ? -1 : ans;
    }
}
