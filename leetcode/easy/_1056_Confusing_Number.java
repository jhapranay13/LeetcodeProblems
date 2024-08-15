package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 *
 * We can rotate digits of a number by 180 degrees to form new digits.
 *
 * When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
 * When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
 * Note that after rotating a number, we can ignore leading zeros.
 *
 * For example, after rotating 8000, we have 0008 which is considered as just 8.
 * Given an integer n, return true if it is a confusing number, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6
 * Output: true
 * Explanation: We get 9 after rotating 6, 9 is a valid number, and 9 != 6.
 * Example 2:
 *
 *
 * Input: n = 89
 * Output: true
 * Explanation: We get 68 after rotating 89, 68 is a valid number and 68 != 89.
 * Example 3:
 *
 *
 * Input: n = 11
 * Output: false
 * Explanation: We get 11 after rotating 11, 11 is a valid number but the value remains the same, thus 11 is not a confusing number
 *
 *
 * Constraints:
 *
 * 0 <= n <= 10^9
 *
 *
 */

public class _1056_Confusing_Number {
    public boolean confusingNumber(int n) {
        Map<Character, Character> reverseMap = new HashMap<>();
        reverseMap.put('0', '0');
        reverseMap.put('1', '1');
        reverseMap.put('6', '9');
        reverseMap.put('8', '8');
        reverseMap.put('9', '6');
        StringBuilder holder = new StringBuilder();

        for (char ch : String.valueOf(n).toCharArray()) {

            if (!reverseMap.containsKey(ch)) {
                return false;
            }
            holder.insert(0, reverseMap.get(ch));
        }
        return Integer.parseInt(holder.toString()) != n;
    }
}
