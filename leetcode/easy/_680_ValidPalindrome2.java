package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, return true if the s can be palindrome after
 *         deleting at most one character from it.
 * 
 *         Example 1:
 * 
 *         Input: s = "aba" Output: true 
 *         
 *         Example 2:
 * 
 *         Input: s = "abca" Output: true Explanation: You could delete the
 *         character 'c'. 
 *         
 *         Example 3:
 * 
 *         Input: s = "abc" Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 105 s consists of lowercase English letters.
 *
 */

public class _680_ValidPalindrome2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean validPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        
        while (lo < hi) {
            if (s.charAt(lo) == s.charAt(hi)) {
                lo++;
                hi--;
            } else {
                return isPalindrome(s, lo, hi - 1) || isPalindrome(s, lo + 1, hi);
            }
        }
        return true;
    }
    
    public boolean isPalindrome(String s, int lo, int hi) {
        
        while (lo < hi) {
            if(s.charAt(lo++) != s.charAt(hi--)) {
                return false;
            }
        }
        return true;
    }
}
