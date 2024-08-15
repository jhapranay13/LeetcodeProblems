package leetcode.Math;

/**
 *
 *
 * Given an integer n, return true if n has exactly three positive divisors. Otherwise, return false.
 *
 * An integer m is a divisor of n if there exists an integer k such that n = k * m.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: false
 * Explantion: 2 has only two divisors: 1 and 2.
 * Example 2:
 *
 * Input: n = 4
 * Output: true
 * Explantion: 4 has three divisors: 1, 2, and 4.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 *
 */

public class _1952_Three_Divisors {
    public boolean isThree(int n) {
        // Every number is divisible by itself and one so count = 2;
        int count = 2;

        for (int i = 2; i <= n / 2; i++) {

            if (n % i == 0) {
                count++;
            }

            if (count > 3) {
                return false;
            }
        }
        return count == 3;
    }
}
