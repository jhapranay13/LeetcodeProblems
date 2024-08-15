package leetcode.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.
 *
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
 *
 *
 *
 * Example 1:
 *                  5
 *                /   \
 *              2      -3
 *
 * Input: root = [5,2,-3]
 * Output: [2,-3,4]
 * Example 2:
 *`                 5
 *                /   \
 *               2    -5
 *
 * Input: root = [5,2,-5]
 * Output: [2]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
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

public class _508_Most_Frequent_Subtree_Sum {
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

    Map<Integer, Integer> sumFreq = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        recur(root);
        List<Integer> ans = new ArrayList<>();
        int currFreq = 0;

        for (int key : sumFreq.keySet()) {
            int freq = sumFreq.get(key);

            if (freq > currFreq) {
                ans = new ArrayList<>();
                currFreq = freq;
                ans.add(key);
            } else if (freq == currFreq) {
                ans.add(key);
            }
        }
        int[] ret = new int[ans.size()];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = ans.get(i);
        }
        return ret;
    }

    private void recur(TreeNode node) {

        if (node == null) {
            return;
        }
        int sum = initSum(node);
        sumFreq.put(sum, sumFreq.getOrDefault(sum, 0 ) + 1);
        recur(node.left);
        recur(node.right);
    }

    private int initSum(TreeNode node) {

        if (node == null) {
            return 0;
        }
        return node.val + initSum(node.left) + initSum(node.right);
    }
}
