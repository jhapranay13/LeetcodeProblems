package leetcode.DP.HashMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of words where each word consists of lowercase
 *         English letters.
 * 
 *         wordA is a predecessor of wordB if and only if we can insert exactly
 *         one letter anywhere in wordA without changing the order of the other
 *         characters to make it equal to wordB.
 * 
 *         For example, "abc" is a predecessor of "abac", while "cba" is not a
 *         predecessor of "bcad". A word chain is a sequence of words [word1,
 *         word2, ..., wordk] with k >= 1, where word1 is a predecessor of
 *         word2, word2 is a predecessor of word3, and so on. A single word is
 *         trivially a word chain with k == 1.
 * 
 *         Return the length of the longest possible word chain with words
 *         chosen from the given list of words.
 * 
 *         Example 1:
 * 
 *         Input: words = ["a","b","ba","bca","bda","bdca"] Output: 4
 *         Explanation: One of the longest word chains is
 *         ["a","ba","bda","bdca"].
 * 
 *         Example 2:
 * 
 *         Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"] Output: 5
 *         Explanation: All the words can be put in a word chain ["xb", "xbc",
 *         "cxbc", "pcxbc", "pcxbcf"].
 * 
 *         Example 3:
 * 
 *         Input: words = ["abcd","dbqca"] Output: 1 Explanation: The trivial
 *         word chain ["abcd"] is one of the longest word chains.
 *         ["abcd","dbqca"] is not a valid word chain because the ordering of
 *         the letters is changed.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= words.length <= 1000 1 <= words[i].length <= 16 words[i] only
 *         consists of lowercase English letters.
 *
 */

public class _1048_LongestStringChain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Graph BFS Correct but TLE
	public int longestStrChain(String[] words) {
		Map<String, Set<String>> adjacency = new HashMap<>();

		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				String left = word.substring(0, i);
				String right = word.substring(i + 1);
				String str = left + right;
				Set<String> wordSet = adjacency.getOrDefault(word, new HashSet<>());
				wordSet.add(str);
				adjacency.put(word, wordSet);
			}
		}
		int ans = 0;
		Deque<String> q = new LinkedList<>();

		for (String word : words) {
			q.offer(word);
			int tempAns = 0;

			while (!q.isEmpty()) {
				int size = q.size();

				while (size > 0) {
					String qWord = q.poll();
					Set<String> wordSet = adjacency.getOrDefault(qWord, new HashSet<>());

					for (String str : wordSet) {
						q.offer(str);
					}
					size--;
				}
				if (!q.isEmpty()) {
					tempAns++;
				}
			}
			ans = Math.max(ans, tempAns);
		}
		return ans;
	}

	// ============================================================================
	// Top Down HashMap DP
	public int longestStrChain1(String[] words) {
		Set<String> wordSet = new HashSet<>();

		for (String word : words) {
			wordSet.add(word);
		}
		int ans = 0;
		Map<String, Integer> memo = new HashMap<>();

		for (String word : words) {
			ans = Math.max(ans, dfs(word, wordSet, memo));
		}
		return ans;
	}

	private int dfs(String word, Set<String> wordSet, Map<String, Integer> memo) {
		if (memo.get(word) != null) {
			return memo.get(word);
		}
		int ans = 1;

		for (int i = 0; i < word.length(); i++) {
			String left = word.substring(0, i);
			String right = word.substring(i + 1);
			String str = left + right;

			if (wordSet.contains(str)) {
				ans = Math.max(ans, 1 + dfs(str, wordSet, memo));
			}
		}
		memo.put(word, ans);
		return ans;
	}

	// ============================================================================
	// Bottom up DP
	public int longestStrChain2(String[] words) {
		Set<String> wordSet = new HashSet<>();

		for (String word : words) {
			wordSet.add(word);
		}
		int max = 0;
		Arrays.sort(words, new Comparator<String>() {
			public int compare(String strA, String strB) {
				return strA.length() - strB.length();
			}
		});
		Map<String, Integer> memo = new HashMap<>();

		for (String word : words) {
			int ans = 1;

			for (int i = 0; i < word.length(); i++) {
				String left = word.substring(0, i);
				String right = word.substring(i + 1);
				String str = left + right;

				if (wordSet.contains(str)) {
					ans = Math.max(ans, 1 + memo.getOrDefault(str, 0));
				}
			}
			memo.put(word, ans);
			max = Math.max(ans, max);
		}
		return max;
	}
}
