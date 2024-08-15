package leetcode.Tree;

/**
 *
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 *
 *
 *
 * Example 1:
 *                      1
 *                     / \
 *                    2   3
 *                   /
 *                  4
 *
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 *
 * Example 2:
 *                    1
 *                   / \
 *                  2   3
 *                   \   \
 *                   4    5
 *
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 *
 * Example 3:
 *                      1
 *                    /  \
 *                   2    3
 *                    \
 *                     4
 *
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 100].
 * 1 <= Node.val <= 100
 * Each node has a unique value.
 * x != y
 * x and y are exist in the tree.
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
public class _993_Cousins_in_Binary_Tree {
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
  // Can be donw without dx and dy. it will still work
  int dx = -1;
    int dy = -1;

    public boolean isCousins(TreeNode root, int x, int y) {
        boolean isPresent =  recur(root, x, y, 0);

        if (isPresent && dy != -1 && dx != -1 && dx == dy) {
            return true;
        }
        return false;
    }

    private boolean recur(TreeNode node, int x, int y, int depth) {

        if (node == null) {
            return false;
        }

        if (node.val == x) {
            dx = depth;
            return true;
        }

        if (node.val == y) {
            dy = depth;
            return true;
        }
        boolean isLeft = recur(node.left, x, y, depth + 1);
        boolean isRight = recur(node.right, x, y, depth + 1);

        if (isLeft && isRight && ((x == node.left.val && y == node.right.val) ||
                (y == node.left.val && x == node.right.val))) {
            return false;
        }
        return isLeft || isRight;
    }
}
