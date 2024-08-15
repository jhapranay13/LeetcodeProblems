package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.


Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
 *
 */

public class _125_ValidPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isPalindrome(String string) {
		boolean validFlag = true;

		for (int i = 0, j = string.length() - 1; i <= j; i++, j--) {

			while (i < string.length() && 
					!Character.isLetterOrDigit( string.charAt(i))) {
				i++;
			}

			if (i > j) {
				break;
			}

			while (j >= 0 && 
					!Character.isLetterOrDigit( string.charAt(j))) {
				j--;
			}

			if (j < i) {
				break;
			}
			String leftSubstring = string.substring(i, i + 1);
			String rightSubstring = string.substring(j, j + 1);

			if (!leftSubstring.equalsIgnoreCase(rightSubstring)) {
				validFlag = false;
				break;
			}
		}
		return validFlag;
	}
}
