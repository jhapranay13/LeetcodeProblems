package leetcode.Graph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 *
 * You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 *
 *
 * Example 1:
 *                          0
 *                      /   |   \
 *                     1    2    3
 *
 * Input: graph = [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1 -> 0 -> 2 -> 0  -> 3]
 * Example 2:
 *
 *
 * Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length < n
 * graph[i] does not contain i.
 * If graph[a] contains b, then graph[b] contains a.
 * The input graph is always connected.
 *
 */

public class _847_Shortest_Path_Visiting_All_Nodes {
    // can also be done using Bit Masking
    public int shortestPathLength(int[][] graph) {
        Deque<String[]> q = new LinkedList<>();
        StringBuilder finalState = new StringBuilder();
        StringBuilder initState = new StringBuilder();

        for (int i = 0; i < graph.length; i++) {
            finalState.append("1");
            initState.append("0");
        }
        // Visited state
        Set<String> v = new HashSet<>();

        for (int i = 0; i < graph.length; i++) {
            String node = "" + i;
            // Tracing the path from the origin to nodes visited
            String state = node;
            initState.setCharAt(i, '1');
            q.offer(new String[] {node, initState.toString()});
            // Unique state to recognize the node and it's current state e.g.
            // how many nodes visited before this
            v.add(initState + ":" + node);
            initState.setCharAt(i, '0');
        }
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String[] data = q.poll();
                String node = data[0];
                String state = data[1];

                if (state.equals(finalState.toString())) {
                    return ans;
                }
                int[] children = graph[Integer.parseInt(node)];

                for (int child : children) {
                    StringBuilder nsHolder = new StringBuilder(state);
                    nsHolder.setCharAt(child, '1');
                    String nextState = nsHolder.toString() + ":" + child;

                    if (!v.add(nextState)) {
                        continue;
                    }
                    q.offer(new String[] {"" + child, nsHolder.toString()});
                }
            }
            ans++;
        }
        return -1;
    }
}
