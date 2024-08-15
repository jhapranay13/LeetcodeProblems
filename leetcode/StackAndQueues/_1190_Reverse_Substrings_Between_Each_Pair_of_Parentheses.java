package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *
 * You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 * Example 2:
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 * Example 3:
 *
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It is guaranteed that all parentheses are balanced.
 *
 */

public class _1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses {
    public String reverseParentheses(String s) {
        Deque<String> stack = new LinkedList<>();

        for (char ch : s.toCharArray()) {

            if (ch != ')') {
                stack.push("" + ch);
            } else {
                StringBuilder holder = new StringBuilder();

                while (!stack.peek().equals("(")) {
                    holder.append(stack.pop());
                }
                stack.pop();

                for (char hch : holder.toString().toCharArray()) {
                    stack.push(hch + "");
                }
            }
        }
        StringBuilder holder = new StringBuilder();

        while (!stack.isEmpty()) {
            holder.insert(0, stack.pop());
        }
        return holder.toString();
    }
}
