package leetcode.Math;

/**
 *
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = 2, b = 3
 * Output: 5
 *
 *
 * Constraints:
 *
 * -1000 <= a, b <= 1000
 *
 */

public class _371_Sum_of_Two_Integers {
    public int getSum(int a, int b) {
        int x = Math.abs(a);
        int y = Math.abs(b);
        // ensure that abs(a) >= abs(b)
        if (x < y) {
            return getSum(b, a);
        }

        // abs(a) >= abs(b) -->
        // a determines the sign
        int sign = a > 0 ? 1 : -1;

        if (a * b >= 0) {
            // sum of two positive integers x + y
            // where x > y
            while (y != 0) {
                int answer = x ^ y;
                int carry = (x & y) << 1;
                x = answer;
                y = carry;
            }
        } else {
            // difference of two positive integers x - y
            // where x > y
            while (y != 0) {
                int answer = x ^ y;
                int borrow = ((~x) & y) << 1;
                x = answer;
                y = borrow;
            }
        }
        return x * sign;
    }
}
