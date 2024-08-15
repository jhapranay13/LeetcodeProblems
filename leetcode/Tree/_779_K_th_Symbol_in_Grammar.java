package leetcode.Tree;

/**
 *
 * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 *
 * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
 * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 1
 * Output: 0
 * Explanation: row 1: 0
 * Example 2:
 *
 * Input: n = 2, k = 1
 * Output: 0
 * Explanation:
 * row 1: 0
 * row 2: 01
 * Example 3:
 *
 * Input: n = 2, k = 2
 * Output: 1
 * Explanation:
 * row 1: 0
 * row 2: 01
 *
 *
 * Constraints:
 *
 * 1 <= n <= 30
 * 1 <= k <= 2^(n - 1)
 *
 */
/*
Intution
            0
           /  \
          0     1
        /  \   /  \
       0    1 1    0
 */

public class _779_K_th_Symbol_in_Grammar {
    public int kthGrammar(int n, int k) {
        long numElementNthLvl = (long)Math.pow(2, n - 1);
        return recur(n, k, 0, 1, 1, numElementNthLvl);
    }

    private int recur(int n, int k, int currVal, int currLevel, long lo, long hi) {

        if (n == currLevel) {
            return currVal;
        }
        int left = currVal == 0 ? 0 : 1;
        int right = currVal == 0 ? 1 : 0;
        long div = (lo + hi) / 2;

        if (k <= div) {
            return recur(n, k, left, currLevel + 1, lo, div);
        } else {
            return recur(n, k, right, currLevel + 1, div + 1, hi);
        }
    }
}
