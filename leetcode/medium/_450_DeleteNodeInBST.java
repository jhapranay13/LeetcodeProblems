package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a root node reference of a BST and a key, delete the node with
 *         the given key in the BST. Return the root node reference (possibly
 *         updated) of the BST.
 * 
 *         Basically, the deletion can be divided into two stages:
 * 
 *         Search for a node to remove. If the node is found, delete the node.
 *         Follow up: Can you solve it with time complexity O(height of tree)?
 * 
 *         Example 1:
 * 						5                            5
 * 					  /   \                        /   \
 *                   3     6                      4     6  
 *                  / \     \     =========>     /       \
 *                 2   4     7                  2         7
 *                 
 *                      5                            5
 * 					  /   \                        /   \
 *                   3     6                      2     6  
 *                  / \     \     =========>       \     \
 *                 2   4     7                      4     7          
 *                                
 * 
 *         Input: root = [5,3,6,2,4,null,7], key = 3 Output:
 *         [5,4,6,2,null,null,7] Explanation: Given key to delete is 3. So we
 *         find the node with value 3 and delete it. One valid answer is
 *         [5,4,6,2,null,null,7], shown in the above BST. Please notice that
 *         another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 * 
 *         Example 2:
 * 
 *         Input: root = [5,3,6,2,4,null,7], key = 0 Output: [5,3,6,2,4,null,7]
 *         Explanation: The tree does not contain a node with value = 0. Example
 *         3:
 * 
 *         Input: root = [], key = 0 Output: []
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 104]. -105 <=
 *         Node.val <= 105 Each node has a unique value. root is a valid binary
 *         search tree. -105 <= key <= 105
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

public class _450_DeleteNodeInBST {

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

	public TreeNode deleteNode(TreeNode root, int key) {

		if (root == null) {
			return root;
		}

		if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.val) {
			root.right = deleteNode(root.right, key);
		} else {

			if (root.left == null && root.right == null) {
				return null;
			}
			TreeNode temp = null;

			if (root.left != null) {
				temp = getPredecessor(root.left);
				root.val = temp.val;
				root.left = deleteNode(root.left, root.val);
			} else {
				temp = getSuccessor(root.right);
				root.val = temp.val;
				root.right = deleteNode(root.right, root.val);
			}
		}
		return root;
	}

	private TreeNode getPredecessor(TreeNode node) {

		if (node.right != null) {
			return getPredecessor(node.right);
		}
		return node;
	}

	private TreeNode getSuccessor(TreeNode node) {

		if (node.left != null) {
			return getSuccessor(node.left);
		}
		return node;
	}
}
