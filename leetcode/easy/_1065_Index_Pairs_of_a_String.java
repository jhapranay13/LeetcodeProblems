package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * Given a string text and an array of strings words, return an array of all index pairs [i, j] so that the substring text[i...j] is in words.
 *
 * Return the pairs [i, j] in sorted order (i.e., sort them by their first coordinate, and in case of ties sort them by their second coordinate).
 *
 *
 *
 * Example 1:
 *
 * Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
 * Output: [[3,7],[9,13],[10,17]]
 * Example 2:
 *
 * Input: text = "ababa", words = ["aba","ab"]
 * Output: [[0,1],[0,2],[2,3],[2,4]]
 * Explanation: Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 100
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 50
 * text and words[i] consist of lowercase English letters.
 * All the strings of words are unique.
 *
 *
 */

public class _1065_Index_Pairs_of_a_String {
    class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean eow = false;
    }

    class Trie {
        Node root = new Node();

        public void insert(String word) {
            Node curr = root;

            for (char ch : word.toCharArray()) {

                if (!curr.children.containsKey(ch)) {
                    curr.children.put(ch, new Node());
                }
                curr = curr.children.get(ch);
            }
            curr.eow = true;
        }

        public void find(String sentence, int index, List<int[]> holder) {
            int currIndex = index;
            Node curr = root;

            for ( ; currIndex < sentence.length(); currIndex++ ) {
                char ch = sentence.charAt(currIndex);

                if (curr.eow) {
                    holder.add(new int[] {index, currIndex - 1});
                }

                if (!curr.children.containsKey(ch)) {
                    return;
                }
                curr = curr.children.get(ch);
            }

            if (curr.eow) {
                holder.add(new int[] {index, currIndex - 1});
            }
        }
    }

    public int[][] indexPairs(String text, String[] words) {
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }
        List<int[]> holder = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            trie.find(text, i, holder);
        }
        return holder.toArray(new int[holder.size()][2]);
    }
}
