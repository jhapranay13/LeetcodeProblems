package leetcode.hard;

import java.util.*;

/**
 *
 *
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 *
 *
 *
 * Example 1:
 *                   0
 *                 /   \
 *                1     2
 *                    / | \
 *                   3  4  5
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * Example 2:
 *
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 3:
 *                  0
 *                 /
 *                1
 *
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 3 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * The given input represents a valid tree.
 *
 */

public class _834_Sum_of_Distances_in_Tree {
    private int rootAns = 0;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] countNode = new int[n];
        Map<Integer, Set<Integer>> adjacency = new HashMap<>();
        Arrays.fill(countNode, 1);

        for (int[] edge : edges) {
            Set<Integer> set1 = adjacency.getOrDefault(edge[0], new HashSet<>());
            set1.add(edge[1]);
            adjacency.put(edge[0], set1);
            Set<Integer> set2 = adjacency.getOrDefault(edge[1], new HashSet<>());
            set2.add(edge[0]);
            adjacency.put(edge[1], set2);
        }
        recur(adjacency, countNode, 0, -1, 0);
        int[] ans = new int[n];
        ans[0] = rootAns;
        recurAns(adjacency, countNode, ans, 0, -1);
        return ans;
    }

    private void recur(Map<Integer, Set<Integer>> adjacency, int[] countNode, int curr,
                       int parent, int distance) {
        Set<Integer> children = adjacency.getOrDefault(curr, new HashSet<>());
        rootAns += distance;

        for (int child : children) {

            if (child != parent) {
                recur(adjacency, countNode, child, curr, distance + 1);
                //count the sum of all the nodes which are child of this node
                countNode[curr] += countNode[child];
            }
        }
    }
    // You get this pattern if you check fe patterns first find for 0 then find for
    // child of zero and see how much gets added or subtracted
    // result[child] = result[parent_node] - count[child] + (N - count[child]);
    private void recurAns(Map<Integer, Set<Integer>> adjacency, int[] countNode, int[] ans, int curr,
                          int parent) {
        Set<Integer> children = adjacency.getOrDefault(curr, new HashSet<>());

        for (int child : children) {

            if (child != parent) {
                //count the sum of all the nodes which are child of this node
                // Idea is that when we move the node suppose to left side then the distance to all the node
                // towards left side will decrease by one and for right node will increawe by one.
                // So number of nodes that will decrease by onr will be ans[curr] - countNode[child]
                // as the answer to the root node decreased by number of nodes to the side of the movement.
                // number of nodes increase by one will be total nodes divided by current node count
                // countNode.length - countNode[child])
                ans[child] = ans[curr] - countNode[child] + (countNode.length - countNode[child]);
                recurAns(adjacency, countNode, ans, child, curr);
            }
        }
    }
}
