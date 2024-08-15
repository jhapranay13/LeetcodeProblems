package leetcode.Strings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a non-empty list of words, return the k most frequent elements.
 * 
 *         Your answer should be sorted by frequency from highest to lowest. If
 *         two words have the same frequency, then the word with the lower
 *         alphabetical order comes first.
 * 
 *         Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k
 *         = 2 Output: ["i", "love"] Explanation: "i" and "love" are the two
 *         most frequent words. Note that "i" comes before "love" due to a lower
 *         alphabetical order.
 *
 *         Example 2: Input: ["the", "day", "is", "sunny", "the", "the", "the",
 *         "sunny", "is", "is"], k = 4 Output: ["the", "is", "sunny", "day"]
 *         Explanation: "the", "is", "sunny" and "day" are the four most
 *         frequent words, with the number of occurrence being 4, 3, 2 and 1
 *         respectively.
 * 
 *         Note: You may assume k is always valid, 1 <= k <= number of unique
 *         elements. Input words contain only lowercase letters.
 *
 */

public class _692_TopKFrequentWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> freq = new HashMap<>();

		for (String word : words) {
			freq.put(word, freq.getOrDefault(word, 0) + 1);
		}
		PriorityQueue<String> q = new PriorityQueue<>(new Comparator<String>() {

			public int compare(String x, String y) {

				if (freq.get(x) == freq.get(y)) {
					return x.compareTo(y);
				}
				return freq.get(y) - freq.get(x);
			}
		} );

		for (String word : freq.keySet()) {
			q.offer(word);
		}
		List<String> ans = new ArrayList<>();

		while (k-- > 0) {
			ans.add(q.poll());
		}
		return ans;
	}
}
