package leetcode.Tree;

/**
 *
 *
 * You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.
 *
 * Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 *
 * As a reminder, any shorter prefix of a string is lexicographically smaller.
 *
 * For example, "ab" is lexicographically smaller than "aba".
 * A leaf of a node is a node that has no children.
 *
 *
 *
 * Example 1:
 *                   a
 *                //   \
 *               b      c
 *            //  \   /  \
 *            d   e  d    e
 *
 * Input: root = [0,1,2,3,4,3,4]
 * Output: "dba"
 * Example 2:
 *              z
 *            /   \
 *          b      d
 *        /  \   //  \
 *       b   d   a    c
 *
 *
 * Input: root = [25,1,3,1,3,0,2]
 * Output: "adz"
 * Example 3:
 *                  c
 *                /   \\
 *              c      b
 *               \    //
 *               b   a
 *             /
 *            a
 *
 * Input: root = [2,2,1,null,1,0,null,0]
 * Output: "abc"
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 8500].
 * 0 <= Node.val <= 25
 *
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

public class _988_Smallest_String_Starting_From_Leaf {
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

    public String smallestFromLeaf(TreeNode root) {
        recur(root, new StringBuilder());
        return ans;
    }
    private String ans = "~";

    private void recur(TreeNode node, StringBuilder holder) {

        if (node != null) {
            holder.append((char)('a' + node.val));

            if (node.left == null && node.right == null) {
                holder.reverse();
                String str = holder.toString();
                holder.reverse();

                if (str.compareTo(ans) < 0) {
                    ans = str;
                }
            }
            recur(node.left, holder);
            recur(node.right, holder);
            holder.deleteCharAt(holder.length() - 1);
        }
    }
}
