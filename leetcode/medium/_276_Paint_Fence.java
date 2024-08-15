package leetcode.medium;

/**
 *
 * You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
 *
 * Every post must be painted exactly one color.
 * There cannot be three or more consecutive posts with the same color.
 * Given the two integers n and k, return the number of ways you can paint the fence.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: All the possibilities are shown.
 * Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: 1
 * Example 3:
 *
 * Input: n = 7, k = 2
 * Output: 42
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * 1 <= k <= 105
 * The testcases are generated such that the answer is in the range [0, 231 - 1] for the given n and k.
 *
 */

public class _276_Paint_Fence {
    // Top down approach
    public int numWays(int n, int k) {
        int[] memo = new int[n];
        return recur(n, k, 0, memo);
    }
    // only the first fence can be panted in k colors. All the other fence
    // Will have the option of k - 1 colors only.
    private int recur(int n, int k, int currFence, int[] memo) {

        if (currFence == n) {
            return 1;
        }

        if (currFence > n) {
            return 0;
        }

        if (memo[currFence] > 0) {
            return memo[currFence];
        }
        int ans = 0;
        int colorLimit = currFence == 0 ? k : k - 1;
        //instead of for loop multiplying be colorLimit will also work
        // recur(n, k, currFence + 1, memo) * colorLimit
        for (int color = 0; color < colorLimit; color++) {
            ans += recur(n, k, currFence + 1, memo);
            ans += recur(n, k, currFence + 2, memo);
        }
        return memo[currFence] = ans;
    }
    //=============================================================================================
    //Bottom up solution
    public int numWays1(int n, int k) {
        int[] memo = new int[n + 3];

        for (int currFence = n; currFence >= 0; currFence--) {

            if (currFence == n) {
                memo[n] = 1;
                continue;
            }
            int ans = 0;
            int colorLimit = currFence == 0 ? k : k - 1;

            for (int color = 0; color < colorLimit; color++) {
                ans += memo[currFence + 1];
                ans += memo[currFence + 2];
            }
            memo[currFence] = ans;
        }
        return memo[0];
    }
}
