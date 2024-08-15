package leetcode.BackTrackingRecursion;

/**
 *
 *
 * You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this so that the resulting number is a power of two.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Example 2:
 *
 * Input: n = 10
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^9
 *
 */

public class _869_Reordered_Power_of_2 {
    public boolean reorderedPowerOf2(int n) {
        String num = "" + n;
        int[] digit = new int[num.length()];

        for (int i = 0; i < num.length(); i++) {
            digit[i] = num.charAt(i) - '0';
        }
        return recur(digit, 0);
    }
    // Permutation
    private boolean recur(int[] digit, int index) {

        if (index == digit.length) {
            return check(digit);
        }

        for (int i = index; i < digit.length; i++) {
            swap(digit, index, i);

            if (recur(digit, index + 1)) {
                return true;
            }
            swap(digit, index, i);
        }
        return false;
    }

    private void swap(int[] digit, int x, int y) {
        int temp = digit[x];
        digit[x] = digit[y];
        digit[y] = temp;
    }

    private boolean check(int[] digit) {

        if (digit[0] == 0) {
            return false;
        }
        int num = 0;

        for (int d : digit) {
            num *= 10;
            num += d;
        }

        while (num > 1) {
            num /= 2;

            if (num % 2 > 0 && num != 1) {
                return false;
            }
        }
        return true;
    }
}
