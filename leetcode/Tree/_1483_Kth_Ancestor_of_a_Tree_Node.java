package leetcode.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of ith node. The root of the tree is node 0. Find the kth ancestor of a given node.
 *
 * The kth ancestor of a tree node is the kth node in the path from that node to the root node.
 *
 * Implement the TreeAncestor class:
 *
 * TreeAncestor(int n, int[] parent) Initializes the object with the number of nodes in the tree and the parent array.
 * int getKthAncestor(int node, int k) return the kth ancestor of the given node node. If there is no such ancestor, return -1.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["TreeAncestor", "getKthAncestor", "getKthAncestor", "getKthAncestor"]
 * [[7, [-1, 0, 0, 1, 1, 2, 2]], [3, 1], [5, 2], [6, 3]]
 * Output
 * [null, 1, 0, -1]
 *
 * Explanation
 * TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);
 * treeAncestor.getKthAncestor(3, 1); // returns 1 which is the parent of 3
 * treeAncestor.getKthAncestor(5, 2); // returns 0 which is the grandparent of 5
 * treeAncestor.getKthAncestor(6, 3); // returns -1 because there is no such ancestor
 *
 *
 * Constraints:
 *
 * 1 <= k <= n <= 5 * 10^4
 * parent.length == n
 * parent[0] == -1
 * 0 <= parent[i] < n for all 0 < i < n
 * 0 <= node < n
 * There will be at most 5 * 10^4 queries.
 *
 */

public class _1483_Kth_Ancestor_of_a_Tree_Node {
    class TreeAncestor {
        Map<Integer, List<Integer>> nodeParentMap;
        Map<Integer, Integer> indexMap; // index of node int the nodeparentMap List

        public TreeAncestor(int n, int[] parent) {
            this.nodeParentMap = new HashMap<>();
            this.indexMap = new HashMap<>();

            for (int i = n - 1; i >= 0; i--) {

                if (nodeParentMap.containsKey(i)) {
                    continue;
                }
                List<Integer> list = new ArrayList<>(); //create a new list for the new path
                int node = i;

                while (node >= 0) {
                    list.add(node);
                    indexMap.put(node, list.size() - 1); // Setting index of the node in the path
                    nodeParentMap.put(node, list);
                    int ancestor = parent[node];
                    node = ancestor;
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int index = indexMap.get(node);
            List<Integer> parentList = nodeParentMap.get(node);

            if (parentList.size() > index + k) {
                return parentList.get(index + k);
            }
            return -1;
        }
    }

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
}
