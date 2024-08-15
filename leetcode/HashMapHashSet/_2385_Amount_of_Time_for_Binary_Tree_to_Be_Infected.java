package leetcode.HashMapHashSet;

import java.util.*;

/**
 *
 * You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.
 *
 * Each minute, a node becomes infected if:
 *
 * The node is currently uninfected.
 * The node is adjacent to an infected node.
 * Return the number of minutes needed for the entire tree to be infected.
 *
 *
 *
 * Example 1:
 *                       1
 *                    /     \
 *                   5       3
 *                    \    /   \
 *                     4  10    6
 *                    / \
 *                   9   2
 *
 * Input: root = [1,5,3,null,4,10,6,9,2], start = 3
 * Output: 4
 * Explanation: The following nodes are infected during:
 * - Minute 0: Node 3
 * - Minute 1: Nodes 1, 10 and 6
 * - Minute 2: Node 5
 * - Minute 3: Node 4
 * - Minute 4: Nodes 9 and 2
 * It takes 4 minutes for the whole tree to be infected so we return 4.
 * Example 2:
 *
 *
 * Input: root = [1], start = 1
 * Output: 0
 * Explanation: At minute 0, the only node in the tree is infected so we return 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 105
 * Each node has a unique value.
 * A node with a value of start exists in the tree.
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

public class _2385_Amount_of_Time_for_Binary_Tree_to_Be_Infected {
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

    public int amountOfTime(TreeNode root, int start) {
        Map<TreeNode, TreeNode> nodeParentHolder = new HashMap<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode startNode = null;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.val == start) {
                startNode = curr;
            }

            if (curr.left != null) {
                q.offer(curr.left);
                nodeParentHolder.put(curr.left, curr);
            }

            if (curr.right != null) {
                q.offer(curr.right);
                nodeParentHolder.put(curr.right, curr);
            }
        }
        q.offer(startNode);
        Set<TreeNode> v = new HashSet<>();
        v.add(startNode);
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                TreeNode curr = q.poll();

                if (curr.left != null && v.add(curr.left)) {
                    q.offer(curr.left);
                }

                if (curr.right != null && v.add(curr.right)) {
                    q.offer(curr.right);
                }

                if (nodeParentHolder.containsKey(curr) && v.add(nodeParentHolder.get(curr))) {
                    q.offer(nodeParentHolder.get(curr));
                }
            }

            if (!q.isEmpty()) {
                ans++;
            }
        }
        return ans;
    }
}
