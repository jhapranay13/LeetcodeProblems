package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.
 *
 * Implement the MagicDictionary class:
 *
 * MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 *
 * Explanation
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 *
 *
 * Constraints:
 *
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case English letters.
 * All the strings in dictionary are distinct.
 * 1 <= searchWord.length <= 100
 * searchWord consists of only lower-case English letters.
 * buildDict will be called only once before search.
 * At most 100 calls will be made to search.
 *
 *
 */

public class _676_Implement_Magic_Dictionary {
    class MagicDictionary {

        class Node {
            Map<Character, Node> cache = new HashMap<>();
            boolean eow = false;
        }
        Node root;

        public MagicDictionary() {
            this.root = new Node();
        }

        public void buildDict(String[] dictionary) {

            for (String word : dictionary) {
                insert(word);
            }
        }

        private void insert(String str) {
            Node curr = root;

            for (char ch : str.toCharArray()) {

                if (!curr.cache.containsKey(ch)) {
                    curr.cache.put(ch, new Node());
                }
                curr = curr.cache.get(ch);
            }
            curr.eow = true;
        }

        public boolean search(String searchWord) {
            return search(searchWord, 0, root, 0);
        }

        private boolean search(String word, int index, Node node, int count) {

            if (index == word.length()) {

                if (node.eow && count == 1) {
                    return true;
                }
                return false;
            }
            char ch = word.charAt(index);

            for (char key : node.cache.keySet()) {
                int nextCount = count + (ch == key ? 0 : 1);

                if (search(word, index + 1, node.cache.get(key), nextCount)) {
                    return true;
                }
            }
            return false;
        }
    }

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
}
