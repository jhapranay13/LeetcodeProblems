package leetcode.Strings;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Write a function that reverses a string. The input string is given as
 *         an array of characters s.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = ["h","e","l","l","o"] Output: ["o","l","l","e","h"]
 *         
 *         Example 2:
 * 
 *         Input: s = ["H","a","n","n","a","h"] Output:
 *         ["h","a","n","n","a","H"]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 105 s[i] is a printable ascii character.
 * 
 * 
 *         Follow up: Do not allocate extra space for another array. You must do
 *         this by modifying the input array in-place with O(1) extra memory.
 *
 */

public class _344_ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void reverseString(char[] s) {

		int lo = 0;
		int hi = s.length - 1;

		while (lo < hi) {

			if (s[lo] != s[hi]) {
				char temp = s[lo];
				s[lo] = s[hi];
				s[hi] = temp;
			}
			lo++;
			hi--;
		}
	}
	//=============================================================================
	//Another version
	public void reverseString1(char[] s) {

		if( s == null || s.length <= 1 ) {
			return;
		}
		int lo = 0;
		int hi = s.length - 1;

		while( lo <= hi ) {
			char temp = s[ lo ];
			s[ lo++ ] = s[ hi ];
			s[ hi-- ] = temp;
		}
	}
}
