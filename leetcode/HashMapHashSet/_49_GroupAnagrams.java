package leetcode.HashMapHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *
 *Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:

Input: strs = [""]
Output: [[""]]

Example 3:

Input: strs = ["a"]
Output: [["a"]]

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lower-case English letters.
 *
 */

public class _49_GroupAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> ans = new ArrayList<>();

		if (strs == null || strs.length == 0) {
			return ans;
		}
		Map<String, List<String>> holder = new HashMap<>();

		for (String str : strs) {
			char[] cArr = str.toCharArray(); 
			Arrays.sort(cArr);
			String code = Arrays.toString(cArr);
			List<String> list = holder.getOrDefault(code, new ArrayList<String>());
			list.add(str);
			holder.put(code, list);
		}
		Set<String> keys = holder.keySet();

		for (String key : keys) {
			ans.add(holder.get(key));
		}
		return ans;
	}
}
