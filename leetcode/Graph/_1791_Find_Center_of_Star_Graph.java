package leetcode.Graph;

import java.util.*;

/**
 *
 * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
 *
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
 *
 *
 *
 * Example 1:
 *                                      4
 *                                      |
 *                                      |
 *                                      2
 *                                     / \
 *                                   /    \
 *                                  1      3
 *
 *
 * Input: edges = [[1,2],[2,3],[4,2]]
 * Output: 2
 * Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
 * Example 2:
 *
 * Input: edges = [[1,2],[5,1],[1,3],[1,4]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 3 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * The given edges represent a valid star graph.
 *
 */

public class _1791_Find_Center_of_Star_Graph {
    public int findCenter(int[][] edges) {
        int incomingOutgoing[] = new int[edges.length + 2];
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int[] edge : edges) {
            incomingOutgoing[edge[0]]++;
            incomingOutgoing[edge[1]]++;
            List<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            adjacency.put(edge[0], list);
            list = adjacency.getOrDefault(edge[1], new ArrayList<>());
            list.add(edge[0]);
            adjacency.put(edge[1], list);
        }
        Deque<Integer> q = new LinkedList<>();
        Set<Integer> v = new HashSet<>();


        for (int i = 0; i < incomingOutgoing.length; i++) {

            if (incomingOutgoing[i] == 1) {
                q.offer(i);
                v.add(i);
            }
        }
        int ans = -1;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int node = q.poll();
                List<Integer> adj = adjacency.getOrDefault(node, new ArrayList<>());

                for (int next : adj) {

                    if (v.add(next)) {
                        q.offer(next);
                    }
                }
            }

            if (q.size() == 1) {
                ans = q.poll();
                break;
            }
        }
        return ans;
    }
}
