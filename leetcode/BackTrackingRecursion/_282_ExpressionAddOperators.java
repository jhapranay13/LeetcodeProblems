package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * Example 3:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -231 <= target <= 231 - 1
 *
 */
public class _282_ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        StringBuilder p = new StringBuilder();
        recur(num, target, ans, "", 0, 0, -1L);
        return ans;
    }

    private void recur(String num, int target, List<String> ans, String p, long solve, int index,
                       long prevNumber) {

        if (index >= num.length()) {

            if (solve == target) {
                ans.add(p);
            }
            return;
        }

        for (int i = index + 1; i <= num.length(); i++) {
            long number = Long.parseLong(num.substring(index, i));

            if (index == 0) {
                recur(num, target, ans, p + number, number, i, number);
            } else {
                long nextSolve = solve - prevNumber + (prevNumber * number);

                recur(num, target, ans, p + "*" + number, nextSolve, i, prevNumber * number);

                recur(num, target, ans, p + "+" + number, solve + number, i, number);

                recur(num, target, ans, p + "-" + number, solve - number, i, -1* number);
            }
            if (num.charAt(index) == '0') {
                return;
            }
        }
    }
}
