package leetcode.Graph;

/**
 *
 * Alice and Bob have an undirected graph of n nodes and three types of edges:
 *
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can be traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 *
 * Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.
 *
 *
 *
 * Example 1:
 *                           1
 *                   1  /  /    \
 *                    /  /3      \1
 *                  /  /     3    \
 *                   2 ----------- 3
 *                     \          /
 *                      \  1    /2
 *                       \    /
 *                         4
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * Output: 2
 * Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
 * Example 2:
 *
 *                              1
 *  *                        / | |
 *  *                      /3  | |
 *  *                    /     | |  3
 *  *                   2 ----------- 3
 *  *                        1| | 2
 *  *                         | |
 *  *                         | |
 *  *                          4
 *
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * Output: 0
 * Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
 * Example 3:
 *                                 1
 *  *  *                        /
 *  *  *                      /1
 *  *  *                    /     3
 *  *  *                   2 ----------- 3
 *  *  *                              /
 *                                  /2
 *  *  *                          4
 *
 *
 * Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * Output: -1
 * Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 *
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, 3 * n * (n - 1) / 2)
 * edges[i].length == 3
 * 1 <= typei <= 3
 * 1 <= ui < vi <= n
 * All tuples (typei, ui, vi) are distinct.
 *
 */

public class _1579_Remove_Max_Number_of_Edges_to_Keep_Graph_Fully_Traversable {
    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            this.parent = new int[size];

            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {

            if (parent[x] != x) {
                return parent[x] = find(parent[x]);
            }
            return x;
        }

        public boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                parent[py] = px;
                return true;
            }
            return false;
        }
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufb = new UnionFind(n);
        UnionFind ufc = new UnionFind(n);
        int countAlice = 0;
        int countBob = 0;
        int countRedundant = 0;

        for (int[] edge : edges) {

            if (edge[0] == 3) {
                boolean isUfb  = ufb.union(edge[1] - 1, edge[2] - 1);
                boolean isUfc = ufc.union(edge[1] - 1, edge[2] - 1);

                if (isUfb) {
                    countBob++;
                }

                if (isUfc) {
                    countAlice++;
                }

                if(isUfb == false && isUfc == false ) {
                    countRedundant++;
                }
            }
        }

        for (int[] edge : edges) {

            if (edge[0] == 2) {

                if (ufb.union(edge[1] - 1, edge[2] - 1)) {
                    countBob++;
                } else {
                    countRedundant++;
                }
            }
        }

        for (int[] edge : edges) {

            if (edge[0] == 1) {

                if (ufc.union(edge[1] - 1, edge[2] - 1)) {
                    countAlice++;
                } else {
                    countRedundant++;
                }
            }
        }
        // Number of Wdges hould be total points - 1
        if (countBob == n - 1 && countAlice == n - 1) {
            return countRedundant;
        }
        return -1;
    }
}
