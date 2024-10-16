package leetcode.DP.SingleDimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * You are given an integer array coins (1-indexed) of length n and an integer maxJump. You can jump to any index i of the array coins if coins[i] != -1 and you have to pay coins[i] when you visit index i. In addition to that, if you are currently at index i, you can only jump to any index i + k where i + k <= n and k is a value in the range [1, maxJump].
 *
 * You are initially positioned at index 1 (coins[1] is not -1). You want to find the path that reaches index n with the minimum cost.
 *
 * Return an integer array of the indices that you will visit in order so that you can reach index n with the minimum cost. If there are multiple paths with the same cost, return the lexicographically smallest such path. If it is not possible to reach index n, return an empty array.
 *
 * A path p1 = [Pa1, Pa2, ..., Pax] of length x is lexicographically smaller than p2 = [Pb1, Pb2, ..., Pbx] of length y, if and only if at the first j where Paj and Pbj differ, Paj < Pbj; when no such j exists, then x < y.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,4,-1,2], maxJump = 2
 * Output: [1,3,5]
 * Example 2:
 *
 * Input: coins = [1,2,4,-1,2], maxJump = 1
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 1000
 * -1 <= coins[i] <= 100
 * coins[1] != -1
 * 1 <= maxJump <= 100
 *
 *
 */

public class _656_Coin_Path {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        long[] memo = new long[coins.length];
        Arrays.fill(memo, -1);
        long minCost = recur(coins, maxJump, memo, 0);
        List<Integer> ans = new ArrayList<>();

        if (memo[coins.length - 1] >= 0) {

            for (int i = 0 ; i < memo.length; i++) {

                if (memo[i] == minCost) {
                    minCost -= coins[i];
                    ans.add(i + 1);
                }
            }
        }
        return ans;
    }

    private long recur(int[] coins, int maxJump, long[] memo, int index) {

        if (index == coins.length - 1) {
            return memo[index] = coins[index];
        }

        if (memo[index] > -1) {
            return memo[index];
        }
        long ans = Integer.MAX_VALUE;

        for (int i = index + 1; i <= index + maxJump && i < coins.length; i++) {

            if (coins[i] >= 0) {
                long temp = coins[index] + recur(coins, maxJump, memo, i);
                ans = Math.min(ans, temp);
            }
        }
        return memo[index] = ans;
    }
}