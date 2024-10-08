package leetcode.Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given a list of unique words, return all the pairs of the distinct
 *         indices (i, j) in the given list, so that the concatenation of the
 *         two words words[i] + words[j] is a palindrome.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: words = ["abcd","dcba","lls","s","sssll"] Output:
 *         [[0,1],[1,0],[3,2],[2,4]] Explanation: The palindromes are
 *         ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 *         Example 2:
 * 
 *         Input: words = ["bat","tab","cat"] Output: [[0,1],[1,0]] Explanation:
 *         The palindromes are ["battab","tabbat"]
 * 
 *         Example 3:
 * 
 *         Input: words = ["a",""] Output: [[0,1],[1,0]]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= words.length <= 5000 0 <= words[i].length <= 300 words[i]
 *         consists of lower-case English letters.
 *
 */

public class _336_PalindromePairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> ans = new ArrayList<>();
		Map<String, Integer> indexes = new HashMap<>();

		for (int i = 0; i < words.length; i++) {
			indexes.put(words[i], i);
		}
		Set<String> v = new HashSet<>();

		for (int j = 0; j < words.length; j++) {
			String w = words[j];

			for (int i = 0; i <= w.length(); i++) {
				StringBuilder left = new StringBuilder(w.substring(0, i));
				StringBuilder right = new StringBuilder(w.substring(i));
				String leftRev = left.reverse().toString();
				String rightRev = right.reverse().toString();
				boolean lflag = isPlaindrome(left.toString(), 0, left.length() - 1);
				boolean rflag = isPlaindrome(right.toString(), 0, right.length() - 1);

				if (indexes.containsKey(leftRev) && indexes.get(leftRev) != j) {
					int index = indexes.get(leftRev);
					String key = j + "," + index;

					if (!v.contains(key) && rflag) {
						v.add(key);
						List<Integer> res = new ArrayList<>();
						res.add(j);
						res.add(index);
						ans.add(res);
					}
				}

				if (indexes.containsKey(rightRev) && indexes.get(rightRev) != j) {
					int index = indexes.get(rightRev);
					String key = index + "," + j;

					if (!v.contains(key) && lflag) {
						v.add(key);
						List<Integer> res = new ArrayList<>();
						res.add(index);
						res.add(j);
						ans.add(res);
					}
				}
			}
		}
		return ans;
	}

	private boolean isPlaindrome(String s, int i, int j) {

		while (i <= j) {

			if (s.charAt(i++) != s.charAt(j--)) {
				return false;
			}
		}
		return true;
	}

	// ======================================================================
	// Trie Solution Sample
	// Is the given string a palindrome after index i?
    // Tip: Leave this as a method stub in an interview unless you have time
    // or the interviewer tells you to write it. The Trie itself should be
    // the main focus of your time.
    public boolean hasPalindromeRemaining(String s, int i) {
        int p1 = i;
        int p2 = s.length() - 1;
        while (p1 < p2) {
            if (s.charAt(p1) != s.charAt(p2)) return false;
            p1++; p2--;
        }
        return true;
    }
    
    class TrieNode {
        public int wordEnding = -1; // We'll use -1 to mean there's no word ending here.
        public Map<Character, TrieNode> next = new HashMap<>();
        public List<Integer> palindromePrefixRemaining = new ArrayList<>();
    }

    public List<List<Integer>> palindromePairs1(String[] words) {

        TrieNode trie = new TrieNode();

        // Build the Trie
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            String reversedWord = new StringBuilder(word).reverse().toString();
            TrieNode currentTrieLevel = trie;
            for (int j = 0; j < word.length(); j++) {
                if (hasPalindromeRemaining(reversedWord, j)) {
                    currentTrieLevel.palindromePrefixRemaining.add(wordId);
                }
                Character c = reversedWord.charAt(j);
                if (!currentTrieLevel.next.containsKey(c)) {
                    currentTrieLevel.next.put(c, new TrieNode());
                }
                currentTrieLevel = currentTrieLevel.next.get(c);
            }
            currentTrieLevel.wordEnding = wordId;
        }

        // Find pairs
        List<List<Integer>> pairs = new ArrayList<>();
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            TrieNode currentTrieLevel = trie;
            for (int j = 0; j < word.length(); j++) {
                // Check for pairs of case 3.
                if (currentTrieLevel.wordEnding != -1
                   && hasPalindromeRemaining(word, j)) {
                    pairs.add(Arrays.asList(wordId, currentTrieLevel.wordEnding));
                }
                // Move down to the next trie level.
                Character c = word.charAt(j);
                currentTrieLevel = currentTrieLevel.next.get(c);
                if (currentTrieLevel == null) break;
            }
            if (currentTrieLevel == null) continue;
            // Check for pairs of case 1. Note the check to prevent non distinct pairs.
            if (currentTrieLevel.wordEnding != -1 && currentTrieLevel.wordEnding != wordId) {
                pairs.add(Arrays.asList(wordId, currentTrieLevel.wordEnding));
            }
            // Check for pairs of case 2.
            for (int other : currentTrieLevel.palindromePrefixRemaining) {
                pairs.add(Arrays.asList(wordId, other));
            }
        }

        return pairs;
    }
}
