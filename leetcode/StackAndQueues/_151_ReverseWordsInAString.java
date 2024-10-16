package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"

Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Example 4:

Input: s = "  Bob    Loves  Alice   "
Output: "Alice Loves Bob"

Example 5:

Input: s = "Alice does not even like bob"
Output: "bob like even not does Alice"

Constraints:

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 *
 */

public class _151_ReverseWordsInAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String reverseWords(String s) {
		StringBuilder holder = new StringBuilder();
		Deque<StringBuilder> stack = new LinkedList<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i); 

			if (ch == ' ') {

				if (holder.length() == 0) {
					continue;
				}
				stack.push(holder);
				holder = new StringBuilder();
				continue;
			}
			holder.append(ch);

			if (i == s.length() - 1 && holder.length() > 0) {
				stack.push(holder);
			}
		}
		StringBuilder ans = new StringBuilder();

		while (!stack.isEmpty()) {
			ans.append(stack.pop());

			if (!stack.isEmpty()) {
				ans.append(" ");
			}
		}
		return ans.toString();
	}
}