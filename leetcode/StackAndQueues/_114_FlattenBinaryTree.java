package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:
				1                        1
			  /    \	                  \
             2      5        ------>>      2
            / \      \                      \
           3   4      6                      3
                                              \
                                               4
                                                \
                                                 5
                                                  \
                                                   6

Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [0]
Output: [0]

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100


Follow up: Can you flatten the tree in-place (with O(1) extra space)?
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


public class _114_FlattenBinaryTree {

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

	//Itration Approach
	public void flatten(TreeNode root) {
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode curr = root;

		while (curr != null || !stack.isEmpty()) {

			if (curr != null) {

				if (curr.right != null) {
					stack.push(curr.right);
				}
				TreeNode temp = curr.left;
				curr.left = null ;

				if (temp == null && !stack.isEmpty()) {
					temp = stack.pop();
				}
				curr.right = temp;
				curr = temp;
			}
		}
	}
	//======================================================================================
	//Recursive Approach. Sort of a Preorder traversal.
	public void flatten1(TreeNode root) {
        recur(root);
    }
    
    private TreeNode recur(TreeNode node) {
        
        if (node == null) {
            return node;
        }
        
        if (node.left == null && node.right == null) {
            return node;
        }
        TreeNode left = recur(node.left);
        TreeNode right = recur(node.right);
        
        if (left != null) {
            TreeNode tempLeft = left;
            
            while(tempLeft.right != null) {
                tempLeft = tempLeft.right;
            }
            node.right = left;
            tempLeft.right = right;
            node.left = null;
        }
        return node;
    }
}
