package leetcode.BFS;

import java.util.*;

/**
 *
 * Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1. In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected. The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 *
 * Return the probability that after t seconds the frog is on the vertex target. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *                   F  1
 *                   /  |  \
 *                F 2   7   3
 *                /  \       \
 *             F 4    6      5
 *
 * Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 * Output: 0.16666666666666666
 * Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 probability to the vertex 2 after second 1 and then jumping with 1/2 probability to vertex 4 after second 2. Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666.
 *
 *  Example 2:
 *                      F  1
 *  *                   /  |  \
 *  *                  2  F7   3
 *  *                /  \       \
 *  *               4    6      5
 *
 * Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 * Output: 0.3333333333333333
 * Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 = 0.3333333333333333 probability to the vertex 7 after second 1.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * 1 <= t <= 50
 * 1 <= target <= n
 *
 */

public class _1377_Frog_Position_After_T_Seconds {
    // DFS Approach
    public double frogPosition(int n, int[][] edges, int t, int target) {

        if (n == 1) {
            return 1.0;
        }
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int[] edge : edges) {
            int point1 = edge[0];
            int point2 = edge[1];
            List<Integer> adj = adjacency.getOrDefault(point1, new ArrayList<>());
            adj.add(point2);
            adjacency.put(point1, adj);
            adj = adjacency.getOrDefault(point2, new ArrayList<>());
            adj.add(point1);
            adjacency.put(point2, adj);
        }
        Set<Integer> v = new HashSet<>();
        v.add(1);
        return recur(adjacency, v, 1, 1.0, target, t);
    }

    private double recur( Map<Integer, List<Integer>> adjacency, Set<Integer> v, int node, double probability, int target, int seconds) {
        List<Integer> adj = adjacency.getOrDefault(node, new ArrayList<>());
        //if Seconds is not zero then it would either move forward or keep jumping if the node is terminal
        if ((node != 1 &&adj.size() == 1) || seconds == 0) {

            if (node == target) {
                return probability;
            }
            return 0;
        }
        int size = adj.size();

        for (int nextNode : adj) {

            if (v.contains(nextNode)) {
                size--;
            }
        }
        double ans = 0;

        for (int nextNode : adj) {

            if (!v.contains(nextNode)) {
                v.add(nextNode);
                ans = recur(adjacency, v, nextNode, (double)probability / size, target, seconds - 1);

                if (ans != 0) {
                    return ans;
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // BFS approach
    public double frogPosition1(int n, int[][] edges, int t, int target) {

        if (n == 1) {
            return 1.0;
        }
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int[] edge : edges) {
            int point1 = edge[0];
            int point2 = edge[1];
            List<Integer> adj = adjacency.getOrDefault(point1, new ArrayList<>());
            adj.add(point2);
            adjacency.put(point1, adj);
            adj = adjacency.getOrDefault(point2, new ArrayList<>());
            adj.add(point1);
            adjacency.put(point2, adj);
        }
        Set<Integer> v = new HashSet<>();
        v.add(1);
        Deque<Integer> q = new LinkedList<>();
        q.offer(1);
        double[] prob = new double[n + 1];
        prob[1] = 1.0;

        while (!q.isEmpty() && t-- > 0) {
            int iter = q.size();

            while (iter-- > 0) {
                int node = q.poll();
                List<Integer> adj = adjacency.getOrDefault(node, new ArrayList<>());
                int size = adj.size();

                for (int nextNode : adj) {

                    if (v.contains(nextNode)) {
                        size--;
                    }
                }

                for (int nextNode : adj) {

                    if (!v.contains(nextNode)) {
                        v.add(nextNode);
                        prob[nextNode] =  prob[node] / size;
                        q.offer(nextNode);
                    }
                }

                // If it cannot stop here then the probability to be here after t seconds is zero
                if (size > 0) {
                    prob[node] = 0;
                }
            }

        }
        return prob[target];
    }
}
