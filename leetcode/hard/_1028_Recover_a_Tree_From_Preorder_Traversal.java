package leetcode.hard;

/**
 *
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 *
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
 *
 * If a node has only one child, that child is guaranteed to be the left child.
 *
 * Given the output traversal of this traversal, recover the tree and return its root.
 *
 *
 *
 * Example 1:
 *                       1
 *                     /   \
 *                   2       5
 *                 /  \     /  \
 *                3    4   6    7
 *
 * Input: traversal = "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 * Example 2:
 *                      1
 *                    /   \
 *                  2      5
 *                 /        \
 *                3          6
 *               /            \
 *              4              7
 *
 * Input: traversal = "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 * Example 3:
 *                       1
 *                     /
 *                  401
 *                /     \
 *             349       88
 *             /
 *            90
 *
 * Input: traversal = "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 *
 *
 * Constraints:
 *
 * The number of nodes in the original tree is in the range [1, 1000].
 * 1 <= Node.val <= 10^9
 *
 */

import java.util.Deque;
import java.util.LinkedList;

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

public class _1028_Recover_a_Tree_From_Preorder_Traversal {
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
    //===============================================================================================
    //2 stavk approavh
    public TreeNode recoverFromPreorder(String traversal) {
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> countStack = new LinkedList<>();
        int count = 0;
        TreeNode ans = null;
        int number = 0;

        for (int i = 0; i < traversal.length(); i++) {
            char ch = traversal.charAt(i);

            if (Character.isDigit(ch)) {
                number *= 10;
                number += ch - '0';

                if (i < traversal.length() - 1 && traversal.charAt(i + 1) != '-') {
                    continue;
                }
            }

            if (ch == '-') {
                count++;
            } else {
                TreeNode node = new TreeNode(number);

                if (!countStack.isEmpty() && count > countStack.peek()) {
                    stack.peek().left = node;
                } else if (!countStack.isEmpty() && count <= countStack.peek()) {

                    while (count <= countStack.peek()) {
                        countStack.pop();
                        stack.pop();
                    }
                    stack.peek().right = node;
                }
                countStack.push(count);
                stack.push(node);
                count = 0;
                number = 0;

                if (ans == null) {
                    ans = node;
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // Single Stack Approach
    public TreeNode recoverFromPreorder1(String traversal) {
        Deque<TreeNode> stack = new LinkedList<>();
        int count = 0;
        TreeNode ans = null;
        int number = 0;

        for (int i = 0; i < traversal.length(); i++) {
            char ch = traversal.charAt(i);

            if (Character.isDigit(ch)) {
                number *= 10;
                number += ch - '0';

                if (i < traversal.length() - 1 && traversal.charAt(i + 1) != '-') {
                    continue;
                }
            }

            if (ch == '-') {
                count++;
            } else {
                TreeNode node = new TreeNode(number);

                if (!stack.isEmpty() && count >= stack.size()) {
                    stack.peek().left = node;
                } else if (!stack.isEmpty() && count < stack.size()) {

                    while (count < stack.size()) {
                        stack.pop();
                    }
                    stack.peek().right = node;
                }
                stack.push(node);
                count = 0;
                number = 0;

                if (ans == null) {
                    ans = node;
                }
            }
        }
        return ans;
    }

}
