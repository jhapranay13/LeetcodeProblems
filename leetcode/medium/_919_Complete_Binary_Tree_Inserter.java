package leetcode.medium;

/**
 *
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
 *
 * Design an algorithm to insert a new node to a complete binary tree keeping it complete after the insertion.
 *
 * Implement the CBTInserter class:
 *
 * CBTInserter(TreeNode root) Initializes the data structure with the root of the complete binary tree.
 * int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that the tree remains complete, and returns the value of the parent of the inserted TreeNode.
 * TreeNode get_root() Returns the root node of the tree.
 *
 *
 *
 * Example 1:
 *              1                      1                       1
 *             /                     /   \                   /   \
 *            2                     2     3                 2     3
 *                                                        /
 *                                                       4
 * Input
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * Output
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * Explanation
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // return 1
 * cBTInserter.insert(4);  // return 2
 * cBTInserter.get_root(); // return [1, 2, 3, 4]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree will be in the range [1, 1000].
 * 0 <= Node.val <= 5000
 * root is a complete binary tree.
 * 0 <= val <= 5000
 * At most 10^4 calls will be made to insert and get_root.
 *
 */

import java.util.Deque;
import java.util.LinkedList;

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

public class _919_Complete_Binary_Tree_Inserter {
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

    class CBTInserter {
        Deque<TreeNode> q;
        TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;
            this.q = new LinkedList<>();
            q.offer(root);

            outer :
            while (!q.isEmpty()) {
                int size = q.size();

                while (size-- > 0) {

                    if (q.peek().left != null) {
                        q.offer(q.peek().left);
                    }

                    if (q.peek().right != null) {
                        q.offer(q.peek().right);
                    }

                    if (q.peek().left == null || q.peek().right == null) {
                        break outer;
                    }
                    q.poll();
                }
            }
        }

        public int insert(int val) {
            TreeNode node = new TreeNode(val);
            int parentVal = -1;

            if (q.peek().left == null) {
                q.peek().left = node;
                parentVal = q.peek().val;
            } else if (q.peek().right == null) {
                q.peek().right = node;
                parentVal = q.peek().val;
                q.poll();
            }
            q.offer(node);
            return parentVal;
        }

        public TreeNode get_root() {
            return this.root;
        }
    }

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */
}
