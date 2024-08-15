package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 50
 * s consists of only '(' and ')'.
 * s is a balanced parentheses string.
 *
 */

public class _856_Score_of_Parentheses {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(0);
            } else {

                if (stack.peek() == 0) {
                    stack.pop();
                    stack.push(1);
                } else {
                    int sum = 0;

                    while (stack.peek() != 0) {
                        sum += stack.pop();
                    }
                    stack.pop();
                    stack.push(2 * sum);
                }
            }
        }
        int ans = 0;

        while (!stack.isEmpty()) {
            ans += stack.poll();
        }
        return ans;
    }
}
