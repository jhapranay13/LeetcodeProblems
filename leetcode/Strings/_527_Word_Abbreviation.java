package leetcode.Strings;

import java.util.*;

/**
 *
 * Given an array of distinct strings words, return the minimal possible abbreviations for every word.
 *
 * The following are the rules for a string abbreviation:
 *
 * The initial abbreviation for each word is: the first character, then the number of characters in between, followed by the last character.
 * If more than one word shares the same abbreviation, then perform the following operation:
 * Increase the prefix (characters in the first part) of each of their abbreviations by 1.
 * For example, say you start with the words ["abcdef","abndef"] both initially abbreviated as "a4f". Then, a sequence of operations would be ["a4f","a4f"] -> ["ab3f","ab3f"] -> ["abc2f","abn2f"].
 * This operation is repeated until every abbreviation is unique.
 * At the end, if an abbreviation did not make a word shorter, then keep it as the original word.
 *
 *
 * Example 1:
 *
 * Input: words = ["like","god","internal","me","internet","interval","intension","face","intrusion"]
 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 * Example 2:
 *
 * Input: words = ["aa","aaa"]
 * Output: ["aa","aaa"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 400
 * 2 <= words[i].length <= 400
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 *
 */

public class _527_Word_Abbreviation {
    class WordIndex {
        String word = "";
        int index = -1;
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                TrieNode temp = curr.children.getOrDefault(ch, new TrieNode());
                temp.count++;
                curr.children.put(ch, temp);
                curr = temp;
            }
        }

        public int getDifferingIndex(String word) {
            TrieNode curr = root;
            int ans = word.length();
            int index = 0;

            for (char ch : word.toCharArray()) {
                TrieNode temp = curr.children.get(ch);

                if (temp.count == 1) {
                    ans = index;
                    break;
                }
                curr = temp;
                index++;
            }
            return ans;
        }
    }

    public List<String> wordsAbbreviation(List<String> words) {
        Map<String, List<WordIndex>> abbrGroup = new HashMap<>();
        int index = 0;

        for (String word : words) {
            String abbr = getAbbriviation(word, 0);
            List<WordIndex> list = abbrGroup.getOrDefault(abbr, new ArrayList<>());
            WordIndex wi = new WordIndex();
            wi.word = word;
            wi.index = index++;
            list.add(wi);
            abbrGroup.put(abbr, list);
        }
        String[] ans = new String[words.size()];

        for (String abb : abbrGroup.keySet()) {
            List<WordIndex> group = abbrGroup.get(abb);
            Trie trie = new Trie();

            for (WordIndex wordIdx : group) {
                trie.insert(wordIdx.word);
            }

            for (WordIndex wordIdx : group) {
                int indx = trie.getDifferingIndex(wordIdx.word);
                ans[wordIdx.index] = getAbbriviation(wordIdx.word, indx);
            }
        }
        return Arrays.asList(ans);
    }

    private String getAbbriviation(String word, int index) {

        if (word.length() == 3) {
            return word;
        }
        int length = word.length() - 1 - index - 1;

        if (length <= 1) {
            return word;
        }
        return word.substring(0, index + 1) + length + word.charAt(word.length() - 1);
    }

}
