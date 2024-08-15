package leetcode.Tree;

import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a n-ary tree, find its maximum depth.
 * 
 *         The maximum depth is the number of nodes along the longest path from
 *         the root node down to the farthest leaf node.
 * 
 *         Nary-Tree input serialization is represented in their level order
 *         traversal, each group of children is separated by the null value (See
 *         examples).
 * 
 *         Example 1: 1 / | \ 3 2 4 / \ 5 6
 * 
 *         Input: root = [1,null,3,2,4,null,5,6] Output: 3
 * 
 *         Example 2: 1 /||\ / || \ / || \ / / \ \ / / | \ 2 3 4 5 / \ | / \ 6 7
 *         8 9 10 | | | 11 12 13 | 14
 * 
 *         Input: root =
 *         [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *         Output: 5
 * 
 * 
 *         Constraints:
 * 
 *         The depth of the n-ary tree is less than or equal to 1000. The total
 *         number of nodes is between [0, 104].
 *
 */
/*
 * //Definition for a Node. class Node { public int val; public List<Node>
 * children;
 * 
 * public Node() {}
 * 
 * public Node(int _val) { val = _val; }
 * 
 * public Node(int _val, List<Node> _children) { val = _val; children =
 * _children; } };
 */

public class _559_MaximumDepthOfNArrayTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	public int maxDepth(Node root) {

		if (root == null) {
			return 0;
		}
		List<Node> children = root.children;
		int ans = 0;

		for (Node child : children) {
			ans = Math.max(ans, maxDepth(child));
		}
		return 1 + ans;
	}
	//=============================================================================
	//Other version
	public int maxDepth1(Node root) {

		if( root == null ) {
			return 0;
		}
		int ans = dfs( root );
		return ans;
	}

	private int dfs( Node node ) {

		if( node == null ) {
			return 0;
		}
		List< Node > children = node.children;
		int ans = 0;

		for( Node ch : children ) {
			ans = Math.max( ans, dfs( ch ) ); 
		}
		return 1 + ans;
	}
}
