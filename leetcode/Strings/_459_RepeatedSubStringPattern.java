package leetcode.Strings;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, check if it can be constructed by taking a
 *         substring of it and appending multiple copies of the substring
 *         together.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "abab" Output: true Explanation: It is the substring "ab"
 *         twice. 
 *         
 *         Example 2:
 * 
 *         Input: s = "aba" Output: false 
 *         
 *         Example 3:
 * 
 *         Input: s = "abcabcabcabc" Output: true Explanation: It is the
 *         substring "abc" four times or the substring "abcabc" twice.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 104 s consists of lowercase English letters.
 *
 */

public class _459_RepeatedSubStringPattern {

	public static void main(String args[]) {

	}
	//================================================================================
	//Implementation Of Rabin Karp's rolling hash algorithm
	public boolean repeatedSubstringPattern(String s) {
		int length = s.length() - 1;
		int i = 0;
		int j = 0;

		if ((length + 1) % 2 == 0) {
			i = length / 2;
			j = i + 1;
		} else {
			i = length / 2 - 1;
			j = length / 2 + 1;
		}
		long leftHash = getHash(s.substring(0, i + 1));
		long rightHash = getHash(s.substring(j));

		while (i >= 0 && j < s.length()) {

			if (leftHash == rightHash) {
				String pattern = s.substring(0, i + 1);
				boolean flag = true;

				for (int k = pattern.length(); k < s.length(); k += pattern.length()) {

					if (k + pattern.length() > s.length()) {
						flag = false;
						break;
					}
					String sub = s.substring(k, k + pattern.length());

					if (!sub.equals(pattern)) {
						flag = false;
						break;
					}

				}

				if (flag) {
					return flag;
				}
			} else {
				leftHash = rollLeft(leftHash, s, i);
				rightHash = rollRight(rightHash, s, j);
			}
			i--;
			j++;
		}
		return false;
	}
	private long prime = 1;

	private long rollLeft(long leftHash, String s, int index) {
		leftHash -= s.charAt(index) * Math.pow(prime, index);
		return leftHash;
	}

	private long rollRight(long rightHash, String s, int index) {
		rightHash -= s.charAt(index);
		rightHash /= prime;
		return rightHash;
	}

	private long getHash(String s) {
		long hash = 0;

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			hash += ch * Math.pow(prime, i);
		}
		return hash;
	}
}
