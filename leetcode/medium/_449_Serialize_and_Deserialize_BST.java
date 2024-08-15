package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 10^4].
 * 0 <= Node.val <= 10^4
 * The input tree is guaranteed to be a binary search tree.
 *
 */

public class _449_Serialize_and_Deserialize_BST {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(){}
          TreeNode(int x) { val = x; }
    }


    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder ans = new StringBuilder();
            postfix(root, ans);

            if (ans.length() > 0) {
                ans.deleteCharAt(ans.length() - 1);
            }
            return ans.toString();
        }

        private void postfix(TreeNode node, StringBuilder ans) {
            if (node == null) {
                return;
            }
            postfix(node.left, ans);
            postfix(node.right, ans);
            ans.append(node.val);
            ans.append(' ');
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            if (data.isEmpty()) {
                return null;
            }
            String[] valsplit = data.split(("\\s+"));
            Deque<Integer> vals = new LinkedList<>();

            for (String val : valsplit) {
                vals.offer(Integer.valueOf(val));
            }
            return helper(vals, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode helper(Deque<Integer> vals, int lo, int hi) {

            if (vals.isEmpty()) {
                return null;
            }
            int val = vals.peekLast();
            if (lo > val || hi < val ) {
                return null;
            }
            TreeNode node = new TreeNode();
            node.val = vals.pollLast();
            node.right = helper(vals, val, hi);
            node.left = helper(vals, lo, val);
            return node;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;

}
