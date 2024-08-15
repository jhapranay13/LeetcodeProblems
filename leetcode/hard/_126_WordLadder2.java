package leetcode.hard;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         A transformation sequence from word beginWord to word endWord using a
 *         dictionary wordList is a sequence of words beginWord -> s1 -> s2 ->
 *         ... -> sk such that:
 * 
 *         Every adjacent pair of words differs by a single letter. Every si for
 *         1 <= i <= k is in wordList. Note that beginWord does not need to be
 *         in wordList. sk == endWord Given two words, beginWord and endWord,
 *         and a dictionary wordList, return all the shortest transformation
 *         sequences from beginWord to endWord, or an empty list if no such
 *         sequence exists. Each sequence should be returned as a list of the
 *         words [beginWord, s1, s2, ..., sk].
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: beginWord = "hit", endWord = "cog", wordList =
 *         ["hot","dot","dog","lot","log","cog"] Output:
 *         [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 *         Explanation: There are 2 shortest transformation sequences: "hit" ->
 *         "hot" -> "dot" -> "dog" -> "cog" "hit" -> "hot" -> "lot" -> "log" ->
 *         "cog" 
 *         
 *         Example 2:
 * 
 *         Input: beginWord = "hit", endWord = "cog", wordList =
 *         ["hot","dot","dog","lot","log"] Output: [] Explanation: The endWord
 *         "cog" is not in wordList, therefore there is no valid transformation
 *         sequence.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= beginWord.length <= 5 endWord.length == beginWord.length 1 <=
 *         wordList.length <= 1000 wordList[i].length == beginWord.length
 *         beginWord, endWord, and wordList[i] consist of lowercase English
 *         letters. beginWord != endWord All the words in wordList are unique.
 *
 */

public class _126_WordLadder2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> ans = new ArrayList<>();
		Set<String> words = new HashSet<>(wordList);

		if (!words.contains(endWord)) {
			return ans;
		}
		Map<String, Set<String>> adj = new HashMap<>();
		createAdj(beginWord, endWord, words, adj);
		Map<String, Integer> wordDistance = new HashMap<>();
		shortestDitanceFromStart(beginWord, endWord, words, adj, wordDistance);
		List<String> partial = new ArrayList<>();
		dfs(endWord, adj, wordDistance, ans, partial, beginWord);
		return ans;
	}

	private void dfs(String endWord, Map<String, Set<String>> adj, Map<String, Integer> wordDistance,
			List<List<String>> ans, List<String> partial, String word) {
		if (word.equals(endWord)) {
			partial.add(endWord);
			ans.add(new ArrayList<>(partial));
			partial.remove(partial.size() - 1);
		}
		partial.add(word);
		Set<String> adjSet = new HashSet<>();

		for (int i = 0; i < word.length(); i++) {
			String left = word.substring(0, i);
			String right = word.substring(i + 1);
			String full = left + "*" + right;
			Set<String> set = adj.get(full);

			if (set != null && set.size() > 0) {
				adjSet.addAll(set);
			}
		}

		for (String child : adjSet) {
			if (wordDistance.containsKey(child)) {
				if (wordDistance.get(child) == wordDistance.get(word) + 1) {
					dfs(endWord, adj, wordDistance, ans, partial, child);
				}
			}
		}
		partial.remove(partial.size() - 1);
	}

	private void shortestDitanceFromStart(String beginWord, String endWord, Set<String> words,
			Map<String, Set<String>> adj, Map<String, Integer> wordDistance) {
		Deque<String> q = new LinkedList<>();
		q.offer(beginWord);
		wordDistance.put(beginWord, 0);

		while (!q.isEmpty()) {
			String word = q.poll();

			if (word.equals(endWord)) {
				break;
			}
			int distance = wordDistance.get(word);

			for (int i = 0; i < word.length(); i++) {
				String left = word.substring(0, i);
				String right = word.substring(i + 1);
				String full = left + "*" + right;
				Set<String> adjSet = adj.getOrDefault(full, new HashSet<>());

				for (String str : adjSet) {
					if (!wordDistance.containsKey(str)) {
						q.offer(str);
						wordDistance.put(str, distance + 1);
					}
				}
			}
		}
	}

	private void createAdj(String beginWord, String endWord, Set<String> words, Map<String, Set<String>> adj) {
		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				String left = word.substring(0, i);
				String right = word.substring(i + 1);
				String full = left + "*" + right;
				Set<String> set = adj.getOrDefault(full, new HashSet<>());
				set.add(word);
				adj.put(full, set);
			}
		}
	}
}
