package leetcode.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, consider all duplicated substrings: (contiguous)
 *         substrings of s that occur 2 or more times. The occurrences may
 *         overlap.
 * 
 *         Return any duplicated substring that has the longest possible length.
 *         If s does not have a duplicated substring, the answer is "".
 * 
 *         Example 1:
 * 
 *         Input: s = "banana" Output: "ana"
 * 
 *         Example 2:
 * 
 *         Input: s = "abcd" Output: ""
 * 
 * 
 *         Constraints:
 * 
 *         2 <= s.length <= 3 * 104 s consists of lowercase English letters.
 *
 */

public class _1044_LongestDuplicateSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===============================================================================
	// Rabin Karp Rolling hash Implementation, can also use the array based implementation as in 1062
	public String longestDupSubstring(String s) {
		String ans = binarySearch(s);
		return ans;
	}

	private String binarySearch(String s) {
		StringBuilder ans = new StringBuilder();
		int lo = 0;
		int hi = s.length() - 1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (check(s, pivot, ans)) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return ans.length() == 0 ? "" : ans.toString();
	}

	private boolean check(String s, int length, StringBuilder ans) {
		long hash = s.charAt(0);
		int prime = 131;
		long power = 1;

		for (int i = 1; i < length; i++) {
			hash *= prime;
			hash += s.charAt(i);
			power *= prime;
		}
		Set<Long> visited = new HashSet<>();
		visited.add(hash);

		for (int i = length; i < s.length(); i++) {
			int prevIndex = i - length;
			int currHigh = i;
			hash -= s.charAt(prevIndex) * power;
			hash *= prime;
			hash += s.charAt(currHigh);

			if (!visited.add(hash)) {
				ans.delete(0, ans.length());
				ans.append(s.substring(i - length + 1, i + 1));
				return true;
			}
		}
		return false;
	}
	// ===============================================================================
	// Sub array prefix. But gives TLE.
	public String longestDupSubstring2(String s) {

		if (s == null || s.length() == 0) {
			return s;
		}
		List<String> prefixList = new ArrayList<>();

		for (int i = 0; i < s.length(); i++) {
			prefixList.add(s.substring(i));
		}
		Collections.sort(prefixList);
		String returnVal = "";

		for (int i = 1; i < prefixList.size(); i++) {
			String str1 = prefixList.get(i);
			String str2 = prefixList.get(i - 1);

			String temp = getCommonPrefix(str1, str2);

			if (temp.length() > returnVal.length()) {
				returnVal = temp;
			}
		}
		return returnVal;
	}

	public String getCommonPrefix(String str1, String str2) {
		int length1 = str1.length();
		int length2 = str2.length();

		int start1 = 0;
		int start2 = 0;
		StringBuilder res = new StringBuilder();

		while (start1 < length1 && start2 < length2) {

			if (str1.charAt(start1) == str2.charAt(start2)) {
				res.append(str1.charAt(start1));
			} else {
				break;
			}
			start1++;
			start2++;
		}
		return res.toString();
	}

}
