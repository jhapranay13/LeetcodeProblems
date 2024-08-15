package leetcode.Tree;

/**
 *
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
 *
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 *
 * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
 *
 * Note:
 *
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 *
 *
 * Example 1:
 *
 *                      1                                     1
 *                    /   \                                  /
 *                   3     4      ============>            3
 *                 /     /  \                            /
 *                2    6     5                         2
 *                            \
 *                             7
 *
 * Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
 * Output: [2]
 * Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
 * The height of the tree is 2 (The path 1 -> 3 -> 2).
 * Example 2:
 *
 *
 * Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
 * Output: [3,2,3,2]
 * Explanation: We have the following queries:
 * - Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
 * - Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
 * - Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
 * - Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 2 <= n <= 10^5
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * m == queries.length
 * 1 <= m <= min(n, 10^4)
 * 1 <= queries[i] <= n
 * queries[i] != root.val
 *
 */

import java.util.HashMap;
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

public class _2458_Height_of_Binary_Tree_After_Subtree_Removal_Queries {
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

    public int[] treeQueries(TreeNode root, int[] queries) {
        Map<Integer, Integer> leftDepth = new HashMap<>();
        Map<Integer, Integer> rightDepth = new HashMap<>();
        Map<Integer, Integer> depthWithoutNode = new HashMap<>();
        initDepth(leftDepth, rightDepth, root);
        initMaxDepthWithoutNode(depthWithoutNode, leftDepth, rightDepth, root, 0, 0);
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = depthWithoutNode.get(queries[i]);
        }
        return ans;
    }

    private void initMaxDepthWithoutNode(Map<Integer, Integer> depthWithoutNode,
                                         Map<Integer, Integer> leftDepth, Map<Integer, Integer> rightDepth, TreeNode root, int level,
                                         int max) {

        if (root == null) {
            return;
        }
        // level + because height is calculated from the bottom so this point
        // will contain height + bottom so till root height is height till this point + level;
        depthWithoutNode.put(root.val, max);
        int leftMax = leftDepth.get(root.val);
        int rightMax = rightDepth.get(root.val);
        // Since if removing left then right should be max
        initMaxDepthWithoutNode(depthWithoutNode, leftDepth, rightDepth, root.left, level + 1,
                Math.max(level + rightMax, max));
        // Since if removing from rigtht then max will be left
        initMaxDepthWithoutNode(depthWithoutNode, leftDepth, rightDepth, root.right, level + 1,
                Math.max(level +leftMax, max));
    }

    private int initDepth(Map<Integer, Integer> leftDepth, Map<Integer, Integer> rightDepth,
                          TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = initDepth(leftDepth, rightDepth, root.left);
        int right = initDepth(leftDepth, rightDepth, root.right);
        leftDepth.put(root.val, left);
        rightDepth.put(root.val, right);
        return 1 + Math.max(left, right);
    }

}
