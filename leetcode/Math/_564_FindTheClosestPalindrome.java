package leetcode.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string n representing an integer, return the closest integer
 *         (not including itself), which is a palindrome. If there is a tie,
 *         return the smaller one.
 * 
 *         The closest is defined as the absolute difference minimized between
 *         two integers.
 * 
 *         Example 1:
 * 
 *         Input: n = "123" Output: "121" 
 *         
 *         Example 2:
 * 
 *         Input: n = "1" Output: "0" Explanation: 0 and 2 are the closest
 *         palindromes but we return the smallest which is 0.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n.length <= 18 n consists of only digits. n does not have
 *         leading zeros. n is representing an integer in the range [1, 1018 -
 *         1].
 *
 */
/**
 * 
 * @author Pranay Jha
 *
 *         Compare 5 candidates for any number and then check the difference
 *         between the candidates and the given number. The minimum difference
 *         candiadate is the one we need to return. If there are two candidates
 *         with the same difference, return the smaller candidate. We take half
 *         of the given number to generate possible palindrome if even i.e
 *         123456 -> we use 123 as the half to generate three combinations(half,
 *         half+1, half-1) i. 123-321 ii. 124-421 iii. 122-221
 * 
 *         if odd i.e 12345 -> we use 123 as the half to generate three
 *         combinations(half, half+1, half-1) i. 12-3-21 ii. 12-4-21 iii.
 *         12-2-21
 * 
 *         two more candidates are i. 10^(len-1)-1 for 5 digit number, it is the
 *         smallest possible palindrome i.e.9999 ii. 10^(len+1)+1 for 5 digit
 *         number, it is the largest possible palindrome i.e. 100001
 *
 */

public class _564_FindTheClosestPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String nearestPalindromic(String n) {
		int length = n.length();
		boolean even = (length % 2 == 0);
		long half = Long.parseLong(n.substring(0, even ? length / 2 : length / 2 + 1));
		List<Long> candidates = new ArrayList<>();
		getPalindromeCandidate(half, even, candidates);
		getPalindromeCandidate(half - 1, even, candidates);
		getPalindromeCandidate(half + 1, even, candidates);
		candidates.add((long) Math.pow(10, length - 1) - 1); // For 99...9 etc.
		candidates.add((long) Math.pow(10, length) + 1); // For 10..01 etc;
		System.out.println(candidates);
		long closest = Long.MAX_VALUE;
		long ans = 0;
		long nVal = Long.parseLong(n);

		for (long num : candidates) {

			if (num == nVal) {
				continue;
			} else if (Math.abs(num - nVal) < closest) {
				closest = Math.abs(num - nVal);
				ans = num;
			} else if (Math.abs(num - nVal) == closest) {
				ans = Math.min(ans, num);
			}
		}
		return String.valueOf(ans);
	}

	private void getPalindromeCandidate(long half, boolean even, List<Long> candidates) {
		long ans = half;

		if (!even) {
			half = half / 10; // if odd loose the last digit;
		}

		while (half != 0) {
			ans *= 10;
			ans += half % 10;
			half = half / 10;
		}
		candidates.add(ans);
	}

	//=============================================================================================
	//Another Approach
	public String nearestPalindromic1(String n) {
		long num = Long.parseLong(n);
		long small = getSmall(String.valueOf(num - 1).toCharArray());
		long large = getLarge(String.valueOf(num + 1).toCharArray());
		return large - num < num - small ? String.valueOf(large) : String.valueOf(small);
	}

	private long getSmall(char[] num) {

		for (int i = 0, n = num.length; i < num.length / 2; i++) {

			while (num[i] != num[n - 1 - i]) {
				decrement(num, n - 1 - i);

				if (num[0] == '0') {
					break;
				}
			}
		}
		return Long.parseLong(new String(num));
	}

	private void decrement(char[] num, int index) {

		while (num[index] == '0') {
			num[index--] = '9';
		}
		num[index]--;
	}

	private long getLarge(char[] num) {

		for (int i = 0, n = num.length; i < num.length / 2; i++) {

			while (num[i] != num[n - 1 - i]) {
				increment(num, n - 1 - i);
			}
		}
		return Long.parseLong(new String(num));
	}

	private void increment(char[] num, int index) {

		while (num[index] == '9') {
			num[index--] = '0';
		}
		num[index]++;
	}
}
