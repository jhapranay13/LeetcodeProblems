package leetcode.easy;

/**
 *
 *
 * A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.
 *
 * Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5
 * Output: 2
 * Explanation: The square triples are (3,4,5) and (4,3,5).
 * Example 2:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).
 *
 *
 * Constraints:
 *
 * 1 <= n <= 250
 *
 */

public class _1925_Count_Square_Sum_Triples {
    public int countTriples(int n) {
        int ans = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {

                for (int k = 1; k <= n; k++) {

                    if (i * i + j * j == k * k) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
