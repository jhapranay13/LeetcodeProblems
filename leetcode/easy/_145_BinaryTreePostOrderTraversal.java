package leetcode.easy;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @author Pranay Jha
 *
 *Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:
			1
			 \
			  2
			 /
			3

Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [1]
Output: [1]

Example 4:

				1
			   /
			  2

Input: root = [1,2]
Output: [2,1]

Example 5:

				1
				 \
				  2
Input: root = [1,null,2]
Output: [2,1]

Constraints:

The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100


Follow up: Recursive solution is trivial, could you do it iteratively?
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

public class _145_BinaryTreePostOrderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class TreeNode {
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
	//Recursive approach
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		recur(root, ans);
		return ans;
	}

	private void recur(TreeNode node, List<Integer> ans) {

		if (node == null) {
			return;
		}
		recur(node.left, ans);
		recur(node.right, ans);
		ans.add(node.val);
	}
	//=============================================================================
	//Iterative approach
	public List<Integer> postorderTraversal1(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode curr = root;

		while (curr != null || !stack.isEmpty()) {

			if (curr != null) {

				if (curr.right != null) {
					stack.push(curr.right);
				}
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();

				if (!stack.isEmpty() && stack.peek() == curr.right) {
					TreeNode right = stack.pop();
					stack.push(curr);
					curr = right;
				} else {
					ans.add(curr.val);
					curr = null;
				}
			}
		}
		return ans;
	}
}
