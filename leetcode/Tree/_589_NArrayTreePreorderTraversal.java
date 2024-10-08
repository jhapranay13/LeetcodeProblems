package leetcode.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of an n-ary tree, return the preorder traversal of its
 *         nodes' values.
 * 
 *         Nary-Tree input serialization is represented in their level order
 *         traversal. Each group of children is separated by the null value (See
 *         examples)
 * 
 * 
 * 
 *         Example 1:
 *                           1
 * 						   / | \
 *                        /  |  \
 *                       3   2   4
 *                      / \
 *                     5   6
 *         Input: root = [1,null,3,2,4,null,5,6] Output: [1,3,5,6,2,4] 
 *         
 *         Example 2:
 * 							   1
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
 * 
 *         Input: root =
 *         [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *         Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
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


public class _589_NArrayTreePreorderTraversal {

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
	//============================================================================
	//Recursion
	public List<Integer> preorder(Node root) {
		List<Integer> ans = new ArrayList<>();
		recur(root, ans);
		return ans;
	}

	private void recur(Node node, List<Integer> ans) {
		if (node == null) {
			return;
		}
		ans.add(node.val);

		for (Node child : node.children) {
			recur(child, ans);
		}
	}
	//=============================================================================
	//Iteration using stack
	public List<Integer> preorder1(Node root) {
		List<Integer> ans = new ArrayList<>();

		if (root == null) {
			return ans;
		}
		Deque<Node> stack = new LinkedList<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			Node node = stack.pop();
			ans.add(node.val);
			List<Node> children = node.children;

			for (int i = children.size() - 1; i >= 0; i--) {
				stack.push(children.get(i));
			}
		}
		return ans;
	}

}
