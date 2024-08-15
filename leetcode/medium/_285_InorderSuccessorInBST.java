package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST. If the given node has no in-order successor in the tree, return null.

The successor of a node p is the node with the smallest key greater than p.val.



Example 1:
					2
				   / \
				  1   3

Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:
					5
				  /   \
				 3     6
                / \
               2   4
              /
             1

Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.


Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
All Nodes will have unique values.
 *
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

public class _285_InorderSuccessorInBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	// =============================================================================
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

		if (p.right != null) {
			TreeNode right = p.right;

			if (right.left == null) {
				return right;
			} else {
				TreeNode left = right.left;

				while (left.left != null) {
					left = left.left;
				}
				return left;
			}
		}
		TreeNode ans = dfs(root, p);
		return ans.equals(p) || ans == null ? null : ans;
	}

	private TreeNode dfs(TreeNode curr, TreeNode find) {

		if (curr == null) {
			return null;
		}

		if (curr.equals(find)) {
			return find;
		}
		TreeNode found = dfs(curr.left, find);

		if (found != null && found.equals(find)) {
			return curr;
		}

		if (found == null) {
			found = dfs(curr.right, find);

			if (found != null && found.equals(find)) {
				return find;
			}
		}
		return found;
	}
	// =============================================================================
	//Using BST property
	public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {

		TreeNode successor = null;

		while (root != null) {

			if (p.val >= root.val) {
				root = root.right;
			} else {
				successor = root;
				root = root.left;
			}
		}

		return successor;
	}
	//===============================================================================
	public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
		return recur(root, p);
	}

	private TreeNode recur(TreeNode node, TreeNode p) {

		if (node == null) {
			return null;
		}

		if (node.val < p.val) {
			return recur(node.right, p);
		} else if (node.val > p.val) {
			TreeNode ans = recur(node.left, p);

			if (ans == null) {
				ans = node;
			}
			return ans;
		}
		//this only if p is found
		if (p.right != null) {
			p = p.right;

			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		return null;
	}
}
