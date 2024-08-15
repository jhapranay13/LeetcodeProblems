package leetcode.StackAndQueues;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?


Example 1:

		1                            3
	   /                            /
	  3             ---\      	   1
       \            ---/            \
        2	                         2

Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.


Example 2:

          3                            2
         / \             ---\      	  / \
        1   4            ---/        1   4
		   /		                    /
          2                            3

Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.


Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1
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


public class _99_RecoverBinarySearchTree {

	public static void main(String[] args) {
		_99_RecoverBinarySearchTree obj = new _99_RecoverBinarySearchTree();
		TreeNode root = obj.new TreeNode();
		TreeNode node1 = obj.new TreeNode();
		TreeNode node2 = obj.new TreeNode();

		node1.val = 3;
		node2.val = 2;
		root.val = 1;
		root.left = node1;
		node1.right = node2;
		obj.recoverTree1(root);
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

	public void recoverTree(TreeNode root) {
		List<TreeNode> inOrder = new ArrayList<>();
		inOrderTraversal(root, inOrder);
		findOutNodesToSwap(inOrder);

		if (x != null && y != null) {
			int temp = x.val;
			x.val = y.val;
			y.val = temp;
		}
	}

	private TreeNode x = null;
	private TreeNode y = null;

	private void findOutNodesToSwap(List<TreeNode> inOrder) {

		for (int i = 0; i < inOrder.size() - 1; i++) {

			if (inOrder.get(i + 1).val < inOrder.get(i).val ) {
				y = inOrder.get(i + 1);

				if (x == null) {
					x = inOrder.get(i);
				} else {
					break;
				}
			}
		}
	}

	private void inOrderTraversal(TreeNode root, List<TreeNode> inOrder) {

		if (root == null) {
			return;
		}
		inOrderTraversal(root.left, inOrder);
		inOrder.add(root);
		inOrderTraversal(root.right, inOrder);
	}
	//==============================================================================
	//Stack Implementation
	public void recoverTree1(TreeNode root) {
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode prev = null;
		TreeNode curr = root;
		TreeNode x = null;
		TreeNode y = null;

		while (!stack.isEmpty() || curr != null) {

			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();

				if (prev != null && curr.val < prev.val) {
					y = curr;

					if (x == null) {
						x = prev;
					} else {
						break;
					}
				}
				prev = curr;
				curr = curr.right;
			}
		}
		if (x != null && y != null) {
			int temp = x.val;
			x.val = y.val;
			y.val = temp;
		}
	}
}
