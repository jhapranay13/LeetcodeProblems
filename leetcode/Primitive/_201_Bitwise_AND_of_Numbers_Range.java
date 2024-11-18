package leetcode.Primitive;

/**
 *
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 5, right = 7
 * Output: 4
 * Example 2:
 *
 * Input: left = 0, right = 0
 * Output: 0
 * Example 3:
 *
 * Input: left = 1, right = 2147483647
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= left <= right <= 231 - 1
 *
 *
 */

public class _201_Bitwise_AND_of_Numbers_Range {
    public int rangeBitwiseAnd(int left, int right) {
        // Shift the bits to get the common prefix of left and right number
        // once you get the common prefix all others will be zero
        // coz 1 & 0 will be 0
        int shift = 0;

        while (left < right) {
            left >>= 1;
            //System.out.println(left);
            right >>= 1;
            //System.out.println(">>" + right);

            shift++;
        }
        return left << shift;
    }
}
