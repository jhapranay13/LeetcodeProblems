package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given string num representing a non-negative integer num, and an
 *         integer k, return the smallest possible integer after removing k
 *         digits from num.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the
 *         three digits 4, 3, and 2 to form the new number 1219 which is the
 *         smallest.
 * 
 *         Example 2:
 * 
 *         Input: num = "10200", k = 1 Output: "200" Explanation: Remove the
 *         leading 1 and the number is 200. Note that the output must not
 *         contain leading zeroes.
 * 
 *         Example 3:
 * 
 *         Input: num = "10", k = 2 Output: "0" Explanation: Remove all the
 *         digits from the number and it is left with nothing which is 0.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= k <= num.length <= 105 num consists of only digits. num does not
 *         have any leading zeros except for the zero itself.
 *
 */
public class _402_RemoveKDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Monotonic Queue
	public String removeKdigits(String num, int k) {
		Deque<Character> queue = new LinkedList<>();

		for (char n : num.toCharArray()) {

			while (!queue.isEmpty() && queue.peekLast() > n && k > 0) {
				queue.pollLast();
				k--;
			}
			queue.offerLast(n);
		}

		while (k > 0) {
			queue.pollLast();
			k--;
		}

		while (!queue.isEmpty() && queue.peekFirst() == '0') {
			queue.pollFirst();
		}
		StringBuilder ans = new StringBuilder();

		while (!queue.isEmpty()) {
			ans.append(queue.pollFirst());
		}
		return ans.length() == 0 ? "0" : ans.toString();
	}
}