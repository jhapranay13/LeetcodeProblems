package leetcode.easy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *
 *Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

Example 1:

Input: s = "()"
Output: true

Example 2:

Input: s = "()[]{}"
Output: true

Example 3:

Input: s = "(]"
Output: false

Example 4:

Input: s = "([)]"
Output: false

Example 5:

Input: s = "{[]}"
Output: true

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 *
 */

public class _20_ValidParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_20_ValidParenthesis obj = new _20_ValidParenthesis();
		obj.isValid("()");
	}

	public boolean isValid(String s) {
		Deque<Character> stack = new LinkedList<>();

		for (char brace : s.toCharArray()) {

			if (brace == '(' || brace == '[' || brace == '{') {
				stack.push(brace);
			} else {

				if (brace == ')') {

					if (!stack.isEmpty() && stack.peek() == '(') {
						stack.pop();
						continue;
					}
					return false;
				}

				if (brace == ']') {

					if (!stack.isEmpty() && stack.peek() == '[') {
						stack.pop();
						continue;
					}
					return false;
				}

				if (brace == '}') {

					if (!stack.isEmpty() && stack.peek() == '{') {
						stack.pop();
						continue;
					}
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
