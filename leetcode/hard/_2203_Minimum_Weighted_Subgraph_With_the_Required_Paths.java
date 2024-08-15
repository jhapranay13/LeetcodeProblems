package leetcode.hard;

import java.util.*;

/**
 *
 *
 * You are given an integer n denoting the number of nodes of a weighted directed graph. The nodes are numbered from 0 to n - 1.
 *
 * You are also given a 2D integer array edges where edges[i] = [fromi, toi, weighti] denotes that there exists a directed edge from fromi to toi with weight weighti.
 *
 * Lastly, you are given three distinct integers src1, src2, and dest denoting three distinct nodes of the graph.
 *
 * Return the minimum weight of a subgraph of the graph such that it is possible to reach dest from both src1 and src2 via a set of edges of this subgraph. In case such a subgraph does not exist, return -1.
 *
 * A subgraph is a graph whose vertices and edges are subsets of the original graph. The weight of a subgraph is the sum of weights of its constituent edges.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
 * Output: 9
 * Explanation:
 * The above figure represents the input graph.
 * The blue edges represent one of the subgraphs that yield the optimal answer.
 * Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer. It is not possible to get a subgraph with less weight satisfying all the constraints.
 * Example 2:
 *
 *
 * Input: n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
 * Output: -1
 * Explanation:
 * The above figure represents the input graph.
 * It can be seen that there does not exist any path from node 1 to node 2, hence there are no subgraphs satisfying all the constraints.
 *
 *
 * Constraints:
 *
 * 3 <= n <= 105
 * 0 <= edges.length <= 105
 * edges[i].length == 3
 * 0 <= fromi, toi, src1, src2, dest <= n - 1
 * fromi != toi
 * src1, src2, and dest are pairwise distinct.
 * 1 <= weight[i] <= 105
 *
 *
 */

public class _2203_Minimum_Weighted_Subgraph_With_the_Required_Paths {
    // Get distance from all src1, src2 and dest to all points.
    // check the min weight and that's the answer
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        Map<Integer, List<int[]>> revAdj = new HashMap<>();

        for (int[] data : edges) {
            int src = data[0];
            int dst = data[1];
            int weight = data[2];
            List<int[]> list = adj.getOrDefault(src, new ArrayList<>());
            list.add(new int[] {dst, weight});
            adj.put(src, list);
            list = revAdj.getOrDefault(dst, new ArrayList<>());
            list.add(new int[] {src, weight});
            revAdj.put(dst, list);
        }
        long[] distSrc1 = new long[n];
        long[] distSrc2 = new long[n];
        long[] distDest = new long[n];
        Arrays.fill(distSrc1, -1);
        Arrays.fill(distSrc2, -1);
        Arrays.fill(distDest, -1);

        dijkstra(adj, src1, distSrc1);
        dijkstra(adj, src2, distSrc2);
        dijkstra(revAdj, dest, distDest);
        long ans = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (distSrc1[i] != -1 && distSrc2[i] != -1 && distDest[i] != -1) {
                ans = Math.min(ans, distSrc1[i] + distSrc2[i] + distDest[i]);
            }
        }
        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    private void dijkstra(Map<Integer, List<int[]>> adj, int src, long[] dist) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[] {src, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            if (dist[(int)curr[0]] == -1) {
                dist[(int)curr[0]] = curr[1];
            } else {
                continue;
            }
            List<int[]> list = adj.getOrDefault((int)curr[0], new ArrayList<>());

            for (int[] next : list) {

                if (dist[(int)next[0]] == -1) {
                    long nextDist = curr[1] + next[1];
                    pq.offer(new long[] {next[0], nextDist});
                }
            }
        }
    }
}
