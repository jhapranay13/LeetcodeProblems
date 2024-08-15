package leetcode.medium;

/**
 *
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.
 *
 *
 *
 * Example 1:
 *              2                                   1
 *            /   \                               /   \
 *           1     4                             0     3
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 * Example 2:
 *             1                              8
 *              \                           /
 *               8                         1
 *
 * Input: root1 = [1,null,8], root2 = [8,1]
 * Output: [1,1,8,8]
 *
 *
 * Constraints:
 *
 * The number of nodes in each tree is in the range [0, 5000].
 * -10^5 <= Node.val <= 10^5
 *
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

public class _1305_All_Elements_in_Two_Binary_Search_Trees {
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

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        TreeNode curr1 = root1;
        TreeNode curr2 = root2;
        List<Integer> ans = new ArrayList<>();

        while (curr1 != null || curr2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {

            while (curr1 != null) {
                stack1.push(curr1);
                curr1 = curr1.left;
            }

            while (curr2 != null) {
                stack2.push(curr2);
                curr2 = curr2.left;
            }

            if (stack2.isEmpty() || !stack1.isEmpty() && stack1.peek().val < stack2.peek().val) {
                TreeNode temp = stack1.pop();
                ans.add(temp.val);
                curr1 = temp.right;
            } else {
                TreeNode temp = stack2.pop();
                ans.add(temp.val);
                curr2 = temp.right;
            }
        }
        return ans;
    }
}
