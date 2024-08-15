package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 3 dots in word for search queries.
 * At most 10^4 calls will be made to addWord and search.
 *
 */

public class _211_Design_Add_and_Search_Words_Data_Structure {
    class WordDictionary {
        class TrieNode {
            Map<Character, TrieNode> childMap = new HashMap<>();
            boolean eow = false;
        }

        class Trie {
            TrieNode root = new TrieNode();

            public void update(String str) {
                TrieNode curr = root;

                for (char ch : str.toCharArray()) {
                    TrieNode temp = curr.childMap.getOrDefault(ch, new TrieNode());
                    curr.childMap.put(ch, temp);
                    curr = temp;
                }
                curr.eow = true;
            }

            public boolean find(String word) {
                return find(word, root);
            }

            private boolean find(String word, TrieNode node) {

                for (int index = 0; index < word.length(); index++) {
                    char ch = word.charAt(index);

                    if (node.childMap.containsKey(ch)) {
                        TrieNode temp = node.childMap.get(ch);
                        node = temp;
                    } else {

                        if (ch == '.') {
                            Map<Character, TrieNode> child = node.childMap;

                            for(char key : child.keySet()) {

                                if (find(word.substring(index + 1), child.get(key))) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                }
                return node != null ? node.eow : false;
            }
        }
        Trie trie;

        public WordDictionary() {
            this.trie = new Trie();
        }

        public void addWord(String word) {
            trie.update(word);
        }

        public boolean search(String word) {
            return trie.find(word);
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
