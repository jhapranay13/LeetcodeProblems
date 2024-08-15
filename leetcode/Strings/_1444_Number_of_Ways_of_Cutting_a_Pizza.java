package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
 *
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 *
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3
 * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
 * Example 2:
 *
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 * Example 3:
 *
 * Input: pizza = ["A..","A..","..."], k = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 50
 * rows == pizza.length
 * cols == pizza[i].length
 * 1 <= k <= 10
 * pizza consists of characters 'A' and '.' only.
 *
 */

public class _1444_Number_of_Ways_of_Cutting_a_Pizza {
    public int ways(String[] pizza, int k) {
        int prefix[][] = new int[pizza.length + 1][pizza[0].length() + 1];

        for (int r = 0; r < pizza.length; r++) {

            for (int c = 0; c < pizza[r].length(); c++) {
                int matrixVal = pizza[r].charAt(c) == 'A' ? 1 : 0;

                prefix[r + 1][c + 1] = prefix[r + 1][c] + prefix[r][c + 1] -
                        prefix[r][c] + matrixVal;
            }
        }
        Map<String, Integer> memo = new HashMap<>();
        return recur(prefix, 0, 0, pizza.length - 1, pizza[0].length() - 1, k, memo);
    }
    private int mod = (int)(1e9+7);

    private int recur(int[][] prefix, int rs, int cs, int re, int ce, int k, Map<String, Integer> memo) {

        if (rs > re || cs > ce || k <= 0) {
            return 0;
        }

        if (k == 1) {

            if (getSum(prefix, rs, cs, re, ce) > 0) {
                return 1;
            }
            return 0;
        }
        String key = rs + "|" + cs + "|" + k;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = 0;

        for (int nr = rs; nr <= re; nr++) {

            if (getSum(prefix, rs, cs, nr, ce) > 0) {
                ans = (ans + recur(prefix, nr + 1, cs, re, ce, k - 1, memo)) % mod;
            }
        }

        for (int nc = cs; nc <= ce; nc++) {

            if (getSum(prefix, rs, cs, re, nc) > 0) {
                ans = (ans + recur(prefix, rs, nc + 1, re, ce, k - 1, memo)) % mod;
            }
        }
        memo.put(key, ans);
        return ans;
    }

    private int getSum(int[][] prefix, int rs, int cs, int re, int ce) {
        return prefix[rs][cs] + prefix[re + 1][ce + 1] - prefix[rs][ce + 1] -
                prefix[re + 1][cs];
    }
}
