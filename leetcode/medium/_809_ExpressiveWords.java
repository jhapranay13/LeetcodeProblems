package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Sometimes people repeat letters to represent extra feeling, such as
 *         "hello" -> "heeellooo", "hi" -> "hiiii". In these strings like
 *         "heeellooo", we have groups of adjacent letters that are all the
 *         same: "h", "eee", "ll", "ooo".
 * 
 *         For some given string s, a query word is stretchy if it can be made
 *         to be equal to s by any number of applications of the following
 *         extension operation: choose a group consisting of characters c, and
 *         add some number of characters c to the group so that the size of the
 *         group is 3 or more.
 * 
 *         For example, starting with "hello", we could do an extension on the
 *         group "o" to get "hellooo", but we cannot get "helloo" since the
 *         group "oo" has size less than 3. Also, we could do another extension
 *         like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then
 *         the query word "hello" would be stretchy because of these two
 *         extension operations: query = "hello" -> "hellooo" -> "helllllooo" =
 *         s.
 * 
 *         Given a list of query words, return the number of words that are
 *         stretchy.
 * 
 * 
 * 
 *         Example: Input: s = "heeellooo" words = ["hello", "hi", "helo"]
 *         Output: 1 Explanation: We can extend "e" and "o" in the word "hello"
 *         to get "heeellooo". We can't extend "helo" to get "heeellooo" because
 *         the group "ll" is not size 3 or more.
 * 
 * 
 *         Constraints:
 * 
 *         0 <= len(s) <= 100. 0 <= len(words) <= 100. 0 <= len(words[i]) <=
 *         100. s and all words in words consist only of lowercase letters
 *
 */

public class _809_ExpressiveWords {
	public static void main(String args[]) {

	}

	public int expressiveWords(String s, String[] words) {
		int count = 0;

		for (String word : words) {
			int stringIndex = s.length() - 1;
			boolean flag = true;

			for (int wordIndex = word.length() - 1; wordIndex >= 0; wordIndex--) {
				int wordCurr = wordIndex;

				while (wordCurr > 0 && word.charAt(wordCurr) == word.charAt(wordCurr - 1)) {
					wordCurr--;
				}
				char wordChar = word.charAt(wordCurr);
				int wordCharCount = wordIndex - wordCurr + 1;
				int indexCurr = stringIndex;

				while (indexCurr > 0 && s.charAt(indexCurr) == s.charAt(indexCurr - 1)) {
					indexCurr--;
				}
				char stringChar = s.charAt(indexCurr);
				int stringCharCount = stringIndex - indexCurr + 1;

				if (stringChar == wordChar) {
					if (wordCharCount > stringCharCount || (wordCharCount < stringCharCount && stringCharCount < 3)) {
						flag = false;
						break;
					}
				} else {
					flag = false;
					break;
				}
				wordIndex = wordCurr;
				stringIndex = indexCurr - 1;
			}

			if (flag && stringIndex < 0) {
				count++;
			}
		}
		return count;
	}
	//=============================================================================================
	//different  approach
	public int expressiveWords1(String s, String[] words) {
		int ans = 0;

		for (String word : words) {

			if (word.length() > s.length()) {
				continue;
			}
			int sIndex = 0;
			boolean flag = true;

			for (int i = 0; i < word.length(); i++) {

				int sanchor = sIndex;

				while (sIndex < s.length() - 1 && s.charAt(sIndex) == s.charAt(sIndex + 1)) {
					sIndex++;
				}
				int ianchor = i;

				while (i < word.length() - 1 && word.charAt(i) == word.charAt(i + 1) ) {
					i++;
				}

				if (s.charAt(sIndex) != word.charAt(i)) {
					sIndex++;
					flag = false;
					break;
				}
				int sCount = sIndex - sanchor + 1;
				int wCount = i - ianchor + 1;

				if (sCount == wCount) {
					sIndex++;
					continue;
				}

				if (sCount > wCount && sCount >= 3) {
					sIndex++;
					continue;
				}
				flag = false;
				break;
			}

			if (flag && sIndex == s.length()) {
				ans++;
			}
		}
		return ans;
	}
}
