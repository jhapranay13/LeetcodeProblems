package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Design a map that allows you to do the following:
 *
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 *
 * Example 1:
 *
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 *
 * Constraints:
 *
 * 1 <= key.length, prefix.length <= 50
 * key and prefix consist of only lowercase English letters.
 * 1 <= val <= 1000
 * At most 50 calls will be made to insert and sum.
 *
 */

public class _677_Map_Sum_Pairs {
    class MapSum {
        class Node {
            Map<Character, Node> children = new HashMap<>();
            int val = 0;
            boolean eow = false;
        }

        class Trie {
            Node root = new Node();
            Map<String, Integer> scoreMap = new HashMap<>();

            public void insert(String key, int val) {
                int delta = val - scoreMap.getOrDefault(key, 0);
                Node curr = root;
                scoreMap.put(key, val);

                for (char ch : key.toCharArray()) {
                    Node temp = curr.children.getOrDefault(ch, new Node());

                    if (!curr.children.containsKey(ch)) {
                        curr.children.put(ch, temp);
                    }
                    System.out.println(temp.val);
                    temp.val += delta;
                    curr = temp;
                }
                curr.eow = true;
            }

            public int get(String prefix) {
                Node cur = root;

                for (char c: prefix.toCharArray()) {
                    cur = cur.children.get(c);

                    if (cur == null)
                        return 0;
                }
                return cur.val;
            }
        }

        Trie trie;

        public MapSum() {
            trie = new Trie();
        }

        public void insert(String key, int val) {
            trie.insert(key, val);
        }

        public int sum(String prefix) {
            return trie.get(prefix);
        }
    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
}
