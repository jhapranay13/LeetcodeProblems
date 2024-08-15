package leetcode.medium;

/**
 *
 * Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree's nodes are unique.
 *
 * Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T is the lowest node that has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 *
 *
 *
 * Example 1:
 *                  3
 *                /   \
 *               5     1
 *             /  \   /  \
 *            6    2 0    8
 *               /  \
 *              7    4
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 * Output: 2
 * Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.
 * Example 2:
 *
 *                     3
 *  *                /   \
 *  *               5     1
 *  *             /  \   /  \
 *  *            6    2 0    8
 *  *               /  \
 *  *              7    4
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 * Output: 1
 * Explanation: The lowest common ancestor of a single node is the node itself.
 *
 * Example 3:
 *                     3
 *  *                /   \
 *  *               5     1
 *  *             /  \   /  \
 *  *            6    2 0    8
 *  *               /  \
 *  *              7    4
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 * Output: 5
 * Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * All nodes[i] will exist in the tree.
 * All nodes[i] are distinct.
 *
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class _1676_Lowest_Common_Ancestor_of_a_Binary_Tree_IV {
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> nodeHolder = new HashSet<>();

        for (TreeNode node : nodes) {
            nodeHolder.add(node);
        }
        foundCount = nodeHolder.size();
        recur(root, nodeHolder);
        return lca;
    }
    private int foundCount;
    TreeNode lca = null;

    private int recur(TreeNode node, Set<TreeNode> nodeHolder) {

        if (node == null) {
            return 0;
        }
        int left = recur(node.left, nodeHolder);
        int right = recur(node.right, nodeHolder);
        int total = left + right;

        if (nodeHolder.contains(node)) {
            total++;
        }

        if (foundCount == total && lca == null) {
            lca = node;
        }
        return total;
    }
}
