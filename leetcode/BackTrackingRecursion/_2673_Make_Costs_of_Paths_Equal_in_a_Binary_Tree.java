package leetcode.BackTrackingRecursion;

/**
 *
 * You are given an integer n representing the number of nodes in a perfect binary tree consisting of nodes numbered from 1 to n. The root of the tree is node 1 and each node i in the tree has two children where the left child is the node 2 * i and the right child is 2 * i + 1.
 *
 * Each node in the tree also has a cost represented by a given 0-indexed integer array cost of size n where cost[i] is the cost of node i + 1. You are allowed to increment the cost of any node by 1 any number of times.
 *
 * Return the minimum number of increments you need to make the cost of paths from the root to each leaf node equal.
 *
 * Note:
 *
 * A perfect binary tree is a tree where each node, except the leaf nodes, has exactly 2 children.
 * The cost of a path is the sum of costs of nodes in the path.
 *
 *
 * Example 1:
 *                  Node : point
 *                   1 : 1
 *                 /        \
 *              2 : 5       3 : 2
 *             /    \       /    \
 *          4 : 2  5 : 3   6 : 3   7 : 1
 *
 * Input: n = 7, cost = [1,5,2,2,3,3,1]
 * Output: 6
 * Explanation: We can do the following increments:
 * - Increase the cost of node 4 one time.
 * - Increase the cost of node 3 three times.
 * - Increase the cost of node 7 two times.
 * Each path from the root to a leaf will have a total cost of 9.
 * The total increments we did is 1 + 3 + 2 = 6.
 * It can be shown that this is the minimum answer we can achieve.
 * Example 2:
 *
 *                 Node : point
 *                    1 : 5
 *                 /        \
 *              2 : 5       3 : 3
 *
 * Input: n = 3, cost = [5,3,3]
 * Output: 0
 * Explanation: The two paths already have equal total costs, so no increments are needed.
 *
 *
 * Constraints:
 *
 * 3 <= n <= 105
 * n + 1 is a power of 2
 * cost.length == n
 * 1 <= cost[i] <= 104
 *
 *
 */

public class _2673_Make_Costs_of_Paths_Equal_in_a_Binary_Tree {
    public int minIncrements(int n, int[] cost) {
        maxi = recurMax(cost, 0);
        recur(cost, 0, 0);
        return ans;
    }
    private int ans = 0;
    private int maxi = 0;

    private int recurMax(int[] cost, int index) {

        if (index >= cost.length) {
            return 0;
        }
        int currCost = cost[index];
        int left = recurMax(cost, index * 2 + 1);
        int right = recurMax(cost, index * 2 + 2);
        return currCost + Math.max(left, right);
    }

    private int recur(int[] cost, int index, int sum) {

        if (index * 2 + 1 >= cost.length) {
            return maxi - (sum + cost[index]); // returning the deficit
        }
        int currCost = cost[index];

        int left = recur(cost, index * 2 + 1, sum + currCost);
        int right = recur(cost, index * 2 + 2, sum + currCost);
        // we can caluclate the difference. That difference is what
        // the minimu side will have to increment to make the values of both path
        // equal to maximium
        int diff = Math.abs(left - right);
        ans += diff;  // adjusting the difference

        return Math.min(left, right);
    }
    //=============================================================================================
    // Another approach using DFS
    public int minIncrements1(int n, int[] cost) {
        recur(cost, 0);
        return ans;
    }
    //private int ans = 0;

    private int recur(int[] cost, int index) {

        if (index * 2 + 1 >= cost.length) {
            return cost[index]; // returning the deficit
        }
        int currCost = cost[index];

        int left = recur(cost, index * 2 + 1);
        int right = recur(cost, index * 2 + 2);
        // trying to make both paths equal
        // finally all paths will be equal to the max path
        int diff = Math.abs(left - right);
        ans += diff;  // adjusting the difference on current level
        // Since lower is now adjusted to max send max up
        return currCost + Math.max(left, right);
    }
}
