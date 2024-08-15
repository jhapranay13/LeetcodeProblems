package leetcode.Tries;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 *
 */

public class _208_ImplementTriePrefixTree {

	public static void main(String[] args) {
		_208_ImplementTriePrefixTree obj = new _208_ImplementTriePrefixTree();
		Trie t = obj.new Trie();
		t.insert("apple");
	}

	class Node {
		Map<Character, Node> adjacencyList = new HashMap<>();
		boolean eow = false;
	}
	class Trie {
		Node root;

		/** Initialize your data structure here. */
		public Trie() {
			this.root = new Node();
		}

		/** Inserts a word into the trie. */
		public void insert(String word) {
			Node curr = root;

			for (int i = 0; i < word.length(); i++) {
				Map<Character, Node> adj = curr.adjacencyList;

				if (adj.containsKey(word.charAt(i))) {
					curr = adj.get(word.charAt(i));
				} else {
					Node node = new Node();
					curr.adjacencyList.put(word.charAt(i), node);
					curr = node;
				}
			}
			curr.eow = true;
		}

		/** Returns if the word is in the trie. */
		public boolean search(String word) {
			Node curr = root;

			for (int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);

				if (curr.adjacencyList.containsKey(ch)) {
					curr = curr.adjacencyList.get(ch);
				} else {
					return false;
				}
			}
			return curr.eow;
		}

		/** Returns if there is any word in the trie that starts with the given prefix. */
		public boolean startsWith(String prefix) {
			Node curr = root;

			for (int i = 0; i < prefix.length(); i++) {
				char ch = prefix.charAt(i);

				if (curr.adjacencyList.containsKey(ch)) {
					curr = curr.adjacencyList.get(ch);
				} else {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * Your Trie object will be instantiated and called as such:
	 * Trie obj = new Trie();
	 * obj.insert(word);
	 * boolean param_2 = obj.search(word);
	 * boolean param_3 = obj.startsWith(prefix);
	 */
}
