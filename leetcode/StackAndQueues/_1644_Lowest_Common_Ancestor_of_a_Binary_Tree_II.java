package leetcode.StackAndQueues;

/**
 *
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 *
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 *
 *
 *
 * Example 1:
 *                        3
 *                    /      \
 *                  5          1
 *                /  \        / \
 *               6    2      0   8
 *                  /  \
 *                 7    4
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *          As above
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
 * Example 3:
 *
 *          As Above
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -109 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
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
 *     TreeNode(int x) { val = x; }
 * }
 */

 class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class _1644_Lowest_Common_Ancestor_of_a_Binary_Tree_II {
    //Normal Recursion
    boolean first = false;
    boolean second = false;
    TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        recur(root, p, q);
        return ans;
    }

    private boolean recur(TreeNode node, TreeNode p, TreeNode q) {

        if (node == null) {
            return false;
        }
        boolean curr = false;

        if (node.equals(p) || node.equals(q)) {

            if (first && !second) {
                second = true;
            } else {
                first = true;
            }
            curr = true;
        }
        boolean left = recur(node.left, p, q);
        boolean right = recur(node.right, p, q);

        if (left && right) {
            ans = node;
        }

        if ((curr && left) || (curr && right)) {
            ans = node;
        }
        return curr || left || right;
    }
    //=============================================================================================
    //Stack based
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new LinkedList<>();
        recur(root, p, q, stack);
        return ans;
    }

    private void recur(TreeNode node, TreeNode p, TreeNode q, Deque<TreeNode> stack) {

        if (node == null) {
            return;
        }

        if (!first) {
            stack.push(node);
        }

        if (node.equals(p) || node.equals(q)) {

            if (first && !second) {
                second = true;
            } else {
                first = true;
            }
        }
        recur(node.left, p, q, stack);
        recur(node.right, p, q, stack);

        if (first && second && stack.peek().equals(node) && ans == null) {
            ans = stack.peek();
        }

        if (stack.peek().equals(node)) {
            stack.pop();
        }

    }
}
