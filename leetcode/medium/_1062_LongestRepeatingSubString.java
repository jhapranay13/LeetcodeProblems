package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s, find out the length of the longest repeating
 *         substring(s). Return 0 if no repeating substring exists.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "abcd" Output: 0 Explanation: There is no repeating
 *         substring.
 * 
 * 
 *         Example 2:
 * 
 *         Input: s = "abbaba" Output: 2 Explanation: The longest repeating
 *         substrings are "ab" and "ba", each of which occurs twice.
 * 
 *         Example 3:
 * 
 *         Input: s = "aabcaabdaab" Output: 3 Explanation: The longest repeating
 *         substring is "aab", which occurs 3 times.
 * 
 *         Example 4:
 * 
 *         Input: s = "aaaaa" Output: 4 Explanation: The longest repeating
 *         substring is "aaaa", which occurs twice.
 * 
 * 
 *         Constraints:
 * 
 *         The string s consists of only lowercase English letters from 'a' -
 *         'z'. 1 <= s.length <= 1500
 *
 */

public class _1062_LongestRepeatingSubString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ===============================================================================
	// Using Rabin Karp Rolling Hash
	public int longestRepeatingSubstring(String s) {
		long[] power = new long[s.length()];
		long[] hash = new long[s.length()];
		int prime = 131;
		initPowerHash(s, power, hash, prime);
		int ans = binarySearch(s, power, hash, prime);
		return ans;
	}

	private long mod = Long.MAX_VALUE - 1;

	private int binarySearch(String s, long[] power, long[] hash, int prime) {
		int lo = 0;
		int hi = s.length() - 1;
		int ans = 0;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (pivot == 0) {
				break;
			}
			if (check(s, power, hash, prime, pivot)) {
				ans = pivot;
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return ans;
	}

	// Rabin Karp Rolling Hash
	private boolean check(String s, long[] power, long[] hash, int prime, int length) {
		long powerVal = power[length - 1];
		long hashVal = hash[length - 1];
		Set<Long> visited = new HashSet<>();
		visited.add(hashVal);

		for (int i = length; i < s.length(); i++) {
			int lo = i - length;
			int hi = i;
			hashVal -= s.charAt(lo) * powerVal;
			hashVal *= prime;
			hashVal += (s.charAt(hi));

			if (!visited.add(hashVal)) {
				return true;
			}
		}
		return false;
	}

	// Using Rabin Karp
	private void initPowerHash(String s, long[] power, long[] hash, int prime) {
		power[0] = 1;
		hash[0] = s.charAt(0);

		for (int i = 1; i < s.length(); i++) {
			power[i] = ((power[i - 1]) * prime);
			hash[i] = (hash[i - 1] * prime + s.charAt(i));
		}
	}

	// ==============================================================================
	// Another Way Of using Rabin Karp
	public int longestRepeatingSubstring1(String S) {
		char[] arr = S.toCharArray();

		int lo = 0;
		int hi = S.length() - 1;
		int ans = 0;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			boolean result = search(arr, mid);

			if (result) {
				lo = mid + 1;
				ans = mid;
			} else {
				hi = mid - 1;
			}
		}

		return ans;
	}

	private boolean search(char[] arr, int l) {
		Set<Long> set = new HashSet<>();
		int base = 31;
		long power = 1;
		// Can use the mode will work just fine without it too
		// long mod = Long.MAX_VALUE - 1;

		for (int i = 0; i < l - 1; i++) {
			power = power * base;
		}
		long hash = 0;

		for (int i = 0; i < l; i++) {
			hash = ((arr[i] - 'a') + hash * base);
		}
		set.add(hash);

		for (int i = l; i < arr.length; i++) {
			hash = ((hash - ((arr[i - l] - 'a') * power)) * base + (arr[i] - 'a'));
			if (!set.add(hash))
				return true;
		}

		return false;
	}

	// ==============================================================================
	// Without using Rabin Karp. Can use HashCode of substring too
	public int longestRepeatingSubstring2(String s) {
		int ans = binarySearch(s);
		return ans;
	}

	private int binarySearch(String s) {
		int lo = 0;
		int hi = s.length();
		int ans = 0;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (check(s, pivot)) {
				lo = pivot + 1;
				ans = pivot;
			} else {
				hi = pivot - 1;
			}
		}
		return ans;
	}

	private boolean check(String s, int length) {
		Set<String> visited = new HashSet<>();

		for (int i = 0; i + length <= s.length(); i++) {
			if (!visited.add(s.substring(i, i + length))) {
				return true;
			}
		}
		return false;
	}
}
