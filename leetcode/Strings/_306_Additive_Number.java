package leetcode.Strings;

/**
 *
 * An additive number is a string whose digits can form an additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits, return true if it is an additive number or false otherwise.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation:
 * The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * Example 2:
 *
 * Input: "199100199"
 * Output: true
 * Explanation:
 * The additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 35
 * num consists only of digits.
 *
 *
 * Follow up: How would you handle overflow for very large input integers?
 *
 */

public class _306_Additive_Number {
    public boolean isAdditiveNumber(String num) {

        for (int i = 1; i <= num.length() - 2; i++) {
            String num1Holder1 = num.substring(0, i);
            long num1 = Long.parseLong(num1Holder1);

            if (num.charAt(0) == '0' && i >= 2) {
                break;
            }

            for (int j = i + 1; j <= num.length() - 1; j++) {

                if (num.charAt(i) == '0' && j - i >= 2) {
                    break;
                }
                String num1Holder2 = num.substring(i, j);
                long num2 = Long.parseLong(num1Holder2);
                String next = num.substring(j);

                if (check(next, num1, num2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(String num, long num1, long num2) {

        if (num.equals("")) {
            return true;
        }
        long sum = num1 + num2;
        String holder = ((Long)sum).toString();

        if (num.startsWith(holder)) {
            return check(num.substring(holder.length()), num2, sum);
        }
        return false;
    }
}
