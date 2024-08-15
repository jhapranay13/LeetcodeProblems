package leetcode.hard;

import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s and an integer k, rearrange s such that the same
 *         characters are at least distance k from each other. If it is not
 *         possible to rearrange the string, return an empty string "".
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "aabbcc", k = 3 Output: "abcabc" Explanation: The same
 *         letters are at least a distance of 3 from each other.
 * 
 *         Example 2:
 * 
 *         Input: s = "aaabc", k = 3 Output: "" Explanation: It is not possible
 *         to rearrange the string.
 * 
 *         Example 3:
 * 
 *         Input: s = "aaadbbcc", k = 2 Output: "abacabcd" Explanation: The same
 *         letters are at least a distance of 2 from each other.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 3 * 105 s consists of only lowercase English
 *         letters. 0 <= k <= s.length
 *
 */

public class _358_RearrangeStringKDistanceApart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String rearrangeString(String s, int k) {

		if (k == 0 || s.length() == 1) {
			return s;
		}
		Map<Character, Integer> freq = new HashMap<>();

		for (char ch : s.toCharArray()) {
			freq.put(ch, freq.getOrDefault(ch, 0) + 1);
		}

		if (freq.size() < k) {
			return "";
		}
		PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {

			public int compare(Character c1, Character c2) {
				int val1 = freq.get(c1);
				int val2 = freq.get(c2);

				if (val1 == val2) {
					return c1 - c2;
				}
				return val2 - val1;
			}
		});
		for (char ch : freq.keySet()) {
			pq.offer(ch);
		}
		StringBuilder ans = new StringBuilder();
		Deque<Character> holder = new LinkedList<>();
		int count = 0;

		while (!pq.isEmpty()) {
			char ch = pq.poll();
			int val = freq.get(ch);
			ans.append(ch);

			if (val == 1) {
				freq.remove(ch);
			} else {
				freq.put(ch, --val);
				holder.offer(ch);
			}
			count++;

			if (count == k) {

				while (!holder.isEmpty()) {
					char c = holder.poll();

					if (freq.containsKey(c)) {
						pq.offer(c);
					}
				}
				count = 0;
			}
		}

		if (!holder.isEmpty()) {
			return "";
		}
		return ans.toString();
	}
}
