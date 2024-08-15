package leetcode.HashMapHashSet;


/**
 *
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 *
 *
 * Example 1:
 *                           2
 *                         //  \\
 *                        3     1
 *                      // \     \\
 *                     3    1     1
 *
 *
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 2:
 *                      2
 *                   //   \
 *                   1     1
 *                //   \
 *                1     3
 *                       \
 *                        1
 *
 *
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * Example 3:
 *
 * Input: root = [9]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 9
 *
 *
 */

import java.util.HashMap;
import java.util.Map;

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

public class _1457_Pseudo_Palindromic_Paths_in_a_Binary_Tree {
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

    public int pseudoPalindromicPaths (TreeNode root) {
        Map<Integer, Integer> countMap = new HashMap<>();
        recur(root, countMap, 0);
        return ans;
    }
    private int ans = 0;

    private void recur(TreeNode root, Map<Integer, Integer> countMap, int oddCount) {

        if (root == null) {
            return;
        }
        int val = root.val;
        int count = countMap.getOrDefault(val, 0);
        count++;
        countMap.put(val, count);
        int nextOddCount = oddCount;

        if (count % 2 == 1) {
            nextOddCount++;
        } else {
            nextOddCount--;
        }

        if (root.left == null && root.right == null) {

            if (nextOddCount <= 1) {
                ans++;
            }
        } else {

            if (root.left != null) {
                recur(root.left, countMap, nextOddCount);
            }

            if (root.right != null) {
                recur(root.right, countMap, nextOddCount);
            }
        }
        if (count == 1) {
            countMap.remove(val);
        } else {
            countMap.put(val, count - 1);
        }
    }
}
