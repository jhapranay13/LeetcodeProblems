package leetcode.easy;

/**
 *
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 *
 * If the tree has more than one mode, return them in any order.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *              1
 *               \
 *                2
 *               /
 *              2
 *
 * Input: root = [1,null,2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: root = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
 *
 */

import java.util.ArrayList;
import java.util.List;

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

public class _501_Find_Mode_in_Binary_Search_Tree {
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

    TreeNode prev = null;
    int freq = 0;
    int currFreq = 0;
    List<Integer> ans = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        recur(root);
        return ans.stream().mapToInt(i->i).toArray();
    }

    private void recur(TreeNode node) {

        if (node == null) {
            return;
        }
        recur(node.left);

        if (prev == null || node.val == prev.val) {
            currFreq += 1;
        } else {
            currFreq = 1;
        }

        if (currFreq >= freq) {

            if (currFreq > freq) {
                ans = new ArrayList<>();
            }
            ans.add(node.val);
            freq = currFreq;
        }
        prev = node;
        recur(node.right);
    }
}
