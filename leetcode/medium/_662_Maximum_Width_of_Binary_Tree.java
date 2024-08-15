package leetcode.medium;

/**
 *
 *
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *                  1
 *                /   \
 *               3     2
 *             /  \     \
 *            5    3     9
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 * Example 2:
 *                     1
 *  *                /   \
 *  *               3     2
 *  *             /        \
 *  *            5          9
 *             /           /
 *            6           7
 *
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 * Example 3:
 *                     1
 *  *                /   \
 *  *               3     2
 *  *             /
 *  *            5
 *
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3000].
 * -100 <= Node.val <= 100
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

public class _662_Maximum_Width_of_Binary_Tree {
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
    //=============================================================================================
    //DFS
    Map<Integer, Integer> levelStart = new HashMap<>();
    int ans = 0;

    public int widthOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        recur(root, 0, 0);
        return ans;
    }

    private void recur(TreeNode node, int level, int index) {

        if (node == null) {
            return;
        }

        if (!levelStart.containsKey(level)) {
            levelStart.put(level, index);
        }
        ans = Math.max(ans, index - levelStart.get(level) + 1);
        recur(node.left, level + 1, index * 2);
        recur(node.right, level + 1, index * 2 + 1);
    }
    //=============================================================================================
    //BFS
    public int widthOfBinaryTree1(TreeNode root) {

        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> levelStart = new HashMap<>();
        Deque<Object[]> q = new LinkedList<>();
        q.offer(new Object[] {root, 0});
        int level = 0;
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Object[] obj = q.poll();
                TreeNode node = (TreeNode)obj[0];
                Integer index = (Integer)obj[1];

                if (!levelStart.containsKey(level)) {
                    levelStart.put(level, index);
                }
                ans = Math.max(ans, index - levelStart.get(level) + 1);

                if (node.left != null) {
                    q.offer(new Object[] {node.left, index * 2});
                }

                if (node.right != null) {
                    q.offer(new Object[] {node.right, index * 2 + 1});
                }
            }
            level++;
        }
        return ans;
    }
}
