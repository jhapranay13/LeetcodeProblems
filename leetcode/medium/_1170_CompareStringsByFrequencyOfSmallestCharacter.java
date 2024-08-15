package leetcode.medium;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Let the function f(s) be the frequency of the lexicographically
 *         smallest character in a non-empty string s. For example, if s =
 *         "dcce" then f(s) = 2 because the lexicographically smallest character
 *         is 'c', which has a frequency of 2.
 * 
 *         You are given an array of strings words and another array of query
 *         strings queries. For each query queries[i], count the number of words
 *         in words such that f(queries[i]) < f(W) for each W in words.
 * 
 *         Return an integer array answer, where each answer[i] is the answer to
 *         the ith query.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: queries = ["cbd"], words = ["zaaaz"] Output: [1] Explanation:
 *         On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") <
 *         f("zaaaz"). 
 *         
 *         Example 2:
 * 
 *         Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 *         Output: [1,2] Explanation: On the first query only f("bbb") <
 *         f("aaaa"). On the second query both f("aaa") and f("aaaa") are both >
 *         f("cc").
 * 
 * 
 *         Constraints:
 * 
 *         1 <= queries.length <= 2000 1 <= words.length <= 2000 1 <=
 *         queries[i].length, words[i].length <= 10 queries[i][j], words[i][j]
 *         consist of lowercase English letters.
 *
 */

public class _1170_CompareStringsByFrequencyOfSmallestCharacter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] maxFreqMinChar = new int[words.length];

		for (int i = 0; i < words.length; i++) {
			maxFreqMinChar[i] = getMaxFreqMinChar(words[i]);
		}
		Arrays.sort(maxFreqMinChar);
		int[] ans = new int[queries.length];
		int pos = 0;

		for (String word : queries) {
			int freq = getMaxFreqMinChar(word);
			int index = binarySearchJustGreaterThan(freq, maxFreqMinChar);

			if (index == 0) {
				ans[pos++] = maxFreqMinChar.length;
			} else if (index == -1) {
				ans[pos++] = 0;
			} else {
				ans[pos++] = maxFreqMinChar.length - index;
			}

		}
		return ans;
	}

	private int binarySearchJustGreaterThan(int target, int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;
		int ans = -1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (arr[pivot] > target) {
				ans = pivot;
				hi = pivot - 1;
			} else {
				lo = pivot + 1;
			}
		}
		return ans;
	}

	private int getMaxFreqMinChar(String str) {
		int ans = 0;
		int minCh = Integer.MAX_VALUE;

		for (int i = 0; i < str.length(); i++) {
			int ch = str.charAt(i) - 'a';

			if (minCh == Integer.MAX_VALUE || minCh > ch) {
				minCh = ch;
				ans = 1;
			} else {
				if (minCh == ch) {
					ans++;
				}
			}
		}
		return ans;
	}

}
