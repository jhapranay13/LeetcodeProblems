package leetcode.Strings;

import java.util.*;

/**
 *
 * Given an array of strings words, find the longest string in words such that every prefix of it is also in words.
 *
 * For example, let words = ["a", "app", "ap"]. The string "app" has prefixes "ap" and "a", all of which are in words.
 * Return the string described above. If there is more than one string with the same length, return the lexicographically smallest one, and if no string exists, return "".
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["k","ki","kir","kira", "kiran"]
 * Output: "kiran"
 * Explanation: "kiran" has prefixes "kira", "kir", "ki", and "k", and all of them appear in words.
 * Example 2:
 *
 * Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation: Both "apple" and "apply" have all their prefixes in words.
 * However, "apple" is lexicographically smaller, so we return that.
 * Example 3:
 *
 * Input: words = ["abc", "bc", "ab", "qwe"]
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 10^5
 * 1 <= sum(words[i].length) <= 10^5
 *
 */

public class _1858_Longest_Word_With_All_Prefixes {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean eow = false;
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word, List<String> holder) {
            TrieNode curr = root;
            int prefixCount = 0;

            for (char ch : word.toCharArray()) {
                TrieNode temp = curr.children.getOrDefault(ch, new TrieNode());

                if (temp.eow) {
                    prefixCount++;
                }
                curr.children.put(ch, temp);
                curr = temp;
            }
            curr.eow = true;

            if (prefixCount == word.length() - 1) {

                if (!holder.isEmpty() && holder.get(holder.size() - 1).length() <  word.length()) {
                    holder.clear();
                }
                holder.add(word);
            }
        }
    }
    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Trie trie = new Trie();
        List<String> holder = new ArrayList<>();

        for (String word : words) {
            trie.insert(word, holder);
        }

        if (holder.isEmpty()) {
            return "";
        }
        Collections.sort(holder);
        return holder.get(0);
    }
}
