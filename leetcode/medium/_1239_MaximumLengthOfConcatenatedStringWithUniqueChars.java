package leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of strings arr. String s is a concatenation of a
 *         sub-sequence of arr which have unique characters.
 * 
 *         Return the maximum possible length of s.
 * 
 *         Example 1:
 * 
 *         Input: arr = ["un","iq","ue"] Output: 4 Explanation: All possible
 *         concatenations are "","un","iq","ue","uniq" and "ique". Maximum
 *         length is 4. 
 *         
 *         Example 2:
 * 
 *         Input: arr = ["cha","r","act","ers"] Output: 6 Explanation: Possible
 *         solutions are "chaers" and "acters". 
 *         
 *         Example 3:
 * 
 *         Input: arr = ["abcdefghijklmnopqrstuvwxyz"] Output: 26
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 16 1 <= arr[i].length <= 26 arr[i] contains only
 *         lower case English letters.
 *
 */

public class _1239_MaximumLengthOfConcatenatedStringWithUniqueChars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxLength(List<String> arr) {
		recur(arr, "", 0);
		return max;
	}

	private int max = 0;

	private void recur(List<String> arr, String s, int index) {
		if (isUnique(s)) {
			max = Math.max(max, s.length());
		} else {
			return;
		}

		for (int i = index; i < arr.size(); i++) {
			recur(arr, s + arr.get(i), i + 1);
		}
	}

	private boolean isUnique(String s) {
		Set<Character> set = new HashSet<>();

		for (char ch : s.toCharArray()) {
			if (!set.add(ch)) {
				return false;
			}
		}
		return true;
	}
}
