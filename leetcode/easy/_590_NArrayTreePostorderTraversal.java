package leetcode.easy;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of an n-ary tree, return the postorder traversal of
 *         its nodes' values.
 * 
 *         Nary-Tree input serialization is represented in their level order
 *         traversal. Each group of children is separated by the null value (See
 *         examples)
 * 
 *         Example 1:
 *         				     1
 * 						   / | \
 *                        /  |  \
 *                       3   2   4
 *                      / \
 *                     5   6
 * 
 * 
 *         Input: root = [1,null,3,2,4,null,5,6] Output: [5,6,3,2,4,1] 
 *         
 *         Example 2:
 * 							  1
 *                          /|  \ \
 * 						   / |   | \
 *                        /  |   |  \
 *                       /   3   4   5
 *                      2   / \  |  / \
 *                         6   7 8 9   10			
 *                             | |    |
 *                            11 12   13
 *                             |
 *                            14
 * 
 *         Input: root =
 *         [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *         Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 104]. 0 <=
 *         Node.val <= 104 The height of the n-ary tree is less than or equal to
 *         1000.
 * 
 * 
 *         Follow up: Recursive solution is trivial, could you do it
 *         iteratively?
 *
 */
/*
//Definition for a Node.
class Node {
 public int val;
 public List<Node> children;

 public Node() {}

 public Node(int _val) {
     val = _val;
 }

 public Node(int _val, List<Node> _children) {
     val = _val;
     children = _children;
 }
};
 */

public class _590_NArrayTreePostorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}
	//=============================================================================
	//recursive
	public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        
        if (root == null) {
            return ans;
        }
        recur(root, ans);
        return ans;
    }
    
    private void recur(Node node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        
        for (Node child : node.children) {
            recur(child, ans);
        }
        ans.add(node.val);
    }
	//=============================================================================
	//Iterative using Stack
	public List<Integer> postorder1(Node root) {
		List<Integer> ans = new ArrayList<>();

		if (root == null) {
			return ans;
		}
		Deque<Node> stack = new LinkedList<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node node = stack.pop();
			ans.add(0, node.val);

			for (Node child : node.children) {
				stack.push(child);    
			}
		}
		return ans;
	}
}
