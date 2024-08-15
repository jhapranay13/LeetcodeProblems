package leetcode.Tree;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the roots of two binary trees root and subRoot, return true if
 *         there is a subtree of root with the same structure and node values of
 *         subRoot and false otherwise.
 * 
 *         A subtree of a binary tree tree is a tree that consists of a node in
 *         tree and all of this node's descendants. The tree tree could also be
 *         considered as a subtree of itself.
 * 
 *         Example 1:
 *         					3
 *         				   / \
 *                        4   5					4
 *                       / \                   / \
 *                      1   2                 1   2
 * 
 *         Input: root = [3,4,5,1,2], subRoot = [4,1,2] Output: true 
 *         
 *         Example 2:
 * 							3
 *         				   / \
 *                        4   5					4
 *                       / \                   / \
 *                      1   2                 1   2
 * 						   /
 * 						  0
 * 	
 *         Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 *         Output: false
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the root tree is in the range [1, 2000]. The
 *         number of nodes in the subRoot tree is in the range [1, 1000]. -104
 *         <= root.val <= 104 -104 <= subRoot.val <= 104
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

public class _572_SubtreeOfAnotherTree {

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
	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		return traverse(root, subRoot);
	}

	private boolean traverse(TreeNode node, TreeNode subNode) {

		if (node == null) {
			return false;
		}
		boolean flag = false;

		if (node.val == subNode.val) {
			flag = checkTreeStructure(node, subNode);    
		}

		if (!flag) {
			flag = traverse(node.right, subNode) || traverse(node.left, subNode); 
		}
		return flag;
	}

	private boolean checkTreeStructure(TreeNode node, TreeNode subNode) {

		if (node == null && subNode == null) {
			return true;
		}

		if (node == null || subNode == null || subNode.val != node.val) {
			return false;
		}
		return checkTreeStructure(node.left, subNode.left) && 
				checkTreeStructure(node.right, subNode.right);
	}
	//==============================================================================
	//Another Approach
	public boolean isSubtree1(TreeNode root, TreeNode subRoot) {
		Set<String> serializationForAllTree = new HashSet<>(); 
		getSerialization(root, serializationForAllTree);
		String subSerial = getSerialization(subRoot, null);

		if (serializationForAllTree.contains(subSerial)) {
			return true;
		}
		return false;
	}

	private String getSerialization(TreeNode node, Set<String> serializationForAllTree) {

		if (node == null) {
			return "#";
		}
		String serialStr = node.val + "," + getSerialization(node.left, serializationForAllTree) + "," + 
				getSerialization(node.right, serializationForAllTree);

		if (serializationForAllTree != null) {
			serializationForAllTree.add(serialStr);
		}
		return serialStr;
	}
}
