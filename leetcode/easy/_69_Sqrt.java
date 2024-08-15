package leetcode.easy;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 4
 * Output: 2
 * Example 2:
 *
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 *
 *
 * Constraints:
 *
 * 0 <= x <= 2^31 - 1
 *
 *
 */

public class _69_Sqrt {
    public int mySqrt(int x) {
        int lo = 0;
        int hi = x;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            long num = (long) pivot * pivot;

            if (num > x) {
                hi = pivot - 1;
            } else if (num < x) {
                lo = pivot + 1;
            } else {
                return pivot;
            }
        }
        return hi;
    }
}
