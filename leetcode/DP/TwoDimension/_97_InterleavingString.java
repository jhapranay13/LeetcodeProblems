package leetcode.DP.TwoDimension;


/**
 * 
 * @author Pranay Jha
 *
 *
 *Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.


Example 1: ------------------
           |                |
	a a bc c   d b b c a    |
	\ \  \\   / / / /       |   
	 \ \  || / / / /        | 
	  a a || d b b c b  c a c
          ||         |  |
          |----------|---
          -----------   

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true

Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false

Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.


Follow up: Could you solve it using only O(s2.length) additional memory space?
 *
 */


public class _97_InterleavingString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===========================================================================
	//Top Down Approach
	public boolean isInterleave(String s1, String s2, String s3) {

		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		Boolean[][] memo = new Boolean[s1.length()][s2.length()];
		return recur(s1, 0, s2, 0, s3, memo);
	}

	private boolean recur(String s1, int index1, String s2, int index2, String s3,
			Boolean[][] memo) {

		if (index1 >= s1.length()) {
			return s2.substring(index2).equals(s3.substring(index1 + index2));
		}

		if (index2 >= s2.length()) {
			return s1.substring(index1).equals(s3.substring(index1 + index2));
		}

		if (memo[index1][index2] != null) {
			return memo[index1][index2];
		}
		char c1 = s1.charAt(index1);
		char c2 = s2.charAt(index2);
		char c3 = s3.charAt(index1 + index2);
		boolean flag = false;

		if (c1 == c3) {
			flag = recur(s1, index1 + 1, s2, index2, s3, memo);
		}

		if (!flag && c2 == c3) {
			flag = recur(s1, index1, s2, index2 + 1, s3, memo);
		}
		return memo[index1][index2] = flag;
	}
	//===========================================================================
	//Bottom up Approach
	public boolean isInterleave1(String s1, String s2, String s3) {

		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}
		Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];

		for (int index1 = s1.length(); index1 >= 0; index1--) {

			for (int index2 = s2.length(); index2 >= 0; index2--) {

				if (index1 >= s1.length()) {
					memo[index1][index2] = s2.substring(index2).equals(s3.substring(index1 + index2));
					continue;
				}

				if (index2 >= s2.length()) {
					memo[index1][index2] = s1.substring(index1).equals(s3.substring(index1 + index2));
					continue;
				}
				char c1 = s1.charAt(index1);
				char c2 = s2.charAt(index2);
				char c3 = s3.charAt(index1 + index2);
				boolean flag = false;

				if (c1 == c3) {
					flag = memo[index1 + 1][index2];
				}

				if (!flag && c2 == c3) {
					flag = memo[index1][index2 + 1];
				}
				memo[index1][index2] = flag;
			}
		}
		return memo[0][0];
	}
}
