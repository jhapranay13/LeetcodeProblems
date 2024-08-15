package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
 *
 * Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
 * Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
 * In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.
 *
 * For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 *
 * Return the minimum number of insertions needed to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to add one more ')' at the end of the string to be "(())))" which is balanced.
 * Example 2:
 *
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 * Example 3:
 *
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of '(' and ')' only.
 *
 */

public class _1541_Minimum_Insertions_to_Balance_a_Parentheses_String {
    public int minInsertions(String s) {
        Deque<Character> stack = new LinkedList<>();
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == ')') {

                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {

                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        //if stack is empty or stack.peek() != '(' meaning  ( needs to be added so ans++
                        ans++;
                    }
                    i++;
                } else {
                    //if stack is empty meaning ( and ) needs to be added so ans += 2
                    if (stack.isEmpty()) {
                        ans += 2;
                    } else {
                        //if stack is not empty meaning it conatins one ( and needs one ) so ans += 1 and poping the ()
                        ans += 1;
                        stack.pop();
                    }
                }
            } else {
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            char ch = stack.pop();
            //for every (  2 ) needs to be added
            ans += 2;
        }
        return ans;
    }
}
