package leetcode.DP;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         From any string, we can form a subsequence of that string by deleting
 *         some number of characters (possibly no deletions).
 * 
 *         Given two strings source and target, return the minimum number of
 *         subsequences of source such that their concatenation equals target.
 *         If the task is impossible, return -1.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: source = "abc", target = "abcbc" Output: 2 Explanation: The
 *         target "abcbc" can be formed by "abc" and "bc", which are
 *         subsequences of source "abc". Example 2:
 * 
 *         Input: source = "abc", target = "acdbc" Output: -1 Explanation: The
 *         target string cannot be constructed from the subsequences of source
 *         string due to the character "d" in target string. Example 3:
 * 
 *         Input: source = "xyz", target = "xzyxz" Output: 3 Explanation: The
 *         target string can be constructed as follows "xz" + "y" + "xz".
 * 
 * 
 *         Constraints:
 * 
 *         Both the source and target strings consist of only lowercase English
 *         letters from "a"-"z". The lengths of source and target string are
 *         between 1 and 1000.
 *
 */

public class _1055_ShortestWayToFormString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ==============================================================================
	// Binary Search Approach
	public int shortestWay(String source, String target) {
		Map<Character, List<Integer>> sourceOrderMap = new HashMap<>();
		int order = 0;

		for (char ch : source.toCharArray()) {
			List<Integer> orderList = sourceOrderMap.getOrDefault(ch, new ArrayList<>());
			orderList.add(order++);
			sourceOrderMap.put(ch, orderList);
		}
		int prevIndex = -1;
		int ans = 1;

		for (int i = 0; i < target.length(); i++) {
			char ch = target.charAt(i);
			List<Integer> orderList = sourceOrderMap.get(ch);

			if (orderList == null) {
				return -1;
			}
			int index = binarySearchGreaterThan(orderList, prevIndex);

			if (index == -1 || orderList.get(index) <= prevIndex) {
				ans++;
			}
			prevIndex = index == -1 ? orderList.get(0) : orderList.get(index);
		}
		return ans;
	}

	// BinarySerach Just Greater Than Simpler version
	private int binarySearchGreaterThan(List<Integer> orderList, int prevOrder) {
		int lo = 0;
		int hi = orderList.size() - 1;
		int ans = -1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (orderList.get(pivot) <= prevOrder) {
				lo = pivot + 1;
			} else {
				ans = pivot;
				hi = pivot - 1;
			}
		}
		return ans;
	}

	// ==============================================================================
	// Greedy Approach
	public int shortestWay1(String source, String target) {
		char[] s = source.toCharArray();
		char[] t = target.toCharArray();
		int sIndex = 0;
		boolean match = false;
		int counter = 0;

		for (int i = 0; i < t.length; i++) {
			char sCh = s[sIndex];
			char tCh = t[i];
			match = false;

			if (tCh == sCh) {
				match = true;

				if (sIndex == s.length - 1) {
					sIndex = 0;
					counter++;
				} else {
					sIndex++;
				}
			} else {
				int j = sIndex;

				for (; j < s.length; j++) {
					sCh = s[j];

					if (tCh == sCh) {

						if (j == s.length - 1) {
							sIndex = 0;
							counter++;
						} else {
							sIndex = j + 1;
						}
						match = true;
						break;
					}
				}

				if (!match && sIndex > 0) {
					j = 0;

					for (; j <= sIndex; j++) {
						sCh = s[j];

						if (tCh == sCh) {

							if (j == s.length - 1) {
								sIndex = 0;
								counter++;
							} else {
								sIndex = j + 1;
								counter++;
							}
							match = true;
							break;
						}
					}
				}

				if (!match) {
					return -1;
				}
			}
		}
		return match && sIndex > 0 ? ++counter : counter;
	}

	// ============================================================================
	// Recursive Top down
	public int shortestWay2(String source, String target) {
		Set<Character> charSet = new HashSet<>();

		for (char ch : source.toCharArray()) {
			charSet.add(ch);
		}
		int[][] memo = new int[source.length()][target.length()];
		int ans = recur(charSet, source, target, 0, 0, memo);
		return ans;
	}

	public int recur(Set<Character> charSet, String source, String target, int sIndex,
					 int tIndex, int[][] memo) {

		if (tIndex == target.length()) {
			return 1;
		}
		int ans = 0;

		if (sIndex == source.length()) {
			ans = 1;
			sIndex = 0;
		}

		if (memo[sIndex][tIndex] > 0) {
			return memo[sIndex][tIndex];
		}
		char tch = target.charAt(tIndex);

		if (!charSet.contains(tch)) {
			return -1;
		}
		char sch = source.charAt(sIndex);
		int temp = 0;

		if (tch == sch) {
			temp = recur(charSet, source, target, sIndex + 1, tIndex + 1, memo);
		} else {
			temp = recur(charSet, source, target, sIndex + 1, tIndex, memo);
		}

		if (temp == -1) {
			ans = -1;
		} else {
			ans += temp;
		}
		return memo[sIndex][tIndex] = ans;
	}
	//=============================================================================================
}
