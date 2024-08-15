package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 *
 */


public class _22_GenerateParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
		StringBuilder partial = new StringBuilder();
		recur(ans, partial, n, n);
		return ans;
	}

	private void recur(List<String> ans, StringBuilder partial, int open, int close) {

		if (close == 0) {

			if (partial.length() > 0) {
				ans.add(partial.toString());
			}
			return;
		}

		if (open > 0) {
			partial.append('(');
			recur(ans, partial, open - 1, close);
			partial.deleteCharAt(partial.length() - 1);
		}

		if (close > open) {
			partial.append(')');
			recur(ans, partial, open, close - 1);
			partial.deleteCharAt(partial.length() - 1);
		}
	}
}
