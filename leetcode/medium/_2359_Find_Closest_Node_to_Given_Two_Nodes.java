package leetcode.medium;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.
 *
 * You are also given two integers node1 and node2.
 *
 * Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.
 *
 * Note that edges may contain cycles.
 *
 *
 *
 * Example 1:
 *
 *              1 -->  2  <--  0
 *                     |
 *                     V
 *                     0
 *
 * Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
 * The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
 * Example 2:
 *
 *              1 <-- 0
 *              \
 *               V
 *               2
 *
 * Input: edges = [1,2,-1], node1 = 0, node2 = 2
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
 * The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 2 <= n <= 10^5
 * -1 <= edges[i] < n
 * edges[i] != i
 * 0 <= node1, node2 < n
 *
 *
 */

public class _2359_Find_Closest_Node_to_Given_Two_Nodes {
    // BFS
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] dist1 = new int[edges.length];
        int[] dist2 = new int[edges.length];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);
        bfs(node1, edges, dist1);
        bfs(node2, edges, dist2);
        int minMaxDist = Integer.MAX_VALUE;
        int node = -1;

        for (int i = 0; i < edges.length; i++) {

            if (minMaxDist > Math.max(dist1[i], dist2[i])) {
                minMaxDist = Math.max(dist1[i], dist2[i]);
                node = i;
            }
        }
        return node;
    }

    private void bfs(int node, int[] edges, int[] dist) {
        Deque<Integer> q = new LinkedList<>();
        q.offer(node);
        dist[node] = 0;
        boolean[] visited = new boolean[edges.length];

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            int child = edges[curr];

            if (child != -1 && !visited[child]) {
                dist[child] = 1 + dist[curr];
                q.offer(child);
            }
        }
    }
    //=============================================================================================
    // DFS
    public int closestMeetingNode1(int[] edges, int node1, int node2) {
        int[] dist1 = new int[edges.length];
        int[] dist2 = new int[edges.length];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);
        boolean[] v = new boolean[edges.length];
        dfs(node1, edges, dist1, v, 0);
        v = new boolean[edges.length];
        dfs(node2, edges, dist2, v, 0);
        int minMaxDist = Integer.MAX_VALUE;
        int node = -1;

        for (int i = 0; i < edges.length; i++) {

            if (minMaxDist > Math.max(dist1[i], dist2[i])) {
                minMaxDist = Math.max(dist1[i], dist2[i]);
                node = i;
            }
        }
        return node;
    }

    private void dfs(int node, int[] edges, int[] dist, boolean[] v, int currDist) {

        if (node == -1 || v[node]) {
            return;
        }
        v[node] = true;
        dist[node] = currDist;
        int child = edges[node];
        dfs(child, edges, dist, v, currDist + 1);
    }
}
