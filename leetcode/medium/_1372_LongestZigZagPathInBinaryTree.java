package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given the root of a binary tree.
 *
 * A ZigZag path for a binary tree is defined as follow:
 *
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 *
 *
 *
 * Example 1:
 *                            1
 *                             \
 *                              1
 *                             / \\
 *                            1   1
 *                              // \
 *                              1   1
 *                              \\
 *                                1
 *                                 \
 *                                  1
 *
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 *
 * Example 2:
 *                          1
 *                         // \
 *                        1   1
 *                        \\
 *                          1
 *                         //\
 *                        1   1
 *                        \\
 *                         1
 *
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 * Example 3:
 *
 * Input: root = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 5 * 104].
 * 1 <= Node.val <= 100
 *
 */

public class _1372_LongestZigZagPathInBinaryTree {
    public class TreeNode {
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
    //=============================================================================================
    //Recursive
    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        recur(root.left, 0, 1);
        recur(root.right, 1, 1);
        return ans;
    }

    private int ans = 0;

    private void recur(TreeNode node, int dir, int sum) {

        if (node == null) {
            return;
        }
        ans = Math.max(sum, ans);
        //Assigning opposite so that it starts to process it from scratch
        if (dir != 0) {
            recur(node.left, 0, sum + 1);
            recur(node.right, 1, 1);
        }

        if (dir != 1) {
            recur(node.right, 1, sum + 1);
            recur(node.left, 0, 1);
        }
    }
    //=============================================================================================
    //DP approach
    public int longestZigZag1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        recur(root, -1);
        longestZigZag(root.left);
        longestZigZag(root.right);
        return ans - 1;
    }

    private Map<String, Integer> memo = new HashMap<>();

    private int recur(TreeNode node, int dir) {

        if (node == null) {
            return 0;
        }
        String key = node.toString() +"," + dir;

        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int left = 1;
        int right = 1;

        if (dir != 0 && node.left != null) {
            left += recur(node.left, 0);
        }

        if (dir != 1 && node.right != null) {
            right += recur(node.right, 1);
        }
        int max = Math.max(left, right);
        ans = Math.max(ans, max);
        memo.put(key, max);
        return max;
    }
    //=============================================================================================
    //Another DP approach
    public int longestZigZag2(TreeNode root) {
        Map<String, Integer> memo = new HashMap<>();
        recur(root, -1, memo);
        return ans - 1;
    }

    private int recur(TreeNode node, int dir, Map<String, Integer> memo) {

        if (node == null) {
            return 0;
        }
        String key = node.toString() +"," + dir;

        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int left = 1;
        int right = 1;
        //giving opposite direction so it starts processing the node.left or node.right one
        int startLeft = recur(node.left, 1, memo);
        int startRight = recur(node.right, 0, memo);
        ans = Math.max(ans, Math.max(startLeft, startRight));
        if (dir != 0 && node.left != null) {
            left += recur(node.left, 0, memo);
        }

        if (dir != 1 && node.right != null) {
            right += recur(node.right, 1, memo);
        }
        int max = Math.max(left, right);
        ans = Math.max(ans, max);
        memo.put(key, max);
        return max;
    }
}
