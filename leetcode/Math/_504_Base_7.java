package leetcode.Math;

/**
 *
 * Given an integer num, return a string of its base 7 representation.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 100
 * Output: "202"
 * Example 2:
 *
 * Input: num = -7
 * Output: "-10"
 *
 *
 * Constraints:
 *
 * -10^7 <= num <= 10^7
 *
 *
 */

public class _504_Base_7 {
    //Recursive
    public String convertToBase7(int num) {

        if (num < 0) {
            return "-" + convertToBase7(-num);
        }

        if (num < 7) {
            return Integer.toString(num);
        }
        return convertToBase7(num / 7) + convertToBase7(num % 7);
    }
    //=============================================================================================
    // Iteration
    public String convertToBase7_1(int num) {

        if (num == 0) {
            return "0";
        }
        String sign = num < 0 ? "-" : "";
        StringBuilder holder = new StringBuilder();
        num = Math.abs(num);

        while (num > 0) {
            holder.insert(0, num % 7);
            num /= 7;
        }
        return sign + holder.toString();
    }
}
