package leetcode.easy;

/**
 *
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Follow up: Do not use any built-in library function such as sqrt.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 16
 * Output: true
 * Example 2:
 *
 * Input: num = 14
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= num <= 2^31 - 1
 *
 */

public class _367_Valid_Perfect_Square {
    public boolean isPerfectSquare(int num) {

        if (num < 2) {
            return true;
        }
        long lo = 2;
        long hi = num / 2;

        while (lo <= hi) {
            long pivot = lo + (long)(hi - lo) / 2;
            long product = pivot * pivot;

            if (product == num) {
                return true;
            }

            if (product < num) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return false;
    }
}
