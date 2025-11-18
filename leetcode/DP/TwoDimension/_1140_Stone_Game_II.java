package leetcode.DP.TwoDimension;

/**
 *
 * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
 *
 * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
 *
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 *
 * The game continues until all the stones have been taken.
 *
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
 * Example 2:
 *
 * Input: piles = [1,2,3,4,5,100]
 * Output: 104
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 104
 *
 */

public class _1140_Stone_Game_II {
    //(Alice's Stone + Bob's Stone) = totalStones
    //(Alice's Stone - Bob's Stone) = Alice's score
    // 2 * Alice's Stone = totalStones + Alice's score
    //Alice's Stone      = (totalStones + Alice's score) / 2
    public int stoneGameII(int[] piles) {
        int m = 1;
        int[] presum = new int[piles.length];
        presum[piles.length - 1] = piles[piles.length - 1];

        for (int i = piles.length - 2; i >= 0; i--) {
            presum[i] = presum[i + 1] + piles[i];
        }
        Integer memo[][] = new Integer[piles.length][piles.length];
        int ans = recur(presum, m, 0, memo);
        return (ans + presum[0]) / 2;
    }

    private int recur(int[] presum, int m, int index, Integer memo[][]) {

        if (index + m * 2 >= presum.length) {
            return presum[index];
        }

        if (memo[m][index] != null) {
            return memo[m][index];
        }
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < 2 * m; i++) {
            int curr = presum[index] - presum[index + i + 1];
            int temp = recur(presum, Math.max(m, i + 1), index + i + 1, memo);
            ans = Math.max(ans, curr - temp);
        }
        return memo[m][index] = ans;
    }
    //=============================================================================================
    int[] piles;
    int[] suffix;
    Integer[][] dp;

    public int stoneGameII_1(int[] piles) {
        this.piles = piles;
        int n = piles.length;

        suffix = new int[n + 1];
        dp = new Integer[n][n + 1]; // i from [0..n-1], M from [1..n]

        // Build suffix sum: suffix[i] = total stones from i to end
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(0, 1);  // Start at index 0, M=1
    }

    private int solve(int i, int M) {
        int n = piles.length;
        if (i >= n) return 0;

        if (dp[i][M] != null) return dp[i][M];

        int best = 0;
        // Try taking X piles, where 1 ≤ X ≤ 2M
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {
            // Opponent gets solve(i + X, max(M, X))
            int opponent = solve(i + X, Math.max(M, X));

            // Current gets totalRemaining - opponent
            best = Math.max(best, suffix[i] - opponent);
        }

        return dp[i][M] = best;
    }

}
