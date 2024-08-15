package leetcode.Strings;

import java.util.*;

/**
 *
 * Given a list of strings dict where all the strings are of the same length.
 *
 * Return true if there are 2 strings that only differ by 1 character in the same index, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 * Input: dict = ["abcd","acbd", "aacd"]
 * Output: true
 * Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.
 * Example 2:
 *
 * Input: dict = ["ab","cd","yz"]
 * Output: false
 * Example 3:
 *
 * Input: dict = ["abcd","cccc","abyd","abab"]
 * Output: true
 *
 *
 * Constraints:
 *
 * The number of characters in dict <= 105
 * dict[i].length == dict[j].length
 * dict[i] should be unique.
 * dict[i] contains only lowercase English letters.
 *
 *
 * Follow up: Could you solve this problem in O(n * m) where n is the length of dict and m is the length of each string.
 *
 */

public class _1554_StringsDifferByOneCharacter {
    //Trie
    class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                TrieNode temp  = curr.child.get(ch);

                if (temp == null) {
                    temp = new TrieNode();
                    curr.child.put(ch, temp);
                }
                curr = temp;
            }
        }

        public boolean search(String word) {
            return search(word, root, 0, 0);
        }

        private boolean search(String word, TrieNode node, int index, int diff) {

            if (index == word.length()) {
                return diff <= 1;
            }

            if (diff > 1 || node == null) {
                return false;
            }
            Map<Character, TrieNode> child = node.child;
            boolean ans = false;

            for (char ch : child.keySet()) {

                if (word.charAt(index) == ch) {
                    ans = search(word, child.get(ch), index + 1, diff);
                } else {
                    ans = search(word, child.get(ch), index + 1, diff + 1);
                }

                if (ans) {
                    break;
                }
            }
            return ans;
        }
    }

    public boolean differByOne(String[] dict) {
        Trie trie = new Trie();
        Arrays.sort(dict, (a, b) -> a.compareTo(b));

        for (String word : dict) {

            if (trie.search(word)) {
                return true;
            }
            trie.insert(word);
        }
        return false;
    }
    //=============================================================================================
    // TLE
    public boolean differByOne1(String[] dict) {
        Set<String> patternSet = new HashSet<>();

        for (String word : dict) {

            for (int i = 0; i < word.length(); i++) {
                String left = word.substring(0, i);
                String right = word.substring(i + 1);
                String fullString = left + "*" + right;

                if(!patternSet.add(fullString)) {
                    return true;
                }
            }
        }
        return false;
    }
}
