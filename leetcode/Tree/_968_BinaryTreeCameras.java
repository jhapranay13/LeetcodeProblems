package leetcode.Tree;

/**
 *
 * ou are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 *
 *
 *
 * Example 1:
 *                      O
 *                     |
 *                     C
 *                   /  \
 *                  O    O
 *
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 *                   O
 *                 /
 *               C
 *             /
 *           O
 *          /
 *         C
 *          \
 *           O
 *
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * Node.val == 0
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

public class _968_BinaryTreeCameras {
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

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int temp = recur(root);

        if (temp == -1) {
            ans++;
        }
        return ans;
    }
    private int ans = 0;
    // 0 = No need Of monitor
    // 1 = a camera has been setup and no need to monitor this
    //-1 = no camera and need monitoring
    private int recur(TreeNode node) {

        if (node == null) {
            return 0;
        }
        int left = recur(node.left);
        int right = recur(node.right);
        //No camera. Most probably leaf node or node that does not need monitoring
        if (left == 0 && right == 0) {
            return -1;
        }
        //If no camera on left or right then setup camera
        if (left == -1 || right == -1) {
            ans++;
            return 1;
        }
        //if previous one has comera then don't need camera or monitoring here
        if (left == 1 || right == 1) {
            return 0;
        }
        return -1;
    }
}
