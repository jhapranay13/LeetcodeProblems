package leetcode.BinarySearchTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a binary search tree (BST), find the lowest common ancestor
 *         (LCA) of two given nodes in the BST.
 * 
 *         According to the definition of LCA on Wikipedia: �The lowest common
 *         ancestor is defined between two nodes p and q as the lowest node in T
 *         that has both p and q as descendants (where we allow a node to be a
 *         descendant of itself).�
 *
 *         Example 1:
 *                                      6
 *									  /   \
 *									 /	   \
 *									2		8
 *								   / \     / \
 *								  0   4   7   9
 *									 / \
 *									3   5		
 *						 
 *         Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8 Output: 6
 *         Explanation: The LCA of nodes 2 and 8 is 6. Example 2:
 * 
 * 
 *         Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4 Output: 2
 *         Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a
 *         descendant of itself according to the LCA definition. Example 3:
 * 
 *         Input: root = [2,1], p = 2, q = 1 Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [2, 105]. -109 <=
 *         Node.val <= 109 All Node.val are unique. p != q p and q will exist in
 *         the BST.
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

public class _235_LowestCommonAncestorOfBST {

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
	//Recursive Solution for BST
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		int rootVal = root.val;
		int pVal = p.val;
		int qVal = q.val;

		if (pVal > rootVal && qVal > rootVal) {
			return lowestCommonAncestor(root.right, p, q);
		} else if (pVal < rootVal && qVal < rootVal) {
			return lowestCommonAncestor(root.left, p, q);
		} else {
			return root;
		}
	}
	//=============================================================================
	//Iterative Solution for BST
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

		while (root != null) {
			int rootVal = root.val;
			int pVal = p.val;
			int qVal = q.val;

			if (pVal > rootVal && qVal > rootVal) {
				root = root.right;
			} else if (pVal < rootVal && qVal < rootVal) {
				root = root.left;
			} else {
				break;
			}
		}
		return root;    
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
}
