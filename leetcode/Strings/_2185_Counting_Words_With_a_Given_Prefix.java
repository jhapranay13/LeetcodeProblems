package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an array of strings words and a string pref.
 *
 * Return the number of strings in words that contain pref as a prefix.
 *
 * A prefix of a string s is any leading contiguous substring of s.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["pay","attention","practice","attend"], pref = "at"
 * Output: 2
 * Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".
 * Example 2:
 *
 * Input: words = ["leetcode","win","loops","success"], pref = "code"
 * Output: 0
 * Explanation: There are no strings that contain "code" as a prefix.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length, pref.length <= 100
 * words[i] and pref consist of lowercase English letters.
 *
 */

public class _2185_Counting_Words_With_a_Given_Prefix {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean eow = false;
        int count = 0;
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                TrieNode temp = curr.children.getOrDefault(ch, new TrieNode());
                temp.count++;
                curr.children.put(ch, temp);
                curr = temp;
            }
            curr.eow = true;
        }

        public int getCount(String word) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                TrieNode temp = curr.children.get(ch);

                if (temp == null) {
                    return 0;
                }
                curr = temp;
            }
            return curr.count;
        }
    }

    public int prefixCount(String[] words, String pref) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }
        return trie.getCount(pref);
    }
}
