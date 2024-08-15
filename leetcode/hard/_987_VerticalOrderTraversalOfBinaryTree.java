package leetcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the root of a binary tree, calculate the vertical order
 *         traversal of the binary tree.
 * 
 *         For each node at position (row, col), its left and right children
 *         will be at positions (row + 1, col - 1) and (row + 1, col + 1)
 *         respectively. The root of the tree is at (0, 0).
 * 
 *         The vertical order traversal of a binary tree is a list of
 *         top-to-bottom orderings for each column index starting from the
 *         leftmost column and ending on the rightmost column. There may be
 *         multiple nodes in the same row and same column. In such a case, sort
 *         these nodes by their values.
 * 
 *         Return the vertical order traversal of the binary tree.
 * 
 * 
 * 
 *         Example 1:
 * 							
 * 							3 (0, 0)
 * 						  /	  \	
 * 				(1, -1)	 9     20 (1, 1)
 *                            /  \
 *                    (2, 0)15    7(2, 2)
 * 
 *         Input: root = [3,9,20,null,null,15,7] Output: [[9],[3,15],[20],[7]]
 *         Explanation: Column -1: Only node 9 is in this column. Column 0:
 *         Nodes 3 and 15 are in this column in that order from top to bottom.
 *         Column 1: Only node 20 is in this column. Column 2: Only node 7 is in
 *         this column. 
 *         
 *         Example 2:
 *    							1 (0, 0)
 *                            /    \
 *                           /      \
 *                          /        \
 *                         /          \
 *                        /            \
 * 						 /              \
 * 						/        	     \	
 * 				(1, -1)2                  3 (1, 1)
 *                    / \                /  \
 *           (2, - 2)4   5(2, 0)  (2, 0)6    7(2, 2)
 * 
 *         Input: root = [1,2,3,4,5,6,7] Output: [[4],[2],[1,5,6],[3],[7]]
 *         Explanation: Column -2: Only node 4 is in this column. Column -1:
 *         Only node 2 is in this column. Column 0: Nodes 1, 5, and 6 are in
 *         this column. 1 is at the top, so it comes first. 5 and 6 are at the
 *         same position (2, 0), so we order them by their value, 5 before 6.
 *         Column 1: Only node 3 is in this column. Column 2: Only node 7 is in
 *         this column. 
 *         
 *         Example 3:
 * 
 * 
 *         Input: root = [1,2,3,4,6,5,7] Output: [[4],[2],[1,5,6],[3],[7]]
 *         Explanation: This case is the exact same as example 2, but with nodes
 *         5 and 6 swapped. Note that the solution remains the same since 5 and
 *         6 are in the same location and should be ordered by their values.
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [1, 1000]. 0 <=
 *         Node.val <= 1000
 *
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */

public class _987_VerticalOrderTraversalOfBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	class Info {
		TreeNode node;
		int level;
		int index;
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		Map<Integer, PriorityQueue<Info>> v = new HashMap<>();
		Deque<Info> q = new LinkedList<>();
		Info rootInfo = new Info();
		rootInfo.node = root;
		rootInfo.level = 0;
		rootInfo.index = 0;
		q.offer(rootInfo);
		int min = 0;
		int max = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				Info info = q.poll();
				PriorityQueue<Info> heap = v.getOrDefault(info.index, new PriorityQueue<Info>(new Comparator<Info>() {

					public int compare(Info a, Info b) {

						if (a.level == b.level) {
							return a.node.val - b.node.val;
						}
						return a.level - b.level;
					}
				}));
				heap.offer(info);
				v.put(info.index, heap);

				if (info.node.left != null) {
					Info lInfo = new Info();
					lInfo.index = info.index - 1;
					lInfo.node = info.node.left;
					lInfo.level = info.level + 1;
					q.offer(lInfo);
					min = Math.min(min, lInfo.index);
				}

				if (info.node.right != null) {
					Info lInfo = new Info();
					lInfo.index = info.index + 1;
					lInfo.node = info.node.right;
					lInfo.level = info.level + 1;
					q.offer(lInfo);
					max = Math.max(max, lInfo.index);
				}
			}
		}
		List<List<Integer>> ans = new ArrayList<>();

		while (min <= max) {
			PriorityQueue<Info> pq = v.get(min++);
			List<Integer> p = new ArrayList<>();

			while (!pq.isEmpty()) {
				Info inf = pq.poll();
				p.add(inf.node.val);
			}
			ans.add(p);
		}
		return ans;
	}
}
