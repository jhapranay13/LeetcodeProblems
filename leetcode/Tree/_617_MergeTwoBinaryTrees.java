package leetcode.Tree;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given two binary trees root1 and root2.
 * 
 *         Imagine that when you put one of them to cover the other, some nodes
 *         of the two trees are overlapped while the others are not. You need to
 *         merge the two trees into a new binary tree. The merge rule is that if
 *         two nodes overlap, then sum node values up as the new value of the
 *         merged node. Otherwise, the NOT null node will be used as the node of
 *         the new tree.
 * 
 *         Return the merged tree.
 * 
 *         Note: The merging process must start from the root nodes of both
 *         trees.
 * 
 *         Example 1:
 * 
 * 					1                    2                         3
 * 				   / \                  / \                       / \
 * 				  3   2                1   3                     4   5
 * 				 /           +          \   \      =======>   	/ \   \
 * 				5                        4   7                 5   4   7		
 * 
 *         Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7] Output:
 *         [3,4,5,5,4,null,7] 
 *         
 *         Example 2:
 * 
 *         Input: root1 = [1], root2 = [1,2] Output: [2,2]
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in both trees is in the range [0, 2000]. -104 <=
 *         Node.val <= 104
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

public class _617_MergeTwoBinaryTrees {

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
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

		if( t1 == null && t2 == null ) {
			return null;
		}
		TreeNode node = new TreeNode();
		TreeNode leftt1 = t1 == null ? null : t1.left; 
		TreeNode rightt1 = t1 == null ? null : t1.right;
		TreeNode leftt2 = t2 == null ? null : t2.left; 
		TreeNode rightt2 = t2 == null ? null : t2.right;
		int val1 = t1 == null ? 0 : t1.val;
		int val2 = t2 == null ? 0 : t2.val;
		node.val = val1 + val2;
		node.left = mergeTrees( leftt1, leftt2 );
		node.right = mergeTrees( rightt1, rightt2 );
		return node;
	}
	//=============================================================================
	public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        return merge(root1, root2);   
    }   
    
    private TreeNode merge(TreeNode node1, TreeNode node2) {
        
        if (node1 == null) {
            return node2;
        }
        
        if (node2 == null) {
            return node1;
        }
        TreeNode node = new TreeNode();
        node.val = node1.val + node2.val;
        node.left = merge(node1.left, node2.left);
        node.right = merge(node1.right, node2.right);
        return node;
    }
}
