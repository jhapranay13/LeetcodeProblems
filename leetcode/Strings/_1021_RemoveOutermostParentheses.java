package leetcode.Strings;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         A valid parentheses string is either empty "", "(" + A + ")", or A +
 *         B, where A and B are valid parentheses strings, and + represents
 *         string concatenation.
 * 
 *         For example, "", "()", "(())()", and "(()(()))" are all valid
 *         parentheses strings. A valid parentheses string s is primitive if it
 *         is nonempty, and there does not exist a way to split it into s = A +
 *         B, with A and B nonempty valid parentheses strings.
 * 
 *         Given a valid parentheses string s, consider its primitive
 *         decomposition: s = P1 + P2 + ... + Pk, where Pi are primitive valid
 *         parentheses strings.
 * 
 *         Return s after removing the outermost parentheses of every primitive
 *         string in the primitive decomposition of s.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "(()())(())" Output: "()()()" Explanation: The input
 *         string is "(()())(())", with primitive decomposition "(()())" +
 *         "(())". After removing outer parentheses of each part, this is "()()"
 *         + "()" = "()()()".
 * 
 *         Example 2:
 * 
 *         Input: s = "(()())(())(()(()))" Output: "()()()()(())" Explanation:
 *         The input string is "(()())(())(()(()))", with primitive
 *         decomposition "(()())" + "(())" + "(()(()))". After removing outer
 *         parentheses of each part, this is "()()" + "()" + "()(())" =
 *         "()()()()(())".
 * 
 *         Example 3:
 * 
 *         Input: s = "()()" Output: "" Explanation: The input string is "()()",
 *         with primitive decomposition "()" + "()". After removing outer
 *         parentheses of each part, this is "" + "" = "".
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 105 s[i] is either '(' or ')'. s is a valid
 *         parentheses string.
 *
 */

public class _1021_RemoveOutermostParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	public String removeOuterParentheses(String s) {
		int start = 0;
		int count = 0;
		StringBuilder ans = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '(') {
				count++;
			}

			if (s.charAt(i) == ')') {
				count--;
			}

			if (count == 0) {
				ans.append(s.substring(start + 1, i));
				start = i + 1;
			}
		}
		return ans.toString();
	}

	// =============================================================================
	// Stack implementation
	public String removeOuterParentheses1(String s) {
		StringBuilder ans = new StringBuilder();
		Deque<Integer> stack = new LinkedList<>();

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '(') {
				stack.push(i);
			}
			int start = -1;

			if (s.charAt(i) == ')') {
				start = stack.pop();
				;
			}

			if (stack.isEmpty()) {
				ans.append(s.substring(start + 1, i));
				start = i + 1;
			}
		}
		return ans.toString();
	}
}
