package leetcode.Tree;

/**
 *
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,
 *
 * If isLefti == 1, then childi is the left child of parenti.
 * If isLefti == 0, then childi is the right child of parenti.
 * Construct the binary tree described by descriptions and return its root.
 *
 * The test cases will be generated such that the binary tree is valid.
 *
 *
 *
 * Example 1:
 *                   50
 *                 /   \
 *              20      80
 *            /   \    /
 *          15    17  19
 *
 * Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * Output: [50,20,80,15,17,19]
 * Explanation: The root node is the node with value 50 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 *
 * Example 2:
 *                   1
 *                  /
 *                 2
 *                  \
 *                   3
 *                  /
 *                4
 *
 * Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * Output: [1,2,null,null,3,4]
 * Explanation: The root node is the node with value 1 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 *
 *
 * Constraints:
 *
 * 1 <= descriptions.length <= 10^4
 * descriptions[i].length == 3
 * 1 <= parenti, childi <= 10^5
 * 0 <= isLefti <= 1
 * The binary tree described by descriptions is valid.
 *
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

public class _2196_Create_Binary_Tree_From_Descriptions {
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

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> holderMap = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] desc : descriptions) {
            int parent = desc[0];
            int child = desc[1];
            int dir = desc[2];

            if (!holderMap.containsKey(parent)) {
                holderMap.put(parent, new TreeNode(parent));
            }

            if (!holderMap.containsKey(child)) {
                holderMap.put(child, new TreeNode(child));
            }
            children.add(child);

            if (dir == 1) {
                holderMap.get(parent).left = holderMap.get(child);
            } else {
                holderMap.get(parent).right = holderMap.get(child);
            }
        }
        TreeNode ans = null;

        for (int[] desc : descriptions) {

            if (!children.contains(desc[0])) {
                ans = holderMap.get(desc[0]);
                break;
            }
        }
        return ans;
    }
}
