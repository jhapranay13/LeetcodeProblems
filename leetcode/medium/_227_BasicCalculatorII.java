package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 */

public class _227_BasicCalculatorII {
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int currNum = 0;
        char op = '+';

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                currNum *= 10;
                currNum += ch - '0';
            }

            if (!Character.isDigit(ch) && !Character.isWhitespace(ch) || i == s.length() - 1) {

                if (op == '+') {
                    stack.push(currNum);
                } else if (op == '-') {
                    stack.push(-currNum);
                } else if (op == '*') {
                    stack.push(stack.pop() * currNum);
                } else if (op == '/') {
                    stack.push(stack.pop() / currNum);
                }
                op = ch;
                currNum = 0;
            }
        }
        int ans = 0;

        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
