package leetcode.Strings;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s that contains parentheses and letters, remove the
 *         minimum number of invalid parentheses to make the input string valid.
 * 
 *         Return all the possible results. You may return the answer in any
 *         order.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "()())()" Output: ["(())()","()()()"]
 * 
 *         Example 2:
 * 
 *         Input: s = "(a)())()" Output: ["(a())()","(a)()()"]
 * 
 *         Example 3:
 * 
 *         Input: s = ")(" Output: [""]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 25 s consists of lowercase English letters and
 *         parentheses '(' and ')'. There will be at most 20 parentheses in s.
 *
 */

public class _301_RemoveInvalidParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =====================================================================
	// Passes all test cases
	public List<String> removeInvalidParentheses(String input) {
		if (input == null || input.length() == 0) {
			return new ArrayList<>();
		}
		int left = 0, right = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				left++;
			}
			if (input.charAt(i) == ')') {
				if (left == 0) {
					right++;
				} else {
					left--;
				}
			}
		}
		int[] mask = new int[input.length()];
		List<String> res = new ArrayList<>();
		dfs(input, left, right, 0, res, mask);
		return res;
	}

	private void dfs(String input, int left, int right, int idx, List<String> res, int[] mask) {
		if (left == 0 && right == 0 && isValid(input, mask)) {
			res.add(toString(input, mask));
			return;
		}
		for (int i = idx; i < input.length(); i++) {
			if (input.charAt(i) != '(' && input.charAt(i) != ')') {
				continue;
			}
			// if (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {
			// continue;
			// }
			if (i > idx && input.charAt(i) == input.charAt(i - 1)) {
				continue;
			}
			// input = input.substring(0, i) + input.substring(i + 1, input.length());
			if (input.charAt(i) == '(' && left > 0) {
				mask[i] = -1;
				dfs(input, left - 1, right, i + 1, res, mask);
				mask[i] = 0;
			} else if (input.charAt(i) == ')' && right > 0) {
				mask[i] = -1;
				dfs(input, left, right - 1, i + 1, res, mask);
				mask[i] = 0;
			}
		}
	}

	private boolean isValid(String s, int[] mask) {
		int off = 0;
		for (int i = 0; i < s.length(); i++) {
			if (mask[i] == 0) {
				if (s.charAt(i) == '(') {
					off++;
				} else if (s.charAt(i) == ')') {
					off--;
				}
				if (off < 0) {
					return false;
				}
			}
		}
		return off == 0;
	}

	private String toString(String s, int[] mask) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (mask[i] == 0) {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	// =====================================================================
	// Slow Version. First versions and might time out
	public List<String> removeInvalidParentheses1(String s) {
		List<String> ans = new ArrayList<>();

		if (s == null || s.length() <= 1) {

			if (s != null && s.length() == 1 && (s.charAt(0) == '(' || s.charAt(0) == ')'))
				ans.add("");
			else {
				ans.add(s);
			}
			return ans;
		}
		Deque<Integer> stack = new LinkedList<>();
		StringBuilder p = new StringBuilder();
		Set<String> holder = new HashSet<>();
		calc(stack, p, holder, s, 0);

		for (String str : holder) {
			ans.add(str);
		}

		if (ans.size() == 0) {
			ans.add("");
		}
		return ans;
	}

	private int length = 0;

	private void calc(Deque<Integer> stack, StringBuilder p, Set<String> holder, String expr, int index) {

		if (expr.length() == index) {

			if (stack.isEmpty() && p.length() > 0) {

				if (p.length() > length) {
					holder.clear();
					holder.add(p.toString());
					length = p.length();
				} else if (p.length() == length) {
					holder.add(p.toString());
				}
			}
			return;
		}

		if (expr.charAt(index) == '(') {
			p.append("(");
			stack.push(index);
			calc(stack, p, holder, expr, index + 1);
			stack.pop();
			p.deleteCharAt(p.length() - 1);
			calc(stack, p, holder, expr, index + 1);
		} else if (expr.charAt(index) == ')') {
			p.append(")");
			int i = !stack.isEmpty() ? stack.peek() : -1;

			if (i >= 0 && expr.charAt(i) == '(') {
				stack.pop();
				calc(stack, p, holder, expr, index + 1);
				stack.push(i);
			} else {
				stack.push(index);
				calc(stack, p, holder, expr, index + 1);
				stack.pop();
			}
			p.deleteCharAt(p.length() - 1);
			calc(stack, p, holder, expr, index + 1);
		} else {
			char ch = expr.charAt(index);
			p.append(ch);
			calc(stack, p, holder, expr, index + 1);
			p.deleteCharAt(p.length() - 1);
			calc(stack, p, holder, expr, index + 1);
		}
	}
	// ============================================================================================
	// Modeified above version. Passes all the test case.
	public List<String> removeInvalidParentheses3(String s) {
		Deque<Character> stack = new LinkedList<>();
		StringBuilder p = new StringBuilder();
		Set<String> ans = new HashSet<>();
		char[] arr = s.toCharArray();
		List<String> ret = new ArrayList<>();

		if (arr.length == 0) {
			ans.add("");
		} else {
			recur(arr, stack, p, ans, 0);
		}

		if (ans.size() == 0) {
			ans.add("");
		} else {

			for (String str : ans) {
				ret.add(str);
			}
		}
		return ret;
	}

	private void recur(char[] arr, Deque<Character> stack, StringBuilder p,
					   Set<String> ans, int index) {

		if (index == arr.length) {

			if (stack.isEmpty()) {

				if (ans.isEmpty()) {
					ans.add(p.toString());
					length = p.length();
				} else if (length == p.length()) {
					ans.add(p.toString());
				} else if (length < p.length()) {
					ans = new HashSet<>();
					ans.add(p.toString());
					length = p.length();
				}
			}
			return;
		}
		char ch = arr[index];

		if (ch != '(' && ch != ')') {
			p.append(ch);
			recur(arr, stack, p, ans, index + 1);
			p.deleteCharAt(p.length() - 1);
		} else if (ch == '(') {
			stack.push(ch);
			p.append(ch);
			recur(arr, stack, p, ans, index + 1);
			p.deleteCharAt(p.length() - 1);
			stack.pop();
		} else {

			if (!stack.isEmpty() && stack.peek() == '(') {
				char temp = stack.pop();
				p.append(ch);
				recur(arr, stack, p, ans, index + 1);
				p.deleteCharAt(p.length() - 1);
				stack.push(temp);
			}
		}
		recur(arr, stack, p, ans, index + 1);
	}
}
