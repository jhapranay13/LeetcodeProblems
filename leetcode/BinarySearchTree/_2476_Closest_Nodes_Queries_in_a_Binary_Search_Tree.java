package leetcode.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * You are given the root of a binary search tree and an array queries of size n consisting of positive integers.
 *
 * Find a 2D array answer of size n where answer[i] = [mini, maxi]:
 *
 * mini is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not exist, add -1 instead.
 * maxi is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not exist, add -1 instead.
 * Return the array answer.
 *
 *
 *
 * Example 1:
 *
 *                  6
 *                /   \
 *              2      13
 *            /  \    /  \
 *           1    4  9    15
 *                       /
 *                     14
 *
 * Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
 * Output: [[2,2],[4,6],[15,-1]]
 * Explanation: We answer the queries in the following way:
 * - The largest number that is smaller or equal than 2 in the tree is 2, and the smallest number that is greater or equal than 2 is still 2. So the answer for the first query is [2,2].
 * - The largest number that is smaller or equal than 5 in the tree is 4, and the smallest number that is greater or equal than 5 is 6. So the answer for the second query is [4,6].
 * - The largest number that is smaller or equal than 16 in the tree is 15, and the smallest number that is greater or equal than 16 does not exist. So the answer for the third query is [15,-1].
 * Example 2:
 *              4
 *               \
 *                9
 *
 * Input: root = [4,null,9], queries = [3]
 * Output: [[-1,4]]
 * Explanation: The largest number that is smaller or equal to 3 in the tree does not exist, and the smallest number that is greater or equal to 3 is 4. So the answer for the query is [-1,4].
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 10^5].
 * 1 <= Node.val <= 10^6
 * n == queries.length
 * 1 <= n <= 10^5
 * 1 <= queries[i] <= 10^6
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

public class _2476_Closest_Nodes_Queries_in_a_Binary_Search_Tree {
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

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> holder = new ArrayList<>();
        recur(root, holder);
        List<List<Integer>> ans = new ArrayList<>();

        for (int query : queries) {
            List<Integer> ansHolder = new ArrayList<>();
            int lessThanEqualIndex = bsLessThanEqual(holder, query);
            int greaterThanEqualIndex = bsGreaterThanEqual(holder, query);
            ansHolder.add(lessThanEqualIndex == -1 ? -1 : holder.get(lessThanEqualIndex));
            ansHolder.add(greaterThanEqualIndex == -1 ? -1 : holder.get(greaterThanEqualIndex));
            ans.add(ansHolder);
        }
        return ans;
    }

    private void recur(TreeNode node, List<Integer> holder) {

        if (node == null) {
            return;
        }
        recur(node.left, holder);
        holder.add(node.val);
        recur(node.right, holder);
    }

    private int bsLessThanEqual(List<Integer> holder, int query) {
        int lo = 0;
        int hi = holder.size() -1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (holder.get(pivot) <= query) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private int bsGreaterThanEqual(List<Integer> holder, int query) {
        int lo = 0;
        int hi = holder.size() -1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (holder.get(pivot) < query) {
                lo = pivot + 1;
            } else {
                ans = pivot;
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
