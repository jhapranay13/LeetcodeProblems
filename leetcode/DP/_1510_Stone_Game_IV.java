package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
 *
 * Also, if a player cannot make a move, he/she loses the game.
 *
 * Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * Example 3:
 *
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 *
 */

public class _1510_Stone_Game_IV {
    // Top down Approach
    public boolean winnerSquareGame(int n) {
        Map<Integer, Boolean> memo = new HashMap<>();
        return recur(n, memo);
    }

    private boolean recur(int n, Map<Integer, Boolean> memo) {
        // If 0 then current player lose
        if (n == 0) {
            return false;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int limit = (int)Math.sqrt(n);
        boolean ans = false;

        for (int i = 1; i <= limit; i++) {
            // if next guy losses then this current guy win
            if (!recur(n - i * i, memo)) {
                ans = true;
                break;
            }
        }
        memo.put(n, ans);
        return ans;
    }
    //=============================================================================================
    // Bottom up approach
    public boolean winnerSquareGame1(int n) {
        Map<Integer, Boolean> memo = new HashMap<>();
        memo.put(0, false);

        for (int N = 0; N <= n; N++) {
            int limit = (int)Math.sqrt(N);
            boolean ans = false;

            for (int i = 1; i <= limit; i++) {
                // if next guy losses then this current guy win
                if (!memo.getOrDefault(N - i * i, false)) {
                    ans = true;
                    break;
                }
            }
            memo.put(N, ans);
        }
        return recur(n, memo);
    }
}
