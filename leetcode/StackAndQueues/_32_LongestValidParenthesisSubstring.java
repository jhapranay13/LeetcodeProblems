package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string containing just the characters '(' and ')', find the
 *         length of the longest valid (well-formed) parentheses substring.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "(()" Output: 2 Explanation: The longest valid parentheses
 *         substring is "()". Example 2:
 * 
 *         Input: s = ")()())" Output: 4 Explanation: The longest valid
 *         parentheses substring is "()()". Example 3:
 * 
 *         Input: s = "" Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         0 <= s.length <= 3 * 104 s[i] is '(', or ')'.
 *
 */

public class _32_LongestValidParenthesisSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int longestValidParentheses(String s) {

		if (s == null || s.length() == 0) {
			return 0;
		}
		Deque<Integer> stack = new LinkedList<>();
		int max = 0;

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
					stack.pop();
					int last = !stack.isEmpty() ? stack.peek() : -1;
					max = Math.max(max, i - last);
				} else {
					stack.push(i);
				}
			}
		}
		return max;
	}

	// ======================================================================
	// Another version
	public int longestValidParentheses1(String s) {
		Deque<Integer> q = new LinkedList<>();
		int max = 0;

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '(') {
				q.offer(i);
				continue;
			}

			if (s.charAt(i) == ')') {

				if (!q.isEmpty() && s.charAt(q.peekLast()) == '(') {
					q.pollLast();

					if (!q.isEmpty()) {
						max = Math.max(max, i - q.peekLast());
					} else {
						max = Math.max(max, i + 1);
					}
				} else {
					q.offer(i);
				}
			}
		}
		return max;
	}
}
