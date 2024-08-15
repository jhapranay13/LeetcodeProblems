package leetcode.hard;

import java.util.Arrays;

/**
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
 *
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
 *
 * Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.
 *
 *
 *
 * Example 1:
 *                      2
 *                     / \
 *                  4/    \8
 *                  /      \
 *                 1--------0
 *                    2, 16
 *
 * Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * Output: [false,true]
 * Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
 * For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
 * For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
 *
 * Example 2:
 *                  4
 *                 /
 *             13 /
 *               /    9
 *              3------------2
 *                          /
 *                        5/
 *                        /    10
 *                       1-----------0
 *
 * Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * Output: [true,false]
 * Exaplanation: The above figure shows the given graph.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^5
 * 1 <= edgeList.length, queries.length <= 10^5
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= ui, vi, pj, qj <= n - 1
 * ui != vi
 * pj != qj
 * 1 <= disi, limitj <= 10^9
 * There may be multiple edges between two nodes.
 *
 */

public class _1697_Checking_Existence_of_Edge_Length_Limited_Paths {
    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            this.parent = new int[size];

            for (int i = 0; i < size; i++) {
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

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        int[][] q = new int[queries.length][4];

        for (int i = 0; i < q.length; i++) {
            q[i] = new int[] {i, queries[i][0], queries[i][1], queries[i][2]};
        }
        Arrays.sort(q, (a, b) -> a[3] - b[3]);
        int index = 0;
        UnionFind uf = new UnionFind(n);
        boolean[] ans = new boolean[queries.length];
        //Union all the edges which are less than current limit and check if answer is valid
        for (int i = 0; i < q.length; i++) {
            int limit = q[i][3];
            int queryindex = q[i][0];

            while (index < edgeList.length && edgeList[index][2] < limit) {
                uf.union(edgeList[index][0], edgeList[index][1]);
                index++;
            }

            if (uf.find(q[i][1]) == uf.find(q[i][2])) {
                ans[queryindex] = true;
            }
        }
        return ans;
    }
}
