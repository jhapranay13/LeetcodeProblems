package leetcode.Strings;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, find the longest palindromic subsequence's length
 *         in s.
 * 
 *         A subsequence is a sequence that can be derived from another sequence
 *         by deleting some or no elements without changing the order of the
 *         remaining elements.
 * 
 *         Example 1:
 * 
 *         Input: s = "bbbab" Output: 4 Explanation: One possible longest
 *         palindromic subsequence is "bbbb". 
 *         
 *         Example 2:
 * 
 *         Input: s = "cbbd" Output: 2 Explanation: One possible longest
 *         palindromic subsequence is "bb".
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 1000 s consists only of lowercase English letters.
 *
 */

public class _516_LongestPalindromicSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int longestPalindromeSubseq(String s) {
        int[][] memo = new int[s.length()][s.length()];
        return recur(s, 0, s.length() - 1, memo);
    }
    
    private int recur(String s, int lo, int hi, int[][] memo) {
        
        if (lo == hi) {
            return 1;
        }
        
        if (lo > hi) {
            return 0;
        }
        
        if (memo[lo][hi] > 0) {
            return memo[lo][hi];
        }
        int ans = 0;
        
        if (s.charAt(lo) == s.charAt(hi)) {
            ans = 2 + recur(s, lo + 1, hi - 1, memo);
        } else {
            ans = Math.max(recur(s, lo + 1, hi, memo), recur(s, lo, hi - 1, memo));
        }
        return memo[lo][hi] = ans;
    }
    //==============================================================================
    //Bottom up approach
    public int longestPalindromeSubseq1(String s) {
        int[][] memo = new int[s.length() + 1][s.length() + 1];
        
        for (int lo = s.length() - 1; lo >= 0; lo--) {
            for (int hi = 0; hi < s.length(); hi++) {
                
                if (lo == hi) {
                    memo[lo][hi] = 1;
                    continue;
                }
                
                if (lo > hi) {
                    continue;
                }
                int ans = 0;
        
                if (s.charAt(lo) == s.charAt(hi)) {
                    ans = 2 + memo[lo + 1][hi - 1];
                } else {
                    ans = Math.max(memo[lo + 1][hi], memo[lo][hi - 1]);
                }
                memo[lo][hi] = ans;
            }
        }
        return memo[0][s.length() - 1];
    }
}
