package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string text, you want to use the characters of text to form
 *         as many instances of the word "balloon" as possible.
 * 
 *         You can use each character in text at most once. Return the maximum
 *         number of instances that can be formed.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: text = "nlaebolko" Output: 1 
 *         
 *         Example 2:
 * 
 *         Input: text = "loonbalxballpoon" Output: 2 
 *         
 *         Example 3:
 * 
 *         Input: text = "leetcode" Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         1 <= text.length <= 104 text consists of lower case English letters
 *         only.
 *
 */

public class _1189_MaximumNumberOfBalloons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxNumberOfBalloons(String text) {
		String balloon = "balloon";
		Map<Character, Integer> charFreqBalloon = new HashMap<>();

		for (char ch : balloon.toCharArray()) {
			charFreqBalloon.put(ch, charFreqBalloon.getOrDefault(ch, 0) + 1);
		}
		Map<Character, Integer> charFreqText = new HashMap<>();

		for (char ch : text.toCharArray()) {
			charFreqText.put(ch, charFreqText.getOrDefault(ch, 0) + 1);
		}
		int ans = Integer.MAX_VALUE;

		for (char ch : charFreqBalloon.keySet()) {

			if (!charFreqText.containsKey(ch)) {
				return 0;
			}
			int textFreq = charFreqText.get(ch);
			int balloonFreq = charFreqBalloon.get(ch);
			ans = Math.min(ans, textFreq / balloonFreq);
		}
		return ans;
	}
}
