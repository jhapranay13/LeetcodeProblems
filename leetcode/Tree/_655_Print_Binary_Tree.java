package leetcode.Tree;


/**
 *
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree. The formatted layout matrix should be constructed using the following rules:
 *
 * The height of the tree is height and the number of rows m should be equal to height + 1.
 * The number of columns n should be equal to 2height+1 - 1.
 * Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 * For each node that has been placed in the matrix at position res[r][c], place its left child at res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
 * Continue this process until all the nodes in the tree have been placed.
 * Any empty cells should contain the empty string "".
 * Return the constructed matrix res.
 *
 *
 *
 * Example 1:
 *
 *          1
 *         /
 *        2
 *
 *
 * Input: root = [1,2]
 * Output:
 * [["","1",""],
 *  ["2","",""]]
 * Example 2:
 *                      1
 *                    /   \
 *                   2     3
 *                    \
 *                     4
 *
 * Input: root = [1,2,3,null,4]
 * Output:
 * [["","","","1","","",""],
 *  ["","2","","","","3",""],
 *  ["","","4","","","",""]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 210].
 * -99 <= Node.val <= 99
 * The depth of the tree will be in the range [1, 10].
 * Ac
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


public class _655_Print_Binary_Tree {
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

    public List<List<String>> printTree(TreeNode root) {
        int depth = getDepth(root, 0);
        int length = (int)Math.pow(2, depth) - 1;
        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < depth; i++) {
            List<String> temp = new ArrayList<>();

            for (int j = 0; j < length; j++) {
                temp.add("");
            }
            ans.add(temp);
        }
        recur(ans, root, 0, length - 1, 0);
        return ans;
    }

    private int getDepth(TreeNode node, int currDepth) {

        if (node == null) {
            return currDepth;
        }
        int left = getDepth(node.left, currDepth + 1);
        int right = getDepth(node.right, currDepth + 1);
        return Math.max(left, right);
    }

    private void recur(List<List<String>> ans, TreeNode node, int lo, int hi, int depth) {

        if (node == null) {
            return;
        }
        int pivot = lo + (hi - lo) / 2;
        ans.get(depth).set(pivot, "" + node.val);
        recur(ans, node.left, lo, pivot - 1, depth + 1);
        recur(ans, node.right, pivot + 1, hi, depth + 1);
    }
}
