package leetcode.ForBiginners.Greedy.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s of '(' and ')' parentheses, we add the minimum
 *         number of parentheses ( '(' or ')', and in any positions ) so that
 *         the resulting parentheses string is valid.
 * 
 *         Formally, a parentheses string is valid if and only if:
 * 
 *         It is the empty string, or It can be written as AB (A concatenated
 *         with B), where A and B are valid strings, or It can be written as
 *         (A), where A is a valid string. Given a parentheses string, return
 *         the minimum number of parentheses we must add to make the resulting
 *         string valid.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "())" Output: 1
 * 
 *         Example 2:
 * 
 *         Input: s = "(((" Output: 3
 * 
 *         Example 3:
 * 
 *         Input: s = "()" Output: 0
 * 
 *         Example 4:
 * 
 *         Input: s = "()))((" Output: 4
 * 
 * 
 *         Note:
 * 
 *         s.length <= 1000 s only consists of '(' and ')' characters.
 *
 */

public class _921_MinimumAddToMakeParenthesisValid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int minAddToMakeValid(String s) {
		Deque<Character> stack = new LinkedList<>();

		for (char ch : s.toCharArray()) {

			if (ch == ')') {
				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
					continue;
				}
			}
			stack.push(ch);
		}
		return stack.size();
	}
}
