package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings str1 and str2 of the same length, determine whether
 *         you can transform str1 into str2 by doing zero or more conversions.
 * 
 *         In one conversion you can convert all occurrences of one character in
 *         str1 to any other lowercase English character.
 * 
 *         Return true if and only if you can transform str1 into str2.
 * 
 *         Example 1:
 * 
 *         Input: str1 = "aabcc", str2 = "ccdee" Output: true Explanation:
 *         Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the
 *         order of conversions matter.
 * 
 *         Example 2:
 * 
 *         Input: str1 = "leetcode", str2 = "codeleet" Output: false
 *         Explanation: There is no way to transform str1 to str2.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= str1.length == str2.length <= 104 str1 and str2 contain only
 *         lowercase English letters.
 *
 */

public class _1153_StringTransformIntoAnotheString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean canConvert(String str1, String str2) {

		if (str1 == null && str2 == null) {
			return true;
		}

		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}

		if (str1.equals(str2)) {
			return true;
		}
		Map<Character, Character> cmap = new HashMap<>();

		for (int i = 0; i < str1.length(); i++) {

			if (cmap.containsKey(str1.charAt(i)) && cmap.get(str1.charAt(i)) != str2.charAt(i)) {
				return false;
			}
			cmap.put(str1.charAt(i), str2.charAt(i));
		}
		Set<Character> set = new HashSet<>();
		set.addAll(cmap.values());
		return set.size() != 26;
	}
}
