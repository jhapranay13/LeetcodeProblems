package leetcode.Graph.WordLadder;

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
 *         and a dictionary wordList, return the number of words in the shortest
 *         transformation sequence from beginWord to endWord, or 0 if no such
 *         sequence exists.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: beginWord = "hit", endWord = "cog", wordList =
 *         ["hot","dot","dog","lot","log","cog"] Output: 5 Explanation: One
 *         shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog"
 *         -> cog", which is 5 words long. 
 *         
 *         Example 2:
 * 
 *         Input: beginWord = "hit", endWord = "cog", wordList =
 *         ["hot","dot","dog","lot","log"] Output: 0 Explanation: The endWord
 *         "cog" is not in wordList, therefore there is no valid transformation
 *         sequence.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= beginWord.length <= 10 endWord.length == beginWord.length 1 <=
 *         wordList.length <= 5000 wordList[i].length == beginWord.length
 *         beginWord, endWord, and wordList[i] consist of lowercase English
 *         letters. beginWord != endWord All the words in wordList are unique.
 *
 */

public class _127_WordLadder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Using char array
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		char[] alphabet = new char[26];

		for (int i = 0; i < 26; i++) {
			alphabet[i] = (char) ('a' + i);
		}
		Set<String> wordSet = new HashSet<>();

		for (String str : wordList) {
			wordSet.add(str);
		}
		Set<String> v = new HashSet<>();
		v.add(beginWord);
		Deque<String> q = new LinkedList<>();
		q.offer(beginWord);
		int step = 0;
		boolean flag = false;

		outer: while (!q.isEmpty()) {
			int size = q.size();
			step++;

			while (size-- > 0) {
				String word = q.poll();

				if (word.equals(endWord)) {
					flag = true;
					break outer;
				}

				for (int i = 0; i < word.length(); i++) {
					String l = word.substring(0, i);
					String r = word.substring(i + 1);

					for (int j = 0; j < alphabet.length; j++) {
						String curr = l + alphabet[j] + r;

						if (wordSet.contains(curr)) {

							if (!v.contains(curr)) {
								q.offer(curr);
								v.add(curr);
							}
						}
					}
				}
			}
		}
		return flag ? step : 0;
	}

	// =======================================================================
	// Using PlaceHolder
	public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		Map<String, List<String>> cache = new HashMap<>();

		for (String word : wordList) {

			for (int i = 0; i < word.length(); i++) {
				String l = word.substring(0, i);
				String r = word.substring(i + 1);
				String curr = l + "*" + r;
				List<String> list = cache.getOrDefault(curr, new ArrayList<>());
				list.add(word);
				cache.put(curr, list);
			}
		}
		Deque<String> q = new LinkedList<>();
		Set<String> v = new HashSet<>();
		v.add(beginWord);
		q.add(beginWord);
		int step = 0;
		boolean flag = false;

		outer: while (!q.isEmpty()) {
			int size = q.size();
			step++;

			while (size-- > 0) {
				String word = q.poll();

				if (word.equals(endWord)) {
					flag = true;
					break outer;
				}

				for (int i = 0; i < word.length(); i++) {
					String l = word.substring(0, i);
					String r = word.substring(i + 1);
					String curr = l + "*" + r;
					List<String> list = cache.getOrDefault(curr, new ArrayList<>());

					for (String str : list) {

						if (!v.contains(str)) {
							v.add(str);
							q.add(str);
						}
					}
				}
			}
		}
		return flag ? step : 0;
	}
}
