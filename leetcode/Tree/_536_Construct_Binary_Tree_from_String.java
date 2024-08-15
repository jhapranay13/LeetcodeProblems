package leetcode.Tree;

/**
 *
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 *
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s = "4(2(3)(1))(6(5))"
 * Output: [4,2,6,3,1,5]
 * Example 2:
 *
 * Input: s = "4(2(3)(1))(6(5)(7))"
 * Output: [4,2,6,3,1,5,7]
 * Example 3:
 *
 * Input: s = "-4(2(3)(1))(6(5)(7))"
 * Output: [-4,2,6,3,1,5,7]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 10^4
 * s consists of digits, '(', ')', and '-' only.
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
public class _536_Construct_Binary_Tree_from_String {
    private  class TreeNode {
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

    public TreeNode str2tree(String s) {
        return recur(s);
    }
    private int index = 0;

    private TreeNode recur(String s) {

        if (s.length() == index) {
            return null;
        }
        int sign = s.charAt(index) == '-' ? -1 : 1;

        if (sign < 0) {
            index++;
        }
        int num = 0;

        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num *= 10;
            num += s.charAt(index++) - '0';
        }
        TreeNode ans = new TreeNode();
        ans.val = num * sign;

        if (index < s.length() && s.charAt(index) == '(') {
            index++;
            ans.left = recur(s);
        }

        if (index < s.length() && s.charAt(index) == '(') {
            index++;
            ans.right = recur(s);
        }
        index++;
        return ans;
    }
}
