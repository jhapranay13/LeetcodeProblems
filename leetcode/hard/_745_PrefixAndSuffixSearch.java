package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 *
 *
 * Example 1:
 *
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 *
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 15000
 * 1 <= words[i].length <= 10
 * 1 <= prefix.length, suffix.length <= 10
 * words[i], prefix and suffix consist of lower-case English letters only.
 * At most 15000 calls will be made to the function f.
 *
 */
public class _745_PrefixAndSuffixSearch {
    //The weight is just a number and not a set coz endings are mostly going to be different for different words
    //index as a set can also be used but may result in TLE
    //Two tries can be used to store prefix and suffix in reverse
    //but gives a TLE
    class WordFilter {

        TrieNode root=new TrieNode();

        public WordFilter(String[] words) {
            for(int i=0;i<words.length;i++)
            {
                String s=words[i];
                for(int j=0;j<s.length();j++)
                {
                    insert(s.substring(j,s.length())+'{'+s,i);
                }
            }
        }
        public void insert(String wd,int weight)
        {
            TrieNode node=root;
            for(char ch: wd.toCharArray())
            {
                if(node.children[ch-'a']==null)
                {
                    node.children[ch-'a']=new TrieNode();
                }
                node=node.children[ch-'a'];
                node.weight=weight;
            }

        }
        public int f(String prefix, String suffix) {
            return startsWith(suffix+'{'+prefix);

        }
        public int startsWith(String pref)
        {
            TrieNode node=root;
            for(char ch: pref.toCharArray()){
                if(node.children[ch-'a']==null)
                {
                    return -1;
                }
                node=node.children[ch-'a'];
            }
            return node.weight;
        }
    }
    class TrieNode
    {
        public TrieNode[] children;
        public int weight;
        TrieNode()
        {
            this.children=new TrieNode[27];
            this.weight=0;
        }
    }
    //=============================================================================================
    //HashMap approach with trie
    class Node {
        Map<Character, Node> cache = new HashMap<>();
        boolean eow = true;
        int index = -1;
    }
    class WordFilter1 {
        Node wordTrie;

        public WordFilter1(String[] words) {
            this.wordTrie = new Node();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                for (int j = 0; j < word.length(); j++) {
                    String suffix = word.substring(j);
                    insert(suffix + "{" + word, i);
                }
            }
        }

        public void insert(String word, int index) {
            Node curr = wordTrie;
            curr.index = index;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Node node = curr.cache.get(ch);

                if (node == null) {
                    node = new Node();
                    curr.cache.put(ch, node);
                }
                curr = node;
                curr.index = index;
            }
            curr.eow = true;
        }

        public int f(String prefix, String suffix) {
            Node curr1 = wordTrie;
            String word = suffix + "{" + prefix;

            for (int i = 0; i < word.length(); i++) {
                if (!curr1.cache.containsKey(word.charAt(i))) {
                    return -1;
                }
                curr1 = curr1.cache.get(word.charAt(i));
            }

            return curr1.index;
        }
    }

}
