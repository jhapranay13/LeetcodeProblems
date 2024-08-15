package leetcode.DP.TwoDimension;

import java.util.Arrays;

/**
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * There are n stones arranged in a row. On each player's turn, they can remove either the leftmost stone or the rightmost stone from the row and receive points equal to the sum of the remaining stones' values in the row. The winner is the one with the higher score when there are no stones left to remove.
 *
 * Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's difference. Alice's goal is to maximize the difference in the score.
 *
 * Given an array of integers stones where stones[i] represents the value of the ith stone from the left, return the difference in Alice and Bob's score if they both play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [5,3,1,4,2]
 * Output: 6
 * Explanation:
 * - Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
 * - Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
 * - Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
 * - Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
 * - Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
 * The score difference is 18 - 12 = 6.
 * Example 2:
 *
 * Input: stones = [7,90,5,1,100,10,10,2]
 * Output: 122
 *
 *
 * Constraints:
 *
 * n == stones.length
 * 2 <= n <= 1000
 * 1 <= stones[i] <= 1000
 *
 */

public class _1690_Stone_Game_VII {
    public int stoneGameVII(int[] stones) {
        int[] presum = new int[stones.length];
        presum[0] = stones[0];

        for (int i = 1; i < stones.length; i++) {
            presum[i] = presum[i - 1] + stones[i];
        }
        int memo[][][] = new int[stones.length][stones.length][2];

        for (int mem[][] : memo) {

            for (int[] me : mem) {
                Arrays.fill(me, -1);
            }
        }
        return recur(presum, 0, stones.length - 1, 0, memo);
    }

    private int recur(int[] presum, int lo, int hi, int isAlice, int[][][] memo) {

        if (lo == hi) {
            return 0;
        }

        if (memo[lo][hi][isAlice] != -1) {
            return memo[lo][hi][isAlice];
        }
        int firstRemoved = presum[hi] - presum[lo];
        int lastRemoved = presum[hi - 1] - (lo == 0 ? 0 : presum[lo  - 1]);
        int ans;

        if (isAlice == 0) {
            ans = Math.max(firstRemoved + recur(presum, lo + 1, hi, isAlice ^ 1, memo),
                    lastRemoved + recur(presum, lo, hi - 1, isAlice ^ 1, memo));
        } else {
            ans = Math.min(recur(presum, lo + 1, hi, isAlice ^ 1, memo) - firstRemoved,
                    recur(presum, lo, hi - 1, isAlice ^ 1, memo) - lastRemoved);
        }
        return memo[lo][hi][isAlice] = ans;
    }
}
