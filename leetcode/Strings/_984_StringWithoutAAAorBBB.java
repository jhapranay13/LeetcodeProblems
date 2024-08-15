package leetcode.Strings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two integers a and b, return any string s such that:
 * 
 *         s has length a + b and contains exactly a 'a' letters, and exactly b
 *         'b' letters, The substring 'aaa' does not occur in s, and The
 *         substring 'bbb' does not occur in s.
 * 
 *         Example 1:
 * 
 *         Input: a = 1, b = 2 Output: "abb" Explanation: "abb", "bab" and "bba"
 *         are all correct answers.
 * 
 *         Example 2:
 * 
 *         Input: a = 4, b = 1 Output: "aabaa"
 * 
 *         Constraints:
 * 
 *         0 <= a, b <= 100 It is guaranteed such an s exists for the given a
 *         and b.
 *
 */

public class _984_StringWithoutAAAorBBB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String strWithout3a3b(int A, int B) {
		StringBuilder ans = new StringBuilder();

		while (A > 0 || B > 0) {
			boolean flag = false;
			int len = ans.length();

			if (len >= 2 && ans.charAt(len - 1) == ans.charAt(len - 2)) {

				if (ans.charAt(len - 1) == 'b') {
					flag = true;
				}
			} else if (A >= B) {
				flag = true;
			}

			if (flag && A > 0) {
				A--;
				ans.append('a');
			} else if (B > 0) {
				B--;
				ans.append('b');
			}
		}
		return ans.toString();
	}

	// =============================================================================
	// Priority Queue Approach
	public String strWithout3a3b1(int a, int b) {
		Map<Character, Integer> freqMap = new HashMap<>();
		freqMap.put('a', a);
		freqMap.put('b', b);
		PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
			public int compare(Character ch1, Character ch2) {
				return freqMap.get(ch2) - freqMap.get(ch1);
			}
		});
		pq.offer('a');
		pq.offer('b');
		Set<Character> holder = new HashSet<>();
		StringBuilder ans = new StringBuilder();

		while (!pq.isEmpty()) {
			char ch = pq.poll();
			int freq = freqMap.get(ch);

			if (freq > 0) {
				if (ans.length() >= 2 && ans.charAt(ans.length() - 1) == ans.charAt(ans.length() - 2)
						&& ans.charAt(ans.length() - 1) == ch) {
					holder.add(ch);
					ch = pq.poll();
					freq = freqMap.get(ch);
				}
				int count = 0;
				ans.append(ch);
				freq--;
				freqMap.put(ch, freq);

				if (freq > 0) {
					holder.add(ch);
				}

				if (!holder.isEmpty()) {
					pq.addAll(holder);
					holder.clear();
				}

			}
		}
		return ans.toString();
	}
}
