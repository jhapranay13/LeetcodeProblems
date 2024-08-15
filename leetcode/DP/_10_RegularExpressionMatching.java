package leetcode.DP;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an input string s and a pattern p, implement regular expression
 *         matching with support for '.' and '*' where:
 * 
 *         '.' Matches any single character '*' Matches zero or more of the
 *         preceding element. The matching should cover the entire input string
 *         (not partial).
 *
 *         Example 1:
 * 
 *         Input: s = "aa", p = "a" Output: false Explanation: "a" does not
 *         match the entire string "aa". Example 2:
 * 
 *         Input: s = "aa", p = "a*" Output: true Explanation: '*' means zero or
 *         more of the preceding element, 'a'. Therefore, by repeating 'a' once,
 *         it becomes "aa". Example 3:
 * 
 *         Input: s = "ab", p = ".*" Output: true Explanation: ".*" means "zero
 *         or more (*) of any character (.)". Example 4:
 * 
 *         Input: s = "aab", p = "c*a*b" Output: true Explanation: c can be
 *         repeated 0 times, a can be repeated 1 time. Therefore, it matches
 *         "aab". Example 5:
 * 
 *         Input: s = "mississippi", p = "mis*is*p*." Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 20 1 <= p.length <= 30 s contains only lowercase
 *         English letters. p contains only lowercase English letters, '.', and
 *         '*'. It is guaranteed for each appearance of the character '*', there
 *         will be a previous valid character to match.
 */

public class _10_RegularExpressionMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// Recursive Approach
	public boolean isMatch(String s, String p) {
		return recur(s, p, 0, 0);
	}

	private boolean recur(String s, String p, int indexS, int indexP) {

		if (indexP >= p.length()) {
			return indexS >= s.length();
		}
		boolean ans = false;
		boolean curr = false;

		if (indexS < s.length() && (s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '.')) {
			curr = true;
		}

		if (indexP < p.length() - 1 && p.charAt(indexP + 1) == '*') {
			ans = curr && recur(s, p, indexS + 1, indexP) || recur(s, p, indexS, indexP + 2);
		} else {
			ans = curr && recur(s, p, indexS + 1, indexP + 1);
		}
		return ans;
	}

	// ===========================================================================
	// Top Down Approach
	public boolean isMatch1(String s, String p) {
		Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
		return recur1(s, p, 0, 0, memo);
	}

	private boolean recur1(String s, String p, int indexS, int indexP, Boolean[][] memo) {

		if (indexP >= p.length()) {
			return indexS >= s.length();
		}

		if (memo[indexS][indexP] != null) {
			return memo[indexS][indexP];
		}
		boolean ans = false;
		boolean curr = false;

		if (indexS < s.length() && (s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '.')) {
			curr = true;
		}

		if (indexP < p.length() - 1 && p.charAt(indexP + 1) == '*') {
			ans = curr && recur1(s, p, indexS + 1, indexP, memo) || recur1(s, p, indexS, indexP + 2, memo);
		} else {
			ans = curr && recur1(s, p, indexS + 1, indexP + 1, memo);
		}
		return memo[indexS][indexP] = ans;
	}

	// ===========================================================================
	// Bottom up approach
	public boolean isMatch2(String s, String p) {
		boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
		memo[s.length()][p.length()] = true;

		for (int indexS = s.length(); indexS >= 0; indexS--) {
			for (int indexP = p.length() - 1; indexP >= 0; indexP--) {
				boolean ans = false;
				boolean curr = false;

				if (indexS < s.length() && (s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '.')) {
					curr = true;
				}

				if (indexP < p.length() - 1 && p.charAt(indexP + 1) == '*') {
					ans = curr && memo[indexS + 1][indexP] || memo[indexS][indexP + 2];
				} else {
					ans = curr && memo[indexS + 1][indexP + 1];
				}
				memo[indexS][indexP] = ans;
			}
		}
		return memo[0][0];
	}
	//=====================================================================
	//Another top down logic
	public boolean isMatch3(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        boolean ans = recur(s, p, 0, 0, memo);
        return ans;
    }
    
   public boolean recur(String s, String p, int sIndex, int pIndex, Boolean[][] memo) {
        if (pIndex >= p.length()) {
            return sIndex >= s.length();
        }
       
        if (memo[sIndex][pIndex] != null) {
            return memo[sIndex][pIndex];
        }
        boolean flag = false;
        boolean curr = false;
        
        if (sIndex < s.length() && 
            (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.')) {
            curr = true;    
        }
        
        if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
            
            if (curr) {
                flag = (recur(s, p, sIndex + 1, pIndex, memo)) || recur(s, p, sIndex, pIndex + 2, memo);
            } else {
                flag = recur(s, p, sIndex, pIndex + 2, memo); 
            }
        } else {
        	flag = curr && recur(s, p, sIndex + 1, pIndex + 1, memo);
        }
        return memo[sIndex][pIndex] = flag;
    }
}
