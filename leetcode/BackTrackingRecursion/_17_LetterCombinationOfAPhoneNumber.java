package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
 *
 *
 */

public class _17_LetterCombinationOfAPhoneNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private String numberToLetter[] = {"", "", "abc", "def", "ghi", "jkl", "mno",
			"pqrs", "tuv", "wxyz"};

	public List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<>();
		StringBuilder cache = new StringBuilder();
		recur(digits, cache, ans, 0);
		return ans;
	}

	private void recur(String digits, StringBuilder cache, List<String> ans, int index) {

		if (index == digits.length()) {

			if (cache.length() > 0) {
				ans.add(cache.toString());
			}
			return;
		}
		char digit = digits.charAt(index);
		String letters = numberToLetter[digit - '0'];

		for (char letter : letters.toCharArray()) {
			cache.append(letter);
			recur(digits, cache, ans, index + 1);
			cache.deleteCharAt(cache.length() - 1);
		}
	}
	//Time complexity O(M^n * N) Space complexity N where N is number of digits.
}
