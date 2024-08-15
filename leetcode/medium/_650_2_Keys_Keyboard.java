package leetcode.medium;

import java.util.Arrays;

/**
 *
 * There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:
 *
 * Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 * Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: Initially, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 * Example 2:
 *
 * Input: n = 1
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 *
 */

public class _650_2_Keys_Keyboard {
    //Top Down Approach
    public int minSteps(int n) {
        int[][] memo = new int[n][n];

        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return recur(n, 1, 0, memo);
    }

    private int recur(int n, int currLength, int copyLength, int[][] memo) {

        if (currLength > n) {
            return Integer.MAX_VALUE;
        }

        if (currLength == n) {
            return 0;
        }

        if (memo[currLength][copyLength] != -1) {
            return memo[currLength][copyLength];
        }
        int paste = Integer.MAX_VALUE;

        if (copyLength > 0) {
            paste = recur(n, currLength + copyLength, copyLength, memo);
        }

        if (paste != Integer.MAX_VALUE) {
            paste += 1;
        }
        int copyPaste = recur(n, currLength + currLength, currLength, memo);

        if (copyPaste != Integer.MAX_VALUE) {
            copyPaste += 2; //Since 2 operations copy and paste was performed
        }
        return Math.min(paste, copyPaste);
    }

    //=============================================================================================
    //Bottom up approach
    public int minSteps1(int n) {
        int[][] memo = new int[n + 1][n + 1];

        for (int[] mem : memo) {
            Arrays.fill(mem, Integer.MAX_VALUE);
        }

        for (int currLength = n; currLength >= 1; currLength--) {

            for (int copyLength = n; copyLength >= 0; copyLength--) {

                if (currLength == n) {
                    memo[currLength][copyLength] = 0;
                    continue;
                }
                int paste = Integer.MAX_VALUE;

                if (copyLength > 0 && currLength + copyLength <= n) {
                    paste = memo[currLength + copyLength][copyLength];
                }

                if (paste != Integer.MAX_VALUE) {
                    paste += 1;
                }
                int copyPaste = Integer.MAX_VALUE;

                if (currLength + currLength <= n) {
                    copyPaste = memo[currLength + currLength][currLength];
                }

                if (copyPaste != Integer.MAX_VALUE) {
                    copyPaste += 2; //Since 2 operations copy and paste was performed
                }
                memo[currLength][copyLength] = Math.min(paste, copyPaste);
            }
        }
        return memo[1][0];
    }
}
