package leetcode.medium;

/**
 *
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *                              3
 *                            /   \
 *                          /      \
 *                        /         \
 *                       5           1
 *                     /  \         / \
 *                    6   2        0   8
 *                       / \
 *                      7   4
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 * Example 2:
 *
 * Input: root = [1], target = 1, k = 3
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 500].
 * 0 <= Node.val <= 500
 * All the values Node.val are unique.
 * target is the value of one of the nodes in the tree.
 * 0 <= k <= 1000
 *
 *
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class _863_AllNodesKDistanceAwayBinaryTree {

    class TreeNode {
     int val;
    TreeNode left;
    TreeNode right;
   TreeNode(int x) { val = x; }
  }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        initParentMap(root, parentMap);
        Deque<TreeNode> q = new LinkedList<>();
        int step = 0;
        q.offer(target);
        Set<TreeNode> v = new HashSet<>();
        v.add(target);
        List<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                TreeNode node = q.poll();

                if (node.left != null && !v.contains(node.left)) {
                    q.offer(node.left);
                    v.add(node.left);
                }

                if (node.right != null && !v.contains(node.right)) {
                    q.offer(node.right);
                    v.add(node.right);
                }

                if (parentMap.get(node) != null && !v.contains(parentMap.get(node))) {
                    q.offer(parentMap.get(node));
                    v.add(parentMap.get(node));
                }
                ans.add(node.val);
            }

            if (step == k) {
                break;
            }
            step++;
            ans = new ArrayList<>();
        }
        return ans;
    }

    private void initParentMap(TreeNode node, Map<TreeNode, TreeNode> parentMap) {

        if (node == null) {
            return;
        }

        if (node.left != null) {
            parentMap.put(node.left, node);
        }

        if (node.right != null) {
            parentMap.put(node.right, node);
        }
        initParentMap(node.left, parentMap);
        initParentMap(node.right, parentMap);
    }
}
