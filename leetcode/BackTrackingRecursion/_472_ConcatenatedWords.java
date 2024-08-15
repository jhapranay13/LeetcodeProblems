package leetcode.BackTrackingRecursion;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of strings words (without duplicates), return all the
 *         concatenated words in the given list of words.
 * 
 *         A concatenated word is defined as a string that is comprised entirely
 *         of at least two shorter words in the given array.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: words =
 *         ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *         Output: ["catsdogcats","dogcatsdog","ratcatdogcat"] Explanation:
 *         "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *         "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 *         "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *        
 *         
 *         Example 2:
 * 
 *         Input: words = ["cat","dog","catdog"] Output: ["catdog"]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= words.length <= 104 0 <= words[i].length <= 1000 words[i]
 *         consists of only lowercase English letters. 0 <= sum(words[i].length)
 *         <= 105
 *
 */

public class _472_ConcatenatedWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {

		if (words == null || words.length == 0) {
			return new ArrayList<String>();
		}
		Set<String> wordSet = new HashSet<>();

		for (int i = 0; i < words.length; i++) {

			if (words[i].equals(""))
				continue;
			wordSet.add(words[i]);
		}

		List<String> result = new ArrayList<>();

		for (String str : wordSet) {

			if (dfs(str, wordSet, 0)) {
				result.add(str);
			}
		}

		return result;
	}

	public boolean dfs(String str, Set<String> wordSet, int counter) {

		if (wordSet.contains(str) && counter > 0) {
			return true;
		}

		for (int i = 1; i <= str.length(); i++) {
			String s = str.substring(0, i);

			if (wordSet.contains(s)) {
				boolean returnVal = dfs(str.substring(i), wordSet, counter + 1);

				if (returnVal) {
					return true;
				}
			}
		}
		return false;
	}
	//=============================================================================================
	//Using Tries based on hashMap
	class NodeHashMap {
		boolean eow = false;
		Map<Character, NodeHashMap> children = new HashMap<>();
	}
	private NodeHashMap root = new NodeHashMap();

	public List<String> findAllConcatenatedWordsInADict1(String[] words) {
		Arrays.sort(words, new Comparator<>() {
			public int compare(String x, String y) {
				return x.length() - y.length();
			}
		});
		int len = words[0].length();
		List<String> ans = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {

			if (words[i].trim().equals("")) {
				continue;
			}
			//Hashset used to reduce the depth of recursion
			if (isConcatenated(words[i], root, 0, new HashSet<Integer>())) {
				ans.add(words[i]);
			}
			insert(words[i]);
		}
		return ans;
	}

	private boolean isConcatenated(String word, NodeHashMap NodeHashMap, int index, Set<Integer> v) {

		if (index == word.length()) {
			return true;
		}

		if (v.contains(index)) {
			return false;
		}

		for (int i = index; i < word.length(); i++) {
			char ch = word.charAt(i);

			if(!NodeHashMap.children.containsKey(ch)) {
				return false;
			}
			NodeHashMap temp = NodeHashMap.children.get(ch);

			if (temp.eow && isConcatenated(word, root, i + 1, v)) {
				return true;
			}
			NodeHashMap = temp;
		}
		v.add(index);
		return false;
	}

	private void insert(String word) {
		NodeHashMap curr = root;

		for (char ch : word.toCharArray()) {
			NodeHashMap nodeHashMap = null;

			if (curr.children.containsKey(ch)) {
				nodeHashMap = curr.children.get(ch);
			} else {
				nodeHashMap = curr.children.getOrDefault(ch, new NodeHashMap());
				curr.children.put(ch, nodeHashMap);
			}
			curr = nodeHashMap;
		}
		curr.eow = true;
	}
	//=============================================================================================
	//Tries using Arrays
	class Node {
		boolean eow = false;
		Node[] children = new Node[26];
	}
	private Node root1 = new Node();

	public List<String> findAllConcatenatedWordsInADict2(String[] words) {
		Arrays.sort(words, new Comparator<>() {
			public int compare(String x, String y) {
				return x.length() - y.length();
			}
		});
		List<String> ans = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {

			if (words[i].length() == 0) {
				continue;
			}
			//Hashset used to reduce the depth of recursion
			if (isConcatenated(words[i].toCharArray(), root1, 0, new HashSet<Integer>())) {
				ans.add(words[i]);
			}
			insert(words[i].toCharArray());
		}
		return ans;
	}

	private boolean isConcatenated(char[] word, Node curr, int index, Set<Integer> v) {

		if (index == word.length) {
			return true;
		}

		if (v.contains(index)) {
			return false;
		}

		for (int i = index; i < word.length; i++) {
			char ch = word[i];

			if(curr.children[ch - 'a'] == null) {
				break;
			}
			Node temp = curr.children[ch - 'a'];

			if (temp.eow && isConcatenated(word, root1, i + 1, v)) {
				return true;
			}
			curr = temp;
		}
		v.add(index);
		return false;
	}

	private void insert(char[] word) {
		Node curr = root1;

		for (int i = 0; i < word.length; i++) {
			char ch = word[i];
			Node node = null;

			if (curr.children[ch - 'a'] == null) {
				node = new Node();
				curr.children[ch - 'a'] = node;
			}
			curr = curr.children[ch - 'a'];
		}
		curr.eow = true;
	}
}
