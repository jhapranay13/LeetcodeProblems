package leetcode.DP;

/**
 *
 *
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 *
 *
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
 *
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 *
 *
 *
 * Example 1:
 *          ABC    AAC  CAA   AAB   ABB
 *          ABC    BBC  CBB   ABB   AAB
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 *
 */

public class _790_Domino_and_Tromino_Tiling {
    private final int mod = 1000000007;

    public int numTilings(int n) {
        long memo[][] = new long[n + 1][n + 1];
        return (int)recur(n, n, memo);
    }

    private long recur(int colsRow1, int colsRow2, long[][] memo) {

        if (colsRow1 == 0 && colsRow2 == 0) {
            return 1;
        }

        if (colsRow1 < 0 || colsRow2 < 0) {
            return 0;
        }

        if (memo[colsRow1][colsRow2] > 0) {
            return memo[colsRow1][colsRow2];
        }
        long ans = 0;

        if (colsRow1 == colsRow2) {
            ans += recur(colsRow1 - 1, colsRow2 - 1, memo); // adding vertical Domino
            ans += recur(colsRow1 - 2, colsRow2 - 2, memo); // adding horizontal Domino
            ans += recur(colsRow1 - 2, colsRow2 - 1, memo); // adding trinamo with 2 on top
            ans += recur(colsRow1 - 1, colsRow2 - 2, memo); // adding trinamo with 2 on bottom
        } else if (colsRow1 > colsRow2) { // try and consume more colsRow1
            ans += recur(colsRow1 - 2, colsRow2 - 1, memo); // adding trinamo with 2 on top
            ans += recur(colsRow1 - 2, colsRow2, memo); // adding horizontal Domino int colsRow1
        } else { // try and consume more colsRow2
            ans += recur(colsRow1 - 1, colsRow2 - 2, memo); // adding trinamo with 2 at bottom
            ans += recur(colsRow1, colsRow2 - 2, memo); // adding horizontal Domino int colsRow2
        }
        return memo[colsRow1][colsRow2] = ans % mod;
    }
}
