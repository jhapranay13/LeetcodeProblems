package leetcode.Tries;

/**
 *
 *
 * You are given a string array words and a string s, where words[i] and s comprise only of lowercase English letters.
 *
 * Return the number of strings in words that are a prefix of s.
 *
 * A prefix of a string is a substring that occurs at the beginning of the string. A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["a","b","c","ab","bc","abc"], s = "abc"
 * Output: 3
 * Explanation:
 * The strings in words which are a prefix of s = "abc" are:
 * "a", "ab", and "abc".
 * Thus the number of strings in words which are a prefix of s is 3.
 * Example 2:
 *
 * Input: words = ["a","a"], s = "aa"
 * Output: 2
 * Explanation:
 * Both of the strings are a prefix of s.
 * Note that the same string can occur multiple times in words, and it should be counted each time.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, s.length <= 10
 * words[i] and s consist of lowercase English letters only.
 *
 *
 */

public class _2255_Count_Prefixes_of_a_Given_String {
    class Node {
        Node[] cache = new Node[26];
        boolean eow = false;
    }

    class Trie {
        Node root = new Node();

        public void insert(String word) {
            Node curr = root;

            for (char ch : word.toCharArray()) {

                if (curr.cache[ch - 'a'] == null) {
                    curr.cache[ch - 'a'] = new Node();
                }
                curr = curr.cache[ch - 'a'];
            }
            curr.eow = true;
        }

        private boolean check(String word) {
            Node curr = root;

            for (char ch : word.toCharArray()) {

                if (curr.cache[ch - 'a'] == null) {
                    return false;
                }
                curr = curr.cache[ch - 'a'];
            }
            return true;
        }
    }

    public int countPrefixes(String[] words, String s) {
        Trie trie = new Trie();
        trie.insert(s);
        int ans = 0;

        for (String word : words) {

            if (trie.check(word)) {
                ans++;
            }
        }
        return ans;
    }
}
