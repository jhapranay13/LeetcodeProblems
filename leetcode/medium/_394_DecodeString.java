package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an encoded string, return its decoded string.
 * 
 *         The encoding rule is: k[encoded_string], where the encoded_string
 *         inside the square brackets is being repeated exactly k times. Note
 *         that k is guaranteed to be a positive integer.
 * 
 *         You may assume that the input string is always valid; No extra white
 *         spaces, square brackets are well-formed, etc.
 * 
 *         Furthermore, you may assume that the original data does not contain
 *         any digits and that digits are only for those repeat numbers, k. For
 *         example, there won't be input like 3a or 2[4].
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "3[a]2[bc]" Output: "aaabcbc" 
 *         
 *         Example 2:
 * 
 *         Input: s = "3[a2[c]]" Output: "accaccacc" 
 *         
 *         Example 3:
 * 
 *         Input: s = "2[abc]3[cd]ef" Output: "abcabccdcdcdef" 
 *         
 *         Example 4:
 * 
 *         Input: s = "abc3[cd]xyz" Output: "abccdcdcdxyz"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 30 s consists of lowercase English letters, digits,
 *         and square brackets '[]'. s is guaranteed to be a valid input. All
 *         the integers in s are in the range [1, 300].
 *
 */
public class _394_DecodeString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String decodeString(String s) {
		return recur(s); 
	}
	private int index = 0;

	private String recur(String s) {

		if (index >= s.length()) {
			return "";
		}
		int num = 0;
		int powerOfTen = 10;
		StringBuilder ans = new StringBuilder();

		while (index < s.length()) {
			char ch = s.charAt(index++);

			if (Character.isAlphabetic(ch)) {
				ans.append(ch);
			}

			if (Character.isDigit(ch)) {
				num *= powerOfTen;
				num += ch - '0';
			}

			if (ch == '[') {
				String bracket = recur(s);

				if (num > 0) {
					while (num > 0) {
						ans.append(bracket);
						num--;
					}
				} else {
					ans.append(bracket);
				}
			}
			if (ch == ']') {
				break;
			}
		}
		return ans.toString();
	}
}
