package leetcode.DP;

/**
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there is a number n on the chalkboard. On each player's turn, that player makes a move consisting of:
 *
 * Choosing any x with 0 < x < n and n % x == 0.
 * Replacing the number n on the chalkboard with n - x.
 * Also, if a player cannot make a move, they lose the game.
 *
 * Return true if and only if Alice wins the game, assuming both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 *
 * Input: n = 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 *
 */

public class _1025_Divisor_Game {
    public boolean divisorGame(int n) {
        Boolean[][] memo = new Boolean[n + 1][2];
        return recur(n, 0, memo);
    }

    private boolean recur(int n, int alicePlay, Boolean[][] memo) {

        if (memo[n][alicePlay] != null) {
            return memo[n][alicePlay];
        }

        int nextMoveAlicePlay = alicePlay == 0 ? 1 : 0;

        for (int i = 1; i < n; i++ ) {

            if (n % i != 0) {
                continue;
            }
            int nextN = n - i;

            if (alicePlay == 0) {

                if (recur(nextN, nextMoveAlicePlay, memo)) {
                    return memo[n][alicePlay] = true;
                }
            } else {

                if (!recur(nextN, nextMoveAlicePlay, memo)) {
                    return memo[n][alicePlay] = false;
                }
            }
        }
        return memo[n][alicePlay] = alicePlay == 1 ? true : false;
    }
}
