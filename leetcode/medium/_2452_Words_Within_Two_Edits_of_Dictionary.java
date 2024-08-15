package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English letters and have the same length.
 *
 * In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words from queries that, after a maximum of two edits, equal some word from dictionary.
 *
 * Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits. Return the words in the same order they appear in queries.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
 * Output: ["word","note","wood"]
 * Explanation:
 * - Changing the 'r' in "word" to 'o' allows it to equal the dictionary word "wood".
 * - Changing the 'n' to 'j' and the 't' to 'k' in "note" changes it to "joke".
 * - It would take more than 2 edits for "ants" to equal a dictionary word.
 * - "wood" can remain unchanged (0 edits) and match the corresponding dictionary word.
 * Thus, we return ["word","note","wood"].
 * Example 2:
 *
 * Input: queries = ["yes"], dictionary = ["not"]
 * Output: []
 * Explanation:
 * Applying any two edits to "yes" cannot make it equal to "not". Thus, we return an empty array.
 *
 *
 * Constraints:
 *
 * 1 <= queries.length, dictionary.length <= 100
 * n == queries[i].length == dictionary[j].length
 * 1 <= n <= 100
 * All queries[i] and dictionary[j] are composed of lowercase English letters.
 *
 */

public class _2452_Words_Within_Two_Edits_of_Dictionary {
    class Node {
        Map<Character, Node> cache = new HashMap<>();
        boolean eof = false;
    }

    class Trie {
        Node root = new Node();

        public void insert(String word) {
            Node curr = root;

            for (char ch : word.toCharArray()) {

                if (!curr.cache.containsKey(ch)) {
                    curr.cache.put(ch, new Node());
                }
                curr = curr.cache.get(ch);
            }
            curr.eof = true;
        }

        public boolean check(String word) {
            return check(root, word, 0, 0);
        }

        private boolean check(Node node, String word, int index, int count) {

            if (index == word.length()) {
                return count <= 2;
            }

            if (count > 2) {
                return false;
            }
            char ch = word.charAt(index);
            Map<Character, Node> cache = node.cache;

            for (char key : node.cache.keySet()) {
                int cnt = count;

                if (key != ch) {
                    cnt++;
                }

                if (check(node.cache.get(key), word, index + 1, cnt)) {
                    return true;
                }
            }
            return false;
        }
    }

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        Trie trie = new Trie();

        for (String word : dictionary) {
            trie.insert(word);
        }
        List<String> ans = new ArrayList<>();

        for (String word : queries) {

            if (trie.check(word)) {
                ans.add(word);
            }
        }
        return ans;
    }
}
