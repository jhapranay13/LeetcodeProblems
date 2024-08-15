package leetcode.Math;

/**
 *
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 *
 */

public class _224_BasicCalculator {
    public int calculate(String s) {
        return recur(s);
    }
    private int index = 0;

    private int recur(String s) {
        char op = '+';
        int lastNum = 0;
        int currNum = 0;
        int ans = 0;

        while (index < s.length()) {
            char ch = s.charAt(index++);

            if (Character.isDigit(ch)) {
                currNum = currNum * 10 + (ch - '0');
            }

            if (ch == '(') {
                currNum = recur(s);
            }

            if ( !Character.isDigit(ch)|| index == s.length()) {

                if (op == '-') {
                    ans += lastNum;
                    lastNum = -currNum;
                } else if (op == '+') {
                    ans += lastNum;
                    lastNum = currNum;
                    currNum = 0;
                } else if (op == '*') {
                    int num = lastNum * currNum;
                    lastNum = num;
                } else if (op == '/') {
                    int num = lastNum / currNum;
                    lastNum = num;
                }
                currNum = 0;

                if (Character.isWhitespace(ch)) {
                    continue;
                }
                op = ch;

                if (op == ')') {
                    break;
                }
            }
        }
        ans += lastNum;
        return ans;
    }
}
