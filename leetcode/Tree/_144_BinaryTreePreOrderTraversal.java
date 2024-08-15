package leetcode.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
			1
			 \
			  2
			 /
			3

Input: root = [1,null,2,3]
Output: [1,2,3]

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
Output: [1,2]

Example 5:
				1
				 \
				  2

Input: root = [1,null,2]
Output: [1,2]


Constraints:

The number of nodes in the tree is in the range [0, 100].
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

public class _144_BinaryTreePreOrderTraversal {

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
	//recursive approach
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		recur(root, ans);

		for (int i = ans.size() - 1; i >= 0; i--){

			if (ans.get(i) == null) {
				ans.remove(i);
			}
		}
		return ans;
	}

	private void recur(TreeNode node, List<Integer> ans) {

		if (node == null) {
			ans.add(null);
			return;
		}
		ans.add(node.val);
		recur(node.left, ans);
		recur(node.right, ans);
	}
	//=============================================================================
	//iterative approach
	public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        
        while (curr != null || !stack.isEmpty()) {
            
            if (curr != null) {
                ans.add(curr.val);
                
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                curr = curr.left;
            } else {
                curr = stack.pop();
            }
        }
        return ans;
    }
}
