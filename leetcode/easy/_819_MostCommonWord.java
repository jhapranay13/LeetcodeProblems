package leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string paragraph and a string array of the banned words
 *         banned, return the most frequent word that is not banned. It is
 *         guaranteed there is at least one word that is not banned, and that
 *         the answer is unique.
 * 
 *         The words in paragraph are case-insensitive and the answer should be
 *         returned in lowercase.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: paragraph = "Bob hit a ball, the hit BALL flew far after it
 *         was hit.", banned = ["hit"] Output: "ball" Explanation: "hit" occurs
 *         3 times, but it is a banned word. "ball" occurs twice (and no other
 *         word does), so it is the most frequent non-banned word in the
 *         paragraph. Note that words in the paragraph are not case sensitive,
 *         that punctuation is ignored (even if adjacent to words, such as
 *         "ball,"), and that "hit" isn't the answer even though it occurs more
 *         because it is banned. 
 *         
 *         Example 2:
 * 
 *         Input: paragraph = "a.", banned = [] Output: "a"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= paragraph.length <= 1000 paragraph consists of English letters,
 *         space ' ', or one of the symbols: "!?',;.". 0 <= banned.length <= 100
 *         1 <= banned[i].length <= 10 banned[i] consists of only lowercase
 *         English letters.
 *
 */

public class _819_MostCommonWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String mostCommonWord(String paragraph, String[] banned) {
		Set<String> bannedSet = new HashSet<>();

		for (String word : banned) {
			bannedSet.add(word.toLowerCase());
		}
		StringBuilder word = new StringBuilder();
		Map<String, Integer> wordFreqMap = new HashMap<>();

		for (char ch : paragraph.toCharArray()) {

			if (Character.isLetter(ch)) {
				word.append(Character.toLowerCase(ch));
			} else {
				String currWord = word.toString();

				if (word.length() > 0 && !bannedSet.contains(currWord)) {
					wordFreqMap.put(currWord, wordFreqMap.getOrDefault(currWord, 0) + 1);
				}
				word = new StringBuilder();

			}
		}

		if (word.length() > 0) {
			String currWord = word.toString();
			word = new StringBuilder();

			if (!bannedSet.contains(currWord)) {
				wordFreqMap.put(currWord, wordFreqMap.getOrDefault(currWord, 0) + 1);
			}
		}
		String maxWord = "";
		int maxCount = 0;

		for (Map.Entry<String, Integer> entry : wordFreqMap.entrySet()) {
			if (maxCount < entry.getValue()) {
				maxWord = entry.getKey();
				maxCount = entry.getValue();
			}
		}
		return maxWord;
	}
}
