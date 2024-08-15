package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.
 *
 * The distance between two nodes is the number of edges on the path from one to the other.
 *
 *
 *
 * Example 1:
 *                                          3
 *  *									  /   \
 *  *									 /	   \
 *  *									5		1
 *  *								   / \     / \
 *  *								  0   2   0   8
 *  *									 / \
 *  *									7   4
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
 * Output: 3
 * Explanation: There are 3 edges between 5 and 0: 5-3-1-0.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
 * Output: 2
 * Explanation: There are 2 edges between 5 and 7: 5-2-7.
 * Example 3:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
 * Output: 0
 * Explanation: The distance between a node and itself is 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^9
 * All Node.val are unique.
 * p and q are values in the tree.
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

public class _1740_Find_Distance_in_a_Binary_Tree {
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
    private int ans = 0;

    public int findDistance1(TreeNode root, int p, int q) {
        recur(root, p, q);
        return ans;
    }

    private int recur(TreeNode root, int p, int q) {

        if (root == null) {
            return 0;
        }
        int returnVal = 0;

        if (root.val == p || root.val == q) {
            returnVal = 1;
        }
        int left = recur(root.left, p, q);
        int right = recur(root.right, p, q);

        if ((root.val == p || root.val == q) && left != 0) {
            ans = left;
        }

        if ((root.val == p || root.val == q) && right != 0) {
            ans = right;
        }

        if (left != 0 && right != 0) {
            ans = left + right;
        } else if ((left != 0 || right != 0)) {
            returnVal = 1 + left + right;
        }
        return returnVal;
    }

    //=============================================================================================
    // Hash Map based
    public int findDistance(TreeNode root, int p, int q) {
        Map<Integer, Integer> childToParent = new HashMap<>();
        childToParent.put(root.val, null);
        helper(childToParent, root);
        Map<Integer, Integer> map = new HashMap<>();
        Integer trav = p;
        int edgeCount = 0;
        while (trav != null) {
            map.put(trav, edgeCount++);
            trav = childToParent.get(trav);
        }
        trav = q;
        edgeCount = 0;
        while (!map.containsKey(trav)) {
            edgeCount++;
            trav = childToParent.get(trav);
        }
        return map.get(trav) + edgeCount;
    }

    void helper(Map<Integer, Integer> childToParent, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            childToParent.put(node.left.val, node.val);
            helper(childToParent, node.left);
        }
        if (node.right != null) {
            childToParent.put(node.right.val, node.val);
            helper(childToParent, node.right);
        }
    }
}
