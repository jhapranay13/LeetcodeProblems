package leetcode.Tries;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *
 *Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 

Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.
 *
 */

public class _14_LongetsCommonPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_14_LongetsCommonPrefix obj = new _14_LongetsCommonPrefix();
		String arr[] = {"","b"};
		System.out.println(obj.longestCommonPrefix(arr));
	}

	public String longestCommonPrefix1(String[] strs) {

		if (strs == null || strs.length == 0) {
			return "";
		}
		int minLength = Integer.MAX_VALUE;

		for (String str : strs) {
			minLength = Math.min(minLength, str.length());
		}
		int lo = 0;
		int hi = minLength - 1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (isCommon(strs, 0, pivot)) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return strs[0].substring(0, (hi + lo + 1) / 2);
	}

	private boolean isCommon(String[] strs, int lo, int hi) {
		String commonStr = strs[0].substring(lo, hi + 1);

		for (String str : strs) {

			if (!str.startsWith(commonStr)) {
				return false;
			}
		}
		return true;
	}

//==========================================================================================

	public String longestCommonPrefix(String[] strs) {

		if (strs == null || strs.length == 0) {
			return "";
		}
		Trie obj = new Trie();

		for (String str : strs) {
			obj.insert(str);
		}
		return obj.getCommonString();
	}
}

class TrieNode {
	Map<Character, TrieNode> cache = new HashMap<>();
	boolean endOfWord = false;
}

class Trie{
	TrieNode node;

	public Trie() {
		this.node = new TrieNode();
	}

	public void insert(String s) {
		TrieNode currNode = node;

		for (char ch : s.toCharArray()) {
			Map<Character, TrieNode> currCache = currNode.cache;

			if(currCache.get(ch) == null) {
				currCache.put(ch, new TrieNode());
			} 
			currNode = currCache.get(ch);
		}
		currNode.endOfWord = true;
	}

	public String getCommonString() {
		TrieNode currNode = node;
		StringBuilder ans  = new StringBuilder();

		while (!currNode.endOfWord) {
			Map<Character, TrieNode> cache = currNode.cache;

			if (cache.size() == 1) {
				Set<Character> keys = cache.keySet();

				for (Character key : keys) {
					ans.append(key);
					currNode = cache.get(key);
				}
			} else {
				break;
			}
		}
		return ans.toString();
	}
}