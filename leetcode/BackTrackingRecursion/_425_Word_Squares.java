package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.
 *
 * A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 *
 *
 * Example 1:
 *
 * Input: words = ["area","lead","wall","lady","ball"]
 * Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * Example 2:
 *
 * Input: words = ["abat","baba","atan","atal"]
 * Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 4
 * All words[i] have the same length.
 * words[i] consists of only lowercase English letters.
 * All words[i] are unique.
 *
 */

public class _425_Word_Squares {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<Integer> wordList = new ArrayList<>();
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word, int index) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {

                if (!curr.children.containsKey(ch)) {
                    curr.children.put(ch, new TrieNode());
                }
                curr = curr.children.get(ch);
                curr.wordList.add(index);
            }
        }

        public List<Integer> getWordsWithPrefix(String prefix) {
            TrieNode curr = root;

            for (char ch : prefix.toCharArray()) {

                if (!curr.children.containsKey(ch)) {
                    return new ArrayList<Integer>();
                }
                curr = curr.children.get(ch);
            }
            return curr.wordList;
        }
    }
    public List<List<String>> wordSquares(String[] words) {
        Trie trie = new Trie();
        int index = 0;

        for (String word : words) {
            trie.insert(word, index++);
        }
        int length = words[0].length();
        List<List<String>> ans = new ArrayList<>();

        for (String word : words) {
            ArrayList<String> holder = new ArrayList<>();
            holder.add(word);
            recur(words, trie, ans, holder, length, 1);
        }
        return ans;
    }

    public void recur(String[] words, Trie trie, List<List<String>> ans, ArrayList<String> holder,
                      int length, int step) {

        if (step == length) {
            ans.add((List<String>)holder.clone());
            return;
        }
        StringBuilder prefix = new StringBuilder();
        // taking all chars from all the words that has been added at position step
        for (String word : holder) {
            prefix.append(word.charAt(step));
        }
        List<Integer> indexes = trie.getWordsWithPrefix(prefix.toString());

        for (int index : indexes) {
            holder.add(words[index]);
            recur(words, trie, ans, holder, length, step + 1);
            holder.remove(holder.size() - 1);
        }
    }
}
