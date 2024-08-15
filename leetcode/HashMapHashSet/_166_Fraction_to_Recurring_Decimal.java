package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 *
 *
 *
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 *
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 *
 *
 * Constraints:
 *
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 *
 *
 */

public class _166_Fraction_to_Recurring_Decimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        //Excluse Or because any one has to be -ve. If both are -ve then result is. +ve
        if (numerator < 0 ^ denominator < 0) {
            ans.append("-");
        }
        long divided = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        ans.append(divided / divisor);
        long remainder = divided % divisor;

        if (remainder == 0) {
            return ans.toString();
        }
        ans.append(".");
        Map<Long, Integer> map = new HashMap<>();

        while (remainder != 0) {

            if(map.containsKey(remainder)) {
                ans.insert(map.get(remainder), "(");
                ans.append(")");
                break;
            }
            map.put(remainder, ans.length());
            remainder *= 10;
            ans.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return ans.toString();
    }
}
