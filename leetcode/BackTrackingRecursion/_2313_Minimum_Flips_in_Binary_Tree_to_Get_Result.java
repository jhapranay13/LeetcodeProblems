package leetcode.BackTrackingRecursion;

/**
 *
 *
 * You are given the root of a binary tree with the following properties:
 *
 * Leaf nodes have either the value 0 or 1, representing false and true respectively.
 * Non-leaf nodes have either the value 2, 3, 4, or 5, representing the boolean operations OR, AND, XOR, and NOT, respectively.
 * You are also given a boolean result, which is the desired result of the evaluation of the root node.
 *
 * The evaluation of a node is as follows:
 *
 * If the node is a leaf node, the evaluation is the value of the node, i.e. true or false.
 * Otherwise, evaluate the node's children and apply the boolean operation of its value with the children's evaluations.
 * In one operation, you can flip a leaf node, which causes a false node to become true, and a true node to become false.
 *
 * Return the minimum number of operations that need to be performed such that the evaluation of root yields result. It can be shown that there is always a way to achieve result.
 *
 * A leaf node is a node that has zero children.
 *
 * Note: NOT nodes have either a left child or a right child, but other non-leaf nodes have both a left child and a right child.
 *
 *
 *
 * Example 1:
 *              AND                                        AND
 *             /   \                                     /    \
 *          NOT     XOR                               NOT      XOR
 *         /       /   \            ------>          /        /   \
 *       OR      true   true                       OR      false   true
 *      /  \                                      /  \     (flip)
 *  true    false                            false    false
 *                                          (flip)
 *
 * Input: root = [3,5,4,2,null,1,1,1,0], result = true
 * Output: 2
 * Explanation:
 * It can be shown that a minimum of 2 nodes have to be flipped to make the root of the tree
 * evaluate to true. One way to achieve this is shown in the diagram above.
 * Example 2:
 *
 * Input: root = [0], result = false
 * Output: 0
 * Explanation:
 * The root of the tree already evaluates to false, so 0 nodes have to be flipped.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 0 <= Node.val <= 5
 * OR, AND, and XOR nodes have 2 children.
 * NOT nodes have 1 child.
 * Leaf nodes have a value of 0 or 1.
 * Non-leaf nodes have a value of 2, 3, 4, or 5.
 *
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

public class _2313_Minimum_Flips_in_Binary_Tree_to_Get_Result {
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

    private static final int FALSE = 0;
    private static final int TRUE = 1;
    private static final int OR = 2;
    private static final int AND = 3;
    private static final int XOR = 4;
    private static final int NOT = 5;

    public int minimumFlips(TreeNode root, boolean result) {
        int[] ans = recur(root);

        if (result) {
            return ans[0];
        }
        return ans[1];
    }

    private int[] recur(TreeNode node) {

        if (node == null) {
            return new int[] {0, 0};
        }

        if (node.left == null && node.right == null) {
            int makeTrue = node.val == FALSE ? 1 : 0;
            int makeFalse = node.val == TRUE ? 1 : 0;
            return new int[] {makeTrue, makeFalse};
        }
        int[] leftAns = recur(node.left);
        int[] rightAns = recur(node.right);
        int leftTrueRightFalse = leftAns[0] + rightAns[1];
        int leftFalseRightTrue = leftAns[1] + rightAns[0];
        int leftTrueRightTrue = leftAns[0] + rightAns[0];
        int leftFalseRightFalse = leftAns[1] + rightAns[1];

        if (node.val == OR) {

            int makeTrue = Math.min(leftTrueRightFalse,
                    Math.min(leftFalseRightTrue, leftTrueRightTrue));
            int makeFalse = leftFalseRightFalse;
            return new int[] {makeTrue, makeFalse};
        } else if (node.val == AND) {
            int makeTrue = leftTrueRightTrue;
            int makeFalse = Math.min(leftTrueRightFalse,
                    Math.min(leftFalseRightTrue, leftFalseRightFalse));
            return new int[] {makeTrue, makeFalse};
        } else if (node.val == XOR) {
            int makeTrue = Math.min(leftTrueRightFalse, leftFalseRightTrue);
            int makeFalse = Math.min(leftTrueRightTrue, leftFalseRightFalse);
            return new int[] {makeTrue, makeFalse};
        } else {

            if (node.left != null) {
                return new int[] {leftAns[1], leftAns[0]};
            } else {
                return new int[] {rightAns[1], rightAns[0]};
            }
        }
    }
}
