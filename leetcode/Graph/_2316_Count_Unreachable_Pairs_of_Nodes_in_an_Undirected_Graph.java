package leetcode.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * Return the number of pairs of different nodes that are unreachable from each other.
 *
 *
 *
 * Example 1:
 *
 *          0 ------- 1
 *            \     /
 *             \  /
 *               2
 *
 * Input: n = 3, edges = [[0,1],[0,2],[1,2]]
 * Output: 0
 * Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
 * Example 2:
 *
 *          0--------2
 *          |        |
 *          5--------4
 *
 *          3    1-----6
 *
 * Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
 * Output: 14
 * Explanation: There are 14 pairs of nodes that are unreachable from each other:
 * [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
 * Therefore, we return 14.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * There are no repeated edges.
 *
 */

public class _2316_Count_Unreachable_Pairs_of_Nodes_in_an_Undirected_Graph {
    //Union Find Approach
    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];

            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {

            if (x != parent[x]) {
                return parent[x] = find(parent[x]);
            }
            return x;
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                parent[py] = px;
            }
        }
    }

    public long countPairs(int n, int[][] edges) {
        Map<Integer, Integer> parentSizeMap = new HashMap<>();
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        for (int i = 0; i < n; i++) {
            int pi = uf.find(i);
            parentSizeMap.put(pi, parentSizeMap.getOrDefault(pi, 0) + 1);
        }
        int totalSize = n;
        long ans = 0;

        for (int key : parentSizeMap.keySet()) {
            long componentSize = parentSizeMap.get(key);
            ans += componentSize * (totalSize - componentSize);
            totalSize -= componentSize;
        }
        return ans;
    }
    //=============================================================================================
    // DFS approach. can also be done using BFS
    public long countPairs1(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> adj = adjMap.getOrDefault(edge[0], new ArrayList<>());
            adj.add(edge[1]);
            adjMap.put(edge[0], adj);
            adj = adjMap.getOrDefault(edge[1], new ArrayList<>());
            adj.add(edge[0]);
            adjMap.put(edge[1], adj);
        }
        boolean[] v = new boolean[n];
        int totalSize = n;
        long ans = 0;

        for (int i = 0; i < n; i++) {

            if (!v[i]) {
                long componentSize = recur(adjMap, v, i);
                ans += componentSize * (totalSize - componentSize);
                totalSize -= componentSize;
            }
        }
        return ans;
    }

    private long recur(Map<Integer, List<Integer>> adjMap, boolean[] v, int node) {

        if (v[node]) {
            return 0;
        }
        long ans = 1;
        List<Integer> adj = adjMap.getOrDefault(node, new ArrayList<>());
        v[node] = true;

        for (int next : adj) {
            ans += recur(adjMap, v, next);
        }
        return ans;
    }
}
