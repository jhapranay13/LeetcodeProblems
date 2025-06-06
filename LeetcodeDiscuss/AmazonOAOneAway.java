package LeetcodeDiscuss;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
