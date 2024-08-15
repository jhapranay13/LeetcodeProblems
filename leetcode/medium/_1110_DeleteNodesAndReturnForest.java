package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, each node in the tree has a distinct
 *         value.
 * 
 *         After deleting all nodes with a value in to_delete, we are left with
 *         a forest (a disjoint union of trees).
 * 
 *         Return the roots of the trees in the remaining forest. You may return
 *         the result in any order.
 * 
 *         Example 1:
 * 							1
 * 						  /   \
 * 						 2	   3
 * 						/ \   / \
 * 					   4   5 6   7	
 * 
 *         Input: root = [1,2,3,4,5,6,7], to_delete = [3,5] Output:
 *         [[1,2,null,4],[6],[7]] 
 *         
 *         Example 2:
 * 
 *         Input: root = [1,2,4,null,3], to_delete = [3] Output: [[1,2,4]]
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the given tree is at most 1000. Each node has
 *         a distinct value between 1 and 1000. to_delete.length <= 1000
 *         to_delete contains distinct values between 1 and 1000.
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

public class _1110_DeleteNodesAndReturnForest {

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
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		Set<Integer> toDelete = new HashSet<>();
		List<TreeNode> ans = new ArrayList<>();

		for (int val : to_delete) {
			toDelete.add(val);
		}
		dfs(root, toDelete, ans);

		if (!toDelete.contains(root.val)) {
			ans.add(root);
		} else {
			if (root.left != null) {
				ans.add(root.left);
				root.left = null;
			}

			if (root.right != null) {
				ans.add(root.right);
				root.right = null;
			}
		}
		return ans;
	}

	private TreeNode dfs(TreeNode node, Set<Integer> toDelete, List<TreeNode> ans) {
		if (node == null) {
			return null;
		}
		node.left = dfs(node.left, toDelete, ans);
		node.right = dfs(node.right, toDelete, ans);

		if (toDelete.contains(node.val)) {
			if (node.left != null) {
				ans.add(node.left);
				node.left = null;
			}

			if (node.right != null) {
				ans.add(node.right);
				node.right = null;
			}
			return null;
		}
		return node;
	}
	//============================================================================
	//Another version
	public List<TreeNode> delNodes1(TreeNode root, int[] to_delete) {
		Set< TreeNode > result = new HashSet<>();

		if( root != null ) {
			result.add( root );
			delete( root, to_delete, null, result );
		}
		List< TreeNode > returnList = new ArrayList<>();
		returnList.addAll( result );
		return returnList;
	}

	public void delete( TreeNode node, int[] toDel, TreeNode parent, Set< TreeNode > result ) {

		if( node == null ) {
			return;
		}
		TreeNode left = node.left;
		TreeNode right = node.right;

		for( int i = 0; i < toDel.length; i++ ) {

			if( node.val == toDel[ i ] ) {

				if( parent != null ) {

					if( parent.left != null && parent.left.val == node.val ) {
						parent.left = null;
					} else if( parent.right != null && parent.right.val == node.val ) {
						parent.right = null;
					}
				}
				if( result.contains( node ) ) {
					result.remove( node );
				}
				node.left = null;
				node.right = null;
				node = null;  

				if( left != null ) {
					result.add( left );
				}

				if( right != null ) {
					result.add( right );
				} 
				break;
			}
		}
		delete( left, toDel, node, result );
		delete( right, toDel, node, result );
	}
}
