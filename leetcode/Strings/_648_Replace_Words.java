package leetcode.Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
 *
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
 *
 * Return the sentence after the replacement.
 *
 *
 *
 * Example 1:
 *
 * Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 * Example 2:
 *
 * Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * Output: "a a b c"
 *
 *
 * Constraints:
 *
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case letters.
 * 1 <= sentence.length <= 106
 * sentence consists of only lower-case letters and spaces.
 * The number of words in sentence is in the range [1, 1000]
 * The length of each word in sentence is in the range [1, 1000]
 * Every two consecutive words in sentence will be separated by exactly one space.
 * sentence does not have leading or trailing spaces.
 *
 */

public class _648_Replace_Words {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean eow;
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {

                if (!curr.children.containsKey(ch)) {
                    curr.children.put(ch, new TrieNode());
                }
                TrieNode temp = curr.children.get(ch);
                curr = temp;
            }
            curr.eow = true;
        }

        public String find(String word) {
            TrieNode curr = root;
            StringBuilder holder = new StringBuilder();
            int index = 0;

            while (index < word.length()) {
                char ch = word.charAt(index++);

                if (curr == null || curr.eow || !curr.children.containsKey(ch)) {
                    break;
                }
                TrieNode temp = curr.children.get(ch);
                holder.append(ch);
                curr = temp;
            }

            if (!curr.eow) {
                return "";
            }
            return holder.toString();
        }
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder ansHolder = new StringBuilder();
        Trie trie = new Trie();

        for (String word : dictionary) {
            trie.insert(word);
        }
        String[] split = sentence.split(" ");

        for (String word : split) {
            String found = trie.find(word);

            if (ansHolder.length() > 0) {
                ansHolder.append(" ");
            }

            if (found.equals("")) {
                ansHolder.append(word);
            } else {
                ansHolder.append(found);
            }
        }
        return ansHolder.toString().trim();
    }
}
