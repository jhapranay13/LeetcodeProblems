package leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:

			3
		  /   \
		 9    20
		     /  \
		    15   7

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-1000 <= Node.val <= 1000
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

public class _102_BinaryTreeLevelOrderTraversal {

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

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();

		if( root != null ) {
			Deque< TreeNode > q = new LinkedList<>();
			q.offer( root );

			while( !q.isEmpty() ) {
				int size = q.size();
				List< Integer > p = new ArrayList<>();

				while( size-- > 0 ) {
					TreeNode child = q.poll();
					p.add( child.val );

					if( child.left != null ) {
						q.offer( child.left );
					}

					if( child.right != null ) {
						q.offer( child.right );
					}
				}
				ans.add( p );
			}
		}
		return ans;
	}
	//=============================================================================
	public List<List<Integer>> levelOrder1(TreeNode root) {
		List< List< Integer > > result = new ArrayList<>();

		if( root == null ) {
			return result;
		}
		Deque< TreeNode > q = new LinkedList<>();
		Deque< TreeNode > nq = new LinkedList<>();
		List< Integer > p = new ArrayList<>();
		q.offer( root );

		while( !q.isEmpty() ) {
			TreeNode node = q.poll();
			p.add( node.val );

			if( node.left != null ) {
				nq.add( node.left );
			}

			if( node.right != null ) {
				nq.add( node.right );
			}

			if( q.isEmpty() ) {
				q = nq;
				result.add( p );
				p = new ArrayList<>();
				nq = new LinkedList<>();
			}
		}
		return result;
	}
	//=============================================================================
	//recursive approach
	public List<List<Integer>> levelOrder2(TreeNode root) {

		if (root == null) {
			return ans;
		}
		recur(root, 0);
		return ans;
	}

	private List<List<Integer>> ans = new ArrayList<>();

	private void recur(TreeNode root, int level) {

		if(root == null) {
			return;
		}

		if (level == ans.size()) {
			ans.add(new ArrayList<Integer>());
		}
		ans.get(level).add(root.val);
		recur(root.left, level + 1);
		recur(root.right, level + 1);
	}
}
