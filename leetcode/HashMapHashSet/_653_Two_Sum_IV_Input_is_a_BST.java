package leetcode.HashMapHashSet;

/**
 *
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 *
 *
 * Example 1:
 *                  5
 *                /   \
 *               3     6
 *             /  \     \
 *            2    4     7
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 *
 *                     5
 *  *                /   \
 *  *               3     6
 *  *             /  \     \
 *  *            2    4     7
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * root is guaranteed to be a valid binary search tree.
 * -10^5 <= k <= 10^5
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
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class _653_Two_Sum_IV_Input_is_a_BST {
    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    // Can also be solved using inorder traversal taking out a list and then doing Two sum.
    // Also BFS.
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> holder = new HashSet<>();
        return recur(root, k, holder);
    }

    private boolean recur(TreeNode node, int k, Set<Integer> holder) {

        if (node == null) {
            return false;
        }
        int diff = k - node.val;

        if (holder.contains(diff)) {
            return true;
        }
        holder.add(node.val);
        return recur(node.left, k, holder) || recur(node.right, k, holder);
    }
}
