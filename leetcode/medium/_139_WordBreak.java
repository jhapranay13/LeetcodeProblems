package leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 *
 */

public class _139_WordBreak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//Top Down Approach
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>();

		for (String str: wordDict) {
			dict.add(str);
		}
		Boolean[] memo = new Boolean[s.length()];
		return recur(s, dict, 0, memo);
	}

	private boolean recur(String s, Set<String> dict, int index, Boolean[] memo) {

		if (index == s.length()) {
			return true;
		}

		if (memo[index] != null) {
			return memo[index];
		}
		boolean flag = false;

		for (int i = index + 1; i <= s.length(); i++) {
			String str = s.substring(index, i);

			if (dict.contains(str) && recur(s, dict, i, memo)) {
				flag = true;
				break;
			}
		}
		return memo[index] = flag;
	}
	//==============================================================================
	//Bottom Up Approach
	public boolean wordBreak1(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>();

		for (String str: wordDict) {
			dict.add(str);
		}
		Boolean[] memo = new Boolean[s.length() + 1];

		for (int index = s.length(); index >= 0; index--) {

			if (index == s.length()) {
				memo[index] = true;
				continue;
			}
			boolean flag = false;

			for (int i = index + 1; i <= s.length(); i++) {
				String str = s.substring(index, i);

				if (dict.contains(str) && memo[i]) {
					flag = true;
					break;
				}
			}
			memo[index] = flag;
		}
		return memo[0];
	}
}
