package leetcode.Strings;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, reverse the order of characters in each word within
 *         a sentence while still preserving whitespace and initial word order.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "Let's take LeetCode contest" Output: "s'teL ekat edoCteeL
 *         tsetnoc" Example 2:
 * 
 *         Input: s = "God Ding" Output: "doG gniD"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 5 * 104 s contains printable ASCII characters. s
 *         does not contain any leading or trailing spaces. There is at least
 *         one word in s. All the words in s are separated by a single space.
 *
 */

public class _557_ReverseWordsInString3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String reverseWords(String s) {
		Deque<String> queue = new LinkedList<>();
		int start = 0;
		int end = 0;

		while (end < s.length()) {
			//Since the constraint says only one space between words
			if (s.charAt(end) == ' ') {
				queue.offer(s.substring(start, end));
				start = end + 1;
			} 
			end++;
		}

		if (start < end) {
			queue.offer(s.substring(start, end));
		}
		StringBuilder ans = new StringBuilder();

		while (!queue.isEmpty()) {
			String str = queue.poll();

			for (int i = str.length() - 1; i >= 0; i--) {
				ans.append(str.charAt(i));
			}

			if (!queue.isEmpty()) {
				ans.append(" ");
			}
		}
		return ans.toString();
	}
}
