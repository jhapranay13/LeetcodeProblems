package leetcode.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given a binary search tree, return a balanced binary search tree with
 *         the same node values.
 * 
 *         A binary search tree is balanced if and only if the depth of the two
 *         subtrees of every node never differ by more than 1.
 * 
 *         If there is more than one answer, return any of them.
 * 
 * 
 * 
 *         Example 1:
 * 							1
 * 							 \
 * 							  2
 * 							   \
 * 								3	
 * 								 \
 * 								  4
 * 
 * 						2
 * 					   / \
 * 					  1   3
 *                         \
 *                          4		
 * 
 *         Input: root = [1,null,2,null,3,null,4,null,null] Output:
 *         [2,1,3,null,null,null,4] Explanation: This is not the only correct
 *         answer, [3,1,4,null,2,null,null] is also correct.
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is between 1 and 10^4. The tree nodes
 *         will have distinct values between 1 and 10^5.
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

public class _1382_BanceABinarySearchTree {

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
	//============================================================================
	//Can also use AVL but will be a bit more complex
	public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> inOrder = new ArrayList<>();
        
        inOrderTraversal(root, inOrder);
        TreeNode ret = createBalance(inOrder, 0, inOrder.size() - 1);
        return ret;
    }
    
    private TreeNode createBalance(List<TreeNode> inOrder, int lo, int hi) {
        TreeNode retNode = null;
        
        if (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            retNode = inOrder.get(pivot);
            retNode.left = createBalance(inOrder, lo, pivot - 1);
            retNode.right = createBalance(inOrder, pivot + 1, hi);
        }
        return retNode;
    }
    
    private void inOrderTraversal(TreeNode root, List<TreeNode> inOrder) {
        
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, inOrder);
        inOrder.add(root);
        inOrderTraversal(root.right, inOrder);
    }
}
