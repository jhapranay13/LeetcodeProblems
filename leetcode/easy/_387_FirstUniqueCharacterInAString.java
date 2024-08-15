package leetcode.easy;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, return the first non-repeating character in it and
 *         return its index. If it does not exist, return -1.
 * 
 *         Example 1:
 * 
 *         Input: s = "leetcode" Output: 0 
 *         
 *         Example 2:
 * 
 *         Input: s = "loveleetcode" Output: 2 
 *         
 *         Example 3:
 * 
 *         Input: s = "aabb" Output: -1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 105 s consists of only lowercase English letters.
 *
 */

public class _387_FirstUniqueCharacterInAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int firstUniqChar(String s) {
		Map<Character, Integer> charPosMap = new LinkedHashMap<>();
		Set<Character> duplicate = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (!duplicate.contains(ch)) {

				if (charPosMap.containsKey(ch)) {
					charPosMap.remove(ch);
					duplicate.add(ch);
				} else {
					charPosMap.put(ch, i);
				}
			}
		}

		for (Map.Entry<Character,Integer> entry : charPosMap.entrySet()) {
			return entry.getValue();
		}
		return -1;
	}
}
