package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al". The interpreted strings are then concatenated in the original order.
 *
 * Given the string command, return the Goal Parser's interpretation of command.
 *
 *
 *
 * Example 1:
 *
 * Input: command = "G()(al)"
 * Output: "Goal"
 * Explanation: The Goal Parser interprets the command as follows:
 * G -> G
 * () -> o
 * (al) -> al
 * The final concatenated result is "Goal".
 * Example 2:
 *
 * Input: command = "G()()()()(al)"
 * Output: "Gooooal"
 * Example 3:
 *
 * Input: command = "(al)G(al)()()G"
 * Output: "alGalooG"
 *
 *
 * Constraints:
 *
 * 1 <= command.length <= 100
 * command consists of "G", "()", and/or "(al)" in some order.
 *
 */

public class _1678_Goal_Parser_Interpretation {
    public String interpret(String command) {
        Deque<Character> stack = new LinkedList<>();
        StringBuilder holder = new StringBuilder();

        for (char ch : command.toCharArray()) {

            if (ch == ')') {

                if (stack.peek() =='(') {
                    stack.pop();
                    stack.push('o');
                }
            }

            if (ch == '(') {
                stack.push(ch);
            }

            if (Character.isAlphabetic(ch)) {

                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            holder.insert(0, stack.pop());
        }
        return holder.toString();
    }
}
