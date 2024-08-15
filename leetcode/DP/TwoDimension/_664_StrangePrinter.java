package leetcode.DP.TwoDimension;

/**
 *
 * There is a strange printer with the following two special properties:
 *
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
 * Given a string s, return the minimum number of turns the printer needed to print it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 * Example 2:
 *
 * Input: s = "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of lowercase English letters.
 *
 */

//Almost similar to remove boxes.
public class _664_StrangePrinter {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        return recur(s, 0, n - 1, memo);
    }

    private int recur(String s, int start, int end, int[][] memo) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return 1;
        }

        if (memo[start][end] > 0) {
            return memo[start][end];
        }

        while (start + 1 < end && s.charAt(start) == s.charAt(start + 1)) {
            start++;
        }

        int ans = recur(s, start + 1, end, memo) + 1;

        for (int i = start + 1; i <= end; i++) {
            if (s.charAt(i) == s.charAt(start)) {
                ans = Math.min(ans, recur(s, start + 1, i - 1, memo) + recur(s, i, end, memo));
            }
        }
        return memo[start][end] = ans;
    }
}
