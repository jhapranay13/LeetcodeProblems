package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a binary tree, find the lowest common ancestor
 *         (LCA) of two given nodes in the Binary Tree.
 * 
 *         According to the definition of LCA on Wikipedia: �The lowest common
 *         ancestor is defined between two nodes p and q as the lowest node in T
 *         that has both p and q as descendants (where we allow a node to be a
 *         descendant of itself).�
 *
 *         Example 1:
 *                                      3
 *									  /   \
 *									 /	   \
 *									5		1
 *								   / \     / \
 *								  0   2   0   8
 *									 / \
 *									7   4
 *						 
 *         Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 		   Output: 3
 * 		   Explanation: The LCA of nodes 5 and 1 is 3.
 * 
 * 
 *         Example 2:
 *
 *
 * 		 	Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 			Output: 5
 * 			Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * 			Example 3:
 *
 * 			Input: root = [1,2], p = 1, q = 2
 * 			Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 *
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class _236_LowestCommonAncestorOfBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

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

	//=============================================================================
	//Solution for any Binary Tree
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		Deque<TreeNode> stack = new LinkedList<>();
		lca(root, p, q, stack);

		if (ans == null) {
			return root;
		}
		return ans;
	}
	private boolean findOne = false;
	private boolean findTwo = false;
	private TreeNode ans = null;

	private void lca(TreeNode node, TreeNode p, TreeNode q, Deque<TreeNode> stack) {

		if (node == null) {
			return;
		}

		if (!findOne) {
			stack.push(node);
		}

		if (node == p || node == q) {

			if (!findOne) {
				findOne = true;
			} else {
				findTwo = true;
				return;
			}
		}

		if (!findTwo) {
			lca(node.left, p, q, stack);
			lca(node.right, p, q, stack);
		}

		if (findTwo && !stack.isEmpty() && stack.peek() == node && ans == null) {
			ans = node;
		}

		if (!stack.isEmpty() && stack.peek() == node) {
			stack.pop();
		}
	}
	//=============================================================================================
	//Another Approach
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

		if (root == null) {
			return null;
		}

		if (root == p || root == q) {
			return root;
		}
		TreeNode left = lowestCommonAncestor1(root.left, p, q);
		TreeNode right = lowestCommonAncestor1(root.right, p, q);
		TreeNode self = root == p || root == q ? root : null;

		if ((self != null && left != null) || self != null && right != null) {
			return self;
		}

		if (left != null && right != null) {
			return root;
		}

		if (left != null) {
			return left;
		}
		return right;
	}
}
