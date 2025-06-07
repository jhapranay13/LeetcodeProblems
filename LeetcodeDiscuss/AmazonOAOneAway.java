package LeetcodeDiscuss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * "Given an array of strings words, where each string consists of lowercase English letters.
 * Two words wordA and wordB are considered a 'family login pair' if wordB can be obtained from wordA by cyclically shifting each character of wordA forward by one position in the alphabet (i.e., 'a' becomes 'b', 'b' becomes 'c', ..., 'z' becomes 'a').
 *
 * The task is to calculate the total count of such 'family login pairs' based on their occurrences in the input array. For each unique word W in the input array, find its corresponding 'shifted word' W_shifted. If W_shifted also exists in the input array words, then this forms a contribution to the total. The contribution for this specific W and W_shifted pair is calculated as the product of the number of times W appears in the original words array and the number of times W_shifted appears in the original words array.
 *
 * Return the sum of all such contributions for every unique word in the input array."
 *
 * Example for your given problem:
 *
 * words = {"bag", "sfe", "cbh", "cbh", "red"}
 *
 * Word: "bag"
 *
 * Occurrences of "bag": 1
 * Shifted word: "cbh"
 * Occurrences of "cbh": 2
 * Contribution: 1 * 2 = 2
 * Word: "sfe"
 *
 * Occurrences of "sfe": 1
 * Shifted word: "tgf"
 * Occurrences of "tgf": 0 (not in input array)
 * Contribution: 0
 * Word: "cbh"
 *
 * Occurrences of "cbh": 2
 * Shifted word: "dci"
 * Occurrences of "dci": 0 (not in input array)
 * Contribution: 0
 * Word: "red"
 *
 * Occurrences of "red": 1
 * Shifted word: "sfe"
 * Occurrences of "sfe": 1
 * Contribution: 1 * 1 = 1
 * Total ans = 2 + 0 + 0 + 1 = 3.
 *
 */

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    int count = 0;
    boolean eof = false;
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {

            if (!node.children.containsKey(ch)) {
                TrieNode child = new TrieNode();
                node.children.put(ch, child);
            }
            node = node.children.get(ch);
        }
        node.eof = true;
        node.count++;
    }

    public int findOneAway(String word1) {
        TrieNode node1 = root;
        TrieNode node2 = root;

        for (int i = 0; i < word1.length(); i++) {
            char ch1 = word1.charAt(i);
            int val = (ch1 - 'a' + 1) % 26;
            char ch2 = (char) ('a' + val);

            if (!node2.children.containsKey(ch2)) {
                return 0;
            }
            node1 = node1.children.get(ch1);
            node2 = node2.children.get(ch2);
        }
        return node1.count * node2.count;
    }
}
// {"bag", "sfe", "cbh", "cbh", "red"}
public class AmazonOAOneAway {

    public static void main(String args[]) {
        String[] words = {"bag", "sfe", "cbh", "cbh", "red"};
        //String[] words = {"corn", "horn", "dpso", "eqtp", "corn"};
        System.out.println(countFamilyLogins(words));
    }

    public static int countFamilyLogins(String[] words) {
        Trie trie = new Trie();
        Set<String> wordSet = new HashSet<>();

        for (String word : words) {
            trie.insert(word);
            wordSet.add(word);
        }
        int ans = 0;

        for (String word : wordSet) {
            ans += trie.findOneAway(word);
        }
        return ans;
    }
}
