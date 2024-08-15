package leetcode.Strings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         In an alien language, surprisingly they also use english lowercase
 *         letters, but possibly in a different order. The order of the alphabet
 *         is some permutation of lowercase letters.
 * 
 *         Given a sequence of words written in the alien language, and the
 *         order of the alphabet, return true if and only if the given words are
 *         sorted lexicographicaly in this alien language.
 * 
 *         Example 1:
 * 
 *         Input: words = ["hello","leetcode"], order =
 *         "hlabcdefgijkmnopqrstuvwxyz" Output: true Explanation: As 'h' comes
 *         before 'l' in this language, then the sequence is sorted.
 * 
 *         Example 2:
 * 
 *         Input: words = ["word","world","row"], order =
 *         "worldabcefghijkmnpqstuvxyz" Output: false Explanation: As 'd' comes
 *         after 'l' in this language, then words[0] > words[1], hence the
 *         sequence is unsorted.
 * 
 *         Example 3:
 * 
 *         Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 *         Output: false Explanation: The first three characters "app" match,
 *         and the second string is shorter (in size.) According to
 *         lexicographical rules "apple" > "app", because 'l' > '', where '' is
 *         defined as the blank character which is less than any other character
 *         (More info).
 * 
 * 
 *         Constraints:
 * 
 *         1 <= words.length <= 100 1 <= words[i].length <= 20 order.length ==
 *         26 All characters in words[i] and order are English lowercase
 *         letters.
 *
 */

public class _953_VerifyingAnAlienDictionary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isAlienSorted(String[] words, String order) {
		Map<Character, Integer> charOrderMap = new HashMap<>();
		int orderPos = 0;

		for (char ch : order.toCharArray()) {
			charOrderMap.put(ch, orderPos++);
		}
		PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<>() {
			public int compare(String a, String b) {
				int index1 = 0;
				int index2 = 0;

				while (index1 < a.length() && index2 < b.length()) {

					if (a.charAt(index1) != b.charAt(index2)) {
						return charOrderMap.get(a.charAt(index1)) - charOrderMap.get(b.charAt(index2));
					}
					index1++;
					index2++;
				}

				if (index1 == a.length()) {
					return -1;
				} else if (index2 == b.length()) {
					return 1;
				} else {
					return index1 - index2;
				}
			}
		});

		for (String word : words) {
			pq.offer(word);
		}

		for (String word : words) {
			if (!word.equals(pq.poll())) {
				return false;
			}
		}
		return true;
	}
}
