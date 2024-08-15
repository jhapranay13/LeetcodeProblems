package leetcode.hard;

import java.util.*;

/**
 *
 * You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.
 *
 * The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph, and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.
 *
 * To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti-1, xcnti], [xcnti, vi].
 *
 * In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.
 *
 * Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.
 *
 *
 *
 * Example 1:
 *
 *       0 ---------------------- 1             0 --x-x-x-x-x-x-x-x-x-x-x-- 1
 *        \                      /                \                       /
 *          \                  /                    \                   /
 *            \              /                        x               x
 *              \          /                           \           /
 *                \      /                               \       x
 *                  \  /                                   \   /
 *                   2                                       2
 *
 * Input: edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
 * Output: 13
 * Explanation: The edge subdivisions are shown in the image above.
 * The nodes that are reachable are highlighted in yellow.
 * Example 2:
 *
 * Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
 * Output: 23
 * Example 3:
 *
 * Input: edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
 * Output: 1
 * Explanation: Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
 *
 *
 * Constraints:
 *
 * 0 <= edges.length <= min(n * (n - 1) / 2, 104)
 * edges[i].length == 3
 * 0 <= ui < vi < n
 * There are no multiple edges in the graph.
 * 0 <= cnti <= 104
 * 0 <= maxMoves <= 109
 * 1 <= n <= 3000
 *
 */

public class _882_Reachable_Nodes_In_Subdivided_Graph {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<String, Integer> leftEdgeMap = new HashMap<>();

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            int count = edge[2];
            String key1 = node1 + ", " + node2;
            String key2 = node2 + ", " + node1;
            leftEdgeMap.put(key1, count);
            leftEdgeMap.put(key2, count);

            List<int[]> adj = graph.getOrDefault(node1, new ArrayList<>());
            adj.add(new int[] {node2, count});
            graph.put(node1, adj);
            adj = graph.getOrDefault(node2, new ArrayList<>());
            adj.add(new int[] {node1, count});
            graph.put(node2, adj);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {0, 0});
        boolean[] v = new boolean[n];
        int ans = 0;

        while (!pq.isEmpty()) {
            int node = pq.peek()[0];
            int distance = pq.peek()[1];
            pq.poll();

            if (v[node] || !graph.containsKey(node)) {
                continue;
            }
            v[node] = true;
            ans++;
            List<int[]> adj = graph.get(node);

            for (int[] nextNodeData : adj) {
                int nextNode = nextNodeData[0];
                String key1 = node + ", " + nextNode;
                String key2 = nextNode + ", " + node;
                int remaining = leftEdgeMap.get(key1);

                if (distance + remaining + 1 <= maxMoves) {
                    // Nothing left between the node
                    leftEdgeMap.put(key1, 0);
                    leftEdgeMap.put(key2, 0);
                    pq.offer(new int[] {nextNode, distance + remaining + 1});
                    ans += remaining;
                } else {
                    int shortOfMaxMoves = Math.max(maxMoves - distance, 0);
                    leftEdgeMap.put(key1, remaining - shortOfMaxMoves);
                    leftEdgeMap.put(key2, remaining - shortOfMaxMoves);
                    ans += shortOfMaxMoves;
                }
            }
        }
        return ans == 0 ? 1 : ans;
    }
}
