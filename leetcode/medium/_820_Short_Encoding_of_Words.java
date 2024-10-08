package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 *
 * words.length == indices.length
 * The reference string s ends with the '#' character.
 * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * Example 2:
 *
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * words[i] consists of only lowercase letters.
 *
 */

public class _820_Short_Encoding_of_Words {
    // The idea is create a suffix trie and insert largest length to smallest length
    // To check
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean eow = false;
    }

    class Trie {
        TrieNode root = new TrieNode();

        public boolean insert(String word) {
            TrieNode curr = root;
            boolean isPresent = true;

            for (int i = word.length() - 1; i >= 0; i--) {
                char ch = word.charAt(i);

                if (!curr.children.containsKey(ch)) {
                    isPresent = false;
                    curr.children.put(ch, new TrieNode());
                }
                curr = curr.children.get(ch);
            }
            curr.eow = true;
            return isPresent;
        }
    }

    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        int ans = 0;
        Trie trie = new Trie();

        for (String word : words) {

            if (!trie.insert(word)) {
                ans += word.length() + 1;
            }
        }
        return ans;
    }
}
