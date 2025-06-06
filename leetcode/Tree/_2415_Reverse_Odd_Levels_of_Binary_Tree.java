package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
 *
 * For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
 * Return the root of the reversed tree.
 *
 * A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
 *
 * The level of a node is the number of edges along the path between it and the root node.
 *
 *
 *
 * Example 1:
 *
 *                   2                             2
 *                /     \                       /      \
 *              3         5                   5         3
 *           /    \     /   \               /   \     /   \
 *           8    13   21   34             8     13  21    34
 *
 *
 * Input: root = [2,3,5,8,13,21,34]
 * Output: [2,5,3,8,13,21,34]
 * Explanation:
 * The tree has only one odd level.
 * The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.
 * Example 2:
 *
 *               7                               7
 *            /     \                         /     \
 *           13      11                      11      13
 *
 * Input: root = [7,13,11]
 * Output: [7,11,13]
 * Explanation:
 * The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
 * Example 3:
 *
 * Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * Explanation:
 * The odd levels have non-zero values.
 * The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
 * The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 214].
 * 0 <= Node.val <= 105
 * root is a perfect binary tree.
 *
 *
 */

public class _2415_Reverse_Odd_Levels_of_Binary_Tree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public TreeNode reverseOddLevels(TreeNode root) {

    /*if (root != null && root.left != null) {
        recur(root.left, root.right, 1);
    }*/
        bfs(root);
        return root;
    }

    /*
    private void recur(TreeNode node1, TreeNode node2, int level) {

        if (level % 2 != 0) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }

        if (node1.left != null) {
            recur(node1.left, node2.right, level + 1);
            recur(node1.right, node2.left, level + 1);
        }
    }*/

    private void bfs(TreeNode node) {
        List<TreeNode> q = new ArrayList<>();
        List<TreeNode> nq = new ArrayList<>();
        q.add(node);
        int level = 0;

        while(!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {

                if (q.get(i).left != null) {
                    nq.add(q.get(i).left);
                    nq.add(q.get(i).right);
                } else {
                    break;
                }
            }

            if (!nq.isEmpty() && (level + 1) % 2 != 0) {

                for (int i = 0; i < nq.size() / 2; i++) {
                    int temp = nq.get(i).val;
                    nq.get(i).val = nq.get(nq.size() - 1 - i).val;
                    nq.get(nq.size() - 1 - i).val = temp;
                }
            }
            level++;
            q = nq;
            nq = new ArrayList<>();
        }
    }
}
