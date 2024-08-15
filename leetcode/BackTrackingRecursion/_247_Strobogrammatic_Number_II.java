package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: ["11","69","88","96"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["0","1","8"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 14
 *
 */

public class _247_Strobogrammatic_Number_II {
    private char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public List<String> findStrobogrammatic(int n) {
        return recur(n, n);
    }

    private List<String> recur(int endLength, int n) {

        if (n == 1) {
            return new ArrayList<>(List.of("0", "1", "8"));
        }
        List<String> ans = new ArrayList<>();

        if (n == 0) {
            ans.add("");
            return ans;
        }
        List<String> numberBetween = recur(endLength, n - 2);

        for (String between : numberBetween) {

            for (char[] pair : pairs) {

                if (pair[0] != '0' || n != endLength) {
                    ans.add(pair[0] + between + pair[1]);
                }
            }
        }
        return ans;
    }
}
