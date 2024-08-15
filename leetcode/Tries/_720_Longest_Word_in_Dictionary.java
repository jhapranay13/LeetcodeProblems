package leetcode.Tries;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.
 *
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.
 *
 * Note that the word should be built from left to right with each additional character being added to the end of a previous word.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 *
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * words[i] consists of lowercase English letters.
 *
 */

public class _720_Longest_Word_in_Dictionary {
    class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        boolean eof = false;
    }

    class Trie {
        TrieNode root = new TrieNode();
        String longestWord = null;

        public void insert(String word) {
            TrieNode node = root;

            for (char ch : word.toCharArray()) {
                TrieNode next = node.child.getOrDefault(ch, new TrieNode());
                node.child.put(ch, next);
                node = next;
            }
            node.eof = true;
        }

        public String getLongestWord() {
            recur(root, new StringBuilder());
            return longestWord == null ? "" : longestWord;
        }

        private void recur(TrieNode node, StringBuilder word) {
            Map<Character, TrieNode> child = node.child;

            if (word.length() > 0 && node.eof) {

                if (longestWord == null) {
                    longestWord = word.toString();
                } else if (longestWord.length() < word.length()) {
                    longestWord = word.toString();
                } else if (longestWord.length() == word.length()) {
                    longestWord = longestWord.compareTo(word.toString()) > 0 ? word.toString() : longestWord;
                }
            }

            for (char ch : child.keySet()) {
                word.append(ch);

                if (child.get(ch).eof) {
                    recur(child.get(ch), word);
                }
                word.deleteCharAt(word.length() - 1);
            }
        }
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }
        return trie.getLongestWord();
    }
}
