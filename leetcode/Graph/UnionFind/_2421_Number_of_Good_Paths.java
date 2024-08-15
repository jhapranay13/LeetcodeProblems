package leetcode.Graph.UnionFind;

import java.util.*;

/**
 *
 *
 * There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
 *
 * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * A good path is a simple path that satisfies the following conditions:
 *
 * The starting node and the ending node have the same value.
 * All nodes between the starting node and the ending node have values less than or equal to the starting node (i.e. the starting node's value should be the maximum value along the path).
 * Return the number of distinct good paths.
 *
 * Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0. A single node is also considered as a valid path.
 *
 *
 *
 * Example 1:
 *                          0(1)
 *                         /    \
 *                        1(3)   2(2)
 *                              /    \
 *                             3(1)   4(3)
 *
 *
 * Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
 * Output: 6
 * Explanation: There are 5 good paths consisting of a single node.
 * There is 1 additional good path: 1 -> 0 -> 2 -> 4.
 * (The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.)
 * Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
 *
 * Example 2:
 *                      0(1)
 *                     /
 *                   1(1)
 *                   /
 *                 2(2)
 *                /   \
 *              3(2)   4(3)
 *
 * Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
 * Output: 7
 * Explanation: There are 5 good paths consisting of a single node.
 * There are 2 additional good paths: 0 -> 1 and 2 -> 3.
 *
 * Example 3:
 *                      0(1)
 *
 * Input: vals = [1], edges = []
 * Output: 1
 * Explanation: The tree consists of only one node, so there is one good path.
 *
 *
 * Constraints:
 *
 * n == vals.length
 * 1 <= n <= 3 * 10^4
 * 0 <= vals[i] <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 *
 */

public class _2421_Number_of_Good_Paths {
    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            this.parent = new int[size];

            for(int i = 0; i < size; i++) {
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

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        TreeMap<Integer, List<Integer>> valueNodeMap = new TreeMap<>();

        for (int i = 0; i < vals.length; i++) {
            List<Integer> list = valueNodeMap.getOrDefault(vals[i], new ArrayList<>());
            list.add(i);
            valueNodeMap.put(vals[i], list);
        }
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int edge[] : edges) {
            List<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            adjacency.put(edge[0], list);
            list = adjacency.getOrDefault(edge[1], new ArrayList<>());
            list.add(edge[0]);
            adjacency.put(edge[1], list);
        }
        UnionFind uf = new UnionFind(vals.length);
        int ans = vals.length;
        //Start from the lowest value as if they are unioned then it won't count
        for (int val : valueNodeMap.keySet()) {
            List<Integer> nodesWithSameValue = valueNodeMap.get(val);

            for (int node : nodesWithSameValue) {
                List<Integer> adjList = adjacency.getOrDefault(node, new ArrayList<>());

                for (int adj : adjList) {

                    if (vals[adj] <= vals[node]) {
                        uf.union(node, adj);
                    }
                }
            }
            Map<Integer, Integer> parentFreq = new HashMap<>();

            for (int node : nodesWithSameValue) {
                int pn = uf.find(node);
                parentFreq.put(pn, parentFreq.getOrDefault(pn, 0) + 1);
            }

            for (int parentKey : parentFreq.keySet()) {
                int freq = parentFreq.get(parentKey);

                if (freq > 1) {
                    ans += (freq * (freq - 1)) / 2;
                }
            }
        }
        return ans;
    }
}
