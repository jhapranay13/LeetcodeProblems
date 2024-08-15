package leetcode.BinarySearchTree;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a node in a binary search tree, return the in-order successor
 *         of that node in the BST. If that node has no in-order successor,
 *         return null.
 * 
 *         The successor of a node is the node with the smallest key greater
 *         than node.val.
 * 
 *         You will have direct access to the node but not to the root of the
 *         tree. Each node will have a reference to its parent node. Below is
 *         the definition for Node:
 * 
 *         class Node { public int val; public Node left; public Node right;
 *         public Node parent; }
 * 
 * 
 *         Example 1:
 * 								2
 * 							   / \
 * 							  1   3		
 * 
 *         Input: tree = [2,1,3], node = 1 Output: 2 Explanation: 1's in-order
 *         successor node is 2. Note that both the node and the return value is
 *         of Node type. 
 *         
 *         Example 2:
 * 								5
 * 							   / \
 * 							  3   6
 * 							 / \
 * 							2   4
 * 						   /
 * 						  1 		
 * 	
 *         Input: tree = [5,3,6,2,4,null,null,1], node = 6 Output: null
 *         Explanation: There is no in-order successor of the current node, so
 *         the answer is null. 
 *         
 *         Example 3:
 * 								15
 * 							   /  \
 * 							  /    \
 * 							 /      \
 * 							6		 18
 * 						   / \      /  \
 * 						  3   7   17    20
 * 						 / \   \		 	
 * 						2   4   13
 * 							   /
 * 							  9
 * 
 *         Input: tree =
 *         [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9],
 *         node = 15 Output: 17 
 *         
 *         Example 4:
 * 								15
 * 							   /  \
 * 							  /    \
 * 							 /      \
 * 							6		 18
 * 						   / \      /  \
 * 						  3   7   17    20
 * 						 / \   \		 	
 * 						2   4   13
 * 							   /
 * 							  9
 * 
 *         Input: tree =
 *         [15,6,18,3,7,17,20,2,4,null,13,null,null,null,null,null,null,null,null,9],
 *         node = 13 Output: 15 Example 5:
 * 
 *         Input: tree = [0], node = 0 Output: null
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 104]. -105 <=
 *         Node.val <= 105 All Nodes will have unique values.
 * 
 * 
 *         Follow up: Could you solve it without looking up any of the node's
 *         values?
 *
 */
/*
//Definition for a Node.
class Node {
 public int val;
 public Node left;
 public Node right;
 public Node parent;
};
 */

public class _510_InOrderSuccessorBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node parent;
	}
	//=============================================================================
	public Node inorderSuccessor(Node node) {

		if( node == null ) {
			return node;
		}

		if( node.right != null ) {
			node = node.right;

			while( node.left != null ) {
				node = node.left;
			}
			return node;
		}
		Node parent = node.parent;
		Node curr = node;

		while( parent != null && parent.left != curr ) {
			curr = parent;
			parent = curr.parent;
		}
		return parent;
	}
	//============================================================================
	public Node inorderSuccessor1(Node node) {
		if (node == null) {
			return null;
		}

		if (node.right != null) {
			Node rNode = node.right;

			while (rNode.left != null) {
				rNode = rNode.left;
			}
			return rNode;
		}

		while (node != null) {
			Node parent = node.parent;

			if (parent != null && parent.left == node) {
				return parent;
			}
			node = parent;
		}
		return node;
	}
}
