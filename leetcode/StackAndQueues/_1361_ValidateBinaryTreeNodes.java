package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You have n binary tree nodes numbered from 0 to n - 1 where node i
 *         has two children leftChild[i] and rightChild[i], return true if and
 *         only if all the given nodes form exactly one valid binary tree.
 * 
 *         If node i has no left child then leftChild[i] will equal -1,
 *         similarly for the right child.
 * 
 *         Note that the nodes have no values and that we only use the node
 *         numbers in this problem.
 * 
 * 
 * 
 *         Example 1:
 * 							0
 * 						   / \
 * 						  1   2
 * 							 /
 * 							3			
 * 
 *         Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 *         Output: true 
 *         
 *         Example 2:
 * 							0
 * 						   / \
 * 						  1   2
 * 						   \ /
 * 							3
 * 
 *         Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 *         Output: false 
 *         
 *         Example 3:
 * 							0
 * 						   /
 * 						  1 
 * 
 *         Input: n = 2, leftChild = [1,0], rightChild = [-1,-1] Output: false
 *         
 *         Example 4:
 * 						    0               3
 * 						   / \             / \
 * 						  1   2           4   5
 * 
 *         Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild =
 *         [2,-1,-1,5,-1,-1] Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 104 leftChild.length == rightChild.length == n -1 <=
 *         leftChild[i], rightChild[i] <= n - 1
 *
 */

public class _1361_ValidateBinaryTreeNodes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
		if (n != leftChild.length || n != rightChild.length) {
			return false;
		}

		for (int i = 0; i < n; i++) {
			Set<Integer> visited = new HashSet<>();
			Deque<Integer> q = new LinkedList<>();
			q.offer(i);

			while (!q.isEmpty()) {
				int size = q.size();

				while (size-- > 0) {
					int node = q.poll();

					if (!visited.add(node)) {
						return false;
					}

					if (leftChild[node] != -1) {
						q.offer(leftChild[node]);
					}

					if (rightChild[node] != -1) {
						q.offer(rightChild[node]);
					}
				}
			}

			if (visited.size() == n) {
				return true;
			}
		}
		return false;
	}	
}
