package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, return the string after replacing every uppercase
 *         letter with the same lowercase letter.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "Hello" Output: "hello" 
 *         
 *         Example 2:
 * 
 *         Input: s = "here" Output: "here" 
 *         
 *         Example 3:
 * 
 *         Input: s = "LOVELY" Output: "lovely"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 100 s consists of printable ASCII characters.
 *
 */

public class _709_ToLowerCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	public String toLowerCase(String str) {
		String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = "abcdefghijklmnopqrstuvwxyz";
		Map<Character, Character> map = new HashMap<>();

		for (int i = 0; i < caps.length(); i++) {
			map.put(caps.charAt(i), lower.charAt(i));
		}
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (map.containsKey(ch)) {
				res.append(map.get(ch));
			} else {
				res.append(ch);
			}
		}
		return res.toString();
	}
}
