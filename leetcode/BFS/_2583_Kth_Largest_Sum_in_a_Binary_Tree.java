package leetcode.BFS;

/**
 *
 *
 * You are given the root of a binary tree and a positive integer k.
 *
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 *
 * Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
 *
 * Note that two nodes are on the same level if they have the same distance from the root.
 *
 *
 *
 * Example 1:
 *
 *                    5
 *                 /    \
 *                8      9
 *              /  \    /  \
 *             2    1  3    7
 *            / \
 *           4   6
 *
 * Input: root = [5,8,9,2,1,3,7,4,6], k = 2
 * Output: 13
 * Explanation: The level sums are the following:
 * - Level 1: 5.
 * - Level 2: 8 + 9 = 17.
 * - Level 3: 2 + 1 + 3 + 7 = 13.
 * - Level 4: 4 + 6 = 10.
 * The 2nd largest level sum is 13.
 * Example 2:
 *
 *                  1
 *                 /
 *                2
 *               /
 *              3
 *
 *
 * Input: root = [1,2,null,3], k = 1
 * Output: 3
 * Explanation: The largest level sum is 3.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 2 <= n <= 105
 * 1 <= Node.val <= 106
 * 1 <= k <= n
 *
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

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

public class _2583_Kth_Largest_Sum_in_a_Binary_Tree {
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

    public long kthLargestLevelSum(TreeNode root, int k) {

        if (root == null) {
            return -1;
        }
        return bfs(root, k);
    }

    private long bfs(TreeNode node, int k) {
        long ans = -1;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(node);
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            long sum = 0;

            while (size-- > 0) {
                TreeNode curr = q.poll();
                sum += curr.val;

                if (curr.left != null) {
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
            pq.offer(sum);

            if (pq.size() > k) {
                pq.poll();
            }
            step++;
        }

        if (step >= k) {
            ans = pq.poll();
        }
        return ans;
    }
}
