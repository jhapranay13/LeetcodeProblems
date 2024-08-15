package leetcode.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, return the number of uni-value
 *         subtrees.
 * 
 *         A uni-value subtree means all nodes of the subtree have the same
 *         value.
 * 
 * 
 * 
 *         Example 1: 
 *         				 5 
 *         				/ \ 
 *                     1   5 
 *                    / \   \ 
 *                   5   5   5
 * 
 *         Input: root = [5,1,5,5,5,null,5] Output: 4
 *         
 *         Example 2:
 * 
 *         Input: root = [] Output: 0 
 *         
 *         Example 3:
 * 
 *         Input: root = [5,5,5,5,5,null,5] Output: 6
 * 
 * 
 *         Constraints:
 * 
 *         The numbrt of the node in the tree will be in the range [0, 1000].
 *         -1000 <= Node.val <= 1000
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

public class _250_CountUnivalueSubtrees {

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

	// =============================================================================
	// DFS Approach
	public int countUnivalSubtrees(TreeNode root) {

		if (root == null) {
			return 0;
		}
		int count = 0;

		if (dfs(root, root.val)) {
			count = 1;
		}
		int left = countUnivalSubtrees(root.left);
		int right = countUnivalSubtrees(root.right);
		return left + right + count;
	}

	private boolean dfs(TreeNode node, int val) {

		if (node == null) {
			return true;
		}
		boolean curr = node.val == val;
		boolean left = dfs(node.left, val);
		boolean right = dfs(node.right, val);
		return curr && left && right;
	}
	// =============================================================================
	// BFS Approach
	public int countUnivalSubtrees1(TreeNode root) {

		if (root == null) {
			return 0;
		}
		int count = 0;

		if (bfs(root, root.val)) {
			count = 1;
		}
		int left = countUnivalSubtrees1(root.left);
		int right = countUnivalSubtrees1(root.right);
		return left + right + count;
	}
	
	private boolean bfs(TreeNode node, int val) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            
            if (temp.val != val) {
                return false;
            }
            
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return true;
    }
}
