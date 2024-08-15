package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings s and t, return true if s is a subsequence of t, or
 *         false otherwise.
 * 
 *         A subsequence of a string is a new string that is formed from the
 *         original string by deleting some (can be none) of the characters
 *         without disturbing the relative positions of the remaining
 *         characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is
 *         not).
 * 
 *         Example 1:
 * 
 *         Input: s = "abc", t = "ahbgdc" Output: true 
 *         
 *         Example 2:
 * 
 *         Input: s = "axc", t = "ahbgdc" Output: false
 * 
 * 
 *         Constraints:
 * 
 *         0 <= s.length <= 100 0 <= t.length <= 104 s and t consist only of
 *         lowercase English letters.
 * 
 * 
 *         Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk
 *         where k >= 109, and you want to check one by one to see if t has its
 *         subsequence. In this scenario, how would you change your code?
 *
 */

public class _392_IsSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean isSubsequence(String s, String t) {
        return recur(s, t, 0, 0);
    }
    
    private boolean recur(String s, String t, int sIndex, int tIndex) {
        
        if (tIndex == t.length()) {
            return sIndex == s.length();
        }
        
        if (sIndex == s.length()) {
            return true;
        }
        
        if (s.charAt(sIndex) == t.charAt(tIndex)) {
            return recur(s, t, sIndex + 1, tIndex + 1);
        }
        return recur(s, t, sIndex, tIndex + 1);
    }
}
