package leetcode.BFS;

/**
 *
 *
 * Given the root of a binary tree where every node has a unique value and a target integer k, return the value of the nearest leaf node to the target k in the tree.
 *
 * Nearest to a leaf means the least number of edges traveled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
 *
 *
 *
 * Example 1:
 *              2
 *             / \
 *            3   2
 *
 * Input: root = [1,3,2], k = 1
 * Output: 2
 * Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
 * Example 2:
 *
 *
 * Input: root = [1], k = 1
 * Output: 1
 * Explanation: The nearest leaf node is the root node itself.
 * Example 3:
 *
 *                   1
 *                 /   \
 *                2     3
 *              /
 *             4
 *            /
 *           5
 *          /
 *         6
 *
 * Input: root = [1,2,3,4,null,null,null,5,null,6], k = 2
 * Output: 3
 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * 1 <= Node.val <= 1000
 * All the values of the tree are unique.
 * There exist some node in the tree where Node.val == k.
 *
 *
 */

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

public class _742_Closest_Leaf_in_a_Binary_Tree {
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

    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        recur(root, k, parentMap);
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(targetNode);


        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                TreeNode curr = q.poll();

                if (curr.left == null && curr.right == null) {
                    return curr.val;
                }

                if (curr.left != null) {
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                }

                if (parentMap.containsKey(curr)) {
                    q.offer(parentMap.remove(curr));
                    // Removing so that the right child does not add in again
                }
            }
        }
        return 0;
    }
    private TreeNode targetNode = null;

    private void recur(TreeNode node, int target, Map<TreeNode, TreeNode> parentMap) {

        if (node == null) {
            return;
        }

        if (node.left != null) {
            parentMap.put(node.left, node);
            recur(node.left, target, parentMap);
        }

        if (node.right != null) {
            parentMap.put(node.right, node);
            recur(node.right, target, parentMap);
        }

        if (node.val == target) {
            targetNode = node;
        }
    }
}
