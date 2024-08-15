package leetcode.Sorting;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 *
 * There is an undirected graph consisting of n nodes numbered from 0 to n - 1. You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.
 *
 * You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * A star graph is a subgraph of the given graph having a center node containing 0 or more neighbors. In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.
 *
 * The image below shows star graphs with 3 and 4 neighbors respectively, centered at the blue node.
 *
 *          o(blue)                   o
 *        / | \                       |
 *       o  o  o               o----- o(blue) --- o
 *                                    |
 *                                    o
 *
 * The star sum is the sum of the values of all the nodes present in the star graph.
 *
 * Given an integer k, return the maximum star sum of a star graph containing at most k edges.
 *
 *
 *
 * Example 1:
 *
 *          0(1) ------- 1(2)*
 *                     /   \
 *                   3(4)*  2(3)
 *                /   |   \
 *          6(-20) 5(-10)  4(10)*
 *
 * Input: vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
 * Output: 16
 * Explanation: The above diagram represents the input graph.
 * The star graph with the maximum star sum is denoted by blue. It is centered at 3 and includes its neighbors 1 and 4.
 * It can be shown it is not possible to get a star graph with a sum greater than 16.
 * Example 2:
 *
 * Input: vals = [-5], edges = [], k = 0
 * Output: -5
 * Explanation: There is only one possible star graph, which is node 0 itself.
 * Hence, we return -5.
 *
 *
 * Constraints:
 *
 * n == vals.length
 * 1 <= n <= 105
 * -10^4 <= vals[i] <= 10^4
 * 0 <= edges.length <= min(n * (n - 1) / 2, 10^5)
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 0 <= k <= n - 1
 *
 *
 */

public class _2497_Maximum_Star_Sum_of_a_Graph {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        PriorityQueue<Integer>[] pqs = new PriorityQueue[n];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            pqs[i] = new PriorityQueue<Integer>(Collections.reverseOrder()); //max First
            ans = Math.max(ans, vals[i]);
        }

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            pqs[node1].offer(vals[node2]);
            pqs[node2].offer(vals[node1]);
        }

        for (int i = 0; i < n; i++) {
            int count = k;
            PriorityQueue<Integer> pq = pqs[i];
            int sum = vals[i];

            while (!pq.isEmpty() && count-- > 0) {
                sum += pq.poll();
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }
}
