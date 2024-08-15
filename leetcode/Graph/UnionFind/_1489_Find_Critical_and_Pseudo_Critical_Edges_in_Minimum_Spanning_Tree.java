package leetcode.Graph.UnionFind;

import java.util.*;

/**
 *
 *
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.
 *
 * Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.
 *
 * Note that you can return the indices of the edges in any order.
 *
 *
 *
 * Example 1:
 *                  2
 *                /  \
 *              /     \
 *             1--- 0--3
 *              \  |  /
 *               \ |/
 *                4
 *
 * Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * Output: [[0,1],[2,3,4,5]]
 * Explanation: The figure above describes the graph.
 * The following figure shows all the possible MSTs:
 *                     2
 *  *                /  \
 *  *              /     \
 *  *             1--- 0  3
 *  *                    /
 *  *                  /
 *  *                4
 *
 *                     2
 *  *                /  \
 *  *              /     \
 *  *             1--- 0  3
 *  *                 |
 *  *                 |
 *  *                4
 *
 *                     2
 *  *                /
 *  *              /
 *  *             1--- 0--3
 *  *                    /
 *  *                  /
 *  *                4
 *
 *                     2
 *  *                /
 *  *              /
 *  *             1--- 0--3
 *  *                 |
 *  *                 |
 *  *                4
 *
 * Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
 * The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
 * Example 2:
 *
 *
 *
 * Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * Output: [[],[0,1,2,3]]
 * Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= ai < bi < n
 * 1 <= weighti <= 1000
 * All pairs (ai, bi) are distinct.
 *
 */

public class _1489_Find_Critical_and_Pseudo_Critical_Edges_in_Minimum_Spanning_Tree {
    class UnionFind {
        int[] cache;

        public UnionFind(int n) {
            this.cache = new int[n];

            for (int i = 1; i < n; i++) {
                cache[i] = i;
            }
        }

        public int find(int x) {

            if (cache[x] != x) {
                return find(cache[x]);
            }
            return x;
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                cache[py] = px;
                return true;
            }
            return false;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> criticalEdge = new ArrayList<>();
        List<Integer> pseudoCriticalEdge = new ArrayList<>();
        Map<int[], Integer> edgePosMap = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            edgePosMap.put(edges[i], i);
        }
        UnionFind uf = new UnionFind(n);
        Arrays.sort(edges, (a, b) -> a[2] - b[2]); // Sort by min cost
        int minCost = 0;
        int idx = 0;

        while (idx < edges.length) {
            int edge[] = edges[idx];

            if (uf.union(edge[0], edge[1])) {
                minCost += edge[2];
            }
            idx++;
        }

        for (int i = 0; i < edges.length; i++) {
            int[] currEdge = edges[i];
            int index = edgePosMap.get(currEdge);
            UnionFind includeUf = new UnionFind(n);
            UnionFind excludeUf = new UnionFind(n);
            int includeCost = currEdge[2];
            includeUf.union(currEdge[0], currEdge[1]);
            int excludeCost = 0;

            for (int j = 0; j < edges.length; j++) {

                if (i == j) {
                    continue;
                }
                int edg[] = edges[j];

                if (excludeUf.union(edg[0], edg[1])) {
                    excludeCost += edg[2];
                }

                if (includeUf.union(edg[0], edg[1])) {
                    includeCost += edg[2];
                }
            }

            if (excludeCost != minCost) {
                criticalEdge.add(index);
            } else if (includeCost == minCost) {
                pseudoCriticalEdge.add(index);
            }
        }
        ans.add(criticalEdge);
        ans.add(pseudoCriticalEdge);
        return ans;
    }
}
