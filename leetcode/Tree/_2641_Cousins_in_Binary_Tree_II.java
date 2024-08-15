package leetcode.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 *
 *
 * Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Return the root of the modified tree.
 *
 * Note that the depth of a node is the number of edges in the path from the root node to it.
 *
 *
 *
 * Example 1:
 *
 *                     5                          0
 *                   /   \                      /   \
 *                  4     9                    0     0
 *                /  \     \                 /  \     \
 *               1    10    7               7   7      11
 *
 *
 * Input: root = [5,4,9,1,10,null,7]
 * Output: [0,0,0,7,7,null,11]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
 * - Node with value 5 does not have any cousins so its sum is 0.
 * - Node with value 4 does not have any cousins so its sum is 0.
 * - Node with value 9 does not have any cousins so its sum is 0.
 * - Node with value 1 has a cousin with value 7 so its sum is 7.
 * - Node with value 10 has a cousin with value 7 so its sum is 7.
 * - Node with value 7 has cousins with values 1 and 10 so its sum is 11.
 *
 * Example 2:
 *
 *                  3                     0
 *                /   \                 /   \
 *               1     2               0     0
 *
 * Input: root = [3,1,2]
 * Output: [0,0,0]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
 * - Node with value 3 does not have any cousins so its sum is 0.
 * - Node with value 1 does not have any cousins so its sum is 0.
 * - Node with value 2 does not have any cousins so its sum is 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^4
 *
 *
 */


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

public class _2641_Cousins_in_Binary_Tree_II {
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

    public TreeNode replaceValueInTree(TreeNode root) {
        List<Integer> sumHolder = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;

            while (size-- > 0) {
                TreeNode curr = q.poll();

                if (curr.left != null) {
                    sum += curr.left.val;
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    sum += curr.right.val;
                    q.offer(curr.right);
                }
            }
            sumHolder.add(sum);
        }
        q.clear();
        q.offer(root);
        int lvl = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int sum = 0;
                TreeNode curr = q.poll();

                if (curr.left != null) {
                    sum += curr.left.val;
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    sum += curr.right.val;
                    q.offer(curr.right);
                }

                if (lvl == 0 || lvl == 1) {
                    curr.val = 0;
                }

                if (lvl >= 1) {

                    if (curr.left != null) {
                        curr.left.val = sumHolder.get(lvl) - sum;
                    }

                    if (curr.right != null) {
                        curr.right.val = sumHolder.get(lvl) - sum;
                    }
                }
            }
            lvl++;
        }
        return root;
    }
}
