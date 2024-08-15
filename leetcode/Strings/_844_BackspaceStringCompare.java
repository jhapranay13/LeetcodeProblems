package leetcode.Strings;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings s and t, return true if they are equal when both
 *         are typed into empty text editors. '#' means a backspace character.
 * 
 *         Note that after backspacing an empty text, the text will continue
 *         empty.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "ab#c", t = "ad#c" Output: true Explanation: Both s and t
 *         become "ac".
 * 
 *         Example 2:
 * 
 *         Input: s = "ab##", t = "c#d#" Output: true Explanation: Both s and t
 *         become "".
 * 
 *         Example 3:
 * 
 *         Input: s = "a##c", t = "#a#c" Output: true Explanation: Both s and t
 *         become "c".
 * 
 *         Example 4:
 * 
 *         Input: s = "a#c", t = "b" Output: false Explanation: s becomes "c"
 *         while t becomes "b".
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length, t.length <= 200 s and t only contain lowercase letters
 *         and '#' characters.
 * 
 * 
 *         Follow up: Can you solve it in O(n) time and O(1) space?
 *
 */

public class _844_BackspaceStringCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Stack implementation
	public boolean backspaceCompare(String s, String t) {
		String str1 = build(s);
		String str2 = build(t);
		return str1.equals(str2);
	}

	private String build(String str) {
		Deque<Character> stack = new LinkedList<>();

		for (char ch : str.toCharArray()) {

			if (ch == '#') {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(ch);
			}
		}
		StringBuilder ans = new StringBuilder();

		while (!stack.isEmpty()) {
			ans.insert(0, stack.pop());
		}
		return ans.toString();
	}
}
