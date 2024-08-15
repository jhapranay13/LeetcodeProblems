package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
 *
 * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 *
 * Implement the StreamChecker class:
 *
 * StreamChecker(String[] words) Initializes the object with the strings array words.
 * boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 *
 *
 * Example 1:
 *
 * Input
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * Output
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 *
 * Explanation
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // return False
 * streamChecker.query("b"); // return False
 * streamChecker.query("c"); // return False
 * streamChecker.query("d"); // return True, because 'cd' is in the wordlist
 * streamChecker.query("e"); // return False
 * streamChecker.query("f"); // return True, because 'f' is in the wordlist
 * streamChecker.query("g"); // return False
 * streamChecker.query("h"); // return False
 * streamChecker.query("i"); // return False
 * streamChecker.query("j"); // return False
 * streamChecker.query("k"); // return False
 * streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * words[i] consists of lowercase English letters.
 * letter is a lowercase English letter.
 * At most 4 * 104 calls will be made to query.
 *
 */

public class _1032_StreamOfCharacter {
    class TrieNode {
        Map<Character, TrieNode> cache = new HashMap<>();
        boolean eow = false;
    }

    class StreamChecker {

        TrieNode node;
        StringBuilder stream;

        public StreamChecker(String[] words) {
            node = new TrieNode();
            stream = new StringBuilder();

            for (String str : words) {
                TrieNode curr = node;

                for (int i = str.length() - 1; i >= 0; i--) {

                    if (!curr.cache.containsKey(str.charAt(i))) {
                        curr.cache.put(str.charAt(i), new TrieNode());
                    }
                    curr = curr.cache.get(str.charAt(i));
                }
                curr.eow = true;
            }
        }

        public boolean query(char letter) {
            stream.insert(0, letter);
            TrieNode curr = node;

            for (int i = 0; i < stream.length(); i++) {
                char ch = stream.charAt(i);

                if (!curr.cache.containsKey(ch)) {
                    return false;
                }
                curr = curr.cache.get(ch);

                if (curr.eow) {
                    return true;
                }
            }
            return false;
        }
    }

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
}
