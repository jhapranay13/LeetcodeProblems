package leetcode.ForBiginners.Greedy.Strings;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, remove duplicate letters so that every letter
 *         appears once and only once. You must make sure your result is the
 *         smallest in lexicographical order among all possible results.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "bcabc" Output: "abc" Example 2:
 * 
 *         Input: s = "cbacdcbc" Output: "acdb"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 104 s consists of lowercase English letters.
 * 
 * 
 *         Note: This question is the same as 1081:
 *         https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 */

public class _316_RemoveDuplicateLetters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String removeDuplicateLetters(String s) {
		Map<Character, Integer> lastIndexMap = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			lastIndexMap.put(s.charAt(i), i);
		}
		Deque<Character> stack = new LinkedList<>();
		Set<Character> seen = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (stack.isEmpty()) {
				stack.push(ch);
				seen.add(ch);
				continue;
			}

			if (seen.contains(ch)) {
				continue;
			}

			while (!stack.isEmpty() && stack.peek() > ch &&
					i < lastIndexMap.get(stack.peek())) {
				seen.remove(stack.pop());
			}
			stack.push(ch);
			seen.add(ch);
		}
		StringBuilder ans = new StringBuilder();

		while (!stack.isEmpty()) {
			ans.insert(0, stack.pop());
		}
		return ans.toString();
	}
}
